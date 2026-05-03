# Getting Started

Supported platforms: **Android** / **Desktop (JVM)** / **iOS** / **WasmJs** / **Js** / **macOS (Native)**

::: warning
This library is experimental, and APIs may change in future versions without notice.
:::

## Adding Dependencies

To use Miuix in your project, follow these steps to add dependencies:

### Gradle (Kotlin DSL)

1. Add the following to the root `settings.gradle.kts` file (usually already included):

```kotlin
repositories {
    mavenCentral()
}
```

2. Check the latest version on Maven Central:
   [![Maven Central](https://img.shields.io/maven-central/v/top.yukonga.miuix.kmp/miuix-ui)](https://search.maven.org/search?q=g:top.yukonga.miuix.kmp)

3. Add dependencies to your project's `build.gradle.kts`:

Miuix is composed of several modules that can be used independently:

| Module | Description |
|---|---|
| `miuix-ui` | Core UI component library (automatically includes `miuix-core`) |
| `miuix-preference` | Preference components (SwitchPreference, CheckboxPreference, etc.), depends on `miuix-ui` |
| `miuix-icons` | Extended icon library, can be used independently or together with `miuix-ui` (automatically includes `miuix-core`) |
| `miuix-blur` | Blur effect library, can be used independently |
| `miuix-navigation3-ui` | Navigation3 UI library, can be used independently |

- For Compose Multiplatform projects:

```kotlin
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
            // Optional: Add miuix-navigation3-ui for Navigation3 support
            implementation("top.yukonga.miuix.kmp:miuix-navigation3-ui:<version>")
        }
    }
}
```

- For Android Compose projects:

```kotlin
dependencies {
    implementation("top.yukonga.miuix.kmp:miuix-ui-android:<version>")
    // Optional: Add miuix-preference for preference components
    implementation("top.yukonga.miuix.kmp:miuix-preference-android:<version>")
    // Optional: Add miuix-icons for more icons
    implementation("top.yukonga.miuix.kmp:miuix-icons-android:<version>")
    // Optional: Add miuix-blur for blur effects (requires minSdk 31)
    implementation("top.yukonga.miuix.kmp:miuix-blur-android:<version>")
    // Optional: Add miuix-navigation3-ui for Navigation3 support
    implementation("top.yukonga.miuix.kmp:miuix-navigation3-ui-android:<version>")
}
```

- For other projects, add platform-specific dependencies as needed:

```kotlin
implementation("top.yukonga.miuix.kmp:miuix-ui-iosarm64:<version>")
implementation("top.yukonga.miuix.kmp:miuix-ui-iossimulatorarm64:<version>")
implementation("top.yukonga.miuix.kmp:miuix-ui-macosarm64:<version>")
implementation("top.yukonga.miuix.kmp:miuix-ui-desktop:<version>")
implementation("top.yukonga.miuix.kmp:miuix-ui-wasmjs:<version>")
implementation("top.yukonga.miuix.kmp:miuix-ui-js:<version>")
// Optional: Add miuix-blur
implementation("top.yukonga.miuix.kmp:miuix-blur-iosarm64:<version>")
implementation("top.yukonga.miuix.kmp:miuix-blur-iossimulatorarm64:<version>")
implementation("top.yukonga.miuix.kmp:miuix-blur-macosarm64:<version>")
implementation("top.yukonga.miuix.kmp:miuix-blur-desktop:<version>")
implementation("top.yukonga.miuix.kmp:miuix-blur-wasmjs:<version>")
implementation("top.yukonga.miuix.kmp:miuix-blur-js:<version>")
// Optional: Add miuix-navigation3-ui
implementation("top.yukonga.miuix.kmp:miuix-navigation3-ui-iosarm64:<version>")
implementation("top.yukonga.miuix.kmp:miuix-navigation3-ui-iossimulatorarm64:<version>")
implementation("top.yukonga.miuix.kmp:miuix-navigation3-ui-macosarm64:<version>")
implementation("top.yukonga.miuix.kmp:miuix-navigation3-ui-desktop:<version>")
implementation("top.yukonga.miuix.kmp:miuix-navigation3-ui-wasmjs:<version>")
implementation("top.yukonga.miuix.kmp:miuix-navigation3-ui-js:<version>")
// Optional: Add miuix-icons
implementation("top.yukonga.miuix.kmp:miuix-icons-iosarm64:<version>")
implementation("top.yukonga.miuix.kmp:miuix-icons-iossimulatorarm64:<version>")
implementation("top.yukonga.miuix.kmp:miuix-icons-macosarm64:<version>")
implementation("top.yukonga.miuix.kmp:miuix-icons-desktop:<version>")
implementation("top.yukonga.miuix.kmp:miuix-icons-wasmjs:<version>")
implementation("top.yukonga.miuix.kmp:miuix-icons-js:<version>")
```

## Basic Usage

### Applying the Miuix Theme

```kotlin
@Composable
fun AppTheme(
    content: @Composable () -> Unit
) {
    // Available modes: System, Light, Dark, MonetSystem, MonetLight, MonetDark
    val controller = remember { ThemeController(ColorSchemeMode.System) }
    return MiuixTheme(
        controller = controller,
        content = content
    )
}
```

### Using the Miuix Scaffold

```kotlin
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
    floatingToolbar = {
        // FloatingToolbar
    }
) {
    // Content...
}
```

::: warning
The Scaffold component provides a suitable container for cross-platform popup windows.
Components such as `OverlayDialog`, `OverlayDropdownPreference`, `OverlaySpinnerPreference`, and `OverlayListPopup` are
all implemented based on this and therefore need to be wrapped by this component.
:::

## API Documentation

- View the [API Documentation](/miuix/dokka/index.html){target="_blank"},
  generated using Dokka, which contains detailed information about all APIs.
