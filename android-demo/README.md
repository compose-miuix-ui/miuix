# Miuix Android Demo

[English](#english) | [中文](#中文)

A pure Android (Jetpack Compose) demo app showcasing the **Miuix** UI library — a Compose Multiplatform component library with Xiaomi HyperOS style. This project builds a standalone APK and is configured to build automatically on GitHub Actions.

---

## 中文

### 项目简介

这是一个纯 Android Compose 示例项目，演示 Miuix UI 库（`top.yukonga.miuix.kmp`）的常用布局与组件。使用 Kotlin + Jetpack Compose，`minSdk 24`，通过 GitHub Actions 云端构建 Debug / Release APK。

### 功能演示

| 页面 | 演示内容 |
| :--- | :--- |
| **Basic** | Button / TextButton / Switch / Checkbox / RadioButton / Slider / TextField / ProgressIndicator |
| **List** | Card / Surface / ArrowPreference 列表项 / HorizontalDivider |
| **Settings** | SwitchPreference / OverlayDropdownPreference / SliderPreference / ArrowPreference，以及主题切换 |
| **Dialog** | OverlayDialog / OverlayBottomSheet / Snackbar |

支持**深色模式**（跟随系统）与 **Monet 动态取色**（动态主题色）切换。

### 技术栈

- Kotlin `2.4.0` / AGP `9.2.1` / Gradle `9.6.1` / JetBrains Compose Multiplatform `1.11.1`
- Miuix `0.9.3`（Maven Central）：
  - `top.yukonga.miuix.kmp:miuix-ui-android`
  - `top.yukonga.miuix.kmp:miuix-preference-android`
  - `top.yukonga.miuix.kmp:miuix-icons-android`
- `minSdk 24`，`targetSdk 37`

### 本地运行

```bash
# 进入项目目录（注意：此 demo 是独立 Gradle 工程）
cd android-demo

# 构建 Debug APK
./gradlew :app:assembleDebug

# 构建 Release APK（默认使用 debug 签名；如需正式签名请设置环境变量）
./gradlew :app:assembleRelease

# 安装到已连接的设备
./gradlew :app:installDebug
```

Release 正式签名（可选）：设置以下环境变量后构建，优先使用正式 keystore：

```bash
export KEYSTORE_PATH=/path/to/your.keystore
export KEYSTORE_PASS=your_store_password
export KEY_ALIAS=your_alias
export KEY_PASSWORD=your_key_password
./gradlew :app:assembleRelease
```

### 目录结构

```
android-demo/
├── app/src/main/java/com/example/miuixdemo/
│   ├── MainActivity.kt            # 入口 + 边到边 + 状态栏/导航栏适配
│   └── ui/
│       ├── theme/Theme.kt          # MiuixTheme + ThemeController + 主题模式
│       └── screen/
│           ├── AppRoot.kt          # Scaffold + TopAppBar + NavigationBar 骨架
│           ├── BasicScreen.kt      # 基础组件
│           ├── ListScreen.kt       # 列表与卡片
│           ├── SettingsScreen.kt   # Preference / 设置页
│           └── DialogScreen.kt     # 弹层与反馈
├── gradle/libs.versions.toml       # 版本目录
├── gradle/wrapper/                 # Gradle Wrapper
└── build.gradle.kts / settings.gradle.kts
```

### GitHub Actions 云端构建

Workflow 位于仓库根目录 `.github/workflows/build-apk.yml`：

- **触发**：push 到 `android` 分支 或 手动触发（Actions 页面 `Run workflow`）
- **产物**：Debug + Release 两个 APK，作为 Artifact 上传（保留 30 天）
- **签名**：默认自动生成 keystore；也可在仓库 Secrets 中配置 `KEYSTORE_PATH` / `KEYSTORE_PASS` / `KEY_ALIAS` / `KEY_PASSWORD` 使用正式签名
- **可选 Releases**：推送 `v*` 标签时自动发布 GitHub Release 并附带 APK

#### 如何触发构建

1. 将代码推送到 `android` 分支：
   ```bash
   git checkout -b android
   git add .
   git commit -m "feat: add miuix android demo"
   git push origin android
   ```
2. 打开仓库 **Actions** 页面查看构建进度。
3. 构建完成后进入该次运行，在 **Artifacts** 区域下载 `miuix-demo-apk`。

### 常见问题

- **报错 `Could not find top.yukonga.miuix...`**：确认 `settings.gradle.kts` 中已配置 `mavenCentral()` 仓库。
- **Release 包无法安装**：本地无签名环境变量时 Release 使用 debug 签名，属正常现象，可直接安装调试。

---

## English

### Overview

A pure Android (Jetpack Compose) demo app for the **Miuix** UI library (a Compose Multiplatform component library with Xiaomi HyperOS design style). The project builds a standalone APK and is configured for cloud building via GitHub Actions.

### Features

| Page | Content |
| :--- | :--- |
| **Basic** | Button / TextButton / Switch / Checkbox / RadioButton / Slider / TextField / ProgressIndicator |
| **List** | Card / Surface / ArrowPreference list items / HorizontalDivider |
| **Settings** | SwitchPreference / OverlayDropdownPreference / SliderPreference / ArrowPreference + theme switching |
| **Dialog** | OverlayDialog / OverlayBottomSheet / Snackbar |

Supports **dark mode** (follow system) and **Monet dynamic color** (dynamic theme color).

### Tech Stack

- Kotlin `2.4.0` / AGP `9.2.1` / Gradle `9.6.1` / JetBrains Compose Multiplatform `1.11.1`
- Miuix `0.9.3` (Maven Central): `miuix-ui-android`, `miuix-preference-android`, `miuix-icons-android`
- `minSdk 24`, `targetSdk 37`

### Run Locally

```bash
cd android-demo
./gradlew :app:assembleDebug   # Debug APK
./gradlew :app:assembleRelease # Release APK
./gradlew :app:installDebug    # Install to device
```

Optional release signing via environment variables:

```bash
export KEYSTORE_PATH=/path/to/your.keystore
export KEYSTORE_PASS=your_store_password
export KEY_ALIAS=your_alias
export KEY_PASSWORD=your_key_password
./gradlew :app:assembleRelease
```

### GitHub Actions

Workflow: `.github/workflows/build-apk.yml` (repo root).

- **Trigger**: push to the `android` branch, or manual `Run workflow`.
- **Artifacts**: Debug + Release APKs uploaded as artifacts (retained 30 days).
- **Signing**: auto-generates a keystore by default; or configure Secrets `KEYSTORE_PATH` / `KEYSTORE_PASS` / `KEY_ALIAS` / `KEY_PASSWORD` for a real signing key.
- **Releases**: pushing a `v*` tag automatically creates a GitHub Release with APKs.

#### How to Build on Cloud

```bash
git checkout -b android
git add .
git commit -m "feat: add miuix android demo"
git push origin android
```

Then open the **Actions** tab, wait for the run, and download the `miuix-demo-apk` artifact.
