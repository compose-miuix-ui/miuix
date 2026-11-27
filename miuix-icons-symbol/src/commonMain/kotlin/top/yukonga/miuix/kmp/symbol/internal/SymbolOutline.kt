// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.symbol.internal

import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.vector.PathNode
import top.yukonga.miuix.kmp.symbol.VariableSymbol
import kotlin.math.min

/**
 * Decoded TrueType outline with pre-resolved IUP variation deltas for the two `wght` extremes.
 *
 * [coords] packs 6 shorts per point sequentially:
 * `[baseX, baseY, dxMin, dyMin, dxMax, dyMax]`.
 */
internal class SymbolOutline(
    val contourEnds: IntArray,
    val onCurveBits: ByteArray,
    val coords: ShortArray,
    val pointCount: Int,
) {
    fun isOnCurve(index: Int): Boolean = ((onCurveBits[index ushr 3].toInt() ushr (index and 7)) and 1) != 0

    /**
     * Evaluates the interpolated outline for [symbol] at [weight] directly into [targetPath],
     * scaled to fit inside `[boxWidth, boxHeight]` and centered without clipping.
     *
     * Performs zero heap allocations per call.
     */
    fun writeToPath(
        targetPath: Path,
        symbol: VariableSymbol,
        weight: Float,
        boxWidth: Float,
        boxHeight: Float,
    ) {
        targetPath.rewind()
        targetPath.fillType = PathFillType.NonZero
        if (pointCount == 0 || contourEnds.isEmpty() || boxWidth <= 0f || boxHeight <= 0f) return

        val clamped = weight.coerceIn(symbol.minWeight, symbol.maxWeight)
        val deltaOffset: Int
        val factor: Float
        when {
            clamped < symbol.defaultWeight && symbol.defaultWeight > symbol.minWeight -> {
                deltaOffset = 2
                factor = (symbol.defaultWeight - clamped) / (symbol.defaultWeight - symbol.minWeight)
            }

            clamped > symbol.defaultWeight && symbol.maxWeight > symbol.defaultWeight -> {
                deltaOffset = 4
                factor = (clamped - symbol.defaultWeight) / (symbol.maxWeight - symbol.defaultWeight)
            }

            else -> {
                deltaOffset = 2
                factor = 0f
            }
        }

        val advW = symbol.advanceWidthAt(clamped).coerceAtLeast(1f)
        val emH = symbol.emHeight.toFloat().coerceAtLeast(1f)
        val scale = min(boxWidth / advW, boxHeight / emH)
        val offsetX = (boxWidth - advW * scale) * 0.5f
        val offsetY = (boxHeight - emH * scale) * 0.5f
        val ascentF = symbol.ascent.toFloat()

        fun px(i: Int): Float {
            val base = i * 6
            val rawX = coords[base] + coords[base + deltaOffset] * factor
            return offsetX + rawX * scale
        }

        fun py(i: Int): Float {
            val base = i * 6
            val rawY = coords[base + 1] + coords[base + deltaOffset + 1] * factor
            return offsetY + (ascentF - rawY) * scale
        }

        var contourStart = 0
        for (c in contourEnds.indices) {
            val contourEnd = contourEnds[c]
            val len = contourEnd - contourStart + 1
            if (len >= 2) {
                val startOn = isOnCurve(contourStart)
                val endOn = isOnCurve(contourEnd)
                val startX: Float
                val startY: Float
                val firstIdx: Int
                val lastIdx: Int

                if (startOn) {
                    startX = px(contourStart)
                    startY = py(contourStart)
                    firstIdx = contourStart + 1
                    lastIdx = contourEnd
                } else if (endOn) {
                    startX = px(contourEnd)
                    startY = py(contourEnd)
                    firstIdx = contourStart
                    lastIdx = contourEnd - 1
                } else {
                    startX = (px(contourEnd) + px(contourStart)) * 0.5f
                    startY = (py(contourEnd) + py(contourStart)) * 0.5f
                    firstIdx = contourStart
                    lastIdx = contourEnd
                }

                targetPath.moveTo(startX, startY)
                var hasPendingOff = false
                var ctrlX = 0f
                var ctrlY = 0f

                var idx = firstIdx
                while (idx <= lastIdx) {
                    val cx = px(idx)
                    val cy = py(idx)
                    if (isOnCurve(idx)) {
                        if (hasPendingOff) {
                            targetPath.quadraticTo(ctrlX, ctrlY, cx, cy)
                            hasPendingOff = false
                        } else {
                            targetPath.lineTo(cx, cy)
                        }
                    } else {
                        if (hasPendingOff) {
                            targetPath.quadraticTo(ctrlX, ctrlY, (ctrlX + cx) * 0.5f, (ctrlY + cy) * 0.5f)
                        }
                        ctrlX = cx
                        ctrlY = cy
                        hasPendingOff = true
                    }
                    idx++
                }

                if (hasPendingOff) {
                    targetPath.quadraticTo(ctrlX, ctrlY, startX, startY)
                }
                targetPath.close()
            }
            contourStart = contourEnd + 1
        }
    }

    /**
     * Materializes [PathNode] commands in the symbol's native viewport coordinate space
     * (`[0..advanceWidthAt(weight)]` x `[0..emHeight]`, Y-down) for [VariableSymbol.toImageVector].
     */
    fun toPathNodes(
        symbol: VariableSymbol,
        weight: Float,
    ): List<PathNode> {
        if (pointCount == 0 || contourEnds.isEmpty()) return emptyList()

        val clamped = weight.coerceIn(symbol.minWeight, symbol.maxWeight)
        val deltaOffset: Int
        val factor: Float
        when {
            clamped < symbol.defaultWeight && symbol.defaultWeight > symbol.minWeight -> {
                deltaOffset = 2
                factor = (symbol.defaultWeight - clamped) / (symbol.defaultWeight - symbol.minWeight)
            }

            clamped > symbol.defaultWeight && symbol.maxWeight > symbol.defaultWeight -> {
                deltaOffset = 4
                factor = (clamped - symbol.defaultWeight) / (symbol.maxWeight - symbol.defaultWeight)
            }

            else -> {
                deltaOffset = 2
                factor = 0f
            }
        }

        val ascentF = symbol.ascent.toFloat()

        fun px(i: Int): Float {
            val base = i * 6
            return coords[base] + coords[base + deltaOffset] * factor
        }

        fun py(i: Int): Float {
            val base = i * 6
            return ascentF - (coords[base + 1] + coords[base + deltaOffset + 1] * factor)
        }

        val nodes = ArrayList<PathNode>(pointCount + contourEnds.size * 2)
        var contourStart = 0
        for (c in contourEnds.indices) {
            val contourEnd = contourEnds[c]
            val len = contourEnd - contourStart + 1
            if (len >= 2) {
                val startOn = isOnCurve(contourStart)
                val endOn = isOnCurve(contourEnd)
                val startX: Float
                val startY: Float
                val firstIdx: Int
                val lastIdx: Int

                if (startOn) {
                    startX = px(contourStart)
                    startY = py(contourStart)
                    firstIdx = contourStart + 1
                    lastIdx = contourEnd
                } else if (endOn) {
                    startX = px(contourEnd)
                    startY = py(contourEnd)
                    firstIdx = contourStart
                    lastIdx = contourEnd - 1
                } else {
                    startX = (px(contourEnd) + px(contourStart)) * 0.5f
                    startY = (py(contourEnd) + py(contourStart)) * 0.5f
                    firstIdx = contourStart
                    lastIdx = contourEnd
                }

                nodes.add(PathNode.MoveTo(startX, startY))
                var hasPendingOff = false
                var ctrlX = 0f
                var ctrlY = 0f

                var idx = firstIdx
                while (idx <= lastIdx) {
                    val cx = px(idx)
                    val cy = py(idx)
                    if (isOnCurve(idx)) {
                        if (hasPendingOff) {
                            nodes.add(PathNode.QuadTo(ctrlX, ctrlY, cx, cy))
                            hasPendingOff = false
                        } else {
                            nodes.add(PathNode.LineTo(cx, cy))
                        }
                    } else {
                        if (hasPendingOff) {
                            nodes.add(PathNode.QuadTo(ctrlX, ctrlY, (ctrlX + cx) * 0.5f, (ctrlY + cy) * 0.5f))
                        }
                        ctrlX = cx
                        ctrlY = cy
                        hasPendingOff = true
                    }
                    idx++
                }

                if (hasPendingOff) {
                    nodes.add(PathNode.QuadTo(ctrlX, ctrlY, startX, startY))
                }
                nodes.add(PathNode.Close)
            }
            contourStart = contourEnd + 1
        }
        return nodes
    }
}

