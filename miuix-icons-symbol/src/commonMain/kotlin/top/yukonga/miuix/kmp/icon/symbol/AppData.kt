// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.AppData: VariableSymbol
    get() = _appData ?: VariableSymbol(
        name = "AppData",
        category = SymbolCategory.Content,
        encodedData = "fdQQu3#)*CJq+P?#T(q]zAA.R9EqB!h'w:H#`/:.5:i#|U_P[&-9ie&!;?%~Y&`?bj2?p9l#I1!!Q(k_P}5MI[;#Z('O:.](B#g-;[X4qU+&3DF:'*L[<i+1Q9ATU>KKnXEjU9~'_.:Q/![1z<d;5KuV.ZWtN1313jhGF#)-3t+/.7]jR9iNB#n.3*T[M~,>wF')i!'SC5wJjTxK]]t,.s!",
    ).also { _appData = it }

private var _appData: VariableSymbol? = null
