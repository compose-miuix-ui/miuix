---
title: SuperSpinner
requiresScaffoldHost: true
prerequisites:
  - Must be used within `Scaffold` to provide `MiuixPopupHost`
  - Using outside `Scaffold` will cause popup content not to render
  - In nested `Scaffold`s, keep `MiuixPopupHost` only at top-level; set others empty
hostComponent: Scaffold
popupHost: MiuixPopupHost
---

# SuperSpinner

`SuperSpinner` is a dropdown selector component in Miuix that provides titles, summaries, and a list of options with icons and text. It supports click interaction and various display modes, commonly used in option settings with visual aids. This component is similar to `SuperDropdown` but offers richer functionality and interaction experience.

<div style="position: relative; max-width: 700px; height: 282px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../compose/index.html?id=superSpinner" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

::: danger Prerequisite
This component depends on `Scaffold` providing `MiuixPopupHost` to render popup content. It must be used within `Scaffold`, otherwise popup content will not render correctly.
:::

## Import

```kotlin
import top.yukonga.miuix.kmp.extra.SuperSpinner
import top.yukonga.miuix.kmp.extra.SpinnerEntry
```

## Basic Usage

The SuperSpinner component provides basic dropdown selector functionality:

```kotlin
var selectedIndex by remember { mutableStateOf(0) }
val options = listOf(
    SpinnerEntry(title = "Option 1"),
    SpinnerEntry(title = "Option 2"),
    SpinnerEntry(title = "Option 3"),
)

Scaffold {
    SuperSpinner(
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
    SuperSpinner(
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
SuperSpinner(
    title = "Disabled Selector",
    summary = "This selector is currently unavailable",
    items = listOf(SpinnerEntry(title = "Option 1")),
    selectedIndex = 0,
    onSelectedIndexChange = {},
    enabled = false
)
```

## Dialog Mode

SuperSpinner also supports dialog mode, suitable for displaying a larger number of options or when a more prominent selection interface is needed. This mode can be activated by providing the `dialogButtonString` parameter.

```kotlin
var selectedIndex by remember { mutableStateOf(0) }
val options = listOf(
    SpinnerEntry(title = "Option 1"),
    SpinnerEntry(title = "Option 2"),
    SpinnerEntry(title = "Option 3")
)

Scaffold {
    SuperSpinner(
        title = "Dialog Mode",
        dialogButtonString = "Cancel",
        items = options,
        selectedIndex = selectedIndex,
        onSelectedIndexChange = { selectedIndex = it }
    )
}
```

## Properties

### SuperSpinner Properties (Dropdown Mode)

| Property Name         | Type                      | Description                     | Default Value                         | Required |
| --------------------- | ------------------------- | ------------------------------- | ------------------------------------- | -------- |
| items                 | List\<SpinnerEntry>       | Options list                    | -                                     | Yes      |
| selectedIndex         | Int                       | Current selected item index     | -                                     | Yes      |
| title                 | String                    | Selector title                  | -                                     | Yes      |
| titleColor            | BasicComponentColors      | Title text color config         | BasicComponentDefaults.titleColor()   | No       |
| summary               | String?                   | Selector description            | null                                  | No       |
| summaryColor          | BasicComponentColors      | Summary text color config       | BasicComponentDefaults.summaryColor() | No       |
| spinnerColors         | SpinnerColors             | Color configuration for spinner | SpinnerDefaults.spinnerColors()       | No       |
| leftAction            | @Composable (() -> Unit)? | Custom left content             | null                                  | No       |
| bottomAction          | @Composable (() -> Unit)? | Custom bottom content           | null                                  | No       |
| modifier              | Modifier                  | Component modifier              | Modifier                              | No       |
| insideMargin          | PaddingValues             | Internal content padding        | BasicComponentDefaults.InsideMargin   | No       |
| maxHeight             | Dp?                       | Maximum dropdown height         | null                                  | No       |
| enabled               | Boolean                   | Interactive state               | true                                  | No       |
| showValue             | Boolean                   | Show current selected value     | true                                  | No       |
| onSelectedIndexChange | ((Int) -> Unit)?          | Selection change callback       | -                                     | No       |

### SuperSpinner Properties (Dialog Mode)

| Property Name         | Type                      | Description                     | Default Value                         | Required |
| --------------------- | ------------------------- | ------------------------------- | ------------------------------------- | -------- |
| items                 | List\<SpinnerEntry>       | Options list                    | -                                     | Yes      |
| selectedIndex         | Int                       | Current selected item index     | -                                     | Yes      |
| title                 | String                    | Selector title                  | -                                     | Yes      |
| titleColor            | BasicComponentColors      | Title text color config         | BasicComponentDefaults.titleColor()   | No       |
| summary               | String?                   | Selector description            | null                                  | No       |
| summaryColor          | BasicComponentColors      | Summary text color config       | BasicComponentDefaults.summaryColor() | No       |
| spinnerColors         | SpinnerColors             | Color configuration for spinner | SpinnerDefaults.dialogSpinnerColors() | No       |
| leftAction            | @Composable (() -> Unit)? | Custom left content             | null                                  | No       |
| dialogButtonString    | String                    | Dialog bottom button text       | -                                     | Yes      |
| popupModifier         | Modifier                  | Dialog popup modifier           | Modifier                              | No       |
| modifier              | Modifier                  | Component modifier              | Modifier                              | No       |
| insideMargin          | PaddingValues             | Internal content padding        | BasicComponentDefaults.InsideMargin   | No       |
| enabled               | Boolean                   | Interactive state               | true                                  | No       |
| showValue             | Boolean                   | Show current selected value     | true                                  | No       |
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
