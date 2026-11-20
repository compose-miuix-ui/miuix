// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toPixelMap
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.getUnclippedBoundsInRoot
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.v2.runComposeUiTest
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.MiuixScrollBehavior
import top.yukonga.miuix.kmp.basic.ScrollBehavior
import top.yukonga.miuix.kmp.basic.TopAppBar
import top.yukonga.miuix.kmp.basic.TopAppBarState
import top.yukonga.miuix.kmp.blur.layerBackdrop
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
import top.yukonga.miuix.kmp.utils.overScrollVertical
import kotlin.math.abs
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertSame
import kotlin.test.assertTrue

@OptIn(ExperimentalTestApi::class)
class GlassTopAppBarStateTest {
    @Test
    fun compactGlassBarStaysCollapsedAndRestoresExpandableScrollState() = assertCompactBarState(glass = true)

    @Test
    fun compactRegularBarStaysCollapsedAndRestoresExpandableScrollState() = assertCompactBarState(glass = false)

    private fun assertCompactBarState(glass: Boolean) = runComposeUiTest {
        val name = if (glass) "glass" else "regular"
        val expandable = mutableStateOf(false)
        val state = TopAppBarState(-1000f, -100f, 0f)
        lateinit var behavior: ScrollBehavior
        setContent {
            DialogTestHost {
                behavior = MiuixScrollBehavior(state)
                if (glass) {
                    GlassTopAppBar(
                        title = "Compact",
                        largeTitle = "Expanded",
                        expandable = expandable.value,
                        scrollBehavior = behavior,
                        contentModifier = Modifier.testTag("foreground"),
                        bottomContent = { Box(Modifier.size(300.dp, 32.dp).testTag("bottom")) },
                        actions = { GlassIconButton(onClick = {}) {} },
                    )
                } else {
                    TopAppBar(
                        title = "Compact",
                        largeTitle = "Expanded",
                        expandable = expandable.value,
                        scrollBehavior = behavior,
                        modifier = Modifier.testTag("foreground"),
                        bottomContent = { Box(Modifier.size(300.dp, 32.dp).testTag("bottom")) },
                    )
                }
            }
        }
        waitForIdle()
        onNodeWithText("Expanded").assertDoesNotExist()
        val compactBounds = onNodeWithTag("foreground").getUnclippedBoundsInRoot()
        val bottomBounds = onNodeWithTag("bottom").getUnclippedBoundsInRoot()

        runOnIdle {
            assertEquals(0f, state.heightOffsetLimit)
            assertEquals(0f, state.heightOffset)
            val consumed = behavior.nestedScrollConnection.onPreScroll(Offset(0f, -120f), NestedScrollSource.UserInput)
            assertEquals(Offset.Zero, consumed)
            behavior.nestedScrollConnection.onPostScroll(Offset(0f, -120f), Offset.Zero, NestedScrollSource.UserInput)
        }
        waitForIdle()
        assertEquals(compactBounds, onNodeWithTag("foreground").getUnclippedBoundsInRoot())
        assertEquals(bottomBounds, onNodeWithTag("bottom").getUnclippedBoundsInRoot())
        saveImage("$name-topbar-compact", onRoot().captureToImage())

        // A wide-to-narrow switch restores collapse from content scroll, not the old height offset.
        runOnIdle { expandable.value = true }
        waitForIdle()
        runOnIdle {
            assertTrue(state.heightOffsetLimit < 0f)
            assertEquals(1f, state.collapsedFraction)
            assertEquals(-120f, state.contentOffset)
            expandable.value = false
        }
        waitForIdle()
        runOnIdle {
            behavior.nestedScrollConnection.onPostScroll(Offset(0f, 120f), Offset.Zero, NestedScrollSource.UserInput)
            expandable.value = true
        }
        waitForIdle()
        val expandedBounds = onNodeWithTag("foreground").getUnclippedBoundsInRoot()
        assertTrue(expandedBounds.bottom - expandedBounds.top > compactBounds.bottom - compactBounds.top)
        runOnIdle {
            assertEquals(0f, state.contentOffset)
            assertEquals(0f, state.heightOffset)
        }
        saveImage("$name-topbar-expanded", onRoot().captureToImage())

        runOnIdle { expandable.value = false }
        waitForIdle()
        onNodeWithText("Expanded").assertDoesNotExist()
        assertEquals(compactBounds, onNodeWithTag("foreground").getUnclippedBoundsInRoot())
    }

    @Test
    fun shortPageOverscrollCompletesProgressiveButtonMaterial() = assertElasticButtonMaterial(progressive = true, explicitOverlap = false)

