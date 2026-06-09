// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.core

import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException

@InternalSerializationApi
internal actual fun navKeyClassName(key: NavKey): String =
    key::class.qualifiedName
        ?: throw SerializationException("Cannot determine class name for anonymous NavKey: $key")

@InternalSerializationApi
@Suppress("UNCHECKED_CAST")
internal actual fun navKeySerializerByName(className: String): KSerializer<NavKey> =
    throw UnsupportedOperationException(
        "NavBackStack process-death restoration is not supported on this platform. " +
            "Class name lookup requires JVM reflection ($className)."
    )
