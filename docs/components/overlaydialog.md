---
title: OverlayDialog
requiresScaffoldHost: true
prerequisites:
  - Must be used within `Scaffold` to provide `MiuixPopupHost`
  - Using outside `Scaffold` will cause popup content not to render
  - Multiple nested or side-by-side `Scaffold`s are supported without extra configuration
hostComponent: Scaffold
popupHost: MiuixPopupHost
---

# OverlayDialog

`OverlayDialog` is a dialog component in Miuix used to display important information, collect user input, or confirm user actions. The dialog appears above the current interface and supports custom styles and content layouts.

<div style="position: relative; height: 240px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../compose/index.html?id=overlayDialog" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

::: danger Prerequisite
This component depends on `Scaffold` providing `MiuixPopupHost` to render popup content. It must be used within `Scaffold`, otherwise popup content will not render correctly.
:::

## Import

```kotlin
import top.yukonga.miuix.kmp.overlay.OverlayDialog
```

## Basic Usage

OverlayDialog component provides basic dialog functionality:

```kotlin
var showDialog by remember { mutableStateOf(false) }

Scaffold {
    TextButton(
        text = "Show Dialog",
        onClick = { showDialog = true }
    )

    OverlayDialog(
        title = "Dialog Title",
        summary = "This is a basic dialog example that can contain various content.",
        show = showDialog,
        onDismissRequest = { showDialog = false } // Close dialog
    ) {
        TextButton(
            text = "Confirm",
            onClick = { showDialog = false }, // Close dialog
            modifier = Modifier.fillMaxWidth()
        )
    }
}
```

## Properties

### OverlayDialog Properties

| Property Name              | Type                   | Description                                                   | Default Value                         | Required |
| -------------------------- | ---------------------- | ------------------------------------------------------------- | ------------------------------------- | -------- |
| show                       | Boolean                | Whether to show the dialog                                    | -                                     | Yes      |
| modifier                   | Modifier               | Modifier applied to the dialog                                | Modifier                              | No       |
| title                      | String?                | Dialog title                                                  | null                                  | No       |
| titleColor                 | Color                  | Title text color                                              | DialogDefaults.titleColor()      | No       |
| summary                    | String?                | Dialog summary text                                           | null                                  | No       |
| summaryColor               | Color                  | Summary text color                                            | DialogDefaults.summaryColor()    | No       |
| backgroundColor            | Color                  | Dialog background color                                       | DialogDefaults.backgroundColor() | No       |
| enableWindowDim            | Boolean                | Whether to enable dimming layer                               | true                                  | No       |
| onDismissRequest           | (() -> Unit)?          | Called when the user requests dismissal (outside tap or back) | null                                  | No       |
| onDismissFinished          | (() -> Unit)?          | Callback after dialog fully dismisses                         | null                                  | No       |
| outsideMargin              | DpSize                 | Dialog external margin                                        | DialogDefaults.outsideMargin     | No       |
| insideMargin               | DpSize                 | Dialog internal content margin                                | DialogDefaults.insideMargin      | No       |
| defaultWindowInsetsPadding | Boolean                | Whether to apply default window insets padding                | true                                  | No       |
| renderInRootScaffold       | Boolean                | Whether to render the dialog in the root (outermost) Scaffold. When true, the dialog covers the full screen. When false, it renders within the current Scaffold's bounds | true | No |
| content                    | @Composable () -> Unit | Dialog content                                                | -                                     | Yes      |

### DialogDefaults Object

The DialogDefaults object provides default settings for the OverlayDialog component.

#### Properties

| Property Name | Type   | Description                    |
| ------------- | ------ | ------------------------------ |
| outsideMargin | DpSize | Default dialog external margin |
| insideMargin  | DpSize | Default dialog internal margin |

#### Functions

| Function Name     | Return Type | Description                         |
| ----------------- | ----------- | ----------------------------------- |
| titleColor()      | Color       | Get default title color             |
| summaryColor()    | Color       | Get default summary color           |
| backgroundColor() | Color       | Get default dialog background color |

## Advanced Usage

### Custom Styled Dialog

```kotlin
var showDialog by remember { mutableStateOf(false) }

Scaffold {
    TextButton(
        text = "Show Custom Styled Dialog",
        onClick = { showDialog = true }
    )

    OverlayDialog(
        title = "Custom Style",
        summary = "This dialog uses custom colors and margins",
        show = showDialog,
        onDismissRequest = { showDialog = false }, // Close dialog
        titleColor = Color.Blue,
        summaryColor = Color.Gray,
        backgroundColor = Color(0xFFF5F5F5),
        outsideMargin = DpSize(20.dp, 20.dp),
        insideMargin = DpSize(30.dp, 30.dp)
    ) {
        Text(
            text = "Custom Content Area",
            modifier = Modifier.padding(vertical = 16.dp)
        )
        
        TextButton(
            text = "Close",
            onClick = { showDialog = false }, // Close dialog
            modifier = Modifier.fillMaxWidth()
        )
    }
}
```

