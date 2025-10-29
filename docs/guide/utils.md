# Utility Functions

Miuix provides a series of utility functions to help you develop applications more efficiently. Below is a detailed introduction and usage examples of the main utility functions.

## Popup Utilities (MiuixPopupUtils)

`MiuixPopupUtils` is a utility class for displaying dialog layout and popup layout. This class is already integrated into the `Scaffold` component and can be used directly.

If you use multiple Scaffolds, you need to set the `popupHost` parameter in the subordinate `Scaffold` to `null`.

### DialogLayout

```kotlin
// Requires a MutableState<Boolean> to control the display state
val showDialogState = remember { mutableStateOf(false) }

DialogLayout(
    visible = showDialogState,          // MutableState<Boolean> to control dialog visibility
    enterTransition = fadeIn(),         // Optional, custom enter animation for dialog content
    exitTransition = fadeOut(),         // Optional, custom exit animation for dialog content
    enableWindowDim = true,             // Optional, whether to enable dimming layer, defaults to true
    enableAutoLargeScreen = true,       // Optional, whether to auto-detect large screen and adjust animations
    dimEnterTransition = fadeIn(),      // Optional, custom enter animation for dim layer
    dimExitTransition = fadeOut(),      // Optional, custom exit animation for dim layer
    dimAlpha = null                     // Optional, MutableState<Float> to dynamically control dim layer alph (0f-1f)
) {
    // Dialog content
}
```

Normally, you don't need to use it actively. See the [SuperDialog](../components/superdialog.md) or [SuperBottomSheet](../components/basiccomponent.md) documentation for details.

### PopupLayout

```kotlin
// Requires a MutableState<Boolean> to control the display state
val showPopupState = remember { mutableStateOf(false) }

PopupLayout(
    visible = showPopupState,                         // MutableState<Boolean> to control popup visibility
    enterTransition = fadeIn(),                       // Optional, custom enter animation for popup content
    exitTransition = fadeOut(),                       // Optional, custom exit animation for popup content
    enableWindowDim = true,                           // Optional, whether to enable dimming layer, defaults to true
    dimEnterTransition = fadeIn(),                    // Optional, custom enter animation for dim layer
    dimExitTransition = fadeOut(),                    // Optional, custom exit animation for dim layer
    transformOrigin = { TransformOrigin.Center }      // Transform origin for scale transformations, defaults to TransformOrigin.Center
) {
    // Popup content
}
```

Normally, you don't need to use it actively. See the [ListPopup](../components/listpopup.md) documentation for details.

## Overscroll Effects

Miuix provides easy-to-use overscroll effect modifiers for smoother and more natural scrolling experiences.

### Vertical Overscroll

```kotlin
LazyColumn(
    modifier = Modifier
        // Add overscroll effect
        .overScrollVertical()
        // If you want to bind the TopAppBar scroll behavior, please add it after the overscroll effect
        .nestedScroll(scrollBehavior.nestedScrollConnection)
) {
    // List content
}
```

### Horizontal Overscroll

```kotlin
LazyRow(
    modifier = Modifier.overScrollHorizontal()
) {
    // List content
}
```

### Custom Overscroll Parameters

You can customize the overscroll effect parameters as needed:

```kotlin
LazyColumn(
    modifier = Modifier.overScrollVertical(
        nestedScrollToParent = true, // Dispatch nested scroll events to parent, default true
        scrollEasing = { distance, range -> // Custom easing, default effect is similar to HyperOS feel
            // Example: DefaultParabolaScrollEasing(distance, range)
        },
        springStiff = 280f, // Spring stiffness, default 280f
        springDamp = 1f,    // Spring damping, default 1f
        isEnabled = { platform() == Platform.Android || platform() == Platform.IOS } // Enable only on Android/iOS by default
    ),
    overscrollEffect = null // It is recommended to set this parameter to null to disable the default effect
) {
    // List content
}
```

**Parameter Explanations:**

*   `nestedScrollToParent`: Boolean, whether to dispatch nested scroll events to parent. Default: `true`.
*   `scrollEasing`: Function `(distance: Float, range: Int) -> Float`, custom easing， default effect is similar to HyperOS feel.
*   `springStiff`: Float, spring stiffness for rebound. Default: `280f`.
*   `springDamp`: Float, spring damping for rebound. Default: `1f`.
*   `isEnabled`: Lambda, whether to enable overscroll. Default: only Android/iOS.

## Scroll End Haptic Feedback (Modifier.scrollEndHaptic())

Miuix provides a `scrollEndHaptic` modifier to trigger haptic feedback when a scrollable container is flung to its start or end boundaries. This enhances the user experience by providing a tactile confirmation that the end of the list has been reached.

```kotlin
LazyColumn(
    modifier = Modifier
        .fillMaxSize()
        // Add scroll end haptic feedback
        .scrollEndHaptic(
            hapticFeedbackType = HapticFeedbackType.TextHandleMove // Default value
        )
) {
    // List content
}
```

**Parameter Explanation:**

*   `hapticFeedbackType`: Specifies the type of haptic feedback to be performed when the scroll reaches its end. Defaults to `HapticFeedbackType.TextHandleMove`. You can use other types available in `androidx.compose.ui.hapticfeedback.HapticFeedbackType`.

## Press Feedback Effects (Modifier.pressable())

Miuix provides visual feedback effects to enhance user interaction experience, improving operability through tactile-like responses.

Supports use with responsive modifiers such as `Modifier.clickable()`, and `SinkFeedback` is the default effect.

```kotlin
Box(
    modifier = Modifier
        .pressable()
        .background(Color.Blue)
        .size(100.dp)
)
```

### Sink Effect

The `SinkFeedback` indication applies a "sink" visual when the component is pressed, which is achieved by smoothly scaling the component.

When `interactionSource` is `null`, internal `MutableInteractionSource` is lazily created only when needed, which reduces clickable performance costs during the combination.

```kotlin
Box(
    modifier = Modifier
        .pressable(interactionSource = null, indication = SinkFeedback())
        .background(Color.Blue)
        .size(100.dp)
)
```

### Tilt Effect

The `TiltFeedback` indication applies a "tilt" effect based on the position of the user pressing the component. The tilt direction is determined by the touch offset, so that one corner "sinks" while the other corner remains "still".

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

### Press Feedback Type (`PressFeedbackType`)

The `PressFeedbackType` enum defines different types of visual feedback that can be applied when the component is pressed.

| Type | Description                                                                           |
| ---- | ------------------------------------------------------------------------------------- |
| None | No visual feedback                                                                    |
| Sink | Applies a sink effect, where the component scales down slightly when pressed          |
| Tilt | Applies a tilt effect, where the component tilts slightly based on the touch position |
