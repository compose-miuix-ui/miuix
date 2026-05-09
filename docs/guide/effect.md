# Effect Library

`miuix-effect` is a Compose Multiplatform visual effects library built on top of `miuix-blur`. It provides liquid glass effects (refraction, vibrancy, inner shadow), animation utilities (damped drag, interactive highlight), highlight configuration, and predefined color blend tokens. The library targets Android, Desktop (JVM), iOS, macOS, and Web (WasmJs/Js).

::: warning
Most effects require `isRuntimeShaderSupported()` (Android API 33+, or Skiko platforms). On unsupported platforms, effects degrade gracefully (no-ops or simplified rendering).
:::

## Setup

Add the `miuix-effect` dependency to your project:

```kotlin
kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation("top.yukonga.miuix.kmp:miuix-effect:<version>")
        }
    }
}
```

`miuix-effect` transitively exposes `miuix-blur` APIs, so you do not need a separate dependency for blur types (`Backdrop`, `BackdropEffectScope`, `Highlight`, etc.).

## Package Structure

| Package | Contents |
| --- | --- |
| `top.yukonga.miuix.kmp.effect` | Background gradient effect (`BgEffectBackground`, `DeviceType`) |
| `top.yukonga.miuix.kmp.effect.liquid` | Liquid glass effects (refraction lens, vibrancy, inner shadow, combined backdrop) |
| `top.yukonga.miuix.kmp.effect.animation` | Animation utilities (damped drag, interactive highlight) |
| `top.yukonga.miuix.kmp.effect.highlight` | Highlight configuration and sensor-driven container highlights |
| `top.yukonga.miuix.kmp.effect.blend` | Predefined color blend token lists |

---

## Background Effect

`BgEffectBackground` renders an animated gradient background using GPU-accelerated runtime shaders. See the existing [Blur Effects](blur.md) documentation for backdrop blur integration.

```kotlin
import top.yukonga.miuix.kmp.effect.BgEffectBackground
import top.yukonga.miuix.kmp.effect.DeviceType

BgEffectBackground(
    dynamicBackground = true,
    deviceType = DeviceType.PHONE,
    isDarkTheme = isSystemInDarkTheme(),
    surface = MiuixTheme.colorScheme.surface,
    modifier = Modifier.fillMaxWidth().height(300.dp),
) {
    Text("Foreground content")
}
```

---

## Liquid Glass Effects

### Refraction Lens

`lens()` applies a rounded-rectangle refraction effect to backdrop content, displacing it inward from the edges to simulate light passing through glass.

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

#### Chromatic Aberration

Enable rainbow fringing at the refraction rim:

```kotlin
lens(
    refractionHeight = 24.dp.toPx(),
    refractionAmount = 24.dp.toPx(),
    chromaticAberration = 0.2f,  // 0.1 subtle, 0.2 Apple-like, 0.3+ pronounced
)
```

#### lens Parameters

| Parameter | Type | Description | Default |
| --- | --- | --- | --- |
| refractionHeight | Float | Height of the refraction band in pixels | - (required) |
| refractionAmount | Float | Maximum displacement amount in pixels | - (required) |
| depthEffect | Boolean | Radial depth simulation | `false` |
| chromaticAberration | Float | Chromatic dispersion strength (`0` disables) | `0f` |

### Vibrancy

`vibrancy()` boosts backdrop saturation to 1.5x, producing the vivid "glass" appearance characteristic of liquid glass effects.

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

### Inner Shadow

`innerShadow()` renders a soft shadow inside a clipped shape, simulating recessed depth.

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

#### InnerShadow Properties

| Property | Type | Description | Default |
| --- | --- | --- | --- |
| radius | Dp | Blur radius for shadow softness | `24.dp` |
| offset | DpOffset | Directional offset from edge | `DpOffset(0.dp, radius)` |
| color | Color | Shadow fill color | `Color.Black.copy(alpha = 0.15f)` |
| alpha | Float | Overall opacity multiplier | `1f` |
| blendMode | BlendMode | Compositing mode | `DrawScope.DefaultBlendMode` |

### Combined Backdrop

`CombinedBackdrop` layers two `Backdrop` instances into a single sampling source. Useful for merging a base content backdrop with a local recording.

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

