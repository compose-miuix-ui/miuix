# Glass Material

`miuix-glass` is a standalone module that brings the glass material to Compose Multiplatform, together with the components built on it: top app bar, navigation bar, tab row, popup, dialog and bottom sheet. It supports Android, Desktop (JVM), iOS, macOS, and Web (WasmJs/Js).

::: warning
On Android, `miuix-glass` requires `minSdk` 33 (Android 13) or higher. The material is drawn with a runtime shader, so the floor is the same one `miuix-blur` has. If your app ships a lower `minSdk`, gate glass code paths with [the capability checks](/guide/blur#runtime-capability-checks).
:::

## Setup

`miuix-glass` depends on `miuix-blur` and `miuix-ui`, and brings both in transitively. The Glass icon set the components are designed around lives in a separate artifact:

```kotlin
kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation("top.yukonga.miuix.kmp:miuix-glass:<version>")
            // Optional: the Glass icon set
            implementation("top.yukonga.miuix.kmp:miuix-glass-icons:<version>")
        }
    }
}
```

For Android-only projects:

```kotlin
dependencies {
    implementation("top.yukonga.miuix.kmp:miuix-glass-android:<version>")
    implementation("top.yukonga.miuix.kmp:miuix-glass-icons-android:<version>")
}
```

## Platform Support

| Platform      | Minimum Requirement |
| ------------- | ------------------- |
| Android       | API 33 (Android 13) |
| Desktop (JVM) | Supported           |
| iOS / macOS   | Supported           |
| WasmJs / Js   | Supported           |

Every glass surface samples what stands behind it through a runtime shader. On a device without runtime shader support the components fall back to a flat fill instead of a sampled backdrop.

## Backdrop

Glass samples the content behind it, so the page has to record itself first. That is the same `Backdrop` mechanism [Blur Effects](/guide/blur) uses:

```kotlin
import top.yukonga.miuix.kmp.blur.layerBackdrop
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop

val backdrop = rememberLayerBackdrop()

Box(
    modifier = Modifier
        .fillMaxSize()
        .layerBackdrop(backdrop)
        .background(MiuixTheme.colorScheme.surface),
) {
    // Page content
}
```

`layerBackdrop` captures only what the composable it is attached to draws, so give that composable an opaque background — see [Blur Effects](/guide/blur#basic-usage) for why. Components that take a nullable `Backdrop` use their flat resting fill when it is `null`.

## Material

`GlassStyle` describes one material and `GlassStyles` holds the stock token table. `GlassDefaults.Style` is the default and `GlassDefaults.style(isDark)` picks between the light and dark defaults; the `Common*`, `Bionic*` and `Tint*` families are all reachable when you want a specific one.

Three companions complete a surface:

| Type          | Role                                                           |
| ------------- | -------------------------------------------------------------- |
| `GlassShape`  | The silhouette.                                                |
| `GlassStroke` | The rim stroke.                                                |
| `GlassShadow` | The drop shadow. `GlassShadows.Regular`, `Float`, `ExtraHigh`. |

`GlassMaterial` is the treatment the floating bars use: a blur radius plus up to three colour layers, each on its own blend mode. `GlassMaterials.puredThinGlass(isDark)` is the token every floating bar uses, `GlassMaterials.popupViewGlass(isDark)` covers a popup, and `GlassMaterials.actionBarMask(isDark)` covers a bar's mask.

`GlassMaterials.dialogGlass(isDark)` selects the separate dialog material described below.

`GlassMotion` holds the springs the components animate with, if you need to match them.

## Surfaces

Two primitives build every glass surface in the module:

```kotlin
// A panel with its shadow, over a backdrop.
Modifier.glassPanel(
    backdrop = backdrop,
    shape = GlassShape(24.dp),
    style = GlassDefaults.style(isDark),
    material = GlassMaterials.puredThinGlass(isDark),
    stroke = GlassStrokes.forTheme(isDark),
    shadow = GlassShadows.Regular,
    shading = false,
)
```

```kotlin
// The same thing in the form the library's own components ask for their surface in.
val surface = glassSurface(backdrop, GlassPopupDefaults.visuals())
```

`shading = true` refracts and shades the backdrop, which is what a control wants; `false` leaves a flat material, which is what a bar or a menu wants. `glassSurface` is what makes the existing overlay components glass — hand it to their `surface` slot and there is no second implementation:

```kotlin
OverlayIconCascadingDropdownMenu(
    entries = entries,
    surface = glassSurface(backdrop, GlassPopupDefaults.visuals()),
) {
    Icon(imageVector = MiuixIcons.Glass.More, contentDescription = null)
}
```

## Components

### GlassTopAppBar

`GlassTopAppBar` is the glass top bar. Put the buttons in `GlassIconButton` so they inherit the bar's material:

```kotlin
GlassTopAppBar(
    title = "Glass",
    backdrop = backdrop,
    scrollBehavior = scrollBehavior,
    navigationIcon = {
        GlassIconButton(onClick = { navigator.pop() }) {
            Icon(
                imageVector = MiuixIcons.Glass.ChevronBackward,
                contentDescription = "Back",
                modifier = Modifier.size(24.dp),
            )
        }
    },
)
```

The overload with `isContentScrolled` takes the page's own "content has left the top" signal instead of deriving it from `scrollBehavior`:

```kotlin
GlassTopAppBar(
    title = "Glass",
    isContentScrolled = listState.canScrollBackward,
    backdrop = backdrop,
    scrollBehavior = scrollBehavior,
)
```

`contentModifier` transforms the foreground without moving the band, which is what a search transition needs, and `titleAlpha` multiplies the collapse opacity of both title containers.

### GlassNavigationBar

```kotlin
GlassNavigationBar(
    items = items,
    selectedIndex = selectedIndex,
    onSelect = { selectedIndex = it },
    backdrop = backdrop,
    modifier = Modifier.padding(horizontal = 24.dp),
)
```

`items` is a `List<GlassNavigationItem>` — an icon plus a caption each. `height` is a minimum rather than a fixed size, and outer placement stays with the caller: the component does not add system bar insets itself.

### GlassTabRow

```kotlin
GlassTabRow(
    tabs = listOf("Home", "Widget", "Theme"),
    selectedIndex = selectedIndex,
    onSelect = { selectedIndex = it },
    backdrop = backdrop,
)
```

Two colour sets ship with it: `GlassTabRowDefaults.primaryColors()` for the default look, and `GlassTabRowDefaults.neutralColors()` for a page whose content already owns the accent (pair it with `GlassTabRowDefaults.NeutralHeight`).

`surfaceAlpha` is how far the material has come up. Drive it from the page-overlap state rather than the title's expand state. `GlassSegmentedTabRow` is the joined variant.

### Dialog Material

`GlassDialog` packages the dialog-specific blur/blend material, scrim and `OverlayDialog` host. It handles titles, sizing, insets, animation and dismissal. Use `GlassDialogButton` for actions that tint the shared material. Import these components from `top.yukonga.miuix.kmp.glass`.

Keep the recording below the popup host. For example, the outer `Scaffold` hosts the dialog and the inner one records the page, including its bars:

```kotlin
val dialogBackdrop = rememberLayerBackdrop()
var showDialog by remember { mutableStateOf(false) }

Scaffold(contentWindowInsets = WindowInsets(0)) {
    Scaffold(
        modifier = Modifier.fillMaxSize().layerBackdrop(dialogBackdrop),
        topBar = { /* Page top bar */ },
        bottomBar = { /* Page bottom bar */ },
    ) { padding ->
        TextButton(
            text = "Show dialog",
            onClick = { showDialog = true },
            modifier = Modifier.padding(padding),
        )
        GlassDialog(
            show = showDialog,
            onDismissRequest = { showDialog = false },
            backdrop = dialogBackdrop,
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                GlassDialogButton(
                    onClick = { showDialog = false },
                    modifier = Modifier.weight(1f),
                ) {
                    Text("Cancel", style = MiuixTheme.textStyles.button)
                }
                GlassDialogButton(
                    onClick = { showDialog = false },
                    modifier = Modifier.weight(1f),
                    role = GlassDialogButtonRole.Primary,
                ) {
                    Text("OK", style = MiuixTheme.textStyles.button)
                }
            }
        }
    }
}
```

`renderInRootScaffold` must remain true here so the dialog is outside the inner recording. If an existing outer host already provides this separation, reuse it.

| GlassDialogDefaults | Value / behavior |
| --- | --- |
| ScrimColor | `#33000000` (20% black) |
| material() | Theme-selected `DialogGlassLight` / `DialogGlassDark`, 60dp blur input |
| stroke() | Initial bloom approximation using the small light/dark stroke |
| containerColor() | Theme background, used for the opaque fallback |

`glassDialogSurface(backdrop, material, stroke, containerColor)` accepts overrides; a null backdrop or unavailable runtime shader uses the fallback. The radius comes from the host. This surface has no refraction or additional drop shadow.

The material applies these layers in order:

| Layer | Light | Dark |
| --- | --- | --- |
| 1 | `#05000000 / PlusDarker` | `#1A000000 / PlusDarker` |
| 2 | `#99FFFFFF / SoftLight` | `#996C6C6C / Luminosity` |
| 3 | `#99FFFFFF / HardLight` | `#99060606 / SoftLight` |

This implementation samples the clean scene, applies the host's current scrim locally, then blurs and blends. Do not capture the scrim in the supplied backdrop. Native scrim sampling order, blur-kernel conversion and bloom still need visual calibration; this is an initial reconstruction, not a pixel-match guarantee.

#### Dialog Actions

`GlassDialogButton` draws over the parent dialog material and takes no backdrop. The normal and destructive variants retain the blurred scene through a translucent fill. Primary actions use the theme's primary fill.

| `GlassDialogButtonRole` | Fill | Content |
| --- | --- | --- |
| `Normal` (default) | Theme `onBackground` at 15/255 light, 36/255 dark | `onBackground` at 90% light, 80% dark |
| `Primary` | `primary` | `onPrimary` |
| `Destructive` | Same as `Normal` | `error` |

With the default neutral theme, the normal fill is `#0F000000` / `#24FFFFFF`. While pressed, a foreground overlay covers both the fill and content: `#1F000000` / `#24FFFFFF`. It appears and clears immediately on press, release or cancellation. There is no additional ripple, blur or scale effect.

| Parameter | Default / behavior |
| --- | --- |
| `onClick` | Required action callback |
| `modifier` | `Modifier`; the caller controls width and placement |
| `enabled` | `true`; disabled actions have no press overlay or click callback |
| `role` | `GlassDialogButtonRole.Normal` |
| `cornerRadius` | `GlassDialogButtonDefaults.CornerRadius` = 16dp |
| `minWidth` / `minHeight` | `GlassDialogButtonDefaults.MinWidth` / `MinHeight` = 0dp / 50dp |
| `colors` | `GlassDialogButtonDefaults.buttonColors(role)`, returning `ButtonColors` |
| `pressedColor` | `GlassDialogButtonDefaults.pressedColor()`, selected from the current theme |
| `insideMargin` | `GlassDialogButtonDefaults.InsideMargin` = 24dp horizontal |
| `interactionSource` | Optional `MutableInteractionSource` |
| `content` | Centered `RowScope` slot; text uses `MiuixTheme.textStyles.button` |

Disabled normal and destructive actions retain the tonal fill; their content opacity is 38% and 30% respectively. A disabled primary action uses a 30% primary fill, with `onPrimary` at full opacity in light mode and 77/255 in dark mode. Primary/error RGB values follow the Compose theme. Padding remains 24dp horizontally at all font scales; native Flutter interpolates its horizontal padding from 24 to 12 to 6 as text scale grows from 1 to 3.

### Bottom Sheet Material

The launcher's native bottom sheet uses the same light/dark three-layer color lists and 60dp blur input as its dialog. `GlassBottomSheet` packages this material with the attached `OverlayBottomSheet` host, rounded top corners and the host's content layout:

```kotlin
val sheetBackdrop = rememberLayerBackdrop()
var showSheet by remember { mutableStateOf(false) }

Scaffold(contentWindowInsets = WindowInsets(0)) {
    Scaffold(
        modifier = Modifier.fillMaxSize().layerBackdrop(sheetBackdrop),
        topBar = { /* Page top bar */ },
        bottomBar = { /* Page bottom bar */ },
    ) { padding ->
        TextButton(
            text = "Show sheet",
            onClick = { showSheet = true },
            modifier = Modifier.padding(padding),
        )
        GlassBottomSheet(
            show = showSheet,
            title = "Glass Bottom Sheet",
            onDismissRequest = { showSheet = false },
            backdrop = sheetBackdrop,
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .navigationBarsPadding()
                    .padding(bottom = 16.dp),
            ) {
                repeat(24) { index -> Text("Sheet item ${index + 1}") }
            }
        }
    }
}
```

Keep `renderInRootScaffold = true` so the outer scaffold hosts the sheet outside the recording. An existing clean page backdrop may be shared with the dialog. Do not capture either overlay or its scrim.

| GlassBottomSheetDefaults | Value / behavior |
| --- | --- |
| `ScrimColor` | `#33000000` (20% black), animated by the host |
| `material()` | Theme-selected `DialogGlassLight` / `DialogGlassDark`; native sheets share these exact blur/color inputs |
| `stroke()` | Initial bloom approximation using `MiddleLight` / `MiddleDark` |
| `containerColor()` | Theme background for the opaque fallback |

The wrapper preserves `OverlayBottomSheet`'s content layout and default padding (24dp horizontal, 0dp bottom). The host handles IME insets. Apply navigation-bar padding and extra bottom spacing inside your scroll container, as shown above, so they scroll with the content while the material extends to the screen edge. Use `insideMargin` when you want fixed panel padding instead. Put long content in `verticalScroll` or a `LazyColumn`.

`glassBottomSheetSurface(backdrop, material, stroke, containerColor)` accepts overrides. A null backdrop or unavailable runtime shaders use the fallback. The host's `BottomSheetSurfaceScope` supplies the top radius and the current scrim color as drawing state. The material also fills the exposed bottom edge during upward overscroll, with square corners and no edge stroke. Slide/drag translations keep sampling the scene at the sheet's current screen position. Content, the handle, insets, nested scrolling and dismissal remain host-owned.

This helper targets the attached `OverlayBottomSheet`; it does not implement the native floating-sheet presentation or detents. Native sheets have a different bloom configuration from dialogs. Bloom mapping, blur-kernel conversion and scrim sampling order still need visual calibration. The implementation uses no refraction or extra drop shadow.

### GlassDialog

Use `show` to control the packaged dialog. Both overlay components share these parameters:

| Parameter | Default / behavior |
| --- | --- |
| `onDismissRequest` | Required; update `show` when dismissal is requested |
| `show` | Required visibility state |
| `backdrop` | Required clean page recording, excluding overlays and scrims; `null` selects the opaque fallback |
| `modifier` | `Modifier`, applied to the panel |
| `enableWindowDim` | `true`; draw the scrim and include it in local material sampling |
| `defaultWindowInsetsPadding` | `true`; the host's default window insets, including IME padding for sheets |
| `renderInRootScaffold` | `true`; use the outermost Scaffold host |
| `title` | `null`; optional title |
| `outsideMargin` | Respective `DialogDefaults.outsideMargin` / `BottomSheetDefaults.outsideMargin` |
| `insideMargin` | `DialogDefaults.insideMargin` / `BottomSheetDefaults.insideMargin` |
| `material` | Respective `GlassDialogDefaults.material()` / `GlassBottomSheetDefaults.material()` |
| `stroke` | Respective Defaults `stroke()`; `null` disables the stroke |
| `containerColor` | Respective Defaults `containerColor()`, for the opaque fallback |
| `dimColor` | Respective Defaults `ScrimColor`, `#33000000` |
| `onDismissFinished` | `null`; called after the hide animation completes |
| `content` | Required sharp content slot above the material |

Dialog-specific parameters:

| Parameter | Default / behavior |
| --- | --- |
| `largeScreen` | `null`; automatically select centered scale or small-screen slide presentation, with an explicit override available |
| `titleColor` | `DialogDefaults.titleColor()` |
| `summary` / `summaryColor` | `null` / `DialogDefaults.summaryColor()` |
| `cornerRadius` | `null`; resolved by the host for its presentation |
| `maxWidth` | `DialogDefaults.MaxWidth` |

The original `GlassDialog(visible = …)` and `GlassOverlayDefaults` have been removed. Migrate to `GlassDialog(show = …)` and `GlassDialogDefaults`. Replace appearance options such as `style`, `shape` and `scrimColor` / `scrimAlpha` with `material`, `cornerRadius` and `dimColor`, and use the Scaffold host and recording placement shown above.

### GlassBottomSheet

In addition to the shared parameters above:

| Parameter | Default / behavior |
| --- | --- |
| `allowDismiss` | `true`; allow outside taps, dragging and back dismissal |
| `enableNestedScroll` | `true`; hand motion between scrolling content and the sheet |
| `startAction` / `endAction` | `null`; content slots on either side of the header |
| `cornerRadius` | `BottomSheetDefaults.cornerRadius`, applied only to the top corners |
| `sheetMaxWidth` | `BottomSheetDefaults.maxWidth` |
| `dragHandleColor` | `BottomSheetDefaults.dragHandleColor()` |

Use `glassDialogSurface` and `glassBottomSheetSurface` when composing the Overlay hosts yourself.

### GlassDropdownPreference

[`GlassDropdownPreference`](/components/glassdropdownpreference) packages a standard preference row with a glass dropdown. It owns expansion, selection callbacks, anchor measurement and dismissal, so callers do not need to manage a separate `GlassDropdownPopup`.

The row shares `BasicComponent`'s 17sp Medium title, padding and dropdown arrow. Popup options use 16sp Medium, matching the standard dropdown. Use `visuals` to override the popup material and `sizing` to adjust its limits. The popup is hosted in the root `Scaffold` by default; keep that host outside the supplied backdrop recording.

### Popups

`GlassTransformPopup` grows a panel out of a button. Hold a `GlassPopupAnchor`, report the button with `Modifier.glassPopupAnchor` and its contents with `Modifier.glassPopupAnchorContent`, and hand the anchor to the popup:

```kotlin
val anchor = rememberGlassPopupAnchor()

Box {
    GlassIconButton(
        onClick = { showMenu = true },
        modifier = Modifier.glassPopupAnchor(anchor, cornerRadius = 20.dp),
    ) {
        Icon(
            imageVector = MiuixIcons.Glass.More,
            contentDescription = "More",
            modifier = Modifier.glassPopupAnchorContent(anchor),
        )
    }

    GlassTransformPopup(
        show = showMenu,
        onDismissRequest = { showMenu = false },
        anchor = anchor,
        backdrop = backdrop,
        anchorContent = {
            Icon(imageVector = MiuixIcons.Glass.More, contentDescription = null)
        },
    ) {
        GlassPopupItem(text = "Rename", onClick = { showMenu = false })
        GlassPopupItem(text = "Delete", onClick = { showMenu = false })
    }
}
```

The anchor supplies the button's collapsed appearance. As the existing geometry animation expands the panel, its background blends into the popup's own `visuals`; closing reverses that blend. The expanded menu uses an independent 60dp blur material with its own light and dark colour layers.

For a top-bar menu, pass a `backdrop` that records the page including the top bar, and render the popup outside that recording. The bar can continue to sample only the body. An explicit popup backdrop takes priority; `null` falls back to the button's backdrop.

`GlassPopup`, `GlassSecondaryPopup` and `GlassDropdownPopup` take an `anchorBounds: Rect` instead, so the caller reports the trigger itself:

```kotlin
var anchorBounds by remember { mutableStateOf(Rect.Zero) }

GlassPopupItem(
    text = "Sort by",
    onClick = { submenu = true },
    modifier = Modifier.onGloballyPositioned { anchorBounds = it.boundsInRoot() },
)

GlassSecondaryPopup(
    show = submenu,
    onDismissRequest = { submenu = false },
    anchorBounds = anchorBounds,
    backdrop = backdrop,
) {
    GlassPopupItem(text = "Name", onClick = { submenu = false })
}
```

`GlassSecondaryPopup` opens beside a menu that is already open; keep it composed while it collapses, and pass the primary popup's anchor as `materialAnchor` so the two share the expanded menu appearance. Its backdrop should include the primary popup and exclude the secondary popup itself.

`GlassDropdownPopup` keeps its panel composed during the exit animation, but stops pointer input and accessibility actions on its rows as soon as `show` becomes false.

## Complete Example

```kotlin
@Composable
fun GlassPage() {
    if (!isRuntimeShaderSupported()) return

    val pageBackdrop = rememberLayerBackdrop()
    val overlayBackdrop = rememberLayerBackdrop()
    val scrollBehavior = MiuixScrollBehavior()
    var dialog by remember { mutableStateOf(false) }

    Scaffold(contentWindowInsets = WindowInsets(0)) {
        Scaffold(
            modifier = Modifier.fillMaxSize().layerBackdrop(overlayBackdrop),
            topBar = {
                GlassTopAppBar(
                    title = "Glass",
                    backdrop = pageBackdrop,
                    scrollBehavior = scrollBehavior,
                )
            },
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .layerBackdrop(pageBackdrop)
                    .background(MiuixTheme.colorScheme.surface),
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = innerPadding,
                ) {
                    items(40) { index ->
                        BasicComponent(
                            title = "Item $index",
                            onClick = { dialog = true },
                        )
                    }
                }
            }
            GlassDialog(
                show = dialog,
                onDismissRequest = { dialog = false },
                backdrop = overlayBackdrop,
                title = "Glass Dialog",
            ) {
                GlassDialogButton(
                    onClick = { dialog = false },
                    role = GlassDialogButtonRole.Primary,
                ) {
                    Text("OK", style = MiuixTheme.textStyles.button)
                }
            }
        }
    }
}
```

The top bar samples only the page content. The dialog samples the inner Scaffold, including the bar, while the outer Scaffold hosts its overlay outside that recording.

## See Also

- [Blur Effects](/guide/blur) — the backdrop mechanism glass samples through.
- [Icon System](/guide/icons) — the Glass icon set in `miuix-glass-icons`.
- [TopAppBar](/components/topappbar), [NavigationBar](/components/navigationbar), [TabRow](/components/tabrow) — the non-glass counterparts.
