# GlassNavigationBar

`GlassNavigationBar` is a floating glass navigation bar for top-level destinations. The caller owns selection state and page navigation; an optional trailing action sits outside the capsule.

<div style="position: relative; height: 320px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../../compose/index.html?id=glassNavigationBar" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

::: warning Background Sampling
Record an opaque background without the glass surface itself. See [Glass Material](/guide/glass#background-sampling).
:::

## Import

```kotlin
import top.yukonga.miuix.kmp.glass.GlassNavigationBar
import top.yukonga.miuix.kmp.glass.GlassNavigationBarDefaults
import top.yukonga.miuix.kmp.glass.GlassNavigationItem
```

## Basic Usage

Keep the navigation bar and trailing action outside the backdrop recording. Pass `backdrop = null` to use the theme's `surfaceContainer` fill instead of background sampling.

`homeIcon` and `libraryIcon` are page-provided `ImageVector` icons:

```kotlin
var selectedIndex by remember { mutableIntStateOf(0) }
val items = listOf(
    GlassNavigationItem(homeIcon, label = "Home"),
    GlassNavigationItem(libraryIcon, label = "Library"),
)

GlassNavigationBar(
    items = items,
    selectedIndex = selectedIndex,
    onSelect = { selectedIndex = it },
    backdrop = backdrop,
    modifier = Modifier.padding(horizontal = 24.dp),
)
```

Destinations share the available width. Labels allow two lines. Selection updates on press and drag; provide `contentDescription` for icon-only items.

## Component States

### Visibility

```kotlin
GlassNavigationBar(
    items = items,
    selectedIndex = selectedIndex,
    onSelect = { selectedIndex = it },
    backdrop = backdrop,
    visible = showNavigation,
)
```

Use `visible` to show or hide the group. `alpha` controls material opacity.

## Properties

### GlassNavigationBar Properties

| Property Name | Type | Description | Default Value | Required |
| --- | --- | --- | --- | --- |
| items | List\<GlassNavigationItem> | Ordered destinations | - | Yes |
| selectedIndex | Int | Current destination index | - | Yes |
| onSelect | (Int) -> Unit | Callback for pointer, keyboard and accessibility selection | - | Yes |
| backdrop | Backdrop? | Background sampling source; null uses a solid theme fill | - | Yes |
| modifier | Modifier | Modifier applied to the capsule and action group | Modifier | No |
| defaultWindowInsetsPadding | Boolean | Apply default window insets padding | true | No |
| style | GlassStyle | Rendering style | GlassDefaults.Style | No |
| shape | GlassShape | Capsule shape | GlassShape(CornerSize(50), CornerSize(50), CornerSize(50), CornerSize(50)) | No |
| alpha | Float | Capsule material opacity multiplier | 1f | No |
| visible | Boolean | Target visibility of the whole group | true | No |
| stroke | GlassStroke? | Capsule stroke; null removes it | GlassNavigationBarDefaults.stroke() | No |
| shadow | GlassShadow? | Capsule shadow; null removes it | GlassShadows.Float | No |
| height | Dp | Minimum capsule height | GlassNavigationBarDefaults.Height | No |
| material | GlassMaterial? | Capsule body; null omits explicit material layers | GlassNavigationBarDefaults.material() | No |
| indicatorColor | Color | Resting indicator fill | GlassNavigationBarDefaults.indicatorColor() | No |
| indicatorPressedColor | Color | Pressed indicator fill | GlassNavigationBarDefaults.indicatorPressedColor() | No |
| selectedColor | Color | Selected icon and label tint | MiuixTheme.colorScheme.onBackground | No |
| unselectedColor | Color | Unselected icon and label tint | MiuixTheme.colorScheme.onBackground | No |
| trailingAction | (@Composable () -> Unit)? | Independent action outside the capsule | null | No |

### GlassNavigationItem Properties {#glassnavigationitem}

| Property Name | Type | Description | Default Value | Required |
| --- | --- | --- | --- | --- |
| icon | ImageVector | Destination icon | - | Yes |
| label | String? | Caption below the icon | null | No |
| contentDescription | String? | Accessibility description; falls back to label | null | No |

### GlassNavigationBarDefaults Object {#glassnavigationbardefaults}

#### Constants

| Constant Name | Type | Description | Default Value |
| --- | --- | --- | --- |
| Height | Dp | Minimum capsule height | 54.dp |
| ActionSpacing | Dp | Capsule-to-action gap | 12.dp |
| IconSize | Dp | Destination icon size | 28.dp |
| LabelSize | Dp | Caption size converted to sp using current density | 11.dp |
| LargeLabelSize | Dp | Caption size when font scale is at least 1.6 | 16.dp |
| ContentPaddingVertical | Dp | Vertical padding around icon and caption | 6.dp |
| LabelSpacing | Dp | Icon-to-caption gap | 0.dp |
| IndicatorPaddingVertical | Dp | Indicator vertical inset | 3.dp |
| ContentPaddingHorizontal | Dp | Horizontal destination inset | 8.dp |
| IndicatorOverhang | Dp | Indicator extension on each side of a destination | 5.dp |
| PressedAlpha | Float | Pressed destination content opacity | 0.6f |

#### Methods

| Method Name | Return Type | Description |
| --- | --- | --- |
| material() | GlassMaterial | Theme-selected `GlassMaterials.puredThinGlass` |
| stroke() | GlassStroke | `GlassStrokes.MiddleLight` in light mode; `MiddleDark` in dark mode |
| indicatorColor() | Color | Black at 0.06 alpha in light mode; white at 0.12 alpha in dark mode |
| indicatorPressedColor() | Color | Black at 0.16 alpha in light mode; white at 0.26 alpha in dark mode |

## Advanced Usage

### Independent Trailing Action

Pass a `GlassIconButton` to `trailingAction`, specifying its backdrop and size:

```kotlin
import top.yukonga.miuix.kmp.glass.GlassIconButton

GlassNavigationBar(
    items = items,
    selectedIndex = selectedIndex,
    onSelect = { selectedIndex = it },
    backdrop = backdrop,
    trailingAction = {
        GlassIconButton(
            onClick = openSearch,
            backdrop = backdrop,
            size = GlassNavigationBarDefaults.Height,
        ) {
            Icon(searchIcon, contentDescription = "Search")
        }
    },
)
```
