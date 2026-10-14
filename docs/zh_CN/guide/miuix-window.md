# miuix-window

`miuix-window` 是专为 Compose Multiplatform 桌面端（Desktop JVM）打造的窗口样式与自绘标题栏模块，全面支持 **Windows (10/11)**、**macOS** 与 **Linux (X11 & Wayland)**。

提供平台原生融合能力，包括 Windows 11 Snap Layouts 贴靠菜单唤起、DWM Mica / Acrylic 材质、macOS 红绿灯按钮垂直居中与 Vibrancy 材质联动、以及 Linux 客户端阴影（CSD）、原生窗口管理器平滑拖拽与主题缩放光标。

## 添加依赖

在模块的 `build.gradle.kts` 中添加 `desktopMain` 依赖：

```kotlin
kotlin {
    sourceSets {
        desktopMain.dependencies {
            implementation("top.yukonga.miuix.kmp:miuix-window:<version>")
        }
    }
}
```

## 基本用法

使用 `MiuixWindow` 替代 Compose 原生 `Window`，并结合 `MiuixCaptionBar` 快速构建自定义标题栏：

将应用的 `MiuixTheme` 放在 `MiuixWindow` 外层，让窗口背景、标题栏和内容使用同一份配色。纯色窗口背景始终使用 `MiuixTheme.colorScheme.surface`；`backgroundIsDarkTheme` 仅控制原生背景材质的深浅色，不会替换主题背景色。

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
            title = "Miuix 桌面端",
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
                                text = "Miuix 桌面端",
                                modifier = Modifier
                                    .align(Alignment.CenterStart)
                                    .padding(start = 16.dp),
                                style = MiuixTheme.textStyles.body2,
                            )
                        }
                    }
                }

                // 主体内容
                Box(modifier = Modifier.weight(1f).fillMaxWidth()) {
                    Text("Hello Miuix Window", modifier = Modifier.align(Alignment.Center))
                }
            }
        }
    }
}
```

## 核心组件与配置

### `MiuixWindow` 与 `MiuixDialogWindow`

Compose 原生 `Window` 与 `DialogWindow` 的强化替代容器。自动接管跨平台非客户区扩展、输入消息拦截与样式处理。

### `MiuixCaptionBar` 与 `CaptionBarHitTest`

- **`MiuixCaptionBar`**：自适应标题栏容器，macOS 端自动向右让出红绿灯按钮空间，Windows 和 Linux 端自动避让右侧最小化、最大化和关闭控制按钮。
- **`CaptionBarHitTest`**：放置在标题栏底层，负责捕获拖拽，避免遮挡上层交互控件（如搜索栏、标签页或操作按钮）。

### `MiuixWindowProperties` 属性配置

| 属性 | 类型 | 说明 |
|---|---|---|
| `captionBarHeight` | `Dp` | 标题栏高度（`defaultCaptionBarHeight()`：Linux 与 Windows 默认为 32.dp，macOS 默认为 40.dp） |
| `captionButtonHeight` | `Dp` | 控制按钮高度（默认与 `captionBarHeight` 一致） |
| `captionButtonsVisible` | `Boolean` | 控制按钮是否可见（默认 `true`） |
| `captionButtonIsDarkTheme` | `Boolean?` | 控制按钮深浅色（传 `null` 时自动跟随系统深浅色动态切换） |
| `backgroundType` | `MiuixWindowBackgroundType` | 原生材质：`None`、`Mica`、`Acrylic`、`MicaAlt` 或 `Vibrancy` |
| `backgroundIsDarkTheme` | `Boolean?` | 原生材质深浅色（传 `null` 时自动跟随系统深浅色动态切换） |
| `moveable` | `Boolean` | 是否支持标题栏拖拽移动（默认 `true`） |
| `minSize` | `DpSize` | 窗口最小尺寸约束 |
| `onResizeEdgeChange` | `(Window, WindowResizeEdge) -> Unit` | 鼠标进入/离开拉伸边缘的回调 |

## 跨平台特性说明

- **Windows**：自绘最大化按钮通过 `WM_NCHITTEST` 上报 `HTMAXBUTTON`，可原生唤起 Windows 11 贴靠布局（Snap Layouts）飞出菜单；支持 DWM Mica 与深浅色无缝联动。
- **macOS**：保留系统级红绿灯交通灯按钮，并在窗口运行时动态将其在 Y 轴上垂直居中对齐 `captionBarHeight`；底层通过 `NSVisualEffectView` 实现 Vibrancy 磨砂半透明背景。
- **Linux**：通过写入 `_GTK_FRAME_EXTENTS` 支持平滑投影与贴靠对齐；标题栏拖拽使用 EWMH `_NET_WM_MOVERESIZE` 原生交互式移动；自动加载系统当前光标主题（如 Breeze、Adwaita）的原生硬件真彩色拉伸光标。
