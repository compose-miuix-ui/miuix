// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.Notifications: VariableSymbol
    get() = _notifications ?: VariableSymbol(
        name = "Notifications",
        category = SymbolCategory.Core,
        encodedData = "C<lj5,a4;4EF3)y=8&d37t9Q2&6?7hv3Sg+!r2g_]lW9Et^1P%8Py+=-]P-#.,[|[|*!=P7%;[U!H#trV~;%592&L't;[%g(%`K%lGK6+,{j{F1?UP)BXi>(lG;lmKkD'%Un|9Z~3?ePr?#cGM7'v^wnL+`'Bava1Pon;[)!5#7%Qo](.>i^T9^-^eT9s5~:1Ed)ddp!|[:MHP,#&R+1E<B:I*)1B!b)oR6R}.U)Fj2Ft&i#Fj/h^-aVhZ>wE1;@Ae?=RAu*,bl2DGp7:p>=^S64)P{91=@;VB]lrKg]W0dQmtM)>'rjvNw#t/Ut]0,1Vx[!k#K+<iD0xatiH#@.W~2%PHX8epelR0bK6*58X#`/H.d&qKagE&@w?G&B.Q^9)#l+A96%w>W~MLoD]%2e0<T29h!S9'9~t!W&#]x'sDo:k1/?w~s,R.o%+BEQY-(v?'(Il[v&PFj^V3L?2jH7M?y'<TiDb%9Ze0]-&K4(/Nv5gd!sv#,5Vk0p'b?Q?+MT`/N+B[#P(!igqEGxh+'72%>k|8Lyc)F'{VhWb91%84~R>=~M+b,Q64c%%A4TqE=vh+x73%9UT!R4*P};0&djPCSED!H#:.c&?c>FF6/&Qh4!k!",
    ).also { _notifications = it }

private var _notifications: VariableSymbol? = null
