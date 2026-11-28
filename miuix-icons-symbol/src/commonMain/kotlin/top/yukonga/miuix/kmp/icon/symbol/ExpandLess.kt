// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.ExpandLess: VariableSymbol
    get() = _expandLess ?: VariableSymbol(
        name = "ExpandLess",
        category = SymbolCategory.Core,
        encodedData = "&P.%RX#l!G!5rZDd&~@#g*t3G!H#x3C!.R'!|!<(2!O!Q)N2^!r#hW,!#(vMB#%-N26!c&|K|!#0}H7~U#U{Q9*!g)Rg7-H_Q95!8!]](~-!F<#P'*}D%~B);['~jN;['~G!M!WR(!N0Rgw%#*,[l|HuX9c!Z%h/|[/?T@=[5PS#fQ39Z{Ui81^/F&yG11::@?EPt%#:B&k#@/N*7:f(?AyaF~M#+/v-B{<iI6^p1mCMJ@HZ,wqDj~w%o:33O.<&)4:Fb,q@M%c62h&UpIdK'P9BI%M*[!M%c6/bx'FJOQC,aq(u<:Z!b^H*Z!I%B6@eK>SJOQ&!]2PP&!1!@eKl[26#a,cq8ui62h#!M*@e^i0!Z!b^-+Z!yiJ6",
    ).also { _expandLess = it }

private var _expandLess: VariableSymbol? = null
