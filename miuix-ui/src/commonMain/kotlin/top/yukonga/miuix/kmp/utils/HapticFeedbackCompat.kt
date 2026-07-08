// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.utils

import androidx.compose.ui.hapticfeedback.HapticFeedback
import androidx.compose.ui.hapticfeedback.HapticFeedbackType

/**
 * The runtime Android SDK level, or `null` on platforms that do not gate
 * [HapticFeedbackType] behind an API level (iOS, Desktop, Web).
 *
 * Used by [performHapticFeedbackCompat] to decide whether a requested haptic type has to
 * be downgraded to one the current device can actually play.
 */
internal expect fun hapticFeedbackApiLevel(): Int?

/**
 * Resolves [type] to the closest haptic type that is actually supported on a device
 * running [apiLevel], so feedback still fires below the type's introduction API instead of
 * silently doing nothing.
 *
 * When [apiLevel] is `null` (non-Android platforms, which do not have this API gating) the
 * [type] is returned unchanged. The fallback anchors are [HapticFeedbackType.TextHandleMove]
 * (API 27) and [HapticFeedbackType.ContextClick] (API 23); both play down to the library's
 * `minSdk`, so the result is always something the device can render.
 */
internal fun resolveHapticFeedbackType(
    type: HapticFeedbackType,
    apiLevel: Int?,
): HapticFeedbackType {
    if (apiLevel == null) return type
    return when (type) {
        // Firm feedback introduced in API 34 -> a crisp click available since API 23.
        HapticFeedbackType.ToggleOn,
        HapticFeedbackType.ToggleOff,
        HapticFeedbackType.GestureThresholdActivate,
        ->
            if (apiLevel >= 34) type else HapticFeedbackType.ContextClick

        // Light tick introduced in API 34 -> the API 27 tick when available, else a click.
        HapticFeedbackType.SegmentTick ->
            when {
                apiLevel >= 34 -> type
                apiLevel >= 27 -> HapticFeedbackType.TextHandleMove
                else -> HapticFeedbackType.ContextClick
            }

        // Confirmation introduced in API 30 -> a crisp click below API 30.
        HapticFeedbackType.Confirm ->
            if (apiLevel >= 30) type else HapticFeedbackType.ContextClick

        // Tick introduced in API 27 -> a crisp click below API 27.
        HapticFeedbackType.TextHandleMove ->
            if (apiLevel >= 27) type else HapticFeedbackType.ContextClick

        // ContextClick (API 23) and anything else already play on the library's minSdk.
        else -> type
    }
}

/**
 * Performs [hapticFeedbackType], first downgrading it to a type the current device can
 * actually play.
 *
 * Compose forwards haptic types straight to the platform without any API-level fallback, so
 * newer constants (toggles, ticks, gesture thresholds, confirm) silently no-op on Android
 * versions below their introduction API. This wrapper keeps feedback working across the
 * library's whole supported range; on non-Android platforms it behaves exactly like
 * [HapticFeedback.performHapticFeedback].
 */
fun HapticFeedback.performHapticFeedbackCompat(hapticFeedbackType: HapticFeedbackType) {
    performHapticFeedback(resolveHapticFeedbackType(hapticFeedbackType, hapticFeedbackApiLevel()))
}
