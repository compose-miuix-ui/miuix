// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.Activity: VariableSymbol
    get() = _activity ?: VariableSymbol(
        name = "Activity",
        category = SymbolCategory.Content,
        encodedData = "+1x:b(Rj#ldGE!LRC(l?=UqRA!cV%!t?^UqRA!B#5*%^!!:*#Pb%I1@<A<2!a<#!Q10<A<I.B#6*y]!!~M9PN7/?%PP)rA2g&P0#p?fxP]a5o.*~i67C)r7Mw:C<iD#!D&iD^(>?#P/!*wcF18yr&~d(9EI#2!rMll(B>#F-~vIE32.pSg3=jbzZ#7Euz'@/pW+KLmNX^u)~:~!+4]n+XxTP*!%72jg/Ab%=4?qgY*7wME,~I800A(_!Y%j7k<ml8MRgq%?_eP*!+E_RR+`!^%m18s.%h4VVIWlGDuj7j<.=WE`bAR@!>#E-OC^%,8jr#",
    ).also { _activity = it }

private var _activity: VariableSymbol? = null
