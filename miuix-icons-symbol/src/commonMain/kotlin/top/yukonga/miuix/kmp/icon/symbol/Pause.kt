// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.Pause: VariableSymbol
    get() = _pause ?: VariableSymbol(
        name = "Pause",
        category = SymbolCategory.Core,
        encodedData = "<.%ix3!,|4F<y.[!q6]P&!,!=r!!t8Yb%P@!Y!9j*!>!Qf&~1PEQ&!A!VT!!x,og&~'!0J#P4,QbOR+!>#h'ZND#P,@<7#{.5bzTF<#P,~MM/SQ?pRq:)-2L&ZGT.Fv-H{6kU@W[`%a+?h+#j+2j-80:tVrYJs|'J!T]O%74gRv8eyl_2?^PF(g&Q)]GIE>(8Mq]>:p.dQ)43W|.,bpG[;z32TeSx?LT/ElGW3F)XmTS#@#]d+<hS+,k~UqK@#B]L+lg@4p1R<j4SQ1]?'R^?;/1l94",
    ).also { _pause = it }

private var _pause: VariableSymbol? = null
