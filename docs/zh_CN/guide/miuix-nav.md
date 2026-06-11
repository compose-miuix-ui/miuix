# miuix-nav

`miuix-nav` 是一个自研的 Compose Multiplatform 导航运行时，核心模型为**连续栈深度**。整条返回栈由单个 `Animatable<Float>`（`animatedTop`）驱动；每个 entry 的视觉是其相对深度的纯函数。由此，连续出入栈、完全自定义的 float 驱动转场、以及 1:1 跟手的手势返回都自然成立。它**零依赖** `androidx.navigation3`。

## 安装

在 `build.gradle.kts` 中添加依赖：

```kotlin
implementation("top.yukonga.miuix.kmp:miuix-nav:<version>")
```

为路由层级标注 `@Serializable`，使返回栈能在配置变更与进程死亡后保存与恢复。

## 基本用法

```kotlin
import kotlinx.serialization.Serializable
import top.yukonga.miuix.kmp.nav.core.NavDisplay
import top.yukonga.miuix.kmp.nav.core.NavKey
import top.yukonga.miuix.kmp.nav.core.rememberNavBackStack
import top.yukonga.miuix.kmp.nav.transition.NavTransitions

@Serializable
sealed interface Route : NavKey {
    @Serializable data object Home : Route
    @Serializable data class Detail(val id: String) : Route
}

@Composable
fun App() {
    val backStack = rememberNavBackStack<Route>(Route.Home)
    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        transition = NavTransitions.Cupertino,
    ) {
        entry<Route.Home> {
            HomeScreen(onOpen = { id -> backStack.add(Route.Detail(id)) })
        }
        entry<Route.Detail> { route ->
            DetailScreen(route.id, onBack = { backStack.removeLastOrNull() })
        }
    }
}
```

`rememberNavBackStack` 返回 `NavBackStack`（即 `SnapshotStateList<NavKey>`）。可直接操作它（`add` / `removeLastOrNull`），也可用 `NavController` 包装以使用 `push` / `pop` / `replace` / `popUntil`。

::: warning
`rememberNavBackStack` 是 `inline fun <reified T : NavKey>`。当你用单个具体 key 作种子时，请把路由**超类型**作为显式类型参数传入——`rememberNavBackStack<Route>(Route.Home)`——以保证整个密封层级可序列化。写成 `rememberNavBackStack(Route.Home)` 会把 `T` 推断为 `Route.Home`，之后 push 其它子类型（如 `Route.Detail`）在保存/恢复时会序列化失败。
:::

## 连续出入栈

由于整栈由单个 float 驱动，一次性 push 或 pop 多个 entry 会作为一段连续的运动播放，而不会塌缩成单次顶层交叉淡入：

```kotlin
// 一次 push 三个：animatedTop 经单一弹簧从 N 连续扫到 N+3。
backStack.add(Route.Detail("1"))
backStack.add(Route.Detail("2"))
backStack.add(Route.Detail("3"))

// pop 回到根：animatedTop 从 N 连续退到 0，反向连续运动。
while (backStack.size > 1) backStack.removeAt(backStack.lastIndex)
```

reconciler 把每次变化分类为 `Push` / `Pop` / `MultiPush(n)` / `MultiPop(n)` / `Replace` / `ReplaceAll`，并经 `NavTransitionScope.change` 暴露给转场，使你能对「连续 pop n 层」与「单次 pop」做不同动画。

## 转场

内置预设库 `NavTransitions`：

| 预设 | 描述 |
| :-- | :-- |
| `Cupertino`（默认） | iOS 横滑 + 下层视差 + 调暗 |
| `MiuixDefault` | 滑动 + 方角裁剪 + 调暗 |
| `Scale` | 缩放 |
| `Fade` | 淡入淡出 |
| `SharedAxisX` | Material 共享 X 轴 |
| `Modal` | 上滑、下层保留可见 |
| `None` | 瞬时无动画 |

在 `NavDisplay(transition = ...)` 上设全局默认，并用 `entry(transition = ...)` 按路由覆盖：

```kotlin
NavDisplay(backStack, transition = NavTransitions.Cupertino) {
    entry<Route.Home> { HomeScreen() }
    entry<Route.Detail>(transition = NavTransitions.Modal) { DetailScreen(it.id) }
}
```

## 滑动关闭方向

