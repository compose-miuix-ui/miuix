// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.serializer

/**
 * The navigation back stack: a [SnapshotStateList] of [NavKey].
 *
 * This is the public type name; callers may operate the list directly (add/removeAt/...) or via a
 * [NavController]. Persistence across configuration changes and process death is provided by
 * [rememberNavBackStack] + [NavBackStackSaver].
 */
public typealias NavBackStack = SnapshotStateList<NavKey>

/**
 * Non-composable constructor for a [NavBackStack], primarily for tests and off-composition setup.
 */
public fun navBackStackOf(vararg elements: NavKey): NavBackStack =
    elements.toList().toMutableStateList()

/** Json instance for key serialization. */
internal val NavBackStackJson: Json = Json { ignoreUnknownKeys = true }

/**
 * Platform-specific: returns a binary class name string usable by [navKeySerializerByName] to
 * reconstruct the serializer on restoration.
 *
 * JVM: returns `KClass.java.name` (uses `$` separators for nested classes).
 * Other platforms: returns `KClass.qualifiedName` (only top-level classes are restorable).
 */
@InternalSerializationApi
internal expect fun navKeyClassName(key: NavKey): String

/**
 * Platform-specific: given the class name produced by [navKeyClassName], returns the
 * [KSerializer] for that class so the JSON data element can be deserialized.
 */
@InternalSerializationApi
internal expect fun navKeySerializerByName(className: String): KSerializer<NavKey>

/**
 * Encodes a single [NavKey] to a JSON string containing a `{ "type": "<className>", "data": ... }`
 * envelope. The key's concrete class must be `@Serializable`.
 *
 * Non-`@Serializable` keys surface a `SerializationException` (design spec §12 known risk).
 */
@OptIn(InternalSerializationApi::class)
internal fun encodeNavKey(key: NavKey): String {
    @Suppress("UNCHECKED_CAST")
    val serializer = key::class.serializer() as KSerializer<NavKey>
    val dataElement = NavBackStackJson.encodeToJsonElement(serializer, key)
    val envelope: JsonObject = buildJsonObject {
        put("type", JsonPrimitive(navKeyClassName(key)))
        put("data", dataElement)
    }
    return NavBackStackJson.encodeToString(JsonObject.serializer(), envelope)
}

/**
 * Restores a [NavKey] from an envelope produced by [encodeNavKey].
 */
@OptIn(InternalSerializationApi::class)
internal fun decodeNavKey(encoded: String): NavKey {
    val envelope = NavBackStackJson.decodeFromString(JsonObject.serializer(), encoded)
    val typeName = envelope["type"]!!.jsonPrimitive.content
    val dataElement = envelope["data"]!!
    val serializer = navKeySerializerByName(typeName)
    return NavBackStackJson.decodeFromJsonElement(serializer, dataElement)
}

/**
 * [Saver] that serializes each [NavKey] to a JSON envelope string and restores it.
 *
 * Each key is encoded as `{"type":"<className>","data":{...}}`. Keys must be `@Serializable`;
 * non-serializable keys surface a `SerializationException` rather than silently dropping data
 * (design spec §12 known risk).
 */
internal val NavBackStackSaver: Saver<NavBackStack, List<String>> = Saver(
    save = { stack -> stack.map { encodeNavKey(it) } },
    restore = { saved -> saved.map { decodeNavKey(it) }.toMutableStateList() },
)

/**
 * Remembers a [NavBackStack] seeded with [elements], persisted via [rememberSaveable] and
 * [NavBackStackSaver].
 *
 * ```kotlin
 * val backStack = rememberNavBackStack(Route.Home)
 * ```
 */
@Composable
public fun rememberNavBackStack(vararg elements: NavKey): NavBackStack =
    rememberSaveable(saver = NavBackStackSaver) {
        elements.toList().toMutableStateList()
    }
