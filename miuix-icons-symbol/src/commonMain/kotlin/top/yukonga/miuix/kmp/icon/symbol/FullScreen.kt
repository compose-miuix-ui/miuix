// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.FullScreen: VariableSymbol
    get() = _fullScreen ?: VariableSymbol(
        name = "FullScreen",
        category = SymbolCategory.Content,
        encodedData = "'PI#>.!l!G!5QBV(^Ak&U>uab#rYiD32c6Sgh)FJjWP[#PE&Q9&~S2Q9&~E&a*0LPL>[?PG%:.L&+_0!ieYu0!_c#!C7&~G%R6ieva0!qm,!:6!!K%R6!!H%RgL&uKa,f`i#|;(!m#/`M!k#0<(!s@D`K!D_#PJ!xQ(!7!e@MUB5S/5-YuJ:.7WjZ9r!{jC;W#Y/f-zy}0p8HxZ906_mGP0&ZRk7#k{>ZNlrv&fuY9k!r@m0n*K&R>+K[?6-YuY97,&y{Qs!P&|lC5Y/f-ny4].7]jZ9r!{j3>KHZ]i-/z}0t8%yjTUJX]8-snn^M70h&U8K%tv&wr-~H~x1wVwSe#i0'7E_EUvM4m[TT{dhJ!h#}0j6Vbt>&N?n)ZOtX0`4u[bT[E&F8-uugRs8zxa'",
    ).also { _fullScreen = it }

private var _fullScreen: VariableSymbol? = null
