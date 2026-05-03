---
title: WindowSpinnerPreference
requiresScaffoldHost: false
prerequisites:
  - 可以在任何地方使用，不需要 `Scaffold` 或 `MiuixPopupHost`
  - 在窗口层级渲染
hostComponent: None
popupHost: None
---

# WindowSpinnerPreference

`WindowSpinnerPreference` 是 Miuix 中的下拉选择器组件，提供了标题、摘要和带有图标、文本的选项列表。它在窗口级别渲染，不需要 `Scaffold` 宿主，适用于没有或不使用 `Scaffold` 的场景。

<div style="position: relative; height: 420px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../../compose/index.html?id=windowSpinnerPreference" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

::: tip 提示
该组件不依赖 `Scaffold`，可在任意 Composable 作用域中使用。
:::

## 引入

```kotlin
import top.yukonga.miuix.kmp.preference.WindowSpinnerPreference
import top.yukonga.miuix.kmp.basic.DropdownEntry
import top.yukonga.miuix.kmp.basic.DropdownItem
```

## 基本用法

WindowSpinnerPreference 组件提供了基础的下拉选择器功能：

```kotlin
var selectedIndex by remember { mutableStateOf(0) }
val options = listOf(
    DropdownItem(text = "选项 1"),
    DropdownItem(text = "选项 2"),
    DropdownItem(text = "选项 3"),
)

WindowSpinnerPreference(
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
    DropdownItem(
        icon = { Icon(RoundedRectanglePainter(), "Icon", Modifier.padding(end = 12.dp), Color(0xFFFF5B29)) },
        text = "红色主题",
        summary = "活力四射的红色"
    ),
    DropdownItem(
        icon = { Icon(RoundedRectanglePainter(), "Icon", Modifier.padding(end = 12.dp), Color(0xFF3482FF)) },
        text = "蓝色主题",
        summary = "沉稳冷静的蓝色"
    ),
)


WindowSpinnerPreference(
    title = "菜单",
    items = options,
    selectedIndex = selectedIndex,
    onSelectedIndexChange = { selectedIndex = it }
)
```

## 组件状态

### 禁用状态

```kotlin
WindowSpinnerPreference(
    title = "禁用选择器",
    summary = "此选择器当前不可用",
    items = listOf(DropdownItem(text = "选项 1")),
    selectedIndex = 0,
    onSelectedIndexChange = {},
    enabled = false
)
```

## 对话框模式

WindowSpinnerPreference 还支持对话框模式，适用于显示较多选项或需要更醒目的选择界面时。通过提供 `dialogButtonString` 参数即可激活此模式。

```kotlin
var selectedIndex by remember { mutableStateOf(0) }
val options = listOf(
    DropdownItem(text = "选项 A"),
    DropdownItem(text = "选项 B"),
    DropdownItem(text = "选项 C")
)

WindowSpinnerPreference(
    title = "对话框模式",
    dialogButtonString = "取消",
    items = options,
    selectedIndex = selectedIndex,
    onSelectedIndexChange = { selectedIndex = it },
)
```

## 分组选项

当选项需要选中状态、禁用状态、点击回调、图标或摘要时，可以使用 `DropdownEntry`。使用 `entries` 可以显示多个分组，分组之间会用分割线隔开。

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

WindowSpinnerPreference(
    title = "分组选项",
    entries = entries,
    collapseOnSelection = false
)
```

对于 `entries` 重载，`collapseOnSelection` 控制点击选项后是否关闭弹出框。默认值为 `entries.size <= 1`，单个分组会在选中后关闭，多个分组会保持打开以便连续选择。提供 `dialogButtonString` 后，对话框模式也支持相同的 `entry` 和 `entries` 重载。

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

WindowSpinnerPreference(
    title = "多选选择器",
    entries = entries,
    collapseOnSelection = false
)
```

## 监听展开状态

```kotlin
var selectedIndex by remember { mutableStateOf(0) }
var expanded by remember { mutableStateOf(false) }
val options = listOf(
    DropdownItem(text = "选项 A"),
    DropdownItem(text = "选项 B"),
    DropdownItem(text = "选项 C")
)

WindowSpinnerPreference(
    title = "下拉选择器",
    summary = if (expanded) "展开" else "收起",
    items = options,
    selectedIndex = selectedIndex,
    onExpandedChange = { expanded = it },
    onSelectedIndexChange = { selectedIndex = it }
)
```

## 属性

### WindowSpinnerPreference 属性（下拉列表模式）

