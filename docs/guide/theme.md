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

- Use `ThemeController` to control modes and enable Monet dynamic colors; Monet modes support a custom seed color via `keyColor`:

```kotlin
@Composable
fun AppWithMonet() {
    val controller = remember {
        ThemeController(
            ColorSchemeMode.MonetSystem, // or MonetLight, MonetDark
            keyColor = Color(0xFF3482FF) // Custom seed color
        )
    }
    MiuixTheme(controller = controller) { /* Content */ }
}
```

- Provide a color scheme directly to `MiuixTheme(colors = ...)` for full customization or to use built-in light/dark schemes:

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
