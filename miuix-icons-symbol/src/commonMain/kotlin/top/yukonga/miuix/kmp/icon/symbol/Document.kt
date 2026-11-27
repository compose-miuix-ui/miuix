// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.Document: VariableSymbol
    get() = _document ?: VariableSymbol(
        name = "Document",
        category = SymbolCategory.Content,
        encodedData = "JJVi?.DPI4#YI4!5#YP'3'auJorYm`X!'WjDc&c6QB3#P,i4V!FN!!=!f'}X]C&!0,f=4!k>urIhF@<[D.o1:Q5(Yb.!kN:AT9f&q=]@@]=[bQxFua%P*/iD.7S,Vl1!O8Rg1!8n%(<!`!m8#!wuHTS7`!8e%~i*3Ov-B!4#84{oAjj6!!R!q,%~H#q,^U&~y-?pRg.'ua0O[ND#P,oRSgW95h#!/'Rg#!Y9Rg&~/!R{Aj)!R3!!j7k<ml8MRg&!o/Ohp%A!@#VZNxvQm7/pSg1!YBn=x?TV9W-i9E,~@!nhU-BmrQo1,V!&Z+@#U-Gx*:N1-pSg1!7Hc>z?KnE-~viD!(X?1#2!`!Hu)7n%Sg%!1!Ct.8;s(''4@#A)c6iD&~yD%j,7ojT;1!D!O!M%^U%Qv2'K=(;MQ9'~&!.!L#*7pjN=d-6cjWo9Y%h7{op:CA7b'P@!)tE-f`v^-8jr&~>'m.eS_@3/2,Nx#P*!!^CG};EjrWzT/b:WT'Z%h7.BGk8MRg&!I.phi*p9^%,8jrA#DA9b'PT'r028w[5(ZLua]Q~=NF+1N[b#P)@#U-[x>R286sK&]EAb1R@!bKHZ4iDul7Gp,lCMYhu)A!ph0,E[_s**",
    ).also { _document = it }

private var _document: VariableSymbol? = null
