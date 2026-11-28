// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.Bluetooth: VariableSymbol
    get() = _bluetooth ?: VariableSymbol(
        name = "Bluetooth",
        category = SymbolCategory.Layout,
        encodedData = "IJ'k-%QK'jP4><F&y8.{-fb(/R:*o.~;`HP#;A=b%=J#wDZ#r,#iz'es~%]Tt.l1cU(#f%/3#P9+:.,=ZE4Q&;V>.sMX9Q}Ang<&q1rD^~7&o6b)TJ]H}A3s/7JQ7m#Vs5N_.~iXqrE&I+-^w{KJL!@<'sSP^&]DGWlrmCzM5?]T!VBR()8,m(_G(])!i;+Wa/IxmP&6T.=!*)LsF-2!3h^%d-95NXdg:RP8oRZj0D3%1)4iq],~QT?tD/eMEUiIdKV-~vdl1~sn.j%G{onqQqPPK#G**_Y4T!j^(77Bj<;!f.%(1Ky('P~h~ru%ZbClmpCnbY%lv1Y:&BG:_@ugl#bX{gTE/?EU/={VF*21q#O2S.Q'wIWKP%aQlk33YK,Ck&;/)",
    ).also { _bluetooth = it }

private var _bluetooth: VariableSymbol? = null
