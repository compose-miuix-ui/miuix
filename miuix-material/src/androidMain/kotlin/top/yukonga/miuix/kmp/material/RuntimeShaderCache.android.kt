// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.material

import top.yukonga.miuix.kmp.material.internal.InternalGraphicsResourceApi
import top.yukonga.miuix.kmp.material.internal.graphicsResourceGeneration
import top.yukonga.miuix.kmp.material.internal.graphicsResourcesResetOnResume
import java.lang.ref.WeakReference

@OptIn(InternalGraphicsResourceApi::class)
internal actual fun graphicsResourceCacheFor(cache: RuntimeShaderCache): RuntimeShaderCache = if (graphicsResourcesResetOnResume) {
    GraphicsResourceShaderCaches.cacheFor(cache)
} else {
    cache
}

@OptIn(InternalGraphicsResourceApi::class)
private object GraphicsResourceShaderCaches {
    private val entries = mutableListOf<Entry>()

    @Synchronized
    fun cacheFor(cache: RuntimeShaderCache): RuntimeShaderCache {
        var match: Entry? = null
        val iterator = entries.iterator()
        while (iterator.hasNext()) {
            val entry = iterator.next()
            val original = entry.original.get()
            if (original == null) {
                iterator.remove()
            } else if (original === cache) {
                match = entry
            }
        }
        val entry = match ?: Entry(WeakReference(cache), graphicsResourceGeneration).also { entries.add(it) }
        return entry.boundCache?.get() ?: BoundCache(cache, entry.generation).also {
            entry.boundCache = WeakReference(it)
        }
    }

    private class Entry(val original: WeakReference<RuntimeShaderCache>, val generation: Long) {
        // Consumers own the wrapper and its replacement. The registry only remembers the original generation.
        var boundCache: WeakReference<BoundCache>? = null
    }

    private class BoundCache(val original: RuntimeShaderCache, val generation: Long) : RuntimeShaderCache {
        private val replacement by lazy { ReplacementCache() }

        override fun obtainRuntimeShader(key: String, string: String): RuntimeShader {
            val cache = if (generation == graphicsResourceGeneration) original else replacement
            return cache.obtainRuntimeShader(key, string)
        }
    }
}

@OptIn(InternalGraphicsResourceApi::class)
private class ReplacementCache : RuntimeShaderCache {
    private val shaders = mutableMapOf<String, MutableMap<String, RuntimeShader>>()
    private var generation = graphicsResourceGeneration

    @Synchronized
    override fun obtainRuntimeShader(key: String, string: String): RuntimeShader {
        val currentGeneration = graphicsResourceGeneration
        if (generation != currentGeneration) {
            shaders.clear()
            generation = currentGeneration
        }
        return shaders.getOrPut(key) { mutableMapOf() }.getOrPut(string) { RuntimeShader(string) }
    }
}
