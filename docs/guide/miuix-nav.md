# miuix-nav

`miuix-nav` is a self-contained Compose Multiplatform navigation runtime with **continuous stack depth** as its core model. The whole back stack is driven by a single `Animatable<Float>` (`animatedTop`); every entry's visuals are a pure function of its relative depth. This makes continuous push/pop, fully custom float-driven transitions, and 1:1 gesture back fall out naturally. It has **zero dependency** on `androidx.navigation3`.

## Setup

Add the dependency to your `build.gradle.kts`:

```kotlin
implementation("top.yukonga.miuix.kmp:miuix-nav:<version>")
```

Annotate your route hierarchy with `@Serializable` so the back stack can be saved and restored across configuration changes and process death.

## Basic usage

```kotlin
import kotlinx.serialization.Serializable
import top.yukonga.miuix.kmp.nav.core.NavDisplay
import top.yukonga.miuix.kmp.nav.core.NavKey
import top.yukonga.miuix.kmp.nav.core.rememberNavBackStack
import top.yukonga.miuix.kmp.nav.transition.NavTransitions

@Serializable
sealed interface Route : NavKey {
    @Serializable data object Home : Route
    @Serializable data class Detail(val id: String) : Route
}

@Composable
fun App() {
    val backStack = rememberNavBackStack<Route>(Route.Home)
    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
    ) {
        entry<Route.Home> {
            HomeScreen(onOpen = { id -> backStack.add(Route.Detail(id)) })
        }
        entry<Route.Detail> { route ->
            DetailScreen(route.id, onBack = { backStack.removeLastOrNull() })
        }
    }
}
```

`rememberNavBackStack` returns a `NavBackStack` (a `SnapshotStateList<NavKey>`). Operate it directly (`add` / `removeLastOrNull`) or wrap it in `NavController` for `push` / `pop` / `replace` / `popUntil`.

::: warning
`rememberNavBackStack` is `inline fun <reified T : NavKey>`. When you seed it with a single concrete key, pass the route **supertype** as the explicit type argument — `rememberNavBackStack<Route>(Route.Home)` — so the whole sealed hierarchy is serializable. Writing `rememberNavBackStack(Route.Home)` infers `T = Route.Home`, and pushing other subtypes (e.g. `Route.Detail`) will later fail to serialize for save/restore.
:::

## Continuous push and pop

Because the stack is driven by one float, pushing or popping several entries at once animates as a single continuous sweep instead of collapsing into one top-level cross-fade:

```kotlin
// Push three at once: animatedTop goes N -> N+3 over one shared spring.
backStack.add(Route.Detail("1"))
backStack.add(Route.Detail("2"))
backStack.add(Route.Detail("3"))

// Pop back to root: animatedTop goes N -> 0, continuous reverse motion.
while (backStack.size > 1) backStack.removeAt(backStack.lastIndex)
```

The reconciler classifies each change as `Push` / `Pop` / `MultiPush(n)` / `MultiPop(n)` / `Replace` / `ReplaceAll`, surfaced to transitions via `NavTransitionScope.change` so you can animate a multi-pop differently from a single pop.

## Transitions

A built-in preset library is available as `NavTransitions`:

| Preset | Description |
| :-- | :-- |
| `MiuixDefault` (default) | Full-width slide + quarter-width parallax + light covered alpha falloff |
| `Modal` | Bottom-up slide; lower layer stays visible |
| `None` | Instant, no animation |

The leading-edge corner clip and the dark dim scrim are not baked into any preset — they come from the orthogonal `NavDisplayEffects` layer (`enableCornerClip`, `dimAmount`; both on by default), while each transition only shapes the scrim's curve along the motion via `scrimFraction`.

Set a global default on `NavDisplay(transition = ...)` and override per route with `entry(transition = ...)`:

```kotlin
NavDisplay(backStack, transition = NavTransitions.MiuixDefault) {
    entry<Route.Home> { HomeScreen() }
    entry<Route.Detail>(transition = NavTransitions.Modal) { DetailScreen(it.id) }
}
```

## Swipe-to-dismiss direction

The interactive swipe that pops the top entry runs along the **same axis as the transition**: a horizontal slide is dismissed by a horizontal swipe, a bottom-up modal by a downward swipe. It is **opt-in** — `dismissDirection` defaults to `None` and **none of the built-in presets enable it**. Turn it on per route with `entry(swipeDismiss = ...)`, or author a transition that declares a direction.

