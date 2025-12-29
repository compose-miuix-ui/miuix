---
title: SuperListPopup
requiresScaffoldHost: true
prerequisites:
  - Must be used within `Scaffold` to provide `MiuixPopupHost`
  - Using outside `Scaffold` will cause popup content not to render
  - In nested `Scaffold`s, keep `MiuixPopupHost` only at top-level; set others empty
hostComponent: Scaffold
popupHost: MiuixPopupHost
---

# SuperListPopup

`SuperListPopup` is a popup list component in Miuix used to display a popup menu with multiple options. It provides a lightweight, floating temporary list suitable for various dropdown menus, context menus, and similar scenarios.

<div style="position: relative; max-width: 700px; height: 250px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../compose/index.html?id=superListPopup" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

::: danger Prerequisite
This component depends on `Scaffold` providing `MiuixPopupHost` to render popup content. It must be used within `Scaffold`, otherwise popup content will not render correctly.
:::

## Import

```kotlin
import top.yukonga.miuix.kmp.extra.SuperListPopup
import top.yukonga.miuix.kmp.basic.ListPopupColumn
```

## Basic Usage

The SuperListPopup component can be used to create simple dropdown menus:

```kotlin
val showPopup = remember { mutableStateOf(false) }
var selectedIndex by remember { mutableStateOf(0) }
val items = listOf("Option 1", "Option 2", "Option 3")

Scaffold {
    Box {
        TextButton(
            text = "Click to show menu",
            onClick = { showPopup.value = true }
        )
        SuperListPopup(
            show = showPopup,
            alignment = PopupPositionProvider.Align.Start,
            onDismissRequest = { showPopup.value = false } // Close the popup menu
        ) {
            ListPopupColumn {
                items.forEachIndexed { index, string ->
                    DropdownImpl(
                        text = string,
                        optionSize = items.size,
                        isSelected = selectedIndex == index,
                        onSelectedIndexChange = {
                            selectedIndex = index
                            showPopup.value = false // Close the popup menu
                        },
                        index = index
                    )
                }
            }
        }
    }
}

## Component States

### Different Alignments

SuperListPopup can be set with different alignment options:

```kotlin
var showPopup = remember { mutableStateOf(false) }

SuperListPopup(
    show = showPopup,
    onDismissRequest = { showPopup.value = false }, // Close the popup menu
    alignment = PopupPositionProvider.Align.Start
) {
    ListPopupColumn {
        // Custom content
    }
}
```

### Disable Window Dimming

```kotlin
var showPopup = remember { mutableStateOf(false) }

SuperListPopup(
    show = showPopup,
    onDismissRequest = { showPopup.value = false } // Close the popup menu
    enableWindowDim = false // Disable dimming layer
) {
    ListPopupColumn {
        // Custom content
    }
}
```

## Properties

### SuperListPopup

| Property Name         | Type                        | Description                                                       | Default Value                              |
| --------------------- | --------------------------- | ----------------------------------------------------------------- | ------------------------------------------ |
| show                  | MutableState\<Boolean>      | Controls the visibility state of the popup.                       | -                                          |
| popupModifier         | Modifier                    | Modifier applied to the popup container.                          | Modifier                                   |
| popupPositionProvider | PopupPositionProvider       | Provides position calculation logic for the popup.                | ListPopupDefaults.DropdownPositionProvider |
| alignment             | PopupPositionProvider.Align | Specifies the alignment of the popup relative to the anchor.      | PopupPositionProvider.Align.End            |
| enableWindowDim       | Boolean                     | Whether to dim the background when popup is shown.                | true                                       |
| onDismissRequest      | (() -> Unit)?               | Called when the user requests dismissal (e.g., clicking outside). | null                                       |
| maxHeight             | Dp?                         | Maximum height of the popup content.                              | null                                       |
| minWidth              | Dp                          | Minimum width of the popup content.                               | 200.dp                                     |
| content               | @Composable () -> Unit      | The content to display inside the popup.                          | -                                          |

### ListPopupColumn

| Property Name | Type                   | Description                                    | Default Value |
| ------------- | ---------------------- | ---------------------------------------------- | ------------- |
| content       | @Composable () -> Unit | The list content to display inside the column. | -             |

### PopupPositionProvider.Align

| Value       | Description                                         |
| ----------- | --------------------------------------------------- |
| Start       | Aligns the popup to the start of the anchor.        |
| End         | Aligns the popup to the end of the anchor.          |
| TopStart    | Aligns the popup to the top-start of the anchor.    |
| TopEnd      | Aligns the popup to the top-end of the anchor.      |
| BottomStart | Aligns the popup to the bottom-start of the anchor. |
| BottomEnd   | Aligns the popup to the bottom-end of the anchor.   |
