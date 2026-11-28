// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.Timer: VariableSymbol
    get() = _timer ?: VariableSymbol(
        name = "Timer",
        category = SymbolCategory.Core,
        encodedData = "HJN(bU}m<4r/y/hjv9f:{#!G)1fG@#83LJTQ-2D,mi/?trJHlgvUpI@@oR}A#Pp%f9zQfG}#18<7W9v!#bW9AOKJM&d4,y}T~+[',X(!['7G:Mv;s8{m;Qx<@(2`%P_gm#%M0_!)Fj<(2_Rg|'B4zQonNF+79ekp+#O1(TA%QE}%,5}SM7_c?c,_&~}!xh]gK&T!Hi?UjDvKGxaU-l`/,iQ2O!Mn!!8!um|Qt]]4ll|oa9+#8*mXUglhi0~dHhd-^KcYMc#uF61eS>YLsm+To2sRM87psjOCsPz*_qh/R+`PB>L>cmSV0f>.3%Z1?'MEWx`U<_[h8&aP,(LJ7h2,~r|;L7nx?R[+BcwZdu(i+0AS'k+B/QO(aCHu!69?>%n=5~%P_&rEl+nk9Er+GBvi<%^/0/y/3_>:9)4V!S[/E)p+M^2,;&0TaBh?eV<mI~v&)Et;73J4i'bG&FW-WxN:5%l1k>&ITsX*in<0(6rg&%x11Qb)SJ+;43%PL!3%S!D043W.LSyFC?bR_C,u12Z13>3=gyGZS#O.5%Tb#(/D_V3Y1pG;d5(e];G.iVsVxKt/p25M(h^&2@n%T9'h^(tr%~l#SM;;e(-KnUQQS/R,FjQ9O+#s!",
    ).also { _timer = it }

private var _timer: VariableSymbol? = null
