// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.Microphone: VariableSymbol
    get() = _microphone ?: VariableSymbol(
        name = "Microphone",
        category = SymbolCategory.Content,
        encodedData = "Cj-if(;4oTi;.6^U5?T~W~k%99~U<[/!'W#!A9BV_U1~c!/?)!:o#!t+QjXupDc!6%e4%~6%:.e~5%e4V{#!e4%~1(RByu_U`9M%G3g{5MpD'~s&iDS2;['~(!uaQ9|!]%F:%#P(|KH#*!EM!!##Fk-!w!p!(T-!M!!+?O3m?Pu&2BMf7{0!2g#!2BnfYu1Pz#!!1!CCuarQo1t3)P8!ua&Pux6:S2zr&~C(ZBGkH.>#E-~vIE32.pSgH.V]X*J8^%,8jrfP^2a]W-ex-QO#X.U%QL}aKTvu<<Q85p8h1!'y4(G7jr#!.!aP=U<MRga%f`ME,~I8CG32qM4(PLUbrR8!^!^%5MRg?(A!IhM(T2:.!!(!+hb)ZX<i'!<11QU&:&&k1?5h7*f]:..1<'n=jLC5kZ9z<]o78pMizKV]*'8;aG0Da!@.XSd6~EU-p+s&TB)j+1/b5~Y'u6g:2%^U=U5M{g0%~A(<)=Cb''Q`fEn*'cvEN2b+>R1%]#",
    ).also { _microphone = it }

private var _microphone: VariableSymbol? = null
