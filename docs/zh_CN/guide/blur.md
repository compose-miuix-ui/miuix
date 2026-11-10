# 模糊效果

`miuix-blur` 是一个独立的 Compose Multiplatform 模糊效果库。它通过 Modifier 扩展提供背景模糊、颜色混合和纹理效果。支持 Android、Desktop (JVM)、iOS、macOS 和 Web (WasmJs/Js) 平台。

::: warning 注意
`miuix-blur` 在 Android 端需要 `minSdk` 33（Android 13）或更高。模糊、混合和
噪点效果依赖 API 33 起提供的 `RuntimeShader`。运行时能力检测不能降低
Android 构件的最低 SDK 要求。
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

## 运行时能力检查

可以使用下面的方法检查运行时效果支持情况。Android 上对应 `Build.VERSION.SDK_INT`
判断，Skiko 系（Desktop / iOS / macOS / Web）始终返回 `true`。

```kotlin
import top.yukonga.miuix.kmp.material.isRenderEffectSupported
import top.yukonga.miuix.kmp.material.isRuntimeShaderSupported

// API 32+（以及所有非 Android 目标）为 true。仅需要背景采样支架时（例如
// 通过 `colorFilter(...)` 链一个 ColorFilter）适用。
val backdropScaffoldSupported = isRenderEffectSupported()

// API 33+（以及所有非 Android 目标）为 true。任何调用 `blur(...)`、
// `blendColors(...)`、`noiseDither(...)`、`Modifier.textureBlur(...)`、
// 或自行构造 `RuntimeShader` 的路径都应被它门控。
val blurAndBlendSupported = isRuntimeShaderSupported()
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

## 渐进模糊

`Modifier.progressiveTextureBlur` 应用渐变背景模糊：模糊强度沿指定方向从满强度连续过渡到零，过渡带中段是真实的中等模糊，清晰端保持全分辨率像素级锐利。适合导航栏、边缘淡出等场景。

```kotlin
import top.yukonga.miuix.kmp.blur.ProgressiveBlur
import top.yukonga.miuix.kmp.blur.progressiveTextureBlur

Box(
    modifier = Modifier
        .fillMaxWidth()
        .height(200.dp)
        .progressiveTextureBlur(
            backdrop = backdrop,
            shape = RectangleShape,
            blurRadius = 20f,
            gradient = ProgressiveBlur.Top // 顶部最强，底部锐利
        )
) {
    // 内容
}
```

### 渐变方向与区间

`ProgressiveBlur` 用两点线性渐变描述过渡：沿 `angle`（角度，从 +X 轴顺时针）投影，模糊在 `startFraction` 处为满强度，在 `endFraction` 处降为零。常见的边缘淡出直接用预设，也可通过 `copy` 调整区间：

```kotlin
// 预设覆盖四个方向的边缘淡出
ProgressiveBlur.Top      // angle = 90°，顶部最强
ProgressiveBlur.Bottom   // angle = 270°，底部最强
ProgressiveBlur.Left     // angle = 0°，左侧最强
ProgressiveBlur.Right    // angle = 180°，右侧最强

// 自定义区间：前 30% 保持满强度，90% 处完全清晰
val gradient = ProgressiveBlur.Top.copy(startFraction = 0.3f, endFraction = 0.9f)

