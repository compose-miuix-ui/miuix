// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.transition.internal

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import top.yukonga.miuix.kmp.nav.core.LiveNavTransitionScope
import top.yukonga.miuix.kmp.nav.runtime.NavDriverSpec
import top.yukonga.miuix.kmp.nav.transition.NavMotion
import top.yukonga.miuix.kmp.nav.transition.NavTransition
import top.yukonga.miuix.kmp.nav.transition.NavTransitionAnchor
import top.yukonga.miuix.kmp.nav.transition.NavTransitionScope
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow

internal class NavExpandTransition(
    private val anchor: (NavTransitionScope) -> NavTransitionAnchor?,
    private val initialScale: Float,
    private val cornerRadius: Dp,
    private val targetCornerRadius: () -> Dp,
    internal val containerColor: () -> Color,
    override val motion: NavMotion,
) : NavTransition,
    NavHostTransition {
    override fun handlesCornerClip(scope: NavTransitionScope): Boolean = true

    internal fun frame(scope: NavTransitionScope): NavExpandFrame = if (scope is LiveNavTransitionScope) {
        scope.expansionFrame(this) { derivedStateOf { calculateFrame(scope) } }
    } else {
        calculateFrame(scope)
    }

    private fun calculateFrame(scope: NavTransitionScope): NavExpandFrame {
        val source = anchor(scope)
        val live = scope as? LiveNavTransitionScope
        val returning = source?.source != null && live?.isReturningExpansion == true
        val bounds = source?.let { live?.expansionReturnBounds(it, returning) }
        val sourceBounds = if (returning) bounds?.full else source?.boundsInHost(scope)
        return navExpandFrame(
            anchor = source,
            layoutSize = scope.layoutSize,
            layoutPosition = scope.layoutPositionInWindow,
            progress = (1f + scope.relativeDepth).coerceIn(0f, 1f),
            initialScale = initialScale,
            cornerRadius = with(scope.density) { cornerRadius.toPx() },
            targetCornerRadius = with(scope.density) { targetCornerRadius().toPx() },
            density = scope.density,
            windowToLocal = { it },
            sourceBounds = sourceBounds,
            visibleSourceBounds = if (returning) bounds?.visible else sourceBounds,
            sourceVisible = bounds?.isLiveVisible != false,
        )
    }

    @Composable
    override fun rememberRenderer(scope: NavTransitionScope): NavEntryRenderer {
        val entryAnchor = anchor(scope)
        val renderer = remember(this, entryAnchor) {
            NavExpandRenderer(this, scope, entryAnchor)
        }
        SideEffect { renderer.update(scope) }
        return renderer
    }

    override fun Modifier.transformEntry(scope: NavTransitionScope): Modifier {
        val live = scope as? LiveNavTransitionScope
        val squircleEnabled = live?.squircleEnabled == true
        val outlineMorph = lazy(LazyThreadSafetyMode.NONE) { live?.expansionOutline ?: NavOutlineMorph() }
        return graphicsLayer {
            val d = scope.relativeDepth
            if (d > 0f || scope.layoutSize.width <= 0 || scope.layoutSize.height <= 0) return@graphicsLayer
            val hasUpperEntry = live?.coveredByEntry == true
            val frame = frame(scope)
            // Gesture ownership alone does not enable clipping at the square page endpoint.
            if (frame.progress == 1f && (scope.gesture != null || !scope.isRunning || hasUpperEntry)) {
                clip = false
                return@graphicsLayer
            }
            // Keep the endpoint hit region at the source while the host awaits handoff. An
            // invisible full-screen entry would otherwise block other sources for those frames.
            if (frame.progress <= 0f) alpha = 0f
            transformOrigin = TransformOrigin(0f, 0f)
            translationX = frame.bounds.left
            translationY = frame.bounds.top
            scaleX = frame.scale
            scaleY = frame.scale
            val source = anchor(scope)
            val sourceBounds = frame.sourceBounds?.takeIf { frame.hasSource }
            shape = NavExpandShape(
                morph = outlineMorph.value,
                frame = frame,
                sourceShape = source?.shapeFor(scope, squircleEnabled),
                sourceSize = source?.source?.size ?: sourceBounds?.size ?: frame.origin.size,
                sourceBounds = sourceBounds,
                squircleEnabled = squircleEnabled,
            )
            clip = true
        }
    }

    override fun Modifier.transformContent(scope: NavTransitionScope): Modifier = graphicsLayer {
        if (scope.relativeDepth > 0f || scope.layoutSize.width <= 0 || scope.layoutSize.height <= 0) return@graphicsLayer
        val frame = frame(scope)
        // Keep the same anchor as the renderer until this host leaves composition.
        translationX = (frame.bounds.width / frame.scale - size.width) / 2f
    }
}

internal data class NavExpandSourceBounds(val full: Rect, val visible: Rect, val isLiveVisible: Boolean = true)

/** Visible silhouette in host-local pixels; contents use one uniform [scale]. */
internal data class NavExpandFrame(
    val progress: Float,
    val bounds: Rect,
    val scale: Float,
    val cornerRadius: Float,
    val hasSource: Boolean,
    val origin: Rect,
    val sourceFraction: Float,
    val movingRadius: Float,
    val sourceBounds: Rect? = null,
    val sourceVisible: Boolean = true,
)

