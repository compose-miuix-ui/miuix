---
title: OverlayDropdownMenu
requiresScaffoldHost: true
prerequisites:
  - 必须在 `Scaffold` 中使用以提供 `MiuixPopupHost`
  - 未在 `Scaffold` 中使用将导致弹出内容无法渲染
  - 支持多个嵌套或并列的 `Scaffold`，无需额外配置
hostComponent: Scaffold
popupHost: MiuixPopupHost
---

# OverlayDropdownMenu

`OverlayDropdownMenu` 是基于 `BasicComponent` 的封装，点击后会展开 `OverlayDropdownPopup`。与 `OverlayDropdownPreference` 不同，它不再持有单一的选中索引——选中状态完全保存在每个 `DropdownItem` 的 `selected` 与 `onClick` 上。适用于动作菜单、多选菜单，或弹出项之间不构成单选互斥关系的场景。

<div style="position: relative; height: 410px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../../compose/index.html?id=overlayDropdownMenu" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

::: danger 使用前提
此组件依赖 `Scaffold` 提供的 `MiuixPopupHost` 以显示弹出内容。必须在 `Scaffold` 中使用，否则弹出内容无法正常渲染。
:::

## 引入

```kotlin
import top.yukonga.miuix.kmp.menu.OverlayDropdownMenu
import top.yukonga.miuix.kmp.basic.DropdownEntry
import top.yukonga.miuix.kmp.basic.DropdownItem
```

## 基本用法

传入单个 `DropdownEntry` 即可显示一个基础下拉菜单行：

```kotlin
var selectedIndex by remember { mutableStateOf(0) }
val entry = DropdownEntry(
    items = listOf("选项 1", "选项 2", "选项 3").mapIndexed { index, text ->
        DropdownItem(
            text = text,
            selected = selectedIndex == index,
            onClick = { selectedIndex = index },
        )
    }
)

Scaffold {
    OverlayDropdownMenu(
        title = "下拉菜单",
        entry = entry
    )
}
```

## 分组菜单

传入 `List<DropdownEntry>` 即可显示由分割线隔开的多个分组。`collapseOnSelection` 默认为 `entries.size <= 1`，多分组场景下选中后弹出框保持打开以便连续选择。

```kotlin
var sizeIndex by remember { mutableStateOf(0) }
var colorIndex by remember { mutableStateOf(0) }
val entries = listOf(
    DropdownEntry(
        items = listOf("小", "中").mapIndexed { index, text ->
            DropdownItem(text = text, selected = sizeIndex == index, onClick = { sizeIndex = index })
        }
    ),
    DropdownEntry(
        items = listOf("红色", "绿色", "蓝色").mapIndexed { index, text ->
            DropdownItem(text = text, selected = colorIndex == index, onClick = { colorIndex = index })
        }
    )
)

Scaffold {
    OverlayDropdownMenu(
        title = "分组菜单",
        entries = entries,
        collapseOnSelection = false
    )
}
```

## 多选

选中状态保存在 `DropdownItem.selected` 上，可以同时选中多个条目，在每个条目的 `onClick` 中切换选中状态即可。

```kotlin
var selected by remember { mutableStateOf(setOf("A1", "B2")) }
val entries = listOf(
    DropdownEntry(
        items = listOf("A1", "A2").map { text ->
            DropdownItem(
                text = text,
                selected = text in selected,
                onClick = {
                    selected = if (text in selected) selected - text else selected + text
                }
            )
        }
    ),
    DropdownEntry(
        items = listOf("B1", "B2", "B3").map { text ->
            DropdownItem(
                text = text,
                selected = text in selected,
                onClick = {
                    selected = if (text in selected) selected - text else selected + text
                }
            )
        }
    )
)

Scaffold {
    OverlayDropdownMenu(
        title = "多选菜单",
        entries = entries,
        collapseOnSelection = false
    )
}
```

## 监听展开状态

```kotlin
var expanded by remember { mutableStateOf(false) }
val entry = DropdownEntry(
    items = listOf("选项 1", "选项 2", "选项 3").map { DropdownItem(text = it) }
)

Scaffold {
    OverlayDropdownMenu(
        title = "监听展开",
        summary = if (expanded) "展开" else "收起",
        entry = entry,
        onExpandedChange = { expanded = it }
    )
}
```

## 组件状态

### 禁用状态

```kotlin
OverlayDropdownMenu(
    title = "禁用菜单",
    summary = "此菜单当前不可用",
    entry = DropdownEntry(items = listOf(DropdownItem(text = "选项 1"))),
    enabled = false
)
```

当所有 `DropdownEntry` 都不包含任何条目时，菜单也会被隐式禁用。

## 属性

### OverlayDropdownMenu 属性（Entries 重载）

| 属性名               | 类型                      | 说明                                                                                        | 默认值                                | 是否必须 |
| -------------------- | ------------------------- | ------------------------------------------------------------------------------------------- | ------------------------------------- | -------- |
| entries              | List\<DropdownEntry>      | 由分割线隔开的下拉选项分组                                                                  | -                                     | 是       |
| title                | String                    | 菜单行的标题                                                                                | -                                     | 是       |
| modifier             | Modifier                  | 应用于组件的修饰符                                                                          | Modifier                              | 否       |
| titleColor           | BasicComponentColors      | 标题文本的颜色配置                                                                          | BasicComponentDefaults.titleColor()   | 否       |
| summary              | String?                   | 菜单的摘要说明                                                                              | null                                  | 否       |
| summaryColor         | BasicComponentColors      | 摘要文本的颜色配置                                                                          | BasicComponentDefaults.summaryColor() | 否       |
| dropdownColors       | DropdownColors            | 下拉选项的颜色配置                                                                          | DropdownDefaults.dropdownColors()     | 否       |
| startAction          | @Composable (() -> Unit)? | 左侧显示的自定义内容                                                                        | null                                  | 否       |
| bottomAction         | @Composable (() -> Unit)? | 底部显示的自定义内容                                                                        | null                                  | 否       |
| insideMargin         | PaddingValues             | 组件内部内容的边距                                                                          | BasicComponentDefaults.InsideMargin   | 否       |
| maxHeight            | Dp?                       | 下拉菜单的最大高度                                                                          | null                                  | 否       |
| enabled              | Boolean                   | 组件是否可交互                                                                              | true                                  | 否       |
| renderInRootScaffold | Boolean                   | 是否在根（最外层）Scaffold 中渲染弹窗。为 true 时，弹窗覆盖全屏。为 false 时，在当前 Scaffold 的范围内渲染并进行位置补偿 | true | 否 |
| collapseOnSelection  | Boolean                   | 每次选中后是否关闭弹出框                                                                    | entries.size <= 1                     | 否       |
| onExpandedChange     | ((Boolean) -> Unit)?      | 展开状态变化时的回调                                                                        | null                                  | 否       |

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
