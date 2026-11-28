// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.Reply: VariableSymbol
    get() = _reply ?: VariableSymbol(
        name = "Reply",
        category = SymbolCategory.Core,
        encodedData = "kD.%tR:F+/:4,yDCU-Xu!!1+#PE#d1]i9Ta9b~`&jrt!rYyr`~i&sJ%~m%iDY!>%MA'We63P2#5tU~[4Mxl[_%?[-%tDK!e!%+'7+l3mnVKRd?=~TeT9?P/%)9!!4%#P]!l%bABV2?1P>##PB!q,7m@~@#D2#PG%:._~H%0@TaB<_9<#Kn^Pif|t7,c!SGq2eMT(?Odx/X<~I!%!e((:pR]2+t2,o+u0r?TV8*-by]e/wQ7G0>huUij;UKqUn2j%19lGhk'P!!Z#yD(#V&C!myQYjG#<r96|djAMyg&!o/csO%A!MnVZwdZRj7{o/:S0cyBY6w)^t+p_#(CO>mzW^|{.n*]~i<I7ig!&?F?h0%A!z)(Wy6Y%h7.p/kZL7b'P54RRb5g{t&!Z!",
    ).also { _reply = it }

private var _reply: VariableSymbol? = null
