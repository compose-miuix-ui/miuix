# GlassIconButton

`GlassIconButton` 是用于工具栏控件和悬浮操作的圆形玻璃按钮。在 [GlassTopAppBar](./glasstopappbar) 中，它继承顶栏的按钮表面。

<div style="position: relative; height: 240px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../../../compose/index.html?id=glassIconButton" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

::: warning 背景采样
采样区域应包含不透明背景，并排除玻璃表面自身。见[玻璃材质](/zh_CN/guide/glass#background-sampling)。
:::

## 引入

```kotlin
import top.yukonga.miuix.kmp.glass.GlassIconButton
```

## 基本用法

没有可用的背景采样源时，按钮使用 `fill`。内容居中显示，请在内容插槽中设置图标尺寸和颜色。

```kotlin
GlassIconButton(
    onClick = onAction,
    backdrop = backdrop,
) {
    Icon(
        imageVector = actionIcon,
        contentDescription = "搜索",
        modifier = Modifier.size(24.dp),
    )
}
```

## 属性

### GlassIconButton 属性

以下默认值适用于 `GlassTopAppBar` 之外的按钮。

| 属性名 | 类型 | 说明 | 默认值 | 是否必须 |
| --- | --- | --- | --- | --- |
| onClick | () -> Unit | 点击时触发的回调 | - | 是 |
| modifier | Modifier | 应用于按钮的修饰符 | Modifier | 否 |
| backdrop | Backdrop? | 背景采样源；顶栏非空的采样源优先 | null | 否 |
| surfaceAlpha | Float | 表面透明度乘数 | 1f | 否 |
| style | GlassStyle | 渲染样式，不会替换按钮材质 | GlassDefaults.Style | 否 |
| size | Dp | 按钮直径 | GlassTopAppBarDefaults.ButtonSize | 否 |
| shape | GlassShape | 按钮形状 | GlassShape(size / 2) | 否 |
| fill | Color | 没有背景采样源时使用的填充色 | GlassTopAppBarDefaults.buttonFill() | 否 |
| stroke | GlassStroke? | 表面描边，null 表示不绘制 | GlassTopAppBarDefaults.buttonStroke() | 否 |
| shadow | GlassShadow? | 按钮阴影，null 表示不绘制 | GlassShadows.Regular | 否 |
| content | @Composable () -> Unit | 居中显示的按钮内容 | - | 是 |

按钮请求边长为 `size` 的正方形，不额外添加最小触摸目标。请根据无障碍需求选择尺寸，并避免裁剪阴影。

### GlassTopAppBarDefaults 对象 {#defaults}

`GlassIconButton` 与顶栏共用默认配置。以下是按钮相关的值，完整对象见 [GlassTopAppBar](./glasstopappbar#glasstopappbardefaults)。

#### 常量

| 常量名 | 类型 | 说明 | 默认值 |
| --- | --- | --- | --- |
| ButtonSize | Dp | 按钮直径 | 44.dp |
| PressedContentAlpha | Float | 按下时的内容透明度 | 0.6f |

#### 方法

| 方法名 | 返回类型 | 说明 |
| --- | --- | --- |
| buttonFill() | Color | 浅色模式为白色，深色模式为 `#FF2C2C2C` |
| buttonMaterial() | GlassMaterial | 随主题选择的 `GlassMaterials.puredThinGlass` |
| buttonStroke() | GlassStroke | 浅色模式为 `GlassStrokes.SmallLight`，深色模式为 `SmallDark` |
| buttonPressedOverlay() | Color | 浅色模式为透明度 0.10 的黑色，深色模式为透明度 0.14 的白色 |

## 进阶用法

### 继承顶栏材质

```kotlin
GlassTopAppBar(
    title = "资料库",
    isContentScrolled = listState.canScrollBackward,
    backdrop = backdrop,
    actions = {
        GlassIconButton(onClick = onAction) {
            Icon(actionIcon, contentDescription = "搜索")
        }
    },
)
```

在 `GlassTopAppBar` 内，按钮继承顶栏的外观和背景采样源。在顶栏外使用时（包括导航栏尾部操作），需显式传入背景采样源。

### 自定义按下缩放 {#按下反馈与-glasspress}

自定义可点击表面时，让 `glassPress` 和 `clickable` 共用一个交互源：

```kotlin
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import top.yukonga.miuix.kmp.glass.glassPress

val interactionSource = remember { MutableInteractionSource() }

Text(
    text = "打开",
    modifier = Modifier
        .glassPress(interactionSource)
        .clickable(
            interactionSource = interactionSource,
            indication = null,
            onClick = onAction,
        )
        .padding(16.dp),
)
```

`Modifier.glassPress` 会为绘制应用缩放动画，不提供点击处理或玻璃材质。

| 属性名 | 类型 | 说明 | 默认值 | 是否必须 |
| --- | --- | --- | --- | --- |
| interactionSource | InteractionSource | 按下交互的来源 | - | 是 |
| enabled | Boolean | 是否应用缩放，不会禁用点击 | true | 否 |

### 作为弹窗锚点

通过 `Modifier.glassPopupAnchor(anchor, cornerRadius)` 将按钮连接到 [GlassTransformPopup](./glasstransformpopup#anchor-api)。
