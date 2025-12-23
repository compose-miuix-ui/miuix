---
title: WindowListPopup
requiresScaffoldHost: false
prerequisites:
  - Can be used anywhere, does not require `Scaffold` or `MiuixPopupHost`
  - Renders at window level
hostComponent: None
popupHost: None
---

# WindowListPopup

`WindowListPopup` is a popup list component that renders at the window level using `Dialog`. Unlike `SuperListPopup`, it does not require a `Scaffold` or `MiuixPopupHost`.

<div style="position: relative; max-width: 700px; height: 250px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../compose/index.html?id=windowListPopup" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

## Import

```kotlin
import top.yukonga.miuix.kmp.extra.WindowListPopup
import top.yukonga.miuix.kmp.basic.ListPopupColumn
```

## Basic Usage

The WindowListPopup component can be used to create dropdown menus without `Scaffold`:

```kotlin
val showPopup = remember { mutableStateOf(false) }
var selectedIndex by remember { mutableStateOf(0) }
val items = listOf("Option 1", "Option 2", "Option 3")

Box {
    TextButton(
        text = "Click to show menu",
        onClick = { showPopup.value = true }
    )
    WindowListPopup(
        show = showPopup,
        alignment = PopupPositionProvider.Align.Left,
        onDismissRequest = { showPopup.value = false }
    ) {
        val dismiss = LocalWindowListPopupState.current
        ListPopupColumn {
            items.forEachIndexed { index, string ->
                DropdownImpl(
                    text = string,
                    optionSize = items.size,
                    isSelected = selectedIndex == index,
                    onSelectedIndexChange = {
                        selectedIndex = index
                        dismiss()
                    },
                    index = index
                )
            }
        }
    }
}
```

## Properties

### WindowListPopup

| Property Name         | Type                        | Description                                                      | Default Value                              |
| --------------------- | --------------------------- | ---------------------------------------------------------------- | ------------------------------------------ |
| show                  | MutableState\<Boolean>      | Controls the visibility state of the popup.                      | -                                          |
| popupModifier         | Modifier                    | Modifier applied to the popup container.                         | Modifier                                   |
| popupPositionProvider | PopupPositionProvider       | Provides position calculation logic for the popup.               | ListPopupDefaults.DropdownPositionProvider |
| alignment             | PopupPositionProvider.Align | Specifies the alignment of the popup relative to the anchor.     | PopupPositionProvider.Align.Right          |
| enableWindowDim       | Boolean                     | Whether to dim the background when popup is shown.               | true                                       |
| shadowElevation       | Dp                          | Elevation of the popup shadow.                                   | 11.dp                                      |
| onDismissRequest      | (() -> Unit)?               | Called when the user requests dismissal (e.g., clicking outside) | null                                       |
| maxHeight             | Dp?                         | Maximum height of the popup content.                             | null                                       |
| minWidth              | Dp                          | Minimum width of the popup content.                              | 200.dp                                     |
| content               | @Composable () -> Unit      | The content to display inside the popup.                         | -                                          |

### ListPopupColumn

| Property Name | Type                   | Description                                    | Default Value |
| ------------- | ---------------------- | ---------------------------------------------- | ------------- |
| content       | @Composable () -> Unit | The list content to display inside the column. | -             |

### PopupPositionProvider.Align

| Value       | Description                                         |
| ----------- | --------------------------------------------------- |
| Left        | Aligns the popup to the left of the anchor.         |
| Right       | Aligns the popup to the right of the anchor.        |
| TopLeft     | Aligns the popup to the top-left of the anchor.     |
| TopRight    | Aligns the popup to the top-right of the anchor.    |
| BottomLeft  | Aligns the popup to the bottom-left of the anchor.  |
| BottomRight | Aligns the popup to the bottom-right of the anchor. |

### LocalWindowListPopupState

Provides a function `() -> Unit` to dismiss the current popup from within its content.

```kotlin
val state = LocalWindowListPopupState.current
TextButton(
    text = "Close",
    onClick = { state.invoke() }
)
```
