// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.DislikeSelected: VariableSymbol
    get() = _dislikeSelected ?: VariableSymbol(
        name = "DislikeSelected",
        category = SymbolCategory.Content,
        encodedData = "YG};x3|4MFtE_B!V_(*WQ#3mf^N[A.H#|3C!,y'!{&yTR9/(1#?Exwt3Q.3RYu2P1#^+#PZ,jr%~d/jr%~H/C[%~<.pDa#kXJDB_0mr!W'gg!!%!b;!!3&m6Rg/#^U(P9^;..~0,:.z31]A'/!W'Q9'LIGIht3x3%~%kX(ANg_n>BM5h/(ScKtM7!e?=C:F@)-3tN._';HW?xaX.=#;;7/G0jm[VlUOtM14Ed_R>5:&(;KsV_[W[s~_#{JXT=A&ys)#Z3t~b0&pr}T*1nE](#,O/:%sRX~0SoD6?v.8U(#p~^9&*=.=i@<(4`/ss'*d@+tV-KjGh**^U[!e)B_|;>MB#lZ0wYEh)tXrj6F1h9*J8Iu88B'mi*#ua;!g*3m=fpNEuq7Hp#",
    ).also { _dislikeSelected = it }

private var _dislikeSelected: VariableSymbol? = null
