// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.transition

import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.RememberObserver
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.GraphicsContext
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.layer.GraphicsLayer
import androidx.compose.ui.graphics.layer.drawLayer
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalGraphicsContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.nav.core.LocalNavTransitionScope
import top.yukonga.miuix.kmp.nav.core.NavKey
import top.yukonga.miuix.kmp.nav.transition.internal.LocalNavExpansionState
import top.yukonga.miuix.kmp.nav.transition.internal.LocalNavSourceRegistry
import top.yukonga.miuix.kmp.nav.transition.internal.NavCornerShape
import top.yukonga.miuix.kmp.nav.transition.internal.NavExpansionBinding
import top.yukonga.miuix.kmp.nav.transition.internal.NavSourceOutline
import top.yukonga.miuix.kmp.squircle.isSquircleEnabled
import kotlin.random.Random

/** A retained Compose drawing, not a bitmap. Its layer survives source culling while in use. */
@Stable
internal class NavTransitionSource(
    private val graphicsContext: GraphicsContext,
    private val onReleased: (NavTransitionSource) -> Unit = {},
) {
    private var recording: GraphicsLayer? = null
    private var recordingEnabled = true
    private var released = false
    internal val isReleased: Boolean get() = released
    internal val layer: GraphicsLayer
        get() {
            recording?.let { return it }
            check(!released) { "Cannot record a released source" }
            return graphicsContext.createGraphicsLayer().also { recording = it }
        }
    internal var coordinates: LayoutCoordinates? by mutableStateOf(null)
    internal var shape: Shape? by mutableStateOf(null)
    internal var squircleEnabled by mutableStateOf(false)
    internal var density: Density by mutableStateOf(Density(1f))
    internal var layoutDirection by mutableStateOf(LayoutDirection.Ltr)
    private val outline by lazy(LazyThreadSafetyMode.NONE) { NavSourceOutline() }
    private var owners = 0
    private var references = 0
    private val drawingOwners = mutableStateMapOf<Any, () -> Boolean>()

    internal fun claimDrawing(owner: Any, claimed: () -> Boolean) {
        drawingOwners[owner] = claimed
    }

    internal fun releaseDrawing(owner: Any) {
        drawingOwners.remove(owner)
    }

    internal val isDrawingClaimed: Boolean get() = drawingOwners.values.any { it() }

    internal val hasLivePlacement: Boolean
        get() = coordinates?.isAttached == true && !released

    internal val currentBounds: Rect?
        get() = coordinates?.takeIf { it.isAttached && !released }
            ?.boundsInWindow(clipBounds = false)?.takeIf { it.isFinite && !it.isEmpty }

    internal fun sourceShape(corners: NavTransitionCorners): Shape = shape ?: NavCornerShape(corners, squircleEnabled)

    internal fun enableRecording(enabled: Boolean) {
        recordingEnabled = enabled
        if (!enabled && references == 0) releaseRecording()
    }

    private fun releaseRecording() {
        recording?.let { graphicsContext.releaseGraphicsLayer(it) }
        recording = null
    }

    internal fun contains(positionInWindow: Offset, corners: NavTransitionCorners): Boolean {
        val coordinates = coordinates?.takeIf { it.isAttached } ?: return false
        val position = coordinates.windowToLocal(positionInWindow)
        val size = Size(coordinates.size.width.toFloat(), coordinates.size.height.toFloat())
        if (!outline.update(sourceShape(corners), size, layoutDirection, density)) return false
        return outline.contains(position)
    }
    internal fun attach() {
        owners++
    }

    internal fun detach() {
        check(owners > 0)
        owners--
        if (owners == 0) coordinates = null
        releaseIfUnused()
    }

    internal fun retain() {
        check(!released) { "Cannot navigate from a released source" }
        references++
    }

    internal fun release() {
        check(references > 0)
        references--
        if (!recordingEnabled && references == 0) releaseRecording()
        releaseIfUnused()
    }

    internal fun releaseIfUnused() {
        if (owners == 0 && references == 0 && !released) {
            released = true
            releaseRecording()
            onReleased(this)
        }
    }

    /**
     * Validates the outline before retaining drawing resources. Unsupported shapes use the
     * host's ordinary navigation instead of failing during an animation's draw traversal.
     */
    fun capture(
        cornerRadius: Dp = 16.dp,
        containerColor: Color = Color.Unspecified,
        corners: NavTransitionCorners = NavTransitionCorners(cornerRadius),
    ): NavTransitionAnchor? {
        val sourceShape = sourceShape(corners)
        val size = Size(layer.size.width.toFloat(), layer.size.height.toFloat())
        if (!outline.update(sourceShape, size, layoutDirection, density)) return null
        return NavTransitionAnchor(
            bounds = currentBounds ?: Rect.Zero,
            cornerRadius = cornerRadius,
            containerColor = containerColor,
            corners = corners,
            shape = sourceShape,
        ).also {
            it.source = this
            it.retain()
        }
    }
}

