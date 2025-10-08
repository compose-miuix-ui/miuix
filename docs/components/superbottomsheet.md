# SuperBottomSheet

`SuperBottomSheet` is a bottom sheet component in Miuix that slides up from the bottom of the screen. The height automatically adapts to the content size, but will not cover the status bar area. Supports swipe-to-dismiss gestures and custom styling.

<div style="position: relative; max-width: 700px; height: 210px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../compose/index.html?id=superBottomSheet" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

::: warning
`SuperBottomSheet` must be used within a `Scaffold` component!
:::

## Import

```kotlin
import top.yukonga.miuix.kmp.extra.SuperBottomSheet
```

## Basic Usage

SuperBottomSheet component provides basic bottom sheet functionality:

```kotlin
var showBottomSheet = remember { mutableStateOf(false) }

Scaffold {
    TextButton(
        text = "Show Bottom Sheet",
        onClick = { showBottomSheet.value = true }
    )

    SuperBottomSheet(
        show = showBottomSheet,
        title = "Bottom Sheet Title",
        onDismissRequest = { showBottomSheet.value = false }
    ) {
        Text(text = "This is the content of the bottom sheet")
    }
}
```

## Properties

### SuperBottomSheet Properties

| Property Name              | Type                               | Description                                  | Default Value                               | Required |
| -------------------------- | ---------------------------------- | -------------------------------------------- | ------------------------------------------- | -------- |
| show                       | MutableState\<Boolean>             | State object to control bottom sheet visibility | -                                        | Yes      |
| modifier                   | Modifier                           | Modifier applied to the bottom sheet         | Modifier                                    | No       |
| title                      | String?                            | Bottom sheet title                           | null                                        | No       |
| leftAction                 | @Composable (() -> Unit?)?         | Optional composable for left action (e.g., close button) | null                           | No       |
| rightAction                | @Composable (() -> Unit?)?         | Optional composable for right action (e.g., submit button) | null                         | No       |
| backgroundColor            | Color                              | Bottom sheet background color                | SuperBottomSheetDefaults.backgroundColor()  | No       |
| enableWindowDim            | Boolean                            | Whether to enable dimming layer              | true                                        | No       |
| cornerRadius               | Dp                                 | Corner radius of the top corners             | SuperBottomSheetDefaults.cornerRadius       | No       |
| sheetMaxWidth              | Dp                                 | Maximum width of the bottom sheet            | SuperBottomSheetDefaults.sheetMaxWidth      | No       |
| onDismissRequest           | (() -> Unit)?                      | Callback when bottom sheet is dismissed      | null                                        | No       |
| outsideMargin              | DpSize                             | Bottom sheet external margin                 | SuperBottomSheetDefaults.outsideMargin      | No       |
| insideMargin               | DpSize                             | Bottom sheet internal content margin         | SuperBottomSheetDefaults.insideMargin       | No       |
| defaultWindowInsetsPadding | Boolean                            | Whether to apply default window insets padding | true                                      | No       |
| dragHandleColor            | Color                              | Drag indicator color                         | SuperBottomSheetDefaults.dragHandleColor()  | No       |
| content                    | @Composable () -> Unit             | Bottom sheet content                         | -                                           | Yes      |

### SuperBottomSheetDefaults Object

The SuperBottomSheetDefaults object provides default settings for the SuperBottomSheet component.

#### Properties

| Property Name  | Type   | Description                            |
| -------------- | ------ | -------------------------------------- |
| cornerRadius   | Dp     | Default corner radius (28.dp)          |
| sheetMaxWidth  | Dp     | Default maximum width (640.dp)         |
| outsideMargin  | DpSize | Default bottom sheet external margin   |
| insideMargin   | DpSize | Default bottom sheet internal margin   |

#### Functions

| Function Name     | Return Type | Description                     |
| ----------------- | ----------- | ------------------------------- |
| backgroundColor() | Color       | Get default background color    |
| dragHandleColor() | Color       | Get default drag indicator color |

## Advanced Usage

### Custom Styled Bottom Sheet

```kotlin
var showBottomSheet = remember { mutableStateOf(false) }

Scaffold {
    TextButton(
        text = "Show Custom Styled Bottom Sheet",
        onClick = { showBottomSheet.value = true }
    )

    SuperBottomSheet(
        show = showBottomSheet,
        title = "Custom Style",
        backgroundColor = MiuixTheme.colorScheme.surfaceVariant,
        dragHandleColor = MiuixTheme.colorScheme.primary,
        outsideMargin = DpSize(16.dp, 0.dp),
        insideMargin = DpSize(32.dp, 16.dp),
        onDismissRequest = { showBottomSheet.value = false }
    ) {
        Column {
            Text("Custom styled bottom sheet")
            Spacer(modifier = Modifier.height(16.dp))
            TextButton(
                text = "Close",
                onClick = { showBottomSheet.value = false },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
```