/**
 * 91-character printable ASCII alphabet (`!`..`~` excluding `"`, `$`, `\`) so encoded payloads
 * require zero escaping inside Kotlin double-quoted string literals.
 */
internal const val BASE91_ALPHABET: String =
    "!#%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[]^_`abcdefghijklmnopqrstuvwxyz{|}~"

private val Base91DecodeTable: IntArray = IntArray(128) { -1 }.apply {
    for (i in BASE91_ALPHABET.indices) {
        this[BASE91_ALPHABET[i].code] = i
    }
}

internal fun encodeBase91(bytes: ByteArray): String {
    val out = StringBuilder((bytes.size * 124) / 100 + 4)
    var b = 0
    var n = 0
    for (raw in bytes) {
        b = b or ((raw.toInt() and 0xFF) shl n)
        n += 8
        if (n > 13) {
            var v = b and 8191
            if (v > 88) {
                b = b ushr 13
                n -= 13
            } else {
                v = b and 16383
                b = b ushr 14
                n -= 14
            }
            out.append(BASE91_ALPHABET[v % 91])
            out.append(BASE91_ALPHABET[v / 91])
        }
    }
    if (n > 0) {
        out.append(BASE91_ALPHABET[b % 91])
        if (n > 7 || b > 90) {
            out.append(BASE91_ALPHABET[b / 91])
        }
    }
    return out.toString()
}

