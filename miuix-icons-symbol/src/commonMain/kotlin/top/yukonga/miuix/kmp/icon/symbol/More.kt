// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.More: VariableSymbol
    get() = _more ?: VariableSymbol(
        name = "More",
        category = SymbolCategory.Core,
        encodedData = "=.)#N+JVe<r/5M%PXXw@1!]!HH/~?!i:_1@!<#j1M!<#fMk=s1,~?!L*)P@!i:>1@!<#j1L!<#a<i={1,~?!d5)P?!i:_1@!<#j1M!H@gS`n;[m*NbsQ0!ObzQ0!a_:>[22#AY_@A%^5@_;>&IiD:'*pRgL*a_];0!`_C;0!NbaTw14#P,)BE%~5.bbTAHiD@(Z+SgX,p12<4!Bv(<4!1y*VX7T#?/CM*&[;54+)ZS/?mT4*<[^-^4XRu!2y_R4!fp`?=8R#0]zK&&;;G1b??T!!",
    ).also { _more = it }

private var _more: VariableSymbol? = null
