// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.material

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.referentialEqualityPolicy
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.SynchronizedObject
import kotlinx.coroutines.internal.synchronized
import top.yukonga.miuix.kmp.material.internal.InternalGraphicsResourceApi
import top.yukonga.miuix.kmp.material.internal.graphicsResourceGeneration

/**
 * Cache for compiled [RuntimeShader] instances, avoiding recompilation each frame.
 */
interface RuntimeShaderCache {

    /**
     * Returns a cached [RuntimeShader] for the given [key], compiling from [string] if not yet cached.
     *
     * @param key The cache key identifying the compiled [RuntimeShader].
     * @param string The AGSL/SkSL shader source compiled on a cache miss.
     */
    fun obtainRuntimeShader(key: String, string: String): RuntimeShader
}

/**
 * CompositionLocal providing a shared [RuntimeShaderCache] within the composition tree.
 *
 * All backdrop effect scopes within the same composition share this cache,
 * avoiding redundant shader compilation while allowing the cache to be
 * garbage-collected when the composition is disposed.
 * After a graphics resource reset, backdrop effects and shader-backed squircles use a
 * Miuix-managed replacement for each custom cache previously used by them.
 */
val LocalRuntimeShaderCache = compositionLocalOf<RuntimeShaderCache>(referentialEqualityPolicy()) {
    DefaultRuntimeShaderCache()
}

/** Internal implementation shared by Miuix backdrop effects. */
@InternalGraphicsResourceApi
fun RuntimeShaderCache.forBackdropEffects(): RuntimeShaderCache = forGraphicsResources()

/** Uses the provided cache until its graphics resources become stale. */
@InternalGraphicsResourceApi
fun RuntimeShaderCache.forGraphicsResources(): RuntimeShaderCache = if (this is DefaultRuntimeShaderCache) this else graphicsResourceCacheFor(this)

internal expect fun graphicsResourceCacheFor(cache: RuntimeShaderCache): RuntimeShaderCache

@OptIn(InternalCoroutinesApi::class, InternalGraphicsResourceApi::class)
class DefaultRuntimeShaderCache : RuntimeShaderCache {

    private val lock = SynchronizedObject()
    private val runtimeShaders = mutableMapOf<String, RuntimeShader>()
    private var resetGeneration = 0L

    override fun obtainRuntimeShader(key: String, string: String): RuntimeShader = synchronized(lock) {
        // The registry keeps advancing while the host lives, including gaps with no nodes.
        invalidate(graphicsResourceGeneration)
        runtimeShaders.getOrPut(key) { RuntimeShader(string) }
    }

    private fun invalidate(generation: Long) {
        if (resetGeneration != generation) {
            runtimeShaders.clear()
            resetGeneration = generation
        }
    }
}
