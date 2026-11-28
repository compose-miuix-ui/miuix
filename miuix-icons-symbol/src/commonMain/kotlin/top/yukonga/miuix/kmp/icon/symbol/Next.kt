// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.Next: VariableSymbol
    get() = _next ?: VariableSymbol(
        name = "Next",
        category = SymbolCategory.Layout,
        encodedData = "Sg-%#Y!>K!V*5/!Si]_Fd)e6=ij=6?^!I0FJDS0WI[<#`XQ9D.,sy*p=Di](??CPh%ks%!sBvbb!i%(*oUo=8Pg*,4ZQX#VF(&d'FksFN.z)jP))KQ_@[[O+EV{V`B%n[TPFht.>Wgll;M6]X*&z@]2+l<_n4aJJ9P))G;Q8suUiz?XVJT;BY%N1i<]lqKTPH*>_!",
        advanceWidth = 475,
    ).also { _next = it }

private var _next: VariableSymbol? = null
