# 效果库

`miuix-effect` 是一个基于 `miuix-blur` 构建的 Compose Multiplatform 视觉效果库。它提供液态玻璃效果（折射、通透感、内阴影）、动画工具（阻尼拖拽、交互高光）、高光配置以及预定义的颜色混合令牌。支持 Android、Desktop (JVM)、iOS、macOS 和 Web (WasmJs/Js) 平台。

::: warning 注意
大多数效果需要 `isRuntimeShaderSupported()` 支持（Android API 33+ 或 Skiko 平台）。在不支持的平台上，效果会优雅降级（空操作或简化渲染）。
:::

## 配置

在项目中添加 `miuix-effect` 依赖：

```kotlin
kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation("top.yukonga.miuix.kmp:miuix-effect:<version>")
        }
    }
}
```

`miuix-effect` 会传递性地暴露 `miuix-blur` 的 API，因此无需为模糊类型（`Backdrop`、`BackdropEffectScope`、`Highlight` 等）单独添加依赖。

## 包结构

| 包 | 内容 |
| --- | --- |
| `top.yukonga.miuix.kmp.effect` | 向后兼容的重导出（已弃用 — 请使用 `effect.bg`） |
| `top.yukonga.miuix.kmp.effect.bg` | 背景渐变效果（`BgEffectBackground`、`DeviceType`）及内部着色器/画笔状态 |
| `top.yukonga.miuix.kmp.effect.liquid` | 液态玻璃效果（折射透镜、通透感、内阴影、组合背景） |
| `top.yukonga.miuix.kmp.effect.animation` | 动画工具（阻尼拖拽、交互高光） |
| `top.yukonga.miuix.kmp.effect.highlight` | 高光配置与传感器驱动的容器高光 |
| `top.yukonga.miuix.kmp.effect.blend` | 预定义颜色混合令牌列表 |

---

## 背景效果

`BgEffectBackground` 使用 GPU 加速的运行时着色器渲染动画渐变背景。有关背景模糊集成，请参阅已有的[模糊效果](blur.md)文档。

```kotlin
import top.yukonga.miuix.kmp.effect.bg.BgEffectBackground
import top.yukonga.miuix.kmp.effect.bg.DeviceType

BgEffectBackground(
    dynamicBackground = true,
    deviceType = DeviceType.PHONE,
    isDarkTheme = isSystemInDarkTheme(),
    surface = MiuixTheme.colorScheme.surface,
    modifier = Modifier.fillMaxWidth().height(300.dp),
) {
    Text("前景内容")
}
```

---

## 液态玻璃效果

### 折射透镜

`lens()` 对背景内容应用圆角矩形折射效果，将内容从边缘向内位移，模拟光线穿过玻璃的折射。

```kotlin
import top.yukonga.miuix.kmp.effect.liquid.lens
import top.yukonga.miuix.kmp.blur.drawBackdrop

Modifier.drawBackdrop(
    backdrop = backdrop,
    shape = { RoundedCornerShape(24.dp) },
    effects = {
        lens(
            refractionHeight = 24.dp.toPx(),
            refractionAmount = 24.dp.toPx(),
        )
    },
)
```

#### 色散

在折射边缘启用彩虹色散效果：

```kotlin
lens(
    refractionHeight = 24.dp.toPx(),
    refractionAmount = 24.dp.toPx(),
    chromaticAberration = 0.2f,  // 0.1 微妙，0.2 类似 Apple 风格，0.3+ 明显
)
```

#### lens 参数

| 参数 | 类型 | 说明 | 默认值 |
| --- | --- | --- | --- |
| refractionHeight | Float | 折射带高度（像素） | -（必须） |
| refractionAmount | Float | 最大位移量（像素） | -（必须） |
| depthEffect | Boolean | 径向深度模拟 | `false` |
| chromaticAberration | Float | 色散强度（`0` 禁用） | `0f` |

### 通透感

`vibrancy()` 将背景饱和度提升至 1.5 倍，呈现液态玻璃效果特有的鲜明"玻璃"外观。

