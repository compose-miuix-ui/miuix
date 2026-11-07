# GlassTabRow

`GlassTabRow` displays equal-width glass tabs with primary or neutral colors. `GlassSegmentedTabRow` uses a joined track and sliding indicator. Both use caller-owned selection state.

<div style="position: relative; height: 440px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../../compose/index.html?id=glassTabRow" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

::: warning Background Sampling
Record an opaque background without the glass surface itself. See [Glass Material](/guide/glass#background-sampling).
:::

## Import

```kotlin
import top.yukonga.miuix.kmp.glass.GlassTabRow
import top.yukonga.miuix.kmp.glass.GlassTabRowDefaults
import top.yukonga.miuix.kmp.glass.GlassTabColors
import top.yukonga.miuix.kmp.glass.GlassSegmentedTabRow
import top.yukonga.miuix.kmp.glass.GlassSegmentedTabRowDefaults
```

## Basic Usage

`GlassTabRow` accepts a nullable backdrop; `GlassSegmentedTabRow` requires a non-null backdrop.

### Primary Pills

```kotlin
val tabs = listOf("Home", "Widgets", "Themes")
var selectedIndex by remember { mutableIntStateOf(0) }

GlassTabRow(
    tabs = tabs,
    selectedIndex = selectedIndex,
    onSelect = { selectedIndex = it },
    backdrop = backdrop,
    modifier = Modifier.fillMaxWidth(),
)
```

Primary colors use the theme's primary color for the selected tab.

### Neutral Pills

Choose neutral colors when page content already carries the accent. Set the shorter height separately:

```kotlin
GlassTabRow(
    tabs = tabs,
    selectedIndex = selectedIndex,
    onSelect = { selectedIndex = it },
    backdrop = backdrop,
    modifier = Modifier.fillMaxWidth(),
    height = GlassTabRowDefaults.NeutralHeight,
    colors = GlassTabRowDefaults.neutralColors(),
)
```

### Joined Segmented Track

```kotlin
GlassSegmentedTabRow(
    tabs = tabs,
    selectedIndex = selectedIndex,
    onSelect = { selectedIndex = it },
    backdrop = backdrop,
    modifier = Modifier.fillMaxWidth(),
)
```

Provide bounded width and distinct labels. Tabs select on click, share the available width and truncate long labels.

## Component States

### Resting and Glass Surfaces

`surfaceAlpha` controls material progress when a backdrop is present. Without a backdrop, it affects the whole tab; zero opacity does not disable clicks.

## Properties

### GlassTabRow Properties

| Property Name | Type | Description | Default Value | Required |
| --- | --- | --- | --- | --- |
| tabs | List\<String> | Ordered, distinct tab labels | - | Yes |
| selectedIndex | Int | Current tab index | - | Yes |
| onSelect | (Int) -> Unit | Callback with the clicked tab index | - | Yes |
| backdrop | Backdrop? | Background sampling source; may be null | - | Yes |
| modifier | Modifier | Modifier applied to the row | Modifier | No |
| style | GlassStyle | Blur style when material is null | GlassDefaults.Style | No |
| alpha | Float | Material color-layer, stroke and shadow opacity multiplier; without a backdrop, multiplies the entire pill drawing | 1f | No |
| surfaceAlpha | Float | Material progress, clamped to 0–1; without a backdrop, also multiplies the entire pill drawing | 1f | No |
| stroke | GlassStroke? | Stroke on each pill; null removes it | GlassTabRowDefaults.stroke() | No |
| shadow | GlassShadow? | Shadow on each pill; null removes it | GlassShadows.Float | No |
| material | GlassMaterial? | Pill material; null uses style-based blur | GlassTabRowDefaults.material() | No |
| height | Dp | Fixed pill height | GlassTabRowDefaults.Height | No |
| tabGap | Dp | Gap between pills | GlassTabRowDefaults.TabGap | No |
| colors | GlassTabColors | Selection, resting and press colors | GlassTabRowDefaults.primaryColors() | No |

### GlassSegmentedTabRow Properties {#glasssegmentedtabrow}

| Property Name | Type | Description | Default Value | Required |
| --- | --- | --- | --- | --- |
| tabs | List\<String> | Ordered, distinct tab labels | - | Yes |
| selectedIndex | Int | Current tab index | - | Yes |
| onSelect | (Int) -> Unit | Callback with the clicked tab index | - | Yes |
| backdrop | Backdrop | Background sampling source | - | Yes |
| modifier | Modifier | Modifier applied to the track | Modifier | No |
| style | GlassStyle | Blur style when material is null | GlassDefaults.Style | No |
| alpha | Float | Material color-layer, stroke and shadow opacity multiplier | 1f | No |
| surfaceAlpha | Float | Material progress, clamped to 0–1 | 1f | No |
| stroke | GlassStroke? | Track stroke; null removes it | GlassSegmentedTabRowDefaults.stroke() | No |
| shadow | GlassShadow? | Track shadow; null removes it | GlassShadows.Float | No |
| material | GlassMaterial? | Track material; null uses style-based blur | GlassSegmentedTabRowDefaults.material() | No |
| height | Dp | Fixed track height | GlassSegmentedTabRowDefaults.Height | No |
| trackColor | Color | Track fill at zero material progress | GlassSegmentedTabRowDefaults.restingTrackColor() | No |
| indicatorColor | Color | Sliding indicator fill | GlassSegmentedTabRowDefaults.indicatorColor() | No |
| selectedContentColor | Color | Selected label color | MiuixTheme.colorScheme.onSurface | No |
| contentColor | Color | Unselected label color | MiuixTheme.colorScheme.onSurface | No |

### GlassTabColors Properties {#glasstabcolors}

All constructor fields are required. Use a defaults factory and `copy` when customizing a few colors.

| Property Name | Type | Description | Default Value | Required |
| --- | --- | --- | --- | --- |
| selectedContainerColor | Color | Selected pill fill | - | Yes |
| selectedContentColor | Color | Selected label color | - | Yes |
| restingContainerColor | Color | Unselected fill at zero material progress | - | Yes |
| containerColor | Color | Unselected fill at full material progress | - | Yes |
| contentColor | Color | Unselected label color | - | Yes |
| pressedOverlayColor | Color | Overlay on a pressed pill, selected or not | - | Yes |
| restingStrokeAlpha | Float | Extra resting stroke strength for unselected pills | - | Yes |

### GlassTabRowDefaults Object {#glasstabrowdefaults}

#### Constants

| Constant Name | Type | Description | Default Value |
| --- | --- | --- | --- |
| Height | Dp | Primary pill height | 40.dp |
| NeutralHeight | Dp | Height paired with neutral colors | 35.dp |
| TabGap | Dp | Gap between pills | 12.dp |
| TabPaddingHorizontal | Dp | Horizontal content padding | 16.dp |
| TabPaddingVertical | Dp | Vertical content padding | 6.dp |

#### Methods

| Method Name | Return Type | Description |
| --- | --- | --- |
| shape(height: Dp = Height) | GlassShape | Capsule with radius height / 2 and smoothing 0f |
| material() | GlassMaterial | Theme-selected `GlassMaterials.puredThinGlass` |
| stroke() | GlassStroke | `GlassStrokes.MiddleLight` in light mode; `MiddleDark` in dark mode |
| primaryColors(selectedContainerColor: Color = MiuixTheme.colorScheme.primary, containerColor: Color = Color.Transparent) | GlassTabColors | Accented palette; arguments set the selected and material-active unselected fills |
| neutralColors() | GlassTabColors | Neutral palette; selected fill is `#FF383838` in light mode or `#FFE6E6E6` in dark mode; material-active unselected fill is transparent |

### GlassSegmentedTabRowDefaults Object {#glasssegmentedtabrowdefaults}

#### Constants

| Constant Name | Type | Description | Default Value |
| --- | --- | --- | --- |
| Height | Dp | Track height | 40.dp |
| TrackPadding | Dp | Inset around tabs and indicator | 3.dp |
| TabGap | Dp | Gap between tab slots | 8.dp |
| TabPaddingHorizontal | Dp | Horizontal tab content padding | 16.dp |

#### Methods

| Method Name | Return Type | Description |
| --- | --- | --- |
| shape(height: Dp = Height) | GlassShape | Capsule with radius height / 2 and smoothing 0f |
| material() | GlassMaterial | Theme-selected `GlassMaterials.puredThinGlass` |
| stroke() | GlassStroke | `GlassStrokes.MiddleLight` in light mode; `MiddleDark` in dark mode |
| restingTrackColor() | Color | White in light mode; white at 0.133 alpha composited over surface in dark mode |
| indicatorColor() | Color | Black at 0.06 alpha in light mode; white at 0.133 alpha in dark mode |

## Advanced Usage

### Custom Colors

```kotlin
GlassTabRow(
    tabs = tabs,
    selectedIndex = selectedIndex,
    onSelect = { selectedIndex = it },
    backdrop = backdrop,
    modifier = Modifier.fillMaxWidth(),
    colors = GlassTabRowDefaults.primaryColors().copy(
        selectedContainerColor = MiuixTheme.colorScheme.primaryContainer,
        selectedContentColor = MiuixTheme.colorScheme.onPrimaryContainer,
    ),
)
```

### Inside GlassTopAppBar {#inside-glasstopappbar}

Nested tabs inherit the top bar's backdrop and blur, but retain their own material colors and `surfaceAlpha`:

```kotlin
val isContentScrolled = listState.canScrollBackward

GlassTopAppBar(
    title = "Library",
    isContentScrolled = isContentScrolled,
    backdrop = backdrop,
    scrollBehavior = scrollBehavior,
    bottomContent = {
        GlassTabRow(
            tabs = tabs,
            selectedIndex = selectedIndex,
            onSelect = { selectedIndex = it },
            backdrop = backdrop,
            surfaceAlpha = if (isContentScrolled) 1f else 0f,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp),
        )
    },
)
```

Drive `surfaceAlpha` from page scroll state; tabs do not share the bar's material transition automatically.
