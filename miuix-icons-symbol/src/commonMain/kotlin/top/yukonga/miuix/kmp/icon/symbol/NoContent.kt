// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.NoContent: VariableSymbol
    get() = _noContent ?: VariableSymbol(
        name = "NoContent",
        category = SymbolCategory.Content,
        encodedData = "NXxR-1l4=u82>!3#L6O3J1JMlju_,V[Q^!b+#P</iDT!Zn8/S2zP=St1<X<i!!HD%~*##Pp#3B&kR2pbdS#wRg;!WGSJ!#}Hjr=.jr%~{#X-!!I&y/LPY&]49TrD*RgjHJg9`@yg''(I%Eg~o(##%~[)iD(*0mI!f~v6/;A/R{a<j=>P5!-!K!;;D0F.Y#X9v9O%%&i#)1Q9,!]'9SbjW9%/R)xYrr)~=_.:v,Y??RU+?K&Pt]rrQ%M_9jo1HV.UyKzEe)VMX<K>]J9W*ZWts2.7Oky?O)'WQXiPT,j+,;T+-/r)rYjrI~O5l/R,0SYj'=HJ&P6!D:r)-WUEu,W.RQ9,Q)&*;[:su&5BTi53]J'*;[5^*1e(j#|3&:Y'UJphX-q(*QA'i4'W;[^~O%v6_:3,Xm!",
    ).also { _noContent = it }

private var _noContent: VariableSymbol? = null
