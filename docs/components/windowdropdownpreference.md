---
title: WindowDropdownPreference
requiresScaffoldHost: false
prerequisites:
  - Can be used anywhere, does not require `Scaffold` or `MiuixPopupHost`
  - Renders at window level
hostComponent: None
popupHost: None
---

# WindowDropdownPreference

`WindowDropdownPreference` is a dropdown menu component in Miuix that provides a title, summary, and a list of dropdown options. It renders at the window level without needing a `Scaffold` host, making it suitable for use cases where `Scaffold` is not available or desired.

<div style="position: relative; height: 360px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../compose/index.html?id=windowDropdownPreference" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

## Import

```kotlin
import top.yukonga.miuix.kmp.preference.WindowDropdownPreference
import top.yukonga.miuix.kmp.basic.DropdownEntry
import top.yukonga.miuix.kmp.basic.DropdownItem
```

## Basic Usage

The WindowDropdownPreference component provides basic dropdown menu functionality:

```kotlin
var selectedIndex by remember { mutableStateOf(0) }
val options = listOf("Option 1", "Option 2", "Option 3")

WindowDropdownPreference(
    title = "Dropdown Menu",
    items = options,
    selectedIndex = selectedIndex,
    onSelectedIndexChange = { selectedIndex = it }
)
```

## Dropdown with Summary

```kotlin
var selectedIndex by remember { mutableStateOf(0) }
val options = listOf("中文", "English", "日本語")

WindowDropdownPreference(
    title = "Language Settings",
    summary = "Choose your preferred language",
    items = options,
    selectedIndex = selectedIndex,
    onSelectedIndexChange = { selectedIndex = it }
)
```

## Observe Expanded State

```kotlin
var selectedIndex by remember { mutableStateOf(0) }
var expanded by remember { mutableStateOf(false) }
val options = listOf("Option 1", "Option 2", "Option 3")

WindowDropdownPreference(
    title = "Dropdown Menu",
    summary = if (expanded) "Expanded" else "Collapsed",
    items = options,
    selectedIndex = selectedIndex,
    onExpandedChange = { expanded = it },
    onSelectedIndexChange = { selectedIndex = it }
)
```

## Custom Entries

Use `DropdownEntry` and `DropdownItem` when individual dropdown items need extra state, such as selection, callbacks, or disabling a specific option.

```kotlin
var selectedIndex by remember { mutableStateOf(0) }
val entry = DropdownEntry(
    items = listOf(
        DropdownItem(text = "Option 1", selected = selectedIndex == 0, onClick = { selectedIndex = 0 }),
        DropdownItem(text = "Option 2", enabled = false),
        DropdownItem(text = "Option 3", selected = selectedIndex == 2, onClick = { selectedIndex = 2 }),
    )
)

WindowDropdownPreference(
    title = "Dropdown Menu",
    entry = entry
)
```

Disabled dropdown items are not clickable and their text and selected indicator use the disabled color.

## Grouped Dropdown

Use `entries` to show multiple dropdown groups separated by dividers. Each item keeps its own selected state and click callback.

```kotlin
var firstSelectedIndex by remember { mutableStateOf(0) }
var secondSelectedIndex by remember { mutableStateOf(0) }
val entries = listOf(
    DropdownEntry(
        items = listOf("Small", "Medium").mapIndexed { index, text ->
            DropdownItem(text = text, selected = firstSelectedIndex == index, onClick = { firstSelectedIndex = index })
        }
    ),
    DropdownEntry(
        items = listOf("Red", "Green", "Blue").mapIndexed { index, text ->
            DropdownItem(text = text, selected = secondSelectedIndex == index, onClick = { secondSelectedIndex = index })
        }
    )
)

WindowDropdownPreference(
    title = "Grouped Dropdown",
    entries = entries,
    collapseOnSelection = false
)
```

For the `entries` overload, `collapseOnSelection` controls whether the popup closes after an item is selected. It defaults to `entries.size <= 1`, so a single group closes after selection while multiple groups stay open for consecutive changes.

## Multi Select

Because selection state lives on `DropdownItem`, multiple items can be selected by keeping a set of selected values and toggling each item from `onClick`.

