---
title: OverlaySpinnerPreference
requiresScaffoldHost: true
prerequisites:
  - Must be used within `Scaffold` to provide `MiuixPopupHost`
  - Using outside `Scaffold` will cause popup content not to render
  - Multiple nested or side-by-side `Scaffold`s are supported without extra configuration
hostComponent: Scaffold
popupHost: MiuixPopupHost
---

# OverlaySpinnerPreference

`OverlaySpinnerPreference` is a dropdown selector component in Miuix that provides titles, summaries, and a list of options with icons and text. It supports click interaction and various display modes, commonly used in option settings with visual aids. This component is similar to `OverlayDropdownPreference` but offers richer functionality and interaction experience.

<div style="position: relative; max-width: 700px; height: 282px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../compose/index.html?id=superSpinner" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

::: danger Prerequisite
This component depends on `Scaffold` providing `MiuixPopupHost` to render popup content. It must be used within `Scaffold`, otherwise popup content will not render correctly.
:::

## Import

```kotlin
import top.yukonga.miuix.kmp.preference.OverlaySpinnerPreference
import top.yukonga.miuix.kmp.basic.DropdownEntry
import top.yukonga.miuix.kmp.basic.DropdownItem
```

## Basic Usage

The OverlaySpinnerPreference component provides basic dropdown selector functionality:

```kotlin
var selectedIndex by remember { mutableStateOf(0) }
val options = listOf(
    DropdownItem(text = "Option 1"),
    DropdownItem(text = "Option 2"),
    DropdownItem(text = "Option 3"),
)

Scaffold {
    OverlaySpinnerPreference(
        title = "Dropdown Selector",
        items = options,
        selectedIndex = selectedIndex,
        onSelectedIndexChange = { selectedIndex = it }
    )
}
```

## Options with Icons and Summaries

```kotlin
// Create a rounded rectangle Painter
class RoundedRectanglePainter(
    private val cornerRadius: Dp = 6.dp
) : Painter() {
    override val intrinsicSize = Size.Unspecified

    override fun DrawScope.onDraw() {
        drawRoundRect(
            color = Color.White,
            size = Size(size.width, size.height),
            cornerRadius = CornerRadius(cornerRadius.toPx(), cornerRadius.toPx())
        )
    }
}

var selectedIndex by remember { mutableStateOf(0) }
val options = listOf(
    DropdownItem(
        icon = { Icon(RoundedRectanglePainter(), "Icon", Modifier.padding(end = 12.dp), Color(0xFFFF5B29)) },
        text = "Red Theme",
        summary = "Vibrant red"
    ),
    DropdownItem(
        icon = { Icon(RoundedRectanglePainter(), "Icon", Modifier.padding(end = 12.dp), Color(0xFF3482FF)) },
        text = "Blue Theme",
        summary = "Calm blue"
    ),
    DropdownItem(
        icon = { Icon(RoundedRectanglePainter(), "Icon", Modifier.padding(end = 12.dp), Color(0xFF36D167)) },
        text = "Green Theme",
        summary = "Fresh green"
    ),
    DropdownItem(
        icon = { Icon(RoundedRectanglePainter(), "Icon", Modifier.padding(end = 12.dp), Color(0xFFFFB21D)) },
        text = "Yellow Theme",
        summary = "Bright yellow"
    )
)

Scaffold {
    OverlaySpinnerPreference(
        title = "Function Selection",
        summary = "Choose the action you want to perform",
        items = options,
        selectedIndex = selectedIndex,
        onSelectedIndexChange = { selectedIndex = it }
    )
}
```

## Component States

### Disabled State

```kotlin
OverlaySpinnerPreference(
    title = "Disabled Selector",
    summary = "This selector is currently unavailable",
    items = listOf(DropdownItem(text = "Option 1")),
    selectedIndex = 0,
    onSelectedIndexChange = {},
    enabled = false
)
```

## Dialog Mode

OverlaySpinnerPreference also supports dialog mode, suitable for displaying a larger number of options or when a more prominent selection interface is needed. This mode can be activated by providing the `dialogButtonString` parameter.

