// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.CreditSelection: VariableSymbol
    get() = _creditSelection ?: VariableSymbol(
        name = "CreditSelection",
        category = SymbolCategory.Content,
        encodedData = "+1r#PX#l#S8m[PC&_.+;f6e.>%:To.EdEj%hr835h'FT/P##SJ#Pz,W[X'j6a#x'`gg9v#a<vn:T@.q&`-P]K+@[`!:.(!P]g*WgaP@(`?=U%!l?%~@([?tS|AA!XT%!l?8TB<BP`%C1@<#!L#hW%P;!S&IJ3!0%gQ.sM+<m-%D)b)'WQ9Q~HT]NBsB(F7Rg8WDmmXoUai94pRqhR>24'P|6:Ri0hMv=2Fn9C)HjQ93Ee6uga&Q?;QM'tG5@e<];a&J@x*m=@[-~y6{:N#BVWWEje(!d(3(]a4K4dQu!HVG'l/P#|[e(2%A;;1[9U0{4&SE!R#",
    ).also { _creditSelection = it }

private var _creditSelection: VariableSymbol? = null
