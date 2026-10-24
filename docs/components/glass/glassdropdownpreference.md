---
title: GlassDropdownPreference
requiresScaffoldHost: true
prerequisites:
  - Must be used within `Scaffold` to provide `MiuixPopupHost`
  - Using outside `Scaffold` will cause popup content not to render
  - Multiple nested or side-by-side `Scaffold`s are supported without extra configuration
hostComponent: Scaffold
popupHost: MiuixPopupHost
---

# GlassDropdownPreference

`GlassDropdownPreference` is a dropdown preference component in Miuix with a glass popup, a title and optional supporting text. It manages expansion and dismissal, while the caller controls the selected option.

<div style="position: relative; height: 360px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../../compose/index.html?id=glassDropdownPreference" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

::: danger Prerequisite
This component depends on `Scaffold` providing `MiuixPopupHost` to render popup content. It must be used within `Scaffold`, otherwise popup content will not render correctly.
:::

::: warning Background Sampling
When using background sampling, `backdrop` must record opaque content behind the component and exclude the glass surface that samples it. See [Glass Material](/guide/glass#background-sampling) for setup.
:::

## Import

```kotlin
import top.yukonga.miuix.kmp.glass.GlassDropdownPreference
import top.yukonga.miuix.kmp.glass.GlassPopupDefaults
import top.yukonga.miuix.kmp.glass.GlassPopupSizing
import top.yukonga.miuix.kmp.blur.layerBackdrop
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
```

## Basic Usage

In this example, the page `Column` includes the preference row; `Scaffold` hosts the popup outside that recording.

```kotlin
val backdrop = rememberLayerBackdrop()
val items = listOf("Verbose", "Debug", "Info", "Warn", "Error")
var selectedIndex by remember { mutableIntStateOf(2) }

Scaffold { padding ->
    Column(
        Modifier.fillMaxSize()
            .layerBackdrop(backdrop)
            .background(MiuixTheme.colorScheme.background)
            .padding(padding),
    ) {
        GlassDropdownPreference(
            title = "Log level",
            summary = "Choose which messages to record",
            items = items,
            selectedIndex = selectedIndex,
            onSelectedIndexChange = { selectedIndex = it },
            backdrop = backdrop,
        )
    }
}
```

If the overlay can cover page bars, include those bars in the backdrop, but exclude the popup host and its scrim. A null backdrop uses `visuals.containerColor` as the opaque fallback, retaining the configured stroke and shadow.

## Component States

- **Collapsed**: The row shows the selected value and dropdown arrow. `showValue = false` hides only the value.
- **Expanded**: The value and arrow fade out while the rest of the row stays in place. The selected popup item shows a tick. Choosing an option requests dismissal and calls `onSelectedIndexChange`; the caller must update `selectedIndex`.
- **Disabled**: `enabled = false` or an empty `items` list prevents opening and closes an already open popup.
- **No selection**: An out-of-range `selectedIndex` shows no selected value or tick.

Opening and selection provide context-click and confirmation haptics respectively. Outside taps and Back dismiss the popup; a cancelled predictive Back gesture restores it. The component retains exit content internally and stops popup-row input and accessibility actions as soon as dismissal is requested. No external `if (expanded)` wrapper or manual anchor measurement is needed.

## Properties

### GlassDropdownPreference Properties

| Property Name | Type | Description | Default Value | Required |
| --- | --- | --- | --- | --- |
| onSelectedIndexChange | (Int) -> Unit | Called with the selected option index; the caller updates selection | - | Yes |
| items | List&lt;String&gt; | Available options; an empty list disables the preference | - | Yes |
| selectedIndex | Int | Selected option index; out-of-range values show no selection | - | Yes |
| title | String | Preference title | - | Yes |
| backdrop | Backdrop? | Clean page backdrop excluding the popup host; null uses the fallback | - | Yes |
| modifier | Modifier | Modifier applied to the preference row | Modifier | No |
| enabled | Boolean | Whether the row can open the popup; also requires non-empty items | true | No |
| showValue | Boolean | Display the selected value beside the arrow | true | No |
| renderInRootScaffold | Boolean | Use the outermost Scaffold; false uses the current Scaffold | true | No |
| titleColor | BasicComponentColors | Enabled and disabled title colors | BasicComponentDefaults.titleColor() | No |
| summary | String? | Supporting text beneath the title | null | No |
| summaryColor | BasicComponentColors | Enabled and disabled summary colors | BasicComponentDefaults.summaryColor() | No |
| startAction | (@Composable () -> Unit)? | Leading content | null | No |
| bottomAction | (@Composable () -> Unit)? | Content below the row | null | No |
| insideMargin | PaddingValues | Row content padding | BasicComponentDefaults.InsideMargin | No |
| sizing | GlassPopupSizing | Popup size limits and window safe margin | GlassPopupSizing() | No |
| visuals | GlassPopupVisuals | Popup material, stroke, shadow and fallback color | GlassPopupDefaults.visuals() | No |
| onExpandedChange | ((Boolean) -> Unit)? | Reports target expansion changes, not animation completion | null | No |

The row uses `BasicComponentDefaults` for padding and title/summary colors. Popup rows use `GlassPopupItem`. `GlassPopupSizing()` defaults to a minimum width of `200.dp`, maximum width of `288.dp`, maximum height of `416.dp` and safe margin of `12.dp`. Appearance options are documented in [shared popup defaults](./glasspopup#shared-defaults). This component does not expose a separate corner-radius parameter.

## Advanced Usage

### Custom Popup Appearance

Replace the preference call inside the recorded page in the basic example to adjust popup limits and remove the shadow:

```kotlin
GlassDropdownPreference(
    title = "Log level",
    items = items,
    selectedIndex = selectedIndex,
    onSelectedIndexChange = { selectedIndex = it },
    backdrop = backdrop,
    showValue = false,
    sizing = GlassPopupSizing(maxWidth = 320.dp, maxHeight = 480.dp),
    visuals = GlassPopupDefaults.visuals(shadow = null),
)
```

The popup still uses its default theme-aware material and bloom stroke. In nested Scaffolds, `renderInRootScaffold = false` selects the current host; make sure that host is also outside the supplied backdrop recording.

### Observing Expansion

Use `onExpandedChange` to update supporting text or coordinate nearby UI. With the same selection and backdrop as the basic example, place this state and preference inside the recorded page:

```kotlin
var expanded by remember { mutableStateOf(false) }

GlassDropdownPreference(
    title = "Log level",
    summary = if (expanded) "Choose a level" else "Choose which messages to record",
    items = items,
    selectedIndex = selectedIndex,
    onSelectedIndexChange = { selectedIndex = it },
    backdrop = backdrop,
    onExpandedChange = { expanded = it },
)
```

The callback observes expansion; it does not make expansion externally controlled. A `false` value reports the start of dismissal, not completion of the exit animation. Expansion, anchor measurement and exit composition remain managed by the component.
