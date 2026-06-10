// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.core

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.NonRestartableComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.movableContentOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEach
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import top.yukonga.miuix.kmp.nav.gesture.PredictiveBackHandler
import top.yukonga.miuix.kmp.nav.gesture.drivePredictiveBack
import top.yukonga.miuix.kmp.nav.gesture.navSwipeDismiss
import top.yukonga.miuix.kmp.nav.runtime.NavChange
import top.yukonga.miuix.kmp.nav.runtime.NavPresentation
import top.yukonga.miuix.kmp.nav.runtime.isVisibleAt
import top.yukonga.miuix.kmp.nav.runtime.navReconcile
import top.yukonga.miuix.kmp.nav.runtime.relativeDepth
import top.yukonga.miuix.kmp.nav.runtime.rememberNavPresentation
import top.yukonga.miuix.kmp.nav.runtime.settleProgrammatic
import top.yukonga.miuix.kmp.nav.runtime.settleTo
import top.yukonga.miuix.kmp.nav.state.NavSaveableStateHolder
import top.yukonga.miuix.kmp.nav.state.ProvideNavEntryLifecycle
import top.yukonga.miuix.kmp.nav.state.ProvideNavEntryViewModelStore
import top.yukonga.miuix.kmp.nav.state.navMaxLifecycleFor
import top.yukonga.miuix.kmp.nav.state.rememberNavEntryLifecycleOwner
import top.yukonga.miuix.kmp.nav.state.rememberNavEntryViewModelStoreOwner
import top.yukonga.miuix.kmp.nav.state.rememberNavSaveableStateHolder
import top.yukonga.miuix.kmp.nav.transition.NavGesture
import top.yukonga.miuix.kmp.nav.transition.NavSwipeDirection
import top.yukonga.miuix.kmp.nav.transition.NavTransition
import top.yukonga.miuix.kmp.nav.transition.NavTransitions
import top.yukonga.miuix.kmp.squircle.absoluteSquircleClip
import kotlin.reflect.KClass

/**
 * Metadata key under which a per-route [NavTransition] override is stored on a built [NavEntry].
 *
 * The renderer reads this key from [NavEntry.metadata]; a `null`/absent value means the entry
 * inherits the global transition passed to [NavDisplay].
 */
@PublishedApi
internal const val NAV_TRANSITION_METADATA_KEY: String = "top.yukonga.miuix.kmp.nav.transition"

/**
 * Metadata key under which a per-route [NavSwipeDirection] override is stored on a built [NavEntry].
 *
 * A `null`/absent value means the entry inherits the dismiss direction of its governing
 * [NavTransition.dismissDirection]; an explicit value (including [NavSwipeDirection.None] to disable
 * the gesture) wins.
 */
@PublishedApi
internal const val NAV_SWIPE_DISMISS_METADATA_KEY: String = "top.yukonga.miuix.kmp.nav.swipeDismiss"

/**
 * DSL receiver of the [NavDisplay] content lambda.
 *
 * Each `entry<T> { ... }` call registers, keyed by the route's runtime [KClass], how to build a
 * [NavEntry] for keys of that type. [build] materializes the registrations into a lookup function.
 */
class NavEntryBuilder {
    private val factories: MutableMap<KClass<*>, (NavKey) -> NavEntry<*>> = mutableMapOf()

    /**
     * Registers how to render keys of type [T].
     *
     * @param contentKey value-stable identity for the produced entry; when `null` the key instance
     *   itself is used (data classes / data objects give stable equality out of the box).
     * @param transition per-route transition override; `null` inherits [NavDisplay]'s global
     *   transition (see design §6.4). Stored in the entry's metadata.
     * @param swipeDismiss per-route interactive swipe-to-dismiss direction; `null` inherits the
     *   governing transition's [NavTransition.dismissDirection]. Pass [NavSwipeDirection.None] to
     *   disable the gesture on this route, or another direction to override it.
     * @param metadata extra per-entry metadata, merged with the transition / swipe-dismiss overrides.
     * @param content the composable rendering a key of type [T].
     */
    inline fun <reified T : NavKey> entry(
        contentKey: Any? = null,
        transition: NavTransition? = null,
        swipeDismiss: NavSwipeDirection? = null,
        metadata: Map<String, Any> = emptyMap(),
        noinline content: @Composable (T) -> Unit,
    ) {
        register(T::class) { key ->
            @Suppress("UNCHECKED_CAST")
            val typed = key as T
            var mergedMetadata = metadata
            if (transition != null) mergedMetadata = mergedMetadata + (NAV_TRANSITION_METADATA_KEY to transition)
            if (swipeDismiss != null) mergedMetadata = mergedMetadata + (NAV_SWIPE_DISMISS_METADATA_KEY to swipeDismiss)
            NavEntry(
                key = typed,
                contentKey = contentKey ?: typed,
                metadata = mergedMetadata,
                content = content,
            )
        }
    }

