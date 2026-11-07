# Extended Components

`miuix-preference` builds preference rows and dropdown menus on `miuix-ui`.

## Components

| Component | Description | Common Usage |
| --- | --- | --- |
| [ArrowPreference](./arrowpreference)               | Arrow component based on BasicComponent                                                  | Clickable indication, navigation hints |
| [SwitchPreference](./switchpreference)             | Switch component based on BasicComponent                                                 | Setting switches, feature enabling     |
| [CheckboxPreference](./checkboxpreference)         | Checkbox component based on BasicComponent                                               | Multiple selection, terms agreement    |
| [RadioButtonPreference](./radiobuttonpreference)   | Radio button component based on BasicComponent                                           | Exclusive choices, option selection    |
| [SliderPreference](./sliderpreference)             | Slider component based on BasicComponent                                                 | Value adjustment, volume/brightness    |
| [RangeSliderPreference](./rangesliderpreference)   | Range slider component based on BasicComponent                                           | Range selection, price filter          |
| [OverlayDropdownPreference](./overlaydropdownpreference) | Dropdown selector based on BasicComponent (uses MiuixPopupUtils; requires `Scaffold`)    | Option selection, feature list         |
| [OverlaySpinnerPreference](./overlayspinnerpreference) | Advanced selector based on BasicComponent (uses MiuixPopupUtils; requires `Scaffold`)    | Advanced options, feature list         |
| [OverlayDropdownMenu](./overlaydropdownmenu)       | Action menu based on BasicComponent (uses MiuixPopupUtils; requires `Scaffold`)          | Action menus, multi-select choices     |
| [OverlayIconDropdownMenu](./overlayicondropdownmenu) | Icon-button action menu (uses MiuixPopupUtils; requires `Scaffold`)                      | Toolbar actions, overflow menu         |
| [OverlayIconCascadingDropdownMenu](./overlayiconcascadingdropdownmenu) | Icon-button cascading two-level menu (uses MiuixPopupUtils; requires `Scaffold`)         | Toolbar actions with submenus          |
| [WindowDropdownPreference](./windowdropdownpreference) | Window-level dropdown selector component                                                 | Option selection, feature list         |
| [WindowSpinnerPreference](./windowspinnerpreference)   | Window-level advanced selector component                                                 | Advanced options, feature list         |
| [WindowDropdownMenu](./windowdropdownmenu) | Window-level action menu component                                                       | Action menus, multi-select choices     |
| [WindowIconDropdownMenu](./windowicondropdownmenu) | Window-level icon-button action menu component                                           | Toolbar actions, overflow menu         |
| [WindowIconCascadingDropdownMenu](./windowiconcascadingdropdownmenu) | Window-level icon-button cascading two-level menu                                        | Toolbar actions with submenus          |

## Setup

Add the `miuix-preference` dependency to your project:

```kotlin
kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation("top.yukonga.miuix.kmp:miuix-preference:<version>")
        }
    }
}
```

For Android-only projects:

```kotlin
dependencies {
    implementation("top.yukonga.miuix.kmp:miuix-preference-android:<version>")
}
```

`miuix-preference` includes `miuix-ui` transitively.

See [Getting Started](/guide/getting-started) for complete project configuration and platform-specific dependencies.

## Choosing Components and Hosts

- Components in `top.yukonga.miuix.kmp.preference` represent setting rows. `DropdownPreference` suits a single selected value; `SpinnerPreference` supports more elaborate option structures.
- Components in `top.yukonga.miuix.kmp.menu` represent action menus. Items can own their selection independently instead of sharing one single-selection index.
- `Overlay*` variants require Miuix [Scaffold](../basic/scaffold) and its `MiuixPopupHost`; `Window*` variants render at window level without that host.

Preference rows build on [BasicComponent](../basic/basiccomponent).

## Related Documentation

- [Theme System](/guide/theme): colors, typography and theme configuration.