### Creating a Confirmation Dialog

```kotlin
var showConfirmDialog by remember { mutableStateOf(false) }
var result by remember { mutableStateOf("") }

Scaffold {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        TextButton(
            text = "Show Confirmation Dialog",
            onClick = { showConfirmDialog = true }
        )
        
        Text("Result: $result")
    }
    
    OverlayDialog(
        title = "Confirm Action",
        summary = "This action is irreversible, do you want to proceed?",
        show = showConfirmDialog,
        onDismissRequest = { showConfirmDialog = false } // Close dialog
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextButton(
                text = "Cancel",
                onClick = { 
                    result = "User cancelled the action"
                    showConfirmDialog = false // Close dialog
                },
                modifier = Modifier.weight(1f)
            )
            Spacer(Modifier.width(20.dp))
            TextButton(
                text = "Confirm",
                onClick = { 
                    result = "User confirmed the action"
                    showConfirmDialog = false // Close dialog 
                },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.textButtonColorsPrimary()
            )
        }
    }
}
```

### Dialog with Input Field

```kotlin
var showDialog by remember { mutableStateOf(false) }
var textFieldValue by remember { mutableStateOf("") }

Scaffold {
    TextButton(
        text = "Show Input Dialog",
        onClick = { showDialog = true }
    )

    OverlayDialog(
        title = "Please Enter Content",
        show = showDialog,
        onDismissRequest = { showDialog = false } // Close dialog
    ) {
        TextField(
            modifier = Modifier.padding(bottom = 16.dp),
            value = textFieldValue,
            maxLines = 1,
            onValueChange = { textFieldValue = it }
        )
        
        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextButton(
                text = "Cancel",
                onClick = { showDialog = false }, // Close dialog
                modifier = Modifier.weight(1f)
            )
            Spacer(Modifier.width(20.dp))
            TextButton(
                text = "Confirm",
                onClick = { showDialog = false }, // Close dialog
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.textButtonColorsPrimary() // Use theme color
            )
        }
    }
}
```

### Dialog with Form

```kotlin
var showDialog by remember { mutableStateOf(false) }
var dropdownSelectedOption by remember { mutableStateOf(0) }
var switchState by remember { mutableStateOf(false) }
val dropdownOptions = listOf("Option 1", "Option 2")

Scaffold {
    TextButton(
        text = "Show Form Dialog",
        onClick = { showDialog = true }
    )

    OverlayDialog(
        title = "Form Dialog",
        show = showDialog,
        onDismissRequest = { showDialog = false } // Close dialog
    ) {
        Card(
            colors = CardDefaults.defaultColors(
                color = MiuixTheme.colorScheme.secondaryContainer,
            ),
        ) {
            OverlayDropdownPreference(
                title = "Dropdown Selection",
                items = dropdownOptions,
                selectedIndex = dropdownSelectedOption,
                onSelectedIndexChange = { dropdownSelectedOption = it }
            )
            
            SwitchPreference(
                title = "Switch Option",
                checked = switchState,
                onCheckedChange = { switchState = it }
            )
        }
        
        Spacer(Modifier.height(12.dp))
        
        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextButton(
                text = "Cancel",
                onClick = { showDialog = false }, // Close dialog
                modifier = Modifier.weight(1f)
            )
            Spacer(Modifier.width(20.dp))
            TextButton(
                text = "Confirm",
                onClick = { showDialog = false }, // Close dialog
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.textButtonColorsPrimary() // Use theme color
            )
        }
    }
}
```

### Dialog with Color Picker

```kotlin
var showColorDialog by remember { mutableStateOf(false) }
var selectedColor by remember { mutableStateOf(Color.Red) }

Scaffold {
    TextButton(
        text = "Select Color",
        onClick = { showColorDialog = true }
    )
    
    OverlayDialog(
        title = "Select Color",
        show = showColorDialog,
        onDismissRequest = { showColorDialog = false } // Close dialog
    ) {
        Column {
            ColorPicker(
                initialColor = selectedColor,
                onColorChanged = { selectedColor = it }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                TextButton(
                    modifier = Modifier.weight(1f),
                    text = "Cancel",
                    onClick = { showColorDialog = false } // Close dialog
                )
                TextButton(
                    modifier = Modifier.weight(1f),
                    text = "Confirm",
                    colors = ButtonDefaults.textButtonColorsPrimary(), // Use theme color
                    onClick = {
                        showColorDialog = false // Close dialog
                        // Handle confirm logic
                    }
                )
            }
        }
    }
}
```
