// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.ReplyAll: VariableSymbol
    get() = _replyAll ?: VariableSymbol(
        name = "ReplyAll",
        category = SymbolCategory.Core,
        encodedData = "8M(R|:!;}4#;+/1]_@392?s6lK#!:2!!=#(.}H9T2P^~h%;[`!8TwD]~T&sB%~[%;[V!3%d?cwL+1!W~!70PQ,ZGCl6#dU3Sj6cgc#f'lt&*IJo_G?.6@<E(P]!!l'#P3#I-w:{A^gT!H%#P^!/?urV!L%[?%~8%:.Z~<%a8)QqR_g3#7nOPB-nro%0?;[qR-Pc&x%'h:AQhRL!!C'%~^~_%rADPxa`g@#Gx?!k_xa_Q'*qP=(/!q4d!;%UQ*#)!{.6:](1!YbqR1~Z~f%jrS!ZN0t<&z(QQNEua>QrN'GR+_!q002cM4(PL5bJW^xqP*!G1E%!/n[yTa@e]6Z&BK&U>0]d)n4WKn[;tcsa.Tp%i1!`P{&*/@#U-OxrQ18|r&~C(77niX+4cD`oC)H+<p&U=ZN(s*'XckFn*_!q0K1cM4(PL6cgd:mDh[%''oB{.&4>~^4'P(h#PBR*!p9_RS2>V<(5MCz6kN2p3VMYXa:-%;ht)F8=%=5m(TSYGQ9|#RTZFc(V!P0x-^{.kz8l)HZ,w3/~'LSOhH.B]x*;_Wh+*xx''610#1YB[/;{4LXah1=c4AR<!/QV*{KD0w3G!yh&/=)NUi6`hI1>MI#/*6VXTWG:sh*8mw:93J4/!nIY)0UH8S+x'E%;!=PS9@.sYpRbI[;x+V*V'm/790_'k}YBPz!=!S)++[G>s%",
    ).also { _replyAll = it }

private var _replyAll: VariableSymbol? = null
