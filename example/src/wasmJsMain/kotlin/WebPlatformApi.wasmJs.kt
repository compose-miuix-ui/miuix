// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

@OptIn(ExperimentalWasmJsInterop::class)
@JsFun(
    """
        function hideLoading() {
            document.getElementById('loading').style.display = 'none';
            document.getElementById('composeApp').style.display = 'block';
        }
    """
)
private external fun hideLoading()

@OptIn(ExperimentalWasmJsInterop::class)
@JsFun(
    """
        function getCssVar(name) {
            const docEl = document.documentElement;
            if (!docEl) return 0.0;
            const style = window.getComputedStyle(docEl);
            if (!style) return 0.0;
            const raw = style.getPropertyValue(name);
            if (!raw) return 0.0;
            const trimmed = raw.trim();
            if (!trimmed) return 0.0;
            const parsed = parseFloat(trimmed);
            return Number.isNaN(parsed) ? 0.0 : parsed;
        }
    """
)
private external fun getCssVar(name: String): Double

@OptIn(ExperimentalWasmJsInterop::class)
@JsFun(
    """
        function isTouchEnabled() {
            if (window.matchMedia && window.matchMedia('(pointer: coarse)').matches) {
                return true;
            }
            if ('ontouchstart' in window) {
                return true;
            }
            const nav = navigator || {};
            const points = (nav.maxTouchPoints || 0) + (nav.msMaxTouchPoints || 0);
            return points > 0;
        }
    """
)
private external fun isTouchEnabled(): Boolean

internal actual fun platformHideLoading() {
    return hideLoading()
}

internal actual fun platformGetCssVar(name: String): Double {
    return getCssVar(name)
}

internal actual fun platformIsTouchEnabled(): Boolean {
    return isTouchEnabled()
}