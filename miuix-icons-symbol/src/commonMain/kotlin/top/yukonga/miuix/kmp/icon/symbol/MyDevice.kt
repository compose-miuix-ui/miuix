// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.MyDevice: VariableSymbol
    get() = _myDevice ?: VariableSymbol(
        name = "MyDevice",
        category = SymbolCategory.Content,
        encodedData = "~oL&C.a4M0@WvX[1l4#Y%Pa9ZR:%h[V,uj^n[7iVcY&I)!Tq!!vO#PI%=EV!Qq*Fv?3]7Rf4(,y#J%!!R!lK&!7GK+L]1,M<7k@5og)#/#;[>!6{2!rIg*(,h'>wRw.6Cs/GK+c,#P.'!!qPA%Evq,yrF%2KPm*QJ!'%xx'!/A:_sKK/o7:po:g&'%,H%!'%91#IMEx,Km2QY&m#33^UJ!S3T90ht,HsN%A.nQS3/?6!:o7'pDC5R!9f%~:!!5'P:!Ra#PGm9%)!=+!!&!eG%~#!q&!p,;&4I/=+OT]0f6Am=P1!^!VRc6+b?RK<j~/Rm/g:5-XuQ9J!8;8u#6EP|&EH6V6Sz(Y%h7{ok9-/mVXWWCX0)7|oSg1!v.-UpKAEj*4p(#d(N&<(5MRg&!d-WKiWlGp;)7!pSg1!M&c>/Kb?ZWjn;/C(a!b%L8XuZ9H.3QW*GF]0I71m%P1!Hs.%;MRg&!B36c;T@!>#FZNCY%h7{o^iCM%h&!2!mrcQ06@@oXyUBR*73p(h1!_!,<'7;_u;D::yJT5;;Gh7{oSgr!=V%UBHQ)uY0p4#()=)<(5MRg0%;BFR63Bsh'JJTg&!2!8Hc>ULf?ZWlnC/()+,4(PLuaGP]<p;12pj:>pKXg&!2!w.=UpKAEj*6p_:d(=)<(5MRgX~d-oVHW`JBR)7'pSg1!Zp3>^L|bpX<pZRe5OS>UAMzEX-ax2Re5[p<l@Fx)&",
    ).also { _myDevice = it }

private var _myDevice: VariableSymbol? = null
