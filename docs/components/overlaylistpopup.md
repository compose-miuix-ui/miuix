---
title: OverlayListPopup
requiresScaffoldHost: true
prerequisites:
  - Must be used within `Scaffold` to provide `MiuixPopupHost`
  - Using outside `Scaffold` will cause popup content not to render
  - Multiple nested or side-by-side `Scaffold`s are supported without extra configuration
hostComponent: Scaffold
popupHost: MiuixPopupHost
---

# OverlayListPopup

`OverlayListPopup` is a popup list component in Miuix used to display a popup menu with multiple options. It provides a lightweight, floating temporary list suitable for various dropdown menus, context menus, and similar scenarios.

<div style="position: relative; height: 250px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../compose/index.html?id=overlayListPopup" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

::: danger Prerequisite
This component depends on `Scaffold` providing `MiuixPopupHost` to render popup content. It must be used within `Scaffold`, otherwise popup content will not render correctly.
:::

## Import

```kotlin
import top.yukonga.miuix.kmp.overlay.OverlayListPopup
import top.yukonga.miuix.kmp.basic.ListPopupColumn
```

## Basic Usage

The OverlayListPopup component can be used to create simple dropdown menus:

```kotlin
var showPopup by remember { mutableStateOf(false) }
var selectedIndex by remember { mutableStateOf(0) }
val items = listOf("Option 1", "Option 2", "Option 3")

Scaffold {
    Box {
        TextButton(
            text = "Click to show menu",
            onClick = { showPopup = true }
        )
        OverlayListPopup(
            show = showPopup,
            alignment = PopupPositionProvider.Align.Start,
            onDismissRequest = { showPopup = false } // Close the popup menu
        ) {
            ListPopupColumn {
                items.forEachIndexed { index, string ->
                    DropdownImpl(
                        text = string,
                        optionSize = items.size,
                        isSelected = selectedIndex == index,
                        index = index,
                        onSelectedIndexChange = {
                            selectedIndex = index
                            showPopup = false // Close the popup menu
                        }
                    )
                }
            }
        }
    }
}

## Component States

### Different Alignments

OverlayListPopup can be set with different alignment options:

```kotlin
var showPopup by remember { mutableStateOf(false) }

OverlayListPopup(
    show = showPopup,
    onDismissRequest = { showPopup = false }, // Close the popup menu
    alignment = PopupPositionProvider.Align.Start
) {
    ListPopupColumn {
        // Custom content
    }
}
```

### Disable Window Dimming

```kotlin
var showPopup by remember { mutableStateOf(false) }

OverlayListPopup(
    show = showPopup,
    onDismissRequest = { showPopup = false } // Close the popup menu
    enableWindowDim = false // Disable dimming layer
) {
    ListPopupColumn {
        // Custom content
    }
}
```

## Properties

### OverlayListPopup

| Property Name         | Type                        | Description                                                       | Default Value                              |
| --------------------- | --------------------------- | ----------------------------------------------------------------- | ------------------------------------------ |
| show                  | Boolean                     | Whether to show the popup.                                        | -                                          |
| popupModifier         | Modifier                    | Modifier applied to the popup container.                          | Modifier                                   |
| popupPositionProvider | PopupPositionProvider       | Provides position calculation logic for the popup.                | ListPopupDefaults.DropdownPositionProvider |
| alignment             | PopupPositionProvider.Align | Specifies the alignment of the popup relative to the anchor.      | PopupPositionProvider.Align.Start          |
| enableWindowDim       | Boolean                     | Whether to dim the background when popup is shown.                | true                                       |
| onDismissRequest      | (() -> Unit)?               | Called when the user requests dismissal (e.g., clicking outside). | null                                       |
| maxHeight             | Dp?                         | Maximum height of the popup content.                              | null                                       |
| minWidth              | Dp                          | Minimum width of the popup content.                               | 200.dp                                     |
| renderInRootScaffold  | Boolean                     | Whether to render the popup in the root (outermost) Scaffold. When true, the popup covers the full screen. When false, it renders within the current Scaffold's bounds with position compensation. | true |
| content               | @Composable () -> Unit      | The content to display inside the popup.                          | -                                          |

### ListPopupColumn

| Property Name | Type                   | Description                                    | Default Value |
| ------------- | ---------------------- | ---------------------------------------------- | ------------- |
| content       | @Composable () -> Unit | The list content to display inside the column. | -             |

### DropdownImpl

`DropdownImpl` can be used as a standard row inside `ListPopupColumn`. Set `enabled = false` to disable a row; disabled rows are not clickable and use the disabled text color.

```kotlin
DropdownImpl(
    text = "Disabled option",
    optionSize = items.size,
    isSelected = false,
    index = 1,
    enabled = false,
    onSelectedIndexChange = {}
)
```

| Property Name         | Type           | Description                                 | Default Value                     |
| --------------------- | -------------- | ------------------------------------------- | --------------------------------- |
| text                  | String         | Text shown for the option                   | -                                 |
| optionSize            | Int            | Total number of options                     | -                                 |
| isSelected            | Boolean        | Whether this option is selected             | -                                 |
| index                 | Int            | Index of this option                        | -                                 |
| dropdownColors        | DropdownColors | Color configuration for the option          | DropdownDefaults.dropdownColors() |
| enabled               | Boolean        | Whether this option can be clicked          | true                              |
| onSelectedIndexChange | (Int) -> Unit  | Callback when this option is clicked        | -                                 |

### PopupPositionProvider.Align

| Value       | Description                                         |
| ----------- | --------------------------------------------------- |
| Start       | Aligns the popup to the start of the anchor.        |
| End         | Aligns the popup to the end of the anchor.          |
| TopStart    | Aligns the popup to the top-start of the anchor.    |
| TopEnd      | Aligns the popup to the top-end of the anchor.      |
| BottomStart | Aligns the popup to the bottom-start of the anchor. |
| BottomEnd   | Aligns the popup to the bottom-end of the anchor.   |
