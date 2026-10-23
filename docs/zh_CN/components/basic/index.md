# 基础组件

`miuix-ui` 提供应用布局、导航、基础控件与 Overlay/Window 浮层。

## 组件

| 组件 | 描述 | 常见用途 |
| --- | --- | --- |
| [Scaffold](./scaffold) | 应用的基础布局 | 页面结构、内容展示 |
| [Surface](./surface)                           | 基础容器组件           | 内容展示、背景容器   |
| [TopAppBar](./topappbar)                       | 应用顶部的导航栏       | 页面标题、主要操作   |
| [NavigationBar](./navigationbar)               | 底部导航组件           | 主要页面切换         |
| [NavigationRail](./navigationrail)             | 侧边导航组件           | 主要页面切换（大屏）  |
| [TabRow](./tabrow)                             | 水平标签页切换栏       | 内容分类浏览         |
| [BreadcrumbBar](./breadcrumbbar)               | 水平面包屑导航栏       | 文件路径导航、位置轨迹 |
| [Card](./card)                                 | 包含相关信息的容器     | 信息展示、内容分组   |
| [BasicComponent](./basiccomponent)             | 通用基础组件           | 自定义组件开发       |
| [Button](./button)                             | 触发操作的交互元素     | 表单提交、操作确认   |
| [IconButton](./iconbutton)                     | 图标按钮组件           | 辅助操作、工具栏     |
| [Text](./text)                                 | 展示各种样式的文字内容 | 标题、正文、描述文本 |
| [SmallTitle](./smalltitle)                     | 小型标题组件           | 辅助标题、分类标识   |
| [TextField](./textfield)                       | 接收用户文本输入       | 表单填写、搜索框     |
| [Switch](./switch)                             | 双态切换控件           | 设置项开关、功能启用 |
| [Checkbox](./checkbox)                         | 多选控件               | 多项选择、条款同意   |
| [RadioButton](./radiobutton)                   | 单选控件               | 单项选择、选项切换   |
| [Slider](./slider)                             | 调节值的滑动控件       | 音量调节、范围选择   |
| [NumberPicker](./numberpicker)                 | 垂直滚动数字选择器     | 时间选择、数量选择   |
| [ProgressIndicator](./progressindicator)       | 展示操作进度状态       | 加载中、进度展示     |
| [Snackbar](./snackbar)                         | 底部临时消息条组件     | 状态提示、操作结果   |
| [Tooltip](./tooltip)                           | 悬停或长按显示的简短标签 | 图标按钮标签、元素提示 |
| [Badge](./badge)                               | 锚点上的小型状态叠加     | 未读数、状态圆点       |
| [Icon](./icon)                                 | 图标展示组件           | 图标按钮、状态指示   |
| [FloatingActionButton](./floatingactionbutton) | 悬浮操作按钮           | 主要操作、快捷功能   |
| [FloatingToolbar](./floatingtoolbar)           | 悬浮工具栏             | 快捷操作、信息展示   |
| [Divider](./divider)                           | 内容分隔线             | 区块分隔、层次划分   |
| [PullToRefresh](./pulltorefresh)               | 下拉触发刷新操作       | 数据更新、页面刷新   |
| [SearchBar](./searchbar)                       | 执行搜索的输入框       | 内容搜索、快速查找   |
| [ColorPalette](./colorpalette)                 | 网格调色盘（含透明度） | 主题设置、颜色选择   |
| [ColorPicker](./colorpicker)                   | 选择颜色的控件         | 主题设置、颜色选择   |
| [OverlayListPopup](./overlaylistpopup)             | 基于 BasicComponent 的列表弹窗组件（使用 MiuixPopupUtils，需在 `Scaffold` 中使用） | 选项选择、功能列表     |
| [OverlayCascadingListPopup](./overlaycascadinglistpopup) | 二级级联列表弹窗组件（使用 MiuixPopupUtils，需在 `Scaffold` 中使用）               | 子菜单、分组动作面板   |
| [OverlayBottomSheet](./overlaybottomsheet)         | 基于 BasicComponent 的底部抽屉组件（使用 MiuixPopupUtils，需在 `Scaffold` 中使用） | 底部抽屉、附加选项     |
| [OverlayDialog](./overlaydialog)                   | 基于 BasicComponent 的对话弹窗组件（使用 MiuixPopupUtils，需在 `Scaffold` 中使用） | 提示、确认操作         |
| [WindowListPopup](./windowlistpopup)     | 窗口级列表弹窗组件                                                                 | 选项选择、功能列表     |
| [WindowCascadingListPopup](./windowcascadinglistpopup) | 窗口级二级级联列表弹窗组件                                                         | 子菜单、分组动作面板   |
| [WindowBottomSheet](./windowbottomsheet) | 窗口级底部抽屉组件                                                                 | 底部抽屉、附加选项     |
| [WindowDialog](./windowdialog)           | 窗口级对话框组件                                                                   | 提示、确认操作         |

## 配置

在项目中添加 `miuix-ui` 依赖：

```kotlin
kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation("top.yukonga.miuix.kmp:miuix-ui:<version>")
        }
    }
}
```

Android 项目：

```kotlin
dependencies {
    implementation("top.yukonga.miuix.kmp:miuix-ui-android:<version>")
}
```

完整项目配置与平台依赖见[快速开始](/zh_CN/guide/getting-started)。

## 组件组织与宿主

- `top.yukonga.miuix.kmp.basic`：脚手架、导航、布局与交互控件。
- `top.yukonga.miuix.kmp.overlay`：在当前 Compose 页面内托管的浮层，必须位于 Miuix [Scaffold](./scaffold) 中，由其提供 `MiuixPopupHost`。
- `top.yukonga.miuix.kmp.window`：窗口级浮层，不依赖 `Scaffold` 弹窗宿主。

选择 Overlay 或 Window 时，应依据宿主与层级需求，而不是把两者当作不同模块。各组件页列出了具体参数、默认值和平台约束。

## 相关文档

- [主题系统](/zh_CN/guide/theme)：颜色、文字与主题配置。