### Bottom Sheet with List Content

```kotlin
var showBottomSheet = remember { mutableStateOf(false) }
var selectedItem by remember { mutableStateOf("") }

Scaffold {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        TextButton(
            text = "Show Selection List",
            onClick = { showBottomSheet.value = true }
        )
        
        Text("Selected: $selectedItem")
    }

    SuperBottomSheet(
        show = showBottomSheet,
        title = "Select Item",
        onDismissRequest = { showBottomSheet.value = false }
    ) {
        LazyColumn {
            items(20) { index ->
                Text(
                    text = "Item ${index + 1}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            selectedItem = "Item ${index + 1}"
                            showBottomSheet.value = false
                        }
                        .padding(vertical = 12.dp)
                )
            }
        }
    }
}
```

### Without Dimming Layer

```kotlin
var showBottomSheet = remember { mutableStateOf(false) }

Scaffold {
    TextButton(
        text = "Show Bottom Sheet Without Dim",
        onClick = { showBottomSheet.value = true }
    )

    SuperBottomSheet(
        show = showBottomSheet,
        title = "No Dimming",
        enableWindowDim = false,
        onDismissRequest = { showBottomSheet.value = false }
    ) {
        Text("This bottom sheet has no background dimming layer")
        Spacer(modifier = Modifier.height(16.dp))
        TextButton(
            text = "Close",
            onClick = { showBottomSheet.value = false },
            modifier = Modifier.fillMaxWidth()
        )
    }
}
```

### Bottom Sheet with Action Buttons

```kotlin
var showBottomSheet = remember { mutableStateOf(false) }

Scaffold {
    TextButton(
        text = "Show Bottom Sheet with Actions",
        onClick = { showBottomSheet.value = true }
    )

    SuperBottomSheet(
        show = showBottomSheet,
        title = "Action Sheet",
        leftAction = {
            TextButton(
                text = "Cancel",
                onClick = { showBottomSheet.value = false }
            )
        },
        rightAction = {
            TextButton(
                text = "Confirm",
                onClick = { 
                    // Handle confirm action
                    showBottomSheet.value = false 
                },
                colors = ButtonDefaults.textButtonColorsPrimary()
            )
        },
        onDismissRequest = { showBottomSheet.value = false }
    ) {
        Text("Content with custom header actions")
        Spacer(modifier = Modifier.height(16.dp))
        Text("Left and right action buttons are displayed in the header")
    }
}
```

### Bottom Sheet with Form

```kotlin
var showBottomSheet = remember { mutableStateOf(false) }
var textFieldValue by remember { mutableStateOf("") }
var switchState by remember { mutableStateOf(false) }

Scaffold {
    TextButton(
        text = "Show Form Bottom Sheet",
        onClick = { showBottomSheet.value = true }
    )

    SuperBottomSheet(
        show = showBottomSheet,
        title = "Settings Form",
        onDismissRequest = { showBottomSheet.value = false }
    ) {
        Card(
            color = MiuixTheme.colorScheme.secondaryContainer,
        ) {
            TextField(
                modifier = Modifier.padding(vertical = 12.dp),
                value = textFieldValue,
                label = "Enter content",
                maxLines = 1,
                onValueChange = { textFieldValue = it }
            )
            
            SuperSwitch(
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
                onClick = { showBottomSheet.value = false },
                modifier = Modifier.weight(1f)
            )
            Spacer(Modifier.width(20.dp))
            TextButton(
                text = "Confirm",
                onClick = { showBottomSheet.value = false },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.textButtonColorsPrimary()
            )
        }
    }
}
```

### Adaptive Content Height

```kotlin
var showBottomSheet = remember { mutableStateOf(false) }

Scaffold {
    TextButton(
        text = "Show Adaptive Height Bottom Sheet",
        onClick = { showBottomSheet.value = true }
    )

    SuperBottomSheet(
        show = showBottomSheet,
        title = "Adaptive Height",
        onDismissRequest = { showBottomSheet.value = false }
    ) {
        Column(modifier = Modifier.padding(vertical = 16.dp)) {
            Text("The height adapts to content")
            Spacer(modifier = Modifier.height(16.dp))
            Text("Add as much content as needed")
            Spacer(modifier = Modifier.height(16.dp))
            Text("But will not cover the status bar area")
            Spacer(modifier = Modifier.height(16.dp))
            TextButton(
                text = "Close",
                onClick = { showBottomSheet.value = false },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
```
