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

See [Glass Material](/guide/glass#setup) for dependencies and background sampling setup. The optional `miuix-glass-icons` module provides icons through `MiuixIcons.Glass`.

## Usage Requirements

- Buttons, bars and tabs can be used directly in layouts.
- Popup menus require a full-page `BoxScope` with anchors in the same coordinate space.
- `GlassDialog`, `GlassBottomSheet` and `GlassDropdownPreference` require a Miuix [Scaffold](../basic/scaffold).

The sampled backdrop must exclude the glass surface itself. See [Background Sampling](/guide/glass#background-sampling).

## Related Documentation

- [Glass Material](/guide/glass): material configuration and background sampling.
- [Icon System](/guide/icons): Glass icon imports and weights.
