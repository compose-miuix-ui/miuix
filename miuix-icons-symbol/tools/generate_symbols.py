#!/usr/bin/env python3
# Copyright 2026, compose-miuix-ui contributors
# SPDX-License-Identifier: Apache-2.0
"""Generate built-in per-icon VariableSymbol Kotlin sources from HyperOS VF TTF files.

Each icon is emitted to its own `.kt` file under
`miuix-icons-symbol/src/commonMain/kotlin/top/yukonga/miuix/kmp/icon/symbol/`
containing a compact ZigZag+Varint+Base91 representation of the TrueType `glyf`
outline and pre-IUP'd `gvar` deltas (`wght` 150..700, default 330).

Usage:
    python3 miuix-icons-symbol/tools/generate_symbols.py \
        --font-dir "/home/yukonga/Downloads/Telegram Desktop/font"
"""

from __future__ import annotations

import argparse
import os
import re
import struct
from dataclasses import dataclass
from pathlib import Path

BASE91_ALPHABET = (
    "!#%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    "[]^_`abcdefghijklmnopqrstuvwxyz{|}~"
)
assert len(BASE91_ALPHABET) == 91
assert '"' not in BASE91_ALPHABET
assert "$" not in BASE91_ALPHABET
assert "\\" not in BASE91_ALPHABET

FONT_SPECS = [
    ("Core", "misymbol_vf.ttf"),
    ("Content", "HyperOSSymbols-Content-RegularVF.ttf"),
    ("Layout", "HyperOSSymbols-LayoutVF.ttf"),
]


@dataclass
class GlyphRecord:
    category: str
    raw_name: str
    kotlin_name: str
    code_point: int
    advance_width: int
    adv_delta_min: int
    adv_delta_max: int
    ascent: int
    descent: int
    default_color_argb: int | None
    encoded_data: str


def encode_uvarint(buf: bytearray, value: int) -> None:
    assert value >= 0, f"uvarint requires non-negative value, got {value}"
    while True:
        byte = value & 0x7F
        value >>= 7
        if value:
            buf.append(byte | 0x80)
        else:
            buf.append(byte)
            break


def encode_svarint(buf: bytearray, value: int) -> None:
    u = (value << 1) ^ (value >> 31)
    u &= 0xFFFFFFFF
    encode_uvarint(buf, u)


def encode_base91(data: bytes) -> str:
    out: list[str] = []
    b = 0
    n = 0
    for byte in data:
        b |= byte << n
        n += 8
        if n > 13:
            v = b & 8191
            if v > 88:
                b >>= 13
                n -= 13
            else:
                v = b & 16383
                b >>= 14
                n -= 14
            out.append(BASE91_ALPHABET[v % 91])
            out.append(BASE91_ALPHABET[v // 91])
    if n > 0:
        out.append(BASE91_ALPHABET[b % 91])
        if n > 7 or b > 90:
            out.append(BASE91_ALPHABET[b // 91])
    return "".join(out)


RAW_NAME_FIXES: dict[str, str] = {
    "cedit_selection_surface": "credit_selection_surface",
    "xiaomin_listen_surface": "xiaomi_listen_surface",
    "mark_ras_unread": "mark_as_unread",
    "battery_new_1000_Charging_on_surface": "battery_new_100_charging_on_surface",
}

EXCLUDED_RAW_NAMES: set[str] = {
    "search_surface",
}


def to_base_name(raw_name: str) -> str:
    fixed = RAW_NAME_FIXES.get(raw_name, raw_name)
    fixed = re.sub(r"(?i)\bwi_fi\b", "wifi", fixed)
    fixed = fixed.replace("wi_fi_", "wifi_").replace("Wi_Fi_", "Wifi_")
    cleaned = fixed.replace(".", "Point")
    parts = re.split(r"[_\-\s]+", cleaned)
    name = "".join(p[0].upper() + p[1:] for p in parts if p)
    if not re.match(r"^[A-Za-z][A-Za-z0-9]*$", name):
        raise ValueError(f"Invalid Kotlin identifier '{name}' from glyph '{raw_name}'")
    for suffix in ("OnSurface", "Surface", "Line"):
        if name.endswith(suffix) and len(name) > len(suffix):
            name = name[: -len(suffix)]
            break
    return name


def resolve_symbol_names(records: list[GlyphRecord]) -> None:
    """Strip OnSurface/Surface/Line suffixes and disambiguate collisions with 1, 2, 3..."""
    bases = [to_base_name(r.raw_name) for r in records]
    base_counts: dict[str, int] = {}
    for base in bases:
        base_counts[base] = base_counts.get(base, 0) + 1

    used_indices: dict[str, int] = {}
    seen: set[str] = set()
    for rec, base in zip(records, bases):
        if base_counts[base] == 1 and base not in seen:
            final_name = base
        else:
            idx = used_indices.get(base, 0) + 1
            while f"{base}{idx}" in seen:
                idx += 1
            used_indices[base] = idx
            final_name = f"{base}{idx}"
        seen.add(final_name)
        rec.kotlin_name = final_name


def lower_camel_field(kotlin_name: str) -> str:
    return "_" + kotlin_name[0].lower() + kotlin_name[1:]


def read_tables(data: bytes) -> dict[str, tuple[int, int]]:
    _, num_tables = struct.unpack(">IH", data[:6])
    tables: dict[str, tuple[int, int]] = {}
    for i in range(num_tables):
        off = 12 + i * 16
        tag = data[off : off + 4].decode("ascii")
        _, toff, tlen = struct.unpack(">III", data[off + 4 : off + 16])
        tables[tag] = (toff, tlen)
    return tables


def parse_post_names(data: bytes, tables: dict[str, tuple[int, int]]) -> dict[int, str]:
    toff, tlen = tables["post"]
    ver = struct.unpack(">I", data[toff : toff + 4])[0]
    if ver != 0x00020000:
        raise ValueError(f"Unsupported post version 0x{ver:08X}")
    ng = struct.unpack(">H", data[toff + 32 : toff + 34])[0]
    idxs = struct.unpack(f">{ng}H", data[toff + 34 : toff + 34 + ng * 2])
    p = toff + 34 + ng * 2
    end = toff + tlen
    pstrings: list[str] = []
    while p < end:
        length = data[p]
        p += 1
        pstrings.append(data[p : p + length].decode("ascii"))
        p += length
    gid_to_name: dict[int, str] = {}
    for gid, idx in enumerate(idxs):
        if idx >= 258 and (idx - 258) < len(pstrings):
            gid_to_name[gid] = pstrings[idx - 258]
    return gid_to_name


def parse_cmap(data: bytes, tables: dict[str, tuple[int, int]]) -> dict[int, int]:
    toff, _ = tables["cmap"]
    _, nsub = struct.unpack(">HH", data[toff : toff + 4])
    cp_to_gid: dict[int, int] = {}
    for i in range(nsub):
        _, _, suboff = struct.unpack(">HHI", data[toff + 4 + i * 8 : toff + 12 + i * 8])
        st = toff + suboff
        fmt = struct.unpack(">H", data[st : st + 2])[0]
        if fmt == 12:
            ngroups = struct.unpack(">I", data[st + 12 : st + 16])[0]
            for g in range(ngroups):
                s, e, gid = struct.unpack(">III", data[st + 16 + g * 12 : st + 28 + g * 12])
                for cp in range(s, e + 1):
                    cp_to_gid[cp] = gid + (cp - s)
    return cp_to_gid


def parse_colr_cpal(
    data: bytes, tables: dict[str, tuple[int, int]]
) -> dict[int, tuple[int, int]]:
    """Return map of base_gid -> (layer_gid, argb_int) for single-layer COLR v0 glyphs."""
    if "COLR" not in tables or "CPAL" not in tables:
        return {}
    coff, _ = tables["CPAL"]
    _, _, _, ncr, first_cr_off = struct.unpack(">HHHHI", data[coff : coff + 12])
    palette: list[int] = []
    for i in range(ncr):
        b, g, r, a = struct.unpack(">BBBB", data[coff + first_cr_off + i * 4 : coff + first_cr_off + i * 4 + 4])
        palette.append((a << 24) | (r << 16) | (g << 8) | b)

    toff, _ = tables["COLR"]
    ver, nbase, base_off, layer_off, _ = struct.unpack(">HHIIH", data[toff : toff + 14])
    if ver != 0:
        return {}
    out: dict[int, tuple[int, int]] = {}
    for i in range(nbase):
        gid, first_l, num_l = struct.unpack(">HHH", data[toff + base_off + i * 6 : toff + base_off + i * 6 + 6])
        if num_l == 1:
            lgid, pidx = struct.unpack(">HH", data[toff + layer_off + first_l * 4 : toff + layer_off + first_l * 4 + 4])
            if pidx < len(palette):
                out[gid] = (lgid, palette[pidx])
    return out


def decode_glyf(
    data: bytes, tables: dict[str, tuple[int, int]], gid: int, idx_to_loc: int
) -> tuple[list[int], list[tuple[int, int]], list[bool]]:
    loff, _ = tables["loca"]
    goff, _ = tables["glyf"]
    if idx_to_loc == 0:
        o1 = struct.unpack(">H", data[loff + gid * 2 : loff + gid * 2 + 2])[0] * 2
        o2 = struct.unpack(">H", data[loff + gid * 2 + 2 : loff + gid * 2 + 4])[0] * 2
    else:
        o1, o2 = struct.unpack(">II", data[loff + gid * 4 : loff + gid * 4 + 8])
    if o1 == o2:
        return [], [], []
    p = goff + o1
    nc = struct.unpack(">h", data[p : p + 2])[0]
    if nc < 0:
        raise ValueError(f"Composite glyph gid={gid} is not supported")
    p += 10
    end_pts = list(struct.unpack(f">{nc}H", data[p : p + nc * 2]))
    p += nc * 2
    instr_len = struct.unpack(">H", data[p : p + 2])[0]
    p += 2 + instr_len
    npts = end_pts[-1] + 1
    flags: list[int] = []
    while len(flags) < npts:
        fl = data[p]
        p += 1
        flags.append(fl)
        if fl & 0x08:
            rep = data[p]
            p += 1
            flags.extend([fl] * rep)
    xs: list[int] = []
    x = 0
    for fl in flags:
        if fl & 0x02:
            dx = data[p]
            p += 1
            x += dx if (fl & 0x10) else -dx
        elif not (fl & 0x10):
            dx = struct.unpack(">h", data[p : p + 2])[0]
            p += 2
            x += dx
        xs.append(x)
    ys: list[int] = []
    y = 0
    for fl in flags:
        if fl & 0x04:
            dy = data[p]
            p += 1
            y += dy if (fl & 0x20) else -dy
        elif not (fl & 0x20):
            dy = struct.unpack(">h", data[p : p + 2])[0]
            p += 2
            y += dy
        ys.append(y)
    on_curve = [bool(fl & 1) for fl in flags]
    return end_pts, list(zip(xs, ys)), on_curve


def read_packed_points(buf: bytes, pos: int) -> tuple[list[int] | None, int]:
    count = buf[pos]
    pos += 1
    if count == 0:
        return None, pos
    if count & 0x80:
        count = ((count & 0x7F) << 8) | buf[pos]
        pos += 1
    pts: list[int] = []
    cur = 0
    while len(pts) < count:
        ctrl = buf[pos]
        pos += 1
        run = (ctrl & 0x7F) + 1
        is16 = bool(ctrl & 0x80)
        for _ in range(run):
            if is16:
                d = struct.unpack(">H", buf[pos : pos + 2])[0]
                pos += 2
            else:
                d = buf[pos]
                pos += 1
            cur += d
            pts.append(cur)
    return pts, pos


def read_packed_deltas(buf: bytes, pos: int, count: int) -> tuple[list[int], int]:
    deltas: list[int] = []
    while len(deltas) < count:
        ctrl = buf[pos]
        pos += 1
        run = (ctrl & 0x3F) + 1
        if ctrl & 0x80:
            deltas.extend([0] * run)
        elif ctrl & 0x40:
            for _ in range(run):
                deltas.append(struct.unpack(">h", buf[pos : pos + 2])[0])
                pos += 2
        else:
            for _ in range(run):
                deltas.append(struct.unpack(">b", buf[pos : pos + 1])[0])
                pos += 1
    return deltas, pos


def iup_contour_1d(coords: list[int], deltas: list[int | None]) -> list[int]:
    """OpenType gvar IUP (Interpolate Untouched Points) along one axis for a single contour."""
    n = len(coords)
    touched = [i for i, d in enumerate(deltas) if d is not None]
    if not touched:
        return [0] * n
    if len(touched) == 1:
        val = deltas[touched[0]]
        assert val is not None
        return [val] * n

    result = [0] * n
    for i in touched:
        d = deltas[i]
        assert d is not None
        result[i] = d

    num_t = len(touched)
    for k in range(num_t):
        p = touched[k]
        q = touched[(k + 1) % num_t]
        c1, c2 = coords[p], coords[q]
        d1, d2 = result[p], result[q]

        # Walk cyclically from p+1 to q-1
        idx = (p + 1) % n
        while idx != q:
            c = coords[idx]
            if c1 == c2:
                result[idx] = d1 if d1 == d2 else 0
            else:
                if c1 < c2:
                    c_lo, d_lo, c_hi, d_hi = c1, d1, c2, d2
                else:
                    c_lo, d_lo, c_hi, d_hi = c2, d2, c1, d1
                if c <= c_lo:
                    result[idx] = d_lo
                elif c >= c_hi:
                    result[idx] = d_hi
                else:
                    t = (c - c_lo) / (c_hi - c_lo)
                    result[idx] = int(round(d_lo + t * (d_hi - d_lo)))
            idx = (idx + 1) % n
    return result


def resolve_full_deltas(
    end_pts: list[int],
    pts: list[tuple[int, int]],
    point_indices: list[int] | None,
    dxs: list[int],
    dys: list[int],
) -> tuple[list[int], list[int], int]:
    """Return full per-point (dx, dy) after OpenType IUP plus advanceWidth phantom delta."""
    npts = len(pts)
    if point_indices is None:
        full_dx = dxs[:npts]
        full_dy = dys[:npts]
        lsb_dx = dxs[npts] if len(dxs) > npts else 0
        aw_dx = dxs[npts + 1] if len(dxs) > npts + 1 else 0
        return full_dx, full_dy, aw_dx - lsb_dx

    sparse_dx: list[int | None] = [None] * npts
    sparse_dy: list[int | None] = [None] * npts
    lsb_dx = 0
    aw_dx = 0
    for idx_i, pt_idx in enumerate(point_indices):
        if pt_idx < npts:
            sparse_dx[pt_idx] = dxs[idx_i]
            sparse_dy[pt_idx] = dys[idx_i]
        elif pt_idx == npts:
            lsb_dx = dxs[idx_i]
        elif pt_idx == npts + 1:
            aw_dx = dxs[idx_i]

    full_dx = [0] * npts
    full_dy = [0] * npts
    start = 0
    for end in end_pts:
        c_xs = [pts[i][0] for i in range(start, end + 1)]
        c_ys = [pts[i][1] for i in range(start, end + 1)]
        r_dx = iup_contour_1d(c_xs, sparse_dx[start : end + 1])
        r_dy = iup_contour_1d(c_ys, sparse_dy[start : end + 1])
        full_dx[start : end + 1] = r_dx
        full_dy[start : end + 1] = r_dy
        start = end + 1

    return full_dx, full_dy, aw_dx - lsb_dx


def encode_outline(
    end_pts: list[int],
    pts: list[tuple[int, int]],
    on_curve: list[bool],
    dx_min: list[int],
    dy_min: list[int],
    dx_max: list[int],
    dy_max: list[int],
) -> str:
    npts = len(pts)
    buf = bytearray()
    encode_uvarint(buf, len(end_pts))
    encode_uvarint(buf, npts)

    prev_end = -1
    for end in end_pts:
        encode_uvarint(buf, end - prev_end)
        prev_end = end

    on_bits = bytearray((npts + 7) // 8)
    for i, flag in enumerate(on_curve):
        if flag:
            on_bits[i >> 3] |= 1 << (i & 7)
    buf.extend(on_bits)

    px = py = 0
    for x, y in pts:
        encode_svarint(buf, x - px)
        encode_svarint(buf, y - py)
        px, py = x, y

    for dx, dy in zip(dx_min, dy_min):
        encode_svarint(buf, dx)
        encode_svarint(buf, dy)

    for dx, dy in zip(dx_max, dy_max):
        encode_svarint(buf, dx)
        encode_svarint(buf, dy)

    return encode_base91(bytes(buf))


def parse_font_records(category: str, path: Path) -> list[GlyphRecord]:
    data = path.read_bytes()
    tables = read_tables(data)

    hoff, _ = tables["head"]
    idx_to_loc = struct.unpack(">h", data[hoff + 50 : hoff + 52])[0]

    hhoff, _ = tables["hhea"]
    ascent, descent, _ = struct.unpack(">hhh", data[hhoff + 4 : hhoff + 10])
    n_hmetrics = struct.unpack(">H", data[hhoff + 34 : hhoff + 36])[0]

    moff, _ = tables["maxp"]
    num_glyphs = struct.unpack(">H", data[moff + 4 : moff + 6])[0]

    hmoff, _ = tables["hmtx"]
    adv_widths: list[int] = []
    last_aw = 0
    for gid in range(num_glyphs):
        if gid < n_hmetrics:
            aw, _ = struct.unpack(">Hh", data[hmoff + gid * 4 : hmoff + gid * 4 + 4])
            last_aw = aw
        adv_widths.append(last_aw)

    gid_to_name = parse_post_names(data, tables)
    cp_to_gid = parse_cmap(data, tables)
    colr_map = parse_colr_cpal(data, tables)

    # Parse gvar header & shared tuples
    gvoff, _ = tables["gvar"]
    _, _, axis_count, shared_cnt, shared_off, glyph_cnt, gflags, gvd_off = struct.unpack(
        ">HHHHIHHI", data[gvoff : gvoff + 20]
    )
    assert axis_count == 1, f"Expected single wght axis, got {axis_count}"
    shared_tuples: list[float] = []
    for i in range(shared_cnt):
        val = struct.unpack(">h", data[gvoff + shared_off + i * 2 : gvoff + shared_off + i * 2 + 2])[0] / 16384.0
        shared_tuples.append(val)

    g_offsets = [
        struct.unpack(">I", data[gvoff + 20 + i * 4 : gvoff + 24 + i * 4])[0]
        if (gflags & 1)
        else struct.unpack(">H", data[gvoff + 20 + i * 2 : gvoff + 22 + i * 2])[0] * 2
        for i in range(glyph_cnt + 1)
    ]

    records: list[GlyphRecord] = []
    for cp, base_gid in sorted(cp_to_gid.items()):
        raw_name = gid_to_name[base_gid]
        if raw_name in EXCLUDED_RAW_NAMES:
            continue
        kotlin_name = raw_name

        # If single-layer COLR v0 glyph, use its layer glyph outline + palette color
        if base_gid in colr_map:
            gid, default_color_argb = colr_map[base_gid]
        else:
            gid = base_gid
            default_color_argb = None

        end_pts, pts, on_curve = decode_glyf(data, tables, gid, idx_to_loc)
        npts = len(pts)
        dx_min = [0] * npts
        dy_min = [0] * npts
        dx_max = [0] * npts
        dy_max = [0] * npts
        adv_d_min = 0
        adv_d_max = 0

        if g_offsets[gid] != g_offsets[gid + 1]:
            p = gvoff + gvd_off + g_offsets[gid]
            tc_raw, data_off = struct.unpack(">HH", data[p : p + 4])
            tc = tc_raw & 0x0FFF
            dp = p + data_off
            shared_pts = None
            if tc_raw & 0x8000:
                shared_pts, dp = read_packed_points(data, dp)
            hp = p + 4
            for _ in range(tc):
                vsz, tidx = struct.unpack(">HH", data[hp : hp + 4])
                hp += 4
                if tidx & 0x8000:
                    peak = struct.unpack(">h", data[hp : hp + 2])[0] / 16384.0
                    hp += 2
                else:
                    peak = shared_tuples[tidx & 0x0FFF]
                if tidx & 0x4000:
                    raise ValueError(f"Unexpected intermediate region in gid={gid}")

                tdp = dp
                if tidx & 0x2000:
                    tpts, tdp = read_packed_points(data, tdp)
                else:
                    tpts = shared_pts
                cnt = (npts + 4) if tpts is None else len(tpts)
                dxs, tdp = read_packed_deltas(data, tdp, cnt)
                dys, tdp = read_packed_deltas(data, tdp, cnt)
                dp += vsz

                f_dx, f_dy, d_aw = resolve_full_deltas(end_pts, pts, tpts, dxs, dys)
                if peak < 0:
                    dx_min, dy_min, adv_d_min = f_dx, f_dy, d_aw
                elif peak > 0:
                    dx_max, dy_max, adv_d_max = f_dx, f_dy, d_aw

        encoded = encode_outline(end_pts, pts, on_curve, dx_min, dy_min, dx_max, dy_max)
        records.append(
            GlyphRecord(
                category=category,
                raw_name=raw_name,
                kotlin_name=kotlin_name,
                code_point=cp,
                advance_width=adv_widths[base_gid],
                adv_delta_min=adv_d_min,
                adv_delta_max=adv_d_max,
                ascent=ascent,
                descent=descent,
                default_color_argb=default_color_argb,
                encoded_data=encoded,
            )
        )
    return records


def format_symbol_file(rec: GlyphRecord) -> str:
    field = lower_camel_field(rec.kotlin_name)
    imports = [
        "top.yukonga.miuix.kmp.icon.MiuixIcons",
        "top.yukonga.miuix.kmp.symbol.SymbolCategory",
        "top.yukonga.miuix.kmp.symbol.VariableSymbol",
    ]
    if rec.default_color_argb is not None:
        imports.insert(0, "androidx.compose.ui.graphics.Color")

    args = [
        f'name = "{rec.kotlin_name}"',
        f"category = SymbolCategory.{rec.category}",
        f'encodedData = "{rec.encoded_data}"',
    ]
    if rec.advance_width != 1330:
        args.append(f"advanceWidth = {rec.advance_width}")
    if rec.adv_delta_min != 0:
        args.append(f"advanceWidthDeltaMin = {rec.adv_delta_min}")
    if rec.adv_delta_max != 0:
        args.append(f"advanceWidthDeltaMax = {rec.adv_delta_max}")
    if rec.ascent != 1040:
        args.append(f"ascent = {rec.ascent}")
    if rec.descent != -290:
        args.append(f"descent = {rec.descent}")
    if rec.default_color_argb is not None:
        args.append(f"defaultColor = Color(0x{rec.default_color_argb:08X})")

    imports_block = "\n".join(f"import {imp}" for imp in imports)
    args_block = ",\n        ".join(args)

    return (
        "// Copyright 2026, compose-miuix-ui contributors\n"
        "// SPDX-License-Identifier: Apache-2.0\n"
        "\n"
        "package top.yukonga.miuix.kmp.icon.symbol\n"
        "\n"
        f"{imports_block}\n"
        "\n"
        f"val MiuixIcons.Symbol.{rec.kotlin_name}: VariableSymbol\n"
        "    get() = "
        f"{field} ?: VariableSymbol(\n"
        f"        {args_block},\n"
        f"    ).also {{ {field} = it }}\n"
        "\n"
        f"private var {field}: VariableSymbol? = null\n"
    )


def format_example_catalog(records: list[GlyphRecord]) -> str:
    sorted_recs = sorted(records, key=lambda r: r.kotlin_name)
    imports = "\n".join(
        f"import top.yukonga.miuix.kmp.icon.symbol.{r.kotlin_name}" for r in sorted_recs
    )
    entries = "\n".join(f"        MiuixIcons.Symbol.{r.kotlin_name}," for r in sorted_recs)
    return (
        "// Copyright 2026, compose-miuix-ui contributors\n"
        "// SPDX-License-Identifier: Apache-2.0\n"
        "\n"
        "package utils\n"
        "\n"
        "import top.yukonga.miuix.kmp.icon.MiuixIcons\n"
        f"{imports}\n"
        "import top.yukonga.miuix.kmp.symbol.VariableSymbol\n"
        "\n"
        "val AllVariableSymbols: List<VariableSymbol> by lazy {\n"
        "    listOf(\n"
        f"{entries}\n"
        "    )\n"
        "}\n"
    )


def main() -> None:
    parser = argparse.ArgumentParser(description="Generate built-in VariableSymbol Kotlin files.")
    parser.add_argument(
        "--font-dir",
        required=True,
        type=Path,
        help="Directory containing the HyperOS VF .ttf files.",
    )
    parser.add_argument(
        "--repo-root",
        type=Path,
        default=Path(__file__).resolve().parents[2],
        help="Repository root directory.",
    )
    args = parser.parse_args()

    out_dir = (
        args.repo_root
        / "miuix-icons-symbol/src/commonMain/kotlin/top/yukonga/miuix/kmp/icon/symbol"
    )
    out_dir.mkdir(parents=True, exist_ok=True)

    all_records: list[GlyphRecord] = []
    for category, filename in FONT_SPECS:
        font_path = args.font_dir / filename
        if not font_path.is_file():
            raise FileNotFoundError(f"Missing expected variable font: {font_path}")
        recs = parse_font_records(category, font_path)
        all_records.extend(recs)
        print(f"Parsed {category:8s} ({filename}): {len(recs)} symbols")

    resolve_symbol_names(all_records)
    expected_files = {f"{rec.kotlin_name}.kt" for rec in all_records}
    for existing in out_dir.glob("*.kt"):
        if existing.name not in expected_files:
            existing.unlink()

    total_encoded_chars = 0
    for rec in all_records:
        total_encoded_chars += len(rec.encoded_data)
        (out_dir / f"{rec.kotlin_name}.kt").write_text(format_symbol_file(rec), encoding="utf-8")

    example_catalog = args.repo_root / "example/shared/src/commonMain/kotlin/utils/SymbolIcons.kt"
    example_catalog.write_text(format_example_catalog(all_records), encoding="utf-8")

    print(
        f"Generated {len(all_records)} symbol files in {out_dir}\n"
        f"Total encoded payload: {total_encoded_chars / 1024:.1f} KiB "
        f"(avg {total_encoded_chars / len(all_records):.0f} chars/icon)\n"
        f"Updated example catalog: {example_catalog}"
    )


if __name__ == "__main__":
    main()
