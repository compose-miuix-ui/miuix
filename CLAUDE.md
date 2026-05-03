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
| Build (full)        | `./gradlew assemble`                                    |
| Build (quick check) | `./gradlew compileKotlinDesktop`                        |
| Test                | `./gradlew check`                                       |
| Check formatting    | `./gradlew spotlessCheck`                               |
| **Fix formatting**  | `./gradlew spotlessApply`                               |
| Run Android demo    | `./gradlew :example:android:installDebug`               |
| Run Desktop demo    | `./gradlew :example:desktop:hotRunDesktop --auto`       |
| Run WasmJs demo     | `./gradlew :example:web:wasmJsBrowserRun`               |
| Run Js demo         | `./gradlew :example:web:jsBrowserDevelopmentRun`        |
| Run macOS demo      | `./gradlew :example:macos:runDebugExecutableMacosArm64` |
| Run iOS demo        | Open `example/ios/iosApp.xcodeproj` in Xcode and run    |

**ALWAYS run `./gradlew spotlessApply` before committing.** CI will reject formatting violations.

## Repository Structure

| Directory                | Purpose                                                        |
| :----------------------- | :------------------------------------------------------------- |
| `miuix-core/`           | Core utilities and MiuixIcons base; depended on by `miuix-ui` and `miuix-icons` |
| `miuix-ui/`             | Main UI library — basic components, theme, colors, animations, overlay/window/layout |
| `miuix-preference/`     | Preference components (SwitchPreference, CheckboxPreference, etc.)                  |
| `miuix-blur/`           | Blur/backdrop effects (Android minSdk=31)                                           |
| `miuix-icons/`          | Extended icon resources                                                             |
| `miuix-navigation3-ui/` | Navigation 3 UI implementation (independent, no miuix dependency)                   |
| `example/`              | Demo app — showcases and tests all components                                       |
| `baselineprofile/`      | Android baseline profile generation                                                 |
| `docs/`                 | VitePress documentation site                                                        |
| `build-plugins/`        | Custom Gradle plugins for build logic reuse                                         |
| `gradle/`               | Version catalog (`libs.versions.toml`) and wrapper                                  |

### Component Source Layout

```
miuix-ui/src/commonMain/kotlin/top/yukonga/miuix/kmp/
├── basic/       # Fundamental components (Button, Switch, TextField, Surface, etc.)
├── overlay/     # Scaffold-hosted overlay components (OverlayDialog, OverlayBottomSheet, etc.)
├── window/      # Platform window components (WindowDialog, WindowBottomSheet, etc.)
├── layout/      # Shared layout components (DialogContentLayout, BottomSheetContentLayout, etc.)
├── theme/       # MiuixTheme, Colors, TextStyles, ThemeController, DynamicColors, etc.
├── color/       # Color utilities, Material Color
├── anim/        # Animation utilities
├── utils/       # General utilities
├── icon/        # Built-in basic icons (ArrowRight, Check, Search, etc.)
└── interfaces/

miuix-preference/src/commonMain/kotlin/top/yukonga/miuix/kmp/
└── preference/  # Preference components (SwitchPreference, CheckboxPreference, ArrowPreference, etc.)
```

### Platform Source Sets

```
commonMain
├── androidMain
└── skikoMain (Skiko rendering — all non-Android targets)
    ├── darwinMain (iOS + macOS)
    │   ├── iosMain
    │   └── macosMain
    ├── desktopMain (JVM)
    └── webMain
        ├── wasmJsMain
        └── jsMain
```

99% of UI logic lives in `commonMain`. Only use platform source sets for genuinely platform-specific code.

## Code Style

- **Formatter**: Spotless + ktlint 1.8.0 with Compose rules (`io.nlopez.compose.rules:ktlint:0.5.6`)
- **License header** (required on all `.kt` and `.kts` files):

  ```
  // Copyright $YEAR, compose-miuix-ui contributors
  // SPDX-License-Identifier: Apache-2.0
  ```

  Spotless auto-fills `$YEAR` with the current year. Do not manually change years in existing file headers.
- **Spotless exclusions**: Icon files (`**/icon/**/*.kt`) and navigation3 files (`**/navigation3/**/*.kt`) are excluded from formatting.
- Line endings: platform-native
- Composable function names may use PascalCase (ktlint rule disabled for `@Composable`)

## API Conventions

### Composable Function Signature

Follow this parameter ordering:

```kotlin
@Composable
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

Add `@NonRestartableComposable` only when the component is a thin wrapper that fully delegates to another composable and reads no state itself — see the Key Patterns below. Do not include it on the default template.

### Defaults Object

Each component provides a `ComponentDefaults` object:

```kotlin
object ButtonDefaults {
    val MinWidth = 58.dp                    // Constant dimensions as val
    val MinHeight = 40.dp
    val CornerRadius = 16.dp
    val InsideMargin = PaddingValues(horizontal = 16.dp, vertical = 13.dp)

