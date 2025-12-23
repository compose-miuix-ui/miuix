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

<div style="position: relative; max-width: 700px; height: 210px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../compose/index.html?id=windowDialog" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

::: tip
This component is independent of `Scaffold` and can be used in any composable scope.
:::

## Import

```kotlin
import top.yukonga.miuix.kmp.extra.WindowDialog
import top.yukonga.miuix.kmp.extra.LocalWindowDialogState
```

## Basic Usage

```kotlin
val showDialog = remember { mutableStateOf(false) }

TextButton(
    text = "Open",
    onClick = { showDialog.value = true }
)

WindowDialog(
    title = "WindowDialog",
    summary = "A basic window-level dialog",
    show = showDialog,
    onDismissRequest = { showDialog.value = false }
) {
    val dismiss = LocalWindowDialogState.current
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
| show                       | MutableState\<Boolean> | Controls visibility of the dialog                             | -                                      | Yes      |
| modifier                   | Modifier               | Root content modifier                                         | Modifier                               | No       |
| title                      | String?                | Dialog title                                                  | null                                   | No       |
| titleColor                 | Color                  | Title color                                                   | WindowDialogDefaults.titleColor()      | No       |
| summary                    | String?                | Dialog summary                                                | null                                   | No       |
| summaryColor               | Color                  | Summary color                                                 | WindowDialogDefaults.summaryColor()    | No       |
| backgroundColor            | Color                  | Dialog background color                                       | WindowDialogDefaults.backgroundColor() | No       |
| onDismissRequest           | (() -> Unit)?          | Called when the user requests dismissal (outside tap or back) | null                                   | No       |
| onDismissFinished          | (() -> Unit)?          | Callback after dialog fully dismisses                         | null                                   | No       |
| outsideMargin              | DpSize                 | Outer margin (window edges)                                   | WindowDialogDefaults.outsideMargin     | No       |
| insideMargin               | DpSize                 | Inner padding for dialog content                              | WindowDialogDefaults.insideMargin      | No       |
| defaultWindowInsetsPadding | Boolean                | Apply default insets padding (IME, nav, caption)              | true                                   | No       |
| content                    | @Composable () -> Unit | Dialog content                                                | -                                      | Yes      |

### WindowDialogDefaults

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

### LocalWindowDialogState

Provides a `() -> Unit` function to close the current popup from within the content.

```kotlin
val state = LocalWindowDialogState.current
TextButton(
    text = "Close",
    onClick = { state.invoke() }
)
```
