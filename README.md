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

```
@Composable
fun App() {
    // Use ThemeController to control color scheme mode
    val controller = remember { ThemeController(ColorSchemeMode.System) }
    
    // Available modes:
    // ColorSchemeMode.System, Light, Dark, DynamicSystem, DynamicLight, DynamicDark
    MiuixTheme(controller = controller) {
        Scaffold(
            topBar = {
                // TopBar
            },
            bottomBar = {
                // BottomBar
            },
            floatingActionButton = {
                // FloatingActionButton
            },
        ) {
            // Other Content...
        }
    }
}
```

### Screenshot

[![screenshot](https://github.com/compose-miuix-ui/miuix/blob/main/assets/screenshot.webp?raw=true)]()
