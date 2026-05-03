# Components

Miuix provides a rich set of UI components that strictly follow Xiaomi HyperOS Design Guidelines. Each component is carefully designed to ensure visual and interactive consistency with the native Xiaomi experience.

## Scaffold Components

| Component                          | Description                   | Common Usage                    |
| ---------------------------------- | ----------------------------- | ------------------------------- |
| [Scaffold](../components/scaffold) | Basic layout for applications | Page structure, content display |

::: warning
The Scaffold component provides a suitable container for cross-platform popup windows. Components such as `OverlayDialog`, `OverlayDropdownPreference`, `OverlaySpinnerPreference`, and `OverlayListPopup` are all implemented based on this and therefore need to be wrapped by this component.
:::

## Basic Components

| Component                                                  | Description                                | Common Usage                          |
| ---------------------------------------------------------- | ------------------------------------------ | ------------------------------------- |
| [Surface](../components/surface)                           | Basic container component                  | Content display, background container |
| [TopAppBar](../components/topappbar)                       | Application top navigation bar             | Page title, primary actions           |
| [NavigationBar](../components/navigationbar)               | Bottom navigation component                | Main page switching                   |
| [NavigationRail](../components/navigationrail)             | Side navigation component                  | Main page switching (Large screen)    |
| [TabRow](../components/tabrow)                             | Horizontal tab bar                         | Content category browsing             |
| [Card](../components/card)                                 | Container with related information         | Information display, content grouping |
| [BasicComponent](../components/basiccomponent)             | Universal base component                   | Custom component development          |
| [Button](../components/button)                             | Interactive element for triggering actions | Form submission, action confirmation  |
| [IconButton](../components/iconbutton)                     | Icon button component                      | Auxiliary actions, toolbars           |
| [Text](../components/text)                                 | Display text content with various styles   | Titles, body text, descriptive text   |
| [SmallTitle](../components/smalltitle)                     | Small title component                      | Auxiliary titles, category labels     |
| [TextField](../components/textfield)                       | Receives user text input                   | Form filling, search boxes            |
| [Switch](../components/switch)                             | Binary state toggle control                | Setting switches, feature enabling    |
| [Checkbox](../components/checkbox)                         | Multiple selection control                 | Multiple choices, terms agreement     |
| [RadioButton](../components/radiobutton)                   | Single selection control                   | Exclusive choices, option selection   |
| [Slider](../components/slider)                             | Sliding control for value adjustment       | Volume control, range selection       |
| [NumberPicker](../components/numberpicker)                 | Vertical scroll picker for number selection | Time picker, quantity selection       |
| [ProgressIndicator](../components/progressindicator)       | Displays operation progress status         | Loading, progress display             |
| [Snackbar](../components/snackbar)                         | Temporary message bar for brief feedback   | Operation result, status notification |
| [Icon](../components/icon)                                 | Icon display component                     | Icon buttons, status indicators       |
| [FloatingActionButton](../components/floatingactionbutton) | Floating action button                     | Primary actions, quick functions      |
| [FloatingToolbar](../components/floatingtoolbar)           | Floating toolbar                           | Quick actions, information display    |
| [Divider](../components/divider)                           | Content separator                          | Block separation, hierarchy division  |
| [PullToRefresh](../components/pulltorefresh)               | Pull-down refresh operation                | Data update, page refresh             |
| [SearchBar](../components/searchbar)                       | Search input field                         | Content search, quick find            |
| [ColorPalette](../components/colorpalette)                 | Grid palette with alpha slider             | Theme settings, color selection       |
| [ColorPicker](../components/colorpicker)                   | Color selection control                    | Theme settings, color selection       |

## Extended Components

| Component                                            | Description                                                                              | Common Usage                           |
| ---------------------------------------------------- | ---------------------------------------------------------------------------------------- | -------------------------------------- |
| [ArrowPreference](../components/arrowpreference)               | Arrow component based on BasicComponent                                                  | Clickable indication, navigation hints |
| [SwitchPreference](../components/switchpreference)             | Switch component based on BasicComponent                                                 | Setting switches, feature enabling     |
| [CheckboxPreference](../components/checkboxpreference)         | Checkbox component based on BasicComponent                                               | Multiple selection, terms agreement    |
| [RadioButtonPreference](../components/radiobuttonpreference)   | Radio button component based on BasicComponent                                           | Exclusive choices, option selection    |
| [OverlayListPopup](../components/overlaylistpopup)             | List popup component based on BasicComponent (uses MiuixPopupUtils; requires `Scaffold`) | Option selection, feature list         |
| [OverlayDropdownPreference](../components/overlaydropdownpreference) | Dropdown selector based on BasicComponent (uses MiuixPopupUtils; requires `Scaffold`)    | Option selection, feature list         |
| [OverlaySpinnerPreference](../components/overlayspinnerpreference) | Advanced selector based on BasicComponent (uses MiuixPopupUtils; requires `Scaffold`)    | Advanced options, feature list         |
| [OverlayDropdownMenu](../components/overlaydropdownmenu)       | Action menu based on BasicComponent (uses MiuixPopupUtils; requires `Scaffold`)          | Action menus, multi-select choices     |
| [OverlayIconDropdownMenu](../components/overlayicondropdownmenu) | Icon-button action menu (uses MiuixPopupUtils; requires `Scaffold`)                      | Toolbar actions, overflow menu         |
| [OverlayBottomSheet](../components/overlaybottomsheet)         | Bottom sheet based on BasicComponent (uses MiuixPopupUtils; requires `Scaffold`)         | Bottom drawer, additional options      |
| [OverlayDialog](../components/overlaydialog)                   | Dialog window based on BasicComponent (uses MiuixPopupUtils; requires `Scaffold`)        | Prompts, action confirmation           |
| [WindowListPopup](../components/windowlistpopup)     | Window-level list popup component                                                        | Option selection, feature list         |
| [WindowDropdownPreference](../components/windowdropdownpreference) | Window-level dropdown selector component                                                 | Option selection, feature list         |
| [WindowSpinnerPreference](../components/windowspinnerpreference)   | Window-level advanced selector component                                                 | Advanced options, feature list         |
| [WindowDropdownMenu](../components/windowdropdownmenu) | Window-level action menu component                                                       | Action menus, multi-select choices     |
| [WindowIconDropdownMenu](../components/windowicondropdownmenu) | Window-level icon-button action menu component                                           | Toolbar actions, overflow menu         |
| [WindowBottomSheet](../components/windowbottomsheet) | Window-level bottom sheet component                                                      | Bottom drawer, additional options      |
| [WindowDialog](../components/windowdialog)           | Window-level dialog component                                                            | Prompts, action confirmation           |
