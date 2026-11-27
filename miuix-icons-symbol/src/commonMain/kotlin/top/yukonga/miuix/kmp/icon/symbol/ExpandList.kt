// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.ExpandList: VariableSymbol
    get() = _expandList ?: VariableSymbol(
        name = "ExpandList",
        category = SymbolCategory.Content,
        encodedData = "YuU%~4#YTt:{[9D/=16kV@j9)#sUTi6Y4mHP38U9P(_`5^X-U&(<>h6?cP38T#<(~%CPg%Hsl.lK&F32vuRg<8v[nPL)@#x,r[-UZNY[*'MF8un14E5(|MqJYWu],]7,&K{j_i{aoQ!:Z2j02pniQC.]X*cCf%m19E7khBf?&S?!U/&-dn%^o1L!K:|&@:](S2]]%^x3q!L#0)62_BNxlQ5*7W%^.~u:q/v,a[i)j<#",
    ).also { _expandList = it }

private var _expandList: VariableSymbol? = null
