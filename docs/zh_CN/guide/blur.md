# 模糊效果

`miuix-blur` 是一个独立的 Compose Multiplatform 模糊效果库。它通过 Modifier 扩展提供背景模糊、颜色混合和纹理效果。支持 Android、Desktop (JVM)、iOS、macOS 和 Web (WasmJs/Js) 平台。

::: warning 注意
在 Android 上，模糊效果需要 API 31 (Android 12) 或更高版本。通过 RuntimeShader 实现的自定义混合模式需要 API 33 (Android 13) 或更高版本。在不支持的 API 级别上，模糊 Modifier 将作为空操作。
:::

## 配置

在项目中添加 `miuix-blur` 依赖：

```kotlin
kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation("top.yukonga.miuix.kmp:miuix-blur:<version>")
        }
    }
}
```

Android 项目：

```kotlin
dependencies {
    implementation("top.yukonga.miuix.kmp:miuix-blur-android:<version>")
}
```

## 平台支持

| 平台 | RenderEffect（模糊） | RuntimeShader（自定义混合模式） |
| --- | --- | --- |
| Android | API 31+ | API 33+ |
| Desktop (JVM) | 支持 | 支持 |
| iOS / macOS | 支持 | 支持 |
| WasmJs / Js | 支持 | 支持 |

可在运行时检查平台能力：

```kotlin
import top.yukonga.miuix.kmp.blur.isRenderEffectSupported
import top.yukonga.miuix.kmp.blur.isRuntimeShaderSupported

val canBlur = isRenderEffectSupported()
val canUseCustomBlendModes = isRuntimeShaderSupported()
```

## 基本用法

应用背景模糊分为三步：

1. 创建 `LayerBackdrop` 以捕获背景内容
2. 在内容容器上应用 `Modifier.layerBackdrop()`
3. 在模糊表面上应用 `Modifier.textureBlur()`

```kotlin
import top.yukonga.miuix.kmp.blur.BlurColors
import top.yukonga.miuix.kmp.blur.BlurDefaults
import top.yukonga.miuix.kmp.blur.layerBackdrop
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
import top.yukonga.miuix.kmp.blur.textureBlur

// 第 1 步：创建 LayerBackdrop
val backdrop = rememberLayerBackdrop()

// 第 2 步：捕获背景内容
Box(
    modifier = Modifier
        .fillMaxSize()
        .layerBackdrop(backdrop) // 捕获此 Box 的内容
) {
    // 背景内容（如图片、渐变或页面内容）
    Image(
        painter = painterResource(Res.drawable.background),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
}
```

::: tip 背景色
`layerBackdrop` 仅捕获其所在 composable 绘制的内容，**不包含**父级 composable 的背景（例如 Scaffold 的 Surface）。如果捕获的内容存在透明区域（如无背景的文字），模糊时颜色会扩散到透明像素中，产生明显的色块。

为避免此问题，请在 `onDraw` lambda 中绘制不透明背景：

```kotlin
val backgroundColor = MaterialTheme.colorScheme.surface
val backdrop = rememberLayerBackdrop {
    drawRect(backgroundColor) // 确保捕获到不透明背景
    drawContent()
}
```

:::

```kotlin

// 第 3 步：在叠加层上应用模糊
Box(
    modifier = Modifier
        .size(200.dp)
        .textureBlur(
            backdrop = backdrop,
            shape = RoundedCornerShape(16.dp)
        )
) {
    Text(
        text = "模糊卡片",
        modifier = Modifier.padding(16.dp)
    )
}
```

## 颜色配置

使用 `BlurColors` 在模糊效果之上应用颜色调整和混合图层：

