// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

/*
 * Copyright 2025 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

@file:JvmName("NavDisplayKt")
@file:JvmMultifileClass

package androidx.navigation3.ui

import androidx.collection.mutableObjectFloatMapOf
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.SeekableTransitionState
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.rememberTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.runtime.snapshots.SnapshotStateList
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
import androidx.navigation3.runtime.NavMetadataKey
import androidx.navigation3.runtime.get
import androidx.navigation3.runtime.rememberDecoratedNavEntries
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.scene.LocalCurrentScene
import androidx.navigation3.scene.LocalEntriesToExcludeFromCurrentScene
import androidx.navigation3.scene.NavigationBackHandler
import androidx.navigation3.scene.OverlayScene
import androidx.navigation3.scene.Scene
import androidx.navigation3.scene.SceneDecoratorStrategy
import androidx.navigation3.scene.SceneInfo
import androidx.navigation3.scene.SceneState
import androidx.navigation3.scene.SceneStrategy
import androidx.navigation3.scene.SinglePaneSceneStrategy
import androidx.navigation3.scene.rememberNavigationEventState
import androidx.navigation3.scene.rememberSceneState
import androidx.navigation3.ui.NavDisplay.popTransitionSpec
import androidx.navigation3.ui.NavDisplay.predictivePopTransitionSpec
import androidx.navigation3.ui.NavDisplay.transitionSpec
import androidx.navigationevent.NavigationEvent
import androidx.navigationevent.NavigationEventTransitionState.Idle
import androidx.navigationevent.NavigationEventTransitionState.InProgress
import androidx.navigationevent.compose.NavigationEventState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import kotlin.jvm.JvmMultifileClass
import kotlin.jvm.JvmName
import kotlin.reflect.KClass

/** Object that indicates the features that can be handled by the [NavDisplay] */
object NavDisplay {
    /**
     * The key for [NavEntry.metadata] or [Scene.metadata] to notify the [NavDisplay] of how the
     * content should be animated when adding to the backstack.
     *
     * **IMPORTANT** [NavDisplay] only looks at the [Scene.metadata] to determine the final
     * [ContentTransform]. It is the responsibility of the [Scene.metadata] to decide which
     * [ContentTransform] to return, whether that be from the [NavEntry.metadata] or something
     * custom.
     */
    object TransitionKey :
        NavMetadataKey<AnimatedContentTransitionScope<Scene<*>>.() -> ContentTransform>

    /**
     * The key for [NavEntry.metadata] or [Scene.metadata] to notify the [NavDisplay] of how the
     * content should be animated when popping from backstack.
     *
     * **IMPORTANT** [NavDisplay] only looks at the [Scene.metadata] to determine the final
     * [ContentTransform]. It is the responsibility of the [Scene.metadata] to decide which
     * [ContentTransform] to return, whether that be from the [NavEntry.metadata] or something
     * custom.
     */
    object PopTransitionKey :
        NavMetadataKey<AnimatedContentTransitionScope<Scene<*>>.() -> ContentTransform>

    /**
     * The key for [NavEntry.metadata] or [Scene.metadata] to notify the [NavDisplay] of how the
     * content should be animated when popping from backstack using a Predictive back gesture.
     *
     * **IMPORTANT** [NavDisplay] only looks at the [Scene.metadata] to determine the final
     * [ContentTransform]. It is the responsibility of the [Scene.metadata] to decide which
     * [ContentTransform] to return, whether that be from the [NavEntry.metadata] or something
     * custom.
     */
    object PredictivePopTransitionKey :
        NavMetadataKey<
                AnimatedContentTransitionScope<Scene<*>>.(@NavigationEvent.SwipeEdge Int) -> ContentTransform?
                >

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
    ): Map<String, Any> = mapOf(TRANSITION_SPEC.toString() to transitionSpec)

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
    ): Map<String, Any> = mapOf(POP_TRANSITION_SPEC.toString() to popTransitionSpec)

    /**
     * Function to be called on the [NavEntry.metadata] or [Scene.metadata] to notify the
     * [NavDisplay] that, when popping from backstack using a Predictive back gesture, the content
     * should be animated using the provided [ContentTransform].
     *
     * **IMPORTANT** [NavDisplay] only looks at the [Scene.metadata] to determine the
     * [predictivePopTransitionSpec], it is the responsibility of the [Scene.metadata] to decide
     * which [predictivePopTransitionSpec] to return, whether that be from the [NavEntry.metadata]
     * or something custom.
     *
     * @param predictivePopTransitionSpec the [ContentTransform] to be used when popping from
     *   backStack with predictive back gesture. If this is null, the transition will fallback to
     *   the transition set on the [NavDisplay]
     */
    fun predictivePopTransitionSpec(
        predictivePopTransitionSpec:
        AnimatedContentTransitionScope<Scene<*>>.(@NavigationEvent.SwipeEdge Int) -> ContentTransform?,
    ): Map<String, Any> =
        mapOf(PREDICTIVE_POP_TRANSITION_SPEC.toString() to predictivePopTransitionSpec)

    internal val TRANSITION_SPEC = TransitionKey
    internal val POP_TRANSITION_SPEC = PopTransitionKey
    internal val PREDICTIVE_POP_TRANSITION_SPEC = PredictivePopTransitionKey
}

