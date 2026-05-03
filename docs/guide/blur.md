# Blur Effects

`miuix-blur` is a standalone blur effect library for Compose Multiplatform. It provides backdrop blur, color blending, and texture effects via Modifier extensions. The library supports Android, Desktop (JVM), iOS, macOS, and Web (WasmJs/Js).

::: warning
On Android, blur effects require API 31 (Android 12) or higher. Custom blend modes via RuntimeShader require API 33 (Android 13) or higher. On unsupported API levels, blur modifiers become no-ops.
:::

## Setup

Add the `miuix-blur` dependency to your project:

```kotlin
kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation("top.yukonga.miuix.kmp:miuix-blur:<version>")
        }
    }
}
```

For Android-only projects:

```kotlin
dependencies {
    implementation("top.yukonga.miuix.kmp:miuix-blur-android:<version>")
}
```

## Platform Support

| Platform | RenderEffect (Blur) | RuntimeShader (Custom Blend Modes) |
| --- | --- | --- |
| Android | API 31+ | API 33+ |
| Desktop (JVM) | Supported | Supported |
| iOS / macOS | Supported | Supported |
| WasmJs / Js | Supported | Supported |

You can check platform capabilities at runtime:

```kotlin
import top.yukonga.miuix.kmp.blur.isRenderEffectSupported
import top.yukonga.miuix.kmp.blur.isRuntimeShaderSupported

val canBlur = isRenderEffectSupported()
val canUseCustomBlendModes = isRuntimeShaderSupported()
```

## Basic Usage

Applying a backdrop blur involves three steps:

1. Create a `LayerBackdrop` to capture the background content
2. Apply `Modifier.layerBackdrop()` on the content container
3. Apply `Modifier.textureBlur()` on the blur surface

```kotlin
import top.yukonga.miuix.kmp.blur.BlurColors
import top.yukonga.miuix.kmp.blur.BlurDefaults
import top.yukonga.miuix.kmp.blur.layerBackdrop
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
import top.yukonga.miuix.kmp.blur.textureBlur

// Step 1: Create a LayerBackdrop
val backdrop = rememberLayerBackdrop()

// Step 2: Capture background content
Box(
    modifier = Modifier
        .fillMaxSize()
        .layerBackdrop(backdrop) // Captures this Box's content
) {
    // Background content (e.g., an image, gradient, or page content)
    Image(
        painter = painterResource(Res.drawable.background),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
}
```

::: tip Background Color
`layerBackdrop` only captures the content drawn by the composable it is applied to — it does **not** include backgrounds from parent composables (e.g., Scaffold's Surface). If the captured content has transparent areas (such as text without a background), the blur will spread colors into transparency, producing visible color artifacts.

To avoid this, draw an opaque background in the `onDraw` lambda:

```kotlin
val backgroundColor = MaterialTheme.colorScheme.surface
val backdrop = rememberLayerBackdrop {
    drawRect(backgroundColor) // Ensures an opaque background is captured
    drawContent()
}
```

:::

```kotlin

// Step 3: Apply blur on an overlay surface
Box(
    modifier = Modifier
        .size(200.dp)
        .textureBlur(
            backdrop = backdrop,
            shape = RoundedCornerShape(16.dp)
        )
) {
    Text(
        text = "Blurred Card",
        modifier = Modifier.padding(16.dp)
    )
}
```

## Color Configuration

Use `BlurColors` to apply color adjustments and blend layers on top of the blur:

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
    brightness = 0.05f,  // Range: [-1, 1], 0 = no change
    contrast = 1.1f,     // Multiplier, 1 = no change
    saturation = 1.2f    // Multiplier, 1 = no change
)

