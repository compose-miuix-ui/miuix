---
title: GlassDialog
requiresScaffoldHost: true
prerequisites:
  - Must be used within `Scaffold` to provide `MiuixPopupHost`
  - Using outside `Scaffold` will cause popup content not to render
  - Multiple nested or side-by-side `Scaffold`s are supported without extra configuration
hostComponent: Scaffold
popupHost: MiuixPopupHost
---

# GlassDialog

`GlassDialog` is a glass dialog component in Miuix for confirmations and input. It provides the layout, animation and dismissal behavior of `OverlayDialog`, with matching `GlassDialogButton` actions.

<div style="position: relative; height: 440px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../../compose/index.html?id=glassDialog" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

::: danger Prerequisite
This component depends on `Scaffold` providing `MiuixPopupHost` to render popup content. It must be used within `Scaffold`, otherwise popup content will not render correctly.
:::

::: warning Background Sampling
When using background sampling, `backdrop` must record opaque content behind the component and exclude the glass surface that samples it. See [Glass Material](/guide/glass#background-sampling) for setup.
:::

## Import

```kotlin
import top.yukonga.miuix.kmp.glass.GlassDialog
import top.yukonga.miuix.kmp.glass.GlassDialogDefaults
import top.yukonga.miuix.kmp.glass.GlassDialogButton
import top.yukonga.miuix.kmp.glass.GlassDialogButtonDefaults
import top.yukonga.miuix.kmp.glass.GlassDialogButtonRole
import top.yukonga.miuix.kmp.glass.glassDialogSurface
import top.yukonga.miuix.kmp.blur.layerBackdrop
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
```

## Basic Usage

In this example, only the page `Box` is recorded; `Scaffold` hosts the overlay outside that recording.

```kotlin
val backdrop = rememberLayerBackdrop()
var showDialog by remember { mutableStateOf(false) }

Scaffold { padding ->
    Box(
        Modifier.fillMaxSize()
            .layerBackdrop(backdrop)
            .background(MiuixTheme.colorScheme.background)
            .padding(padding),
    ) {
        TextButton(text = "Show dialog", onClick = { showDialog = true })
    }

    GlassDialog(
        show = showDialog,
        onDismissRequest = { showDialog = false },
        backdrop = backdrop,
        title = "Discard changes?",
        summary = "Your unsaved changes will be lost.",
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            GlassDialogButton(
                onClick = { showDialog = false },
                modifier = Modifier.weight(1f),
            ) { Text("Cancel", style = MiuixTheme.textStyles.button) }
            GlassDialogButton(
                onClick = { showDialog = false },
                modifier = Modifier.weight(1f),
                role = GlassDialogButtonRole.Destructive,
            ) { Text("Discard", style = MiuixTheme.textStyles.button) }
        }
    }
}
```

When closing, keep the component call in composition until the exit animation finishes. Do not wrap the call in `if (show)`.

If the overlay can cover page bars, include those bars in the backdrop, but exclude the popup host and its scrim. A null backdrop or unavailable runtime shaders uses `containerColor`; provide an opaque fallback color.

## Properties

### GlassDialog Properties

| Property Name | Type | Description | Default Value | Required |
| --- | --- | --- | --- | --- |
| onDismissRequest | () -> Unit | Called on an outside tap or completed Back gesture; update `show` to dismiss | - | Yes |
| show | Boolean | Target visibility of the dialog | - | Yes |
| backdrop | Backdrop? | Clean page backdrop; null selects the opaque fallback | - | Yes |
| modifier | Modifier | Modifier applied to the dialog panel | Modifier | No |
| enableWindowDim | Boolean | Draw and sample the animated scrim | true | No |
| defaultWindowInsetsPadding | Boolean | Apply IME, navigation-bar and caption-bar padding; top safe inset is always applied | true | No |
| renderInRootScaffold | Boolean | Use the outermost Scaffold; false uses the current Scaffold | true | No |
| largeScreen | Boolean? | Centered scale/fade presentation; null detects the window size | null | No |
| title | String? | Dialog title | null | No |
| titleColor | Color | Title text color | DialogDefaults.titleColor() | No |
| summary | String? | Supporting text | null | No |
| summaryColor | Color | Summary text color | DialogDefaults.summaryColor() | No |
| cornerRadius | Dp? | Corner radius override; null uses the host's presentation defaults | null | No |
| maxWidth | Dp | Maximum dialog width | DialogDefaults.MaxWidth | No |
| outsideMargin | DpSize | External margins | DialogDefaults.outsideMargin | No |
| insideMargin | DpSize | Content padding | DialogDefaults.insideMargin | No |
| material | GlassMaterial | Blur and ordered color layers | GlassDialogDefaults.material() | No |
| stroke | GlassStroke? | Bloom stroke; null removes it | GlassDialogDefaults.stroke() | No |
| containerColor | Color | Opaque fallback fill | GlassDialogDefaults.containerColor() | No |
| dimColor | Color | Scrim color before animation progress is applied | GlassDialogDefaults.ScrimColor | No |
| onDismissFinished | (() -> Unit)? | Called after the hide animation completes, unless hiding is cancelled | null | No |
| content | @Composable () -> Unit | Dialog content, drawn sharply above the material | - | Yes |

### GlassDialogButton Properties {#glassdialogbutton}

`GlassDialogButton` draws an action over the dialog's material without a separate backdrop or blur. It provides `LocalContentColor`; use `MiuixTheme.textStyles.button` for labels. The caller controls the action-row widths. This button alone does not require `Scaffold`.

| Property Name | Type | Description | Default Value | Required |
| --- | --- | --- | --- | --- |
| onClick | () -> Unit | Called when the enabled button is clicked | - | Yes |
| modifier | Modifier | Modifier applied to the button | Modifier | No |
| enabled | Boolean | Whether the button accepts input | true | No |
| role | GlassDialogButtonRole | Action color treatment | GlassDialogButtonRole.Normal | No |
| cornerRadius | Dp | Radius of the fill, clip and press feedback | GlassDialogButtonDefaults.CornerRadius | No |
| minWidth | Dp | Minimum button width | GlassDialogButtonDefaults.MinWidth | No |
| minHeight | Dp | Minimum button height | GlassDialogButtonDefaults.MinHeight | No |
| colors | ButtonColors | Enabled and disabled fill/content colors | GlassDialogButtonDefaults.buttonColors(role) | No |
| pressedColor | Color | Foreground overlay while pressed | GlassDialogButtonDefaults.pressedColor() | No |
| insideMargin | PaddingValues | Content padding | GlassDialogButtonDefaults.InsideMargin | No |
| interactionSource | MutableInteractionSource? | Source for observing or driving interactions | null | No |
| content | @Composable RowScope.() -> Unit | Centered button content | - | Yes |

`GlassDialogButtonRole` has three values:

| Value | Description |
| --- | --- |
| Normal | Tonal fill with normal content color |
| Primary | Theme primary fill and matching content color |
| Destructive | Tonal fill with error-colored content |

Disabled buttons use the disabled colors and do not respond to clicks. Press feedback covers the fill and content together, without a ripple or scale animation.

### glassDialogSurface Properties {#glassdialogsurface}

`glassDialogSurface` returns `@Composable DialogSurfaceScope.() -> Modifier` for the `surface` slot of `OverlayDialog`. It uses the host's resolved corner radius, animated scrim and scale; it does not create a host or handle dismissal.

| Property Name | Type | Description | Default Value | Required |
| --- | --- | --- | --- | --- |
| backdrop | Backdrop? | Page backdrop excluding the dialog host and scrim; null uses the fallback | - | Yes |
| material | GlassMaterial | Blur and ordered color layers | GlassDialogDefaults.material() | No |
| stroke | GlassStroke? | Bloom stroke; null removes it | GlassDialogDefaults.stroke() | No |
| containerColor | Color | Opaque fallback fill | GlassDialogDefaults.containerColor() | No |

### GlassDialogDefaults Object

#### Constants

| Constant Name | Type | Description | Value |
| --- | --- | --- | --- |
| ScrimColor | Color | Scrim color before host animation | Color(0x33000000) |

#### Methods

| Method Name | Return Type | Description |
| --- | --- | --- |
| material() | GlassMaterial | Theme-aware `GlassMaterials.dialogGlass` material |
| stroke() | GlassStroke | Theme-aware `GlassStrokes.SmallLight` or `SmallDark` bloom stroke |
| containerColor() | Color | Theme background color for the opaque fallback |

### GlassDialogButtonDefaults Object

#### Constants

| Constant Name | Type | Description | Value |
| --- | --- | --- | --- |
| CornerRadius | Dp | Default button corner radius | 16.dp |
| MinWidth | Dp | Default minimum width | 0.dp |
| MinHeight | Dp | Default minimum height | 50.dp |
| InsideMargin | PaddingValues | Default content padding | PaddingValues(horizontal = 24.dp) |

#### Methods

| Method Name | Return Type | Description |
| --- | --- | --- |
| buttonColors(role: GlassDialogButtonRole = GlassDialogButtonRole.Normal) | ButtonColors | Theme-aware enabled and disabled colors for the given role |
| pressedColor() | Color | Theme `onBackground` with light-mode alpha `31/255` or dark-mode alpha `36/255` |

## Advanced Usage

### Applying Glass to an Existing Dialog

Use the surface factory when an existing `OverlayDialog` already owns the layout and visibility. With `showDialog` and the clean `backdrop` from the basic example:

```kotlin
OverlayDialog(
    show = showDialog,
    onDismissRequest = { showDialog = false },
    dimColor = GlassDialogDefaults.ScrimColor,
    surface = glassDialogSurface(backdrop = backdrop),
) {
    GlassDialogButton(
        onClick = { showDialog = false },
        role = GlassDialogButtonRole.Primary,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text("Confirm", style = MiuixTheme.textStyles.button)
    }
}
```

The surface applies the host's animated dimming to the backdrop itself and keeps sampling aligned during scaling. Do not record the scrim into the backdrop. `enableWindowDim = false` removes both the visible and sampled dimming. Content remains sharp; this surface does not add refraction or a separate drop shadow.

### Dismissal and Insets

Use `onDismissFinished` for work that must wait until the exit animation completes. It is not called if `show` returns to `true` before hiding finishes. Keep the dialog composed until then rather than wrapping it in `if (showDialog)`.

With `defaultWindowInsetsPadding = true`, the host applies IME, navigation-bar and caption-bar padding. Set it to `false` when providing those insets yourself; the top safe inset (the maximum of status-bar, caption-bar and display-cutout top insets) still applies. In nested Scaffolds, `renderInRootScaffold = false` selects the current host; that host must also remain outside the supplied backdrop recording.
