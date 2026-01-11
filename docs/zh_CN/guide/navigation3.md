# 导航支持

`miuix-navigation3-ui` 提供了 `androidx.navigation3` 的 UI 实现，适配了 Miuix 的设计风格和过渡动画。

## 配置

在 `build.gradle.kts` 中添加依赖：

```kotlin
implementation("androidx.navigation3:navigation3-runtime:<navigation3-version>")
implementation("top.yukonga.miuix.kmp:miuix-navigation3-ui:<version>")
```

::: warning
本库仅包含 UI 实现。您必须自行引入 `androidx-navigation3-runtime` 依赖。
:::

## 使用

使用 `NavDisplay` 来渲染你的导航场景。定义实现 `NavKey` 接口的屏幕类。

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