关闭顶层 entry 的交互式滑动手势，与转场**沿同一轴向**：水平滑动类转场用水平滑动关闭，底部上滑的 Modal 用下滑关闭。它是**按需开启**的——`dismissDirection` 默认为 `None`，且**所有内置预设默认都不开启**。请按路由用 `entry(swipeDismiss = ...)` 开启，或自定义转场时声明方向。

`NavSwipeDirection` 的取值是**物理屏幕方向**（手指往哪个方向移动），**不随布局方向镜像**，因此请按 LTR / RTL 选对方向：

| 方向 | 手指移动 |
| :-- | :-- |
| `LeftToRight` | 向右 —— LTR 下的返回滑动 |
| `RightToLeft` | 向左 —— RTL 下的返回滑动 |
| `TopToBottom` | 向下 —— 底部上滑 Modal 的关闭 |
| `BottomToTop` | 向上 |
| `None`（默认） | 禁用 —— 仅经返回按钮 / 系统返回 pop |

一旦手势认领成功，它会在本次手势余下过程中独占指针（消费两个轴向），因此跨轴的轻微滑动既不会抢走它、也不会中途取消；在手指抬起前，页面内的点击 / 滚动都被屏蔽。可按路由设置或覆盖——包括用 `NavSwipeDirection.None` 让某路由仅按钮关闭：

```kotlin
import top.yukonga.miuix.kmp.nav.transition.NavSwipeDirection

NavDisplay(backStack) {
    // 在该路由上开启 LTR 返回滑动（默认关闭）。
    entry<Route.Home>(swipeDismiss = NavSwipeDirection.LeftToRight) { HomeScreen() }
    // 底部表单：下滑关闭。
    entry<Route.Sheet>(transition = NavTransitions.Modal, swipeDismiss = NavSwipeDirection.TopToBottom) { SheetScreen() }
    // 不设置（默认 None）：仅按钮关闭。
    entry<Route.Confirm> { ConfirmScreen() }
}
```

自定义转场仍可声明自然方向，供路由继承：`navGraphicsTransition(dismissDirection = NavSwipeDirection.LeftToRight) { ... }`。

## 自定义转场

读取原始 float 深度，自由写 `graphicsLayer`。该 block 在延迟读取的图层内执行，读 `relativeDepth` 不触发重组：

```kotlin
import top.yukonga.miuix.kmp.nav.transition.navGraphicsTransition

val myTransition = navGraphicsTransition { scope ->
    val d = scope.relativeDepth          // animatedTop - index
    translationX = -d * scope.layoutSize.width.toFloat()
    scaleX = 1f - 0.1f * d.coerceIn(0f, 1f)
    scaleY = scaleX
    cameraDistance = 16f * scope.density.density
}
```

`NavTransitionScope` 暴露 `relativeDepth`、`role`、`change`、`gesture`、`layoutSize`、`layoutDirection`、`density`。

转场同时拥有自己的 **scrim 曲线**：渲染在最顶层之下的全屏调暗遮罩由 `NavTransition.scrimFraction` 决定——曲线取自**上层**（即覆盖者）转场（与被覆盖层变换一致的边界归属规则），并以下方被覆盖层为参照求值（深度 0 = 完全显露，1 = 完全覆盖）——`NavDisplayEffects.dimAmount` 只封顶最大暗度。默认曲线随深度线性变化（下层显露得越多，调暗越浅）；经 `scrim` 参数传入自定义曲线，例如卡片式的「手势期间保持、提交后扫程内才淡出」：

```kotlin
val cardStyle = navGraphicsTransition(
    scrim = { scope ->
        val d = scope.relativeDepth.coerceIn(0f, 1f)
        val g = scope.gesture
        if (g != null) (d / (1f - g.progress).coerceAtLeast(0.01f)).coerceIn(0f, 1f) else d
    },
) { scope -> /* transform */ }
```

一个完全基于该公共 API 实现的平台级完整转场——居中缩放、贴边、带阻尼的纵向跟手、提交后飞出以及上述手势保持 scrim——见示例应用中的 `CrossActivityTransition`（`example/shared/src/commonMain/kotlin/navigation/CrossActivityTransition.kt`）。

## 正交效果

`NavDisplayEffects` 承载叠加在当前转场之上的横切效果——圆角裁剪、调暗封顶、转场期输入拦截与背景底色（`backdropColor`）：

```kotlin
NavDisplay(
    backStack = backStack,
    effects = NavDisplayEffects(
        enableCornerClip = true,
        dimAmount = 0.5f,
        blockInputDuringTransition = true,
    ),
) { /* ... */ }
```

