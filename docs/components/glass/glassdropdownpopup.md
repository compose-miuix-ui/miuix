---
title: GlassDropdownPopup
---

# GlassDropdownPopup

`GlassDropdownPopup` presents choices from a settings row. The panel travels along an arc as its size and center settle independently. Row/value anchor modifiers keep the row in place while fading only its displayed value and chevron; when value bounds are reported, horizontal alignment follows that value's side of the page.

<div style="position: relative; height: 380px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../../compose/index.html?id=glassDropdownPopup" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

::: warning Background Sampling
When using background sampling, `backdrop` must record opaque content behind the component and exclude the glass surface that samples it. See [Glass Material](/guide/glass#background-sampling) for setup.
:::

## Import

```kotlin
import top.yukonga.miuix.kmp.glass.GlassDropdownPopup
import top.yukonga.miuix.kmp.glass.GlassPopupItem
import top.yukonga.miuix.kmp.glass.rememberGlassPopupAnchor
import top.yukonga.miuix.kmp.glass.glassPopupAnchorRow
import top.yukonga.miuix.kmp.glass.glassPopupAnchorValue
```

## Basic Usage

This component is a `BoxScope` extension and does not require `MiuixPopupHost`. Place it last in a full-page `Box`, outside clipping containers; it handles outside taps itself. Anchor coordinates must use the same coordinate space as the popup's `Box`.

The `boundsInRoot()` example and `glassPopupAnchorValue` require a popup Box whose origin matches the coordinate root. Without a value anchor, an offset host can instead receive `anchorBounds` converted to the Box's local coordinates. System-bar and IME insets are not applied automatically; `safeMargin` is relative to this Box. Back handling requires a `LocalNavigationEventDispatcherOwner`; outside-tap dismissal works without one.

When closing, keep the component call in composition until the exit animation finishes. Do not wrap the call in `if (show)`.

Report the row and only its value/chevron to coordinate the dropdown transition:

```kotlin
val anchor = rememberGlassPopupAnchor()
val items = listOf("Name", "Date", "Size")
var selected by remember { mutableIntStateOf(0) }
var show by remember { mutableStateOf(false) }
var rowBounds by remember { mutableStateOf(Rect.Zero) }

Box(Modifier.fillMaxSize()) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .glassPopupAnchorRow(anchor)
            .onGloballyPositioned { rowBounds = it.boundsInRoot() }
            .clickable { show = true }
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text("Sort by")
        Text(items[selected], modifier = Modifier.glassPopupAnchorValue(anchor))
    }
    GlassDropdownPopup(
        show = show,
        onDismissRequest = { show = false },
        anchorBounds = rowBounds,
        backdrop = backdrop,
        anchor = anchor,
    ) {
        items.forEachIndexed { index, text ->
            GlassPopupItem(
                text = text,
                selected = selected == index,
                onClick = { selected = index; show = false },
            )
        }
    }
}
```

## Properties

### GlassDropdownPopup Properties

| Property Name | Type | Description | Default Value | Required |
| --- | --- | --- | --- | --- |
| show | Boolean | Whether the panel is open; row input and accessibility actions stop immediately when false | - | Yes |
| onDismissRequest | () -> Unit | Called for an outside tap or Back request | - | Yes |
| anchorBounds | Rect | Settings-row bounds in this popup Box's coordinate space | - | Yes |
| backdrop | Backdrop? | Scene behind the panel; null uses opaque fill with stroke and shadow | - | Yes |
| modifier | Modifier | Modifier applied to the panel | Modifier | No |
| anchor | GlassPopupAnchor? | Optional value alignment and fade coordination anchor; does not supply anchorBounds, backdrop, or visuals | null | No |
| sizing | GlassPopupSizing | Width, height, and safe-margin limits | GlassPopupSizing() | No |
| visuals | GlassPopupVisuals | Panel appearance | GlassPopupDefaults.visuals() | No |
| cornerRadius | Dp | Settled dropdown corner radius | GlassDropdownDefaults.CornerRadius (24.dp) | No |
| contentPadding | PaddingValues | Padding around the rows | PaddingValues(vertical = GlassPopupDefaults.ContentPaddingVertical) | No |
| content | @Composable ColumnScope.() -> Unit | Choice rows, usually GlassPopupItems with selected | - | Yes |

### Defaults

Sizing, visuals, and shared row tokens are documented in [GlassPopup shared defaults](./glasspopup#shared-defaults).

## Advanced Usage

### Value alignment and custom appearance

When value bounds are reported with `glassPopupAnchorValue`, the popup aligns its left edge to the value's left edge if its center is in the page's left half, otherwise its right edge to the value's right edge, constrained by `safeMargin`. Vertical placement still uses `anchorBounds`. Omitting the value modifier falls back to `anchorBounds` placement. The value fades out while the menu is active and normally returns after exit; predictive Back can reveal it during the return gesture. A null `backdrop` uses `visuals.containerColor` directly, not an anchor backdrop. Shared sizing and visuals can be passed to keep multiple dropdowns consistent:

```kotlin
val sizing = GlassPopupSizing(maxWidth = 320.dp, maxHeight = 480.dp)
val visuals = GlassPopupDefaults.visuals(shadow = null)

GlassDropdownPopup(
    show = show,
    onDismissRequest = { show = false },
    anchorBounds = rowBounds,
    backdrop = backdrop,
    anchor = anchor,
    sizing = sizing,
    visuals = visuals,
) {
    GlassPopupItem(text = items[selected], selected = true, onClick = { show = false })
}
```

`GlassDropdownDefaults.CornerRadius` is `24.dp` and `StartCornerRadius` is `4.dp`. The list scrolls within the available viewport and safe margin. Predictive Back follows the arc, recovers on cancellation, and completes dismissal on commit. See the [`GlassTransformPopup` anchor API](./glasstransformpopup#anchor-api) for row/value modifiers.

