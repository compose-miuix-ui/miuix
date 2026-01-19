// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

@file:JvmName("NavDisplayKt")
@file:JvmMultifileClass

package androidx.navigation3.ui

import androidx.collection.mutableObjectFloatMapOf
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.SeekableTransitionState
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.rememberTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEach
import androidx.compose.ui.util.fastForEachReversed
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.rememberLifecycleOwner
import androidx.navigation3.animation.NavTransitionEasing
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavEntryDecorator
import androidx.navigation3.runtime.rememberDecoratedNavEntries
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.scene.LocalEntriesToExcludeFromCurrentScene
import androidx.navigation3.scene.Scene
import androidx.navigation3.scene.SceneInfo
import androidx.navigation3.scene.SceneState
import androidx.navigation3.scene.SceneStrategy
import androidx.navigation3.scene.SinglePaneSceneStrategy
import androidx.navigation3.scene.rememberSceneState
import androidx.navigation3.ui.NavDisplay.POP_TRANSITION_SPEC
import androidx.navigation3.ui.NavDisplay.PREDICTIVE_POP_TRANSITION_SPEC
import androidx.navigation3.ui.NavDisplay.TRANSITION_SPEC
import androidx.navigation3.ui.NavDisplay.popTransitionSpec
import androidx.navigation3.ui.NavDisplay.predictivePopTransitionSpec
import androidx.navigation3.ui.NavDisplay.transitionSpec
import androidx.navigationevent.NavigationEvent
import androidx.navigationevent.NavigationEventTransitionState.Idle
import androidx.navigationevent.NavigationEventTransitionState.InProgress
import androidx.navigationevent.compose.NavigationBackHandler
import androidx.navigationevent.compose.NavigationEventState
import androidx.navigationevent.compose.rememberNavigationEventState
import com.mocharealm.gaze.capsule.ContinuousRoundedRectangle
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import top.yukonga.miuix.kmp.utils.Platform
import top.yukonga.miuix.kmp.utils.getRoundedCorner
import top.yukonga.miuix.kmp.utils.platform
import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName

/** Object that indicates the features that can be handled by the [NavDisplay] */
object NavDisplay {
    /**
     * Function to be called on the [NavEntry.metadata] or [Scene.metadata] to notify the
     * [NavDisplay] of how the content should be animated using the provided [ContentTransform].
     *
     * **IMPORTANT** [NavDisplay] only looks at the [Scene.metadata] to determine the
     * [transitionSpec], it is the responsibility of the [Scene.metadata] to decide which
     * [transitionSpec] to return, whether that be from the [NavEntry.metadata] or something custom.
     *
     * @param transitionSpec the [ContentTransform] to be used when adding to the backstack. If this
     *   is null, the transition will fallback to the transition set on the [NavDisplay]
     */
    fun transitionSpec(
        transitionSpec: AnimatedContentTransitionScope<Scene<*>>.() -> ContentTransform?,
    ): Map<String, Any> = mapOf(TRANSITION_SPEC to transitionSpec)

    /**
     * Function to be called on the [NavEntry.metadata] or [Scene.metadata] to notify the
     * [NavDisplay] that, when popping from backstack, the content should be animated using the
     * provided [ContentTransform].
     *
     * **IMPORTANT** [NavDisplay] only looks at the [Scene.metadata] to determine the
     * [popTransitionSpec], it is the responsibility of the [Scene.metadata] to decide which
     * [popTransitionSpec] to return, whether that be from the [NavEntry.metadata] or something
     * custom.
     *
     * @param popTransitionSpec the [ContentTransform] to be used when popping from backstack. If
     *   this is null, the transition will fallback to the transition set on the [NavDisplay]
     */
    fun popTransitionSpec(
        popTransitionSpec: AnimatedContentTransitionScope<Scene<*>>.() -> ContentTransform?,
    ): Map<String, Any> = mapOf(POP_TRANSITION_SPEC to popTransitionSpec)

