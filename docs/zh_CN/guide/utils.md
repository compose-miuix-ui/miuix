# 工具函数

Miuix 提供了一系列工具函数，帮助您更高效地开发应用程序。以下是主要工具函数的详细介绍和使用示例。

## 弹出窗口工具 (MiuixPopupUtils)

`MiuixPopupUtils` 是一个用于显示对话框布局和弹出窗口布局的工具类。该类已经集成到 `Scaffold` 组件中，可以直接使用。

如果你使用多个 Scaffold，则需要将下属 `Scaffold` 中的 `popupHost` 参数设为 `null`。

### 对话框布局 (DialogLayout)

```kotlin
// 需要一个 MutableState<Boolean> 来控制显示状态
val showDialogState = remember { mutableStateOf(false) }

DialogLayout(
    visible = showDialogState,          // MutableState<Boolean> 用于控制对话框的可见性
    enterTransition = fadeIn(),         // 可选，对话框内容的自定义进入动画
    exitTransition = fadeOut(),         // 可选，对话框内容的自定义退出动画
    enableWindowDim = true,             // 可选，是否启用遮罩层, 默认为 true
    enableAutoLargeScreen = true,       // 可选，是否自动检测大屏幕并调整动画
    dimEnterTransition = fadeIn(),      // 可选，遮罩层的自定义进入动画
    dimExitTransition = fadeOut(),      // 可选，遮罩层的自定义退出动画
    dimAlpha = null                     // 可选，MutableState<Float> 用于动态控制遮罩层透明度 (0f-1f)
) {
    // 对话框内容
}
```

正常情况下无需主动使用。详见 [SuperDialog](../components/superdialog.md) 或 [SuperBottomSheet](../components//basiccomponent.md) 文档。

### 弹出窗口布局 (PopupLayout)

```kotlin
// 需要一个 MutableState<Boolean> 来控制显示状态
val showPopupState = remember { mutableStateOf(false) }

PopupLayout(
    visible = showPopupState,                         // MutableState<Boolean> 用于控制弹出窗口的可见性
    enterTransition = fadeIn(),                       // 可选，弹出窗口内容的自定义进入动画
    exitTransition = fadeOut(),                       // 可选，弹出窗口内容的自定义退出动画
    enableWindowDim = true,                           // 可选，是否启用遮罩层, 默认为 true
    dimEnterTransition = fadeIn(),                    // 可选，遮罩层的自定义进入动画
    dimExitTransition = fadeOut()                    // 可选，遮罩层的自定义退出动画
) {
    // 弹出窗口内容
}
```

正常情况下无需主动使用。详见 [ListPopup](../components/listpopup.md) 文档。

## 越界回弹效果 (Overscroll)

Miuix 提供了易于使用的越界回弹效果修饰符，让滚动体验更加流畅自然。

### 垂直越界回弹

```kotlin
LazyColumn(
    modifier = Modifier
        // 添加越界回弹效果
        .overScrollVertical()
        // 如需绑定 TopAppBar 滚动事件，则应在越界回弹效果之后添加
        .nestedScroll(scrollBehavior.nestedScrollConnection)
) {
    // 列表内容
}
```

### 水平越界回弹

```kotlin
LazyRow(
    modifier = Modifier.overScrollHorizontal()
) {
    // 列表内容
}
```

### 自定义越界回弹参数

您可以根据需要自定义越界回弹效果的参数：

```kotlin
LazyColumn(
    modifier = Modifier.overScrollVertical(
        nestedScrollToParent = true, // 是否分发嵌套滚动事件到父级，默认 true
        scrollEasing = { distance, range -> // 自定义回弹阻尼，默认效果类似 HyperOS 手感
            // 示例：DefaultParabolaScrollEasing(distance, range)
        },
        springStiff = 280f, // 回弹弹性系数，默认 280f
        springDamp = 1f,    // 回弹阻尼系数，默认 1f
        isEnabled = { platform() == Platform.Android || platform() == Platform.IOS } // 默认仅在 Android/iOS 启用
    ),
    overscrollEffect = null // 建议将此参数设置为 null，禁用默认效果
) {
    // 列表内容
}
```

**参数说明:**

*   `nestedScrollToParent`：布尔值，是否分发嵌套滚动事件到父级。默认：`true`。
*   `scrollEasing`：函数 `(distance: Float, range: Int) -> Float`，自定义回弹阻尼，默认效果类似 HyperOS 手感。
*   `springStiff`：浮点数，回弹弹性系数。默认：`280f`。
*   `springDamp`：浮点数，回弹阻尼系数。默认：`1f`。
*   `isEnabled`：Lambda，是否启用越界回弹。默认仅 Android/iOS。

## 滚动到边界触觉反馈 (Modifier.scrollEndHaptic())

Miuix 提供了用于在可滚动容器快速滑动到其开始或结束边界时触发触觉反馈的修饰符，通过触觉反馈确认已到达边界增强用户的交互体验。

```kotlin
LazyColumn(
    modifier = Modifier
        .fillMaxSize()
        // 添加滚动到边界触觉反馈
        .scrollEndHaptic(
            hapticFeedbackType = HapticFeedbackType.TextHandleMove // 默认值
        )
) {
    // 列表内容
}
```

**参数说明:**

*   `hapticFeedbackType`: 指定滚动到达末端时要执行的触觉反馈类型。默认为 `HapticFeedbackType.TextHandleMove`。您可以使用 `androidx.compose.ui.hapticfeedback.HapticFeedbackType` 中可用的其他类型。

## 按压反馈效果 (Modifier.pressable())

Miuix 提供了视觉反馈效果来增强用户交互体验，通过类似触觉的响应提升操作感。

支持与 `Modifier.clickable()` 等响应式修饰符一起使用，`SinkFeedback` 为默认效果。

```kotlin
Box(
    modifier = Modifier
        .pressable()
        .background(Color.Blue)
        .size(100.dp)
)
```

### 下沉效果

`SinkFeedback` 指示会在组件被按下时应用一种“下沉”视觉效果，通过平滑缩放组件实现。

`interactionSource` 为 `null` 时，仅在需要时才会懒惰地创建内部 `MutableInteractionSource`，这会降低组合过程中可点击的性能成本。

```kotlin
Box(
    modifier = Modifier
        .pressable(interactionSource = null, indication = SinkFeedback())
        .background(Color.Blue)
        .size(100.dp)
)
```

### 倾斜效果

`TiltFeedback` 指示会根据用户按压组件的位置应用一种“倾斜”效果。倾斜方向由触摸偏移决定，使一角“下沉”而另一角保持“静止”。

```kotlin
val interactionSource = remember { MutableInteractionSource() }

Box(
    modifier = Modifier
        .pressable(interactionSource = interactionSource, indication = TiltFeedback())
        .combinedClickable(
            interactionSource = interactionSource,
            indication = LocalIndication.current,
            onClick = {},
            onLongClick = {}
        )
        .background(Color.Green)
        .size(100.dp)
)
```

### 按压反馈类型 (PressFeedbackType)

`PressFeedbackType` 枚举定义了组件被按下时可以应用的不同类型的视觉反馈。

| 类型 | 说明                                   |
| ---- | -------------------------------------- |
| None | 无视觉反馈                             |
| Sink | 应用下沉效果，组件在按下时轻微缩小     |
| Tilt | 应用倾斜效果，组件根据触摸位置轻微倾斜 |
