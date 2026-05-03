---
title: WindowIconDropdownMenu
requiresScaffoldHost: false
prerequisites:
  - 可在任何位置使用，不需要 `Scaffold` 或 `MiuixPopupHost`
  - 在窗口级别渲染
hostComponent: None
popupHost: None
---

# WindowIconDropdownMenu

`WindowIconDropdownMenu` 是基于 `IconButton` 的封装，点击图标按钮后会展开 `WindowDropdownPopup`（通过 `Dialog` 在窗口级别渲染）。适用于工具栏的 action 槽位，例如 `TopAppBar` 的右侧动作按钮——单个图标按钮展开后呈现一组动作、排序选项或筛选开关。与 `OverlayIconDropdownMenu` 不同，它不需要 `Scaffold`。

<div style="position: relative; height: 300px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../../compose/index.html?id=windowIconDropdownMenu" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

## 引入

```kotlin
import top.yukonga.miuix.kmp.menu.WindowIconDropdownMenu
import top.yukonga.miuix.kmp.basic.DropdownEntry
import top.yukonga.miuix.kmp.basic.DropdownItem
```

## 基本用法

将 `WindowIconDropdownMenu` 放在 `TopAppBar`（或 `SmallTopAppBar`）的 `actions` 槽中，点击图标即可展开弹出框。这是最典型的使用场景——工具栏上的一个图标按钮，展开后呈现一组菜单项。与 `OverlayIconDropdownMenu` 不同，菜单本身不要求外层有 `Scaffold`，但承载 `TopAppBar` 的最自然方式仍然是 `Scaffold`。

```kotlin
val entry = DropdownEntry(
    items = listOf("编辑", "复制", "分享", "删除").map { text ->
        DropdownItem(text = text, onClick = { /* 处理动作 */ })
    }
)

Scaffold(
    topBar = {
        SmallTopAppBar(
            title = "收件箱",
            actions = {
                WindowIconDropdownMenu(entry = entry) {
                    Icon(imageVector = MiuixIcons.Edit, contentDescription = "动作菜单")
                }
            }
        )
    }
) { padding ->
    // 页面内容
}
```

## 排序 / 单选

对于排序菜单或单选场景，在每个 `DropdownItem` 上设置 `selected`，并保持 `collapseOnSelection = true`（entry 重载的默认值），让弹出框在每次选中后自动关闭。

```kotlin
var sortIndex by remember { mutableStateOf(0) }
val entry = DropdownEntry(
    items = listOf("名称", "日期", "大小").mapIndexed { index, text ->
        DropdownItem(text = text, selected = sortIndex == index, onClick = { sortIndex = index })
    }
)

WindowIconDropdownMenu(entry = entry) {
    Icon(imageVector = MiuixIcons.Sort, contentDescription = "排序")
}
```

## 多选

用一个 `Set` 跟踪选中值，在每个条目的 `onClick` 中切换状态，并设置 `collapseOnSelection = false` 让弹出框在多次选择之间保持打开。

```kotlin
var selected by remember { mutableStateOf(setOf("照片")) }
val entry = DropdownEntry(
    items = listOf("照片", "视频", "文件").map { text ->
        DropdownItem(
            text = text,
            selected = text in selected,
            onClick = {
                selected = if (text in selected) selected - text else selected + text
            }
        )
    }
)

WindowIconDropdownMenu(entry = entry, collapseOnSelection = false) {
    Icon(imageVector = MiuixIcons.SelectAll, contentDescription = "多选")
}
```

## 分组菜单

传入 `entries: List<DropdownEntry>` 即可显示由分割线隔开的多个分组。

```kotlin
val entries = listOf(
    DropdownEntry(items = listOf("条目 A-1", "条目 A-2").map { DropdownItem(text = it) }),
    DropdownEntry(items = listOf("条目 B-1", "条目 B-2", "条目 B-3").map { DropdownItem(text = it) })
)

WindowIconDropdownMenu(entries = entries) {
    Icon(imageVector = MiuixIcons.MoreCircle, contentDescription = "更多")
}
```

## 组件状态

### 禁用状态

