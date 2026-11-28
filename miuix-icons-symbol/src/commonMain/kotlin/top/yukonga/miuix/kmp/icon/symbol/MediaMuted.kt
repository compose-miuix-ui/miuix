// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.MediaMuted: VariableSymbol
    get() = _mediaMuted ?: VariableSymbol(
        name = "MediaMuted",
        category = SymbolCategory.Content,
        encodedData = "gdm;=.b1BW0l#lw_>.=hQlua'PS!t-#Pc<]Tm+~G,o[9p9B%F!m,!!?![70cX4V9)-!^?/t33mR&c/O[+~Kh7PU#v02VZ._<F:R+=!k&)DhbSPp&D]w@vu^P.'4B!!H(I`a][#w.39{MAQ9Alxp?9F*h!)+)X#:MT#4S^U::h^Ux/Q3!*:W'WGsD@%?!~Er,-prQJ7#se!:&p?^()Pcg**G?v%y:](8R]EjbkW-Ek%k;;{Uix?SbaRy/JRt9`M-iK.Etu0I5lR19v3pWs_ZVj^jG=#o!,7sg<&l7|om]o#g3~K7s1#_Gj?COQx&SK8&_19A{p:06o4lTq@Lu19;{SgB3jK_X/iS/C(Q&T(XL<@oXgg['9SpRs#??3/#)34(<G7<bl&B::c}[h6tg[9E!;.w:2);)WElKFVH*1^u9'Bg%X4))#P)BY9?5X9/%.",
    ).also { _mediaMuted = it }

private var _mediaMuted: VariableSymbol? = null
