---
title: OverlayBottomSheet
requiresScaffoldHost: true
prerequisites:
  - Must be used within `Scaffold` to provide `MiuixPopupHost`
  - Using outside `Scaffold` will cause popup content not to render
  - Multiple nested or side-by-side `Scaffold`s are supported without extra configuration
hostComponent: Scaffold
popupHost: MiuixPopupHost
---

# OverlayBottomSheet

`OverlayBottomSheet` is a bottom sheet component in Miuix that slides up from the bottom of the screen. Supports swipe-to-dismiss gestures and custom styling.

<div style="position: relative; height: 240px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../compose/index.html?id=overlayBottomSheet" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

::: danger Prerequisite
This component depends on `Scaffold` providing `MiuixPopupHost` to render popup content. It must be used within `Scaffold`, otherwise popup content will not render correctly.
:::

## Import

```kotlin
import top.yukonga.miuix.kmp.overlay.OverlayBottomSheet
import top.yukonga.miuix.kmp.theme.LocalDismissState
```

## Basic Usage

OverlayBottomSheet component provides basic bottom sheet functionality:

```kotlin
var showBottomSheet by remember { mutableStateOf(false) }

Scaffold {
    TextButton(
        text = "Show Bottom Sheet",
        onClick = { showBottomSheet = true }
    )

    OverlayBottomSheet(
        show = showBottomSheet,
        title = "Bottom Sheet Title",
        onDismissRequest = { showBottomSheet = false }
    ) {
        Text(text = "This is the content of the bottom sheet")
    }
}
```

## Properties

### OverlayBottomSheet Properties

| Property Name              | Type                      | Description                                                    | Default Value                              | Required |
| -------------------------- | ------------------------- |----------------------------------------------------------------| ------------------------------------------ |----------|
| show                       | Boolean                   | Whether to show the bottom sheet                               | -                                          | Yes      |
| modifier                   | Modifier                  | Modifier applied to the bottom sheet                           | Modifier                                   | No       |
| title                      | String?                   | Bottom sheet title                                             | null                                       | No       |
| startAction                | @Composable (() -> Unit)? | Optional composable for start action (e.g., close button)      | null                                       | No       |
| endAction                  | @Composable (() -> Unit)? | Optional composable for end action (e.g., submit button)       | null                                       | No       |
| backgroundColor            | Color                     | Bottom sheet background color                                  | BottomSheetDefaults.backgroundColor() | No       |
| enableWindowDim            | Boolean                   | Whether to enable dimming layer                                | true                                       | No       |
| cornerRadius               | Dp                        | Corner radius of the top corners                               | BottomSheetDefaults.cornerRadius      | No       |
| sheetMaxWidth              | Dp                        | Maximum width of the bottom sheet                              | BottomSheetDefaults.maxWidth          | No       |
| onDismissRequest           | (() -> Unit)?             | Called when the user requests dismissal (outside tap or back)  | null                                       | No       |
| onDismissFinished          | (() -> Unit)?             | Callback after bottom sheet fully dismisses                    | null                                       | No       |
| outsideMargin              | DpSize                    | Bottom sheet external margin                                   | BottomSheetDefaults.outsideMargin     | No       |
| insideMargin               | DpSize                    | Bottom sheet internal content margin                           | BottomSheetDefaults.insideMargin      | No       |
| defaultWindowInsetsPadding | Boolean                   | Whether to apply default window insets padding                 | true                                       | No       |
| dragHandleColor            | Color                     | Drag indicator color                                           | BottomSheetDefaults.dragHandleColor() | No       |
| allowDismiss               | Boolean                   | Whether to allow dismissing the sheet via drag or back gesture | true                                       | No       |
| enableNestedScroll         | Boolean                   | Whether to enable nested scrolling for the content             | true                                       | No       |
| renderInRootScaffold       | Boolean                   | Whether to render the bottom sheet in the root (outermost) Scaffold. When true, it covers the full screen. When false, it renders within the current Scaffold's bounds | true | No |
| content                    | @Composable () -> Unit    | Bottom sheet content                                           | -                                          | Yes      |

### BottomSheetDefaults Object

The BottomSheetDefaults object provides default settings for the OverlayBottomSheet component.

#### BottomSheetDefaults Properties

| Property Name | Type   | Description                          |
| ------------- | ------ | ------------------------------------ |
| cornerRadius  | Dp     | Default corner radius (28.dp)        |
| maxWidth      | Dp     | Default maximum width (640.dp)       |
| outsideMargin | DpSize | Default bottom sheet external margin |
| insideMargin  | DpSize | Default bottom sheet internal margin |

#### BottomSheetDefaults Functions

| Function Name     | Return Type | Description                      |
| ----------------- | ----------- | -------------------------------- |
| backgroundColor() | Color       | Get default background color     |
| dragHandleColor() | Color       | Get default drag indicator color |

## Advanced Usage

### Custom Styled Bottom Sheet

