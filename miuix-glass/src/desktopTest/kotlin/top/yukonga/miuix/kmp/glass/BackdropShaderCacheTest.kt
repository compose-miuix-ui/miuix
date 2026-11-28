// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.v2.runComposeUiTest
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.blur.BackdropEffectScope
import top.yukonga.miuix.kmp.blur.drawBackdrop
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
import top.yukonga.miuix.kmp.glass.internal.GLASS_COLOR_BLEND_SHADER_KEY
import top.yukonga.miuix.kmp.material.DefaultRuntimeShaderCache
import top.yukonga.miuix.kmp.material.GlassColorBlendMode
import top.yukonga.miuix.kmp.material.GlassColorLayer
import top.yukonga.miuix.kmp.material.GlassDefaults
import top.yukonga.miuix.kmp.material.GlassMaterial
import top.yukonga.miuix.kmp.material.GlassShape
import top.yukonga.miuix.kmp.material.LocalRuntimeShaderCache
import top.yukonga.miuix.kmp.material.RuntimeShader
import top.yukonga.miuix.kmp.material.RuntimeShaderCache
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalTestApi::class)
class BackdropShaderCacheTest {
    @Test
    fun replacingCacheKeepsEffectStateObserved() = runComposeUiTest {
        val firstCache = RecordingShaderCache()
        val replacement = RecordingShaderCache()
        val cache = mutableStateOf<RuntimeShaderCache>(firstCache)
        val effectValue = mutableIntStateOf(1)
        val effects: BackdropEffectScope.() -> Unit = {
            obtainRuntimeShader(
                "effect-${effectValue.intValue}",
                "half4 main(float2 coord) { return half4(1.0); }",
            )
        }

        setContent {
            CompositionLocalProvider(LocalRuntimeShaderCache provides cache.value) {
                Box(Modifier.size(96.dp).drawBackdrop(rememberLayerBackdrop(), { RectangleShape }, effects))
            }
        }

        onRoot().captureToImage()
        runOnIdle {
            assertEquals("effect-1", firstCache.lastKey)
            cache.value = replacement
        }
        onRoot().captureToImage()
        runOnIdle {
            assertEquals("effect-1", replacement.lastKey)
            effectValue.intValue = 2
        }
        onRoot().captureToImage()
        runOnIdle { assertEquals("effect-2", replacement.lastKey) }
    }

    @Test
    fun replacingCacheRebuildsGlassMaterialAndUnderlayEffects() = runComposeUiTest {
        val firstCache = RecordingShaderCache()
        val replacement = RecordingShaderCache()
        val cache = mutableStateOf<RuntimeShaderCache>(firstCache)
        val material = GlassMaterial(0.dp, GlassColorLayer(Color.Red, GlassColorBlendMode.SrcOver))
        val underlay = GlassMaterial(0.dp, GlassColorLayer(Color.Blue, GlassColorBlendMode.SrcOver))

        setContent {
            val backdrop = rememberLayerBackdrop()
            CompositionLocalProvider(LocalRuntimeShaderCache provides cache.value) {
                Box(
                    Modifier.size(96.dp).glassOnActionBar(
                        backdrop = backdrop,
                        shape = GlassShape(16.dp),
                        style = GlassDefaults.Style,
                        alpha = 1f,
                        material = material,
                        underlayMaterial = underlay,
                        stroke = null,
                    ),
                )
            }
        }

        val blendKeys = setOf(GLASS_COLOR_BLEND_SHADER_KEY, "${GLASS_COLOR_BLEND_SHADER_KEY}ActionBar")
        onRoot().captureToImage()
        runOnIdle {
            assertTrue(firstCache.keys.containsAll(blendKeys))
            cache.value = replacement
        }
        onRoot().captureToImage()
        runOnIdle { assertTrue(replacement.keys.containsAll(blendKeys)) }
    }

    private class RecordingShaderCache : RuntimeShaderCache {
        private val cache = DefaultRuntimeShaderCache()
        val keys = mutableSetOf<String>()
        var lastKey: String? = null
            private set

        override fun obtainRuntimeShader(key: String, string: String): RuntimeShader {
            keys.add(key)
            lastKey = key
            return cache.obtainRuntimeShader(key, string)
        }
    }
}
