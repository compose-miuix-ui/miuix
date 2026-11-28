// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.Recording: VariableSymbol
    get() = _recording ?: VariableSymbol(
        name = "Recording",
        category = SymbolCategory.Core,
        encodedData = "+~e!o%Y9f~!;!5#Y#l!G!5#YlkK|t33Pu!X&fM!!Y&Rgw~W&fMFklDf9l#GxK!BV|aLPX&bM%~X&;[u!Y&bM&k}A7!v!`/fgh#ex#P'!V0#P18.:#!+!7!X](~,!,>#PtK&F'~2!)1*_CMQ9%!5(RgW-W[!!'!-!mm%P,!j7#PtK&F'~2!a<SdCMQ9%!p*RgW-W[!!'!-!mm%P,!@B#PtK&F'~2!x19rlK@#A)iDi!g*,si#Q9Q9&~N7t3%P4/:.7-jXRg*!t/>sG7`!X#H#(!zQj6K!n%)1jr-(f=:.t!w:T]e)6{Rg8MMhG-*wiDC(IBqh1!`P5UCAeyU-Gx6:S2zr&~H)z9Y#A'i4'WI<fQe0f6%iS>iJVP_+7;!)t+R&0?HP(*D[=~U!N0kJgPJ'|/g0:SzjJ>RJ5SC[1~f!j/uP4&`+RF)1qUJ&K>LJ%S)ZRs,#?4KS!/i4&*=[n~(*D[=~U!N0kJgPJ'|/g0:SzjJ>RJ5SC[1~f!j/uP4&`+RF)1qUJ&K>LJ%S)ZRs,#?4KS!/i4&*=[MEq17sA#2!aPdSD.@#VZNxG;m7/pSgd-XVaR@!35wVY8!'!C{oy:@?/Q~+LTRFB).!O!zQ?TNFk0:7?k7R64(PD!N#x*5IRF,1]9*QO+Rg!!X-]JkT4Ie#f0DM@#U-Gxn%>[(#P+z9S&p/H#:.%~BPt)~Yos@(.7qh,!J!e#!)K!i#)1b9TS3?#P%!`-gJ&P6!r!K&W-3!d!n%WxR&;?)Q<.}Do~(!9!2N0mrDt~K&",
    ).also { _recording = it }

private var _recording: VariableSymbol? = null
