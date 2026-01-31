# Copilot Coding Agent Instructions for Miuix

## Repository Overview

**Miuix** is a Compose Multiplatform UI library providing MIUI-styled components. It supports Android, iOS, macOS (Native), Desktop (JVM), WasmJs, and JavaScript platforms.

- **Language**: Kotlin
- **Framework**: Compose Multiplatform (JetBrains), Kotlin Multiplatform (KMP)
- **Build System**: Gradle (Kotlin DSL)
- **Kotlin Version**: 2.3.0
- **Compose Version**: 1.10.0
- **JDK Requirement**: 21

## Build Commands

> **⚠️ IMPORTANT**: Always run all commands from the repository root directory.

### Check/Lint (spotless)

```bash
./gradlew spotlessCheck    # Check code formatting
./gradlew spotlessApply    # Auto-fix formatting issues
```

Spotless is applied to all subprojects. Always run `./gradlew spotlessApply` before committing to ensure code formatting passes CI.

### Build Library

```bash
./gradlew build            # Build all modules
./gradlew :miuix:build     # Build main library only
```

### Run Tests

CI runs these test targets:

```bash
./gradlew check                          # Runs full check (macOS only in CI)
./gradlew :miuix:desktopTest             # Desktop JVM tests
./gradlew :miuix:wasmJsBrowserTest       # WasmJs browser tests
./gradlew :miuix:iosSimulatorArm64Test   # iOS simulator tests (requires macOS)
./gradlew :miuix:assembleAndroidTest     # Android test assembly
```

### Build Example Apps

```bash
./gradlew createReleaseDistributable     # Desktop distributable
./gradlew assembleDebug                  # Android debug APK
./gradlew assembleRelease                # Android release APK
```

## Project Structure

```
miuix/                  # Root
├── build-plugin/       # Custom Gradle convention plugins
│   └── src/main/kotlin/
│       ├── BuildConfig.kt              # Version constants, SDK configs
│       ├── root.build-plugin.gradle.kts # JVM toolchain, spotless setup
│       ├── module.spotless.gradle.kts   # Spotless/ktlint configuration
│       └── module.publication.gradle.kts # Maven publishing config
├── miuix/              # Main UI library module
│   ├── build.gradle.kts
│   └── src/
│       ├── commonMain/kotlin/top/yukonga/miuix/kmp/
│       │   ├── anim/       # Animation utilities
│       │   ├── basic/      # Basic components (Button, Text, etc.)
│       │   ├── color/      # Color utilities and schemes
│       │   ├── extra/      # Extended components
│       │   ├── icon/       # Icon components
│       │   ├── interfaces/ # Common interfaces
│       │   ├── theme/      # MiuixTheme, ThemeController
│       │   └── utils/      # Utility functions
│       ├── androidMain/
│       ├── desktopMain/
│       ├── iosMain/
│       ├── macosMain/
│       ├── wasmJsMain/
│       └── jsMain/
├── miuix-icons/        # Extended icon library module
├── miuix-navigation3-ui/        # Navigation3 integration
├── miuix-navigation3-adaptive/  # Navigation3 adaptive support
├── example/            # Example applications
│   ├── shared/         # Shared code for examples
│   ├── android/        # Android example app
│   ├── desktop/        # Desktop (JVM) example
│   ├── ios/            # iOS example
│   ├── macos/          # macOS example
│   ├── js/             # JavaScript example
│   └── wasmJs/         # WasmJs example
├── docs/               # VitePress documentation site
├── gradle/
│   └── libs.versions.toml  # Version catalog
├── spotless/
│   └── copyright.txt   # License header template
└── .github/workflows/
    ├── test.yml        # CI: Tests on push/PR to main
    ├── example.yml     # CI: Build examples on push to main
    ├── publish.yml     # CI: Publish to GitHub Packages
    └── docs.yml        # Manual: Build documentation
```

## Key Configuration Files

| File | Purpose |
|------|---------|
| `build.gradle.kts` | Root build config, applies build-plugin |
| `settings.gradle.kts` | Module includes, repository config |
| `gradle.properties` | Gradle/Kotlin/Android properties |
| `gradle/libs.versions.toml` | Dependency version catalog |
| `build-plugin/src/main/kotlin/BuildConfig.kt` | Shared version constants |
| `spotless/copyright.txt` | License header template |

## Code Style Requirements

1. **License Header**: All `.kt` and `.kts` files must have:
   ```kotlin
   // Copyright 2025, compose-miuix-ui contributors
   // SPDX-License-Identifier: Apache-2.0
   ```

2. **Formatting**: ktlint 1.8.0 with compose rules. Run `./gradlew spotlessApply` to auto-fix.

3. **Source Set Hierarchy**: The library uses a custom source set hierarchy:
   - `commonMain` → base for all platforms
   - `skikoMain` → shared by desktop, darwin, and web
   - `darwinMain` → shared by iOS and macOS
   - `iosMain` → iOS-specific
   - `macosMain` → macOS-specific
   - `webMain` → shared by wasmJs and js

## CI Validation (test.yml)

Pull requests trigger:
1. `./gradlew check` (macOS runner only)
2. `./gradlew :miuix:desktopTest`
3. `./gradlew :miuix:wasmJsBrowserTest`
4. `./gradlew :miuix:iosSimulatorArm64Test` (macOS runner)
5. `./gradlew :miuix:assembleAndroidTest`

**Always ensure spotless and tests pass locally before pushing.**

## Adding New Components

1. Add component in `miuix/src/commonMain/kotlin/top/yukonga/miuix/kmp/` (appropriate subdirectory)
2. Add platform-specific implementations in respective source sets if needed
3. Run `./gradlew spotlessApply` to format
4. Run `./gradlew :miuix:desktopTest` for basic validation

## Common Issues

- **JDK Version**: Project requires JDK 21. Ensure `JDK_VERSION = 21` in `BuildConfig.kt` matches your environment.
- **Spotless Failures**: Run `./gradlew spotlessApply` before committing.
- **Icon Files**: Icon source files under `/**/icon/**/*.kt` are excluded from spotless formatting.

## Trust These Instructions

Use these instructions directly. Only perform additional searches if information is incomplete or found to be incorrect. The build and validation commands documented here have been verified against the CI workflows.
