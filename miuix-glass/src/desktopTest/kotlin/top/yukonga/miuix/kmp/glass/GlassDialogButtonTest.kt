// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asSkiaBitmap
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.graphics.toPixelMap
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertHeightIsEqualTo
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.v2.runComposeUiTest
import androidx.compose.ui.unit.dp
import org.jetbrains.skia.EncodedImageFormat
import org.jetbrains.skia.Image
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.theme.LocalContentColor
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.theme.darkColorScheme
import top.yukonga.miuix.kmp.theme.lightColorScheme
import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalTestApi::class)
class GlassDialogButtonTest {

    @Test
    fun lightActionsTintTheParentAndUseTheirSemanticColors() = assertPalette(isDark = false)

    @Test
    fun darkActionsTintTheParentAndUseTheirSemanticColors() = assertPalette(isDark = true)

    private fun assertPalette(isDark: Boolean) = runComposeUiTest {
        val enabled = mutableStateOf(true)
        val primary = Color(0xFF3467AB)
        val error = Color(0xFFD02040)
        val parent = Color(0xFF8090A0)
        setContent {
            MiuixTheme(colors = if (isDark) darkColorScheme(primary = primary, error = error) else lightColorScheme(primary = primary, error = error)) {
                Column(Modifier.background(parent).padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    for (role in GlassDialogButtonRole.entries) {
                        GlassDialogButton(
                            onClick = {},
                            modifier = Modifier.width(160.dp).testTag(role.name),
                            enabled = enabled.value,
                            role = role,
                        ) {
                            // A solid content swatch makes inherited foreground colors measurable.
                            Box(Modifier.size(16.dp).background(LocalContentColor.current))
                        }
                    }
                }
            }
        }
        waitForIdle()
        val ink = if (isDark) Color.White else Color.Black
        val tonal = ink.copy(alpha = if (isDark) 36f / 255f else 15f / 255f).compositeOver(parent)
        val expectedFills = listOf(tonal, primary, tonal)
        val expectedContent = listOf(ink.copy(alpha = if (isDark) 0.8f else 0.9f), Color.White, error)
        for ((i, role) in GlassDialogButtonRole.entries.withIndex()) {
            val node = onNodeWithTag(role.name)
            node.assertHeightIsEqualTo(50.dp)
            val pixels = node.captureToImage().toPixelMap()
            assertColor(expectedFills[i], pixels[8, pixels.height / 2])
            assertColor(expectedContent[i].compositeOver(expectedFills[i]), pixels[pixels.width / 2, pixels.height / 2])
        }
        val image = onRoot().captureToImage()
        val output = File("build/reports/dialog-material/buttons-${if (isDark) "dark" else "light"}.png")
        output.parentFile.mkdirs()
        Image.makeFromBitmap(image.asSkiaBitmap()).use { skiaImage ->
            checkNotNull(skiaImage.encodeToData(EncodedImageFormat.PNG)).use { output.writeBytes(it.bytes) }
        }

        runOnIdle { enabled.value = false }
        val disabledFills = listOf(tonal, primary.copy(alpha = 0.3f).compositeOver(parent), tonal)
        val disabledContent = listOf(ink.copy(alpha = 0.38f), Color.White.copy(alpha = if (isDark) 77f / 255f else 1f), error.copy(alpha = 0.3f))
        for ((i, role) in GlassDialogButtonRole.entries.withIndex()) {
            val node = onNodeWithTag(role.name)
            node.assertIsNotEnabled()
            val pixels = node.captureToImage().toPixelMap()
            assertColor(disabledFills[i], pixels[8, pixels.height / 2])
            assertColor(disabledContent[i].compositeOver(disabledFills[i]), pixels[pixels.width / 2, pixels.height / 2])
        }
    }

    @Test
    fun lightPressCoversFillAndContentAndClearsOnReleaseCancelAndDisable() = assertPress(isDark = false)

    @Test
    fun darkPressCoversFillAndContentAndClearsOnReleaseCancelAndDisable() = assertPress(isDark = true)

