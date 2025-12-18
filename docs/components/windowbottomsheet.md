---
title: WindowBottomSheet
requiresScaffoldHost: false
prerequisites:
  - Can be used anywhere, does not require `Scaffold` or `MiuixPopupHost`
  - Renders at window level
hostComponent: None
popupHost: None
---

# WindowBottomSheet

`WindowBottomSheet` is a window-level bottom sheet component. It renders using platform `Dialog` and does not require `Scaffold` or `MiuixPopupHost`. It supports large-screen optimized animations, system back gesture dismissal, and a composition local to request dismiss from inside content.

<div style="position: relative; max-width: 700px; height: 210px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../compose/index.html?id=windowBottomSheet" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

::: tip
This component is independent of `Scaffold` and can be used in any composable scope.
:::

## Import

```kotlin
import top.yukonga.miuix.kmp.extra.WindowBottomSheet
import top.yukonga.miuix.kmp.extra.LocalWindowBottomSheetState
```

## Basic Usage

`WindowBottomSheet` component provides basic bottom sheet functionality:

```kotlin
var showBottomSheet = remember { mutableStateOf(false) }

// Can be used anywhere
TextButton(
    text = "Show Window Bottom Sheet",
    onClick = { showBottomSheet.value = true }
)

WindowBottomSheet(
    show = showBottomSheet,
    title = "Window Bottom Sheet Title",
    onDismissRequest = { showBottomSheet.value = false }
) {
    val dismiss = LocalWindowBottomSheetState.current
    Text(text = "This is the content of the window bottom sheet")
    TextButton(
        text = "Close",
        onClick = { dismiss?.invoke() }
    )
}
```

## Properties

### WindowBottomSheet Properties

| Property Name              | Type                             | Description                                  | Default Value                               | Required |
| -------------------------- | -------------------------------- | -------------------------------------------- | ------------------------------------------- | -------- |
| show                       | MutableState\<Boolean>           | State object to control bottom sheet visibility | -                                        | Yes      |
| modifier                   | Modifier                         | Modifier applied to the bottom sheet         | Modifier                                    | No       |
| title                      | String?                          | Bottom sheet title                           | null                                        | No       |
| leftAction                 | @Composable (() -> Unit)?        | Optional composable for left action (e.g., close button) | null                           | No       |
| rightAction                | @Composable (() -> Unit)?        | Optional composable for right action (e.g., submit button) | null                         | No       |
| backgroundColor            | Color                            | Bottom sheet background color                | SuperBottomSheetDefaults.backgroundColor()  | No       |
| enableWindowDim            | Boolean                          | Whether to enable dimming layer              | true                                        | No       |
| cornerRadius               | Dp                               | Corner radius of the top corners             | SuperBottomSheetDefaults.cornerRadius       | No       |
| sheetMaxWidth              | Dp                               | Maximum width of the bottom sheet            | SuperBottomSheetDefaults.maxWidth           | No       |
| onDismissRequest           | (() -> Unit)?                    | Callback when bottom sheet is dismissed      | null                                        | No       |
| outsideMargin              | DpSize                           | Bottom sheet external margin                 | SuperBottomSheetDefaults.outsideMargin      | No       |
| insideMargin               | DpSize                           | Bottom sheet internal content margin         | SuperBottomSheetDefaults.insideMargin       | No       |
| defaultWindowInsetsPadding | Boolean                          | Whether to apply default window insets padding | true                                      | No       |
| dragHandleColor            | Color                            | Drag indicator color                         | SuperBottomSheetDefaults.dragHandleColor()  | No       |
| allowDismiss               | Boolean                          | Whether to allow dismissing the sheet via drag or back gesture | true                            | No       |
| content                    | @Composable () -> Unit           | Bottom sheet content                         | -                                           | Yes      |

### WindowBottomSheetDefaults

`WindowBottomSheet` uses `SuperBottomSheetDefaults` for default values.

## Advanced Usage

### Dismissing from Content

You can use `LocalWindowBottomSheetState` to dismiss the bottom sheet from within its content:

```kotlin
WindowBottomSheet(
    show = showBottomSheet,
    title = "Dismiss Example",
    onDismissRequest = { showBottomSheet.value = false }
) {
    val dismiss = LocalWindowBottomSheetState.current
    
    Button(
        onClick = { dismiss?.invoke() }
    ) {
        Text("Close Bottom Sheet")
    }
}
```
