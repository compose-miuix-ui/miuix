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

<div style="position: relative; max-width: 700px; height: 285px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../../compose/index.html?id=windowDropdown" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
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

当单个下拉项需要额外状态时，例如禁用某个选项，可以使用 `DropdownEntry` 和 `DropdownItem`。

```kotlin
var selectedIndex by remember { mutableStateOf(0) }
val entry = DropdownEntry(
    items = listOf(
        DropdownItem(text = "选项 1"),
        DropdownItem(text = "选项 2", enabled = false),
        DropdownItem(text = "选项 3"),
    ),
    selectedIndex = selectedIndex,
    onSelectedIndexChange = { selectedIndex = it }
)

WindowDropdownPreference(
    title = "下拉菜单",
    entry = entry
)
```

禁用的下拉项不可点击，文本和选中指示图标会使用禁用颜色。

## 分组下拉菜单

使用 `entries` 可以显示多个由分割线隔开的下拉分组。每个分组都可以维护自己的选中索引和回调。

```kotlin
var firstSelectedIndex by remember { mutableStateOf(0) }
var secondSelectedIndex by remember { mutableStateOf(0) }
val entries = listOf(
    DropdownEntry(
        items = listOf("小", "中").map { DropdownItem(text = it) },
        selectedIndex = firstSelectedIndex,
        onSelectedIndexChange = { firstSelectedIndex = it }
    ),
    DropdownEntry(
        items = listOf("红色", "绿色", "蓝色").map { DropdownItem(text = it) },
        selectedIndex = secondSelectedIndex,
        onSelectedIndexChange = { secondSelectedIndex = it }
    )
)

WindowDropdownPreference(
    title = "分组下拉菜单",
    entries = entries,
    collapseOnSelection = false
)
```

对于 `entries` 重载，`collapseOnSelection` 控制选中条目后是否关闭弹窗。它默认是 `false`，便于用户在不重新打开弹窗的情况下修改多个分组。

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

| 属性名              | 类型                 | 说明                       | 默认值 | 是否必须 |
| ------------------- | -------------------- | -------------------------- | ------ | -------- |
| entries             | List\<DropdownEntry> | 由分割线隔开的下拉条目分组 | -      | 是       |
| collapseOnSelection | Boolean              | 每次选中条目后是否关闭弹窗 | false  | 否       |

### DropdownEntry 属性

| 属性名                | 类型                | 说明                                 | 默认值 | 是否必须 |
| --------------------- | ------------------- | ------------------------------------ | ------ | -------- |
| items                 | List\<DropdownItem> | 此分组中显示的条目                   | -      | 是       |
| selectedIndex         | Int?                | 选中项索引，为 null 时不显示选中状态 | null   | 否       |
| onSelectedIndexChange | ((Int) -> Unit)?    | 选中条目时触发的回调                 | null   | 否       |

### DropdownItem 属性

| 属性名  | 类型          | 说明                         | 默认值 | 是否必须 |
| ------- | ------------- | ---------------------------- | ------ | -------- |
| text    | String        | 条目显示文本                 | -      | 是       |
| enabled | Boolean       | 条目是否可点击，禁用项会变灰 | true   | 否       |
| onClick | (() -> Unit)? | 预留给动作式下拉条目使用     | null   | 否       |

### DropdownColors 属性

| 属性名                 | 类型  | 说明           |
| ---------------------- | ----- | -------------- |
| contentColor           | Color | 选项文本颜色   |
| containerColor         | Color | 选项背景颜色   |
| selectedContentColor   | Color | 选中项文本颜色 |
| selectedContainerColor | Color | 选中项背景颜色 |
