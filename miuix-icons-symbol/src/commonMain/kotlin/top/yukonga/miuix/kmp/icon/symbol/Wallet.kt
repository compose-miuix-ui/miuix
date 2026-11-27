// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.Wallet: VariableSymbol
    get() = _wallet ?: VariableSymbol(
        name = "Wallet",
        category = SymbolCategory.Content,
        encodedData = ">[ES/1a49@hjb`)`z=A#|#j1.R1%?/Q+:E]Vu1gV=iN2E5!!i-Q9W!)1*PY4(su&Nj!hO%_!U%0m%!4,y#2%Z:!5(qG=I5^+.R=%iDb%n%0!3S{An=eBCb9^Ic;/y&@'Q90#FJq!P,N29rUnu4I1)(-=A!3#zIf=n+GW,4z5t3D'-%DC8PX'R[F:a(t!'7#!K!0yGW'B~QM%g6r9r%{m9%5(4P>'igdS~3!hv-<.%P0&Q9&Pb&.VhW:?nQG*%pR95!<M.#!!}a0#W&_.M%r%fEK+urrQ&/w.=(@MTPK#6BxhK#>4dSY@p[9'[JbQV-wa^%EC=Ej*{U(<K7Zm{QD'R:F*q6+;)1uDo%!4fEO2p.&UXLHm'P~/Lu*7cj/kIH7nAXm.Bko>@eP?0Wj[;'rX:&~:AhiW6f/h7&%3O`iCWj7=mG[Q)-UfMv}Ahg{#B8y#12o9LSAABsvSC8)%Ua&L:^h(YJP#1,`U8#,8%E9'R[6R]4#VDSZ2<]b)^Gg:`/Y9N%m6^E'*kU<PK#vrt!l*H4YT`G>:)*'E.R*1%~l!X'qRkg4,?bB&>T:FG0c9_~(!P.K;l144!&aG#]@('0*_.8,E[&iBam*T,ZgFN1O&(?jPM?~Qk#fj_;+4h%Z>D@/XW[<<69L{EiHAjKiZItb^79s+:<U0k]0/W+Xh8,",
    ).also { _wallet = it }

private var _wallet: VariableSymbol? = null
