# Navigation3 Support

`miuix-navigation3-ui` provides UI implementation for `androidx.navigation3`, adapting to Miuix design style and transitions.

## Setup

Add the dependency to your `build.gradle.kts`:

```kotlin
implementation("androidx.navigation3:navigation3-runtime:<navigation3-version>")
implementation("top.yukonga.miuix.kmp:miuix-navigation3-ui:<version>")
// Optional: Add miuix-navigation3-adaptive for navigation3 adaptive support
implementation("top.yukonga.miuix.kmp:miuix-navigation3-adaptive:<version>")
```

::: warning
This library only contains the UI implementation. You must also include the `androidx-navigation3-runtime` dependency yourself.
:::

## Usage

Use `NavDisplay` to render your navigation scenes. Define your screens implementing `NavKey`.

```kotlin
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberDecoratedNavEntries
import androidx.navigation3.ui.NavDisplay

sealed interface Screen : NavKey {
    data object Home : Screen
    data class Detail(val id: String) : Screen
}

@Composable
fun App() {
    val backStack = remember { mutableStateListOf<NavKey>(Screen.Home) }

    val entryProvider = remember(backStack) {
        entryProvider<NavKey> {
            entry(Screen.Home) {
                HomePage()
            }
            entry<Screen.Detail> { screen ->
                DetailPage(screen.id)
            }
        }
    }

    val entries = rememberDecoratedNavEntries(
        backStack = backStack,
        entryProvider = entryProvider
    )

    NavDisplay(
        entries = entries,
        onBack = { backStack.removeLast() }
    )
}
```

## Adaptive Support

`miuix-navigation3-adaptive` provides adaptive layout strategies for Navigation3, such as `ListDetailSceneStrategy`.

### List Detail Layout

Use `ListDetailSceneStrategy` to create a list-detail layout.

```kotlin
import androidx.navigation3.adaptive.rememberListDetailSceneStrategy

val strategy = rememberListDetailSceneStrategy<NavKey> {
    // Optional: Configure panes explicitly if needed
    // listPane { ... }
}

NavDisplay(
    entries = entries,
    sceneStrategy = strategy,
    onBack = { backStack.removeLast() }
)
```
