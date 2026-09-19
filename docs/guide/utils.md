# Utility Functions

Miuix provides a series of utility functions to help you develop applications more efficiently. Below is a detailed introduction and usage examples of the main utility functions.

## Popup Utilities (MiuixPopupUtils)

`MiuixPopupUtils` is a utility class for displaying dialog layout and popup layout. This class is already integrated into the `Scaffold` component and can be used directly.

Each `Scaffold` automatically manages its own isolated popup state, so multiple nested or side-by-side `Scaffold`s work correctly without any extra configuration.

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
    dimAlpha = null,                    // Optional, MutableState<Float> to dynamically control dim layer alph (0f-1f)
    onDismissFinished = { /* callback after dialog fully dismisses */ }, // Optional, callback when the dialog is completely dismissed (after animations)
    renderInRootScaffold = true,       // Optional, whether to render in the root (outermost) Scaffold, defaults to true
) {
    // Dialog content
}
```

Normally, you don't need to use it actively. See the [OverlayDialog](../components/overlaydialog.md) or [OverlayBottomSheet](../components/overlaybottomsheet.md) documentation for details.

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
    renderInRootScaffold = true,                      // Optional, whether to render in the root (outermost) Scaffold, defaults to true
) {
    // Popup content
}
```

Normally, you don't need to use it actively. See the [OverlayListPopup](../components/overlaylistpopup) documentation for details.

## Overscroll Effects

Miuix provides easy-to-use overscroll effect modifiers for smoother and more natural scrolling experiences.

The overscroll effect engages only during press or pan gestures (touch drag; trackpad pan on Android). Mouse wheel and keyboard scrolling pass through unchanged, as does desktop/macOS trackpad scrolling (delivered as wheel events) — none of them provide a release signal to settle the bounce. With a snap- or pager-style fling behavior, leftover wheel velocity can still produce a small edge bounce.

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
        isEnabled = { true },        // Whether the effect is enabled, default true on all platforms
    ),
    overscrollEffect = null // It is recommended to set this parameter to null to disable the default effect
) {
    // List content
}
```

**Parameter Explanations:**

* `nestedScrollToParent`: Boolean, whether to dispatch nested scroll events to parent. Default: `true`.
* `isEnabled`: Lambda, whether to enable the overscroll effect. Default: enabled on all platforms.

### Via OverscrollFactory (Theme-Level Integration)

`MiuixOverscrollFactory` implements the Compose `OverscrollFactory` interface and provides the same spring-based overscroll physics as `overScrollVertical()`, but integrated at the theme level. It is already set as the default factory in both overloads of `MiuixTheme`, so all standard Compose scrollable components (`LazyColumn`, `LazyRow`, `verticalScroll`, `horizontalScroll`, etc.) use the Miuix overscroll effect automatically — no extra modifier required.

```kotlin
// No modifier needed — MiuixTheme provides the overscroll effect automatically
LazyColumn {
    items(list) { item -> ItemRow(item) }
}
```

Disable the overscroll effect for a specific component:

```kotlin
LazyColumn(overscrollEffect = null) {
    items(list) { item -> ItemRow(item) }
}
```

Disable for an entire subtree:

```kotlin
CompositionLocalProvider(LocalOverscrollFactory provides null) {
    // Components here will not have the Miuix overscroll effect
}
```

Use `MiuixOverscrollEffect` explicitly (e.g., to share with a custom `Modifier.overscroll()`):

```kotlin
val overscrollEffect = rememberOverscrollEffect()

LazyColumn(
    modifier = Modifier.overscroll(overscrollEffect),
    overscrollEffect = overscrollEffect,
) {
    items(list) { item -> ItemRow(item) }
}
```

**Comparison with `overScrollVertical()`:**

| Feature | `overScrollVertical()` | `MiuixOverscrollFactory` |
| :--- | :---: | :---: |
| How it's applied | `Modifier` per component | Theme-level, automatic |
| Spring physics | Identical | Identical |
| Platforms enabled by default | All platforms | All platforms (via theme) |
| Triggers when content does not overflow container | ✅ | ❌ |
| Requires a modifier on each component | ✅ | ❌ |

> **Limitation:** `MiuixOverscrollFactory` works by receiving the delta that the scrollable component could not consume after reaching its scroll boundary. If the content does not overflow the container (e.g., a `LazyColumn` whose items all fit on screen), the scrollable component never reaches a boundary, so `applyToScroll` is never called with a non-zero remainder and the overscroll effect will **not** trigger. For such cases, use `overScrollVertical()` instead.

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

* `hapticFeedbackType`: Specifies the type of haptic feedback to be performed when the scroll reaches its end. Defaults to `HapticFeedbackType.TextHandleMove`. You can use other types available in `androidx.compose.ui.hapticfeedback.HapticFeedbackType`.

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

## Pager Gesture Conflict Resolution (Modifier.pagerGestureOverride())

In Compose, when a vertical scrollable list (such as `LazyColumn`) is placed inside a `HorizontalPager`, touching the screen during vertical momentum (fling) or overscroll causes Compose's default `scrollable` to immediately enter vertical dragging and bypass touch slop. As a result, horizontal swipes are hijacked by the vertical list and page switching is blocked.

Miuix provides utilities in `top.yukonga.miuix.kmp.utils` to resolve this conflict and deliver smooth, natural pager gesture physics:

```kotlin
HorizontalPager(
    state = pagerState,
    modifier = Modifier
        .fillMaxSize()
        .pagerGestureOverride(
            pagerState = pagerState,
            mode = PagerInterceptionMode.CrossAxisInterceptor.ordinal,
        ),
    userScrollEnabled = false,
    pageNestedScrollConnection = PagerGestureNestedScrollConnection,
) { page ->
    // Page content with LazyColumn
}
```

Cross-Axis mode requires both `userScrollEnabled = false` and `pageNestedScrollConnection = PagerGestureNestedScrollConnection`. The former disables the built-in touch recognizer so it cannot claim a down during settling; the latter preserves vertical deltas during diagonal vertical drags when the pager is between pages. For Native or TapToHalt, restore native `userScrollEnabled` and `PagerDefaults.pageNestedScrollConnection(pagerState, Orientation.Horizontal)`. To disable paging, also pass `enabled = false` to the modifier.

After system touch slop, dragging tracks each pixel and passes release velocity into the spring. A new touch pauses the current animation. If it becomes a vertical drag or ends as a tap, the pager settles to the nearest page while the child handles vertical scrolling and overscroll. Direction stays locked for each press; changing axes requires lifting and touching again.

### Pager Interception Modes (`PagerInterceptionMode`)

| Mode | Title | Description |
| :--- | :--- | :--- |
| `0` (`Native`) | Default | Standard Compose Foundation behavior without intervention. |
| `1` (`CrossAxisInterceptor`) | Cross-Axis | Prioritizes horizontal swipes at the `HorizontalPager` level in `PointerEventPass.Initial`, driving drag and spring settling in one scroll mutation. New touches take over at the displayed position. |
| `2` (`TapToHalt`) | iOS-like | First horizontal swipe halts vertical list inertia without paging; subsequent swipe pages natively. |

### Spring Page Navigation (`springAnimateToPage()`)

Use `PagerState.springAnimateToPage(target)` to smoothly animate between pages using `PagerNavigationSpringSpec`. It runs with `MutatePriority.UserInput` so external focus events do not interrupt the animation before the page settles:

```kotlin
coroutineScope.launch {
    pagerState.springAnimateToPage(targetPage)
}
```