```kotlin
WindowIconDropdownMenu(
    entry = DropdownEntry(items = listOf(DropdownItem(text = "选项 1"))),
    enabled = false
) {
    Icon(imageVector = MiuixIcons.MoreCircle, contentDescription = "更多")
}
```

当所有 `DropdownEntry` 都不包含任何条目时，菜单也会被隐式禁用。

## 属性

### WindowIconDropdownMenu 属性（Entries 重载）

| 属性名              | 类型                      | 说明                                | 默认值                              | 是否必须 |
| ------------------- | ------------------------- | ----------------------------------- | ----------------------------------- | -------- |
| entries             | List\<DropdownEntry>      | 由分割线隔开的下拉选项分组          | -                                   | 是       |
| modifier            | Modifier                  | 应用于外层 `Box` 的修饰符           | Modifier                            | 否       |
| enabled             | Boolean                   | 图标按钮是否可交互                  | true                                | 否       |
| maxHeight           | Dp?                       | 下拉菜单的最大高度                  | null                                | 否       |
| dropdownColors      | DropdownColors            | 下拉选项的颜色配置                  | DropdownDefaults.dropdownColors()   | 否       |
| collapseOnSelection | Boolean                   | 每次选中后是否关闭弹出框            | entries.size <= 1                   | 否       |
| onExpandedChange    | ((Boolean) -> Unit)?      | 展开状态变化时的回调                | null                                | 否       |
| backgroundColor     | Color                     | 底层 `IconButton` 的背景颜色        | Color.Unspecified                   | 否       |
| cornerRadius        | Dp                        | 底层 `IconButton` 的圆角半径        | IconButtonDefaults.CornerRadius     | 否       |
| minHeight           | Dp                        | 底层 `IconButton` 的最小高度        | IconButtonDefaults.MinHeight        | 否       |
| minWidth            | Dp                        | 底层 `IconButton` 的最小宽度        | IconButtonDefaults.MinWidth         | 否       |
| content             | @Composable () -> Unit    | 按钮内显示的图标（或其他可组合内容）| -                                   | 是       |

### Entry 重载属性

| 属性名              | 类型          | 说明                 | 默认值 | 是否必须 |
| ------------------- | ------------- | -------------------- | ------ | -------- |
| entry               | DropdownEntry | 单个下拉选项分组     | -      | 是       |
| collapseOnSelection | Boolean       | 选中后是否关闭弹出框 | true   | 否       |

其余参数与上方 entries 重载完全一致。

### DropdownEntry 属性

| 属性名  | 类型                | 说明                                                                              | 默认值 | 是否必须 |
| ------- | ------------------- | --------------------------------------------------------------------------------- | ------ | -------- |
| items   | List\<DropdownItem> | 此分组中显示的条目                                                                | -      | 是       |
| enabled | Boolean             | 此分组是否启用。为 false 时禁用整组条目；为 true 时仍会遵循每个条目的 enabled 状态 | true   | 否       |

### DropdownItem 属性

| 属性名   | 类型                              | 说明                         | 默认值 | 是否必须 |
| -------- | --------------------------------- | ---------------------------- | ------ | -------- |
| text     | String                            | 选项显示的文本               | -      | 是       |
| enabled  | Boolean                           | 选项是否可点击，禁用时置灰   | true   | 否       |
| selected | Boolean                           | 选项是否处于选中状态         | false  | 否       |
| onClick  | (() -> Unit)?                     | 点击选项时触发的回调         | null   | 否       |
| icon     | @Composable ((Modifier) -> Unit)? | 显示在选项文本前的图标       | null   | 否       |
| summary  | String?                           | 显示在选项文本下方的摘要文本 | null   | 否       |

### DropdownColors 属性

| 属性名                 | 类型  | 说明             |
| ---------------------- | ----- | ---------------- |
| contentColor           | Color | 选项标题颜色     |
| summaryColor           | Color | 选项摘要颜色     |
| containerColor         | Color | 选项背景颜色     |
| selectedContentColor   | Color | 选中项标题颜色   |
| selectedSummaryColor   | Color | 选中项摘要颜色   |
| selectedContainerColor | Color | 选中项背景颜色   |
| selectedIndicatorColor | Color | 选中指示图标颜色 |
