// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.BackupData: VariableSymbol
    get() = _backupData ?: VariableSymbol(
        name = "BackupData",
        category = SymbolCategory.Content,
        encodedData = "M+CjTX#l!GXF}Fo#NFi4IJb[H'2(RJq,lg`'%=t3!#T@X]j*;[%P(!fx#P(!y2!!'5_k+>R%W3a,_q>^.~*(;['~iN;['~;!8sM(.!d7#P{-BB2&=#fE!!!6Rg*#C?ZS-b1=o0T_tCM+&!40#!C'#PC*bEJ1-bdSl1!b(~T!0m:!hWk(}hY{`Uh9k6c]W*f=1!Li!!6X7W%;OF9@C[OR9?h!TS681Q1Y2m]P[-Y[9#=,=n2YNMiiz4eX[gR>U?DY1WT#y4eXih88]:k'i2>R>5dxXTZG`9`(sR>:a(WMP<U9ag#!v,uD6~m*:Vx'KJ]Pg!3*fEz'>!/tZ.!&p:I)DVnU`N:sQ,,p`hm*8ViT&I>.0%P[0#*!-_[;H<+npRe(O[k#N1H<NE<mwSvGcRQ8IS;>)IAE:'K?NR}.))DSo=^E#&]Qy]Z-:E{&`Bs(fS]@,]N8f{TlqIjK,[wy[/+0FSkj{8N@,[g|`tv2/,t>WL{(]Q8*.FF*gdkg8%LVHT!;)Ev&2po:_-vxdSbN#]f)0vR>-",
    ).also { _backupData = it }

private var _backupData: VariableSymbol? = null
