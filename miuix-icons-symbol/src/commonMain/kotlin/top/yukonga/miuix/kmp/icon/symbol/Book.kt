// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.Book: VariableSymbol
    get() = _book ?: VariableSymbol(
        name = "Book",
        category = SymbolCategory.Core,
        encodedData = "lr`R|aVFlVk^p+dwH<2!w%l[%P,!dA!!47Yp+Dl#5z?!4a0myPJ5j^8-WBt;iWFJ)PP0iDj~O+Mj.CK7F:f=#P?!Un;[@PP?k%[#6:3~DH/?1!D0/?4Pm(XiM#E!##+l'!)AE^KJk:.7=phhI'{#Hu!!C1:_&r0t.+s!1%yu-!]#!5M7_R`N?h!)/B#i|.f(cQc41nxZ!&(;G/:,E@W`ZPh&G;eH`B9mgPb+PiN2n6qhB.j~v%q8}(p%c9;tK.l|2FA.e~r%Z:>B3j>1Ji>2GC3s}&iS}@J^gmrUZTSG59BpZjQ7PV3VeN:s5&fJgRu?FVEVUM[Gi0nX`h'!6!p!-(VQW/d/:..~6&WM@<{3d~v%_;x3&P4!G)2VJQBs[.P+[g+!o+Y<i;{yV^G.uhE/p+SgP#SE1)RQq]a/;.&~'!#&YQ;.Wx-%U9R.%!6%l!3&l6ePX'9?<P%!-!Xm'Wm()iY,",
    ).also { _book = it }

private var _book: VariableSymbol? = null
