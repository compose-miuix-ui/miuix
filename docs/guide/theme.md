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

`ColorSchemeMode.System` / `ColorSchemeMode.MonetSystem` automatically follows the system's dark mode.

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

- **Palette Style and Color Spec**

  You can customize the palette style and color specification used by Monet color generation via `paletteStyle` and `colorSpec`:

  ```kotlin
  val controller = remember {
      ThemeController(
          ColorSchemeMode.MonetSystem,
          keyColor = Color(0xFF3482FF),
          paletteStyle = ThemePaletteStyle.Vibrant,
          colorSpec = ThemeColorSpec.Spec2025
      )
  }
  MiuixTheme(controller = controller) { /* Content */ }
  ```

  ::: tip
  `ThemeColorSpec.Spec2025` is only supported by `TonalSpot`, `Neutral`, `Vibrant`, and `Expressive` palette styles; other styles gracefully fall back to `Spec2021` (`Cmf` style automatically uses the 2026 specification).
  :::

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

## ThemeController

The `ThemeController` manages the current color scheme of the Miuix theme.

### ThemeController Properties

| Property Name   | Type              | Description                                                                  | Default Value               |
| --------------- | ----------------- | ---------------------------------------------------------------------------- | --------------------------- |
| colorSchemeMode | ColorSchemeMode   | The color scheme mode                                                        | ColorSchemeMode.System      |
| lightColors     | Colors            | Light color scheme                                                           | lightColorScheme()          |
| darkColors      | Colors            | Dark color scheme                                                            | darkColorScheme()           |
| keyColor        | Color?            | Seed color for dynamic color generation. `null` uses system wallpaper colors | null                        |
| colorSpec       | ThemeColorSpec    | Material color specification version                                         | ThemeColorSpec.Spec2021     |
| paletteStyle    | ThemePaletteStyle | Palette style for dynamic color generation                                   | ThemePaletteStyle.TonalSpot |
| isDark          | Boolean?          | Override system dark mode. `null` follows system                             | null                        |

### ColorSchemeMode

| Value       | Description                               |
| ----------- | ----------------------------------------- |
| System      | Follows system light/dark setting         |
| Light       | Forces light mode                         |
| Dark        | Forces dark mode                          |
| MonetSystem | Dynamic colors, follows system light/dark |
| MonetLight  | Dynamic colors, forces light mode         |
| MonetDark   | Dynamic colors, forces dark mode          |

### ThemePaletteStyle

| Value      | Color Spec         | Description                                               |
| ---------- | ------------------ | --------------------------------------------------------- |
| TonalSpot  | Spec2021, Spec2025 | Default Material You style with balanced tonal variations |
| Neutral    | Spec2021, Spec2025 | Muted, low-chroma palette                                 |
| Vibrant    | Spec2021, Spec2025 | High-chroma, vivid palette                                |
| Expressive | Spec2021, Spec2025 | Bold and artistic palette with creative color shifts      |
| Rainbow    | Spec2021           | Broad spectrum of hues                                    |
| FruitSalad | Spec2021           | Playful, multi-hue palette                                |
| Monochrome | Spec2021           | Single-hue grayscale palette                              |
| Fidelity   | Spec2021           | Closely matches the seed color                            |
| Content    | Spec2021           | Derived from content colors for maximum accuracy          |
| Cmf        | Spec2026 (Auto)    | Color, Material, Finish (CMF) palette                     |

### ThemeColorSpec

| Value    | Description                                                                             |
| -------- | --------------------------------------------------------------------------------------- |
| Spec2021 | Original Material Design 3 color specification                                          |
| Spec2025 | 2025 color specification (supported by TonalSpot, Neutral, Vibrant, Expressive) |

## MiuixTheme Object

Access the current theme values through the `MiuixTheme` object:

```kotlin
val colors = MiuixTheme.colorScheme
val textStyles = MiuixTheme.textStyles
val mode = MiuixTheme.colorSchemeMode
val isDynamic = MiuixTheme.isDynamicColor
```

| Property        | Type             | Description                                                        |
| --------------- | ---------------- | ------------------------------------------------------------------ |
| colorScheme     | Colors           | Current color scheme                                               |
| textStyles      | TextStyles       | Current text styles                                                |
| colorSchemeMode | ColorSchemeMode? | Current color scheme mode (null if using direct `colors` overload) |
| isDynamicColor  | Boolean          | Whether the current mode is a Monet dynamic color mode             |

## Customizing the Theme

You can customize the theme in the following ways:

- Select a color scheme mode via `ThemeController(ColorSchemeMode.*)`.
- Opt into dynamic colors via `MonetSystem` / `MonetLight` / `MonetDark`.
- Choose a palette style via `paletteStyle` and a color spec via `colorSpec`.
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

Following the system's dark mode is built-in. Use `ColorSchemeMode.System`:

```kotlin
@Composable
fun MyApp() {
    val controller = remember { ThemeController(ColorSchemeMode.System) }
    MiuixTheme(controller = controller) {
        // Application content
    }
}

## Display Cutout Adaptation (`ProvideDisplayCutoutFix`)

Miuix automatically integrates `ProvideDisplayCutoutFix` into `MiuixTheme`.

On certain Android devices (especially tablets in landscape orientation and foldable devices), the OEM system window insets dispatch mechanism may fail to report the display cutout to the View tree (`WindowInsets.displayCutout` evaluates to 0) even when the window extends into the cutout area.

`ProvideDisplayCutoutFix` queries the WindowManager's current window metrics (API 30+) and supplements the View tree's `WindowInsets` so that all Compose components (such as `NavigationRail`, `TopAppBar`, `Scaffold`, and `ListPopup`) properly avoid display cutouts.

### Manual Usage

Normally this is handled automatically by `MiuixTheme`. If you have a custom root window or overlay outside of `MiuixTheme`, you can also invoke it directly:

```kotlin
ProvideDisplayCutoutFix()
```

### Disabling the Fix

If your application handles display cutouts manually or requires the default system behavior without supplementation, you can disable it via `LocalDisplayCutoutFixEnabled`:

```kotlin
CompositionLocalProvider(LocalDisplayCutoutFixEnabled provides false) {
    MiuixTheme {
        // Content with automatic cutout fix disabled
    }
}
```
```
