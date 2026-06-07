#! /usr/bin/env markdown

# Tooltip

`Tooltip` 是 Miuix 中用于简短描述锚点元素的组件。参照 Material 3，统一由 `TooltipBox` 将 tooltip 锚定到内容上；你在它的 `tooltip` 槽里填入 `PlainTooltip`（反色表面短标签）或 `RichTooltip`（带可选标题与操作的 surfaceContainer 卡片）。tooltip 在悬停（光标）或长按（触摸）时显示，也可通过 `TooltipState` 以编程方式显示。

<div style="position: relative; height: 360px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../../compose/index.html?id=tooltip" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

## 引入

```kotlin
import top.yukonga.miuix.kmp.basic.TooltipBox
import top.yukonga.miuix.kmp.basic.RichTooltipBox
import top.yukonga.miuix.kmp.basic.PlainTooltip
import top.yukonga.miuix.kmp.basic.RichTooltip
import top.yukonga.miuix.kmp.basic.TooltipState
import top.yukonga.miuix.kmp.basic.rememberTooltipState
import top.yukonga.miuix.kmp.basic.TooltipDefaults
import top.yukonga.miuix.kmp.basic.TooltipAnchorPosition
```

## 基本用法

为纯图标控件加标签，最快的方式是 `TooltipBox` 的 `text` 便捷重载：

```kotlin
TooltipBox(text = "搜索") {
    IconButton(onClick = { /* ... */ }) {
        Icon(
            imageVector = MiuixIcons.Basic.Search,
            contentDescription = "搜索",
        )
    }
}
```

## TooltipBox

`TooltipBox` 将 tooltip 锚定到 `content`。它的 `tooltip` 槽是一个 `TooltipScope` lambda，用 `PlainTooltip` 或 `RichTooltip` 填充。

```kotlin
@Composable
fun TooltipBox(
    positionProvider: PopupPositionProvider,
    tooltip: @Composable TooltipScope.() -> Unit,
    state: TooltipState,
    modifier: Modifier = Modifier,
    focusable: Boolean = false,
    enableUserInput: Boolean = true,
    content: @Composable () -> Unit,
)
```

| 参数名           | 类型                                | 说明                                                       | 默认值   | 是否必须 |
| ---------------- | ----------------------------------- | ---------------------------------------------------------- | -------- | -------- |
| positionProvider | PopupPositionProvider               | 定位 tooltip，见 `TooltipDefaults.rememberTooltipPositionProvider` | -  | 是       |
| tooltip          | @Composable TooltipScope.() -> Unit | tooltip 内容（`PlainTooltip` / `RichTooltip`）            | -        | 是       |
| state            | TooltipState                        | 控制显隐的状态                                            | -        | 是       |
| modifier         | Modifier                            | 应用于锚点包裹层的修饰符                                  | Modifier | 否       |
| focusable        | Boolean                             | tooltip 是否 focusable（可交互的 rich tooltip 需为 true） | false    | 否       |
| enableUserInput  | Boolean                             | 悬停 / 长按锚点是否显示 tooltip                          | true     | 否       |
| content          | @Composable () -> Unit              | 锚点内容                                                  | -        | 是       |

```kotlin
TooltipBox(
    positionProvider = TooltipDefaults.rememberTooltipPositionProvider(),
    tooltip = { PlainTooltip { Text("添加到收藏") } },
    state = rememberTooltipState(),
) {
    IconButton(onClick = { /* ... */ }) {
        Icon(imageVector = MiuixIcons.Basic.Check, contentDescription = "添加到收藏")
    }
}
```

## PlainTooltip

`PlainTooltip` 是渲染在 Miuix 反色表面上的短标签、不可交互。它是 `TooltipScope` 扩展，用于 `TooltipBox` 的 `tooltip` 槽内。

```kotlin
@Composable
fun TooltipScope.PlainTooltip(
    modifier: Modifier = Modifier,
    caretShape: Shape? = null,
    maxWidth: Dp = TooltipDefaults.PlainTooltipMaxWidth,
    cornerRadius: Dp = TooltipDefaults.PlainTooltipCornerRadius,
    containerColor: Color = TooltipDefaults.plainTooltipContainerColor,
    contentColor: Color = TooltipDefaults.plainTooltipContentColor,
    insideMargin: PaddingValues = TooltipDefaults.PlainTooltipInsideMargin,
    content: @Composable () -> Unit,
)
```

