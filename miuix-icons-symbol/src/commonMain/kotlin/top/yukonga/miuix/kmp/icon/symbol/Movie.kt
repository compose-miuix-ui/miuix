// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.Movie: VariableSymbol
    get() = _movie ?: VariableSymbol(
        name = "Movie",
        category = SymbolCategory.Core,
        encodedData = "8M5SJ7uX[1l4KF@W>Kdzf6lDp#D?[SS22]dS#wRg;!-f,!^EA)R6HWf9aSN1>_Jh*#FJS#N2)!Xee]YE82_M5;K)[%,i!!D>#P2%d[4*6E0WMAm4jex3)!CC'!~WQ~e`jr&!'6P4%Pk-iD'~.IiD'~<BiS*!?+;[c#U3rQH)440,%~RgR!#PY9l#%BAQh!v%sFe6N?ig7#|xcVyR[hW'F{phY&w(JTg@sscjZu_9-6qV!&8'+/H*]j>kDHiVjZl2Euq7{oZ9-/mVXW6IX0)7~oSg1!v.5U/Kh?kW4pZ:()N&<(5M:i70.z/GT21s6Rn1|3%P,!3EzT%!](%P>?h;}4nX5i/!B!Ch%~PJfQ,1W[;!@(P_&k3?sPt)6wKt@.{[kVWZ_(YP3>|Gc<S<;j}8;5dY*Z?tx-E{OhV&U?CS(*yDH#)!qDx'>[#P?'F:h*&bbQ.1Q~zQR2~9YTvRLPQ%8&B&f=iD/~a'=Yp?vRD:Y*f`BF?.}9mSBA{JUS[+p!>RI#/!S!-%ZJ9TmD%~j#nD%~#!G)wJkQ.!0VYQ.!Q!Ti<.JPd&QCCtF0/4&%t1y42YLxVgk!84VRH7<m:QH.k:Q,^g`hk*W[y#d/V..%}3?hB)kD,P&",
    ).also { _movie = it }

private var _movie: VariableSymbol? = null
