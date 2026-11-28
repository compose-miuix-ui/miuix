// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.Portrait: VariableSymbol
    get() = _portrait ?: VariableSymbol(
        name = "Portrait",
        category = SymbolCategory.Content,
        encodedData = ">[J#[G!S!;]4RK!5RK.#~+C1=vA<[9v!F(Rgv!zAD.v!F(&N'W,_7!GW%!&NcVNXggb%/?L!j1V9MPE(*N~Uwa7!8u0?_9N%^-xxK+1!my!!f-,yK+ag8#@(vM!!B(#PN%[-Ly&*1!]y!!d-<y&*I[8#?(^M!!m*2wo8Yb%PL!G%=f<0(alQCj/!P,6{f%~5t3'P1PEQ&!2%jrwSq%d;)6U!>*~5GxM(}HiD+~1PEQ&!G!I%L8c1p'}T2?0!q,%~H#q,;[&~y-?pQ9.#Q9R9o%8#Y-?!/'q,%~H#q,;[A#HArbrR@!cyWW@8Y%h7/p6=?Mqg&!I.Khp%A!MnFZu]r^i7{oh:_@mg&!I.phH*%7^%,8vD[&TLua'PzD-jm76pyhzK0sU&>wgF2+_!T#W'FJ%~/+8H=(FMH@sYrUY%j7LpUi1!F!W!M%^U%Qv23hll8MQ/hZpk1j18&s&~'!*!=!*&VLvb.[PtG/U25sr#RCAQV-^URg&~W9ZR32rM<(5Msgi*Ox#PC(ZBGkH.>#FZV``ti7{oI#-BOhq%A!eyhWg9Y%h7.p/kZL7b'Po9nQA(`!_Ru,V.I#(/JVCVXJUQ].YMa<F<=h2)@TIEp+e+qh-=G)?U!SEQT,YMa<F<=h2)@TIEp+e+qhh/&]2)3IBF}.GP*&c;JVCVeUePu,V.I#o='/I'lN*FO+GP*&c;JVCVeUd!",
    ).also { _portrait = it }

private var _portrait: VariableSymbol? = null