## Animation Utilities

### Damped Drag Animation

`DampedDragAnimation` provides a multi-property animation controller for draggable UI elements with spring physics, press/release scale transitions, and velocity tracking.

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

#### DampedDragAnimation Properties

| Property | Type | Description |
| --- | --- | --- |
| value | Float | Current animated position |
| targetValue | Float | Target position being animated toward |
| pressProgress | Float | Press state `[0, 1]` |
| scaleX | Float | Current horizontal scale |
| scaleY | Float | Current vertical scale |
| velocity | Float | Normalized velocity |

#### DampedDragAnimation Parameters

| Parameter | Type | Description | Default |
| --- | --- | --- | --- |
| animationScope | CoroutineScope | Scope for animation coroutines | - (required) |
| initialValue | Float | Starting position | - (required) |
| valueRange | ClosedRange\<Float\> | Allowed position range | - (required) |
| visibilityThreshold | Float | Minimum visible change | - (required) |
| initialScale | Float | Resting scale | - (required) |
| pressedScale | Float | Pressed scale | - (required) |
| canDrag | (Offset) -> Boolean | Drag acceptance predicate | `{ true }` |
| onDragStarted | (Offset) -> Unit | Drag start callback | - (required) |
| onDragStopped | () -> Unit | Drag end callback | - (required) |
| onDrag | (IntSize, Offset) -> Unit | Drag event callback | - (required) |

### Interactive Highlight

`InteractiveHighlight` renders a touch-responsive radial spotlight that follows the pointer position, with intensity driven by spring-animated press progress.

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
        .then(highlight.modifier)       // Drawing overlay
        .then(highlight.gestureModifier) // Touch handling
)
```

---

## Highlight Configuration

### HighlightConfig

`HighlightConfig` maps container size categories to the appropriate `Highlight` presets:

```kotlin
import top.yukonga.miuix.kmp.effect.highlight.HighlightConfig

val highlight = HighlightConfig.get(
    container = HighlightConfig.Container.Medium,
    isDark = isSystemInDarkTheme(),
)
```

#### Container Sizes

| Container | Visual | Preset (Light / Dark) |
| --- | --- | --- |
| Disabled | No highlight | `null` |
| Large | Full-width cards, dialogs | `GlassStrokeBigLight` / `GlassStrokeBigDark` |
| Medium | Standard cards, sheets | `GlassStrokeMiddleLight` / `GlassStrokeMiddleDark` |
| Small | Chips, compact buttons | `GlassStrokeSmallLight` / `GlassStrokeSmallDark` |

### Sensor-Driven Container Highlight

`rememberContainerHighlight` combines `HighlightConfig` with device tilt sensor data for parallax lighting:

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

## Color Blend Tokens

`ColorBlendToken` provides 30+ predefined blend configurations organized by series and weight for both light and dark themes.

### Series Overview

| Series | Character | Typical Use |
| --- | --- | --- |
| Info | Neutral grey | Informational surfaces |
| Colored | Tinted with Overlay/SoftLight | Rich, colorful surfaces |
| Pured | High-contrast with ColorBurn/HardLight | Bold, saturated surfaces |
| Overlay | Luminosity-based | Translucent overlays |

### Weights

| Weight | Opacity | Description |
| --- | --- | --- |
| Extra_Thin | Minimal | Barely visible tint |
| Thin | Light | Subtle presence |
| Regular | Standard | Balanced opacity |
| Thick | Strong | High presence |
| Extra_Thick | Maximum | Near-opaque |

### Usage

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

### Available Tokens

| Token | Light | Dark |
| --- | --- | --- |
| Info_Extra_Thin | `Info_Extra_Thin_Light` | `Info_Extra_Thin_Dark` |
| Info_Thin | `Info_Thin_Light` | `Info_Thin_Dark` |
| Info_Regular | `Info_Regular_Light` | `Info_Regular_Dark` |
| Info_Thick | `Info_Thick_Light` | `Info_Thick_Dark` |
| Info_Colored_Regular | `Info_Colored_Regular` | (same) |
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

## Complete Example: Liquid Glass Card

Combining multiple effects into a single liquid glass card:

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
    // Background content
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
    Text("Liquid Glass Card")
}
```