    @Test
    fun shortPageOverscrollCompletesSceneButtonMaterial() = assertElasticButtonMaterial(progressive = false, explicitOverlap = false)

    @Test
    fun explicitUnscrolledShortPageOverscrollCompletesProgressiveButtonMaterial() = assertElasticButtonMaterial(progressive = true, explicitOverlap = true)

    @Test
    fun explicitUnscrolledShortPageOverscrollCompletesSceneButtonMaterial() = assertElasticButtonMaterial(progressive = false, explicitOverlap = true)

    private fun assertElasticButtonMaterial(progressive: Boolean, explicitOverlap: Boolean) = runComposeUiTest {
        val state = TopAppBarState(-1000f, 0f, 0f)
        val explicitScrolled = mutableStateOf(false)
        setContent {
            DialogTestHost {
                val backdrop = rememberLayerBackdrop()
                val reportOffset: (Float) -> Unit = remember { { state.overscrollOffset = it } }
                Box(Modifier.size(400.dp)) {
                    Box(
                        Modifier.matchParentSize().layerBackdrop(backdrop).drawBehind {
                            drawRect(Color.Blue)
                            val stripe = 6.dp.toPx()
                            var x = 0f
                            while (x < size.width) {
                                drawRect(Color.White, topLeft = Offset(x, 0f), size = Size(stripe, size.height))
                                x += stripe * 2
                            }
                        },
                    )
                    LazyColumn(
                        modifier = Modifier.align(Alignment.BottomCenter).size(300.dp, 100.dp)
                            .testTag("short-list").overScrollVertical(onOffsetChanged = reportOffset),
                        overscrollEffect = null,
                    ) {
                        item { Box(Modifier.height(20.dp)) }
                    }
                    if (explicitOverlap) {
                        GlassTopAppBar(
                            title = "",
                            isContentScrolled = explicitScrolled.value,
                            scrollBehavior = MiuixScrollBehavior(state),
                            progressiveBlur = progressive,
                            backdrop = backdrop,
                            navigationIcon = {
                                GlassIconButton(onClick = {}, modifier = Modifier.testTag("elastic-button")) { }
                            },
                        )
                    } else {
                        GlassTopAppBar(
                            title = "",
                            scrollBehavior = MiuixScrollBehavior(state),
                            progressiveBlur = progressive,
                            backdrop = backdrop,
                            navigationIcon = {
                                GlassIconButton(onClick = {}, modifier = Modifier.testTag("elastic-button")) { }
                            },
                        )
                    }
                }
            }
        }
        fun buttonPixels() = onNodeWithTag("elastic-button").captureToImage().toPixelMap()
        val resting = buttonPixels()
        runOnIdle {
            state.contentOffset = -1000f
            explicitScrolled.value = true
        }
        val complete = buttonPixels()
        assertTrue(
            (0 until complete.height).any { y ->
                (0 until complete.width).any { x -> abs(complete[x, y].red - resting[x, y].red) > 0.05f }
            },
            "The reference material must visibly blur and tint the striped backdrop",
        )
        runOnIdle {
            state.contentOffset = 0f
            explicitScrolled.value = false
        }
        waitForIdle()
        onNodeWithTag("short-list").performTouchInput {
            down(Offset(centerX, height - 10f))
            repeat(6) { moveBy(Offset(0f, -50f), delayMillis = 40) }
        }
        assertEquals(0f, state.contentOffset, 0f, "Short content must not produce actual scroll")
        assertTrue(state.overscrollOffset < -with(density) { GlassTopAppBarDefaults.ProgressiveBlurRampDistance.toPx() })
        val held = buttonPixels()
        // Compare the whole surface, including the rim, to the fully active scrolled material.
        for (y in 0 until complete.height) {
            for (x in 0 until complete.width) assertColorNear(complete[x, y], held[x, y], tolerance = 3f / 255f)
        }
        onNodeWithTag("short-list").performTouchInput { up() }
        mainClock.advanceTimeBy(2000)
        waitForIdle()
        assertEquals(0f, state.overscrollOffset, 0f)
        val returned = buttonPixels()
        for (y in 0 until resting.height) {
            for (x in 0 until resting.width) assertColorNear(resting[x, y], returned[x, y], tolerance = 3f / 255f)
        }
    }

