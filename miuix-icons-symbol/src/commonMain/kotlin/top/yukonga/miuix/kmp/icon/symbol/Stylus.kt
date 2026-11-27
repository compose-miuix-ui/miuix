// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.Stylus: VariableSymbol
    get() = _stylus ?: VariableSymbol(
        name = "Stylus",
        category = SymbolCategory.Content,
        encodedData = "}o^~pE>DWPd%d+rj2FsD,+jGV=x3<PN~-#megJ~:)!0]7-_[*&S,`@P:xb(T@;*Pe~k%a4J>%Es&IjGtH6B/[+x:E)BF.RxKS2JT{F-8oDuS6MP.p(5!Hs.(o2iD6%F)JVSSm(]TWQ~EZ&P)Y)cIamHPt%l:;-[G`hj*6Pk'~Y&so+*1-i8Fs()WH[%Q2%-=x')W>.h!x*YE{##)SS&=rrL!6%V_6k4?v[OU)b!",
    ).also { _stylus = it }

private var _stylus: VariableSymbol? = null
