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

- **动态取色 (Monet)**

  使用 `ThemeController` 的 `Monet` 模式启用动态取色。
  - 如果提供了 `keyColor`，将从该种子颜色生成配色方案。
  - 如果 `keyColor` 为 `null`（默认），则尝试使用系统的壁纸颜色（Android "Material You"）。

  ```kotlin
  @Composable
  fun AppWithMonet() {
      val controller = remember {
          ThemeController(
              ColorSchemeMode.MonetSystem, // 或 MonetLight、MonetDark
              // keyColor = Color(0xFF3482FF) // 可选：自定义种子颜色
          )
      }
      MiuixTheme(controller = controller) { /* 内容 */ }
  }
  ```

- **手动控制深色模式**

  使用 `isDark` 参数显式控制深色状态，覆盖系统设置。

  ```kotlin
  val controller = remember {
      ThemeController(
          ColorSchemeMode.System,
          isDark = true // 强制深色模式
      )
  }
  ```

- **Controller 中的自定义配色**

  您也可以直接将自定义的 `lightColors` 和 `darkColors` 传递给 `ThemeController`。

  ```kotlin
  val controller = remember {
      ThemeController(
          ColorSchemeMode.System,
          lightColors = myLightColors,
          darkColors = myDarkColors
      )
  }
  ```

- **直接使用**

  直接传入颜色方案到 `MiuixTheme(colors = ...)`，用于在不使用 Controller 的情况下完全自定义：

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
