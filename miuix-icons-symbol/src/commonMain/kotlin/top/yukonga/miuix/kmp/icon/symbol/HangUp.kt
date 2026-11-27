// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.HangUp: VariableSymbol
    get() = _hangUp ?: VariableSymbol(
        name = "HangUp",
        category = SymbolCategory.Content,
        encodedData = "jDuSe<~4|kO+u~s!2fs.<P/%n5qLg_3~d{lD*O!!Y(Zgk%KAAP(3prvE_';;(#rUrRu(XPW'ku4C.IW[[!I?j98-dULiT!7m[S+6%b1~2R^(XD=5w3SYh67&{4bM1~@/Upr<9'MJ]ZSX6%p8l6.:U-@V^%ICc.0%Q.bQ{.1?UPXXZgX'[9v~y'`Jq#G0b9Q)@tgst,m[h?hW]JyP94e#f)(Vs&zAmPz',b!;w%3=En*W*P5&}D8<F0u(&:l1NJ1R[J7!0%/BX#0%x9dSmDb[e&f20uX-r6/hn*Vm!S%I2sI*[<%%c-z)][f(AP[&O7E=l6x~]S_BIxfS+Z|/))W&8mCOomKT)LVsl*):|S'=!",
    ).also { _hangUp = it }

private var _hangUp: VariableSymbol? = null
