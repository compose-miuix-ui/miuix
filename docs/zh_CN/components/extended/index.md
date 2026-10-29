# 扩展组件

`miuix-preference` 在 `miuix-ui` 之上提供设置项与下拉菜单。

## 组件

| 组件 | 描述 | 常见用途 |
| --- | --- | --- |
| [ArrowPreference](./arrowpreference)               | 基于 BasicComponent 的带箭头组件                                                   | 指示可点击、导航提示   |
| [SwitchPreference](./switchpreference)             | 基于 BasicComponent 的开关组件                                                     | 设置项开关、功能启用   |
| [CheckboxPreference](./checkboxpreference)         | 基于 BasicComponent 的复选框组件                                                   | 多项选择、条款同意     |
| [RadioButtonPreference](./radiobuttonpreference)   | 基于 BasicComponent 的单选组件                                                     | 单项选择、选项切换     |
| [SliderPreference](./sliderpreference)             | 基于 BasicComponent 的滑块组件                                                     | 数值调节、音量/亮度    |
| [RangeSliderPreference](./rangesliderpreference)   | 基于 BasicComponent 的范围滑块组件                                                 | 范围选择、价格筛选     |
| [OverlayDropdownPreference](./overlaydropdownpreference) | 基于 BasicComponent 的下拉选择器组件（使用 MiuixPopupUtils，需在 `Scaffold` 中使用） | 选项选择、功能列表     |
| [OverlaySpinnerPreference](./overlayspinnerpreference) | 基于 BasicComponent 的高级选择器组件（使用 MiuixPopupUtils，需在 `Scaffold` 中使用） | 进阶选项选择、功能列表 |
| [OverlayDropdownMenu](./overlaydropdownmenu)       | 基于 BasicComponent 的动作菜单组件（使用 MiuixPopupUtils，需在 `Scaffold` 中使用） | 动作菜单、多选场景     |
| [OverlayIconDropdownMenu](./overlayicondropdownmenu) | 基于 IconButton 的动作菜单组件（使用 MiuixPopupUtils，需在 `Scaffold` 中使用）     | 工具栏动作、更多菜单   |
| [OverlayIconCascadingDropdownMenu](./overlayiconcascadingdropdownmenu) | 基于 IconButton 的二级级联动作菜单组件（使用 MiuixPopupUtils，需在 `Scaffold` 中使用） | 含子菜单的工具栏动作   |
| [WindowDropdownPreference](./windowdropdownpreference) | 窗口级下拉选择器组件                                                               | 选项选择、功能列表     |
| [WindowSpinnerPreference](./windowspinnerpreference) | 窗口级高级选择器组件                                                               | 进阶选项选择、功能列表 |
| [WindowDropdownMenu](./windowdropdownmenu) | 窗口级动作菜单组件                                                                 | 动作菜单、多选场景     |
| [WindowIconDropdownMenu](./windowicondropdownmenu) | 基于 IconButton 的窗口级动作菜单组件                                               | 工具栏动作、更多菜单   |
| [WindowIconCascadingDropdownMenu](./windowiconcascadingdropdownmenu) | 基于 IconButton 的窗口级二级级联动作菜单组件                                       | 含子菜单的工具栏动作   |

## 配置

在项目中添加 `miuix-preference` 依赖：

```kotlin
kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation("top.yukonga.miuix.kmp:miuix-preference:<version>")
        }
    }
}
```

Android 项目：

```kotlin
dependencies {
    implementation("top.yukonga.miuix.kmp:miuix-preference-android:<version>")
}
```

`miuix-preference` 会传递引入 `miuix-ui`。

完整项目配置与平台依赖见[快速开始](/zh_CN/guide/getting-started)。

## 组件选择与宿主

- `top.yukonga.miuix.kmp.preference` 中的组件用于设置行。`DropdownPreference` 适合单选值，`SpinnerPreference` 提供更复杂的选项结构。
- `top.yukonga.miuix.kmp.menu` 中的组件用于动作菜单；菜单项可以分别管理选中状态，不要求共享同一个单选索引。
- `Overlay*` 变体必须位于 Miuix [Scaffold](../basic/scaffold) 中，由其提供 `MiuixPopupHost`；`Window*` 变体在窗口层显示，不依赖该宿主。

设置行使用 [BasicComponent](../basic/basiccomponent) 作为基础。

## 相关文档

- [主题系统](/zh_CN/guide/theme)：颜色、文字与主题配置。