/**
 * Configuration for MIUI-specific transition effects applied during scene transitions.
 *
 * @param enableCornerClip whether to clip the top scene with rounded corners during transitions.
 * @param dimAmount the maximum dim alpha applied to the scene behind during transitions. Set to
 *   0f to disable dimming.
 * @param blockInputDuringTransition whether to block touch input on the non-target scene during
 *   transitions.
 * @param popDirectionFollowsSwipeEdge whether the pop animation direction follows the finger swipe
 *   edge. When false (default), the pop animation always exits in the reverse of the entry
 *   direction (entered from right, exits to right). When true, the pop animation direction matches
 *   the swipe edge (swipe from right edge, exits to left).
 */
@Immutable
data class NavDisplayTransitionEffects(
    val enableCornerClip: Boolean = true,
    val dimAmount: Float = 0.5f,
    val blockInputDuringTransition: Boolean = true,
    val popDirectionFollowsSwipeEdge: Boolean = false,
) {
    companion object {
        /** Default transition effects with corner clipping, dimming, and input blocking enabled. */
        val Default = NavDisplayTransitionEffects()

        /** No transition effects applied. */
        val None = NavDisplayTransitionEffects(
            enableCornerClip = false,
            dimAmount = 0f,
            blockInputDuringTransition = false,
        )
    }
}

