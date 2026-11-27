// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.PageDown: VariableSymbol
    get() = _pageDown ?: VariableSymbol(
        name = "PageDown",
        category = SymbolCategory.Content,
        encodedData = "+_q#:7*hsQEb!!o%o-bz&*&&6ki6<:#&{Yzr=(A6KdIewr6QX900<(qdS>3,JJqR`uX.e%!boPk-u@&^kuUQv&3?QQL7n)=[.!X*hSdQcs5&]xi#68,]Z'*7EvN8su7k_@MhJ'<Bq#o1{g[lqKwP'~i@0]7Zdum#32Gp<;06lb9W8",
    ).also { _pageDown = it }

private var _pageDown: VariableSymbol? = null
