// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.material

import top.yukonga.miuix.kmp.material.internal.graphicsResourceGeneration
import top.yukonga.miuix.kmp.material.internal.graphicsResourcesResetOnResume
import java.lang.ref.WeakReference

internal actual fun backdropCacheFor(cache: RuntimeShaderCache): RuntimeShaderCache = if (graphicsResourcesResetOnResume) {
    BackdropShaderCaches.cacheFor(cache)
} else {
    cache
}

private object BackdropShaderCaches {
    private val entries = mutableListOf<Entry>()

    @Synchronized
    fun cacheFor(cache: RuntimeShaderCache): RuntimeShaderCache {
        val generation = graphicsResourceGeneration
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
        if (match == null) {
            entries.add(Entry(WeakReference(cache), generation))
            return cache
        }
        if (match.generation == generation) return cache
        return match.replacement ?: ReplacementCache().also { match.replacement = it }
    }

    private class Entry(val original: WeakReference<RuntimeShaderCache>, val generation: Long) {
        var replacement: RuntimeShaderCache? = null
    }
}

private const val MAX_REPLACEMENT_SHADERS = 32

private class ReplacementCache : RuntimeShaderCache {
    private val shaders = LinkedHashMap<Pair<String, String>, RuntimeShader>()
    private var generation = graphicsResourceGeneration

    @Synchronized
    override fun obtainRuntimeShader(key: String, string: String): RuntimeShader {
        val currentGeneration = graphicsResourceGeneration
        if (generation != currentGeneration) {
            shaders.clear()
            generation = currentGeneration
        }
        val shaderKey = key to string
        // Reinsert hits so that eviction drops the least recently used shader.
        val shader = shaders.remove(shaderKey) ?: RuntimeShader(string)
        shaders[shaderKey] = shader
        if (shaders.size > MAX_REPLACEMENT_SHADERS) shaders.remove(shaders.keys.first())
        return shader
    }
}
