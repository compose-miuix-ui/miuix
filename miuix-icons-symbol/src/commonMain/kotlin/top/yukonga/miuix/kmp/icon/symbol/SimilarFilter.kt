// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.SimilarFilter: VariableSymbol
    get() = _similarFilter ?: VariableSymbol(
        name = "SimilarFilter",
        category = SymbolCategory.Layout,
        encodedData = "'PI#>.!l!G!5#Y%#WC9j*!>!9%P,V{M%*_@!M%Q9d!M%WGRgM(1IiD6(](`gE&*y%P0!.#h'kN8#{o.!8#:.C!8#=5jrf%*6FJ*%oRprb#U{Rg)!V!4#z-?!a,#!1!d!&~&!=!#y_U0!oT#P!w//**H[8#|:XgJ'vG_R,8p(cQG024n(#wvEs++,4(;:XmR&6?:F,8's>%}%55!Vl6,]c)[.5UVLKbWPL?MQY'ZJjQ5%{VV-Tx4Pi)oXhh4,')gPuGq0k7?s5(^LjbvV9iwFl01:5(^Lz)YZZxjQD5BsS&Y>z)YZMmY%m7Fpo=]Lhb*WMmZR?4C7=(?M_:X-[xfQ?4[p,l=?bKHZ4iDul7Gp,lCMYhu)Y[_RC52:ml>M!",
    ).also { _similarFilter = it }

private var _similarFilter: VariableSymbol? = null
