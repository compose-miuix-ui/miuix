# 窗口与标题栏

`miuix-window` 是专为 Compose Multiplatform 桌面端（Desktop JVM）打造的窗口样式与自绘标题栏模块，支持 **Windows 10/11**、**macOS** 与 **Linux (X11 & Wayland)**。

提供平台原生融合能力，包括 Windows 11 Snap Layouts 贴靠布局、DWM Mica / Acrylic 材质、macOS 红绿灯按钮垂直居中与 Vibrancy 材质联动、Linux 客户端阴影（CSD），以及多点触摸屏输入支持。

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

### Linux JVM 参数配置

在 Linux 平台，加载系统主题硬件光标（Breeze、Adwaita 等）与 XInput2 触控焦点需要访问 AWT 内部包。推荐在应用模块的 `build.gradle.kts` 中添加：

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

## 基本用法

使用 `MiuixWindow` 替代 Compose 原生 `Window`，并结合 `MiuixCaptionBar` 与 `WindowDraggableArea` 构建自定义标题栏：

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
                            .height(captionBarHeight),
                    ) {
                        WindowDraggableArea()
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

> 将应用的 `MiuixTheme` 放在 `MiuixWindow` 外层，使窗口装饰、自绘标题栏和内部内容共享同一套配色。`backgroundIsDarkTheme` 仅控制原生背景材质的深浅色外观，不会覆盖 `MiuixTheme.colorScheme.surface`。

## 核心组件

### `MiuixWindow` 与 `MiuixDialogWindow`

Compose 原生 `Window` 与 `DialogWindow` 的强化替代容器。自动接管跨平台非客户区扩展、输入消息拦截与原生材质处理。

### `MiuixCaptionBar` 与 `WindowDraggableArea`

- **`MiuixCaptionBar`**：自适应标题栏容器，macOS 端自动向右让出红绿灯按钮空间，Windows 端避让右侧控制按钮，Linux 端按照系统配置避让控制按钮。
- **`WindowDraggableArea`**：放置在交互控件底层，负责捕获窗口拖拽，不拦截子控件（如搜索栏、按钮）的点击事件。

> **约束说明**：每个 `MiuixWindow` 或 `MiuixDialogWindow` 作用域内仅允许存在**一个**活跃的 `WindowDraggableArea`。子窗口与对话框拥有独立的注册作用域。请避免在重叠动效分支中（如 `AnimatedContent` 或 `Crossfade`）重复声明拖拽区域，建议保持在窗口顶层单一声明。

### `MiuixWindowProperties` 属性配置

| 属性 | 类型 | 默认值 | 说明 |
| :--- | :--- | :--- | :--- |
| `captionBarHeight` | `Dp` | `defaultCaptionBarHeight()` | 标题栏高度（Windows 与 Linux 默认为 32.dp，macOS 默认为 40.dp） |
| `captionButtonHeight` | `Dp` | `captionBarHeight` | 控制按钮高度 |
| `captionButtonsVisible` | `Boolean` | `true` | 控制按钮是否可见 |
| `captionButtonIsDarkTheme` | `Boolean?` | `null` | 控制按钮深浅色外观（`null` 时跟随系统） |
| `backgroundType` | `MiuixWindowBackgroundType` | `None` | 原生背景材质：`None`、`Mica`、`Acrylic`、`MicaAlt` 或 `Vibrancy` |
| `backgroundIsDarkTheme` | `Boolean?` | `null` | 原生材质深浅色（`null` 时跟随系统） |
| `moveable` | `Boolean` | `true` | 是否支持标题栏拖拽移动 |
| `minimizeButtonEnabled` | `Boolean` | `true` | 是否启用最小化按钮 |
| `maximizeOrRestoreButtonEnabled` | `Boolean` | `true` | 是否启用最大化/还原操作 |
| `minSize` | `DpSize` | `DpSize.Zero` | 窗口最小尺寸约束 |
| `extraDisplayScale` | `Float` | `1.0f` | 额外的 UI 缩放比例因子 |
| `extraFontScale` | `Float` | `1.0f` | 额外的字体缩放比例因子 |
| `onResizeEdgeChange` | `(Window, WindowResizeEdge) -> Unit` | `{ _, _ -> }` | 鼠标进入/离开拉伸边缘的回调 |
| `onVisibleChange` | `(Window, Boolean) -> Unit` | `{ _, _ -> }` | 窗口可见性改变的回调 |

## 跨平台特性说明

- **Windows**：
  - 自绘最大化按钮悬停上报 `HTMAXBUTTON`，原生呼出 Windows 11 Snap Layouts 贴靠菜单。
  - 支持 DWM Mica、Mica Alt 和 Acrylic 原生材质。
  - 支持基于 `WM_POINTER` 的触摸屏多指触控与拖动手势。
- **macOS**：
  - 动态在 Y 轴上垂直居中原生红绿灯交通灯按钮以贴合 `captionBarHeight`。
  - 窗口底层应用 `NSVisualEffectView` 实现 Vibrancy 毛玻璃半透明材质。
- **Linux**：
  - 客户端阴影（CSD）配合 `_GTK_FRAME_EXTENTS` 实现窗口管理器吸附贴靠。
  - 标题栏拖拽使用 EWMH `_NET_WM_MOVERESIZE` 原生交互式移动。
  - 自动加载系统当前光标主题（如 Breeze、Adwaita）的双向硬件拉伸光标。
  - 支持基于 XInput2 (XI2) 的多点触控与拖动手势。

## Release 打包与混淆（ProGuard）

使用 Compose Desktop 打包 Release 分发包（如 `./gradlew packageReleaseDistributable`）且开启 ProGuard 时，需保留 JNA 与 `miuix-window` 相关符号。

在应用模块的 `build.gradle.kts` 中添加：

```kotlin
compose.desktop {
    application {
        buildTypes.release.proguard {
            configurationFiles.from(project.file("proguard-rules.pro"))
        }
    }
}
```

在 `proguard-rules.pro` 中加入规则：

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

> **提示**：如果项目使用 `dev.nucleusframework` 插件，Nucleus 插件会自动注入 JNA 规则，无需手动配置。

### 剔除非目标平台原生库（可选体积优化）

JNA 官方 JAR 包含 20 余个平台的原生库（约 1.5 MB）。如需缩减最终安装包体积，可在 `build.gradle.kts` 中配置打包任务剥离当前目标架构之外的原生库：

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

> **提示**：如果使用 `dev.nucleusframework` 插件，仅需在 `nativeDistributions { cleanupNativeLibs = true }` 中声明，Nucleus 会自动剔除无关原生库。
