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

`GlassBottomSheet` is a glass sheet that slides up from the screen edge. It supports a header, dismissal gestures, predictive Back, and nested scrolling.

<div style="position: relative; height: 320px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../../compose/index.html?id=glassBottomSheet" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

::: danger Prerequisite
This component depends on `Scaffold` providing `MiuixPopupHost` to render popup content. It must be used within `Scaffold`, otherwise popup content will not render correctly.
:::

::: warning Background Sampling
When using background sampling, `backdrop` must record opaque content behind the component and exclude the glass surface that samples it. See [Glass Surfaces](/guide/glass#background-sampling) for setup.
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

The page `Box` is recorded by `backdrop`; `Scaffold` hosts the overlay outside that recording.

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
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
                .navigationBarsPadding()
                .padding(bottom = 16.dp),
        ) {
            repeat(24) { index -> Text("Item ${index + 1}") }
        }
    }
}
```

When closing, keep the component call in composition until the exit animation finishes. Do not wrap the call in `if (show)`.

Include any page bars that the overlay can cover in `backdrop`, but exclude the popup host and scrim. A null backdrop uses `containerColor`; provide an opaque fill color.

The caller supplies scrolling and extra body spacing. Place navigation-bar padding inside scrollable content when that spacing should scroll with it.

::: tip `innerPadding`
With `progressiveBlur = false` (default), `innerPadding` is `0.dp`. With `progressiveBlur = true`, the header overlays the body, so apply `innerPadding` to `content` or its scrollable container (e.g. `LazyColumn(contentPadding = innerPadding)`).
:::

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
| progressiveBlur | Boolean | Overlay the header with a progressive blur band while scrolling; when `true`, apply `innerPadding` to `content` | false | No |
| title | String? | Optional title; header shows if any of `title`, `startAction`, or `endAction` is non-null | null | No |
| startAction | (@Composable () -> Unit)? | Leading header action | null | No |
| endAction | (@Composable () -> Unit)? | Trailing header action | null | No |
| headerBottomContent | @Composable () -> Unit | Content pinned below the title inside the header | {} | No |
| cornerRadius | Dp | Top-corner radius; bottom corners remain square | BottomSheetDefaults.cornerRadius | No |
| sheetMaxWidth | Dp | Maximum sheet width | BottomSheetDefaults.maxWidth | No |
| outsideMargin | DpSize | Horizontal external margins use `width`; `height` is not applied | BottomSheetDefaults.outsideMargin | No |
| insideMargin | DpSize | Horizontal content padding uses `width`; bottom padding uses `height` | GlassBottomSheetDefaults.InsideMargin | No |
| dragHandleColor | Color | Handle color before press opacity is applied | BottomSheetDefaults.dragHandleColor() | No |
| material | GlassMaterial | Blur and ordered color layers | GlassBottomSheetDefaults.material() | No |
| stroke | EdgeStroke? | Edge stroke; null removes it | GlassBottomSheetDefaults.stroke() | No |
| containerColor | Color | Opaque fallback fill | GlassBottomSheetDefaults.containerColor() | No |
| dimColor | Color | Scrim color before animation progress is applied | GlassBottomSheetDefaults.ScrimColor | No |
| onDismissFinished | (() -> Unit)? | Called after the hide animation completes, unless hiding is cancelled | null | No |
| scrollOffset | (() -> Float)? | External scroll offset in pixels; `null` tracks nested scroll | null | No |
| content | @Composable (PaddingValues) -> Unit | Sheet body receiving top header padding (`0.dp` when `progressiveBlur` is `false`) | - | Yes |

### glassBottomSheetSurface Properties {#glassbottomsheetsurface}

`glassBottomSheetSurface` returns `@Composable BottomSheetSurfaceScope.() -> Modifier` for the `surface` slot of `OverlayBottomSheet`. The host owns sizing, insets, the handle, and gestures; the surface uses the host's corners and scrim.

| Property Name | Type | Description | Default Value | Required |
| --- | --- | --- | --- | --- |
| backdrop | Backdrop? | Page backdrop excluding the sheet host and scrim; null uses the fallback | - | Yes |
| material | GlassMaterial | Blur and ordered color layers | GlassBottomSheetDefaults.material() | No |
| stroke | EdgeStroke? | Edge stroke; null removes it | GlassBottomSheetDefaults.stroke() | No |
| containerColor | Color | Opaque fallback fill | GlassBottomSheetDefaults.containerColor() | No |

### GlassBottomSheetDefaults Object

#### Constants

| Constant Name | Type | Description | Value |
| --- | --- | --- | --- |
| ScrimColor | Color | Scrim color before host animation | GlassDialogDefaults.ScrimColor (`Color(0x33000000)`) |
| InsideMargin | DpSize | Default panel content padding | `DpSize(12.dp, 0.dp)` |
| ProgressiveBlurRadius | Dp | Maximum blur radius of the progressive header band | `32.dp` |
| ProgressiveBlurOverhang | Dp | Extension of the progressive header band below the title row | `24.dp` |
| ProgressiveBlurRampDistance | Dp | Scroll distance over which the progressive header band fades in | `32.dp` |
| ButtonSize | Dp | Diameter of header action buttons | `44.dp` |

#### Methods

| Method Name | Return Type | Description |
| --- | --- | --- |
| material() | GlassMaterial | Theme-aware `GlassMaterials.dialogGlass`, shared with GlassDialog |
| stroke() | EdgeStroke | Theme-aware `GlassEdgeStrokes.MiddleLight` or `MiddleDark` edge stroke |
| containerColor() | Color | Theme background color for the opaque fallback |
| buttonFill() | Color | Fallback fill for header action buttons |
| buttonMaterial() | GlassMaterial | Theme-aware `GlassMaterials.puredThinGlass` for header buttons |
| buttonStroke() | EdgeStroke | Small bloom stroke for header buttons |

## Advanced Usage

### Applying Glass to an Existing Sheet

Use the surface factory when an existing `OverlayBottomSheet` already manages the layout and visibility. With `showSheet` and the clean `backdrop` from the basic example:

```kotlin
OverlayBottomSheet(
    show = showSheet,
    onDismissRequest = { showSheet = false },
    title = "Details",
    dimColor = GlassBottomSheetDefaults.ScrimColor,
    motion = SheetMotion.Glass,
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

Do not record the scrim into `backdrop`. Set `enableWindowDim = false` to disable dimming.

### Dismissal and Insets

`allowDismiss = false` prevents outside taps, drags, and Back from dismissing the sheet; the caller can still update `show`. Keep `enableNestedScroll = true` when scrollable children should hand motion to the sheet.

Use `onDismissFinished` for work that waits until exit animation completes. It is not called if hiding is cancelled by setting `show` back to `true`. Do not wrap the sheet in `if (showSheet)`.

`defaultWindowInsetsPadding = true` adds IME padding; set it to `false` when providing it yourself. The sheet remains below the top safe inset, and body navigation-bar padding is caller-owned. In nested Scaffolds, `renderInRootScaffold = false` selects the current host, which must remain outside `backdrop`.
