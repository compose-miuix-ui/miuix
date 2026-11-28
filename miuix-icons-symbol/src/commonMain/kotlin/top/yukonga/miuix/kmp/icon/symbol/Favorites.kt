// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.Favorites: VariableSymbol
    get() = _favorites ?: VariableSymbol(
        name = "Favorites",
        category = SymbolCategory.Core,
        encodedData = "`U/=31(/.=%;GBi/J)nEd]cx@P/>fuX'DMOh53^U.R,13hN%RXQ?;#)z*PB%:.z~@%b5wZ%!j5%~!hY533lP1J[|{3l!j#`-XTF[<#*#c<oRs,C[_(vD19)rjDD~E#~)3~dZ#!{6!!C%b5;[8{7!Z~B%#P>`<[`9/z7-#Pa!f+U^JLU45n+1jgKyT6ef@>QE'~I<b%S8VB1~U~2(SK{N6(KSi4E%:.Gxt>^!W%EU2!S!H,40W'wE,1IPLP}'##R&U9E~C)SJgR<ajDM+N2z[I'l@`/j~D!);p/s,i[*0|6Uia4rx?RW#s:d)0&_R.8[[=(0P(#>.04Y#21'ho%l(CtV-%s*&*=Tm9T/PJ:9-ouF:k!^Xcgx,4:02G~htp2]GIP!.M76=CFnhh&NQ[.>&F=sooW2<}X2-~]Y,DYpWYbrUBUF~SEB%=&k&)=rxTS;8BF%0h6,h4+'|#@.PasO(n#>k:Ae97R)/}4nX&p7!m#a<t;Z4'E>%H5//'*B[%~)'ETqD6&XM9jN7wm3VkNWtx-cjH<)=bU5SWJ|hB/2ebjj6=hR)g@p/a/Tg=%/?>PpU10?_%.B=3+#]^blWR<B%G/.N/pDqvr8&3VEvUSSg^({Dq#q2?MWg=&9|#@,;jt#a~Hn]x2>v]@pWzxBR179=n>xGR<VCy:V0!IBE.",
    ).also { _favorites = it }

private var _favorites: VariableSymbol? = null
