// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import kotlin.js.ExperimentalWasmJsInterop

@OptIn(ExperimentalWasmJsInterop::class)
@JsFun(
    """
        function hideLoading() {
            if (window.__miuixLoading) {
                window.__miuixLoading.finish();
            } else {
                const el = document.getElementById('loading');
                if (el) el.style.display = 'none';
            }
        }
    """,
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
    """,
)
private external fun getCssVar(name: String): Double

fun platformHideLoading() = hideLoading()

fun platformGetCssVar(name: String): Double = getCssVar(name)
