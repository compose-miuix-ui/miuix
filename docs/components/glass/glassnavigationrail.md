# GlassNavigationRail

`GlassNavigationRail` is a floating glass sidebar for wide layouts. Expanded, it is a panel at the start edge listing destinations as icon and label. Collapsed, it becomes a top-centred, labels-only capsule with an expand toggle and the same drag-to-select interaction as `GlassNavigationBar`.

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

The rail overlays the page. Here, the parent handles safe-area insets for the page foreground and rail, with the rail's own inset padding disabled. Record the opaque background separately, edge-to-edge, without the rail. Offsets below are relative to the inset area.

```kotlin
var selectedIndex by remember { mutableIntStateOf(0) }
val railState = rememberGlassNavigationRailState(initialExpanded = true)
val expandedWidth = GlassNavigationRailDefaults.ExpandedWidth
val items = listOf(
    GlassNavigationItem(homeIcon, "Home"),
    GlassNavigationItem(libraryIcon, "Library"),
    GlassNavigationItem(settingsIcon, "Settings"),
)

Box(
    Modifier.fillMaxSize().windowInsetsPadding(
        WindowInsets.systemBars.union(WindowInsets.captionBar).union(WindowInsets.displayCutout),
    ),
) {
    PageContent(
        // Reserve start space for the panel during layout; keep the background edge-to-edge.
        Modifier.layout { measurable, constraints ->
            val inset = ((GlassNavigationRailDefaults.Margin + expandedWidth).toPx() *
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
        expandedWidth = expandedWidth,
        defaultWindowInsetsPadding = false,
    )
}
```

`railState.progress` ranges from `0` (collapsed) to `1` (expanded). Read it during layout or drawing to avoid recomposition on each animation frame.

Expanded rows use the theme's `MiuixIndication` and scroll vertically. The collapsed capsule supports horizontal drag-to-select. Presses survive the morph without replacing the destination nodes.

## Component States

### Collapsed

```kotlin
val railState = rememberGlassNavigationRailState(initialExpanded = false)
```

Collapsed, the page regains its width and the capsule sits at the top bar's centre. Set the `GlassTopAppBar`'s `titleAlpha = { railState.progress }` to hide the covered title. Side reserves are fixed; narrow containers or wide action groups may cause overlap.

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
| secondaryItems | List&lt;GlassNavigationItem&gt; | Optional secondary destinations shown below a divider while expanded | emptyList() | No |
| showToggle | Boolean | Show the built-in expand/collapse toggle | true | No |
| defaultWindowInsetsPadding | Boolean | Apply system safe-area insets; disable when the parent handles them | true | No |
| collapsedTop | Dp | Capsule top offset within the available area | GlassNavigationRailDefaults.CollapsedTop | No |
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

### GlassNavigationItem Properties

| Property Name | Type | Description | Default Value | Required |
| --- | --- | --- | --- | --- |
| icon | ImageVector | Destination icon | - | Yes |
| label | String? | Label of the item | null | No |
| contentDescription | String? | Accessibility description; falls back to label | null | No |
| badge | (@Composable () -> Unit)? | Optional badge shown on the item's icon, e.g. a `Badge` | null | No |

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
| Margin | Dp | Expanded panel's start and bottom spacing | 12.dp |
| CollapsedHeight | Dp | Height of the collapsed capsule | 44.dp |
| CollapsedTop | Dp | Default capsule top offset and expanded panel's top spacing | 4.dp |
| ExpandContentDescription | String | Default toggle description while collapsed | "Expand navigation rail" |
| CollapseContentDescription | String | Default toggle description while expanded | "Collapse navigation rail" |

#### Methods

| Method Name | Return Type | Description |
| --- | --- | --- |
| material() | GlassMaterial | Theme-selected `GlassMaterials.puredThinGlass` |
| stroke() | EdgeStroke | `GlassEdgeStrokes.MiddleLight` in light mode; `MiddleDark` in dark mode |