```kotlin
var selectedIndex by remember { mutableStateOf(0) }
val options = listOf(
    DropdownItem(text = "Option 1"),
    DropdownItem(text = "Option 2"),
    DropdownItem(text = "Option 3")
)

Scaffold {
    OverlaySpinnerPreference(
        title = "Dialog Mode",
        dialogButtonString = "Cancel",
        items = options,
        selectedIndex = selectedIndex,
        onSelectedIndexChange = { selectedIndex = it }
    )
}
```

## Grouped Options

Use `DropdownEntry` when you need a custom item list with disabled items, item callbacks, icons, or summaries. Use `entries` to show multiple option groups separated by dividers.

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

Scaffold {
    OverlaySpinnerPreference(
        title = "Grouped Selector",
        entries = entries,
        collapseOnSelection = false
    )
}
```

For the `entries` overload, `collapseOnSelection` controls whether the popup closes after an item is selected. It defaults to `entries.size <= 1`, so a single group closes after selection while multiple groups stay open for consecutive changes. The same `entry` and `entries` overloads are also available in dialog mode by providing `dialogButtonString`.

## Observe Expanded State

```kotlin
var selectedIndex by remember { mutableStateOf(0) }
var expanded by remember { mutableStateOf(false) }
val options = listOf(
    DropdownItem(text = "Option 1"),
    DropdownItem(text = "Option 2"),
    DropdownItem(text = "Option 3"),
)

