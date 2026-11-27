// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.Search: VariableSymbol
    get() = _search ?: VariableSymbol(
        name = "Search",
        category = SymbolCategory.Core,
        encodedData = "qRw%EjP0#S!;'!g#6%48w:(#T&W9Tgi'W(SOg*D?,!N+!).%)!.e]il%M9''z%o:G0E)UVB#jr,~>#C-UMyh0~APY%:._!xhur`~X%,D%~Y%:._!Y%,D,yfd2P>#ua?~2#p1vna]*Px!ua)!:o*_*Px!e&f5O`#!n5!!d&b5k_L+8!/`#!j5J_L+g9q#B,*o!!ggT&.&1Tj=XJO~V>xro%~:zrU~X4#V^X'*:=s901wigL]Vm(IQR.,#A_{gu%LsL(e@ZFj15?I#!;ogf!*/g@#YQ8njE=F_0?S`qD]'P~2F}'w+UvR9dmI)CVC?AUlXZRk>~RZ<]E|bP/!-M0_4I4W%u6C/=.A?7:s#A70??T{D)~:1C/U&F!I5A]Yj'S=;)15;YEU?(Pc<+SC5HV1)8Tt9!,/*<[))XS!>0/R#//%iT0^;t3%",
    ).also { _search = it }

private var _search: VariableSymbol? = null