默认情况下 plain tooltip 采用反色表面（浅色主题为深色气泡，深色主题为浅色气泡）。传入 `caretShape = TooltipDefaults.caretShape()` 可绘制指向锚点的箭头；默认无箭头。

## RichTooltip

`RichTooltip` 是带可选标题与操作的持久、可交互卡片，渲染在普通 `surfaceContainer` 上。它是 `TooltipScope` 扩展。

```kotlin
@Composable
fun TooltipScope.RichTooltip(
    modifier: Modifier = Modifier,
    title: (@Composable () -> Unit)? = null,
    action: (@Composable () -> Unit)? = null,
    caretShape: Shape? = null,
    maxWidth: Dp = TooltipDefaults.RichTooltipMaxWidth,
    cornerRadius: Dp = TooltipDefaults.RichTooltipCornerRadius,
    colors: RichTooltipColors = TooltipDefaults.richTooltipColors(),
    insideMargin: PaddingValues = TooltipDefaults.RichTooltipInsideMargin,
    text: @Composable () -> Unit,
)
```

rich tooltip 可交互，因此需在 `TooltipBox` 上设 `focusable = true`（便捷的 `RichTooltipBox` 已替你设好），以便点外部关闭、操作可达。

```kotlin
val tooltipState = rememberTooltipState(isPersistent = true)
val scope = rememberCoroutineScope()

TooltipBox(
    positionProvider = TooltipDefaults.rememberTooltipPositionProvider(),
    tooltip = {
        RichTooltip(
            title = { Text("新功能") },
            action = {
                TextButton(text = "知道了", onClick = { tooltipState.dismiss() })
            },
        ) {
            Text("Rich tooltip 支持标题、说明文字与一个操作。")
        }
    },
    state = tooltipState,
    focusable = true,
) {
    IconButton(onClick = { scope.launch { tooltipState.show() } }) {
        Icon(imageVector = MiuixIcons.Basic.Check, contentDescription = "新功能")
    }
}
```

## TooltipState

`TooltipState` 控制 tooltip 的显隐。全局 `MutatorMutex` 保证同一时刻只有一个 tooltip 可见。

```kotlin
@Stable
interface TooltipState {
    val transition: MutableTransitionState<Boolean>
    val isVisible: Boolean
    val isPersistent: Boolean
    suspend fun show(mutatePriority: MutatePriority = MutatePriority.Default)
    fun dismiss()
    fun onDispose()
}

@Composable
fun rememberTooltipState(
    initialIsVisible: Boolean = false,
    isPersistent: Boolean = false,
    mutatorMutex: MutatorMutex = BasicTooltipDefaults.GlobalMutatorMutex,
): TooltipState
```

| 参数名           | 类型         | 说明                                  | 默认值                                  | 是否必须 |
| ---------------- | ------------ | ------------------------------------- | --------------------------------------- | -------- |
| initialIsVisible | Boolean      | tooltip 是否初始可见                  | false                                   | 否       |
| isPersistent     | Boolean      | tooltip 是否一直显示直到被关闭        | false                                   | 否       |
| mutatorMutex     | MutatorMutex | 保证同一时刻只显示一个 tooltip 的互斥锁 | BasicTooltipDefaults.GlobalMutatorMutex | 否     |

当 `isPersistent` 为 false（plain tooltip）时，tooltip 在短暂超时后自动消失；为 true（rich tooltip）时，会一直显示直到 `dismiss()`、点外部或按返回键。`show()` 是 suspend 函数，需在协程中调用（例如 `scope.launch { state.show() }`）。

## TooltipDefaults

`TooltipDefaults` 提供定位 provider、颜色、caret 与尺寸常量。

### 定位 provider

```kotlin
@Composable
fun TooltipDefaults.rememberTooltipPositionProvider(
    positioning: TooltipAnchorPosition = TooltipAnchorPosition.Below,
    spacingBetweenTooltipAndAnchor: Dp = TooltipDefaults.SpacingBetweenTooltipAndAnchor,
): PopupPositionProvider
```

`TooltipAnchorPosition` 选择优先方位：`Above`、`Below`、`Left`、`Right`、`Start`、`End`。tooltip 在交叉轴上居中，空间不足时翻转到对侧。

### 颜色

