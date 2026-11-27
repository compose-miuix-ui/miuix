// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.Phone: VariableSymbol
    get() = _phone ?: VariableSymbol(
        name = "Phone",
        category = SymbolCategory.Core,
        encodedData = "{Al&~4|kT<v3w[[!l%5%|F5hPRL5j)Ed>POCKlo%=i@%*y'hq,A0DI~A~i)#OWu`X?KkV~qd0P=,H4/E`/6:f^gU;73tZu*>J?(c'Z.I'_[3Ke/!f^Sg9)Jh'PMP4(v5p`%P`g3#h7U=_UpD%(yXbjg/[E1)Dc*s|4du4;a&KQ+.4w:FH)yMHm2KJyzWCf@0}-yMu(UL^bEVxG[G'7luPhS7UxPRiGk:].&e5=T@LPz'YJYQd/_.4Sm=]P@RYMj%k0bM,l+Ptr2,y%:Q!(Ne%neNv!s#+4^Qx-&e)jB5j?O%;*R*G^C1KRI/S)Q)vY7!ESWGNPe&4Iq]P+e1c>jI.@#YKt80v2g18?6R!",
    ).also { _phone = it }

private var _phone: VariableSymbol? = null
