---
name: create-component
description: Create a new Compose Multiplatform UI component for the miuix library, including every required wiring point (library source, example app section + registration, docs interactive demo + registration, EN/zh_CN docs pages, component overview index, VitePress sidebar). Use whenever the user wants to add a new component, create a new composable, scaffold a component, or add a UI element to miuix — even if they only name the component ("add a Chip"). Triggers on "create component", "new component", "add a component", "scaffold component", "new composable", "新建组件", "添加组件", "新组件", "加个组件".
---

# Create a Miuix Component

Add a new component to the miuix library and complete every companion change beyond the component file itself — example registration, docs, sidebar, and so on: 8 touch points in total (see the checklist at the end).

The authoritative sources for conventions are `CLAUDE.md` at the repo root (API Conventions, Key Patterns, Critical Constraints) and the reference source files listed below. This skill only describes the workflow and where the changes go; wherever it conflicts with CLAUDE.md or existing source, they win — source code does not go stale, details copied into a skill do.

## Step 1: Gather requirements

Ask the user for whatever is not yet provided:

1. **Component name** — PascalCase (e.g. `Chip`, `Badge`, `Tooltip`)
2. **Category** —
   - **basic** (`basic/` in `miuix-ui`): standalone UI components, e.g. Button, Slider, Switch, TabRow
   - **preference** (`preference/` in `miuix-preference`): settings-item components built on `BasicComponent` with title/summary/action slots, named `XxxPreference` (SwitchPreference, CheckboxPreference, …)
   - Overlays (`overlay/`, `window/`) and menus/popups (`menu/`, `popup/` in `miuix-preference`) are rarely added; confirm the design with the user before starting
3. **Brief description** — what it does and where it is used
4. **Key parameters** — state, callbacks, content slots, configuration
5. **Whether a Colors class is needed** — most components need one; minimal components can take plain `Color` parameters (see how Divider does it)

## Step 2: Read reference files by component type

Before generating code, read the API Conventions section of `CLAUDE.md`, pick the closest type from the table below, and read its reference source in full. Match coding style, indentation, and idioms exactly:

| Component type | Reference (under `miuix-ui/src/commonMain/kotlin/top/yukonga/miuix/kmp/` unless noted) | Key points |
| :--- | :--- | :--- |
| Clickable filled | `basic/Button.kt` | `Box` + `Modifier.squircleSurface(color, cornerRadius)` + `clickable(interactionSource, LocalIndication.current, …)`; do not invent a Surface wrapper |
| Container | `basic/Card.kt` | two overloads: non-interactive `@NonRestartableComposable` one + interactive one (`combinedClickable` / `pressable` / `HoldDownObserver`) |
| Minimal drawn | `basic/Divider.kt` | `Canvas` drawing; plain `Color` parameters, no Colors class; theme colors in Defaults via `@Composable get()` |
| Animated / custom-drawn | `basic/ProgressIndicator.kt`, `basic/Switch.kt` | looping animation, drag gestures, haptics, semantics such as `progressBarRangeInfo` |
| Settings item | `miuix-preference/.../preference/SwitchPreference.kt` | delegates to `BasicComponent`, control placed in `endActions`, whole-row click callback + correct `Role` |
| BasicComponent API | `basic/Component.kt` | see CLAUDE.md Critical Constraints: its custom `Layout` must not be replaced with `Row + weight(1f)` |
| Grouped Colors (selected-state aware) | `miuix-preference/.../preference/RadioButtonPreference.kt` | `@Immutable` data class holding several `BasicComponentColors` + `@Stable internal fun xxx(selected)` accessors |

## Step 3: Write the component source file

Locations:

- basic: `miuix-ui/src/commonMain/kotlin/top/yukonga/miuix/kmp/basic/{Name}.kt`
- preference: `miuix-preference/src/commonMain/kotlin/top/yukonga/miuix/kmp/preference/{Name}Preference.kt`

Structure and conventions follow CLAUDE.md (license header, parameter order, Defaults object, `@Immutable` Colors class, when `@NonRestartableComposable` applies, where `rememberUpdatedState` applies, `@Immutable`/`@Stable` and collection-stability rules). Below are only the pitfalls CLAUDE.md does not spell out:

