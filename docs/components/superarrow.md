# SuperArrow

`SuperArrow` is a directional indicator component in Miuix, typically used for navigation or displaying additional content. It provides a title, summary, and right arrow icon with click interaction support, commonly used in settings, menu items, or list items.

<div style="position: relative; max-width: 700px; height: 280px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../compose/index.html?id=superArrow" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

## Import

```kotlin
import top.yukonga.miuix.kmp.extra.SuperArrow
```

## Basic Usage

The SuperArrow component provides basic click navigation functionality:

```kotlin
SuperArrow(
    title = "Setting Item",
    onClick = { /* Handle click event */ }
)
```

## Arrow with Summary

```kotlin
SuperArrow(
    title = "Wireless Network",
    summary = "Connected to WIFI-HOME",
    onClick = { /* Handle click event */ }
)
```

## Component States

### Disabled State

```kotlin
SuperArrow(
    title = "Disabled Item",
    summary = "This item is currently unavailable",
    enabled = false,
    onClick = { /* Won't be triggered */ }
)
```

### Hold Down State

SuperArrow supports controlling the hold-down state through the `holdDownState` parameter, typically used for visual feedback when displaying popup dialogs:

```kotlin
val showDialog = remember { mutableStateOf(false) }

Scaffold {
    SuperArrow(
        title = "Open Dialog",
        summary = "Click to show dialog",
        onClick = { showDialog.value = true },
        holdDownState = showDialog.value
    )
    // Define dialog elsewhere
    SuperDialog(
        title = "Dialog",
        show = showDialog,
        onDismissRequest = { showDialog.value = false }
    ) {
        // Dialog content
    }
}
```

## Properties

### SuperArrow Properties

| Property Name    | Type                           | Description                          | Default Value                       | Required |
| ---------------- | ------------------------------ | ------------------------------------ | ----------------------------------- | -------- |
| title            | String                         | Arrow item title                     | -                                   | Yes      |
| titleColor       | BasicComponentColors           | Title text color configuration       | BasicComponentDefaults.titleColor() | No       |
| summary          | String?                        | Arrow item summary description       | null                                | No       |
| summaryColor     | BasicComponentColors           | Summary text color configuration     | BasicComponentDefaults.summaryColor() | No     |
| leftAction       | @Composable (() -> Unit)?      | Custom left content                  | null                                | No       |
| rightActions     | @Composable RowScope.() -> Unit | Custom right-side content (slot)     | {}                                  | No       |
| bottomAction     | @Composable (() -> Unit)?       | Custom bottom content                | null                                | No       |
| modifier         | Modifier                       | Modifier applied to component        | Modifier                            | No       |
| insideMargin     | PaddingValues                  | Internal content padding             | BasicComponentDefaults.InsideMargin | No       |
| onClick          | (() -> Unit)?                  | Callback triggered on click          | null                                | No       |
| holdDownState    | Boolean                        | Whether component is held down       | false                               | No       |
| enabled          | Boolean                        | Whether component is interactive     | true                                | No       |

### SuperArrowDefaults Object

The SuperArrowDefaults object provides default color configuration for the trailing arrow icon.

#### Methods

| Method Name       | Type              | Description                                        |
| ----------------- | ----------------- | -------------------------------------------------- |
| rightActionColors | RightActionColors | Returns tint colors used by the trailing arrow icon |

### Arrow Tint

- The trailing arrow icon is always shown and tinted automatically.
- Tint uses `MiuixTheme.colorScheme.onSurfaceVariantActions` when `enabled = true`.
- Tint uses `MiuixTheme.colorScheme.disabledOnSecondaryVariant` when `enabled = false`.

## Advanced Usage

### With Left Icon

```kotlin
SuperArrow(
    title = "Personal Information",
    summary = "View and edit your profile",
    leftAction = {
        Icon(
            imageVector = MiuixIcons.Useful.Personal,
            contentDescription = "Personal Icon",
            tint = MiuixTheme.colorScheme.onBackground,
            modifier = Modifier.padding(end = 16.dp)
        )
    },
    onClick = { /* Handle click event */ }
)
```

### With Right Actions (Text)

```kotlin
SuperArrow(
    title = "Storage Space",
    summary = "Manage app storage space",
    rightActions = {
        Text("12.5 GB")
    },
    onClick = { /* Handle click event */ }
)
```

### Using with Dialog

```kotlin
val showDialog = remember { mutableStateOf(false) }
var language by remember { mutableStateOf("Simplified Chinese") }

Scaffold {
SuperArrow(
    title = "Language Settings",
    summary = "Select app display language",
    rightActions = {
        Text(language)
    },
    onClick = { showDialog.value = true },
    holdDownState = showDialog.value
)
    SuperDialog(
        title = "Select Language",
        show = showDialog,
        onDismissRequest = { showDialog.value = false }
    ) {
        // Dialog content
        Card {
            SuperArrow(
                title = "Simplified Chinese",
                onClick = {
                    language = "Simplified Chinese"
                    showDialog.value = false
                }
            )
            SuperArrow(
                title = "English",
                onClick = {
                    language = "English"
                    showDialog.value = false
                }
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextButton(
                text = "Cancel",
                onClick = { showDialog.value = false },
                modifier = Modifier.weight(1f).padding(top = 8.dp)
            )
        }
    }
}
```
