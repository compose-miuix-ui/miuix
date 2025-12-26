// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package utils

import androidx.compose.ui.graphics.vector.ImageVector
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.extended.Add
import top.yukonga.miuix.kmp.icon.extended.AddCircle
import top.yukonga.miuix.kmp.icon.extended.AddFolder
import top.yukonga.miuix.kmp.icon.extended.Alarm
import top.yukonga.miuix.kmp.icon.extended.Album
import top.yukonga.miuix.kmp.icon.extended.All
import top.yukonga.miuix.kmp.icon.extended.Answer
import top.yukonga.miuix.kmp.icon.extended.AppRecording
import top.yukonga.miuix.kmp.icon.extended.Back
import top.yukonga.miuix.kmp.icon.extended.Background
import top.yukonga.miuix.kmp.icon.extended.Backup
import top.yukonga.miuix.kmp.icon.extended.BankCards
import top.yukonga.miuix.kmp.icon.extended.Blocklist
import top.yukonga.miuix.kmp.icon.extended.CallRecording
import top.yukonga.miuix.kmp.icon.extended.Carrier
import top.yukonga.miuix.kmp.icon.extended.ChevronBackward
import top.yukonga.miuix.kmp.icon.extended.ChevronForward
import top.yukonga.miuix.kmp.icon.extended.Clear
import top.yukonga.miuix.kmp.icon.extended.Close
import top.yukonga.miuix.kmp.icon.extended.Close2
import top.yukonga.miuix.kmp.icon.extended.CloudFill
import top.yukonga.miuix.kmp.icon.extended.Community
import top.yukonga.miuix.kmp.icon.extended.Contacts
import top.yukonga.miuix.kmp.icon.extended.ContactsBook
import top.yukonga.miuix.kmp.icon.extended.ContactsCircle
import top.yukonga.miuix.kmp.icon.extended.ConvertFile
import top.yukonga.miuix.kmp.icon.extended.Copy
import top.yukonga.miuix.kmp.icon.extended.Create
import top.yukonga.miuix.kmp.icon.extended.Cut
import top.yukonga.miuix.kmp.icon.extended.Delete
import top.yukonga.miuix.kmp.icon.extended.Download
import top.yukonga.miuix.kmp.icon.extended.Edit
import top.yukonga.miuix.kmp.icon.extended.Email
import top.yukonga.miuix.kmp.icon.extended.ExpandLess
import top.yukonga.miuix.kmp.icon.extended.ExpandMore
import top.yukonga.miuix.kmp.icon.extended.Favorites
import top.yukonga.miuix.kmp.icon.extended.FavoritesFill
import top.yukonga.miuix.kmp.icon.extended.File
import top.yukonga.miuix.kmp.icon.extended.FileDownloads
import top.yukonga.miuix.kmp.icon.extended.Filter
import top.yukonga.miuix.kmp.icon.extended.Folder
import top.yukonga.miuix.kmp.icon.extended.FolderFill
import top.yukonga.miuix.kmp.icon.extended.Forward
import top.yukonga.miuix.kmp.icon.extended.GridView
import top.yukonga.miuix.kmp.icon.extended.Help
import top.yukonga.miuix.kmp.icon.extended.Hide
import top.yukonga.miuix.kmp.icon.extended.HorizontalSplit
import top.yukonga.miuix.kmp.icon.extended.Image
import top.yukonga.miuix.kmp.icon.extended.Import
import top.yukonga.miuix.kmp.icon.extended.Info
import top.yukonga.miuix.kmp.icon.extended.Layers
import top.yukonga.miuix.kmp.icon.extended.Link
import top.yukonga.miuix.kmp.icon.extended.ListView
import top.yukonga.miuix.kmp.icon.extended.Location
import top.yukonga.miuix.kmp.icon.extended.Lock
import top.yukonga.miuix.kmp.icon.extended.MapAlbum
import top.yukonga.miuix.kmp.icon.extended.Merge
import top.yukonga.miuix.kmp.icon.extended.Messages
import top.yukonga.miuix.kmp.icon.extended.Mic
import top.yukonga.miuix.kmp.icon.extended.MicSlash
import top.yukonga.miuix.kmp.icon.extended.MindMap
import top.yukonga.miuix.kmp.icon.extended.Months
import top.yukonga.miuix.kmp.icon.extended.More
import top.yukonga.miuix.kmp.icon.extended.MoreCircle
import top.yukonga.miuix.kmp.icon.extended.MoveFile
import top.yukonga.miuix.kmp.icon.extended.Music
import top.yukonga.miuix.kmp.icon.extended.Notes
import top.yukonga.miuix.kmp.icon.extended.NotesFill
import top.yukonga.miuix.kmp.icon.extended.Ok
import top.yukonga.miuix.kmp.icon.extended.Paste
import top.yukonga.miuix.kmp.icon.extended.Pause
import top.yukonga.miuix.kmp.icon.extended.Phone
import top.yukonga.miuix.kmp.icon.extended.Photos
import top.yukonga.miuix.kmp.icon.extended.Pin
import top.yukonga.miuix.kmp.icon.extended.Play
import top.yukonga.miuix.kmp.icon.extended.Playlist
import top.yukonga.miuix.kmp.icon.extended.Promotions
import top.yukonga.miuix.kmp.icon.extended.Recent
import top.yukonga.miuix.kmp.icon.extended.Recording
import top.yukonga.miuix.kmp.icon.extended.RecordingTape
import top.yukonga.miuix.kmp.icon.extended.Redo
import top.yukonga.miuix.kmp.icon.extended.Refresh
import top.yukonga.miuix.kmp.icon.extended.Remove
import top.yukonga.miuix.kmp.icon.extended.RemoveContact
import top.yukonga.miuix.kmp.icon.extended.Rename
import top.yukonga.miuix.kmp.icon.extended.Replace
import top.yukonga.miuix.kmp.icon.extended.Reply
import top.yukonga.miuix.kmp.icon.extended.ReplyAll
import top.yukonga.miuix.kmp.icon.extended.Report
import top.yukonga.miuix.kmp.icon.extended.Reset
import top.yukonga.miuix.kmp.icon.extended.RotateLeft
import top.yukonga.miuix.kmp.icon.extended.Scan
import top.yukonga.miuix.kmp.icon.extended.ScreenCapture
import top.yukonga.miuix.kmp.icon.extended.ScreenMirroring
import top.yukonga.miuix.kmp.icon.extended.Search
import top.yukonga.miuix.kmp.icon.extended.SearchDevice
import top.yukonga.miuix.kmp.icon.extended.SelectAll
import top.yukonga.miuix.kmp.icon.extended.Send
import top.yukonga.miuix.kmp.icon.extended.Settings
import top.yukonga.miuix.kmp.icon.extended.Share
import top.yukonga.miuix.kmp.icon.extended.Show
import top.yukonga.miuix.kmp.icon.extended.Sidebar
import top.yukonga.miuix.kmp.icon.extended.Sort
import top.yukonga.miuix.kmp.icon.extended.Stopwatch
import top.yukonga.miuix.kmp.icon.extended.Store
import top.yukonga.miuix.kmp.icon.extended.Tasks
import top.yukonga.miuix.kmp.icon.extended.Th1
import top.yukonga.miuix.kmp.icon.extended.Th10
import top.yukonga.miuix.kmp.icon.extended.Th11
import top.yukonga.miuix.kmp.icon.extended.Th12
import top.yukonga.miuix.kmp.icon.extended.Th13
import top.yukonga.miuix.kmp.icon.extended.Th14
import top.yukonga.miuix.kmp.icon.extended.Th15
import top.yukonga.miuix.kmp.icon.extended.Th16
import top.yukonga.miuix.kmp.icon.extended.Th17
import top.yukonga.miuix.kmp.icon.extended.Th18
import top.yukonga.miuix.kmp.icon.extended.Th19
import top.yukonga.miuix.kmp.icon.extended.Th2
import top.yukonga.miuix.kmp.icon.extended.Th20
import top.yukonga.miuix.kmp.icon.extended.Th21
import top.yukonga.miuix.kmp.icon.extended.Th22
import top.yukonga.miuix.kmp.icon.extended.Th23
import top.yukonga.miuix.kmp.icon.extended.Th24
import top.yukonga.miuix.kmp.icon.extended.Th25
import top.yukonga.miuix.kmp.icon.extended.Th26
import top.yukonga.miuix.kmp.icon.extended.Th27
import top.yukonga.miuix.kmp.icon.extended.Th28
import top.yukonga.miuix.kmp.icon.extended.Th29
import top.yukonga.miuix.kmp.icon.extended.Th3
import top.yukonga.miuix.kmp.icon.extended.Th30
import top.yukonga.miuix.kmp.icon.extended.Th31
import top.yukonga.miuix.kmp.icon.extended.Th4
import top.yukonga.miuix.kmp.icon.extended.Th5
import top.yukonga.miuix.kmp.icon.extended.Th6
import top.yukonga.miuix.kmp.icon.extended.Th7
import top.yukonga.miuix.kmp.icon.extended.Th8
import top.yukonga.miuix.kmp.icon.extended.Th9
import top.yukonga.miuix.kmp.icon.extended.Theme
import top.yukonga.miuix.kmp.icon.extended.Timer
import top.yukonga.miuix.kmp.icon.extended.TopDownloads
import top.yukonga.miuix.kmp.icon.extended.Translate
import top.yukonga.miuix.kmp.icon.extended.Trim
import top.yukonga.miuix.kmp.icon.extended.Tune
import top.yukonga.miuix.kmp.icon.extended.Undo
import top.yukonga.miuix.kmp.icon.extended.Unlock
import top.yukonga.miuix.kmp.icon.extended.Unpin
import top.yukonga.miuix.kmp.icon.extended.Update
import top.yukonga.miuix.kmp.icon.extended.UploadCloud
import top.yukonga.miuix.kmp.icon.extended.VerticalSplit
import top.yukonga.miuix.kmp.icon.extended.VolumeOff
import top.yukonga.miuix.kmp.icon.extended.VolumeUp
import top.yukonga.miuix.kmp.icon.extended.Weeks
import top.yukonga.miuix.kmp.icon.extended.WorldClock
import top.yukonga.miuix.kmp.icon.extended.Years
import top.yukonga.miuix.kmp.icon.extended.ZoomOut

