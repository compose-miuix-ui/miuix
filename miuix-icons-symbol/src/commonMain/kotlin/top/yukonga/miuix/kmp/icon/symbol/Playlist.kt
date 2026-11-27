// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.Playlist: VariableSymbol
    get() = _playlist ?: VariableSymbol(
        name = "Playlist",
        category = SymbolCategory.Core,
        encodedData = ":{A#>.DPI4#Y#l!G!5b!M&TJ2Qu1u(^!D#}gxWoKT#l+n%B!++j+WkO2R.^!D#cZ1ItR0#U-jr%P=:Mb%P)6:.(~2I:.(~@!u.j'1!?p!!6?E.Y'5M#P*!XQvE(~)6:.(~2I:.(~@!u.j'1!?p!!~:E.Y'5M#P*!XQvE(~X-:.(~2I:.(~@!u.j'1!EM#PkJN&a<tuxRyTLm+Wjx2RF/T4>U;??te0;.5<1%-Y6kd/')DS`+ZRk7:p0h71D:C&[9v~v!16phV-JxB:g)_X#it?%b|T>IDuh7w6=i-1r9(WIx5Qx,T4llX@a.DSn=@ih7;pvi*(m:s)p=t!S&:*GG/8*shk-P%~TS_G2:0%{N7Gn*_!Y%h7K{p'BOT4@U&EZRs+`!^%,8mg<(5MRgJ'@wvE,~@!yV)T@!>#E-.wu0/8jr&~7,&s&~*!PFGG7,_!Y%h7}d4(PLua;Tex-Q*!A!X]8'A!phX-XxQ9&!!!!!5%YL6c'P@",
    ).also { _playlist = it }

private var _playlist: VariableSymbol? = null
