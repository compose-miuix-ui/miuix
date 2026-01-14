#! /usr/bin/env markdown

# Snackbar

`Snackbar` 是 Miuix 中用于在屏幕底部展示简短提示信息的轻量反馈组件。它可以附带操作按钮（例如“撤销”），并支持多种显示时长。

<div style="position: relative; max-width: 700px; height: 360px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../../compose/index.html?id=snackbar" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

## 引入

```kotlin
import top.yukonga.miuix.kmp.basic.Snackbar
import top.yukonga.miuix.kmp.basic.SnackbarHost
import top.yukonga.miuix.kmp.basic.SnackbarHostState
import top.yukonga.miuix.kmp.basic.SnackbarDuration
import top.yukonga.miuix.kmp.basic.SnackbarResult
```

## 基本用法

Snackbar 通常与 `Scaffold` 一起使用。你需要创建一个 `SnackbarHostState`，将其传给 `SnackbarHost`，再通过 `showSnackbar` 来显示消息：

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
            text = "显示消息",
            onClick = {
                scope.launch {
                    snackbarHostState.showSnackbar("这是一条短提示")
                }
            },
        )
    }
}
```

## SnackbarHostState 与 showSnackbar

`SnackbarHostState` 负责管理 Snackbar 消息队列。

### showSnackbar

```kotlin
suspend fun SnackbarHostState.showSnackbar(
    message: String,
    actionLabel: String? = null,
    withDismissAction: Boolean = false,
    duration: SnackbarDuration = SnackbarDuration.Short,
): SnackbarResult
```

| 参数名             | 类型             | 说明                             | 默认值                    | 是否必须 |
| ------------------ | ---------------- | -------------------------------- | ------------------------- | -------- |
| message            | String           | 要显示在 Snackbar 中的文本       | -                         | 是       |
| actionLabel        | String?          | 操作按钮的文本（可选）          | null                      | 否       |
| withDismissAction  | Boolean          | 是否显示关闭图标                 | false                     | 否       |
| duration           | SnackbarDuration | Snackbar 显示的时长              | SnackbarDuration.Short    | 否       |

返回值 `SnackbarResult` 用于区分 Snackbar 是被操作按钮触发，还是自然消失或关闭。

### 获取最新或最旧的 Snackbar

```kotlin
suspend fun SnackbarHostState.newestSnackbarData(): SnackbarData?
suspend fun SnackbarHostState.oldestSnackbarData(): SnackbarData?
```

你可以通过返回的 `SnackbarData` 调用 `dismiss()` 或 `performAction()`，以手动关闭最新或最旧的 Snackbar。

## SnackbarHost

`SnackbarHost` 负责根据给定的 `SnackbarHostState` 渲染屏幕上的 Snackbar。

```kotlin
@Composable
fun SnackbarHost(
    state: SnackbarHostState,
    modifier: Modifier = Modifier,
    content: @Composable (SnackbarData) -> Unit = { Snackbar(it) },
)
```

| 参数名   | 类型                               | 说明                                       | 默认值             | 是否必须 |
| -------- | ---------------------------------- | ------------------------------------------ | ------------------ | -------- |
| state    | SnackbarHostState                  | 管理 Snackbar 队列的状态                   | -                  | 是       |
| modifier | Modifier                           | 应用于宿主容器的修饰符                     | Modifier           | 否       |
| content  | @Composable (SnackbarData) -> Unit | 自定义每一条 Snackbar 的内容               | `{ Snackbar(it) }` | 否       |

大多数情况下，可以直接使用默认的 `content`，即内置的 `Snackbar` 视觉样式。

## Snackbar

`Snackbar` 定义了默认的消息样式。

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

| 参数名       | 类型           | 说明                     | 默认值                          | 是否必须 |
| ------------ | -------------- | ------------------------ | -------------------------------- | -------- |
| data         | SnackbarData   | 包含消息与操作信息的数据 | -                                | 是       |
| modifier     | Modifier       | 应用于 Snackbar 容器的修饰符 | Modifier                     | 否       |
| cornerRadius | Dp             | Snackbar 的圆角半径      | 12.dp                           | 否       |
| colors       | SnackbarColors | Snackbar 的颜色配置      | SnackbarDefaults.snackbarColors() | 否     |
| insideMargin | PaddingValues  | Snackbar 内容区域的内边距 | PaddingValues(12.dp, 8.dp)    | 否       |

### SnackbarColors 与 SnackbarDefaults

`SnackbarColors` 定义了 Snackbar 使用的颜色集合：

```kotlin
data class SnackbarColors(
    val containerColor: Color,
    val contentColor: Color,
    val actionContentColor: Color,
    val dismissActionContentColor: Color,
)
```

可以通过 `SnackbarDefaults.snackbarColors` 创建颜色配置：

```kotlin
val colors = SnackbarDefaults.snackbarColors(
    containerColor = MiuixTheme.colorScheme.surfaceContainerHighest,
    contentColor = MiuixTheme.colorScheme.onSurfaceContainer,
    actionContentColor = MiuixTheme.colorScheme.onSurfaceContainerHighest,
    dismissActionContentColor = MiuixTheme.colorScheme.onSurfaceContainerHighest,
)
```

## SnackbarDuration 与 SnackbarResult

### SnackbarDuration

`SnackbarDuration` 用于控制 Snackbar 显示的时长：

```kotlin
sealed interface SnackbarDuration {
    data object Short : SnackbarDuration
    data object Long : SnackbarDuration
    data object Indefinite : SnackbarDuration
    data class Custom(val durationMillis: Long) : SnackbarDuration
}
```

| 选项        | 说明                         | 显示时长        |
| ----------- | ---------------------------- | --------------- |
| Short       | 短提示                       | 约 4 秒         |
| Long        | 较长提示                     | 约 10 秒        |
| Indefinite  | 一直显示，需手动关闭         | 直到手动关闭    |
| Custom      | 自定义毫秒级时长             | 用户自定义      |

### SnackbarResult

`SnackbarResult` 用于描述 Snackbar 的完成方式：

```kotlin
enum class SnackbarResult {
    Dismissed,
    ActionPerformed,
}
```

## 进阶用法

### 带操作按钮的 Snackbar

```kotlin
val snackbarHostState = remember { SnackbarHostState() }
val scope = rememberCoroutineScope()

TextButton(
    text = "显示带操作的提示",
    onClick = {
        scope.launch {
            val result = snackbarHostState.showSnackbar(
                message = "这条消息包含操作按钮",
                actionLabel = "撤销",
                duration = SnackbarDuration.Short,
            )
            when (result) {
                SnackbarResult.ActionPerformed -> { /* 处理撤销 */ }
                SnackbarResult.Dismissed -> { /* 超时或被关闭 */ }
            }
        }
    },
)
```

### 可关闭且无限时长的 Snackbar

```kotlin
val snackbarHostState = remember { SnackbarHostState() }
val scope = rememberCoroutineScope()

TextButton(
    text = "显示无限时长提示",
    onClick = {
        scope.launch {
            snackbarHostState.showSnackbar(
                message = "无限时长提示，需要手动关闭",
                withDismissAction = true,
                duration = SnackbarDuration.Indefinite,
            )
        }
    },
)

TextButton(
    text = "关闭最早的一条",
    onClick = {
        scope.launch {
            snackbarHostState.oldestSnackbarData()?.dismiss()
        }
    },
)
```