| 属性名                | 类型                      | 说明                 | 默认值                                | 是否必须 |
| --------------------- | ------------------------- | -------------------- | ------------------------------------- | -------- |
| items                 | List\<DropdownItem>       | 选项列表             | -                                     | 是       |
| selectedIndex         | Int                       | 当前选中项的索引     | -                                     | 是       |
| title                 | String                    | 选择器的标题         | -                                     | 是       |
| modifier              | Modifier                  | 应用于组件的修饰符   | Modifier                              | 否       |
| titleColor            | BasicComponentColors      | 标题文本的颜色配置   | BasicComponentDefaults.titleColor()   | 否       |
| summary               | String?                   | 摘要说明             | null                                  | 否       |
| summaryColor          | BasicComponentColors      | 摘要文本的颜色配置   | BasicComponentDefaults.summaryColor() | 否       |
| spinnerColors         | DropdownColors            | 选择器的颜色配置     | DropdownDefaults.dropdownColors()     | 否       |
| startAction           | @Composable (() -> Unit)? | 左侧显示的自定义内容 | null                                  | 否       |
| bottomAction          | @Composable (() -> Unit)? | 底部自定义内容       | null                                  | 否       |
| insideMargin          | PaddingValues             | 组件内部内容的边距   | BasicComponentDefaults.InsideMargin   | 否       |
| maxHeight             | Dp?                       | 弹出框的最大高度     | null                                  | 否       |
| enabled               | Boolean                   | 组件是否可交互       | true                                  | 否       |
| showValue             | Boolean                   | 是否显示当前选中值   | true                                  | 否       |
| onExpandedChange      | ((Boolean) -> Unit)?      | 展开状态变化时的回调 | null                                  | 否       |
| onSelectedIndexChange | ((Int) -> Unit)?          | 选中项变化时的回调   | -                                     | 否       |

### Entry 重载属性

| 属性名              | 类型          | 说明                 | 默认值 | 是否必须 |
| ------------------- | ------------- | -------------------- | ------ | -------- |
| entry               | DropdownEntry | 单个下拉选项分组     | -      | 是       |
| collapseOnSelection | Boolean       | 选中后是否关闭弹出框 | true   | 否       |

### Entries 分组重载属性

| 属性名              | 类型                 | 说明                       | 默认值            | 是否必须 |
| ------------------- | -------------------- | -------------------------- | ----------------- | -------- |
| entries             | List\<DropdownEntry> | 由分割线隔开的下拉选项分组 | -                 | 是       |
| collapseOnSelection | Boolean              | 每次选中后是否关闭弹出框   | entries.size <= 1 | 否       |

### WindowSpinnerPreference 属性 (对话框模式)

| 属性名                | 类型                      | 说明                 | 默认值                                  | 是否必须 |
| --------------------- | ------------------------- | -------------------- | --------------------------------------- | -------- |
| items                 | List\<DropdownItem>       | 选项列表             | -                                       | 是       |
| selectedIndex         | Int                       | 当前选中项的索引     | -                                       | 是       |
| title                 | String                    | 选择器的标题         | -                                       | 是       |
| dialogButtonString    | String                    | 对话框按钮的文本     | -                                       | 是       |
| modifier              | Modifier                  | 应用于组件的修饰符   | Modifier                                | 否       |
| popupModifier         | Modifier                  | 对话框弹出的修饰符   | Modifier                                | 否       |
| titleColor            | BasicComponentColors      | 标题文本的颜色配置   | BasicComponentDefaults.titleColor()     | 否       |
| summary               | String?                   | 摘要说明             | null                                    | 否       |
| summaryColor          | BasicComponentColors      | 摘要文本的颜色配置   | BasicComponentDefaults.summaryColor()   | 否       |
| spinnerColors         | DropdownColors            | 选择器的颜色配置     | DropdownDefaults.dialogDropdownColors() | 否       |
| startAction           | @Composable (() -> Unit)? | 左侧显示的自定义内容 | null                                    | 否       |
| bottomAction          | @Composable (() -> Unit)? | 底部自定义内容       | null                                    | 否       |
| insideMargin          | PaddingValues             | 组件内部内容的边距   | BasicComponentDefaults.InsideMargin     | 否       |
| enabled               | Boolean                   | 组件是否可交互       | true                                    | 否       |
| showValue             | Boolean                   | 是否显示当前选中值   | true                                    | 否       |
| onExpandedChange      | ((Boolean) -> Unit)?      | 展开状态变化时的回调 | null                                    | 否       |
| onSelectedIndexChange | ((Int) -> Unit)?          | 选中项变化时的回调   | -                                       | 否       |

### 对话框 Entry 重载属性

| 属性名              | 类型          | 说明                 | 默认值 | 是否必须 |
| ------------------- | ------------- | -------------------- | ------ | -------- |
| entry               | DropdownEntry | 单个下拉选项分组     | -      | 是       |
| dialogButtonString  | String        | 对话框按钮的文本     | -      | 是       |
| collapseOnSelection | Boolean       | 选中后是否关闭对话框 | true   | 否       |

### 对话框 Entries 分组重载属性

| 属性名             | 类型                 | 说明                       | 默认值            | 是否必须 |
| ------------------ | -------------------- | -------------------------- | ----------------- | -------- |
| entries            | List\<DropdownEntry> | 由分割线隔开的下拉选项分组 | -                 | 是       |
| dialogButtonString | String               | 对话框按钮的文本           | -                 | 是       |
| collapseOnSelection | Boolean             | 每次选中后是否关闭对话框   | entries.size <= 1 | 否       |

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
