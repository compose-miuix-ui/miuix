// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.Data: VariableSymbol
    get() = _data ?: VariableSymbol(
        name = "Data",
        category = SymbolCategory.Content,
        encodedData = "N+X:i/JVz=y/l4A/c(E#n#YK'P5!oPd,6!t!,(1!I!x/j6J!D4_UV+1:(/'zyWu@.FUF1BcGJ>&b/vf=V6g*!]VEXuvh4)K%3h_!WG=!Gx!!i,n%|UU%=A(c](uRNP?PmGqrV!D,Sh9+ee@<SQc#S)AT][3~0,#P+&(A:odGs#|+gk#udA#YR9Q*t3'P[m9%.!=!_:12]m=%4a-!6sM+[g!!U&]g!!P%|dGh(!W</h(!F?QQ}.u(4SW93h=(s6xhb/Y9u!P+6sR&I<>:C)jUeP|'Tm3SG<2:>(aXhhu,f[CSw:zrt&gGphX-NmCS|:g.4&t6P#}.Tg+*3&O:+!eC#0M~`FQ=&C}o{<uP/)(P>B7;l!*H>2FCS9<P-%v3v~g*%bjQV97!4!L#8P(#d/UEB)iU~~>(EMxh=.qgCSg=p[Y'kG4i~.jx!S|:V.p%zDP#!/%)4S7?aPY'lGK;,1+~(#u,E~H#O+wg2&F<z9p%u6X#&=/beSZ@G[p%^gDPy,um~UpRU~t&~AVgY'+pj99,XsQ)",
    ).also { _data = it }

private var _data: VariableSymbol? = null
