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

`WindowListPopup` 是一个基于 `Dialog` 在窗口层级渲染的弹出列表组件。与 `OverlayListPopup` 不同，它不需要 `Scaffold` 或 `MiuixPopupHost`。

<div style="position: relative; max-width: 700px; height: 250px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../../compose/index.html?id=windowListPopup" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

::: tip 提示
该组件不依赖 `Scaffold`，可在任意 Composable 作用域中使用。
:::

## 引入

```kotlin
import top.yukonga.miuix.kmp.window.WindowListPopup
import top.yukonga.miuix.kmp.basic.ListPopupColumn
```

## 基本用法

WindowListPopup 组件可用于在没有 `Scaffold` 的情况下创建下拉菜单：

```kotlin
var showPopup by remember { mutableStateOf(false) }
var selectedIndex by remember { mutableStateOf(0) }
val items = listOf("选项 1", "选项 2", "选项 3")

Box {
    TextButton(
        text = "点击显示菜单",
        onClick = { showPopup = true }
    )
    WindowListPopup(
        show = showPopup,
        alignment = PopupPositionProvider.Align.Start,
        onDismissRequest = { showPopup = false }
    ) {
        val dismiss = LocalDismissState.current
        ListPopupColumn {
            items.forEachIndexed { index, string ->
                DropdownImpl(
                    text = string,
                    optionSize = items.size,
                    isSelected = selectedIndex == index,
                    index = index,
                    onSelectedIndexChange = {
                        selectedIndex = index
                        dismiss?.invoke()
                    }
                )
            }
        }
    }
}
```

## 属性

### WindowListPopup 属性

| 属性名                | 类型                        | 说明                                 | 默认值                                     |
| --------------------- | --------------------------- | ------------------------------------ | ------------------------------------------ |
| show                  | Boolean                     | 是否显示弹窗                         | -                                          |
| popupModifier         | Modifier                    | 应用于弹窗容器的修饰符               | Modifier                                   |
| popupPositionProvider | PopupPositionProvider       | 提供弹窗的位置计算逻辑               | ListPopupDefaults.DropdownPositionProvider |
| alignment             | PopupPositionProvider.Align | 指定弹窗相对于锚点的对齐方式         | PopupPositionProvider.Align.Start          |
| enableWindowDim       | Boolean                     | 是否在弹窗显示时使背景变暗           | true                                       |
| onDismissRequest      | (() -> Unit)?               | 当用户请求关闭（例如点击外部）时触发 | null                                       |
| maxHeight             | Dp?                         | 弹窗内容的最大高度                   | null                                       |
| minWidth              | Dp                          | 弹窗内容的最小宽度                   | 200.dp                                     |
| content               | @Composable () -> Unit      | 要在弹窗内显示的内容                 | -                                          |

### ListPopupColumn 属性

| 属性名  | 类型                   | 说明                   | 默认值 |
| ------- | ---------------------- | ---------------------- | ------ |
| content | @Composable () -> Unit | 要在列内显示的列表内容 | -      |

### DropdownImpl

`DropdownImpl` 可作为 `ListPopupColumn` 内的标准选项行使用。设置 `enabled = false` 可以禁用某一行；禁用行不可点击，并使用禁用文本颜色。

```kotlin
DropdownImpl(
    text = "禁用选项",
    optionSize = items.size,
    isSelected = false,
    index = 1,
    enabled = false,
    onSelectedIndexChange = {}
)
```

| 属性名                | 类型          | 说明               | 默认值                            |
| --------------------- | ------------- | ------------------ | --------------------------------- |
| text                  | String        | 选项显示文本       | -                                 |
| optionSize            | Int           | 选项总数           | -                                 |
| isSelected            | Boolean       | 此选项是否被选中   | -                                 |
| index                 | Int           | 此选项的索引       | -                                 |
| dropdownColors        | DropdownColors | 选项颜色配置      | DropdownDefaults.dropdownColors() |
| enabled               | Boolean       | 此选项是否可点击   | true                              |
| onSelectedIndexChange | (Int) -> Unit | 点击此选项时的回调 | -                                 |

### PopupPositionProvider.Align

| 值          | 说明                         |
| ----------- | ---------------------------- |
| Start       | 将弹窗对齐到锚点的起始端     |
| End         | 将弹窗对齐到锚点的结束端     |
| TopStart    | 将弹窗对齐到锚点的顶部起始端 |
| TopEnd      | 将弹窗对齐到锚点的顶部结束端 |
| BottomStart | 将弹窗对齐到锚点的底部起始端 |
| BottomEnd   | 将弹窗对齐到锚点的底部结束端 |

### LocalDismissState

提供一个 `(() -> Unit)?` 函数，用于从内容内部关闭当前弹窗。这是所有弹出组件提供的统一关闭状态。

```kotlin
val dismiss = LocalDismissState.current
TextButton(
    text = "关闭",
    onClick = { dismiss?.invoke() }
)
```
