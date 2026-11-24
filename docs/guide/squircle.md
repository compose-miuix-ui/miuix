# Squircle Shapes

`miuix-squircle` is a standalone Compose Multiplatform library for squircle (continuous-curvature) rounded rectangles. It provides `Modifier` extensions for fill, clip, and border, plus a low-level `Path` builder across Android, Desktop (JVM), iOS, macOS, and Web (WasmJs/Js).

::: tip Squircle vs. RoundedCornerShape
`RoundedCornerShape` uses circular arcs whose curvature jumps at straight-to-arc tangents. A squircle distributes curvature over a wider corner region for smoother continuous corners, most noticeable at medium to large radii.
:::

::: warning Platform floor
Shader-backed modifiers (`squircleBackground`, `squircleSurface`, `squircleClip`, and `absolute*` variants) require Android API 33+. On older APIs, they—along with `squircleBorder`—automatically fall back to matching `RoundedCornerShape` rendering without manual gating. See [Global Toggle](#global-toggle) for runtime opt-out.
:::

## Setup

```kotlin
kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation("top.yukonga.miuix.kmp:miuix-squircle:<version>")
        }
    }
}
```

Android-only:

```kotlin
dependencies {
    implementation("top.yukonga.miuix.kmp:miuix-squircle-android:<version>")
}
```

## Platform Support

| Platform | Shader modifiers (`Background` / `Surface` / `Clip`) | Path APIs (`squircleBorder`, `addSquircleRect`) |
| --- | --- | --- |
| Android | API 33+ (automatic `RoundedCornerShape` fallback below 33) | All API levels (`squircleBorder` follows `isSquircleEnabled()`) |
| Desktop / iOS / macOS / WasmJs / Js | Supported | Supported |

## Modifiers

### `squircleBackground` — fill only

Fills the squircle silhouette without clipping descendants or allocating an offscreen layer.

```kotlin
Modifier.squircleBackground(
    color = MiuixTheme.colorScheme.primaryVariant,
    cornerRadius = 16.dp,
)
```

### `squircleSurface` — fill + clip

Drop-in replacement for `.clip(RoundedCornerShape(r)).background(color)` with a squircle outline. Fill and child clipping share one offscreen layer.

```kotlin
Modifier.squircleSurface(
    color = MiuixTheme.colorScheme.primaryVariant,
    cornerRadius = 16.dp,
)
```

### `squircleClip` — clip only

Clips descendants to the squircle silhouette without drawing a fill (e.g., around an `Image`).

```kotlin
Modifier.squircleClip(cornerRadius = 16.dp)
```

::: warning Offscreen cost
`squircleSurface` and `squircleClip` (including `absolute*` variants) composite children via a cached offscreen GPU layer per node:
- Pure capsules (all corners `>=` half of the shorter side) bypass it with hardware `CircleShape` clipping.
- Oversized nodes (either side `> 4096 px` or area `> 4096 × 2160`) fall back to cached `Path` clipping.

Prefer constant radii where possible and avoid driving `cornerRadius` from frame-by-frame animations unnecessarily.
:::

### `squircleBorder` — stroke only

Draws a path-based squircle stroke inset by half the stroke width to align with `squircleBackground` / `squircleSurface`. Includes a deferred-read overload (`width: () -> Dp`, `color: () -> Color`) to animate stroke properties in the draw phase without recomposition.

```kotlin
Modifier.squircleBorder(
    width = 1.dp,
    color = MiuixTheme.colorScheme.outline,
    cornerRadius = 16.dp,
)
```

## Per-corner & Absolute Radii

`squircleBackground`, `squircleSurface`, and `squircleClip` provide per-corner overloads (`topStart`, `topEnd`, `bottomEnd`, `bottomStart`) that mirror `RoundedCornerShape` and flip in RTL layouts:

```kotlin
Modifier.squircleSurface(
    color = MiuixTheme.colorScheme.secondaryVariant,
    topStart = 24.dp,
    topEnd = 24.dp,
    bottomEnd = 0.dp,
    bottomStart = 0.dp,
)
```

For layout-direction-agnostic physical corners (`topLeft`, `topRight`, `bottomRight`, `bottomLeft`), use `absoluteSquircleBackground`, `absoluteSquircleSurface`, and `absoluteSquircleClip`.

## Customizing the Curve

`extension` controls how far the continuous-curvature region extends from each corner relative to `cornerRadius`. `1.0f` matches a circular arc; `1.1f` (`SquircleDefaults.Extension`) is the default.

```kotlin
Modifier.squircleBackground(
    color = MiuixTheme.colorScheme.primaryVariant,
    cornerRadius = 24.dp,
    extension = 1.2f,
)
```

## Path API

For custom `Canvas` drawing, `Outline` shapes, or per-frame animated clip paths, use `Path.addSquircleRect`. For static fills and clips, prefer the shader modifiers—they reuse a compile-time baked SDF texture decoded once per process.

```kotlin
val path = Path().apply {
    addSquircleRect(
        width = sizePx.width,
        height = sizePx.height,
        cornerRadius = 20.dp.toPx(),
    )
}
```

A physical-corner overload replaces `cornerRadius` with `topLeft`, `topRight`, `bottomRight`, and `bottomLeft` (`Float`, in pixels).

## Global Toggle

`LocalSquircleEnabled` (default `true`) controls all squircle modifiers (`squircleBackground`, `squircleSurface`, `squircleClip`, `squircleBorder`, and `absolute*` variants). Setting it to `false` switches them to `RoundedCornerShape` fallbacks at runtime:

```kotlin
CompositionLocalProvider(LocalSquircleEnabled provides userPrefersSquircle) {
    AppContent()
}
```

`isSquircleEnabled()` combines `LocalSquircleEnabled.current` with `isRuntimeShaderSupported()`. Capture it inside `@Composable` and forward it to `addSquircleRect` when mixing path drawing with squircle modifiers:

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

## API Reference

### `squircleBackground` / `squircleSurface` / `squircleClip`

| Parameter | Type | Description | Default |
| --- | --- | --- | --- |
| `color` | `Color` | Fill color (omitted on `squircleClip`) | - |
| `cornerRadius` | `Dp` | Uniform corner radius | - |
| `topStart` / `topEnd` / `bottomEnd` / `bottomStart` | `Dp` | Per-corner radii (per-corner overload; `absolute*` uses `topLeft`, `topRight`, `bottomRight`, `bottomLeft`) | - |
| `extension` | `Float` | Corner-tile multiplier, clamped to `[1.0, 2.0]` | `SquircleDefaults.Extension` (`1.1`) |

### `squircleBorder`

| Parameter | Type | Description | Default |
| --- | --- | --- | --- |
| `width` | `Dp` / `() -> Dp` | Stroke width (direct or deferred-read lambda) | - |
| `color` | `Color` / `() -> Color` | Stroke color (direct or deferred-read lambda) | - |
| `cornerRadius` | `Dp` | Uniform corner radius | - |
| `extension` | `Float` | Corner-tile multiplier, clamped to `[1.0, 2.0]` | `SquircleDefaults.Extension` (`1.1`) |

### `Path.addSquircleRect`

| Parameter | Type | Description | Default |
| --- | --- | --- | --- |
| `width` | `Float` | Path width in pixels | - |
| `height` | `Float` | Path height in pixels | - |
| `cornerRadius` | `Float` | Corner radius in pixels (or `topLeft`, `topRight`, `bottomRight`, `bottomLeft`) | - |
| `extension` | `Float` | Corner-tile multiplier, clamped to `[1.0, 2.0]` | `SquircleDefaults.Extension` (`1.1`) |
| `squircleEnabled` | `Boolean` | When `false`, appends a rounded-rect path instead | `true` |

### `SquircleDefaults`

| Constant | Type | Description | Value |
| --- | --- | --- | ---: |
| `Extension` | `Float` | Default corner-tile multiplier | `1.1` |
| `ExtensionMin` | `Float` | Lower bound for `Extension` | `1.0` |
| `ExtensionMax` | `Float` | Upper bound for `Extension` | `2.0` |