    /**
     * Function to be called on the [NavEntry.metadata] or [Scene.metadata] to notify the
     * [NavDisplay] that, when popping from backstack using a Predictive back gesture, the content
     * should be animated using the provided [predictivePopTransitionSpec].
     *
     * **IMPORTANT** [NavDisplay] only looks at the [Scene.metadata] to determine the
     * [predictivePopTransitionSpec], it is the responsibility of the [Scene.metadata] to decide
     * which [predictivePopTransitionSpec] to return, whether that be from the [NavEntry.metadata]
     * or something custom.
     *
     * @param predictivePopTransitionSpec the [PredictivePopTransitionSpec] to be used when popping from
     *   backStack with predictive back gesture. If not specified in [Scene.metadata], the transition
     *   will fallback to the default transition set on the [NavDisplay].
     */
    fun predictivePopTransitionSpec(
        predictivePopTransitionSpec: PredictivePopTransitionSpec,
    ): Map<String, Any> = mapOf(PREDICTIVE_POP_TRANSITION_SPEC to predictivePopTransitionSpec)

    internal const val TRANSITION_SPEC = "transitionSpec"
    internal const val POP_TRANSITION_SPEC = "popTransitionSpec"
    internal const val PREDICTIVE_POP_TRANSITION_SPEC = "predictivePopTransitionSpec"
}

/**
 * Class to define the transition for predictive back.
 *
 * @param animationSpec the [FiniteAnimationSpec] to be used when committing the predictive back gesture.
 * @param contentTransform the [ContentTransform] to be used when popping from backStack with predictive back gesture.
 */
data class PredictivePopTransitionSpec(
    val animationSpec: FiniteAnimationSpec<Float>,
    val contentTransform: AnimatedContentTransitionScope<Scene<*>>.(@NavigationEvent.SwipeEdge Int) -> ContentTransform,
)

/**
 * A nav display that renders and animates between different [Scene]s, each of which can render one
 * or more [NavEntry]s.
 *
 * The [Scene]s are calculated with the given [SceneStrategy], which may be an assembled delegated
 * chain of [SceneStrategy]s. If no [Scene] is calculated, the fallback will be to a
 * [SinglePaneSceneStrategy].
 *
 * It is allowable for different [Scene]s to render the same [NavEntry]s, perhaps on some conditions
 * as determined by the [sceneStrategy] based on window size, form factor, other arbitrary logic.
 *
 * If this happens, and these [Scene]s are rendered at the same time due to animation or predictive
 * back, then the content for the [NavEntry] will only be rendered in the most recent [Scene] that
 * is the target for being the current scene as determined by [sceneStrategy]. This enforces a
 * unique invocation of each [NavEntry], even if it is displayable by two different [Scene]s.
 *
 * By default, AnimatedContent transitions are prioritized in this order:
 * ```
 * transitioning [NavEntry.metadata] > current [Scene.metadata] > NavDisplay defaults
 * ```
 *
 * However, a [Scene.metadata] does have the ability to override [NavEntry.metadata]. Nevertheless,
 * the final fallback will always be the NavDisplay's default transitions.
 *
 * @param backStack the collection of keys that represents the state that needs to be handled
 * @param modifier the modifier to be applied to the layout.
 * @param contentAlignment The [Alignment] of the [AnimatedContent]
 * @param onBack a callback for handling system back press. By default, this pops a single item off
 *   of the given back stack if it is a [MutableList], otherwise you should provide this parameter.
 * @param entryDecorators list of [NavEntryDecorator] to add information to the entry content
 * @param sceneStrategy the [SceneStrategy] to determine which scene to render a list of entries.
 * @param sharedTransitionScope the [SharedTransitionScope] to allow transitions between scenes.
 * @param sizeTransform the [SizeTransform] for the [AnimatedContent].
 * @param transitionSpec Default [ContentTransform] when navigating to [NavEntry]s.
 * @param popTransitionSpec Default [ContentTransform] when popping [NavEntry]s.
 * @param predictivePopTransitionSpec Default [PredictivePopTransitionSpec] when popping with predictive back
 *   [NavEntry]s.
 * @param entryProvider lambda used to construct each possible [NavEntry]
 */
