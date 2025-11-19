# 更新日志：版本 0.7.0

## 发布日期：2025 年 11 月 19 日

::: info 版本 0.7.0
这是一个包含破坏性更改的主要版本。请参阅[迁移指南](/zh_CN/guide/migration-0.7.0)获取升级说明。
:::

## 概述

版本 0.7.0 代表了 Miuix 库的完全重写，在架构、性能和开发者体验方面带来了重大改进。此版本为未来的发展奠定了坚实的基础。

## 主要变更

### 库的完全重写

整个代码库已从头开始重建，具有：

- **现代架构**：改进的代码组织和模块化
- **更好的性能**：优化的渲染和减少的重组
- **增强的类型安全**：更严格的类型定义和空安全
- **一致的 API**：跨组件的统一参数命名和行为
- **全面的文档**：所有组件的完整文档及示例

### 所有组件重建

每个组件都经过改进重新实现：

#### 基础组件
- **Button** - 使用更好的状态管理和动画重建
- **Card** - 增强的立体效果和交互处理
- **Checkbox** - 精致的动画和辅助功能
- **Divider** - 水平和垂直分隔符，具有更好的样式
- **Icon** - 改进的图标渲染和大小调整
- **IconButton** - 增强的触摸目标和反馈
- **SmallTitle** - 一致的排版和间距
- **Surface** - 更好的背景和立体效果处理
- **Switch** - 更流畅的动画和交互
- **Text** - 增强的文本渲染，具有更好的样式继承
- **TextField** - 改进的输入处理和验证支持

#### 布局组件
- **Scaffold** - 完全重写，具有更好的布局管理
- **TopAppBar** - 增强的滚动行为和自定义
- **NavigationBar** - 改进的项目管理和动画
- **TabRow** - 更好的标签切换和指示器动画

#### 交互组件
- **Slider** - 增强的更流畅的拖动和触觉反馈
- **ProgressIndicator** - 线性、圆形和无限进度变体
- **FloatingActionButton** - 改进的立体效果和动画
- **FloatingToolbar** - 用于浮动操作工具栏的新组件

#### 高级组件
- **PullToRefresh** - 平滑的下拉刷新，具有可自定义的指示器
- **SearchBar** - 增强的搜索功能与动画
- **ColorPalette** - 交互式颜色选择调色板
- **ColorPicker** - 具有多个色彩空间的高级颜色选择器
- **ListPopup** - 改进的弹出位置和动画

#### 扩展组件
- **SuperArrow** - 用于设置的增强箭头组件
- **SuperSwitch** - 具有附加功能的扩展开关
- **SuperCheckbox** - 具有自定义内容的高级复选框
- **SuperDropdown** - 改进的下拉菜单，具有更好的项目管理
- **SuperSpinner** - 增强的旋转器，具有可自定义选项
- **SuperDialog** - 灵活的对话框系统与动画
- **SuperBottomSheet** - 具有拖动支持的模态底部表单

### 主题系统改造

主题系统的完全重新设计：

- **ThemeController**：新的集中式主题管理
- **多种颜色方案**：支持浅色、深色和 Monet 变体
- **动态颜色**：跨平台更好的动态颜色支持
- **Material 3 映射**：改进的 Material 3 颜色方案映射
- **自定义样式**：增强的自定义功能

### 图标系统重组

图标已重新组织为逻辑类别：

- **基础图标**：基本图标（箭头、勾选、搜索）
- **实用图标**：操作图标（返回、确认、删除、编辑、保存等）
- **其他图标**：第三方服务图标（GitHub 等）

### 工具函数改进

增强的实用函数和修饰符：

- **MiuixIndication**：交互的自定义指示效果
- **Pressable**：改进的可按压修饰符与反馈
- **PressFeedback**：增强的按压反馈系统
- **Overscroll**：自定义过度滚动效果
- **ScrollEndHaptic**：滚动结束的触觉反馈
- **MiuixPopupUtils**：弹出窗口管理工具

### 平台支持

改进了对所有平台的支持：

- **Android**：完全支持平台特定优化
- **iOS**：增强的 iOS 支持，具有原生体验
- **Desktop (JVM)**：更好的桌面集成
- **WasmJS**：WebAssembly JavaScript 支持
- **JavaScript**：浏览器 JavaScript 支持
- **macOS Native**：原生 macOS 支持

## 破坏性更改

::: danger 破坏性更改
由于完全重写，此版本包含许多破坏性更改。需要进行迁移。
:::

### 包结构

- 所有包已重新组织
- 需要更新导入语句
- 新的包层次结构：`top.yukonga.miuix.kmp.{basic,extra,theme,utils,icon}`

### 组件 API

- 组件参数名称和排序可能已更改
- 某些组件具有不同的默认值
- 滚动和动画组件的行为变化

### 主题系统

- 完全替换旧的主题系统
- 新的 `ThemeController` 和 `MiuixTheme` API
- 不同的颜色方案模式

### 图标路径

- 图标移至新包
- `MiuixIcons.Basic.*` 用于基础图标
- `MiuixIcons.Useful.*` 用于操作图标
- `MiuixIcons.Other.*` 用于第三方图标

## 迁移指南

请参阅全面的[迁移指南](/zh_CN/guide/migration-0.7.0)以获取从版本 0.6.1 升级到 0.7.0 的详细说明。

## 文档

现在提供完整的文档：

- **组件文档**：每个组件的完整文档
- **代码示例**：所有功能的工作示例
- **API 参考**：详细的 API 文档
- **最佳实践**：有效使用 Miuix 的指南

## 致谢

感谢所有在此主要版本开发期间提供反馈的贡献者和用户。特别感谢：

- [@YuKongA](https://github.com/YuKongA) - 首席开发者和架构
- [@HowieHChen](https://github.com/HowieHChen) - 核心贡献者
- [@Voemp](https://github.com/Voemp) - 组件开发
- 所有测试并提供反馈的社区成员

## 开始使用

要开始使用版本 0.7.0：

1. 将依赖更新为 `top.yukonga.miuix.kmp:miuix:0.7.0`
2. 遵循[迁移指南](/zh_CN/guide/migration-0.7.0)
3. 探索[组件文档](/zh_CN/components/)
4. 查看[最佳实践](/zh_CN/guide/best-practices)

## 支持

如果您遇到任何问题：

- 查看[文档](/zh_CN/guide/getting-started)
- 查阅[迁移指南](/zh_CN/guide/migration-0.7.0)
- 搜索[现有问题](https://github.com/compose-miuix-ui/miuix/issues)
- 如需要，创建[新问题](https://github.com/compose-miuix-ui/miuix/issues/new)

## 下一步

未来版本将专注于：

- 基于社区反馈的其他组件
- 性能优化
- 增强的辅助功能
- 更多平台特定优化
- 扩展的文档和示例

---

**完整更新日志**：[v0.6.1...v0.7.0](https://github.com/compose-miuix-ui/miuix/compare/v0.6.1...v0.7.0)