Box(
    modifier = Modifier
        .textureBlur(
            backdrop = backdrop,
            shape = RoundedCornerShape(16.dp),
            colors = colors
        )
) {
    // Content
}
```

You can also use the composable helper `BlurDefaults.blurColors()` which remembers the configuration:

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

## Blend Modes

`BlurBlendMode` provides 40+ blend modes for color blending over the blurred backdrop.

### Standard Modes

Standard SkBlendMode values (0-28), using Skia-compatible premultiplied-alpha formulas:

| Mode | Description |
| --- | --- |
| `SrcOver` | Normal alpha compositing (default) |
| `Screen` | Brightening blend |
| `Multiply` | Darkening blend |
| `Overlay` | Contrast-enhancing blend |
| `SoftLight` | Soft contrast adjustment |
| `ColorDodge` | Brightens by reducing contrast |
| `ColorBurn` | Darkens by increasing contrast |
| `Darken` | Keeps darker pixels |
| `Lighten` | Keeps lighter pixels |
| `Difference` | Absolute difference |
| `Exclusion` | Similar to Difference but lower contrast |
| `Hue` | Applies source hue |
| `Saturation` | Applies source saturation |
| `Color` | Applies source hue and saturation |
| `Luminosity` | Applies source luminosity |

### Custom Modes

Custom modes (100+) implementing Lab color space operations, linear light blending, and more (requires `isRuntimeShaderSupported()`):

| Mode | Description |
| --- | --- |
| `LinearLight` | Linear light blend |
| `LinearLightWithGreyscale` | Linear light with greyscale modulation |
| `LinearLightLab` | Linear light in Lab color space |
| `LabLightenWithGreyscale` | Lab lighten with greyscale modulation |
| `LabDarkenWithGreyscale` | Lab darken with greyscale modulation |
| `Lab` | Lab color mapping |
| `MiColorDodge` | Enhanced color dodge |
| `MiColorBurn` | Enhanced color burn |
| `PlusDarker` | Plus darker with alpha compositing |
| `PlusLighter` | Plus lighter with alpha compositing |
| `AlphaBlend` | Alpha blend with child modulation |
| `MiSaturation` | Saturation adjustment |
| `MiBrightness` | Brightness adjustment |
| `MiLuminance` | Luminance curve adjustment |

### Example: Multiple Blend Layers

```kotlin
val colors = BlurColors(
    blendColors = listOf(
        BlendColorEntry(Color(0x40FFFFFF), BlurBlendMode.Screen),
        BlendColorEntry(Color(0x20000000), BlurBlendMode.Overlay)
    ),
    saturation = 1.5f
)
```

## Advanced Usage

### Independent X/Y Blur Radii

Apply different blur strengths for horizontal and vertical axes:

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
    // Content with directional blur
}
```

### Foreground Blur (Content Masking)

Use `contentBlendMode` to create a foreground blur effect where the content's alpha channel masks the blur:

```kotlin
import androidx.compose.ui.graphics.BlendMode as ComposeBlendMode

Text(
    text = "Frosted Text",
    style = MiuixTheme.textStyles.title1,
    modifier = Modifier
        .textureBlur(
            backdrop = backdrop,
            shape = RectangleShape,
            blurRadius = 150f,
            contentBlendMode = ComposeBlendMode.DstIn // Content alpha masks the blur
        )
)
```

### Noise Dithering

The `noiseCoefficient` parameter controls anti-banding noise applied to the blur result. The default value is `0.0045f`. Set to `0f` to disable:

```kotlin
Box(
    modifier = Modifier
        .textureBlur(
            backdrop = backdrop,
            shape = RoundedCornerShape(16.dp),
            noiseCoefficient = 0f // Disable noise dithering
        )
)
```

### Low-Level API: drawBackdrop

For full control over the effect pipeline, use `Modifier.drawBackdrop()` with `BackdropEffectScope`:

