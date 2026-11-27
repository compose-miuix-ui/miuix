// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.ChildSafetyMode: VariableSymbol
    get() = _childSafetyMode ?: VariableSymbol(
        name = "ChildSafetyMode",
        category = SymbolCategory.Content,
        encodedData = "B<6%PXIT5iT#!C&^kr~%c6Rgp!N2=Et&ua*!#q!!!3?pWG*!Q!~v&!Lii6M!lt!!)(Rg}#'-_sCC.!!t!!QC#PSJ|h&!A!,.vnUgn@2F!f)D7#m,l|AjM4-%;(GhL#Sb5P{,N6M%'(&JHeWgPPW%s6M%L[FR!6uaEP%43hk#?1K;P2K~%%53NJvP28vrj#2!3hs&z%)QK+Rg(#0?@PO%N.5P#(0ytS2?@Pv&>B1jB<h9&!D'vE0,1_0#k*<4[l1_h9M#4>{_::[Pq'mICKJW/b5QA(O!v~e!#Bk.9'{3yDH#I.f~s&T{Fk%B4:X#?.uPr,@m^%h=.Pv!=3%nUS)&W;84.p[SJ>04#",
    ).also { _childSafetyMode = it }

private var _childSafetyMode: VariableSymbol? = null
