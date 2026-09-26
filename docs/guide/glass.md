# Glass Material

`miuix-glass` is a standalone module that brings the glass material to Compose Multiplatform, together with the components built on it: top app bar, navigation bar, tab row, popup and dialog. It supports Android, Desktop (JVM), iOS, macOS, and Web (WasmJs/Js).

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

### GlassDialog

```kotlin
GlassDialog(
    visible = showDialog,
    onDismissRequest = { showDialog = false },
    backdrop = backdrop,
) {
    Text(text = "Glass Dialog", style = MiuixTheme.textStyles.title4)
    TextButton(text = "OK", onClick = { showDialog = false })
}
```

The scrim and the predictive back gesture are handled inside.

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

Sharing the anchor is what keeps the button and the panel that grows out of it consistent.

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

`GlassSecondaryPopup` opens beside a menu that is already open; keep it composed while it collapses, and pass the primary popup's `materialAnchor` so the two share their material.

## Complete Example

```kotlin
@Composable
fun GlassPage() {
    if (!isRuntimeShaderSupported()) return

    val backdrop = rememberLayerBackdrop()
    val scrollBehavior = MiuixScrollBehavior()
    var tab by remember { mutableIntStateOf(0) }
    var dialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            GlassTopAppBar(
                title = "Glass",
                backdrop = backdrop,
                scrollBehavior = scrollBehavior,
                bottomContent = {
                    GlassTabRow(
                        tabs = listOf("Home", "Widget", "Theme"),
                        selectedIndex = tab,
                        onSelect = { tab = it },
                        backdrop = backdrop,
                    )
                },
            )
        },
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize()) {
            // The page records itself, and everything glass samples that recording.
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .layerBackdrop(backdrop)
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
                visible = dialog,
                onDismissRequest = { dialog = false },
                backdrop = backdrop,
            ) {
                Text(text = "Glass Dialog", style = MiuixTheme.textStyles.title4)
                TextButton(text = "OK", onClick = { dialog = false })
            }
        }
    }
}
```

## See Also

- [Blur Effects](/guide/blur) — the backdrop mechanism glass samples through.
- [Icon System](/guide/icons) — the Glass icon set in `miuix-glass-icons`.
- [TopAppBar](/components/topappbar), [NavigationBar](/components/navigationbar), [TabRow](/components/tabrow) — the non-glass counterparts.
