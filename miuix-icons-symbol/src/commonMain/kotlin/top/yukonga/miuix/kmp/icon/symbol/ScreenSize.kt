// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.ScreenSize: VariableSymbol
    get() = _screenSize ?: VariableSymbol(
        name = "ScreenSize",
        category = SymbolCategory.Content,
        encodedData = "bUk#o1NPI4cieX#l/Z#llKtDGRL%_w?g#!|'tS+!|'@g#!qr8KiDh!+K!!*!j6M%Q91!%/Q94Jx'>JjrO%K;'!._&{M#83#:e!-)&~C!g0N.]!L#[;'!d/+7K&w%8T+!v%+KB){,ePuaQQx)p!N(1!62!!*!E~#P&!m&#Py-?pQ9/!>!/t(~0!w,_s!!Y!9j*!>!!G`#Z2n%+!?!{6xh#!8#:.C!8#X4jrf%*6t3N!~5GxM(}HiDz!}H3tt,;[#!j7JBml8MRg&!I.Ohp%A!@#VZNxvQm7/pSg1!qM>k`->#E-~vHuO2OP4(PLuabRm`_FC(_!Y%h7.BGk8MRg&!I.phi*p9^%,8;[bj5M)h@%VTGGg/&K5UVL|3ESl=EEb/}rF~7,[pfi;%m:2)G5Gh@/3plgg4nhZ'{(GGb/YJ3;G0<:E-/wEEg/Is+&?,{VWZ_UGh'*H[0#!@nbQR@!>#FZg])^i7{oSg!-V]8*[5^%,8jr:Q_@og&!2![pih(Bmg&!2!'K<()B+tU-Gx#P_'JBih1!_!r^i7IBd>SLua'PZ1lGg0v64;F0d[R&C?jQ^;C.M&d>AA^b}+D'sEl7?BRjyFL4LT=ivE22ApJj4RJ4[Ta`:Fb.M&Z)';'*E[F:'*S<4iM0g40/=TSF51+#",
    ).also { _screenSize = it }

private var _screenSize: VariableSymbol? = null
