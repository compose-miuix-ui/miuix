// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.MyServices: VariableSymbol
    get() = _myServices ?: VariableSymbol(
        name = "MyServices",
        category = SymbolCategory.Content,
        encodedData = "*1;&!56@+&I:5M0`Tgz`hWYdeEr,tDd)P!yPk#RXi#CC9Ev@vaD.~?;*5=m#:L4cDl9Hz!Di.PYK&0}0Sg_iY0C8M^E0Q9#!~'RP,XfgH0n!:&(<01m&}<|~jm(t.#-yaTB:T4/Xc9(;)5^7PptkM;s5D*W9q>|d^ic@`E/(B!{sG*?_Di.1W[ZQ|%@#VZIx::W,0H&=_@2]B,|y;[;-j<};pR_gb(cMD01,`<YjI0Y?3V#IO/43aUEP-*S!Lir,>P!S@5{sZ.sG)jl1RmCSU@;[h~m6Ch!)<B|;`(Psh'i/^~H#*",
    ).also { _myServices = it }

private var _myServices: VariableSymbol? = null
