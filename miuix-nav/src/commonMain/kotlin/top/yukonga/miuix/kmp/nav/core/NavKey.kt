// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.core

/**
 * Marker interface for navigation destination keys.
 *
 * A [NavKey] is a pure tag carrying no behavior. User route hierarchies implement it and should
 * annotate the hierarchy with `@Serializable` (kotlinx.serialization) so the back stack can be
 * persisted across configuration changes and process death via [rememberNavBackStack].
 *
 * ```kotlin
 * @Serializable
 * sealed interface Route : NavKey {
 *     @Serializable data object Home : Route
 *     @Serializable data class Detail(val id: String) : Route
 * }
 * ```
 *
 * `@Serializable` is a hard requirement when the stack is persisted via [rememberNavBackStack]: a
 * non-serializable key type throws `SerializationException` at the first composition (serializer
 * capture), and a key escaping the captured hierarchy throws at state-save time. Stacks built with
 * [navBackStackOf] are in-memory only and place no serializability requirement on keys.
 */
public interface NavKey
