---
title: SuperDropdown
requiresScaffoldHost: true
prerequisites:
  - 必须在 `Scaffold` 中使用以提供 `MiuixPopupHost`
  - 未在 `Scaffold` 中使用将导致弹出内容无法渲染
  - 多层 `Scaffold` 时仅在顶层保留 `MiuixPopupHost`，其余置空
hostComponent: Scaffold
popupHost: MiuixPopupHost
---

# SuperDropdown

`SuperDropdown` 是 Miuix 中的下拉菜单组件，提供了标题、摘要和下拉选项列表，支持点击交互，常用于选项设置和列表选择中。

<div style="position: relative; max-width: 700px; height: 285px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../../compose/index.html?id=superDropdown" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

::: danger 使用前提
此组件依赖 `Scaffold` 提供的 `MiuixPopupHost` 以显示弹出内容。必须在 `Scaffold` 中使用，否则弹出内容无法正常渲染。
:::

## 引入

```kotlin
import top.yukonga.miuix.kmp.extra.SuperDropdown
```

## 基本用法

SuperDropdown 组件提供了基础的下拉菜单功能：

```kotlin
var selectedIndex by remember { mutableStateOf(0) }
val options = listOf("选项 1", "选项 2", "选项 3")

Scaffold {
    SuperDropdown(
        title = "下拉菜单",
        items = options,
        selectedIndex = selectedIndex,
        onSelectedIndexChange = { selectedIndex = it }
    )
}
```

## 带摘要的下拉菜单

```kotlin
var selectedIndex by remember { mutableStateOf(0) }
val options = listOf("中文", "English", "日本語")

Scaffold {
    SuperDropdown(
        title = "语言设置",
        summary = "选择您的首选语言",
        items = options,
        selectedIndex = selectedIndex,
        onSelectedIndexChange = { selectedIndex = it }
    )
}
```

## 组件状态

### 禁用状态

```kotlin
SuperDropdown(
    title = "禁用下拉菜单",
    summary = "此下拉菜单当前不可用",
    items = listOf("选项 1"),
    selectedIndex = 0,
    onSelectedIndexChange = {},
    enabled = false
)
```

## 属性

### SuperDropdown 属性

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
| leftAction            | @Composable (() -> Unit)? | 左侧显示的自定义内容 | null                                  | 否       |
| bottomAction          | @Composable (() -> Unit)? | 底部自定义内容       | null                                  | 否       |
| insideMargin          | PaddingValues             | 组件内部内容的边距   | BasicComponentDefaults.InsideMargin   | 否       |
| maxHeight             | Dp?                       | 下拉菜单的最大高度   | null                                  | 否       |
| enabled               | Boolean                   | 组件是否可交互       | true                                  | 否       |
| showValue             | Boolean                   | 是否显示当前选中的值 | true                                  | 否       |
| onSelectedIndexChange | ((Int) -> Unit)?          | 选中项变化时的回调   | -                                     | 否       |

### DropdownColors 属性

| 属性名                 | 类型  | 说明           |
| ---------------------- | ----- | -------------- |
| contentColor           | Color | 选项文本颜色   |
| containerColor         | Color | 选项背景颜色   |
| selectedContentColor   | Color | 选中项文本颜色 |
| selectedContainerColor | Color | 选中项背景颜色 |