@Composable
fun <T : Any> NavDisplay(
    backStack: List<T>,
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.TopStart,
    onBack: () -> Unit = {
        if (backStack is MutableList<T>) {
            backStack.removeLastOrNull()
        }
    },
    entryDecorators: List<NavEntryDecorator<T>> =
        listOf(rememberSaveableStateHolderNavEntryDecorator()),
    sceneStrategy: SceneStrategy<T> = SinglePaneSceneStrategy(),
    sharedTransitionScope: SharedTransitionScope? = null,
    sizeTransform: SizeTransform? = null,
    transitionSpec: AnimatedContentTransitionScope<Scene<T>>.() -> ContentTransform =
        defaultTransitionSpec(),
    popTransitionSpec: AnimatedContentTransitionScope<Scene<T>>.() -> ContentTransform =
        defaultPopTransitionSpec(),
    predictivePopTransitionSpec: PredictivePopTransitionSpec =
        defaultPredictivePopTransitionSpec(),
    entryProvider: (key: T) -> NavEntry<T>,
) {
    require(backStack.isNotEmpty()) { "NavDisplay backstack cannot be empty" }

    val entries =
        rememberDecoratedNavEntries(
            backStack = backStack,
            entryDecorators = entryDecorators,
            entryProvider = entryProvider,
        )

    NavDisplay(
        entries = entries,
        sceneStrategy = sceneStrategy,
        sharedTransitionScope = sharedTransitionScope,
        modifier = modifier,
        contentAlignment = contentAlignment,
        sizeTransform = sizeTransform,
        transitionSpec = transitionSpec,
        popTransitionSpec = popTransitionSpec,
        predictivePopTransitionSpec = predictivePopTransitionSpec,
        onBack = onBack,
    )
}

/**
 * A nav display that renders and animates between different [Scene]s, each of which can render one
 * or more [NavEntry]s.
 *
 * The [Scene]s are calculated with the given [SceneStrategy], which may be an assembled delegated
 * chain of [SceneStrategy]s. If no [Scene] is calculated, the fallback will be to a
 * [SinglePaneSceneStrategy].
 *
 * It is allowable for different [Scene]s to render the same [NavEntry]s, perhaps on some conditions
 * as determined by the [sceneStrategy] based on window size, form factor, other arbitrary logic.
 *
 * If this happens, and these [Scene]s are rendered at the same time due to animation or predictive
 * back, then the content for the [NavEntry] will only be rendered in the most recent [Scene] that
 * is the target for being the current scene as determined by [sceneStrategy]. This enforces a
 * unique invocation of each [NavEntry], even if it is displayable by two different [Scene]s.
 *
 * By default, AnimatedContent transitions are prioritized in this order:
 * ```
 * transitioning [NavEntry.metadata] > current [Scene.metadata] > NavDisplay defaults
 * ```
 *
 * However, a [Scene.metadata] does have the ability to override [NavEntry.metadata]. Nevertheless,
 * the final fallback will always be the NavDisplay's default transitions.
 *
 * **WHEN TO USE** This overload can be used when you need to switch between different backStacks
 * and each with their own separate decorator states, or when you want to concatenate backStacks and
 * their states to form a larger backstack.
 *
 * **HOW TO USE** The [entries] can first be created via [rememberDecoratedNavEntries] in order to
 * associate a backStack with a particular set of states.
 *
 * @param entries the list of [NavEntry] built from a backStack. The entries can be created from a
 *   backStack decorated with [NavEntryDecorator] via [rememberDecoratedNavEntries].
 * @param modifier the modifier to be applied to the layout.
 * @param contentAlignment The [Alignment] of the [AnimatedContent]
 * @param sceneStrategy the [SceneStrategy] to determine which scene to render a list of entries.
 * @param sharedTransitionScope the [SharedTransitionScope] to allow transitions between scenes.
 * @param sizeTransform the [SizeTransform] for the [AnimatedContent].
 * @param transitionSpec Default [ContentTransform] when navigating to [NavEntry]s.
 * @param popTransitionSpec Default [ContentTransform] when popping [NavEntry]s.
 * @param predictivePopTransitionSpec Default [PredictivePopTransitionSpec] when popping [NavEntry]s with predictive back.
 * @param onBack a callback for handling system back press.
 * @see [rememberDecoratedNavEntries]
 */
