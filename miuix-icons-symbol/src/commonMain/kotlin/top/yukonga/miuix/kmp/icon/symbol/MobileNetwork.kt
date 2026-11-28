// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.MobileNetwork: VariableSymbol
    get() = _mobileNetwork ?: VariableSymbol(
        name = "MobileNetwork",
        category = SymbolCategory.Content,
        encodedData = "S9j#QX>l!5G]G~HR3P4#0L{oRgt8ua%PL3C?%~@!N4KW&&E.|!p=8#lKjr!!>.jr%~3(S3ll)!;#E%m8b!E%PL@!E%u8[!E-c6!!o%c6Q9(%U&#P*!k(@;xg_(ig3=dbFSL?Y%m7f1ml9M(FF-Dw7`G7T.8';M_gE&EWGGK0w[dl73J@E-,wRF383sb#81*F(*M?GG.83s6UYLAbYZQx{REBGjfi0Do:3,Y[Hu08?s5(^LjbYZ<%HuU!bC*PYYR9J<?3bKE-6w'G38AsK&>?'hK#4Zu028w[5(ZLhb#Y:wNF01w[5(&GdKXZAPoDSX|otF.R(,O/Rj!",
    ).also { _mobileNetwork = it }

private var _mobileNetwork: VariableSymbol? = null
