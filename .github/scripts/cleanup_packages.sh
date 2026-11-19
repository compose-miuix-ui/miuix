#!/usr/bin/env bash
set -euo pipefail

MODE="${1:-}"
KEEP_SNAPSHOTS="${KEEP_SNAPSHOTS:-10}"
OWNER="${GITHUB_REPOSITORY_OWNER:-}"
REPO_FULL="${GITHUB_REPOSITORY:-}"
REPO_NAME="${REPO_FULL#*/}"
TOKEN="${GITHUB_TOKEN:-}"

if [ -z "$TOKEN" ] || [ -z "$OWNER" ] || [ -z "$REPO_NAME" ]; then
    echo "Missing GITHUB_TOKEN, GITHUB_REPOSITORY_OWNER, or GITHUB_REPOSITORY; skipping package cleanup."
    exit 0
fi

SHORT_HASH=""
if [ "$MODE" = "failed" ]; then
    SHORT_HASH=$(git rev-parse --short HEAD 2>/dev/null || true)
    if [ -z "$SHORT_HASH" ]; then
        echo "Could not determine short git hash; skipping failed-run cleanup."
        exit 0
    fi
fi

export MODE KEEP_SNAPSHOTS OWNER REPO_NAME TOKEN SHORT_HASH

python3 - <<'PY'
import json
import os
import urllib.error
import urllib.parse
import urllib.request

API = os.environ.get("GITHUB_API_URL", "https://api.github.com").rstrip("/")
TOKEN = os.environ["TOKEN"]
OWNER = os.environ["OWNER"]
REPO_NAME = os.environ["REPO_NAME"]
MODE = os.environ["MODE"]
if MODE not in ("failed", "prune"):
    print(f"Unknown mode: {MODE}")
    raise SystemExit(1)
SHORT_HASH = os.environ.get("SHORT_HASH", "")
KEEP_SNAPSHOTS = int(os.environ.get("KEEP_SNAPSHOTS", "10"))
HEADERS = {
    "Accept": "application/vnd.github+json",
    "Authorization": f"Bearer {TOKEN}",
    "X-GitHub-Api-Version": "2026-03-10",
    "User-Agent": "miuix-package-cleanup",
}


def request(method, path, payload=None):
    url = f"{API}{path}"
    data = json.dumps(payload).encode("utf-8") if payload is not None else None
    headers = dict(HEADERS)
    if data is not None:
        headers["Content-Type"] = "application/json"
    req = urllib.request.Request(url, data=data, method=method, headers=headers)
    try:
        with urllib.request.urlopen(req, timeout=30) as resp:
            body = resp.read().decode("utf-8")
            return resp.status, json.loads(body) if body else None
    except urllib.error.HTTPError as e:
        body = e.read().decode("utf-8", errors="replace")
        return e.code, body
    except (urllib.error.URLError, TimeoutError) as e:
        return 0, str(e)


def paginate(base_path):
    items = []
    page = 1
    sep = "&" if "?" in base_path else "?"
    while True:
        status, data = request("GET", f"{base_path}{sep}per_page=100&page={page}")
        if status != 200 or not isinstance(data, list):
            if page == 1:
                return status, None
            break
        if not data:
            break
        items.extend(data)
        if len(data) < 100:
            break
        page += 1
    return 200, items
# Query organization Maven packages linked to this repository.
scope_prefix = f"/orgs/{urllib.parse.quote(OWNER, safe='')}"
status, packages = paginate(f"{scope_prefix}/packages?package_type=maven")
if status != 200 or packages is None:
    print(f"Warn: failed to list packages for {OWNER}: HTTP {status}")
    raise SystemExit(0)

repo_package_names = [
    p["name"] for p in packages
    if (p.get("repository") or {}).get("name") == REPO_NAME
]
if not repo_package_names:
    print("No Maven packages found.")
    raise SystemExit(0)

print(f"Found {len(repo_package_names)} Maven packages for {OWNER}/{REPO_NAME} (mode={MODE}).")

deleted_versions = 0
deleted_packages = 0

for pkg_name in repo_package_names:
    pkg_q = urllib.parse.quote(pkg_name, safe="")
    v_status, versions = paginate(f"{scope_prefix}/packages/maven/{pkg_q}/versions")
    if v_status != 200 or not versions:
        if v_status not in (200, 404):
            print(f"Warn: failed to list versions for {pkg_name}: {v_status}")
        continue
    if MODE == "failed":
        suffix = f"-{SHORT_HASH}-SNAPSHOT"
        targets = [v for v in versions if v.get("name", "").endswith(suffix)]
    elif MODE == "prune":
        snapshots = [v for v in versions if v.get("name", "").endswith("-SNAPSHOT")]
        snapshots.sort(key=lambda v: v.get("created_at", ""), reverse=True)
        targets = snapshots[KEEP_SNAPSHOTS:]

    for v in targets:
        vid = v["id"]
        vname = v.get("name", "")
        status, body = request("DELETE", f"{scope_prefix}/packages/maven/{pkg_q}/versions/{vid}")
        if status in (200, 204):
            deleted_versions += 1
            print(f"Deleted {pkg_name}:{vname} (id={vid})")
        elif status == 400 and MODE == "failed" and len(versions) == 1:
            p_status, p_body = request("DELETE", f"{scope_prefix}/packages/maven/{pkg_q}")
            if p_status in (200, 204):
                deleted_packages += 1
                print(f"Deleted single-version package {pkg_name} ({vname})")
            else:
                print(f"Warn: failed to delete package {pkg_name}: {p_status} {p_body}")
        elif status == 404:
            pass
        else:
            print(f"Warn: failed to delete {pkg_name}:{vname}: {status} {body}")

print(f"Cleanup finished: deleted_versions={deleted_versions}, deleted_packages={deleted_packages}")
PY
