// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.core

import androidx.compose.runtime.Composable
import top.yukonga.miuix.kmp.nav.transition.NavTransition
import kotlin.reflect.KClass

/**
 * Metadata key under which a per-route [NavTransition] override is stored on a built [NavEntry].
 *
 * The renderer reads this key from [NavEntry.metadata]; a `null`/absent value means the entry
 * inherits the global transition passed to [NavDisplay].
 */
@PublishedApi
internal const val NAV_TRANSITION_METADATA_KEY: String = "top.yukonga.miuix.kmp.nav.transition"

/**
 * DSL receiver of the [NavDisplay] content lambda.
 *
 * Each `entry<T> { ... }` call registers, keyed by the route's runtime [KClass], how to build a
 * [NavEntry] for keys of that type. [build] materializes the registrations into a lookup function.
 */
class NavEntryBuilder {
    private val factories: MutableMap<KClass<*>, (NavKey) -> NavEntry<*>> = mutableMapOf()

    /**
     * Registers how to render keys of type [T].
     *
     * @param contentKey value-stable identity for the produced entry; when `null` the key instance
     *   itself is used (data classes / data objects give stable equality out of the box).
     * @param transition per-route transition override; `null` inherits [NavDisplay]'s global
     *   transition (see design §6.4). Stored in the entry's metadata.
     * @param metadata extra per-entry metadata, merged with the transition override.
     * @param content the composable rendering a key of type [T].
     */
    inline fun <reified T : NavKey> entry(
        contentKey: Any? = null,
        transition: NavTransition? = null,
        metadata: Map<String, Any> = emptyMap(),
        noinline content: @Composable (T) -> Unit,
    ) {
        register(T::class) { key ->
            @Suppress("UNCHECKED_CAST")
            val typed = key as T
            val mergedMetadata =
                if (transition != null) metadata + (NAV_TRANSITION_METADATA_KEY to transition) else metadata
            NavEntry(
                key = typed,
                contentKey = contentKey ?: typed,
                metadata = mergedMetadata,
                content = content,
            )
        }
    }

    /**
     * Low-level registration hook used by the [entry] inline function. Public only so the inline
     * reified [entry] can call it across the inline boundary; not intended for direct use.
     */
    @PublishedApi
    internal fun register(clazz: KClass<*>, factory: (NavKey) -> NavEntry<*>) {
        factories[clazz] = factory
    }

    /**
     * Materializes the accumulated registrations into an entry lookup. Throws if a key has no
     * registered [entry] of its (or a superclass') type.
     */
    @PublishedApi
    internal fun build(): (NavKey) -> NavEntry<*> = { key ->
        val factory = factories[key::class]
            ?: error("No entry { } registered for ${key::class.simpleName ?: key::class}")
        factory(key)
    }
}

/**
 * Materializes a [NavEntryBuilder] DSL block into an entry lookup function.
 *
 * The [NavDisplay] DSL overloads call this internally; the low-level [NavDisplay] overload accepts a
 * prebuilt lookup of this exact shape.
 */
internal fun entryProvider(builder: NavEntryBuilder.() -> Unit): (NavKey) -> NavEntry<*> = NavEntryBuilder().apply(builder).build()
