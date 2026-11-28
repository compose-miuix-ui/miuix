// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.symbol

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.PathNode
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.symbol.FavoritesFill
import top.yukonga.miuix.kmp.icon.symbol.Next
import top.yukonga.miuix.kmp.icon.symbol.Search
import top.yukonga.miuix.kmp.icon.symbol.SpeedPlayback0Point5x
import top.yukonga.miuix.kmp.icon.symbol.Undo
import top.yukonga.miuix.kmp.symbol.internal.BASE91_ALPHABET
import top.yukonga.miuix.kmp.symbol.internal.SymbolOutline
import top.yukonga.miuix.kmp.symbol.internal.decodeBase91
import top.yukonga.miuix.kmp.symbol.internal.encodeBase91
import kotlin.math.abs
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue

class VariableSymbolTest {

    @Test
    fun base91AlphabetIsSafeAndUnique() {
        assertEquals(91, BASE91_ALPHABET.length)
        assertEquals(91, BASE91_ALPHABET.toSet().size)
        assertTrue('"' !in BASE91_ALPHABET)
        assertTrue('$' !in BASE91_ALPHABET)
        assertTrue('\\' !in BASE91_ALPHABET)
    }

    @Test
    fun base91RoundTripsAcrossSizesAndByteValues() {
        val cases = listOf(
            ByteArray(0),
            byteArrayOf(0),
            byteArrayOf(0xFF.toByte()),
            byteArrayOf(1, 2, 3),
            ByteArray(256) { it.toByte() },
            ByteArray(513) { ((it * 73 + 19) and 0xFF).toByte() },
        )
        for (original in cases) {
            val encoded = encodeBase91(original)
            val decoded = decodeBase91(encoded)
            assertContentEquals(original, decoded)
        }
    }

    @Test
    fun builtInSymbolsDecodeAndInterpolateAcrossWeights() {
        val samples = listOf(
            MiuixIcons.Symbol.Search,
            MiuixIcons.Symbol.Undo,
            MiuixIcons.Symbol.FavoritesFill,
            MiuixIcons.Symbol.SpeedPlayback0Point5x,
            MiuixIcons.Symbol.Next,
        )

        for (symbol in samples) {
            val outline = symbol.outline()
            assertTrue(outline.pointCount > 0, "${symbol.name} must have points")
            assertTrue(outline.contourEnds.isNotEmpty(), "${symbol.name} must have contours")

            val thinNodes = outline.toPathNodes(symbol, SymbolWeights.Min)
            val regularNodes = outline.toPathNodes(symbol, SymbolWeights.Regular)
            val boldNodes = outline.toPathNodes(symbol, SymbolWeights.Max)
            assertTrue(thinNodes.isNotEmpty())
            assertEquals(thinNodes.size, regularNodes.size)
            assertEquals(regularNodes.size, boldNodes.size)
            assertNotEquals(thinNodes, boldNodes, "${symbol.name} should vary between min and max weight")

            assertEquals(thinNodes, outline.toPathNodes(symbol, 0f))
            assertEquals(boldNodes, outline.toPathNodes(symbol, 1200f))
        }
    }

    @Test
    fun favoritesFillPreservesColrDefaultColor() {
        assertEquals(Color(0xFFFA382E), MiuixIcons.Symbol.FavoritesFill.defaultColor)
        assertEquals(Color.Unspecified, MiuixIcons.Symbol.Search.defaultColor)
    }

    @Test
    fun narrowAndWideSymbolsPreserveAspectRatioAndDynamicAdvanceWidth() {
        val narrow = MiuixIcons.Symbol.Next
        assertTrue(narrow.widthRatio < 1f, "Expected narrow widthRatio, got ${narrow.widthRatio}")
        val narrowIv = narrow.toImageVector(weight = SymbolWeights.Medium, defaultSize = 24.dp)
        assertTrue(narrowIv.defaultWidth < 24.dp)
        assertEquals(24.dp, narrowIv.defaultHeight)

        val wide = MiuixIcons.Symbol.SpeedPlayback0Point5x
        assertTrue(wide.widthRatio > 1f, "Expected wide widthRatio, got ${wide.widthRatio}")
        assertTrue(wide.advanceWidthAt(SymbolWeights.Max) > wide.advanceWidthAt(SymbolWeights.Regular))
    }

    @Test
    fun trueTypeContourEdgeCasesEmitValidPathNodes() {
        val dummySymbol = VariableSymbol(
            name = "Test",
            category = SymbolCategory.Core,
            encodedData = "",
            advanceWidth = 100,
            ascent = 100,
            descent = 0,
        )

        val caseB = SymbolOutline(
            contourEnds = intArrayOf(2),
            onCurveBits = byteArrayOf(0b0000_0100),
            coords = shortArrayOf(
                0, 0, 0, 0, 0, 0,
                100, 0, 0, 0, 0, 0,
                50, 100, 0, 0, 0, 0,
            ),
            pointCount = 3,
        )
        val nodesB = caseB.toPathNodes(dummySymbol, SymbolWeights.Regular)
        assertEquals(4, nodesB.size)
        assertEquals(PathNode.MoveTo(50f, 0f), nodesB[0])
        assertEquals(PathNode.QuadTo(0f, 100f, 50f, 100f), nodesB[1])
        assertEquals(PathNode.QuadTo(100f, 100f, 50f, 0f), nodesB[2])
        assertEquals(PathNode.Close, nodesB[3])

        val caseC = SymbolOutline(
            contourEnds = intArrayOf(3),
            onCurveBits = byteArrayOf(0),
            coords = shortArrayOf(
                0, 50, 0, 0, 0, 0,
                50, 100, 0, 0, 0, 0,
                100, 50, 0, 0, 0, 0,
                50, 0, 0, 0, 0, 0,
            ),
            pointCount = 4,
        )
        val nodesC = caseC.toPathNodes(dummySymbol, SymbolWeights.Regular)
        assertEquals(6, nodesC.size)
        assertEquals(PathNode.MoveTo(25f, 75f), nodesC[0])
        assertEquals(PathNode.Close, nodesC[5])
        val lastQuad = nodesC[4] as PathNode.QuadTo
        assertTrue(abs(lastQuad.x2 - 25f) < 1e-4f && abs(lastQuad.y2 - 75f) < 1e-4f)
    }
}
