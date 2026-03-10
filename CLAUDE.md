# Miuix

Compose Multiplatform UI component library. Targets Android, iOS, Desktop (JVM), macOS, Web (Wasm/JS).

## Quick Start

- For significant features or refactors, sketch an Plan first; keep it updated as you work.
- Run the component-specific checks below before handing work off; do not skip failing steps.
- Use Context7 to pull library/API docs when you touch unfamiliar Compose/Android/JVM/Js/WasmJs/Swift APIs or deps.
- Always use Chinese to communicate with users and output Plan content, but the generated code (including comments and KDoc) must remain in English.

## Key Commands

| Action              | Command                                                 |
| :------------------ | :------------------------------------------------------ |
| Build               | `./gradlew assemble`                                    |
| Test                | `./gradlew check`                                       |
| Check formatting    | `./gradlew spotlessCheck`                               |
| **Fix formatting**  | `./gradlew spotlessApply`                               |
| Run Android demo    | `./gradlew :example:android:installDebug`               |
| Run Desktop demo    | `./gradlew :example:desktop:hotRunDesktop --auto`       |
| Run WasmJs demo     | `./gradlew :example:wasmJs:wasmJsBrowserRun`            |
| Run Js demo         | `./gradlew :example:js:jsBrowserDevelopmentRun`         |
| Run macOS demo      | `./gradlew :example:macos:runDebugExecutableMacosArm64` |

**ALWAYS run `./gradlew spotlessApply` before committing.** CI will reject formatting violations.

## Repository Structure

| Directory                | Purpose                                           |
| :----------------------- | :------------------------------------------------ |
| `miuix/`                 | Core library — all UI components                  |
| `miuix-icons/`           | Extended icon resources                           |
| `miuix-navigation3-ui/`  | Navigation 3 UI implementation                    |
| `example/`               | Demo app — showcases and tests all components     |
| `docs/`                  | VitePress documentation site                      |
| `build-plugins/`         | Custom Gradle plugins for build logic reuse       |
| `gradle/`                | Version catalog (`libs.versions.toml`) and wrapper|

### Component Source Layout

```
miuix/src/commonMain/kotlin/top/yukonga/miuix/kmp/
├── basic/       # Fundamental components (Button, Switch, TextField, Surface, etc.)
├── extra/       # Composite components (SuperArrow, SuperCheckbox, SuperDropdown, etc.)
├── theme/       # MiuixTheme, Colors, TextStyles, ThemeController
├── color/       # Color utilities, Material Color
├── anim/        # Animation utilities
├── utils/       # General utilities
├── icon/        # Icons
└── interfaces/
```

### Platform Source Sets

```
commonMain
├── skikoMain (Skiko rendering)
│   ├── darwinMain (iOS + macOS)
│   │   ├── iosMain
│   │   └── macosMain
│   ├── desktopMain (JVM)
│   └── webMain
│       ├── wasmJsMain
│       └── jsMain
```

99% of UI logic lives in `commonMain`. Only use platform source sets for genuinely platform-specific code.

## Code Style

- **Formatter**: Spotless + ktlint 1.8.0 with Compose rules (`io.nlopez.compose.rules:ktlint:0.5.6`)
- **License header** (required on all `.kt` and `.kts` files):

  ```
  // Copyright 2025, compose-miuix-ui contributors
  // SPDX-License-Identifier: Apache-2.0
  ```

- Line endings: platform-native
- Composable function names may use PascalCase (ktlint rule disabled for `@Composable`)

## API Conventions

### Composable Function Signature

Follow this parameter ordering:

```kotlin
@Composable
@NonRestartableComposable
fun ComponentName(
    // 1. Required parameters (functional callbacks first)
    onClick: () -> Unit,
    // 2. Modifier
    modifier: Modifier = Modifier,
    // 3. Boolean flags
    enabled: Boolean = true,
    // 4. Visual parameters with defaults from ComponentDefaults
    cornerRadius: Dp = ComponentDefaults.CornerRadius,
    minWidth: Dp = ComponentDefaults.MinWidth,
    minHeight: Dp = ComponentDefaults.MinHeight,
    colors: ComponentColors = ComponentDefaults.componentColors(),
    insideMargin: PaddingValues = ComponentDefaults.InsideMargin,
    // 5. Content lambda (always last)
    content: @Composable () -> Unit,
)
```

### Defaults Object

Each component provides a `ComponentDefaults` object:

```kotlin
object ButtonDefaults {
    val MinWidth = 58.dp                    // Constant dimensions as val
    val CornerRadius = 16.dp

    @Composable
    fun buttonColors(                       // Color factories must be @Composable
        color: Color = MiuixTheme.colorScheme.secondaryVariant,
        disabledColor: Color = MiuixTheme.colorScheme.disabledSecondaryVariant,
    ): ButtonColors = remember(color, disabledColor) {
        ButtonColors(color = color, disabledColor = disabledColor)
    }
}
```

### Colors Data Class

```kotlin
@Immutable
data class ComponentColors(
    val color: Color,
    val disabledColor: Color,
)
```

### Key Patterns

- **`rememberUpdatedState`** for callback lambdas: `val currentOnClick by rememberUpdatedState(onClick)`
- **`remember` with keys** for derived values: `val shape = remember(cornerRadius) { RoundedRectangle(cornerRadius) }`
- **`@NonRestartableComposable`** on component functions that delegate entirely to other composables
- **`@Immutable`** on color/style data classes
- **Shapes**: Use `com.kyant.shapes.RoundedRectangle` / `Capsule`, not Compose's built-in shapes
- **Theme colors**: Always use `MiuixTheme.colorScheme.*`, never hardcode colors
- **Text styles**: Always use `MiuixTheme.textStyles.*` (e.g., `MiuixTheme.textStyles.button`)

## Critical Constraints

### Do NOT Replace Custom Layout in Component.kt

`BasicComponent` (`basic/Component.kt`) uses a custom `Layout` with intrinsic measurement and a weighted distribution algorithm (2:5:3). **Do NOT replace it with `Row + weight(1f)`** — this was tried and caused catastrophic layout breakage (text rendered as single vertical characters) when start/end content overflows.

### Performance

- `LaunchedEffect` keys: only include values actually read in the effect body
- `minIntrinsicWidth`/`maxIntrinsicWidth` triggers full subtree traversal — defer to overflow branch when possible
- Use `@Stable` on frequently-accessed properties in data classes

## Workflows

### Adding a New Component

1. Create the `@Composable` function in `miuix/src/commonMain/kotlin/top/yukonga/miuix/kmp/basic/` (or `extra/` for composite components)
2. Follow API conventions above (parameter ordering, Defaults object, Colors data class)
3. Add a demo section in `example/shared/src/commonMain/kotlin/component/`
4. Register the demo in the example app
5. Verify on at least Android and Desktop

Use `/create-component` to scaffold a new component.

### Fixing Bugs

1. Reproduce in the `example` app
2. Fix in `miuix/`
3. If platform-specific, verify the fix across affected platforms

## Git Commit Style

Format: `<scope>: <summary>`

- Scopes: `library`, `docs`, `example`, `build`, `fix`, `fix(deps)`, `chore(deps)`
- Keep subject line ≤ 72 characters, sentence case, no trailing period
- Reference PRs/issues as `(#1234)` at end
- Check recent `git log --oneline` to stay consistent with current conventions
- **Run `./gradlew spotlessApply` before every commit**
