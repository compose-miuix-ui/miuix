// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.Mouse: VariableSymbol
    get() = _mouse ?: VariableSymbol(
        name = "Mouse",
        category = SymbolCategory.Content,
        encodedData = "_(Y#x3A/#Y{AGP9(:+HLk_JxQ!Yb&!YI#!}'gJttorRPb%m8!!b%!!R!B#)-+K#!L,%~/';[APX%(D'xTg`9>#jrt<<[e#e05MJ!#0h<K&9A/?&P>_Rg'!z)%~H*aX#P(!-W`/63IJUP^&R:A)p6a#D0%4,SeB8m;Qb+9^v-7B:&%=pb8^[2&Er#|mk&P)X]U-]xq<i=nDW~Y,IpHhO)4cBVgGHi.2!B(h7%n??R4!Ts1)e@40N2qDN~2%sg(#,!%)d&71O/rYsr7;l7/pqhj;bKvV%>x;B6`+7kW@[[%",
    ).also { _mouse = it }

private var _mouse: VariableSymbol? = null
