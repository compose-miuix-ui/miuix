// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.QuotationMarks: VariableSymbol
    get() = _quotationMarks ?: VariableSymbol(
        name = "QuotationMarks",
        category = SymbolCategory.Content,
        encodedData = "Ix:jeUh/b4%Se<g])!<E|~((:JC#HH1PM}q,A9^!=%Zo;[#lX!;[&~o4D%r5sF/#0#{._.M%(pU(w!1v*QD),&V:&*l9Y#b/zrk#p6?hGx%4SB3FI[3qlDgHWG!1@P-%#(WG#P0!#Gf'!!0![Ww5Tau3+,g*t33&d6?h2PSg7!'N&sYQj!z%F:,1d9X#ECO[N%T+Yj14Zi/(P.bQz'!)Qjy:8:x*?ch[]'?e7?9R?5p[^5>1RDL=Z?f-3z@`VC:^y29k_n@Y,*p[x|Q^l0G]C+U7uPA)21P#{.j(;&REa(}QU5DuX:V1:m?R[nT]rQm`H;{M~n*dhb}Wkc*2|@RSI??RgyeYSMy'NEq[y<9:GbQ)'Wd[Y-J?MPx%x!yTLXz9g)>b3Sm=^bN(o9&_d6QX0hM)F]|+5mTP~'^_O'yFBstYyhe:8WOQD]%0)1G:l6ZPv!x%t!iTLXz9g)=b3Sm=^bOUn9~^C6cU^PM)D]l+lk=E~']_G'8FW!",
    ).also { _quotationMarks = it }

private var _quotationMarks: VariableSymbol? = null