    /**
     * Low-level registration hook used by the [entry] inline function. Public only so the inline
     * reified [entry] can call it across the inline boundary; not intended for direct use.
     */
    @PublishedApi
    internal fun register(clazz: KClass<*>, factory: (NavKey) -> NavEntry<*>) {
        factories[clazz] = factory
    }

    /**
     * Materializes the accumulated registrations into an entry lookup. Throws if a key has no
     * registered [entry] of its (or a superclass') type.
     */
    @PublishedApi
    internal fun build(): (NavKey) -> NavEntry<*> = { key ->
        val factory = factories[key::class]
            ?: error("No entry { } registered for ${key::class.simpleName ?: key::class}")
        factory(key)
    }
}

/**
 * Materializes a [NavEntryBuilder] DSL block into an entry lookup function.
 *
 * The [NavDisplay] DSL overloads call this internally; the low-level [NavDisplay] overload accepts a
 * prebuilt lookup of this exact shape.
 */
internal fun entryProvider(builder: NavEntryBuilder.() -> Unit): (NavKey) -> NavEntry<*> = NavEntryBuilder().apply(builder).build()

/**
 * Drives [NavPresentation.animatedTop] towards [target], unless a gesture currently owns the
 * float (in which case the gesture layer snaps it via `snapToFinger`). Implemented as a
 * [LaunchedEffect] keyed on [target] that defers to the driver's settle dispatch
 * (`settleProgrammatic`: the fixed-duration programmatic curve from rest over a full step, the
 * velocity-continuous shared spring otherwise) — the renderer never builds its own
 * [androidx.compose.animation.core.AnimationSpec].
 */
@Composable
private fun NavPresentation.animateTopTo(target: Float) {
    LaunchedEffect(target) {
        if (animatedTop.value == target) return@LaunchedEffect
        // A gesture-release settle toward this same target may already own the float (a swipe
        // commit pops synchronously and queues its velocity-seeded settle ahead of this effect);
        // defer to it instead of stomping the seeded spring with a fresh-from-rest curve.
        if (animatedTop.isRunning && animatedTop.targetValue == target) return@LaunchedEffect
        animatedTop.settleProgrammatic(target)
    }
}

/** Reads the per-route [NavTransition] override from an entry's metadata, or null to inherit. */
private fun NavEntry<*>.transitionOrNull(): NavTransition? = metadata[NAV_TRANSITION_METADATA_KEY] as? NavTransition

/** Reads the per-route [NavSwipeDirection] override from an entry's metadata, or null to inherit. */
private fun NavEntry<*>.swipeDismissOrNull(): NavSwipeDirection? = metadata[NAV_SWIPE_DISMISS_METADATA_KEY] as? NavSwipeDirection

