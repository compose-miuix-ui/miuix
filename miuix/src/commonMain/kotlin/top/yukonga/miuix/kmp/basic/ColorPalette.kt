// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.basic

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.mocharealm.gaze.capsule.ContinuousCapsule
import com.mocharealm.gaze.capsule.ContinuousRoundedRectangle
import top.yukonga.miuix.kmp.color.api.toHsv
import top.yukonga.miuix.kmp.color.space.Hsv
import kotlin.math.abs
import kotlin.math.min
import kotlin.math.roundToInt

/**
 * A color palette component that allows users to select colors from a grid of HSV values.
 *
 * @param initialColor The initial color to display in the palette.
 * @param onColorChanged Callback invoked when the selected color changes.
 * @param modifier Modifier for styling the palette.
 * @param rows Number of rows in the color grid.
 * @param hueColumns Number of columns for hue variations.
 * @param includeGrayColumn Whether to include a gray column in the palette.
 * @param showPreview Whether to show a preview of the selected color.
 * @param cornerRadius Corner radius for the palette's shape.
 * @param indicatorRadius Radius of the selection indicator circle.
 */
@Composable
fun ColorPalette(
    initialColor: Color,
    onColorChanged: (Color) -> Unit,
    modifier: Modifier = Modifier,
    rows: Int = 7,
    hueColumns: Int = 12,
    includeGrayColumn: Boolean = true,
    showPreview: Boolean = true,
    cornerRadius: Dp = 16.dp,
    indicatorRadius: Dp = 10.dp,
) {
    val totalColumns = hueColumns + if (includeGrayColumn) 1 else 0

    val rowSV = remember(rows) { buildRowSV(rows) }
    val grayV = remember(rows) { buildGrayV(rows) }

    var selectedRow by remember { mutableStateOf(0) }
    var selectedCol by remember { mutableStateOf(0) }
    var alpha by remember { mutableStateOf(initialColor.alpha.coerceIn(0f, 1f)) }
    var lastEmittedColor by remember { mutableStateOf<Color?>(null) }
    var lastAcceptedHSV by remember { mutableStateOf<Triple<Float, Float, Float>?>(null) }
    LaunchedEffect(initialColor, rows, hueColumns, includeGrayColumn) {
        val hsvInit = initialColor.toHsv()
        val h = hsvInit.h.toFloat()
        val s = (hsvInit.s / 100.0).toFloat()
        val v = (hsvInit.v / 100.0).toFloat()
        val currentHSV = Triple(h, s, v)
        if (lastAcceptedHSV?.let { hsvEqualApprox(it, currentHSV) } == true) {
            alpha = initialColor.alpha
            lastAcceptedHSV = currentHSV
            return@LaunchedEffect
        }

        val isGray = includeGrayColumn && s < 0.05f
        val col = if (isGray) {
            totalColumns - 1
        } else {
            val k = (h % 360f) / 360f * hueColumns
            k.roundToInt().coerceIn(0, hueColumns - 1)
        }
        val row = if (isGray) {
            indexOfNearestGrayV(v, grayV)
        } else {
            indexOfNearestRowSV(s, v, rowSV)
        }
        selectedCol = col
        selectedRow = row
        alpha = initialColor.alpha
        lastAcceptedHSV = currentHSV
    }

    val onColorChangedState = rememberUpdatedState(onColorChanged)

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        // Color preview
        if (showPreview) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(26.dp)
                    .clip(ContinuousCapsule)
                    .background(lastEmittedColor ?: initialColor)
            )
        }

        val baseColor = remember(selectedRow, selectedCol, rows, hueColumns, includeGrayColumn) {
            cellColor(selectedCol, selectedRow, rowSV, grayV, hueColumns, includeGrayColumn)
        }

        PaletteCanvas(
            rows = rows,
            hueColumns = hueColumns,
            includeGrayColumn = includeGrayColumn,
            cornerRadius = cornerRadius,
            indicatorRadius = indicatorRadius,
            selectedRow = selectedRow,
            selectedCol = selectedCol,
            rowSV = rowSV,
            grayV = grayV,
            onSelect = { r, c ->
                selectedRow = r
                selectedCol = c
                val newColor = cellColor(c, r, rowSV, grayV, hueColumns, includeGrayColumn).copy(alpha = alpha)
                lastAcceptedHSV = newColor.toHsv().let { Triple(it.h.toFloat(), (it.s / 100.0).toFloat(), (it.v / 100.0).toFloat()) }
                lastEmittedColor = newColor
                onColorChangedState.value(newColor)
            }
        )

        val hsvBase = baseColor.toHsv()
        val h = hsvBase.h.toFloat()
        val s = (hsvBase.s / 100.0).toFloat()
        val v = (hsvBase.v / 100.0).toFloat()

        HsvAlphaSlider(
            currentHue = h,
            currentSaturation = s,
            currentValue = v,
            currentAlpha = alpha,
            onAlphaChanged = {
                alpha = it
                val newColor = baseColor.copy(alpha = it)
                lastAcceptedHSV =
                    baseColor.toHsv().let { Triple(it.h.toFloat(), (it.s / 100.0).toFloat(), (it.v / 100.0).toFloat()) }
                lastEmittedColor = newColor
                onColorChangedState.value(newColor)
            }
        )
    }
}

