---
title: WindowSpinner
requiresScaffoldHost: false
prerequisites:
  - Can be used anywhere, does not require `Scaffold` or `MiuixPopupHost`
  - Renders at the window layer
hostComponent: None
popupHost: None
---

# WindowSpinner

`WindowSpinner` is a dropdown selector component in Miuix that provides titles, summaries, and a list of options with icons and text. It renders at the window level without needing a `Scaffold` host, making it suitable for use cases where `Scaffold` is not available or desired.

<div style="position: relative; max-width: 700px; height: 350px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../compose/index.html?id=windowSpinner" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

::: tip Note
This component does not rely on `Scaffold` and can be used in any Composable scope.
:::

## Import

```kotlin
import top.yukonga.miuix.kmp.extra.WindowSpinner
import top.yukonga.miuix.kmp.extra.SpinnerEntry
```

## Basic Usage

The WindowSpinner component provides basic dropdown selector functionality:

```kotlin
var selectedIndex by remember { mutableStateOf(0) }
val options = listOf(
    SpinnerEntry(title = "Option 1"),
    SpinnerEntry(title = "Option 2"),
    SpinnerEntry(title = "Option 3"),
)

WindowSpinner(
    title = "Dropdown Selector",
    items = options,
    selectedIndex = selectedIndex,
    onSelectedIndexChange = { selectedIndex = it }
)
```

## Options with Icons and Summaries

```kotlin
// Create a rounded rectangle Painter
private class RoundedRectanglePainter(
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
        summary = "Energetic Red"
    ),
    SpinnerEntry(
        icon = { Icon(RoundedRectanglePainter(), "Icon", Modifier.padding(end = 12.dp), Color(0xFF3482FF)) },
        title = "Blue Theme",
        summary = "Calm Blue"
    ),
)


WindowSpinner(
    title = "Menu",
    items = options,
    selectedIndex = selectedIndex,
    onSelectedIndexChange = { selectedIndex = it }
)
```

## Component State

### Disabled State

```kotlin
WindowSpinner(
    title = "Disabled Spinner",
    summary = "This spinner is currently unavailable",
    items = listOf(SpinnerEntry(title = "Option 1")),
    selectedIndex = 0,
    onSelectedIndexChange = {},
    enabled = false
)
```

## Dialog Mode

WindowSpinner also supports a dialog mode, which is useful for displaying a larger list of options or when a more prominent selection interface is needed. This mode is activated by providing a `dialogButtonString`.

```kotlin
var selectedIndex by remember { mutableStateOf(0) }
val options = listOf(
    SpinnerEntry(title = "Option A"),
    SpinnerEntry(title = "Option B"),
    SpinnerEntry(title = "Option C")
)

WindowSpinner(
    title = "Dialog Selector",
    dialogButtonString = "Cancel",
    items = options,
    selectedIndex = selectedIndex,
    onSelectedIndexChange = { selectedIndex = it },
)
```

## Properties

### WindowSpinner Properties (Popup Mode)

| Property Name         | Type                      | Description                        | Default Value                         | Required |
| --------------------- | ------------------------- | ---------------------------------- | ------------------------------------- | -------- |
| items                 | List\<SpinnerEntry>       | List of spinner entries            | -                                     | Yes      |
| selectedIndex         | Int                       | Index of currently selected item   | -                                     | Yes      |
| title                 | String                    | Title of the spinner               | -                                     | Yes      |
| modifier              | Modifier                  | Modifier applied to the component  | Modifier                              | No       |
| titleColor            | BasicComponentColors      | Title text color configuration     | BasicComponentDefaults.titleColor()   | No       |
| summary               | String?                   | Summary description                | null                                  | No       |
| summaryColor          | BasicComponentColors      | Summary text color configuration   | BasicComponentDefaults.summaryColor() | No       |
| spinnerColors         | SpinnerColors             | Color configuration for spinner    | SpinnerDefaults.spinnerColors()       | No       |
| leftAction            | @Composable (() -> Unit)? | Custom left content                | null                                  | No       |
| bottomAction          | @Composable (() -> Unit)? | Custom bottom content              | null                                  | No       |
| insideMargin          | PaddingValues             | Internal content padding           | BasicComponentDefaults.InsideMargin   | No       |
| maxHeight             | Dp?                       | Maximum height of popup            | null                                  | No       |
| enabled               | Boolean                   | Whether component is interactive   | true                                  | No       |
| showValue             | Boolean                   | Whether to show the selected value | true                                  | No       |
| onSelectedIndexChange | ((Int) -> Unit)?          | Selection change callback          | -                                     | No       |

### WindowSpinner Properties (Dialog Mode)

| Property Name         | Type                      | Description                        | Default Value                         | Required |
| --------------------- | ------------------------- | ---------------------------------- | ------------------------------------- | -------- |
| items                 | List\<SpinnerEntry>       | List of spinner entries            | -                                     | Yes      |
| selectedIndex         | Int                       | Index of currently selected item   | -                                     | Yes      |
| title                 | String                    | Title of the spinner               | -                                     | Yes      |
| dialogButtonString    | String                    | Text for the dialog button         | -                                     | Yes      |
| modifier              | Modifier                  | Modifier applied to the component  | Modifier                              | No       |
| popupModifier         | Modifier                  | Modifier for the popup dialog      | Modifier                              | No       |
| titleColor            | BasicComponentColors      | Title text color configuration     | BasicComponentDefaults.titleColor()   | No       |
| summary               | String?                   | Summary description                | null                                  | No       |
| summaryColor          | BasicComponentColors      | Summary text color configuration   | BasicComponentDefaults.summaryColor() | No       |
| spinnerColors         | SpinnerColors             | Color configuration for spinner    | SpinnerDefaults.dialogSpinnerColors() | No       |
| leftAction            | @Composable (() -> Unit)? | Custom left content                | null                                  | No       |
| insideMargin          | PaddingValues             | Internal content padding           | BasicComponentDefaults.InsideMargin   | No       |
| enabled               | Boolean                   | Whether component is interactive   | true                                  | No       |
| showValue             | Boolean                   | Whether to show the selected value | true                                  | No       |
| onSelectedIndexChange | ((Int) -> Unit)?          | Selection change callback          | -                                     | No       |

### SpinnerEntry Properties

| Property Name | Type                              | Description                       |
| ------------- | --------------------------------- | --------------------------------- |
| icon          | @Composable ((Modifier) -> Unit)? | Icon component for the option     |
| title         | String?                           | Title of the option               |
| summary       | String?                           | Summary description of the option |

### SpinnerColors Properties

| Property Name          | Type  | Description                             |
| ---------------------- | ----- | --------------------------------------- |
| contentColor           | Color | Color of the option title               |
| summaryColor           | Color | Color of the option summary             |
| containerColor         | Color | Background color of the option          |
| selectedContentColor   | Color | Title color of the selected option      |
| selectedSummaryColor   | Color | Summary color of the selected option    |
| selectedContainerColor | Color | Background color of the selected option |
| selectedIndicatorColor | Color | Color of the selection indicator icon   |
