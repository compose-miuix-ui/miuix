// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.Menu: VariableSymbol
    get() = _menu ?: VariableSymbol(
        name = "Menu",
        category = SymbolCategory.Core,
        encodedData = "'PI#>.!l!G!5Rp3)5A!S)!A!U%U-QmE%U->!5%[;Y!5%+K0m!VSJ!!F%FJ*B|.=!@#G7GxwR(6GxE%2FiDT,AjQ91!z(F%*!1I%~!]b(>%,8;[(~=:Mb%P)6:.(~2I:.(~?!u.j'1!?p%~rK#P%!'!4!?;k7Op`k06>#r)f=Rg#!EF@uL7am?R1!K!d!~%a]vE{3qMelVLFJ&P,!?!q<rK>]N+uGM%D0FJRg'![mD(&N3nlTA!v!q#K+Q9I'IfM%i6`+XkLC8s*##EuP)!=!<@2Y4BPio2mGj<3Rt3(PP?ii0+W1(THCF:|.](%PN*@Hr<Y2.@xW_@9%y4LX=iQCLs?%z6%%**`!^%,8!s}SfBLm@R&LbE,~@!>#",
    ).also { _menu = it }

private var _menu: VariableSymbol? = null
