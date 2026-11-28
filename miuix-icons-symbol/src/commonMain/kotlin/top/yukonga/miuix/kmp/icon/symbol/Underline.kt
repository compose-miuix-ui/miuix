// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.Underline: VariableSymbol
    get() = _underline ?: VariableSymbol(
        name = "Underline",
        category = SymbolCategory.Content,
        encodedData = "`UJQTX#l(ih;0PU([{Aj)!C>Rg)!U{Rg)!_!4#z-?!J6#!1!d!&~&!Qtvg-(D~4#h7{oRgt8ua%Pk,iDY!F%-A4aqR2~Y!0m)!YABjW9Y!j&#PY!{o>!yT3P4#h7{oRgt8ua%Pk,:.U~-%|7#q7{tD!!8'Rg>#K+cg5M*!sg!!2JFZITr^i7-pcjCM=h@%%7_Rd/'Kk>8M=hH-4wEE_'+,Zj>,)tE-4wGG08wr&~d([?I#2!rMll_@>#F-~vAEF/Bpah!-m:G-VTv^-8jrQ#DA9b'PI8CG32NS4(PLmg8~}:L.k#TC:FL~UTDui7;eOWm_A?^Trx:RC(,,>UA5kV0,5m%Qn71p%i`9>#XWB54#P,-pFk7Msg`(9;0<2,u<XTYG#:H-+wQEA.`P[&i-%%33NJsQ58>so%./!",
    ).also { _underline = it }

private var _underline: VariableSymbol? = null
