// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.ChevronForward: VariableSymbol
    get() = _chevronForward ?: VariableSymbol(
        name = "ChevronForward",
        category = SymbolCategory.Core,
        encodedData = "+_q#05@E@#O67;W'NjC>X-waqR<MV[H%?6[dV-p9r)#;BFu,J?.~(*a82w;.C!E=t,LNeq^NFFe0_u8TnK!hS,*&-(X4Msa'x1ZVs&N#CtG0Hb`'TL-b]QX5003,e_k>(B2]G-.wC/N1w!?;m77pT;z?TV*T*E,<N1k<l>;Ml[H*?_!",
    ).also { _chevronForward = it }

private var _chevronForward: VariableSymbol? = null
