// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.MultiScreen: VariableSymbol
    get() = _multiScreen ?: VariableSymbol(
        name = "MultiScreen",
        category = SymbolCategory.Content,
        encodedData = "=.JQ(*!l-YE8`?`U+p2~]~G%>.L&*_0!ieYu0!Cd#!C7&~G%R6ieva0!qm,!:6!!K%R6!!H%RgL&/8gJiD,!6*O`s#91,8M!v^nCW&Ta'!.o%~8E#!(!J*:.S2}9R9_#!!nY:b'!;G.+@)EUvMimzZC{VgJ!=B_:k!/&n^I7RbR>+Kwa8-Yu_:t8^x-Ps!^pl;W#8cjZwy;G*7BjZ9]2f[H~CMlhi-ny9j2,;)-UlKf[(->t>.L~=I4]m~0&4<O8^p{>ZNTgjZ)zY^.7{j3>@Fi:C,9iVRN7imM(hNssC,Rtf^)6]pulzKL]&",
    ).also { _multiScreen = it }

private var _multiScreen: VariableSymbol? = null
