---
title: GlassTransformPopup
---

# GlassTransformPopup

`GlassTransformPopup` is a glass menu that transforms from an associated control.

<div style="position: relative; height: 380px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../../compose/index.html?id=glassTransformPopup" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

::: warning Background Sampling
When using background sampling, `backdrop` must record opaque content behind the component and exclude the glass surface that samples it. See [Glass Material](/guide/glass#background-sampling) for setup.
:::

## Import

```kotlin
import top.yukonga.miuix.kmp.glass.GlassIconButton
import top.yukonga.miuix.kmp.glass.GlassTransformPopup
import top.yukonga.miuix.kmp.glass.GlassPopupItem
import top.yukonga.miuix.kmp.glass.rememberGlassPopupAnchor
import top.yukonga.miuix.kmp.glass.glassPopupAnchor
import top.yukonga.miuix.kmp.glass.glassPopupAnchorContent
```

## Basic Usage

This `BoxScope` extension does not require `MiuixPopupHost`. Place it last in a full-page `Box`, outside clipping containers; it handles outside taps. Anchor coordinates must use the popup `Box`'s coordinate space.

Anchor modifiers report root bounds, so the popup Box must start at the root origin; do not place it inside inset padding or an offset area. System-bar and IME insets are not applied automatically; `safeMargin` is relative to this Box. Back handling requires `LocalNavigationEventDispatcherOwner`, while outside taps work without it.

Keep the call in composition until exit animation completes; do not wrap it in `if (show)`.


Use one remembered anchor for the control and menu. Mark only the content that the popup should copy when the control has its own background:

```kotlin
val anchor = rememberGlassPopupAnchor()
var show by remember { mutableStateOf(false) }

Box(Modifier.fillMaxSize()) {
    GlassIconButton(
        onClick = { show = true },
        backdrop = backdrop,
        modifier = Modifier.glassPopupAnchor(anchor, cornerRadius = 20.dp),
    ) {
        Text("⋯", modifier = Modifier.glassPopupAnchorContent(anchor))
    }
    GlassTransformPopup(
        show = show,
        onDismissRequest = { show = false },
        anchor = anchor,
        backdrop = backdrop,
        anchorContent = { Text("⋯") },
    ) {
        GlassPopupItem(text = "Rename", onClick = { show = false })
        GlassPopupItem(text = "Delete", onClick = { show = false })
    }
}
```

Without `glassPopupAnchorContent`, `anchorContent` represents the whole control and should match the reported anchor content.

## Properties

### GlassTransformPopup Properties

| Property Name | Type | Description | Default Value | Required |
| --- | --- | --- | --- | --- |
| show | Boolean | Whether the menu is open; keep the call composed during exit | - | Yes |
| onDismissRequest | () -> Unit | Called for an outside tap or Back request | - | Yes |
| anchor | GlassPopupAnchor | Measured control and shared material anchor | - | Yes |
| backdrop | Backdrop? | Expanded-menu scene; null falls back to the attached button backdrop, then to visuals.containerColor if neither exists | - | Yes |
| anchorContent | @Composable () -> Unit | Visual copy of the control or its marked content | - | Yes |
| modifier | Modifier | Modifier applied to the panel | Modifier | No |
| simplified | Boolean | Omits the outgoing content copy during ordinary opening; the copy still participates in closing and predictive Back | false | No |
| stacked | Boolean | Shrinks and masks this menu while a secondary menu is in front; its rows stop accepting input | false | No |
| sizing | GlassPopupSizing | Width, height, and safe-margin limits | GlassPopupSizing() | No |
| visuals | GlassPopupVisuals | Expanded panel appearance | GlassPopupDefaults.visuals() | No |
| anchorAlpha | Float | Anchor background opacity when no shared button surface is available | 1f | No |
| maskColor | Color | Wash over the primary menu while stacked | GlassPopupDefaults.maskColor() | No |
| cornerRadius | Dp | Settled panel corner radius | GlassPopupDefaults.CornerRadius (24.dp) | No |
| gap | Dp | Downward offset applied to the anchor before resolving settled panel placement; not a guaranteed edge-to-edge gap | 0.dp | No |
| contentPadding | PaddingValues | Padding around the rows | PaddingValues(vertical = GlassPopupDefaults.ContentPaddingVertical) | No |
| onMeasured | ((Size) -> Unit)? | Called with the settled panel size in pixels | null | No |
| content | @Composable ColumnScope.() -> Unit | Menu rows | - | Yes |

### Defaults

Sizing and appearance options are documented in [GlassPopup shared defaults](./glasspopup#shared-defaults).

## Advanced Usage

### Anchor API {#anchor-api}

Use the `@Composable` `rememberGlassPopupAnchor()` factory and one anchor for each control/menu relationship. The modifiers report root-coordinate bounds:

```kotlin
import top.yukonga.miuix.kmp.glass.glassPopupAnchorRow
import top.yukonga.miuix.kmp.glass.glassPopupAnchorValue
```

| API | Type | Description | Default Value | Required |
| --- | --- | --- | --- | --- |
| rememberGlassPopupAnchor() | GlassPopupAnchor | Remembers the anchor used by a transforming or dropdown popup; no parameters | - | - |
| Modifier.glassPopupAnchor(anchor, cornerRadius, floating) | Modifier | Reports the outer control, hides it during the transform, and publishes shared GlassIconButton material | floating = false | anchor, cornerRadius |
| Modifier.glassPopupAnchorContent(anchor) | Modifier | Reports only the copied content bounds; omit it to copy the whole control | - | anchor |
| Modifier.glassPopupAnchorRow(anchor) | Modifier | Reports dropdown row bounds without hiding the row; the dropdown still takes explicit anchorBounds | - | anchor |
| Modifier.glassPopupAnchorValue(anchor) | Modifier | Reports and fades the dropdown value and chevron | - | anchor |
| anchor.secondaryBackProgress | Float | Read-only secondary Back progress, including cancellation recovery and retained exit | 0f | No |

Use the anchor modifier to associate a control with its popup:

```kotlin
val anchor = rememberGlassPopupAnchor()
GlassIconButton(
    onClick = { show = true },
    modifier = Modifier.glassPopupAnchor(anchor, cornerRadius = 20.dp, floating = true),
) {
    Text("⋯", modifier = Modifier.glassPopupAnchorContent(anchor))
}
```

When the explicit backdrop is null, the attached button backdrop is used before `visuals.containerColor`.

