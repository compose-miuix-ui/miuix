# Glass Components

`miuix-glass` provides glass buttons, navigation bars, tabs, menus, dialogs and bottom sheets.

## Components

| Component | Description | Common Usage |
| --- | --- | --- |
| [GlassIconButton](./glassiconbutton) | Round glass icon action | Toolbar and floating actions |
| [GlassTopAppBar](./glasstopappbar) | Collapsing glass top bar | Page titles and actions |
| [GlassNavigationBar](./glassnavigationbar) | Floating glass navigation bar | Top-level destinations |
| [GlassTabRow](./glasstabrow) | Independent glass tab pills | Content categories |
| [GlassSegmentedTabRow](./glasstabrow#glasssegmentedtabrow) | Joined glass segmented track | Exclusive view switching |
| [GlassTransformPopup](./glasstransformpopup) | Menu transforming from its button | Toolbar overflow actions |
| [GlassPopup](./glasspopup) | Anchored glass menu | Action selection |
| [GlassSecondaryPopup](./glasssecondarypopup) | Submenu beside a primary menu | Cascading menus |
| [GlassDropdownPopup](./glassdropdownpopup) | Glass dropdown from a settings row | Option selection |
| [GlassDropdownPreference](./glassdropdownpreference) | Preference row with a managed glass dropdown | Settings selection |
| [GlassDialog](./glassdialog) | Glass dialog over a scrim | Prompts and confirmation |
| [GlassDialogButton](./glassdialog#glassdialogbutton) | Action over shared dialog material | Dialog actions |
| [GlassBottomSheet](./glassbottomsheet) | Attached glass bottom sheet | Additional content and actions |

## Setup

Add the `miuix-glass` dependency to your project:

```kotlin
kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation("top.yukonga.miuix.kmp:miuix-glass:<version>")
            // Optional: Glass icon set
            implementation("top.yukonga.miuix.kmp:miuix-glass-icons:<version>")
        }
    }
}
```

For Android-only projects:

```kotlin
dependencies {
    implementation("top.yukonga.miuix.kmp:miuix-glass-android:<version>")
    // Optional: Glass icon set
    implementation("top.yukonga.miuix.kmp:miuix-glass-icons-android:<version>")
}
```

`miuix-glass` includes `miuix-blur` and `miuix-ui` transitively. The optional `miuix-glass-icons` module provides the Glass icon set.

See [Getting Started](/guide/getting-started) for complete project configuration and platform-specific dependencies.

## Component Organization & Hosts

- **Standalone Glass Controls** (`GlassIconButton`, `GlassTopAppBar`, `GlassNavigationBar`, `GlassTabRow`): Used directly in layouts, sampling background content via `backdrop` to render frosted blur, tint, and bloom strokes.
- **Popup & Transform Menus** (`GlassPopup`, `GlassTransformPopup`, `GlassSecondaryPopup`, `GlassDropdownPopup`): Used in `BoxScope`, providing smooth transformation and cascading levels from measured anchors without requiring a `Scaffold`.
- **Scaffold-Hosted Overlays** (`GlassDialog`, `GlassBottomSheet`, `GlassDropdownPreference`): Must be used within a Miuix [Scaffold](../basic/scaffold) to provide `MiuixPopupHost` for rendering scrims and overlay panels.

When using background sampling, `backdrop` must record content behind the component and exclude the glass surface itself. Each component page details specific parameters, defaults, and usage.

## Related Documentation

- [Glass Material](/guide/glass): material configuration and background sampling.
- [Icon System](/guide/icons): Glass icon imports and weights.
