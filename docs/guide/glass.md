# Glass Material

`miuix-glass` is a standalone glass material library for Compose Multiplatform. It provides backdrop sampling, blur, color blending, and rim lighting via Modifier extensions. The library supports Android, Desktop (JVM), iOS, macOS, and Web (WasmJs/Js).

::: warning
On Android, `miuix-glass` requires `minSdk` 33 (Android 13) or higher. Runtime capability checks do not lower the Android artifact's minimum SDK requirement.
:::

## Setup

Add the `miuix-glass` dependency to your project:

```kotlin
kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation("top.yukonga.miuix.kmp:miuix-glass:<version>")
        }
    }
}
```

For Android-only projects:

```kotlin
dependencies {
    implementation("top.yukonga.miuix.kmp:miuix-glass-android:<version>")
}
```

`miuix-glass` includes `miuix-blur` and `miuix-ui` transitively.

## Basic Usage

Applying a glass material involves three steps:

1. Create a `LayerBackdrop` to capture the background content
2. Apply `Modifier.layerBackdrop()` on the content container
3. Apply `Modifier.glassPanel()` on the glass surface

```kotlin
import androidx.compose.ui.graphics.luminance
import top.yukonga.miuix.kmp.blur.layerBackdrop
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
import top.yukonga.miuix.kmp.glass.GlassDefaults
import top.yukonga.miuix.kmp.glass.GlassMaterials
import top.yukonga.miuix.kmp.glass.GlassShape
import top.yukonga.miuix.kmp.glass.GlassStrokes
import top.yukonga.miuix.kmp.glass.glassPanel

// Step 1: Create a LayerBackdrop
val backdrop = rememberLayerBackdrop()
val isDark = MiuixTheme.colorScheme.background.luminance() < 0.5f

Box(modifier = Modifier.fillMaxSize()) {
    // Step 2: Capture background content
    Box(
        modifier = Modifier
            .matchParentSize()
            .layerBackdrop(backdrop)
            .background(MiuixTheme.colorScheme.background),
    ) {
        Text("Page content", modifier = Modifier.padding(24.dp))
    }
    // Step 3: Apply glass on an overlay surface
    Box(
        modifier = Modifier
            .align(Alignment.Center)
            .size(240.dp, 120.dp)
            .glassPanel(
                backdrop = backdrop,
                shape = GlassShape(24.dp),
                style = GlassDefaults.style(isDark),
                material = GlassMaterials.puredThinGlass(isDark),
                stroke = GlassStrokes.forTheme(isDark),
                shading = false,
            )
            .padding(24.dp),
    ) {
        Text("Glass panel")
    }
}
```

### Background Sampling {#background-sampling}