/** Match the component's [shape]: a convex outline or a star-shaped polygon, without holes. */
@Composable
internal fun rememberNavTransitionSource(shape: Shape? = null, recordingEnabled: Boolean = true): NavTransitionSource {
    val graphicsContext = LocalGraphicsContext.current
    val registry = LocalNavSourceRegistry.current
    val identity = rememberSaveable { Random.nextLong().toString(16) }
    val squircleEnabled = isSquircleEnabled()
    val density = LocalDensity.current
    val layoutDirection = LocalLayoutDirection.current
    val source = remember(graphicsContext, registry, identity) {
        NavSourceOwner(registry?.acquire(identity, graphicsContext) ?: NavTransitionSource(graphicsContext))
    }.source
    SideEffect {
        source.shape = shape
        source.squircleEnabled = squircleEnabled
        source.density = density
        source.layoutDirection = layoutDirection
        source.enableRecording(recordingEnabled)
    }
    return source
}

private class NavSourceOwner(val source: NavTransitionSource) : RememberObserver {
    override fun onRemembered() = source.attach()
    override fun onForgotten() = source.detach()
    override fun onAbandoned() = source.releaseIfUnused()
}

/** Attach after outer padding and before the component's surface/clip; one source per component. */
internal fun Modifier.navTransitionSource(source: NavTransitionSource, recordingEnabled: Boolean = true): Modifier = this
    .onGloballyPositioned { source.coordinates = it }
    .drawWithContent {
        if (recordingEnabled) {
            // Keep the endpoint up to date while its destination owns drawing.
            source.layer.record { this@drawWithContent.drawContent() }
            if (!source.isDrawingClaimed) drawLayer(source.layer)
        } else {
            drawContent()
        }
    }

/**
 * Binds this component to [route] for [NavTransitions.Expansion]. Navigate using the application's
 * normal click callback; the nearest NavDisplay captures the source and manages its lifetime.
 * Supply the component's background and outline (convex or star-shaped polygon, without holes). Multiple components may bind the same route;
 * the pressed component wins. A unique binding can also serve programmatic navigation.
 */
@Suppress("ktlint:compose:modifier-composed-check")
fun Modifier.navTransitionSource(
    route: NavKey,
    cornerRadius: Dp = 16.dp,
    containerColor: Color = Color.Unspecified,
    corners: NavTransitionCorners = NavTransitionCorners(cornerRadius),
    shape: Shape? = null,
): Modifier = composed {
    val state = checkNotNull(LocalNavExpansionState.current) { "Route-bound navigation sources require a NavDisplay" }
    val origin = checkNotNull(LocalNavTransitionScope.current.entryKey) { "Navigation sources must belong to a destination entry" }
    val recordingEnabled = state.usesExpansion(route)
    val source = rememberNavTransitionSource(shape, recordingEnabled)
    val binding = remember(origin, route, source, cornerRadius, containerColor, corners) {
        NavExpansionBinding(origin, route, source, cornerRadius, containerColor, corners)
    }
    DisposableEffect(state, binding) {
        state.bind(binding)
        onDispose { state.unbind(binding) }
    }
    if (!recordingEnabled) return@composed this.navTransitionSource(source, recordingEnabled = false)
    this.navTransitionSource(source).pointerInput(state, binding) {
        awaitEachGesture {
            // Observe without consuming or replacing the component's click/press indication.
            awaitFirstDown(requireUnconsumed = false, pass = PointerEventPass.Initial)
            state.activate(binding)
            try {
                if (waitForUpOrCancellation(pass = PointerEventPass.Initial) != null) {
                    // Let the component's normal click callback run before capturing its route.
                    awaitPointerEvent(PointerEventPass.Final)
                }
            } finally {
                state.deactivate(binding)
            }
        }
    }
}
