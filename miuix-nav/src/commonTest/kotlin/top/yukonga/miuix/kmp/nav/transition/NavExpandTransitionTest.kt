// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.transition

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.nav.transition.internal.NavExpandFrame
import top.yukonga.miuix.kmp.nav.transition.internal.navExpandFrame
import kotlin.math.abs
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class NavExpandTransitionTest {
    private val host = IntSize(400, 800)

    private fun frame(
        anchor: NavTransitionAnchor?,
        progress: Float,
        position: Offset = Offset.Zero,
        targetCornerRadius: Float = 0f,
    ): NavExpandFrame = navExpandFrame(
        anchor = anchor,
        layoutSize = host,
        layoutPosition = position,
        progress = progress,
        initialScale = 0.85f,
        cornerRadius = 28f,
        targetCornerRadius = targetCornerRadius,
        density = Density(2f),
    )

    @Test
    fun windowCoordinatesBecomeHostLocalAndLandExactlyAtFullSize() {
        val source = NavTransitionAnchor(Rect(120f, 380f, 440f, 500f), 16.dp)
        val start = frame(source, 0f, Offset(100f, 200f))
        assertEquals(Rect(20f, 180f, 340f, 300f), start.bounds)
        assertEquals(32f, start.cornerRadius)
        val end = frame(source, 1f, Offset(100f, 200f))
        assertEquals(Rect(0f, 0f, 400f, 800f), end.bounds)
        assertEquals(1f, end.scale)
        assertEquals(0f, end.cornerRadius)
    }

    @Test
    fun wideAndTallSourcesRevealWithoutStretchingOrUnpaintedAreas() {
        for (bounds in listOf(Rect(20f, 300f, 380f, 400f), Rect(150f, 20f, 250f, 780f))) {
            val source = NavTransitionAnchor(bounds)
            for (step in 0..20) {
                val result = frame(source, step / 20f)
                assertTrue(result.scale.isFinite() && result.scale > 0f)
                assertTrue(result.bounds.width / result.scale <= host.width + 0.001f)
                assertTrue(result.bounds.height / result.scale <= host.height + 0.001f)
            }
        }
    }

    @Test
    fun missingEmptyInvalidAndOffHostSourcesUseTheSameCenteredFallback() {
        val fallback = frame(null, 0.4f)
        for (bounds in listOf(Rect.Zero, Rect(Float.NaN, 0f, 10f, 10f), Rect(500f, 0f, 600f, 100f))) {
            assertEquals(fallback, frame(NavTransitionAnchor(bounds), 0.4f))
        }
        val start = frame(null, 0f)
        assertEquals(0.85f, start.scale, 0.0001f)
        assertEquals(200f, start.bounds.center.x)
        assertEquals(400f, start.bounds.center.y)
    }

    @Test
    fun partiallyVisibleSourceIsConstrainedToItsNavigationPane() {
        val source = NavTransitionAnchor(Rect(-20f, 700f, 420f, 900f))
        assertEquals(Rect(0f, 700f, 400f, 800f), frame(source, 0f).bounds)
    }

    @Test
    fun aClippedSourceKeepsItsFullDrawingBoundsAndUsesOnlyTheVisibleLandingArea() {
        val full = Rect(20f, 300f, 380f, 420f)
        val visible = Rect(20f, 360f, 380f, 420f)
        val frame = navExpandFrame(
            anchor = NavTransitionAnchor(full),
            layoutSize = host,
            layoutPosition = Offset.Zero,
            progress = 0f,
            initialScale = 0.85f,
            cornerRadius = 28f,
            targetCornerRadius = 0f,
            density = Density(2f),
            visibleSourceBounds = visible,
        )
        assertEquals(visible, frame.bounds)
        assertEquals(full, frame.sourceBounds)
    }

    @Test
    fun displacedSourcesApproachOppositeEdgesTogetherWithoutUncoveringTheCard() {
        for (bounds in listOf(
            Rect(20f, 100f, 380f, 220f),
            Rect(20f, 580f, 380f, 700f),
            Rect(12f, 240f, 160f, 380f),
            Rect(240f, 240f, 388f, 380f),
            Rect(12f, 20f, 120f, 140f),
            Rect(0f, 100f, 160f, 240f),
        )) {
            val source = NavTransitionAnchor(bounds)
            var previous = bounds
            for (step in 0..100) {
                val current = frame(source, step / 100f).bounds
                assertTrue(current.left <= previous.left + 0.001f)
                assertTrue(current.top <= previous.top + 0.001f)
                assertTrue(current.right >= previous.right - 0.001f)
                assertTrue(current.bottom >= previous.bottom - 0.001f)
                previous = current
            }
            val late = frame(source, 0.9f).bounds
            assertTrue(abs(late.left - (host.width - late.right)) < 0.4f)
            assertTrue(abs(late.top - (host.height - late.bottom)) < 0.8f)
        }
    }

    @Test
    fun driverResidualCannotMoveEitherEndpointDuringHandoff() {
        val source = NavTransitionAnchor(Rect(20f, 580f, 380f, 700f))
        for (residual in listOf(0.0007f, 0.0025f, 0.005f)) {
            assertEquals(frame(source, 0f), frame(source, residual))
            assertEquals(frame(source, 1f), frame(source, 1f - residual))
        }
    }

    @Test
    fun progressOvershootCannotExpandBeyondTheNavigationPane() {
        val source = NavTransitionAnchor(Rect(20f, 300f, 380f, 400f))
        assertEquals(frame(source, 1f), frame(source, 1.2f))
        assertEquals(frame(source, 0f), frame(source, -0.2f))
    }

    @Test
    fun cornerHandoffWaitsForEveryEdgeAndPreservesTheDeviceRadius() {
        val source = NavTransitionAnchor(Rect(20f, 100f, 380f, 220f), 8.dp)
        // Assert before the eased geometry reaches the 4px (2dp) edge clearance.
        val beforeHandoff = frame(source, 0.94f)
        assertTrue(host.height - beforeHandoff.bounds.bottom >= 4f)
        assertEquals(28f, beforeHandoff.cornerRadius)
        assertTrue(frame(source, 0.99f).cornerRadius in 0f..28f)
        for (step in 35..100) {
            assertEquals(48f, frame(source, step / 100f, targetCornerRadius = 48f).cornerRadius)
        }
        assertEquals(16f, frame(source, 0f, targetCornerRadius = 48f).cornerRadius)
        val fullHostSource = NavTransitionAnchor(Rect(0f, 0f, 400f, 800f), 8.dp)
        assertEquals(16f, frame(fullHostSource, 0f).cornerRadius)
        assertEquals(0f, frame(fullHostSource, 1f).cornerRadius)
    }

    @Test
    fun invalidPublicGeometryOptionsFailEarly() {
        assertFailsWith<IllegalArgumentException> { NavTransitions.expansion(cornerRadius = (-1).dp) }
        assertFailsWith<IllegalArgumentException> { NavTransitionAnchor(Rect.Zero, Float.NaN.dp) }
        assertFailsWith<IllegalArgumentException> { NavTransitions.expandFrom(Color.White, targetCornerRadius = (-1).dp) { null } }
    }
}
