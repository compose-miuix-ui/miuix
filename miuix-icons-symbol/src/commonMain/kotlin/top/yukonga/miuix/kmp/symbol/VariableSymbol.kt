// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.symbol

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import top.yukonga.miuix.kmp.symbol.internal.SymbolOutline
import top.yukonga.miuix.kmp.symbol.internal.decodeSymbolOutline
import kotlin.concurrent.Volatile

/**
 * Standard named weight presets matching the HyperOS Symbols variable font axis (`wght`: 150–700).
 */
object SymbolWeights {
    val Min: Float = 150f
    val Light: Float = 250f
    val Normal: Float = 305f
    val Regular: Float = 330f
    val Medium: Float = 430f
    val Demibold: Float = 500f
    val Max: Float = 700f
}

/**
 * Source category of a built-in [VariableSymbol].
 */
@Immutable
enum class SymbolCategory {
    /** Core HyperOS symbols. */
    Core,

    /** Content & device symbols. */
    Content,

    /** Layout & navigation symbols. */
    Layout,
}

/**
 * A self-contained, continuously variable vector symbol backed by compact encoded TrueType
 * outline + `gvar` variation deltas.
 *
 * Each built-in symbol is stored in its own top-level property so unused symbols are stripped by
 * R8 / ProGuard / JS / Wasm dead-code elimination. No external font resource files or platform
 * font engines are required at runtime.
 *
 * @property name Canonical icon name.
 * @property category Source family category.
 * @property advanceWidth Default advance width in font design units at [defaultWeight].
 * @property advanceWidthDeltaMin Advance width delta at [minWeight].
 * @property advanceWidthDeltaMax Advance width delta at [maxWeight].
 * @property ascent Font ascender in design units.
 * @property descent Font descender in design units (negative).
 * @property defaultColor Native default color for color-table symbols (or [Color.Unspecified]).
 * @property minWeight Minimum supported `wght` axis coordinate.
 * @property defaultWeight Default `wght` axis coordinate where base coordinates live.
 * @property maxWeight Maximum supported `wght` axis coordinate.
 */
@Immutable
class VariableSymbol(
    val name: String,
    val category: SymbolCategory,
    internal val encodedData: String,
    val advanceWidth: Int = DEFAULT_EM_HEIGHT,
    val advanceWidthDeltaMin: Int = 0,
    val advanceWidthDeltaMax: Int = 0,
    val ascent: Int = DEFAULT_ASCENT,
    val descent: Int = DEFAULT_DESCENT,
    val defaultColor: Color = Color.Unspecified,
    val minWeight: Float = SymbolWeights.Min,
    val defaultWeight: Float = SymbolWeights.Regular,
    val maxWeight: Float = SymbolWeights.Max,
) {
    @Volatile
    private var cachedOutline: SymbolOutline? = null

    /** Total vertical design box height (`ascent - descent`). */
    val emHeight: Int
        get() = ascent - descent

    /** Aspect ratio (`width / height`) at [defaultWeight]. */
    val widthRatio: Float
        get() = advanceWidth.toFloat() / emHeight.toFloat()

    /** Interpolated advance width in design units for a given [weight]. */
    fun advanceWidthAt(weight: Float): Float {
        val clamped = weight.coerceIn(minWeight, maxWeight)
        return when {
            clamped < defaultWeight && defaultWeight > minWeight -> {
                val factor = (defaultWeight - clamped) / (defaultWeight - minWeight)
                advanceWidth + advanceWidthDeltaMin * factor
            }

            clamped > defaultWeight && maxWeight > defaultWeight -> {
                val factor = (clamped - defaultWeight) / (maxWeight - defaultWeight)
                advanceWidth + advanceWidthDeltaMax * factor
            }

            else -> advanceWidth.toFloat()
        }
    }

    internal fun outline(): SymbolOutline {
        cachedOutline?.let { return it }
        val decoded = decodeSymbolOutline(encodedData)
        cachedOutline = decoded
        return decoded
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is VariableSymbol) return false
        return name == other.name &&
            category == other.category &&
            encodedData == other.encodedData &&
            advanceWidth == other.advanceWidth &&
            advanceWidthDeltaMin == other.advanceWidthDeltaMin &&
            advanceWidthDeltaMax == other.advanceWidthDeltaMax &&
            ascent == other.ascent &&
            descent == other.descent &&
            defaultColor == other.defaultColor &&
            minWeight == other.minWeight &&
            defaultWeight == other.defaultWeight &&
            maxWeight == other.maxWeight
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + category.hashCode()
        result = 31 * result + encodedData.hashCode()
        result = 31 * result + advanceWidth
        result = 31 * result + advanceWidthDeltaMin
        result = 31 * result + advanceWidthDeltaMax
        result = 31 * result + ascent
        result = 31 * result + descent
        result = 31 * result + defaultColor.hashCode()
        result = 31 * result + minWeight.hashCode()
        result = 31 * result + defaultWeight.hashCode()
        result = 31 * result + maxWeight.hashCode()
        return result
    }

    override fun toString(): String = "VariableSymbol(name=$name, category=$category, advanceWidth=$advanceWidth)"

    companion object {
        const val DEFAULT_ASCENT: Int = 1040
        const val DEFAULT_DESCENT: Int = -290
        const val DEFAULT_EM_HEIGHT: Int = DEFAULT_ASCENT - DEFAULT_DESCENT
    }
}
