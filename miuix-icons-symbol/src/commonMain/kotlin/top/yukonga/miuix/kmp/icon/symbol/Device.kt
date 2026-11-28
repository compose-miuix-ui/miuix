// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.Device: VariableSymbol
    get() = _device ?: VariableSymbol(
        name = "Device",
        category = SymbolCategory.Content,
        encodedData = "9{m&C.uXcbx=@WvX[1#Y&E^-.R1%G/_4My2T?3`+y#>%#P&&c6a<ed%~&P+'jr3%SBLi53|?vS!Bh[3%Z!&.!!0!D&]P_-'KMXl+~#a5]<2~kQ#!P@&~/%:.Z~ZnL]82C&hir*^+#P>%;[]#YWD8sY_gN%cJ8#!(44r)-f:.%#5Mhga';pKlp6DsT0oK<[b!}Hkrr#sGCl(6CniT?5<[0~3+;[8%8KE%iI.czTW99P.!i%jry!d%uP(*0m7!&*;[a#D0](&Po=YE'~_&iD'~8?#P%!G{4PH)Fs''Y2<#V-[x,P@A(y?U*&lW:4&SvPX%]e#@:?5;?2]N=]U1x&t+&k7?/Q_#c:?/`Gx:88;QI-<wqD()2h%UVEvbtYTxKhp#b!f%i#_XxhQ+9/bY)w<]+0~<FU!N^bVS349^P,[X3;k11?8W|Ycsd)DMb9L+=~jQ{:1E5ST<]#-+AM4PU-_JpRS<?;E!W2<u~;,7}gU0{y'ZMc.Fz-dX>:B)LV(TR2jE@/cGSg!!l(a##6@4:TqUe#j0N7>n^Zfx(P=Zg_->r6|9G'>]n(SCx/*1Q9K&p=F:C)hGX#=.7bn%1?4P>(]#i#.1Y9N%m6^Ex'A<#P#!#!!!Y9S>b?fSq/&F}(XGQ9!!!!e##0Bjj9F)A?UPT#]#e0!!iD%~N#QE5-T+D;b4~?uV)ZjrI~O5l/7-0SX<(=LJ&P)Z}]r%T[P##/*VR&;.+!C!H#lUCSn=QEn+<1/:,!H!!",
    ).also { _device = it }

private var _device: VariableSymbol? = null