```kotlin
var showBottomSheet by remember { mutableStateOf(false) }

Scaffold {
    TextButton(
        text = "Show Custom Styled Bottom Sheet",
        onClick = { showBottomSheet = true }
    )

    OverlayBottomSheet(
        show = showBottomSheet,
        title = "Custom Style",
        backgroundColor = MiuixTheme.colorScheme.surfaceVariant,
        dragHandleColor = MiuixTheme.colorScheme.primary,
        outsideMargin = DpSize(16.dp, 0.dp),
        insideMargin = DpSize(32.dp, 16.dp),
        onDismissRequest = { showBottomSheet = false }
    ) {
        Column {
            Text("Custom styled bottom sheet")
            Spacer(modifier = Modifier.height(16.dp))
            TextButton(
                text = "Close",
                onClick = { showBottomSheet = false },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
```

### Bottom Sheet with List Content

```kotlin
var showBottomSheet by remember { mutableStateOf(false) }
var selectedItem by remember { mutableStateOf("") }

Scaffold {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        TextButton(
            text = "Show Selection List",
            onClick = { showBottomSheet = true }
        )
        
        Text("Selected: $selectedItem")
    }

    OverlayBottomSheet(
        show = showBottomSheet,
        title = "Select Item",
        onDismissRequest = { showBottomSheet = false }
    ) {
        LazyColumn {
            items(20) { index ->
                Text(
                    text = "Item ${index + 1}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            selectedItem = "Item ${index + 1}"
                            showBottomSheet = false
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
var showBottomSheet by remember { mutableStateOf(false) }

Scaffold {
    TextButton(
        text = "Show Bottom Sheet Without Dim",
        onClick = { showBottomSheet = true }
    )

    OverlayBottomSheet(
        show = showBottomSheet,
        title = "No Dimming",
        enableWindowDim = false,
        onDismissRequest = { showBottomSheet = false }
    ) {
        Text("This bottom sheet has no background dimming layer")
        Spacer(modifier = Modifier.height(16.dp))
        TextButton(
            text = "Close",
            onClick = { showBottomSheet = false },
            modifier = Modifier.fillMaxWidth()
        )
    }
}
```

### Bottom Sheet with Action Buttons

```kotlin
var showBottomSheet by remember { mutableStateOf(false) }

Scaffold {
    TextButton(
        text = "Show Bottom Sheet with Actions",
        onClick = { showBottomSheet = true }
    )

    OverlayBottomSheet(
        show = showBottomSheet,
        title = "Action Sheet",
        startAction = {
            val dismiss = LocalDismissState.current
            TextButton(
                text = "Cancel",
                onClick = { dismiss?.invoke() }
            )
        },
        endAction = {
            val dismiss = LocalDismissState.current
            TextButton(
                text = "Confirm",
                onClick = { dismiss?.invoke() },
                colors = ButtonDefaults.textButtonColorsPrimary()
            )
        },
        onDismissRequest = { showBottomSheet = false }
    ) {
        Text("Content with custom header actions")
        Spacer(modifier = Modifier.height(16.dp))
        Text("Start and end action buttons are displayed in the header")
    }
}
```

### Bottom Sheet with Form

```kotlin
var showBottomSheet by remember { mutableStateOf(false) }
var textFieldValue by remember { mutableStateOf("") }
var switchState by remember { mutableStateOf(false) }

Scaffold {
    TextButton(
        text = "Show Form Bottom Sheet",
        onClick = { showBottomSheet = true }
    )

    OverlayBottomSheet(
        show = showBottomSheet,
        title = "Settings Form",
        onDismissRequest = { showBottomSheet = false }
    ) {
        Card(
            colors = CardDefaults.defaultColors(
                color = MiuixTheme.colorScheme.secondaryContainer,
            ),
        ) {
            TextField(
                modifier = Modifier.padding(vertical = 12.dp),
                value = textFieldValue,
                label = "Enter content",
                maxLines = 1,
                onValueChange = { textFieldValue = it }
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
                onClick = { showBottomSheet = false },
                modifier = Modifier.weight(1f)
            )
            Spacer(Modifier.width(20.dp))
            TextButton(
                text = "Confirm",
                onClick = { showBottomSheet = false },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.textButtonColorsPrimary()
            )
        }
    }
}
```

### Adaptive Content Height

```kotlin
var showBottomSheet by remember { mutableStateOf(false) }

Scaffold {
    TextButton(
        text = "Show Adaptive Height Bottom Sheet",
        onClick = { showBottomSheet = true }
    )

    OverlayBottomSheet(
        show = showBottomSheet,
        title = "Adaptive Height",
        onDismissRequest = { showBottomSheet = false }
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
                onClick = { showBottomSheet = false },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
```

### Non-Dismissible Bottom Sheet

```kotlin
var showBottomSheet by remember { mutableStateOf(false) }

Scaffold {
    TextButton(
        text = "Show Non-Dismissible Bottom Sheet",
        onClick = { showBottomSheet = true }
    )

    OverlayBottomSheet(
        show = showBottomSheet,
        title = "Non-Dismissible",
        allowDismiss = false,
        onDismissRequest = { showBottomSheet = false }
    ) {
        Column(modifier = Modifier.padding(vertical = 16.dp)) {
            Text("This bottom sheet cannot be dismissed by dragging or back gesture")
            Spacer(modifier = Modifier.height(16.dp))
            Text("You must explicitly close it using the button below")
            Spacer(modifier = Modifier.height(16.dp))
            TextButton(
                text = "Close",
                onClick = { showBottomSheet = false },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
```
