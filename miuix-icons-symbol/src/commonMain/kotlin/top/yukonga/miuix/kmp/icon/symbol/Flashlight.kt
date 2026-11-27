// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.Flashlight: VariableSymbol
    get() = _flashlight ?: VariableSymbol(
        name = "Flashlight",
        category = SymbolCategory.Content,
        encodedData = "mD;Qy,oggVL4K4!>Ep/QH*/C<X9!f!`G!!EP}S+!E!%%)!3!4,%~gB^)&Pu)eqW'j(}P:h+W;!&n:V{TEm*kP8:B(:9!5#Uf#!])V+:!>(!!/!4V%P/!g,Q9F'SK=XQj%Q(09)Y4x'f)V:t,9!NJ%!i2y}O`s#f-`/2!gZ!Ac%L8ddX*ZN;[`!ZNcF{-w:fgD%W{Rg*!-#a#(!V>%~%!zA#P/!YQa](~X/RgJ1oXP<w:D./%vRQ9['n+HhZ&G)2VF5m#I1(,k>ZN!!FPn/zEL+9_jQL>|rp%[JDP&!-/g:s&[#3;D0+4rTcIiJ'Pg+p;q2a<'k61U/+.oyiDL~R'3Gc68MH<p*@:RV)W6s;.%~d!d)QXggL#DBw:+!hJk)=YzbPXngI%H67e`k5D_y7^C8:^x28K>n,1K)RV/Wp[T,[XSg},tE.(P5x/F0GJi#)1W)}XkkRsu2p<k<:=:L]_e2J^%52YTlEAh)}Xq=x;B6ed=ieG#:6!FAj)pX8bF&9@CpHkYJX:%&z/40`/L+-Pt,3KBVhUXPT,}RR9<,{4kTN5m]A/_G8#q,D:y*Lc8]b(h.v%x:rrh~|,jE,+I[X#d/Y.#",
    ).also { _flashlight = it }

private var _flashlight: VariableSymbol? = null
