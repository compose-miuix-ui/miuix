# NavigationBar

`NavigationBar` is a bottom navigation bar component in Miuix, used to create navigation menus fixed at the bottom of applications. It supports 2 to 5 navigation items, offering different display modes (icon only, text only, icon and text, icon with selected label).

`FloatingNavigationBar` is a floating-style bottom navigation bar component, also supporting 2 to 5 navigation items, offering different display modes.

These components are typically used in conjunction with the `Scaffold` component to maintain consistent layout and behavior across different pages in the application.

<div style="position: relative; height: 300px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../compose/index.html?id=navigationBar" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

## Import

```kotlin
import top.yukonga.miuix.kmp.basic.NavigationBar
import top.yukonga.miuix.kmp.basic.NavigationBarItem
import top.yukonga.miuix.kmp.basic.FloatingNavigationBar
import top.yukonga.miuix.kmp.basic.FloatingNavigationBarItem
import top.yukonga.miuix.kmp.basic.NavigationBarDisplayMode
import top.yukonga.miuix.kmp.basic.FloatingNavigationBarDisplayMode
```

## Basic Usage

### NavigationBar

The NavigationBar component can be used to create bottom navigation menus fixed to the bottom:

```kotlin
var selectedIndex by remember { mutableStateOf(0) }
val items = listOf("Home", "Profile", "Settings")
val icons = listOf(MiuixIcons.VerticalSplit, MiuixIcons.Contacts, MiuixIcons.Settings)

Scaffold(
    bottomBar = {
        NavigationBar {
            items.forEachIndexed { index, label ->
                NavigationBarItem(
                    selected = selectedIndex == index,
                    onClick = { selectedIndex = index },
                    icon = icons[index],
                    label = label
                )
            }
        }
    }
)
```

### FloatingNavigationBar

The FloatingNavigationBar component can be used to create floating navigation menus at the bottom:

```kotlin
var selectedIndex by remember { mutableStateOf(0) }
val items = listOf("Home", "Profile", "Settings")
val icons = listOf(MiuixIcons.VerticalSplit, MiuixIcons.Contacts, MiuixIcons.Settings)

Scaffold(
    bottomBar = {
        FloatingNavigationBar(
            mode = FloatingNavigationBarDisplayMode.IconOnly // Show icons only
        ) {
            items.forEachIndexed { index, label ->
                FloatingNavigationBarItem(
                    selected = selectedIndex == index,
                    onClick = { selectedIndex = index },
                    icon = icons[index],
                    label = label
                )
            }
        }
    }
)
```

## Component States

### Selected State

Both `NavigationBarItem` and `FloatingNavigationBarItem` automatically handle the visual style of the selected item, displaying it with bold text and highlighting the icon/text.

## Properties

### NavigationBar Properties

| Property Name              | Type                     | Description                         | Default Value                         | Required |
| -------------------------- | ------------------------ | ----------------------------------- | ------------------------------------- | -------- |
| modifier                   | Modifier                 | Modifier applied to the nav bar     | Modifier                              | No       |
| color                      | Color                    | Background color of the nav bar     | MiuixTheme.colorScheme.surface        | No       |
| showDivider                | Boolean                  | Show top divider line or not        | true                                  | No       |
| defaultWindowInsetsPadding | Boolean                  | Apply default window insets padding | true                                  | No       |
| mode                       | NavigationBarDisplayMode    | Display mode for items              | NavigationBarDisplayMode.IconAndText     | No       |
| content                    | @Composable RowScope.()  | The content of the nav bar          | -                                     | Yes      |

### NavigationBarItem Properties

| Property Name | Type          | Description                      | Default Value | Required |
| ------------- | ------------- | -------------------------------- | ------------- | -------- |
| selected      | Boolean       | Whether the item is selected     | -             | Yes      |
| onClick       | () -> Unit    | Callback when the item is clicked| -             | Yes      |
| icon          | ImageVector   | Icon of the item                 | -             | Yes      |
| label         | String        | Label of the item                | -             | Yes      |
| modifier      | Modifier      | Modifier applied to the item     | Modifier      | No       |
| enabled       | Boolean       | Whether the item is enabled      | true          | No       |

### FloatingNavigationBar Properties

