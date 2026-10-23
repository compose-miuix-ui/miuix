# GlassTopAppBar

`GlassTopAppBar` displays collapsing titles, navigation controls and actions above scrolling content. A fading background band separates the bar from the page. Use [GlassIconButton](./glassiconbutton) in its slots to add glass button surfaces.

<div style="position: relative; height: 440px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../../compose/index.html?id=glassTopAppBar" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

::: warning Background Sampling
When using background sampling, `backdrop` must record opaque content behind the component and exclude the glass surface that samples it. See [Glass Material](/guide/glass#background-sampling) for setup.
:::

## Import

```kotlin
import top.yukonga.miuix.kmp.basic.MiuixScrollBehavior
import top.yukonga.miuix.kmp.glass.GlassIconButton
import top.yukonga.miuix.kmp.glass.GlassTopAppBar
import top.yukonga.miuix.kmp.glass.GlassTopAppBarDefaults
```

## Basic Usage

When no backdrop is available, top-bar buttons use their fallback fill.

`backIcon` is an `ImageVector` and `onBack` is the page's navigation callback:

```kotlin
val backdrop = rememberLayerBackdrop()
val listState = rememberLazyListState()
val scrollBehavior = MiuixScrollBehavior()

Scaffold(
    topBar = {
        GlassTopAppBar(
            title = "Library",
            isContentScrolled = listState.canScrollBackward,
            backdrop = backdrop,
            scrollBehavior = scrollBehavior,
            navigationIcon = {
                GlassIconButton(onClick = onBack) {
                    Icon(backIcon, contentDescription = "Back")
                }
            },
        )
    },
) { padding ->
    LazyColumn(
        state = listState,
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection)
            .layerBackdrop(backdrop)
            .background(MiuixTheme.colorScheme.surface),
        contentPadding = padding,
    ) {
        items(50) { index ->
            Text("Item $index", modifier = Modifier.padding(16.dp))
        }
    }
}
```

Only the list is recorded into `backdrop`. The glass controls use the bar's backdrop and material automatically.

## Component States

### Content at the Top or Scrolled

There are two overloads:

- `GlassTopAppBar(title, scrollBehavior = …)` derives overlap from `scrollBehavior.state.contentOffset < 0f`. Without a scroll behavior, it keeps the material active.
- `GlassTopAppBar(title, isContentScrolled = …)` accepts the actual page-overlap state. For a lazy list, pass `listState.canScrollBackward` so that returning to the top clears the material even if the large title remains collapsed.

`scrollBehavior` controls title collapse and the scroll-distance shadow ramp. `isContentScrolled` controls the background band and button material transitions; title collapse alone does not determine page overlap.

## Properties

### GlassTopAppBar Properties

These parameters are shared by both overloads:

| Property Name | Type | Description | Default Value | Required |
| --- | --- | --- | --- | --- |
| title | String | Compact title | - | Yes |
| modifier | Modifier | Modifier applied to the whole bar and background band | Modifier | No |
| contentModifier | Modifier | Modifier applied only to the foreground bar | Modifier | No |
| backdrop | Backdrop? | Background sampling source; null uses button fallback fills | null | No |
| largeTitle | String | Expanded title | title | No |
| subtitle | String | Subtitle | "" | No |
| scrollBehavior | ScrollBehavior? | Title collapse and shadow behavior | null | No |
| bandBrush | Brush | Background band gradient | GlassTopAppBarDefaults.bandBrush(MiuixTheme.colorScheme.surface) | No |
| bandOverhang | Dp | Band extension below the bar | GlassTopAppBarDefaults.BandOverhang | No |
| largeTitleBlurRadius | Dp | Blur radius as the large title fades out | GlassTopAppBarDefaults.LargeTitleBlurRadius | No |
| style | GlassStyle | Rendering style; does not replace the default button material | GlassDefaults.Style | No |
| alpha | Float | Additional button surface opacity multiplier | 1f | No |
| buttonSize | Dp | Default diameter for nested glass buttons | GlassTopAppBarDefaults.ButtonSize | No |
| buttonShape | GlassShape | Default shape for nested glass buttons | GlassShape(buttonSize / 2) | No |
| fill | Color | Button fallback fill | GlassTopAppBarDefaults.buttonFill() | No |
| stroke | GlassStroke? | Button stroke; null removes it | GlassTopAppBarDefaults.buttonStroke() | No |
| buttonShadow | GlassShadow? | Button shadow; null removes it | GlassShadows.Regular | No |
| defaultWindowInsetsPadding | Boolean | Whether to apply horizontal display-cutout and navigation-bar insets; top system-bar insets are always applied | true | No |
| titleAlpha | () -> Float | Draw-phase opacity multiplier for compact and large titles and subtitles | { 1f } | No |
| navigationIcon | @Composable () -> Unit | Leading control slot | {} | No |
| actions | @Composable RowScope.() -> Unit | Trailing action slot | {} | No |
| bottomContent | @Composable () -> Unit | Content below the titles | {} | No |

The explicit-overlap overload adds one required parameter:

| Property Name | Type | Description | Default Value | Required |
| --- | --- | --- | --- | --- |
| isContentScrolled | Boolean | Whether page content has left its resting position | - | Yes |

### GlassTopAppBarDefaults Object {#glasstopappbardefaults}

#### Constants

| Constant Name | Type | Description | Default Value |
| --- | --- | --- | --- |
| HorizontalPadding | Dp | Leading and trailing control inset | 12.dp |
| RampStart | Dp | Shadow ramp start offset | 0.dp |
| RampDistance | Dp | Scroll distance for the shadow ramp | 32.dp |
| LargeTitleBlurRadius | Dp | Large-title blur radius | 12.dp |
| ButtonSize | Dp | Button diameter | 44.dp |
| PressedContentAlpha | Float | Content opacity while pressed | 0.6f |
| BandOverhang | Dp | Band extension below the bar | 28.dp |

#### Functions

| Function Name | Return Type | Description |
| --- | --- | --- |
| collapseRamp(scrollBehavior: ScrollBehavior?) | State\<Float> | Shadow progress from 0 to 1 over RampDistance after RampStart; null behavior returns 1f |
| buttonFill() | Color | White in light mode; `#FF2C2C2C` in dark mode |
| buttonMaterial() | GlassMaterial | Theme-selected `GlassMaterials.puredThinGlass` |
| buttonStroke() | GlassStroke | `GlassStrokes.SmallLight` in light mode; `SmallDark` in dark mode |
| buttonPressedOverlay() | Color | Black at 0.10 alpha in light mode; white at 0.14 alpha in dark mode |
| bandBrush(color: Color) | Brush | Vertical page-color gradient, solid at the top and transparent at the bottom |

## Advanced Usage

### Adding Tabs

Place [GlassTabRow or GlassSegmentedTabRow](./glasstabrow) in `bottomContent`. They inherit the bar's backdrop and effective blur, but not its material-transition progress. Set their `surfaceAlpha` from page overlap rather than title expansion; see the [tab-row example](./glasstabrow#inside-glasstopappbar).

Nested `GlassIconButton` controls share the bar's material transition. Leave their `surfaceAlpha` at its default. Explicit null strokes and shadows on the bar remain null when inherited.

### Transforming the Foreground

`contentModifier` leaves the background band stationary, while `titleAlpha` affects the compact and large titles and their subtitles, not action controls or `bottomContent`. Here `foregroundOffsetPx` and `titleOpacity` are page-owned transition values:

```kotlin
GlassTopAppBar(
    title = "Library",
    isContentScrolled = listState.canScrollBackward,
    backdrop = backdrop,
    scrollBehavior = scrollBehavior,
    contentModifier = Modifier.graphicsLayer { translationY = foregroundOffsetPx },
    titleAlpha = { titleOpacity },
)
```

The band's overhang, button shadows and title blur do not increase layout height. Avoid clipping them in parent containers.