```kotlin
import top.yukonga.miuix.kmp.blur.BlendColorEntry
import top.yukonga.miuix.kmp.blur.BlurBlendMode
import top.yukonga.miuix.kmp.blur.BlurColors

val colors = BlurColors(
    blendColors = listOf(
        BlendColorEntry(
            color = Color.White.copy(alpha = 0.3f),
            mode = BlurBlendMode.SrcOver
        )
    ),
    brightness = 0.05f,  // 范围：[-1, 1]，0 = 无变化
    contrast = 1.1f,     // 倍数，1 = 无变化
    saturation = 1.2f    // 倍数，1 = 无变化
)

Box(
    modifier = Modifier
        .textureBlur(
            backdrop = backdrop,
            shape = RoundedCornerShape(16.dp),
            colors = colors
        )
) {
    // 内容
}
```

也可以使用可组合辅助函数 `BlurDefaults.blurColors()`，它会自动 remember 配置：

```kotlin
val colors = BlurDefaults.blurColors(
    blendColors = listOf(
        BlendColorEntry(Color.White.copy(alpha = 0.2f), BlurBlendMode.Screen)
    ),
    brightness = 0f,
    contrast = 1f,
    saturation = 1.2f
)
```

## 混合模式

`BlurBlendMode` 提供了 40 多种混合模式，用于模糊背景上的颜色混合。

### 标准模式

标准 SkBlendMode 值（0-28），使用与 Skia 兼容的预乘 Alpha 公式：

| 模式 | 说明 |
| --- | --- |
| `SrcOver` | 普通 Alpha 合成（默认） |
| `Screen` | 提亮混合 |
| `Multiply` | 变暗混合 |
| `Overlay` | 增强对比度混合 |
| `SoftLight` | 柔和对比度调整 |
| `ColorDodge` | 通过降低对比度提亮 |
| `ColorBurn` | 通过增加对比度变暗 |
| `Darken` | 保留较暗像素 |
| `Lighten` | 保留较亮像素 |
| `Difference` | 绝对差值 |
| `Exclusion` | 类似 Difference 但对比度较低 |
| `Hue` | 应用源色相 |
| `Saturation` | 应用源饱和度 |
| `Color` | 应用源色相和饱和度 |
| `Luminosity` | 应用源亮度 |

### 自定义模式

自定义模式（100+），实现了 Lab 色彩空间运算、线性光混合等（需要 `isRuntimeShaderSupported()`）：

| 模式 | 说明 |
| --- | --- |
| `LinearLight` | 线性光混合 |
| `LinearLightWithGreyscale` | 带灰度调制的线性光 |
| `LinearLightLab` | Lab 色彩空间中的线性光 |
| `LabLightenWithGreyscale` | 带灰度调制的 Lab 提亮 |
| `LabDarkenWithGreyscale` | 带灰度调制的 Lab 变暗 |
| `Lab` | Lab 颜色映射 |
| `MiColorDodge` | 增强版颜色减淡 |
| `MiColorBurn` | 增强版颜色加深 |
| `PlusDarker` | 带 Alpha 合成的加暗 |
| `PlusLighter` | 带 Alpha 合成的加亮 |
| `AlphaBlend` | 带子级调制的 Alpha 混合 |
| `MiSaturation` | 饱和度调整 |
| `MiBrightness` | 亮度调整 |
| `MiLuminance` | 明度曲线调整 |

### 示例：多层混合

```kotlin
val colors = BlurColors(
    blendColors = listOf(
        BlendColorEntry(Color(0x40FFFFFF), BlurBlendMode.Screen),
        BlendColorEntry(Color(0x20000000), BlurBlendMode.Overlay)
    ),
    saturation = 1.5f
)
```

## 进阶用法

### 独立 X/Y 模糊半径

可对水平和垂直方向应用不同的模糊强度：

```kotlin
Box(
    modifier = Modifier
        .textureBlur(
            backdrop = backdrop,
            shape = RoundedCornerShape(16.dp),
            blurRadiusX = 100f,
            blurRadiusY = 20f
        )
) {
    // 具有方向性模糊的内容
}
```

### 前景模糊（内容遮罩）

使用 `contentBlendMode` 创建前景模糊效果，内容的 Alpha 通道将作为模糊区域的遮罩：

