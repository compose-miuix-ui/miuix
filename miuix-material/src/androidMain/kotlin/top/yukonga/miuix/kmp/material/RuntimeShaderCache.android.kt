// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.material

import top.yukonga.miuix.kmp.material.internal.InternalGraphicsResourceApi
import top.yukonga.miuix.kmp.material.internal.graphicsResourceGeneration
import top.yukonga.miuix.kmp.material.internal.graphicsResourcesResetOnResume
import java.lang.ref.WeakReference

@OptIn(InternalGraphicsResourceApi::class)
internal actual fun backdropCacheFor(cache: RuntimeShaderCache): RuntimeShaderCache = if (graphicsResourcesResetOnResume) {
    BackdropShaderCaches.cacheFor(cache)
} else {
    cache
}

private object BackdropShaderCaches {
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
        return BoundCache(cache, entry)
    }

    private class Entry(val original: WeakReference<RuntimeShaderCache>, val generation: Long) {
        val replacement: RuntimeShaderCache by lazy { ReplacementCache() }
    }

    private class BoundCache(val original: RuntimeShaderCache, val entry: Entry) : RuntimeShaderCache {
        override fun obtainRuntimeShader(key: String, string: String): RuntimeShader {
            val cache = if (entry.generation == graphicsResourceGeneration) original else entry.replacement
            return cache.obtainRuntimeShader(key, string)
        }
    }
}

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
