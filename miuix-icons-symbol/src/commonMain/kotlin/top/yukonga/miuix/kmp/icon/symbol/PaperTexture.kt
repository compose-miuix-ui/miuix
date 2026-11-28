// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.PaperTexture: VariableSymbol
    get() = _paperTexture ?: VariableSymbol(
        name = "PaperTexture",
        category = SymbolCategory.Content,
        encodedData = "mDw~t&ur)m2k5krzYg@EU!V`)!l|!!5SP<'!,.#PC%>sS!!z1Y1m{R.hl,7Sa<'!..%~/!A'@#(#cPJG28X~O(.%b~;!2FRgA#)1*Pu3u(:'(iU~)#+#:.=!Q9y34QI,T@%~G(UnxZ%!A5%~2%:s0+uS`kJ3xb,i!!6,#Pb&IE7,a~r0u72:n>LM].L#PM&FrK[p^q]2xavgmye0%(Y&DPx*#LzZ~xAU/#-Y[l'IisB,At^EO#_!q0t,`!Hui7{otg71jhA,g`/GJ7}r>~*!%71Q@.b~_R58Ceel_L'nuVPTa#g<IsR)2!'K,(^Gw.EV>f|/|-6vc>UL}aVScJBR32Up[l9MXg&!xDtb'P%e^%,8mrcQ06AnIWaJRRj7!pSg{8!",
    ).also { _paperTexture = it }

private var _paperTexture: VariableSymbol? = null
