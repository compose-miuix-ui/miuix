// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.Google: VariableSymbol
    get() = _google ?: VariableSymbol(
        name = "Google",
        category = SymbolCategory.Content,
        encodedData = "pR@'!Y#SgVP#;dixHxAP(%4,V:]N<8C!vKH]'~P,;['~YQjr'~X%jr}.:b!!b#K+wEPL`(BPU&CxU!gk)!jABj@.Y!k&#PY!0mW9Y!k&9AYb<[0!}HQ9ag:_Y/>%)&d!,lFJW9_(%PQ#1%>[>DBjs5%~X&jr@#/*8]yT*!H]!!3*@]yT+PaPV&h?%~WxEj_5kjM=SE3b<Qr@Dui7zASg+!G!dt-8!st&3P`!8RVLuabRm`_FC(_!Y%02bM<(5Mcg#)Q.^PI)RPR&j*O)XWO#;sG/3H4(PL9bkW]xaP*!p9zQb(`!1jj7[1l>6MG[?(Rx#P01aPllx3I!a#D0S~4(ZLec=_cnG/.8jrI#-BOhq%A!eyhWg9Y%h7(pY<EA'n*.g|ltn1,)CQ(4Zs>(C1^%,8yrmSZL9b'PI800A(_!Y%",
    ).also { _google = it }

private var _google: VariableSymbol? = null
