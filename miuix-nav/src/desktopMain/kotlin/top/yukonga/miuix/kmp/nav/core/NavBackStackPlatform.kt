// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.core

import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.serializer

@InternalSerializationApi
internal actual fun navKeyClassName(key: NavKey): String = key::class.java.name

@InternalSerializationApi
@Suppress("UNCHECKED_CAST")
internal actual fun navKeySerializerByName(className: String): KSerializer<NavKey> =
    Class.forName(className).kotlin.serializer() as KSerializer<NavKey>