@Composable
fun <T : Any> NavDisplay(
    entries: List<NavEntry<T>>,
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.TopStart,
    sceneStrategy: SceneStrategy<T> = SinglePaneSceneStrategy(),
    sharedTransitionScope: SharedTransitionScope? = null,
    sizeTransform: SizeTransform? = null,
    transitionSpec: AnimatedContentTransitionScope<Scene<T>>.() -> ContentTransform =
        defaultTransitionSpec(),
    popTransitionSpec: AnimatedContentTransitionScope<Scene<T>>.() -> ContentTransform =
        defaultPopTransitionSpec(),
    predictivePopTransitionSpec: PredictivePopTransitionSpec =
        defaultPredictivePopTransitionSpec(),
    onBack: () -> Unit,
) {
    require(entries.isNotEmpty()) { "NavDisplay entries cannot be empty" }

    val sceneState = rememberSceneState(entries, sceneStrategy, sharedTransitionScope, onBack)
    val scene = sceneState.currentScene

    // Predictive Back Handling
    val currentInfo = SceneInfo(scene)
    val previousSceneInfos = sceneState.previousScenes.map { SceneInfo(it) }
    val gestureState =
        rememberNavigationEventState(currentInfo = currentInfo, backInfo = previousSceneInfos)

    NavigationBackHandler(
        state = gestureState,
        isBackEnabled = scene.previousEntries.isNotEmpty(),
        onBackCompleted = {
            // If `enabled` becomes stale (e.g., it was set to false but a gesture was
            // dispatched in the same frame), this may result in no entries being popped
            // due to entries.size being smaller than scene.previousEntries.size
            // but that's preferable to crashing with an IndexOutOfBoundsException
            repeat(entries.size - scene.previousEntries.size) { onBack() }
        },
    )

    NavDisplay(
        sceneState,
        gestureState,
        modifier,
        contentAlignment,
        sizeTransform,
        transitionSpec,
        popTransitionSpec,
        predictivePopTransitionSpec,
    )
}

/**
 * A nav display that renders and animates between different [Scene]s, each of which can render one
 * or more [NavEntry]s.
 *
 * By default, AnimatedContent transitions are prioritized in this order:
 * ```
 * transitioning [NavEntry.metadata] > current [Scene.metadata] > NavDisplay defaults
 * ```
 *
 * However, a [Scene.metadata] does have the ability to override [NavEntry.metadata]. Nevertheless,
 * the final fallback will always be the NavDisplay's default transitions.
 *
 * @param sceneState the state that determines what current scene of the NavDisplay.
 * @param modifier the modifier to be applied to the layout.
 * @param contentAlignment The [Alignment] of the [AnimatedContent]
 * @param navigationEventState the [NavigationEventState] responsible for handling back navigation
 * @param sizeTransform the [SizeTransform] for the [AnimatedContent].
 * @param transitionSpec Default [ContentTransform] when navigating to [NavEntry]s.
 * @param popTransitionSpec Default [ContentTransform] when popping [NavEntry]s.
 * @param predictivePopTransitionSpec Default [PredictivePopTransitionSpec] when popping [NavEntry]s with
 *   predictive back.
 * @see [rememberSceneState]
 */
