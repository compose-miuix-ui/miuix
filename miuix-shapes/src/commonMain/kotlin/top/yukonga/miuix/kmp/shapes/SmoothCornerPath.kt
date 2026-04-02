// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.shapes

import androidx.compose.ui.graphics.Path
import kotlin.math.max
import kotlin.math.min

/**
 * Builds a smooth rounded rectangle path with continuous curvature corners.
 *
 * Each corner is a single cubic Bézier whose extension and control point ratio are
 * interpolated based on available space. When corners have ample room, the curve
 * extends further along the edges with a gradual curvature transition. When corners
 * overlap (e.g. capsule shapes), it smoothly degrades to a standard circular arc.
 */
internal object SmoothCornerPath {

    /** Smooth edge extent: curve starts further from the corner than a standard circle. */
    private const val SMOOTH_EXT = 1.2f

    /** Smooth Bézier control point ratio. */
    private const val SMOOTH_CP = 0.63f

    /** Standard circular arc Bézier control point ratio. */
    private const val CIRCLE_CP = 0.5523f

    /** Interpolation threshold for smooth-to-standard degradation. */
    private const val K = 0.6541677f

    /** Pre-computed: SMOOTH_CP - CIRCLE_CP, avoids subtraction per corner. */
    private const val CP_DELTA = SMOOTH_CP - CIRCLE_CP

    /** Pre-computed: SMOOTH_EXT - 1f, avoids subtraction per corner. */
    private const val EXT_DELTA = SMOOTH_EXT - 1f

    /**
     * Builds the path into [path] with equal corner radii.
     * This is the hot path — most components use equal radii.
     */
    fun buildEqualPath(
        path: Path,
        width: Float,
        height: Float,
        radius: Float,
    ) {
        path.rewind()
        val r = min(radius, min(width / 2f, height / 2f))
        if (r <= 0f) return

        // Single weight computation for all 4 corners (equal radii)
        val he = min(width / 2f, height / 2f) // halfEdge simplification for equal radii
        val w = weight(r, he)
        val ext = r * (SMOOTH_EXT - EXT_DELTA * w)
        val cp = SMOOTH_CP - CP_DELTA * w

        // Clamp: 2 corners per edge, use the tighter constraint
        val ext2 = ext * 2f
        val extH = if (ext2 <= width) ext else width / 2f
        val extV = if (ext2 <= height) ext else height / 2f
        val e = min(extH, extV)

        path.moveTo(e, 0f)
        path.lineTo(width - e, 0f)
        corner(path, width - e, 0f, width, 0f, width, e, cp)
        path.lineTo(width, height - e)
        corner(path, width, height - e, width, height, width - e, height, cp)
        path.lineTo(e, height)
        corner(path, e, height, 0f, height, 0f, height - e, cp)
        path.lineTo(0f, e)
        corner(path, 0f, e, 0f, 0f, e, 0f, cp)
        path.close()
    }

    /**
     * Builds the path into [path] with independent corner radii.
     */
    fun buildUnevenPath(
        path: Path,
        width: Float,
        height: Float,
        topStartRadius: Float,
        topEndRadius: Float,
        bottomEndRadius: Float,
        bottomStartRadius: Float,
    ) {
        path.rewind()
        val maxRH = width / 2f
        val maxRV = height / 2f
        val tsr = min(topStartRadius, min(maxRH, maxRV))
        val ter = min(topEndRadius, min(maxRH, maxRV))
        val ber = min(bottomEndRadius, min(maxRH, maxRV))
        val bsr = min(bottomStartRadius, min(maxRH, maxRV))

        val tsW = weight(tsr, halfEdge(tsr, ter, width, bsr, height))
        val teW = weight(ter, halfEdge(ter, ber, height, tsr, width))
        val beW = weight(ber, halfEdge(ber, bsr, width, ter, height))
        val bsW = weight(bsr, halfEdge(bsr, tsr, height, ber, width))

        val tsExt = tsr * (SMOOTH_EXT - EXT_DELTA * tsW)
        val teExt = ter * (SMOOTH_EXT - EXT_DELTA * teW)
        val beExt = ber * (SMOOTH_EXT - EXT_DELTA * beW)
        val bsExt = bsr * (SMOOTH_EXT - EXT_DELTA * bsW)

        val topS = clampScale(tsExt, teExt, width)
        val rightS = clampScale(teExt, beExt, height)
        val bottomS = clampScale(beExt, bsExt, width)
        val leftS = clampScale(bsExt, tsExt, height)

        // Separate horizontal (H) and vertical (V) extents per corner
        val tsH = tsExt * topS
        val tsV = tsExt * leftS
        val teH = teExt * topS
        val teV = teExt * rightS
        val beH = beExt * bottomS
        val beV = beExt * rightS
        val bsH = bsExt * bottomS
        val bsV = bsExt * leftS

        val tsCp = SMOOTH_CP - CP_DELTA * tsW
        val teCp = SMOOTH_CP - CP_DELTA * teW
        val beCp = SMOOTH_CP - CP_DELTA * beW
        val bsCp = SMOOTH_CP - CP_DELTA * bsW

        path.moveTo(tsH, 0f)
        path.lineTo(width - teH, 0f)
        corner(path, width - teH, 0f, width, 0f, width, teV, teCp)
        path.lineTo(width, height - beV)
        corner(path, width, height - beV, width, height, width - beH, height, beCp)
        path.lineTo(bsH, height)
        corner(path, bsH, height, 0f, height, 0f, height - bsV, bsCp)
        path.lineTo(0f, tsV)
        corner(path, 0f, tsV, 0f, 0f, tsH, 0f, tsCp)
        path.close()
    }

    private fun corner(
        path: Path,
        sx: Float,
        sy: Float,
        cx: Float,
        cy: Float,
        ex: Float,
        ey: Float,
        cp: Float,
    ) {
        path.cubicTo(
            sx + (cx - sx) * cp,
            sy + (cy - sy) * cp,
            ex + (cx - ex) * cp,
            ey + (cy - ey) * cp,
            ex,
            ey,
        )
    }

    private fun halfEdge(r: Float, adjH: Float, edgeH: Float, adjV: Float, edgeV: Float): Float {
        val sumH = r + adjH
        val heH = if (sumH > 0f) r + (edgeH - sumH) * (r / sumH) else edgeH / 2f
        val sumV = r + adjV
        val heV = if (sumV > 0f) r + (edgeV - sumV) * (r / sumV) else edgeV / 2f
        return min(heH, heV)
    }

    private fun weight(radius: Float, halfEdge: Float): Float {
        if (halfEdge <= 0f || radius <= 0f) return 1f
        val threshold = halfEdge * K
        val range = halfEdge - threshold
        return if (range <= 0f) 1f else max(0f, min(1f, (radius - threshold) / range))
    }

    private fun clampScale(ext1: Float, ext2: Float, edgeLen: Float): Float {
        val sum = ext1 + ext2
        return if (sum <= edgeLen || sum <= 0f) 1f else edgeLen / sum
    }
}
