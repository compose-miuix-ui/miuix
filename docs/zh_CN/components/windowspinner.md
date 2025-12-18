---
title: WindowSpinner
requiresScaffoldHost: false
prerequisites:
  - 可以在任何地方使用，不需要 `Scaffold` 或 `MiuixPopupHost`
  - 在窗口层级渲染
hostComponent: None
popupHost: None
---

# WindowSpinner

`WindowSpinner` 是 Miuix 中的下拉选择器组件，提供了标题、摘要和带有图标、文本的选项列表。它在窗口级别渲染，不需要 `Scaffold` 宿主，适用于没有或不使用 `Scaffold` 的场景。

<div style="position: relative; max-width: 700px; height: 350px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../../compose/index.html?id=windowSpinner" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

::: tip 提示
该组件不依赖 `Scaffold`，可在任意 Composable 作用域中使用。
:::

## 引入

```kotlin
import top.yukonga.miuix.kmp.extra.WindowSpinner
import top.yukonga.miuix.kmp.extra.SpinnerEntry
```

## 基本用法

WindowSpinner 组件提供了基础的下拉选择器功能：

```kotlin
var selectedIndex by remember { mutableStateOf(0) }
val options = listOf(
    SpinnerEntry(title = "选项 1"),
    SpinnerEntry(title = "选项 2"),
    SpinnerEntry(title = "选项 3"),
)

WindowSpinner(
    title = "下拉选择器",
    items = options,
    selectedIndex = selectedIndex,
    onSelectedIndexChange = { selectedIndex = it }
)
```

## 带图标和摘要的选项

```kotlin
// 创建一个圆角矩形的 Painter
private class RoundedRectanglePainter(
    private val cornerRadius: Dp = 6.dp
) : Painter() {
    override val intrinsicSize = Size.Unspecified
    override fun DrawScope.onDraw() {
        drawRoundRect(
            color = Color.White,
            size = Size(size.width, size.height),
            cornerRadius = CornerRadius(cornerRadius.toPx(), cornerRadius.toPx())
        )
    }
}

var selectedIndex by remember { mutableStateOf(0) }
val options = listOf(
    SpinnerEntry(
        icon = { Icon(RoundedRectanglePainter(), "Icon", Modifier.padding(end = 12.dp), Color(0xFFFF5B29)) },
        title = "红色主题",
        summary = "活力四射的红色"
    ),
    SpinnerEntry(
        icon = { Icon(RoundedRectanglePainter(), "Icon", Modifier.padding(end = 12.dp), Color(0xFF3482FF)) },
        title = "蓝色主题",
        summary = "沉稳冷静的蓝色"
    ),
)


WindowSpinner(
    title = "菜单",
    items = options,
    selectedIndex = selectedIndex,
    onSelectedIndexChange = { selectedIndex = it }
)
```

## 组件状态

### 禁用状态

```kotlin
SuperSpinner(
    title = "禁用选择器",
    summary = "此选择器当前不可用",
    items = listOf(SpinnerEntry(title = "选项 1")),
    selectedIndex = 0,
    onSelectedIndexChange = {},
    enabled = false
)
```

## 对话框模式

WindowSpinner 还支持对话框模式，适用于显示较多选项或需要更醒目的选择界面时。通过提供 `dialogButtonString` 参数即可激活此模式。

```kotlin
var selectedIndex by remember { mutableStateOf(0) }
val options = listOf(
    SpinnerEntry(title = "选项 A"),
    SpinnerEntry(title = "选项 B"),
    SpinnerEntry(title = "选项 C")
)

WindowSpinner(
    title = "对话框模式",
    dialogButtonString = "取消",
    items = options,
    selectedIndex = selectedIndex,
    onSelectedIndexChange = { selectedIndex = it },
)
```

## 属性

### WindowSpinner 属性（下拉列表模式）

