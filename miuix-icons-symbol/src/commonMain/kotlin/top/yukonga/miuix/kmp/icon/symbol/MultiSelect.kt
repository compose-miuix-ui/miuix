// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.MultiSelect: VariableSymbol
    get() = _multiSelect ?: VariableSymbol(
        name = "MultiSelect",
        category = SymbolCategory.Content,
        encodedData = "x3J#>.!h!G!5#Y#l#cdF9MO.Y'5MQ9*!XQvE(~)6:.(~2I:.(~?!u.j'1!?p#PV,*B|.=!@#G7GxwR(6/?2#2FiDT,AjQ91!z(F%*!d5#P=(cB}(0!`!,(,8~[i',8Y!G'8T4#l+AjR9~:-h#!T,iD<P1%m#F*>1n9N%9?^%&*&sW!A%?;N1u+ZjH0uaUS3_qr`!ZQ2&{.].C~@#z9K&g;,]6-;[!!%P+!2]F-:woFW,_!e#b)FJ#Pb4ay7-qu_:*!7!C!P#p?MbOXJfCGk7c6Sg'!1!x/J7`m9TK58#b)c6iD'~mGb%R8,vu;2!L!x!-%^U,#Z3]PK>}T_JAUz/;`YAfd)hL:h)Y1vk|t9:l(KQ^9fEu&QFHuI0Z~1=^51&a'3RGK.XndkgP+O[&~)!o@U^l*E4M=dBz?l.7'jD3+4)&RR&mDD!=3Fc}.](zPw2~GL;^&.@eS<!#c~+,wlt63SJ:Q]9{g&!2!g_k>78isxWDcT]j7{oSgx1T4|Tq9>:_&m&IAzhePg!H3-5|T'7._z4]jClUSS?bUJ?z%k02B[!%",
    ).also { _multiSelect = it }

private var _multiSelect: VariableSymbol? = null