```kotlin
import top.yukonga.miuix.kmp.effect.liquid.vibrancy

Modifier.drawBackdrop(
    backdrop = backdrop,
    shape = { RoundedCornerShape(16.dp) },
    effects = {
        vibrancy()
        blur(4.dp.toPx())
    },
)
```

### 内阴影

`innerShadow()` 在裁剪形状内部渲染柔和的阴影，模拟凹陷的深度感。

```kotlin
import top.yukonga.miuix.kmp.effect.liquid.InnerShadow
import top.yukonga.miuix.kmp.effect.liquid.innerShadow

Box(
    modifier = Modifier
        .size(200.dp, 100.dp)
        .innerShadow(shape = RoundedCornerShape(16.dp)) {
            InnerShadow(
                radius = 12.dp,
                color = Color.Black.copy(alpha = 0.15f),
                alpha = pressProgress,
            )
        }
)
```

#### InnerShadow 属性

| 属性 | 类型 | 说明 | 默认值 |
| --- | --- | --- | --- |
| radius | Dp | 阴影柔化的模糊半径 | `24.dp` |
| offset | DpOffset | 相对于边缘的方向偏移 | `DpOffset(0.dp, radius)` |
| color | Color | 阴影填充颜色 | `Color.Black.copy(alpha = 0.15f)` |
| alpha | Float | 整体不透明度倍数 | `1f` |
| blendMode | BlendMode | 合成模式 | `DrawScope.DefaultBlendMode` |

### 组合背景

`CombinedBackdrop` 将两个 `Backdrop` 实例叠加为单一采样源。适用于将基础内容背景与局部录制合并。

```kotlin
import top.yukonga.miuix.kmp.effect.liquid.rememberCombinedBackdrop

val baseBackdrop = rememberLayerBackdrop()
val tabsBackdrop = rememberLayerBackdrop()
val combined = rememberCombinedBackdrop(baseBackdrop, tabsBackdrop)

Modifier.drawBackdrop(
    backdrop = combined,
    shape = { pillShape },
    effects = { lens(...) },
)
```

---

## 动画工具

### 阻尼拖拽动画

`DampedDragAnimation` 为可拖拽 UI 元素提供多属性动画控制器，支持弹簧物理、按压/释放缩放过渡和速度追踪。

```kotlin
import top.yukonga.miuix.kmp.effect.animation.DampedDragAnimation

val dampedDrag = remember(animationScope) {
    DampedDragAnimation(
        animationScope = animationScope,
        initialValue = 0f,
        valueRange = 0f..3f,
        visibilityThreshold = 0.001f,
        initialScale = 1f,
        pressedScale = 1.4f,
        onDragStopped = { animateToValue(targetValue.roundToInt().toFloat()) },
        onDrag = { _, dragAmount -> updateValue(targetValue + dragAmount.x) },
    )
}

Box(
    modifier = Modifier
        .then(dampedDrag.modifier)
        .graphicsLayer {
            scaleX = dampedDrag.scaleX
            scaleY = dampedDrag.scaleY
        }
)
```

#### DampedDragAnimation 属性

| 属性 | 类型 | 说明 |
| --- | --- | --- |
| value | Float | 当前动画位置 |
| targetValue | Float | 正在动画趋向的目标位置 |
| pressProgress | Float | 按压状态 `[0, 1]` |
| scaleX | Float | 当前水平缩放 |
| scaleY | Float | 当前垂直缩放 |
| velocity | Float | 归一化速度 |

#### DampedDragAnimation 参数

