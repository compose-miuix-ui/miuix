// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window

import top.yukonga.miuix.kmp.window.platform.linux.LinuxCaptionButtonType
import top.yukonga.miuix.kmp.window.platform.linux.LinuxCaptionIconPack
import top.yukonga.miuix.kmp.window.platform.linux.LinuxSystemDecorationAdapter
import kotlin.test.Test
import kotlin.test.assertEquals

class LinuxSystemDecorationAdapterTest {
    @Test
    fun parsesButtonsOnTheLeft() {
        val decoration = LinuxSystemDecorationAdapter.parse("'close,minimize,maximize:'")

        assertEquals(CaptionButtonsAlign.Start, decoration.alignment)
        assertEquals(
            listOf(
                LinuxCaptionButtonType.Close,
                LinuxCaptionButtonType.Minimize,
                LinuxCaptionButtonType.Maximize,
            ),
            decoration.buttons,
        )
    }

    @Test
    fun parsesButtonsOnTheRight() {
        val decoration = LinuxSystemDecorationAdapter.parse(":minimize,maximize,close")

        assertEquals(CaptionButtonsAlign.End, decoration.alignment)
        assertEquals(
            listOf(
                LinuxCaptionButtonType.Minimize,
                LinuxCaptionButtonType.Maximize,
                LinuxCaptionButtonType.Close,
            ),
            decoration.buttons,
        )
    }

    @Test
    fun ignoresUnsupportedAndDuplicateButtons() {
        val decoration = LinuxSystemDecorationAdapter.parse("appmenu,close,close:shade,minimize")

        assertEquals(CaptionButtonsAlign.End, decoration.alignment)
        assertEquals(
            listOf(LinuxCaptionButtonType.Close, LinuxCaptionButtonType.Minimize),
            decoration.buttons,
        )
    }

    @Test
    fun fallsBackWhenLayoutIsUnavailable() {
        val decoration = LinuxSystemDecorationAdapter.parse(null)

        assertEquals(CaptionButtonsAlign.End, decoration.alignment)
        assertEquals(
            listOf(
                LinuxCaptionButtonType.Minimize,
                LinuxCaptionButtonType.Maximize,
                LinuxCaptionButtonType.Close,
            ),
            decoration.buttons,
        )
    }

    @Test
    fun resolvesIconPackFromDesktopAndTheme() {
        assertEquals(
            LinuxCaptionIconPack.Kde,
            LinuxSystemDecorationAdapter.resolveIconPack("GNOME", "Breeze-Dark"),
        )
        assertEquals(
            LinuxCaptionIconPack.Gnome,
            LinuxSystemDecorationAdapter.resolveIconPack("KDE", "Adwaita"),
        )
        assertEquals(
            LinuxCaptionIconPack.Kde,
            LinuxSystemDecorationAdapter.resolveIconPack("KDE", null),
        )
    }
}
