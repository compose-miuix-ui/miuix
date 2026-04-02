# Smooth Shapes

`miuix-shapes` provides smooth rounded corner shapes with continuous curvature transitions for Compose Multiplatform. Unlike standard `RoundedCornerShape` which has an abrupt curvature change where the straight edge meets the arc, smooth shapes gradually ease into the curve for a more natural appearance.

## Setup

Add the `miuix-shapes` dependency to your project:

```kotlin
kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation("top.yukonga.miuix.kmp:miuix-shapes:<version>")
        }
    }
}
```

For Android-only projects:

```kotlin
dependencies {
    implementation("top.yukonga.miuix.kmp:miuix-shapes-android:<version>")
}
```

::: tip
If you are already using `miuix-ui`, smooth shapes are included automatically — no additional dependency is needed.
:::

## Shapes

### SmoothRoundedCornerShape

A rounded rectangle with equal corner radii and smooth curvature transitions.

```kotlin
Box(
    modifier = Modifier
        .size(200.dp)
        .clip(SmoothRoundedCornerShape(24.dp))
        .background(Color.Blue)
)
```

### SmoothCapsuleShape

A capsule (stadium/pill) shape. The corner radius is automatically set to half the shorter dimension.

```kotlin
Box(
    modifier = Modifier
        .width(200.dp)
        .height(48.dp)
        .clip(SmoothCapsuleShape())
        .background(Color.Blue)
)
```

### SmoothUnevenRoundedCornerShape

A rounded rectangle where each corner can have an independent radius. Supports RTL layout direction.

```kotlin
Box(
    modifier = Modifier
        .size(200.dp)
        .clip(
            SmoothUnevenRoundedCornerShape(
                topStart = 32.dp,
                topEnd = 8.dp,
                bottomEnd = 32.dp,
                bottomStart = 8.dp,
            )
        )
        .background(Color.Blue)
)
```

### AbsoluteSmoothUnevenRoundedCornerShape

Similar to `SmoothUnevenRoundedCornerShape`, but uses absolute (physical) corner positions — `topLeft`, `topRight`, `bottomRight`, `bottomLeft` — that remain fixed regardless of layout direction. Use this when you need corners at specific physical screen positions (e.g. matching device screen corners).

```kotlin
Box(
    modifier = Modifier
        .size(200.dp)
        .clip(
            AbsoluteSmoothUnevenRoundedCornerShape(
                topLeft = 32.dp,
                topRight = 8.dp,
                bottomRight = 32.dp,
                bottomLeft = 8.dp,
            )
        )
        .background(Color.Blue)
)
```

## Using with MiuixTheme

When using `miuix-ui`, prefer the theme-aware wrapper functions from `SmoothRounding.kt`. These automatically switch between smooth and standard shapes based on `MiuixTheme.smoothRounding`:

```kotlin
// Equal corners — uses SmoothRoundedCornerShape when smooth rounding is enabled
val shape = miuixShape(16.dp)

// Capsule — uses SmoothCapsuleShape when enabled
val shape = miuixCapsuleShape()

// Independent corners — uses SmoothUnevenRoundedCornerShape when enabled
val shape = miuixUnevenShape(topStart = 16.dp, topEnd = 16.dp)
```

The `smoothRounding` parameter in `MiuixTheme` controls this behavior:

```kotlin
MiuixTheme(smoothRounding = true) {
    // All miuixShape() calls will use smooth shapes
}
```

## Adaptive Corner Behavior

When adjacent corners are too large to fit (e.g. capsule shapes or tight layouts), the smooth corner automatically degrades toward a standard circular arc. This ensures correct rendering at all sizes without manual adjustments.

| Scenario | Behavior |
| :--- | :--- |
| Ample space (e.g. `r=16dp` on a `400×200` card) | Full smooth corner |
| Tight space (e.g. `r=40dp` on a `100×100` box) | Partially smooth |
| Capsule (`r = height/2`) | Standard circular arc |
