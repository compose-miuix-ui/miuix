// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav

import top.yukonga.miuix.kmp.nav.internal.NAV_MODULE_NAME
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Scaffold smoke test proving the commonTest + kotlin.test toolchain runs on `:miuix-nav:desktopTest`.
 *
 * The repository previously had zero automated tests; this asserts only the internal module marker
 * so it stays green independent of the navigation API that lands in later phases. Phase 2 builds the
 * real reconciler unit tests on top of this same infrastructure.
 */
class ScaffoldSmokeTest {
    @Test
    fun moduleMarkerIsPresent() {
        assertEquals("miuix-nav", NAV_MODULE_NAME)
    }

    @Test
    fun testFrameworkIsWired() {
        assertTrue(NAV_MODULE_NAME.isNotEmpty())
    }
}
