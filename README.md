## Miuix

**Miuix** is a shared UI library for [Compose Multiplatform](https://www.jetbrains.com/compose-multiplatform/).

Now Supported: **Android** / **Desktop(JVM)** / **iOS** / **WasmJs** / **Js** / **macOS(Native)**.

> This library is experimental, any API would be changed in the future without any notification.

[![License](https://img.shields.io/github/license/compose-miuix-ui/miuix)](LICENSE)
[![Maven Central](https://img.shields.io/maven-central/v/top.yukonga.miuix.kmp/miuix)](https://search.maven.org/search?q=g:top.yukonga.miuix.kmp)

### Web Example

[JsCanvas Demo](https://compose-miuix-ui.github.io/miuix-jsCanvas/) / [WasmJs Demo](https://compose-miuix-ui.github.io/miuix-wasmJs/)

### Start

```
kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation("top.yukonga.miuix.kmp:miuix:<version>")
            // Other dependencies...
        }
        // Other sourceSets...
    }
    // Other configurations...
}
```

### Usage

- Direct colors: provide a color scheme to `MiuixTheme(colors = ...)`, e.g. built-in `lightColorScheme()` / `darkColorScheme()`.

```
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

- Controller-based: control modes via `ThemeController` and enable Monet dynamic colors; pass `keyColor` to set a custom seed color.

```
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

### Screenshot

<table>
  <tr>
    <td><a href="https://github.com/compose-miuix-ui/miuix/blob/main/assets/001.webp?raw=true"><img src="https://github.com/compose-miuix-ui/miuix/blob/main/assets/001.webp?raw=true" width="300" alt="screenshot-001"/></a></td>
    <td><a href="https://github.com/compose-miuix-ui/miuix/blob/main/assets/002.webp?raw=true"><img src="https://github.com/compose-miuix-ui/miuix/blob/main/assets/002.webp?raw=true" width="300" alt="screenshot-002"/></a></td>
    <td><a href="https://github.com/compose-miuix-ui/miuix/blob/main/assets/003.webp?raw=true"><img src="https://github.com/compose-miuix-ui/miuix/blob/main/assets/003.webp?raw=true" width="300" alt="screenshot-003"/></a></td>
  </tr>
  <tr>
    <td><a href="https://github.com/compose-miuix-ui/miuix/blob/main/assets/004.webp?raw=true"><img src="https://github.com/compose-miuix-ui/miuix/blob/main/assets/004.webp?raw=true" width="300" alt="screenshot-004"/></a></td>
    <td><a href="https://github.com/compose-miuix-ui/miuix/blob/main/assets/005.webp?raw=true"><img src="https://github.com/compose-miuix-ui/miuix/blob/main/assets/005.webp?raw=true" width="300" alt="screenshot-005"/></a></td>
    <td><a href="https://github.com/compose-miuix-ui/miuix/blob/main/assets/006.webp?raw=true"><img src="https://github.com/compose-miuix-ui/miuix/blob/main/assets/006.webp?raw=true" width="300" alt="screenshot-006"/></a></td>
  </tr>
</table>
