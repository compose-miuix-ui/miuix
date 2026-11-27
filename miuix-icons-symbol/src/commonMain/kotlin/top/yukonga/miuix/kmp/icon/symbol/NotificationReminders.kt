// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.NotificationReminders: VariableSymbol
    get() = _notificationReminders ?: VariableSymbol(
        name = "NotificationReminders",
        category = SymbolCategory.Content,
        encodedData = "qRb<w3GFDFqXVgE&`IWOg*xa=Pg%V.W~Y2I[7Gf&agBY,c+Tn]oFZ.Bj<i31p9_%@-`'3t,!`'9AC[H#?.{g7~j!-S@?(ZAt=1N%A~M'~'v9W~9%}8;|jD^9,##P%mj6*Pk/B:<iw3M!9z!!8!Jsp%p(Z),#iF(!#]#@UY5p|hn7&,8kW<C%X09;!_b4NEATu8P4YT}AG[(#U+#i0,XJDS|KgFu2?Hn@?aV4yV8l51n7C&`kA:Hc91x:o9D/d+ihe(u.c&jD+!E!/*n~SSQ+3/GW;{EQ42zG,;NC:]4)JI62uFGP]&[>`KNX-BqPN%'!#P&~(!!!f~&*1~~~M+mui#_-esw*j]8].+{dmP#/[[:Q2!@V#(x8Q)iW8M/hz'YJ6RM>9?%SnN!/,~?",
    ).also { _notificationReminders = it }

private var _notificationReminders: VariableSymbol? = null
