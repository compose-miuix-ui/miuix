---
title: OverlayCascadingListPopup
requiresScaffoldHost: true
prerequisites:
  - Must be used within `Scaffold` to provide `MiuixPopupHost`
  - Using outside `Scaffold` will cause popup content not to render
  - Multiple nested or side-by-side `Scaffold`s are supported without extra configuration
hostComponent: Scaffold
popupHost: MiuixPopupHost
---

# OverlayCascadingListPopup

`OverlayCascadingListPopup` is a popup list that supports a two-level cascading menu. Items whose `DropdownItem.children` is non-empty become submenu triggers; tapping such an item morphs the popup into a secondary list anchored at the trigger row, while the primary list scales down behind a translucent mask. Cascading depth is limited to **2**.

<div style="position: relative; height: 410px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../compose/index.html?id=overlayCascadingListPopup" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

::: danger Prerequisite
This component depends on `Scaffold` providing `MiuixPopupHost` to render popup content. It must be used within `Scaffold`, otherwise popup content will not render correctly.
:::

## Import

```kotlin
import top.yukonga.miuix.kmp.overlay.OverlayCascadingListPopup
import top.yukonga.miuix.kmp.basic.DropdownEntry
import top.yukonga.miuix.kmp.basic.DropdownItem
```

## Basic Usage

Build `entries` so that some `DropdownItem`s carry a non-empty `children` list. The component renders a chevron next to such trigger rows; tapping the trigger expands the second level inline.

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

Scaffold {
    Box {
        TextButton(
            text = "Click to show menu",
            onClick = { showPopup = true },
        )
        OverlayCascadingListPopup(
            show = showPopup,
            entries = entries,
            onDismissRequest = { showPopup = false },
        )
    }
}
```

::: tip Cascading Depth
Cascading depth is capped at 2. Items at the secondary level cannot have their own `children`; deeper trees are silently ignored.
:::

## Component States

### Different Alignments

```kotlin
OverlayCascadingListPopup(
    show = showPopup,
    entries = entries,
    onDismissRequest = { showPopup = false },
    alignment = PopupPositionProvider.Align.Start,
)
```

### Disable Window Dimming

```kotlin
OverlayCascadingListPopup(
    show = showPopup,
    entries = entries,
    onDismissRequest = { showPopup = false },
    enableWindowDim = false,
)
```

### Keep Popup Open After Selection

By default the popup closes when the user picks any leaf item. Set `collapseOnSelection = false` to keep it open (e.g., for multi-select within a submenu).

```kotlin
OverlayCascadingListPopup(
    show = showPopup,
    entries = entries,
    onDismissRequest = { showPopup = false },
    collapseOnSelection = false,
)
```

## Properties

### OverlayCascadingListPopup

| Property Name         | Type                        | Description                                                                                                                                                                  | Default Value                              |
| --------------------- | --------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------ |
| show                  | Boolean                     | Whether to show the popup.                                                                                                                                                   | -                                          |
| entries               | List\<DropdownEntry>        | Grouped dropdown entries; top-level items with non-empty `children` become submenu triggers.                                                                                 | -                                          |
| onDismissRequest      | () -> Unit                  | Called when the user requests dismissal (e.g., clicking outside, tapping the back button).                                                                                   | -                                          |
| popupModifier         | Modifier                    | Modifier applied to the popup body.                                                                                                                                          | Modifier                                   |
| onDismissFinished     | (() -> Unit)?               | Called after the exit animation finishes.                                                                                                                                    | null                                       |
| popupPositionProvider | PopupPositionProvider       | Position strategy for the primary popup relative to its anchor.                                                                                                              | ListPopupDefaults.DropdownPositionProvider |
| alignment             | PopupPositionProvider.Align | Alignment of the primary popup.                                                                                                                                              | PopupPositionProvider.Align.End            |
| enableWindowDim       | Boolean                     | Whether to dim the rest of the window while the popup is shown.                                                                                                              | true                                       |
| maxHeight             | Dp?                         | Maximum height of either side. Null bounds it by the safe area.                                                                                                              | null                                       |
| minWidth              | Dp                          | Minimum width of the popup.                                                                                                                                                  | 200.dp                                     |
| renderInRootScaffold  | Boolean                     | Whether to render the popup in the root (outermost) Scaffold. When true, the popup covers the full screen. When false, it renders within the current Scaffold's bounds with position compensation. | true |
| dropdownColors        | DropdownColors              | Colors used by every row.                                                                                                                                                    | DropdownDefaults.dropdownColors()          |
| collapseOnSelection   | Boolean                     | When true, selecting any leaf dismisses the popup.                                                                                                                           | true                                       |

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

## Glass Secondary Menus

The optional `miuix-glass` module provides `GlassSecondaryPopup`, which opens a submenu beside a `GlassTransformPopup` that is already showing. It is a separate glass API and does not change `OverlayCascadingListPopup` or its `surface` hook.

Rows accept clicks while the panel opens, and stop accepting them the moment dismissal is requested.

```kotlin
var anchorBounds by remember { mutableStateOf(Rect.Zero) }

GlassPopupItem(
    text = "Sort by",
    onClick = { submenu = true },
    modifier = Modifier.onGloballyPositioned { anchorBounds = it.boundsInRoot() },
)

GlassSecondaryPopup(
    show = submenu,
    onDismissRequest = { submenu = false },
    anchorBounds = anchorBounds,
    backdrop = backdrop,
    onDismissFinished = { submenuPresent = false },
) {
    GlassPopupItem(text = "Name", onClick = { submenu = false })
}
```

Keep the popup composed while it collapses, and freeze the trigger bounds before opening — resume capturing them only after `onDismissFinished`, not as soon as `show` becomes false. Pass the primary popup's `materialAnchor` to share its blur, colour treatment and bloom stroke. An explicit `backdrop` takes precedence, and `null` falls back to the anchor's backdrop. Secondary visibility must depend on the primary being open, never the other way round.

`GlassPopup(secondary = true)` delegates to the same geometry, but does not inherit the anchor's material. Back and outside taps request a collapse; predictive back previews the secondary collapsing first and, with no secondary open, the primary transforming back into its button. The host has to provide a navigation event dispatcher for back gestures.

See [Glass Material](/guide/glass) for setup and the rest of the glass components.
