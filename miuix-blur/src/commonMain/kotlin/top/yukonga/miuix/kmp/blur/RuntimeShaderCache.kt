// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.blur

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf

/**
 * Cache for compiled [RuntimeShader] instances, avoiding recompilation each frame.
 */
sealed interface RuntimeShaderCache {

    /**
     * Returns a cached [RuntimeShader] for the given [key], compiling from [string] if not yet cached.
     */
    fun obtainRuntimeShader(key: String, string: String): RuntimeShader
}

/**
 * CompositionLocal providing a shared [RuntimeShaderCache] within the composition tree.
 *
 * All backdrop effect scopes within the same composition share this cache,
 * avoiding redundant shader compilation while allowing the cache to be
 * garbage-collected when the composition is disposed.
 */
val LocalRuntimeShaderCache = staticCompositionLocalOf<RuntimeShaderCache> {
    RuntimeShaderCacheImpl()
}

/**
 * Remembers a [RuntimeShaderCache] instance scoped to the current composition.
 */
@Composable
fun rememberRuntimeShaderCache(): RuntimeShaderCache = remember { RuntimeShaderCacheImpl() }

internal class RuntimeShaderCacheImpl : RuntimeShaderCache {

    private val lock = Any()
    private val runtimeShaders = mutableMapOf<String, RuntimeShader>()

    override fun obtainRuntimeShader(key: String, string: String): RuntimeShader = synchronized(lock) {
        runtimeShaders.getOrPut(key) { RuntimeShader(string) }
    }
}
