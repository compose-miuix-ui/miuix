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
        transition = NavTransitions.Cupertino,
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
| `Cupertino` (default) | iOS horizontal slide + lower-layer parallax + dim |
| `MiuixDefault` | Slide + corner clip + dim |
| `Scale` | Scale |
| `Fade` | Fade |
| `SharedAxisX` | Material shared X axis |
| `Modal` | Bottom-up slide; lower layer stays visible |
| `None` | Instant, no animation |

Set a global default on `NavDisplay(transition = ...)` and override per route with `entry(transition = ...)`:

```kotlin
NavDisplay(backStack, transition = NavTransitions.Cupertino) {
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

A transition also owns its **scrim curve**: the fullscreen dim rendered just beneath the top-most layer follows `NavTransition.scrimFraction` — taken from the transition governing the layer **above** (the covering entry's, the same boundary-ownership rule as the covered transform) and evaluated against the covered layer below (depth 0 = revealed, 1 = covered) — while `NavDisplayEffects.dimAmount` only caps how dark it gets. The default curve is linear in depth (the dim lightens as the layer below is revealed); pass a custom one via the `scrim` parameter, e.g. a card-style scrim that holds during a gesture and fades only across the post-commit sweep:

```kotlin
val cardStyle = navGraphicsTransition(
    scrim = { scope ->
        val d = scope.relativeDepth.coerceIn(0f, 1f)
        val g = scope.gesture
        if (g != null) (d / (1f - g.progress).coerceAtLeast(0.01f)).coerceIn(0f, 1f) else d
    },
) { scope -> /* transform */ }
```

For a complete, platform-grade transition built entirely on this public API — centered scale, edge hug, damped vertical finger follow, post-commit fly-out and the gesture-held scrim above — see `CrossActivityTransition` in the example app (`example/shared/src/commonMain/kotlin/navigation/CrossActivityTransition.kt`).

## Orthogonal effects

`NavDisplayEffects` holds the cross-cutting effects layered on top of the active transition — corner clip, dim cap, input blocking and a backdrop fill (`backdropColor`):

```kotlin
NavDisplay(
    backStack = backStack,
    effects = NavDisplayEffects(
        enableCornerClip = true,
        dimAmount = 0.5f,
        blockInputDuringTransition = true,
    ),
) { /* ... */ }
```

## Gesture back

Back is built in and shares the same `animatedTop` and `NavTransition` as a normal pop — there is no separate predictive animation to maintain. When a gesture is in progress the finger drives `animatedTop` 1:1 (`snapTo`, no interpolator); on release a velocity-first / position-fallback decision commits or cancels, handing the lift velocity to the convergence spring so motion stays continuous.

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
Keys must be `@Serializable` to survive process death. A non-serializable key does not crash — it simply **degrades to in-memory only**: the entry stays navigable within the live process, but the back stack will **not** be restored after process death (Android low-memory kill, etc.). Make every route `@Serializable` if you need cross-process restoration.
:::

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

## Relationship to Navigation3 Support

`miuix-nav` is an independent runtime and coexists with [`miuix-navigation3-ui`](/guide/navigation3). Pick `miuix-nav` for continuous-depth motion and fully custom float-driven transitions; pick `miuix-navigation3-ui` if you are standardizing on `androidx.navigation3`.
