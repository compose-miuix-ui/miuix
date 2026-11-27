// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.Reset: VariableSymbol
    get() = _reset ?: VariableSymbol(
        name = "Reset",
        category = SymbolCategory.Core,
        encodedData = "%P8':7{4jJ/Q*!h#=5!F-_g6J~d)O!uPb/=bi#zAx9<lGJ3~b##P)!|;JQ+~=P]&~rX~4FA[X~l%C?cVBj/!.s*!4#v-B!4#y4{oAjj6:.F#K+*!<H&~UgA~{#yD?!c]2?A~&%)3=v,8*!wk'!C7Kl0?t!hW9?UQf)2BSgH03=#@Ix%QR+Y?3&51UnwZ`g`;u9z%Fih;z+VZGxePq1}gI#r!KS&k{,x)FZ.wuD-*_[0#v,z9U!h4z)VZOx~Qm7kudg?,aVESf#CGW~bq+t#(ZSCl=:l)0/-*Y]p1<S2<[7F@)ZRTe<e<-Ymlm]0:}'<p:6E=zAVi,Np)dR))N&*7c_b)LXRgq(A;a//~K!3zoUH!_#40Z2Auv?sU.Pt/.F:'I!JF>!!La:m'_M_RM7q.5@zY#F(->",
    ).also { _reset = it }

private var _reset: VariableSymbol? = null
