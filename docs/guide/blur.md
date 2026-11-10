# Blur Effects

`miuix-blur` is a standalone blur effect library for Compose Multiplatform. It provides backdrop blur, color blending, and texture effects via Modifier extensions. The library supports Android, Desktop (JVM), iOS, macOS, and Web (WasmJs/Js).

::: warning
On Android, `miuix-blur` requires `minSdk` 33 (Android 13) or higher. Blur,
blend and noise effects rely on `RuntimeShader`, which is available
from API 33. Runtime capability checks do not lower the Android artifact's
minimum SDK requirement.
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

## Runtime capability checks

Use the checks below to inspect runtime effect support. On Android they map to
`Build.VERSION.SDK_INT` comparisons; on Skiko-based targets (Desktop, iOS,
macOS, Web) they always return `true`.

```kotlin
import top.yukonga.miuix.kmp.material.isRenderEffectSupported
import top.yukonga.miuix.kmp.material.isRuntimeShaderSupported

// True on API 32+ (and all non-Android targets). Useful when you only need
// the backdrop scaffold (e.g. a chained ColorFilter via `colorFilter(...)`).
val backdropScaffoldSupported = isRenderEffectSupported()

// True on API 33+ (and all non-Android targets). Gate any code path that
// applies `blur(...)`, `blendColors(...)`, `noiseDither(...)`,
// `Modifier.textureBlur(...)`, or any `RuntimeShader` you build yourself.
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

`colors` and `noiseCoefficient` work the same way as in [`textureBlur`](#color-configuration), but they apply to the blurred region only and fade out together with the blur — the clear end blends seamlessly into the surrounding content instead of showing a tinted edge. Note that progressive blur defaults `noiseCoefficient` to `0f` (disabled): grain riding a blur gradient is more visible than grain on a uniform blur.

::: warning Performance
On top of the downscaled multi-level blur, the pixel-sharp clear end adds a full-resolution overlay pass per frame — more GPU bandwidth than `textureBlur` over the same area. Prefer progressive blur for bars and edge bands rather than large fills.
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
| `noiseCoefficient` | Float | Noise dithering coefficient |

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

### progressiveTextureBlur Parameters

| Parameter Name | Type | Description | Default Value | Required |
| --- | --- | --- | --- | --- |
| backdrop | Backdrop | The backdrop providing background content to blur | - | Yes |
| shape | Shape | Shape for the blur region clipping | - | Yes |
| blurRadius | Float | Blur radius in dp at full strength. Clamped to [0, 150] | 20f | No |
| blurRadiusX | Float | Horizontal blur radius in dp at full strength (independent radii overload) | - | Yes* |
| blurRadiusY | Float | Vertical blur radius in dp at full strength (independent radii overload) | - | Yes* |
| gradient | ProgressiveBlur | Direction and band controlling where the blur is full vs zero | ProgressiveBlur.Top | No |
| noiseCoefficient | Float | Noise dithering coefficient for anti-banding, 0 disables | 0f | No |
| colors | BlurColors | Color adjustments and blend layers, fading out with the blur | BlurColors() | No |
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

## Edge Stroke

Edge strokes are part of `miuix-material`: `EdgeStroke` plus the `EdgeStrokes` and `GlassEdgeStrokes` preset objects. They are applied as their own modifier after the backdrop, not through a `textureBlur` / `drawBackdrop` parameter:

```kotlin
Modifier
    .drawBackdrop(backdrop, shape = { shape }, effects = { blur(20.dp.toPx()) })
    .edgeStroke(shape = shape, stroke = EdgeStrokes.MiddleLight, alpha = 1f)
```

See [Edge Stroke](/guide/material#edge-stroke) for the properties, presets and tilt-driven lights.
