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

::: warning Platform floor
Shader-backed modifiers (`squircleBackground`, `squircleSurface`,
`squircleClip`, `squircleBorder`) require Android API 33+. On older API levels
they fall back to the matching `RoundedCornerShape` rendering — no crash, no
manual gating. See [Global Toggle](#global-toggle) for the runtime opt-out.
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

Fallback is automatic — call sites do not branch. The path-based
`Path.addSquircleRect` works on every platform but accepts an explicit
`squircleEnabled` flag so callers can mirror the modifier fallback when they
need consistency.

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
node. The shader output is cached as long as size and parameters are stable;
pass constants where you can and avoid driving `cornerRadius` from a
continuously animating state if you don't have to.
:::

### squircleBorder — stroke only

Draws a squircle-outlined stroke around the layout, inset by half the stroke
width so it lines up with a same-radius `squircleBackground` / `squircleSurface`.
Path-based (no shader required); cheaper than the shader variants.

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

## Absolute Corners (layout-direction-agnostic)

`absoluteSquircleBackground`, `absoluteSquircleClip`, and `absoluteSquircleSurface`
mirror the per-corner overloads but take physical corners (`topLeft`, `topRight`,
`bottomRight`, `bottomLeft`, clockwise from top-left) that are **not** flipped by
`LocalLayoutDirection` — the same relationship `AbsoluteRoundedCornerShape` has to
`RoundedCornerShape`. Reach for these when corners must anchor to physical sides
regardless of RTL/LTR (e.g. a transition reveal tied to a swipe edge).

```kotlin
Modifier.absoluteSquircleSurface(
    color = MiuixTheme.colorScheme.surface,
    topLeft = 24.dp,
    topRight = 24.dp,
    bottomRight = 0.dp,
    bottomLeft = 0.dp,
)
```

## Customizing the Curve

`extension` controls how far the continuous-curvature zone extends from the
corner. `1.0` matches a circular arc; the default `1.1` gives the standard
squircle look.

| Parameter   | Default | Range    | Effect                                                                    |
| ----------- | :-----: | -------- | ------------------------------------------------------------------------- |
| `extension` |  `1.1`  | `[1, 2]` | Corner-tile size as a multiple of `cornerRadius`. `1.0` is a circular arc. |

```kotlin
Modifier.squircleBackground(
    color = MiuixTheme.colorScheme.primaryVariant,
    cornerRadius = 24.dp,
    extension = 1.2f,
)
```

Reference the default via `SquircleDefaults.Extension`.

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

For static fills and clips prefer the shader-backed modifiers — they cache
their distance field once per process.

## Global Toggle

`LocalSquircleEnabled` (default `true`) gates every shader-backed squircle
modifier. Set it to `false` to force the `RoundedCornerShape` fallback at
runtime — useful for visual preferences or A/B comparisons:

```kotlin
import top.yukonga.miuix.kmp.squircle.LocalSquircleEnabled

CompositionLocalProvider(LocalSquircleEnabled provides userPrefersRoundedRects) {
    AppContent()
}
```

`isSquircleEnabled()` combines `LocalSquircleEnabled.current` with the runtime
shader-support check. Use it inside `@Composable` to keep mixed shader+path
visuals in sync — capture the boolean and pass it to `addSquircleRect`:

```kotlin
@Composable
fun OutlinedSurface(cornerRadius: Dp) {
    val squircleEnabled = isSquircleEnabled()
    val path = remember { Path() }
    Box(
        Modifier
            .squircleBackground(MiuixTheme.colorScheme.surface, cornerRadius)
            .drawWithContent {
                drawContent()
                path.rewind()
                path.addSquircleRect(
                    width = size.width,
                    height = size.height,
                    cornerRadius = cornerRadius.toPx(),
                    squircleEnabled = squircleEnabled,
                )
                drawPath(path, MiuixTheme.colorScheme.outline, style = Stroke(1.dp.toPx()))
            },
    )
}
```

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

### squircleBorder

| Parameter      | Type    | Description                                  | Default                         |
| -------------- | ------- | -------------------------------------------- | ------------------------------- |
| `width`        | `Dp`    | Stroke width                                 | -                               |
| `color`        | `Color` | Stroke color                                 | -                               |
| `cornerRadius` | `Dp`    | Uniform corner radius                        | -                               |
| `extension`    | `Float` | Corner-tile size multiplier                  | `SquircleDefaults.Extension`    |

### Path.addSquircleRect

| Parameter         | Type      | Description                                                        | Default                         |
| ----------------- | --------- | ------------------------------------------------------------------ | ------------------------------- |
| `width`           | `Float`   | Path width in pixels                                               | -                               |
| `height`          | `Float`   | Path height in pixels                                              | -                               |
| `cornerRadius`    | `Float`   | Corner radius in pixels                                            | -                               |
| `extension`       | `Float`   | Corner-tile size multiplier                                        | `SquircleDefaults.Extension`    |
| `squircleEnabled` | `Boolean` | When `false`, appends a plain rounded rect of the same dimensions  | `true`                          |

### SquircleDefaults

| Constant       | Type    | Description                          | Value  |
| -------------- | ------- | ------------------------------------ | -----: |
| `Extension`    | `Float` | Default corner-tile size multiplier  | `1.1`  |
| `ExtensionMin` | `Float` | Lower bound for `Extension`          | `1.0`  |
| `ExtensionMax` | `Float` | Upper bound for `Extension`          | `2.0`  |
