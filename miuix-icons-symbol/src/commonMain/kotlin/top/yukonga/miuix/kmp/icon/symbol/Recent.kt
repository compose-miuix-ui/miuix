// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.Recent: VariableSymbol
    get() = _recent ?: VariableSymbol(
        name = "Recent",
        category = SymbolCategory.Core,
        encodedData = "+1x:b(RjWlz%Asb!A(h?8T1?A!^U%!h?tSkDp9`%A10<#!G1#P@([?tS|AA!XT%!l?8TB<BP`%C1@<#!T:CQ,P#P'!r*%~t&bG#P'!0!D4%P.!0>3t#!O!Li(!4!1%}(ydq#G7,!1Qh;I)QRa#xt@/!HO'sF44EVTj2:I!|6YQ|'U!d;-1AbR&/?UQ2,H[=~v!c4;K3Y_+}/*=7s[&d4-ng*{a)Q01@:j#j/j/!,->A1[;pU9TsRQ9&&>iE^I)W&XmQEh)0/&*,/.7]jR9?Y/5|WAWqib5X_]l|K{]uY3sj%#5{X2jy8Ry-[k2R%'7",
    ).also { _recent = it }

private var _recent: VariableSymbol? = null
