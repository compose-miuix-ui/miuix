# 图标系统

Miuix 提供了一套丰富的内置图标系统，满足大多数应用的主要设计需求。这些图标提供三种粗细：Light、Regular 和 Heavy。

## 安装

基础的 `miuix` 库仅包含少量核心图标。如需使用完整的 Miuix 图标库，您需要将 `miuix-icons` 依赖添加到您的项目中。

```kotlin
// build.gradle.kts
commonMain.dependencies {
    implementation("top.yukonga.miuix.kmp:miuix:<version>")
    // 添加扩展图标库
    implementation("top.yukonga.miuix.kmp:miuix-icons:<version>")
}
```

## 使用方法

要在项目中使用 Miuix 图标，首先需要正确导入：

```kotlin
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.icons.Ok
import top.yukonga.miuix.kmp.icon.icons.Settings
```

然后，你可以像下面这样使用图标：

```kotlin
// 使用 Ok 图标 (默认为 Regular 粗细)
Icon(
    imageVector = MiuixIcons.Ok,
    contentDescription = "Ok",
    modifier = Modifier.size(24.dp)
)

// 使用 Settings 图标
Icon(
    imageVector = MiuixIcons.Settings,
    contentDescription = "Settings",
    modifier = Modifier.size(24.dp)
)

// 使用特定粗细 (例如 Light)
Icon(
    imageVector = MiuixIcons.Light.Settings,
    contentDescription = "Settings (Light)",
    modifier = Modifier.size(24.dp)
)
```

## 图标粗细

Miuix 图标支持三种粗细：
- `MiuixIcons.Light`: 细图标 (250)
- `MiuixIcons.Regular`: 常规图标 (330) - 直接访问 `MiuixIcons.IconName` 时的默认值
- `MiuixIcons.Heavy`: 粗图标 (500)

## 可用图标

### Basic（基础图标）

基础图标包含一些常用的基本界面元素，如箭头、勾选等，这些图标在 Miuix 本身的组件中也会使用到。以下是完整的列表：

| 图标名称 | Light | Regular | Heavy |
|---|---|---|---|
| ArrowRight | - | <img src="/icons/basic/ArrowRight.svg" width="24" height="24" /> | - |
| ArrowUpDown | - | <img src="/icons/basic/ArrowUpDown.svg" width="24" height="24" /> | - |
| Check | - | <img src="/icons/basic/Check.svg" width="24" height="24" /> | - |
| Search | - | <img src="/icons/basic/Search.svg" width="24" height="24" /> | - |
| SearchCleanup | - | <img src="/icons/basic/SearchCleanup.svg" width="24" height="24" /> | - |

### Extended (扩展图标)

扩展图标包含更多场景下的图标。以下是完整的列表：