```kotlin
import androidx.compose.ui.graphics.BlendMode as ComposeBlendMode

Text(
    text = "磨砂文字",
    style = MiuixTheme.textStyles.title1,
    modifier = Modifier
        .textureBlur(
            backdrop = backdrop,
            shape = RectangleShape,
            blurRadius = 150f,
            contentBlendMode = ComposeBlendMode.DstIn // 内容 Alpha 遮罩模糊
        )
)
```

### 噪声抖动

`noiseCoefficient` 参数控制应用于模糊结果的抗条纹噪声。默认值为 `0.0045f`，设为 `0f` 可禁用：

```kotlin
Box(
    modifier = Modifier
        .textureBlur(
            backdrop = backdrop,
            shape = RoundedCornerShape(16.dp),
            noiseCoefficient = 0f // 禁用噪声抖动
        )
)
```

### 低级 API：drawBackdrop

如需完全控制效果管线，可使用 `Modifier.drawBackdrop()` 配合 `BackdropEffectScope`：

```kotlin
import top.yukonga.miuix.kmp.blur.drawBackdrop

Box(
    modifier = Modifier
        .drawBackdrop(
            backdrop = backdrop,
            shape = { RoundedCornerShape(16.dp) },
            effects = {
                // 应用高斯模糊
                blur(radius = 60f)
                // 调整颜色
                colorControls(
                    brightness = 0.05f,
                    contrast = 1.1f,
                    saturation = 1.3f
                )
            }
        )
) {
    // 内容
}
```

#### BackdropEffectScope 扩展函数

| 扩展函数 | 说明 |
| --- | --- |
| `blur(radius, edgeTreatment)` | 应用高斯模糊 |
| `colorFilter(colorFilter)` | 应用 ColorFilter |
| `colorControls(brightness, contrast, saturation)` | 调整亮度、对比度和饱和度 |
| `effect(effect)` | 链接任意 RenderEffect |
| `runtimeShaderEffect(key, shaderString, uniformShaderName, block)` | 应用自定义 AGSL/SkSL 运行时着色器 |

#### BackdropEffectScope 属性

| 属性 | 类型 | 说明 |
| --- | --- | --- |
| `size` | Size | 当前渲染尺寸 |
| `layoutDirection` | LayoutDirection | 当前布局方向 |
| `shape` | Shape | 当前裁剪形状 |
| `padding` | Float | 模糊溢出的额外内边距 |
| `renderEffect` | RenderEffect? | 累积的效果链 |
| `downscaleFactor` | Int | 降采样系数（1、2、4、8、16） |
| `noiseCoefficient` | Float | 全分辨率噪声抖动系数 |

## 属性

### textureBlur / textureEffect 参数

| 参数名 | 类型 | 说明 | 默认值 | 是否必须 |
| --- | --- | --- | --- | --- |
| backdrop | Backdrop | 提供模糊背景内容的 Backdrop | - | 是 |
| shape | Shape | 模糊区域裁剪的形状 | - | 是 |
| blurRadius | Float | 模糊半径（dp），库内部自动转换为像素。限制在 [0, 150] 范围内 | 20f | 否 |
| blurRadiusX | Float | 水平模糊半径（dp，独立半径重载） | - | 是* |
| blurRadiusY | Float | 垂直模糊半径（dp，独立半径重载） | - | 是* |
| noiseCoefficient | Float | 抗条纹噪声抖动系数，0 表示禁用 | 0.0045f | 否 |
| colors | BlurColors | 模糊后应用的颜色调整和混合图层 | BlurColors() | 否 |
| contentBlendMode | BlendMode? | 内容在模糊上方合成的混合模式 | null | 否 |
| enabled | Boolean | 是否启用模糊，为 false 时跳过效果并正常绘制内容 | true | 否 |

\* 仅在独立半径重载中必须。

### BlurColors 属性

