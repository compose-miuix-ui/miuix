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
| [GlassDropdownPreference](./glassdropdownpreference) | 内置玻璃下拉弹窗的设置行 | 设置选项选择 |
| [GlassDialog](./glassdialog) | 遮罩之上的玻璃对话框 | 提示与确认 |
| [GlassDialogButton](./glassdialog#glassdialogbutton) | 共享对话框材质的操作按钮 | 对话框操作 |
| [GlassBottomSheet](./glassbottomsheet) | 贴底玻璃抽屉 | 补充内容与操作 |

## 配置

依赖与背景采样配置见[玻璃材质](/zh_CN/guide/glass#配置)。可选的 `miuix-glass-icons` 模块通过 `MiuixIcons.Glass` 提供图标。

## 使用要求

- 按钮、顶栏、底栏与标签栏可直接用于页面布局。
- 弹窗菜单需要整页 `BoxScope`，锚点与弹窗须使用相同坐标系。
- `GlassDialog`、`GlassBottomSheet` 与 `GlassDropdownPreference` 需要 Miuix [Scaffold](../basic/scaffold)。

背景采样不能包含玻璃表面自身，见[背景采样](/zh_CN/guide/glass#background-sampling)。

## 相关文档

- [玻璃材质](/zh_CN/guide/glass)：材质配置与背景采样。
- [图标系统](/zh_CN/guide/icons)：Glass 图标的引入与字重。