    private fun assertPress(isDark: Boolean) = runComposeUiTest {
        val source = MutableInteractionSource()
        val enabled = mutableStateOf(true)
        setContent {
            MiuixTheme(colors = if (isDark) darkColorScheme() else lightColorScheme()) {
                Box(Modifier.background(Color(0xFF8090A0)).padding(16.dp)) {
                    GlassDialogButton(
                        onClick = {},
                        modifier = Modifier.width(160.dp).testTag("action"),
                        enabled = enabled.value,
                        role = GlassDialogButtonRole.Primary,
                        interactionSource = source,
                    ) {
                        Box(Modifier.size(16.dp).background(LocalContentColor.current))
                    }
                }
            }
        }
        waitForIdle()
        val node = onNodeWithTag("action")
        val idle = node.captureToImage().toPixelMap()
        val overlay = if (isDark) Color(0x24FFFFFF) else Color(0x1F000000)
        val press = PressInteraction.Press(Offset.Zero)
        runOnIdle { assertTrue(source.tryEmit(press)) }
        waitForIdle()
        val pressed = node.captureToImage().toPixelMap()
        assertColor(overlay.compositeOver(idle[8, idle.height / 2]), pressed[8, pressed.height / 2])
        assertColor(overlay.compositeOver(idle[idle.width / 2, idle.height / 2]), pressed[pressed.width / 2, pressed.height / 2])
        assertColor(idle[0, 0], pressed[0, 0]) // The overlay shares the rounded clipping.
        val secondPress = PressInteraction.Press(Offset.Zero)
        runOnIdle {
            assertTrue(source.tryEmit(secondPress))
            assertTrue(source.tryEmit(PressInteraction.Release(press)))
        }
        waitForIdle()
        assertColor(pressed[8, pressed.height / 2], node.captureToImage().toPixelMap()[8, pressed.height / 2])
        runOnIdle { assertTrue(source.tryEmit(PressInteraction.Cancel(secondPress))) }
        waitForIdle()
        assertColor(idle[8, idle.height / 2], node.captureToImage().toPixelMap()[8, idle.height / 2])

        runOnIdle { assertTrue(source.tryEmit(PressInteraction.Press(Offset.Zero))) }
        waitForIdle()
        runOnIdle { enabled.value = false }
        node.assertIsNotEnabled()
        runOnIdle { enabled.value = true }
        waitForIdle()
        assertColor(idle[8, idle.height / 2], node.captureToImage().toPixelMap()[8, idle.height / 2])
    }

    @Test
    fun actionKeepsButtonSemanticsAndOnlyInvokesTheCurrentEnabledCallback() = runComposeUiTest {
        val enabled = mutableStateOf(true)
        val secondCallback = mutableStateOf(false)
        var firstClicks = 0
        var secondClicks = 0
        setContent {
            MiuixTheme {
                GlassDialogButton(
                    onClick = if (secondCallback.value) ({ secondClicks++ }) else ({ firstClicks++ }),
                    enabled = enabled.value,
                ) {
                    Text("Confirm", style = MiuixTheme.textStyles.button)
                }
            }
        }
        val node = onNodeWithText("Confirm")
        node.assert(SemanticsMatcher.expectValue(SemanticsProperties.Role, Role.Button)).assertIsEnabled()
        node.performClick()
        assertEquals(1, firstClicks)
        runOnIdle { secondCallback.value = true }
        node.performClick()
        assertEquals(1, secondClicks)
        runOnIdle { enabled.value = false }
        node.assertIsNotEnabled().performClick()
        assertEquals(1, secondClicks)
    }
}

private fun assertColor(expected: Color, actual: Color) {
    assertEquals(expected.red, actual.red, 2f / 255f, "red")
    assertEquals(expected.green, actual.green, 2f / 255f, "green")
    assertEquals(expected.blue, actual.blue, 2f / 255f, "blue")
    assertEquals(expected.alpha, actual.alpha, 2f / 255f, "alpha")
}
