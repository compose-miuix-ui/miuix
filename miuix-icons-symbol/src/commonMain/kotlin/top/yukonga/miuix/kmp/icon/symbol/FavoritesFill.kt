// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import androidx.compose.ui.graphics.Color
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.FavoritesFill: VariableSymbol
    get() = _favoritesFill ?: VariableSymbol(
        name = "FavoritesFill",
        category = SymbolCategory.Core,
        encodedData = "A<[&f<e<]St3,:r'+(-nBRNm_RU2:s'~x3n9=(q6'h'S/-E^Z!F,#Pc&Y5J_7{7!/`#!w&l^GJ:)6NmrdPB)??St1m%%Q784~U(P{gI#O~0#9{Hx3~E#&.wZ8#0u<!8T&!]Hp?&!hgp#@,*o!!txHWpdx!o(8'krP#(/im>HLVtVCTD]x-C{Zgg/4s6~-*b~>R{3eP^(yD3;b/ggM%08Y9`%RC~E**'VU%93p?!V,Bk:6-pRbQm8dxC&FF,0Z4qUBQ`&a]:.(p>:6,]mtS5?HPO%7!;KYT!&l/@/E{8<c;R4pUp]ks^.+&#;Q#1/2)WQ*Fn1Q.g%c;h?DVhUHPh!dG%~?%7",
        defaultColor = Color(0xFFFA382E),
    ).also { _favoritesFill = it }

private var _favoritesFill: VariableSymbol? = null
