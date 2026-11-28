// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.symbol

import androidx.compose.ui.graphics.vector.PathNode
import org.jetbrains.skia.Data
import org.jetbrains.skia.Font
import org.jetbrains.skia.FontHinting
import org.jetbrains.skia.FontMgr
import org.jetbrains.skia.FontVariation
import org.jetbrains.skia.PathBuilder
import org.jetbrains.skia.Rect
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.symbol.Next
import top.yukonga.miuix.kmp.icon.symbol.Reset
import top.yukonga.miuix.kmp.icon.symbol.Search
import top.yukonga.miuix.kmp.icon.symbol.SpeedPlayback0Point5x
import top.yukonga.miuix.kmp.icon.symbol.Undo
import java.io.File
import kotlin.math.abs
import kotlin.test.Test
import kotlin.test.assertTrue

/**
 * Cross-checks our built-in IUP + `glyf`/`gvar` interpolation against Skia's native TrueType
 * variable font engine when the source `.ttf` files are present locally.
 */
class VariableSymbolSkiaParityTest {

    private data class Case(
        val fontFile: String,
        val codePoint: Int,
        val symbol: VariableSymbol,
    )

    private fun computeNodesTightBounds(nodes: List<PathNode>): Rect {
        val builder = PathBuilder()
        try {
            for (node in nodes) {
                when (node) {
                    is PathNode.MoveTo -> builder.moveTo(node.x, node.y)
                    is PathNode.LineTo -> builder.lineTo(node.x, node.y)
                    is PathNode.QuadTo -> builder.quadTo(node.x1, node.y1, node.x2, node.y2)
                    PathNode.Close -> builder.closePath()
                    else -> error("Unexpected PathNode: $node")
                }
            }
            val skiaPath = builder.detach()
            return try {
                skiaPath.computeTightBounds()
            } finally {
                skiaPath.close()
            }
        } finally {
            builder.close()
        }
    }

    @Test
    fun interpolatedBoundsMatchSkiaNativeVariableFont() {
        val fontDir = File("/home/yukonga/Downloads/Telegram Desktop/font")
        if (!fontDir.isDirectory) return

        val cases = listOf(
            Case("misymbol_vf.ttf", 0xF0004, MiuixIcons.Symbol.Search),
            Case("misymbol_vf.ttf", 0xF0000, MiuixIcons.Symbol.Reset),
            Case("misymbol_vf.ttf", 0xF0006, MiuixIcons.Symbol.Undo),
            Case("HyperOSSymbols-Content-RegularVF.ttf", 0xF00FA, MiuixIcons.Symbol.SpeedPlayback0Point5x),
            Case("HyperOSSymbols-LayoutVF.ttf", 0xF00BD, MiuixIcons.Symbol.Next),
        )
        val weights = floatArrayOf(150f, 250f, 305f, 330f, 430f, 500f, 700f)

        val baseTypefaces = cases.map { it.fontFile }.distinct().associateWith { name ->
            val bytes = File(fontDir, name).readBytes()
            requireNotNull(FontMgr.default.makeFromData(Data.makeFromBytes(bytes)))
        }

        try {
            val emSize = 1000f
            for (case in cases) {
                val baseTf = baseTypefaces.getValue(case.fontFile)
                for (w in weights) {
                    val cloned = baseTf.makeClone(FontVariation("wght", w))
                    val skiaFont = Font(cloned, emSize).apply {
                        hinting = FontHinting.NONE
                        isLinearMetrics = true
                        isSubpixel = true
                    }
                    try {
                        val glyph = skiaFont.getUTF32Glyph(case.codePoint)
                        assertTrue(glyph.toInt() != 0, "Glyph not found for ${case.symbol.name}")
                        val skiaPath = requireNotNull(skiaFont.getPath(glyph)) {
                            "Skia returned null path for ${case.symbol.name}@$w"
                        }
                        val skiaRect = try {
                            skiaPath.computeTightBounds()
                        } finally {
                            skiaPath.close()
                        }

                        val nodes = case.symbol.outline().toPathNodes(case.symbol, w)
                        val ourRect = computeNodesTightBounds(nodes)

                        val ascent = case.symbol.ascent.toFloat()
                        val skiaLeft = skiaRect.left
                        val skiaRight = skiaRect.right
                        val skiaTop = ascent + skiaRect.top
                        val skiaBottom = ascent + skiaRect.bottom

                        val tol = 2.5f
                        assertTrue(
                            abs(ourRect.left - skiaLeft) <= tol &&
                                abs(ourRect.right - skiaRight) <= tol &&
                                abs(ourRect.top - skiaTop) <= tol &&
                                abs(ourRect.bottom - skiaBottom) <= tol,
                            "${case.symbol.name}@$w mismatch: ours=$ourRect vs skia=($skiaLeft,$skiaTop,$skiaRight,$skiaBottom)",
                        )
                    } finally {
                        skiaFont.close()
                        cloned.close()
                    }
                }
            }
        } finally {
            baseTypefaces.values.forEach { it.close() }
        }
    }

    @Test
    fun speedPlaybackSymbolDecodesAndRendersBounds() {
        val symbol = MiuixIcons.Symbol.SpeedPlayback0Point5x
        val bounds = computeNodesTightBounds(symbol.outline().toPathNodes(symbol, SymbolWeights.Medium))
        assertTrue(bounds.width > 0f && bounds.height > 0f)
    }
}