| 图标名称 | Light | Regular | Heavy |
|---|---|---|---|
| Add | <img src="/icons/extended/Add.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Add.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Add.Heavy.svg" width="24" height="24" /> |
| AddCircle | <img src="/icons/extended/AddCircle.Light.svg" width="24" height="24" /> | <img src="/icons/extended/AddCircle.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/AddCircle.Heavy.svg" width="24" height="24" /> |
| AddFolder | <img src="/icons/extended/AddFolder.Light.svg" width="24" height="24" /> | <img src="/icons/extended/AddFolder.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/AddFolder.Heavy.svg" width="24" height="24" /> |
| Alarm | <img src="/icons/extended/Alarm.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Alarm.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Alarm.Heavy.svg" width="24" height="24" /> |
| Album | <img src="/icons/extended/Album.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Album.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Album.Heavy.svg" width="24" height="24" /> |
| All | <img src="/icons/extended/All.Light.svg" width="24" height="24" /> | <img src="/icons/extended/All.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/All.Heavy.svg" width="24" height="24" /> |
| Answer | <img src="/icons/extended/Answer.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Answer.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Answer.Heavy.svg" width="24" height="24" /> |
| AppRecording | <img src="/icons/extended/AppRecording.Light.svg" width="24" height="24" /> | <img src="/icons/extended/AppRecording.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/AppRecording.Heavy.svg" width="24" height="24" /> |
| Back | <img src="/icons/extended/Back.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Back.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Back.Heavy.svg" width="24" height="24" /> |
| Background | <img src="/icons/extended/Background.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Background.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Background.Heavy.svg" width="24" height="24" /> |
| Backup | <img src="/icons/extended/Backup.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Backup.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Backup.Heavy.svg" width="24" height="24" /> |
| BankCards | <img src="/icons/extended/BankCards.Light.svg" width="24" height="24" /> | <img src="/icons/extended/BankCards.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/BankCards.Heavy.svg" width="24" height="24" /> |
| Blocklist | <img src="/icons/extended/Blocklist.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Blocklist.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Blocklist.Heavy.svg" width="24" height="24" /> |
| CallRecording | <img src="/icons/extended/CallRecording.Light.svg" width="24" height="24" /> | <img src="/icons/extended/CallRecording.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/CallRecording.Heavy.svg" width="24" height="24" /> |
| Carrier | <img src="/icons/extended/Carrier.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Carrier.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Carrier.Heavy.svg" width="24" height="24" /> |
| ChevronBackward | <img src="/icons/extended/ChevronBackward.Light.svg" width="24" height="24" /> | <img src="/icons/extended/ChevronBackward.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/ChevronBackward.Heavy.svg" width="24" height="24" /> |
| ChevronForward | <img src="/icons/extended/ChevronForward.Light.svg" width="24" height="24" /> | <img src="/icons/extended/ChevronForward.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/ChevronForward.Heavy.svg" width="24" height="24" /> |
| Clear | <img src="/icons/extended/Clear.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Clear.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Clear.Heavy.svg" width="24" height="24" /> |
| Close | <img src="/icons/extended/Close.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Close.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Close.Heavy.svg" width="24" height="24" /> |
| Close2 | <img src="/icons/extended/Close2.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Close2.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Close2.Heavy.svg" width="24" height="24" /> |
| CloudFill | <img src="/icons/extended/CloudFill.Light.svg" width="24" height="24" /> | <img src="/icons/extended/CloudFill.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/CloudFill.Heavy.svg" width="24" height="24" /> |
| Community | <img src="/icons/extended/Community.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Community.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Community.Heavy.svg" width="24" height="24" /> |
| Contacts | <img src="/icons/extended/Contacts.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Contacts.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Contacts.Heavy.svg" width="24" height="24" /> |
| ContactsBook | <img src="/icons/extended/ContactsBook.Light.svg" width="24" height="24" /> | <img src="/icons/extended/ContactsBook.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/ContactsBook.Heavy.svg" width="24" height="24" /> |
| ContactsCircle | <img src="/icons/extended/ContactsCircle.Light.svg" width="24" height="24" /> | <img src="/icons/extended/ContactsCircle.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/ContactsCircle.Heavy.svg" width="24" height="24" /> |
| ConvertFile | <img src="/icons/extended/ConvertFile.Light.svg" width="24" height="24" /> | <img src="/icons/extended/ConvertFile.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/ConvertFile.Heavy.svg" width="24" height="24" /> |
| Copy | <img src="/icons/extended/Copy.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Copy.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Copy.Heavy.svg" width="24" height="24" /> |
| Create | <img src="/icons/extended/Create.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Create.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Create.Heavy.svg" width="24" height="24" /> |
| Cut | <img src="/icons/extended/Cut.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Cut.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Cut.Heavy.svg" width="24" height="24" /> |
| Delete | <img src="/icons/extended/Delete.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Delete.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Delete.Heavy.svg" width="24" height="24" /> |
| Download | <img src="/icons/extended/Download.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Download.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Download.Heavy.svg" width="24" height="24" /> |
| Edit | <img src="/icons/extended/Edit.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Edit.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Edit.Heavy.svg" width="24" height="24" /> |
| Email | <img src="/icons/extended/Email.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Email.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Email.Heavy.svg" width="24" height="24" /> |
| ExpandLess | <img src="/icons/extended/ExpandLess.Light.svg" width="24" height="24" /> | <img src="/icons/extended/ExpandLess.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/ExpandLess.Heavy.svg" width="24" height="24" /> |
| ExpandMore | <img src="/icons/extended/ExpandMore.Light.svg" width="24" height="24" /> | <img src="/icons/extended/ExpandMore.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/ExpandMore.Heavy.svg" width="24" height="24" /> |
| Favorites | <img src="/icons/extended/Favorites.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Favorites.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Favorites.Heavy.svg" width="24" height="24" /> |
| FavoritesFill | <img src="/icons/extended/FavoritesFill.Light.svg" width="24" height="24" /> | <img src="/icons/extended/FavoritesFill.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/FavoritesFill.Heavy.svg" width="24" height="24" /> |
| File | <img src="/icons/extended/File.Light.svg" width="24" height="24" /> | <img src="/icons/extended/File.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/File.Heavy.svg" width="24" height="24" /> |
| FileDownloads | <img src="/icons/extended/FileDownloads.Light.svg" width="24" height="24" /> | <img src="/icons/extended/FileDownloads.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/FileDownloads.Heavy.svg" width="24" height="24" /> |
| Filter | <img src="/icons/extended/Filter.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Filter.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Filter.Heavy.svg" width="24" height="24" /> |
| Folder | <img src="/icons/extended/Folder.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Folder.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Folder.Heavy.svg" width="24" height="24" /> |
| FolderFill | <img src="/icons/extended/FolderFill.Light.svg" width="24" height="24" /> | <img src="/icons/extended/FolderFill.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/FolderFill.Heavy.svg" width="24" height="24" /> |
| Forward | <img src="/icons/extended/Forward.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Forward.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Forward.Heavy.svg" width="24" height="24" /> |
| GridView | <img src="/icons/extended/GridView.Light.svg" width="24" height="24" /> | <img src="/icons/extended/GridView.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/GridView.Heavy.svg" width="24" height="24" /> |
| Help | <img src="/icons/extended/Help.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Help.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Help.Heavy.svg" width="24" height="24" /> |
| Hide | <img src="/icons/extended/Hide.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Hide.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Hide.Heavy.svg" width="24" height="24" /> |
| HorizontalSplit | <img src="/icons/extended/HorizontalSplit.Light.svg" width="24" height="24" /> | <img src="/icons/extended/HorizontalSplit.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/HorizontalSplit.Heavy.svg" width="24" height="24" /> |
| Image | <img src="/icons/extended/Image.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Image.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Image.Heavy.svg" width="24" height="24" /> |
| Import | <img src="/icons/extended/Import.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Import.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Import.Heavy.svg" width="24" height="24" /> |
| Info | <img src="/icons/extended/Info.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Info.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Info.Heavy.svg" width="24" height="24" /> |
| Layers | <img src="/icons/extended/Layers.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Layers.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Layers.Heavy.svg" width="24" height="24" /> |
| Link | <img src="/icons/extended/Link.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Link.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Link.Heavy.svg" width="24" height="24" /> |
| ListView | <img src="/icons/extended/ListView.Light.svg" width="24" height="24" /> | <img src="/icons/extended/ListView.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/ListView.Heavy.svg" width="24" height="24" /> |
| Location | <img src="/icons/extended/Location.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Location.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Location.Heavy.svg" width="24" height="24" /> |
| Lock | <img src="/icons/extended/Lock.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Lock.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Lock.Heavy.svg" width="24" height="24" /> |
| MapAlbum | <img src="/icons/extended/MapAlbum.Light.svg" width="24" height="24" /> | <img src="/icons/extended/MapAlbum.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/MapAlbum.Heavy.svg" width="24" height="24" /> |
| Merge | <img src="/icons/extended/Merge.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Merge.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Merge.Heavy.svg" width="24" height="24" /> |
| Messages | <img src="/icons/extended/Messages.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Messages.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Messages.Heavy.svg" width="24" height="24" /> |
| Mic | <img src="/icons/extended/Mic.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Mic.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Mic.Heavy.svg" width="24" height="24" /> |
| MicSlash | <img src="/icons/extended/MicSlash.Light.svg" width="24" height="24" /> | <img src="/icons/extended/MicSlash.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/MicSlash.Heavy.svg" width="24" height="24" /> |
| MindMap | <img src="/icons/extended/MindMap.Light.svg" width="24" height="24" /> | <img src="/icons/extended/MindMap.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/MindMap.Heavy.svg" width="24" height="24" /> |
| Months | <img src="/icons/extended/Months.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Months.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Months.Heavy.svg" width="24" height="24" /> |
| More | <img src="/icons/extended/More.Light.svg" width="24" height="24" /> | <img src="/icons/extended/More.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/More.Heavy.svg" width="24" height="24" /> |
| MoreCircle | <img src="/icons/extended/MoreCircle.Light.svg" width="24" height="24" /> | <img src="/icons/extended/MoreCircle.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/MoreCircle.Heavy.svg" width="24" height="24" /> |
| MoveFile | <img src="/icons/extended/MoveFile.Light.svg" width="24" height="24" /> | <img src="/icons/extended/MoveFile.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/MoveFile.Heavy.svg" width="24" height="24" /> |
| Music | <img src="/icons/extended/Music.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Music.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Music.Heavy.svg" width="24" height="24" /> |
| Notes | <img src="/icons/extended/Notes.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Notes.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Notes.Heavy.svg" width="24" height="24" /> |
| NotesFill | <img src="/icons/extended/NotesFill.Light.svg" width="24" height="24" /> | <img src="/icons/extended/NotesFill.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/NotesFill.Heavy.svg" width="24" height="24" /> |
| Ok | <img src="/icons/extended/Ok.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Ok.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Ok.Heavy.svg" width="24" height="24" /> |
| Paste | <img src="/icons/extended/Paste.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Paste.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Paste.Heavy.svg" width="24" height="24" /> |
| Pause | <img src="/icons/extended/Pause.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Pause.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Pause.Heavy.svg" width="24" height="24" /> |
| Phone | <img src="/icons/extended/Phone.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Phone.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Phone.Heavy.svg" width="24" height="24" /> |
| Photos | <img src="/icons/extended/Photos.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Photos.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Photos.Heavy.svg" width="24" height="24" /> |
| Pin | <img src="/icons/extended/Pin.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Pin.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Pin.Heavy.svg" width="24" height="24" /> |
| Play | <img src="/icons/extended/Play.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Play.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Play.Heavy.svg" width="24" height="24" /> |
| Playlist | <img src="/icons/extended/Playlist.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Playlist.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Playlist.Heavy.svg" width="24" height="24" /> |
| Promotions | <img src="/icons/extended/Promotions.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Promotions.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Promotions.Heavy.svg" width="24" height="24" /> |
| Recent | <img src="/icons/extended/Recent.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Recent.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Recent.Heavy.svg" width="24" height="24" /> |
| Recording | <img src="/icons/extended/Recording.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Recording.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Recording.Heavy.svg" width="24" height="24" /> |
| RecordingTape | <img src="/icons/extended/RecordingTape.Light.svg" width="24" height="24" /> | <img src="/icons/extended/RecordingTape.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/RecordingTape.Heavy.svg" width="24" height="24" /> |
| Redo | <img src="/icons/extended/Redo.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Redo.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Redo.Heavy.svg" width="24" height="24" /> |
| Refresh | <img src="/icons/extended/Refresh.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Refresh.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Refresh.Heavy.svg" width="24" height="24" /> |
| Remove | <img src="/icons/extended/Remove.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Remove.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Remove.Heavy.svg" width="24" height="24" /> |
| RemoveContact | <img src="/icons/extended/RemoveContact.Light.svg" width="24" height="24" /> | <img src="/icons/extended/RemoveContact.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/RemoveContact.Heavy.svg" width="24" height="24" /> |
| Rename | <img src="/icons/extended/Rename.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Rename.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Rename.Heavy.svg" width="24" height="24" /> |
| Replace | <img src="/icons/extended/Replace.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Replace.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Replace.Heavy.svg" width="24" height="24" /> |
| Reply | <img src="/icons/extended/Reply.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Reply.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Reply.Heavy.svg" width="24" height="24" /> |
| ReplyAll | <img src="/icons/extended/ReplyAll.Light.svg" width="24" height="24" /> | <img src="/icons/extended/ReplyAll.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/ReplyAll.Heavy.svg" width="24" height="24" /> |
| Report | <img src="/icons/extended/Report.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Report.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Report.Heavy.svg" width="24" height="24" /> |
| Reset | <img src="/icons/extended/Reset.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Reset.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Reset.Heavy.svg" width="24" height="24" /> |
| RotateLeft | <img src="/icons/extended/RotateLeft.Light.svg" width="24" height="24" /> | <img src="/icons/extended/RotateLeft.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/RotateLeft.Heavy.svg" width="24" height="24" /> |
| Scan | <img src="/icons/extended/Scan.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Scan.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Scan.Heavy.svg" width="24" height="24" /> |
| ScreenCapture | <img src="/icons/extended/ScreenCapture.Light.svg" width="24" height="24" /> | <img src="/icons/extended/ScreenCapture.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/ScreenCapture.Heavy.svg" width="24" height="24" /> |
| ScreenMirroring | <img src="/icons/extended/ScreenMirroring.Light.svg" width="24" height="24" /> | <img src="/icons/extended/ScreenMirroring.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/ScreenMirroring.Heavy.svg" width="24" height="24" /> |
| Search | <img src="/icons/extended/Search.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Search.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Search.Heavy.svg" width="24" height="24" /> |
| SearchDevice | <img src="/icons/extended/SearchDevice.Light.svg" width="24" height="24" /> | <img src="/icons/extended/SearchDevice.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/SearchDevice.Heavy.svg" width="24" height="24" /> |
| SelectAll | <img src="/icons/extended/SelectAll.Light.svg" width="24" height="24" /> | <img src="/icons/extended/SelectAll.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/SelectAll.Heavy.svg" width="24" height="24" /> |
| Send | <img src="/icons/extended/Send.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Send.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Send.Heavy.svg" width="24" height="24" /> |
| Settings | <img src="/icons/extended/Settings.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Settings.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Settings.Heavy.svg" width="24" height="24" /> |
| Share | <img src="/icons/extended/Share.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Share.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Share.Heavy.svg" width="24" height="24" /> |
| Show | <img src="/icons/extended/Show.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Show.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Show.Heavy.svg" width="24" height="24" /> |
| Sidebar | <img src="/icons/extended/Sidebar.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Sidebar.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Sidebar.Heavy.svg" width="24" height="24" /> |
| Sort | <img src="/icons/extended/Sort.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Sort.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Sort.Heavy.svg" width="24" height="24" /> |
| Stopwatch | <img src="/icons/extended/Stopwatch.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Stopwatch.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Stopwatch.Heavy.svg" width="24" height="24" /> |
| Store | <img src="/icons/extended/Store.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Store.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Store.Heavy.svg" width="24" height="24" /> |
| Tasks | <img src="/icons/extended/Tasks.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Tasks.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Tasks.Heavy.svg" width="24" height="24" /> |
| Th1 | <img src="/icons/extended/Th1.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Th1.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Th1.Heavy.svg" width="24" height="24" /> |
| Th10 | <img src="/icons/extended/Th10.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Th10.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Th10.Heavy.svg" width="24" height="24" /> |
| Th11 | <img src="/icons/extended/Th11.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Th11.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Th11.Heavy.svg" width="24" height="24" /> |
| Th12 | <img src="/icons/extended/Th12.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Th12.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Th12.Heavy.svg" width="24" height="24" /> |
| Th13 | <img src="/icons/extended/Th13.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Th13.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Th13.Heavy.svg" width="24" height="24" /> |
| Th14 | <img src="/icons/extended/Th14.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Th14.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Th14.Heavy.svg" width="24" height="24" /> |
| Th15 | <img src="/icons/extended/Th15.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Th15.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Th15.Heavy.svg" width="24" height="24" /> |
| Th16 | <img src="/icons/extended/Th16.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Th16.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Th16.Heavy.svg" width="24" height="24" /> |
| Th17 | <img src="/icons/extended/Th17.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Th17.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Th17.Heavy.svg" width="24" height="24" /> |
| Th18 | <img src="/icons/extended/Th18.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Th18.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Th18.Heavy.svg" width="24" height="24" /> |
| Th19 | <img src="/icons/extended/Th19.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Th19.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Th19.Heavy.svg" width="24" height="24" /> |
| Th2 | <img src="/icons/extended/Th2.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Th2.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Th2.Heavy.svg" width="24" height="24" /> |
| Th20 | <img src="/icons/extended/Th20.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Th20.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Th20.Heavy.svg" width="24" height="24" /> |
| Th21 | <img src="/icons/extended/Th21.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Th21.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Th21.Heavy.svg" width="24" height="24" /> |
| Th22 | <img src="/icons/extended/Th22.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Th22.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Th22.Heavy.svg" width="24" height="24" /> |
| Th23 | <img src="/icons/extended/Th23.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Th23.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Th23.Heavy.svg" width="24" height="24" /> |
| Th24 | <img src="/icons/extended/Th24.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Th24.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Th24.Heavy.svg" width="24" height="24" /> |
| Th25 | <img src="/icons/extended/Th25.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Th25.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Th25.Heavy.svg" width="24" height="24" /> |
| Th26 | <img src="/icons/extended/Th26.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Th26.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Th26.Heavy.svg" width="24" height="24" /> |
| Th27 | <img src="/icons/extended/Th27.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Th27.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Th27.Heavy.svg" width="24" height="24" /> |
| Th28 | <img src="/icons/extended/Th28.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Th28.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Th28.Heavy.svg" width="24" height="24" /> |
| Th29 | <img src="/icons/extended/Th29.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Th29.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Th29.Heavy.svg" width="24" height="24" /> |
| Th3 | <img src="/icons/extended/Th3.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Th3.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Th3.Heavy.svg" width="24" height="24" /> |
| Th30 | <img src="/icons/extended/Th30.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Th30.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Th30.Heavy.svg" width="24" height="24" /> |
| Th31 | <img src="/icons/extended/Th31.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Th31.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Th31.Heavy.svg" width="24" height="24" /> |
| Th4 | <img src="/icons/extended/Th4.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Th4.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Th4.Heavy.svg" width="24" height="24" /> |
| Th5 | <img src="/icons/extended/Th5.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Th5.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Th5.Heavy.svg" width="24" height="24" /> |
| Th6 | <img src="/icons/extended/Th6.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Th6.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Th6.Heavy.svg" width="24" height="24" /> |
| Th7 | <img src="/icons/extended/Th7.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Th7.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Th7.Heavy.svg" width="24" height="24" /> |
| Th8 | <img src="/icons/extended/Th8.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Th8.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Th8.Heavy.svg" width="24" height="24" /> |
| Th9 | <img src="/icons/extended/Th9.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Th9.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Th9.Heavy.svg" width="24" height="24" /> |
| Theme | <img src="/icons/extended/Theme.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Theme.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Theme.Heavy.svg" width="24" height="24" /> |
| Timer | <img src="/icons/extended/Timer.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Timer.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Timer.Heavy.svg" width="24" height="24" /> |
| TopDownloads | <img src="/icons/extended/TopDownloads.Light.svg" width="24" height="24" /> | <img src="/icons/extended/TopDownloads.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/TopDownloads.Heavy.svg" width="24" height="24" /> |
| Translate | <img src="/icons/extended/Translate.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Translate.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Translate.Heavy.svg" width="24" height="24" /> |
| Trim | <img src="/icons/extended/Trim.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Trim.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Trim.Heavy.svg" width="24" height="24" /> |
| Tune | <img src="/icons/extended/Tune.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Tune.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Tune.Heavy.svg" width="24" height="24" /> |
| Undo | <img src="/icons/extended/Undo.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Undo.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Undo.Heavy.svg" width="24" height="24" /> |
| Unlock | <img src="/icons/extended/Unlock.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Unlock.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Unlock.Heavy.svg" width="24" height="24" /> |
| Unpin | <img src="/icons/extended/Unpin.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Unpin.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Unpin.Heavy.svg" width="24" height="24" /> |
| Update | <img src="/icons/extended/Update.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Update.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Update.Heavy.svg" width="24" height="24" /> |
| UploadCloud | <img src="/icons/extended/UploadCloud.Light.svg" width="24" height="24" /> | <img src="/icons/extended/UploadCloud.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/UploadCloud.Heavy.svg" width="24" height="24" /> |
| VerticalSplit | <img src="/icons/extended/VerticalSplit.Light.svg" width="24" height="24" /> | <img src="/icons/extended/VerticalSplit.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/VerticalSplit.Heavy.svg" width="24" height="24" /> |
| VolumeOff | <img src="/icons/extended/VolumeOff.Light.svg" width="24" height="24" /> | <img src="/icons/extended/VolumeOff.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/VolumeOff.Heavy.svg" width="24" height="24" /> |
| VolumeUp | <img src="/icons/extended/VolumeUp.Light.svg" width="24" height="24" /> | <img src="/icons/extended/VolumeUp.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/VolumeUp.Heavy.svg" width="24" height="24" /> |
| Weeks | <img src="/icons/extended/Weeks.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Weeks.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Weeks.Heavy.svg" width="24" height="24" /> |
| WorldClock | <img src="/icons/extended/WorldClock.Light.svg" width="24" height="24" /> | <img src="/icons/extended/WorldClock.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/WorldClock.Heavy.svg" width="24" height="24" /> |
| Years | <img src="/icons/extended/Years.Light.svg" width="24" height="24" /> | <img src="/icons/extended/Years.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/Years.Heavy.svg" width="24" height="24" /> |
| ZoomOut | <img src="/icons/extended/ZoomOut.Light.svg" width="24" height="24" /> | <img src="/icons/extended/ZoomOut.Regular.svg" width="24" height="24" /> | <img src="/icons/extended/ZoomOut.Heavy.svg" width="24" height="24" /> |

