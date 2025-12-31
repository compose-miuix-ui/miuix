---
title: WindowDropdown
requiresScaffoldHost: false
prerequisites:
  - Can be used anywhere, does not require `Scaffold` or `MiuixPopupHost`
  - Renders at window level
hostComponent: None
popupHost: None
---

# WindowDropdown

`WindowDropdown` is a dropdown menu component in Miuix that provides a title, summary, and a list of dropdown options. It renders at the window level without needing a `Scaffold` host, making it suitable for use cases where `Scaffold` is not available or desired.

<div style="position: relative; max-width: 700px; height: 285px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../compose/index.html?id=windowDropdown" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

## Import

```kotlin
import top.yukonga.miuix.kmp.extra.WindowDropdown
```

## Basic Usage

The WindowDropdown component provides basic dropdown menu functionality:

```kotlin
var selectedIndex by remember { mutableStateOf(0) }
val options = listOf("Option 1", "Option 2", "Option 3")

WindowDropdown(
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

WindowDropdown(
    title = "Language Settings",
    summary = "Choose your preferred language",
    items = options,
    selectedIndex = selectedIndex,
    onSelectedIndexChange = { selectedIndex = it }
)
```

## Component States

### Disabled State

```kotlin
WindowDropdown(
    title = "Disabled Dropdown",
    summary = "This dropdown menu is currently unavailable",
    items = listOf("Option 1"),
    selectedIndex = 0,
    onSelectedIndexChange = {},
    enabled = false
)
```

## Properties

### WindowDropdown Properties

| Property Name         | Type                      | Description                        | Default Value                         | Required |
| --------------------- | ------------------------- | ---------------------------------- | ------------------------------------- | -------- |
| items                 | List\<String>             | List of dropdown options           | -                                     | Yes      |
| selectedIndex         | Int                       | Index of currently selected item   | -                                     | Yes      |
| title                 | String                    | Title of the dropdown menu         | -                                     | Yes      |
| modifier              | Modifier                  | Modifier applied to the component  | Modifier                              | No       |
| titleColor            | BasicComponentColors      | Title text color configuration     | BasicComponentDefaults.titleColor()   | No       |
| summary               | String?                   | Summary description of dropdown    | null                                  | No       |
| summaryColor          | BasicComponentColors      | Summary text color configuration   | BasicComponentDefaults.summaryColor() | No       |
| dropdownColors        | DropdownColors            | Color configuration for dropdown   | DropdownDefaults.dropdownColors()     | No       |
| startAction           | @Composable (() -> Unit)? | Custom start side content          | null                                  | No       |
| bottomAction          | @Composable (() -> Unit)? | Custom bottom side content         | null                                  | No       |
| insideMargin          | PaddingValues             | Internal content padding           | BasicComponentDefaults.InsideMargin   | No       |
| maxHeight             | Dp?                       | Maximum height of dropdown menu    | null                                  | No       |
| enabled               | Boolean                   | Whether component is interactive   | true                                  | No       |
| showValue             | Boolean                   | Whether to show the selected value | true                                  | No       |
| onSelectedIndexChange | ((Int) -> Unit)?          | Selection change callback          | -                                     | No       |

### DropdownColors Properties

| Property Name          | Type  | Description                    |
| ---------------------- | ----- | ------------------------------ |
| contentColor           | Color | Option text color              |
| containerColor         | Color | Option background color        |
| selectedContentColor   | Color | Selected item text color       |
| selectedContainerColor | Color | Selected item background color |
