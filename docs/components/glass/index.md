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
| [GlassPopupItem](./glasspopupitem) | Menu row with optional icon and selection | Custom popup content |
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

## Related Documentation

- [Glass Material](/guide/glass): material configuration and background sampling.
- [Icon System](/guide/icons): Glass icon imports and weights.

