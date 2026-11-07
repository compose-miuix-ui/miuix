---
title: WindowIconCascadingDropdownMenu
requiresScaffoldHost: false
prerequisites:
  - Can be used anywhere, does not require `Scaffold` or `MiuixPopupHost`
  - Renders at window level
hostComponent: None
popupHost: None
---

# WindowIconCascadingDropdownMenu

`WindowIconCascadingDropdownMenu` is an `IconButton` wrapper that opens a `WindowCascadingListPopup` (rendered at window level via `Dialog`) when clicked. Unlike `OverlayIconCascadingDropdownMenu`, it does not require a `Scaffold`. Items whose `DropdownItem.children` is non-empty become submenu triggers; cascading depth is limited to **2**.

<div style="position: relative; height: 410px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../../compose/index.html?id=windowIconCascadingDropdownMenu" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

## Import

```kotlin
import top.yukonga.miuix.kmp.menu.WindowIconCascadingDropdownMenu
import top.yukonga.miuix.kmp.basic.DropdownEntry
import top.yukonga.miuix.kmp.basic.DropdownItem
```

## Basic Usage

Place `WindowIconCascadingDropdownMenu` in the `actions` slot of a `TopAppBar` (or `SmallTopAppBar`). Top-level items with non-empty `children` render a chevron and expand inline on click. No `Scaffold` is required around the menu itself, but a `Scaffold` is still the natural way to host a `TopAppBar`.

```kotlin
var sortIndex by remember { mutableStateOf(0) }
var viewIndex by remember { mutableStateOf(0) }
var filterIndex by remember { mutableStateOf(0) }

val sortLabels = listOf("Sort by capture date", "Sort by date added")
val viewLabels = listOf("Group by date", "Compact")
val filterLabels = listOf("All items", "Camera album")

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
            DropdownItem(
                text = "Filter",
                children = filterLabels.mapIndexed { idx, label ->
                    DropdownItem(
                        text = label,
                        selected = filterIndex == idx,
                        onClick = { filterIndex = idx },
                    )
                },
            ),
        ),
    ),
)

Scaffold(
    topBar = {
        SmallTopAppBar(
            title = "Library",
            actions = {
                WindowIconCascadingDropdownMenu(entries = entries) {
                    Icon(imageVector = MiuixIcons.Tune, contentDescription = "Adjust")
                }
            }
        )
    }
) { padding ->
    // page content
}
```

## Single Entry Overload

When you only need one group of items, use the entry overload to skip the surrounding `listOf(...)`.

```kotlin
val entry = DropdownEntry(
    items = listOf(
        DropdownItem(
            text = "View mode",
            children = listOf(
                DropdownItem(text = "Group by date", onClick = { /* ... */ }),
                DropdownItem(text = "Compact", onClick = { /* ... */ }),
            ),
        ),
        DropdownItem(text = "Refresh", onClick = { /* ... */ }),
    ),
)

WindowIconCascadingDropdownMenu(entry = entry) {
    Icon(imageVector = MiuixIcons.Tune, contentDescription = "Adjust")
}
```

## Component States

### Disabled State

```kotlin
WindowIconCascadingDropdownMenu(
    entry = DropdownEntry(items = listOf(DropdownItem(text = "Option 1"))),
    enabled = false,
) {
    Icon(imageVector = MiuixIcons.MoreCircle, contentDescription = "More")
}
```

The menu is also implicitly disabled when no `DropdownEntry` contains any items.

::: tip Cascading Depth
Cascading depth is capped at 2. Items at the secondary level cannot have their own `children`; deeper trees are silently ignored.
:::

## Properties

### WindowIconCascadingDropdownMenu Properties (Entries Overload)

