// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.NotificationsStatusBar: VariableSymbol
    get() = _notificationsStatusBar ?: VariableSymbol(
        name = "NotificationsStatusBar",
        category = SymbolCategory.Content,
        encodedData = ",_x:f(Q<@WGB[hM(GIqD1YS5;!1Y0mI^-%|3E=r!J&IQjDuV(#jr)~DYS+'hc6G&_cJjVEg*eVEa4iB2;4K</!@]#!9f%~=%=ET!:ftttD3V'R*#P.=!gk.!xG!!A'(##4H0N+=E.R9T#!E5!!'+EI^M*_T97#Zopr!!Q%U-|rI#Id(!UEU29sA#2!aPs&E.@#VZNxvQm71pSgd-pbaR@!m:E-<tP]T2DbGR-/55U-Gx#Pk!^<FRQ2:siTpKZg&!2!,,k>pKCEY*lnO/H)<)4(PLO+L(3%(PT!-4+~!53%bQV~t3pr/RpRzDPLuaa%n`cFd(_!Du/2aM4(PL9bkW]xaP*!p9zQb(`!GG,8CmGRlB0@|TK?ZRh7{oSg;%/5BYKcgFa5Tjlg1!_!ZRh7Uj};PC`Ek''>GG,8jrgU<aHJHS<zTu5+j&_*lW^)Y-Gx!",
    ).also { _notificationsStatusBar = it }

private var _notificationsStatusBar: VariableSymbol? = null