    @Test
    fun bandBrushEqualityIgnoresTheRenderedShaderCache() = runComposeUiTest {
        val brush = GlassTopAppBarDefaults.bandBrush(Color.Red)
        val equalBrush = GlassTopAppBarDefaults.bandBrush(Color.Red)
        val hash = brush.hashCode()
        setContent { Box(Modifier.size(100.dp).background(brush)) }
        onRoot().captureToImage()
        assertEquals(equalBrush, brush)
        assertEquals(hash, brush.hashCode())
        assertEquals(equalBrush.hashCode(), brush.hashCode())
        assertNotEquals(GlassTopAppBarDefaults.bandBrush(Color.Blue), brush)
        assertNotEquals(GlassTopAppBarDefaults.bandBrush(Color.Red.copy(alpha = 0.5f)), brush)
    }

    @Test
    fun rampRetainsItsStateAndReadsReplacementProviders() = runComposeUiTest {
        val provider = mutableStateOf<(() -> Float)?>(null)
        val offset = mutableFloatStateOf(0f)
        var observed: State<Float>? = null
        setContent {
            val ramp = GlassTopAppBarDefaults.collapseRamp(null, provider.value)
            SideEffect { observed = ramp }
        }
        waitForIdle()
        val original = checkNotNull(observed)
        assertEquals(1f, original.value)
        val halfRamp = with(density) { GlassTopAppBarDefaults.RampDistance.toPx() / 2 }
        runOnIdle { provider.value = { offset.floatValue } }
        waitForIdle()
        assertSame(original, observed)
        assertEquals(0f, original.value, 0f)
        runOnIdle { offset.floatValue = -halfRamp }
        assertEquals(0.5f, original.value, 0.001f)
        runOnIdle { provider.value = { -halfRamp * 2 } }
        waitForIdle()
        assertSame(original, observed)
        assertEquals(1f, original.value)
        runOnIdle { offset.floatValue = 1000f }
        assertEquals(1f, original.value, "The replaced provider must no longer drive progress")
        runOnIdle { provider.value = null }
        waitForIdle()
        assertSame(original, observed)
        assertEquals(1f, original.value)
    }

    @Test
    fun elasticMotionRestoresButtonFillWithoutChangingActualScroll() = runComposeUiTest {
        val state = TopAppBarState(-1000f, 0f, 0f)
        setContent {
            DialogTestHost {
                Box(Modifier.size(400.dp, 300.dp).background(Color.Blue)) {
                    GlassTopAppBar(
                        title = "",
                        scrollBehavior = MiuixScrollBehavior(state),
                        backgroundAlpha = { 0f },
                        fill = Color.Red,
                        buttonShadow = null,
                        stroke = null,
                        navigationIcon = {
                            GlassIconButton(onClick = {}, modifier = Modifier.testTag("button")) { }
                        },
                    )
                }
            }
        }
        fun assertButton(color: Color) {
            val image = onNodeWithTag("button").captureToImage().toPixelMap()
            assertColorNear(color, image[image.width / 2, image.height / 2])
        }
        assertButton(Color.Blue)
        runOnIdle { state.overscrollOffset = -80f }
        assertButton(Color.Red)
        assertEquals(0f, state.contentOffset, 0f)
        runOnIdle { state.overscrollOffset = 0f }
        assertButton(Color.Blue)
        runOnIdle { state.contentOffset = -80f }
        assertButton(Color.Red)
        runOnIdle { state.overscrollOffset = 120f }
        assertButton(Color.Red)
        runOnIdle {
            state.overscrollOffset = 0f
            state.contentOffset = 0f
        }
        assertButton(Color.Blue)
    }

    @Test
    fun explicitUnscrolledActivatesOnlyOnNegativeOverscroll() = runComposeUiTest {
        val state = TopAppBarState(-1000f, 0f, 0f)
        setContent {
            DialogTestHost {
                Box(Modifier.size(400.dp, 300.dp).background(Color.Blue)) {
                    GlassTopAppBar(
                        title = "",
                        isContentScrolled = false,
                        scrollBehavior = MiuixScrollBehavior(state),
                        backgroundAlpha = { 0f },
                        fill = Color.Red,
                        buttonShadow = null,
                        stroke = null,
                        navigationIcon = {
                            GlassIconButton(onClick = {}, modifier = Modifier.testTag("button")) { }
                        },
                    )
                }
            }
        }
        fun assertButton(color: Color) {
            val image = onNodeWithTag("button").captureToImage().toPixelMap()
            assertColorNear(color, image[image.width / 2, image.height / 2])
        }
        assertButton(Color.Blue)
        runOnIdle { state.overscrollOffset = 80f }
        assertButton(Color.Blue)
        runOnIdle { state.overscrollOffset = -80f }
        assertButton(Color.Red)
        runOnIdle { state.overscrollOffset = 0f }
        assertButton(Color.Blue)
    }
}