| 参数 | 类型 | 说明 | 默认值 |
| --- | --- | --- | --- |
| animationScope | CoroutineScope | 动画协程的作用域 | -（必须） |
| initialValue | Float | 初始位置 | -（必须） |
| valueRange | ClosedRange\<Float\> | 允许的位置范围 | -（必须） |
| visibilityThreshold | Float | 最小可见变化量 | -（必须） |
| initialScale | Float | 静止时的缩放值 | -（必须） |
| pressedScale | Float | 按压时的缩放值 | -（必须） |
| canDrag | (Offset) -> Boolean | 拖拽接受谓词 | `{ true }` |
| onDragStarted | (Offset) -> Unit | 拖拽开始回调 | -（必须） |
| onDragStopped | () -> Unit | 拖拽结束回调 | -（必须） |
| onDrag | (IntSize, Offset) -> Unit | 拖拽事件回调 | -（必须） |

### 交互高光

`InteractiveHighlight` 渲染跟随指针位置的触摸响应式径向聚光灯，强度由弹簧动画的按压进度驱动。

```kotlin
import top.yukonga.miuix.kmp.effect.animation.InteractiveHighlight

val highlight = remember(animationScope) {
    InteractiveHighlight(
        animationScope = animationScope,
        position = { size, _ -> Offset(size.width / 2f, size.height / 2f) },
    )
}

Box(
    modifier = Modifier
        .then(highlight.modifier)       // 绘制叠加层
        .then(highlight.gestureModifier) // 触摸处理
)
```

---

## 高光配置

### HighlightConfig

`HighlightConfig` 将容器尺寸类别映射到对应的 `Highlight` 预设：

```kotlin
import top.yukonga.miuix.kmp.effect.highlight.HighlightConfig

val highlight = HighlightConfig.get(
    container = HighlightConfig.Container.Medium,
    isDark = isSystemInDarkTheme(),
)
```

#### 容器尺寸

| 容器 | 视觉场景 | 预设（亮色 / 暗色） |
| --- | --- | --- |
| Disabled | 无高光 | `null` |
| Large | 全宽卡片、对话框 | `GlassStrokeBigLight` / `GlassStrokeBigDark` |
| Medium | 标准卡片、底部弹窗 | `GlassStrokeMiddleLight` / `GlassStrokeMiddleDark` |
| Small | 标签、紧凑按钮 | `GlassStrokeSmallLight` / `GlassStrokeSmallDark` |

### 传感器驱动的容器高光

`rememberContainerHighlight` 将 `HighlightConfig` 与设备倾斜传感器数据结合，实现视差照明效果：

```kotlin
import top.yukonga.miuix.kmp.effect.highlight.rememberContainerHighlight

val highlight = rememberContainerHighlight(
    container = HighlightConfig.Container.Medium,
    isDark = isSystemInDarkTheme(),
    tiltDriven = true,
    primarySensitivity = 0.15f,
    secondarySensitivity = 0.12f,
)

Modifier.textureBlur(
    backdrop = backdrop,
    shape = RoundedCornerShape(16.dp),
    highlight = highlight,
)
```

---

## 颜色混合令牌

`ColorBlendToken` 提供 30 多种预定义的混合配置，按系列和权重组织，同时支持亮色和暗色主题。

### 系列概述

| 系列 | 风格特征 | 典型用途 |
| --- | --- | --- |
| Info | 中性灰色 | 信息性表面 |
| Colored | 使用 Overlay/SoftLight 着色 | 色彩丰富的表面 |
| Pured | 使用 ColorBurn/HardLight 的高对比度 | 大胆、饱和的表面 |
| Overlay | 基于明度 | 半透明叠加层 |

### 权重

| 权重 | 不透明度 | 说明 |
| --- | --- | --- |
| Extra_Thin | 极低 | 几乎不可见的着色 |
| Thin | 轻微 | 微妙的存在感 |
| Regular | 标准 | 平衡的不透明度 |
| Thick | 较强 | 高存在感 |
| Extra_Thick | 最大 | 接近不透明 |

### 用法

```kotlin
import top.yukonga.miuix.kmp.effect.blend.ColorBlendToken

val colors = BlurColors(
    blendColors = if (isDark) {
        ColorBlendToken.Colored_Regular_Dark
    } else {
        ColorBlendToken.Colored_Regular_Light
    },
)

Modifier.textureBlur(
    backdrop = backdrop,
    shape = RoundedCornerShape(16.dp),
    colors = colors,
)
```

