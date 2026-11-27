// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.Stopwatch: VariableSymbol
    get() = _stopwatch ?: VariableSymbol(
        name = "Stopwatch",
        category = SymbolCategory.Core,
        encodedData = "8Myh=.!S%5#YQTM'z*Bw[|mrB!Z%!!7!ePZu4~>#H-pOf~%!3?%~5(E9||MX@!DP%!M9@}MXB~>#Y(K[!!Y4Y!33#PO!0,igM%Q9jr#!83^U%Pn*;[R,A_Rg*!m*iDB<[!c&iD;[3)GTiD]#n%R9v8FJ&P:!5/I'6!-=!!G7ua%P0!Y!U%G7ua%P0!Y!U%G7ua%P0!Y!U%G7;K(WV[^%.1mgC!+#7p]i8?EhvV1ZNF-+gdsg2%rxHT!iJsC(E,o*]b^USRsJOSk7/S~ie/M!q#m1vU(VojT<H6s<cjO+-!{gT&_4^X0ia0A;@pP:=!-qihwDyVJZ3Z1^12<B'k?Mnhs/qg^%x3Uxg:x,G[j#=1+;{A!bVPa#k*9bqRw9=&?=a<V@=[6~N#W!",
    ).also { _stopwatch = it }

private var _stopwatch: VariableSymbol? = null
