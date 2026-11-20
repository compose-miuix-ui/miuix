// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.transition.internal

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.GraphicsLayerScope
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.graphics.layer.drawLayer
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import top.yukonga.miuix.kmp.nav.transition.NavSourceDrawing
import top.yukonga.miuix.kmp.nav.transition.NavTransitionAnchor
import top.yukonga.miuix.kmp.nav.transition.NavTransitionScope

/** The entry's own layer renders both the moving container and the settled page. */
internal class NavExpandRenderer(
    private val transition: NavExpandTransition,
    initialScope: NavTransitionScope,
    private val anchor: NavTransitionAnchor?,
) : NavEntryRenderer {
    private var scope by mutableStateOf(initialScope)
    private var exitAcknowledged by mutableStateOf(false)
    private val sourceOutline = NavSourceOutline()
    private val alphaPaint = Paint()

    override val retainEntry: Boolean
        get() = scope.relativeDepth <= -1f && !exitAcknowledged

    fun update(scope: NavTransitionScope) {
        this.scope = scope
    }

    override fun GraphicsLayerScope.transformPage() {
        val d = scope.relativeDepth
        if (d < 0f) alpha = navExpandFraction(navExpandProgress(1f + d), 0f, 0.92f)
    }

    override fun drawContent(scope: ContentDrawScope) {
        val live = this.scope
        val d = live.relativeDepth
        // Covered pages never participate in their own expansion. At the page endpoint this
        // is already the real content, in the same layer and coordinate space as every frame.
        if (d >= 0f) {
            scope.drawContent()
            return
        }
        // The source node reads this same depth and draws in place in this very frame.
        if (d <= -1f || live.layoutSize.width <= 0 || live.layoutSize.height <= 0) return
        val frame = transition.frame(live)
        val progress = frame.progress
        if (progress <= 0f) return
        if (frame.scale <= 0f || frame.bounds.isEmpty) return
        with(scope) {
            // transformEntry owns the one scale/clip; transformContent centres the page inside
            // it. Undo only that centring for the container and source, not for page content.
            val revealSize = Size(frame.bounds.width / frame.scale, frame.bounds.height / frame.scale)
            val containerOrigin = Offset((size.width - revealSize.width) / 2f, 0f)
            val containerBounds = Rect(containerOrigin, revealSize)
            val source = anchor?.source
            val sourceAvailable = source == null || source.hasLivePlacement
            val alpha = if (frame.hasSource && sourceAvailable) 1f else navExpandFraction(progress, 0f, 0.25f)
            withAlpha(alpha, containerBounds) {
                val anchorColor = anchor?.containerColor ?: Color.Unspecified
                val color = navExpansionContainerColor(transition.containerColor(), anchorColor)
                val sourceColor = navExpansionContainerColor(anchorColor, color)
                val sourceCover = if (frame.hasSource) navExpandFraction(progress, 0f, SOURCE_COVER_END) else 1f
                // The recording includes the source background. Keep it equal to the growing
                // surface until covered, so its original rectangular edge cannot show through.
                val surfaceColor = if (!frame.hasSource || color == Color.Unspecified) color else androidx.compose.ui.graphics.lerp(sourceColor, color, navExpandFraction(progress, SOURCE_COVER_END, 0.8f))
                if (color != Color.Unspecified) {
                    drawRect(
                        surfaceColor,
                        topLeft = containerOrigin,
                        size = revealSize,
                    )
                }
                // Content handoff is independent of the short outline morph. Cover the source
                // progressively while the incoming page is already visible through its veil.
                if (sourceCover < 1f) {
                    if (surfaceColor != Color.Unspecified) {
                        drawSource(frame, containerOrigin)
                        drawRect(surfaceColor, topLeft = containerOrigin, size = revealSize, alpha = sourceCover)
                    } else {
                        withAlpha(1f - sourceCover, containerBounds) { drawSource(frame, containerOrigin) }
                    }
                }
                // Reveal the page through the container-colour veil while the source fades below.
                scope.drawContent()
            }
        }
    }

    private fun DrawScope.drawSource(frame: NavExpandFrame, containerOrigin: Offset) {
        val source = anchor?.source ?: return
        val layer = source.layer ?: return
        if (layer.size.width <= 0 || layer.size.height <= 0) return
        val original = frame.sourceBounds ?: return
        val visible = frame.origin
        if (visible.isEmpty) return
        val sourceSize = Size(layer.size.width.toFloat(), layer.size.height.toFloat())
        if (!sourceOutline.update(anchor.shapeFor(scope, source.squircleEnabled), sourceSize, scope.layoutDirection, scope.density)) return
        val factor = frame.bounds.width / visible.width / frame.scale
        withTransform({
            translate(containerOrigin.x, containerOrigin.y)
            scale(factor, factor, pivot = Offset.Zero)
            translate(original.left - visible.left, original.top - visible.top)
            scale(original.width / layer.size.width, original.height / layer.size.height, pivot = Offset.Zero)
        }) {
            // The container owns the moving silhouette. Retain the source's interior drawing,
            // without magnifying its external shadow inside the newly expanded container.
            clipPath(sourceOutline.path) { drawLayer(layer) }
        }
    }

    override suspend fun trackLifetime(): Unit = coroutineScope {
        if (anchor != null) {
            launch {
                val drawing = derivedStateOf(structuralEqualityPolicy()) {
                    val live = scope
                    val depth = live.relativeDepth
                    val progress = navExpandProgress(1f + depth)
                    when {
                        progress <= 0f -> NavSourceDrawing.InPlace
                        depth < 0f && live.layoutSize.width > 0 && live.layoutSize.height > 0 && !transition.frame(live).hasSource -> NavSourceDrawing.InPlace
                        progress >= SOURCE_COVER_END -> NavSourceDrawing.Covered
                        else -> NavSourceDrawing.Handoff
                    }
                }
                snapshotFlow { anchor.source }.collectLatest { source ->
                    if (source == null) return@collectLatest
                    source.claimDrawing(this@NavExpandRenderer, drawing)
                    try {
                        awaitCancellation()
                    } finally {
                        source.releaseDrawing(this@NavExpandRenderer)
                    }
                }
            }
        }
        snapshotFlow { scope.relativeDepth <= -1f }.collectLatest { atSource ->
            exitAcknowledged = false
            if (atSource) {
                // Keep resources for the endpoint draw before removing the host.
                withFrameNanos { }
                withFrameNanos { }
                exitAcknowledged = true
            }
        }
    }

    private inline fun DrawScope.withAlpha(alpha: Float, bounds: Rect, block: DrawScope.() -> Unit) {
        if (alpha <= 0f) return
        if (alpha >= 1f) {
            block()
        } else {
            val canvas = drawContext.canvas
            alphaPaint.alpha = alpha
            canvas.saveLayer(bounds, alphaPaint)
            try {
                block()
            } finally {
                canvas.restore()
            }
        }
    }

    private companion object {
        const val SOURCE_COVER_END = 0.24f
    }
}
