// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.Blacklist: VariableSymbol
    get() = _blacklist ?: VariableSymbol(
        name = "Blacklist",
        category = SymbolCategory.Content,
        encodedData = "`(D&=.uXgV2ke<!5=.]~r'@'N:e4i*?XrCwF4S#!A5%~g-:.W!f/+~]+Jc,H<3sU7R*#P.=!ua,!hG!!A')QI5jEE03E%%[4;?/!|[#!}y!!>%~9V~LjXi8EESm;*#u(=!4a,!NcG79PC,sYjrF6>7Sg,!,h#!(!~rui'!04A!N(T@ks#%5MDiY-t3&P*!7E&~m1CC5~V9ME#!0!7_kgL#X.Y#i(hhV-WxYQ&/(yyToD_gf!h6Y9k#V<LiP2%Ej#V+7/A)OJrQ33{a?R:8S/7-kuxhw,#:!SL#aPb):_V:l*Zm(TZ#^Q%0SMW'WGr9e)ZX/h,#rdRgW&7b*QM<zEU,8Sui#61b4SSJfQ12zU_R'*64^%/8]Pf!Q#EEo%<1S;Z4(V#SL<-Q-+VX'hg!I<Y9q%JPB&l=9EO%_+bQ0,rUoR/?=~j#<?_:2,Ama#G)W?-%r%s.A)F[~~9'-bHTm/n)QY)BQ%'7Tj3>BH5?6SKjQ9z3ig*Q`&c:4,*S1Q1,;mwRyF,@!]A*JRp25pzQU2!",
    ).also { _blacklist = it }

private var _blacklist: VariableSymbol? = null
