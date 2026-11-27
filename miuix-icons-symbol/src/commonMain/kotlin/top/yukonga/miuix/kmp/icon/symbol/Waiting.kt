// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.Waiting: VariableSymbol
    get() = _waiting ?: VariableSymbol(
        name = "Waiting",
        category = SymbolCategory.Content,
        encodedData = "-_r#>.!lTT!;!/^|f(ShN70m%Po*;[=.+s#P:&Rg#!Y9Rg&~/!R{Aj)!F-!!a]'!!t=.>!9%ua7M4#c%M1a<0?2!E=#!U1P<0?xDB#:*-^!!;*Rgb%O1q<g=2!5=#!S1%=g=3PB#7*5^!!C(LHR&X9BP_%_J!!b%%~A!A#o6Wh_U,~A!gk&!>s]ilDBPz'jr`!rYB[b~z'ZJos*12!D0jrj#>?o:V-P[_R.86sz#2!K!t!B&'WfE01Zpel=?(FR)f=#P'~NxvQm7/pSgd-XVaR@!>#F-v]Hu-8jrJQ(Bsg&!?,yV9WZ5Y%h7N<6=8MRgq%7brQ!(`!v^O2MP4(PLD:F-~vYEi)B7{jR035F-~v_s**N[i#4?RgE&fx_Ri)`!jQ(*aXi#TLrbXZNx#Pd(9EI#2!rMll(B>#F-~vIE32.pSgH.phi*p9^%,8xDl&TLuabR~]_FC(_!Y%K1bM<(5M!",
    ).also { _waiting = it }

private var _waiting: VariableSymbol? = null