@Composable
fun <T : Any> NavDisplay(
    sceneState: SceneState<T>,
    navigationEventState: NavigationEventState<SceneInfo<T>>,
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.TopStart,
    sizeTransform: SizeTransform? = null,
    transitionSpec: AnimatedContentTransitionScope<Scene<T>>.() -> ContentTransform =
        defaultTransitionSpec(),
    popTransitionSpec: AnimatedContentTransitionScope<Scene<T>>.() -> ContentTransform =
        defaultPopTransitionSpec(),
    predictivePopTransitionSpec: PredictivePopTransitionSpec =
        defaultPredictivePopTransitionSpec(),
) {
    // Calculate current Scene and set up transitions
    val scene = sceneState.currentScene
    val transitionState = remember {
        // The state returned here cannot be nullable cause it produces the input of the
        // transitionSpec passed into the AnimatedContent and that must match the non-nullable
        // scope exposed by the transitions on the NavHost and composable APIs.
        SeekableTransitionState(scene)
    }

    val transition = rememberTransition(transitionState, label = "scene")

    // Transition Handling
    val currentEntriesState = remember { mutableStateOf(sceneState.entries) }
    val isPopState = remember { mutableStateOf(false) }

    if (currentEntriesState.value != sceneState.entries) {
        isPopState.value = isPop(currentEntriesState.value, sceneState.entries)
        currentEntriesState.value = sceneState.entries
    }
    val isPop = isPopState.value

    // Set up Gesture Back tracking
    val previousScene = sceneState.previousScenes.lastOrNull()
    val gestureTransition = navigationEventState.transitionState

    val inPredictiveBack = gestureTransition is InProgress && previousScene != null
    val progress =
        when (gestureTransition) {
            is Idle -> 0f
            is InProgress -> gestureTransition.latestEvent.progress
        }
    val swipeEdge =
        when (gestureTransition) {
            is Idle -> NavigationEvent.EDGE_NONE
            is InProgress -> gestureTransition.latestEvent.swipeEdge
        }

    // Track currently rendered Scenes and their ZIndices
    val sceneMap = remember { mutableStateMapOf<SceneKey, Scene<T>>() }
    val zIndices = remember { mutableObjectFloatMapOf<SceneKey>() }

    // Cache stable keys per Scene instance during transitions and gestures.
    val sceneKeyCache = remember { mutableMapOf<Scene<T>, SceneKey>() }
    fun sceneKeyOf(target: Scene<T>): SceneKey = sceneKeyCache.getOrPut(target) { target.createSceneKey() }

    // Explicit invalidation signal for rebuilding sceneToExcludedEntryMap.
    var excludedMapVersion by remember { mutableIntStateOf(0) }

    val initialScene = transition.currentState
    val targetScene = transition.targetState

    val initialKey = sceneKeyOf(initialScene)
    val targetKey = sceneKeyOf(targetScene)

    // Update Z-Indices based on absolute position in the stack
    val currentScenes = remember(sceneState) {
        sceneState.previousScenes + sceneState.currentScene + sceneState.overlayScenes
    }

    var didMutateKeys = false
    currentScenes.forEachIndexed { index, scene ->
        val key = sceneKeyOf(scene)
        if (!zIndices.containsKey(key) || zIndices[key] != index.toFloat()) {
            zIndices[key] = index.toFloat()
            didMutateKeys = true
        }
    }

    val initialZIndex = zIndices.getOrElse(initialKey) { 0f }
    val targetZIndex = zIndices.getOrElse(targetKey) {
        currentScenes.indexOf(targetScene).let { if (it != -1) it.toFloat() else 0f }
    }

    if (sceneMap[targetKey] !== targetScene) {
        sceneMap[targetKey] = targetScene
        didMutateKeys = true
    }
    if (sceneMap[initialKey] !== initialScene) {
        sceneMap[initialKey] = initialScene
        didMutateKeys = true
    }
    if (didMutateKeys) excludedMapVersion += 1

    val overlayScenes = sceneState.overlayScenes

    // Determine which entries should be rendered within each currently rendered scene,
    // using the z-index of each screen to always show the entry on the topmost screen
    // The map is Pair<KCLass<Scene<T>, Scene.key> to a Set of NavEntry.key values
    val sceneToExcludedEntryMap =
        remember(excludedMapVersion, overlayScenes) {
            buildMap {
                val scenes = mutableListOf<Scene<T>>()
                // Ensure we include the current and target scenes from the transition, even if
                // they haven't been fully synchronized to sceneMap yet.
                val relevantScenes = sceneMap.values.toMutableSet().apply {
                    add(initialScene)
                    add(targetScene)
                }

                // First sort the non-overlay scenes by z-order in descending order.
                relevantScenes
                    .sortedByDescending { scene ->
                        val key = sceneKeyOf(scene)
                        if (zIndices.containsKey(key)) zIndices[key] else 0f
                    }
                    .forEach { if (!scenes.contains(it)) scenes.add(it) }

                // At this point we have a list in this order
                // [zIndex larger --> zIndex smaller]

                // Then combine them with overlay scenes to get the complete order of scenes in
                // z-order
                // overlayScenes is already in order of [top most overlay ---> lowest overlay],
                // so we put overlayScenes in front, and then add the scenes after.
                val scenesInZOrder = overlayScenes + scenes
                // At this point we have a list of all scenes in this order
                // [top most overlay ---> lowest overlay, other scenes zIndex larger --> zIndex
                // smaller]

                // Then we track which entries are already covered
                val coveredEntries = mutableSetOf<NavEntry<T>>()

                // In scenesInZOrder's natural order, go through each scene, marking
                // all of the entries not already covered as associated
                // with that scene. This ensures that each unique contentKey will only be
                // rendered by one scene.
                scenesInZOrder.fastForEach { scene ->
                    val excludedKeys = mutableSetOf<Any>()
                    scene.entries.forEach { entry ->
                        if (entry in coveredEntries) {
                            excludedKeys.add(entry.contentKey)
                        } else {
                            coveredEntries.add(entry)
                        }
                    }
                    put(sceneKeyOf(scene), excludedKeys)
                }
            }
        }

    // Determine which NavEntry's transition to use(if any), prioritizing the one with highest
    // zIndex
    val transitionScene =
        if (initialZIndex >= targetZIndex) {
            transition.currentState
        } else {
            transition.targetState
        }

    // check if in gesture back
    // Capture the fraction when we start interrupting an entry animation
    var entryInterruptionFraction by remember { mutableFloatStateOf(1f) }
    LaunchedEffect(inPredictiveBack) {
        if (inPredictiveBack && transition.currentState == previousScene) {
            entryInterruptionFraction = transitionState.fraction
        }
    }

    if (inPredictiveBack) {
        LaunchedEffect(previousScene, progress) {
            if (transition.currentState == previousScene) {
                // Interrupting entry: A -> B
                // Scale progress to match the partial entry state
                val startPhysical = NavAnimationEasing.transform(entryInterruptionFraction)
                val targetPhysical = startPhysical * (1f - progress)
                val targetFraction = NavAnimationEasing.inverseTransform(targetPhysical)
                transitionState.seekTo(targetFraction, scene)
            } else {
                // Standard back: B -> A
                transitionState.seekTo(progress, previousScene)
            }
        }
    } else {
        LaunchedEffect(scene, sceneState.entries) {
            // Cleanup stale scenes from interrupted transitions
            val currentKey = sceneKeyOf(transitionState.currentState)
            val targetKey = sceneKeyOf(scene)
            val activeTargetKey = sceneKeyOf(transitionState.targetState)
            // Creating a copy to avoid ConcurrentModificationException
            val sceneMapSizeBefore = sceneMap.size
            sceneMap.keys.toList().forEach { key ->
                if (key != currentKey && key != targetKey && key != activeTargetKey) {
                    sceneMap.remove(key)
                }
            }
            val sceneMapSizeAfter = sceneMap.size
            val zIndicesSizeBefore = zIndices.size
            zIndices.removeIf { key, _ -> key != currentKey && key != targetKey && key != activeTargetKey }
            val zIndicesSizeAfter = zIndices.size
            if (sceneMapSizeBefore != sceneMapSizeAfter || zIndicesSizeBefore != zIndicesSizeAfter) {
                excludedMapVersion += 1
            }

            if (transitionState.currentState != scene) {
                if (transitionState.targetState == scene) {
                    // Predictive Back has been committed
                    // so now we need to animate to the final state
                    // Resolve the PredictiveBackTransition
                    val predictiveTransition = (transition.targetState as? Scene<*>)
                        ?.metadata
                        ?.get(PREDICTIVE_POP_TRANSITION_SPEC) as? PredictivePopTransitionSpec
                        ?: predictivePopTransitionSpec

                    transitionState.animateTo(
                        scene,
                        animationSpec = predictiveTransition.animationSpec,
                    )
                } else {
                    transitionState.animateTo(scene)
                }
            } else {
                // Predictive Back has been cancelled
                // so now we need to seekTo+snapTo the final state

                // convert from nanoseconds to milliseconds
                val totalDuration = transition.totalDurationNanos / 1000000

                // It it got cancelled, animate back to the
                // initial state, reversing what we seeked to
                if (transition.targetState != scene) {
                    val remainingDuration = (transitionState.fraction * totalDuration).toInt()
                    animate(
                        transitionState.fraction,
                        0f,
                        animationSpec = tween(remainingDuration, easing = NavAnimationEasing),
                    ) { value, _ ->
                        this@LaunchedEffect.launch {
                            // Seek the transition towards the finalFraction
                            transitionState.seekTo(value, transition.targetState)
                        }
                    }
                }
                // Once the animation finishes, we need to snap to the right state.
                transitionState.snapTo(scene)
            }

            // Cleanup after animation settles
            val settledKey = sceneKeyOf(scene)
            // Creating a copy to avoid ConcurrentModificationException
            val sceneMapSizeBeforeSettle = sceneMap.size
            sceneMap.keys.toList().forEach { key ->
                if (key != settledKey) {
                    sceneMap.remove(key)
                }
            }
            val sceneMapSizeAfterSettle = sceneMap.size
            val zIndicesSizeBeforeSettle = zIndices.size
            zIndices.removeIf { key, _ -> key != settledKey }
            val zIndicesSizeAfterSettle = zIndices.size
            if (sceneMapSizeBeforeSettle != sceneMapSizeAfterSettle ||
                zIndicesSizeBeforeSettle != zIndicesSizeAfterSettle
            ) {
                excludedMapVersion += 1
            }

            // Drop cached keys for scenes that can no longer be referenced after this update.
            run {
                val activeScenes = buildSet {
                    add(transitionState.currentState)
                    add(transitionState.targetState)
                    add(scene)
                    overlayScenes.fastForEach { add(it) }
                }
                sceneKeyCache.keys.retainAll(activeScenes)
            }
        }
    }

    val contentTransform: AnimatedContentTransitionScope<Scene<T>>.() -> ContentTransform = {
        when {
            inPredictiveBack -> {
                val predictiveTransition = transitionScene.metadata[PREDICTIVE_POP_TRANSITION_SPEC] as? PredictivePopTransitionSpec
                    ?: predictivePopTransitionSpec
                @Suppress("UNCHECKED_CAST")
                predictiveTransition.contentTransform.invoke(this as AnimatedContentTransitionScope<Scene<*>>, swipeEdge)
            }

            isPop -> {
                transitionScene.contentTransform(POP_TRANSITION_SPEC)?.invoke(this)
                    ?: popTransitionSpec(this)
            }

            else -> {
                transitionScene.contentTransform(TRANSITION_SPEC)?.invoke(this)
                    ?: transitionSpec(this)
            }
        }
    }

    transition.AnimatedContent(
        contentKey = { target -> sceneKeyOf(target) },
        contentAlignment = contentAlignment,
        modifier = modifier,
        transitionSpec = {
            ContentTransform(
                targetContentEnter = contentTransform(this).targetContentEnter,
                initialContentExit = contentTransform(this).initialContentExit,
                // z-index increases based on stack position.
                targetContentZIndex = targetZIndex,
                sizeTransform = sizeTransform,
            )
        },
    ) { targetScene ->
        // If there is a transition in progress, set the maximum state of the scene (and every
        // entry within the scene) to STARTED - only allow the RESUMED state when the
        // AnimatedContent has settled into its final state
        val isSettled = transition.currentState == transition.targetState
        val sceneLifecycleOwner =
            rememberLifecycleOwner(
                maxLifecycle = if (isSettled) Lifecycle.State.RESUMED else Lifecycle.State.STARTED,
            )
        CompositionLocalProvider(
            LocalLifecycleOwner provides sceneLifecycleOwner,
            LocalNavAnimatedContentScope provides this,
            LocalEntriesToExcludeFromCurrentScene provides
                    sceneToExcludedEntryMap.getOrElse(sceneKeyOf(targetScene)) { emptySet() },
        ) {
            val corner = if (Platform.Android == platform() && !isInMultiWindowMode()) getRoundedCorner() else 0.dp
            val shape = remember(corner) {
                ContinuousRoundedRectangle(topStart = corner, bottomStart = corner)
            }

            val myKey = sceneKeyOf(targetScene)
            val myIndex = currentScenes.indexOfFirst { sceneKeyOf(it) == myKey }
            val effectiveIndex = if (myIndex == -1) Int.MAX_VALUE else myIndex

            val transitionTargetKey = sceneKeyOf(transition.targetState)
            val targetIndex = currentScenes.indexOfFirst { sceneKeyOf(it) == transitionTargetKey }
            val effectiveTargetIndex = if (targetIndex == -1) Int.MAX_VALUE else targetIndex

            val shouldDim = effectiveIndex < effectiveTargetIndex
            val showCorner = effectiveIndex > 0 && !isSettled

            val roundedModifier = if (showCorner) Modifier.clip(shape) else Modifier

            val blockInputModifier = if (!isSettled && targetScene != transition.targetState) {
                remember {
                    Modifier.pointerInput(Unit) {
                        awaitPointerEventScope {
                            while (true) {
                                val event = awaitPointerEvent(pass = PointerEventPass.Initial)
                                event.changes.fastForEach { it.consume() }
                            }
                        }
                    }
                }
            } else {
                Modifier
            }

            androidx.compose.foundation.layout.Box(
                modifier = Modifier
                    .then(roundedModifier)
                    .then(blockInputModifier),
            ) {
                targetScene.content()

                val dimAlpha by transition.animateFloat(
                    transitionSpec = { tween(durationMillis = 550, easing = NavAnimationEasing) },
                    label = "dimAlpha",
                ) { state ->
                    val stateKey = sceneKeyOf(state)
                    val stateIndex = currentScenes.indexOfFirst { sceneKeyOf(it) == stateKey }
                    val effectiveStateIndex = if (stateIndex == -1) Int.MAX_VALUE else stateIndex

                    if (effectiveIndex < effectiveStateIndex) 0.5f else 0f
                }

                val showDim = dimAlpha > 0f && (shouldDim || !isSettled)
                if (showDim) {
                    androidx.compose.foundation.layout.Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .graphicsLayer { alpha = dimAlpha }
                            .background(Color.Black),
                    )
                }
            }
        }
    }

    // Clean-up scene book-keeping once the transition is finished
    LaunchedEffect(transition) {
        snapshotFlow { transition.isRunning }
            .filter { !it }
            .collect {
                val targetKey = transition.targetState::class to transition.targetState.key
                // Creating a copy to avoid ConcurrentModificationException
                @Suppress("ListIterator")
                sceneMap.keys.toList().forEach { key ->
                    if (key != targetKey) {
                        sceneMap.remove(key)
                    }
                }
                // Creating a copy to avoid ConcurrentModificationException
                zIndices.removeIf { key, _ -> key != targetKey }
            }
    }

    // Show all OverlayScene instances above the AnimatedContent
    overlayScenes.fastForEachReversed { overlayScene ->
        CompositionLocalProvider(
            LocalEntriesToExcludeFromCurrentScene provides
                    sceneToExcludedEntryMap.getOrElse(sceneKeyOf(overlayScene)) { emptySet() },
        ) {
            overlayScene.content.invoke()
        }
    }
}