| 属性名 | 类型 | 说明 | 默认值 |
| --- | --- | --- | --- |
| blendColors | List\<BlendColorEntry\> | 在模糊背景上按顺序混合的颜色列表 | emptyList() |
| brightness | Float | 亮度调整，范围 [-1, 1] | 0f |
| contrast | Float | 对比度倍数 | 1f |
| saturation | Float | 饱和度倍数 | 1f |

### BlurDefaults

| 常量 | 类型 | 说明 | 值 |
| --- | --- | --- | --- |
| BlurRadius | Float | 默认模糊半径（dp） | 20f |
| NoiseCoefficient | Float | 默认噪声抖动系数 | 0.0045f |
| MaxBlurRadius | Float | 最大允许模糊半径（dp） | 150f |

| 方法 | 返回类型 | 说明 |
| --- | --- | --- |
| blurColors() | BlurColors | 创建被 remember 的 BlurColors 实例 |

## 边缘高光

`highlight` modifier 沿圆角形状绘制一条带两路定向光的玻璃质感边缘。配合 `Modifier.textureBlur` 使用可营造"模糊背景上的发光边缘"效果。

```kotlin
import androidx.compose.foundation.shape.RoundedCornerShape
import top.yukonga.miuix.kmp.blur.highlight.Highlight
import top.yukonga.miuix.kmp.blur.highlight.highlight

Box(
    modifier = Modifier
        .size(200.dp, 100.dp)
        .textureBlur(
            backdrop = backdrop,
            shape = RoundedCornerShape(24.dp),
        )
        .highlight(
            shape = RoundedCornerShape(24.dp),
            highlight = Highlight.GlassStrokeMiddleLight,
        ),
)
```

传给 `highlight` 的形状应与传给 `textureBlur` 的形状保持一致。传 `null`（或 `width = 0.dp` 的 `Highlight`）即可禁用。

::: warning 注意
边缘高光需要 `isRuntimeShaderSupported()` 支持。在不支持的平台或 API 级别上，modifier 会变为空操作。
:::

### 内置预设

提供六个预设，按卡片尺寸和主题选用：

| 预设 | innerBlurRadius | 视觉特征 |
| --- | --- | --- |
| `Highlight.GlassStrokeBigLight` | 3.5 dp | halo 最厚最柔 — 亮模式大卡片 |
| `Highlight.GlassStrokeMiddleLight` | 2.8 dp | 标准亮模式卡片（默认） |
| `Highlight.GlassStrokeSmallLight` | 2.6 dp | 紧凑、更锐利的亮模式卡片 |
| `Highlight.GlassStrokeBigDark` | 1.7 dp | halo 最薄 — 暗模式大卡片 |
| `Highlight.GlassStrokeMiddleDark` | 2.0 dp | 标准暗模式卡片 |
| `Highlight.GlassStrokeSmallDark` | 2.3 dp | 紧凑、最锐利的暗模式卡片 |

`Highlight.Default` 等价于 `GlassStrokeMiddleLight`。

### 自定义 BloomStroke

可基于 token 覆盖单个字段，或从零构建 `BloomStroke`：

```kotlin
import top.yukonga.miuix.kmp.blur.highlight.BloomStroke
import top.yukonga.miuix.kmp.blur.highlight.LightPosition
import top.yukonga.miuix.kmp.blur.highlight.LightSource

val custom = Highlight(
    width = 1.dp,
    alpha = 0.8f,
    style = BloomStroke(
        color = Color.White.copy(alpha = 0.05f),
        innerBlurRadius = 3.dp,
        primaryLight = LightSource(
            position = LightPosition(0.5f, 0.4f, -0.5f),
            intensity = 0.4f,
        ),
        secondaryLight = LightSource(
            position = LightPosition(0.5f, 0.85f, -0.5f),
            intensity = 0.3f,
        ),
    ),
)
```

