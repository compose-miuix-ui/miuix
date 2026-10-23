---
title: GlassPopupItem
---

# GlassPopupItem

`GlassPopupItem` is a menu row with optional icon, summary, selection tick, and submenu arrow. These elements can be combined independently. Clicking a row does not update selection or dismiss its popup; handle both in `onClick`.

<div style="position: relative; height: 440px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../../compose/index.html?id=glassPopupItem" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

## Import

```kotlin
import top.yukonga.miuix.kmp.glass.GlassPopupItem
```

## Basic Usage

`GlassPopupItem` has no `backdrop` parameter; background sampling is configured on its parent popup.

Place rows in a popup's `content` and manage menu state in the caller:

```kotlin
GlassPopupItem(
    text = "Sort by",
    summary = "Name",
    showArrow = true,
    arrowRotation = { if (submenuOpen) 90f else 0f },
    onClick = { submenuOpen = true },
)
```

### Selectable Row

Update the selected value and request dismissal together:

```kotlin
GlassPopupItem(
    text = "Name",
    selected = selectedIndex == 0,
    onClick = {
        selectedIndex = 0
        show = false
    },
)
```

## Component States

### Disabled State

Set `enabled = false` to prevent the row action. Its label and leading icon use `contentColor.copy(alpha = 0.4f)`, even when selected. The summary and submenu arrow retain `summaryColor`; a selected tick remains visible in `selectedContentColor`:

```kotlin
GlassPopupItem(
    text = "Unavailable",
    enabled = false,
    onClick = {},
)
```

## Properties

### GlassPopupItem Properties

| Property Name | Type | Description | Default Value | Required |
| --- | --- | --- | --- | --- |
| `text` | `String` | Single-line row label | — | Yes |
| `onClick` | `() -> Unit` | Called when an enabled row is tapped | — | Yes |
| `modifier` | `Modifier` | Modifier applied to the row | `Modifier` | No |
| `icon` | `ImageVector?` | Optional leading icon | `null` | No |
| `summary` | `String?` | Optional single-line second label | `null` | No |
| `enabled` | `Boolean` | Whether the row responds to taps | `true` | No |
| `selected` | `Boolean` | Shows a trailing tick; uses selected label and leading-icon color when enabled | `false` | No |
| `showArrow` | `Boolean` | Shows a trailing submenu chevron | `false` | No |
| `arrowRotation` | `() -> Float` | Chevron rotation in degrees, read during the layer phase | `{ 0f }` | No |
| `contentColor` | `Color` | Label and icon color | `MiuixTheme.colorScheme.onSurfaceContainer` | No |
| `summaryColor` | `Color` | Summary and arrow color | `MiuixTheme.colorScheme.onSurfaceContainerVariant` | No |
| `selectedContentColor` | `Color` | Enabled selected label and leading-icon color; selection tick color even when disabled | `MiuixTheme.colorScheme.primary` | No |
| `pressedColor` | `Color` | Fill behind a pressed row | `GlassPopupDefaults.itemPressedColor()` | No |

## Advanced Usage

### Predictive Back submenu arrow

For an arrow that follows a secondary menu's predictive Back progress, read the anchor from a draw or layer callback rather than storing the progress in separate state:

```kotlin
GlassPopupItem(
    text = "Sort by",
    showArrow = true,
    arrowRotation = {
        90f * (1f - anchor.secondaryBackProgress)
    },
    onClick = { openSecondary = true },
)
```

The row fills its panel width, truncates overflowing labels with an ellipsis, and uses an immediate press fill that animates out without a ripple. Row dimensions and `itemPressedColor()` are listed in [GlassPopup shared defaults](./glasspopup#shared-defaults).
