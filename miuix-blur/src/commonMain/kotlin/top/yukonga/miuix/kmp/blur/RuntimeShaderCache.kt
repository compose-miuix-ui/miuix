// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.blur

import androidx.compose.runtime.staticCompositionLocalOf
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.SynchronizedObject
import kotlinx.coroutines.internal.synchronized

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

@OptIn(InternalCoroutinesApi::class)
internal class RuntimeShaderCacheImpl : RuntimeShaderCache {

    private val lock = SynchronizedObject()
    private val runtimeShaders = mutableMapOf<String, RuntimeShader>()

    override fun obtainRuntimeShader(key: String, string: String): RuntimeShader = synchronized(lock) {
        runtimeShaders.getOrPut(key) { RuntimeShader(string) }
    }
}
