// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.NextForward: VariableSymbol
    get() = _nextForward ?: VariableSymbol(
        name = "NextForward",
        category = SymbolCategory.Layout,
        encodedData = "Hx^%#Y:7DIDc+;K+1bQP#''/'~p;3K!#'+yPA/PX3;jB>4EPA?Ch_#90]a&!D!>F(~2!2#L(DFP#Rg1!lWTaEjei[;[?iW-4#HA<Z[]SZLMb+Tu]]]^'u?JV1K.cb]P+0h[-G'c>^LKbGP%&*:](1?6%KHW/<!xD<:b%N1NRX-Wp:VZSf)OXu(]h^-4V9To6bn}[Yj`;&<!7v;YE>,?/K+uhC;G1S94!+k8<4!*=_9_EOK}T1LH0>4T!",
    ).also { _nextForward = it }

private var _nextForward: VariableSymbol? = null
