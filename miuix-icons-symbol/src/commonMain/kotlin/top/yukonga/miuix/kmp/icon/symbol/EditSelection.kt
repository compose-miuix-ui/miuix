// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.EditSelection: VariableSymbol
    get() = _editSelection ?: VariableSymbol(
        name = "EditSelection",
        category = SymbolCategory.Content,
        encodedData = "Hx^%&l.6#jNQ)!_(FJ#Pl&;[i#oR.!M%Q9d!M%%~#!!t=.>!Zc&~0!*y%P0!l%h'kN8#xh(!t!#P,!f`#P1PEQ&!-%iD**+,c>VLBcYZhx1QO#-Ybju,VmCSn(,<k78plg71<:>%m6uP,#~j=UEMphX-ix-Q/#`[8#5,LPF-bC^%,8^s5(eLua;Tex%Q*!A!AQ8'A!@#VZRxY%k7{oSgA3Gb'PI8DGo7qM6(PLuJF-~viD*",
    ).also { _editSelection = it }

private var _editSelection: VariableSymbol? = null
