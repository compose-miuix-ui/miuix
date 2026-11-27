// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.ConnectionSpeed: VariableSymbol
    get() = _connectionSpeed ?: VariableSymbol(
        name = "ConnectionSpeed",
        category = SymbolCategory.Content,
        encodedData = "TgS&bU9Sbb!/DB#&EI_{~5zaMPN(jrw!xh+P_~1(*6~U0?,S9?l!z#(!6e;;1#p+amzA.!8u!!v-6{iD3~]!D#v-!!D##P^Pe%m8,X&!i8%~Q%I5Zoe6_9-#Y?%P~DChz->&TSqDlD>~:#W7!!D#!!hgo#y8<yGJtrw!`/@.%&UXtjx=|9D&9&~%F)lx9PN&%E>%l@k.9'07ph|TMJm!TLrbXZNx#Pd(sJ_%q!a&hk^9R#1]ok7tV,|<4(PL9bjWNx#Pd(IEt&I.@#VZ[u,#}-DHRV%Ih!+>LX4:Y*8wEE,~I8_FA(_!,<2+n?(T68wsVZOx~Qc5QM;l4?+E#Pc6HP-#P4U=9,{VIW#7Y%h7BMj<hBya0XwdfQj)YbR&tD?h0%A!MnGWw6Y%h7,p.=ZL3b'P54W;i0<&NiT0AK9W6!!^X*QTGG-8jr:Q_@og&!`Lem}Qf`&FB(y[4(|?*c4SqJr%d),16RzT_((ScX!",
    ).also { _connectionSpeed = it }

private var _connectionSpeed: VariableSymbol? = null