`layerBackdrop` only captures the content drawn by the composable it is applied to — it does **not** include backgrounds from parent composables (e.g., Scaffold's Surface). Draw an opaque background inside the sampled area and keep the glass panel outside it.

Dialogs and bottom sheets should sample the page without their own panel or scrim. Their surface helpers apply the host's animated scrim themselves. If the backdrop must include page bars, record the page Scaffold and render the overlay in an outer Scaffold. See the [Backdrop API](/guide/blur#basic-usage) for recording options.

## Material Configuration

### GlassStyle

`GlassStyle` controls refraction, reflection, rim lighting and color treatment. Use `GlassDefaults.style(isDark)` to select the default light/dark pair, or choose a preset from the `Common*`, `Bionic*` and `Tint*` families in `GlassStyles`.

`GlassDefaults.Style` is the fixed `CommonMediumRegularLowLight` preset; it does not read the current theme.

### GlassMaterial

`GlassMaterial` controls the blurred body and its color layers. A non-null material replaces the blur radius requested by `GlassStyle`.

| Property Name | Type | Description | Default Value | Required |
| --- | --- | --- | --- | --- |
| blurRadius | Dp | Backdrop blur radius | - | Yes |
| first | GlassColorLayer | First color layer, nearest the backdrop | - | Yes |
| second | GlassColorLayer? | Second color layer | null | No |
| third | GlassColorLayer? | Third color layer; requires a non-null second layer | null | No |

Each `GlassColorLayer` has a `color: Color` and a `mode: GlassColorBlendMode`. Layers are applied in order. Blend modes include `SrcOver`, `PlusDarker`, `PlusLighter`, `SoftLight`, `HardLight`, `Overlay`, `Luminosity`, `ColorDodge` and `ColorBurn`.

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

### GlassShape

Use `GlassShape(cornerRadius)` for equal corners, or supply all four radii for an asymmetric surface:

```kotlin
val shape = GlassShape(
    topStart = 24.dp,
    topEnd = 24.dp,
    bottomEnd = 0.dp,
    bottomStart = 0.dp,
)
```

Start/end corners follow layout direction. The primary constructor also accepts `CornerSize` values, including percentage corners for capsules.

`smoothing` defaults to `1f` for continuous corners; `0f` uses circular corners in the glass shader. Ordinary Compose outlines and `Modifier.clip` still use rounded rectangles.

### Stroke and Shadow

Use `GlassStrokes.forTheme(isDark)` for a theme-matched rim. Set `stroke = null` to remove it. `GlassShadows` provides `Low`, `Regular`, `High`, `ExtraHigh` and `Float` presets; `shadow = null` removes the shadow.

## Modifier API

### glassPanel

`Modifier.glassPanel` combines the material, clipping, rim stroke and drop shadow:

| Parameter | Type | Description | Default Value | Required |
| --- | --- | --- | --- | --- |
| backdrop | Backdrop? | Background content to sample; null selects fallback | - | Yes |
| shape | GlassShape | Shared surface, stroke and shadow shape | - | Yes |
| style | GlassStyle | Optical treatment | GlassDefaults.Style | No |
| alpha | Float | Color-layer, shading, stroke and shadow opacity multiplier | 1f | No |
| material | GlassMaterial? | Blur and body color layers; null keeps style-based blur without GlassMaterial color layers | null | No |
| stroke | GlassStroke? | Rim stroke | null | No |
| shadow | GlassShadow? | Drop shadow | GlassShadows.Regular | No |
| shading | Boolean | Whether to apply refraction and shading | true | No |
| fallback | Modifier | Surface used when backdrop is null | Modifier.clip(shape) | No |

Use `shading = true` for refractive controls and `false` for flat glass bars or menus. With shading disabled, the style's refraction, reflection and rim lighting are off; the independent `stroke` still controls the bloom stroke. The default fallback only clips; it does not paint a background color.

With a non-null backdrop, `alpha` does not disable blur or fade foreground content. When the backdrop is null, it fades the entire fallback layer, including content.

### glass

`Modifier.glass` draws the glass material without a drop shadow:

| Parameter | Type | Description | Default Value | Required |
| --- | --- | --- | --- | --- |
| backdrop | Backdrop | Background content to sample | - | Yes |
| shape | GlassShape | Surface silhouette | - | Yes |
| style | GlassStyle | Optical treatment | GlassDefaults.Style | No |
| alpha | Float | Color-layer, shading and stroke opacity multiplier; does not disable blur or fade content | 1f | No |
| tint | Color | Overrides the style tint; alpha controls tint strength | Color.Unspecified | No |
| material | GlassMaterial? | Blur and body color layers; null keeps style-based blur without GlassMaterial color layers | null | No |
| noiseCoefficient | Float | Blur dithering; 0f disables it | GlassDefaults.NoiseCoefficient | No |
| stroke | GlassStroke? | Rim stroke | null | No |
| contentBlendMode | BlendMode | Foreground content blend mode | BlendMode.SrcOver | No |
| enabled | Boolean | Whether to draw the glass effect | true | No |
| shading | Boolean | Whether to apply refraction and shading | true | No |

When `enabled = false`, only the composable's content is drawn. Use this flag, rather than `alpha = 0f`, to bypass backdrop blur and the other glass effects.

### glassShadow

`Modifier.glassShadow` draws only a shadow. Use the same shape as the material:

| Parameter | Type | Description | Default Value | Required |
| --- | --- | --- | --- | --- |
| shape | GlassShape | Shape casting the shadow | - | Yes |
| shadow | GlassShadow? | Shadow preset; null disables drawing | GlassShadows.Regular | No |
| alpha | Float | Shadow opacity multiplier | 1f | No |

### glassSurface

`glassSurface` returns an `@Composable (Shape) -> Modifier` for compatible overlay `surface` slots:

```kotlin
import top.yukonga.miuix.kmp.glass.GlassPopupDefaults
import top.yukonga.miuix.kmp.glass.glassSurface

val surface = glassSurface(
    backdrop = backdrop,
    visuals = GlassPopupDefaults.visuals(),
)
```

| Parameter | Type | Description | Default Value | Required |
| --- | --- | --- | --- | --- |
| backdrop | Backdrop? | Background content to sample; null uses the container fill | - | Yes |
| visuals | GlassPopupVisuals | Popup style, material, stroke, shadow and fallback color | - | Yes |
| cornerRadius | Dp | Radius used when the supplied shape cannot be converted | GlassPopupDefaults.CornerRadius | No |

The helper preserves all four corners of `RoundedCornerShape` and `GlassShape`, and disables refraction. [GlassPopupVisuals](/components/glass/glasspopup#shared-defaults) controls its appearance.

For hosts with an animated scrim or scale, use [glassDialogSurface](/components/glass/glassdialog#glassdialogsurface) or [glassBottomSheetSurface](/components/glass/glassbottomsheet#glassbottomsheetsurface).

## GlassDefaults Object

### Constants

| Constant Name | Type | Description | Default Value |
| --- | --- | --- | --- |
| CornerRadius | Dp | Default corner radius | 24.dp |
| Smoothing | Float | Default corner smoothing | 1f |
| SourceDensity | Float | Source density for pixel-based style and shadow tokens | 3f |
| NoiseCoefficient | Float | Blur dithering coefficient | BlurDefaults.NoiseCoefficient |
| Overspill | Float | Material overspill before masking | 3f |
| Style | GlassStyle | Default optical preset | GlassStyles.CommonMediumRegularLowLight |

Pixel-based style and shadow values scale by `density / SourceDensity`.

### Functions

| Function Name | Return Type | Description |
| --- | --- | --- |
| style(isDark: Boolean) | GlassStyle | Selects CommonMediumRegularLowLight or CommonMediumRegularDark |

`GlassMotion` provides the motion presets used by Glass components.

## Advanced Usage

### Custom Fallback Surface

Pass a fill in `fallback` when a custom panel should remain opaque without a backdrop:

```kotlin
val shape = GlassShape(24.dp)

Box(
    modifier = Modifier
        .size(240.dp, 120.dp)
        .glassPanel(
            backdrop = null,
            shape = shape,
            fallback = Modifier
                .clip(shape)
                .background(MiuixTheme.colorScheme.surface),
        )
        .padding(24.dp),
) {
    Text("Fallback surface")
}
```

This fallback is selected by a null backdrop. Components with runtime-capability handling also provide their own fallback behavior; see the relevant component's parameters.
