# GlassIconButton

`GlassIconButton` 是用于工具栏控件和悬浮操作的圆形玻璃按钮。在 [GlassTopAppBar](./glasstopappbar) 中，它继承顶栏材质并共享材质过渡。

<div style="position: relative; height: 240px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../../../compose/index.html?id=glassIconButton" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

::: warning 背景采样
使用背景采样时，`backdrop` 应记录组件后方的不透明内容，不能包含使用该采样的玻璃表面。配置方式见[玻璃材质](/zh_CN/guide/glass#background-sampling)。
:::

## 引入

```kotlin
import top.yukonga.miuix.kmp.glass.GlassIconButton
```

## 基本用法

没有可用的背景采样源时，按钮使用 `fill` 填充。

`actionIcon` 是 `ImageVector`，`onAction` 是页面的操作回调：

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

内容居中显示。请按需设置图标尺寸和颜色，按钮不会强制设置这些值。

## 组件状态

### 按下状态

按下时，按钮以动画显示覆盖色，并将内容透明度降低到 `0.6f`，不会应用按下缩放。组件没有 `enabled`、`holdDownState` 或 `interactionSource` 参数。

`surfaceAlpha` 只影响表面，不影响图标或点击处理。设为零不会禁用或移除按钮。

## 属性

### GlassIconButton 属性

以下默认值适用于 `GlassTopAppBar` 之外的按钮。

| 属性名 | 类型 | 说明 | 默认值 | 是否必须 |
| --- | --- | --- | --- | --- |
| onClick | () -> Unit | 点击时触发的回调 | - | 是 |
| modifier | Modifier | 应用于按钮的修饰符 | Modifier | 否 |
| backdrop | Backdrop? | 背景采样源；顶栏非空的采样源优先 | null | 否 |
| surfaceAlpha | Float | 表面透明度乘数，与顶栏 alpha 相乘后限制到 0–1 | 1f | 否 |
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

在顶栏内，`style`、`size`、`shape`、`fill`、`stroke` 和 `shadow` 默认使用顶栏的值，也可以显式覆盖。顶栏的空描边或空阴影仍保持为空。按钮还共享顶栏的背景采样源、材质与过渡，阴影跟随顶栏的滚动渐变。保留 `surfaceAlpha = 1f` 即可使用共享过渡，不额外叠加透明度乘数。

[GlassNavigationBar](./glassnavigationbar) 的尾部操作不会继承此材质，需要显式传入背景采样源和尺寸。

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

`Modifier.glassPress` 返回用于绘制缩放动画的 `Modifier`，不添加点击处理或玻璃材质。

| 属性名 | 类型 | 说明 | 默认值 | 是否必须 |
| --- | --- | --- | --- | --- |
| interactionSource | InteractionSource | 按下交互的来源 | - | 是 |
| enabled | Boolean | 是否应用缩放，不会禁用点击 | true | 否 |

独立的 `glassPressScale` 方法返回表面按住时的 `Float` 缩放比例：

| 属性名 | 类型 | 说明 | 默认值 | 是否必须 |
| --- | --- | --- | --- | --- |
| shorterSidePx | Float | 表面短边的像素值 | - | 是 |
| density | Float | 每 dp 对应的像素数 | - | 是 |

短边非正时返回 `1f`，否则返回 `max((shorterSidePx - 10f * density) / shorterSidePx, 0.9f)`。

### 作为弹窗锚点

附加 `Modifier.glassPopupAnchor(anchor)`，即可向 [GlassTransformPopup](./glasstransformpopup#anchor-api) 的折叠表面共享按钮解析后的背景采样源和材质。