```kotlin
var selectedItems by remember { mutableStateOf(setOf("A1", "B2")) }
val entries = listOf(
    DropdownEntry(
        items = listOf("A1", "A2").map { text ->
            DropdownItem(
                text = text,
                selected = text in selectedItems,
                onClick = {
                    selectedItems = if (text in selectedItems) selectedItems - text else selectedItems + text
                }
            )
        }
    ),
    DropdownEntry(
        items = listOf("B1", "B2", "B3").map { text ->
            DropdownItem(
                text = text,
                selected = text in selectedItems,
                onClick = {
                    selectedItems = if (text in selectedItems) selectedItems - text else selectedItems + text
                }
            )
        }
    )
)

WindowDropdownPreference(
    title = "Multi Select Dropdown",
    entries = entries,
    collapseOnSelection = false
)
```

## Component States

### Disabled State

```kotlin
WindowDropdownPreference(
    title = "Disabled Dropdown",
    summary = "This dropdown menu is currently unavailable",
    items = listOf("Option 1"),
    selectedIndex = 0,
    onSelectedIndexChange = {},
    enabled = false
)
```

## Properties

### WindowDropdownPreference Properties

| Property Name         | Type                      | Description                          | Default Value                         | Required |
| --------------------- | ------------------------- | ------------------------------------ | ------------------------------------- | -------- |
| items                 | List\<String>             | List of dropdown options             | -                                     | Yes      |
| selectedIndex         | Int                       | Index of currently selected item     | -                                     | Yes      |
| title                 | String                    | Title of the dropdown menu           | -                                     | Yes      |
| modifier              | Modifier                  | Modifier applied to the component    | Modifier                              | No       |
| titleColor            | BasicComponentColors      | Title text color configuration       | BasicComponentDefaults.titleColor()   | No       |
| summary               | String?                   | Summary description of dropdown      | null                                  | No       |
| summaryColor          | BasicComponentColors      | Summary text color configuration     | BasicComponentDefaults.summaryColor() | No       |
| dropdownColors        | DropdownColors            | Color configuration for dropdown     | DropdownDefaults.dropdownColors()     | No       |
| startAction           | @Composable (() -> Unit)? | Custom start side content            | null                                  | No       |
| bottomAction          | @Composable (() -> Unit)? | Custom bottom side content           | null                                  | No       |
| insideMargin          | PaddingValues             | Internal content padding             | BasicComponentDefaults.InsideMargin   | No       |
| maxHeight             | Dp?                       | Maximum height of dropdown menu      | null                                  | No       |
| enabled               | Boolean                   | Whether component is interactive     | true                                  | No       |
| showValue             | Boolean                   | Whether to show the selected value   | true                                  | No       |
| onExpandedChange      | ((Boolean) -> Unit)?      | Callback when expanded state changes | null                                  | No       |
| onSelectedIndexChange | ((Int) -> Unit)?          | Selection change callback            | -                                     | No       |

### Entry Overload Properties

| Property Name       | Type          | Description                                | Default Value | Required |
| ------------------- | ------------- | ------------------------------------------ | ------------- | -------- |
| entry               | DropdownEntry | Single dropdown entry group                | -             | Yes      |
| collapseOnSelection | Boolean       | Whether to close the popup after selection | true          | No       |

### Grouped Entries Overload Properties

| Property Name       | Type                 | Description                                     | Default Value     | Required |
| ------------------- | -------------------- | ----------------------------------------------- | ----------------- | -------- |
| entries             | List\<DropdownEntry> | Dropdown entry groups separated by dividers     | -                 | Yes      |
| collapseOnSelection | Boolean              | Whether to close the popup after each selection | entries.size <= 1 | No       |

### DropdownEntry Properties

| Property Name | Type                | Description                        | Default Value | Required |
| ------------- | ------------------- | ---------------------------------- | ------------- | -------- |
| items         | List\<DropdownItem> | Items shown in this dropdown group | -             | Yes      |
| enabled       | Boolean             | Whether this group is enabled. False disables all items; true still respects each item's enabled state | true | No |

Group titles are reserved for future use. The original MIUI dropdown style currently has no matching group-title presentation, so the `title` field is not exposed yet.

### DropdownItem Properties

| Property Name | Type                              | Description                                              | Default Value | Required |
| ------------- | --------------------------------- | -------------------------------------------------------- | ------------- | -------- |
| text          | String                            | Text shown for the item                                  | -             | Yes      |
| enabled       | Boolean                           | Whether the item can be clicked. Disabled items are gray | true          | No       |
| selected      | Boolean                           | Whether the item is selected                             | false         | No       |
| onClick       | (() -> Unit)?                     | Callback invoked when the item is clicked                | null          | No       |
| icon          | @Composable ((Modifier) -> Unit)? | Icon shown before the item text                          | null          | No       |
| summary       | String?                           | Summary text shown below the item text                   | null          | No       |

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
