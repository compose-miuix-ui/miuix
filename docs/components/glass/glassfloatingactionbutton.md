# GlassFloatingActionButton

`GlassFloatingActionButton` is a round floating action button with a primary-coloured glass surface. It scales to 90% on press and springs back on release or cancellation.

<div style="position: relative; height: 240px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../../compose/index.html?id=glassFloatingActionButton" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

::: warning Background Sampling
Record an opaque background without the glass surface itself. See [Glass Surfaces](/guide/glass#background-sampling).
:::

## Import

```kotlin
import top.yukonga.miuix.kmp.glass.GlassFloatingActionButton
```

## Basic Usage

With a backdrop, the button blends a translucent primary layer over the blurred glass. When backdrop rendering is unavailable, it uses the theme's primary colour as the fallback fill.

```kotlin
GlassFloatingActionButton(
    onClick = onAction,
    backdrop = backdrop,
) {
    Icon(
        imageVector = actionIcon,
        tint = MiuixTheme.colorScheme.onPrimary,
        contentDescription = "Add",
    )
}
```

## Properties

### GlassFloatingActionButton Properties

| Property Name | Type | Description | Default Value | Required |
| --- | --- | --- | --- | --- |
| onClick | () -> Unit | Callback triggered on click | - | Yes |
| modifier | Modifier | Modifier applied to the button | Modifier | No |
| backdrop | Backdrop? | Background sampling source | null | No |
| surfaceAlpha | Float | Surface opacity multiplier | 1f | No |
| style | GlassStyle | Rendering style | GlassDefaults.Style | No |
| material | GlassMaterial | Glass material applied when `backdrop` is provided | GlassFloatingActionButtonDefaults.material() | No |
| size | Dp | Button diameter | GlassFloatingActionButtonDefaults.Size | No |
| shape | GlassShape | Button shape | GlassShape(size / 2) | No |
| fill | Color | Fill used when backdrop rendering is unavailable | GlassFloatingActionButtonDefaults.fill() | No |
| stroke | EdgeStroke? | Surface stroke; null removes it | GlassFloatingActionButtonDefaults.stroke() | No |
| shadow | GlassShadow? | Button shadow; null removes it | GlassShadows.Regular | No |
| content | @Composable () -> Unit | Centered button content | - | Yes |

### GlassFloatingActionButtonDefaults Object

#### Constants

| Name | Type | Description | Default Value |
| --- | --- | --- | --- |
| Size | Dp | Button diameter | 56.dp |

#### Functions

| Name | Return Type | Description |
| --- | --- | --- |
| material() | GlassMaterial | Glass with 20.dp blur, two PuredThin layers, and a primary colour layer at 92% opacity |
| fill() | Color | Fallback fill (`MiuixTheme.colorScheme.primary`) |
| stroke() | EdgeStroke | Bloom stroke matching `GlassTopAppBarDefaults.buttonStroke()` |
