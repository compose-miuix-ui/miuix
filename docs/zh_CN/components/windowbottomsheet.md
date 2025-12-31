---
title: WindowBottomSheet
requiresScaffoldHost: false
prerequisites:
  - 可以在任何地方使用，不需要 `Scaffold` 或 `MiuixPopupHost`
  - 在窗口层级渲染
hostComponent: None
popupHost: None
---

# WindowBottomSheet

`WindowBottomSheet` 是窗口级的底部抽屉组件。它使用平台 `Dialog` 渲染，不依赖 `Scaffold` 或 `MiuixPopupHost`。支持大屏优化的动效、系统返回手势关闭，以及在内容内部通过组合局部请求关闭。

<div style="position: relative; max-width: 700px; height: 210px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../../compose/index.html?id=windowBottomSheet" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

::: tip 提示
该组件不依赖 `Scaffold`，可在任意 Composable 作用域中使用。
:::

## 引入

```kotlin
import top.yukonga.miuix.kmp.extra.WindowBottomSheet
import top.yukonga.miuix.kmp.extra.LocalWindowBottomSheetState
```

## 基本用法

`WindowBottomSheet` 组件提供了基础的底部抽屉功能：

```kotlin
var showBottomSheet = remember { mutableStateOf(false) }

// 可以在任何地方使用
TextButton(
    text = "显示 Window 底部抽屉",
    onClick = { showBottomSheet.value = true }
)

WindowBottomSheet(
    show = showBottomSheet,
    title = "Window 底部抽屉标题",
    onDismissRequest = { showBottomSheet.value = false }
) {
    val dismiss = LocalWindowBottomSheetState.current
    Text(text = "这是 Window 底部抽屉的内容")
    TextButton(
        text = "关闭",
        onClick = { dismiss?.invoke() }
    )
}
```

## 属性

### WindowBottomSheet 属性

| 属性名                     | 类型                      | 说明                                         | 默认值                                      | 是否必须 |
| -------------------------- | ------------------------- | -------------------------------------------- | ------------------------------------------- | -------- |
| show                       | MutableState\<Boolean>    | 控制底部抽屉显示状态的状态对象               | -                                           | 是       |
| modifier                   | Modifier                  | 应用于底部抽屉的修饰符                       | Modifier                                    | 否       |
| title                      | String?                   | 底部抽屉的标题                               | null                                        | 否       |
| startAction                | @Composable (() -> Unit)? | 可选的左侧操作按钮(例如关闭按钮)             | null                                        | 否       |
| endAction                  | @Composable (() -> Unit)? | 可选的右侧操作按钮(例如提交按钮)             | null                                        | 否       |
| backgroundColor            | Color                     | 底部抽屉背景色                               | WindowBottomSheetDefaults.backgroundColor() | 否       |
| enableWindowDim            | Boolean                   | 是否启用遮罩层                               | true                                        | 否       |
| cornerRadius               | Dp                        | 顶部圆角半径                                 | WindowBottomSheetDefaults.cornerRadius      | 否       |
| sheetMaxWidth              | Dp                        | 底部抽屉的最大宽度                           | WindowBottomSheetDefaults.maxWidth          | 否       |
| onDismissRequest           | (() -> Unit)?             | 当用户请求关闭（点击遮罩层或返回手势）时触发 | null                                        | 否       |
| onDismissFinished          | (() -> Unit)?             | 底部抽屉完全关闭（动画结束）时的回调         | null                                        | 否       |
| outsideMargin              | DpSize                    | 底部抽屉外部边距                             | WindowBottomSheetDefaults.outsideMargin     | 否       |
| insideMargin               | DpSize                    | 底部抽屉内部内容的边距                       | WindowBottomSheetDefaults.insideMargin      | 否       |
| defaultWindowInsetsPadding | Boolean                   | 是否应用默认窗口插入内边距                   | true                                        | 否       |
| dragHandleColor            | Color                     | 拖拽指示器的颜色                             | WindowBottomSheetDefaults.dragHandleColor() | 否       |
| allowDismiss               | Boolean                   | 是否允许通过拖拽或返回手势关闭抽屉           | true                                        | 否       |
| content                    | @Composable () -> Unit    | 底部抽屉的内容                               | -                                           | 是       |

### WindowBottomSheetDefaults

#### WindowBottomSheetDefaults 属性

| 属性名        | 类型   | 说明                  |
| ------------- | ------ | --------------------- |
| cornerRadius  | Dp     | 默认圆角半径 (28.dp)  |
| maxWidth      | Dp     | 默认最大宽度 (640.dp) |
| outsideMargin | DpSize | 底部抽屉外部默认边距  |
| insideMargin  | DpSize | 底部抽屉内部默认边距  |

#### WindowBottomSheetDefaults 函数

| 函数名            | 返回类型 | 说明                   |
| ----------------- | -------- | ---------------------- |
| backgroundColor() | Color    | 获取默认背景颜色       |
| dragHandleColor() | Color    | 获取默认拖拽指示器颜色 |

## 进阶用法

### 从内容中关闭

您可以使用 `LocalWindowBottomSheetState` 从其内容中关闭底部抽屉：

```kotlin
WindowBottomSheet(
    show = showBottomSheet,
    title = "关闭示例",
    onDismissRequest = { showBottomSheet.value = false }
) {
    val dismiss = LocalWindowBottomSheetState.current
    
    Button(
        onClick = { dismiss?.invoke() }
    ) {
        Text("关闭底部抽屉")
    }
}
```