| 属性名                | 类型                      | 说明                   | 默认值                                | 是否必须 |
| --------------------- | ------------------------- | ---------------------- | ------------------------------------- | -------- |
| items                 | List\<SpinnerEntry>       | 选项列表               | -                                     | 是       |
| selectedIndex         | Int                       | 当前选中项的索引       | -                                     | 是       |
| title                 | String                    | 选择器的标题           | -                                     | 是       |
| titleColor            | BasicComponentColors      | 标题文本的颜色配置     | BasicComponentDefaults.titleColor()   | 否       |
| summary               | String?                   | 摘要说明               | null                                  | 否       |
| summaryColor          | BasicComponentColors      | 摘要文本的颜色配置     | BasicComponentDefaults.summaryColor() | 否       |
| spinnerColors         | SpinnerColors             | 选择器的颜色配置       | SpinnerDefaults.spinnerColors()       | 否       |
| leftAction            | @Composable (() -> Unit)? | 左侧显示的自定义内容   | null                                  | 否       |
| bottomAction          | @Composable (() -> Unit)? | 底部自定义内容         | null                                  | 否       |
| modifier              | Modifier                  | 应用于组件的修饰符     | Modifier                              | 否       |
| insideMargin          | PaddingValues             | 组件内部内容的边距     | BasicComponentDefaults.InsideMargin   | 否       |
| maxHeight             | Dp?                       | 弹出框的最大高度       | null                                  | 否       |
| enabled               | Boolean                   | 组件是否可交互         | true                                  | 否       |
| showValue             | Boolean                   | 是否显示当前选中值     | true                                  | 否       |
| onClick               | (() -> Unit)?             | 点击选择器时的额外回调 | null                                  | 否       |
| onSelectedIndexChange | ((Int) -> Unit)?          | 选中项变化时的回调     | -                                     | 否       |

### WindowSpinner 属性 (对话框模式)

| 属性名                | 类型                      | 说明                   | 默认值                                | 是否必须 |
| --------------------- | ------------------------- | ---------------------- | ------------------------------------- | -------- |
| items                 | List\<SpinnerEntry>       | 选项列表               | -                                     | 是       |
| selectedIndex         | Int                       | 当前选中项的索引       | -                                     | 是       |
| title                 | String                    | 选择器的标题           | -                                     | 是       |
| titleColor            | BasicComponentColors      | 标题文本的颜色配置     | BasicComponentDefaults.titleColor()   | 否       |
| summary               | String?                   | 摘要说明               | null                                  | 否       |
| summaryColor          | BasicComponentColors      | 摘要文本的颜色配置     | BasicComponentDefaults.summaryColor() | 否       |
| spinnerColors         | SpinnerColors             | 选择器的颜色配置       | SpinnerDefaults.dialogSpinnerColors() | 否       |
| leftAction            | @Composable (() -> Unit)? | 左侧显示的自定义内容   | null                                  | 否       |
| dialogButtonString    | String                    | 对话框按钮的文本       | -                                     | 是       |
| popupModifier         | Modifier                  | 对话框弹出的修饰符     | Modifier                              | 否       |
| modifier              | Modifier                  | 应用于组件的修饰符     | Modifier                              | 否       |
| insideMargin          | PaddingValues             | 组件内部内容的边距     | BasicComponentDefaults.InsideMargin   | 否       |
| enabled               | Boolean                   | 组件是否可交互         | true                                  | 否       |
| showValue             | Boolean                   | 是否显示当前选中值     | true                                  | 否       |
| onClick               | (() -> Unit)?             | 点击选择器时的额外回调 | null                                  | 否       |
| onSelectedIndexChange | ((Int) -> Unit)?          | 选中项变化时的回调     | -                                     | 否       |

### SpinnerEntry 属性

| 属性名  | 类型                              | 说明           |
| ------- | --------------------------------- | -------------- |
| icon    | @Composable ((Modifier) -> Unit)? | 选项的图标组件 |
| title   | String?                           | 选项的标题     |
| summary | String?                           | 选项的摘要描述 |

### SpinnerColors 属性

| 属性名                 | 类型  | 说明             |
| ---------------------- | ----- | ---------------- |
| contentColor           | Color | 选项标题颜色     |
| summaryColor           | Color | 选项摘要颜色     |
| containerColor         | Color | 选项背景颜色     |
| selectedContentColor   | Color | 选中项标题颜色   |
| selectedSummaryColor   | Color | 选中项摘要颜色   |
| selectedContainerColor | Color | 选中项背景颜色   |
| selectedIndicatorColor | Color | 选中指示图标颜色 |
