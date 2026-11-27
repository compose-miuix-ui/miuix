// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.MultiSplitScreen: VariableSymbol
    get() = _multiSplitScreen ?: VariableSymbol(
        name = "MultiSplitScreen",
        category = SymbolCategory.Content,
        encodedData = "&!e!p%!5#Y#l!G#c%0fjJoRgA[W!myJ!%n!!J,Jo!!:,#P(1;[W!D%r5E^#!}8#!@%RgX!D%ZgW!&!D?L%Z1,S.!(1#P.!K*;.F|#!Q%L8a!Q%wZ+!%jS8^!)9%~2FW_+CLXs!!C@<b?>c*.%~1!*y%P1!;;#P(N!W'Pi%#P,&:v|>1!:+Q91!:vR91!:;Q9iNZ]&!)%;[(~jN;[&!))bKIT@!>#E-/wHu.8jr&~R+(s&~*!bCGGR+_!Y%84&H<(5MRgq%`jK;01y9K&T>;K)W)ZOti0.7LSX>5nqR@!>#FZ(wH0,7y9K&T>;K)WN[i#.1z9S&5KuP(*O[i#j0/7'k8?sPuV)ZOti0.7'k8?sPt)*Zi#18mg<(5MRg*'@wvE,~@!yV)T@!>#E-.wHu.8jr%P'Nnb1RZ5CGm74vel8?sPt)*ZSt-1z9S&9?uPG-jnPu08sr)#d@*Fy-Y[e#j0/7'k8?9KuV)ZOti0C7{jS>9Kt)*ZSt-1y9K&9?uP(*O[St-1y9K&T>;Kt)",
    ).also { _multiSplitScreen = it }

private var _multiSplitScreen: VariableSymbol? = null
