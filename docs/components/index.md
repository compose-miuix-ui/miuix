# Components

Miuix provides a rich set of UI components that strictly follow Xiaomi HyperOS Design Guidelines. Each component is carefully designed to ensure visual and interactive consistency with the native Xiaomi experience.

## Basic Components

Components provided by `miuix-ui`, including Scaffold, basic controls and Overlay/Window surfaces. See the [Basic overview](./basic/) for setup and host requirements.

| Component                                                  | Description                                | Common Usage                          |
| ---------------------------------------------------------- | ------------------------------------------ | ------------------------------------- |
| [Scaffold](./basic/scaffold) | Basic layout for applications | Page structure, content display |
| [Surface](./basic/surface)                           | Basic container component                  | Content display, background container |
| [TopAppBar](./basic/topappbar)                       | Application top navigation bar             | Page title, primary actions           |
| [NavigationBar](./basic/navigationbar)               | Bottom navigation component                | Main page switching                   |
| [NavigationRail](./basic/navigationrail)             | Side navigation component                  | Main page switching (Large screen)    |
| [TabRow](./basic/tabrow)                             | Horizontal tab bar                         | Content category browsing             |
| [BreadcrumbBar](./basic/breadcrumbbar)               | Horizontal breadcrumb navigation bar         | File path navigation, location trail   |
| [Card](./basic/card)                                 | Container with related information         | Information display, content grouping |
| [BasicComponent](./basic/basiccomponent)             | Universal base component                   | Custom component development          |
| [Button](./basic/button)                             | Interactive element for triggering actions | Form submission, action confirmation  |
| [IconButton](./basic/iconbutton)                     | Icon button component                      | Auxiliary actions, toolbars           |
| [Text](./basic/text)                                 | Display text content with various styles   | Titles, body text, descriptive text   |
| [SmallTitle](./basic/smalltitle)                     | Small title component                      | Auxiliary titles, category labels     |
| [TextField](./basic/textfield)                       | Receives user text input                   | Form filling, search boxes            |
| [Switch](./basic/switch)                             | Binary state toggle control                | Setting switches, feature enabling    |
| [Checkbox](./basic/checkbox)                         | Multiple selection control                 | Multiple choices, terms agreement     |
| [RadioButton](./basic/radiobutton)                   | Single selection control                   | Exclusive choices, option selection   |
| [Slider](./basic/slider)                             | Sliding control for value adjustment       | Volume control, range selection       |
| [NumberPicker](./basic/numberpicker)                 | Vertical scroll picker for number selection | Time picker, quantity selection       |
| [ProgressIndicator](./basic/progressindicator)       | Displays operation progress status         | Loading, progress display             |
| [Snackbar](./basic/snackbar)                         | Temporary message bar for brief feedback   | Operation result, status notification |
| [Tooltip](./basic/tooltip)                           | Brief label shown on hover or long press   | Icon button labels, element hints     |
| [Badge](./basic/badge)                               | Small status overlay on an anchor          | Unread counts, status dots            |
| [Icon](./basic/icon)                                 | Icon display component                     | Icon buttons, status indicators       |
| [FloatingActionButton](./basic/floatingactionbutton) | Floating action button                     | Primary actions, quick functions      |
| [FloatingToolbar](./basic/floatingtoolbar)           | Floating toolbar                           | Quick actions, information display    |
| [Divider](./basic/divider)                           | Content separator                          | Block separation, hierarchy division  |
| [PullToRefresh](./basic/pulltorefresh)               | Pull-down refresh operation                | Data update, page refresh             |
| [SearchBar](./basic/searchbar)                       | Search input field                         | Content search, quick find            |
| [ColorPalette](./basic/colorpalette)                 | Grid palette with alpha slider             | Theme settings, color selection       |
| [ColorPicker](./basic/colorpicker)                   | Color selection control                    | Theme settings, color selection       |
| [OverlayListPopup](./basic/overlaylistpopup)             | List popup component based on BasicComponent (uses MiuixPopupUtils; requires `Scaffold`) | Option selection, feature list         |
| [OverlayCascadingListPopup](./basic/overlaycascadinglistpopup) | Two-level cascading list popup (uses MiuixPopupUtils; requires `Scaffold`)               | Submenus, grouped action panels        |
| [OverlayBottomSheet](./basic/overlaybottomsheet)         | Bottom sheet based on BasicComponent (uses MiuixPopupUtils; requires `Scaffold`)         | Bottom drawer, additional options      |
| [OverlayDialog](./basic/overlaydialog)                   | Dialog window based on BasicComponent (uses MiuixPopupUtils; requires `Scaffold`)        | Prompts, action confirmation           |
| [WindowListPopup](./basic/windowlistpopup)     | Window-level list popup component                                                        | Option selection, feature list         |
| [WindowCascadingListPopup](./basic/windowcascadinglistpopup) | Window-level two-level cascading list popup                                              | Submenus, grouped action panels        |
| [WindowBottomSheet](./basic/windowbottomsheet) | Window-level bottom sheet component                                                      | Bottom drawer, additional options      |
| [WindowDialog](./basic/windowdialog)           | Window-level dialog component                                                            | Prompts, action confirmation           |

