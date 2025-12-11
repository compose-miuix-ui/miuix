# Theme System

Miuix provides a complete theme system that allows you to easily maintain a consistent design style
throughout your application. The theme system consists of color schemes and text styles.

## Using MiuixTheme

Use `ThemeController` to control the color scheme mode, then wrap your content with `MiuixTheme`:

```kotlin
@Composable
fun App() {
    val controller = remember { ThemeController(ColorSchemeMode.System) }

    // Available modes:
    // ColorSchemeMode.System, Light, Dark, MonetSystem, MonetLight, MonetDark
    MiuixTheme(controller = controller) {
        Scaffold(
            topBar = { /* ... */ },
        ) { padding ->
            // Main content
        }
    }
}
```

`ColorSchemeMode.System` automatically follows the system’s dark mode.

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

## Color System

The Miuix color system is based on the HyperOS design language and provides a complete set of color
schemes, including:

- Primary colors
- Secondary colors
- Background and surface colors
- Text colors
- Special state colors (e.g., disabled state)

### Key Color Properties

Below are some core properties of the color system:

| Property Name      | Description               | Usage                        |
|--------------------|---------------------------|------------------------------|
| primary            | Primary color             | Switches, buttons, sliders   |
| onPrimary          | Text color on primary     | Text on primary color        |
| primaryContainer   | Primary container         | Components with primary      |
| onPrimaryContainer | Text on primary container | Text on primary containers   |
| secondary          | Secondary color           | Secondary buttons, cards     |
| onSecondary        | Text on secondary         | Text on secondary color      |
| background         | Background color          | App background               |
| onBackground       | Text on background        | Text on background           |
| surface            | Surface color             | Cards, dialogs               |
| onSurface          | Text on surface           | Regular text                 |
| onSurfaceSecondary | Secondary text on surface | Secondary text               |
| outline            | Outline color             | Borders, outlines            |
| dividerLine        | Divider line color        | List dividers                |
| windowDimming      | Window dimming color      | Dialog, dropdown backgrounds |

### Using Colors

In composable functions, you can access the current theme's colors via `MiuixTheme.colorScheme`:

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

### Theme Color Schemes

Miuix provides default light and dark theme color schemes:

- `lightColorScheme()` - Light theme
- `darkColorScheme()` - Dark theme

## Text Styles

Miuix provides a set of predefined text styles and usage rules to maintain consistency in text
throughout the application.

| Style Name | Size | Notes / Usage       |
|------------|------|---------------------|
| main       | 17sp | Default text        |
| paragraph  | 17sp | long text           |
| body1      | 16sp | Standard body copy  |
| body2      | 14sp | Secondary body copy |
| button     | 17sp | Button labels       |
| footnote1  | 13sp | Footnote            |
| footnote2  | 11sp | Small footnote      |
| headline1  | 17sp | Section lead label  |
| headline2  | 16sp | Sub-lead label      |
| subtitle   | 14sp | Auxiliary title     |
| title1     | 32sp | Large title         |
| title2     | 24sp | Medium title        |
| title3     | 20sp | Small title         |
| title4     | 18sp | Extra small title   |

### Using Text Styles

You can access the current theme's text styles via `MiuixTheme.textStyles`:

```kotlin
Text(
    text = "This is a title",
    style = MiuixTheme.textStyles.title2
)

Text(
    text = "This is body content",
    style = MiuixTheme.textStyles.body1
)
```

## Customizing the Theme

You can customize the theme in the following ways:

- Select a color scheme mode via `ThemeController(ColorSchemeMode.*)`.
- Opt into dynamic colors via `ColorSchemeMode.MonetSystem` / `MonetLight` / `MonetDark`.
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
