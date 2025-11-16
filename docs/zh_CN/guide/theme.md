# 主题系统

Miuix 提供了一套完整的主题系统，使您能够轻松地在整个应用中保持一致的设计风格。整个主题系统由颜色方案和文本样式组成。

## 使用主题

使用 `ThemeController` 控制配色模式，然后用 `MiuixTheme` 包裹内容：

```kotlin
@Composable
fun App() {
    val controller = remember { ThemeController(ColorSchemeMode.System) }

    // 可用模式：
    // ColorSchemeMode.System、Light、Dark、DynamicSystem、DynamicLight、DynamicDark
    MiuixTheme(controller = controller) {
        Scaffold(
            topBar = { /* ... */ },
        ) { padding ->
            // 主体内容
        }
    }
}
```

使用 `ColorSchemeMode.System` 时会自动跟随系统深色模式。

## 颜色系统

Miuix 的颜色系统是基于 HyperOS 设计语言创建的，提供了一套完整的颜色方案，包括：

- 主色调（Primary colors）
- 次要色调（Secondary colors）
- 背景和表面色（Background and surface colors）
- 文本色（Text colors）
- 特殊状态颜色（如禁用状态）

### 主要颜色属性

以下是颜色系统中一些核心属性：

| 属性名             | 描述                   | 用途                 |
| ------------------ | ---------------------- | -------------------- |
| primary            | 主色调                 | 开关、按钮、滑块等   |
| onPrimary          | 主色调上的文本颜色     | 主色调上的文本       |
| primaryContainer   | 主色调容器             | 包含主色调的容器组件 |
| onPrimaryContainer | 主色调容器上的文本颜色 | 主容器上的文本       |
| secondary          | 次要色调               | 次要按钮、卡片等     |
| onSecondary        | 次要色调上的文本颜色   | 次要色调上的文本     |
| background         | 背景色                 | 应用背景             |
| onBackground       | 背景上的文本颜色       | 背景上的文本         |
| surface            | 表面色                 | 卡片、对话框等       |
| onSurface          | 表面色上的文本颜色     | 常规文本             |
| onSurfaceSecondary | 表面色上的次要文本颜色 | 次要文本             |
| outline            | 轮廓线颜色             | 边框、分割线         |
| dividerLine        | 分隔线颜色             | 列表分隔线           |
| windowDimming      | 窗口遮罩颜色           | 对话框、下拉菜单背景 |

### 使用颜色

在组合函数中，您可以通过 `MiuixTheme.colorScheme` 访问当前主题的颜色：

```kotlin
val backgroundColor = MiuixTheme.colorScheme.background
val textColor = MiuixTheme.colorScheme.onBackground

Surface(
    color = backgroundColor,
    modifier = Modifier.fillMaxSize()
) {
    Text(
        text = "Hello Miuix!",
        color = textColor
    )
}
```

### 浅色和深色主题

Miuix提供了默认的浅色和深色主题颜色方案：

- `lightColorScheme()` - 浅色主题的颜色方案
- `darkColorScheme()` - 深色主题的颜色方案

## 文本样式

Miuix 提供了一套预定义的文本样式，以保持整个应用中文本的一致性：

| 样式名    | 用途             |
| --------- | ---------------- |
| main      | 主要文本         |
| title1    | 大标题（32sp）   |
| title2    | 中标题（24sp）   |
| title3    | 小标题（20sp）   |
| body1     | 正文（16sp）     |
| body2     | 次要正文（14sp） |
| button    | 按钮文本（17sp） |
| footnote1 | 脚注（13sp）     |
| footnote2 | 小脚注（11sp）   |

### 使用文本样式

您可以通过 `MiuixTheme.textStyles` 访问当前主题的文本样式：

```kotlin
Text(
    text = "这是一个标题",
    style = MiuixTheme.textStyles.title2
)

Text(
    text = "这是一段正文内容",
    style = MiuixTheme.textStyles.body1
)
```

## 自定义主题

可以通过以下方式进行主题自定义：

- 通过 `ThemeController(ColorSchemeMode.*)` 选择配色模式。
- 选择动态配色：`ColorSchemeMode.DynamicSystem` / `DynamicLight` / `DynamicDark`。
- 传入 `textStyles` 覆盖文本样式：

```kotlin
val customTextStyles = defaultTextStyles(
    title1 = TextStyle(
        fontSize = 36.sp,
        fontWeight = FontWeight.Bold
    ),
    // 其他文本样式...
)

val controller = remember { ThemeController(ColorSchemeMode.Light) }
MiuixTheme(
    controller = controller,
    textStyles = customTextStyles
) {
    // 您的应用内容
}
```

## 跟随系统深色模式

跟随系统深色模式已内置，使用 `ColorSchemeMode.System` 即可：

```kotlin
@Composable
fun MyApp() {
    val controller = remember { ThemeController(ColorSchemeMode.System) }
    MiuixTheme(controller = controller) {
        // 应用内容
    }
}
```
