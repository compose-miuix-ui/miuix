// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package lazyfont

import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi
import kotlin.js.ExperimentalWasmJsInterop

@OptIn(ExperimentalWasmJsInterop::class)
@JsFun(
    """
        function fetchTextJs(url, onOk, onErr) {
            fetch(url, { credentials: 'omit' })
                .then(function (r) {
                    if (!r.ok) { onErr('http ' + r.status); return null; }
                    return r.text();
                })
                .then(function (t) { if (t !== null) onOk(t); })
                .catch(function (e) { onErr(String(e)); });
        }
    """,
)
private external fun fetchTextJs(
    url: String,
    onOk: (String) -> Unit,
    onErr: (String) -> Unit,
)

@OptIn(ExperimentalWasmJsInterop::class)
@JsFun(
    """
        function fetchBytesAsBase64Js(url, onOk, onErr) {
            fetch(url, { credentials: 'omit' })
                .then(function (r) {
                    if (!r.ok) { onErr('http ' + r.status); return null; }
                    return r.arrayBuffer();
                })
                .then(function (buf) {
                    if (buf === null) return;
                    var bytes = new Uint8Array(buf);
                    var chunks = [];
                    var step = 0x8000;
                    for (var i = 0; i < bytes.length; i += step) {
                        chunks.push(String.fromCharCode.apply(null, bytes.subarray(i, i + step)));
                    }
                    onOk(btoa(chunks.join('')));
                })
                .catch(function (e) { onErr(String(e)); });
        }
    """,
)
private external fun fetchBytesAsBase64Js(
    url: String,
    onOk: (String) -> Unit,
    onErr: (String) -> Unit,
)

@OptIn(ExperimentalWasmJsInterop::class)
@JsFun(
    """
        function consoleWarnJs(message) {
            if (typeof console !== 'undefined' && console.warn) console.warn(message);
        }
    """,
)
external fun consoleWarn(message: String)

@OptIn(ExperimentalWasmJsInterop::class)
@JsFun(
    """
        function queryParamJs(name) {
            try {
                var p = new URLSearchParams(window.location.search);
                return p.get(name) || '';
            } catch (e) {
                return '';
            }
        }
    """,
)
external fun queryParam(name: String): String

internal suspend fun fetchTextOrNull(url: String): String? = suspendCancellableCoroutine { cont ->
    fetchTextJs(
        url,
        { ok -> if (cont.isActive) cont.resume(ok) },
        { err ->
            if (cont.isActive) {
                consoleWarn("[lazyfont] fetchText failed: $url -> $err")
                cont.resume(null)
            }
        },
    )
}

@OptIn(ExperimentalEncodingApi::class)
internal suspend fun fetchBytesOrNull(url: String): ByteArray? = suspendCancellableCoroutine { cont ->
    fetchBytesAsBase64Js(
        url,
        { b64 ->
            if (cont.isActive) {
                cont.resume(runCatching { Base64.decode(b64) }.getOrNull())
            }
        },
        { err ->
            if (cont.isActive) {
                consoleWarn("[lazyfont] fetchBytes failed: $url -> $err")
                cont.resume(null)
            }
        },
    )
}
