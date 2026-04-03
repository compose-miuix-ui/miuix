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
import top.yukonga.miuix.kmp.theme.miuixShape

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
            shape = miuixShape(16.dp)
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
            shape = miuixShape(16.dp),
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
            shape = miuixShape(16.dp),
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
            shape = miuixShape(16.dp),
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
            shape = { miuixShape(16.dp) },
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
