// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.CollapseList: VariableSymbol
    get() = _collapseList ?: VariableSymbol(
        name = "CollapseList",
        category = SymbolCategory.Content,
        encodedData = "YuU%#Y@W,KZu5!k.{KcFZ-W.&P38q8LyR9eP'!48cNStJxU%u!fxs&]9=)|[CMgV1Y[GV=J<3E6*g]9Ek#u+N=d4OJo~y1]]G*{dC;T96!@;b(:@{oRg!E1+mGWG8:6ELS%NMbFSt]Otp+=e!leIbUVSbJiia5376='UrU^Q)>Zs9&y?9){M>m9P,)o&eCI{@h8*DFg-*>%v%=/!O:o1oJORm/<#wZaudhk0o<6=81!",
    ).also { _collapseList = it }

private var _collapseList: VariableSymbol? = null