    @Composable
    fun buttonColors(                       // Color factories must be @Composable
        color: Color = MiuixTheme.colorScheme.secondaryVariant,
        disabledColor: Color = MiuixTheme.colorScheme.disabledSecondaryVariant,
        contentColor: Color = MiuixTheme.colorScheme.onSecondaryVariant,
        disabledContentColor: Color = MiuixTheme.colorScheme.disabledOnSecondaryVariant,
    ): ButtonColors = remember(color, disabledColor, contentColor, disabledContentColor) {
        ButtonColors(
            color = color,
            disabledColor = disabledColor,
            contentColor = contentColor,
            disabledContentColor = disabledContentColor,
        )
    }
}
```

### Colors Data Class

```kotlin
@Immutable
data class ButtonColors(
    val color: Color,
    val disabledColor: Color,
    val contentColor: Color,
    val disabledContentColor: Color,
)
```

### Key Patterns

- **`rememberUpdatedState`** for values whose latest reading must be visible to a long-lived closure without re-running an effect or rebuilding a Modifier: use inside `LaunchedEffect`/`DisposableEffect` so the effect body sees the newest callback without being keyed on it, and inside `remember { }`-cached lambdas (e.g., the `onClick` you pass to `Modifier.clickable` after wrapping it in `remember`) so the cached body still calls the up-to-date callback. Note: this prevents *stale captures*, it does not stabilize the outer lambda's identity — a `Modifier.clickable { current() }` literal is still a fresh object on each composition. Do NOT use it when forwarding a callback directly to a child composable (e.g., `Button(onClick = onClick)`) — Compose's skip mechanism handles lambda stability there
- **`remember` with keys** for derived values: `val alpha = remember(enabled) { if (enabled) 1f else 0.38f }`
- **`@NonRestartableComposable`** on thin wrapper composables that fully delegate to other composables and read no state themselves; avoid on composables with multiple internal state reads (they benefit from smart recomposition)
- **`@Immutable`** on color/style data classes
- **Shapes**: Use `RoundedCornerShape(cornerRadius)` for rounded corners and `CircleShape` for capsules from `androidx.compose.foundation.shape`
- **Theme colors**: Always use `MiuixTheme.colorScheme.*`, never hardcode colors
- **Text styles**: Always use `MiuixTheme.textStyles.*` (e.g., `MiuixTheme.textStyles.button`)

## Critical Constraints

### Do NOT Replace Custom Layout in Component.kt

`BasicComponent` (`basic/Component.kt`) uses a custom `Layout` with intrinsic measurement and a weighted distribution algorithm (2:5:3). **Do NOT replace it with `Row + weight(1f)`** — this was tried and caused catastrophic layout breakage (text rendered as single vertical characters) when start/end content overflows.

### Performance

- `LaunchedEffect` keys: only include values actually read in the effect body
- `minIntrinsicWidth`/`maxIntrinsicWidth` triggers full subtree traversal — defer to overflow branch when possible
- Use `@Immutable` on truly immutable data classes (all `val`, never mutated, **and** no lambda/callback fields — lambda equality is reference-based and breaks `@Immutable`'s "equals stays equal forever" contract); use `@Stable` for data classes that hold lambdas/callbacks, or for classes whose mutable properties notify Compose via `MutableState`; `@Stable` is also the standard pattern for internal helper functions within `@Immutable` data classes (e.g., `@Stable internal fun color(enabled: Boolean): Color`)
- Standard collections (`List`, `Set`, `Map`) are unstable to Compose. Prefer `kotlinx.collections.immutable` (`ImmutableList`/`PersistentList`) — the Compose compiler recognizes them as stable. Wrapping a raw `List` in an `@Immutable` data class only works if the caller keeps the wrapper's identity stable (e.g., builds it inside a `remember`); otherwise the wrapper is "new" each composition and the `@Immutable` annotation is a false promise to Compose

## Workflows

### Adding a New Component

1. Create the `@Composable` function in `miuix-ui/src/commonMain/kotlin/top/yukonga/miuix/kmp/basic/` (or `preference/` in `miuix-preference` for preference components)
2. Follow API conventions above (parameter ordering, Defaults object, Colors data class)
3. Add a demo section in `example/shared/src/commonMain/kotlin/component/`
4. Register the demo in the example app
5. Verify on at least Android and Desktop

Use `/create-component` to scaffold a new component.

### Modifying a Component

When changing a component's API, defaults, or behavior, check and update all related artifacts:

1. **Documentation** (`docs/components/` and `docs/zh_CN/components/`): update properties tables, Defaults object sections, and code examples in both English and Chinese docs
2. **Docs demo code** (`docs/demo/`): update the interactive demo if it uses changed APIs
3. **Example app** (`example/shared/src/commonMain/kotlin/component/`): update demo code to reflect the changes

### Fixing Bugs

1. Reproduce in the `example` app
2. Fix in `miuix-ui/` or `miuix-preference/`
3. If platform-specific, verify the fix across affected platforms

## Git Commit Style

Format: `<scope>: <summary>`

- Scopes: `library`, `docs`, `example`, `build`, `fix`, `fix(deps)`, `chore(deps)`
- Keep subject line ≤ 72 characters, sentence case, no trailing period
- Reference PRs/issues as `(#1234)` at end
- Check recent `git log --oneline` to stay consistent with current conventions
- **Run `./gradlew spotlessApply` before every commit**
