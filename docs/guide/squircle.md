# Squircle Shapes

`miuix-squircle` is a standalone library that provides squircle (continuous-corner)
rounded-rectangle shapes for Compose Multiplatform. It exposes `Modifier` extensions
for fill / clip / border and a low-level `Path` builder. The library supports
Android, Desktop (JVM), iOS, macOS, and Web (WasmJs/Js).

::: tip Squircle vs. RoundedCornerShape
A `RoundedCornerShape` corner is a pure quarter circle, so the curvature jumps
discontinuously at the point where the straight edge meets the arc. A squircle
distributes the curvature along a wider corner zone, producing the smoother
"continuous corner" look used by modern mobile app icons. Visual differences
are most apparent at moderate to large radii.
:::

::: warning
The shader-backed modifiers (`squircleBackground`, `squircleSurface`,
`squircleClip`) require Android API 33+ on Android. On lower API levels they
**automatically fall back** to the equivalent `RoundedCornerShape` API — no
crash, no manual gating needed. The path-based APIs (`squircleBorder`,
`Path.addSquircleRect`) have no platform floor.
:::

## Setup

Add the `miuix-squircle` dependency to your project:

```kotlin
kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation("top.yukonga.miuix.kmp:miuix-squircle:<version>")
        }
    }
}
```

For Android-only projects:

```kotlin
dependencies {
    implementation("top.yukonga.miuix.kmp:miuix-squircle-android:<version>")
}
```

## Platform Support

| Platform      | Shader-backed modifiers                  | Path-based APIs |
| ------------- | ---------------------------------------- | --------------- |
| Android       | API 33+ (else falls back to rounded arc) | All API levels  |
| Desktop (JVM) | Supported                                | Supported       |
| iOS / macOS   | Supported                                | Supported       |
| WasmJs / Js   | Supported                                | Supported       |

`squircleBackground`, `squircleSurface`, and `squircleClip` are implemented as
an SDF-driven `RuntimeShader` and require API 33 on Android. When the runtime
shader is unavailable, each modifier transparently degrades to
`Modifier.background(color, RoundedCornerShape(...))` /
`Modifier.clip(RoundedCornerShape(...))` so callers do not have to branch.

`squircleBorder` and `Path.addSquircleRect` build the silhouette from cubic
Bézier segments and run on every platform.

### Runtime capability check

If you need to know whether the shader path is active (for example, to vary
some other visual decision), reuse the capability flag exported by
`miuix-shader`:

```kotlin
import top.yukonga.miuix.kmp.shader.isRuntimeShaderSupported

val realSquircle = isRuntimeShaderSupported()
```

## Basic Usage

```kotlin
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.squircle.squircleBackground

Box(
    modifier = Modifier
        .size(120.dp, 80.dp)
        .squircleBackground(
            color = MiuixTheme.colorScheme.primaryVariant,
            cornerRadius = 20.dp,
        ),
)
```

The default `extension = 1.1f` and `control = 0.63f` produce the standard
continuous-corner look. Pass `extension = 1f` and `control = 0.55228f` to
approximate `RoundedCornerShape` of the same radius.

## Variants

### squircleBackground — fill only

Fills the squircle silhouette with a solid color. Descendants are **not**
clipped to the corner — useful when you want to paint a background but still
let children overflow or paint their own corners.

```kotlin
Modifier.squircleBackground(
    color = MiuixTheme.colorScheme.primaryVariant,
    cornerRadius = 16.dp,
)
```

### squircleSurface — fill + clip

Drop-in replacement for `Modifier.clip(RoundedCornerShape(r)).background(color)`
with a squircle outline. Children are clipped to the squircle silhouette in
the same offscreen layer as the fill, so there is no double-pass cost.

```kotlin
Modifier.squircleSurface(
    color = MiuixTheme.colorScheme.primaryVariant,
    cornerRadius = 16.dp,
)
```

### squircleClip — clip only

Clips children to the squircle silhouette without drawing a fill. Useful when
the fill is supplied by something else (an `Image`, another modifier chain,
etc.).

```kotlin
Modifier.squircleClip(cornerRadius = 16.dp)
```

::: warning Offscreen cost
`squircleSurface` and `squircleClip` each open one offscreen GPU layer per
node — the same cost shape as `Modifier.clip(...)` followed by content draw.
The shader output is cached as long as size and parameters are stable; pass
constants where you can and avoid driving `cornerRadius` from a continuously
animating state if you don't have to.
:::

### squircleBorder — stroke only

Draws a squircle-outlined stroke around the layout, inset by half the stroke
width so it lines up with a same-radius `squircleBackground` / `squircleSurface`.
Path-based; no `RuntimeShader` required.

```kotlin
import top.yukonga.miuix.kmp.squircle.squircleBorder

Modifier.squircleBorder(
    width = 1.dp,
    color = MiuixTheme.colorScheme.outline,
    cornerRadius = 16.dp,
)
```

## Per-corner Radii

