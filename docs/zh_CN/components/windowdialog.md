---
title: WindowDialog
requiresScaffoldHost: false
prerequisites:
  - 可以在任何地方使用，不需要 `Scaffold` 或 `MiuixPopupHost`
  - 在窗口层级渲染
hostComponent: None
popupHost: None
---

# WindowDialog

`WindowDialog` 是一个窗口级对话框组件。它基于平台 `Dialog` 渲染，无需依赖 `Scaffold` 或 `MiuixPopupHost`。支持大屏优化动效、系统返回手势关闭，以及在内容内部通过组合局部触发关闭。

<div style="position: relative; max-width: 700px; height: 210px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../../compose/index.html?id=windowDialog" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
    </div>

::: tip 提示
该组件不依赖 `Scaffold`，可在任意 Composable 作用域中使用。
:::

## 导入

```kotlin
import top.yukonga.miuix.kmp.extra.WindowDialog
import top.yukonga.miuix.kmp.extra.LocalWindowDialogState
```

## 基本用法

```kotlin
val showDialog = remember { mutableStateOf(false) }

TextButton(
    text = "打开",
    onClick = { showDialog.value = true }
)

WindowDialog(
    title = "WindowDialog",
    summary = "一个基础的窗口级对话框示例",
    show = showDialog,
    onDismissRequest = { showDialog.value = false }
) {
    val dismiss = LocalWindowDialogState.current
    TextButton(
        text = "确认",
        onClick = { dismiss?.invoke() },
        modifier = Modifier.fillMaxWidth()
    )
}
```

## 属性

### WindowDialog 属性

| 名称                         | 类型                     | 描述                                           | 默认值                                   | 必填 |
| ---------------------------- | ------------------------ | ---------------------------------------------- | ---------------------------------------- | ---- |
| `show`                       | `MutableState<Boolean>`  | 控制显示状态                                   | -                                        | 是   |
| `modifier`                   | `Modifier`               | 根内容修饰符                                   | `Modifier`                               | 否   |
| `title`                      | `String?`                | 对话框标题                                     | `null`                                   | 否   |
| `titleColor`                 | `Color`                  | 标题颜色                                       | `WindowDialogDefaults.titleColor()`      | 否   |
| `summary`                    | `String?`                | 对话框摘要                                     | `null`                                   | 否   |
| `summaryColor`               | `Color`                  | 摘要颜色                                       | `WindowDialogDefaults.summaryColor()`    | 否   |
| `backgroundColor`            | `Color`                  | 对话框背景色                                   | `WindowDialogDefaults.backgroundColor()` | 否   |
| `onDismissRequest`           | `(() -> Unit)?`          | 对话框关闭时的回调                             | `null`                                   | 否   |
| `outsideMargin`              | `DpSize`                 | 相对窗口边缘的外部边距                         | `WindowDialogDefaults.outsideMargin`     | 否   |
| `insideMargin`               | `DpSize`                 | 对话框内容内部边距                             | `WindowDialogDefaults.insideMargin`      | 否   |
| `defaultWindowInsetsPadding` | `Boolean`                | 是否应用默认窗口插入内边距（输入法/导航/标题） | `true`                                   | 否   |
| `content`                    | `@Composable () -> Unit` | 对话框内容                                     | -                                        | 是   |

### WindowDialogDefaults

#### 属性

| 属性名        | 类型   | 说明               |
| ------------- | ------ | ------------------ |
| outsideMargin | DpSize | 对话框外部默认边距 |
| insideMargin  | DpSize | 对话框内部默认边距 |

#### 函数

| 函数名            | 返回类型 | 说明                   |
| ----------------- | -------- | ---------------------- |
| titleColor()      | Color    | 获取默认标题颜色       |
| summaryColor()    | Color    | 获取默认摘要颜色       |
| backgroundColor() | Color    | 获取默认对话框背景颜色 |

## 在内容内部关闭

通过 `LocalWindowDialogState` 获取关闭方法：

```kotlin
val dismiss = LocalWindowDialogState.current
TextButton(
    text = "关闭",
    onClick = { dismiss?.invoke() }
)
```
