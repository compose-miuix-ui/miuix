// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.DrawerListHandle: VariableSymbol
    get() = _drawerListHandle ?: VariableSymbol(
        name = "DrawerListHandle",
        category = SymbolCategory.Content,
        encodedData = "=.)#N+0qWI=6WW@}fU#!,'^U#!X4FJ%PYEFJ'%xh!![+>ciDY#U!0L#!<!:.(~.!ESSg4+](&!ME0m6PF)5%*_.!5%!!<!##mX;!Ve#P;!]('PP.kF63cJ^P'(ZJzQ0f!tU,%:/'i*K[H#8I&R63MJ^Pz']J;+;f{ss%%:|!S&PJ7R780@,X+1}Dj#wx^=.D[xlQ.15Eo7<p2jCMnh/(SM_R]-9vllCM)tGZ0wr^k7=pmlCM1h?(fxAQ<-[pml:8Kn'Z>IY%l7^p|>bNJsX-Wx5Q.7~j1<U7!",
    ).also { _drawerListHandle = it }

private var _drawerListHandle: VariableSymbol? = null