| Property Name       | Type                      | Description                                                                                                            | Default Value                       | Required |
| ------------------- | ------------------------- | ---------------------------------------------------------------------------------------------------------------------- | ----------------------------------- | -------- |
| entries             | List\<DropdownEntry>      | Dropdown entry groups; top-level items with non-empty `children` become submenu triggers                               | -                                   | Yes      |
| modifier            | Modifier                  | Modifier applied to the wrapping `Box`                                                                                 | Modifier                            | No       |
| enabled             | Boolean                   | Whether the icon button is interactive                                                                                 | true                                | No       |
| maxHeight           | Dp?                       | Maximum height of either side of the cascading popup                                                                   | null                                | No       |
| dropdownColors      | DropdownColors            | Color configuration for dropdown items                                                                                 | DropdownDefaults.dropdownColors()   | No       |
| collapseOnSelection | Boolean                   | Whether to close the popup after a leaf is selected                                                                    | true                                | No       |
| onExpandedChange    | ((Boolean) -> Unit)?      | Callback when the expanded state changes                                                                               | null                                | No       |
| backgroundColor     | Color                     | Background color of the underlying `IconButton`                                                                        | Color.Unspecified                   | No       |
| cornerRadius        | Dp                        | Corner radius of the underlying `IconButton`                                                                           | IconButtonDefaults.CornerRadius     | No       |
| minHeight           | Dp                        | Minimum height of the underlying `IconButton`                                                                          | IconButtonDefaults.MinHeight        | No       |
| minWidth            | Dp                        | Minimum width of the underlying `IconButton`                                                                           | IconButtonDefaults.MinWidth         | No       |
| content             | @Composable () -> Unit    | The icon (or other composable) shown inside the button                                                                 | -                                   | Yes      |

### Entry Overload Properties

| Property Name       | Type          | Description                                                          | Default Value | Required |
| ------------------- | ------------- | -------------------------------------------------------------------- | ------------- | -------- |
| entry               | DropdownEntry | Single dropdown entry group                                          | -             | Yes      |
| collapseOnSelection | Boolean       | Whether to close the popup after a leaf is selected                  | true          | No       |

All other parameters are identical to the entries overload above.

### DropdownEntry Properties

| Property Name | Type                | Description                                                                                            | Default Value | Required |
| ------------- | ------------------- | ------------------------------------------------------------------------------------------------------ | ------------- | -------- |
| items         | List\<DropdownItem> | Items shown in this dropdown group                                                                     | -             | Yes      |
| enabled       | Boolean             | Whether this group is enabled. False disables all items; true still respects each item's enabled state | true          | No       |

### DropdownItem Properties

| Property Name | Type                              | Description                                                                                                                       | Default Value | Required |
| ------------- | --------------------------------- | --------------------------------------------------------------------------------------------------------------------------------- | ------------- | -------- |
| text          | String                            | Text shown for the item                                                                                                           | -             | Yes      |
| enabled       | Boolean                           | Whether the item can be clicked. Disabled items are gray                                                                          | true          | No       |
| selected      | Boolean                           | Whether the item is selected                                                                                                      | false         | No       |
| onClick       | (() -> Unit)?                     | Callback invoked when the item is clicked. Ignored when `children` is non-empty (the click expands the submenu instead)            | null          | No       |
| icon          | @Composable ((Modifier) -> Unit)? | Icon shown before the item text                                                                                                   | null          | No       |
| summary       | String?                           | Summary text shown below the item text                                                                                            | null          | No       |
| children      | List\<DropdownItem>?              | Optional submenu items; only the cascading variants render these as a submenu (depth limited to 2)                                | null          | No       |

### DropdownColors Properties

| Property Name          | Type  | Description                             |
| ---------------------- | ----- | --------------------------------------- |
| contentColor           | Color | Color of the option title               |
| summaryColor           | Color | Color of the option summary             |
| containerColor         | Color | Background color of the option          |
| selectedContentColor   | Color | Title color of the selected option      |
| selectedSummaryColor   | Color | Summary color of the selected option    |
| selectedContainerColor | Color | Background color of the selected option |
| selectedIndicatorColor | Color | Color of the selected indicator icon    |
