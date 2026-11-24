// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.basic

import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.isSpecified
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastRoundToInt
import androidx.compose.ui.util.lerp
import top.yukonga.miuix.kmp.color.api.toHsv
import top.yukonga.miuix.kmp.color.space.Hsv
import top.yukonga.miuix.kmp.squircle.squircleClip
import kotlin.math.abs
import kotlin.math.min

/**
 * A color palette component that allows users to select colors from a grid of HSV values.
 *
 * @param color The color to display in the palette.
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
    color: Color,
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

    val currentExternalColor by rememberUpdatedState(color)
    var selectedRow by remember { mutableIntStateOf(0) }
    var selectedCol by remember { mutableIntStateOf(0) }
    var alpha by remember { mutableStateOf(color.alpha.coerceIn(0f, 1f)) }
    var lastEmittedColor by remember { mutableStateOf(Color.Unspecified) }
    var lastAcceptedHSV by remember { mutableStateOf<Triple<Float, Float, Float>?>(null) }
    var lastSyncedColor by remember { mutableStateOf(Color.Unspecified) }
    var lastSyncedRows by remember { mutableIntStateOf(-1) }
    var lastSyncedHueCols by remember { mutableIntStateOf(-1) }
    var lastSyncedIncludeGray by remember { mutableStateOf(includeGrayColumn) }

    SideEffect {
        if (
            lastSyncedColor == color &&
            lastSyncedRows == rows &&
            lastSyncedHueCols == hueColumns &&
            lastSyncedIncludeGray == includeGrayColumn
        ) {
            return@SideEffect
        }
        lastSyncedColor = color
        lastSyncedRows = rows
        lastSyncedHueCols = hueColumns
        lastSyncedIncludeGray = includeGrayColumn

        val hsvInit = color.toHsv()
        val h = hsvInit.h
        val s = hsvInit.s / 100f
        val v = hsvInit.v / 100f
        val currentHSV = Triple(h, s, v)
        if (lastAcceptedHSV?.let { hsvEqualApprox(it, currentHSV) } == true) {
            alpha = color.alpha
            lastAcceptedHSV = currentHSV
            return@SideEffect
        }

        val isGray = includeGrayColumn && s < 0.05f
        val col = if (isGray) {
            totalColumns - 1
        } else {
            val k = (h % 360f) / 360f * hueColumns
            k.fastRoundToInt().coerceIn(0, hueColumns - 1)
        }
        val row = if (isGray) {
            indexOfNearestGrayV(v, grayV)
        } else {
            indexOfNearestRowSV(s, v, rowSV)
        }
        selectedCol = col
        selectedRow = row
        alpha = color.alpha
        lastAcceptedHSV = currentHSV
    }

    val onColorChangedState = rememberUpdatedState(onColorChanged)
    val currentHueColumns by rememberUpdatedState(hueColumns)
    val currentIncludeGray by rememberUpdatedState(includeGrayColumn)
    val currentRowSV by rememberUpdatedState(rowSV)
    val currentGrayV by rememberUpdatedState(grayV)

    val baseColor = remember(selectedRow, selectedCol, rows, hueColumns, includeGrayColumn) {
        cellColor(selectedCol, selectedRow, rowSV, grayV, hueColumns, includeGrayColumn)
    }
    val currentBaseColor by rememberUpdatedState(baseColor)
    val selectedRowProvider = remember { { selectedRow } }
    val selectedColProvider = remember { { selectedCol } }
    val onCellSelect = remember {
        { r: Int, c: Int ->
            selectedRow = r
            selectedCol = c
            val newColor = cellColor(
                c,
                r,
                currentRowSV,
                currentGrayV,
                currentHueColumns,
                currentIncludeGray,
            ).copy(alpha = alpha)
            lastAcceptedHSV = newColor.toHsv().let { Triple(it.h, it.s / 100f, it.v / 100f) }
            lastEmittedColor = newColor
            onColorChangedState.value(newColor)
        }
    }
    val alphaProvider = remember { { alpha } }
    val onAlphaChanged = remember {
        { newAlpha: Float ->
            alpha = newAlpha
            val base = currentBaseColor
            val newColor = base.copy(alpha = newAlpha)
            lastAcceptedHSV = base.toHsv().let { Triple(it.h, it.s / 100f, it.v / 100f) }
            lastEmittedColor = newColor
            onColorChangedState.value(newColor)
        }
    }

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
                    .clip(CircleShape)
                    .drawBehind {
                        val previewColor = if (lastEmittedColor.isSpecified) lastEmittedColor else currentExternalColor
                        drawRect(previewColor)
                    },
            )
        }

        PaletteCanvas(
            rows = rows,
            hueColumns = hueColumns,
            includeGrayColumn = includeGrayColumn,
            cornerRadius = cornerRadius,
            indicatorRadius = indicatorRadius,
            selectedRowProvider = selectedRowProvider,
            selectedColProvider = selectedColProvider,
            rowSV = rowSV,
            grayV = grayV,
            onSelect = onCellSelect,
        )

        val hsvBase = remember(baseColor) { baseColor.toHsv() }
        val h = hsvBase.h
        val s = hsvBase.s / 100f
        val v = hsvBase.v / 100f

        HsvAlphaSliderImpl(
            currentHue = h,
            currentSaturation = s,
            currentValue = v,
            alphaProvider = alphaProvider,
            onAlphaChanged = onAlphaChanged,
            hapticEffect = SliderDefaults.DefaultHapticEffect,
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
    selectedRowProvider: () -> Int,
    selectedColProvider: () -> Int,
    rowSV: List<Pair<Float, Float>>,
    grayV: List<Float>,
    onSelect: (row: Int, col: Int) -> Unit,
) {
    val onSelectState = rememberUpdatedState(onSelect)
    val totalColumns = hueColumns + if (includeGrayColumn) 1 else 0
    val layoutDirection = LocalLayoutDirection.current
    val isRtl = layoutDirection == LayoutDirection.Rtl

    var sizePx by remember { mutableStateOf(IntSize.Zero) }

    Box(
        modifier = Modifier
            .squircleClip(cornerRadius)
            .onSizeChanged { sizePx = it }
            .pointerInput(rows, hueColumns, includeGrayColumn, isRtl) {
                awaitEachGesture {
                    val down = awaitFirstDown()
                    if (sizePx.width == 0 || sizePx.height == 0) return@awaitEachGesture
                    var lastRow = pointToRow(down.position.y, sizePx.height, rows)
                    var lastCol = pointToCol(down.position.x, sizePx.width, totalColumns, isRtl)
                    onSelectState.value(lastRow, lastCol)

                    val pointerId = down.id
                    while (true) {
                        val event = awaitPointerEvent()
                        val change = event.changes.find { it.id == pointerId } ?: event.changes.firstOrNull() ?: break
                        if (!change.pressed) break
                        val r = pointToRow(change.position.y, sizePx.height, rows)
                        val c = pointToCol(change.position.x, sizePx.width, totalColumns, isRtl)
                        if (r != lastRow || c != lastCol) {
                            lastRow = r
                            lastCol = c
                            onSelectState.value(r, c)
                        }
                        change.consume()
                    }
                }
            }
            .fillMaxWidth()
            .height(180.dp),
    ) {
        PaletteGrid(
            rows = rows,
            hueColumns = hueColumns,
            includeGrayColumn = includeGrayColumn,
            rowSV = rowSV,
            grayV = grayV,
            isRtl = isRtl,
        )

        val indicatorSize = indicatorRadius * 2
        val density = LocalDensity.current
        val halfIndicatorPx = with(density) { (indicatorSize / 2).roundToPx() }
        Box(
            modifier = Modifier
                .graphicsLayer {
                    alpha = if (sizePx.width > 0 && sizePx.height > 0) 1f else 0f
                }
                .offset {
                    val w = sizePx.width
                    val h = sizePx.height
                    if (w <= 0 || h <= 0) {
                        IntOffset.Zero
                    } else {
                        val col = selectedColProvider()
                        val row = selectedRowProvider()
                        val start = (col * w) / totalColumns
                        val end = ((col + 1) * w) / totalColumns
                        val top = (row * h) / rows
                        val bottom = ((row + 1) * h) / rows
                        val cxPx = (start + end) / 2f
                        val cyPx = (top + bottom) / 2f
                        IntOffset(
                            x = (cxPx - halfIndicatorPx).toInt(),
                            y = (cyPx - halfIndicatorPx).toInt(),
                        )
                    }
                }
                .size(indicatorSize)
                .drawWithCache {
                    val strokeWidth = 6.dp.toPx()
                    val halfStroke = strokeWidth / 2f
                    val glowSpread = 2.dp.toPx()
                    val glowColor = Color.Black.copy(alpha = 0.25f)

                    val ringCenterRadius = (size.minDimension / 2f) - halfStroke
                    val gradientRadius = ringCenterRadius + halfStroke + glowSpread

                    val indicatorStroke = Stroke(width = strokeWidth)
                    val glowBrush = Brush.radialGradient(
                        colorStops = arrayOf(
                            ((ringCenterRadius - halfStroke - glowSpread).coerceAtLeast(0f) / gradientRadius) to Color.Transparent,
                            ((ringCenterRadius - halfStroke) / gradientRadius) to glowColor,
                            ((ringCenterRadius + halfStroke) / gradientRadius) to glowColor,
                            ((ringCenterRadius + halfStroke + glowSpread) / gradientRadius) to Color.Transparent,
                        ),
                        radius = gradientRadius,
                    )

                    onDrawBehind {
                        drawCircle(brush = glowBrush, radius = gradientRadius)
                        drawCircle(
                            color = Color.White,
                            radius = ringCenterRadius,
                            style = indicatorStroke,
                        )
                    }
                },
        )
    }
}

@Composable
private fun PaletteGrid(
    rows: Int,
    hueColumns: Int,
    includeGrayColumn: Boolean,
    rowSV: List<Pair<Float, Float>>,
    grayV: List<Float>,
    isRtl: Boolean,
) {
    val totalColumns = hueColumns + if (includeGrayColumn) 1 else 0
    val precomputedColors = remember(rows, hueColumns, includeGrayColumn) {
        Array(rows) { r ->
            Array(totalColumns) { c ->
                cellColor(c, r, rowSV, grayV, hueColumns, includeGrayColumn)
            }
        }
    }
    Spacer(
        modifier = Modifier
            .fillMaxSize()
            .drawWithCache {
                val w = size.width.toInt()
                val h = size.height.toInt()
                val colEdges = IntArray(totalColumns + 1) { i -> (i * w) / totalColumns }
                val rowEdges = IntArray(rows + 1) { i -> (i * h) / rows }

                onDrawBehind {
                    for (r in 0 until rows) {
                        val top = rowEdges[r].toFloat()
                        val bottom = rowEdges[r + 1].toFloat()
                        val cellH = bottom - top
                        for (c in 0 until totalColumns) {
                            val start = colEdges[c].toFloat()
                            val end = colEdges[c + 1].toFloat()
                            val cellW = end - start
                            val color = precomputedColors[r][c]
                            val left = if (isRtl) size.width - end else start
                            drawRect(
                                color = color,
                                topLeft = Offset(left, top),
                                size = Size(cellW, cellH),
                            )
                        }
                    }
                }
            },
    )
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
        val v = if (t <= topBrightCut) {
            1f
        } else {
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
        Hsv(0f, 0f, grayV[row] * 100f).toColor()
    } else {
        val step = 360f / hueColumns
        val h = (col * step) % 360f
        Hsv(h, s * 100f, v * 100f).toColor()
    }
}

private fun pointToCol(posX: Float, width: Int, totalColumns: Int, isRtl: Boolean): Int {
    val x = posX.coerceIn(0f, width.toFloat() - 1f)
    var col = ((x / width) * totalColumns).toInt().coerceIn(0, totalColumns - 1)
    if (isRtl) col = totalColumns - 1 - col
    return col
}

private fun pointToRow(posY: Float, height: Int, rows: Int): Int {
    val y = posY.coerceIn(0f, height.toFloat() - 1f)
    return ((y / height) * rows).toInt().coerceIn(0, rows - 1)
}

private fun hsvEqualApprox(
    a: Triple<Float, Float, Float>,
    b: Triple<Float, Float, Float>,
    epsH: Float = 1.5f,
    eps: Float = 0.02f,
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
