#! /usr/bin/env markdown

# Snackbar

`Snackbar` is a lightweight feedback component in Miuix used to display brief messages at the bottom of the screen. It can optionally provide actions such as “Undo” and supports different display durations.

<div style="position: relative; max-width: 700px; height: 360px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../compose/index.html?id=snackbar" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

## Import

```kotlin
import top.yukonga.miuix.kmp.basic.Snackbar
import top.yukonga.miuix.kmp.basic.SnackbarHost
import top.yukonga.miuix.kmp.basic.SnackbarHostState
import top.yukonga.miuix.kmp.basic.SnackbarDuration
import top.yukonga.miuix.kmp.basic.SnackbarResult
```

## Basic Usage

The Snackbar is usually used together with `Scaffold`. You create a `SnackbarHostState`, pass it to `SnackbarHost`, and then call `showSnackbar` to display messages:a

```kotlin
val snackbarHostState = remember { SnackbarHostState() }
val scope = rememberCoroutineScope()

Scaffold(
    snackbarHost = {
        SnackbarHost(state = snackbarHostState)
    },
) { paddingValues ->
    Box(
        modifier = Modifier
            .padding(paddingValues),
    ) {
        TextButton(
            text = "Show message",
            onClick = {
                scope.launch {
                    snackbarHostState.showSnackbar("This is a short message")
                }
            },
        )
    }
}
```

## SnackbarHostState and showSnackbar

`SnackbarHostState` manages a queue of Snackbar messages.

### showSnackbar

```kotlin
suspend fun SnackbarHostState.showSnackbar(
    message: String,
    actionLabel: String? = null,
    withDismissAction: Boolean = false,
    duration: SnackbarDuration = SnackbarDuration.Short,
): SnackbarResult
```

| Parameter Name    | Type             | Description                              | Default Value          | Required |
| ----------------- | ---------------- | ---------------------------------------- | ---------------------- | -------- |
| message           | String           | Text shown in the Snackbar               | -                      | Yes      |
| actionLabel       | String?          | Optional label for the action button     | null                   | No       |
| withDismissAction | Boolean          | Whether to show a dismiss icon           | false                  | No       |
| duration          | SnackbarDuration | Duration that the Snackbar stays visible | SnackbarDuration.Short | No       |

The return value `SnackbarResult` indicates whether the Snackbar was dismissed or the action was performed.

### Getting the oldest or newest Snackbar

```kotlin
suspend fun SnackbarHostState.newestSnackbarData(): SnackbarData?
suspend fun SnackbarHostState.oldestSnackbarData(): SnackbarData?
```

These helpers allow you to manually dismiss the newest or oldest visible Snackbar via `dismiss()` or `performAction()`.

## SnackbarHost

`SnackbarHost` is responsible for rendering the Snackbars based on the given `SnackbarHostState`.

```kotlin
@Composable
fun SnackbarHost(
    state: SnackbarHostState,
    modifier: Modifier = Modifier,
    content: @Composable (SnackbarData) -> Unit = { Snackbar(it) },
)
```

| Parameter Name | Type                               | Description                            | Default Value        | Required |
| -------------- | ---------------------------------- | -------------------------------------- | -------------------- | -------- |
| state          | SnackbarHostState                  | State that holds the Snackbar queue    | -                    | Yes      |
| modifier       | Modifier                           | Modifier applied to the host container | Modifier             | No       |
| content        | @Composable (SnackbarData) -> Unit | Custom content for each Snackbar item  | `{ Snackbar(it) }` | No       |

In most cases you can keep the default `content` which uses the built‑in `Snackbar` composable.

## Snackbar

`Snackbar` defines the default visual style for messages.

```kotlin
@Composable
fun Snackbar(
    data: SnackbarData,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 12.dp,
    colors: SnackbarColors = SnackbarDefaults.snackbarColors(),
    insideMargin: PaddingValues = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
)
```

| Parameter Name | Type           | Description                                | Default Value                     | Required |
| -------------- | -------------- | ------------------------------------------ | --------------------------------- | -------- |
| data           | SnackbarData   | Data describing message and actions        | -                                 | Yes      |
| modifier       | Modifier       | Modifier applied to the Snackbar container | Modifier                          | No       |
| cornerRadius   | Dp             | Corner radius of the Snackbar              | 12.dp                             | No       |
| colors         | SnackbarColors | Color configuration for the Snackbar       | SnackbarDefaults.snackbarColors() | No       |
| insideMargin   | PaddingValues  | Inner padding of the Snackbar content      | PaddingValues(12.dp, 8.dp)        | No       |

### SnackbarColors and SnackbarDefaults

`SnackbarColors` defines the color set used by the Snackbar:

```kotlin
data class SnackbarColors(
    val containerColor: Color,
    val contentColor: Color,
    val actionContentColor: Color,
    val dismissActionContentColor: Color,
)
```

You can create a color configuration via `SnackbarDefaults.snackbarColors`:

```kotlin
val colors = SnackbarDefaults.snackbarColors(
    containerColor = MiuixTheme.colorScheme.surfaceContainerHighest,
    contentColor = MiuixTheme.colorScheme.onSurfaceContainer,
    actionContentColor = MiuixTheme.colorScheme.onSurfaceContainerHighest,
    dismissActionContentColor = MiuixTheme.colorScheme.onSurfaceContainerHighest,
)
```

## SnackbarDuration and SnackbarResult

### SnackbarDuration

`SnackbarDuration` controls how long the Snackbar stays visible:

```kotlin
sealed interface SnackbarDuration {
    data object Short : SnackbarDuration
    data object Long : SnackbarDuration
    data object Indefinite : SnackbarDuration
    data class Custom(val durationMillis: Long) : SnackbarDuration
}
```

| Option     | Description                           | Duration         |
| ---------- | ------------------------------------- | ---------------- |
| Short      | Short message                         | About 4 seconds  |
| Long       | Longer message                        | About 10 seconds |
| Indefinite | Stays until dismissed or action fired | Until dismissed  |
| Custom     | Custom duration in milliseconds       | User‑specified  |

### SnackbarResult

`SnackbarResult` describes how the Snackbar was completed:

```kotlin
enum class SnackbarResult {
    Dismissed,
    ActionPerformed,
}
```

## Advanced Usage

### Snackbar with action

```kotlin
val snackbarHostState = remember { SnackbarHostState() }
val scope = rememberCoroutineScope()

TextButton(
    text = "Show action",
    onClick = {
        scope.launch {
            val result = snackbarHostState.showSnackbar(
                message = "This message has an action",
                actionLabel = "Undo",
                duration = SnackbarDuration.Short,
            )
            when (result) {
                SnackbarResult.ActionPerformed -> { /* handle undo */ }
                SnackbarResult.Dismissed -> { /* handle timeout */ }
            }
        }
    },
)
```

### Dismissible and indefinite Snackbar

```kotlin
val snackbarHostState = remember { SnackbarHostState() }
val scope = rememberCoroutineScope()

TextButton(
    text = "Show indefinite",
    onClick = {
        scope.launch {
            snackbarHostState.showSnackbar(
                message = "Indefinite message, dismiss manually",
                withDismissAction = true,
                duration = SnackbarDuration.Indefinite,
            )
        }
    },
)

TextButton(
    text = "Dismiss oldest",
    onClick = {
        scope.launch {
            snackbarHostState.oldestSnackbarData()?.dismiss()
        }
    },
)
```
