// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.Address: VariableSymbol
    get() = _address ?: VariableSymbol(
        name = "Address",
        category = SymbolCategory.Content,
        encodedData = "|oE%ci!GxGb|+PD(d~LPqyIJ7#S9n[(-hNW[u'-.X<o8-tk.v=1Y.slrAQW#*:8h&P3PS&@_?hpf6?}PKCvrd(S.jgE&uDJ'unD0]-T?.kG7R46*,WO/U2fm7Rz%Vsk_/1o98&^M.R+1uDR&nDI~8~3!`7mX2?_4LQqg(i^'2H=UU@bEVZ%p3hk0e{(h1=&y@2f/'sr%=P)jp1F4eSgN>sf~h(Y%};Np!",
    ).also { _address = it }

private var _address: VariableSymbol? = null