// 自定义衰减曲线：前半段半径变化明显，后半段留长而柔和的尾部
val frontLoaded = ProgressiveBlur.Top.copy(curve = 0.5f)
```

`colors` 与 `noiseCoefficient` 的用法与 [`textureBlur`](#颜色配置) 一致，但只作用于模糊区域并随模糊一起淡出——清晰端与周围内容无缝衔接，不会出现染色硬边。注意渐进模糊的 `noiseCoefficient` 默认为 `0f`（禁用）：噪点叠在模糊渐变上比叠在均匀模糊上更显眼。

::: warning 性能
多级模糊层已在降采样分辨率上求值，但像素级锐利的清晰端仍需每帧额外一个全分辨率覆盖 pass——GPU 带宽开销高于同面积的 `textureBlur`。渐进模糊适合用在栏、边缘过渡带上，不建议覆盖大面积区域。
:::

::: tip
在自定义 `drawBackdrop` 管线中，`progressiveTextureBlurEffect(...)` 在 `effects` 块内运行同样的预设链；需搭配 `drawBackdrop` 的 `progressiveGradient` 参数（传入同一 gradient）以保证清晰端真正锐利——`Modifier.progressiveTextureBlur` 已将两者接好。
:::

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

::: warning 像素空间 uniform 需按 `downscaleFactor` 缩放
当 `runtimeShaderEffect` 链接在 `blur`（或其他抬高 `downscaleFactor` 的效果）之后，backdrop layer 以 `1 / downscaleFactor` 的分辨率录制，shader 接收的 `coord` 也是该降采样后 layer 的像素坐标。任何描述像素空间距离的 uniform —— 例如 size、padding/offset、圆角半径、折射带宽等 —— 都必须在 `block` 内部除以 `downscaleFactor`，否则形状几何会落在 layer 范围之外，所有采样都会拿到透明黑色。
:::

#### BackdropEffectScope 属性

| 属性 | 类型 | 说明 |
| --- | --- | --- |
| `size` | Size | 当前渲染尺寸 |
| `layoutDirection` | LayoutDirection | 当前布局方向 |
| `shape` | Shape | 当前裁剪形状 |
| `padding` | Float | 模糊溢出的额外内边距 |
| `renderEffect` | RenderEffect? | 累积的效果链 |
| `downscaleFactor` | Int | 降采样系数（1、2、4、8、16） |
| `noiseCoefficient` | Float | 噪声抖动系数 |

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

### progressiveTextureBlur 参数

| 参数名 | 类型 | 说明 | 默认值 | 是否必须 |
| --- | --- | --- | --- | --- |
| backdrop | Backdrop | 提供模糊背景内容的 Backdrop | - | 是 |
| shape | Shape | 模糊区域裁剪的形状 | - | 是 |
| blurRadius | Float | 满强度处的模糊半径（dp）。限制在 [0, 150] 范围内 | 20f | 否 |
| blurRadiusX | Float | 满强度处的水平模糊半径（dp，独立半径重载） | - | 是* |
| blurRadiusY | Float | 满强度处的垂直模糊半径（dp，独立半径重载） | - | 是* |
| gradient | ProgressiveBlur | 控制模糊满强度与清晰位置的方向和区间 | ProgressiveBlur.Top | 否 |
| noiseCoefficient | Float | 抗条纹噪声抖动系数，0 表示禁用 | 0f | 否 |
| colors | BlurColors | 颜色调整和混合图层，随模糊一起淡出 | BlurColors() | 否 |
| contentBlendMode | BlendMode | 内容在模糊上方合成的混合模式 | SrcOver | 否 |
| enabled | Boolean | 是否启用模糊，为 false 时跳过效果并正常绘制内容 | true | 否 |

\* 仅在独立半径重载中必须。

### ProgressiveBlur 属性

| 属性名 | 类型 | 说明 | 默认值 |
| --- | --- | --- | --- |
| angle | Float | 渐变方向（角度，从 +X 轴顺时针）：0 从左向右淡出，90 从上向下，180 从右向左，270 从下向上 | 90f |
| startFraction | Float | 沿 angle 投影范围内模糊为满强度的位置，范围 [0, 1] | 0f |
| endFraction | Float | 模糊降为零的位置，范围 [0, 1]；可小于 startFraction 以反转过渡，但不能与其相等 | 1f |
| curve | Float | 幂次衰减曲线，端点固定不变：< 1 时半径变化集中在强端（前段变化明显、尾部柔长），> 1 时集中在清晰端。必须为正数 | 1f |

预设：`ProgressiveBlur.Top` / `Bottom` / `Left` / `Right` 覆盖四个方向的边缘淡出。

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

## 边缘描边

边缘描边属于 `miuix-material`：`EdgeStroke` 以及 `EdgeStrokes`、`GlassEdgeStrokes` 两个预设对象。它作为独立修饰符追加在背景之后，而不再是 `textureBlur` / `drawBackdrop` 的参数：

```kotlin
Modifier
    .drawBackdrop(backdrop, shape = { shape }, effects = { blur(20.dp.toPx()) })
    .edgeStroke(shape = shape, stroke = EdgeStrokes.MiddleLight, alpha = 1f)
```

属性、预设与倾斜光见[边缘描边](/zh_CN/guide/material#边缘描边)。
