# Glass Surfaces

`miuix-glass` provides the glass surfaces and components for Compose Multiplatform: `glassPanel`, `glass`, `glassShadow` and `glassSurface` on top of the [material definitions](/guide/material), with background blur, color blending and lighting effects.

::: note
`miuix-glass` targets `minSdk` 24 on Android. The glass effects need `RuntimeShader` (Android 13+); below that the surfaces fall back to their opaque container color — see [Runtime capability checks](/guide/material#runtime-capability-checks).

Note that `miuix-glass` depends on `miuix-blur`, which itself declares `minSdk` 33: an app below API 33 must add `tools:overrideLibrary="top.yukonga.miuix.kmp.blur"` to its manifest and expect the blur layer to be skipped.
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

`miuix-glass` includes `miuix-material`, `miuix-blur` and `miuix-ui` transitively.

The optional `miuix-glass-icons` module provides the Glass icon set, reached through `MiuixIcons.Glass`; see [Icon System](/guide/icons).

## Basic Usage

Create a backdrop, record the background with `layerBackdrop`, then apply `glassPanel` to a separate surface:

```kotlin
import androidx.compose.ui.graphics.luminance
import top.yukonga.miuix.kmp.blur.layerBackdrop
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
import top.yukonga.miuix.kmp.glass.glassPanel
import top.yukonga.miuix.kmp.material.GlassDefaults
import top.yukonga.miuix.kmp.material.GlassEdgeStrokes
import top.yukonga.miuix.kmp.material.GlassMaterials
import top.yukonga.miuix.kmp.material.GlassShape

val backdrop = rememberLayerBackdrop()
val isDark = MiuixTheme.colorScheme.background.luminance() < 0.5f

Box(modifier = Modifier.fillMaxSize()) {
    // Record an opaque background, excluding the glass panel.
    Box(
        modifier = Modifier
            .matchParentSize()
            .layerBackdrop(backdrop)
            .background(MiuixTheme.colorScheme.background),
    ) {
        Text("Page content", modifier = Modifier.padding(24.dp))
    }
    Box(
        modifier = Modifier
            .align(Alignment.Center)
            .size(240.dp, 120.dp)
            .glassPanel(
                backdrop = backdrop,
                shape = GlassShape(24.dp),
                style = GlassDefaults.style(isDark),
                material = GlassMaterials.puredThinGlass(isDark),
                stroke = GlassEdgeStrokes.forTheme(isDark),
                shading = false,
            )
            .padding(24.dp),
    ) {
        Text("Glass panel")
    }
}
```

### Background Sampling {#background-sampling}

`layerBackdrop` records only its own composable's content, not parent backgrounds. Include an opaque background and exclude the glass surface to avoid self-sampling.

For dialogs and bottom sheets, also exclude their scrims; the surface helpers apply those automatically. To sample page bars, record the page Scaffold and host the overlay in an outer Scaffold. See [Backdrop API](/guide/blur#basic-usage).

## Material Configuration

`GlassStyle`, `GlassStyles`, `GlassMaterial`, `GlassMaterials`, `GlassShape`, `GlassShadow`, `GlassShadows` and `GlassDefaults` live in `miuix-material` (`top.yukonga.miuix.kmp.material`). See [Material definitions](/guide/material#material-definitions) for their properties and presets, and [Edge Stroke](/guide/material#edge-stroke) for the rim stroke.

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
| stroke | EdgeStroke? | Rim stroke (`width`/`bevel` in `Dp`) | null | No |
| shadow | GlassShadow? | Drop shadow | GlassShadows.Regular | No |
| shading | Boolean | Whether to apply refraction and shading | true | No |
| fallback | Modifier | Surface used when backdrop is null | Modifier.clip(shape) | No |

Use `shading = false` for flat glass bars and menus; independent `stroke` remains available. The default `fallback` clips without adding a fill.

With a backdrop, `alpha` affects material opacity, not blur or foreground content. Without a backdrop, it affects the entire fallback layer.

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
| stroke | EdgeStroke? | Rim stroke (`width`/`bevel` in `Dp`) | null | No |
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

`GlassDefaults` is part of `miuix-material`; its constants (`CornerRadius`, `Smoothing`, `SourceDensity`, `NoiseCoefficient`, `Overspill`, `Style`) and the `style(isDark)` helper are listed under [GlassDefaults](/guide/material#glassdefaults-object).

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
