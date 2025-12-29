---
title: SuperListPopup
requiresScaffoldHost: true
prerequisites:
  - 必须在 `Scaffold` 内使用以提供 `MiuixPopupHost`
  - 在 `Scaffold` 外使用会导致弹窗内容不渲染
  - 在嵌套的 `Scaffold` 中，仅在顶层保留 `MiuixPopupHost`；将其他的设为空
hostComponent: Scaffold
popupHost: MiuixPopupHost
---

# SuperListPopup

`SuperListPopup` 是 Miuix 中的弹出列表组件，用于显示包含多个选项的弹出菜单。它提供了一个轻量级的、浮动的临时列表，适用于各种下拉菜单、上下文菜单等场景。

<div style="position: relative; max-width: 700px; height: 250px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../../compose/index.html?id=superListPopup" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

::: danger 前置条件
此组件依赖于 `Scaffold` 提供的 `MiuixPopupHost` 来渲染弹窗内容。必须在 `Scaffold` 内部使用，否则弹窗内容将无法正常渲染。
:::

## 引入

```kotlin
import top.yukonga.miuix.kmp.extra.SuperListPopup
import top.yukonga.miuix.kmp.basic.ListPopupColumn
```

## 基本用法

SuperListPopup 组件可用于创建简单的下拉菜单：

```kotlin
val showPopup = remember { mutableStateOf(false) }
var selectedIndex by remember { mutableStateOf(0) }
val items = listOf("选项 1", "选项 2", "选项 3")

Scaffold {
    Box {
        TextButton(
            text = "点击显示菜单",
            onClick = { showPopup.value = true }
        )
        SuperListPopup(
            show = showPopup,
            alignment = PopupPositionProvider.Align.Start,
            onDismissRequest = { showPopup.value = false } // 关闭弹窗菜单
        ) {
            ListPopupColumn {
                items.forEachIndexed { index, string ->
                    DropdownImpl(
                        text = string,
                        optionSize = items.size,
                        isSelected = selectedIndex == index,
                        onSelectedIndexChange = {
                            selectedIndex = index
                            showPopup.value = false // 关闭弹窗菜单
                        },
                        index = index
                    )
                }
            }
        }
    }
}
```

## 组件状态

### 不同的对齐方式

SuperListPopup 可以设置不同的对齐选项：

```kotlin
var showPopup = remember { mutableStateOf(false) }

SuperListPopup(
    show = showPopup,
    onDismissRequest = { showPopup.value = false }, // 关闭弹窗菜单
    alignment = PopupPositionProvider.Align.Start
) {
    ListPopupColumn {
        // 自定义内容
    }
}
```

### 禁用窗口变暗

```kotlin
var showPopup = remember { mutableStateOf(false) }

SuperListPopup(
    show = showPopup,
    onDismissRequest = { showPopup.value = false } // 关闭弹窗菜单
    enableWindowDim = false // 禁用变暗层
) {
    ListPopupColumn {
        // 自定义内容
    }
}
```

## 属性

### SuperListPopup

| 属性名                | 类型                        | 说明                                 | 默认值                                     |
| --------------------- | --------------------------- | ------------------------------------ | ------------------------------------------ |
| show                  | MutableState\<Boolean>      | 控制弹窗的显示状态                   | -                                          |
| popupModifier         | Modifier                    | 应用于弹窗容器的修饰符               | Modifier                                   |
| popupPositionProvider | PopupPositionProvider       | 提供弹窗的位置计算逻辑               | ListPopupDefaults.DropdownPositionProvider |
| alignment             | PopupPositionProvider.Align | 指定弹窗相对于锚点的对齐方式         | PopupPositionProvider.Align.End            |
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
| Start       | 将弹窗对齐到锚点的起始端 |
| End         | 将弹窗对齐到锚点的结束端 |
| TopStart    | 将弹窗对齐到锚点的顶部起始端 |
| TopEnd      | 将弹窗对齐到锚点的顶部结束端 |
| BottomStart | 将弹窗对齐到锚点的底部起始端 |
| BottomEnd   | 将弹窗对齐到锚点的底部结束端 |

