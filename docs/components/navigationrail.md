# NavigationRail

`NavigationRail` is a side navigation component in Miuix, suitable for wide screens. It offers different display modes (icon only, text only, icon and text, icon with selected label).

<div style="position: relative; height: 300px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../compose/index.html?id=navigationRail" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

## Import

```kotlin
import top.yukonga.miuix.kmp.basic.NavigationRail
import top.yukonga.miuix.kmp.basic.NavigationRailItem
import top.yukonga.miuix.kmp.basic.NavigationRailDisplayMode
```

## Basic Usage

The NavigationRail component can be used to create side navigation menus:

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

## Component States

### Selected State

`NavigationRailItem` automatically handles the visual style of the selected item, displaying it with bold text and highlighting the icon/text.

## Properties

### NavigationRail Properties

| Property Name              | Type                                   | Description                                   | Default Value                     | Required |
| -------------------------- | -------------------------------------- | --------------------------------------------- | --------------------------------- | -------- |
| modifier                   | Modifier                               | Modifier applied to the rail                  | Modifier                          | No       |
| header                     | @Composable (ColumnScope.() -> Unit)?  | Header content (e.g. FAB or Logo)             | null                              | No       |
| color                      | Color                                  | Background color of the rail                  | MiuixTheme.colorScheme.surface    | No       |
| showDivider                | Boolean                                | Show divider line between rail and content    | true                              | No       |
| defaultWindowInsetsPadding | Boolean                                | Apply default window insets padding           | true                              | No       |
| minWidth                   | Dp                                     | Minimum width of the rail                     | NavigationRailDefaults.MinWidth   | No       |
| mode                       | NavigationRailDisplayMode                  | Display mode for items                        | NavigationRailDisplayMode.IconAndText | No       |
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

### NavigationRailDefaults Object

The NavigationRailDefaults object provides default values for NavigationRail and NavigationRailItem components.

#### Constants

| Constant Name          | Type     | Description                                | Default Value |
| ---------------------- | -------- | ------------------------------------------ | ------------- |
| MinWidth               | Dp       | Minimum width of the rail                  | 80.dp         |
| VerticalPadding        | Dp       | Vertical padding of the content            | 24.dp         |
| HeaderSpacing          | Dp       | Spacing after the header                   | 24.dp         |
| IconSize               | Dp       | Icon size                                  | 28.dp         |
| IconTextSpacing        | Dp       | Spacing between icon and text              | 4.dp          |
| ItemVerticalPadding    | Dp       | Vertical padding for each item             | 12.dp         |
| LabelFontSize          | TextUnit | Label font size                            | 12.sp         |
| TextOnlyFontSize       | TextUnit | Font size in TextOnly mode                 | 14.sp         |
| TextOnlyVerticalPadding| Dp       | Vertical padding in TextOnly mode          | 4.dp          |
| SelectedPressedAlpha   | Float    | Alpha for selected pressed item            | 0.5f          |
| UnselectedPressedAlpha | Float    | Alpha for unselected pressed item          | 0.6f          |
| UnselectedAlpha        | Float    | Alpha for unselected item                  | 0.4f          |

### NavigationRailDisplayMode

| Value                 | Description                                       |
| --------------------- | ------------------------------------------------- |
| IconAndText           | Display both icon and text.                       |
| IconOnly              | Display only icon.                                |
| TextOnly              | Display only text.                                |
| IconWithSelectedLabel | Display icon always, and text only when selected. |
