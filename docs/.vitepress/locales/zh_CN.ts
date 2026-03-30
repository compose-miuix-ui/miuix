import { defineConfig } from "vitepress";

export default defineConfig({
    description: "A UI library for Compose MultiPlatform",
    lang: "zh_CN",
    themeConfig: {
        outline: {
            level: [2, 4],
            label: "页面导航",
        },

        langMenuLabel: "多语言",
        returnToTopLabel: "回到顶部",
        sidebarMenuLabel: "菜单",
        darkModeSwitchLabel: "主题",
        lightModeSwitchTitle: "切换到浅色模式",
        darkModeSwitchTitle: "切换到深色模式",
        skipToContentLabel: "跳转到内容",

        footer: {
            message: "基于 Apache-2.0 许可发布",
            copyright: `版权所有 © 2024-${new Date().getFullYear()} compose-miuix-ui`,
        },

        nav: [
            { text: "首页", link: "/zh_CN/" },
            { text: "快速开始", link: "/zh_CN/guide/getting-started" },
            { text: "组件库", link: "/zh_CN/components/" },
        ],

        sidebar: {
            "/zh_CN/guide/": [
                {
                    text: "入门",
                    collapsed: false,
                    items: [{ text: "快速开始", link: "/zh_CN/guide/getting-started" }],
                },
                {
                    text: "进阶",
                    collapsed: false,
                    items: [
                        { text: "主题系统", link: "/zh_CN/guide/theme" },
                        { text: "颜色系统", link: "/zh_CN/guide/colors" },
                        { text: "文本样式", link: "/zh_CN/guide/textstyles" },
                        { text: "图标系统", link: "/zh_CN/guide/icons" },
                        { text: "工具函数", link: "/zh_CN/guide/utils" },
                        { text: "导航支持", link: "/zh_CN/guide/navigation3" },
                        { text: "模糊效果", link: "/zh_CN/guide/blur" },
                        { text: "平台支持", link: "/zh_CN/guide/multiplatform" },
                        { text: "最佳实践", link: "/zh_CN/guide/best-practices" },
                    ],
                },
            ],
            "/zh_CN/components/": [
                {
                    text: "组件库",
                    collapsed: false,
                    items: [{ text: "总览", link: "/zh_CN/components/" }],
                },
                {
                    text: "脚手架",
                    collapsed: false,
                    items: [{ text: "Scaffold", link: "/zh_CN/components/scaffold" }],
                },
                {
                    text: "基础组件",
                    collapsed: false,
                    items: [
                        { text: "Surface", link: "/zh_CN/components/surface" },
                        { text: "TopAppBar", link: "/zh_CN/components/topappbar" },
                        { text: "NavigationBar", link: "/zh_CN/components/navigationbar" },
                        { text: "NavigationRail", link: "/zh_CN/components/navigationrail" },
                        { text: "TabRow", link: "/zh_CN/components/tabrow" },
                        { text: "Card", link: "/zh_CN/components/card" },
                        { text: "BasicComponent", link: "/zh_CN/components/basiccomponent", },
                        { text: "Button", link: "/zh_CN/components/button" },
                        { text: "IconButton", link: "/zh_CN/components/iconbutton" },
                        { text: "Text", link: "/zh_CN/components/text" },
                        { text: "SmallTitle", link: "/zh_CN/components/smalltitle" },
                        { text: "TextField", link: "/zh_CN/components/textfield" },
                        { text: "Switch", link: "/zh_CN/components/switch" },
                        { text: "Checkbox", link: "/zh_CN/components/checkbox" },
                        { text: "RadioButton", link: "/zh_CN/components/radiobutton" },
                        { text: "Slider", link: "/zh_CN/components/slider" },
                        { text: "NumberPicker", link: "/zh_CN/components/numberpicker" },
                        { text: "ProgressIndicator", link: "/zh_CN/components/progressindicator", },
                        { text: "Snackbar", link: "/zh_CN/components/snackbar" },
                        { text: "Icon", link: "/zh_CN/components/icon" },
                        { text: "FloatingActionButton", link: "/zh_CN/components/floatingactionbutton", },
                        { text: "FloatingToolbar", link: "/zh_CN/components/floatingtoolbar", },
                        { text: "Divider", link: "/zh_CN/components/divider" },
                        { text: "PullToRefresh", link: "/zh_CN/components/pulltorefresh" },
                        { text: "SearchBar", link: "/zh_CN/components/searchbar" },
                        { text: "ColorPalette", link: "/zh_CN/components/colorpalette" },
                        { text: "ColorPicker", link: "/zh_CN/components/colorpicker" },
                    ],
                },
                {
                    text: "扩展组件",
                    collapsed: false,
                    items: [
                        { text: "ArrowPreference", link: "/zh_CN/components/arrowpreference" },
                        { text: "SwitchPreference", link: "/zh_CN/components/switchpreference" },
                        { text: "CheckboxPreference", link: "/zh_CN/components/checkboxpreference" },
                        { text: "RadioButtonPreference", link: "/zh_CN/components/radiobuttonpreference" },
                        { text: "OverlayListPopup", link: "/zh_CN/components/overlaylistpopup" },
                        { text: "OverlayDropdownPreference", link: "/zh_CN/components/overlaydropdownpreference" },
                        { text: "OverlaySpinnerPreference", link: "/zh_CN/components/overlayspinnerpreference" },
                        { text: "OverlayBottomSheet", link: "/zh_CN/components/overlaybottomsheet" },
                        { text: "OverlayDialog", link: "/zh_CN/components/overlaydialog" },
                        { text: "WindowListPopup", link: "/zh_CN/components/windowlistpopup" },
                        { text: "WindowDropdownPreference", link: "/zh_CN/components/windowdropdownpreference" },
                        { text: "WindowSpinnerPreference", link: "/zh_CN/components/windowspinnerpreference" },
                        { text: "WindowBottomSheet", link: "/zh_CN/components/windowbottomsheet" },
                        { text: "WindowDialog", link: "/zh_CN/components/windowdialog" },
                    ],
                },
            ],
        },
    },
});
