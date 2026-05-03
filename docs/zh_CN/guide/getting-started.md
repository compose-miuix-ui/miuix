# 快速开始

当前支持的平台: **Android** / **Desktop(JVM)** / **iOS** / **WasmJs** / **Js** / **macOS(Native)**

::: warning 注意
此库处于实验阶段，API 可能会在未来版本中变更而不另行通知
:::

## 添加依赖

要在您的项目中使用 Miuix，请按照以下步骤添加依赖：

### Gradle (Kotlin DSL)

1. 在根目录的 settings.gradle.kts 添加（正常情况应已包含）：

```kotlin
repositories {
    mavenCentral()
}
```

2. 检查 Maven Central 当前最新版本：
   [![Maven Central](https://img.shields.io/maven-central/v/top.yukonga.miuix.kmp/miuix-ui)](https://search.maven.org/search?q=g:top.yukonga.miuix.kmp)

3. 在项目的 build.gradle.kts 中添加依赖：

Miuix 由多个可独立使用的模块组成：

| 模块 | 说明 |
|---|---|
| `miuix-ui` | 核心 UI 组件库（自动包含 `miuix-core`） |
| `miuix-preference` | Preference 组件（SwitchPreference、CheckboxPreference 等），依赖 `miuix-ui` |
| `miuix-icons` | 扩展图标库，可独立使用，也可与 `miuix-ui` 同时使用（自动包含 `miuix-core`） |
| `miuix-blur` | 模糊效果库，可独立使用 |
| `miuix-navigation3-ui` | Navigation3 UI 库，可独立使用 |

- 在 Compose Multiplatform 项目目录的 build.gradle.kts 中：

```kotlin
kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation("top.yukonga.miuix.kmp:miuix-ui:<version>")
            // 可选：添加 miuix-preference 以获取 Preference 组件
            implementation("top.yukonga.miuix.kmp:miuix-preference:<version>")
            // 可选：添加 miuix-icons 以获取更多图标
            implementation("top.yukonga.miuix.kmp:miuix-icons:<version>")
            // 可选：添加 miuix-blur 以获取模糊效果
            implementation("top.yukonga.miuix.kmp:miuix-blur:<version>")
            // 可选：添加 miuix-navigation3-ui 以获取 Navigation3 支持
            implementation("top.yukonga.miuix.kmp:miuix-navigation3-ui:<version>")
        }
    }
}
```

- 在 Android Compose 项目目录的 build.gradle.kts 中：

```kotlin
dependencies {
    implementation("top.yukonga.miuix.kmp:miuix-ui-android:<version>")
    // 可选：添加 miuix-preference 以获取 Preference 组件
    implementation("top.yukonga.miuix.kmp:miuix-preference-android:<version>")
    // 可选：添加 miuix-icons 以获取更多图标
    implementation("top.yukonga.miuix.kmp:miuix-icons-android:<version>")
    // 可选：添加 miuix-blur 以获取模糊效果（需要 minSdk 31）
    implementation("top.yukonga.miuix.kmp:miuix-blur-android:<version>")
    // 可选：添加 miuix-navigation3-ui 以获取 Navigation3 支持
    implementation("top.yukonga.miuix.kmp:miuix-navigation3-ui-android:<version>")
}
```

- 在其他常规项目中使用，则只需要根据需要添加对应平台后缀的依赖即可：

```kotlin
implementation("top.yukonga.miuix.kmp:miuix-ui-iosarm64:<version>")
implementation("top.yukonga.miuix.kmp:miuix-ui-iossimulatorarm64:<version>")
implementation("top.yukonga.miuix.kmp:miuix-ui-macosarm64:<version>")
implementation("top.yukonga.miuix.kmp:miuix-ui-desktop:<version>")
implementation("top.yukonga.miuix.kmp:miuix-ui-wasmjs:<version>")
implementation("top.yukonga.miuix.kmp:miuix-ui-js:<version>")
// 可选：添加 miuix-blur
implementation("top.yukonga.miuix.kmp:miuix-blur-iosarm64:<version>")
implementation("top.yukonga.miuix.kmp:miuix-blur-iossimulatorarm64:<version>")
implementation("top.yukonga.miuix.kmp:miuix-blur-macosarm64:<version>")
implementation("top.yukonga.miuix.kmp:miuix-blur-desktop:<version>")
implementation("top.yukonga.miuix.kmp:miuix-blur-wasmjs:<version>")
implementation("top.yukonga.miuix.kmp:miuix-blur-js:<version>")
// 可选：添加 miuix-navigation3-ui
implementation("top.yukonga.miuix.kmp:miuix-navigation3-ui-iosarm64:<version>")
implementation("top.yukonga.miuix.kmp:miuix-navigation3-ui-iossimulatorarm64:<version>")
implementation("top.yukonga.miuix.kmp:miuix-navigation3-ui-macosarm64:<version>")
implementation("top.yukonga.miuix.kmp:miuix-navigation3-ui-desktop:<version>")
implementation("top.yukonga.miuix.kmp:miuix-navigation3-ui-wasmjs:<version>")
implementation("top.yukonga.miuix.kmp:miuix-navigation3-ui-js:<version>")
// 可选：添加 miuix-icons
implementation("top.yukonga.miuix.kmp:miuix-icons-iosarm64:<version>")
implementation("top.yukonga.miuix.kmp:miuix-icons-iossimulatorarm64:<version>")
implementation("top.yukonga.miuix.kmp:miuix-icons-macosarm64:<version>")
implementation("top.yukonga.miuix.kmp:miuix-icons-desktop:<version>")
implementation("top.yukonga.miuix.kmp:miuix-icons-wasmjs:<version>")
implementation("top.yukonga.miuix.kmp:miuix-icons-js:<version>")
```

## 基本用法

### 应用 Miuix 主题

```kotlin
@Composable
fun AppTheme(
    content: @Composable () -> Unit
) {
    // 可用模式: System, Light, Dark, MonetSystem, MonetLight, MonetDark
    val controller = remember { ThemeController(ColorSchemeMode.System) }
    return MiuixTheme(
        controller = controller,
        content = content
    )
}
```

### 使用 Miuix 脚手架

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

::: warning 注意
Scaffold 组件为跨平台提供了一个合适的弹出窗口的容器。`OverlayDialog`、`OverlayDropdownPreference`、`OverlaySpinnerPreference`、
`OverlayListPopup` 等组件都基于此实现弹出窗口，因此都需要被该组件包裹。
:::

## API 文档

- 查看 [API 文档](/miuix/dokka/index.html){target="_blank"}，此文档使用 Dokka 生成，包含了所有 API
  的详细信息。