```kotlin
import top.yukonga.miuix.kmp.blur.drawBackdrop

Box(
    modifier = Modifier
        .drawBackdrop(
            backdrop = backdrop,
            shape = { RoundedCornerShape(16.dp) },
            effects = {
                // Apply a Gaussian blur
                blur(radius = 60f)
                // Adjust colors
                colorControls(
                    brightness = 0.05f,
                    contrast = 1.1f,
                    saturation = 1.3f
                )
            }
        )
) {
    // Content
}
```

#### BackdropEffectScope Extensions

| Extension | Description |
| --- | --- |
| `blur(radius, edgeTreatment)` | Applies a Gaussian blur |
| `colorFilter(colorFilter)` | Applies a ColorFilter |
| `colorControls(brightness, contrast, saturation)` | Adjusts brightness, contrast, and saturation |
| `effect(effect)` | Chains an arbitrary RenderEffect |
| `runtimeShaderEffect(key, shaderString, uniformShaderName, block)` | Applies a custom AGSL/SkSL runtime shader |

#### BackdropEffectScope Properties

| Property | Type | Description |
| --- | --- | --- |
| `size` | Size | Current render size |
| `layoutDirection` | LayoutDirection | Current layout direction |
| `shape` | Shape | Current clip shape |
| `padding` | Float | Extra padding for blur overflow |
| `renderEffect` | RenderEffect? | Accumulated effect chain |
| `downscaleFactor` | Int | Downsampling factor (1, 2, 4, 8, 16) |
| `noiseCoefficient` | Float | Noise dithering coefficient for full-resolution application |

## Properties

### textureBlur / textureEffect Parameters

| Parameter Name | Type | Description | Default Value | Required |
| --- | --- | --- | --- | --- |
| backdrop | Backdrop | The backdrop providing background content to blur | - | Yes |
| shape | Shape | Shape for the blur region clipping | - | Yes |
| blurRadius | Float | Blur radius in dp, internally converted to pixels using display density. Clamped to [0, 150] | 20f | No |
| blurRadiusX | Float | Horizontal blur radius in dp (independent radii overload) | - | Yes* |
| blurRadiusY | Float | Vertical blur radius in dp (independent radii overload) | - | Yes* |
| noiseCoefficient | Float | Noise dithering coefficient for anti-banding, 0 disables | 0.0045f | No |
| colors | BlurColors | Color adjustments and blend layers applied after blur | BlurColors() | No |
| contentBlendMode | BlendMode? | Blend mode for compositing content over the blur | null | No |
| enabled | Boolean | Whether blur is active, when false the effect is skipped and content draws normally | true | No |

\* Required only in the independent radii overload.

### BlurColors Properties

| Property Name | Type | Description | Default Value |
| --- | --- | --- | --- |
| blendColors | List\<BlendColorEntry\> | Colors blended over the blurred backdrop, drawn in order | emptyList() |
| brightness | Float | Brightness adjustment in range [-1, 1] | 0f |
| contrast | Float | Contrast multiplier | 1f |
| saturation | Float | Saturation multiplier | 1f |

### BlurDefaults

| Constant | Type | Description | Value |
| --- | --- | --- | --- |
| BlurRadius | Float | Default blur radius in dp | 20f |
| NoiseCoefficient | Float | Default noise dithering coefficient | 0.0045f |
| MaxBlurRadius | Float | Maximum allowed blur radius in dp | 150f |

| Method | Return Type | Description |
| --- | --- | --- |
| blurColors() | BlurColors | Creates a remembered BlurColors instance |

## Edge Highlight

The `highlight` modifier paints a thin glassy edge with two directional lights along a rounded shape. Combine with `Modifier.textureBlur` to produce a "lit edge over blurred backdrop" look.

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

The shape passed to `highlight` should match the shape passed to `textureBlur`. Pass `null` (or a `Highlight` with `width = 0.dp`) to disable.

::: warning
Edge highlight requires `isRuntimeShaderSupported()`. On unsupported platforms or API levels, the modifier becomes a no-op.
:::

### Built-in Tokens

Six presets are provided. Pick by card size and theme:

| Token | innerBlurRadius | Visual |
| --- | --- | --- |
| `Highlight.GlassStrokeBigLight` | 3.5 dp | Thickest, softest halo — large light-mode cards |
| `Highlight.GlassStrokeMiddleLight` | 2.8 dp | Standard light-mode card (default) |
| `Highlight.GlassStrokeSmallLight` | 2.6 dp | Compact, sharper light-mode card |
| `Highlight.GlassStrokeBigDark` | 1.7 dp | Thinnest halo — large dark-mode card |
| `Highlight.GlassStrokeMiddleDark` | 2.0 dp | Standard dark-mode card |
| `Highlight.GlassStrokeSmallDark` | 2.3 dp | Compact, sharpest dark-mode card |

`Highlight.Default` aliases `GlassStrokeMiddleLight`.

### Custom BloomStroke

Override individual fields on a token, or build a `BloomStroke` from scratch:

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

`LightPosition` is in normalized UV with the reference origin at `(0.5, 0.7, 0)`. The shader normalizes `(x − 0.5, y − 0.7, z)` into a 3D unit direction. Negative `z` places the light behind the surface plane, illuminating the inward-facing edge — all built-in tokens use `z < 0`.

The two lights are restricted to opposite hemispheres by the shader: `primaryLight` lights the upper half of the rounded rect, `secondaryLight` lights the lower half. Position `y < 0.7` biases a light upward; `y > 0.7` biases it downward.

### Sensor-driven Tilt Parallax

`rememberTiltLight` shifts a base position in real time using the device rotation sensor. On Android, this produces a parallax-like edge that follows device tilt; on Desktop / iOS / macOS / Web the tilt is always zero, so the lights stay anchored.

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

`sensitivity` controls how far the UV position shifts per radian of tilt — `0.1f` shifts the light by 10% of the bounds at 1 rad. Higher values amplify the parallax.

### Edge Highlight Properties

#### Highlight

| Property | Type | Description | Default |
| --- | --- | --- | --- |
| width | Dp | Stroke band width painted with `style.color` | 0.8.dp |
| alpha | Float | Overall opacity multiplier, range [0, 1] | 1f |
| style | HighlightStyle | Shading model (typically a `BloomStroke`) | `HighlightStyle.Default` |

#### BloomStroke

| Property | Type | Description | Default |
| --- | --- | --- | --- |
| color | Color | Base color of the stroke band; alpha drives stroke brightness | `White.copy(alpha = 0.05f)` |
| blendMode | BlendMode | Compositing mode for the highlight overlay | `BlendMode.Plus` |
| innerBlurRadius | Dp | Depth the lighting reaches inward from the rounded edge — controls halo softness and thickness | 2.8.dp |
| primaryLight | LightSource | Upper-hemisphere light | `LightPosition(0.5f, 0.5f, -0.5f)`, intensity 0.4 |
| secondaryLight | LightSource | Lower-hemisphere light | `LightPosition(0.5f, 0.8f, -0.5f)`, intensity 0.25 |

#### LightSource

| Property | Type | Description | Default |
| --- | --- | --- | --- |
| position | LightPosition | UV position of the light | - |
| color | Color | Light color (color alpha is folded into the contribution scale) | `White` |
| intensity | Float | Brightness scalar, ≥ 0 | 1f |

#### LightPosition

| Property | Type | Description |
| --- | --- | --- |
| x | Float | Normalized UV x (reference origin: 0.5) |
| y | Float | Normalized UV y (reference origin: 0.7) |
| z | Float | Signed depth; negative places the light behind the surface |

#### rememberTiltLight Parameters

| Parameter | Type | Description | Default |
| --- | --- | --- | --- |
| basePosition | LightPosition | Position at zero tilt | - |
| color | Color | Light color | `White` |
| intensity | Float | Brightness scalar | 1f |
| sensitivity | Float | UV offset applied per radian of tilt | 0.1f |