internal fun decodeBase91(encoded: String): ByteArray {
    val out = ByteArray((encoded.length * 14) / 16 + 4)
    var outPos = 0
    var v = -1
    var b = 0
    var n = 0

    for (i in encoded.indices) {
        val code = encoded[i].code
        if (code >= 128) continue
        val d = Base91DecodeTable[code]
        if (d == -1) continue
        if (v < 0) {
            v = d
        } else {
            v += d * 91
            b = b or (v shl n)
            n += if ((v and 8191) > 88) 13 else 14
            while (n > 7) {
                out[outPos++] = (b and 0xFF).toByte()
                b = b ushr 8
                n -= 8
            }
            v = -1
        }
    }
    if (v != -1) {
        out[outPos++] = ((b or (v shl n)) and 0xFF).toByte()
    }
    return out.copyOf(outPos)
}

internal fun decodeSymbolOutline(encoded: String): SymbolOutline {
    val bytes = decodeBase91(encoded)
    var pos = 0

    fun readUVarint(): Int {
        var result = 0
        var shift = 0
        while (true) {
            val byte = bytes[pos++].toInt() and 0xFF
            result = result or ((byte and 0x7F) shl shift)
            if ((byte and 0x80) == 0) break
            shift += 7
        }
        return result
    }

    fun readSVarint(): Int {
        val u = readUVarint()
        return (u ushr 1) xor -(u and 1)
    }

    val contourCount = readUVarint()
    val pointCount = readUVarint()
    val contourEnds = IntArray(contourCount)
    var running = -1
    for (i in 0 until contourCount) {
        running += readUVarint()
        contourEnds[i] = running
    }

    val onCurveLen = (pointCount + 7) ushr 3
    val onCurveBits = bytes.copyOfRange(pos, pos + onCurveLen)
    pos += onCurveLen

    val coords = ShortArray(pointCount * 6)
    var curX = 0
    var curY = 0
    for (i in 0 until pointCount) {
        curX += readSVarint()
        curY += readSVarint()
        val base = i * 6
        coords[base] = curX.toShort()
        coords[base + 1] = curY.toShort()
    }
    for (i in 0 until pointCount) {
        val base = i * 6
        coords[base + 2] = readSVarint().toShort()
        coords[base + 3] = readSVarint().toShort()
    }
    for (i in 0 until pointCount) {
        val base = i * 6
        coords[base + 4] = readSVarint().toShort()
        coords[base + 5] = readSVarint().toShort()
    }

    return SymbolOutline(
        contourEnds = contourEnds,
        onCurveBits = onCurveBits,
        coords = coords,
        pointCount = pointCount,
    )
}