Every shader-backed modifier has a per-corner overload that matches
`RoundedCornerShape`'s parameter ordering (`topStart`, `topEnd`, `bottomEnd`,
`bottomStart`):

```kotlin
Modifier.squircleSurface(
    color = MiuixTheme.colorScheme.secondaryVariant,
    topStart = 24.dp,
    topEnd = 24.dp,
    bottomEnd = 0.dp,
    bottomStart = 0.dp,
)
```

Mix and match to produce asymmetric shapes (a "card stuck to the top of the
screen", an inset chip with one squared edge, and so on).

## Customizing the Curve

Two scalars control the look of the corner, both clamped to safe ranges:

| Parameter   | Default | Range      | Effect                                                                              |
| ----------- | :-----: | ---------- | ----------------------------------------------------------------------------------- |
| `extension` |  `1.1`  | `[1, 2]`   | Corner-tile size as a multiple of `cornerRadius`. `1.0` is a circular arc.          |
| `control`   | `0.63`  | `[0.3, 0.9]` | Cubic Bézier handle ratio. `0.55228` reproduces a quarter circle.                  |

```kotlin
// More iOS-like: slightly larger corner zone, slightly fuller bulge
Modifier.squircleBackground(
    color = MiuixTheme.colorScheme.primaryVariant,
    cornerRadius = 24.dp,
    extension = 1.2f,
    control = 0.7f,
)
```

Reach for `SquircleDefaults.Extension` / `SquircleDefaults.Control` when you
need to reference the defaults from your own API surface — both are exposed.

## Path API

When you need a squircle outline outside the modifier pipeline (a `clipPath`
reveal that rebuilds per frame, a custom `Canvas` draw, an `Outline` for a
non-shader Shape, etc.), build the path directly:

```kotlin
import androidx.compose.ui.graphics.Path
import top.yukonga.miuix.kmp.squircle.addSquircleRect

val path = Path().apply {
    addSquircleRect(
        width = sizePx.width,
        height = sizePx.height,
        cornerRadius = 20.dp.toPx(),
    )
}
```

The path is built from eight cubic Bézier segments and works on every
platform. For static fills and clips prefer the modifier APIs — they're
shader-accelerated and cache the SDF.

## Properties

### squircleBackground / squircleSurface / squircleClip

| Parameter      | Type    | Description                                              | Default                         |
| -------------- | ------- | -------------------------------------------------------- | ------------------------------- |
| `color`        | `Color` | Fill color (omit on `squircleClip`)                      | -                               |
| `cornerRadius` | `Dp`    | Uniform corner radius                                    | -                               |
| `topStart`     | `Dp`    | Per-corner radius (per-corner overload)                  | -                               |
| `topEnd`       | `Dp`    | Per-corner radius (per-corner overload)                  | -                               |
| `bottomEnd`    | `Dp`    | Per-corner radius (per-corner overload)                  | -                               |
| `bottomStart`  | `Dp`    | Per-corner radius (per-corner overload)                  | -                               |
| `extension`    | `Float` | Corner-tile size multiplier, clamped to `[1, 2]`         | `SquircleDefaults.Extension` = 1.1  |
| `control`      | `Float` | Bézier handle ratio, clamped to `[0.3, 0.9]`             | `SquircleDefaults.Control` = 0.63   |

### squircleBorder

| Parameter      | Type    | Description                                  | Default                         |
| -------------- | ------- | -------------------------------------------- | ------------------------------- |
| `width`        | `Dp`    | Stroke width                                 | -                               |
| `color`        | `Color` | Stroke color                                 | -                               |
| `cornerRadius` | `Dp`    | Uniform corner radius                        | -                               |
| `extension`    | `Float` | Corner-tile size multiplier                  | `SquircleDefaults.Extension`    |
| `control`      | `Float` | Bézier handle ratio                          | `SquircleDefaults.Control`      |

### Path.addSquircleRect

| Parameter      | Type    | Description                                  | Default                         |
| -------------- | ------- | -------------------------------------------- | ------------------------------- |
| `width`        | `Float` | Path width in pixels                         | -                               |
| `height`       | `Float` | Path height in pixels                        | -                               |
| `cornerRadius` | `Float` | Corner radius in pixels                      | -                               |
| `extension`    | `Float` | Corner-tile size multiplier                  | `SquircleDefaults.Extension`    |
| `control`      | `Float` | Bézier handle ratio                          | `SquircleDefaults.Control`      |

### SquircleDefaults

| Constant       | Type    | Description                          | Value  |
| -------------- | ------- | ------------------------------------ | -----: |
| `Extension`    | `Float` | Default corner-tile size multiplier  | `1.1`  |
| `Control`      | `Float` | Default Bézier handle ratio          | `0.63` |
| `ExtensionMin` | `Float` | Lower bound for `Extension`          | `1.0`  |
| `ExtensionMax` | `Float` | Upper bound for `Extension`          | `2.0`  |
| `ControlMin`   | `Float` | Lower bound for `Control`            | `0.3`  |
| `ControlMax`   | `Float` | Upper bound for `Control`            | `0.9`  |
