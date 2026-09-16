# NavigationRail

`NavigationRail` is a side navigation component for wide screens. It supports fixed collapsed,
fixed expanded, and state-controlled layouts.

<div style="position: relative; height: 300px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../compose/index.html?id=navigationRail" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

## Import

```kotlin
import top.yukonga.miuix.kmp.basic.NavigationRail
import top.yukonga.miuix.kmp.basic.NavigationRailItem
import top.yukonga.miuix.kmp.basic.rememberNavigationRailState
```

## Basic Usage

By default, `NavigationRail` uses the collapsed layout:

```kotlin
var selectedIndex by remember { mutableStateOf(0) }
val items = listOf("Home", "Profile", "Settings")
val icons = listOf(MiuixIcons.VerticalSplit, MiuixIcons.Contacts, MiuixIcons.Settings)

Row {
    NavigationRail {
        items.forEachIndexed { index, label ->
            NavigationRailItem(
                selected = selectedIndex == index,
                onClick = { selectedIndex = index },
                icon = icons[index],
                label = label
            )
        }
    }
    // Content area
}
```

## Layout Modes

Use the fixed-layout overload and set `expanded` to choose its layout:

```kotlin
NavigationRail(expanded = true) {
    // NavigationRailItem content
}
```

Use the stateful overload to show a toggle and animate between layouts:

```kotlin
val railState = rememberNavigationRailState()

NavigationRail(state = railState) {
    // NavigationRailItem(...)
}
```

Use `railState.expand()`, `railState.collapse()` or `railState.toggle()` to control the layout
programmatically. Omit both `state` and `expanded` for a fixed collapsed rail.

## Properties

### NavigationRail Overloads

`NavigationRail` provides two overloads. Use one of them:

#### Fixed Layout

| Parameter | Type | Description | Default | Required |
| --------- | ---- | ----------- | ------- | -------- |
| expanded  | Boolean | Use the expanded layout | false | No |

#### Expandable Layout

| Parameter | Type | Description | Default | Required |
| --------- | ---- | ----------- | ------- | -------- |
| state | NavigationRailState | Controls the layout and displays the toggle | - | Yes |
| expandContentDescription | String | Toggle description when collapsed | NavigationRailDefaults.ExpandContentDescription | No |
| collapseContentDescription | String | Toggle description when expanded | NavigationRailDefaults.CollapseContentDescription | No |

### Common NavigationRail Parameters

| Property Name              | Type                                   | Description                                   | Default Value                     | Required |
| -------------------------- | -------------------------------------- | --------------------------------------------- | --------------------------------- | -------- |
| modifier                   | Modifier                               | Modifier applied to the rail                  | Modifier                          | No       |
| header                     | @Composable (ColumnScope.() -> Unit)?  | Header content (e.g. FAB or Logo)             | null                              | No       |
| color                      | Color                                  | Background color of the rail                  | MiuixTheme.colorScheme.surface    | No       |
| showDivider                | Boolean                                | Show divider line between rail and content    | true                              | No       |
| defaultWindowInsetsPadding | Boolean                                | Apply default window insets padding           | true                              | No       |
| minWidth                   | Dp                                     | Minimum (collapsed) width of the rail         | NavigationRailDefaults.MinWidth   | No       |
| expandedWidth              | Dp                                     | Width of the rail when expanded               | NavigationRailDefaults.ExpandedWidth | No    |
| scrollState                | ScrollState                            | Scroll state of the rail's content column    | rememberScrollState()             | No       |
| content                    | @Composable ColumnScope.()             | The content of the rail                       | -                                 | Yes      |

### NavigationRailItem Properties

| Property Name | Type        | Description                      | Default Value | Required |
| ------------- | ----------- | -------------------------------- | ------------- | -------- |
| selected      | Boolean     | Whether the item is selected     | -             | Yes      |
| onClick       | () -> Unit  | Callback when the item is clicked| -             | Yes      |
| icon          | ImageVector | Icon of the item                 | -             | Yes      |
| label         | String      | Label of the item                | -             | Yes      |
| modifier      | Modifier    | Modifier applied to the item     | Modifier      | No       |
| enabled       | Boolean     | Whether the item is enabled      | true          | No       |
| badge         | (@Composable () -> Unit)? | Optional badge shown on the item's icon, e.g. a `Badge` | null | No |

### NavigationRailDefaults Object

The NavigationRailDefaults object provides default values for NavigationRail and NavigationRailItem components.

#### Constants

| Constant Name                      | Type     | Description                                | Default Value |
| ---------------------------------- | -------- | ------------------------------------------ | ------------- |
| MinWidth                           | Dp       | Minimum (collapsed) width of the rail      | 80.dp         |
| ExpandedWidth                      | Dp       | Width of the rail when expanded            | 240.dp        |
| VerticalPadding                    | Dp       | Vertical padding of the content            | 24.dp         |
| HeaderSpacing                      | Dp       | Spacing after the header                   | 24.dp         |
| IconSize                           | Dp       | Icon size                                  | 28.dp         |
| IconTextSpacing                    | Dp       | Spacing between icon and text              | 4.dp          |
| ItemVerticalPadding                | Dp       | Vertical padding for each item             | 12.dp         |
| LabelFontSize                      | TextUnit | Label font size                            | 12.sp         |
| ExpandedLabelFontSize              | TextUnit | Label font size when expanded              | 16.sp         |
| ExpandedItemHorizontalMargin       | Dp       | Margin between an expanded item and edges  | 12.dp         |
| ExpandedItemCornerRadius           | Dp       | Corner radius of the selected pill         | 16.dp         |
| CollapsedIndicatorVerticalPadding  | Dp       | Padding of a collapsed item's indicator around its icon | 4.dp |
| ExpandedItemContentHorizontalPadding | Dp     | Horizontal padding inside an expanded item | 14.dp         |
| ExpandedItemContentVerticalPadding | Dp       | Vertical padding inside an expanded item   | 14.dp         |
| ExpandedItemIconTextSpacing        | Dp       | Spacing between icon and label (expanded)  | 16.dp         |
| ExpandContentDescription           | String   | Toggle description while collapsed         | "Expand navigation rail" |
| CollapseContentDescription         | String   | Toggle description while expanded          | "Collapse navigation rail" |

### NavigationRailState

Create it with `rememberNavigationRailState(initialValue)` and pass it to `NavigationRail` to make the rail expandable.

| Member       | Type                | Description                                    |
| ------------ | ------------------- | ---------------------------------------------- |
| currentValue | NavigationRailValue | The current expansion value                    |
| isExpanded   | Boolean             | Whether the rail is currently expanded         |
| expand()     | fun                 | Expands the rail                               |
| collapse()   | fun                 | Collapses the rail                             |
| toggle()     | fun                 | Toggles between collapsed and expanded         |

### NavigationRailValue

| Value     | Description                                     |
| --------- | ----------------------------------------------- |
| Collapsed | The rail is collapsed to `MinWidth`.            |
| Expanded  | The rail is expanded to `ExpandedWidth`.        |