@Composable
private fun PaletteCanvas(
    rows: Int,
    hueColumns: Int,
    includeGrayColumn: Boolean,
    cornerRadius: Dp,
    indicatorRadius: Dp,
    selectedRow: Int,
    selectedCol: Int,
    rowSV: List<Pair<Float, Float>>,
    grayV: List<Float>,
    onSelect: (row: Int, col: Int) -> Unit,
) {
    val onSelectState = rememberUpdatedState(onSelect)
    val totalColumns = hueColumns + if (includeGrayColumn) 1 else 0
    val shape = remember(cornerRadius) { ContinuousRoundedRectangle(cornerRadius) }

    var sizePx by remember { mutableStateOf(IntSize.Zero) }

    Box(
        modifier = Modifier
            .clip(shape)
            .onGloballyPositioned { sizePx = it.size }
            .pointerInput(rows, hueColumns, includeGrayColumn) {
                awaitEachGesture {
                    val down = awaitFirstDown()
                    if (sizePx.width == 0 || sizePx.height == 0) return@awaitEachGesture
                    val (r0, c0) = pointToCell(down.position, sizePx, rows, totalColumns)
                    onSelectState.value(r0, c0)

                    val pointerId = down.id
                    while (true) {
                        val event = awaitPointerEvent()
                        val change = event.changes.find { it.id == pointerId } ?: event.changes.firstOrNull() ?: break
                        if (!change.pressed) break
                        val (r, c) = pointToCell(change.position, sizePx, rows, totalColumns)
                        onSelectState.value(r, c)
                        change.consume()
                    }
                }
            }
            .fillMaxWidth()
            .height(180.dp)
    ) {
        PaletteGrid(
            rows = rows,
            hueColumns = hueColumns,
            includeGrayColumn = includeGrayColumn,
            rowSV = rowSV,
            grayV = grayV
        )

        if (sizePx.width > 0 && sizePx.height > 0) {
            val w = sizePx.width
            val h = sizePx.height
            val colEdges = IntArray(totalColumns + 1) { i -> (i * w) / totalColumns }
            val rowEdges = IntArray(rows + 1) { i -> (i * h) / rows }
            val left = colEdges[selectedCol]
            val right = colEdges[selectedCol + 1]
            val top = rowEdges[selectedRow]
            val bottom = rowEdges[selectedRow + 1]
            val cxPx = (left + right) / 2f
            val cyPx = (top + bottom) / 2f

            val indicatorSize = indicatorRadius * 2
            val density = LocalDensity.current
            Box(
                modifier = Modifier
                    .offset(
                        x = with(density) { cxPx.toDp() - indicatorSize / 2 },
                        y = with(density) { cyPx.toDp() - indicatorSize / 2 }
                    )
                    .size(indicatorSize)
                    .drawWithCache {
                        val strokeWidth = 6.dp.toPx()
                        val halfStroke = strokeWidth / 2f
                        val glowSpread = 2.dp.toPx()
                        val glowColor = Color.Black.copy(alpha = 0.25f)

                        val ringCenterRadius = (size.minDimension / 2f) - halfStroke
                        val gradientRadius = ringCenterRadius + halfStroke + glowSpread

                        val glowBrush = Brush.radialGradient(
                            colorStops = listOf(
                                ((ringCenterRadius - halfStroke - glowSpread).coerceAtLeast(0f) / gradientRadius) to Color.Transparent,
                                ((ringCenterRadius - halfStroke) / gradientRadius) to glowColor,
                                ((ringCenterRadius + halfStroke) / gradientRadius) to glowColor,
                                ((ringCenterRadius + halfStroke + glowSpread) / gradientRadius) to Color.Transparent
                            ).toTypedArray(),
                            radius = gradientRadius
                        )

                        onDrawBehind {
                            drawCircle(
                                brush = glowBrush,
                                radius = gradientRadius
                            )

                            drawCircle(
                                color = Color.White,
                                radius = ringCenterRadius,
                                style = Stroke(width = strokeWidth)
                            )
                        }
                    }
            )
        }
    }
}

