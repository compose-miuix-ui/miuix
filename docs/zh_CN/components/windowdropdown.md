---
title: WindowDropdown
requiresScaffoldHost: false
prerequisites:
  - 可以在任何地方使用，不需要 `Scaffold` 或 `MiuixPopupHost`
  - 在窗口层级渲染
hostComponent: None
popupHost: None
---

# WindowDropdown

`WindowDropdown` 是 Miuix 中的下拉菜单组件，提供了标题、摘要和下拉选项列表。它在窗口级别渲染，不需要 `Scaffold` 宿主，适用于没有或不使用 `Scaffold` 的场景。

<div style="position: relative; max-width: 700px; height: 285px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../../compose/index.html?id=windowDropdown" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

::: tip 提示
该组件不依赖 `Scaffold`，可在任意 Composable 作用域中使用。
:::

## 引入

```kotlin
import top.yukonga.miuix.kmp.extra.WindowDropdown
```

## 基本用法

WindowDropdown 组件提供了基础的下拉菜单功能：

```kotlin
var selectedIndex by remember { mutableStateOf(0) }
val options = listOf("选项 1", "选项 2", "选项 3")

WindowDropdown(
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

WindowDropdown(
    title = "语言设置",
    summary = "选择您的首选语言",
    items = options,
    selectedIndex = selectedIndex,
    onSelectedIndexChange = { selectedIndex = it }
)
```

## 组件状态

### 禁用状态

```kotlin
WindowDropdown(
    title = "禁用下拉菜单",
    summary = "此下拉菜单当前不可用",
    items = listOf("选项 1"),
    selectedIndex = 0,
    onSelectedIndexChange = {},
    enabled = false
)
```

## 属性

### WindowDropdown 属性

| 属性名                | 类型                      | 说明                     | 默认值                                | 是否必须 |
| --------------------- | ------------------------- | ------------------------ | ------------------------------------- | -------- |
| items                 | List\<String>             | 下拉选项列表             | -                                     | 是       |
| selectedIndex         | Int                       | 当前选中项的索引         | -                                     | 是       |
| title                 | String                    | 下拉菜单的标题           | -                                     | 是       |
| titleColor            | BasicComponentColors      | 标题文本的颜色配置       | BasicComponentDefaults.titleColor()   | 否       |
| summary               | String?                   | 下拉菜单的摘要说明       | null                                  | 否       |
| summaryColor          | BasicComponentColors      | 摘要文本的颜色配置       | BasicComponentDefaults.summaryColor() | 否       |
| dropdownColors        | DropdownColors            | 下拉菜单的颜色配置       | DropdownDefaults.dropdownColors()     | 否       |
| leftAction            | @Composable (() -> Unit)? | 左侧显示的自定义内容     | null                                  | 否       |
| bottomAction          | @Composable (() -> Unit)? | 底部自定义内容           | null                                  | 否       |
| modifier              | Modifier                  | 应用于组件的修饰符       | Modifier                              | 否       |
| insideMargin          | PaddingValues             | 组件内部内容的边距       | BasicComponentDefaults.InsideMargin   | 否       |
| maxHeight             | Dp?                       | 下拉菜单的最大高度       | null                                  | 否       |
| enabled               | Boolean                   | 组件是否可交互           | true                                  | 否       |
| showValue             | Boolean                   | 是否显示当前选中值       | true                                  | 否       |
| onClick               | (() -> Unit)?             | 点击下拉菜单时的额外回调 | null                                  | 否       |
| onSelectedIndexChange | ((Int) -> Unit)?          | 选中项变化时的回调       | -                                     | 否       |

### DropdownColors 属性

| 属性名                 | 类型  | 说明           |
| ---------------------- | ----- | -------------- |
| contentColor           | Color | 选项文本颜色   |
| containerColor         | Color | 选项背景颜色   |
| selectedContentColor   | Color | 选中项文本颜色 |
| selectedContainerColor | Color | 选中项背景颜色 |
