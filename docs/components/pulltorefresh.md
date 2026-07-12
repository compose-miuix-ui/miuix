# PullToRefresh

`PullToRefresh` is a pull-to-refresh component in Miuix that provides refresh functionality for lists and other scrollable content. It features an animated interactive refresh indicator suitable for various scenarios where data refresh is needed.

::: warning
This component is only available in touch-enabled environments.
:::

For a demo, see the DropDowns page of the <a href="https://compose-miuix-ui.github.io/miuix-jsCanvas/" target="_blank" rel="noopener noreferrer">Miuix Example</a>.

## Import

```kotlin
import top.yukonga.miuix.kmp.basic.PullToRefresh
import top.yukonga.miuix.kmp.basic.rememberPullToRefreshState
```

## Basic Usage

PullToRefresh can wrap any scrollable content:

```kotlin
var isRefreshing by rememberSaveable { mutableStateOf(false) }
val pullToRefreshState = rememberPullToRefreshState()
var items by remember { mutableStateOf(1) }

LaunchedEffect(isRefreshing) {
    if (isRefreshing) {
        delay(500)
        items += 6
        isRefreshing = false
    }
}

Surface {
    PullToRefresh(
        isRefreshing = isRefreshing,
        onRefresh = { isRefreshing = true },
        pullToRefreshState = pullToRefreshState,
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(items) { index ->
                ArrowPreference(
                    title = "Item $index",
                    modifier = Modifier.padding(horizontal = 16.dp),
                    onClick = { /* Click event */ }
                )
            }
        }
    }
}
```

::: tip Contract for `isRefreshing`
`isRefreshing` is the source of truth and is synchronized in both directions: raising it to `true` while the indicator is idle shows the indicator programmatically (see below), and lowering it to `false` ends the refresh. Set it to `true` promptly in `onRefresh` — synchronously (as above), or in a coroutine launched on the UI scope before its first suspension. If it is still (or already again) `false` when the indicator settles into the refreshing state, the refresh is treated as finished and the completion animation runs immediately; a `true` that arrives later shows the indicator again.
:::

## Component States

PullToRefresh has the following states:

1. `Idle`: Initial state, no interaction
2. `Pulling`: User is pulling but hasn't reached the refresh threshold
3. `ThresholdReached`: Pull threshold reached, release to refresh
4. `Refreshing`: Currently refreshing
5. `RefreshComplete`: Refresh completed, returning to initial state

## Properties

### PullToRefresh Properties

| Property Name           | Type                   | Description                    | Default Value                          | Required |
| ----------------------- | ---------------------- | ------------------------------ | -------------------------------------- | -------- |
| isRefreshing            | Boolean                | Refresh state                  | None                                   | Yes      |
| onRefresh               | () -> Unit             | Refresh callback function      | None                                   | Yes      |
| modifier                | Modifier               | Container modifier             | Modifier                               | No       |
| pullToRefreshState      | PullToRefreshState     | PullToRefresh state            | rememberPullToRefreshState()           | No       |
| contentPadding          | PaddingValues          | Content padding                | PaddingValues(0.dp)                    | No       |
| topAppBarScrollBehavior | ScrollBehavior         | Top app bar scroll behavior    | null                                   | No       |
| color                   | Color                  | Indicator color                | PullToRefreshDefaults.color            | No       |
| circleSize              | Dp                     | Indicator circle size          | PullToRefreshDefaults.circleSize       | No       |
| refreshTexts            | List\<String>          | Text list for different states | PullToRefreshDefaults.refreshTexts     | No       |
| refreshTextStyle        | TextStyle              | Refresh text style             | PullToRefreshDefaults.refreshTextStyle | No       |
| onPullProgress          | ((Float) -> Unit)?     | Real-time full-range drag progress (0-1) callback | null                    | No       |
| content                 | @Composable () -> Unit | Scrollable content composable  | None                                   | Yes      |

### PullToRefreshState Class

PullToRefreshState manages the UI state of the refresh indicator and can be created using `rememberPullToRefreshState()`. It should only be used for UI state, while refresh logic should be controlled by `isRefreshing` and `onRefresh`.

`rememberPullToRefreshState()` accepts an optional `refreshThreshold` parameter to customize the pull progress percentage required to trigger refresh:

| Parameter        | Type  | Description                                                                                 | Default |
| ---------------- | ----- | ------------------------------------------------------------------------------------------- | ------- |
| refreshThreshold | Float | Pull progress threshold (0.0~1.0). 0.4 means 40% of full drag range. 1.0 requires maximum stretch. | 0.4 |

| Property Name               | Type         | Description                                                     |
| --------------------------- | ------------ | --------------------------------------------------------------- |
| refreshState                | RefreshState | Current refresh state                                           |
| pullProgress                | Float        | Pull progress (0-1) relative to the effective trigger threshold |
| fullDragProgress            | Float        | Full-range drag progress (0-1) across the entire visual range   |
| visualProgress              | Float        | Visual scaling progress (0-1) of the indicator circle           |

### PullToRefreshDefaults Object

PullToRefreshDefaults provides default values for the component.

| Property Name    | Type          | Description             | Default Value                                                                             |
| ---------------- | ------------- | ----------------------- | ----------------------------------------------------------------------------------------- |
| color            | Color         | Default indicator color | Color.Gray                                                                                |
| circleSize       | Dp            | Default indicator size  | 20.dp                                                                                     |
| refreshTexts     | List\<String> | Default text list       | ["Pull down to refresh", "Release to refresh", "Refreshing...", "Refreshed successfully"] |
| refreshTextStyle | TextStyle     | Default text style      | TextStyle(fontSize = 14.sp, fontWeight = Bold, color = color)                             |

## Advanced Usage

### Programmatic Refresh

Setting `isRefreshing` to `true` while the indicator is idle shows the indicator without a gesture — for example, to refresh on entry:

```kotlin
LaunchedEffect(Unit) {
    isRefreshing = true // The indicator expands and spins until set back to false
}
```

Note that while the indicator is shown, nested scrolling of the content is consumed, consistent with a gesture-triggered refresh.

### Custom Indicator Color

```kotlin
PullToRefresh(
    color = Color.Blue,
    // Other properties
) {
    // Content
}
```

### Custom Refresh Texts

```kotlin
PullToRefresh(
    refreshTexts = listOf(
        "Pull to refresh",
        "Release to refresh",
        "Refreshing",
        "Refresh successful",
    ),
    // Other properties
) {
    // Content
}
```

### Custom Refresh Threshold

```kotlin
val pullToRefreshState = rememberPullToRefreshState(
    refreshThreshold = 0.5f // 50% of full drag range
)

PullToRefresh(
    isRefreshing = isRefreshing,
    onRefresh = { isRefreshing = true },
    pullToRefreshState = pullToRefreshState,
) {
    // Content
}
```

### Real-time Pull Progress Callback

```kotlin
PullToRefresh(
    isRefreshing = isRefreshing,
    onRefresh = { isRefreshing = true },
    pullToRefreshState = pullToRefreshState,
    onPullProgress = { progress ->
        // React to full-range drag progress in real time (0.0 to 1.0)
    },
) {
    // Content
}
```