Scaffold {
    OverlaySpinnerPreference(
        title = "Dropdown Selector",
        summary = if (expanded) "Expanded" else "Collapsed",
        items = options,
        selectedIndex = selectedIndex,
        onExpandedChange = { expanded = it },
        onSelectedIndexChange = { selectedIndex = it }
    )
}
```

## Properties

### OverlaySpinnerPreference Properties (Dropdown Mode)

| Property Name         | Type                      | Description                     | Default Value                         | Required |
| --------------------- | ------------------------- | ------------------------------- | ------------------------------------- | -------- |
| items                 | List\<DropdownItem>       | Options list                    | -                                     | Yes      |
| selectedIndex         | Int                       | Current selected item index     | -                                     | Yes      |
| title                 | String                    | Selector title                  | -                                     | Yes      |
| modifier              | Modifier                  | Component modifier              | Modifier                              | No       |
| titleColor            | BasicComponentColors      | Title text color config         | BasicComponentDefaults.titleColor()   | No       |
| summary               | String?                   | Selector description            | null                                  | No       |
| summaryColor          | BasicComponentColors      | Summary text color config       | BasicComponentDefaults.summaryColor() | No       |
| spinnerColors         | DropdownColors            | Color configuration for spinner | DropdownDefaults.dropdownColors()     | No       |
| startAction           | @Composable (() -> Unit)? | Custom start side content       | null                                  | No       |
| bottomAction          | @Composable (() -> Unit)? | Custom bottom side content      | null                                  | No       |
| insideMargin          | PaddingValues             | Internal content padding        | BasicComponentDefaults.InsideMargin   | No       |
| maxHeight             | Dp?                       | Maximum dropdown height         | null                                  | No       |
| enabled               | Boolean                   | Interactive state               | true                                  | No       |
| showValue             | Boolean                   | Show current selected value     | true                                  | No       |
| renderInRootScaffold  | Boolean                   | Whether to render the popup in the root (outermost) Scaffold. When true, the popup covers the full screen. When false, it renders within the current Scaffold's bounds with position compensation | true | No |
| onExpandedChange      | ((Boolean) -> Unit)?      | Callback when expanded state changes | null                               | No       |
| onSelectedIndexChange | ((Int) -> Unit)?          | Selection change callback       | -                                     | No       |

### Entry Overload Properties

| Property Name       | Type          | Description                                | Default Value | Required |
| ------------------- | ------------- | ------------------------------------------ | ------------- | -------- |
| entry               | DropdownEntry | Single dropdown entry group                | -             | Yes      |
| collapseOnSelection | Boolean       | Whether to close the popup after selection | true          | No       |

### Grouped Entries Overload Properties

| Property Name        | Type                 | Description                                      | Default Value     | Required |
| -------------------- | -------------------- | ------------------------------------------------ | ----------------- | -------- |
| entries              | List\<DropdownEntry> | Dropdown entry groups separated by dividers      | -                 | Yes      |
| collapseOnSelection  | Boolean              | Whether to close the popup after each selection  | entries.size <= 1 | No       |
| renderInRootScaffold | Boolean              | Whether to render the popup in the root Scaffold | true              | No       |

### OverlaySpinnerPreference Properties (Dialog Mode)

| Property Name         | Type                      | Description                          | Default Value                           | Required |
| --------------------- | ------------------------- | ------------------------------------ | --------------------------------------- | -------- |
| items                 | List\<DropdownItem>       | Options list                         | -                                       | Yes      |
| selectedIndex         | Int                       | Current selected item index          | -                                       | Yes      |
| title                 | String                    | Selector title                       | -                                       | Yes      |
| dialogButtonString    | String                    | Dialog bottom button text            | -                                       | Yes      |
| modifier              | Modifier                  | Component modifier                   | Modifier                                | No       |
| popupModifier         | Modifier                  | Dialog popup modifier                | Modifier                                | No       |
| titleColor            | BasicComponentColors      | Title text color config              | BasicComponentDefaults.titleColor()     | No       |
| summary               | String?                   | Selector description                 | null                                    | No       |
| summaryColor          | BasicComponentColors      | Summary text color config            | BasicComponentDefaults.summaryColor()   | No       |
| spinnerColors         | DropdownColors            | Color configuration for spinner      | DropdownDefaults.dialogDropdownColors() | No       |
| startAction           | @Composable (() -> Unit)? | Custom start side content            | null                                    | No       |
| bottomAction          | @Composable (() -> Unit)? | Custom bottom side content           | null                                    | No       |
| insideMargin          | PaddingValues             | Internal content padding             | BasicComponentDefaults.InsideMargin     | No       |
| enabled               | Boolean                   | Interactive state                    | true                                    | No       |
| showValue             | Boolean                   | Show current selected value          | true                                    | No       |
| renderInRootScaffold  | Boolean                   | Whether to render the dialog in the root (outermost) Scaffold. When true, the dialog covers the full screen. When false, it renders within the current Scaffold's bounds | true | No |
| onExpandedChange      | ((Boolean) -> Unit)?      | Callback when expanded state changes | null                               | No       |
| onSelectedIndexChange | ((Int) -> Unit)?          | Selection change callback            | -                                       | No       |

### Dialog Entry Overload Properties

| Property Name      | Type          | Description                 | Default Value | Required |
| ------------------ | ------------- | --------------------------- | ------------- | -------- |
| entry              | DropdownEntry | Single dropdown entry group | -             | Yes      |
| dialogButtonString | String        | Dialog bottom button text   | -             | Yes      |
| collapseOnSelection | Boolean      | Whether to close the dialog after selection | true | No       |

### Dialog Grouped Entries Overload Properties

| Property Name        | Type                 | Description                                       | Default Value     | Required |
| -------------------- | -------------------- | ------------------------------------------------- | ----------------- | -------- |
| entries              | List\<DropdownEntry> | Dropdown entry groups separated by dividers       | -                 | Yes      |
| dialogButtonString   | String               | Dialog bottom button text                         | -                 | Yes      |
| collapseOnSelection  | Boolean              | Whether to close the dialog after each selection  | entries.size <= 1 | No       |
| renderInRootScaffold | Boolean              | Whether to render the dialog in the root Scaffold | true              | No       |

### DropdownEntry Properties

| Property Name         | Type                | Description                                     | Default Value | Required |
| --------------------- | ------------------- | ----------------------------------------------- | ------------- | -------- |
| items                 | List\<DropdownItem> | Items shown in this dropdown group              | -             | Yes      |
| selectedIndex         | Int?                | Selected item index. Null hides selection state | null          | No       |
| onSelectedIndexChange | ((Int) -> Unit)?    | Callback when an item is selected               | null          | No       |

### DropdownItem Properties

| Property Name | Type                              | Description                                              | Default Value | Required |
| ------------- | --------------------------------- | -------------------------------------------------------- | ------------- | -------- |
| text          | String                            | Text shown for the item                                  | -             | Yes      |
| enabled       | Boolean                           | Whether the item can be clicked. Disabled items are gray | true          | No       |
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
