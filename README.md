## Miuix

A UI library for Compose Multiplatform.

> This library is experimental. APIs may change without notice.

[![Kotlin](https://img.shields.io/badge/kotlin-2.4.10-7F52FF)](https://kotlinlang.org/)
[![Compose Multiplatform](https://img.shields.io/badge/compose-1.11.1-4285F4)](https://kotlinlang.org/compose-multiplatform/)
[![Maven Central](https://img.shields.io/maven-central/v/top.yukonga.miuix.kmp/miuix-ui)](https://search.maven.org/search?q=g:top.yukonga.miuix.kmp)
[![License](https://img.shields.io/github/license/compose-miuix-ui/miuix)](LICENSE)

### Supported Platforms

![Android](https://img.shields.io/badge/Android-3DDC84?logo=android&logoColor=white)
![iOS](https://img.shields.io/badge/iOS-Native-white?logo=apple)
![macOS](https://img.shields.io/badge/macOS-Native-white?logo=apple)
![Desktop](https://img.shields.io/badge/Desktop-JVM-007396?logo=openjdk)
![JsCanvas](https://img.shields.io/badge/Web-JsCanvas-F7DF1E?logo=javascript&logoColor=white)
![WasmJs](https://img.shields.io/badge/Web-WasmJs-654FF0?logo=webassembly&logoColor=white)

### Demos

[![JsCanvas](https://img.shields.io/badge/Demo-JsCanvas-F7DF1E?logo=javascript&logoColor=white)](https://compose-miuix-ui.github.io/miuix-jsCanvas/)
[![WasmJs](https://img.shields.io/badge/Demo-WasmJs-654FF0?logo=webassembly&logoColor=white)](https://compose-miuix-ui.github.io/miuix-wasmJs/)
[![Other](https://img.shields.io/badge/Demo-Other-white?logo=githubactions&logoColor=white)](https://github.com/compose-miuix-ui/miuix/actions/workflows/example.yml)

### Modules

| Module             | Description                                          |
| ------------------ | ---------------------------------------------------- |
| `miuix-ui`         | Core UI component library                            |
| `miuix-preference` | Preference components library, depends on `miuix-ui` |
| `miuix-icons`      | Extended icon library, can be used independently     |
| `miuix-blur`       | Blur effect library, can be used independently       |
| `miuix-squircle`   | Squircle shapes library, can be used independently   |
| `miuix-nav`        | Navigation library, can be used independently        |
| `miuix-shader`     | Low-level runtime shader / render effect abstraction |

### Getting Started

```diff
kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation("top.yukonga.miuix.kmp:miuix-ui:<version>")
            // Optional: Add miuix-preference for preference components
            implementation("top.yukonga.miuix.kmp:miuix-preference:<version>")
            // Optional: Add miuix-icons for more icons
            implementation("top.yukonga.miuix.kmp:miuix-icons:<version>")
            // Optional: Add miuix-blur for blur effects
            implementation("top.yukonga.miuix.kmp:miuix-blur:<version>")
            // Optional: Add miuix-squircle for squircle (smooth rounded corner) shapes
            implementation("top.yukonga.miuix.kmp:miuix-squircle:<version>")
            // Optional: Add miuix-nav for navigation
            implementation("top.yukonga.miuix.kmp:miuix-nav:<version>")
            // Other dependencies...
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

<table>
  <tr>
    <td><a href="https://github.com/compose-miuix-ui/miuix/blob/main/assets/001.webp?raw=true"><img src="https://github.com/compose-miuix-ui/miuix/blob/main/assets/001.webp?raw=true" width="300" alt="Screenshot 001"/></a></td>
    <td><a href="https://github.com/compose-miuix-ui/miuix/blob/main/assets/002.webp?raw=true"><img src="https://github.com/compose-miuix-ui/miuix/blob/main/assets/002.webp?raw=true" width="300" alt="Screenshot 002"/></a></td>
    <td><a href="https://github.com/compose-miuix-ui/miuix/blob/main/assets/003.webp?raw=true"><img src="https://github.com/compose-miuix-ui/miuix/blob/main/assets/003.webp?raw=true" width="300" alt="Screenshot 003"/></a></td>
  </tr>
  <tr>
    <td><a href="https://github.com/compose-miuix-ui/miuix/blob/main/assets/004.webp?raw=true"><img src="https://github.com/compose-miuix-ui/miuix/blob/main/assets/004.webp?raw=true" width="300" alt="Screenshot 004"/></a></td>
    <td><a href="https://github.com/compose-miuix-ui/miuix/blob/main/assets/005.webp?raw=true"><img src="https://github.com/compose-miuix-ui/miuix/blob/main/assets/005.webp?raw=true" width="300" alt="Screenshot 005"/></a></td>
    <td><a href="https://github.com/compose-miuix-ui/miuix/blob/main/assets/006.webp?raw=true"><img src="https://github.com/compose-miuix-ui/miuix/blob/main/assets/006.webp?raw=true" width="300" alt="Screenshot 006"/></a></td>
  </tr>
</table>
