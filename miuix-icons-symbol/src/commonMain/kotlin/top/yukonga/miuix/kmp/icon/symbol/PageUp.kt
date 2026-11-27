// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.PageUp: VariableSymbol
    get() = _pageUp ?: VariableSymbol(
        name = "PageUp",
        category = SymbolCategory.Content,
        encodedData = "+_q#05):O!y#HJPRprQ%Y'>&H#1fwr5#a,8q](a9EPd/>:z'|%ZF(*^8BwV-9b&PC5vz(e{3P'=3mmX#c@y]/1Os!!^97bTSE<8Pz#9T4]}4a+q:S)!^u0U2xs.79pEiz?TV*TuG,<N1LX/==3?QF-v],018%s/R_@4]Y'bCu0o1X?#",
    ).also { _pageUp = it }

private var _pageUp: VariableSymbol? = null
