// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.Date: VariableSymbol
    get() = _date ?: VariableSymbol(
        name = "Date",
        category = SymbolCategory.Content,
        encodedData = "vRmQY-fx_R6?EWvX!Y0GWY:I#c#lXgP(g'^EK+-Q94lg7'w3uD/!|[#!UfFk2!:.*%n%2~uSSg/'zfk*pX41oD}[#!}y!!J)/OX'bY6k^gF0_+l/*P][#!yy%~=%:.V~}h;/D4f{5;:,)+-579:.z~Y)/tS,K+1!P,6{n%c6!!&~=..s%~[!Gx!!E-ZNx+Q,U{E%.R&!E%m8d!M%Q9Rg^(VJ#P[!Gx!!E-ZN?WP,U{E%.R&!E%m8d!M%Q9Rg^(VJ#P[!Gx!!E-ZN;Wvn9{9j)!{'Rg)!D~#P&!3![!o%*!|'%~*!~vjrH^!p9j)6t3?!~5Gx^(FJ!!%!X97h#!9#Rg&!PLGx9#9;?G%6:Ea'2KAbhP@!V]W-]x#PO#v[h'JCOhQ,buDP*!A!Mn}QZ5X012Jp2>/K'b'P@!?QE-<tP]T2Db6%?,:cwZ#z}]N+cUJQb-rEl+xDAQU-Jx&RNE{(rR(LI^+#W.U%X&,#nXHjj9[-`xDP4%'K*QD81%:40S*jG78m9TCj6:B(J4HT{32:=U6MSh84~Rx:S77nUPk(<#l#VPwk5FF.?(AMcg+#&e&:b-*#^X*i&F5-]u[;3FZ.a%e+eiN+X.1j,8;E]X!i{.;-.vj9u%}aVScJqiN1Up[l9MXg&!|8cyF-~vF.i)TpM=,6'5'Z!wiD,~S{PP!(gm''n6c:6-Ix#P*!U'GGI74b|&GHMbQRm2CG-80~z~M(aN:sI*U{Din*5hL(a@2F;.1~H#>.1~O!e&gN>sv&I_9jq1WbL(aN@]<.0~S!_(4PS!{#e@os:'=B8<r8n?NU`N>sN+:~O!/%4PH#;'>B@<y35hMU`Ncsy-K{OhR+cPd&_2IQ;.E~IQ;.0~z~M(aN:sI*U{Din*5hL(a@2F;.1~H#>.1~O!",
    ).also { _date = it }

private var _date: VariableSymbol? = null