`LightPosition` 使用归一化 UV 坐标，参考原点为 `(0.5, 0.7, 0)`。Shader 将 `(x − 0.5, y − 0.7, z)` 归一化后作为 3D 方向向量。负 `z` 把光放在表面平面之后（从内向外照亮边缘）— 所有内置预设均使用 `z < 0`。

两路光在 shader 中被限制到相反的半球：`primaryLight` 照亮圆角矩形上半部，`secondaryLight` 照亮下半部。`y < 0.7` 让光偏上；`y > 0.7` 让光偏下。

### 传感器驱动的视差

`rememberTiltLight` 通过设备旋转传感器实时偏移基准位置。Android 上会产生跟随设备倾斜的视差边缘；Desktop / iOS / macOS / Web 上 tilt 始终为零，光斑保持静止。

```kotlin
import top.yukonga.miuix.kmp.blur.highlight.rememberTiltLight

val baseStyle = Highlight.GlassStrokeMiddleLight.style as BloomStroke

val tiltPrimary = rememberTiltLight(
    basePosition = baseStyle.primaryLight.position,
    intensity = baseStyle.primaryLight.intensity,
    sensitivity = 0.15f,
)
val tiltSecondary = rememberTiltLight(
    basePosition = baseStyle.secondaryLight.position,
    intensity = baseStyle.secondaryLight.intensity,
    sensitivity = 0.12f,
)

val highlight = Highlight.GlassStrokeMiddleLight.copy(
    style = baseStyle.copy(
        primaryLight = tiltPrimary,
        secondaryLight = tiltSecondary,
    ),
)
```

`sensitivity` 控制每弧度倾斜对应的 UV 偏移量 — `0.1f` 表示在 1 rad 倾斜下光位置移动 10%。提高数值会放大视差幅度。

### 边缘高光属性

#### Highlight

| 属性 | 类型 | 说明 | 默认值 |
| --- | --- | --- | --- |
| width | Dp | 用 `style.color` 绘制的描边带宽度 | 0.8.dp |
| alpha | Float | 整体不透明度倍数，范围 [0, 1] | 1f |
| style | HighlightStyle | 着色模型（通常是 `BloomStroke`） | `HighlightStyle.Default` |

#### BloomStroke

| 属性 | 类型 | 说明 | 默认值 |
| --- | --- | --- | --- |
| color | Color | 描边带的基础颜色；alpha 驱动描边亮度 | `White.copy(alpha = 0.05f)` |
| blendMode | BlendMode | 高光叠加层的合成模式 | `BlendMode.Plus` |
| innerBlurRadius | Dp | 光晕从圆角向内延伸的深度 — 决定 halo 的厚度与柔度 | 2.8.dp |
| primaryLight | LightSource | 上半球定向光 | `LightPosition(0.5f, 0.5f, -0.5f)`，intensity 0.4 |
| secondaryLight | LightSource | 下半球定向光 | `LightPosition(0.5f, 0.8f, -0.5f)`，intensity 0.25 |

#### LightSource

| 属性 | 类型 | 说明 | 默认值 |
| --- | --- | --- | --- |
| position | LightPosition | 光的 UV 位置 | - |
| color | Color | 光颜色（颜色 alpha 折入贡献缩放） | `White` |
| intensity | Float | 亮度缩放，≥ 0 | 1f |

#### LightPosition

| 属性 | 类型 | 说明 |
| --- | --- | --- |
| x | Float | 归一化 UV x（参考原点：0.5） |
| y | Float | 归一化 UV y（参考原点：0.7） |
| z | Float | 有符号深度；负值把光放在表面之后 |

#### rememberTiltLight 参数

| 参数 | 类型 | 说明 | 默认值 |
| --- | --- | --- | --- |
| basePosition | LightPosition | 零倾斜时的位置 | - |
| color | Color | 光颜色 | `White` |
| intensity | Float | 亮度缩放 | 1f |
| sensitivity | Float | 每弧度倾斜对应的 UV 偏移量 | 0.1f |
