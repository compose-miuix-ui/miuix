---
title: WindowCascadingListPopup
requiresScaffoldHost: false
prerequisites:
  - 可以在任何地方使用，不需要 `Scaffold` 或 `MiuixPopupHost`
  - 在窗口层级渲染
hostComponent: None
popupHost: None
---

# WindowCascadingListPopup

`WindowCascadingListPopup` 是支持二级菜单的弹出列表组件，通过 `Dialog` 在窗口层级渲染。与 `OverlayCascadingListPopup` 不同，它不需要 `Scaffold` 或 `MiuixPopupHost`。`DropdownItem.children` 非空的项会成为子菜单触发行；级联深度限制为 **2 级**。

<div style="position: relative; height: 410px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../../../compose/index.html?id=windowCascadingListPopup" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

::: tip 提示
该组件不依赖 `Scaffold`，可在任意 Composable 作用域中使用。
:::

## 引入

```kotlin
import top.yukonga.miuix.kmp.window.WindowCascadingListPopup
import top.yukonga.miuix.kmp.basic.DropdownEntry
import top.yukonga.miuix.kmp.basic.DropdownItem
```

## 基本用法

`WindowCascadingListPopup` 与 `OverlayCascadingListPopup` 用法一致，区别仅在于无需 `Scaffold` 宿主。下例展示了两个顶层项：一个扁平排序分组，与一个会展开二级列表的"查看模式"触发行。

```kotlin
var showPopup by remember { mutableStateOf(false) }
var sortIndex by remember { mutableStateOf(0) }
var viewIndex by remember { mutableStateOf(0) }

val sortLabels = listOf("按拍摄日期排序", "按添加日期排序")
val viewLabels = listOf("按日期分组", "紧凑视图")
val entries = listOf(
    DropdownEntry(
        items = sortLabels.mapIndexed { idx, label ->
            DropdownItem(
                text = label,
                selected = sortIndex == idx,
                onClick = { sortIndex = idx },
            )
        },
    ),
    DropdownEntry(
        items = listOf(
            DropdownItem(
                text = "查看模式",
                children = viewLabels.mapIndexed { idx, label ->
                    DropdownItem(
                        text = label,
                        selected = viewIndex == idx,
                        onClick = { viewIndex = idx },
                    )
                },
            ),
        ),
    ),
)

Box {
    TextButton(
        text = "点击显示菜单",
        onClick = { showPopup = true },
    )
    WindowCascadingListPopup(
        show = showPopup,
        entries = entries,
        onDismissRequest = { showPopup = false },
    )
}
```

::: tip 级联深度
级联深度上限为 2。二级菜单中的项不会再处理自己的 `children`；更深层的子树会被静默忽略。
:::

## 属性

### WindowCascadingListPopup

| 属性名                | 类型                        | 说明                                                                | 默认值                                     |
| --------------------- | --------------------------- | ------------------------------------------------------------------- | ------------------------------------------ |
| show                  | Boolean                     | 是否显示弹窗                                                        | -                                          |
| entries               | List\<DropdownEntry>        | 由分割线分组的下拉条目；顶层中 `children` 非空的项会成为子菜单触发行 | -                                          |
| onDismissRequest      | () -> Unit                  | 用户请求关闭（点击外部、触发返回等）时调用                          | -                                          |
| popupModifier         | Modifier                    | 应用于弹窗主体的修饰符                                              | Modifier                                   |
| onDismissFinished     | (() -> Unit)?               | 退出动画结束后调用                                                  | null                                       |
| popupPositionProvider | PopupPositionProvider       | 主弹窗相对锚点的定位策略                                            | ListPopupDefaults.DropdownPositionProvider |
| alignment             | PopupPositionProvider.Align | 主弹窗的对齐方式                                                    | PopupPositionProvider.Align.End            |
| enableWindowDim       | Boolean                     | 弹窗显示时是否变暗背景                                              | true                                       |
| maxHeight             | Dp?                         | 任一面的最大高度。null 时由安全区限制                               | null                                       |
| minWidth              | Dp                          | 弹窗的最小宽度                                                      | 200.dp                                     |
| dropdownColors        | DropdownColors              | 每一行使用的颜色配置                                                | DropdownDefaults.dropdownColors()          |
| collapseOnSelection   | Boolean                     | 为 true 时，选中任何叶子项后即关闭弹窗                              | true                                       |

### DropdownEntry

| 属性名  | 类型                | 说明                                                                              | 默认值 | 是否必须 |
| ------- | ------------------- | --------------------------------------------------------------------------------- | ------ | -------- |
| items   | List\<DropdownItem> | 此分组中显示的条目                                                                | -      | 是       |
| enabled | Boolean             | 此分组是否启用。为 false 时禁用整组条目；为 true 时仍会遵循每个条目的 enabled 状态 | true   | 否       |

### DropdownItem

| 属性名   | 类型                              | 说明                                                                          | 默认值 | 是否必须 |
| -------- | --------------------------------- | ----------------------------------------------------------------------------- | ------ | -------- |
| text     | String                            | 选项显示的文本                                                                | -      | 是       |
| enabled  | Boolean                           | 选项是否可点击，禁用时置灰                                                    | true   | 否       |
| selected | Boolean                           | 选项是否处于选中状态                                                          | false  | 否       |
| onClick  | (() -> Unit)?                     | 点击选项时触发的回调。当 `children` 非空时被忽略（点击会改为展开二级菜单）    | null   | 否       |
| icon     | @Composable ((Modifier) -> Unit)? | 显示在选项文本前的图标                                                        | null   | 否       |
| summary  | String?                           | 显示在选项文本下方的摘要文本                                                  | null   | 否       |
| children | List\<DropdownItem>?              | 可选的子菜单项；仅级联变体会将其渲染为二级菜单（最多两级）                    | null   | 否       |

### DropdownColors

| 属性名                 | 类型  | 说明             |
| ---------------------- | ----- | ---------------- |
| contentColor           | Color | 选项标题颜色     |
| summaryColor           | Color | 选项摘要颜色     |
| containerColor         | Color | 选项背景颜色     |
| selectedContentColor   | Color | 选中项标题颜色   |
| selectedSummaryColor   | Color | 选中项摘要颜色   |
| selectedContainerColor | Color | 选中项背景颜色   |
| selectedIndicatorColor | Color | 选中指示图标颜色 |

### PopupPositionProvider.Align

| 值          | 说明                         |
| ----------- | ---------------------------- |
| Start       | 将弹窗对齐到锚点的起始端     |
| End         | 将弹窗对齐到锚点的结束端     |
| TopStart    | 将弹窗对齐到锚点的顶部起始端 |
| TopEnd      | 将弹窗对齐到锚点的顶部结束端 |
| BottomStart | 将弹窗对齐到锚点的底部起始端 |
| BottomEnd   | 将弹窗对齐到锚点的底部结束端 |
