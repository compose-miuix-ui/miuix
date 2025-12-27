# Platform Support

Miuix is a Compose Multiplatform UI framework that supports multiple platforms, allowing you to build applications for different platforms using the same codebase.

## Supported Platforms

Currently, Miuix supports the following platforms:

- **Android**: For Android mobile devices
- **iOS**: For iPhone and iPad devices
- **Desktop (JVM)**: For JVM-based desktop applications
- **WasmJs**: For WebAssembly (Web) environments
- **MacOS**: For native macOS applications
- **Js**: For JavaScript (Web) environments

## Platform Detection and Adaptation

You can use the `platform()` function to detect the current running platform and adjust the UI or functionality accordingly:

```kotlin
when (platform()) {
    Platform.Android -> {
        // Android-specific code
    }
    Platform.IOS -> {
        // iOS-specific code
    }
    Platform.Desktop -> {
        // JVM-specific code
    }
    Platform.WasmJs -> {
        // WebAssembly-specific code
    }
    Platform.MacOS -> {
        // macOS-specific code
    }
    Platform.Js -> {
        // JavaScript-specific code
    }
}
```

## Device Rounded Corners

Android devices have varying screen corner radii, while other platforms may not have rounded corners. You can use the `getRoundedCorner()` function to retrieve the corner radius of the device (using a preset value if unavailable):

```kotlin
@Composable
fun AdaptiveRoundedComponent() {
    val cornerRadius = getRoundedCorner()
    Surface(
        shape = RoundedCornerShape(cornerRadius),
        // Other properties
    ) {
        // Content
    }
}
```
