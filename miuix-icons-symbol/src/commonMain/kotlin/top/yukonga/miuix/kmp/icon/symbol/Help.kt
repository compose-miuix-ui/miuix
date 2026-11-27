// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.Help: VariableSymbol
    get() = _help ?: VariableSymbol(
        name = "Help",
        category = SymbolCategory.Core,
        encodedData = "hdJ#O+{P!;!/r/p*w=]4wUImK!c%M1a<0?2!E=#!U1P<0?xDB#:*-^!!;*Rgb%O1q<g=2!5=#!S1%=g=3PB#7*5^!!C(LHR&pDBP_%_J!!a%%~A!A#o6Wh_U,~A!0m&!>s]iT9BPz'jr`!'WpDb~z'ZJStd62!KK-+0mRCRg%!4Pjr'~JO>c&P9!ky'P)S+~@!g/uDo%Lm==Y-v[}+;!?'%~zEK'{FrYg#d3lK@.eAD'Q96!T[&R(!r!8#K+X.NR{A`hB/}R#P'!qQU^-~:!f?~Xo=n1'5]17EU-d5B&-I|!`/,8GGS2`P4(PL7b9W)wiDd(k?[&e-@#U-2_y#.8jrah~?pbbR@!)t6*R5^%,8xreS?Mog&!e-nbPR@!nhZ'Tx#P2+[pni1!}jTi1!lm4(B3@#7Wmyo.X,[4O'-=_VjWuGY%h7S<N=SLuarR6bG;W,BeZ>DC&s/R!4~QM8w%:<!&Qh?%A!+tU-yd_R.8jr}!i;nbIWxd^Q;-H&K>Y@=[5PS#MEU29sA#2!aPs&E.@#VZNxvQm7/pSgd-pbaR@!>#V-@_^%.8jrA#DA9b'PI8CG32qM4(PL9bIWNx#PZ3lX4(PLEbOXbgO!K#iU#Pf)YM8hr*VVl+!p1Qn#hj1j38d~1#/!wJn%/*(#LXXgMQW3AMSga-Cb%P0!,:i'ZCE^33}rJ&*DGx;QrNGt]'R!)%f0.&Q<!;XsW*l]X]2%b<N:y:5!",
    ).also { _help = it }

private var _help: VariableSymbol? = null
