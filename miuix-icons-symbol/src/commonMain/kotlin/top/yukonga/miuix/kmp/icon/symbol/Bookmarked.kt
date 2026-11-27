// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.Bookmarked: VariableSymbol
    get() = _bookmarked ?: VariableSymbol(
        name = "Bookmarked",
        category = SymbolCategory.Content,
        encodedData = "1?ZTY@&AFANA&Y:IJxbPh,Y+YQJ*CG)J,Yip1K'byPI!c,#P^-jrX!S`d.`-.)l&d4~b2Ylr9~E)tXv5ME<(-E&*rr%~.%Y-'hLXpr`@4E,&E'nEK+Ojhhm1Q?T!y%l!W7!!_-Q9]~e69PZ,tjDC<I^hv0R`][V!IQ2!!CV@3P2%)S6kU2/EWWKC<LwZO+OkA%sP&@MXsD(*/?{Qk=(]H*e`x7#q<[a#R2O3yuO2csG*3_cgc/2P)#??hhs,zrH#K0h?zTAM*:%!y3Zg)#%SLi{3&#K.uY_sD04?EP0*qPT&PX%%W-vUzQ`/lrt!!/yaZQ?FK/S,2pxh/1CEi'X@K[t&6M&:?.}gc&Y@`[l1_?!l&=j?=XjkH]73dP+S#4zE:'`Xui#6C.7R<A(VaX,4z=>B,1Nl,*I~YQa/lry#Y@N.#)fXei[4|UTS;?,#o+GMcgy'waePF<iPe)(Hrji=].RV'W;[)~9W!/]''H)j!/E!1Qg*??n%WG&:N+mg=%B5Y[!&b@G/(*T9)Qq,D._%W2'/B)hUU%d-[K0,y(116:yrr#!],4jP;&@Hi)bBe(,KJ.U-U+JQ*!w#G`_B%V#",
    ).also { _bookmarked = it }

private var _bookmarked: VariableSymbol? = null
