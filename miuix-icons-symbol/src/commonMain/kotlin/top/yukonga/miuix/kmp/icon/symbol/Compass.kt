// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.Compass: VariableSymbol
    get() = _compass ?: VariableSymbol(
        name = "Compass",
        category = SymbolCategory.Layout,
        encodedData = "IJJQO+!Sok~=[Q&&vIDyg*zaLPA(;[u!=5.Pw~A(fMtSHJ7!yT%!fM8TkD9~A#FJ7!yT|A9~A#a-@]0,(!,y@}X9Y#k=uDCwedzgE0ObDSGqN[c~<'B:3&N.)QC#{>H#PJ9Qp%}0JN,yeUPPpCkr,~vGKk*!C!5^*~2!He<o2!{Rcj:FP.(~W+>:/%<!~bmXoU2R94oRPhd;s('Pa#4]X''&QQ|.Wx.RV9n9k#o(zQt,g.3SB<?!z#28Z./%c9a#F)K!i#_(K!;;+1JPB&f=_.c)LXbQ_E8@WSE!G5^X~=&&;;=1x=DT3E)~:1C/U&F!l/F*DMC;D0%41#M0D4PRu6t/I1vu-=%=K?PR/BqP['~Gq<+1JPB&f=_.c)LX7h-1d9#!b(b9#!(",
    ).also { _compass = it }

private var _compass: VariableSymbol? = null
