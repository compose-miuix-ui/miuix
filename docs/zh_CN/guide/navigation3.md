# 导航支持

`miuix-navigation3-ui` 提供了 `androidx.navigation3` 的 UI 实现，适配了 Miuix 的设计风格和过渡动画。

## 配置

在 `build.gradle.kts` 中添加依赖：

```kotlin
implementation("androidx.navigation3:navigation3-runtime:<navigation3-version>")
implementation("top.yukonga.miuix.kmp:miuix-navigation3-ui:<version>")
// 可选：添加 miuix-navigation3-adaptive 以支持自适应布局
implementation("top.yukonga.miuix.kmp:miuix-navigation3-adaptive:<version>")
```

::: warning
本库仅包含 UI 实现。您必须自行引入 `androidx-navigation3-runtime` 依赖。
:::

## 使用

使用 `NavDisplay` 来渲染你的导航场景。定义实现 `NavKey` 接口的屏幕类。

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
        backstack = backStack,
        entryProvider = entryProvider
    )

    NavDisplay(
        entries = entries,
        onBack = { backStack.removeLast() }
    )
}
```

## 自适应布局支持

`miuix-navigation3-adaptive` 为 Navigation3 提供了自适应布局策略，例如 `ListDetailSceneStrategy`。

### 列表详情布局

使用 `ListDetailSceneStrategy` 创建列表详情布局。

```kotlin
import androidx.navigation3.adaptive.rememberListDetailSceneStrategy

val strategy = rememberListDetailSceneStrategy<NavKey> {
    // 可选：如果需要，显式配置面板
    // listPane { ... }
}

NavDisplay(
    entries = entries,
    sceneStrategy = strategy,
    onBack = { backStack.removeLast() }
)
```
