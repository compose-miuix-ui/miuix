---
title: WindowListPopup
requiresScaffoldHost: false
prerequisites:
  - 可以在任何地方使用，不需要 `Scaffold` 或 `MiuixPopupHost`
  - 在窗口层级渲染
hostComponent: None
popupHost: None
---

# WindowListPopup

`WindowListPopup` 是一个基于 `Dialog` 在窗口层级渲染的弹出列表组件。与 `SuperListPopup` 不同，它不需要 `Scaffold` 或 `MiuixPopupHost`。

<div style="position: relative; max-width: 700px; height: 250px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../../compose/index.html?id=windowListPopup" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

::: tip 提示
该组件不依赖 `Scaffold`，可在任意 Composable 作用域中使用。
:::

## 引入

```kotlin
import top.yukonga.miuix.kmp.extra.WindowListPopup
import top.yukonga.miuix.kmp.basic.ListPopupColumn
```

## 基本用法

WindowListPopup 组件可用于在没有 `Scaffold` 的情况下创建下拉菜单：

```kotlin
val showPopup = remember { mutableStateOf(false) }
var selectedIndex by remember { mutableStateOf(0) }
val items = listOf("选项 1", "选项 2", "选项 3")

Box {
    TextButton(
        text = "点击显示菜单",
        onClick = { showPopup.value = true }
    )
    WindowListPopup(
        show = showPopup,
        alignment = PopupPositionProvider.Align.Left,
        onDismissRequest = { showPopup.value = false }
    ) {
        val dismiss = LocalWindowListPopupState.current
        ListPopupColumn {
            items.forEachIndexed { index, string ->
                DropdownImpl(
                    text = string,
                    optionSize = items.size,
                    isSelected = selectedIndex == index,
                    onSelectedIndexChange = {
                        selectedIndex = index
                        dismiss()
                    },
                    index = index
                )
            }
        }
    }
}
```

## 属性

### WindowListPopup

| 属性名                | 类型                        | 说明                                 | 默认值                                     |
| --------------------- | --------------------------- | ------------------------------------ | ------------------------------------------ |
| show                  | MutableState\<Boolean>      | 控制弹窗的显示状态                   | -                                          |
| popupModifier         | Modifier                    | 应用于弹窗容器的修饰符               | Modifier                                   |
| popupPositionProvider | PopupPositionProvider       | 提供弹窗的位置计算逻辑               | ListPopupDefaults.DropdownPositionProvider |
| alignment             | PopupPositionProvider.Align | 指定弹窗相对于锚点的对齐方式         | PopupPositionProvider.Align.Right          |
| enableWindowDim       | Boolean                     | 是否在弹窗显示时使背景变暗           | true                                       |
| shadowElevation       | Dp                          | 弹窗阴影的高度                       | 11.dp                                      |
| onDismissRequest      | (() -> Unit)?               | 当用户请求关闭（例如点击外部）时触发 | null                                       |
| maxHeight             | Dp?                         | 弹窗内容的最大高度                   | null                                       |
| minWidth              | Dp                          | 弹窗内容的最小宽度                   | 200.dp                                     |
| content               | @Composable () -> Unit      | 要在弹窗内显示的内容                 | -                                          |

### ListPopupColumn

| 属性名  | 类型                   | 说明                   | 默认值 |
| ------- | ---------------------- | ---------------------- | ------ |
| content | @Composable () -> Unit | 要在列内显示的列表内容 | -      |

### PopupPositionProvider.Align

| 值          | 说明                     |
| ----------- | ------------------------ |
| Left        | 将弹窗对齐到锚点的左侧   |
| Right       | 将弹窗对齐到锚点的右侧   |
| TopLeft     | 将弹窗对齐到锚点的左上角 |
| TopRight    | 将弹窗对齐到锚点的右上角 |
| BottomLeft  | 将弹窗对齐到锚点的左下角 |
| BottomRight | 将弹窗对齐到锚点的右下角 |


### LocalWindowListPopupState

提供一个 `() -> Unit` 函数，用于从内容内部关闭当前弹窗。

```kotlin
val state = LocalWindowListPopupState.current
TextButton(
    text = "关闭",
    onClick = { state.invoke() }
)
```
