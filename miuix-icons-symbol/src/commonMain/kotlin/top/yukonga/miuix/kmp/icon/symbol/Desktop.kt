// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.Desktop: VariableSymbol
    get() = _desktop ?: VariableSymbol(
        name = "Desktop",
        category = SymbolCategory.Content,
        encodedData = "_(`%UX:7#Y#l!G.#dQg{~HO%u0w::!U^C;G&%^%!Vg=#iDMFt32?+P8%;[3~6%u4%~~#DL%^%!F&O?v!J&qm(!&k1Kx[I4hkX9I*V!gVpE)WI.`#%3MM!!mvt12YNx?_oq)!}8Un9!%nRgA[X!?%Q9b!oRSg!G]('P,%Q9t,Lm!!<!WG#Pd,gk;['#n%QB5i#PA!T@&!AIWG&!LH!!{2#P.`a,d3.^egA#OEs)vYjrA%=Zy]@'x_=@)(?neS1&%119L{;>(IwDX^B5v]3>!Ha:6!rPe(GHkVyZn6Xhs1Wb+>t?&V#VwD0i8-k<c&T@U[k#/_~9:Mrx6kKHky<P4-<<R,H&iT}HEE=..~1!,F#P?nHWf_&:7&k<7::,v!-=I7%KSV|KoFl7,pSg1!lm5%2!Js=(>?o:>.&ev^18Ms5(bLjbX-Gx1Q38(s&~*![5sFH0E4|&ULCbUV=W~Qo8Zx<S0=Hy)W54Y%h7Fppk]LhbYZexZRo7[p=(5Mj[F-~viD2+ZpUi1!Zpel_L4cH-;w7G38Es@'2!IE7'p=jE-+",
    ).also { _desktop = it }

private var _desktop: VariableSymbol? = null
