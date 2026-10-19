---
title: GlassDropdownPreference
requiresScaffoldHost: true
hostComponent: Scaffold
popupHost: MiuixPopupHost
---

# GlassDropdownPreference

`GlassDropdownPreference` is a standard Miuix preference row that opens a glass dropdown. It manages expansion, anchor measurement, selection and dismissal. The caller owns `selectedIndex`.

## Import

```kotlin
import top.yukonga.miuix.kmp.glass.GlassDropdownPreference
```

The component is provided by `miuix-glass`; see [Glass Material](/guide/glass) for setup and platform requirements.

## Basic Usage

```kotlin
var selectedIndex by remember { mutableIntStateOf(0) }

Scaffold {
    GlassDropdownPreference(
        title = "Bluetooth stack log",
        items = listOf("Verbose", "Debug", "Info", "Warn", "Error"),
        selectedIndex = selectedIndex,
        onSelectedIndexChange = { selectedIndex = it },
        backdrop = null,
        summary = "Choose a log level",
    )
}
```

Pass a clean page `Backdrop` to enable the glass material, keeping its recording outside the popup host. A null backdrop uses the popup's opaque fallback. The root `Scaffold` hosts the popup by default. Use `renderInRootScaffold = false` to select the nearest host.

The title uses the same 17sp Medium typography as other preferences. Popup options use 16sp Medium, matching standard dropdown items. The selected value uses 14sp text and the standard 10 × 16dp arrow. Empty options disable the row. Disabling an open preference closes its popup.

## Properties

| Property | Type | Default / behavior |
| --- | --- | --- |
| `onSelectedIndexChange` | `(Int) -> Unit` | Required; called on selection and the popup closes |
| `items` | `List<String>` | Required; available options |
| `selectedIndex` | `Int` | Required; an out-of-range index shows no selected value |
| `title` | `String` | Required; preference title |
| `backdrop` | `Backdrop?` | Required; null uses an opaque fallback |
| `modifier` | `Modifier` | `Modifier`; applied to the row |
| `enabled` | `Boolean` | `true` |
| `showValue` | `Boolean` | `true`; false leaves only the arrow |
| `renderInRootScaffold` | `Boolean` | `true` |
| `titleColor` | `BasicComponentColors` | `BasicComponentDefaults.titleColor()` |
| `summary` | `String?` | `null` |
| `summaryColor` | `BasicComponentColors` | `BasicComponentDefaults.summaryColor()` |
| `startAction` | `(@Composable () -> Unit)?` | `null`; leading content |
| `bottomAction` | `(@Composable () -> Unit)?` | `null`; content below the row |
| `insideMargin` | `PaddingValues` | `BasicComponentDefaults.InsideMargin`, 16dp on all sides |
| `sizing` | `GlassPopupSizing` | `GlassPopupSizing()` |
| `visuals` | `GlassPopupVisuals` | `GlassPopupDefaults.visuals()` |
| `onExpandedChange` | `((Boolean) -> Unit)?` | `null`; reports opening and dismissal |

## Defaults

Row typography comes from `BasicComponent`; padding and title/summary colors come from `BasicComponentDefaults`. The arrow comes from `DropdownDefaults`. Glass panel colors, material and strokes come from `GlassPopupDefaults.visuals()`. `GlassDropdownDefaults.CornerRadius` controls the popup's default 24dp corner radius. Outside taps and Back dismiss the popup, retaining its exit animation.
