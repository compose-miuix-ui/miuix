---
title: GlassBottomSheet
requiresScaffoldHost: true
prerequisites:
  - 必须在 `Scaffold` 中使用以提供 `MiuixPopupHost`
  - 未在 `Scaffold` 中使用将导致弹出内容无法渲染
  - 支持多个嵌套或并列的 `Scaffold`，无需额外配置
hostComponent: Scaffold
popupHost: MiuixPopupHost
---

# GlassBottomSheet

`GlassBottomSheet` 是从屏幕边缘滑入的玻璃底部抽屉。它支持标题栏、关闭手势、预测性返回和嵌套滚动。

<div style="position: relative; height: 480px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../../../compose/index.html?id=glassBottomSheet" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

::: danger 使用前提
此组件依赖 `Scaffold` 提供的 `MiuixPopupHost` 以显示弹出内容。必须在 `Scaffold` 中使用，否则弹出内容无法正常渲染。
:::

::: warning 背景采样
使用背景采样时，`backdrop` 应记录组件后方的不透明内容，不能包含使用该采样的玻璃表面。配置方式见[玻璃材质](/zh_CN/guide/glass#background-sampling)。
:::

## 引入

```kotlin
import top.yukonga.miuix.kmp.glass.GlassBottomSheet
import top.yukonga.miuix.kmp.glass.GlassBottomSheetDefaults
import top.yukonga.miuix.kmp.glass.glassBottomSheetSurface
import top.yukonga.miuix.kmp.blur.layerBackdrop
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
```

## 基本用法

页面 `Box` 由 `backdrop` 记录，`Scaffold` 在该采样范围之外承载浮层。

```kotlin
val backdrop = rememberLayerBackdrop()
var showSheet by remember { mutableStateOf(false) }

Scaffold { padding ->
    Box(
        Modifier.fillMaxSize()
            .layerBackdrop(backdrop)
            .background(MiuixTheme.colorScheme.background)
            .padding(padding),
    ) {
        TextButton(text = "显示抽屉", onClick = { showSheet = true })
    }

    GlassBottomSheet(
        show = showSheet,
        onDismissRequest = { showSheet = false },
        backdrop = backdrop,
        title = "详细信息",
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .navigationBarsPadding()
                .padding(bottom = 16.dp),
        ) {
            repeat(24) { index -> Text("项目 ${index + 1}") }
        }
    }
}
```

关闭时仍须保留组件调用，直到离场动画完成。不要使用 `if (show)` 包裹调用。

浮层可能覆盖页面栏时，应将这些栏包含在 `backdrop` 中，但排除弹窗宿主及遮罩。backdrop 为 null 或运行时着色器不可用时使用 `containerColor`，应提供不透明回退色。

内容滚动与额外间距由调用方提供。若底部留白应随内容滚动，应将导航栏内边距放在可滚动内容内部。

## 属性

### GlassBottomSheet 属性

| 属性名 | 类型 | 说明 | 默认值 | 是否必须 |
| --- | --- | --- | --- | --- |
| onDismissRequest | () -> Unit | 允许的外部点击、拖动关闭或完成返回手势时调用；更新 `show` 以关闭 | - | 是 |
| show | Boolean | 抽屉的目标显示状态 | - | 是 |
| backdrop | Backdrop? | 不含弹窗的页面背景采样；null 使用不透明回退 | - | 是 |
| modifier | Modifier | 应用于抽屉面板的修饰符 | Modifier | 否 |
| enableWindowDim | Boolean | 绘制并采样动画遮罩 | true | 否 |
| defaultWindowInsetsPadding | Boolean | 应用输入法内边距；顶部安全高度限制始终保留，主体导航栏内边距由调用方提供 | true | 否 |
| allowDismiss | Boolean | 允许外部点击、拖动或返回关闭 | true | 否 |
| enableNestedScroll | Boolean | 在子内容滚动与抽屉拖动之间传递位移 | true | 否 |
| renderInRootScaffold | Boolean | 使用最外层 Scaffold；false 使用当前 Scaffold | true | 否 |
| title | String? | 标题栏标题 | null | 否 |
| startAction | (@Composable () -> Unit)? | 标题栏前侧操作 | null | 否 |
| endAction | (@Composable () -> Unit)? | 标题栏后侧操作 | null | 否 |
| cornerRadius | Dp | 顶角半径；底角保持直角 | BottomSheetDefaults.cornerRadius | 否 |
| sheetMaxWidth | Dp | 抽屉最大宽度 | BottomSheetDefaults.maxWidth | 否 |
| outsideMargin | DpSize | `width` 用作水平外边距；不应用 `height` | BottomSheetDefaults.outsideMargin | 否 |
| insideMargin | DpSize | `width` 用作水平内容内边距，`height` 用作底部内边距 | BottomSheetDefaults.insideMargin | 否 |
| dragHandleColor | Color | 应用按压透明度之前的拖动手柄颜色 | BottomSheetDefaults.dragHandleColor() | 否 |
| material | GlassMaterial | 模糊及有序颜色层 | GlassBottomSheetDefaults.material() | 否 |
| stroke | GlassStroke? | 辉光描边；null 移除描边 | GlassBottomSheetDefaults.stroke() | 否 |
| containerColor | Color | 不透明回退填充色 | GlassBottomSheetDefaults.containerColor() | 否 |
| dimColor | Color | 应用动画进度之前的遮罩颜色 | GlassBottomSheetDefaults.ScrimColor | 否 |
| onDismissFinished | (() -> Unit)? | 隐藏动画完成后调用；隐藏被取消时不调用 | null | 否 |
| content | @Composable () -> Unit | 抽屉主体内容 | - | 是 |

### glassBottomSheetSurface 属性 {#glassbottomsheetsurface}

`glassBottomSheetSurface` 返回 `@Composable BottomSheetSurfaceScope.() -> Modifier`，用于 `OverlayBottomSheet` 的 `surface` 插槽。尺寸、内边距、拖动手柄和手势由宿主管理；表面使用宿主圆角和遮罩。

| 属性名 | 类型 | 说明 | 默认值 | 是否必须 |
| --- | --- | --- | --- | --- |
| backdrop | Backdrop? | 排除抽屉宿主和遮罩的页面背景采样；null 使用回退 | - | 是 |
| material | GlassMaterial | 模糊及有序颜色层 | GlassBottomSheetDefaults.material() | 否 |
| stroke | GlassStroke? | 辉光描边；null 移除描边 | GlassBottomSheetDefaults.stroke() | 否 |
| containerColor | Color | 不透明回退填充色 | GlassBottomSheetDefaults.containerColor() | 否 |

### GlassBottomSheetDefaults 对象

#### 常量

| 常量名 | 类型 | 说明 | 值 |
| --- | --- | --- | --- |
| ScrimColor | Color | 宿主应用动画之前的遮罩颜色 | GlassDialogDefaults.ScrimColor（`Color(0x33000000)`） |

#### 方法

| 方法名 | 返回类型 | 说明 |
| --- | --- | --- |
| material() | GlassMaterial | 随主题变化的 `GlassMaterials.dialogGlass`，与 GlassDialog 共用 |
| stroke() | GlassStroke | 随主题选择 `GlassStrokes.MiddleLight` 或 `MiddleDark` 辉光描边 |
| containerColor() | Color | 用于不透明回退的主题背景色 |

## 进阶用法

### 为现有抽屉应用玻璃材质

已有 `OverlayBottomSheet` 管理布局与显示状态时，可以使用表面工厂。沿用基本示例的 `showSheet` 和不含弹窗的 `backdrop`：

```kotlin
OverlayBottomSheet(
    show = showSheet,
    onDismissRequest = { showSheet = false },
    title = "详细信息",
    dimColor = GlassBottomSheetDefaults.ScrimColor,
    surface = glassBottomSheetSurface(backdrop = backdrop),
) {
    Column(
        Modifier.verticalScroll(rememberScrollState())
            .navigationBarsPadding()
            .padding(bottom = 16.dp),
    ) {
        repeat(24) { index -> Text("项目 ${index + 1}") }
    }
}
```

不要将遮罩记录到 `backdrop` 中。设置 `enableWindowDim = false` 可禁用变暗效果。

### 关闭与系统内边距

`allowDismiss = false` 会阻止外部点击、拖动和返回关闭抽屉，调用方仍可直接更新 `show`。可滚动子内容需要向抽屉传递位移时，应保留 `enableNestedScroll = true`。

需要等待离场动画完成的操作可放入 `onDismissFinished`。若将 `show` 设回 `true` 取消隐藏，则不会调用。不要使用 `if (showSheet)` 包裹抽屉。

`defaultWindowInsetsPadding = true` 时添加输入法内边距，自行提供时可设为 `false`。抽屉仍受顶部安全内边距限制，主体内容的导航栏内边距由调用方负责。在嵌套 Scaffold 中，`renderInRootScaffold = false` 选择当前宿主，该宿主必须位于 `backdrop` 记录范围之外。
