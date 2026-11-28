// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.MoreSettings: VariableSymbol
    get() = _moreSettings ?: VariableSymbol(
        name = "MoreSettings",
        category = SymbolCategory.Layout,
        encodedData = "?[I#N+<P!;r/KV6&?;0LkW|aLPW&bM%~X&jru!X&YMgk}A8Pl#jrK!LXYgK!l#@8!!m#jrLPW&fMFklDf9l#ua]Pl,yu-~<!QKu]<!.#p/H!0#0,`.>%my&!yu-~<!QKu]<!.#O/H!0#0,`.6%my&!%v-~<!iVu]<!.#O/H!0#0,`.6%](HEl&e-@#U-}ay#.8jrA#HArbrR@!cyWWA8Y%h7Y{CldIFJ1R5P##w&O?)Q%!(BOh:3omMU_2L#Z.dGn%Q9?!!&z::.{#aNjr:'`<N:%!rD(#|36:_(44M%c6E.g%s8qg9*7wME,~I8VFA(_!Y%j7HBml8MRgq%?_eP*!bCi#](!!jr,+.7{jW>/zu0,8Os/1y9I#9?DP%!`-gJ&P?8Ot@(J!e#!)K!i#01uP&*;[Y[u)*Zos)~*!!!IQ4?uP%",
    ).also { _moreSettings = it }

private var _moreSettings: VariableSymbol? = null
