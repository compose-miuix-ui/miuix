// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.SignalStrength: VariableSymbol
    get() = _signalStrength ?: VariableSymbol(
        name = "SignalStrength",
        category = SymbolCategory.Content,
        encodedData = "'!e!o%|3#Y#l!G!5706?AU<?#P/!.!/h&~(!b-#P'6h?%PT!I!)j)!8!bM*_._1j/1Q9+~(!*s#P(!G5#P'6h?%PT!I!)j)!8!Tm*_._1j/1Q9+~(!*s#P(!F>#P'6h?%PT!I!)j)!8!N6+_._1j/1Q9+~(!*s#P(!oF#P'6h?%PT!I!)j)!8!8Xn8)E('KCDst)JtO/d(WBSlO>lb7*q]40r8_x~PI.lh'*^Jlt63rJE(}MPmlT4Z00}-Gs#(5K`K8Zfu7hx,sJpR-6GK.XcJQ%)7_j/kYLObaU6b-jP1;vL(_N/h-+tddhj0pj};H.F@'Z&Z!G|4mXGkgNPsf&q9+G!)O4qTcI'K_[w%c95%.y_RG'6F[[v%ria50_s9S0yJ7Pd#yiD6+Y,(mK;[:'Rx(PJ!PFv^R+a!cRL8TjB>bIHJwStuB:O#L&-US2v)6ZXuZ:S8ouZ9}%;5jTR'3GB6xdLlJJ,PK#@PuP0#4,PmuRF#,.`|3!",
    ).also { _signalStrength = it }

private var _signalStrength: VariableSymbol? = null
