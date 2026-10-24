---
title: GlassPopup
---

# GlassPopup

`GlassPopup` opens a menu from a measured control without transforming the control. It aligns the panel to the anchor's end edge, mirrors that placement in RTL, and opens upward when the panel does not fit below and there is more room above.

<div style="position: relative; height: 380px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../../compose/index.html?id=glassPopup" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

::: warning Background Sampling
When using background sampling, `backdrop` must record opaque content behind the component and exclude the glass surface that samples it. See [Glass Material](/guide/glass#background-sampling) for setup.
:::

## Import

```kotlin
import top.yukonga.miuix.kmp.glass.GlassPopup
import top.yukonga.miuix.kmp.glass.GlassPopupItem
```

## Basic Usage

This component is a `BoxScope` extension and does not require `MiuixPopupHost`. Place it last in a full-page `Box`, outside clipping containers; it handles outside taps itself. Anchor coordinates must use the same coordinate space as the popup's `Box`.

The `boundsInRoot()` example requires the popup `Box` to start at the coordinate root's origin. In an offset or inset host, convert the trigger bounds to that Box's local coordinates instead. The popup does not apply system-bar or IME insets itself; `safeMargin` is measured from the host's edges. Back dismissal requires a `LocalNavigationEventDispatcherOwner`; without one, outside-tap dismissal still works. Ordinary `GlassPopup` handles Back completion but does not preview its geometry during the gesture.

When closing, keep the component call in composition until the exit animation finishes. Do not wrap the call in `if (show)`.

Unlike the transforming, secondary, and dropdown variants, an ordinary primary `GlassPopup` leaves its rows interactive during the retained exit animation; `show = false` does not itself disable those rows.

Measure the trigger in the same coordinate root as the popup and update the state from each item:

```kotlin
var show by remember { mutableStateOf(false) }
var anchorBounds by remember { mutableStateOf(Rect.Zero) }

Box(Modifier.fillMaxSize()) {
    TextButton(
        text = "Actions",
        onClick = { show = true },
        modifier = Modifier.onGloballyPositioned {
            anchorBounds = it.boundsInRoot()
        },
    )
    GlassPopup(
        show = show,
        onDismissRequest = { show = false },
        anchorBounds = anchorBounds,
        backdrop = backdrop,
    ) {
        GlassPopupItem(text = "Share", icon = MiuixIcons.Glass.Share, onClick = { show = false })
        GlassPopupItem(text = "Favorite", selected = true, onClick = { show = false })
        GlassPopupItem(text = "More options", summary = "Detailed preferences", showArrow = true, onClick = { show = false })
        GlassPopupItem(text = "Unavailable", enabled = false, onClick = {})
    }
}
```

### Menu Items (GlassPopupItem)

`GlassPopupItem` builds each interactive row inside popup menus, supporting icons, subtitles, checkmarks, and submenu chevrons:

```kotlin
GlassPopupItem(
    text = "Sort by",
    summary = "Name",
    showArrow = true,
    onClick = { /* Open submenu */ },
)
}
```

## Properties

### GlassPopup Properties

| Property Name | Type | Description | Default Value | Required |
| --- | --- | --- | --- | --- |
| show | Boolean | Whether the menu is open; keep the call composed during exit | - | Yes |
| onDismissRequest | () -> Unit | Called for an outside tap or Back request | - | Yes |
| anchorBounds | Rect | Trigger bounds in this popup Box's coordinate space | - | Yes |
| backdrop | Backdrop? | Scene behind the panel; null uses the opaque fallback | - | Yes |
| modifier | Modifier | Modifier applied to the panel | Modifier | No |
| sizing | GlassPopupSizing | Width, height, and safe-margin limits | GlassPopupSizing() | No |
| visuals | GlassPopupVisuals | Panel appearance | GlassPopupDefaults.visuals() | No |
| cornerRadius | Dp | Settled panel corner radius | GlassPopupDefaults.CornerRadius (24.dp) | No |
| secondary | Boolean | Uses secondary geometry; use GlassSecondaryPopup to inherit a primary popup's material | false | No |
| contentPadding | PaddingValues | Padding around the rows | PaddingValues(vertical = GlassPopupDefaults.ContentPaddingVertical) | No |
| content | @Composable ColumnScope.() -> Unit | Menu rows | - | Yes |

### GlassPopupItem Properties {#glasspopupitem}

| Property Name | Type | Description | Default Value | Required |
| --- | --- | --- | --- | --- |
| text | String | Single-line row label | - | Yes |
| onClick | () -> Unit | Called when an enabled row is tapped | - | Yes |
| modifier | Modifier | Modifier applied to the row | Modifier | No |
| icon | ImageVector? | Optional leading icon | null | No |
| summary | String? | Optional single-line second label | null | No |
| enabled | Boolean | Whether the row responds to taps | true | No |
| selected | Boolean | Shows a trailing tick; uses selected label and leading-icon color when enabled | false | No |
| showArrow | Boolean | Shows a trailing submenu chevron | false | No |
| arrowRotation | () -> Float | Chevron rotation in degrees, read during the layer phase | { 0f } | No |
| contentColor | Color | Label and icon color | MiuixTheme.colorScheme.onSurfaceContainer | No |
| summaryColor | Color | Summary and arrow color | MiuixTheme.colorScheme.onSurfaceContainerVariant | No |
| selectedContentColor | Color | Enabled selected label and leading-icon color; selection tick color even when disabled | MiuixTheme.colorScheme.primary | No |
| pressedColor | Color | Fill behind a pressed row | GlassPopupDefaults.itemPressedColor() | No |
### GlassPopupSizing Properties

| Property Name | Type | Description | Default Value | Required |
| --- | --- | --- | --- | --- |
| minWidth | Dp | Narrowest panel width | GlassPopupDefaults.MinWidth | No |
| maxWidth | Dp | Widest panel width | GlassPopupDefaults.MaxWidth | No |
| maxHeight | Dp | Maximum panel height before rows scroll | GlassPopupDefaults.MaxHeight | No |
| safeMargin | Dp | Minimum distance from the page edge | GlassPopupDefaults.SafeMargin | No |

### GlassPopupVisuals Properties

All constructor fields are required. `GlassPopupDefaults.visuals()` supplies `GlassDefaults.Style`, an alpha of `1f`, `GlassShadows.Regular`, and the theme-selected `stroke()`, `material()` and `containerColor()` defaults.

| Property Name | Type | Description | Default Value | Required |
| --- | --- | --- | --- | --- |
| style | GlassStyle | Blur fallback when material has no blur radius; glass shading and the style's rim are off | - | Yes |
| alpha | Float | Material, bloom-stroke, and shadow opacity multiplier; GlassTransformPopup additionally applies it to the whole panel, including rows and fallback fill | - | Yes |
| stroke | GlassStroke? | Bloom stroke; null removes it | - | Yes |
| shadow | GlassShadow? | Panel shadow; null removes it | - | Yes |
| material | GlassMaterial? | Panel color treatment and optional blur radius; null removes color treatment, not background sampling or blur | - | Yes |
| containerColor | Color | Opaque fill used when backdrop is null | - | Yes |

With a non-null backdrop, blur uses `material.blurRadius` when present, otherwise `style.blur.small / GlassDefaults.SourceDensity`. Setting `material = null` retains that style-based blur, noise, bloom stroke, and shadow; it does not select `containerColor`. Only a null resolved backdrop selects the fallback fill, retaining stroke and shadow but not background sampling.

### GlassPopupDefaults Object {#shared-defaults}

`GlassPopupDefaults` provides shared popup sizing, row, surface, and stacking tokens.

#### Constants

| Constant Name | Type | Description | Value |
| --- | --- | --- | --- |
| `MinWidth` | `Dp` | Narrowest popup width | `200.dp` |
| `MaxWidth` | `Dp` | Widest popup width | `288.dp` |
| `MaxHeight` | `Dp` | Height before rows scroll | `416.dp` |
| `SafeMargin` | `Dp` | Page-edge margin | `12.dp` |
| `ContentPaddingVertical` | `Dp` | Vertical panel padding | `8.dp` |
| `CornerRadius` | `Dp` | Anchored popup radius | `24.dp` |
| `DropdownCornerRadius` | `Dp` | Dropdown popup radius | `24.dp` |
| `ItemMinHeight` | `Dp` | Minimum row height | `44.dp` |
| `ItemPaddingHorizontal` | `Dp` | Row horizontal padding | `20.dp` |
| `ItemPaddingVertical` | `Dp` | Row vertical padding | `10.dp` |
| `ItemIconSize` | `Dp` | Leading icon size | `24.dp` |
| `ItemIconGap` | `Dp` | Gap around row icons | `8.dp` |
| `ItemTickSize` | `Dp` | Selection tick size | `24.dp` |
| `ItemArrowSize` | `Dp` | Submenu arrow size | `16.dp` |
| `ItemPressInset` | `Dp` | Press-fill inset | `7.dp` |
| `ItemPressRadius` | `Dp` | Press-fill corner radius | `15.dp` |
| `DividerPaddingHorizontal` | `Dp` | Divider horizontal inset | `20.dp` |
| `DividerPaddingVertical` | `Dp` | Divider vertical space | `7.75.dp` |
| `DividerThickness` | `Dp` | Divider thickness | `0.5.dp` |

#### Methods

| Method Name | Parameters | Return Type | Description |
| --- | --- | --- | --- |
| `visuals` | `style: GlassStyle = GlassDefaults.Style`, `alpha: Float = 1f`, `stroke: GlassStroke? = stroke()`, `shadow: GlassShadow? = GlassShadows.Regular`, `material: GlassMaterial? = material()`, `containerColor: Color = containerColor()` | `GlassPopupVisuals` | Creates popup visuals from theme-aware defaults |
| `shape` | `cornerRadius: Dp = CornerRadius` | `GlassShape` | Creates the popup silhouette |
| `material` | — | `GlassMaterial` | Returns the theme-aware popup material |
| `stroke` | — | `GlassStroke` | Returns the theme-aware bloom stroke |
| `containerColor` | — | `Color` | White in light themes; `Color(0xFF2C2C2C)` in dark themes |
| `itemPressedColor` | — | `Color` | Black at `0.1f` alpha in light themes; white at `0.1f` in dark themes |
| `maskColor` | — | `Color` | White at `0.4f` alpha in light themes; black at `0.4f` in dark themes |

All these functions are `@Composable`. Theme selection treats `MiuixTheme.colorScheme.background.luminance() < 0.5f` as dark.

### GlassDropdownDefaults Object

#### Constants

| Constant Name | Type | Description | Value |
| --- | --- | --- | --- |
| `CornerRadius` | `Dp` | Settled dropdown radius | `24.dp` |
| `StartCornerRadius` | `Dp` | Radius at the start of dropdown opening | `4.dp` |

## Advanced Usage

Create shared sizing and visuals when several menus need the same limits or appearance. With no backdrop, stroke and shadow are still retained around the opaque fallback fill:

```kotlin
val sizing = GlassPopupSizing(maxWidth = 320.dp, maxHeight = 480.dp)
val visuals = GlassPopupDefaults.visuals(
    stroke = null,
    shadow = null,
    containerColor = MiuixTheme.colorScheme.background,
)

GlassPopup(
    show = show,
    onDismissRequest = { show = false },
    anchorBounds = anchorBounds,
    backdrop = backdrop,
    sizing = sizing,
    visuals = visuals,
) {
    GlassPopupItem(text = "Settings", onClick = { show = false })
}
```

For a submenu that inherits the expanded material and Back progress of a transforming primary menu, use [`GlassSecondaryPopup`](./glasssecondarypopup) instead of `secondary = true`.

