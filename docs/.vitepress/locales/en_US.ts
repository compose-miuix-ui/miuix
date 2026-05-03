import { defineConfig } from "vitepress";

export default defineConfig({
    description: "A UI library for Compose MultiPlatform",
    lang: "en_US",
    themeConfig: {
        outline: {
            level: [2, 4],
        },

        footer: {
            message: "Released under the Apache-2.0 License",
            copyright: `Copyright © 2024-${new Date().getFullYear()} compose-miuix-ui`,
        },

        nav: [
            { text: "Home", link: "/" },
            { text: "Getting Started", link: "/guide/getting-started" },
            { text: "Components", link: "/components/" },
        ],

        sidebar: {
            "/guide/": [
                {
                    text: "Introduction",
                    collapsed: false,
                    items: [{ text: "Getting Started", link: "/guide/getting-started" }],
                },
                {
                    text: "Advanced",
                    collapsed: false,
                    items: [
                        { text: "Theme System", link: "/guide/theme" },
                        { text: "Color System", link: "/guide/colors" },
                        { text: "Text Styles", link: "/guide/textstyles" },
                        { text: "Icon System", link: "/guide/icons" },
                        { text: "Utility Functions", link: "/guide/utils" },
                        { text: "Navigation3 Support", link: "/guide/navigation3" },
                        { text: "Blur Effects", link: "/guide/blur" },
                        { text: "Platform Support", link: "/guide/multiplatform" },
                        { text: "Best Practices", link: "/guide/best-practices" },
                    ],
                },
            ],
            "/components/": [
                {
                    text: "Components",
                    collapsed: false,
                    items: [{ text: "Overview", link: "/components/" }],
                },
                {
                    text: "Scaffold Components",
                    collapsed: false,
                    items: [{ text: "Scaffold", link: "/components/scaffold" }],
                },
                {
                    text: "Basic Components",
                    collapsed: false,
                    items: [
                        { text: "Surface", link: "/components/surface" },
                        { text: "TopAppBar", link: "/components/topappbar" },
                        { text: "NavigationBar", link: "/components/navigationbar" },
                        { text: "NavigationRail", link: "/components/navigationrail" },
                        { text: "TabRow", link: "/components/tabrow" },
                        { text: "Card", link: "/components/card" },
                        { text: "BasicComponent", link: "/components/basiccomponent" },
                        { text: "Button", link: "/components/button" },
                        { text: "IconButton", link: "/components/iconbutton" },
                        { text: "Text", link: "/components/text" },
                        { text: "SmallTitle", link: "/components/smalltitle" },
                        { text: "TextField", link: "/components/textfield" },
                        { text: "Switch", link: "/components/switch" },
                        { text: "Checkbox", link: "/components/checkbox" },
                        { text: "RadioButton", link: "/components/radiobutton" },
                        { text: "Slider", link: "/components/slider" },
                        { text: "NumberPicker", link: "/components/numberpicker" },
                        { text: "ProgressIndicator", link: "/components/progressindicator", },
                        { text: "Snackbar", link: "/components/snackbar" },
                        { text: "Icon", link: "/components/icon" },
                        { text: "FloatingActionButton", link: "/components/floatingactionbutton", },
                        { text: "FloatingToolbar", link: "/components/floatingtoolbar" },
                        { text: "Divider", link: "/components/divider" },
                        { text: "PullToRefresh", link: "/components/pulltorefresh" },
                        { text: "SearchBar", link: "/components/searchbar" },
                        { text: "ColorPalette", link: "/components/colorpalette" },
                        { text: "ColorPicker", link: "/components/colorpicker" },
                    ],
                },
                {
                    text: "Extended Components",
                    collapsed: false,
                    items: [
                        { text: "ArrowPreference", link: "/components/arrowpreference" },
                        { text: "SwitchPreference", link: "/components/switchpreference" },
                        { text: "CheckboxPreference", link: "/components/checkboxpreference" },
                        { text: "RadioButtonPreference", link: "/components/radiobuttonpreference" },
                        { text: "OverlayListPopup", link: "/components/overlaylistpopup" },
                        { text: "OverlayDropdownPreference", link: "/components/overlaydropdownpreference" },
                        { text: "OverlaySpinnerPreference", link: "/components/overlayspinnerpreference" },
                        { text: "OverlayDropdownMenu", link: "/components/overlaydropdownmenu" },
                        { text: "OverlayIconDropdownMenu", link: "/components/overlayicondropdownmenu" },
                        { text: "OverlayBottomSheet", link: "/components/overlaybottomsheet" },
                        { text: "OverlayDialog", link: "/components/overlaydialog" },
                        { text: "WindowListPopup", link: "/components/windowlistpopup" },
                        { text: "WindowDropdownPreference", link: "/components/windowdropdownpreference" },
                        { text: "WindowSpinnerPreference", link: "/components/windowspinnerpreference" },
                        { text: "WindowDropdownMenu", link: "/components/windowdropdownmenu" },
                        { text: "WindowIconDropdownMenu", link: "/components/windowicondropdownmenu" },
                        { text: "WindowBottomSheet", link: "/components/windowbottomsheet" },
                        { text: "WindowDialog", link: "/components/windowdialog" },
                    ],
                },
            ],
        },
    },
});
