if [ -z "${BOT_TOKEN}" ]; then
    exit 0
fi

BOT_API_URL="${BOT_API_LINK:-https://api.telegram.org}"

# Get Version Info
VERSION_NAME=$(grep 'const val APPLICATION_VERSION_NAME' build-plugins/src/main/kotlin/BuildConfig.kt | cut -d '"' -f 2)
SHORT_HASH=$(git rev-parse --short HEAD)
COMMIT_MSG_BODY=$(git log -1 --pretty=%B)
COMMIT_COUNT=$(git rev-list --count HEAD)

export VERSION_NAME
export SHORT_HASH
export COMMIT_MSG_BODY
export COMMIT_COUNT

root_dir="$(pwd)"
packaged_dir="${root_dir}/packaged"
mkdir -p "$packaged_dir"
for artifact in artifacts/*; do
    if [ -d "$artifact" ]; then
        file_count=$(find "$artifact" -type f | wc -l | tr -d ' ')
        dir_count=$(find "$artifact" -type d | wc -l | tr -d ' ')
        if [ "$file_count" -eq 1 ] && [ "$dir_count" -eq 1 ]; then
            file=$(find "$artifact" -type f | head -n 1)
            filename=$(basename "$file")
            if [ -n "$COMMIT_COUNT" ]; then
                extension="${filename##*.}"
                if [ "$filename" == "$extension" ]; then
                    new_filename="${filename}-${COMMIT_COUNT}"
                else
                    filename_no_ext="${filename%.*}"
                    new_filename="${filename_no_ext}-${COMMIT_COUNT}.${extension}"
                fi
                cp "$file" "${packaged_dir}/${new_filename}"
            else
                cp "$file" "${packaged_dir}/${filename}"
            fi
        else
            name=$(basename "$artifact")
            if [ -n "$COMMIT_COUNT" ]; then
                zip_name="${name}-${COMMIT_COUNT}.zip"
            else
                zip_name="${name}.zip"
            fi
            (cd "$artifact" && zip -qr "${packaged_dir}/${zip_name}" .)
        fi
    elif [ -f "$artifact" ]; then
        filename=$(basename "$artifact")
        if [ -n "$COMMIT_COUNT" ]; then
            extension="${filename##*.}"
            if [ "$filename" == "$extension" ]; then
                new_filename="${filename}-${COMMIT_COUNT}"
            else
                filename_no_ext="${filename%.*}"
                new_filename="${filename_no_ext}-${COMMIT_COUNT}.${extension}"
            fi
            cp "$artifact" "${packaged_dir}/${new_filename}"
        else
            cp "$artifact" "${packaged_dir}/${filename}"
        fi
    fi
done

python3 - <<'PY'
import json
import os

def escape_md(text):
    escape_chars = r"_*[]()~`>#+-=|{}.!\\"
    escaped = []
    for ch in text:
        if ch in escape_chars:
            escaped.append("\\" + ch)
        else:
            escaped.append(ch)
    return "".join(escaped)

def escape_code(text):
    return text.replace("\\", "\\\\").replace("`", "\\`")

repo_name = os.environ.get("GITHUB_REPOSITORY", "Unknown")
version = os.environ.get("VERSION_NAME", "Unknown")
short_hash = os.environ.get("SHORT_HASH", "Unknown")
commit_count = os.environ.get("COMMIT_COUNT", "0")
commit_msg = os.environ.get("COMMIT_MSG_BODY", "No commit message")

max_len = 900
if len(commit_msg) > max_len:
    commit_msg = commit_msg[:max_len] + "..."

header = f"New CI from {repo_name}"
version_line = f"Version: v{version}-{short_hash} ({commit_count})"
msg_label = "Commit message:"

text = f"{escape_md(header)}\n\n{escape_md(version_line)}\n\n{escape_md(msg_label)}\n```\n{escape_code(commit_msg)}\n```"

max_batch_files = int(os.environ.get("TELEGRAM_MAX_BATCH_FILES", "10"))

files = []
for name in sorted(os.listdir("packaged")):
    path = os.path.join("packaged", name)
    if os.path.isfile(path):
        files.append((name, os.path.getsize(path)))

batches = []
current = []
for name, size in files:
    if len(current) >= max_batch_files:
        batches.append(current)
        current = []
    current.append((name, size))
if current:
    batches.append(current)

os.makedirs("telegram_batches", exist_ok=True)
with open("caption.txt", "w", encoding="utf-8") as f:
    f.write(text)
with open("batches.list", "w", encoding="utf-8") as f:
    for i, batch in enumerate(batches):
        media = []
        for index, (name, _) in enumerate(batch):
            item = {"type": "document", "media": f"attach://file{index}"}
            if i == 0 and index == len(batch) - 1 and text:
                item["caption"] = text
                item["parse_mode"] = "MarkdownV2"
            media.append(item)
        media_path = os.path.join("telegram_batches", f"media_{i}.json")
        files_path = os.path.join("telegram_batches", f"files_{i}.txt")
        with open(media_path, "w", encoding="utf-8") as mf:
            json.dump(media, mf)
        with open(files_path, "w", encoding="utf-8") as ff:
            ff.write("\n".join([name for name, _ in batch]) + "\n")
        f.write(f"{media_path}|{files_path}\n")
PY

caption_text=$(cat caption.txt)
batches_count=$(wc -l <batches.list | tr -d ' ')
if [ "$batches_count" -eq 0 ]; then
    exit 0
fi

while IFS= read -r line; do
    [ -z "$line" ] && continue
    media_path="${line%%|*}"
    files_path="${line##*|}"
    args=()
    index=0
    while IFS= read -r name; do
        if [ -n "$name" ]; then
            args+=(-F "file${index}=@packaged/${name}")
            index=$((index + 1))
        fi
    done <"$files_path"
    curl -s "${BOT_API_URL}/bot${BOT_TOKEN}/sendMediaGroup" \
        -F "chat_id=${CHANNEL_ID}" \
        -F "media=$(cat "$media_path")" \
        "${args[@]}"
done <batches.list
