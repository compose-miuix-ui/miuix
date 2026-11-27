// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.ZoomOut: VariableSymbol
    get() = _zoomOut ?: VariableSymbol(
        name = "ZoomOut",
        category = SymbolCategory.Core,
        encodedData = "lr`RSX*Y#Y/Z*Yx{C[YPjN;[&!t&#P('RCiD.~.!_m%P.!Z8bzedyrB)C44it?k4C&Q`NsHx8Mq9d%FJ%P.I:.'~J!ygH'/!*B!!W%p-gZA!:#'h%!RP-(/!a#7'iD@#K+RgBPi%+9oR}a8P(*Y?j#2%DyoRS(?O`p&!(#q,?!l!)1/?c:.+)1O!b)[pyh>,m:F-P`+/e)gXFk9MVgU&J8oD|-!H0hi(<::#e+Ti!/VP_g-/>#E-|Y^E)*W[c&5Mug+#M?ZRC('Kll9MTgT&<?-Q023p[gR0>#<!q68]/%a#i]0%n<-=Q7Vy;!o/@#VZD[qPe)hXZ&PL=bxPRMZRb.8H<(5MXPV-Gx#P2+[pni1!`P1~o1(s&%!@VVIW*Eq0L1aM1~*!bCGGR+_!Y%h7{d4(PL%b/(@w:F!(_!q0.+_!Y%h77pl>;MRg4jO`(0k77p6=_@Ih*'vGrQcU|oSg&4nh*'A!@#U-uD^%,8or#",
    ).also { _zoomOut = it }

private var _zoomOut: VariableSymbol? = null
