# RadioButtonPreference

`RadioButtonPreference` is a radio button component in Miuix that provides a title, summary, and radio button control. It supports click interactions and is commonly used in single-select settings and selection lists.

<div style="position: relative; height: 293px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../compose/index.html?id=radioButtonPreference" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

## Import

```kotlin
import top.yukonga.miuix.kmp.preference.RadioButtonPreference
```

## Basic Usage

RadioButtonPreference is typically used within a mutually exclusive group:

```kotlin
var selectedIndex by remember { mutableIntStateOf(0) }

Card {
    RadioButtonPreference(
        title = "Option A",
        selected = selectedIndex == 0,
        onClick = { selectedIndex = 0 }
    )
    RadioButtonPreference(
        title = "Option B",
        selected = selectedIndex == 1,
        onClick = { selectedIndex = 1 }
    )
    RadioButtonPreference(
        title = "Option C",
        selected = selectedIndex == 2,
        onClick = { selectedIndex = 2 }
    )
}
```

## RadioButton with Summary

```kotlin
var selectedIndex by remember { mutableIntStateOf(0) }

RadioButtonPreference(
    title = "Option A",
    summary = "Description for option A",
    selected = selectedIndex == 0,
    onClick = { selectedIndex = 0 }
)
```

## Component States

### Disabled State

```kotlin
RadioButtonPreference(
    title = "Disabled RadioButton",
    summary = "This radio button is currently unavailable",
    selected = true,
    onClick = {},
    enabled = false
)
```

## Properties

### RadioButtonPreference Properties

| Property Name     | Type                            | Description                             | Default Value                             | Required |
| ----------------- | ------------------------------- | --------------------------------------- | ----------------------------------------- | -------- |
| title             | String                          | Title of the radio button item          | -                                         | Yes      |
| selected          | Boolean                         | Radio button selected state             | -                                         | Yes      |
| onClick           | (() -> Unit)?                   | Callback when radio button is clicked   | -                                         | Yes      |
| modifier          | Modifier                        | Modifier applied to component           | Modifier                                  | No       |
| titleColor        | BasicComponentColors            | Title text color configuration          | BasicComponentDefaults.titleColor()       | No       |
| summary           | String?                         | Summary description                     | null                                      | No       |
| summaryColor      | BasicComponentColors            | Summary text color configuration        | BasicComponentDefaults.summaryColor()     | No       |
| radioButtonColors | RadioButtonColors               | RadioButton control color configuration | RadioButtonDefaults.radioButtonColors()   | No       |
| endActions        | @Composable RowScope.() -> Unit | Custom end content                      | {}                                        | No       |
| bottomAction      | @Composable (() -> Unit)?       | Custom bottom content                   | null                                      | No       |
| holdDownState     | Boolean                         | Whether the component is held down      | false                                     | No       |
| insideMargin      | PaddingValues                   | Internal content padding                | BasicComponentDefaults.InsideMargin       | No       |
| enabled           | Boolean                         | Whether component is interactive        | true                                      | No       |

## Advanced Usage

### Custom Colors

```kotlin
var selected by remember { mutableStateOf(false) }

RadioButtonPreference(
    title = "Custom Colors",
    titleColor = BasicComponentDefaults.titleColor(
        color = MiuixTheme.colorScheme.primary
    ),
    summary = "RadioButton with custom colors",
    summaryColor = BasicComponentDefaults.summaryColor(
        color = MiuixTheme.colorScheme.secondary
    ),
    selected = selected,
    onClick = { selected = !selected },
    radioButtonColors = RadioButtonDefaults.radioButtonColors(
        selectedColor = Color.Red
    )
)
```

### Using with Dialog

```kotlin
var showDialog by remember { mutableStateOf(false) }
var selectedTheme by remember { mutableIntStateOf(0) }
val themes = listOf("Light", "Dark", "System")

Scaffold {
    ArrowPreference(
        title = "Theme Settings",
        onClick = { showDialog = true },
        holdDownState = showDialog
    )

    OverlayDialog(
        title = "Theme Settings",
        show = showDialog,
        onDismissRequest = { showDialog = false }
    ) {
        Card {
            themes.forEachIndexed { index, theme ->
                RadioButtonPreference(
                    title = theme,
                    selected = selectedTheme == index,
                    onClick = { selectedTheme = index }
                )
            }
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(top = 12.dp)
        ) {
            TextButton(
                text = "Cancel",
                onClick = { showDialog = false },
                modifier = Modifier.weight(1f)
            )
            Spacer(Modifier.width(16.dp))
            TextButton(
                text = "Confirm",
                onClick = { showDialog = false },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.textButtonColorsPrimary()
            )
        }
    }
}
```
