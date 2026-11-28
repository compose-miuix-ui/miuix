// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.Xiaomi: VariableSymbol
    get() = _xiaomi ?: VariableSymbol(
        name = "Xiaomi",
        category = SymbolCategory.Content,
        encodedData = "w3D&>.~=!5#l!G!5):@P+#ig!!%!~=!!8'[9#P0';[>!XuIJ~[~(M<wz#!66%~g)jrs&n6Q9i#FJ#Py3%~%~J&Q9-'0%(i_.mXo;[#N8#P&!tU#P'!hM#PQ2:s%~R&Q9Y#zA(#](Q9##:.TPo%FJ7!n%iD|!M%K+Q!M%)B[!XT%!F*&~y3%~!!h)#P-%?.Q9t!:.#P3&c6Rgg)FJzQv,:.t!jr(PT%C7ll8MBPE&%>iD!)%&5i&(a:E-1wRFT%B7B><HkD6!r!C7ll6MDiB/Mm%%h/5K&*sY5t..Iw<H[@B7dlcI1?8~q!J&ll6Mag;#3-f^!67_ah=,bKFZ%w)ET%I&2>}H&P6!+(dKVZXxT#l0=74lkIhb5VUTDuh7kdZ9q!H&4i3Djx&P:!lE!&;!(#K+Ug_:#/Q?>%i((#K+RgiPU35MSg*/Jy%S:!&#<Xd];/_.Y<|g.!R!XiT3fM7'iD%~Q#-DGx&P2;pt&)R!)%S3'S7'iD%~z#uDI~#!)!3;gs*~:!`)=X2;)%S35MMilD%~#!c(TPz#/!S!D0(*R!)%S3",
    ).also { _xiaomi = it }

private var _xiaomi: VariableSymbol? = null