### 可用令牌

| 令牌 | 亮色 | 暗色 |
| --- | --- | --- |
| Info_Extra_Thin | `Info_Extra_Thin_Light` | `Info_Extra_Thin_Dark` |
| Info_Thin | `Info_Thin_Light` | `Info_Thin_Dark` |
| Info_Regular | `Info_Regular_Light` | `Info_Regular_Dark` |
| Info_Thick | `Info_Thick_Light` | `Info_Thick_Dark` |
| Info_Colored_Regular | `Info_Colored_Regular` | （相同） |
| Colored_Extra_Thin | `Colored_Extra_Thin_Light` | `Colored_Extra_Thin_Dark` |
| Colored_Thin | `Colored_Thin_Light` | `Colored_Thin_Dark` |
| Colored_Regular | `Colored_Regular_Light` | `Colored_Regular_Dark` |
| Colored_Thick | `Colored_Thick_Light` | `Colored_Thick_Dark` |
| Colored_Extra_Thick | `Colored_Extra_Thick_Light` | `Colored_Extra_Thick_Dark` |
| Pured_Extra_Thin | `Pured_Extra_Thin_Light` | `Pured_Extra_Thin_Dark` |
| Pured_Thin | `Pured_Thin_Light` | `Pured_Thin_Dark` |
| Pured_Regular | `Pured_Regular_Light` | `Pured_Regular_Dark` |
| Pured_Thick | `Pured_Thick_Light` | `Pured_Thick_Dark` |
| Pured_Extra_Thick | `Pured_Extra_Thick_Light` | `Pured_Extra_Thick_Dark` |
| Overlay_Extra_Thin | `Overlay_Extra_Thin_Light` | `Overlay_Extra_Thin_Dark` |
| Overlay_Thin | `Overlay_Thin_Light` | - |
| Overlay_Regular | `Overlay_Regular_Light` | - |
| Overlay_Thick | `Overlay_Thick_Light` | `Overlay_Thick_Dark` |
| Overlay_Extra_Thick | `Overlay_Extra_Thick_Light` | - |

---

## 完整示例：液态玻璃卡片

将多种效果组合到一个液态玻璃卡片中：

```kotlin
import top.yukonga.miuix.kmp.blur.drawBackdrop
import top.yukonga.miuix.kmp.blur.layerBackdrop
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
import top.yukonga.miuix.kmp.effect.liquid.innerShadow
import top.yukonga.miuix.kmp.effect.liquid.lens
import top.yukonga.miuix.kmp.effect.liquid.InnerShadow
import top.yukonga.miuix.kmp.effect.liquid.vibrancy
import top.yukonga.miuix.kmp.effect.highlight.rememberContainerHighlight
import top.yukonga.miuix.kmp.effect.highlight.HighlightConfig

val backdrop = rememberLayerBackdrop()
val highlight = rememberContainerHighlight(
    container = HighlightConfig.Container.Medium,
    isDark = isSystemInDarkTheme(),
    tiltDriven = true,
)

Box(
    modifier = Modifier
        .layerBackdrop(backdrop)
        .fillMaxSize()
) {
    // 背景内容
}

Box(
    modifier = Modifier
        .size(200.dp, 100.dp)
        .drawBackdrop(
            backdrop = backdrop,
            shape = { RoundedCornerShape(24.dp) },
            effects = {
                vibrancy()
                blur(4.dp.toPx())
                lens(
                    refractionHeight = 20.dp.toPx(),
                    refractionAmount = 20.dp.toPx(),
                    chromaticAberration = 0.15f,
                )
            },
            highlight = { highlight },
        )
        .innerShadow(
            shape = RoundedCornerShape(24.dp),
        ) {
            InnerShadow(
                radius = 8.dp,
                color = Color.Black.copy(alpha = 0.1f),
            )
        }
) {
    Text("液态玻璃卡片")
}
```