/**
 * Private rendering core shared by all [NavDisplay] overloads.
 *
 * Responsibilities:
 * - build one [NavEntry] per current back-stack key via [entryProvider];
 * - reconcile against the previous render set ([NavPresentation.reconcile]) to obtain the presentation
 *   set (current ∪ exiting) and the [NavChange];
 * - keep the single driving float [NavPresentation.animatedTop] converging to `lastIndex` via the
 *   shared spring ([animateTopTo] → `settleTo`);
 * - precompute a `contentKey -> index` map at reconcile time so depth math is an O(1) lookup at
 *   render time (no O(n^2) rebuild of NavEntry instances per frame);
 * - render only the visible window ([derivedStateOf] over `presented` + the governing opaqueDepth),
 *   applying the governing transition (boundary ownership §4.3), the orthogonal [effects], and
 *   per-entry state scopes; fully-exited entries are unloaded.
 *
 * Visual reads of [NavPresentation.animatedTop] happen inside deferred `graphicsLayer { }` blocks so
 * the per-frame float change does not recompose this function. The saveable-state holder is created
 * **once here** and passed down to each [NavEntryHost] (stable identity, see §0.4).
 */
@Composable
private fun NavDisplayLayout(
    backStack: NavBackStack,
    entryProvider: (NavKey) -> NavEntry<*>,
    onBack: () -> Unit,
    transition: NavTransition,
    effects: NavDisplayEffects,
    modifier: Modifier = Modifier,
) {
    val presentation = rememberNavPresentation(backStack.lastIndex)
    val stateHolder = rememberNavSaveableStateHolder()

    val topIndex = backStack.lastIndex
    val backScope = rememberCoroutineScope()
    val currentOnBack = rememberUpdatedState(onBack)

    // Wire the platform predictive back gesture to the single driver. Enabled only when there is
    // something to pop (size > 1). During the gesture each NavBackEvent snaps animatedTop 1:1;
    // on commit onBack() pops and the renderer's animateTopTo converges to the new top; on cancel
    // the shared spring settles back to topIndex. Modifier.navSwipeDismiss on the display
    // container drives the same animatedTop for the interactive edge swipe.
    PredictiveBackHandler(
        enabled = backStack.size > 1,
        onProgress = { events ->
            drivePredictiveBack(
                // Mirror each event into the live gesture context for gesture-aware transitions
                // (touch-following pivots etc.); NavBackEvent and NavGesture share field semantics.
                events = events.onEach { event ->
                    presentation.gesture = NavGesture(
                        progress = event.progress,
                        swipeEdge = event.swipeEdge,
                        touchY = event.touchY,
                    )
                },
                animatedTop = presentation.animatedTop,
                topIndex = topIndex,
            )
        },
        onCommit = {
            // Pop synchronously; convergence is owned by the renderer. The pop retargets
            // animateTopTo, which picks the programmatic curve for a from-rest discrete back
            // (identical to a programmatic pop) and the velocity-continuous spring when the
            // gesture left the float mid-flight. The gesture context stays frozen through the
            // settle and is cleared when the leaving entry unloads.
            currentOnBack.value()
        },
        onCancel = {
            backScope.launch {
                presentation.animatedTop.settleTo(target = topIndex.toFloat())
                presentation.gesture = null
            }
        },
    )

    // Build entries for the current back stack and reconcile. The build runs only when the back-stack
    // key list changes; entry instances for surviving keys are reused by the presentation. The
    // reconcile pass classifies the change against the PREVIOUS back-stack content keys (kept in a
    // remembered holder, not the presented set — the latter still holds exiting keys) and precomputes a
    // stable contentKey -> back-stack-index map so the render loop never rebuilds NavEntry instances to
    // recover an index (no O(n^2)).
    val currentKeyList = backStack.toList()
    // Plain (non-snapshot) holder for the previous classification input; bookkeeping only, never read
    // by UI, so it must not be snapshot state (no cross-phase back-write).
    val previousContentKeys = remember { arrayOfNulls<List<Any>>(1) }
    // Persistent contentKey -> back-stack index. Current entries get their fresh index on each
    // reconcile; a leaving (popped/replaced) entry KEEPS its last index here so it can still render
    // its exit animation, and is pruned only when it has finished leaving (see the unload effect
    // below). Plain (non-snapshot) map: it is mutated in lockstep with presentation.presented (a
    // snapshot list) inside the reconcile block, and the visible-window derivedStateOf re-evaluates
    // off that snapshot list rather than off this map.
    val indexByContentKey = remember { mutableMapOf<Any, Int>() }
    remember(currentKeyList) {
        val currentEntries = currentKeyList.map { entryProvider(it) }
        val newContentKeys = currentEntries.map { it.contentKey }
        presentation.reconcile(currentEntries, navReconcile(previousContentKeys[0] ?: emptyList(), newContentKeys))
        previousContentKeys[0] = newContentKeys
        currentEntries.forEachIndexed { index, e -> indexByContentKey[e.contentKey] = index }
    }

    // Unload entries that have finished leaving — either they slid fully off the front edge
    // (relativeDepth <= -1, the pop case) or a current entry now occupies their index (the replace
    // case, where the driver never moves past them). Such entries are no longer (or never were) in the
    // visible window, so no NavEntryHost runs to self-unload; prune them here at the layout level: drop
    // the saved state and remove from the presentation set + index map. Each entry's per-entry
    // ViewModelStore is cleared automatically by its host's RememberObserver.onForgotten when the host
    // leaves composition.
    val finishedLeaving by remember(presentation, indexByContentKey) {
        derivedStateOf {
            val top = presentation.animatedTop.value
            val currentSize = backStack.size
            presentation.presented.filter { e ->
                e.presentation.isRemoving &&
                    (indexByContentKey[e.contentKey]?.let { index -> top - index <= -1f || index < currentSize } ?: true)
            }
        }
    }
    if (finishedLeaving.isNotEmpty()) {
        SideEffect {
            finishedLeaving.fastForEach { e ->
                stateHolder.removeState(e.contentKey)
                indexByContentKey.remove(e.contentKey)
                presentation.unload(e)
            }
            // A committed back gesture resolves here (its leaving entry just unloaded); release
            // the gesture context that was kept frozen through the settle.
            presentation.gesture = null
        }
    }

    // Drive the single shared float towards the new top index via the shared spring. The renderer only
    // sets the target; the interactive gesture layer may snapToFinger it instead.
    presentation.animateTopTo(backStack.lastIndex.toFloat())

    // Effective interactive dismiss direction of the CURRENT top entry: a per-route override wins,
    // else its governing transition declares the natural direction. Rebuilding one entry to read
    // its metadata is O(1) and only happens when the stack or the global transition changes.
    val topDismissDirection = remember(currentKeyList, transition) {
        val topEntry = entryProvider(currentKeyList.last())
        topEntry.swipeDismissOrNull() ?: (topEntry.transitionOrNull() ?: transition).dismissDirection
    }

    var layoutSize by remember { mutableStateOf(IntSize.Zero) }
    val layoutDirection = LocalLayoutDirection.current
    val density = LocalDensity.current

    Box(
        // The unified swipe recognizer lives on the DISPLAY CONTAINER, not on the top entry's host:
        // the host's hit-rect follows its graphicsLayer translation, so early in a push most of the
        // screen belongs to the covered (input-blocked) layer below and an interrupting back-swipe
        // could never engage. The container sees every pointer on the Initial pass parent-first —
        // before any entry's nested scroll or input-blocking modifier — keeping the two-phase
        // claiming contract (invariant 4) intact while making the whole screen grabbable at any
        // point of an in-flight transition. At rest the behavior is unchanged (the settled top host
        // was full-screen anyway).
        modifier = modifier
            .onSizeChanged { layoutSize = it }
            .navSwipeDismiss(
                // Never on the root (nothing to pop); NavSwipeDirection.None keeps it disabled.
                enabled = topIndex > 0,
                direction = topDismissDirection,
                animatedTop = presentation.animatedTop,
                topIndex = topIndex,
                onCommit = currentOnBack.value,
                onCancel = {},
                onGesture = { presentation.gesture = it },
            ),
    ) {
        // Visible window (spec §4.4 / §9): an entry is visible when -1 < d <= opaqueDepth. The window
        // depth is the MAX opaqueDepth across the global transition and every presented entry's
        // per-route override — the renderer keeps a layer alive while ANY transition in play would, so
        // a Modal override (opaqueDepth 2f) keeps the layer below it visible even under a shallower
        // global transition. Over-keeping a hidden layer is cheap; under-keeping would blank it out.
        // This derivedStateOf reads the coarse (composition-time) animatedTop value so only crossing an
        // integer boundary re-evaluates the window; per-frame floats are read lazily in graphicsLayer.
        val visibleEntries by remember(presentation, transition, indexByContentKey) {
            derivedStateOf {
                val top = presentation.animatedTop.value
                val windowDepth = presentation.presented.fold(transition.opaqueDepth) { acc, entry ->
                    maxOf(acc, (entry.transitionOrNull() ?: transition).opaqueDepth)
                }
                presentation.presented.filter { entry ->
                    val index = indexByContentKey[entry.contentKey] ?: return@filter false
                    val d = relativeDepth(top, index)
                    isVisibleAt(d, windowDepth)
                }
            }
        }

        visibleEntries.fastForEach { entry ->
            key(entry.contentKey) {
                // Local non-null guard, NOT a non-local `return@key`. A non-local return out of an
                // inline @Composable lambda makes the Kotlin compiler emit a synthetic
                // `$$$$$NON_LOCAL_RETURN$$$$$."<anonymous>"()` marker; the Compose Hot Reload javaagent
                // then materialises that into a method literally named "<anonymous>", which the JVM
                // rejects at class load (ClassFormatError). Entries reaching here always have an index
                // (they survived the visibleEntries filter over the same map), so the body always runs.
                indexByContentKey[entry.contentKey]?.let { entryIndex ->
                    // Boundary ownership (§4.3): the entry's own transition governs while it is at the
                    // top (d <= 0); the upper neighbour's transition governs the covered treatment
                    // (0 < d <= 1). NavEntryHost picks per relativeDepth inside the deferred
                    // graphicsLayer so the choice costs no recomposition.
                    val ownTransition = entry.transitionOrNull() ?: transition
                    // Resolve the upper neighbour from the PRESENTED set (which still holds a
                    // popped-but-animating-out entry), NOT the live back stack. A leaving entry keeps its
                    // last index in indexByContentKey, so index + 1 still points at it until it unloads.
                    // Using backStack here would, the instant an upper entry with a per-route transition
                    // (e.g. a bottom-up Modal) is popped, revert this covered layer to the global transition
                    // mid-dismiss — for a Modal over the horizontal-slide default that wrongly parallaxes the
                    // layer below sideways while the modal is still sliding down (§4.3 boundary ownership).
                    val upperEntry = presentation.presented.firstOrNull { indexByContentKey[it.contentKey] == entryIndex + 1 }
                    val upperTransition = upperEntry?.transitionOrNull() ?: transition

                    NavEntryHost(
                        entry = entry,
                        entryIndex = entryIndex,
                        presentation = presentation,
                        stateHolder = stateHolder,
                        ownTransition = ownTransition,
                        upperTransition = upperTransition,
                        change = presentation.change,
                        effects = effects,
                        layoutSize = layoutSize,
                        layoutDirection = layoutDirection,
                        density = density,
                    )
                }
            }
        }
    }
}

