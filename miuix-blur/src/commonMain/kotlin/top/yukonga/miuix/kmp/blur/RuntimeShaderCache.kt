// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.blur

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
 * Global shader cache shared across all backdrop effect scopes.
 * Shader compilation is expensive (especially on GPU), so compiled shaders
 * are retained for the lifetime of the process.
 */
internal object GlobalRuntimeShaderCache : RuntimeShaderCache {

    private val lock = Any()
    private val runtimeShaders = mutableMapOf<String, RuntimeShader>()

    override fun obtainRuntimeShader(key: String, string: String): RuntimeShader = synchronized(lock) {
        runtimeShaders.getOrPut(key) { RuntimeShader(string) }
    }
}
