# 组件库

Miuix 提供了丰富的 UI 组件，严格遵循 Xiaomi HyperOS 设计规范。每个组件都经过精心设计，确保视觉与交互效果与小米原生体验一致。

## 基础组件

`miuix-ui` 提供的组件，包括脚手架、基础控件与 Overlay/Window 浮层。配置与宿主要求见[基础组件总览](./basic/)。

| 组件                                                       | 描述                   | 常见用途             |
| ---------------------------------------------------------- | ---------------------- | -------------------- |
| [Scaffold](./basic/scaffold) | 应用的基础布局 | 页面结构、内容展示 |
| [Surface](./basic/surface)                           | 基础容器组件           | 内容展示、背景容器   |
| [TopAppBar](./basic/topappbar)                       | 应用顶部的导航栏       | 页面标题、主要操作   |
| [NavigationBar](./basic/navigationbar)               | 底部导航组件           | 主要页面切换         |
| [NavigationRail](./basic/navigationrail)             | 侧边导航组件           | 主要页面切换（大屏）  |
| [TabRow](./basic/tabrow)                             | 水平标签页切换栏       | 内容分类浏览         |
| [BreadcrumbBar](./basic/breadcrumbbar)               | 水平面包屑导航栏       | 文件路径导航、位置轨迹 |
| [Card](./basic/card)                                 | 包含相关信息的容器     | 信息展示、内容分组   |
| [BasicComponent](./basic/basiccomponent)             | 通用基础组件           | 自定义组件开发       |
| [Button](./basic/button)                             | 触发操作的交互元素     | 表单提交、操作确认   |
| [IconButton](./basic/iconbutton)                     | 图标按钮组件           | 辅助操作、工具栏     |
| [Text](./basic/text)                                 | 展示各种样式的文字内容 | 标题、正文、描述文本 |
| [SmallTitle](./basic/smalltitle)                     | 小型标题组件           | 辅助标题、分类标识   |
| [TextField](./basic/textfield)                       | 接收用户文本输入       | 表单填写、搜索框     |
| [Switch](./basic/switch)                             | 双态切换控件           | 设置项开关、功能启用 |
| [Checkbox](./basic/checkbox)                         | 多选控件               | 多项选择、条款同意   |
| [RadioButton](./basic/radiobutton)                   | 单选控件               | 单项选择、选项切换   |
| [Slider](./basic/slider)                             | 调节值的滑动控件       | 音量调节、范围选择   |
| [NumberPicker](./basic/numberpicker)                 | 垂直滚动数字选择器     | 时间选择、数量选择   |
| [ProgressIndicator](./basic/progressindicator)       | 展示操作进度状态       | 加载中、进度展示     |
| [Snackbar](./basic/snackbar)                         | 底部临时消息条组件     | 状态提示、操作结果   |
| [Tooltip](./basic/tooltip)                           | 悬停或长按显示的简短标签 | 图标按钮标签、元素提示 |
| [Badge](./basic/badge)                               | 锚点上的小型状态叠加     | 未读数、状态圆点       |
| [Icon](./basic/icon)                                 | 图标展示组件           | 图标按钮、状态指示   |
| [FloatingActionButton](./basic/floatingactionbutton) | 悬浮操作按钮           | 主要操作、快捷功能   |
| [FloatingToolbar](./basic/floatingtoolbar)           | 悬浮工具栏             | 快捷操作、信息展示   |
| [Divider](./basic/divider)                           | 内容分隔线             | 区块分隔、层次划分   |
| [PullToRefresh](./basic/pulltorefresh)               | 下拉触发刷新操作       | 数据更新、页面刷新   |
| [SearchBar](./basic/searchbar)                       | 执行搜索的输入框       | 内容搜索、快速查找   |
| [ColorPalette](./basic/colorpalette)                 | 网格调色盘（含透明度） | 主题设置、颜色选择   |
| [ColorPicker](./basic/colorpicker)                   | 选择颜色的控件         | 主题设置、颜色选择   |
| [OverlayListPopup](./basic/overlaylistpopup)             | 基于 BasicComponent 的列表弹窗组件（使用 MiuixPopupUtils，需在 `Scaffold` 中使用） | 选项选择、功能列表     |
| [OverlayCascadingListPopup](./basic/overlaycascadinglistpopup) | 二级级联列表弹窗组件（使用 MiuixPopupUtils，需在 `Scaffold` 中使用）               | 子菜单、分组动作面板   |
| [OverlayBottomSheet](./basic/overlaybottomsheet)         | 基于 BasicComponent 的底部抽屉组件（使用 MiuixPopupUtils，需在 `Scaffold` 中使用） | 底部抽屉、附加选项     |
| [OverlayDialog](./basic/overlaydialog)                   | 基于 BasicComponent 的对话弹窗组件（使用 MiuixPopupUtils，需在 `Scaffold` 中使用） | 提示、确认操作         |
| [WindowListPopup](./basic/windowlistpopup)     | 窗口级列表弹窗组件                                                                 | 选项选择、功能列表     |
| [WindowCascadingListPopup](./basic/windowcascadinglistpopup) | 窗口级二级级联列表弹窗组件                                                         | 子菜单、分组动作面板   |
| [WindowBottomSheet](./basic/windowbottomsheet) | 窗口级底部抽屉组件                                                                 | 底部抽屉、附加选项     |
| [WindowDialog](./basic/windowdialog)           | 窗口级对话框组件                                                                   | 提示、确认操作         |