/**
 * Renders one presented entry as a pure function of [NavPresentation.animatedTop].
 *
 * The driving float is read **only** inside deferred `graphicsLayer { }` blocks (here and inside the
 * transition's own `transformEntry`), so per-frame changes never recompose this host. The host:
 * - computes the coarse `relativeDepth = animatedTop - entryIndex` (composition-time, integer bucket);
 * - selects the governing transition (own when d <= 0, upper when 0 < d) per §4.3 and applies its
 *   `transformEntry` with a [LiveNavTransitionScope] (per-frame deferred read);
 * - layers the orthogonal [effects] via [NavDisplayEffects]'s per-depth helpers
 *   (`shouldClipCornersAt` / `shouldBlockInputAt` / `dimAlphaAt`);
 * - scopes the content with the shared [stateHolder]'s `EntryStateContent`, a per-entry lifecycle
 *   owner, and a per-entry view-model store owner (Phase 5, real signatures);
 * - keeps composition identity via the surrounding `key(contentKey)` + a cached `movableContentOf`.
 *
 * @param stateHolder the single saveable-state holder created once in [NavDisplayLayout] and shared
 *   across all hosts (stable identity, §0.4).
 */
@Composable
private fun NavEntryHost(
    entry: NavEntry<*>,
    entryIndex: Int,
    presentation: NavPresentation,
    stateHolder: NavSaveableStateHolder,
    ownTransition: NavTransition,
    upperTransition: NavTransition,
    change: NavChange,
    effects: NavDisplayEffects,
    layoutSize: IntSize,
    layoutDirection: LayoutDirection,
    density: Density,
) {
    // opaqueDepth of the upper transition decides culling of this (covered) layer; for the top
    // entry its own transition's opaqueDepth applies. Use the larger so Modal-style transitions
    // (opaqueDepth > 1) keep the lower layer alive.
    val opaqueDepth = maxOf(ownTransition.opaqueDepth, upperTransition.opaqueDepth)

    // Cache a single movableContentOf per host so the entry's content keeps its state if the host is
    // moved within the call hierarchy across recompositions.
    val movableContent = remember {
        movableContentOf<@Composable () -> Unit> { inner -> inner() }
    }

    // Coarse (composition-time) relative depth; only the integer bucket matters here, unlike the
    // per-frame float read inside graphicsLayer.
    val coarseDepth = presentation.animatedTop.value - entryIndex

    // Per-entry state scopes. The view-model store owner and lifecycle owner are remembered per host;
    // the saveable holder is the shared one passed from NavDisplayLayout.
    val vmOwner = rememberNavEntryViewModelStoreOwner()
    val maxLifecycle = navMaxLifecycleFor(entry.presentation, coarseDepth)
    val lifecycleOwner = rememberNavEntryLifecycleOwner(maxLifecycle)

    // Choose the governing transition at the coarse (integer-bucket) depth: own transition while at
    // or entering the top (d <= 0), the upper neighbour's transition while covered (0 < d). The
    // per-frame visual is then a pure deferred read inside the transition's own graphicsLayer.
    val activeTransition = if (coarseDepth <= 0f) ownTransition else upperTransition
    val entryModifier = with(activeTransition) {
        Modifier.transformEntry(
            LiveNavTransitionScope(
                presentation = presentation,
                entryIndex = entryIndex,
                isRemoving = entry.presentation.isRemoving,
                change = change,
                layoutSize = layoutSize,
                layoutDirection = layoutDirection,
                density = density,
            ),
        )
    }

    val clipModifier = if (effects.shouldClipCornersAt(coarseDepth)) {
        // Squircle-clip the transitioning top entry. The radius comes from the effects, so callers
        // can follow the platform screen corner (e.g. rememberNavSystemCornerRadius); the corner
        // set follows the effects' clip mode. absoluteSquircleClip uses physical corners (never
        // flipped by layout direction), so the leading side is picked per layout direction.
        val r = effects.cornerClipRadius
        when (effects.cornerClipMode) {
            // Card-style transitions scale the whole page: every corner meets visible background.
            NavCornerClipMode.All -> Modifier.absoluteSquircleClip(topLeft = r, topRight = r, bottomRight = r, bottomLeft = r)

            // Slide-style transitions: only the corners that meet the screen edge as the entering
            // page slides in — left for LTR, right for RTL — matching the reference navigation.
            NavCornerClipMode.Leading ->
                if (layoutDirection == LayoutDirection.Ltr) {
                    Modifier.absoluteSquircleClip(topLeft = r, topRight = 0.dp, bottomRight = 0.dp, bottomLeft = r)
                } else {
                    Modifier.absoluteSquircleClip(topLeft = 0.dp, topRight = r, bottomRight = r, bottomLeft = 0.dp)
                }
        }
    } else {
        Modifier
    }

    val blockInputModifier = if (effects.shouldBlockInputAt(coarseDepth)) {
        Modifier.pointerInput(Unit) {
            awaitPointerEventScope {
                while (true) {
                    val event = awaitPointerEvent(pass = PointerEventPass.Initial)
                    event.changes.fastForEach { it.consume() }
                }
            }
        }
    } else {
        Modifier
    }

    Box(modifier = entryModifier.then(clipModifier).then(blockInputModifier)) {
        ProvideNavEntryViewModelStore(vmOwner) {
            ProvideNavEntryLifecycle(lifecycleOwner) {
                stateHolder.EntryStateContent(entry.contentKey) {
                    movableContent { entry.Content() }
                }
            }
        }

        // Dim scrim on the covered layer, alpha driven by a deferred relative-depth read via the
        // effects helper.
        if (effects.dimAmount > 0f && coarseDepth > 0f && coarseDepth <= opaqueDepth) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer {
                        val d = presentation.animatedTop.value - entryIndex
                        alpha = effects.dimAlphaAt(d)
                    }
                    .background(Color.Black),
            )
        }
    }
}

