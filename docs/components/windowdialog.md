---
title: WindowDialog
requiresScaffoldHost: false
prerequisites:
  - Can be used anywhere, does not require `Scaffold` or `MiuixPopupHost`
  - Renders at window level
hostComponent: None
popupHost: None
---

# WindowDialog

`WindowDialog` is a window-level dialog component. It renders using platform `Dialog` and does not require `Scaffold` or `MiuixPopupHost`. It supports large-screen optimized animations, system back gesture dismissal, and a composition local to request dismiss from inside content.

<div style="position: relative; height: 240px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../compose/index.html?id=windowDialog" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

::: tip
This component is independent of `Scaffold` and can be used in any composable scope.
:::

## Import

```kotlin
import top.yukonga.miuix.kmp.window.WindowDialog
import top.yukonga.miuix.kmp.theme.LocalDismissState
```

## Basic Usage

```kotlin
var showDialog by remember { mutableStateOf(false) }

TextButton(
    text = "Open",
    onClick = { showDialog = true }
)

WindowDialog(
    title = "WindowDialog",
    summary = "A basic window-level dialog",
    show = showDialog,
    onDismissRequest = { showDialog = false }
) {
    val dismiss = LocalDismissState.current
    TextButton(
        text = "Confirm",
        onClick = { dismiss?.invoke() },
        modifier = Modifier.fillMaxWidth()
    )
}
```

## Properties

### WindowDialog Properties

| Property Name              | Type                   | Description                                                   | Default Value                          | Required |
| -------------------------- | ---------------------- | ------------------------------------------------------------- | -------------------------------------- | -------- |
| show                       | Boolean                | Whether to show the dialog                                    | -                                      | Yes      |
| modifier                   | Modifier               | Root content modifier                                         | Modifier                               | No       |
| title                      | String?                | Dialog title                                                  | null                                   | No       |
| titleColor                 | Color                  | Title color                                                   | DialogDefaults.titleColor()      | No       |
| summary                    | String?                | Dialog summary                                                | null                                   | No       |
| summaryColor               | Color                  | Summary color                                                 | DialogDefaults.summaryColor()    | No       |
| backgroundColor            | Color                  | Dialog background color                                       | DialogDefaults.backgroundColor() | No       |
| enableWindowDim            | Boolean                | Whether to enable dimming layer                               | true                                   | No       |
| onDismissRequest           | (() -> Unit)?          | Called when the user requests dismissal (outside tap or back) | null                                   | No       |
| onDismissFinished          | (() -> Unit)?          | Callback after dialog fully dismisses                         | null                                   | No       |
| outsideMargin              | DpSize                 | Outer margin (window edges)                                   | DialogDefaults.outsideMargin     | No       |
| insideMargin               | DpSize                 | Inner padding for dialog content                              | DialogDefaults.insideMargin      | No       |
| defaultWindowInsetsPadding | Boolean                | Apply default insets padding (IME, nav, caption)              | true                                   | No       |
| content                    | @Composable () -> Unit | Dialog content                                                | -                                      | Yes      |

### DialogDefaults

#### Properties

| Name          | Type   | Description                      |
| ------------- | ------ | -------------------------------- |
| outsideMargin | DpSize | Default outer margin for dialog  |
| insideMargin  | DpSize | Default inner padding for dialog |

#### Functions

| Name              | Return Type | Description                         |
| ----------------- | ----------- | ----------------------------------- |
| titleColor()      | Color       | Get default title color             |
| summaryColor()    | Color       | Get default summary color           |
| backgroundColor() | Color       | Get default dialog background color |

### LocalDismissState

Provides a `(() -> Unit)?` function to close the current popup from within the content. This is a unified dismiss state provided by all overlay components.

```kotlin
val dismiss = LocalDismissState.current
TextButton(
    text = "Close",
    onClick = { dismiss?.invoke() }
)
```
