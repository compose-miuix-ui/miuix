// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.LockScreen: VariableSymbol
    get() = _lockScreen ?: VariableSymbol(
        name = "LockScreen",
        category = SymbolCategory.Content,
        encodedData = "NX}!QxV2l4hVdg('AG'GYbwc<!!dGxBPx*!!6{!!TiPlWGW9NC5M/!^~g%L4XTMX2!=U#!-9,XBjim)#_m[Q.%:.V!~v-!wk)1x3K+tJp*vBGKtgN2%5!!`)Q9V!n%*P43&s*TIc>sk#.%Q9V!Gx-!+l`/(4++@&P?W[It!!F#%~^9+#~-g{c6prT!Q9_RS2aP<(5M4cIZ@!>#W*B8^%,8/pGk?Mqg&!KHb)SYfuqP/#`!^%,84mkQp6Shz'[<VR,8jr&~/#OS[l'IMhB,At^EO#_!Y%h7Dj4;/6;n~X7w+G,~I8_FA(_!q0X:xXt>{M:mt,-s~^m8~DQT2Rt3A%@Wq]X&_1`'DAmmZTX<:Ry4NX(hz%eVxW@Tm]K1BMR9T#d)[+xhFsl*d.,S*/gV0,2m<Pj!w(d;53aJ?Rr=dhSY}o{gd/lU%P/",
    ).also { _lockScreen = it }

private var _lockScreen: VariableSymbol? = null
