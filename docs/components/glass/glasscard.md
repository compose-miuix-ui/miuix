---
title: GlassCard
---

# GlassCard

`GlassCard` is a [Card](../basic/card) for content placed on a glass surface such as [GlassBottomSheet](./glassbottomsheet) or [GlassDialog](./glassdialog). It draws a translucent light fill over the glass material instead of an opaque surface color, so the blurred page stays visible through each group.

<div style="position: relative; height: 360px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../../compose/index.html?id=glassCard" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

::: tip Material
`GlassCard` does not sample or blur a backdrop itself. Its fill relies on the glass surface underneath; on an opaque page it looks like a faint tinted card.
:::

## Import

```kotlin
import top.yukonga.miuix.kmp.glass.GlassCard
import top.yukonga.miuix.kmp.glass.GlassCardDefaults
```

## Basic Usage

Place cards inside a glass surface body:

```kotlin
GlassBottomSheet(
    show = showSheet,
    onDismissRequest = { showSheet = false },
    backdrop = backdrop,
    title = "Wallpaper",
) { innerPadding ->
    Column(
        modifier = Modifier.padding(innerPadding),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        GlassCard(
            modifier = Modifier.fillMaxWidth(),
            insideMargin = PaddingValues(horizontal = 16.dp, vertical = 18.dp),
            onClick = { /* Handle click */ },
        ) {
            Text("Choose from gallery")
        }
        GlassCard(modifier = Modifier.fillMaxWidth()) {
            ArrowPreference(title = "Recent wallpapers", onClick = {})
        }
    }
}
```

## Properties

### GlassCard Properties

| Property Name | Type | Description | Default Value | Required |
| --- | --- | --- | --- | --- |
| modifier | Modifier | Modifier applied to the card | Modifier | No |
| cornerRadius | Dp | Card corner radius | GlassCardDefaults.CornerRadius | No |
| insideMargin | PaddingValues | Card inner padding | GlassCardDefaults.InsideMargin | No |
| colors | CardColors | Fill and content colors | GlassCardDefaults.colors() | No |
| pressFeedbackType | PressFeedbackType | Press feedback type (interactive overload) | PressFeedbackType.None | No |
| showIndication | Boolean | Show indication on interaction (interactive overload) | false | No |
| holdDownState | Boolean | Whether the card is held down (interactive overload) | false | No |
| onClick | (() -> Unit)? | Click callback (interactive overload) | null | No |
| onLongPress | (() -> Unit)? | Long press callback (interactive overload) | null | No |
| content | @Composable ColumnScope.() -> Unit | Card content | - | Yes |

### GlassCardDefaults Object

#### Constants

| Constant Name | Type | Description | Value |
| --- | --- | --- | --- |
| CornerRadius | Dp | Default corner radius | 20.dp |
| InsideMargin | PaddingValues | Default inner padding | CardDefaults.InsideMargin |

#### Methods

| Method Name | Return Type | Description |
| --- | --- | --- |
| color() | Color | Translucent fill derived from the current theme color scheme |
| colors(color, contentColor) | CardColors | Card colors; defaults to `color()` and `onSurfaceContainer` |

## Advanced Usage

### Custom Fill

```kotlin
GlassCard(
    colors = GlassCardDefaults.colors(color = Color(0x33FFFFFF)),
) {
    Text("Stronger fill")
}
```
