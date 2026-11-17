# GlassFloatingActionButton

`GlassFloatingActionButton` is a round glass floating action button with the same glass stroke, shadow, and press feedback as [GlassIconButton](./glassiconbutton), tinted with the primary theme color by default.

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

When no backdrop is available, the button uses `fill` (`MiuixTheme.colorScheme.primary` by default).

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
| fill | Color | Fill used without a backdrop | GlassFloatingActionButtonDefaults.fill() | No |
| stroke | EdgeStroke? | Surface stroke; null removes it | GlassFloatingActionButtonDefaults.stroke() | No |
| shadow | GlassShadow? | Button shadow; null removes it | GlassShadows.Regular | No |
| content | @Composable () -> Unit | Centered button content | - | Yes |

### GlassFloatingActionButtonDefaults Object

#### Constants

| Name | Type | Description | Default Value |
| --- | --- | --- | --- |
| Size | Dp | Button diameter | 60.dp |

#### Functions

| Name | Return Type | Description |
| --- | --- | --- |
| material() | GlassMaterial | Primary-tinted glass material (`SrcOver`, `0.92f` alpha) |
| fill() | Color | Fallback fill (`MiuixTheme.colorScheme.primary`) |
| stroke() | EdgeStroke | Bloom stroke matching `GlassTopAppBarDefaults.buttonStroke()` |