internal fun navExpandFrame(
    anchor: NavTransitionAnchor?,
    layoutSize: IntSize,
    layoutPosition: Offset,
    progress: Float,
    initialScale: Float,
    cornerRadius: Float,
    targetCornerRadius: Float,
    density: Density,
    windowToLocal: (Offset) -> Offset = { it - layoutPosition },
    sourceBounds: Rect? = anchor?.bounds,
    visibleSourceBounds: Rect? = sourceBounds,
    sourceVisible: Boolean = true,
): NavExpandFrame {
    val width = layoutSize.width.toFloat()
    val height = layoutSize.height.toFloat()
    val viewport = Rect(0f, 0f, width, height)
    val source = visibleSourceBounds?.takeIf { it.isFinite && !it.isEmpty }
        ?.let { Rect(windowToLocal(it.topLeft), windowToLocal(it.bottomRight)) }
        ?.takeIf { it.isFinite && !it.isEmpty }
        ?.intersect(viewport)
        ?.takeUnless { it.isEmpty }
    val start = source ?: Rect(
        left = width * (1f - initialScale) / 2f,
        top = height * (1f - initialScale) / 2f,
        right = width * (1f + initialScale) / 2f,
        bottom = height * (1f + initialScale) / 2f,
    )
    val p = navExpandProgress(progress)
    val remaining = 1f - p
    // Lead with width to make room for the moving corners. The lead vanishes cubically near
    // the page, preserving the shared final edge approach and both exact handoff endpoints.
    val horizontalRemaining = remaining * (1f - p * remaining * remaining)
    val horizontalProgress = 1f - horizontalRemaining
    val horizontalAsymmetry = horizontalRemaining.pow(1f + 2f * navExpandFraction(horizontalProgress, 0f, 1f))
    val asymmetry = remaining.pow(1f + 2f * navExpandFraction(p, 0f, 1f))
    val horizontalInset = min(start.left, width - start.right)
    val verticalInset = min(start.top, height - start.bottom)
    // Opposite edges share their final approach. Only the source's positional imbalance
    // converges progressively faster, so an offset source centres before filling the host.
    // Every inset decreases monotonically: moving the center independently of size could
    // pull the nearer edge back in and uncover the old card outside the incoming container.
    val bounds = Rect(
        left = horizontalInset * horizontalRemaining + (start.left - horizontalInset) * horizontalAsymmetry,
        top = verticalInset * remaining + (start.top - verticalInset) * asymmetry,
        right = width - horizontalInset * horizontalRemaining - (width - start.right - horizontalInset) * horizontalAsymmetry,
        bottom = height - verticalInset * remaining - (height - start.bottom - verticalInset) * asymmetry,
    )
    val startRadius = if (source != null && anchor != null) with(density) { anchor.cornerRadius.toPx() } else cornerRadius
    val sourceRadius = if (source != null && anchor != null) {
        with(density) {
            anchor.corners.let { max(max(it.topLeft.toPx(), it.topRight.toPx()), max(it.bottomRight.toPx(), it.bottomLeft.toPx())) }
        }
    } else {
        startRadius
    }
    val movingRadius = max(max(sourceRadius, cornerRadius), targetCornerRadius)
    val sourceCornerFraction = navExpandFraction(p, 0f, 0.5f)
    return NavExpandFrame(
        progress = p,
        bounds = bounds,
        // Cover the revealed rect while preserving the page's aspect ratio. Only its clip grows;
        // non-uniform X/Y scaling would stretch text when card and page aspect ratios differ.
        scale = max(bounds.width / width, bounds.height / height),
        cornerRadius = lerp(
            lerp(startRadius, movingRadius, sourceCornerFraction),
            targetCornerRadius,
            navExpandFraction(p, 0.5f, 1f),
        ),
        hasSource = source != null,
        origin = start,
        sourceFraction = sourceCornerFraction,
        movingRadius = movingRadius,
        sourceBounds = sourceBounds.takeIf { source != null },
        sourceVisible = sourceVisible,
    )
}

/**
 * Match the real source/page before the driver snaps its final residual distance. The default
 * spring settles in depth units, while an off-centre source can amplify that residual into several
 * pixels. Both endpoint holds are flat; a quintic joins them to the unchanged middle with matching
 * first and second derivatives. Geometry, content and source visibility must use this same value.
 */
internal fun navExpandProgress(progress: Float): Float {
    val p = progress.coerceIn(0f, 1f)
    val distance = min(p, 1f - p)
    val rest = NavDriverSpec.VISIBILITY_THRESHOLD * 2f
    val approach = 0.08f
    if (distance >= approach) return p
    if (distance <= rest) return if (p < 0.5f) 0f else 1f
    val t = (distance - rest) / (approach - rest)
    val slope = (approach - rest) / approach
    val eased = approach * t * t * t * (
        10f - 4f * slope + t * (-15f + 7f * slope + t * (6f - 3f * slope))
        )
    return if (p < 0.5f) eased else 1f - eased
}

/** Separate continuous reveal tracks, reversible even if a gesture interrupts a settle. */
internal fun navExpandFraction(progress: Float, start: Float, end: Float): Float {
    val t = ((progress - start) / (end - start)).coerceIn(0f, 1f)
    return t * t * (3f - 2f * t)
}