## 手势返回

返回已内置，且与普通 pop **共用同一个 `animatedTop` 与同一套 `NavTransition`**——无需为预测返回单独维护一套动画。手势进行中，手指以 1:1 驱动 `animatedTop`（`snapTo`，不过插值器）；松手时按「速度优先、位置兜底」判定 commit 或 cancel，并把松手瞬时速度作为初速度交接给收敛弹簧，保证运动连续。

两个来源汇入它：**页面内滑动**（全平台的 Compose 手势，按上文[滑动关闭方向](#滑动关闭方向)区分方向）与**平台返回**（系统预测返回 / ESC / 自定义触发）。二者流入同一驱动。

各平台的**输入来源与语义不同**——运行时把它们归一到同一条返回流，但跟手观感并非处处一致：

| 平台 | 来源 | 语义 |
| :-- | :-- | :-- |
| Android | 系统预测返回 | **连续**的系统手势：progress + 侧滑边沿全程流式上报；手指 1:1 驱动 `animatedTop`，松手按速度交接 commit/cancel |
| iOS | 交互式边缘侧滑 | **连续**的左边缘拖拽（运行时内实现）；与 Android 相同的 1:1 驱动与速度交接 |
| Desktop | `ESC` 键 | **离散**、非交互：按键触发一次性 commit 直接进入收敛弹簧（无逐帧跟手 progress） |
| Web | 自定义来源 | **由调用方定义**：从你自己的触发源（如浏览器返回键 / 自定义按钮）喂入同一返回流；语义取决于来源 |

## 保存与恢复

`rememberNavBackStack` 经 `rememberSaveable` + 基于 `kotlinx.serialization` 的 Saver 持久化返回栈。

::: warning
key 必须 `@Serializable` 才能跨进程死亡恢复。非序列化 key **不会崩溃**——它会**退化为仅内存**：该 entry 在存活进程内仍可正常导航，但返回栈在进程死亡（Android 低内存杀进程等）后**不会**恢复。若需跨进程恢复，请确保每个路由都 `@Serializable`。
:::

## 向上一屏回传结果

v1 核心**不提供**内置结果通道（`NavController` 上没有 `navigateForResult` / `setResult`）。请把结果留在导航运行时之外，用你已有的状态机制传递，或自行在返回栈之上叠一层极小的结果总线。

推荐形态是在栈旁挂一个按 `requestKey` 寻址的总线——用 `MutableSharedFlow(replay = 1)` 或带缓冲的 `Channel`，确保 pop 前一刻发出的结果不会因调用方屏幕当时未在收集而丢失：

```kotlin
// navigateForResult(route, requestKey)：push 并为 requestKey 装一条通道。
// setResult(requestKey, value)：向通道 emit，然后 pop。
// observeResult(requestKey)：在调用方屏幕以 SharedFlow 收集结果。
```

把这套思路（或 `SavedStateHandle` 风格的持有者）移植到你自己的 app 层即可；它被有意排除在 `miuix-nav` v1 之外，以保持运行时是一个纯粹的深度驱动栈。

## 范围与扩展边界（v1）

`miuix-nav` v1 有意做成一个**单一、扁平的返回栈**。以下能力在 v1 中**明确不支持**，延后到 v2+；请勿提前依赖：

| 能力 | v1 状态 |
| :-- | :-- |
| 多窗格 / 列表-详情自适应布局 | 不支持（仅单栈） |
| Dialog / 底部表单 场景策略（覆盖型目的地） | 不支持 |
| 跨目的地的共享元素转场（SharedTransition） | 不支持 |
| 嵌套 `NavDisplay`（entry 内再套一个返回栈） | 不支持 |
| KSP / 注解式路由注册 | 不支持（用 `entry<T> { }` DSL 注册 entry） |
| `NavController` 内置结果通道 | 不支持（见上一节） |

这些边界的存在是为了让 v1 的 API 面保持精简、让连续深度模型保持唯一事实来源。它们已记录在册，留待 v2+。

## 与「导航支持」的关系

`miuix-nav` 是独立运行时，与 [`miuix-navigation3-ui`](/zh_CN/guide/navigation3) 并存。若需要连续深度运动与完全自定义的 float 驱动转场，选 `miuix-nav`；若你以 `androidx.navigation3` 为标准，选 `miuix-navigation3-ui`。