::: warning 注意
Scaffold 组件为跨平台提供了一个合适的弹出窗口的容器。`OverlayDialog`、`OverlayDropdownPreference`、`OverlaySpinnerPreference`、`OverlayListPopup` 等组件都基于此实现弹出窗口，因此都需要被该组件包裹。
:::

## 扩展组件

`miuix-preference` 提供的设置项与下拉菜单组件。配置与组件选择见[扩展组件总览](./extended/)。

| 组件                                                 | 描述                                                                               | 常见用途               |
| ---------------------------------------------------- | ---------------------------------------------------------------------------------- | ---------------------- |
| [ArrowPreference](./extended/arrowpreference)               | 基于 BasicComponent 的带箭头组件                                                   | 指示可点击、导航提示   |
| [SwitchPreference](./extended/switchpreference)             | 基于 BasicComponent 的开关组件                                                     | 设置项开关、功能启用   |
| [CheckboxPreference](./extended/checkboxpreference)         | 基于 BasicComponent 的复选框组件                                                   | 多项选择、条款同意     |
| [RadioButtonPreference](./extended/radiobuttonpreference)   | 基于 BasicComponent 的单选组件                                                     | 单项选择、选项切换     |
| [SliderPreference](./extended/sliderpreference)             | 基于 BasicComponent 的滑块组件                                                     | 数值调节、音量/亮度    |
| [RangeSliderPreference](./extended/rangesliderpreference)   | 基于 BasicComponent 的范围滑块组件                                                 | 范围选择、价格筛选     |
| [OverlayDropdownPreference](./extended/overlaydropdownpreference) | 基于 BasicComponent 的下拉选择器组件（使用 MiuixPopupUtils，需在 `Scaffold` 中使用） | 选项选择、功能列表     |
| [OverlaySpinnerPreference](./extended/overlayspinnerpreference) | 基于 BasicComponent 的高级选择器组件（使用 MiuixPopupUtils，需在 `Scaffold` 中使用） | 进阶选项选择、功能列表 |
| [OverlayDropdownMenu](./extended/overlaydropdownmenu)       | 基于 BasicComponent 的动作菜单组件（使用 MiuixPopupUtils，需在 `Scaffold` 中使用） | 动作菜单、多选场景     |
| [OverlayIconDropdownMenu](./extended/overlayicondropdownmenu) | 基于 IconButton 的动作菜单组件（使用 MiuixPopupUtils，需在 `Scaffold` 中使用）     | 工具栏动作、更多菜单   |
| [OverlayIconCascadingDropdownMenu](./extended/overlayiconcascadingdropdownmenu) | 基于 IconButton 的二级级联动作菜单组件（使用 MiuixPopupUtils，需在 `Scaffold` 中使用） | 含子菜单的工具栏动作   |
| [WindowDropdownPreference](./extended/windowdropdownpreference) | 窗口级下拉选择器组件                                                               | 选项选择、功能列表     |
| [WindowSpinnerPreference](./extended/windowspinnerpreference) | 窗口级高级选择器组件                                                               | 进阶选项选择、功能列表 |
| [WindowDropdownMenu](./extended/windowdropdownmenu) | 窗口级动作菜单组件                                                                 | 动作菜单、多选场景     |
| [WindowIconDropdownMenu](./extended/windowicondropdownmenu) | 基于 IconButton 的窗口级动作菜单组件                                               | 工具栏动作、更多菜单   |
| [WindowIconCascadingDropdownMenu](./extended/windowiconcascadingdropdownmenu) | 基于 IconButton 的窗口级二级级联动作菜单组件                                       | 含子菜单的工具栏动作   |

## Glass 组件

`miuix-glass` 提供的独立玻璃组件。配置、平台要求与背景采样见 [Glass 总览](./glass/)。

| 组件 | 描述 | 常见用途 |
| --- | --- | --- |
| [GlassIconButton](./glass/glassiconbutton) | 圆形玻璃图标按钮 | 工具栏与悬浮操作 |
| [GlassTopAppBar](./glass/glasstopappbar) | 可折叠玻璃顶栏 | 页面标题与操作 |
| [GlassNavigationBar](./glass/glassnavigationbar) | 悬浮玻璃导航栏 | 主要页面切换 |
| [GlassTabRow](./glass/glasstabrow) | 独立胶囊玻璃标签栏 | 内容分类 |
| [GlassSegmentedTabRow](./glass/glasstabrow#glasssegmentedtabrow) | 相连分段玻璃标签栏 | 互斥视图切换 |
| [GlassTransformPopup](./glass/glasstransformpopup) | 从按钮变换展开的菜单 | 工具栏更多操作 |
| [GlassPopup](./glass/glasspopup) | 锚定式玻璃菜单 | 操作选择 |
| [GlassSecondaryPopup](./glass/glasssecondarypopup) | 一级菜单旁的二级菜单 | 级联菜单 |
| [GlassDropdownPopup](./glass/glassdropdownpopup) | 从设置行展开的玻璃下拉弹窗 | 选项选择 |
| [GlassPopupItem](./glass/glasspopup#glasspopupitem) | 菜单行组件（可选图标、摘要、勾选及子菜单） | 弹窗菜单项 |
| [GlassDropdownPreference](./glass/glassdropdownpreference) | 内置玻璃下拉弹窗的设置行 | 设置选项选择 |
| [GlassDialog](./glass/glassdialog) | 遮罩之上的玻璃对话框 | 提示与确认 |
| [GlassDialogButton](./glass/glassdialog#glassdialogbutton) | 共享对话框材质的操作按钮 | 对话框操作 |
| [GlassBottomSheet](./glass/glassbottomsheet) | 贴底玻璃抽屉 | 补充内容与操作 |
