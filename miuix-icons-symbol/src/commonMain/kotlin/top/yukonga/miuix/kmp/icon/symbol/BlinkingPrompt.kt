// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.BlinkingPrompt: VariableSymbol
    get() = _blinkingPrompt ?: VariableSymbol(
        name = "BlinkingPrompt",
        category = SymbolCategory.Content,
        encodedData = "T9T&>.a4M0y/#)#S^nehE#`+-%%E-%(=Ah7'8FS[;!w:&!yu#!Y'}P*#i.*'x/&vP+&hUEK+6,#P?(:.W!N2oDT3LMT;TVvx*Tg2<4%~=%iDb%n%2~4SSg?'.D;WPX^g%QV~tR)!3t#!)3ZNt,Yg>L#(!!2![p(7AjvaX-Gx#P@PYp#7%~_!hA*tq,B[!M^U/Eq,=.N14%Q9-!D;Q4XT_U0*|[0m<PC;M4%~K!&0ric6@.z?K+}i@5NP-(|Y5Ek~<*oFT3HM=iV+hEWWrY`[e)ydo:3%Y!E%~5yapR*B##f)i<E=CC1~)#51y4MXid8Pi!=1IQi*<P>Rc6Rg#!R+_s=(,4i#)1RxU%M>FJ|Q!L'tw&J!fQ6-<BR&/?_J{TH[Q~%!U+/h.%7!jb'WSJfQf0c6Ph(6]J&Pp(ZR)7F_b<uDSEk''>GG,8jr&~O#v[h'pD>:n+-pF:O#`!^%,8mrcQ06)5oXuUBR*73ptg1!_!CG'7=_vig;G)lT#>;Gh7{oSgO)!n.Us/e#e0cu0<1?#Pk#IM@#W&K!#0V-L?J&J>6P.(%Z6s)~s/T]5&J!7;f0wu%=1?#Pk#DMM~%!-",
    ).also { _blinkingPrompt = it }

private var _blinkingPrompt: VariableSymbol? = null
