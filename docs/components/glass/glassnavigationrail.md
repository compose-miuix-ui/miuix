# GlassNavigationRail

`GlassNavigationRail` is a floating glass sidebar for wide layouts. Expanded, it is a panel at the start edge listing destinations as icon and label. Collapsed, it travels to the top centre and becomes a `GlassNavigationBar` showing labels only, with the expand toggle in its leading slot, so the capsule navigates exactly like the bottom bar.

<div style="position: relative; height: 320px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../../compose/index.html?id=glassNavigationRail" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

::: warning Background Sampling
Record an opaque background without the glass surface itself. See [Glass Surfaces](/guide/glass#background-sampling).
:::

## Import

```kotlin
import top.yukonga.miuix.kmp.glass.GlassNavigationItem
import top.yukonga.miuix.kmp.glass.GlassNavigationRail
import top.yukonga.miuix.kmp.glass.GlassNavigationRailDefaults
import top.yukonga.miuix.kmp.glass.rememberGlassNavigationRailState
```

## Basic Usage

The rail is an overlay: it fills the space it is given and draws the surface inside it. Place it over the page, outside the backdrop recording.

```kotlin
var selectedIndex by remember { mutableIntStateOf(0) }
val railState = rememberGlassNavigationRailState(initialExpanded = true)
val items = listOf(
    GlassNavigationItem(homeIcon, "Home"),
    GlassNavigationItem(libraryIcon, "Library"),
    GlassNavigationItem(settingsIcon, "Settings"),
)

Box(Modifier.fillMaxSize()) {
    PageContent(
        // Move the page clear of the panel in the layout phase, on the rail's own clock.
        Modifier.layout { measurable, constraints ->
            val inset = ((GlassNavigationRailDefaults.Margin + GlassNavigationRailDefaults.ExpandedWidth).toPx() *
                railState.progress).roundToInt()
            val placeable = measurable.measure(constraints.offset(horizontal = -inset))
            layout(constraints.maxWidth, placeable.height) { placeable.placeRelative(inset, 0) }
        },
    )
    GlassNavigationRail(
        items = items,
        selectedIndex = selectedIndex,
        onSelect = { selectedIndex = it },
        backdrop = backdrop,
        state = railState,
    )
}
```

Collapsing is one continuous movement: each row flies from its place in the column into its slot in the bar's row while the icons dissolve, and the surface shrinks, rises and slides to the top centre with a short rebound. `railState.progress` is the morph's position from `0` (capsule) to `1` (panel). Read it in a layout or draw block, as above, so the page follows the panel without recomposing.

Once collapsed and settled, the rail *is* a `GlassNavigationBar` showing labels only. Taps, horizontal drags, the selection indicator and the semantics are the bar's own: a drag previews the destination under the pointer and commits it on release.

## Component States

### Collapsed

```kotlin
val railState = rememberGlassNavigationRailState(initialExpanded = false)
```

Collapsed, the rail occupies only the centred capsule on a top bar's centre line, so the page can use the full width. The capsule takes the place of the bar's collapsed title, so hide that title while it is there. `GlassTopAppBar` accepts `titleAlpha = { railState.progress }`. The capsule leaves room on both sides for the bar's navigation icon and actions, and compresses its slots when the labels need more than that.

### No Toggle

```kotlin
GlassNavigationRail(
    items = items,
    selectedIndex = selectedIndex,
    onSelect = { selectedIndex = it },
    backdrop = backdrop,
    state = railState,
    showToggle = false,
)
```

Hide the built-in toggle and drive `railState.expanded` yourself.

## Advanced Usage

### Scrollable Column

```kotlin
val scrollState = rememberScrollState()
GlassNavigationRail(
    items = items,
    selectedIndex = selectedIndex,
    onSelect = { selectedIndex = it },
    backdrop = backdrop,
    state = railState,
    scrollState = scrollState,
)
```

Once the panel has settled, the destination column scrolls under the toggle header; the surface itself stays put.

## Properties

### GlassNavigationRail Properties

| Property Name | Type | Description | Default Value | Required |
| --- | --- | --- | --- | --- |
| items | List\<GlassNavigationItem> | Ordered destinations | - | Yes |
| selectedIndex | Int | Current destination index | - | Yes |
| onSelect | (Int) -> Unit | Called with the destination the user selected | - | Yes |
| backdrop | Backdrop? | Background sampling source; null uses a solid theme fill | - | Yes |
| modifier | Modifier | Modifier applied to the overlay box | Modifier | No |
| state | GlassNavigationRailState | Expanded state and morph progress | rememberGlassNavigationRailState() | No |
| showToggle | Boolean | Show the built-in expand/collapse toggle | true | No |
| defaultWindowInsetsPadding | Boolean | Keep the surface inside the system bars, caption bar and display cutout | true | No |
| collapsedTop | Dp | Top of the collapsed capsule, relative to the safe area | GlassNavigationRailDefaults.CollapsedTop | No |
| expandedWidth | Dp | Panel width while expanded | GlassNavigationRailDefaults.ExpandedWidth | No |
| style | GlassStyle | Rendering style | GlassDefaults.Style | No |
| alpha | Float | Material opacity multiplier | 1f | No |
| material | GlassMaterial? | Surface body; null omits explicit material layers | GlassNavigationRailDefaults.material() | No |
| stroke | EdgeStroke? | Surface stroke; null removes it | GlassNavigationRailDefaults.stroke() | No |
| shadow | GlassShadow? | Surface shadow; null removes it | GlassShadows.Float | No |
| selectedColor | Color | Selected icon and label tint | MiuixTheme.colorScheme.primary in dynamic colour themes, otherwise onSurface | No |
| unselectedColor | Color | Unselected icon and label tint | MiuixTheme.colorScheme.onSurface | No |
| indicatorColor | Color | Resting indicator fill | GlassNavigationBarDefaults.indicatorColor() | No |
| indicatorPressedColor | Color | Pressed indicator fill in the collapsed capsule | GlassNavigationBarDefaults.indicatorPressedColor() | No |
| labelStyle | TextStyle | Destination label style; it also decides the width of a collapsed slot | MiuixTheme.textStyles.body1 | No |
| expandContentDescription | String | Toggle description while collapsed | GlassNavigationRailDefaults.ExpandContentDescription | No |
| collapseContentDescription | String | Toggle description while expanded | GlassNavigationRailDefaults.CollapseContentDescription | No |
| scrollState | ScrollState | Scroll state of the expanded column | rememberScrollState() | No |

### GlassNavigationRailState {#glassnavigationrailstate}

Create it with `rememberGlassNavigationRailState(initialExpanded = true)`; it survives configuration changes.

| Member | Type | Description |
| --- | --- | --- |
| expanded | Boolean | Whether the rail is the expanded panel; setting it starts the morph |
| progress | Float | Morph position, `0` for the capsule and `1` for the panel; read it in layout or draw |

### GlassNavigationRailDefaults Object {#glassnavigationraildefaults}

#### Constants

| Constant Name | Type | Description | Default Value |
| --- | --- | --- | --- |
| ExpandedWidth | Dp | Panel width while expanded | 250.dp |
| Margin | Dp | Distance between the panel and the window edges | 12.dp |
| CollapsedHeight | Dp | Height of the collapsed capsule | 44.dp |
| CollapsedTop | Dp | Capsule top that centres it on a top bar's centre line | 6.dp |
| ExpandContentDescription | String | Default toggle description while collapsed | "Expand navigation rail" |
| CollapseContentDescription | String | Default toggle description while expanded | "Collapse navigation rail" |

#### Methods

| Method Name | Return Type | Description |
| --- | --- | --- |
| material() | GlassMaterial | Theme-selected `GlassMaterials.puredThinGlass` |
| stroke() | EdgeStroke | `GlassEdgeStrokes.MiddleLight` in light mode; `MiddleDark` in dark mode |
