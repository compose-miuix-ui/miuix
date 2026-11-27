// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.Back: VariableSymbol
    get() = _back ?: VariableSymbol(
        name = "Back",
        category = SymbolCategory.Core,
        encodedData = "Hx1jWl!GS9|A;~Y%#P!!_(5MRg)!VQX](~k7%~o!@&z9#!=31)#VtDe#++A_#h#'_46Pb[fQ!%&3]ik==[X'`XOh%!TC?/U!3-u008<)6URL7b5V1w)E)V|oa'[SO?cRPTGt&).KvU0DPyxZw%m!#),bl!}#kQcFw,u?XmPE=h?Pp#~jn>a&s)rYjr%)b9(!.rQ91uP>fJqRSJ9Q`(rQj1j60h/*(]DVeYaq7t_-ibrToIB*g0/pM%",
    ).also { _back = it }

private var _back: VariableSymbol? = null
