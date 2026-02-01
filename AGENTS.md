# Miuix Agent Guide

## Agent Quick Start

- For significant features or refactors, sketch an Plan first; keep it updated as you work.
- Default to `rg` for searching and keep edits ASCII unless the file already uses non-ASCII.
- Run the component-specific checks below before handing work off; do not skip failing steps.
- Use Context7 to pull library/API docs when you touch unfamiliar Compose/Android/JVM/Js/WasmJs/Swift APIs or deps.

---

## Project Overview

**Miuix** is a UI component library built with **Compose Multiplatform**.

- **Supported Platforms**: Android, iOS, Desktop (JVM), MacOS, Web (Wasm/JS).

---

### Key Commands

| Action                      | Command                                                 |
| :-------------------------- | :------------------------------------------------------ |
| **Build Project**           | `./gradlew assemble`                                    |
| **Run Tests**               | `./gradlew check`                                       |
| **Check Formatting**        | `./gradlew spotlessCheck`                               |
| **Fix Formatting**          | `./gradlew spotlessApply`                               |
| **Run Android Demo**        | `./gradlew :example:android:installDebug`               |
| **Run Desktop (JVM) Demo**  | `./gradlew :example:desktop:hotRunDesktop --auto`       |
| **Run Web (WasmJs) Demo**   | `./gradlew :example:wasmJs:wasmJsBrowserRun`            |
| **Run Web (Js) Demo**       | `./gradlew :example:js:jsBrowserDevelopmentRun`         |
| **Run macOS (Native) Demo** | `./gradlew :example:macos:runDebugExecutableMacosArm64` |

---

## Repository Structure

| Directory                  | Description                                                              |
| :------------------------- | :----------------------------------------------------------------------- |
| **`miuix/`**               | **Core Library**. Contains all UI components.                            |
| **`miuix-icons/`**         | Extended icon resources.                                                 |
| **`miuix-navigation3-*/`** | Custom Navigation 3.0 implementation and adapters.                       |
| **`example/`**             | **Demo Application**. Multiplatform app to showcase and test components. |
| **`docs/`**                | Documentation site (VitePress).                                          |
| **`build-plugins/`**       | Custom Gradle plugins for build logic reuse.                             |
| **`gradle/`**              | Version catalog (`libs.versions.toml`) and wrapper.                      |

---

## Core Concepts

### 1. Multiplatform Architecture

Code is organized into source sets to maximize sharing while allowing platform-specific implementations:

- **`commonMain`**: 99% of UI logic. Pure Compose code.
- **`androidMain`**: Android-specific implementations.
- **`skikoMain`**: Skiko-specific implementations.
- **`desktopMain`**: Desktop-specific implementations.
- **`jsMain` / `wasmJsMain`**: Web-specific implementations.
- **`iosMain` / `macosMain`**: Darwin-specific implementations.

### 2. Theming

The project uses a custom theming system found in `miuix.kmp.theme`. Components rely on `MaterialKolor` for dynamic color generation.

### 3. Navigation

The project uses **AndroidX Navigation 3** (alpha) ported to KMP. Key modules:

- `miuix-navigation3-ui`: UI hosting logic.
- `miuix-navigation3-adaptive`: Adaptive layouts (list-detail, etc.).

---

## Workflows

### 1. Adding a New Component

1.  **Plan**: Define the API in `miuix/src/commonMain/kotlin/top/yukonga/miuix/kmp/basic/`.
2.  **Implement**: Create the `@Composable` function. Use `Modifier` as the first optional argument.
3.  **Preview**: Add a demo page in `example/shared/src/commonMain/kotlin/`.
4.  **Register**: Add the demo to `HomePage.kt` in the example app.
5.  **Verify**: Run the example app on at least Android and Desktop.

### 2. Fixing Bugs

1.  **Reproduce**: Create a reproduction case in the `example` app.
2.  **Fix**: Apply the fix in `miuix/`.
3.  **Test**: Verify the fix across platforms if the bug was platform-specific.

### 3. Updating Documentation

1.  Edit Markdown files in `docs/`.
2.  Run `npm run docs:dev` in `docs/` (if Node.js is available) or rely on PR previews.

---

## Common Pitfalls

### Spotless Violations

**Issue**: CI fails due to formatting.
**Fix**: **ALWAYS** run `./gradlew spotlessApply` before committing.

---

## Git Commit

- Mirror existing history style: `<scope>: <summary>` with a short lowercase scope tied to the touched area (e.g., `library`, `docs`, `example`). Keep the summary concise, sentence case, and avoid trailing period.
- For doc-related changes use `docs`; for build-related changes use `build`; for fix-related changes use `fix`; for depsdependency-related changes use `fix(deps)`; for gradle-related changes use `chore(deps)`.
- Keep subject lines brief (target ≤ 72 characters); briefly describe specific modifications in the body.
- If referencing a PR/issue, append `(#1234)` at the end as seen in history.
- Before committing, glance at recent `git log --oneline` to stay consistent with current prefixes and capitalization used in this repo.
