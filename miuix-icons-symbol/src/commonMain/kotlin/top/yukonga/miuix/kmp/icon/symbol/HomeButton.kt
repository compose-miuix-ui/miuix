// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.HomeButton: VariableSymbol
    get() = _homeButton ?: VariableSymbol(
        name = "HomeButton",
        category = SymbolCategory.Content,
        encodedData = "lr9'l](2W_;=*`%`QHy!e%7I-nwMc(jSi@e4*><Y_g:YpU|PH!3-#P|&jr]!Q2d.81]ELF)1xaKW-P%#q#v1lK!!{*!!^!t3<!,#=ErQ)WjDd)-#v~wPU'a#Xu!!w2!!<#]g]#SZ4H0WMX0P'@-)zS{(>&ml#!kB%~Q%j5Kl#!NB!!T%#Pq#~1:>~hT:K4(i&s7%g!;7%~x&;[_~9'!s5+~X7B@DmV[_9'P[]!N2.!(u!!8FY1XnG/#4n.)R_&)A{.c6*!sF#!1-(#n+s&[4o._+-I*:-##w%Eu%Tb#(CHvbSYTxiP/#,,<(5MRgg!}%W/Q,rd&R%6kgg~4%w.<(5MRgV!b&dKVZurdhu2X_};n/m:E-#wiD,~T'CGh7;_u;DAf?gS1-r^h7~oSg1!+,<(5MRgg!}%+tU-,pzQ'6*s9#}%o:U-Gx#P/#,,k>#I-hX*cnEEO#v.4(PLuaWPT'r^i7C_?kFHObART'CGh7{oSg|%m:E-]n~ES22b1#<%55VZIx@Pi)Wp:>NCbhF-*wyDO#_!Y%h7~olgd-(cXW6I,u)7&p|g;%>#E-~vyD4%1:4(1Kh?kW4p5QC(,,ll5MRg&!}%o:U-'phh835bj#k(*FU-Ix#P*!{(Y%h7{olg;%Kn&Zbn1^c5]jqh;%35E-~viDO#v.#(@HQb.X+p*:/#,,<(5MRgg!}%+t5-,pLi'6LmI#}%o:U-Gx#P/#,,[l#I-hy*cns.O#_!",
    ).also { _homeButton = it }

private var _homeButton: VariableSymbol? = null
