// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import kotlin.test.Test
import kotlin.test.assertEquals

class GlassTransformPopupTest {

    @Test
    fun transformSpringsUseFolmeValueTargetThreshold() {
        assertEquals(GlassMotion.TRANSFORM_VISIBILITY_THRESHOLD, GlassMotion.transformBounds(true).visibilityThreshold)
        assertEquals(GlassMotion.TRANSFORM_VISIBILITY_THRESHOLD, GlassMotion.transformBounds(false).visibilityThreshold)
        assertEquals(GlassMotion.TRANSFORM_VISIBILITY_THRESHOLD, GlassMotion.transformCenter(true).visibilityThreshold)
        assertEquals(GlassMotion.TRANSFORM_VISIBILITY_THRESHOLD, GlassMotion.transformCenter(false).visibilityThreshold)
    }

    @Test
    fun simplifiedAnchorContentReturnsDuringDismissal() {
        assertEquals(false, shouldRenderAnchorContent(simplified = true, show = true))
        assertEquals(true, shouldRenderAnchorContent(simplified = true, show = false))
        assertEquals(true, shouldRenderAnchorContent(simplified = false, show = true))
    }

    @Test
    fun nonFloatingPanelFadesWithIconMaterial() {
        assertEquals(
            0f,
            transformPanelAlpha(
                visualAlpha = 1f,
                floating = false,
                anchorAlpha = 1f,
                geometryProgress = 0.5f,
                iconMaterial = 0f,
            ),
        )
        assertEquals(
            0.4f,
            transformPanelAlpha(
                visualAlpha = 0.8f,
                floating = false,
                anchorAlpha = 1f,
                geometryProgress = 0.5f,
                iconMaterial = 0.5f,
            ),
        )
    }

    @Test
    fun floatingPanelReturnsToCapturedAnchorBackground() {
        assertEquals(
            1f,
            transformPanelAlpha(
                visualAlpha = 1f,
                floating = true,
                anchorAlpha = 1f,
                geometryProgress = 0f,
                iconMaterial = 0f,
            ),
        )
        assertEquals(
            0f,
            transformPanelAlpha(
                visualAlpha = 1f,
                floating = true,
                anchorAlpha = 0f,
                geometryProgress = 0f,
                iconMaterial = 0f,
            ),
        )
        assertEquals(
            0.5f,
            transformPanelAlpha(
                visualAlpha = 1f,
                floating = true,
                anchorAlpha = 0f,
                geometryProgress = 0.5f,
                iconMaterial = 0f,
            ),
        )
    }
}
