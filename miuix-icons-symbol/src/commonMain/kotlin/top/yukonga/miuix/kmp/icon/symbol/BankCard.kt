// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.BankCard: VariableSymbol
    get() = _bankCard ?: VariableSymbol(
        name = "BankCard",
        category = SymbolCategory.Content,
        encodedData = "ya*#211Ea^2kz=M@XFO^DT;P]!+K/?'PuuRg##0m#!G7ua%P9K/?Q!%=wrdQ;%BsV,veC>=6Cn[l!!g,#PQ(:.Z!bN*PE64H7qSJZP=P*~&!Ta#!x'!!V!prhhp6!KP%!-0,%~6#Rgb!5M3mll}H8uhIehq'N7mrTq!!sH!!~)<B#PK):.Z%5N8<lPWnNU9F*E2~-.iD2~g.L#39yu0mT9ag*~=!v/2wCjB[=#<82_X-fPU%XZt3fm)!Q9M#z9phT9f~P#aBqD&!k%!!Y%<(#P%~52IE=(5M%~Rg&!HEelZL}aVScJBR32Up[l9MXg&!2!w.,(&I/hi*-p>:Y&`!^%,8srRQ06*cjWaJRRj7!pSg1!+,Sl@HObYWcn+//#_!Y%h7Js=(5MA[%&MFcF5,7V%UPLua'PT'CG)7C_?kFH4V&ST'Y%h7=)=UpKAEj*5p1Q!(rMll_L4cbR#7p;12nj;lpKXg&!2!~j*D2ieF`27'&~%~*!7b_RD8_R-f/s1Pi)Yp:>NCbh&-,wyD,~@!35&Z_nL]t2E_4;;%>#E-~vyD-*GsaTjB*c7ZPx0P*!A!o:5-)phh835bkQ|%@#U-GxCGo7{oc9n/J]x*IF]0I7mr&~*!{(0<I73b(',Ip[5&0&^%,8+,c>/Kb?{WlnD]z&y[4(DM+tU&n2]063[maT/K}a'P@!>#",
    ).also { _bankCard = it }

private var _bankCard: VariableSymbol? = null