private fun <T : Any> isPop(oldBackStack: List<T>, newBackStack: List<T>): Boolean {
    // entire stack replaced
    if (oldBackStack.first() != newBackStack.first()) return false
    // navigated
    if (newBackStack.size > oldBackStack.size) return false

    val divergingIndex =
        newBackStack.indices.firstOrNull { index -> newBackStack[index] != oldBackStack[index] }
    // if newBackStack never diverged from oldBackStack, then it is a clean subset of the oldStack
    // and is a pop
    return divergingIndex == null && newBackStack.size != oldBackStack.size
}

@Suppress("UNCHECKED_CAST")
private fun <T : Any> Scene<T>.contentTransform(
    key: String,
): (AnimatedContentTransitionScope<Scene<T>>.() -> ContentTransform)? = metadata[key] as? AnimatedContentTransitionScope<Scene<T>>.() -> ContentTransform

/** Default [transitionSpec] for forward navigation to be used by [NavDisplay]. */
fun <T : Any> defaultTransitionSpec(): AnimatedContentTransitionScope<Scene<T>>.() -> ContentTransform = {
    ContentTransform(
        slideInHorizontally(
            initialOffsetX = { it },
            animationSpec = tween(durationMillis = 500, easing = NavAnimationEasing),
        ),
        slideOutHorizontally(
            targetOffsetX = { -it / 4 },
            animationSpec = tween(durationMillis = 500, easing = NavAnimationEasing),
        ),
    )
}

