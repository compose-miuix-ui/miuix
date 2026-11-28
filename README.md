## Miuix

A UI library for Compose Multiplatform.

> This library is experimental. APIs may change without notice.

[![Kotlin](https://img.shields.io/badge/kotlin-2.4.21-7F52FF)](https://kotlinlang.org/)
[![Compose Multiplatform](https://img.shields.io/badge/compose-1.13.0--rc01-4285F4)](https://kotlinlang.org/compose-multiplatform/)
[![Maven Central](https://img.shields.io/maven-central/v/top.yukonga.miuix.kmp/miuix-ui)](https://search.maven.org/search?q=g:top.yukonga.miuix.kmp)
[![License](https://img.shields.io/github/license/compose-miuix-ui/miuix)](LICENSE)

### Supported Platforms

![Android](https://img.shields.io/badge/Android-3DDC84?logo=android&logoColor=white)
![iOS](https://img.shields.io/badge/iOS-000000?logo=apple&logoColor=white)
![macOS](https://img.shields.io/badge/macOS-000000?logo=apple&logoColor=white)
![Desktop](https://img.shields.io/badge/Desktop-007396?logo=openjdk&logoColor=white)
![Web](https://img.shields.io/badge/Web-4285F4?logo=googlechrome&logoColor=white)

### Demos

[![Web](https://img.shields.io/badge/Demo-Web-4285F4?logo=googlechrome&logoColor=white)](https://compose-miuix-ui.github.io/miuix-wasmJs/)
[![Other](https://img.shields.io/badge/Demo-Other-2088FF?logo=githubactions&logoColor=white)](https://github.com/compose-miuix-ui/miuix/actions/workflows/example.yml)

### Modules

| Module              | Description                                                |
| ------------------- | ---------------------------------------------------------- |
| `miuix-ui`          | Core UI component library                                  |
| `miuix-preference`  | Preference components library, depends on `miuix-ui`       |
| `miuix-icons-symbol` | Built-in variable symbol icon library, can be used independently |
| `miuix-material`    | Render primitives and material definitions                 |
| `miuix-blur`        | Blur effect library, can be used independently             |
| `miuix-squircle`    | Squircle shapes library, can be used independently         |
| `miuix-glass`       | Glass surfaces and components library                      |
| `miuix-nav`         | Navigation library, can be used independently              |
| `miuix-window`      | Desktop window & caption bar styling library (Desktop JVM) |

### Getting Started

```diff
kotlin {
    sourceSets {
        commonMain.dependencies {
+           implementation("top.yukonga.miuix.kmp:miuix-ui:<version>")
            // Optional: Add miuix-preference for preference components
            implementation("top.yukonga.miuix.kmp:miuix-preference:<version>")
            // Optional: Add miuix-icons-symbol for the variable Symbol icon set
            implementation("top.yukonga.miuix.kmp:miuix-icons-symbol:<version>")
            // Optional: Add miuix-blur for blur effects
            implementation("top.yukonga.miuix.kmp:miuix-blur:<version>")
            // Optional: Add miuix-squircle for squircle (smooth rounded corner) shapes
            implementation("top.yukonga.miuix.kmp:miuix-squircle:<version>")
            // Optional: Add miuix-glass for the glass surfaces and components
            implementation("top.yukonga.miuix.kmp:miuix-glass:<version>")
            // Optional: Add miuix-nav for navigation
            implementation("top.yukonga.miuix.kmp:miuix-nav:<version>")
            // Other dependencies...
        }
        // Optional: Add miuix-window for desktop window and caption bar styling (Desktop JVM)
        desktopMain.dependencies {
            implementation("top.yukonga.miuix.kmp:miuix-window:<version>")
        }
        // Other sourceSets...
    }
    // Other configurations...
}
```

### Usage

- Provide a color scheme via `MiuixTheme(colors = ...)`, e.g., `lightColorScheme()` or `darkColorScheme()`.

```kotlin
@Composable
fun AppTheme(
    content: @Composable () -> Unit
) {
    val colors = if (isSystemInDarkTheme()) darkColorScheme() else lightColorScheme()
    return MiuixTheme(
        colors = colors,
        content = content
    )
}
```

- Use `ThemeController` to manage modes and enable Monet dynamic colors. Pass `keyColor` to set a custom seed color.

```kotlin
@Composable
fun AppTheme(
    content: @Composable () -> Unit
) {
    val controller = remember {
        ThemeController(
            ColorSchemeMode.MonetSystem,
            keyColor = Color(0xFF3482FF)
        )
    }
    return MiuixTheme(
        controller = controller,
        content = content
    )
}
```

### Screenshots

<p>
  <a href="assets/light/001.webp"><img src="assets/light/001.webp" width="14.2%" alt="Home (Light)"/></a><!--
--><a href="assets/light/002.webp"><img src="assets/light/002.webp" width="14.2%" alt="Icons (Light)"/></a><!--
--><a href="assets/light/003.webp"><img src="assets/light/003.webp" width="14.2%" alt="Colors (Light)"/></a><!--
--><a href="assets/light/004.webp"><img src="assets/light/004.webp" width="14.2%" alt="Text Styles (Light)"/></a><!--
--><a href="assets/light/005.webp"><img src="assets/light/005.webp" width="14.2%" alt="Settings (Light)"/></a><!--
--><a href="assets/light/006.webp"><img src="assets/light/006.webp" width="14.2%" alt="Theme Settings (Light)"/></a><!--
--><a href="assets/light/007.webp"><img src="assets/light/007.webp" width="14.2%" alt="About (Light)"/></a><br><!--
--><a href="assets/dark/001.webp"><img src="assets/dark/001.webp" width="14.2%" alt="Home (Dark)"/></a><!--
--><a href="assets/dark/002.webp"><img src="assets/dark/002.webp" width="14.2%" alt="Icons (Dark)"/></a><!--
--><a href="assets/dark/003.webp"><img src="assets/dark/003.webp" width="14.2%" alt="Colors (Dark)"/></a><!--
--><a href="assets/dark/004.webp"><img src="assets/dark/004.webp" width="14.2%" alt="Text Styles (Dark)"/></a><!--
--><a href="assets/dark/005.webp"><img src="assets/dark/005.webp" width="14.2%" alt="Settings (Dark)"/></a><!--
--><a href="assets/dark/006.webp"><img src="assets/dark/006.webp" width="14.2%" alt="Theme Settings (Dark)"/></a><!--
--><a href="assets/dark/007.webp"><img src="assets/dark/007.webp" width="14.2%" alt="About (Dark)"/></a>
</p>