`NavSwipeDirection` values are **physical screen directions** (which way the finger moves) and are *not* mirrored for layout direction, so pick the right one per LTR / RTL:

| Direction | Finger motion |
| :-- | :-- |
| `LeftToRight` | Rightward — the back swipe under **LTR** |
| `RightToLeft` | Leftward — the back swipe under **RTL** |
| `TopToBottom` | Downward — dismiss for a bottom-up modal |
| `BottomToTop` | Upward |
| `None` (default) | Disabled — pop via back button / system back only |

Once a swipe engages it owns the pointer for the rest of the gesture (consuming both axes), so a cross-axis wiggle cannot steal it or cancel mid-swipe, and in-page taps / scrolls are suppressed until the finger lifts. Set or override it per route — including `NavSwipeDirection.None` to keep a route button-only:

```kotlin
import top.yukonga.miuix.kmp.nav.transition.NavSwipeDirection

NavDisplay(backStack) {
    // Enable an LTR back swipe on this route (off by default).
    entry<Route.Home>(swipeDismiss = NavSwipeDirection.LeftToRight) { HomeScreen() }
    // Bottom sheet: swipe down to dismiss.
    entry<Route.Sheet>(transition = NavTransitions.Modal, swipeDismiss = NavSwipeDirection.TopToBottom) { SheetScreen() }
    // Left untouched (default None): button-only.
    entry<Route.Confirm> { ConfirmScreen() }
}
```

A custom transition can still declare a natural direction so routes inherit it: `navGraphicsTransition(dismissDirection = NavSwipeDirection.LeftToRight) { ... }`.

## Custom transitions

Build any transition by reading the raw float depth and writing a `graphicsLayer`. The block runs inside a deferred-read layer, so reading `relativeDepth` does not recompose:

```kotlin
import top.yukonga.miuix.kmp.nav.transition.navGraphicsTransition

val myTransition = navGraphicsTransition { scope ->
    val d = scope.relativeDepth          // animatedTop - index
    translationX = -d * scope.layoutSize.width.toFloat()
    scaleX = 1f - 0.1f * d.coerceIn(0f, 1f)
    scaleY = scaleX
    cameraDistance = 16f * scope.density.density
}
```

`NavTransitionScope` exposes `relativeDepth`, `role`, `change`, `gesture`, `layoutSize`, `layoutDirection` and `density`.

Generic looks (fade, scale, shared-axis, …) are deliberately not shipped as presets — each is a few lines on this builder. A cross-fade, for instance:

```kotlin
val fade = navGraphicsTransition { scope ->
    val d = scope.relativeDepth
    alpha = if (d <= 0f) (1f + d).coerceIn(0f, 1f) else 1f - d.coerceIn(0f, 1f)
}
```

