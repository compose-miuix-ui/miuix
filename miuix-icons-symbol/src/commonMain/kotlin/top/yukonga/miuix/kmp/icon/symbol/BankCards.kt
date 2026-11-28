// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.BankCards: VariableSymbol
    get() = _bankCards ?: VariableSymbol(
        name = "BankCards",
        category = SymbolCategory.Core,
        encodedData = "xaj#d/^M}F!59@2ygGGZV@c.l*V<G:W&>#nE!!T08}&!5MI!tS0?=-u?Y)O_?_Ch@&C'!!/&@<8PC#xE&!3!z!AjC5b!W7!!B!)5'PB!s#+[pnb%5n#!jbBV&B!Cj=5/>1v]0]a&B'Rg:)ua;!=vpUES-^00+)CBH,]iq}%~.8Tgz#>Fk.)~r/}]g0x6_%68#@(WVXv9'!%4U~D!H)Ub<(;Mw:::]((Rz1qy`Uc.WSP?zRak3Drx*T:!P./%K.h/7&]MYj_4lb}XDqc.>'WBR>-KJJ1RqGnQT,^M5=1?AKYWS`B.W&UmVR8,7Kb)LXkgC/q+<S&=/?;QM5D]5&7&[S;A1b|Q%LptC(5:UU@Omm<[(~9!*#SCJs*#Q.lt8'MB_k)Di4&*%P.:t,~35PK)rbg*{aLPH!u/x/t,fJKSJ>5?z'YJ!",
    ).also { _bankCards = it }

private var _bankCards: VariableSymbol? = null
