# Basic Components

`miuix-ui` provides application layout, navigation, basic controls and Overlay/Window surfaces.

## Components

| Component | Description | Common Usage |
| --- | --- | --- |
| [Scaffold](./scaffold) | Basic layout for applications | Page structure, content display |
| [Surface](./surface)                           | Basic container component                  | Content display, background container |
| [TopAppBar](./topappbar)                       | Application top navigation bar             | Page title, primary actions           |
| [NavigationBar](./navigationbar)               | Bottom navigation component                | Main page switching                   |
| [NavigationRail](./navigationrail)             | Side navigation component                  | Main page switching (Large screen)    |
| [TabRow](./tabrow)                             | Horizontal tab bar                         | Content category browsing             |
| [BreadcrumbBar](./breadcrumbbar)               | Horizontal breadcrumb navigation bar         | File path navigation, location trail   |
| [Card](./card)                                 | Container with related information         | Information display, content grouping |
| [BasicComponent](./basiccomponent)             | Universal base component                   | Custom component development          |
| [Button](./button)                             | Interactive element for triggering actions | Form submission, action confirmation  |
| [IconButton](./iconbutton)                     | Icon button component                      | Auxiliary actions, toolbars           |
| [Text](./text)                                 | Display text content with various styles   | Titles, body text, descriptive text   |
| [SmallTitle](./smalltitle)                     | Small title component                      | Auxiliary titles, category labels     |
| [TextField](./textfield)                       | Receives user text input                   | Form filling, search boxes            |
| [Switch](./switch)                             | Binary state toggle control                | Setting switches, feature enabling    |
| [Checkbox](./checkbox)                         | Multiple selection control                 | Multiple choices, terms agreement     |
| [RadioButton](./radiobutton)                   | Single selection control                   | Exclusive choices, option selection   |
| [Slider](./slider)                             | Sliding control for value adjustment       | Volume control, range selection       |
| [NumberPicker](./numberpicker)                 | Vertical scroll picker for number selection | Time picker, quantity selection       |
| [ProgressIndicator](./progressindicator)       | Displays operation progress status         | Loading, progress display             |
| [Snackbar](./snackbar)                         | Temporary message bar for brief feedback   | Operation result, status notification |
| [Tooltip](./tooltip)                           | Brief label shown on hover or long press   | Icon button labels, element hints     |
| [Badge](./badge)                               | Small status overlay on an anchor          | Unread counts, status dots            |
| [Icon](./icon)                                 | Icon display component                     | Icon buttons, status indicators       |
| [FloatingActionButton](./floatingactionbutton) | Floating action button                     | Primary actions, quick functions      |
| [FloatingToolbar](./floatingtoolbar)           | Floating toolbar                           | Quick actions, information display    |
| [Divider](./divider)                           | Content separator                          | Block separation, hierarchy division  |
| [PullToRefresh](./pulltorefresh)               | Pull-down refresh operation                | Data update, page refresh             |
| [SearchBar](./searchbar)                       | Search input field                         | Content search, quick find            |
| [ColorPalette](./colorpalette)                 | Grid palette with alpha slider             | Theme settings, color selection       |
| [ColorPicker](./colorpicker)                   | Color selection control                    | Theme settings, color selection       |
| [OverlayListPopup](./overlaylistpopup)             | List popup component based on BasicComponent (uses MiuixPopupUtils; requires `Scaffold`) | Option selection, feature list         |
| [OverlayCascadingListPopup](./overlaycascadinglistpopup) | Two-level cascading list popup (uses MiuixPopupUtils; requires `Scaffold`)               | Submenus, grouped action panels        |
| [OverlayBottomSheet](./overlaybottomsheet)         | Bottom sheet based on BasicComponent (uses MiuixPopupUtils; requires `Scaffold`)         | Bottom drawer, additional options      |
| [OverlayDialog](./overlaydialog)                   | Dialog window based on BasicComponent (uses MiuixPopupUtils; requires `Scaffold`)        | Prompts, action confirmation           |
| [WindowListPopup](./windowlistpopup)     | Window-level list popup component                                                        | Option selection, feature list         |
| [WindowCascadingListPopup](./windowcascadinglistpopup) | Window-level two-level cascading list popup                                              | Submenus, grouped action panels        |
| [WindowBottomSheet](./windowbottomsheet) | Window-level bottom sheet component                                                      | Bottom drawer, additional options      |
| [WindowDialog](./windowdialog)           | Window-level dialog component                                                            | Prompts, action confirmation           |

## Setup

Add the `miuix-ui` dependency to your project:

```kotlin
kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation("top.yukonga.miuix.kmp:miuix-ui:<version>")
        }
    }
}
```

For Android-only projects:

```kotlin
dependencies {
    implementation("top.yukonga.miuix.kmp:miuix-ui-android:<version>")
}
```

See [Getting Started](/guide/getting-started) for complete project configuration and platform-specific dependencies.

## Organization and Hosts

- `top.yukonga.miuix.kmp.basic`: Scaffold, navigation, layout and interactive controls.
- `top.yukonga.miuix.kmp.overlay`: surfaces hosted inside the Compose page; they require Miuix [Scaffold](./scaffold) to provide `MiuixPopupHost`.
- `top.yukonga.miuix.kmp.window`: window-level surfaces, independent of the Scaffold popup host.

Choose Overlay or Window based on hosting and layering requirements; both belong to `miuix-ui`. Each component page documents its parameters, defaults and platform constraints.

## Related Documentation

- [Theme System](/guide/theme): colors, typography and theme configuration.