| Property Name              | Type                     | Description                             | Default Value                           | Required |
| -------------------------- | ------------------------ | --------------------------------------- | --------------------------------------- | -------- |
| modifier                   | Modifier                 | Modifier applied to the nav bar         | Modifier                                | No       |
| color                      | Color                    | Background color of the nav bar         | MiuixTheme.colorScheme.surfaceContainer | No       |
| cornerRadius               | Dp                       | Corner radius of the nav bar            | FloatingToolbarDefaults.CornerRadius    | No       |
| horizontalAlignment        | Alignment.Horizontal     | Horizontal alignment within its parent  | CenterHorizontally                      | No       |
| horizontalOutSidePadding   | Dp                       | Horizontal padding outside the nav bar  | FloatingNavigationBarDefaults.HorizontalOutSidePadding | No       |
| shadowElevation            | Dp                       | The shadow elevation of the nav bar     | FloatingNavigationBarDefaults.ShadowElevation          | No       |
| showDivider                | Boolean                  | Show divider line around the nav bar    | false                                   | No       |
| defaultWindowInsetsPadding | Boolean                  | Apply default window insets padding     | true                                    | No       |
| mode                       | FloatingNavigationBarDisplayMode | Display mode for items (icon/text/both) | FloatingNavigationBarDisplayMode.IconOnly  | No       |
| content                    | @Composable RowScope.()  | The content of the nav bar              | -                                       | Yes      |

### FloatingNavigationBarItem Properties

| Property Name | Type          | Description                      | Default Value | Required |
| ------------- | ------------- | -------------------------------- | ------------- | -------- |
| selected      | Boolean       | Whether the item is selected     | -             | Yes      |
| onClick       | () -> Unit    | Callback when the item is clicked| -             | Yes      |
| icon          | ImageVector   | Icon of the item                 | -             | Yes      |
| label         | String        | Label of the item                | -             | Yes      |
| modifier      | Modifier      | Modifier applied to the item     | Modifier      | No       |
| enabled       | Boolean       | Whether the item is enabled      | true          | No       |

### NavigationBarDefaults Object

The NavigationBarDefaults object provides default values for NavigationBar and NavigationBarItem components.

#### Constants

| Constant Name        | Type  | Description                                 | Default Value |
| -------------------- | ----- | ------------------------------------------- | ------------- |
| ItemHeight           | Dp    | Item height on non-iOS platforms             | 64.dp         |
| ItemHeightIOS        | Dp    | Item height on iOS                           | 48.dp         |
| IconSize             | Dp    | Icon size                                    | 26.dp         |
| LabelFontSize        | TextUnit | Label font size                           | 12.sp         |
| IconTopPadding       | Dp    | Top padding for the icon                     | 8.dp          |
| BottomPadding        | Dp    | Bottom padding for the label                 | 8.dp          |
| SelectedPressedAlpha | Float | Alpha value for selected item when pressed   | 0.5f          |
| UnselectedPressedAlpha | Float | Alpha value for unselected item when pressed | 0.6f        |
| UnselectedAlpha      | Float | Alpha value for unselected item              | 0.4f          |

### FloatingNavigationBarDefaults Object

The FloatingNavigationBarDefaults object provides default values for FloatingNavigationBar and FloatingNavigationBarItem components.

#### Constants

| Constant Name          | Type     | Description                         | Default Value |
| ---------------------- | -------- | ----------------------------------- | ------------- |
| HorizontalOutSidePadding | Dp    | Horizontal outside padding          | 36.dp         |
| ShadowElevation        | Dp       | Shadow elevation                    | 1.dp          |
| HorizontalPadding      | Dp       | Horizontal padding inside the bar   | 12.dp         |
| ItemSpacing            | Dp       | Spacing between items               | 12.dp         |
| IconSize               | Dp       | Icon size (IconAndText mode)        | 24.dp         |
| LabelFontSize          | TextUnit | Label font size (IconAndText mode)  | 12.sp         |
| VerticalPadding        | Dp       | Vertical padding (IconAndText mode) | 6.dp          |
| TextVerticalPadding    | Dp       | Vertical padding (TextOnly mode)    | 16.dp         |
| TextHorizontalPadding  | Dp       | Horizontal padding (TextOnly mode)  | 2.dp          |
| TextFontSize           | TextUnit | Font size (TextOnly mode)           | 14.sp         |
| IconOnlySize           | Dp       | Icon size (IconOnly mode)           | 28.dp         |
| IconOnlyPadding        | Dp       | Padding (IconOnly mode)             | 10.dp         |
| SelectedPressedAlpha   | Float    | Alpha for selected pressed item     | 0.5f          |
| UnselectedPressedAlpha | Float    | Alpha for unselected pressed item   | 0.6f          |
| UnselectedAlpha        | Float    | Alpha for unselected item           | 0.4f          |

