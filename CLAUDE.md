# Miuix

Compose Multiplatform UI component library. Targets Android, iOS, Desktop (JVM), macOS, Web (Wasm/JS).

## Quick Start

- For significant features or refactors, sketch a plan first and keep it updated as you work.
- Run the component-specific checks below before handing work off; do not skip failing steps.
- Use Context7 to pull library/API docs when you touch unfamiliar Compose/Android/JVM/Js/WasmJs/Swift APIs or deps.

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

## Repository Structure

| Directory               | Purpose                                                                    |
| :---------------------- | :------------------------------------------------------------------------- |
| `miuix-core/`           | Utilities + MiuixIcons base (depended on by `ui` and `icons`)              |
| `miuix-ui/`             | Main UI library                                                            |
| `miuix-preference/`     | Preference / menu / popup components                                       |
| `miuix-shader/`         | Runtime shader / render effect abstraction (used by `blur` and `squircle`) |
| `miuix-blur/`           | Blur effects (Android minSdk=33; depends on `miuix-shader`)                |
| `miuix-squircle/`       | Squircle corner shapes (depends on `miuix-shader`)                         |
| `miuix-icons/`          | Extended icon resources                                                    |
| `miuix-navigation3-ui/` | Navigation 3 UI (depends on `miuix-squircle` for transition clip)          |
| `example/`              | Demo app                                                                   |
| `baselineprofile/`      | Android baseline profile generation                                        |
| `docs/`                 | VitePress documentation site                                               |
| `build-plugins/`        | Custom Gradle plugins                                                      |
| `gradle/`               | Version catalog + wrapper                                                  |

### Component Source Layout

`miuix-ui/src/commonMain/kotlin/top/yukonga/miuix/kmp/`:

| Subdir        | Contents                                                        |
| :------------ | :-------------------------------------------------------------- |
| `basic/`      | Fundamental components (Button, Switch, TextField, Surface, …)  |
| `overlay/`    | Scaffold-hosted overlays (OverlayDialog, OverlayBottomSheet, …) |
| `window/`     | Platform window components (WindowDialog, WindowBottomSheet, …) |
| `layout/`     | Shared layout primitives                                        |
| `theme/`      | MiuixTheme, Colors, TextStyles, ThemeController, DynamicColors  |
| `color/`      | Color utilities, Material Color                                 |
| `anim/`       | Animation utilities                                             |
| `utils/`      | General utilities                                               |
| `icon/`       | Built-in basic icons (ArrowRight, Check, Search, …)             |
| `interfaces/` | Shared interfaces                                               |

`miuix-preference/src/commonMain/kotlin/top/yukonga/miuix/kmp/`:

| Subdir        | Contents                                                        |
| :------------ | :-------------------------------------------------------------- |
| `preference/` | Preference components (SwitchPreference, CheckboxPreference, …) |
| `menu/`       | Dropdown menus (Overlay/Window variants, cascading)             |
| `popup/`      | Dropdown popup primitives shared by menus                       |

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

99% of UI logic lives in `commonMain`; platform source sets are only for genuinely platform-specific code.

## Code Style

- **Formatter**: Spotless + ktlint with Compose rules (`io.nlopez.compose.rules:ktlint`); exact versions in `build-plugins/src/main/kotlin/module.spotless.gradle.kts`
- **License header** (required on all `.kt` and `.kts` files; Spotless auto-fills `$YEAR` with the current year — do not manually change years in existing headers):

  ```
  // Copyright $YEAR, compose-miuix-ui contributors
  // SPDX-License-Identifier: Apache-2.0
  ```

- **Spotless exclusions**: icon files (`**/icon/**/*.kt`) and a subset of navigation3 sources (`**/navigation3/ListUtils.kt`, `**/navigation3/scene/*.kt`, `**/navigation3/ui/*.kt`)
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

`@NonRestartableComposable` is not part of the default template — see Key Patterns for when to add it.

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

