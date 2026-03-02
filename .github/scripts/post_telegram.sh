if [ -z "${BOT_TOKEN}" ]; then
    exit 0
fi

BOT_API_URL="${BOT_API_LINK:-https://api.telegram.org}"

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

text = os.environ.get("COMMIT_MESSAGE", "")
max_len = 1024
if len(text) > max_len:
    text = text[: max_len - 3] + "..."
escape_chars = r"_*[]()~`>#+-=|{}.!\\"
escaped = []
for ch in text:
    if ch in escape_chars:
        escaped.append("\\" + ch)
    else:
        escaped.append(ch)
text = "".join(escaped)

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
