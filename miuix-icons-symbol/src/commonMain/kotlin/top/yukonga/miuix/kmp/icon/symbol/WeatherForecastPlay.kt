// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.WeatherForecastPlay: VariableSymbol
    get() = _weatherForecastPlay ?: VariableSymbol(
        name = "WeatherForecastPlay",
        category = SymbolCategory.Content,
        encodedData = "Zuw:`(#SP'z4-9&klD9~u!A(!!L!q,zaK!a%>8[;-8-!0<#!>8K;O2sDA#WG7!=UkDf9`%@8K;R9-!??jD~gn1EEmi3m}3Q!t1uaxV}YM.##/e{V.!Q!(eW'~+r<F.*#]X>Tu]94oRh:L<)/@%<!,#B)v65%X4De6=q/:i5^DIAvQ?w6?%OXg.Z-fx.R?4;1x=DT+EI~:1C/5&F!2*A]Yj'S=;+1q:YEQ?(PDw0uY@UJ+QxD0]R%H;D0<':v~n<R35F--",
    ).also { _weatherForecastPlay = it }

private var _weatherForecastPlay: VariableSymbol? = null