@Composable
private fun PaletteGrid(
    rows: Int,
    hueColumns: Int,
    includeGrayColumn: Boolean,
    rowSV: List<Pair<Float, Float>>,
    grayV: List<Float>,
) {
    val totalColumns = hueColumns + if (includeGrayColumn) 1 else 0
    Canvas(Modifier.fillMaxSize()) {
        val w = size.width.toInt()
        val h = size.height.toInt()

        val colEdges = IntArray(totalColumns + 1) { i -> (i * w) / totalColumns }
        val rowEdges = IntArray(rows + 1) { i -> (i * h) / rows }

        for (r in 0 until rows) {
            val top = rowEdges[r].toFloat()
            val bottom = rowEdges[r + 1].toFloat()
            val cellH = bottom - top
            for (c in 0 until totalColumns) {
                val left = colEdges[c].toFloat()
                val right = colEdges[c + 1].toFloat()
                val cellW = right - left
                val color = cellColor(c, r, rowSV, grayV, hueColumns, includeGrayColumn)
                drawRect(
                    color = color,
                    topLeft = Offset(left, top),
                    size = Size(cellW, cellH)
                )
            }
        }
    }
}

private fun buildRowSV(rows: Int): List<Pair<Float, Float>> {
    if (rows <= 1) return listOf(1f to 1f)

    if (rows == 7) {
        val sArr = floatArrayOf(0.10f, 0.35f, 0.70f, 1.00f, 1.00f, 1.00f, 1.00f)
        val vArr = floatArrayOf(1.00f, 1.00f, 1.00f, 0.85f, 0.65f, 0.45f, 0.20f)
        return List(7) { i -> sArr[i] to vArr[i] }
    }

    val topBrightCut = minOf(0.34f, 2f / (rows - 1f))
    return List(rows) { i ->
        val t = i / (rows - 1f)
        val sRamp = (t / 0.35f).coerceIn(0f, 1f)
        val s = (0.10f + 0.90f * sRamp).coerceIn(0f, 1f)
        val v = if (t <= topBrightCut) 1f else {
            val k = ((t - topBrightCut) / (1f - topBrightCut)).coerceIn(0f, 1f)
            lerp(1f, 0.20f, k)
        }
        s to v
    }
}

private fun buildGrayV(rows: Int): List<Float> {
    if (rows <= 1) return listOf(1f)
    return List(rows) { i -> 1f - (i / (rows - 1f)) }
}

private fun cellColor(
    col: Int,
    row: Int,
    rowSV: List<Pair<Float, Float>>,
    grayV: List<Float>,
    hueColumns: Int,
    includeGrayColumn: Boolean,
): Color {
    val totalColumns = hueColumns + if (includeGrayColumn) 1 else 0
    val (s, v) = rowSV[row]
    return if (includeGrayColumn && col == totalColumns - 1) {
        Hsv(0.0, 0.0, (grayV[row] * 100.0)).toColor()
    } else {
        val step = 360f / hueColumns
        val h = (col * step) % 360f
        Hsv(h.toDouble(), (s * 100.0), (v * 100.0)).toColor()
    }
}

private fun pointToCell(pos: Offset, size: IntSize, rows: Int, totalColumns: Int): Pair<Int, Int> {
    val x = pos.x.coerceIn(0f, size.width.toFloat() - 1)
    val y = pos.y.coerceIn(0f, size.height.toFloat() - 1)
    val col = ((x / size.width) * totalColumns).toInt().coerceIn(0, totalColumns - 1)
    val row = ((y / size.height) * rows).toInt().coerceIn(0, rows - 1)
    return row to col
}

private fun hsvEqualApprox(
    a: Triple<Float, Float, Float>,
    b: Triple<Float, Float, Float>,
    epsH: Float = 1.5f,
    eps: Float = 0.02f
): Boolean {
    val dhRaw = abs(a.first - b.first)
    val dh = min(dhRaw, 360f - dhRaw)
    return dh <= epsH && abs(a.second - b.second) <= eps && abs(a.third - b.third) <= eps
}

private fun indexOfNearestGrayV(targetV: Float, grayV: List<Float>): Int {
    var idx = 0
    var minVal = Float.POSITIVE_INFINITY
    var i = 0
    val n = grayV.size
    while (i < n) {
        val diff = targetV - grayV[i]
        val v = diff * diff
        if (v < minVal) {
            minVal = v
            idx = i
        }
        i++
    }
    return idx
}

private fun indexOfNearestRowSV(targetS: Float, targetV: Float, rowSV: List<Pair<Float, Float>>): Int {
    var idx = 0
    var minVal = Float.POSITIVE_INFINITY
    var i = 0
    val n = rowSV.size
    while (i < n) {
        val s = rowSV[i].first
        val v = rowSV[i].second
        val ds = targetS - s
        val dv = targetV - v
        val d = ds * ds + dv * dv
        if (d < minVal) {
            minVal = d
            idx = i
        }
        i++
    }
    return idx
}

private fun sq(v: Float) = v * v
