# Theme System

Miuix provides a complete theme system that allows you to easily maintain a consistent design style
throughout your application. The theme system consists of color schemes and text styles.

## Using MiuixTheme

Use `ThemeController` to control the color scheme mode, then wrap your content with `MiuixTheme`:

```kotlin
@Composable
fun App() {
    // Available modes: System, Light, Dark, MonetSystem, MonetLight, MonetDark
    val controller = remember { ThemeController(ColorSchemeMode.System) }
    MiuixTheme(controller = controller) { /* Content */ }
}
```

`ColorSchemeMode.System` / `ColorSchemeMode.MonetSystem` automatically follows the system’s dark mode.

### Specific Modes

- **Dynamic Colors (Monet)**

  Use `ThemeController` with `Monet` modes to enable dynamic colors.
  - If `keyColor` is provided, colors are generated from this seed color.
  - If `keyColor` is `null` (default), it attempts to use the system's wallpaper colors (Android "Material You").

  ```kotlin
  @Composable
  fun AppWithMonet() {
      val controller = remember {
          ThemeController(
              ColorSchemeMode.MonetSystem, // or MonetLight, MonetDark
              // keyColor = Color(0xFF3482FF) // Optional: Custom seed color
          )
      }
      MiuixTheme(controller = controller) { /* Content */ }
  }
  ```

- **Manual Dark Mode Control**

  Use the `isDark` parameter to explicitly control the dark state, overriding the system setting.

  ```kotlin
  val controller = remember {
      ThemeController(
          ColorSchemeMode.System,
          isDark = true // Force dark mode
      )
  }
  ```

- **Custom Color Schemes**

  You can provide custom `lightColors` and `darkColors` directly to the `ThemeController`.

  ```kotlin
  val controller = remember {
      ThemeController(
          ColorSchemeMode.System,
          lightColors = myLightColors,
          darkColors = myDarkColors
      )
  }
  ```

- **Direct Usage**

  Provide a color scheme directly to `MiuixTheme(colors = ...)` for full customization without a controller:

  ```kotlin
  @Composable
  fun AppWithColors() {
      val colors = lightColorScheme() // or darkColorScheme()
      MiuixTheme(colors = colors) { /* Content */ }
  }
  ```

## Customizing the Theme

You can customize the theme in the following ways:

- Select a color scheme mode via `ThemeController(ColorSchemeMode.*)`.
- Opt into dynamic colors via `MonetSystem` / `MonetLight` / `MonetDark`.
- Override text styles by passing `textStyles`:

```kotlin
val customTextStyles = defaultTextStyles(
    title1 = TextStyle(
        fontSize = 36.sp,
        fontWeight = FontWeight.Bold
    ),
    // Other text styles...
)

val controller = remember { ThemeController(ColorSchemeMode.Light) }
MiuixTheme(
    controller = controller,
    textStyles = customTextStyles
) {
    // Your application content
}
```

## Follow System Dark Mode

Following the system’s dark mode is built-in. Use `ColorSchemeMode.System`:

```kotlin
@Composable
fun MyApp() {
    val controller = remember { ThemeController(ColorSchemeMode.System) }
    MiuixTheme(controller = controller) {
        // Application content
    }
}
```
