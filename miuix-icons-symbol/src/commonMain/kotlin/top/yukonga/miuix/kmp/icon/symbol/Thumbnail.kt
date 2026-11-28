// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.Thumbnail: VariableSymbol
    get() = _thumbnail ?: VariableSymbol(
        name = "Thumbnail",
        category = SymbolCategory.Content,
        encodedData = "mrk#I<L0z]2kz=@WvX[1d]eUVZAb##%)j7%-7'4!%--fJ.4&3BoRR#1v.:i&g#NB(!k#_2^Nsgu!y415W'Vp,qoI=dcYrK)!Tq!!71#PI%FsV!dnf^oI.hGR/#U[>!5M0!rI!!U'8#q6JRt,~Jui<[#!J6!!V&Q9]~;MoDf6~J[,pIim>~.K&!La#!O9TH>cH.`%{N+td(q9o*:o/?_!qmlUdRkNz^}-VMBormuaF#a];.y%EPD(u8iy~UZGR90!7(#P{%!QoR+PH#!)`Gs90!0.!!3%64ihr*6@[+xh:._#;>)I+D#KgXJef?-T!>iD'~9mqP5+~psg71jhA,f`/GJ7}r>~*!A!o:5-*p9QS25bkQ|%@#U-Gx0Pi)Yp;l(Bbh&--wyD,~@!35&Z_nzE32E_4;;%>#E-~v4P-*kmh')BL]6-Px4P*!A!556Z(p[ht2D_<;|%>#E-~vuDi){j2>jBmV'Z+wuD,~@!m:%-_nvES22bcQ;%@#U-GxJ2P8Xpx?5YYx(PJ!hQu0G5P/v88)>SG%v)MX,!I!4iP0t)4d8'GGI74b}SGH:VVSz(Y%h7{ok9R0(cyW7I+G)7~oSg1!v.4(0Kh?/X5pc:i),,<(5MRgi~71dh,+^JFRI7nr&~*!g#q0)7C_OkFH4V&ST'Y%h7{os9n/J]W*HF]0I7kr&~*!{(0<I73bt&,Ip[5&{(^%,8jr=P-/oV-XmGp;)7!pSg1!_!",
    ).also { _thumbnail = it }

private var _thumbnail: VariableSymbol? = null
