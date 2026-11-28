// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.Currency: VariableSymbol
    get() = _currency ?: VariableSymbol(
        name = "Currency",
        category = SymbolCategory.Content,
        encodedData = "2?EV>.!l22)0QBg<u];S!;Vgx#%'_w1C4!A%W'DM0#k_0m#PX9Rg#!Q'l.M<tg_~I#}DeP3Ik.4S,p`S/!C!^Q%!-!Q1=i**(F2)S21]m*Pbe(:!j!lS^L]V#P1!2PIQ&!z![!oR*!=!p'x4hK/Q<'YEN%yMPhk1NJ.R{R?SM1*_R9pB](&PXXL#N*rM4@nDNPJ'RFjD5%pA'`w{xz;-U9%Pv!Q9yru!Z&rM%~[&:.{+=@0P#7>[O!5M&!EMnf&!og,%U-xx!!uB[Kg=wDc~c%?K%~e%jra!d%7K'W}A2!b!Rg*!&kvaYga!'(Rga!RgB[a!&(7Kcw6M2!l+[1F*/f4PN#.:s>yMym:W]cf^B5QP!>6K?h@U@I~^I7M.k9T%;EtSZGA~9#(;_:2,M.b#b4dbX'jN*FU,cmc>SL{a#Q-@r5s`oGy;|9Z&n;J.KQa,1f[[^'Q_o:~'|9=%mDw:d//)X'pDs.?(OMeiG0,4UP[&<y^UMJ::t&g6>:X4.VCVxn%]!'gY9Y&kGb<W&}|G5>uSpWufkJ<P,-A[WRedSrTo|z]7XCv^];z3KQt6+tH*QM|;e6HJnPG)~U(#O2VsW-R[#P|'LmM%c6v9F*1_r9W&+)ZQ+!Rm_RB5Bs~(S<0#T&Lm(#Q9[!-%,!<43S/1o.W'+BN:i*UxTPY9;/=(eG3;E0t35PC.iP~(tRr9;.-~[!J#VJDPU&og-%/16:p%o6r9)*H.>Rd/!/<(213;D0x3kQ,1U9#!/!>:f~D){gM%j6Gh&*2mtS2?c.Z'ZC<033wgZ>oK&]V!7*4#U-Lx]Pn*m.C&78Y)gP.-%149Y{OkKO[b,Tc5nj19EMyhB.?EuPc(%Q!",
    ).also { _currency = it }

private var _currency: VariableSymbol? = null
