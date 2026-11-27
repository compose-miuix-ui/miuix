// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.SecurityStatus: VariableSymbol
    get() = _securityStatus ?: VariableSymbol(
        name = "SecurityStatus",
        category = SymbolCategory.Content,
        encodedData = "lrz<QXfS'^%Y#lap.Pgh5!<S<0rmaUxV;Zss:4[_7?o81?HP;&G!B&S#1!nic6<4nfL+9P~#O1Qzxhbu9:-E3P5bV9L!8#r1M%2%/hT!y%ag(DSg!Q;3yy.X(B<<o>-7cg`(o<1zXuE[g#))w:R2S~@#%=fx+;P2|r~(()@#]4@[MPX9Rga()b-%d/Q)oR+Pqr_!D(~M2&=4;S_;44qR.Bs:S,oXbjh=sDD&M.4iD0v3UPw%?h<(*BH#b/GJb#b4A):T,4nQI1H<c9-!07zj^;VmjTyK3/%)6SX<`;:4PRR500y-aj>k<A`x9TAM*:}'Zm^%|3OhhW+PZ:y-,Bp:c4b!qTs80PZ#/;p/b/r(6%O7P4vS+4e#J1B<c9-!;&-=q?dU|QTC;/f)5&k&`;))?UjU!h,#O1q<f=S9J#*4#i2,5bn%i=n9O%u6,ia/Rg5PD',#+#,&'Gt3{3@TsF/?xPEf)E.#4)0TS&f)P,krj9)!B*5%",
    ).also { _securityStatus = it }

private var _securityStatus: VariableSymbol? = null
