// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.FrequentlyUsed: VariableSymbol
    get() = _frequentlyUsed ?: VariableSymbol(
        name = "FrequentlyUsed",
        category = SymbolCategory.Content,
        encodedData = "L+w(QBItVLTV#lUni(l:7(tb/U1IK/=;fGl>@#8m9Q},*sa~N%+/g0'p0!o^&P5EE^xUnUX2w.-+G<i:t%Q[-(p=;K~UC<wg3&;)*To|<4i#8?rQF)%Exh6{6RN+M?HP3&!7Qj{AJ:p+s6]i:T<Po%<?n~F02%!hy~Nmprg**PI#_NlD@!_(XPNR_UpDU2]9J#j6Ns'*r(Ta-1#3mHE0bh]+aQltJ7Q.wP'4uy|[R+3:j)U+(::1zh%-'i*FZ-X?,S@:8@1,Zg0#[-z9DSk]iD=+;Wlt+D?)%V@_Ct?.=L<0e/T~!!0++b3;R2cJ9#@31nHW8PPiq2vdyh+;c]l.a/?.8:I.B#A,QQV-+~;EO(qNdt5,|X8<.=}.HWDj[h'0KMG:,/NyBYHqmD='1V&R:,K/L+Hj_g&)9Srj2?r[s)xYbE_.&7u;d4IK8P!bthz-T{Ajg(F4USD_G;327S[>e(L*TV7W<]&)X1O'SE3KIWBm%QT%_PW'rK4PO(e`*FR+o?u(ePVm<Q?B#<W3x+P?'PmD9'iG!",
    ).also { _frequentlyUsed = it }

private var _frequentlyUsed: VariableSymbol? = null
