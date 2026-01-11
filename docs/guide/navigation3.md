# Navigation3 Support

`miuix-navigation3-ui` provides UI implementation for `androidx.navigation3`, adapting to Miuix design style and transitions.

## Setup

Add the dependency to your `build.gradle.kts`:

```kotlin
implementation("androidx.navigation3:navigation3-runtime:<navigation3-version>")
implementation("top.yukonga.miuix.kmp:miuix-navigation3-ui:<version>")
```

::: warning
This library only contains the UI implementation. You must also include the `androidx-navigation3-runtime` dependency yourself.
:::

## Usage

Use `NavDisplay` to render your navigation scenes. Define your screens implementing `NavKey`.

```kotlin
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
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

    NavDisplay(
        backstack = backStack,
        entryProvider = entryProvider,
        onBack = { backStack.removeLast() }
    )
}
```