/**
 * A nav display that renders and animates between different [Scene]s, each of which can render one
 * or more [NavEntry]s.
 *
 * The [Scene]s are calculated with the given list of [SceneStrategy] in the order of the list. If
 * no [Scene] is calculated, the fallback will be to a [SinglePaneSceneStrategy].
 *
 * It is allowable for different [Scene]s to render the same [NavEntry]s, perhaps on some conditions
 * as determined by the [sceneStrategies] based on window size, form factor, other arbitrary logic.
 *
 * If this happens, and these [Scene]s are rendered at the same time due to animation or predictive
 * back, then the content for the [NavEntry] will only be rendered in the most recent [Scene] that
 * is the target for being the current scene as determined by [sceneStrategies]. This enforces a
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
 * @param sceneStrategies the list of [SceneStrategy] to determine which scene to render a list of
 *   entries.
 * @param sceneDecoratorStrategies list of [SceneDecoratorStrategy] to add content to the scene.
 * @param sharedTransitionScope the [SharedTransitionScope] to allow transitions between scenes.
 * @param sizeTransform the [SizeTransform] for the [AnimatedContent].
 * @param transitionSpec Default [ContentTransform] when navigating to [NavEntry]s.
 * @param popTransitionSpec Default [ContentTransform] when popping [NavEntry]s.
 * @param predictivePopTransitionSpec Default [ContentTransform] when popping with predictive back
 *   [NavEntry]s.
 * @param transitionEffects configuration for MIUI-specific transition effects (corner clipping,
 *   dimming, input blocking).
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
    sceneStrategies: List<SceneStrategy<T>> = listOf(SinglePaneSceneStrategy()),
    sceneDecoratorStrategies: List<SceneDecoratorStrategy<T>> = emptyList(),
    sharedTransitionScope: SharedTransitionScope? = null,
    sizeTransform: SizeTransform? = null,
    transitionSpec: AnimatedContentTransitionScope<Scene<T>>.() -> ContentTransform =
        defaultTransitionSpec(),
    popTransitionSpec: AnimatedContentTransitionScope<Scene<T>>.() -> ContentTransform =
        defaultPopTransitionSpec(),
    predictivePopTransitionSpec:
    AnimatedContentTransitionScope<Scene<T>>.(@NavigationEvent.SwipeEdge Int) -> ContentTransform =
        defaultPredictivePopTransitionSpec(),
    transitionEffects: NavDisplayTransitionEffects = NavDisplayTransitionEffects.Default,
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
        sceneStrategies = sceneStrategies,
        sceneDecoratorStrategies = sceneDecoratorStrategies,
        sharedTransitionScope = sharedTransitionScope,
        modifier = modifier,
        contentAlignment = contentAlignment,
        sizeTransform = sizeTransform,
        transitionSpec = transitionSpec,
        popTransitionSpec = popTransitionSpec,
        predictivePopTransitionSpec = predictivePopTransitionSpec,
        transitionEffects = transitionEffects,
        onBack = onBack,
    )
}

/**
 * A nav display that renders and animates between different [Scene]s, each of which can render one
 * or more [NavEntry]s.
 *
 * The [Scene]s are calculated with the given list of [SceneStrategy] in the order of the list. If
 * no [Scene] is calculated, the fallback will be to a [SinglePaneSceneStrategy].
 *
 * It is allowable for different [Scene]s to render the same [NavEntry]s, perhaps on some conditions
 * as determined by the [sceneStrategies] based on window size, form factor, other arbitrary logic.
 *
 * If this happens, and these [Scene]s are rendered at the same time due to animation or predictive
 * back, then the content for the [NavEntry] will only be rendered in the most recent [Scene] that
 * is the target for being the current scene as determined by [sceneStrategies]. This enforces a
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
 * @param sceneStrategies the list of [SceneStrategy] to determine which scene to render a list of
 *   entries.
 * @param sceneDecoratorStrategies list of [SceneDecoratorStrategy] to add content to the scene.
 * @param sharedTransitionScope the [SharedTransitionScope] to allow transitions between scenes.
 * @param sizeTransform the [SizeTransform] for the [AnimatedContent].
 * @param transitionSpec Default [ContentTransform] when navigating to [NavEntry]s.
 * @param popTransitionSpec Default [ContentTransform] when popping [NavEntry]s.
 * @param predictivePopTransitionSpec Default [ContentTransform] when popping [NavEntry]s with
 *   predictive back.
 * @param transitionEffects configuration for MIUI-specific transition effects (corner clipping,
 *   dimming, input blocking).
 * @param onBack a callback for handling system back press.
 * @see [rememberDecoratedNavEntries]
 */