A transition also owns its **scrim curve**: the fullscreen dim rendered just beneath the top-most layer follows `NavTransition.scrimFraction` — taken from the transition governing the layer **above** (the covering entry's, the same boundary-ownership rule as the covered transform) and evaluated against the covered layer below (depth 0 = revealed, 1 = covered) — while `NavDisplayEffects.dimAmount` only caps how dark it gets. The default curve is linear in depth (the dim lightens as the layer below is revealed); pass a custom one via the `scrim` parameter, e.g. a card-style scrim that holds during a gesture and fades only across the post-commit sweep:

```kotlin
val cardStyle = navGraphicsTransition(
    scrim = { scope ->
        val d = scope.relativeDepth.coerceIn(0f, 1f)
        val g = scope.gesture
        if (g != null) (d / (1f - g.progress).coerceAtLeast(0.001f)).coerceIn(0f, 1f) else d
    },
) { scope -> /* transform */ }
```

::: tip Progress saturates below 1
`gesture.progress` never reaches exactly `1` — the driver saturates finger-driven progress just under the fully-popped boundary (also shielding devices that misreport back-gesture progress past 1). Division by `1 - progress` is therefore safe, **as long as any floor on the denominator stays at or below that headroom** (the `0.001` above). A larger floor (e.g. `0.01`) breaks the drag identity `depth == 1 - progress` in the last percent and visibly collapses the scrim on such devices.
:::

For a complete, platform-grade transition built entirely on this public API — centered scale, edge hug, damped vertical finger follow, post-commit fly-out and the gesture-held scrim above — see `CrossActivityTransition` in the example app (`example/shared/src/commonMain/kotlin/navigation/CrossActivityTransition.kt`).

## Settle physics

A transition also declares its **settle physics** via `NavTransition.motion`: the timing curves used once the geometry has to converge on its own (the gesture released, or a programmatic push/pop fired). `NavMotion` keys one `NavSettleSpec` per phase:

| Phase | Runs when | Default |
| :-- | :-- | :-- |
| `commit` | A gesture release commits the pop (predictive back / edge swipe); seeded with the release velocity when it is a `Spring` | Critically damped spring (`dampingRatio 1`, `stiffness 146`) |
| `cancel` | A gesture release cancels and the entry springs back to rest | Same spring |
| `programmatic` | A from-rest, full-step push/pop (multi-step included) | 500ms tween on the established curve (`NavProgrammaticEasing`) |

A `NavSettleSpec` is either a `Spring(dampingRatio, stiffness, clampOvershoot)` or a fixed-duration `Tween(durationMillis, easing)`:

```kotlin
val snappy = navGraphicsTransition(
    motion = NavMotion(
        // A flung release overshoots and bounces back, scaled by the throw.
        commit = NavSettleSpec.Spring(dampingRatio = 0.8f, stiffness = 280f, clampOvershoot = false),
        programmatic = NavSettleSpec.Tween(durationMillis = 450, easing = NavProgrammaticEasing),
    ),
) { scope -> /* transform */ }
```

The host resolves the motion from the **topmost presented entry's** governing transition (per-route overrides win), so during a pop the leaving entry's own motion carries its exit.

Overshoot rules:

- By default the commit settle floors its seed velocity at the exact no-overshoot bound, so navigation never bounces. Pass `clampOvershoot = false` to keep the full release velocity; an underdamped spring (`dampingRatio < 1`) overshoots by construction and **requires** that opt-out (the constructor rejects the ambiguous combination).
- The **cancel** settle always pins the rest position as a bound: input blocking, boundary ownership and the dim scrim all flip past rest, so a cancel may never cross it regardless of its spring.
- A `Tween` cannot carry a release velocity: as a commit spec it starts from the commit point with a velocity cut, and a velocity-carrying programmatic settle falls back to a spring.

### The settle context

While the driver animates on its own, the scope exposes `settle: NavSettle?` — the self-driven counterpart of `gesture` (finger driving → `gesture` non-null; settling → `settle` non-null; at rest → both null):

| Field | Meaning |
| :-- | :-- |
| `phase` | `Commit` (gesture release committed), `Cancel` (springing back) or `Programmatic` (from-rest push/pop) |
| `releaseVelocity` | The seeded release velocity, progress-units/sec toward pop; recorded even when the curve is a `Tween` (which cannot consume it) |
| `elapsedMillis` | Wall-clock since the settle started, a per-frame deferred-read source (read it inside `graphicsLayer { }`) |

This is what makes wall-clock curves and velocity overlays expressible without any extra animation machinery — an overlay spring is just closed-form math over `(releaseVelocity, elapsedMillis)`:

```kotlin
val withBounce = navGraphicsTransition(
    motion = NavMotion(commit = NavSettleSpec.Tween(450, NavProgrammaticEasing)),
) { scope ->
    val s = scope.settle
    // Deterministic track from the tween; a separate velocity-scaled bounce on top.
    if (s?.phase == NavSettlePhase.Commit) {
        val kick = (s.releaseVelocity * scope.layoutSize.width * 10f).coerceIn(0f, 1000f)
        val omega = sqrt(200f)
        val omegaD = omega * sqrt(1f - 0.75f * 0.75f)
        val t = s.elapsedMillis / 1000f
        val overlay = -(kick / omegaD) * exp(-0.75f * omega * t) * sin(omegaD * t)
        scaleX = ((100f + overlay) / 100f).coerceAtMost(1f)
        scaleY = scaleX
        // A wall-clock fade, independent of the motion easing:
        alpha = (1f - 5f * (s.elapsedMillis / 450f)).coerceAtLeast(0f)
    }
    /* depth-driven geometry as usual */
}
```

One caveat: a grab-anytime interruption replaces the settle (and restarts `elapsedMillis`); design wall-clock curves to tolerate a restart, or fall back to depth-axis formulas when `settle == null`.

## Programmatic vs. predictive transitions

By default one transition serves both drive modes — the visual is a pure function of depth, so the gesture and the programmatic settle replay the same geometry. When a design calls for **two distinct effect systems** (the platform itself animates a back-button pop and a predictive-back gesture completely differently), compose them with `navDirectionalTransition`:

```kotlin
val platformLike = navDirectionalTransition(
    push = classicOpen,          // forward changes (and replace/initial)
    pop = classicClose,          // programmatic pops; defaults to push
    predictivePop = gestureCard, // while a gesture drives; defaults to pop
)
```

Dispatch: a live gesture (predictive back or edge swipe — the context stays frozen through the whole release settle) always selects `predictivePop`; otherwise `NavChange.Pop` / `MultiPop` selects `pop`, and everything else selects `push`. Static contracts merge from their natural sources: `opaqueDepth` takes the max of the three, `dismissDirection` and the commit/cancel physics come from `predictivePop`, the programmatic curve from `pop` (`push.motion` is never consumed — use per-route overrides for route-level asymmetry).

One trade-off to know: a gesture may grab the stack mid-programmatic-settle, switching the dispatch to `predictivePop` at the grab instant. If the two branches disagree geometrically at that depth, the style jumps for one frame — author branches that stay close in the grabbable range when that matters. The example's `CrossActivityTransition` is built exactly this way: classic 450ms slide+fade for programmatic push/pop, the gesture-scaled card with a velocity-seeded bouncing commit spring for predictive back.

## Orthogonal effects

`NavDisplayEffects` holds the cross-cutting effects layered on top of the active transition, computed per depth independently of the transition itself:

| Property | Default | Description |
| :-- | :-- | :-- |
| `enableCornerClip` | `true` | Clip the transitioning top entry with smooth rounded corners while it animates over the layer below |
| `cornerClipRadius` | `0.dp` | Radius of that clip; pass `rememberNavSystemCornerRadius()` to follow the device screen corner (still no rounding where the platform reports 0) |
| `cornerClipMode` | `Leading` | Which corners to round: `Leading` — the corners meeting the screen edge, for slide-style transitions; `All` — every corner, for card-style transitions that scale the whole page |
| `dimAmount` | `0.5f` | Maximum alpha of the fullscreen dim scrim beneath the top-most layer; the curve along the motion belongs to the transition (`scrimFraction`), this only caps darkness. `0f` disables |
| `blockInputDuringTransition` | `true` | Swallow touch input on mid-transition entries, so taps cannot reach a half-animated screen |
| `backdropColor` | `Unspecified` | Solid fill behind every entry layer — card-style transitions scale pages below full size, revealing the area behind the host; pass the theme background so it reads as the page extending outward |

`NavDisplayEffects.Default` is the table above; `NavDisplayEffects.None` disables everything. A card-style setup:

```kotlin
NavDisplay(
    backStack = backStack,
    effects = NavDisplayEffects(
        cornerClipRadius = rememberNavSystemCornerRadius(),
        cornerClipMode = NavCornerClipMode.All,
        dimAmount = 0.32f,
        backdropColor = MiuixTheme.colorScheme.background,
    ),
) { /* ... */ }
```

## Gesture back

Back is built in and shares the same `animatedTop` and `NavTransition` as a normal pop — by default there is no separate predictive animation to maintain (opt into a split with [`navDirectionalTransition`](#programmatic-vs-predictive-transitions)). When a gesture is in progress the finger drives `animatedTop` 1:1 (`snapTo`, no interpolator); on release a velocity-first / position-fallback decision commits or cancels, handing the lift velocity to the governing commit curve so motion stays continuous.

Two sources feed it: the **in-content swipe** (an all-platform Compose gesture, direction-aware per [Swipe-to-dismiss direction](#swipe-to-dismiss-direction) above) and the **platform back** (system predictive back / ESC / a custom trigger). Both stream into the same driver.

The **input source** and its **semantics differ per platform** — the runtime normalizes them into the same back stream, but the gesture feel is not identical everywhere:

| Platform | Source | Semantics |
| :-- | :-- | :-- |
| Android | System predictive back | **Continuous** OS gesture: progress + swipe edge stream the whole pull; finger drives `animatedTop` 1:1, release commits/cancels with velocity handoff |
| iOS | Interactive edge swipe | **Continuous** left-edge drag implemented in-runtime; same 1:1 drive and velocity handoff as Android |
| Desktop | `ESC` key | **Discrete**, non-interactive: a key press triggers a single commit straight into the convergence spring (no per-finger progress) |
| Web | Custom source | **Caller-defined**: you feed the same back stream from your own trigger (e.g. browser back button / custom button); semantics are whatever the source provides |

## Save and restore

`rememberNavBackStack` persists the stack via `rememberSaveable` and a `kotlinx.serialization`-based saver.

::: warning
`@Serializable` is a **hard requirement** for every key in a `rememberNavBackStack` stack, not a soft hint. There are two distinct failure points: a key **type** that is not `@Serializable` throws `SerializationException` at the first composition of `rememberNavBackStack` (the serializer is captured there); a key **instance** outside the captured hierarchy — or a non-serializable subtype inside it — navigates fine all session and then throws at state-save time (on Android: when the app is backgrounded). If you cannot make keys serializable, build the stack with a plain in-memory list (`navBackStackOf`) instead of `rememberNavBackStack`.
:::

### Entry state and `contentKey`

Each entry's `rememberSaveable` state is scoped by its **contentKey** — the route value itself, unless you derive one via `entry<T>(contentKey = { route -> ... })`. Two requirements apply on top of the stack-wide uniqueness check:

- **Distinct keys must print distinct strings.** The saveable slot is keyed by the contentKey's `toString()`. Two *unequal* keys that print the same string — same-named `data class` routes in different packages (`data class` `toString()` omits the package), or an `Int 1` vs a `String "1"` returned from contentKey factories — are rejected at reconcile time with an actionable `IllegalArgumentException` instead of silently sharing and corrupting each other's saved state.
- **The string must be value-derived.** `data class` / `data object` routes qualify out of the box. A route class that keeps the default identity `toString()` (`com.app.Detail@1a2b3c`) passes every runtime check — the string is unique within the session — but resolves to a new string after process death, so the entry's `rememberSaveable` state silently resets. Stick to `data class` / `data object` routes, or return a value-derived key from the factory.

## Entry lifecycle and ViewModels

Every entry runs under its own `LifecycleOwner` and `ViewModelStoreOwner`, so `collectAsStateWithLifecycle`, `viewModel()` and store-based DI scope per screen with no extra setup.

Lifecycle is a pure function of depth: the settled top is `RESUMED`; covered, incoming and leaving layers are `STARTED`; an entry being removed drops to `CREATED` until it unloads. While a **gesture** drives the stack, everyone is capped at `STARTED` — `RESUMED` means "settled, sole top", so work keyed on it does not flap while a finger hovers around the transition thresholds.

ViewModel stores are owned by the display, not the entry's composition: a covered entry keeps its ViewModels for as long as it stays on the back stack, and the store is cleared when the entry is popped.

## Returning a result to a previous screen

The v1 core **does not** ship a built-in result channel (no `navigateForResult` / `setResult` on `NavController`). Keep results out of the navigation runtime and pass them with whatever state mechanism you already use, or layer a tiny result bus on top of the back stack yourself.

The recommended shape is a `requestKey`-addressed bus alongside the stack — use a `MutableSharedFlow(replay = 1)` or a buffered `Channel`, so the result emitted right before the pop is not lost if the caller screen is not actively collecting at that moment:

```kotlin
// navigateForResult(route, requestKey): push and arm a channel for requestKey.
// setResult(requestKey, value): emit on the channel, then pop.
// observeResult(requestKey): collect the result as a SharedFlow on the caller screen.
```

Port that approach (or a `SavedStateHandle`-style holder) into your own app layer; it is intentionally left out of `miuix-nav` v1 so the runtime stays a pure depth-driven stack.

## Scope and extension boundaries (v1)

`miuix-nav` v1 is deliberately a **single, flat back stack**. The following are explicitly **not supported in v1** and are deferred to v2+; do not build on them yet:

| Capability | v1 status |
| :-- | :-- |
| Multi-pane / list-detail adaptive layout | Not supported (single stack only) |
| Dialog / bottom-sheet scene strategy (overlay destinations) | Not supported |
| Shared-element transitions across destinations | Not supported |
| Nested `NavDisplay` (a back stack inside an entry) | Not supported |
| KSP / annotation-based route registration | Not supported (register entries via the `entry<T> { }` DSL) |
| Built-in result channel on `NavController` | Not supported (see the section above) |

These boundaries exist so the v1 surface stays small and the continuous-depth model stays the single source of truth. They are tracked for v2+.
