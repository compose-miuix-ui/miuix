# Window & Title Bar

`miuix-window` is a desktop-only window styling and custom caption bar module for Compose Multiplatform on **Windows 10/11**, **macOS**, and **Linux (X11 & Wayland)**.

It provides native desktop integrations, including Windows 11 Snap Layouts, DWM Mica / Acrylic materials, macOS traffic light alignment and Vibrancy, Linux client-side decorations (CSD), and multi-touch touchscreen input.

## Setup

Add the dependency to `desktopMain` in your `build.gradle.kts`:

```kotlin
kotlin {
    sourceSets {
        desktopMain.dependencies {
            implementation("top.yukonga.miuix.kmp:miuix-window:<version>")
        }
    }
}
```

### Linux JVM Options

On Linux, loading system cursor themes (e.g. Breeze, Adwaita) and managing XInput2 touch focus require access to AWT internal packages. Add the following module opens to your `application` configuration:

```kotlin
compose.desktop {
    application {
        mainClass = "MainKt"
        if (System.getProperty("os.name").startsWith("Linux")) {
            jvmArgs += listOf(
                "--add-opens=java.desktop/sun.awt=ALL-UNNAMED",
                "--add-opens=java.desktop/sun.awt.X11=ALL-UNNAMED",
                "--add-opens=java.desktop/java.awt=ALL-UNNAMED",
            )
        }
    }
}
```

## Basic Usage

Replace Compose `Window` with `MiuixWindow` and use `MiuixCaptionBar` with `WindowDraggableArea`:

```kotlin
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.window.*

fun main() = application {
    val state = rememberWindowState(size = DpSize(960.dp, 640.dp))

    MiuixTheme {
        val isDark = MiuixTheme.colorScheme.surface.luminance() < 0.5f
        MiuixWindow(
            state = state,
            onCloseRequest = ::exitApplication,
            title = "Miuix Desktop",
            properties = MiuixWindowProperties.default(
                captionButtonIsDarkTheme = isDark,
                backgroundIsDarkTheme = isDark,
                backgroundType = when {
                    MiuixWindowBackgroundType.Vibrancy.isSupported() -> MiuixWindowBackgroundType.Vibrancy
                    MiuixWindowBackgroundType.Mica.isSupported() -> MiuixWindowBackgroundType.Mica
                    else -> MiuixWindowBackgroundType.None
                },
            ),
        ) {
            val captionBarHeight = LocalMiuixWindowInfo.current.captionBarHeight
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MiuixTheme.colorScheme.surface),
            ) {
                if (captionBarHeight > 0.dp) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(captionBarHeight),
                    ) {
                        WindowDraggableArea()
                        MiuixCaptionBar {
                            Text(
                                text = "Miuix Desktop",
                                modifier = Modifier
                                    .align(Alignment.CenterStart)
                                    .padding(start = 16.dp),
                                style = MiuixTheme.textStyles.body2,
                            )
                        }
                    }
                }

                // Main content
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                ) {
                    Text("Hello Miuix Window", modifier = Modifier.align(Alignment.Center))
                }
            }
        }
    }
}
```

> Place `MiuixTheme` outside `MiuixWindow` so window decorations, caption bars, and content inherit the same color scheme. `backgroundIsDarkTheme` sets the native backdrop luminance without overriding `MiuixTheme.colorScheme.surface`.

## Core Components

### `MiuixWindow` & `MiuixDialogWindow`

Drop-in replacements for Compose `Window` and `DialogWindow`. They manage non-client area extension, platform hit-testing, and native backdrop materials.

### `MiuixCaptionBar` & `WindowDraggableArea`

- **`MiuixCaptionBar`**: Title bar container that automatically insets content for native buttons (left on macOS for traffic lights, right on Windows, and system-configured on Linux).
- **`WindowDraggableArea`**: Invisible region placed behind interactive elements to handle native dragging without blocking clicks on child controls.

> **Constraint**: Each `MiuixWindow` or `MiuixDialogWindow` scope accepts at most **one** active `WindowDraggableArea`. Child windows and dialogs maintain independent scopes. Keep draggable areas outside overlapping transitions (`AnimatedContent`, `Crossfade`) to avoid temporary duplicate registrations during animation handoff.

### `MiuixWindowProperties`

| Property | Type | Default | Description |
| :--- | :--- | :--- | :--- |
| `captionBarHeight` | `Dp` | `defaultCaptionBarHeight()` | Caption bar height (32.dp on Windows/Linux, 40.dp on macOS) |
| `captionButtonHeight` | `Dp` | `captionBarHeight` | Height of caption buttons |
| `captionButtonsVisible` | `Boolean` | `true` | Whether native/custom caption buttons are visible |
| `captionButtonIsDarkTheme` | `Boolean?` | `null` | Dark appearance for caption buttons (`null` follows system) |
| `backgroundType` | `MiuixWindowBackgroundType` | `None` | Native backdrop: `None`, `Mica`, `Acrylic`, `MicaAlt`, or `Vibrancy` |
| `backgroundIsDarkTheme` | `Boolean?` | `null` | Dark appearance for native backdrop (`null` follows system) |
| `moveable` | `Boolean` | `true` | Whether caption dragging is enabled |
| `minimizeButtonEnabled` | `Boolean` | `true` | Enables or disables the minimize button |
| `maximizeOrRestoreButtonEnabled` | `Boolean` | `true` | Enables or disables maximize and restore operations |
| `minSize` | `DpSize` | `DpSize.Zero` | Minimum window size constraint |
| `extraDisplayScale` | `Float` | `1.0f` | Additional UI display scaling factor |
| `extraFontScale` | `Float` | `1.0f` | Additional font scaling factor |
| `onResizeEdgeChange` | `(Window, WindowResizeEdge) -> Unit` | `{ _, _ -> }` | Callback when pointer enters/leaves resize borders |
| `onVisibleChange` | `(Window, Boolean) -> Unit` | `{ _, _ -> }` | Callback on window visibility change |