@Composable
fun <T : Any> NavDisplay(
    entries: List<NavEntry<T>>,
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.TopStart,
    sceneStrategies: List<SceneStrategy<T>> = listOf(SinglePaneSceneStrategy()),
    sceneDecoratorStrategies: List<SceneDecoratorStrategy<T>> = emptyList(),
    sharedTransitionScope: SharedTransitionScope? = null,
    sizeTransform: SizeTransform? = null,
    transitionSpec: AnimatedContentTransitionScope<Scene<T>>.() -> ContentTransform =
        defaultTransitionSpec(),
    popTransitionSpec: AnimatedContentTransitionScope<Scene<T>>.() -> ContentTransform =
        defaultPopTransitionSpec(),
    predictivePopTransitionSpec:
    AnimatedContentTransitionScope<Scene<T>>.(@NavigationEvent.SwipeEdge Int) -> ContentTransform =
        defaultPredictivePopTransitionSpec(),
    transitionEffects: NavDisplayTransitionEffects = NavDisplayTransitionEffects.Default,
    onBack: () -> Unit,
) {
    require(entries.isNotEmpty()) { "NavDisplay entries cannot be empty" }

    val sceneState =
        rememberSceneState(
            entries,
            sceneStrategies,
            sceneDecoratorStrategies,
            sharedTransitionScope,
            onBack,
        )

    // Predictive Back Handling
    val navigationEventState = rememberNavigationEventState(sceneState)
    NavigationBackHandler(sceneState, navigationEventState, onBack)

    NavDisplay(
        sceneState,
        navigationEventState,
        modifier,
        contentAlignment,
        sizeTransform,
        transitionSpec,
        popTransitionSpec,
        predictivePopTransitionSpec,
        transitionEffects,
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
 * @param predictivePopTransitionSpec Default [ContentTransform] when popping [NavEntry]s with
 *   predictive back.
 * @param transitionEffects configuration for MIUI-specific transition effects (corner clipping,
 *   dimming, input blocking).
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
    predictivePopTransitionSpec:
    AnimatedContentTransitionScope<Scene<T>>.(@NavigationEvent.SwipeEdge Int) -> ContentTransform =
        defaultPredictivePopTransitionSpec(),
    transitionEffects: NavDisplayTransitionEffects = NavDisplayTransitionEffects.Default,
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
    // Track content keys (value-based) to reliably detect pop vs push, even during
    // rapid consecutive navigations where entry references may be recreated.
    // Uses a ref-holder to avoid writing mutableStateOf during composition.
    val currentContentKeys = remember(sceneState.entries) {
        sceneState.entries.map { it.contentKey }
    }
    val isPopRef = remember {
        object {
            var previousKeys = currentContentKeys
            var isPop = false
        }
    }
    if (isPopRef.previousKeys != currentContentKeys) {
        isPopRef.isPop = isPop(isPopRef.previousKeys, currentContentKeys)
        isPopRef.previousKeys = currentContentKeys
    }
    val isPop = isPopRef.isPop

    // Set up Gesture Back tracking
    val previousScene = sceneState.previousScenes.lastOrNull()

    val hasPreviousScene = previousScene != null
    val inPredictiveBack by remember(hasPreviousScene) {
        derivedStateOf {
            navigationEventState.transitionState is InProgress && hasPreviousScene
        }
    }
    val swipeEdge by remember {
        derivedStateOf {
            when (val gesture = navigationEventState.transitionState) {
                is Idle -> NavigationEvent.EDGE_NONE
                is InProgress -> gesture.latestEvent.swipeEdge
            }
        }
    }

    // Remember the swipe edge for use after finger lift (when isPop but not inPredictiveBack)
    var lastSwipeEdge by remember { mutableStateOf(NavigationEvent.EDGE_NONE) }
    if (inPredictiveBack) {
        lastSwipeEdge = swipeEdge
    }

    // Track currently rendered Scenes and their ZIndices
    val sceneMap = remember { mutableStateMapOf<AnimatedSceneKey, Scene<T>>() }
    val zIndices = remember { mutableObjectFloatMapOf<AnimatedSceneKey>() }
    val initialKey = AnimatedSceneKey(transition.currentState)
    val targetKey = AnimatedSceneKey(transition.targetState)
    val initialZIndex = zIndices.getOrPut(initialKey) { 0f }
    // Preserve existing z-index for scenes already tracked in the current transition.
    // This prevents z-index from flipping during settle animations (e.g. after committing
    // a predictive back that interrupted a forward push).
    val isInterruptingEntry = inPredictiveBack && transition.currentState == previousScene
    val targetZIndex =
        when {
            initialKey == targetKey -> initialZIndex
            zIndices.containsKey(targetKey) -> zIndices[targetKey]
            (isPop || inPredictiveBack) && !isInterruptingEntry -> initialZIndex - 1f
            else -> initialZIndex + 1f
        }
    sceneMap[targetKey] = transition.targetState
    zIndices[targetKey] = targetZIndex

    // overlay scenes that are still on the backStack
    val overlayScenes = sceneState.overlayScenes
    // includes overlay scenes that are already popped off backStack but still animating out
    val currentOverlayScenes = remember { SnapshotStateList<OverlayScene<T>>() }
    LaunchedEffect(overlayScenes) {
        // we want a unique set of overlay scenes, but it needs to be ordered to preserve z-order
        overlayScenes.fastForEach {
            if (!currentOverlayScenes.contains(it)) currentOverlayScenes.add(it)
        }
    }

    // Determine which entries should be rendered within each currently rendered scene,
    // using the z-index of each screen to always show the entry on the topmost screen
    // The map is AnimatedSceneKey to a Set of NavEntry.key values
    val sceneToExcludedEntryMap by remember {
        derivedStateOf {
            buildMap {
                val scenes = mutableListOf<Scene<T>>()
                // First sort the non-overlay scenes by z-order in descending order.
                sceneMap.entries
                    .sortedByDescending { zIndices[it.key] }
                    .map { it.value }
                    .forEach { if (!scenes.contains(it)) scenes.add(it) }

                // At this point we have a list in this order
                // [zIndex larger --> zIndex smaller]

                // Then combine them with overlay scenes to get the complete order of scenes in
                // z-order
                // overlayScenes is already in order of [top most overlay ---> lowest overlay],
                // so we put overlayScenes in front, and then add the scenes after.
                val scenesInZOrder = currentOverlayScenes + scenes
                // At this point we have a list of all scenes in this order
                // [top most overlay ---> lowest overlay, other scenes zIndex larger --> zIndex
                // smaller]

                // Then we track which entries are already covered
                val coveredEntryKeys = mutableSetOf<Any>()

                // This determines whether this is a pop or not
                val shouldSwapExcludedScenesFromTarget = transition.targetState != scenes.first()

                // In scenesInZOrder's natural order, go through each scene, marking
                // all of the entries not already covered as associated
                // with that scene. This ensures that each unique contentKey will only be
                // rendered by one scene.
                scenesInZOrder.fastForEach { scene ->
                    val newlyCoveredEntryKeys =
                        scene.entries
                            .map { it.contentKey }
                            .filterNot(coveredEntryKeys::contains)
                            .toSet()
                    // If our target scene is not the scene on top
                    // we should exclude the entries in the target scene from all other scenes
                    // this ensures we render the entry in the target scene when popping using
                    // shared elements
                    if (shouldSwapExcludedScenesFromTarget && transition.targetState != scene) {
                        put(
                            AnimatedSceneKey(scene),
                            transition.targetState.entries.map { it.contentKey }.toSet(),
                        )
                    } else {
                        put(AnimatedSceneKey(scene), coveredEntryKeys.toMutableSet())
                    }
                    coveredEntryKeys.addAll(newlyCoveredEntryKeys)
                }

                // After we are done building the entire map, check if we should clear
                // the target scene key
                if (shouldSwapExcludedScenesFromTarget) {
                    put(AnimatedSceneKey(transition.targetState), emptySet())
                }
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

    if (inPredictiveBack && previousScene != null) {
        LaunchedEffect(previousScene) {
            snapshotFlow { navigationEventState.transitionState }
                .collectLatest { gestureState ->
                    if (gestureState is InProgress) {
                        val progress = gestureState.latestEvent.progress
                        if (transition.currentState == previousScene) {
                            // Interrupting entry: A -> B
                            // Scale progress to match the partial entry state
                            val startPhysical =
                                NavAnimationEasing.transform(entryInterruptionFraction)
                            val targetPhysical = startPhysical * (1f - progress)
                            val targetFraction =
                                NavAnimationEasing.inverseTransform(targetPhysical)
                            transitionState.seekTo(targetFraction, scene)
                        } else {
                            // Standard back: B -> A
                            transitionState.seekTo(progress, previousScene)
                        }
                    }
                }
        }
    } else {
        LaunchedEffect(scene) {
            if (transitionState.currentState != scene) {
                if (transitionState.targetState == scene) {
                    // Predictive Back has been committed
                    // so now we need to animate to the final state
                    transitionState.animateTo(
                        scene,
                        animationSpec = tween(500, easing = NavAnimationEasing),
                    )
                } else {
                    // We are animating to the final state for regular navigate forward
                    // and regular pop
                    transitionState.animateTo(scene)
                }
            } else {
                // Predictive Back has either been completed or cancelled
                // so now we need to seekTo+snapTo the final state

                // convert from nanoseconds to milliseconds
                val totalDuration = transition.totalDurationNanos / 1000000
                // Which way we have to seek depends on whether the
                // Predictive Back was completed or cancelled
                val predictiveBackCompleted = transition.targetState == scene
                val (finalFraction, remainingDuration) =
                    if (predictiveBackCompleted) {
                        // If it completed, animate to the state we were
                        // already seeking to with the remaining duration
                        1f to ((1f - transitionState.fraction) * totalDuration).toInt()
                    } else {
                        // It it got cancelled, animate back to the
                        // initial state, reversing what we seeked to
                        0f to (transitionState.fraction * totalDuration).toInt()
                    }
                animate(
                    transitionState.fraction,
                    finalFraction,
                    animationSpec = tween(remainingDuration, easing = NavAnimationEasing),
                ) { value, _ ->
                    this@LaunchedEffect.launch {
                        if (value != finalFraction) {
                            // Seek the transition towards the finalFraction
                            transitionState.seekTo(value)
                        }
                        if (value == finalFraction) {
                            // Once the animation finishes, we need to snap to the right state.
                            transitionState.snapTo(scene)
                        }
                    }
                }
            }
        }
    }

    val shouldFlipDirection = transitionEffects.popDirectionFollowsSwipeEdge &&
            (if (inPredictiveBack) swipeEdge else lastSwipeEdge) == NavigationEvent.EDGE_RIGHT

    val contentTransform: AnimatedContentTransitionScope<Scene<T>>.() -> ContentTransform = {
        when {
            inPredictiveBack -> {
                if (shouldFlipDirection) {
                    flippedPredictivePopTransitionSpec()
                } else {
                    transitionScene.predictivePopSpec<T>()?.invoke(this, swipeEdge)
                        ?: predictivePopTransitionSpec(swipeEdge)
                }
            }

            isPop -> {
                if (shouldFlipDirection) {
                    flippedPopTransitionSpec()
                } else {
                    transitionScene.contentTransform(NavDisplay.PopTransitionKey)?.invoke(this)
                        ?: popTransitionSpec(this)
                }
            }

            else -> {
                transitionScene.contentTransform(NavDisplay.TransitionKey)?.invoke(this)
                    ?: transitionSpec(this)
            }
        }
    }

    // allows OverlayScenes to access animatedContentScope even if it's no-op so that
    // LocalNavAnimatedContentScope doesn't need to be nullable
    lateinit var animatedContentScope: AnimatedContentScope

    transition.AnimatedContent(
        contentKey = { scene -> AnimatedSceneKey(scene) },
        contentAlignment = contentAlignment,
        modifier = modifier,
        transitionSpec = {
            ContentTransform(
                targetContentEnter = contentTransform(this).targetContentEnter,
                initialContentExit = contentTransform(this).initialContentExit,
                // z-index increases during navigate and decreases during pop.
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
                maxLifecycle =
                    if (isSettled && currentOverlayScenes.isEmpty()) Lifecycle.State.RESUMED
                    else Lifecycle.State.STARTED
            )
        animatedContentScope = remember { this }
        CompositionLocalProvider(
            LocalLifecycleOwner provides sceneLifecycleOwner,
            LocalNavAnimatedContentScope provides animatedContentScope,
            LocalCurrentScene provides targetScene,
            LocalEntriesToExcludeFromCurrentScene provides
                    sceneToExcludedEntryMap.getValue(AnimatedSceneKey(targetScene)),
        ) {
            val myZIndex =
                zIndices.getOrElse(AnimatedSceneKey(targetScene)) { targetZIndex }
            val topZIndex = maxOf(initialZIndex, targetZIndex)

            val isTopScene =
                !isSettled &&
                        myZIndex > minOf(initialZIndex, targetZIndex) &&
                        initialZIndex != targetZIndex

            val roundedModifier = if (transitionEffects.enableCornerClip && isTopScene) {
                val corner = if (!isInMultiWindowMode()) getRoundedCorner() else 0.dp
                val shape = remember(corner, shouldFlipDirection) {
                    if (shouldFlipDirection) {
                        AbsoluteRoundedCornerShape(topRight = corner, bottomRight = corner)
                    } else {
                        AbsoluteRoundedCornerShape(topLeft = corner, bottomLeft = corner)
                    }
                }
                Modifier.clip(shape)
            } else {
                Modifier
            }

            val blockInputModifier =
                if (transitionEffects.blockInputDuringTransition && !isSettled && targetScene != transition.targetState) {
                    remember {
                        Modifier.pointerInput(Unit) {
                            awaitPointerEventScope {
                                while (true) {
                                    val event =
                                        awaitPointerEvent(pass = PointerEventPass.Initial)
                                    event.changes.fastForEach { it.consume() }
                                }
                            }
                        }
                    }
                } else {
                    Modifier
                }

            Box(
                modifier =
                    Modifier
                        .then(roundedModifier)
                        .then(blockInputModifier),
            ) {
                targetScene.content()

                if (transitionEffects.dimAmount > 0f && myZIndex < topZIndex) {
                    val isCurrentScene = targetScene == transition.currentState
                    Box(
                        modifier =
                            Modifier
                                .fillMaxSize()
                                .graphicsLayer {
                                    val settled =
                                        transition.currentState == transition.targetState
                                    alpha = if (!settled) {
                                        if (isCurrentScene) {
                                            transitionState.fraction *
                                                    transitionEffects.dimAmount
                                        } else {
                                            (1f - transitionState.fraction) *
                                                    transitionEffects.dimAmount
                                        }
                                    } else {
                                        0f
                                    }
                                }
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
                val targetKey = AnimatedSceneKey(transition.targetState)
                // Creating a copy to avoid ConcurrentModificationException
                @Suppress("ListIterator")
                sceneMap.keys.toList().forEach { key ->
                    if (key != targetKey) {
                        sceneMap.remove(key)
                    }
                }
                // Creating a copy to avoid ConcurrentModificationException
                zIndices.removeIf { key, _ -> key != targetKey }
                lastSwipeEdge = NavigationEvent.EDGE_NONE
            }
    }

    // Show all OverlayScene instances above the AnimatedContent
    currentOverlayScenes.fastForEachReversed { overlayScene ->
        val scope = rememberCoroutineScope()
        key(overlayScene) {
            val overlaySceneLifecycleOwner =
                rememberLifecycleOwner(
                    maxLifecycle =
                        if (overlayScenes.firstOrNull() == overlayScene) Lifecycle.State.RESUMED
                        else Lifecycle.State.STARTED
                )

            CompositionLocalProvider(
                LocalLifecycleOwner provides overlaySceneLifecycleOwner,
                LocalEntriesToExcludeFromCurrentScene provides
                        sceneToExcludedEntryMap.getValue(AnimatedSceneKey(overlayScene)),
                LocalCurrentScene provides overlayScene,
                LocalNavAnimatedContentScope provides animatedContentScope,
            ) {
                overlayScene.content()
            }
        }
        // if the overlay scene is popped, let onRemoved finish before
        // removing from composition to ensure animations can complete
        if (overlayScene !in overlayScenes) {
            scope.launch {
                overlayScene.onRemove()
                currentOverlayScenes.remove(overlayScene)
            }
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
    key: NavMetadataKey<AnimatedContentTransitionScope<Scene<*>>.() -> ContentTransform>,
): (AnimatedContentTransitionScope<Scene<T>>.() -> ContentTransform)? {
    return metadata[key] as? AnimatedContentTransitionScope<Scene<T>>.() -> ContentTransform
}

@Suppress("UNCHECKED_CAST")
private fun <T : Any> Scene<T>.predictivePopSpec():
        (AnimatedContentTransitionScope<Scene<T>>.(@NavigationEvent.SwipeEdge Int) -> ContentTransform?)? {
    return metadata[NavDisplay.PredictivePopTransitionKey]
            as?
            AnimatedContentTransitionScope<Scene<T>>.(@NavigationEvent.SwipeEdge Int) -> ContentTransform?
}

/** Default [transitionSpec] for forward navigation to be used by [NavDisplay]. */
fun <T : Any> defaultTransitionSpec():
        AnimatedContentTransitionScope<Scene<T>>.() -> ContentTransform = {
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
fun <T : Any> defaultPopTransitionSpec():
        AnimatedContentTransitionScope<Scene<T>>.() -> ContentTransform = {
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
fun <T : Any> defaultPredictivePopTransitionSpec():
        AnimatedContentTransitionScope<Scene<T>>.(@NavigationEvent.SwipeEdge Int) -> ContentTransform = {
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

/** Flipped [popTransitionSpec] for right-edge swipe (exit to left). */
private fun flippedPopTransitionSpec(): ContentTransform =
    ContentTransform(
        slideInHorizontally(
            initialOffsetX = { it / 4 },
            animationSpec = tween(durationMillis = 500, easing = NavAnimationEasing),
        ),
        slideOutHorizontally(
            targetOffsetX = { -it },
            animationSpec = tween(durationMillis = 500, easing = NavAnimationEasing),
        ),
    )

/** Flipped [predictivePopTransitionSpec] for right-edge swipe (exit to left). */
private fun flippedPredictivePopTransitionSpec(): ContentTransform =
    ContentTransform(
        slideInHorizontally(
            initialOffsetX = { it / 4 },
            animationSpec = tween(durationMillis = 550, easing = LinearEasing),
        ),
        slideOutHorizontally(
            targetOffsetX = { -it },
            animationSpec = tween(durationMillis = 550, easing = LinearEasing),
        ),
    )

/** Default easing for navigation animations. */
private val NavAnimationEasing = NavTransitionEasing(0.8f, 0.95f)

internal data class AnimatedSceneKey(val clazz: KClass<*>, val key: Any) {
    constructor(scene: Scene<*>) : this(scene::class, scene.key)
}
