// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.JumpNext: VariableSymbol
    get() = _jumpNext ?: VariableSymbol(
        name = "JumpNext",
        category = SymbolCategory.Content,
        encodedData = "1?D;QX*Y#lP'vM^U)!nQH*66Vc*1yg%!.1B:u&-!q4TS2,Hm~vnDT~(*iDb#a;e4uS8!>49Dpy>P}#6!nQH*66Vc*1yg%!.1B:u&-!q4TS2,Hm~vnDT~(*iDb#a;e4uS8!>49Dpy>P`'QEh'j6dh@]f(VQd5)7-;g-Cn{1Dq+t#(6Y2>d;!5l.,*Q](6U.2#]+dhy'34pGy:_[bT%US?cR+Lg&B6%7i:U0l)4V!>{R<;F_<lnW)b=Q`5IE:&b4ZVVSyyE^g6lg#(x!iu&=rDVP&--Em#L*c_];0KY)jW=?BR>'`.6%6K*Vg-?We0N+^P-!N8Q1g=z3:#<Tggz~C,a@Q%",
    ).also { _jumpNext = it }

private var _jumpNext: VariableSymbol? = null