/**
 * Host composable that renders and animates a navigation back stack with continuous-depth float
 * transitions.
 *
 * This is the primary overload: routes are registered via the [content] DSL ([NavEntryBuilder.entry]).
 * The [transition] is the global default; individual routes may override it via `entry(transition = …)`.
 *
 * @param backStack the live back stack to render (a [androidx.compose.runtime.snapshots.SnapshotStateList] of [NavKey]).
 * @param modifier modifier applied to the host container.
 * @param onBack callback for a system/predictive back; defaults to popping the last entry.
 * @param transition the global default [NavTransition]; per-route overrides win.
 * @param effects orthogonal visual effects (corner clip / dim / input blocking).
 * @param content the route-registration DSL block.
 */
@Composable
fun NavDisplay(
    backStack: NavBackStack,
    modifier: Modifier = Modifier,
    onBack: () -> Unit = { backStack.removeLastOrNull() },
    transition: NavTransition = NavTransitions.Cupertino,
    effects: NavDisplayEffects = NavDisplayEffects(),
    content: NavEntryBuilder.() -> Unit,
) {
    val provider = remember(content) { entryProvider(content) }
    NavDisplay(
        backStack = backStack,
        entryProvider = provider,
        modifier = modifier,
        onBack = onBack,
        transition = transition,
        effects = effects,
    )
}

