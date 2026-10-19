---
title: GlassDropdownPreference
requiresScaffoldHost: true
hostComponent: Scaffold
popupHost: MiuixPopupHost
---

# GlassDropdownPreference

`GlassDropdownPreference` 将标准 Miuix 设置行与玻璃下拉弹窗封装在一起，内部管理展开、锚点测量、选择和关闭。调用方管理 `selectedIndex`。

## 导入

```kotlin
import top.yukonga.miuix.kmp.glass.GlassDropdownPreference
```

组件由 `miuix-glass` 提供，依赖配置与平台要求见[柔光玻璃](/zh_CN/guide/glass)。

## 基本用法

```kotlin
var selectedIndex by remember { mutableIntStateOf(0) }

Scaffold {
    GlassDropdownPreference(
        title = "Bluetooth stack log",
        items = listOf("Verbose", "Debug", "Info", "Warn", "Error"),
        selectedIndex = selectedIndex,
        onSelectedIndexChange = { selectedIndex = it },
        backdrop = null,
        summary = "选择日志级别",
    )
}
```

传入干净的页面 `Backdrop` 即可启用玻璃材质，采样层必须排除弹窗宿主。传入 null 时使用不透明背景。弹窗默认由根 `Scaffold` 承载，设置 `renderInRootScaffold = false` 可改用最近的宿主。

标题与其他 Preference 一样使用 17sp Medium，弹出选项使用 16sp Medium，与标准下拉组件一致。右侧选中值使用 14sp 文字和标准的 10 × 16dp 箭头。选项为空时设置行不可点击，禁用已展开的组件会关闭弹窗。

## 属性

| 属性 | 类型 | 默认值 / 行为 |
| --- | --- | --- |
| `onSelectedIndexChange` | `(Int) -> Unit` | 必填；选择时回调并关闭弹窗 |
| `items` | `List<String>` | 必填；可选项列表 |
| `selectedIndex` | `Int` | 必填；索引越界时不显示选中值 |
| `title` | `String` | 必填；设置行标题 |
| `backdrop` | `Backdrop?` | 必填；null 使用不透明背景 |
| `modifier` | `Modifier` | `Modifier`，作用于设置行 |
| `enabled` | `Boolean` | `true` |
| `showValue` | `Boolean` | `true`；false 时只保留箭头 |
| `renderInRootScaffold` | `Boolean` | `true` |
| `titleColor` | `BasicComponentColors` | `BasicComponentDefaults.titleColor()` |
| `summary` | `String?` | `null` |
| `summaryColor` | `BasicComponentColors` | `BasicComponentDefaults.summaryColor()` |
| `startAction` | `(@Composable () -> Unit)?` | `null`，左侧内容 |
| `bottomAction` | `(@Composable () -> Unit)?` | `null`，设置行底部内容 |
| `insideMargin` | `PaddingValues` | `BasicComponentDefaults.InsideMargin`，四边 16dp |
| `sizing` | `GlassPopupSizing` | `GlassPopupSizing()` |
| `visuals` | `GlassPopupVisuals` | `GlassPopupDefaults.visuals()` |
| `onExpandedChange` | `((Boolean) -> Unit)?` | `null`，通知展开与关闭 |

## 默认值

设置行复用 `BasicComponent` 的排版与 `BasicComponentDefaults` 的内边距、标题和摘要颜色，箭头复用 `DropdownDefaults`。弹窗颜色、材质与描边来自 `GlassPopupDefaults.visuals()`，默认圆角来自 `GlassDropdownDefaults.CornerRadius`（24dp）。点击外部区域或返回均会关闭弹窗，并保留退出动画。