val MiuixIcons.All: Map<String, List<ImageVector>>
    get() = mapOf(
        "Light" to listOf(
            MiuixIcons.Light.Add,
            MiuixIcons.Light.AddCircle,
            MiuixIcons.Light.AddFolder,
            MiuixIcons.Light.Alarm,
            MiuixIcons.Light.Album,
            MiuixIcons.Light.All,
            MiuixIcons.Light.Answer,
            MiuixIcons.Light.AppRecording,
            MiuixIcons.Light.Back,
            MiuixIcons.Light.Background,
            MiuixIcons.Light.Backup,
            MiuixIcons.Light.BankCards,
            MiuixIcons.Light.Blocklist,
            MiuixIcons.Light.CallRecording,
            MiuixIcons.Light.Carrier,
            MiuixIcons.Light.ChevronBackward,
            MiuixIcons.Light.ChevronForward,
            MiuixIcons.Light.Clear,
            MiuixIcons.Light.Close,
            MiuixIcons.Light.Close2,
            MiuixIcons.Light.CloudFill,
            MiuixIcons.Light.Community,
            MiuixIcons.Light.Contacts,
            MiuixIcons.Light.ContactsBook,
            MiuixIcons.Light.ContactsCircle,
            MiuixIcons.Light.ConvertFile,
            MiuixIcons.Light.Copy,
            MiuixIcons.Light.Create,
            MiuixIcons.Light.Cut,
            MiuixIcons.Light.Delete,
            MiuixIcons.Light.Download,
            MiuixIcons.Light.Edit,
            MiuixIcons.Light.Email,
            MiuixIcons.Light.ExpandLess,
            MiuixIcons.Light.ExpandMore,
            MiuixIcons.Light.Favorites,
            MiuixIcons.Light.FavoritesFill,
            MiuixIcons.Light.File,
            MiuixIcons.Light.FileDownloads,
            MiuixIcons.Light.Filter,
            MiuixIcons.Light.Folder,
            MiuixIcons.Light.FolderFill,
            MiuixIcons.Light.Forward,
            MiuixIcons.Light.GridView,
            MiuixIcons.Light.Help,
            MiuixIcons.Light.Hide,
            MiuixIcons.Light.HorizontalSplit,
            MiuixIcons.Light.Image,
            MiuixIcons.Light.Import,
            MiuixIcons.Light.Info,
            MiuixIcons.Light.Layers,
            MiuixIcons.Light.Link,
            MiuixIcons.Light.ListView,
            MiuixIcons.Light.Location,
            MiuixIcons.Light.Lock,
            MiuixIcons.Light.MapAlbum,
            MiuixIcons.Light.Merge,
            MiuixIcons.Light.Messages,
            MiuixIcons.Light.Mic,
            MiuixIcons.Light.MicSlash,
            MiuixIcons.Light.MindMap,
            MiuixIcons.Light.Months,
            MiuixIcons.Light.More,
            MiuixIcons.Light.MoreCircle,
            MiuixIcons.Light.MoveFile,
            MiuixIcons.Light.Music,
            MiuixIcons.Light.Notes,
            MiuixIcons.Light.NotesFill,
            MiuixIcons.Light.Ok,
            MiuixIcons.Light.Paste,
            MiuixIcons.Light.Pause,
            MiuixIcons.Light.Phone,
            MiuixIcons.Light.Photos,
            MiuixIcons.Light.Pin,
            MiuixIcons.Light.Play,
            MiuixIcons.Light.Playlist,
            MiuixIcons.Light.Promotions,
            MiuixIcons.Light.Recent,
            MiuixIcons.Light.Recording,
            MiuixIcons.Light.RecordingTape,
            MiuixIcons.Light.Redo,
            MiuixIcons.Light.Refresh,
            MiuixIcons.Light.Remove,
            MiuixIcons.Light.RemoveContact,
            MiuixIcons.Light.Rename,
            MiuixIcons.Light.Replace,
            MiuixIcons.Light.Reply,
            MiuixIcons.Light.ReplyAll,
            MiuixIcons.Light.Report,
            MiuixIcons.Light.Reset,
            MiuixIcons.Light.RotateLeft,
            MiuixIcons.Light.Scan,
            MiuixIcons.Light.ScreenCapture,
            MiuixIcons.Light.ScreenMirroring,
            MiuixIcons.Light.Search,
            MiuixIcons.Light.SearchDevice,
            MiuixIcons.Light.SelectAll,
            MiuixIcons.Light.Send,
            MiuixIcons.Light.Settings,
            MiuixIcons.Light.Share,
            MiuixIcons.Light.Show,
            MiuixIcons.Light.Sidebar,
            MiuixIcons.Light.Sort,
            MiuixIcons.Light.Stopwatch,
            MiuixIcons.Light.Store,
            MiuixIcons.Light.Tasks,
            MiuixIcons.Light.Th1,
            MiuixIcons.Light.Th10,
            MiuixIcons.Light.Th11,
            MiuixIcons.Light.Th12,
            MiuixIcons.Light.Th13,
            MiuixIcons.Light.Th14,
            MiuixIcons.Light.Th15,
            MiuixIcons.Light.Th16,
            MiuixIcons.Light.Th17,
            MiuixIcons.Light.Th18,
            MiuixIcons.Light.Th19,
            MiuixIcons.Light.Th2,
            MiuixIcons.Light.Th20,
            MiuixIcons.Light.Th21,
            MiuixIcons.Light.Th22,
            MiuixIcons.Light.Th23,
            MiuixIcons.Light.Th24,
            MiuixIcons.Light.Th25,
            MiuixIcons.Light.Th26,
            MiuixIcons.Light.Th27,
            MiuixIcons.Light.Th28,
            MiuixIcons.Light.Th29,
            MiuixIcons.Light.Th3,
            MiuixIcons.Light.Th30,
            MiuixIcons.Light.Th31,
            MiuixIcons.Light.Th4,
            MiuixIcons.Light.Th5,
            MiuixIcons.Light.Th6,
            MiuixIcons.Light.Th7,
            MiuixIcons.Light.Th8,
            MiuixIcons.Light.Th9,
            MiuixIcons.Light.Theme,
            MiuixIcons.Light.Timer,
            MiuixIcons.Light.TopDownloads,
            MiuixIcons.Light.Translate,
            MiuixIcons.Light.Trim,
            MiuixIcons.Light.Tune,
            MiuixIcons.Light.Undo,
            MiuixIcons.Light.Unlock,
            MiuixIcons.Light.Unpin,
            MiuixIcons.Light.Update,
            MiuixIcons.Light.UploadCloud,
            MiuixIcons.Light.VerticalSplit,
            MiuixIcons.Light.VolumeOff,
            MiuixIcons.Light.VolumeUp,
            MiuixIcons.Light.Weeks,
            MiuixIcons.Light.WorldClock,
            MiuixIcons.Light.Years,
            MiuixIcons.Light.ZoomOut,
        ),
        "Regular" to listOf(
            MiuixIcons.Regular.Add,
            MiuixIcons.Regular.AddCircle,
            MiuixIcons.Regular.AddFolder,
            MiuixIcons.Regular.Alarm,
            MiuixIcons.Regular.Album,
            MiuixIcons.Regular.All,
            MiuixIcons.Regular.Answer,
            MiuixIcons.Regular.AppRecording,
            MiuixIcons.Regular.Back,
            MiuixIcons.Regular.Background,
            MiuixIcons.Regular.Backup,
            MiuixIcons.Regular.BankCards,
            MiuixIcons.Regular.Blocklist,
            MiuixIcons.Regular.CallRecording,
            MiuixIcons.Regular.Carrier,
            MiuixIcons.Regular.ChevronBackward,
            MiuixIcons.Regular.ChevronForward,
            MiuixIcons.Regular.Clear,
            MiuixIcons.Regular.Close,
            MiuixIcons.Regular.Close2,
            MiuixIcons.Regular.CloudFill,
            MiuixIcons.Regular.Community,
            MiuixIcons.Regular.Contacts,
            MiuixIcons.Regular.ContactsBook,
            MiuixIcons.Regular.ContactsCircle,
            MiuixIcons.Regular.ConvertFile,
            MiuixIcons.Regular.Copy,
            MiuixIcons.Regular.Create,
            MiuixIcons.Regular.Cut,
            MiuixIcons.Regular.Delete,
            MiuixIcons.Regular.Download,
            MiuixIcons.Regular.Edit,
            MiuixIcons.Regular.Email,
            MiuixIcons.Regular.ExpandLess,
            MiuixIcons.Regular.ExpandMore,
            MiuixIcons.Regular.Favorites,
            MiuixIcons.Regular.FavoritesFill,
            MiuixIcons.Regular.File,
            MiuixIcons.Regular.FileDownloads,
            MiuixIcons.Regular.Filter,
            MiuixIcons.Regular.Folder,
            MiuixIcons.Regular.FolderFill,
            MiuixIcons.Regular.Forward,
            MiuixIcons.Regular.GridView,
            MiuixIcons.Regular.Help,
            MiuixIcons.Regular.Hide,
            MiuixIcons.Regular.HorizontalSplit,
            MiuixIcons.Regular.Image,
            MiuixIcons.Regular.Import,
            MiuixIcons.Regular.Info,
            MiuixIcons.Regular.Layers,
            MiuixIcons.Regular.Link,
            MiuixIcons.Regular.ListView,
            MiuixIcons.Regular.Location,
            MiuixIcons.Regular.Lock,
            MiuixIcons.Regular.MapAlbum,
            MiuixIcons.Regular.Merge,
            MiuixIcons.Regular.Messages,
            MiuixIcons.Regular.Mic,
            MiuixIcons.Regular.MicSlash,
            MiuixIcons.Regular.MindMap,
            MiuixIcons.Regular.Months,
            MiuixIcons.Regular.More,
            MiuixIcons.Regular.MoreCircle,
            MiuixIcons.Regular.MoveFile,
            MiuixIcons.Regular.Music,
            MiuixIcons.Regular.Notes,
            MiuixIcons.Regular.NotesFill,
            MiuixIcons.Regular.Ok,
            MiuixIcons.Regular.Paste,
            MiuixIcons.Regular.Pause,
            MiuixIcons.Regular.Phone,
            MiuixIcons.Regular.Photos,
            MiuixIcons.Regular.Pin,
            MiuixIcons.Regular.Play,
            MiuixIcons.Regular.Playlist,
            MiuixIcons.Regular.Promotions,
            MiuixIcons.Regular.Recent,
            MiuixIcons.Regular.Recording,
            MiuixIcons.Regular.RecordingTape,
            MiuixIcons.Regular.Redo,
            MiuixIcons.Regular.Refresh,
            MiuixIcons.Regular.Remove,
            MiuixIcons.Regular.RemoveContact,
            MiuixIcons.Regular.Rename,
            MiuixIcons.Regular.Replace,
            MiuixIcons.Regular.Reply,
            MiuixIcons.Regular.ReplyAll,
            MiuixIcons.Regular.Report,
            MiuixIcons.Regular.Reset,
            MiuixIcons.Regular.RotateLeft,
            MiuixIcons.Regular.Scan,
            MiuixIcons.Regular.ScreenCapture,
            MiuixIcons.Regular.ScreenMirroring,
            MiuixIcons.Regular.Search,
            MiuixIcons.Regular.SearchDevice,
            MiuixIcons.Regular.SelectAll,
            MiuixIcons.Regular.Send,
            MiuixIcons.Regular.Settings,
            MiuixIcons.Regular.Share,
            MiuixIcons.Regular.Show,
            MiuixIcons.Regular.Sidebar,
            MiuixIcons.Regular.Sort,
            MiuixIcons.Regular.Stopwatch,
            MiuixIcons.Regular.Store,
            MiuixIcons.Regular.Tasks,
            MiuixIcons.Regular.Th1,
            MiuixIcons.Regular.Th10,
            MiuixIcons.Regular.Th11,
            MiuixIcons.Regular.Th12,
            MiuixIcons.Regular.Th13,
            MiuixIcons.Regular.Th14,
            MiuixIcons.Regular.Th15,
            MiuixIcons.Regular.Th16,
            MiuixIcons.Regular.Th17,
            MiuixIcons.Regular.Th18,
            MiuixIcons.Regular.Th19,
            MiuixIcons.Regular.Th2,
            MiuixIcons.Regular.Th20,
            MiuixIcons.Regular.Th21,
            MiuixIcons.Regular.Th22,
            MiuixIcons.Regular.Th23,
            MiuixIcons.Regular.Th24,
            MiuixIcons.Regular.Th25,
            MiuixIcons.Regular.Th26,
            MiuixIcons.Regular.Th27,
            MiuixIcons.Regular.Th28,
            MiuixIcons.Regular.Th29,
            MiuixIcons.Regular.Th3,
            MiuixIcons.Regular.Th30,
            MiuixIcons.Regular.Th31,
            MiuixIcons.Regular.Th4,
            MiuixIcons.Regular.Th5,
            MiuixIcons.Regular.Th6,
            MiuixIcons.Regular.Th7,
            MiuixIcons.Regular.Th8,
            MiuixIcons.Regular.Th9,
            MiuixIcons.Regular.Theme,
            MiuixIcons.Regular.Timer,
            MiuixIcons.Regular.TopDownloads,
            MiuixIcons.Regular.Translate,
            MiuixIcons.Regular.Trim,
            MiuixIcons.Regular.Tune,
            MiuixIcons.Regular.Undo,
            MiuixIcons.Regular.Unlock,
            MiuixIcons.Regular.Unpin,
            MiuixIcons.Regular.Update,
            MiuixIcons.Regular.UploadCloud,
            MiuixIcons.Regular.VerticalSplit,
            MiuixIcons.Regular.VolumeOff,
            MiuixIcons.Regular.VolumeUp,
            MiuixIcons.Regular.Weeks,
            MiuixIcons.Regular.WorldClock,
            MiuixIcons.Regular.Years,
            MiuixIcons.Regular.ZoomOut,
        ),
        "Heavy" to listOf(
            MiuixIcons.Heavy.Add,
            MiuixIcons.Heavy.AddCircle,
            MiuixIcons.Heavy.AddFolder,
            MiuixIcons.Heavy.Alarm,
            MiuixIcons.Heavy.Album,
            MiuixIcons.Heavy.All,
            MiuixIcons.Heavy.Answer,
            MiuixIcons.Heavy.AppRecording,
            MiuixIcons.Heavy.Back,
            MiuixIcons.Heavy.Background,
            MiuixIcons.Heavy.Backup,
            MiuixIcons.Heavy.BankCards,
            MiuixIcons.Heavy.Blocklist,
            MiuixIcons.Heavy.CallRecording,
            MiuixIcons.Heavy.Carrier,
            MiuixIcons.Heavy.ChevronBackward,
            MiuixIcons.Heavy.ChevronForward,
            MiuixIcons.Heavy.Clear,
            MiuixIcons.Heavy.Close,
            MiuixIcons.Heavy.Close2,
            MiuixIcons.Heavy.CloudFill,
            MiuixIcons.Heavy.Community,
            MiuixIcons.Heavy.Contacts,
            MiuixIcons.Heavy.ContactsBook,
            MiuixIcons.Heavy.ContactsCircle,
            MiuixIcons.Heavy.ConvertFile,
            MiuixIcons.Heavy.Copy,
            MiuixIcons.Heavy.Create,
            MiuixIcons.Heavy.Cut,
            MiuixIcons.Heavy.Delete,
            MiuixIcons.Heavy.Download,
            MiuixIcons.Heavy.Edit,
            MiuixIcons.Heavy.Email,
            MiuixIcons.Heavy.ExpandLess,
            MiuixIcons.Heavy.ExpandMore,
            MiuixIcons.Heavy.Favorites,
            MiuixIcons.Heavy.FavoritesFill,
            MiuixIcons.Heavy.File,
            MiuixIcons.Heavy.FileDownloads,
            MiuixIcons.Heavy.Filter,
            MiuixIcons.Heavy.Folder,
            MiuixIcons.Heavy.FolderFill,
            MiuixIcons.Heavy.Forward,
            MiuixIcons.Heavy.GridView,
            MiuixIcons.Heavy.Help,
            MiuixIcons.Heavy.Hide,
            MiuixIcons.Heavy.HorizontalSplit,
            MiuixIcons.Heavy.Image,
            MiuixIcons.Heavy.Import,
            MiuixIcons.Heavy.Info,
            MiuixIcons.Heavy.Layers,
            MiuixIcons.Heavy.Link,
            MiuixIcons.Heavy.ListView,
            MiuixIcons.Heavy.Location,
            MiuixIcons.Heavy.Lock,
            MiuixIcons.Heavy.MapAlbum,
            MiuixIcons.Heavy.Merge,
            MiuixIcons.Heavy.Messages,
            MiuixIcons.Heavy.Mic,
            MiuixIcons.Heavy.MicSlash,
            MiuixIcons.Heavy.MindMap,
            MiuixIcons.Heavy.Months,
            MiuixIcons.Heavy.More,
            MiuixIcons.Heavy.MoreCircle,
            MiuixIcons.Heavy.MoveFile,
            MiuixIcons.Heavy.Music,
            MiuixIcons.Heavy.Notes,
            MiuixIcons.Heavy.NotesFill,
            MiuixIcons.Heavy.Ok,
            MiuixIcons.Heavy.Paste,
            MiuixIcons.Heavy.Pause,
            MiuixIcons.Heavy.Phone,
            MiuixIcons.Heavy.Photos,
            MiuixIcons.Heavy.Pin,
            MiuixIcons.Heavy.Play,
            MiuixIcons.Heavy.Playlist,
            MiuixIcons.Heavy.Promotions,
            MiuixIcons.Heavy.Recent,
            MiuixIcons.Heavy.Recording,
            MiuixIcons.Heavy.RecordingTape,
            MiuixIcons.Heavy.Redo,
            MiuixIcons.Heavy.Refresh,
            MiuixIcons.Heavy.Remove,
            MiuixIcons.Heavy.RemoveContact,
            MiuixIcons.Heavy.Rename,
            MiuixIcons.Heavy.Replace,
            MiuixIcons.Heavy.Reply,
            MiuixIcons.Heavy.ReplyAll,
            MiuixIcons.Heavy.Report,
            MiuixIcons.Heavy.Reset,
            MiuixIcons.Heavy.RotateLeft,
            MiuixIcons.Heavy.Scan,
            MiuixIcons.Heavy.ScreenCapture,
            MiuixIcons.Heavy.ScreenMirroring,
            MiuixIcons.Heavy.Search,
            MiuixIcons.Heavy.SearchDevice,
            MiuixIcons.Heavy.SelectAll,
            MiuixIcons.Heavy.Send,
            MiuixIcons.Heavy.Settings,
            MiuixIcons.Heavy.Share,
            MiuixIcons.Heavy.Show,
            MiuixIcons.Heavy.Sidebar,
            MiuixIcons.Heavy.Sort,
            MiuixIcons.Heavy.Stopwatch,
            MiuixIcons.Heavy.Store,
            MiuixIcons.Heavy.Tasks,
            MiuixIcons.Heavy.Th1,
            MiuixIcons.Heavy.Th10,
            MiuixIcons.Heavy.Th11,
            MiuixIcons.Heavy.Th12,
            MiuixIcons.Heavy.Th13,
            MiuixIcons.Heavy.Th14,
            MiuixIcons.Heavy.Th15,
            MiuixIcons.Heavy.Th16,
            MiuixIcons.Heavy.Th17,
            MiuixIcons.Heavy.Th18,
            MiuixIcons.Heavy.Th19,
            MiuixIcons.Heavy.Th2,
            MiuixIcons.Heavy.Th20,
            MiuixIcons.Heavy.Th21,
            MiuixIcons.Heavy.Th22,
            MiuixIcons.Heavy.Th23,
            MiuixIcons.Heavy.Th24,
            MiuixIcons.Heavy.Th25,
            MiuixIcons.Heavy.Th26,
            MiuixIcons.Heavy.Th27,
            MiuixIcons.Heavy.Th28,
            MiuixIcons.Heavy.Th29,
            MiuixIcons.Heavy.Th3,
            MiuixIcons.Heavy.Th30,
            MiuixIcons.Heavy.Th31,
            MiuixIcons.Heavy.Th4,
            MiuixIcons.Heavy.Th5,
            MiuixIcons.Heavy.Th6,
            MiuixIcons.Heavy.Th7,
            MiuixIcons.Heavy.Th8,
            MiuixIcons.Heavy.Th9,
            MiuixIcons.Heavy.Theme,
            MiuixIcons.Heavy.Timer,
            MiuixIcons.Heavy.TopDownloads,
            MiuixIcons.Heavy.Translate,
            MiuixIcons.Heavy.Trim,
            MiuixIcons.Heavy.Tune,
            MiuixIcons.Heavy.Undo,
            MiuixIcons.Heavy.Unlock,
            MiuixIcons.Heavy.Unpin,
            MiuixIcons.Heavy.Update,
            MiuixIcons.Heavy.UploadCloud,
            MiuixIcons.Heavy.VerticalSplit,
            MiuixIcons.Heavy.VolumeOff,
            MiuixIcons.Heavy.VolumeUp,
            MiuixIcons.Heavy.Weeks,
            MiuixIcons.Heavy.WorldClock,
            MiuixIcons.Heavy.Years,
            MiuixIcons.Heavy.ZoomOut,
        ),
    )