- **`rememberUpdatedState`** for values whose latest reading must be visible to a long-lived closure without re-running an effect or rebuilding a Modifier: inside `LaunchedEffect`/`DisposableEffect` (the body sees the newest value without being keyed on it) and inside `remember { }`-cached lambdas (e.g., a wrapped `Modifier.clickable` onClick). It only prevents _stale captures_ — it does not stabilize the outer lambda's identity (`Modifier.clickable { current() }` is still a fresh object each composition). Do NOT use it when forwarding a callback directly to a child composable (`Button(onClick = onClick)`) — Compose's skip mechanism handles lambda stability there
- **`remember` with keys** for derived values: `val alpha = remember(enabled) { if (enabled) 1f else 0.38f }`
- **`@NonRestartableComposable`** on thin wrapper composables that fully delegate to other composables and read no state themselves; avoid on composables with multiple internal state reads (they benefit from smart recomposition)
- **`@Immutable`** on color/style data classes
- **Shapes**: `RoundedCornerShape(cornerRadius)` for rounded corners, `CircleShape` for capsules — both from `androidx.compose.foundation.shape`
- **Theme colors**: always `MiuixTheme.colorScheme.*`, never hardcoded colors
- **Text styles**: always `MiuixTheme.textStyles.*` (e.g., `MiuixTheme.textStyles.button`)

## Critical Constraints

### Do NOT Replace Custom Layout in Component.kt

`BasicComponent` (`basic/Component.kt`) uses a custom `Layout` with intrinsic measurement and a 2:5:3 weighted distribution. **Do NOT replace it with `Row + weight(1f)`** — this was tried and broke layout catastrophically (text rendered as single vertical characters) when start/end content overflows.

### Performance

- `LaunchedEffect` keys: only include values actually read in the effect body
- `minIntrinsicWidth`/`maxIntrinsicWidth` triggers full subtree traversal — defer to overflow branch when possible
- `@Immutable` only on truly immutable data classes: all `val`, never mutated, **and** no lambda/callback fields (lambda equality is reference-based and breaks `@Immutable`'s "equals stays equal forever" contract). `@Stable` for data classes holding lambdas/callbacks, for classes whose mutable properties notify Compose via `MutableState`, and on internal helper functions inside `@Immutable` classes (e.g., `@Stable internal fun color(enabled: Boolean): Color`)
- Standard collections (`List`, `Set`, `Map`) are unstable to Compose — prefer `kotlinx.collections.immutable` (`ImmutableList`/`PersistentList`), which the compiler recognizes as stable. Wrapping a raw `List` in an `@Immutable` data class only works if the caller keeps the wrapper's identity stable (e.g., builds it inside `remember`); a wrapper recreated each composition makes `@Immutable` a false promise to Compose

## Workflows

### Adding a New Component

1. Create the `@Composable` function in `miuix-ui/src/commonMain/kotlin/top/yukonga/miuix/kmp/basic/` (or `preference/` in `miuix-preference` for preference components)
2. Follow the API conventions above (parameter ordering, Defaults object, Colors data class)
3. Add a demo section in `example/shared/src/commonMain/kotlin/component/` and register it in the example app
4. Verify on at least Android and Desktop

### Modifying a Component

When changing a component's API, defaults, or behavior, update all related artifacts:

1. **Documentation** (`docs/components/` and `docs/zh_CN/components/`): properties tables, Defaults object sections, and code examples in both English and Chinese docs
2. **Docs demo code** (`docs/demo/`): the interactive demo if it uses changed APIs
3. **Example app** (`example/shared/src/commonMain/kotlin/component/`): demo code reflecting the changes

### Fixing Bugs

1. Reproduce in the `example` app
2. Fix in `miuix-ui/` or `miuix-preference/`
3. If platform-specific, verify the fix across affected platforms

## Git Commit Style

Format: `<scope>: <summary>`

- Scopes: `library`, `docs`, `example`, `build`, `fix`, `fix(deps)`, `chore(deps)`
- Keep subject line ≤ 72 characters, sentence case, no trailing period
- Reference PRs as `(#1234)` at subject end; issues as `* Fix #1234` in the body
- Check recent `git log --oneline` to stay consistent with current conventions
- **Run `./gradlew spotlessCheck` before every commit; only run `./gradlew spotlessApply` if it reports violations — CI rejects formatting violations**
