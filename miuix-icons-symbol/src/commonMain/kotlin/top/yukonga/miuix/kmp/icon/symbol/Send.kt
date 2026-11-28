// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.Send: VariableSymbol
    get() = _send ?: VariableSymbol(
        name = "Send",
        category = SymbolCategory.Core,
        encodedData = "}o~i{3yF!GusWlttp62P*#o.!5=.14-f]9EnK{r+'WlQIM,lE<[j,/T99k:.8;:#3(+;+1j!q#|[(!4(Y^OdK+XJX9X!k6K;&*C.i96#53Rg.fa[D/s'{Fx'=?.%t3Q9y!0mJRz26BB~b~B.~9|'FPf~o&a2CKtF=m^%@c(]>5KP}Px%A~0L6M&bUBM#rE0+~DEf#PT9[[iukRu9k6RmR<,V8ZV?tR{417;>w:Zsl+K[5%]4&Vz#U#1QS&q@)1g6Km}!d&^QZ.ur+/H)Z~Hi'7ejJmARc4HPG*`t`.Rp!lU>^/;+FFd;V!{xlh))FVh'015QwZtrthh6b':>WG@:q]fn6FS%]~W;i)`!5%X4u(('jDFPI'Kf(uu%}+u;R7tbTV}/-jh7%p5iyF&VEVaXe#|4odvi-6Uh|[1~@:3,%kMUWL3h@U6M'h2,UxQj3?9bW~O*bp%E!dwDL'v@[!3!d!IM??=[%b?fKmJR))F,b)<M!",
    ).also { _send = it }

private var _send: VariableSymbol? = null
