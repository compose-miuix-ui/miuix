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
import top.yukonga.miuix.kmp.basic.SpinnerEntry
```

## Basic Usage

The OverlaySpinnerPreference component provides basic dropdown selector functionality:

```kotlin
var selectedIndex by remember { mutableStateOf(0) }
val options = listOf(
    SpinnerEntry(title = "Option 1"),
    SpinnerEntry(title = "Option 2"),
    SpinnerEntry(title = "Option 3"),
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
    SpinnerEntry(
        icon = { Icon(RoundedRectanglePainter(), "Icon", Modifier.padding(end = 12.dp), Color(0xFFFF5B29)) },
        title = "Red Theme",
        summary = "Vibrant red"
    ),
    SpinnerEntry(
        icon = { Icon(RoundedRectanglePainter(), "Icon", Modifier.padding(end = 12.dp), Color(0xFF3482FF)) },
        title = "Blue Theme",
        summary = "Calm blue"
    ),
    SpinnerEntry(
        icon = { Icon(RoundedRectanglePainter(), "Icon", Modifier.padding(end = 12.dp), Color(0xFF36D167)) },
        title = "Green Theme",
        summary = "Fresh green"
    ),
    SpinnerEntry(
        icon = { Icon(RoundedRectanglePainter(), "Icon", Modifier.padding(end = 12.dp), Color(0xFFFFB21D)) },
        title = "Yellow Theme",
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
    items = listOf(SpinnerEntry(title = "Option 1")),
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
    SpinnerEntry(title = "Option 1"),
    SpinnerEntry(title = "Option 2"),
    SpinnerEntry(title = "Option 3")
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

## Observe Expanded State

```kotlin
var selectedIndex by remember { mutableStateOf(0) }
var expanded by remember { mutableStateOf(false) }
val options = listOf(
    SpinnerEntry(title = "Option 1"),
    SpinnerEntry(title = "Option 2"),
    SpinnerEntry(title = "Option 3"),
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
| items                 | List\<SpinnerEntry>       | Options list                    | -                                     | Yes      |
| selectedIndex         | Int                       | Current selected item index     | -                                     | Yes      |
| title                 | String                    | Selector title                  | -                                     | Yes      |
| modifier              | Modifier                  | Component modifier              | Modifier                              | No       |
| titleColor            | BasicComponentColors      | Title text color config         | BasicComponentDefaults.titleColor()   | No       |
| summary               | String?                   | Selector description            | null                                  | No       |
| summaryColor          | BasicComponentColors      | Summary text color config       | BasicComponentDefaults.summaryColor() | No       |
| spinnerColors         | SpinnerColors             | Color configuration for spinner | SpinnerDefaults.spinnerColors()       | No       |
| startAction           | @Composable (() -> Unit)? | Custom start side content       | null                                  | No       |
| bottomAction          | @Composable (() -> Unit)? | Custom bottom side content      | null                                  | No       |
| insideMargin          | PaddingValues             | Internal content padding        | BasicComponentDefaults.InsideMargin   | No       |
| maxHeight             | Dp?                       | Maximum dropdown height         | null                                  | No       |
| enabled               | Boolean                   | Interactive state               | true                                  | No       |
| showValue             | Boolean                   | Show current selected value     | true                                  | No       |
| renderInRootScaffold  | Boolean                   | Whether to render the popup in the root (outermost) Scaffold. When true, the popup covers the full screen. When false, it renders within the current Scaffold's bounds with position compensation | true | No |
| onExpandedChange      | ((Boolean) -> Unit)?      | Callback when expanded state changes | null                               | No       |
| onSelectedIndexChange | ((Int) -> Unit)?          | Selection change callback       | -                                     | No       |

### OverlaySpinnerPreference Properties (Dialog Mode)

| Property Name         | Type                      | Description                     | Default Value                         | Required |
| --------------------- | ------------------------- | ------------------------------- | ------------------------------------- | -------- |
| items                 | List\<SpinnerEntry>       | Options list                    | -                                     | Yes      |
| selectedIndex         | Int                       | Current selected item index     | -                                     | Yes      |
| title                 | String                    | Selector title                  | -                                     | Yes      |
| dialogButtonString    | String                    | Dialog bottom button text       | -                                     | Yes      |
| modifier              | Modifier                  | Component modifier              | Modifier                              | No       |
| popupModifier         | Modifier                  | Dialog popup modifier           | Modifier                              | No       |
| titleColor            | BasicComponentColors      | Title text color config         | BasicComponentDefaults.titleColor()   | No       |
| summary               | String?                   | Selector description            | null                                  | No       |
| summaryColor          | BasicComponentColors      | Summary text color config       | BasicComponentDefaults.summaryColor() | No       |
| spinnerColors         | SpinnerColors             | Color configuration for spinner | SpinnerDefaults.dialogSpinnerColors() | No       |
| startAction           | @Composable (() -> Unit)? | Custom start side content       | null                                  | No       |
| bottomAction          | @Composable (() -> Unit)? | Custom bottom side content      | null                                  | No       |
| insideMargin          | PaddingValues             | Internal content padding        | BasicComponentDefaults.InsideMargin   | No       |
| enabled               | Boolean                   | Interactive state               | true                                  | No       |
| showValue             | Boolean                   | Show current selected value     | true                                  | No       |
| renderInRootScaffold  | Boolean                   | Whether to render the dialog in the root (outermost) Scaffold. When true, the dialog covers the full screen. When false, it renders within the current Scaffold's bounds | true | No |
| onExpandedChange      | ((Boolean) -> Unit)?      | Callback when expanded state changes | null                               | No       |
| onSelectedIndexChange | ((Int) -> Unit)?          | Selection change callback       | -                                     | No       |

### SpinnerEntry Properties

| Property Name | Type                              | Description        |
| ------------- | --------------------------------- | ------------------ |
| icon          | @Composable ((Modifier) -> Unit)? | Option icon        |
| title         | String?                           | Option title       |
| summary       | String?                           | Option description |

### SpinnerColors Properties

| Property Name          | Type  | Description                    |
| ---------------------- | ----- | ------------------------------ |
| contentColor           | Color | Option title color             |
| summaryColor           | Color | Option summary color           |
| containerColor         | Color | Option background color        |
| selectedContentColor   | Color | Selected item title color      |
| selectedSummaryColor   | Color | Selected item summary color    |
| selectedContainerColor | Color | Selected item background color |
| selectedIndicatorColor | Color | Selected check icon color      |
