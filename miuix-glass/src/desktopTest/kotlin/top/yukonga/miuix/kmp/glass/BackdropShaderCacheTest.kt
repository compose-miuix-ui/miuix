// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.v2.runComposeUiTest
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.blur.BackdropEffectScope
import top.yukonga.miuix.kmp.blur.drawBackdrop
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
import top.yukonga.miuix.kmp.material.DefaultRuntimeShaderCache
import top.yukonga.miuix.kmp.material.LocalRuntimeShaderCache
import top.yukonga.miuix.kmp.material.RuntimeShader
import top.yukonga.miuix.kmp.material.RuntimeShaderCache
import kotlin.test.Test
import kotlin.test.assertEquals

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

    private class RecordingShaderCache : RuntimeShaderCache {
        private val cache = DefaultRuntimeShaderCache()
        var lastKey: String? = null
            private set

        override fun obtainRuntimeShader(key: String, string: String): RuntimeShader {
            lastKey = key
            return cache.obtainRuntimeShader(key, string)
        }
    }
}
