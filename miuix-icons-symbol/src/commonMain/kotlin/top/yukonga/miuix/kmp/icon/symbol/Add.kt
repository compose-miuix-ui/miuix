// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.Add: VariableSymbol
    get() = _add ?: VariableSymbol(
        name = "Add",
        category = SymbolCategory.Core,
        encodedData = "'~d!tF!5Rpp&;(^%*!=!:o#!-IX](~B!U!)j*!8!6o&~0Pc:&!C!.N;;%IW/(~^7jr'~/I;[(~D!U!WR(!>L#P)!A~Q9&!IWGG01w[dlTL5KHZ)wUEK0Is+&S0o:V-rU^%.8{rd>S>35F-,wJF385sA#^2X]C)O?WGS9J!Z%m73pQ#VLdKV-Gx#PR+(s&~*!bCGGR+_!Y%h7{d4(PLuaX-uD^%,8jr&%`LMb'P@!bK)T@!>#E-.wHu.8jr#!",
    ).also { _add = it }

private var _add: VariableSymbol? = null
