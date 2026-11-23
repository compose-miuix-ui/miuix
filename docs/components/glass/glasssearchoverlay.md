# GlassSearchOverlay

`GlassSearchOverlay` is a full-screen glass search overlay with a progressive blur header band, glassmorphic search input capsule, animated cancel button, and scrollable result area.

When the input contains text, a translucent circular clear button appears. Its cutout reveals the input background.

<div style="position: relative; height: 440px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../../compose/index.html?id=glassSearchOverlay" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

## Import

```kotlin
import top.yukonga.miuix.kmp.glass.GlassSearchOverlay
import top.yukonga.miuix.kmp.glass.GlassSearchOverlayDefaults
import top.yukonga.miuix.kmp.glass.glassSearchBarTransition
import top.yukonga.miuix.kmp.glass.rememberGlassSearchBarAlpha
```

## Basic Usage

Pair `GlassSearchOverlay` with `rememberGlassSearchBarAlpha` and `Modifier.glassSearchBarTransition` on the page's top bar so the bar fades, scales, and blurs smoothly when entering search mode.

```kotlin
var searchExpanded by remember { mutableStateOf(false) }
val searchBarAlpha = rememberGlassSearchBarAlpha(searchExpanded)
val listState = rememberLazyListState()

Box(Modifier.fillMaxSize()) {
    Scaffold(
        topBar = {
            GlassTopAppBar(
                title = "Search",
                contentModifier = Modifier.glassSearchBarTransition(searchExpanded) { 1f },
                actions = {
                    GlassIconButton(onClick = { searchExpanded = true }) {
                        Icon(MiuixIcons.Basic.Search, contentDescription = "Search")
                    }
                },
            )
        },
    ) {
        // Page content
    }

    GlassSearchOverlay(
        expanded = searchExpanded,
        onDismissRequest = { searchExpanded = false },
        label = "Search items",
        scrollOffset = {
            if (listState.firstVisibleItemIndex > 0) {
                Float.MAX_VALUE
            } else {
                listState.firstVisibleItemScrollOffset.toFloat()
            }
        },
    ) { query ->
        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(top = 52.dp),
        ) {
            // Filtered results
        }
    }
}
```

## API Reference

### GlassSearchOverlay

| Property | Type | Default | Description |
| :--- | :--- | :--- | :--- |
| expanded | Boolean | - | Whether the search overlay is expanded |
| onDismissRequest | () -> Unit | - | Callback invoked when the user cancels or presses Back/Escape |
| scrollMaterialThreshold | Dp | `Dp.Unspecified` when omitted | Pass the initial gap from the input to the first result. The input and cancel button fade between a solid fill and glass over 450 ms as results cross that gap |
| modifier | Modifier | Modifier | Modifier applied to the root overlay container |
| horizontalInsetsModifier | Modifier | Modifier | Optional horizontal inset modifier applied to the search bar row |
| label | String | "Search" | Placeholder text shown when the query is empty |
| scrollOffset | () -> Float | `{ 0f }` | Vertical scroll offset of the result list in pixels, or zero if it is empty. Drives the progressive blur band and the optional material transition |
| topInsets | WindowInsets | WindowInsets.statusBars | Top safe-area insets applied to the search bar |
| searchTopPadding | () -> Dp | `{ 0.dp }` | Additional top offset for the search bar (e.g. below a collapsed navigation capsule) |
| blurEnabled | Boolean | true | Whether blur and glass effects are enabled (automatically falls back when RuntimeShader is unsupported) |
| style | GlassStyle | GlassDefaults.Style | Glass appearance style |
| material | GlassMaterial | GlassSearchOverlayDefaults.material() | Glass material for the search input and cancel button |
| stroke | EdgeStroke? | GlassSearchOverlayDefaults.stroke() | Edge stroke along the glass capsules |
| shadow | GlassShadow? | GlassSearchOverlayDefaults.shadow() | Drop shadow behind the glass capsules |
| searchIcon | ImageVector | MiuixIcons.Basic.Search | Leading search icon |
| closeIcon | ImageVector | MiuixIcons.Basic.Close | Cancel icon beside the input |
| content | @Composable ColumnScope.(String) -> Unit | `{}` | Search result content receiving the current query string |

### GlassSearchOverlayDefaults

| Property / Function | Type | Default |
| :--- | :--- | :--- |
| BarHeight | Dp | `44.dp` |
| HorizontalPadding | Dp | `GlassTopAppBarDefaults.HorizontalPadding` |
| VerticalPadding | Dp | `4.dp` |
| ProgressiveBlurOverhang | Dp | `24.dp` |
| material() | GlassMaterial | `GlassNavigationRailDefaults.material()` |
| stroke() | EdgeStroke? | `GlassNavigationRailDefaults.stroke()` |
| shadow() | GlassShadow | `GlassShadows.Float` |
