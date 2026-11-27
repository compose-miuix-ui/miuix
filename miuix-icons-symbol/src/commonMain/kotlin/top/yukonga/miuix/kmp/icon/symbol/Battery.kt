// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.Battery: VariableSymbol
    get() = _battery ?: VariableSymbol(
        name = "Battery",
        category = SymbolCategory.Content,
        encodedData = "2m4S.1l49@:@.@a]U9R:0#aJlTjtj^^,3HOG!!u'Rg;!s'g:0#!K9Z>eR%W,Y'0#7#RgB#6{)!0mc6NReIkmb,MJV.=!5M&!l+ZN3m0m}He0iIBnq't8mr9r!!0N%~Q,57zQ(!5!Iz!!rD~~#!?!v97'cUP#L+*P#P1!<(#Pa&gJ8T#SR:~(hd&~U3xG&=bGn!c#x*l/?/L+SgK!:4oR?5x9d&KCM~j#gNosN+}dC;Z-(VjQN+u(-%t3,Pe!U2UEW-T?]SkBiyyZF?@0{-Dpjj*=`?{T8P)%s2J_1<]2J#0/u,srM~5&E1;;R+a:x1?n=X<f.F)1]9Q#P7T45S+47;e0c6Sg6%hb2VtKS/A/n6lg,!EBz<T>)KJTN5't}(}Akg-%t3#Po%0PN%jG0<F<cP8*d`i#)1U[9Qs,jr%~D)L4!V21Chb)6_+;b/[9e!(!7!^EA)dUv~h*A[v~}.7!",
    ).also { _battery = it }

private var _battery: VariableSymbol? = null