## Platform Specifics

- **Windows**:
  - Reports `HTMAXBUTTON` on maximize button hover to invoke Windows 11 Snap Layouts flyout menus.
  - Supports DWM Mica, Mica Alt, and Acrylic materials with automatic dark mode switching.
  - Multi-touch touchscreen input and caption dragging via `WM_POINTER`.
- **macOS**:
  - Vertically centers native traffic light buttons to match `captionBarHeight`.
  - Full-window `NSVisualEffectView` Vibrancy material behind transparent surfaces.
- **Linux**:
  - Client-side shadows with `_GTK_FRAME_EXTENTS` for window manager snapping and positioning.
  - Smooth native dragging via EWMH `_NET_WM_MOVERESIZE`.
  - Automatic Xcursor theme loading (Breeze, Adwaita, etc.) with bidirectional resize cursors.
  - Multi-touch touchscreen gestures via XInput2 (XI2).

## Release Packaging & ProGuard

When building release distributions with ProGuard (`./gradlew packageReleaseDistributable`), preserve JNA and `miuix-window` symbols.

Add to `build.gradle.kts`:

```kotlin
compose.desktop {
    application {
        buildTypes.release.proguard {
            configurationFiles.from(project.file("proguard-rules.pro"))
        }
    }
}
```

Add to `proguard-rules.pro`:

```pro
# JNA
-keep class com.sun.jna.** { *; }
-keepclassmembers class * extends com.sun.jna.** { *; }
-keep interface * extends com.sun.jna.** { *; }
-keep class * implements com.sun.jna.Callback { *; }
-keep class * implements com.sun.jna.Library { *; }
-keep class * implements com.sun.jna.Structure { *; }
-keep class * extends com.sun.jna.Structure { *; }
-keepclassmembers class * extends com.sun.jna.Structure { <fields>; }
-keep class * extends com.sun.jna.NativeLong { *; }
-dontwarn com.sun.jna.**
-dontnote com.sun.jna.**

# miuix-window
-keep class top.yukonga.miuix.kmp.window.** { *; }
-dontnote top.yukonga.miuix.**
```

> **Note**: If using `dev.nucleusframework`, JNA rules are automatically injected.

### Pruning Foreign Native Libraries (Optional)

JNA bundles native binaries for ~20 platforms (~1.5 MB total). To reduce distribution size, strip foreign binaries during packaging:

```kotlin
import java.util.zip.ZipEntry
import java.util.zip.ZipFile
import java.util.zip.ZipOutputStream
import org.jetbrains.compose.desktop.application.tasks.AbstractProguardTask

tasks.withType<AbstractProguardTask>().configureEach {
    doLast {
        val osName = System.getProperty("os.name").lowercase()
        val osArch = System.getProperty("os.arch").lowercase()

        val jnaTarget = when {
            osName.contains("linux") && (osArch == "x86_64" || osArch == "amd64") -> "linux-x86-64"
            osName.contains("linux") && (osArch == "aarch64" || osArch == "arm64") -> "linux-aarch64"
            osName.contains("windows") && (osArch == "x86_64" || osArch == "amd64") -> "win32-x86-64"
            osName.contains("windows") && (osArch == "aarch64" || osArch == "arm64") -> "win32-aarch64"
            osName.contains("mac") && (osArch == "x86_64" || osArch == "amd64") -> "darwin-x86-64"
            osName.contains("mac") && (osArch == "aarch64" || osArch == "arm64") -> "darwin-aarch64"
            else -> null
        }

        if (jnaTarget != null) {
            val nativeExts = listOf(".so", ".dll", ".dylib", ".a")
            destinationDir.asFile.get().listFiles()?.filter { it.extension == "jar" }?.forEach { jarFile ->
                val tempFile = File(jarFile.parentFile, jarFile.name + ".tmp")
                var modified = false
                ZipFile(jarFile).use { zipIn ->
                    val hasForeign = zipIn.entries().asSequence().any { entry ->
                        val name = entry.name
                        val isNative = nativeExts.any { name.endsWith(it) }
                        name.startsWith("com/sun/jna/") && !name.contains(jnaTarget) && isNative
                    }
                    if (hasForeign) {
                        ZipOutputStream(tempFile.outputStream()).use { zipOut ->
                            for (entry in zipIn.entries()) {
                                val name = entry.name
                                val isNative = nativeExts.any { name.endsWith(it) }
                                val isForeignJna = name.startsWith("com/sun/jna/") && !name.contains(jnaTarget) && isNative
                                if (!isForeignJna) {
                                    zipOut.putNextEntry(ZipEntry(name))
                                    zipIn.getInputStream(entry).copyTo(zipOut)
                                    zipOut.closeEntry()
                                }
                            }
                        }
                        modified = true
                    }
                }
                if (modified) {
                    jarFile.delete()
                    tempFile.renameTo(jarFile)
                }
            }
        }
    }
}
```

> **Note**: In `dev.nucleusframework`, simply enable `nativeDistributions { cleanupNativeLibs = true }`.
