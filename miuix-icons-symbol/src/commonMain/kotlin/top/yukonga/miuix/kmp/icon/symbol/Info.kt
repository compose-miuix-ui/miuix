// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.Info: VariableSymbol
    get() = _info ?: VariableSymbol(
        name = "Info",
        category = SymbolCategory.Core,
        encodedData = "nrI#O+DP!;!/r/#l^9]:l#T8a<A<.PB#ua7!hWqR9~B#i-%^j1(!-^!!i-y]D0y3a!j1(!-^)1y3a!c%K1E=#!d-vn&*2Pb~_%_J!!b%%~A!A#o6Wh_U,~A!gk&!>s]ilDBPz'jr`!rYB[b~z'ZJos*12!N2F>](3!Q!=^t8P!}#19c!u#.2T&u&}H0N<(1!W![K#!7p0<*!Z!,#5-B!x!(u%!U!GR)![!aP52Kpyh1!qM==`->#E-*w,018yr&~d(7EI#2!8Hc>z?KnE-~vs.o1wr&~d(IEd&#-@#VZ{am#j7{o&%`LMb'PaCjE,~uGZR2+`!^%N+`!v^r%YBdlWLfbVS[#w.A)u3.~n*T4ml<Mh9J#B.j~oR6bvQC(`!^%P2PP<(5MqgY*8wME,~I8_FA(_!Y%.89E=(9MRga%f`ME,~I8q032qM4(PL~UjWNx#Pn*Is6%2!3h-%2!~jll]2>#FZNCY%h7eX<(9MGhK4m2xs)~6!9KtVl2DGt7GpyhI0t3%P,",
    ).also { _info = it }

private var _info: VariableSymbol? = null
