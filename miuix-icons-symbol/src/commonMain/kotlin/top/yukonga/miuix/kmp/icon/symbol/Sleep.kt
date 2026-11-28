// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.Sleep: VariableSymbol
    get() = _sleep ?: VariableSymbol(
        name = "Sleep",
        category = SymbolCategory.Content,
        encodedData = "B<]&TXcbb4EF!YM0;.C~.'D|T97!5(jQ`UG!]QhkB.H*?P{g81B*v~`,g<gkU#@:@,,)THH#=)!SQJiPsg5M99M%O'k&J)_VhP^+Y=-)YS+;%!6(#P)nK+u0x,2Po^)!kJE=+(W/y'w3=!qmGx|!6:5n_(oRRg83](%P0!^x^%(!VL#P(!=pQ9(!Z!L#u2C!P#33Q!P#^U(!JwS!?%]TOfaUis#!T-/W3KeyU-Gx;!)~j*uVF-,zv^*6jmATE'+t5-Dm9j/8]gA#D0ZyE-oy?/&6=m_~R+GEX'QC@#8-#sC/Y'e!ZRh7{o4;kI)K+T&7b%*7sg<(CM8i&!L5'h&!B36c[T@!>#J-(0Iu58Js2)DM#iX-A!X]8'A!dKWZuGY%h7npK>|3d9u!<'d[SSEM.jo7Gp@h-*wg6-Yx(<01S?X#'*3?DPc({:i7Ep]l61fEr,QxVR01Q?8#8''~Zgq%cP%-6w@uf/):`R9MRg)*T{#iB'{4.ByhP.SU~v>F.7_!Du-+_!Y%k7De<(5MRgE&=tiD,~8;CG()_!Y%h7{d4(PLIbYZRx#P.7N&A<&*#PDPq%G!!",
    ).also { _sleep = it }

private var _sleep: VariableSymbol? = null
