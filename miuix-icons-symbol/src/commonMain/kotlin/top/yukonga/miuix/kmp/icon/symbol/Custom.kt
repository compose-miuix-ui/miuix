// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.Custom: VariableSymbol
    get() = _custom ?: VariableSymbol(
        name = "Custom",
        category = SymbolCategory.Content,
        encodedData = "ya)#>.-b0G!5#YS0D>aLuF<?4QNCuDT,2-Y918KEF~T%EEf!z3eH!Cjkur}#:pRg'!%#a#)6K!Rw#!7!9Q&!-!)%SSSJp!pp&!C3?O~H4#b)XuTgg6,:&~@&Q9#!j6Q9&~:!3p}i'!=0RgQ%bI^vU@3Pb!g%9Q&!HI!t}!w*E%^Zuai'WCvEE)g[@TW9+ht&iG;tw,`mZ&O7Hy?`a/G.0#Q+g:g6)hw--phh;'>4q<v=-;A2cCtsO~<4RR94G[(#k!JBtS-B`hd)ZXGh<4hj+>mNPcp/2WPiB/~G};y%v?zQE']#'0yuv%3%n)(W8?3/F*1BFRY42VMXduHP'0`[{#a2vEI#Y2Ct#65B%%53AbR&3?>RB<C[~!6IC;|.mr<P1%{35%O>7K|QM.fQn+w6i#219Q|[b(cQN*v9F%?AVVHWkDS!TSLXCh(07!",
    ).also { _custom = it }

private var _custom: VariableSymbol? = null
