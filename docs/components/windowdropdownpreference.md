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

<div style="position: relative; max-width: 700px; height: 285px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../compose/index.html?id=windowDropdown" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
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

Use `DropdownEntry` and `DropdownItem` when individual dropdown items need extra state, such as disabling a specific option.

```kotlin
var selectedIndex by remember { mutableStateOf(0) }
val entry = DropdownEntry(
    items = listOf(
        DropdownItem(text = "Option 1"),
        DropdownItem(text = "Option 2", enabled = false),
        DropdownItem(text = "Option 3"),
    ),
    selectedIndex = selectedIndex,
    onSelectedIndexChange = { selectedIndex = it }
)

WindowDropdownPreference(
    title = "Dropdown Menu",
    entry = entry
)
```

Disabled dropdown items are not clickable and their text and selected indicator use the disabled color.

## Grouped Dropdown

Use `entries` to show multiple dropdown groups separated by dividers. Each group keeps its own selected index and callback.

```kotlin
var firstSelectedIndex by remember { mutableStateOf(0) }
var secondSelectedIndex by remember { mutableStateOf(0) }
val entries = listOf(
    DropdownEntry(
        items = listOf("Small", "Medium").map { DropdownItem(text = it) },
        selectedIndex = firstSelectedIndex,
        onSelectedIndexChange = { firstSelectedIndex = it }
    ),
    DropdownEntry(
        items = listOf("Red", "Green", "Blue").map { DropdownItem(text = it) },
        selectedIndex = secondSelectedIndex,
        onSelectedIndexChange = { secondSelectedIndex = it }
    )
)

WindowDropdownPreference(
    title = "Grouped Dropdown",
    entries = entries,
    collapseOnSelection = false
)
```

For the `entries` overload, `collapseOnSelection` controls whether the popup closes after an item is selected. It defaults to `false` so users can change multiple groups without reopening the popup.

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

| Property Name       | Type                 | Description                                     | Default Value | Required |
| ------------------- | -------------------- | ----------------------------------------------- | ------------- | -------- |
| entries             | List\<DropdownEntry> | Dropdown entry groups separated by dividers     | -             | Yes      |
| collapseOnSelection | Boolean              | Whether to close the popup after each selection | false         | No       |

### DropdownEntry Properties

| Property Name         | Type                | Description                                     | Default Value | Required |
| --------------------- | ------------------- | ----------------------------------------------- | ------------- | -------- |
| items                 | List\<DropdownItem> | Items shown in this dropdown group              | -             | Yes      |
| selectedIndex         | Int?                | Selected item index. Null hides selection state | null          | No       |
| onSelectedIndexChange | ((Int) -> Unit)?    | Callback when an item is selected               | null          | No       |

### DropdownItem Properties

| Property Name | Type          | Description                                              | Default Value | Required |
| ------------- | ------------- | -------------------------------------------------------- | ------------- | -------- |
| text          | String        | Text shown for the item                                  | -             | Yes      |
| enabled       | Boolean       | Whether the item can be clicked. Disabled items are gray | true          | No       |
| onClick       | (() -> Unit)? | Reserved for action-style dropdown items                 | null          | No       |

### DropdownColors Properties

| Property Name          | Type  | Description                    |
| ---------------------- | ----- | ------------------------------ |
| contentColor           | Color | Option text color              |
| containerColor         | Color | Option background color        |
| selectedContentColor   | Color | Selected item text color       |
| selectedContainerColor | Color | Selected item background color |
