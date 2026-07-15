# Blur Effects

`miuix-blur` is a standalone blur effect library for Compose Multiplatform. It provides backdrop blur, color blending, and texture effects via Modifier extensions. The library supports Android, Desktop (JVM), iOS, macOS, and Web (WasmJs/Js).

::: warning
On Android, `miuix-blur` requires `minSdk` 33 (Android 13) or higher. All
effects (blur, blend, noise, highlight) rely on `RuntimeShader`, which is only
available from API 33. Apps with a lower `minSdk` that still want to include
this library should gate blur-related code paths with [the capability checks
described below](#runtime-capability-checks).
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

| Platform      | Minimum Requirement |
| ------------- | ------------------- |
| Android       | API 33 (Android 13) |
| Desktop (JVM) | Supported           |
| iOS / macOS   | Supported           |
| WasmJs / Js   | Supported           |

Blur is implemented as a separable Gaussian `RuntimeShader` wrapped in a
`RenderEffect`. On Android, `RuntimeShader` was introduced in API 33, so that
is the hard floor for the entire library — including blend, noise, and
highlight effects.

### Runtime capability checks

If your app has a lower `minSdk` than `miuix-blur` and you want to keep
shipping a single APK, gate blur usage with the capability checks below.
On Android they map to `Build.VERSION.SDK_INT` comparisons; on Skiko-based
targets (Desktop, iOS, macOS, Web) they always return `true`.

```kotlin
import top.yukonga.miuix.kmp.shader.isRenderEffectSupported
import top.yukonga.miuix.kmp.shader.isRuntimeShaderSupported

// True on API 32+ (and all non-Android targets). Useful when you only need
// the backdrop scaffold (e.g. a chained ColorFilter via `colorFilter(...)`).
val backdropScaffoldSupported = isRenderEffectSupported()

// True on API 33+ (and all non-Android targets). Gate any code path that
// applies `blur(...)`, `blendColors(...)`, `noiseDither(...)`,
// `Modifier.textureBlur(...)`, the highlight styles, or any `RuntimeShader`
// you build yourself.
val blurAndBlendSupported = isRuntimeShaderSupported()
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

## Progressive Blur

`Modifier.progressiveTextureBlur` applies a gradient backdrop blur: the blur strength ramps continuously from full to zero along a direction, with a genuine medium blur in the middle of the ramp and a pixel-sharp, full-resolution clear end. Ideal for navigation bars and edge fades.

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
            gradient = ProgressiveBlur.Top // Strongest at the top, sharp at the bottom
        )
) {
    // Content
}
```

### Gradient Direction and Band

`ProgressiveBlur` describes the ramp as a two-point linear gradient: the blur is at full strength at `startFraction` and fades to zero at `endFraction`, measured along `angle` (degrees, clockwise from the +X axis). Use the presets for the common edge fades, or adjust the band with `copy`:

```kotlin
// Presets cover the four edge fades
ProgressiveBlur.Top      // angle = 90°, strongest at the top edge
ProgressiveBlur.Bottom   // angle = 270°, strongest at the bottom edge
ProgressiveBlur.Left     // angle = 0°, strongest at the left edge
ProgressiveBlur.Right    // angle = 180°, strongest at the right edge

// Custom band: full strength until 30% of the extent, sharp from 90% on
val gradient = ProgressiveBlur.Top.copy(startFraction = 0.3f, endFraction = 0.9f)

// Custom falloff: pronounced radius change early in the band, long soft tail after
val frontLoaded = ProgressiveBlur.Top.copy(curve = 0.5f)
```

`colors` and `noiseCoefficient` use the same pipeline as [`textureBlur`](#color-configuration), but they apply to the blurred region only and fade out together with the blur — the clear end blends seamlessly into the surrounding content instead of showing a tinted edge.

::: warning Performance
The pixel-sharp clear end requires recording the backdrop at full resolution (no downscale) and evaluating several graduated blur levels of it per frame — noticeably more GPU bandwidth than `textureBlur` over the same area. Prefer progressive blur for bars and edge bands rather than large fills.
:::

::: tip
Inside a custom `drawBackdrop` pipeline, `progressiveTextureBlurEffect(...)` runs the same preset chain in the `effects` block. Pair it with `drawBackdrop`'s `progressiveGradient` parameter (same gradient) so the clear end stays genuinely sharp — `Modifier.progressiveTextureBlur` wires both together.
:::

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
                // Apply a Blur
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
| `blur(radius, edgeTreatment)` | Applies a Blur |
| `colorFilter(colorFilter)` | Applies a ColorFilter |
| `colorControls(brightness, contrast, saturation)` | Adjusts brightness, contrast, and saturation |
| `effect(effect)` | Chains an arbitrary RenderEffect |
| `runtimeShaderEffect(key, shaderString, uniformShaderName, block)` | Applies a custom AGSL/SkSL runtime shader |

::: warning Pixel-space uniforms must be scaled by `downscaleFactor`
When `runtimeShaderEffect` is chained after `blur` (or any other effect that raises `downscaleFactor`), the backdrop layer is recorded at `1 / downscaleFactor` resolution and the shader receives `coord` values in the downscaled layer's pixel space. Any uniform that describes a pixel-space distance — size, padding/offset, corner radii, refraction band, etc. — must be divided by `downscaleFactor` inside `block`, otherwise samples land outside the layer bounds and return transparent black.
:::

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
| highlight | Highlight? | Optional edge highlight painted on top of the content. `null` skips drawing | null | No |
| contentBlendMode | BlendMode? | Blend mode for compositing content over the blur | null | No |
| enabled | Boolean | Whether blur is active, when false the effect is skipped and content draws normally | true | No |

\* Required only in the independent radii overload.

### progressiveTextureBlur Parameters

| Parameter Name | Type | Description | Default Value | Required |
| --- | --- | --- | --- | --- |
| backdrop | Backdrop | The backdrop providing background content to blur | - | Yes |
| shape | Shape | Shape for the blur region clipping | - | Yes |
| blurRadius | Float | Blur radius in dp at full strength. Clamped to [0, 150] | 20f | No |
| blurRadiusX | Float | Horizontal blur radius in dp at full strength (independent radii overload) | - | Yes* |
| blurRadiusY | Float | Vertical blur radius in dp at full strength (independent radii overload) | - | Yes* |
| gradient | ProgressiveBlur | Direction and band controlling where the blur is full vs zero | ProgressiveBlur.Top | No |
| noiseCoefficient | Float | Noise dithering coefficient for anti-banding, 0 disables | 0.0045f | No |
| colors | BlurColors | Color adjustments and blend layers, fading out with the blur | BlurColors() | No |
| highlight | Highlight? | Optional edge highlight painted on top of the content. `null` skips drawing | null | No |
| contentBlendMode | BlendMode | Blend mode for compositing content over the blur | SrcOver | No |
| enabled | Boolean | Whether blur is active, when false the effect is skipped and content draws normally | true | No |

\* Required only in the independent radii overload.

### ProgressiveBlur Properties

| Property Name | Type | Description | Default Value |
| --- | --- | --- | --- |
| angle | Float | Gradient direction in degrees, clockwise from the +X axis: 0 fades left→right, 90 top→bottom, 180 right→left, 270 bottom→top | 90f |
| startFraction | Float | Fraction in [0, 1] of the projected extent along angle where the blur is at full strength | 0f |
| endFraction | Float | Fraction in [0, 1] where the blur fades to zero; may be smaller than startFraction to reverse the ramp, must differ from it | 1f |
| curve | Float | Power-curve exponent reshaping the falloff between the fixed endpoints: < 1 concentrates the radius change toward the strong end, > 1 toward the clear end. Must be positive | 1f |

Presets: `ProgressiveBlur.Top` / `Bottom` / `Left` / `Right` cover the four edge fades.

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

A `Highlight` paints a thin glassy edge with two directional lights along a rounded shape. It is drawn through the `highlight` parameter on `Modifier.textureBlur` / `Modifier.textureEffect` (constant case) or on `Modifier.drawBackdrop` (reactive case, sharing the `BackdropEffectScope`). Combined with the blurred backdrop this produces a "lit edge over blurred backdrop" look.

### With textureBlur (constant)

```kotlin
import androidx.compose.foundation.shape.RoundedCornerShape
import top.yukonga.miuix.kmp.blur.highlight.Highlight

Box(
    modifier = Modifier
        .size(200.dp, 100.dp)
        .textureBlur(
            backdrop = backdrop,
            shape = RoundedCornerShape(24.dp),
            highlight = Highlight.GlassStrokeMiddleLight,
        ),
)
```

### With drawBackdrop (reactive)

The `highlight` lambda runs inside the same `BackdropEffectScope` as `effects`, so its return value can change with state (e.g. press progress) and pick up the current `size` / `shape` automatically.

```kotlin
import top.yukonga.miuix.kmp.blur.drawBackdrop
import top.yukonga.miuix.kmp.blur.blur
import top.yukonga.miuix.kmp.blur.highlight.Highlight

Box(
    modifier = Modifier
        .size(200.dp, 100.dp)
        .drawBackdrop(
            backdrop = backdrop,
            shape = { RoundedCornerShape(24.dp) },
            effects = { blur(20.dp.toPx()) },
            highlight = { Highlight.GlassStrokeMiddleLight.copy(alpha = pressProgress) },
        ),
)
```

Pass `null` (or a `Highlight` with `width = 0.dp`) to disable.

::: warning
Edge highlight requires `isRuntimeShaderSupported()`. On unsupported platforms or API levels, the highlight is skipped silently.
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