/**
 * Convenience overload driving the back stack through a [NavController].
 *
 * @param navController the controller whose [NavController.backStack] is rendered.
 * @param modifier modifier applied to the host container.
 * @param onBack callback for a system/predictive back; defaults to [NavController.pop].
 * @param transition the global default [NavTransition]; per-route overrides win.
 * @param effects orthogonal visual effects.
 * @param content the route-registration DSL block.
 */
@Composable
@NonRestartableComposable
fun NavDisplay(
    navController: NavController,
    modifier: Modifier = Modifier,
    onBack: () -> Unit = { navController.pop() },
    transition: NavTransition = NavTransitions.Cupertino,
    effects: NavDisplayEffects = NavDisplayEffects(),
    content: NavEntryBuilder.() -> Unit,
) {
    NavDisplay(
        backStack = navController.backStack,
        modifier = modifier,
        onBack = onBack,
        transition = transition,
        effects = effects,
        content = content,
    )
}

/**
 * Low-level overload taking a prebuilt [entryProvider] instead of the DSL lambda.
 *
 * Declared `internal` because its signature exposes the `internal` [NavEntry] type; the public DSL
 * overloads above forward to it within the module.
 *
 * @param backStack the live back stack to render.
 * @param entryProvider lookup that builds a [NavEntry] for each [NavKey].
 * @param modifier modifier applied to the host container.
 * @param onBack callback for a system/predictive back; defaults to popping the last entry.
 * @param transition the global default [NavTransition]; per-route overrides win.
 * @param effects orthogonal visual effects.
 */
@Composable
internal fun NavDisplay(
    backStack: NavBackStack,
    entryProvider: (NavKey) -> NavEntry<*>,
    modifier: Modifier = Modifier,
    onBack: () -> Unit = { backStack.removeLastOrNull() },
    transition: NavTransition = NavTransitions.Cupertino,
    effects: NavDisplayEffects = NavDisplayEffects(),
) {
    require(backStack.isNotEmpty()) { "NavDisplay back stack cannot be empty" }
    NavDisplayLayout(
        backStack = backStack,
        entryProvider = entryProvider,
        onBack = onBack,
        transition = transition,
        effects = effects,
        modifier = modifier,
    )
}
