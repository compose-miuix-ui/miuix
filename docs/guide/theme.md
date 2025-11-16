# Theme System

Miuix provides a complete theme system that allows you to easily maintain a consistent design style throughout your application. The theme system consists of color schemes and text styles.

## Using the Theme

Use `ThemeController` to control the color scheme mode, then wrap your content with `MiuixTheme`:

```kotlin
@Composable
fun App() {
    val controller = remember { ThemeController(ColorSchemeMode.System) }

    // Available modes:
    // ColorSchemeMode.System, Light, Dark, DynamicSystem, DynamicLight, DynamicDark
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

## Color System

The Miuix color system is based on the HyperOS design language and provides a complete set of color schemes, including:

- Primary colors
- Secondary colors
- Background and surface colors
- Text colors
- Special state colors (e.g., disabled state)

### Key Color Properties

Below are some core properties of the color system:

| Property Name      | Description               | Usage                        |
| ------------------ | ------------------------- | ---------------------------- |
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

### Light and Dark Themes

Miuix provides default light and dark theme color schemes:

- `lightColorScheme()` - Light theme color scheme
- `darkColorScheme()` - Dark theme color scheme

## Text Styles

Miuix provides a set of predefined text styles to maintain consistency in text throughout the application:

| Style Name | Usage                      |
| ---------- | -------------------------- |
| main       | Main text                  |
| title1     | Large title (32sp)         |
| title2     | Medium title (24sp)        |
| title3     | Small title (20sp)         |
| body1      | Body text (16sp)           |
| body2      | Secondary body text (14sp) |
| button     | Button text (17sp)         |
| footnote1  | Footnote (13sp)            |
| footnote2  | Small footnote (11sp)      |

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
- Opt into dynamic colors via `ColorSchemeMode.DynamicSystem` / `DynamicLight` / `DynamicDark`.
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
