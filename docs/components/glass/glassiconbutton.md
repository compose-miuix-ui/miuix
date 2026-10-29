# GlassIconButton

`GlassIconButton` is a round glass button for toolbar controls and floating actions. In [GlassTopAppBar](./glasstopappbar), it inherits the bar's button surface.

<div style="position: relative; height: 240px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../../compose/index.html?id=glassIconButton" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

::: warning Background Sampling
Record an opaque background without the glass surface itself. See [Glass Material](/guide/glass#background-sampling).
:::

## Import

```kotlin
import top.yukonga.miuix.kmp.glass.GlassIconButton
```

## Basic Usage

When no backdrop is available, the button uses `fill`. The content is centered; set the icon size and tint in the content slot.

```kotlin
GlassIconButton(
    onClick = onAction,
    backdrop = backdrop,
) {
    Icon(
        imageVector = actionIcon,
        contentDescription = "Search",
        modifier = Modifier.size(24.dp),
    )
}
```

## Properties

### GlassIconButton Properties

The defaults below apply outside `GlassTopAppBar`.

| Property Name | Type | Description | Default Value | Required |
| --- | --- | --- | --- | --- |
| onClick | () -> Unit | Callback triggered on click | - | Yes |
| modifier | Modifier | Modifier applied to the button | Modifier | No |
| backdrop | Backdrop? | Background sampling source; a non-null top-bar source takes precedence | null | No |
| surfaceAlpha | Float | Surface opacity multiplier | 1f | No |
| style | GlassStyle | Rendering style; does not replace the button's material | GlassDefaults.Style | No |
| size | Dp | Button diameter | GlassTopAppBarDefaults.ButtonSize | No |
| shape | GlassShape | Button shape | GlassShape(size / 2) | No |
| fill | Color | Fill used without a backdrop | GlassTopAppBarDefaults.buttonFill() | No |
| stroke | GlassStroke? | Surface stroke; null removes it | GlassTopAppBarDefaults.buttonStroke() | No |
| shadow | GlassShadow? | Button shadow; null removes it | GlassShadows.Regular | No |
| content | @Composable () -> Unit | Centered button content | - | Yes |

The button requests a square of `size` and adds no separate minimum touch target. Choose a size suitable for accessibility and avoid clipping its shadow.

### GlassTopAppBarDefaults Object {#defaults}

`GlassIconButton` shares the top bar's defaults. The following values apply to buttons; see [GlassTopAppBar](./glasstopappbar#glasstopappbardefaults) for the full object.

#### Constants

| Constant Name | Type | Description | Default Value |
| --- | --- | --- | --- |
| ButtonSize | Dp | Button diameter | 44.dp |
| PressedContentAlpha | Float | Content opacity while pressed | 0.6f |

#### Methods

| Method Name | Return Type | Description |
| --- | --- | --- |
| buttonFill() | Color | White in light mode; `#FF2C2C2C` in dark mode |
| buttonMaterial() | GlassMaterial | Theme-selected `GlassMaterials.puredThinGlass` |
| buttonStroke() | GlassStroke | `GlassStrokes.SmallLight` in light mode; `SmallDark` in dark mode |
| buttonPressedOverlay() | Color | Black at 0.10 alpha in light mode; white at 0.14 alpha in dark mode |

## Advanced Usage

### Inheriting Top-Bar Material

```kotlin
GlassTopAppBar(
    title = "Library",
    isContentScrolled = listState.canScrollBackward,
    backdrop = backdrop,
    actions = {
        GlassIconButton(onClick = onAction) {
            Icon(actionIcon, contentDescription = "Search")
        }
    },
)
```

Inside `GlassTopAppBar`, buttons inherit its appearance and backdrop. Outside it, including in a navigation bar's trailing action, pass the backdrop explicitly.

### Custom Press Scaling {#press-feedback-and-glasspress}

For a custom clickable surface, share one interaction source between `glassPress` and `clickable`:

```kotlin
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import top.yukonga.miuix.kmp.glass.glassPress

val interactionSource = remember { MutableInteractionSource() }

Text(
    text = "Open",
    modifier = Modifier
        .glassPress(interactionSource)
        .clickable(
            interactionSource = interactionSource,
            indication = null,
            onClick = onAction,
        )
        .padding(16.dp),
)
```

`Modifier.glassPress` animates drawing scale; it does not provide click handling or glass material.

| Property Name | Type | Description | Default Value | Required |
| --- | --- | --- | --- | --- |
| interactionSource | InteractionSource | Source of press interactions | - | Yes |
| enabled | Boolean | Whether to apply scaling; does not disable clicks | true | No |

### Using as a Popup Anchor

Use `Modifier.glassPopupAnchor(anchor, cornerRadius)` to connect the button to a [GlassTransformPopup](./glasstransformpopup#anchor-api).
