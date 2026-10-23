# GlassNavigationBar

`GlassNavigationBar` is a floating glass navigation bar for top-level destinations. Its selection indicator follows presses and drags, and an optional trailing action sits outside the navigation capsule. The caller owns selection and page navigation.

<div style="position: relative; height: 320px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../../compose/index.html?id=glassNavigationBar" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

::: warning Background Sampling
When using background sampling, `backdrop` must record opaque content behind the component and exclude the glass surface that samples it. See [Glass Material](/guide/glass#background-sampling) for setup.
:::

## Import

```kotlin
import top.yukonga.miuix.kmp.glass.GlassNavigationBar
import top.yukonga.miuix.kmp.glass.GlassNavigationBarDefaults
import top.yukonga.miuix.kmp.glass.GlassNavigationItem
```

## Basic Usage

A non-null `backdrop` is required. Keep the navigation bar and trailing action outside its recording.

`homeIcon` and `libraryIcon` are page-provided `ImageVector` icons. Use `selectedIndex` to display the corresponding page:

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
    modifier = Modifier
        .navigationBarsPadding()
        .padding(horizontal = 24.dp, vertical = 12.dp),
)
```

The bar fills the available width and adds no system-bar insets. Provide bounded horizontal space and apply margins and insets as needed. Destinations have equal widths; labels allow two lines and can increase the bar's height.

`onSelect` runs on pointer down and when a drag enters another destination, not only on release; pressing the current destination also calls it. Keyboard activation with Enter, NumPad Enter or Space calls it on key up, and accessibility activation calls it directly. Give icon-only items a meaningful `contentDescription`.

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

`showNavigation` is caller-owned Boolean state. The whole group scales, fades and blurs when hidden, and appears after a short delay when shown. Hiding does not remove its layout space. After the hide transition, destination interaction and semantics are disabled, and the trailing action is no longer placed.

`alpha` only controls the capsule material, not its labels, icons or indicator. Use `visible` to hide the whole group.

## Properties

### GlassNavigationBar Properties

| Property Name | Type | Description | Default Value | Required |
| --- | --- | --- | --- | --- |
| items | List\<GlassNavigationItem> | Ordered destinations | - | Yes |
| selectedIndex | Int | Current destination index | - | Yes |
| onSelect | (Int) -> Unit | Callback for pointer, keyboard and accessibility selection | - | Yes |
| backdrop | Backdrop | Background sampling source | - | Yes |
| modifier | Modifier | Modifier applied to the capsule and action group | Modifier | No |
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

Empty `items` render nothing, including the trailing action. Out-of-range `selectedIndex` is clamped for display without changing caller state. Setting `material = null` does not remove the indicator, stroke or shadow.

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

#### Functions

| Function Name | Return Type | Description |
| --- | --- | --- |
| material() | GlassMaterial | Theme-selected `GlassMaterials.puredThinGlass` |
| stroke() | GlassStroke | `GlassStrokes.MiddleLight` in light mode; `MiddleDark` in dark mode |
| indicatorColor() | Color | Black at 0.06 alpha in light mode; white at 0.12 alpha in dark mode |
| indicatorPressedColor() | Color | Black at 0.16 alpha in light mode; white at 0.26 alpha in dark mode |

## Advanced Usage

### Independent Trailing Action

The action owns its size and material and shares only the group's visibility transition. A `GlassIconButton` here does not inherit navigation-bar material, so pass its backdrop and size explicitly. `searchIcon` is an `ImageVector` and `openSearch` is the action callback:

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

The action is measured first; its width and `ActionSpacing` are reserved before the capsule is measured. Placement follows layout direction. Set `trailingAction = null` to remove both the action and its gap; an empty non-null slot still reserves a gap.
