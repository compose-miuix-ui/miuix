// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.BookmarkSelected: VariableSymbol
    get() = _bookmarkSelected ?: VariableSymbol(
        name = "BookmarkSelected",
        category = SymbolCategory.Content,
        encodedData = "<.Y'X@&AFANA&YP'N8qL=olUyP>I^I#5-qSliW:m(PI%:.`%5M0!^HyR2<&;MhI':IU^c6V95Sy=L,84^%(4R&JJ#P.%Y-'hLXpr`@4E,&E'nEK+Ojhhm1Q?T!y%l!W7!!_-Q9]~e69PZ,tjDC<I^hv0{h/sW~IQ2!Ncz)EQ&!4!(#A)hGX#Gx'!P#{.<!D#y4n%:jb;Gxr!j1rxdSX@rrB)YgS!**5bM%g6sgC&c@O/X-kU#PM+nrU~!/tx9Ti9Eje0w6q<U@C[k#N[v~b)i9@#D)s(+;q,/~QQ~5+bIT?FzER,/p<iT2ZPc&?5g.<(0_w:e/f[CSj=Khk!'&.:2&e6D;%G#F(-@tjr)~)!?!c&aN{s!,lXt!3&C<Vgt&4_z9%/J4IT9Fw.)*T[5%_4lxDPt,mDI#kUP#K0-ncVSX::#0*1R9,!N1#;:,+n-U#;h!=%O2[[](0m)Qz3XPX'bG2:#)D_l;-1*P-%d6f9>(;1+;+1f9zT:!A!C!H#;[-%fGr)uYvr-~D!I#B._R9fxtB5R!",
    ).also { _bookmarkSelected = it }

private var _bookmarkSelected: VariableSymbol? = null
