// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.LikeSelected: VariableSymbol
    get() = _likeSelected ?: VariableSymbol(
        name = "LikeSelected",
        category = SymbolCategory.Content,
        encodedData = "YG};!pii(i!G*6Vn,p)BL^!-^E%!g(t!E^4!]PC)3!oc#!A'#P?#<,F*0%#8gk5m)AN<3m;!tb](s!-%FJ(#^(;;a!j60;U~&:7&<it%+hbE`))P;!YG#PN_/>,!=%%~%~(*#P%~2)#PX#w,jrV!3tq0h0ud/he-xn?`Q+X!z~1!d1]>tKdEk'qqj^)0^<:jJHJ@MZ`wqafOC&^9R&3m=f!LDGq7:H+A>mkPn'^1XT8:1h-(|Kq]h0'7ZjcAbSr?7PdQW0T{hhO+igzQ**c.SS@?Xi*7ljU@Oji:<P91prTS6M2Fn%O+&*i{&k']Hx8GZN#,{.:%;SV~f6XgePR9~XJ[iy|ZDeD4](B'cg*#ya<P}Y=u07kj)<R0w43V3_Ch;-!=}lnN!",
    ).also { _likeSelected = it }

private var _likeSelected: VariableSymbol? = null