/** Default [transitionSpec] for pop navigation to be used by [NavDisplay]. */
fun <T : Any> defaultPopTransitionSpec(): AnimatedContentTransitionScope<Scene<T>>.() -> ContentTransform = {
    ContentTransform(
        slideInHorizontally(
            initialOffsetX = { -it / 4 },
            animationSpec = tween(durationMillis = 500, easing = NavAnimationEasing),
        ),
        slideOutHorizontally(
            targetOffsetX = { it },
            animationSpec = tween(durationMillis = 500, easing = NavAnimationEasing),
        ),
    )
}

/** Default [transitionSpec] for predictive pop navigation to be used by [NavDisplay]. */
fun defaultPredictivePopTransitionSpec(): PredictivePopTransitionSpec =
    PredictivePopTransitionSpec(
        animationSpec = tween(durationMillis = 500, easing = NavAnimationEasing),
        contentTransform = {
            ContentTransform(
                slideInHorizontally(
                    initialOffsetX = { -it / 4 },
                    animationSpec = tween(durationMillis = 550, easing = LinearEasing),
                ),
                slideOutHorizontally(
                    targetOffsetX = { it },
                    animationSpec = tween(durationMillis = 550, easing = LinearEasing),
                ),
            )
        }
    )

/** Default easing for navigation animations. */
private val NavAnimationEasing = NavTransitionEasing(0.8f, 0.95f)

/** Stable identity for Scene instances within NavDisplay. */
private data class SceneKey(
    val sceneClass: Any,
    val key: Any?,
    val entryContentKeys: List<Any>,
)

/** Create a stable key for a Scene based on its class, public key, and entry content keys. */
private fun <T : Any> Scene<T>.createSceneKey(): SceneKey = SceneKey(
    sceneClass = this::class,
    key = this.key,
    entryContentKeys = buildList(entries.size) {
        entries.fastForEach { entry -> add(entry.contentKey) }
    },
)