```kotlin
// plain tooltip（反色表面）
val plainContainer = TooltipDefaults.plainTooltipContainerColor   // onSecondaryVariant
val plainContent = TooltipDefaults.plainTooltipContentColor       // secondaryVariant

// rich tooltip（surface container）
val richColors = TooltipDefaults.richTooltipColors(
    containerColor = MiuixTheme.colorScheme.surfaceContainer,
    contentColor = MiuixTheme.colorScheme.onSurfaceContainerVariant,
    titleContentColor = MiuixTheme.colorScheme.onSurfaceContainer,
    actionContentColor = MiuixTheme.colorScheme.primary,
)
```

`RichTooltipColors` 是包含 `containerColor`、`contentColor`、`titleContentColor`、`actionContentColor` 的数据类。

### Caret

```kotlin
fun TooltipDefaults.caretShape(): Shape
val TooltipDefaults.caretSize: DpSize // 16 x 8 dp
```

向 `PlainTooltip` / `RichTooltip` 传入 `caretShape = TooltipDefaults.caretShape()` 即可绘制指向锚点的箭头（针对 `Above` / `Below` 方位）。默认无箭头，与 Miuix 其余浮层保持一致。

### 常量

| 常量名                         | 类型          | 说明                     | 默认值                                             |
| ------------------------------ | ------------- | ------------------------ | -------------------------------------------------- |
| SpacingBetweenTooltipAndAnchor | Dp            | tooltip 与锚点之间的间距 | 8.dp                                               |
| PlainTooltipMaxWidth           | Dp            | plain tooltip 的最大宽度 | 200.dp                                             |
| PlainTooltipCornerRadius       | Dp            | plain tooltip 的圆角半径 | 12.dp                                              |
| PlainTooltipInsideMargin       | PaddingValues | plain tooltip 的内边距   | PaddingValues(horizontal = 12.dp, vertical = 8.dp) |
| RichTooltipMaxWidth            | Dp            | rich tooltip 的最大宽度  | 320.dp                                             |
| RichTooltipCornerRadius        | Dp            | rich tooltip 的圆角半径  | 16.dp                                              |
| RichTooltipInsideMargin        | PaddingValues | rich tooltip 的内边距    | PaddingValues(all = 16.dp)                         |
| RichTooltipActionCornerRadius  | Dp            | rich tooltip 操作按钮的圆角半径 | 8.dp                                          |
| RichTooltipActionInsideMargin  | PaddingValues | rich tooltip 操作按钮的内边距   | PaddingValues(horizontal = 12.dp, vertical = 6.dp) |
| caretSize                      | DpSize        | caret 的尺寸             | DpSize(16.dp, 8.dp)                                |

## 便捷重载

常见场景下可以省去定位 provider 与内容组合函数。

### Plain tooltip

```kotlin
@Composable
fun TooltipBox(
    text: String,
    modifier: Modifier = Modifier,
    state: TooltipState = rememberTooltipState(isPersistent = false),
    enabled: Boolean = true,
    positioning: TooltipAnchorPosition = TooltipAnchorPosition.Below,
    containerColor: Color = TooltipDefaults.plainTooltipContainerColor,
    contentColor: Color = TooltipDefaults.plainTooltipContentColor,
    content: @Composable () -> Unit,
)
```

### Rich tooltip

```kotlin
@Composable
fun RichTooltipBox(
    text: String,
    modifier: Modifier = Modifier,
    state: TooltipState = rememberTooltipState(isPersistent = true),
    title: String? = null,
    actionText: String? = null,
    onActionClick: (() -> Unit)? = null,
    enabled: Boolean = true,
    positioning: TooltipAnchorPosition = TooltipAnchorPosition.Below,
    colors: RichTooltipColors = TooltipDefaults.richTooltipColors(),
    content: @Composable () -> Unit,
)
```

`RichTooltipBox` 是持久且 focusable 的。通过长按 / 悬停触发，或提升 `state` 并在锚点自身的点击里调用 `state.show()`。操作会先调用 `onActionClick` 再关闭 tooltip。

```kotlin
val richState = rememberTooltipState(isPersistent = true)
val scope = rememberCoroutineScope()

RichTooltipBox(
    title = "Rich tooltip",
    text = "Rich tooltip 支持标题、说明文字与一个操作。",
    actionText = "知道了",
    onActionClick = { /* ... */ },
    state = richState,
) {
    TextButton(
        text = "显示 rich tooltip",
        onClick = { scope.launch { richState.show() } },
    )
}
```
