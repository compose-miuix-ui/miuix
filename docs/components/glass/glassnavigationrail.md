# GlassNavigationRail

`GlassNavigationRail` is a floating glass sidebar for wide layouts. Expanded, it is a panel at the start edge listing destinations as icon and label. Collapsed, it travels to the top centre and becomes a `GlassNavigationBar` — labels only, with the expand toggle in its leading slot — so the capsule navigates exactly like the bottom bar.

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
```

## Basic Usage

The rail is an overlay: it fills the space it is given and draws the surface inside it. Place it over the page, outside the backdrop recording, and reserve the expanded width in the page's own layout if the content must stay clear of the panel.

```kotlin
var selectedIndex by remember { mutableIntStateOf(0) }
var expanded by rememberSaveable { mutableStateOf(true) }
val items = listOf(
    GlassNavigationItem(homeIcon, "Home"),
    GlassNavigationItem(libraryIcon, "Library"),
    GlassNavigationItem(settingsIcon, "Settings"),
)

Box(Modifier.fillMaxSize()) {
    PageContent(Modifier.padding(start = if (expanded) 274.dp else 0.dp))
    GlassNavigationRail(
        items = items,
        selectedIndex = selectedIndex,
        onSelect = { selectedIndex = it },
        backdrop = backdrop,
        expanded = expanded,
        onExpandedChange = { expanded = it },
    )
}
```

Collapsing is one continuous movement: each row flies from its place in the column into its slot in the bar's row while the icons dissolve. The surface itself turns a corner rather than drifting diagonally — collapsing, it shrinks in place at the start edge and then slides to the top centre; expanding, it slides back before it grows. The morph's progress is read in layout and draw blocks, so a morph frame moves rows without recomposing them.

Once collapsed and settled, the rail *is* a `GlassNavigationBar` (`labelsOnly = true`). Taps, horizontal drags, the selection indicator and the semantics are the bar's own — a drag previews the destination under the pointer and commits it on release, and the capsule compresses its slots when the labels need more width than the window has.

## Component States

### Collapsed

```kotlin
GlassNavigationRail(
    items = items,
    selectedIndex = selectedIndex,
    onSelect = { selectedIndex = it },
    backdrop = backdrop,
    expanded = false,
    onExpandedChange = { expanded = it },
)
```

While collapsed, the rail occupies only the centred capsule at the top; the page can use the full width.

### No Toggle

```kotlin
GlassNavigationRail(
    items = items,
    selectedIndex = selectedIndex,
    onSelect = { selectedIndex = it },
    backdrop = backdrop,
    expanded = expanded,
)
```

Pass `onExpandedChange = null` to hide the built-in toggle and drive `expanded` yourself.

## Advanced Usage

### Scrollable Column

```kotlin
val scrollState = rememberScrollState()
GlassNavigationRail(
    items = items,
    selectedIndex = selectedIndex,
    onSelect = { selectedIndex = it },
    backdrop = backdrop,
    expanded = expanded,
    onExpandedChange = { expanded = it },
    scrollState = scrollState,
)
```

The expanded column scrolls; the surface itself stays put.

## Properties

### GlassNavigationRail Properties

| Property Name | Type | Description | Default Value | Required |
| --- | --- | --- | --- | --- |
| items | List\<GlassNavigationItem> | Ordered destinations | - | Yes |
| selectedIndex | Int | Current destination index | - | Yes |
| onSelect | (Int) -> Unit | Callback for the bar's pointer, keyboard and accessibility selection | - | Yes |
| backdrop | Backdrop? | Background sampling source; null uses a solid theme fill | - | Yes |
| modifier | Modifier | Modifier applied to the overlay box | Modifier | No |
| expanded | Boolean | Whether the rail is the panel or the top capsule | true | No |
| onExpandedChange | ((Boolean) -> Unit)? | Called by the built-in toggle; null hides it | null | No |
| defaultWindowInsetsPadding | Boolean | Keep the surface inside the system bars and display cutout | true | No |
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

### GlassNavigationRailDefaults Object {#glassnavigationraildedefaults}

#### Constants

| Constant Name | Type | Description | Default Value |
| --- | --- | --- | --- |
| ExpandedWidth | Dp | Panel width while expanded | 250.dp |
| Margin | Dp | Distance between the panel and the window edges | 12.dp |
| CornerRadius | Dp | Expanded panel corner radius | 22.dp |
| IconSize | Dp | Destination icon size | 24.dp |
| IconColumnWidth | Dp | Icon column width, icon and its gap | 36.dp |
| ItemContentPadding | Dp | Horizontal padding inside a destination | 14.dp |
| CollapsedRowHeight | Dp | Destination row height while collapsed | 38.dp |
| ExpandedRowGrowth | Dp | Extra row height gained while expanding | 4.dp |
| RowPitch | Dp | Distance between two expanded rows | 46.dp |
| RowTop | Dp | Row top while collapsed | 3.dp |
| ExpandedRowTop | Dp | Top of the first expanded row | 64.dp |
| ExpandedToggleTop | Dp | Toggle top while expanded | 12.dp |
| CollapsedToggleStart | Dp | Toggle start while collapsed, aligned with the bar's leading action | 2.dp |
| ExpandedToggleStart | Dp | Toggle start while expanded | 16.dp |
| ToggleSize | Dp | Toggle diameter while expanded | 44.dp |
| CollapsedToggleSpacing | Dp | Gap between the collapsed toggle and the first destination | 4.dp |
| LeadingActionWidth | Dp | Width reserved for the toggle inside the collapsed capsule | 48.dp |
| ItemHorizontalMargin | Dp | Horizontal margin of an expanded row | 12.dp |
| CollapsedEdgePadding | Dp | Inset after the last destination in the capsule | 3.dp |
| CollapsedContentStart | Dp | Where the first collapsed slot begins; the bar's content padding plus its leading action | 56.dp |
| CollapsedTopMargin | Dp | Top of the capsule, relative to the safe area | 6.dp |
| ExpandContentDescription | String | Default toggle description while collapsed | "Expand navigation rail" |
| CollapseContentDescription | String | Default toggle description while expanded | "Collapse navigation rail" |

#### Methods

| Method Name | Return Type | Description |
| --- | --- | --- |
| material() | GlassMaterial | Theme-selected `GlassMaterials.puredThinGlass` |
| stroke() | EdgeStroke | `GlassEdgeStrokes.MiddleLight` in light mode; `MiddleDark` in dark mode |
