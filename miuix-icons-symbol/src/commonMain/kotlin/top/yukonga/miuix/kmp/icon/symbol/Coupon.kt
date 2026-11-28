// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.Coupon: VariableSymbol
    get() = _coupon ?: VariableSymbol(
        name = "Coupon",
        category = SymbolCategory.Core,
        encodedData = "&P_(-1s/l4M0M0#lFo|h5wO[u&f[jQq*Y?U-&*&!5-&*M[X#}HL[~R#!7'CCR@1~y%-!z9#!(!Q%<i`/Lal1)V@*{?[?qQ2%jrH&@</!0LI<D;t1kyFSh@(01L@</!gJ@<F:(*Rg=#C0)!!3ZN<xU~e'G!YQ'!5!--A)>[x7l]_s::5:cQt18X<i!!bY#Pk%-M++9!(#1Y(!`hM+?!P#U-;[%%X4^U}!T@{sH*n%Sg5MY[k'k`5^w,^P5UPLua?X(l4u!5wjZjO)>#E-8t:FC5Pb6U]Lvb'Pf#i0`59B?kBH#VxP@!>#E-#w'/x,p.1TB:*c7ZOx(P*!&e0<i6r[YT);X]U-Gxv9F/__LlBM^h>+A!W/5-'p8#f/#VORm/k:U-Gx#PR+Q91#w8F:(*`G#P%~#!Y!J&N>3KOU?8p!S!n%:.a,_nG//1)V7R,/-5q,jr(#q1ug)#`9-QU&?!r)!Vz6JRb5?7K>KJ#Pi~m/D]G*mGd;#6Tg#!*!S'e0#6wUg%kBE)[TZ<JRB6ddEiW>v?AReQpt')Z!JRx-PBJ>NJN]c,Pqjr.#HSn=eB.y=X(i#].#Z!I%B6fX4(TLjbvVI8%Q%!-!5ES&m2Hu086s:QJ0t3%P,",
    ).also { _coupon = it }

private var _coupon: VariableSymbol? = null
