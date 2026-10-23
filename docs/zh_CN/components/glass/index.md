# Glass 组件

`miuix-glass` 提供玻璃按钮、导航栏、标签栏、菜单、对话框与底部抽屉。

## 组件

| 组件 | 描述 | 常见用途 |
| --- | --- | --- |
| [GlassIconButton](./glassiconbutton) | 圆形玻璃图标按钮 | 工具栏与悬浮操作 |
| [GlassTopAppBar](./glasstopappbar) | 可折叠玻璃顶栏 | 页面标题与操作 |
| [GlassNavigationBar](./glassnavigationbar) | 悬浮玻璃导航栏 | 主要页面切换 |
| [GlassTabRow](./glasstabrow) | 独立胶囊玻璃标签栏 | 内容分类 |
| [GlassSegmentedTabRow](./glasstabrow#glasssegmentedtabrow) | 相连分段玻璃标签栏 | 互斥视图切换 |
| [GlassTransformPopup](./glasstransformpopup) | 从按钮变换展开的菜单 | 工具栏更多操作 |
| [GlassPopup](./glasspopup) | 锚定式玻璃菜单 | 操作选择 |
| [GlassSecondaryPopup](./glasssecondarypopup) | 一级菜单旁的二级菜单 | 级联菜单 |
| [GlassDropdownPopup](./glassdropdownpopup) | 从设置行展开的玻璃下拉弹窗 | 选项选择 |
| [GlassPopupItem](./glasspopupitem) | 可带图标与选中标记的菜单项 | 自定义弹窗内容 |
| [GlassDropdownPreference](./glassdropdownpreference) | 内置玻璃下拉弹窗的设置行 | 设置选项选择 |
| [GlassDialog](./glassdialog) | 遮罩之上的玻璃对话框 | 提示与确认 |
| [GlassDialogButton](./glassdialog#glassdialogbutton) | 共享对话框材质的操作按钮 | 对话框操作 |
| [GlassBottomSheet](./glassbottomsheet) | 贴底玻璃抽屉 | 补充内容与操作 |

## 配置

在项目中添加 `miuix-glass` 依赖：

```kotlin
kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation("top.yukonga.miuix.kmp:miuix-glass:<version>")
            // 可选：Glass 图标集
            implementation("top.yukonga.miuix.kmp:miuix-glass-icons:<version>")
        }
    }
}
```

Android 项目：

```kotlin
dependencies {
    implementation("top.yukonga.miuix.kmp:miuix-glass-android:<version>")
    // 可选：Glass 图标集
    implementation("top.yukonga.miuix.kmp:miuix-glass-icons-android:<version>")
}
```

`miuix-glass` 会传递引入 `miuix-blur` 与 `miuix-ui`。Glass 图标集由可选的 `miuix-glass-icons` 提供。

完整项目配置与平台依赖见[快速开始](/zh_CN/guide/getting-started)。

## 相关文档

- [玻璃材质](/zh_CN/guide/glass)：材质配置与背景采样。
- [图标系统](/zh_CN/guide/icons)：Glass 图标的引入与字重。