::: warning
The Scaffold component provides a suitable container for cross-platform popup windows. Components such as `OverlayDialog`, `OverlayDropdownPreference`, `OverlaySpinnerPreference`, and `OverlayListPopup` are all implemented based on this and therefore need to be wrapped by this component.
:::

## Extended Components

Preference and dropdown-menu components provided by `miuix-preference`. See the [Extended overview](./extended/) for setup and component selection.

| Component                                            | Description                                                                              | Common Usage                           |
| ---------------------------------------------------- | ---------------------------------------------------------------------------------------- | -------------------------------------- |
| [ArrowPreference](./extended/arrowpreference)               | Arrow component based on BasicComponent                                                  | Clickable indication, navigation hints |
| [SwitchPreference](./extended/switchpreference)             | Switch component based on BasicComponent                                                 | Setting switches, feature enabling     |
| [CheckboxPreference](./extended/checkboxpreference)         | Checkbox component based on BasicComponent                                               | Multiple selection, terms agreement    |
| [RadioButtonPreference](./extended/radiobuttonpreference)   | Radio button component based on BasicComponent                                           | Exclusive choices, option selection    |
| [SliderPreference](./extended/sliderpreference)             | Slider component based on BasicComponent                                                 | Value adjustment, volume/brightness    |
| [RangeSliderPreference](./extended/rangesliderpreference)   | Range slider component based on BasicComponent                                           | Range selection, price filter          |
| [OverlayDropdownPreference](./extended/overlaydropdownpreference) | Dropdown selector based on BasicComponent (uses MiuixPopupUtils; requires `Scaffold`)    | Option selection, feature list         |
| [OverlaySpinnerPreference](./extended/overlayspinnerpreference) | Advanced selector based on BasicComponent (uses MiuixPopupUtils; requires `Scaffold`)    | Advanced options, feature list         |
| [OverlayDropdownMenu](./extended/overlaydropdownmenu)       | Action menu based on BasicComponent (uses MiuixPopupUtils; requires `Scaffold`)          | Action menus, multi-select choices     |
| [OverlayIconDropdownMenu](./extended/overlayicondropdownmenu) | Icon-button action menu (uses MiuixPopupUtils; requires `Scaffold`)                      | Toolbar actions, overflow menu         |
| [OverlayIconCascadingDropdownMenu](./extended/overlayiconcascadingdropdownmenu) | Icon-button cascading two-level menu (uses MiuixPopupUtils; requires `Scaffold`)         | Toolbar actions with submenus          |
| [WindowDropdownPreference](./extended/windowdropdownpreference) | Window-level dropdown selector component                                                 | Option selection, feature list         |
| [WindowSpinnerPreference](./extended/windowspinnerpreference)   | Window-level advanced selector component                                                 | Advanced options, feature list         |
| [WindowDropdownMenu](./extended/windowdropdownmenu) | Window-level action menu component                                                       | Action menus, multi-select choices     |
| [WindowIconDropdownMenu](./extended/windowicondropdownmenu) | Window-level icon-button action menu component                                           | Toolbar actions, overflow menu         |
| [WindowIconCascadingDropdownMenu](./extended/windowiconcascadingdropdownmenu) | Window-level icon-button cascading two-level menu                                        | Toolbar actions with submenus          |

## Glass Components

Independent glass components provided by `miuix-glass`. See the [Glass overview](./glass/) for setup, platform requirements and background sampling.

| Component | Description | Common Usage |
| --- | --- | --- |
| [GlassIconButton](./glass/glassiconbutton) | Round glass icon action | Toolbar and floating actions |
| [GlassTopAppBar](./glass/glasstopappbar) | Collapsing glass top bar | Page titles and actions |
| [GlassNavigationBar](./glass/glassnavigationbar) | Floating glass navigation bar | Top-level destinations |
| [GlassTabRow](./glass/glasstabrow) | Independent glass tab pills | Content categories |
| [GlassSegmentedTabRow](./glass/glasstabrow#glasssegmentedtabrow) | Joined glass segmented track | Exclusive view switching |
| [GlassTransformPopup](./glass/glasstransformpopup) | Menu transforming from its button | Toolbar overflow actions |
| [GlassPopup](./glass/glasspopup) | Anchored glass menu | Action selection |
| [GlassSecondaryPopup](./glass/glasssecondarypopup) | Submenu beside a primary menu | Cascading menus |
| [GlassDropdownPopup](./glass/glassdropdownpopup) | Glass dropdown from a settings row | Option selection |
| [GlassPopupItem](./glass/glasspopupitem) | Menu row with optional icon and selection | Custom popup content |
| [GlassDropdownPreference](./glass/glassdropdownpreference) | Preference row with a managed glass dropdown | Settings selection |
| [GlassDialog](./glass/glassdialog) | Glass dialog over a scrim | Prompts and confirmation |
| [GlassDialogButton](./glass/glassdialog#glassdialogbutton) | Action over shared dialog material | Dialog actions |
| [GlassBottomSheet](./glass/glassbottomsheet) | Attached glass bottom sheet | Additional content and actions |
