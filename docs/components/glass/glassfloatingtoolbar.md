# GlassFloatingToolbar

`GlassFloatingToolbar` is a floating glass container for toolbar actions, typically hosted in a [Scaffold](../basic/scaffold).

<div style="position: relative; height: 240px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../../compose/index.html?id=glassFloatingToolbar" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

::: warning Background Sampling
Record an opaque background without the glass surface itself. See [Glass Surfaces](/guide/glass#background-sampling).
:::

## Import

```kotlin
import top.yukonga.miuix.kmp.glass.GlassFloatingToolbar
```

## Basic Usage

```kotlin
GlassFloatingToolbar(
    backdrop = backdrop,
    cornerRadius = 20.dp,
) {
    Row(
        modifier = Modifier.padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        IconButton(onClick = onEdit) {
            Icon(MiuixIcons.Edit, contentDescription = "Edit")
        }
        IconButton(onClick = onDelete) {
            Icon(MiuixIcons.Delete, contentDescription = "Delete")
        }
    }
}
```

## Properties

### GlassFloatingToolbar Properties

| Property Name | Type | Description | Default Value | Required |
| --- | --- | --- | --- | --- |
| modifier | Modifier | Modifier applied to the toolbar | Modifier | No |
| backdrop | Backdrop? | Background sampling source | null | No |
| cornerRadius | Dp | Corner radius used to build `shape` | GlassFloatingToolbarDefaults.CornerRadius | No |
| shape | GlassShape | Toolbar shape | GlassShape(cornerRadius) | No |
| outSidePadding | PaddingValues | Padding outside the toolbar | GlassFloatingToolbarDefaults.OutSidePadding | No |
| surfaceAlpha | Float | Surface and shadow opacity multiplier | 1f | No |
| style | GlassStyle | Rendering style | GlassDefaults.Style | No |
| material | GlassMaterial | Glass material applied when `backdrop` is provided | GlassFloatingToolbarDefaults.material() | No |
| fill | Color | Fill used without a backdrop | GlassFloatingToolbarDefaults.fill() | No |
| stroke | EdgeStroke? | Surface stroke; null removes it | GlassFloatingToolbarDefaults.stroke() | No |
| shadow | GlassShadow? | Toolbar shadow; null removes it | GlassShadows.Regular | No |
| content | @Composable () -> Unit | Toolbar content | - | Yes |

### GlassFloatingToolbarDefaults Object

#### Constants

| Name | Type | Description | Default Value |
| --- | --- | --- | --- |
| CornerRadius | Dp | Default corner radius | 50.dp |
| OutSidePadding | PaddingValues | Default outside padding | PaddingValues(12.dp, 8.dp) |

#### Functions

| Name | Return Type | Description |
| --- | --- | --- |
| material() | GlassMaterial | Thin glass material (`GlassMaterials.puredThinGlass`) |
| stroke() | EdgeStroke | Theme-adaptive bloom stroke (`GlassEdgeStrokes.forTheme`) |
| fill() | Color | Fallback fill (`MiuixTheme.colorScheme.surfaceContainer`) |
