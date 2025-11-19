# 迁移指南：从 0.6.1 到 0.7.0

## 概述

版本 0.7.0 代表了 Miuix 库的完全重写。这个主要版本在架构、性能和 API 设计方面带来了重大改进，但也引入了需要迁移工作的破坏性更改。

::: warning 重要提示
这是一个重大破坏性版本。升级前请仔细阅读本指南。
:::

## 0.7.0 新特性

### 亮点

- **库的完全重写**：整个代码库已重构，以获得更好的可维护性和性能
- **增强的组件架构**：所有组件都经过重新设计，具有改进的组合性和模块化
- **更好的类型安全**：更严格的类型定义和空安全改进
- **性能优化**：减少重组并改进渲染性能
- **扩展的平台支持**：在所有 Compose Multiplatform 目标平台上提供更好的支持
- **改进的文档**：为所有组件提供包含示例的全面文档

### 新增组件

版本 0.7.0 引入了之前版本中的所有组件，并具有改进的实现：

- **基础组件**：Button、Card、Checkbox、Divider、Icon、IconButton、SmallTitle、Surface、Switch、Text、TextField
- **布局组件**：Scaffold、TopAppBar、NavigationBar、TabRow
- **交互组件**：Slider、ProgressIndicator、FloatingActionButton、FloatingToolbar
- **高级组件**：PullToRefresh、SearchBar、ColorPalette、ColorPicker、ListPopup
- **扩展组件**：SuperArrow、SuperSwitch、SuperCheckbox、SuperDropdown、SuperSpinner、SuperDialog、SuperBottomSheet

### 架构改进

1. **模块化包结构**：更好的包层次结构组织
2. **一致的 API 设计**：跨组件的统一参数命名和排序
3. **增强的主题系统**：更灵活和强大的主题功能
4. **改进的工具函数**：用于常见任务的更好的实用函数

## 破坏性更改

### 包结构变更

整个包结构已重新组织。您需要更新所有导入语句。

**之前（0.6.1）：**
```kotlin
// 导入语句各不相同且组织较少
```

**之后（0.7.0）：**
```kotlin
// 基础组件
import top.yukonga.miuix.kmp.basic.*

// 扩展组件
import top.yukonga.miuix.kmp.extra.*

// 主题
import top.yukonga.miuix.kmp.theme.*

// 工具
import top.yukonga.miuix.kmp.utils.*

// 图标
import top.yukonga.miuix.kmp.icon.icons.useful.*
import top.yukonga.miuix.kmp.icon.icons.basic.*
```

### 组件 API 变更

由于完全重写，所有组件 API 都已重新设计。一般模式保持相似，但具体参数和行为可能不同。

### 主题系统变更

主题系统已完全重新设计：

**之前（0.6.1）：**
```kotlin
// 旧的主题设置
```

**之后（0.7.0）：**
```kotlin
@Composable
fun App() {
    val controller = remember { ThemeController(ColorSchemeMode.System) }
    
    MiuixTheme(controller = controller) {
        // 您的内容
    }
}
```

可用的颜色方案模式：
- `ColorSchemeMode.System` - 跟随系统
- `ColorSchemeMode.Light` - 浅色模式
- `ColorSchemeMode.Dark` - 深色模式
- `ColorSchemeMode.MonetSystem` - Monet 跟随系统
- `ColorSchemeMode.MonetLight` - Monet 浅色
- `ColorSchemeMode.MonetDark` - Monet 深色

### 图标系统变更

图标已重新组织为更清晰的类别：

- `MiuixIcons.Basic.*` - 基础图标（箭头、勾选、搜索）
- `MiuixIcons.Useful.*` - 实用操作图标（返回、确认、删除、编辑等）
- `MiuixIcons.Other.*` - 其他图标（GitHub 等）

## 迁移步骤

### 步骤 1：更新依赖

更新您的 `build.gradle.kts` 以使用版本 0.7.0：

```kotlin
kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation("top.yukonga.miuix.kmp:miuix:0.7.0")
        }
    }
}
```

### 步骤 2：更新导入

由于完全重写，您需要更新所有导入语句。使用 IDE 的自动导入功能来帮助完成此过程。

### 步骤 3：更新主题设置

用新的 `MiuixTheme` 和 `ThemeController` 替换现有的主题设置：

```kotlin
@Composable
fun App() {
    val controller = remember { ThemeController(ColorSchemeMode.System) }
    
    MiuixTheme(controller = controller) {
        Scaffold(
            topBar = { /* TopBar 内容 */ },
            bottomBar = { /* BottomBar 内容 */ },
        ) { paddingValues ->
            // 您的应用内容
        }
    }
}
```

### 步骤 4：更新组件使用

查看每个组件的文档并相应地更新您的代码：

- [Scaffold](/zh_CN/components/scaffold)
- [TopAppBar](/zh_CN/components/topappbar)
- [NavigationBar](/zh_CN/components/navigationbar)
- [Button](/zh_CN/components/button)
- [Card](/zh_CN/components/card)
- 以及所有其他组件...

### 步骤 5：全面测试

迁移后：

1. **构建您的项目**以识别编译错误
2. **在所有目标平台上运行您的应用**
3. **测试所有功能**以确保正确行为
4. **验证视觉外观**符合预期

## 常见迁移问题

### 问题：导入错误

**问题**：先前的导入不再有效。

**解决方案**：更新到新的包结构。使用 IDE 自动导入或参考组件文档。

### 问题：组件 API 更改

**问题**：组件参数或行为不同。

**解决方案**：查看特定组件文档以了解新 API。

### 问题：主题未应用

**问题**：颜色或样式未正确应用。

**解决方案**：确保您已使用 `MiuixTheme` 包装内容并创建了 `ThemeController`。

## 获取帮助

如果在迁移过程中遇到问题：

1. 查看[组件文档](/zh_CN/components/)
2. 查阅[最佳实践](/zh_CN/guide/best-practices)
3. 在 [GitHub](https://github.com/compose-miuix-ui/miuix/issues) 上提交问题
4. 参与社区讨论

## 结论

虽然版本 0.7.0 引入了破坏性更改，但它为未来的开发提供了坚实的基础，具有更好的架构、性能和可维护性。迁移工作对于长期利益是值得的。

感谢您使用 Miuix！我们感谢您在这次重大转变期间的耐心。
