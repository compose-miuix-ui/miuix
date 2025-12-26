// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import kotlinx.browser.document

actual fun platformHideLoading() {
    val loading = document.getElementById("loading")
    val composeApp = document.getElementById("composeApp")
    loading?.asDynamic().style?.display = "none"
    composeApp?.asDynamic().style?.display = "block"
}

actual fun platformGetCssVar(name: String): Double {
    val docEl = document.documentElement ?: return 0.0
    val style = docEl.ownerDocument?.defaultView?.getComputedStyle(docEl) ?: return 0.0
    val raw = style.getPropertyValue(name)
    val trimmed = raw.trim()
    val direct = trimmed.toDoubleOrNull()
    if (direct != null) return direct
    val px = trimmed.removeSuffix("px").toDoubleOrNull()
    return px ?: 0.0
}

actual fun platformIsTouchEnabled(): Boolean {
    val hasCoarse = js("window.matchMedia && window.matchMedia('(pointer: coarse)').matches") as? Boolean ?: false
    if (hasCoarse) return true

    val hasOnTouchStart = js("'ontouchstart' in window") as? Boolean ?: false
    if (hasOnTouchStart) return true

    val maxTouchPoints = (js("navigator.maxTouchPoints") as? Int ?: 0) +
        (js("navigator.msMaxTouchPoints") as? Int ?: 0)
    return maxTouchPoints > 0
}
