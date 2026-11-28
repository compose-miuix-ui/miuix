// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.HomeScreen: VariableSymbol
    get() = _homeScreen ?: VariableSymbol(
        name = "HomeScreen",
        category = SymbolCategory.Content,
        encodedData = ";.3&#Y#l9%%<#y;;M'kVDa-PdPe.k5%~?-NYrzZG9?{1&!t+`p.!![)#U+19/?-!0,XuM%~5;[?!sF@@o+n%&!l+{o?%i4f=1!@,bq=%'W%!T(.R;./!#P#!ffI1?{IjO>wm#SD?vQ9-{6A<]@M['~:,4]i#R5q#m1/?6%]4lURY%bUQ221E3&I<+hY'sGn9`(/?'R{,?KGW=1)^J1Q9vPX99E!)N<,0l1/srQP7+KlQ6!g4tVzKNFg0Q7bm-P~!`+Z.)1s,6)2><Hb?.[d|.s?4;HYmKVD:s)%ZRF,1e9#!",
    ).also { _homeScreen = it }

private var _homeScreen: VariableSymbol? = null
