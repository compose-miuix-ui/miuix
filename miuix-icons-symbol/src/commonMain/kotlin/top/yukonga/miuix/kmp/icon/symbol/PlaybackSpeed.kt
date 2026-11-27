// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.PlaybackSpeed: VariableSymbol
    get() = _playbackSpeed ?: VariableSymbol(
        name = "PlaybackSpeed",
        category = SymbolCategory.Content,
        encodedData = "yaqhN+<PI4ci!G!5&ZWGR9Ezq`lGXq#P~A-iH3B@(P:!^O#P*&gj,)]mocHxd(b#1PfvbBYm@e&~D<-iH3B@(P:!^O#P=~Z#(&nGxp6{f6-:y#b[%~%!#6#P+1<Pv~''E7jQ%!,VTP++hK>c21QQ%!-!'p!!E<U~t!('E7jQ%!,VTP,+hKu@r+T9:=,*N[i`j^S3<_X#`/:.TS!pXi^:~+x=g=I!z9T+X:A,Ft%vnD:~#!)!g2>F`(746RY4&p&i3/(*}[&BAP^&@yK#+#cg<P8{3:5@up)pKh(*&]NBiSb:?KI#l(L@,X>'2=T10#xWBTG/0+B14!G&{=yD*#v6|g-;r/D,.lc.P+/?5=YGrr.(s2=Hv1B/7p]ig4v],-q[8&o[@,ZnM~>;D1j;N5]`B5H#T'b9/#](>PXG;l-IrEpX^jv;358^RI]?[Geg6!C,QY>",
    ).also { _playbackSpeed = it }

private var _playbackSpeed: VariableSymbol? = null
