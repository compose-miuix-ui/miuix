// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.AppSettings: VariableSymbol
    get() = _appSettings ?: VariableSymbol(
        name = "AppSettings",
        category = SymbolCategory.Content,
        encodedData = "UgI#@.!S{F!5#)Dt-P::)#pnKh!!c#T0M*XgU~-7Sg,!0%zj7R#!J&J>f4^9)#rn(PW,p?a<E#3+9A.!4X-|_cMK4F.!|*7%!!0+u)+C/?[?,.-fRg~!5Mgjz>U1KP!%Qttj!!tn=K4F0~AQ7VB[S!0%88%~0%jrU~/%88os*1_g)#FJ;!]ilD<P/%<8!!1%%~=~)#^-,yK++~;!g*fbzT*4]##0*pui'=/?0RXJdhw&I!bQr,)EB&f=VV8Z`uQ9*!o9C;5,@s8ToKU[k'JFL0o1^P#(+K^UbRu]C/V,+H%U.K#yZTx/t/o+f<:&%=-bzT?[%~/%ZJ8#<'K!#0r,J?:&%=`x#VcgO!&!W+!//%;!zbLX!S*RT35MO:>:^x&PrNq]s%V1|S`BUx3VW`L]@(OPk&,==b`Uudog2%W1''S9'/h*ZXePA(Kpq<~A3!.%W93!#!Q%|d7h*!&79j83)KL(ZN.sM+,~!!%",
    ).also { _appSettings = it }

private var _appSettings: VariableSymbol? = null
