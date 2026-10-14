# miuix-window

`miuix-window` is a desktop-only window styling and custom caption bar module for Compose Multiplatform, targeting **Windows (10/11)**, **macOS**, and **Linux (X11 & Wayland)**.

It provides native desktop integrations such as Windows 11 Snap Layouts, DWM Mica/Acrylic materials, macOS traffic lights layout and Vibrancy, and Linux client-side shadow decorations with smooth window dragging and themed resizing.

## Setup

Add the dependency to your `build.gradle.kts` in `desktopMain`:

```kotlin
kotlin {
    sourceSets {
        desktopMain.dependencies {
            implementation("top.yukonga.miuix.kmp:miuix-window:<version>")
        }
    }
}
```

## Basic Usage

Replace `Window` with `MiuixWindow` and use `MiuixCaptionBar` to build an integrated title bar:

Place the app's `MiuixTheme` outside `MiuixWindow` so the window background, caption bar, and content share the same color scheme. The solid window background always uses `MiuixTheme.colorScheme.surface`; `backgroundIsDarkTheme` controls the native backdrop appearance without replacing the theme background color.

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
                            .height(captionBarHeight)
                            .background(MiuixTheme.colorScheme.surface),
                    ) {
                        CaptionBarHitTest()
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
                Box(modifier = Modifier.weight(1f).fillMaxWidth()) {
                    Text("Hello Miuix Window", modifier = Modifier.align(Alignment.Center))
                }
            }
        }
    }
}
```

## Core Components

### `MiuixWindow` / `MiuixDialogWindow`

Drop-in containers for standard Compose `Window` and `DialogWindow`. They automatically setup platform-specific styling hooks, input handling, and non-client region geometry.

### `MiuixCaptionBar` & `CaptionBarHitTest`

- **`MiuixCaptionBar`**: Automatically reserves space based on platform button alignment (left on macOS for traffic lights, right on Windows/Linux for minimize/maximize/close buttons).
- **`CaptionBarHitTest`**: Placed behind or around interactive elements in the caption bar to capture native drag-to-move gestures without intercepting clicks on buttons or search fields.

### `MiuixWindowProperties`

Configures window appearance and behavior:

| Property | Type | Description |
|---|---|---|
| `captionBarHeight` | `Dp` | Height of the caption bar (`defaultCaptionBarHeight()`: 32.dp on Linux and Windows, 40.dp on macOS) |
| `captionButtonHeight` | `Dp` | Height of caption buttons (defaults to `captionBarHeight`) |
| `captionButtonsVisible` | `Boolean` | Whether caption buttons are visible (default `true`) |
| `captionButtonIsDarkTheme` | `Boolean?` | Dark appearance for caption buttons (`null` to follow system dynamically) |
| `backgroundType` | `MiuixWindowBackgroundType` | Native backdrop: `None`, `Mica`, `Acrylic`, `MicaAlt`, or `Vibrancy` |
| `backgroundIsDarkTheme` | `Boolean?` | Dark appearance for native backdrop (`null` to follow system dynamically) |
| `moveable` | `Boolean` | Whether native caption dragging is enabled (default `true`) |
| `minSize` | `DpSize` | Minimum window size constraints |
| `onResizeEdgeChange` | `(Window, WindowResizeEdge) -> Unit` | Callback when cursor enters/leaves resize borders |

## Platform Specifics

- **Windows**: Intercepts `WM_NCHITTEST` and reports `HTMAXBUTTON` over the custom maximize button, triggering Windows 11 Snap Layouts flyout menus natively.
- **macOS**: Repositions the native traffic light buttons vertically to align with `captionBarHeight` and applies `NSVisualEffectView` Vibrancy behind the transparent window.
- **Linux**: Applies `_GTK_FRAME_EXTENTS` for proper window snapping and shadows, initiates `_NET_WM_MOVERESIZE` for smooth window manager moves, and uses native Xcursor theme cursors.
