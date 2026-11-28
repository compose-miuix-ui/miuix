// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.Messages: VariableSymbol
    get() = _messages ?: VariableSymbol(
        name = "Messages",
        category = SymbolCategory.Core,
        encodedData = "^(!&7@%SG!=((iHT-8dGb~&*S[8'y33!_!2Sih[!)Eh:0%j97GCCa,?/9)S6lKB(!d!!Z(%~d~a&!Kos`(a9o#^UK!&kMJAPi&sJ%~m%iDY!@%fA8TMX2?J5IJp_I.Xh%(K)]Xfg,i)0%:H'c4NsP?]@ug`%f2kF43B?&%w8I)mQ>*I^].p<4(PL1b%VXjR:o#(:CS38Vsc)L[)ju9&7%;-(|E,.U`s:^5tG^i41=/]+#i{.G)UPO'-Dam`Uggh!6!},QE/%<!gVmX[XXiT36M`:e;<V/UoN00F/",
    ).also { _messages = it }

private var _messages: VariableSymbol? = null