### NavigationBarDisplayMode Enum

| Value                 | Description                                  |
| --------------------- | -------------------------------------------- |
| IconAndText           | Show both icon and text                      |
| IconOnly              | Show icon only                               |
| TextOnly              | Show text only                               |
| IconWithSelectedLabel | Show icon always, show text only when selected|

### FloatingNavigationBarDisplayMode Enum

| Value       | Description             |
| ----------- | ----------------------- |
| IconAndText | Show both icon and text |
| IconOnly    | Show icon only          |
| TextOnly    | Show text only          |

## Advanced Usage

### NavigationBar

#### Custom Colors

```kotlin
NavigationBar(
    color = Color.Red.copy(alpha = 0.3f)
) {
    // ... items ...
}
```

#### Without Divider

```kotlin
NavigationBar(
    showDivider = false
) {
    // ... items ...
}
```

#### Handling Window Insets

```kotlin
NavigationBar(
    defaultWindowInsetsPadding = false // Handle window insets padding manually
) {
    // ... items ...
}
```

### FloatingNavigationBar

#### Custom Mode (Icon and Text)

```kotlin
FloatingNavigationBar(
    mode = FloatingNavigationBarDisplayMode.IconAndText
) {
    // ... items ...
}
```

#### Custom Mode (Text Only)

```kotlin
FloatingNavigationBar(
    mode = FloatingNavigationBarDisplayMode.TextOnly
) {
    // ... items ...
}
```

#### Custom Color and Corner Radius

```kotlin
FloatingNavigationBar(
    color = MiuixTheme.colorScheme.primaryContainer,
    cornerRadius = 28.dp,
    mode = FloatingNavigationBarDisplayMode.IconAndText
) {
    // ... items ...
}
```

#### Custom Alignment and Padding

```kotlin
FloatingNavigationBar(
    horizontalAlignment = Alignment.Start, // Align to start
    horizontalOutSidePadding = 16.dp, // Set outside padding
    mode = FloatingNavigationBarDisplayMode.IconOnly
) {
    // ... items ...
}
```

### Using with Page Navigation (Using Scaffold)

#### Using NavigationBar

```kotlin
val pages = listOf("Home", "Profile", "Settings")
val icons = listOf(MiuixIcons.VerticalSplit, MiuixIcons.Contacts, MiuixIcons.Settings)
var selectedIndex by remember { mutableStateOf(0) }

Scaffold(
    bottomBar = {
        NavigationBar {
            pages.forEachIndexed { index, label ->
                NavigationBarItem(
                    selected = selectedIndex == index,
                    onClick = { selectedIndex = index },
                    icon = icons[index],
                    label = label
                )
            }
        }
    }
) { paddingValues ->
    // Content area needs to consider padding
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Current Page: ${pages[selectedIndex]}",
            style = MiuixTheme.textStyles.title1
        )
    }
}
```

#### Using FloatingNavigationBar

```kotlin
val pages = listOf("Home", "Profile", "Settings")
val icons = listOf(MiuixIcons.VerticalSplit, MiuixIcons.Contacts, MiuixIcons.Settings)
var selectedIndex by remember { mutableStateOf(0) }

Scaffold(
    bottomBar = {
        FloatingNavigationBar(
            mode = FloatingNavigationBarDisplayMode.IconOnly // Show icons only
        ) {
            pages.forEachIndexed { index, label ->
                FloatingNavigationBarItem(
                    selected = selectedIndex == index,
                    onClick = { selectedIndex = index },
                    icon = icons[index],
                    label = label
                )
            }
        }
    }
) { paddingValues ->
    // Content area needs to consider padding
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Current Page: ${pages[selectedIndex]}",
            style = MiuixTheme.textStyles.title1
        )
    }
}
```
