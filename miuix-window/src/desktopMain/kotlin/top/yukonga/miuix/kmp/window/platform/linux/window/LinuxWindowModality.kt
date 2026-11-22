// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window.platform.linux.window

import java.awt.Dialog
import java.awt.Dialog.ModalExclusionType
import java.awt.Dialog.ModalityType
import java.awt.Window
import java.lang.reflect.Method

/**
 * Checks native input eligibility on the EDT, using AWT's exact blocker when accessible.
 * The public fallback covers the current AppContext and may over-block overlapping modal
 * dialogs because AWT does not expose their blocker ordering through public methods.
 */
internal object LinuxWindowModality {
    private val isModalBlockedMethod: Method? = runCatching {
        val method = Window::class.java.getDeclaredMethod("isModalBlocked")
        if (method.trySetAccessible()) method else null
    }.getOrNull()

    fun isBlocked(window: Window): Boolean {
        val blocked = isModalBlockedMethod?.let { method ->
            runCatching { method.invoke(window) as Boolean }.getOrNull()
        }
        if (blocked != null) return blocked

        val owners = generateSequence(window) { it.owner }.toList()
        val exclusion = owners.maxOf { it.modalExclusionType }
        val dialogs = Window.getWindows().filterIsInstance<Dialog>().filter {
            it.isShowing && it.isModal && it !in owners
        }
        return dialogs.any { dialog ->
            when (dialog.modalityType) {
                ModalityType.MODELESS -> false

                ModalityType.DOCUMENT_MODAL -> {
                    val dialogOwners = generateSequence<Window>(dialog) { it.owner }
                    if (exclusion >= ModalExclusionType.APPLICATION_EXCLUDE) {
                        // Document-modal children still block their excluded owners.
                        dialogOwners.any { it === window }
                    } else {
                        dialogOwners.last() === owners.last()
                    }
                }

                ModalityType.APPLICATION_MODAL -> exclusion < ModalExclusionType.APPLICATION_EXCLUDE

                ModalityType.TOOLKIT_MODAL -> exclusion < ModalExclusionType.TOOLKIT_EXCLUDE
            }
        }
    }
}
