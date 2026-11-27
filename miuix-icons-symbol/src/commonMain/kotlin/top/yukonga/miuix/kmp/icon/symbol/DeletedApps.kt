// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.DeletedApps: VariableSymbol
    get() = _deletedApps ?: VariableSymbol(
        name = "DeletedApps",
        category = SymbolCategory.Layout,
        encodedData = ":{BT=.-EG@Yt.6>l)5#Y#lUIeqlkjr~!{o#!h7XuSgW95h&~?!npl~4SGJ(,+=nb._=_p!?#.%Rg>#:./!bI7?kQB?8];'w6XTpR%PT65tRg#!Y9Rg&~2!R{Aj)!~*Rg/!|DJ)/#r[56m#jD!#6{Sg8U8Vt]:%YbWGMj*>d#5?9Q#!J<#P#!.PC!XG4uW'0!+3#P%(2Z0,v(a#`-G.CSyKx!L(w:iD3,^UKS['S9y'>?!!%!<&Rg-(f=|/?%pRd;J0Gx%P,!Ds#!4?xGC!h6o.#!+!OMWk8%>]=+^#C;m10Vqm^UyPPXgd_gM#oJ_RA.mP'W<[1~f!R#;K'WeU5Qh0fGs98%IK6*%)I1B<pJ]Sv?`UfS5fzrt2qj_kzMIKWWM#fQe0f6dg'BC~_~N8uJj'vk`E}*bf2w2EX1a=xBrWB,]c4HL>]JG%`BxxDV/iVFJ*611Q1,TmcV]XHi,1Pm(#e![Gr9p%v[-(;W_F_.iGj9:'l9>%LT#0z3kJ/Rt%n9d!&=z)WZZxZRo7Gp{j[E|bvVbqCG/#|<ClwF<VzWbn!//#_!Y%h7}o,;K5J]y**L8u.8or<!o7Gp,lCMYhu)Y[_RC52:>U9MChj*6p0#T2|r&~*!J8v^o1fmlS'(0]IQ+~mg|#J8_R.8zr9#I.'WH#=[y#N1LPR&E5eP^U'P5Qg0e6ah?::yJT>*9%z4!7'k3?&Pi*~rhht%^1*>F<-/s)sYRs9-:p/hc(r[>%`+i#,1",
    ).also { _deletedApps = it }

private var _deletedApps: VariableSymbol? = null
