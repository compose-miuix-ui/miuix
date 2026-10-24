---
title: GlassBottomSheet
requiresScaffoldHost: true
prerequisites:
  - Must be used within `Scaffold` to provide `MiuixPopupHost`
  - Using outside `Scaffold` will cause popup content not to render
  - Multiple nested or side-by-side `Scaffold`s are supported without extra configuration
hostComponent: Scaffold
popupHost: MiuixPopupHost
---

# GlassBottomSheet

`GlassBottomSheet` is a glass bottom sheet component in Miuix that slides up from the screen edge. It supports a header, swipe-to-dismiss, predictive Back and nested scrolling, while the material extends to the bottom edge.

<div style="position: relative; height: 480px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../../compose/index.html?id=glassBottomSheet" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

::: danger Prerequisite
This component depends on `Scaffold` providing `MiuixPopupHost` to render popup content. It must be used within `Scaffold`, otherwise popup content will not render correctly.
:::

::: warning Background Sampling
When using background sampling, `backdrop` must record opaque content behind the component and exclude the glass surface that samples it. See [Glass Material](/guide/glass#background-sampling) for setup.
:::

## Import

```kotlin
import top.yukonga.miuix.kmp.glass.GlassBottomSheet
import top.yukonga.miuix.kmp.glass.GlassBottomSheetDefaults
import top.yukonga.miuix.kmp.glass.glassBottomSheetSurface
import top.yukonga.miuix.kmp.blur.layerBackdrop
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
```

## Basic Usage

In this example, only the page `Box` is recorded; `Scaffold` hosts the overlay outside that recording.

```kotlin
val backdrop = rememberLayerBackdrop()
var showSheet by remember { mutableStateOf(false) }

Scaffold { padding ->
    Box(
        Modifier.fillMaxSize()
            .layerBackdrop(backdrop)
            .background(MiuixTheme.colorScheme.background)
            .padding(padding),
    ) {
        TextButton(text = "Show sheet", onClick = { showSheet = true })
    }

    GlassBottomSheet(
        show = showSheet,
        onDismissRequest = { showSheet = false },
        backdrop = backdrop,
        title = "Details",
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .navigationBarsPadding()
                .padding(bottom = 16.dp),
        ) {
            repeat(24) { index -> Text("Item ${index + 1}") }
        }
    }
}
```

When closing, keep the component call in composition until the exit animation finishes. Do not wrap the call in `if (show)`.

If the overlay can cover page bars, include those bars in the backdrop, but exclude the popup host and its scrim. A null backdrop or unavailable runtime shaders uses `containerColor`; provide an opaque fallback color.

The caller supplies scrolling and extra body spacing. Place navigation-bar padding **inside scrollable content** when the bottom spacing should scroll with it. The glass itself extends to the screen edge and also fills the extension exposed by upward overscroll.

## Properties

### GlassBottomSheet Properties

| Property Name | Type | Description | Default Value | Required |
| --- | --- | --- | --- | --- |
| onDismissRequest | () -> Unit | Called on an allowed outside tap, drag dismissal or completed Back gesture; update `show` to dismiss | - | Yes |
| show | Boolean | Target visibility of the sheet | - | Yes |
| backdrop | Backdrop? | Clean page backdrop; null selects the opaque fallback | - | Yes |
| modifier | Modifier | Modifier applied to the sheet panel | Modifier | No |
| enableWindowDim | Boolean | Draw and sample the animated scrim | true | No |
| defaultWindowInsetsPadding | Boolean | Apply IME padding; the top safe height limit is always applied, and body navigation-bar padding is caller-owned | true | No |
| allowDismiss | Boolean | Allow dismissal by outside tap, drag or Back | true | No |
| enableNestedScroll | Boolean | Hand motion between child scrolling and sheet dragging | true | No |
| renderInRootScaffold | Boolean | Use the outermost Scaffold; false uses the current Scaffold | true | No |
| title | String? | Header title | null | No |
| startAction | (@Composable () -> Unit)? | Leading header action | null | No |
| endAction | (@Composable () -> Unit)? | Trailing header action | null | No |
| cornerRadius | Dp | Top-corner radius; bottom corners remain square | BottomSheetDefaults.cornerRadius | No |
| sheetMaxWidth | Dp | Maximum sheet width | BottomSheetDefaults.maxWidth | No |
| outsideMargin | DpSize | Horizontal external margins use `width`; `height` is not applied | BottomSheetDefaults.outsideMargin | No |
| insideMargin | DpSize | Horizontal content padding uses `width`; bottom padding uses `height` | BottomSheetDefaults.insideMargin | No |
| dragHandleColor | Color | Handle color before press opacity is applied | BottomSheetDefaults.dragHandleColor() | No |
| material | GlassMaterial | Blur and ordered color layers | GlassBottomSheetDefaults.material() | No |
| stroke | GlassStroke? | Bloom stroke; omitted on the overscroll extension; null removes it | GlassBottomSheetDefaults.stroke() | No |
| containerColor | Color | Opaque fallback fill | GlassBottomSheetDefaults.containerColor() | No |
| dimColor | Color | Scrim color before animation progress is applied | GlassBottomSheetDefaults.ScrimColor | No |
| onDismissFinished | (() -> Unit)? | Called after the hide animation completes, unless hiding is cancelled | null | No |
| content | @Composable () -> Unit | Sheet body | - | Yes |

### glassBottomSheetSurface Properties {#glassbottomsheetsurface}

`glassBottomSheetSurface` returns `@Composable BottomSheetSurfaceScope.() -> Modifier` for the `surface` slot of `OverlayBottomSheet`. The host still owns sizing, insets, the handle and gestures. The surface uses rounded top corners, square bottom corners and the host's animated scrim. Its overscroll extension uses the same material without a bloom stroke.

| Property Name | Type | Description | Default Value | Required |
| --- | --- | --- | --- | --- |
| backdrop | Backdrop? | Page backdrop excluding the sheet host and scrim; null uses the fallback | - | Yes |
| material | GlassMaterial | Blur and ordered color layers | GlassBottomSheetDefaults.material() | No |
| stroke | GlassStroke? | Bloom stroke, omitted on the overscroll extension; null removes it | GlassBottomSheetDefaults.stroke() | No |
| containerColor | Color | Opaque fallback fill | GlassBottomSheetDefaults.containerColor() | No |

### GlassBottomSheetDefaults Object

#### Constants

| Constant Name | Type | Description | Value |
| --- | --- | --- | --- |
| ScrimColor | Color | Scrim color before host animation | GlassDialogDefaults.ScrimColor (`Color(0x33000000)`) |

#### Methods

| Method Name | Return Type | Description |
| --- | --- | --- |
| material() | GlassMaterial | Theme-aware `GlassMaterials.dialogGlass`, shared with GlassDialog |
| stroke() | GlassStroke | Theme-aware `GlassStrokes.MiddleLight` or `MiddleDark` bloom stroke |
| containerColor() | Color | Theme background color for the opaque fallback |

## Advanced Usage

### Applying Glass to an Existing Sheet

Use the surface factory when an existing `OverlayBottomSheet` already manages the layout and visibility. With `showSheet` and the clean `backdrop` from the basic example:

```kotlin
OverlayBottomSheet(
    show = showSheet,
    onDismissRequest = { showSheet = false },
    title = "Details",
    dimColor = GlassBottomSheetDefaults.ScrimColor,
    surface = glassBottomSheetSurface(backdrop = backdrop),
) {
    Column(
        Modifier.verticalScroll(rememberScrollState())
            .navigationBarsPadding()
            .padding(bottom = 16.dp),
    ) {
        repeat(24) { index -> Text("Item ${index + 1}") }
    }
}
```

The surface samples at the sheet's translated screen coordinates and applies the animated dimming itself. Do not record the scrim into the backdrop. `enableWindowDim = false` removes both visible and sampled dimming. Content remains sharp; this surface does not add refraction or a separate drop shadow.

### Dismissal and Insets

`allowDismiss = false` prevents outside taps, drags and Back gestures from dismissing the sheet; the caller can still update `show` directly. Leave `enableNestedScroll = true` when scrollable children should hand motion to sheet dragging.

Use `onDismissFinished` for work that must wait until the exit animation completes. It is not called if hiding is cancelled by setting `show` back to `true`. Do not wrap the sheet in `if (showSheet)`.

`defaultWindowInsetsPadding = true` adds IME padding; set it to `false` when providing that padding yourself. The sheet's height is still limited below the top safe inset (the maximum of status-bar, caption-bar and display-cutout top insets). Navigation-bar padding for the body remains the caller's responsibility. In nested Scaffolds, `renderInRootScaffold = false` selects the current host, which must also stay outside the supplied backdrop recording.
