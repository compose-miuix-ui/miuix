# 主题系统

Miuix 提供了一套完整的主题系统，使您能够轻松地在整个应用中保持一致的设计风格。整个主题系统由颜色方案和文本样式组成。

## 使用 MiuixTheme

使用 `ThemeController` 控制配色模式，然后用 `MiuixTheme` 包裹内容：

```kotlin
@Composable
fun App() {
    // 可用模式：System、Light、Dark、MonetSystem、MonetLight、MonetDark
    val controller = remember { ThemeController(ColorSchemeMode.System) }
    MiuixTheme(controller = controller) { /* 内容 */ }
}
```

使用 `ColorSchemeMode.System` / `ColorSchemeMode.MonetSystem` 时会自动跟随系统深色模式。

### 具体方式

- 通过 `ThemeController` 控制配色模式，并可启用莫奈颜色；在莫奈模式下支持自定义种子色（`keyColor`）：

```kotlin
@Composable
fun AppWithMonet() {
    val controller = remember {
        ThemeController(
            ColorSchemeMode.MonetSystem, // 或 MonetLight、MonetDark
            keyColor = Color(0xFF3482FF) // 自定义种子颜色
        )
    }
    MiuixTheme(controller = controller) { /* 内容 */ }
}
```

- 直接传入颜色方案到 `MiuixTheme(colors = ...)`，用于完全自定义或使用内置浅/深色方案：

```kotlin
@Composable
fun AppWithColors() {
    val colors = lightColorScheme() // 或 darkColorScheme()
    MiuixTheme(colors = colors) { /* 内容 */ }
}
```

## 自定义主题

可以通过以下方式进行主题自定义：

- 通过 `ThemeController(ColorSchemeMode.*)` 选择配色模式。
- 选择动态配色：`MonetSystem` / `MonetLight` / `MonetDark`。
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
