---
title: GlassSecondaryPopup
---

# GlassSecondaryPopup

`GlassSecondaryPopup` is a glass submenu anchored to a row in a primary menu.

<div style="position: relative; height: 440px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../../compose/index.html?id=glassSecondaryPopup" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

::: warning Background Sampling
When using background sampling, `backdrop` must record opaque content behind the component and exclude the glass surface that samples it. See [Glass Material](/guide/glass#background-sampling) for setup.
:::

## Import

```kotlin
import top.yukonga.miuix.kmp.glass.GlassIconButton
import top.yukonga.miuix.kmp.glass.GlassTransformPopup
import top.yukonga.miuix.kmp.glass.GlassSecondaryPopup
import top.yukonga.miuix.kmp.glass.GlassPopupItem
import top.yukonga.miuix.kmp.glass.GlassPopupSizing
import top.yukonga.miuix.kmp.glass.GlassPopupDefaults
import top.yukonga.miuix.kmp.glass.rememberGlassPopupAnchor
import top.yukonga.miuix.kmp.glass.glassPopupAnchor
import top.yukonga.miuix.kmp.glass.glassPopupAnchorContent
```

## Basic Usage

- Place both popups in full-page Boxes sharing the root origin, outside clipping containers.
- Provide system/IME padding as needed and a `LocalNavigationEventDispatcherOwner` for Back handling.
- Control visibility with `show`; do not wrap the calls in `if (show)`.

The primary backdrop records the page. A second backdrop records that page plus the primary popup, and the secondary popup is drawn outside it:

```kotlin
val anchor = rememberGlassPopupAnchor()
val secondaryBackdrop = rememberLayerBackdrop()
val density = LocalDensity.current
var show by remember { mutableStateOf(false) }
var secondary by remember { mutableStateOf(false) }
var stacked by remember { mutableStateOf(false) }
var rowBounds by remember { mutableStateOf(Rect.Zero) }
var frozenBounds by remember { mutableStateOf(Rect.Zero) }
var primaryWidth by remember { mutableStateOf(GlassPopupDefaults.MinWidth) }

Box(Modifier.fillMaxSize()) {
    Box(Modifier.fillMaxSize().layerBackdrop(secondaryBackdrop)) {
        Box(Modifier.fillMaxSize().background(MiuixTheme.colorScheme.background)) {
            GlassIconButton(
                onClick = { show = true },
                backdrop = backdrop,
                modifier = Modifier.glassPopupAnchor(anchor, cornerRadius = 20.dp),
            ) {
                Text("⋯", modifier = Modifier.glassPopupAnchorContent(anchor))
            }
        }
        GlassTransformPopup(
            show = show,
            onDismissRequest = { show = false },
            anchor = anchor,
            backdrop = backdrop,
            anchorContent = { Text("⋯") },
            stacked = stacked,
            onMeasured = { primaryWidth = with(density) { it.width.toDp() } },
        ) {
            GlassPopupItem(
                text = "Sort by",
                showArrow = true,
                arrowRotation = { if (stacked) 90f * (1f - anchor.secondaryBackProgress) else 0f },
                modifier = Modifier.onGloballyPositioned {
                    if (!stacked) rowBounds = it.boundsInRoot()
                },
                onClick = {
                    frozenBounds = rowBounds
                    stacked = true
                    secondary = true
                },
            )
        }
    }
    GlassSecondaryPopup(
        show = secondary,
        onDismissRequest = { secondary = false },
        anchorBounds = frozenBounds,
        backdrop = secondaryBackdrop,
        materialAnchor = anchor,
        sizing = GlassPopupSizing(minWidth = primaryWidth),
        onDismissFinished = { stacked = false },
    ) {
        GlassPopupItem(text = "Back", onClick = { secondary = false })
        GlassPopupItem(text = "Name", onClick = { secondary = false })
        GlassPopupItem(text = "Date", onClick = { secondary = false })
    }
}
```

## Properties

### GlassSecondaryPopup Properties

| Property Name | Type | Description | Default Value | Required |
| --- | --- | --- | --- | --- |
| show | Boolean | Whether the secondary menu is open; keep the call composed during collapse | - | Yes |
| onDismissRequest | () -> Unit | Called for outside tap or Back to return to the primary menu | - | Yes |
| anchorBounds | Rect | Frozen resting trigger-row bounds in this popup's coordinate root | - | Yes |
| backdrop | Backdrop? | Scene containing the page and primary popup, excluding this popup; null tries materialAnchor's button backdrop, then the resolved visuals' containerColor | - | Yes |
| modifier | Modifier | Modifier applied to the panel | Modifier | No |
| materialAnchor | GlassPopupAnchor? | Primary anchor whose expanded appearance and Back progress are shared | null | No |
| sizing | GlassPopupSizing | Panel limits; set minWidth to the primary panel width | GlassPopupSizing() | No |
| visuals | GlassPopupVisuals | Fallback appearance; published expanded popup visuals take precedence even when their material is null | GlassPopupDefaults.visuals() | No |
| cornerRadius | Dp | Panel corner radius during the transition | GlassPopupDefaults.CornerRadius (24.dp) | No |
| contentPadding | PaddingValues | Padding around menu rows | PaddingValues(vertical = GlassPopupDefaults.ContentPaddingVertical) | No |
| onDismissFinished | (() -> Unit)? | Called after collapse completes following an opening; not called for the initial closed state | null | No |
| content | @Composable ColumnScope.() -> Unit | Header row followed by secondary choices | - | Yes |

### Defaults

Sizing and appearance options are documented in [GlassPopup shared defaults](./glasspopup#shared-defaults).

## Advanced Usage

Use `GlassIconDropdownMenu` to manage grouped entries automatically. Set `collapseOnSelection = true` to close both menu levels when an option is selected, or `false` to keep the submenu open.

### Stacking and dismissal

Freeze the trigger row before setting `stacked = true`. Measure the primary width with `onMeasured` and pass it as `minWidth`. Keep the primary stacked until `onDismissFinished`:

```kotlin
GlassSecondaryPopup(
    show = secondary,
    onDismissRequest = { secondary = false },
    anchorBounds = frozenBounds,
    backdrop = secondaryBackdrop,
    materialAnchor = anchor,
    sizing = GlassPopupSizing(minWidth = primaryWidth),
    onDismissFinished = { stacked = false },
) {
    GlassPopupItem(text = "Back", onClick = { secondary = false })
}
```

Pass the secondary backdrop explicitly; `materialAnchor` shares the primary's appearance, not its scene recording.

