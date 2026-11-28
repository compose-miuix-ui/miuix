// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.LoanPromotion: VariableSymbol
    get() = _loanPromotion ?: VariableSymbol(
        name = "LoanPromotion",
        category = SymbolCategory.Content,
        encodedData = "'~d!x:]4!,O'S(LyK+WgK!a%jrK!q,zaK!a%B8[;>5-!l;#!B8K;O2sDA#WG7!XTkDgg`%D8K;d6-!/po%q9%=4VP`iD)P(*p#cFT`|A8!Y3zAO={?cUNPDf^1.2bM8!|~Fj(iW3CMdgr*_4|Q:!Uh;+JT{FY4`U9#o=R.(~gN:sf&v],0P+}jSlT>PViZ,wosR22)7R&4phH*?_0]a4I4dQ4!&]HT51T#@]T+a;A;=1S9U0d)FSE!vKEST<nQ&0MMs&GCRs=(:_?h]'.e[;z,FyxWVQGt%",
    ).also { _loanPromotion = it }

private var _loanPromotion: VariableSymbol? = null
