---
title: WindowDropdownPreference
requiresScaffoldHost: false
prerequisites:
  - 可以在任何地方使用，不需要 `Scaffold` 或 `MiuixPopupHost`
  - 在窗口层级渲染
hostComponent: None
popupHost: None
---

# WindowDropdownPreference

`WindowDropdownPreference` 是 Miuix 中的下拉菜单组件，提供了标题、摘要和下拉选项列表。它在窗口级别渲染，不需要 `Scaffold` 宿主，适用于没有或不使用 `Scaffold` 的场景。

<div style="position: relative; height: 360px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../../compose/index.html?id=windowDropdownPreference" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

::: tip 提示
该组件不依赖 `Scaffold`，可在任意 Composable 作用域中使用。
:::

## 引入

```kotlin
import top.yukonga.miuix.kmp.preference.WindowDropdownPreference
import top.yukonga.miuix.kmp.basic.DropdownEntry
import top.yukonga.miuix.kmp.basic.DropdownItem
```

## 基本用法

WindowDropdownPreference 组件提供了基础的下拉菜单功能：

```kotlin
var selectedIndex by remember { mutableStateOf(0) }
val options = listOf("选项 1", "选项 2", "选项 3")

WindowDropdownPreference(
    title = "下拉菜单",
    items = options,
    selectedIndex = selectedIndex,
    onSelectedIndexChange = { selectedIndex = it }
)
```

## 带摘要的下拉菜单

```kotlin
var selectedIndex by remember { mutableStateOf(0) }
val options = listOf("中文", "English", "日本語")

WindowDropdownPreference(
    title = "语言设置",
    summary = "选择您的首选语言",
    items = options,
    selectedIndex = selectedIndex,
    onSelectedIndexChange = { selectedIndex = it }
)
```

## 监听展开状态

```kotlin
var selectedIndex by remember { mutableStateOf(0) }
var expanded by remember { mutableStateOf(false) }
val options = listOf("选项 1", "选项 2", "选项 3")

WindowDropdownPreference(
    title = "下拉菜单",
    summary = if (expanded) "展开" else "收起",
    items = options,
    selectedIndex = selectedIndex,
    onExpandedChange = { expanded = it },
    onSelectedIndexChange = { selectedIndex = it }
)
```

## 自定义条目

当单个下拉项需要额外状态时，例如选中状态、点击回调或禁用某个选项，可以使用 `DropdownEntry` 和 `DropdownItem`。

```kotlin
var selectedIndex by remember { mutableStateOf(0) }
val entry = DropdownEntry(
    items = listOf(
        DropdownItem(text = "选项 1", selected = selectedIndex == 0, onClick = { selectedIndex = 0 }),
        DropdownItem(text = "选项 2", enabled = false),
        DropdownItem(text = "选项 3", selected = selectedIndex == 2, onClick = { selectedIndex = 2 }),
    )
)

WindowDropdownPreference(
    title = "下拉菜单",
    entry = entry
)
```

禁用的下拉项不可点击，文本和选中指示图标会使用禁用颜色。

## 分组下拉菜单

使用 `entries` 可以显示多个由分割线隔开的下拉分组。每个条目都可以维护自己的选中状态和点击回调。

```kotlin
var firstSelectedIndex by remember { mutableStateOf(0) }
var secondSelectedIndex by remember { mutableStateOf(0) }
val entries = listOf(
    DropdownEntry(
        items = listOf("小", "中").mapIndexed { index, text ->
            DropdownItem(text = text, selected = firstSelectedIndex == index, onClick = { firstSelectedIndex = index })
        }
    ),
    DropdownEntry(
        items = listOf("红色", "绿色", "蓝色").mapIndexed { index, text ->
            DropdownItem(text = text, selected = secondSelectedIndex == index, onClick = { secondSelectedIndex = index })
        }
    )
)

WindowDropdownPreference(
    title = "分组下拉菜单",
    entries = entries,
    collapseOnSelection = false
)
```

对于 `entries` 重载，`collapseOnSelection` 控制选中条目后是否关闭弹窗。它默认是 `entries.size <= 1`，单个分组会在选中后关闭，多个分组会保持打开以便连续修改。

## 多选

因为选中状态放在 `DropdownItem` 上，可以用集合保存多个选中值，并在每个条目的 `onClick` 中切换选中状态。

