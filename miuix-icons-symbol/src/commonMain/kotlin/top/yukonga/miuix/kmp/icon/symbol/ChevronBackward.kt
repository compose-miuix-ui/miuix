// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.ChevronBackward: VariableSymbol
    get() = _chevronBackward ?: VariableSymbol(
        name = "ChevronBackward",
        category = SymbolCategory.Core,
        encodedData = "+_q#:7n.~!1,iDb(J[3:H(@4G*GJ9Qd/pUC!A(iuY!I%@6Sd;.}(hWW9Q%_'q6[gX-LNTqw3IP[&kP^UnD+/CVv3K/f03B3>GO3%L(q`]0l*N?F:01t~;&h4fnE-.wC/N1j<Ui&4V]8*v]Hu.8G?d&8!k<l>;Ml[H*.b}Ps+{<6=_@!",
    ).also { _chevronBackward = it }

private var _chevronBackward: VariableSymbol? = null
