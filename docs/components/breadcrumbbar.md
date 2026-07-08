# BreadcrumbBar

`BreadcrumbBar` is a horizontal navigation component in Miuix that displays a trail of path segments as capsule-shaped items separated by arrow icons. When the content overflows the available width, it scrolls horizontally instead of collapsing (given that miuix is typically used on mobile devices).

The `highlightIndex` is decoupled from the `items` list: the caller may show the full path while highlighting any segment (e.g. the current directory or a parent the user navigated back to). The bar automatically scrolls to keep the highlighted item visible.

<div style="position: relative; height: 200px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../compose/index.html?id=breadcrumbBar" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

## Import

```kotlin
import top.yukonga.miuix.kmp.basic.BreadcrumbBar
import top.yukonga.miuix.kmp.basic.BreadcrumbItem
import top.yukonga.miuix.kmp.basic.joinToPath
```

## Basic Usage

```kotlin
val items = listOf(
    BreadcrumbItem(path = "/storage/emulated/0", text = "Internal storage"),
    BreadcrumbItem(path = "DataBackup"),
    BreadcrumbItem(path = "apps"),
    BreadcrumbItem(path = "com.tencent.mobileqq"),
    BreadcrumbItem(path = "user_0"),
)

BreadcrumbBar(
    items = items,
    onItemClick = { index -> /* Navigate to segment at index */ },
)
```

## Highlight Control

The `highlightIndex` parameter is decoupled from the items list. You can show the full path while highlighting any segment:

```kotlin
var highlightIndex by remember { mutableIntStateOf(items.lastIndex) }

BreadcrumbBar(
    items = items,
    onItemClick = { index -> highlightIndex = index },
    highlightIndex = highlightIndex,
)
```

## Joining Path Segments

Use the `joinToPath` extension function to reconstruct the full path from a list of `BreadcrumbItem`s:

```kotlin
val fullPath = items.joinToPath("/")
// Result: "/storage/emulated/0/DataBackup/apps/com.tencent.mobileqq/user_0"

val windowsPath = items.joinToPath("\\")
// Result: "/storage/emulated/0\\DataBackup\\apps\\com.tencent.mobileqq\\user_0"
```

## Component States

### Disabled State

```kotlin
BreadcrumbBar(
    items = items,
    onItemClick = {},
    enabled = false,
)
```

## Properties

### BreadcrumbBar Properties

| Property Name     | Type                      | Description                                                                    | Default Value                               | Required |
| ----------------- | ------------------------- | ------------------------------------------------------------------------------ | ------------------------------------------- | -------- |
| items             | List\<BreadcrumbItem>     | The list of breadcrumb items to display                                        | -                                           | Yes      |
| onItemClick       | (Int) -> Unit             | Callback invoked with the index of the clicked item                            | -                                           | Yes      |
| modifier          | Modifier                  | Modifier applied to the breadcrumb bar                                         | Modifier                                    | No       |
| highlightIndex    | Int                       | Index of the highlighted segment                                               | items.lastIndex                             | No       |
| enabled           | Boolean                   | Whether items are clickable; horizontal scrolling remains active when disabled | true                                        | No       |
| colors            | BreadcrumbBarColors       | Color configuration for the breadcrumb bar                                     | BreadcrumbBarDefaults.breadcrumbBarColors() | No       |
| insideMargin      | PaddingValues             | Internal padding of the breadcrumb bar                                         | BreadcrumbBarDefaults.InsideMargin          | No       |
| scrollState       | ScrollState?              | Scroll state for horizontal scrolling; if null an internal state is created    | null                                        | No       |
| interactionSource | MutableInteractionSource? | Interaction source for the items                                               | null                                        | No       |
| indication        | Indication?               | Indication for click interactions                                              | LocalIndication.current                     | No       |

### BreadcrumbItem Properties

| Property Name | Type    | Description                                                         | Default Value | Required |
| ------------- | ------- | ------------------------------------------------------------------- | ------------- | -------- |
| path          | String  | The path segment, used by `joinToPath` to reconstruct the full path | -             | Yes      |
| text          | String? | The display text. If null, `path` is used for display               | null          | No       |

### BreadcrumbBarDefaults Object

The BreadcrumbBarDefaults object provides default values and color configurations for breadcrumb bar components.

#### Constants

| Constant Name         | Type          | Description                            | Default Value |
| --------------------- | ------------- | -------------------------------------- | ------------- |
| InsideMargin          | PaddingValues | Internal padding of the breadcrumb bar | PaddingValues(horizontal = 12.dp, vertical = 8.dp) |
| ItemHeight            | Dp            | Height of each capsule-shaped item     | 32.dp         |
| ItemHorizontalPadding | Dp            | Horizontal padding of each item (equals the corner radius for full semicircle ends) | 10.dp |

#### Methods

| Method Name           | Type                | Description                                        |
| --------------------- | ------------------- | -------------------------------------------------- |
| breadcrumbBarColors() | BreadcrumbBarColors | Creates color configuration for the breadcrumb bar |

### BreadcrumbBarColors

| Property Name            | Type  | Description                                 |
| ------------------------ | ----- | ------------------------------------------- |
| color                    | Color | Text color of normal segments               |
| highlightColor           | Color | Text color of the highlighted segment       |
| disabledColor            | Color | Text color when disabled                    |
| separatorColor           | Color | Color of the arrow separators               |
| backgroundColor          | Color | Background color of normal segments         |
| highlightBackgroundColor | Color | Background color of the highlighted segment |
| disabledBackgroundColor  | Color | Background color when disabled              |

## Advanced Usage

### Custom Colors

```kotlin
BreadcrumbBar(
    items = items,
    onItemClick = { index -> /* Handle click */ },
    colors = BreadcrumbBarDefaults.breadcrumbBarColors(
        color = MiuixTheme.colorScheme.onBackground.copy(alpha = 0.55f),
        highlightColor = MiuixTheme.colorScheme.primary,
        backgroundColor = MiuixTheme.colorScheme.onBackground.copy(alpha = 0.1f),
        highlightBackgroundColor = MiuixTheme.colorScheme.primary.copy(alpha = 0.2f),
    ),
)
```

### Decoupled Highlight and Navigation

The `highlightIndex` is independent from the items list, enabling scenarios where you show the full path but highlight a parent segment:

```kotlin
// Full path: /storage/emulated/0/DataBackup/apps/com.tencent.mobileqq/user_0
// Highlight on "apps" (index 2) to indicate the user navigated back
BreadcrumbBar(
    items = items,
    onItemClick = { index ->
        // Update items and highlightIndex as needed
        highlightIndex = index
    },
    highlightIndex = 2,
)
```
