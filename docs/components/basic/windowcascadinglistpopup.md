---
title: WindowCascadingListPopup
requiresScaffoldHost: false
prerequisites:
  - Can be used anywhere, does not require `Scaffold` or `MiuixPopupHost`
  - Renders at window level
hostComponent: None
popupHost: None
---

# WindowCascadingListPopup

`WindowCascadingListPopup` is a popup list with two-level cascading menu support, rendered at the window level via `Dialog`. Unlike `OverlayCascadingListPopup`, it does not require a `Scaffold` or `MiuixPopupHost`. Items whose `DropdownItem.children` is non-empty become submenu triggers; cascading depth is limited to **2**.

<div style="position: relative; height: 410px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../../compose/index.html?id=windowCascadingListPopup" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

## Import

```kotlin
import top.yukonga.miuix.kmp.window.WindowCascadingListPopup
import top.yukonga.miuix.kmp.basic.DropdownEntry
import top.yukonga.miuix.kmp.basic.DropdownItem
```

## Basic Usage

`WindowCascadingListPopup` is used the same way as `OverlayCascadingListPopup` but does not need a `Scaffold` host. The example below shows two top-level items: a flat sort group, and a "View mode" trigger that expands a secondary list.

```kotlin
var showPopup by remember { mutableStateOf(false) }
var sortIndex by remember { mutableStateOf(0) }
var viewIndex by remember { mutableStateOf(0) }

val sortLabels = listOf("Sort by capture date", "Sort by date added")
val viewLabels = listOf("Group by date", "Compact")
val entries = listOf(
    DropdownEntry(
        items = sortLabels.mapIndexed { idx, label ->
            DropdownItem(
                text = label,
                selected = sortIndex == idx,
                onClick = { sortIndex = idx },
            )
        },
    ),
    DropdownEntry(
        items = listOf(
            DropdownItem(
                text = "View mode",
                children = viewLabels.mapIndexed { idx, label ->
                    DropdownItem(
                        text = label,
                        selected = viewIndex == idx,
                        onClick = { viewIndex = idx },
                    )
                },
            ),
        ),
    ),
)

Box {
    TextButton(
        text = "Click to show menu",
        onClick = { showPopup = true },
    )
    WindowCascadingListPopup(
        show = showPopup,
        entries = entries,
        onDismissRequest = { showPopup = false },
    )
}
```

::: tip Cascading Depth
Cascading depth is capped at 2. Items at the secondary level cannot have their own `children`; deeper trees are silently ignored.
:::

## Properties

### WindowCascadingListPopup

| Property Name         | Type                        | Description                                                                                  | Default Value                              |
| --------------------- | --------------------------- | -------------------------------------------------------------------------------------------- | ------------------------------------------ |
| show                  | Boolean                     | Whether to show the popup.                                                                   | -                                          |
| entries               | List\<DropdownEntry>        | Grouped dropdown entries; top-level items with non-empty `children` become submenu triggers. | -                                          |
| onDismissRequest      | () -> Unit                  | Called when the user requests dismissal (e.g., clicking outside, tapping the back button).   | -                                          |
| popupModifier         | Modifier                    | Modifier applied to the popup body.                                                          | Modifier                                   |
| onDismissFinished     | (() -> Unit)?               | Called after the exit animation finishes.                                                    | null                                       |
| popupPositionProvider | PopupPositionProvider       | Position strategy for the primary popup relative to its anchor.                              | ListPopupDefaults.DropdownPositionProvider |
| alignment             | PopupPositionProvider.Align | Alignment of the primary popup.                                                              | PopupPositionProvider.Align.End            |
| enableWindowDim       | Boolean                     | Whether to dim the rest of the window while the popup is shown.                              | true                                       |
| maxHeight             | Dp?                         | Maximum height of either side. Null bounds it by the safe area.                              | null                                       |
| minWidth              | Dp                          | Minimum width of the popup.                                                                  | 200.dp                                     |
| dropdownColors        | DropdownColors              | Colors used by every row.                                                                    | DropdownDefaults.dropdownColors()          |
| collapseOnSelection   | Boolean                     | When true, selecting any leaf dismisses the popup.                                           | true                                       |

### DropdownEntry

| Property Name | Type                | Description                                                                                            | Default Value | Required |
| ------------- | ------------------- | ------------------------------------------------------------------------------------------------------ | ------------- | -------- |
| items         | List\<DropdownItem> | Items shown in this dropdown group                                                                     | -             | Yes      |
| enabled       | Boolean             | Whether this group is enabled. False disables all items; true still respects each item's enabled state | true          | No       |

### DropdownItem

| Property Name | Type                              | Description                                                                                                                       | Default Value | Required |
| ------------- | --------------------------------- | --------------------------------------------------------------------------------------------------------------------------------- | ------------- | -------- |
| text          | String                            | Text shown for the item                                                                                                           | -             | Yes      |
| enabled       | Boolean                           | Whether the item can be clicked. Disabled items are gray                                                                          | true          | No       |
| selected      | Boolean                           | Whether the item is selected                                                                                                      | false         | No       |
| onClick       | (() -> Unit)?                     | Callback invoked when the item is clicked. Ignored when `children` is non-empty (the click expands the submenu instead)            | null          | No       |
| icon          | @Composable ((Modifier) -> Unit)? | Icon shown before the item text                                                                                                   | null          | No       |
| summary       | String?                           | Summary text shown below the item text                                                                                            | null          | No       |
| children      | List\<DropdownItem>?              | Optional submenu items; only the cascading variants render these as a submenu (depth limited to 2)                                | null          | No       |

### DropdownColors

| Property Name          | Type  | Description                             |
| ---------------------- | ----- | --------------------------------------- |
| contentColor           | Color | Color of the option title               |
| summaryColor           | Color | Color of the option summary             |
| containerColor         | Color | Background color of the option          |
| selectedContentColor   | Color | Title color of the selected option      |
| selectedSummaryColor   | Color | Summary color of the selected option    |
| selectedContainerColor | Color | Background color of the selected option |
| selectedIndicatorColor | Color | Color of the selected indicator icon    |

### PopupPositionProvider.Align

| Value       | Description                                         |
| ----------- | --------------------------------------------------- |
| Start       | Aligns the popup to the start of the anchor.        |
| End         | Aligns the popup to the end of the anchor.          |
| TopStart    | Aligns the popup to the top-start of the anchor.    |
| TopEnd      | Aligns the popup to the top-end of the anchor.      |
| BottomStart | Aligns the popup to the bottom-start of the anchor. |
| BottomEnd   | Aligns the popup to the bottom-end of the anchor.   |