- **Backgrounds and shapes**: filled backgrounds (including those of clickable components) use `Modifier.squircleSurface(color = …, cornerRadius = …)`; borders use `Modifier.squircleBorder(…)`; squircle-radius clipping uses `Modifier.squircleClip(…)` (all from `miuix-squircle`; they fall back to rounded rectangles when `LocalSquircleEnabled` is off, and their outlines align at equal radii). Where an actual `Shape` value is required (e.g. `dropShadow`, the `shape` parameter of `Surface`), use `RoundedCornerShape(cornerRadius)` / `CircleShape`
- **Theming**: colors always come from `MiuixTheme.colorScheme.*` (full list in `theme/Colors.kt`) and text styles from `MiuixTheme.textStyles.*` (`theme/TextStyles.kt`); never hardcode
- **Resolving state to color**: prefer a `@Stable internal fun` on the Colors class (e.g. `color(enabled: Boolean)`; most of the library does this); a simple two-state case may also resolve inline like `Button.kt` does — follow the chosen reference file
- **Haptics**: toggle-like components fire haptics inside the click callback (`HapticFeedbackType.ToggleOn`/`ToggleOff`, following the `rememberUpdatedState(hapticFeedback)` pattern in `Switch.kt`/`Checkbox.kt`/`RadioButton.kt`); never fire on state change — programmatic state writes would trigger it
- **Semantics**: set the correct `Role` (Button/Switch/Checkbox/RadioButton…); the non-interactive form (`onClick = null`) still needs a `semantics` block declaring role and state
- **RTL**: for directional icons, capture `val layoutDirection = LocalLayoutDirection.current` first, then `graphicsLayer { scaleX = if (layoutDirection == LayoutDirection.Rtl) -1f else 1f }` (`GraphicsLayerScope` does not expose layoutDirection; see `miuix-preference/.../preference/ArrowPreference.kt`)
- **Platforms**: implement in `commonMain` first; use expect/actual only for genuine platform differences (see Platform Source Sets in CLAUDE.md)
- **Naming and wording**: no Material components or dependencies; code, comments, and KDoc must not reference external design systems or vendor names
- **Single-file layout**: the main composable, private helper composables, the Defaults object, and the Colors class live in one file; extract complex `startAction`/`endActions` content into private helper composables

## Step 4: Example app demo

1. Create `example/shared/src/commonMain/kotlin/component/{Name}Section.kt`:

   ```kotlin
   fun LazyListScope.xxxSection() {
       item(key = "xxx") {
           SmallTitle(text = "Xxx")
           Card(
               modifier = Modifier
                   .padding(horizontal = 12.dp)
                   .padding(bottom = 12.dp),
           ) {
               // demo content
           }
       }
   }
   ```

   The demo should cover the default look, key parameter variants, and the disabled state. Use several Cards (each with the padding above) for independent groups.

2. Register in `example/shared/src/commonMain/kotlin/MainPage.kt`: add `import component.xxxSection` to the alphabetized import block, and insert the `xxxSection()` call in display order inside the `if (notExpanded) { … }` block of the `LazyColumn`.

## Step 5: Docs

1. **Interactive demo**: create `docs/demo/src/commonMain/kotlin/{Name}Demo.kt`, structured like `RadioButtonDemo.kt`:

   ```kotlin
   @Composable
   fun XxxDemo() {
       Box(
           modifier = Modifier
               .fillMaxSize()
               .background(demoBackground()),
           contentAlignment = Alignment.Center,
       ) {
           Column(
               Modifier
                   .padding(16.dp)
                   .widthIn(max = 600.dp)
                   .fillMaxWidth(),
               verticalArrangement = Arrangement.spacedBy(16.dp),
               horizontalAlignment = Alignment.CenterHorizontally,
           ) {
               // demo content
           }
       }
   }
   ```

2. **Register the demo**: add one line `AvailableComponent("Xxx", "xxx") { XxxDemo() }` to the `availableComponents` list in `docs/demo/src/commonMain/kotlin/Demo.kt`, keeping the existing order. The id is camelCase; the docs page iframe references it via `?id=`, and the demo home screen picks it up automatically.
3. **Doc pages**: `docs/components/{slug}.md` and `docs/zh_CN/components/{slug}.md` (slug all-lowercase, e.g. `progressindicator`). Structure follows `docs/components/radiobutton.md`: intro + iframe (`src="../compose/index.html?id={demoId}"`; the zh version has one extra `../` in the path), Import, Basic Usage, Properties tables, Defaults object, Colors class, Advanced Usage. Keep the EN and zh content in one-to-one correspondence; size the iframe container height to the demo's actual content.
4. **Component overview**: add one row each to the overview tables in `docs/components/index.md` and `docs/zh_CN/components/index.md` (every component page has a row there — do not skip this).
5. **Sidebar**: add one entry to the matching group in `docs/.vitepress/locales/en_US.ts` and `zh_CN.ts` (basic components go under Basic Components/基础组件; preference and similar under Extended Components/扩展组件): `{ text: "Xxx", link: "/components/{slug}" }`, with the `/zh_CN` link prefix in the zh file.

## Step 6: Verify

- `./gradlew compileKotlinDesktop` (fast compile of library + example); also run `./gradlew :docs:demo:compileKotlinJs` if the docs demo changed
- `./gradlew spotlessCheck`; run `spotlessApply` only if it reports violations
- Run the example app on at least Android and Desktop to confirm visuals and interaction (commands in CLAUDE.md Key Commands)

Commits follow the CLAUDE.md Git Commit Style (the component and its example/docs companion changes go in one `library:` commit).

## Checklist (self-check)

A new component lands only when all 8 are done:

1. Component source file (miuix-ui or miuix-preference)
2. `example/shared/.../component/{Name}Section.kt`
3. `example/shared/.../MainPage.kt` registration (import + call)
4. `docs/demo/.../{Name}Demo.kt`
5. `availableComponents` entry in `docs/demo/.../Demo.kt`
6. `docs/components/{slug}.md` + `docs/zh_CN/components/{slug}.md`
7. One row each in `docs/components/index.md` + `docs/zh_CN/components/index.md`
8. Sidebar entries in `docs/.vitepress/locales/en_US.ts` + `zh_CN.ts`