```kotlin
var selectedItems by remember { mutableStateOf(setOf("A1", "B2")) }
val entries = listOf(
    DropdownEntry(
        items = listOf("A1", "A2").map { text ->
            DropdownItem(
                text = text,
                selected = text in selectedItems,
                onClick = {
                    selectedItems = if (text in selectedItems) selectedItems - text else selectedItems + text
                }
            )
        }
    ),
    DropdownEntry(
        items = listOf("B1", "B2", "B3").map { text ->
            DropdownItem(
                text = text,
                selected = text in selectedItems,
                onClick = {
                    selectedItems = if (text in selectedItems) selectedItems - text else selectedItems + text
                }
            )
        }
    )
)

WindowDropdownPreference(
    title = "多选下拉菜单",
    entries = entries,
    collapseOnSelection = false
)
```

## 组件状态

### 禁用状态

```kotlin
WindowDropdownPreference(
    title = "禁用下拉菜单",
    summary = "此下拉菜单当前不可用",
    items = listOf("选项 1"),
    selectedIndex = 0,
    onSelectedIndexChange = {},
    enabled = false
)
```

## 属性

### WindowDropdownPreference 属性

| 属性名                | 类型                      | 说明                 | 默认值                                | 是否必须 |
| --------------------- | ------------------------- | -------------------- | ------------------------------------- | -------- |
| items                 | List\<String>             | 下拉选项列表         | -                                     | 是       |
| selectedIndex         | Int                       | 当前选中项的索引     | -                                     | 是       |
| title                 | String                    | 下拉菜单的标题       | -                                     | 是       |
| modifier              | Modifier                  | 应用于组件的修饰符   | Modifier                              | 否       |
| titleColor            | BasicComponentColors      | 标题文本的颜色配置   | BasicComponentDefaults.titleColor()   | 否       |
| summary               | String?                   | 下拉菜单的摘要说明   | null                                  | 否       |
| summaryColor          | BasicComponentColors      | 摘要文本的颜色配置   | BasicComponentDefaults.summaryColor() | 否       |
| dropdownColors        | DropdownColors            | 下拉菜单的颜色配置   | DropdownDefaults.dropdownColors()     | 否       |
| startAction           | @Composable (() -> Unit)? | 左侧显示的自定义内容 | null                                  | 否       |
| bottomAction          | @Composable (() -> Unit)? | 底部自定义内容       | null                                  | 否       |
| insideMargin          | PaddingValues             | 组件内部内容的边距   | BasicComponentDefaults.InsideMargin   | 否       |
| maxHeight             | Dp?                       | 下拉菜单的最大高度   | null                                  | 否       |
| enabled               | Boolean                   | 组件是否可交互       | true                                  | 否       |
| showValue             | Boolean                   | 是否显示当前选中值   | true                                  | 否       |
| onExpandedChange      | ((Boolean) -> Unit)?      | 展开状态变化时的回调 | null                                  | 否       |
| onSelectedIndexChange | ((Int) -> Unit)?          | 选中项变化时的回调   | -                                     | 否       |

### Entry 重载属性

| 属性名              | 类型          | 说明                   | 默认值 | 是否必须 |
| ------------------- | ------------- | ---------------------- | ------ | -------- |
| entry               | DropdownEntry | 单个下拉条目分组       | -      | 是       |
| collapseOnSelection | Boolean       | 选中条目后是否关闭弹窗 | true   | 否       |

### Entries 分组重载属性

| 属性名              | 类型                 | 说明                       | 默认值            | 是否必须 |
| ------------------- | -------------------- | -------------------------- | ----------------- | -------- |
| entries             | List\<DropdownEntry> | 由分割线隔开的下拉条目分组 | -                 | 是       |
| collapseOnSelection | Boolean              | 每次选中条目后是否关闭弹窗 | entries.size <= 1 | 否       |

### DropdownEntry 属性

| 属性名  | 类型                | 说明                       | 默认值 | 是否必须 |
| ------- | ------------------- | -------------------------- | ------ | -------- |
| items   | List\<DropdownItem> | 此分组中显示的条目         | -      | 是       |
| enabled | Boolean             | 此分组是否启用。为 false 时禁用整组条目；为 true 时仍会遵循每个条目的 enabled 状态 | true   | 否       |

分组标题预留给后续使用。原版 MIUI 下拉样式目前没有对应的分组标题表现，因此 `title` 字段暂不开放。

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
