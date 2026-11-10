# Material

`miuix-material` is the rendering-primitive and material-definition layer of Miuix. It holds the runtime-shader abstraction, the shared AGSL fragments, the glass style / material / shape / shadow definitions, and the edge-stroke material. `miuix-blur`, `miuix-squircle` and `miuix-glass` all build on it.

The module targets `minSdk` 24. Everything that actually draws a runtime shader is gated at runtime — see [Runtime capability checks](#runtime-capability-checks).

## Setup

```kotlin
kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation("top.yukonga.miuix.kmp:miuix-material:<version>")
        }
    }
}
```

For Android-only projects:

```kotlin
dependencies {
    implementation("top.yukonga.miuix.kmp:miuix-material-android:<version>")
}
```

`miuix-material` is included transitively by `miuix-blur`, `miuix-squircle` and `miuix-glass`; add it explicitly only when you use its types directly.

## Render Primitives

### Runtime capability checks

| Function | Meaning |
| --- | --- |
| `isRuntimeShaderSupported()` | AGSL/SkSL shaders can be compiled and drawn (Android 13+; always true on Skia backends) |
| `isRenderEffectSupported()` | `RenderEffect` chains are available (Android 12+) |

### RuntimeShader

`RuntimeShader` is the cross-platform handle for a compiled shader: set uniforms on it, then use it as a Compose `Shader` or `ShaderBrush`.

```kotlin
val shader = RuntimeShader(source)
shader.setFloatUniform("in_size", width, height)
drawRect(brush = shader.asBrush())
```

Use `asComposeShader()` when you need a `Shader` rather than a brush. On Android the underlying shader is mutable, so a cached brush is returned; on Skiko each `asBrush()` call snapshots the current uniforms.

### RuntimeShaderCache

`RuntimeShaderCache` reuses compiled shaders by key instead of recompiling per frame. `LocalRuntimeShaderCache` provides one per composition; `DefaultRuntimeShaderCache` is a standalone in-memory implementation for code with no composition context.

```kotlin
val shader = LocalRuntimeShaderCache.current.obtainRuntimeShader("MyShader", source)
```

### MaterialAgsl

`MaterialAgsl` exposes the two AGSL fragments every material shader shares, written against a common uniform contract — `in_size`, `in_radii` (top-left, top-right, bottom-right, bottom-left) and `in_smoothing`:

| Fragment | Provides |
| --- | --- |
| `SilhouetteSdf` | `roundedBoxSdf`, `supercircleSdf`, `pickRadius`, `sdfShape`, `silhouetteSdf` (negative inside the shape) |
| `RimLighting` | `luma`, `smooth5Map`, `edgeCurve`, `edgeCurveSlope`, `sdfGradient`, `liftToward`, `dynamicAdd`, `lightFalloff`, `rimLighten` |

Include them in your own shader source to stay geometrically consistent with the built-in materials.

## Material Definitions

### GlassStyle

Use `GlassDefaults.style(isDark)` for theme-aware refraction, reflection and lighting, or select a preset from `GlassStyles`. `GlassDefaults.Style` is the fixed `CommonMediumRegularLowLight` preset.

### GlassMaterial

`GlassMaterial` controls the blurred body and its color layers. A non-null material replaces the blur radius requested by `GlassStyle`.

| Property Name | Type | Description | Default Value | Required |
| --- | --- | --- | --- | --- |
| blurRadius | Dp | Backdrop blur radius | - | Yes |
| first | GlassColorLayer | First color layer, nearest the backdrop | - | Yes |
| second | GlassColorLayer? | Second color layer | null | No |
| third | GlassColorLayer? | Third color layer; requires a non-null second layer | null | No |

Each `GlassColorLayer` specifies a `Color` and `GlassColorBlendMode`; layers are applied in order.

`GlassMaterials` provides theme-aware presets:

| Function Name | Return Type | Description | Blur Radius |
| --- | --- | --- | --- |
| puredThinGlass(isDark) | GlassMaterial | Floating bar material | 20.dp |
| popupViewGlass(isDark) | GlassMaterial | Expanded popup material | 60.dp |
| dialogGlass(isDark) | GlassMaterial | Dialog and attached bottom-sheet material | 60.dp |
| actionBarMask(isDark) | GlassMaterial | Top-bar masking band | 40.dp light / 60.dp dark |

Each preset also has corresponding `*Light` and `*Dark` values. To adjust a preset, use `copy`:

```kotlin
val material = GlassMaterials.puredThinGlass(isDark).copy(
    blurRadius = 32.dp,
)
```

`withUnderlayBlur(underlay)` combines two materials for a single backdrop filter: the blur variances add in quadrature, so one pass produces both layers' reach.

### GlassShape

`GlassShape` is the silhouette shared by clipping, the rim stroke and the shadow. Use `GlassShape(cornerRadius)` for equal corners, or supply all four radii for an asymmetric surface:

```kotlin
val shape = GlassShape(
    topStart = 24.dp,
    topEnd = 24.dp,
    bottomEnd = 0.dp,
    bottomStart = 0.dp,
)
```

Start/end corners follow layout direction. The primary constructor also accepts `CornerSize` values, including percentage corners for capsules.

`smoothing` controls shader corners (`1f`: continuous; `0f`: circular). Compose outlines and clipping use rounded rectangles.

### GlassShadow

`GlassShadows` provides `Low`, `Regular`, `High`, `ExtraHigh` and `Float` preset shadows, scaled by `density / GlassDefaults.SourceDensity`. Pass `null` where a modifier takes a shadow to remove it.

## Edge Stroke

An edge stroke draws a lit rim along a shape's silhouette: a flat stroke band plus a hemispheric normal lit by two directional lights.

### EdgeStroke

| Property Name | Type | Description | Default Value | Required |
| --- | --- | --- | --- | --- |
| width | Dp | Width of the flat stroke band | - | Yes |
| bevel | Dp | Inward halo depth | - | Yes |
| color | Color | Flat stroke colour; its alpha controls strength | - | Yes |
| primary | EdgeLightSource | First light | - | Yes |
| secondary | EdgeLightSource | Second light | - | Yes |
| blendMode | BlendMode | How the stroke composites over the content | BlendMode.Plus | No |
| peak | EdgePeak | How each light is gated around the rim | EdgePeak.Vertical | No |

`EdgeLightSource` positions a light in normalized UV: the shader normalizes `(x - 0.5, y - 0.7, z)`, so `(0.5, 0.7)` is the reference origin and contributes nothing. A negative `z` places the light behind the surface plane and lights the inward-facing edge. `color` alpha and `intensity` both scale the contribution.

| EdgePeak | Gate |
| --- | --- |
| `Vertical` | Fixed vertical axes: the primary lights the upper half of the rim, the secondary the lower |
| `Axial` | The gate follows each light's own XY direction, so the lit arc moves with the light |
| `Dual` | `dot(n.xy, L.xy)²`: one light produces two peaks 180° apart |

### Modifier.edgeStroke

Append the stroke after whatever draws the surface. Over a blurred backdrop:

```kotlin
import androidx.compose.foundation.shape.RoundedCornerShape
import top.yukonga.miuix.kmp.blur.blur
import top.yukonga.miuix.kmp.blur.drawBackdrop
import top.yukonga.miuix.kmp.material.EdgeStrokes
import top.yukonga.miuix.kmp.material.edgeStroke

val shape = RoundedCornerShape(24.dp)

Box(
    modifier = Modifier
        .size(200.dp, 100.dp)
        .drawBackdrop(
            backdrop = backdrop,
            shape = { shape },
            effects = { blur(20.dp.toPx()) },
        )
        .edgeStroke(
            shape = shape,
            stroke = EdgeStrokes.MiddleLight,
            alpha = 1f, // e.g. press progress
        ),
)
```

The modifier takes a plain shape; a `GlassShape` additionally contributes its `smoothing`. `alpha = 0f` (or a stroke with `width = 0.dp`) disables the rim. Inside a `DrawScope` that already holds the right bounds, call `drawEdgeStroke(shape, stroke, alpha)` directly.

### Presets

| Preset | Module family | Notes |
| --- | --- | --- |
| `EdgeStrokes` | OS3-style bloom | `BigLight` / `MiddleLight` / `SmallLight` / `BigDark` / `MiddleDark` / `SmallDark`, plus `Default` (aliases `MiddleLight`). All use `EdgePeak.Axial` |
| `GlassEdgeStrokes` | Glass | Same six names plus `forTheme(isDark)`. All use `EdgePeak.Vertical` |

Names read `{Big,Middle,Small}{Light,Dark}`: the size picks the halo depth, the suffix the theme. Both objects expose full `EdgeStroke` values, so `copy` is the way to adjust one.

### Tilt-driven lights

`rememberTiltLight(base, sensitivity)` returns an `EdgeLightSource` whose UV position follows the device rotation sensor, producing a parallax-like edge. On platforms without a sensor the light stays anchored at `base`.

```kotlin
import top.yukonga.miuix.kmp.material.EdgeLightSource
import top.yukonga.miuix.kmp.material.rememberTiltLight

val primary = rememberTiltLight(
    base = EdgeLightSource(0.5f, 0.5f, -0.5f, intensity = 0.4f),
    sensitivity = 0.1f, // UV shift per radian of tilt
)
```

`sensitivity = 0.1f` moves the light by 10% of the stroked bounds at 1 rad of tilt. Use `EdgePeak.Axial` when the lights move horizontally, so the lit arc follows them.

### Behaviour notes

- Strokes require `isRuntimeShaderSupported()`; elsewhere they are skipped silently.
- The shader outputs premultiplied light: the alpha carries the light's own magnitude, so additive channels are not clamped by a lower alpha. `BlendMode.Plus` then adds it onto the surface.
- The flat band is drawn with a half-pixel bias, so a very small `width` still yields at least one anti-aliased pixel.

## GlassDefaults Object

### Constants

| Constant Name | Type | Description | Default Value |
| --- | --- | --- | --- |
| CornerRadius | Dp | Default corner radius | 24.dp |
| Smoothing | Float | Default corner smoothing | 1f |
| SourceDensity | Float | Source density for pixel-based style and shadow tokens | 3f |
| NoiseCoefficient | Float | Blur dithering coefficient | 0.0045f |
| Overspill | Float | Material overspill before masking | 3f |
| Style | GlassStyle | Default optical preset | GlassStyles.CommonMediumRegularLowLight |

Pixel-based style and shadow values scale by `density / SourceDensity`.

### Functions

| Function Name | Return Type | Description |
| --- | --- | --- |
| style(isDark: Boolean) | GlassStyle | Selects CommonMediumRegularLowLight or CommonMediumRegularDark |

## Related

- [Blur Effects](/guide/blur) — background sampling and the blur effect stack that strokes are usually drawn over.
- [Glass Surfaces](/guide/glass) — `glassPanel` / `glass` / `glassShadow` / `glassSurface` and the glass components.
