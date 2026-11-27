// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.RecentTasks: VariableSymbol
    get() = _recentTasks ?: VariableSymbol(
        name = "RecentTasks",
        category = SymbolCategory.Content,
        encodedData = "p%c<01l49@v`vXU!2+8eOR{A_k(dd)'a8f:sS!ZN)!En&!.,.R0%os^4jsA'?3[+y#=%Q9b!#P)!xht3-%FC4V,+oD<P;!Q9&!=v#!W'|!V~^g2R.ChM^i)#`x<!Ns&!Mf;olDr)U@J.,~<%iDd%6{_g+K{a;Qu6)!m8;[tg**c43&;1qP=5Gx%P)%jr_!o)r+q?x=@:.5&w<[T!}H}]%0P+dgg/h9V~b(PPtS}A@PJ#{D3!m#|Grj5?_Pg0,4j#L0s.uSX9&se!!(!Kx'JJsg+#>BE=MJj!<&^>,L/Uc`)^N1p<#l'G(4<Qv(X;32JBWkYCIt3)g`%^A..K9)DVdUyPp+fj|;R+q:|8AK4Ve2hFz2'HQ#w8|?#VsGZR29t%0:Q)c]J.`|}Dz&yji<u8K)tVzY)vI=#vkjC:hbNU&I}Dq%J?t;#6'b*Q'4fQE6{usjA?`Q#&=1o:/%'S<i,1d9j#[9F:C)/&lS@AIb:TIM]#1+2HKlmKG[K#2-n^c6}a9##4IE9'~DLiQ2jD#",
    ).also { _recentTasks = it }

private var _recentTasks: VariableSymbol? = null
