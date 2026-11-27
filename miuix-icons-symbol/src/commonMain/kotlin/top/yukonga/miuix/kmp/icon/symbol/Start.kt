// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.Start: VariableSymbol
    get() = _start ?: VariableSymbol(
        name = "Start",
        category = SymbolCategory.Content,
        encodedData = "lD)#>.!l!;!/FP0&6<s>+!V!~Y%~O{MQv,(.?`zA[!amG5+9YA!w#[Fk}Aggk#B8#PX&;[u!X&bMgk}A8Pl#jrK!LXYgK!l#D8%~l#jrLPW&fMFklDf9l#RgAPK%0D?p1m`9)%ua@!!daUAP/',D?p|o1!#q#!,D`pBj2P)%Rg@!dd>[B~)%W3BwFJ*!XTrx{Rl02:=(N500a]1S9=w@luxRyT9bkW]xaP*!p9zQb(`!Hu-88E5(TLuabRe`IE,~o9_RS2aP<(5MqgX**wiDC(YB?kd->#E-W`dt-8jr6%fL>caUxNDus+T)u(k;j5/2Px+;w:yr]SZL9b'PI8(0A(_!Y%j7k<ml8MRgq%?_eP*!J8q0N1qM4(PL~UIWNx#Pd(l?d&I.@#VZ1_m#j7{o#",
    ).also { _start = it }

private var _start: VariableSymbol? = null
