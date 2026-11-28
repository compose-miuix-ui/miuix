// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.symbol

import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.symbol.SymbolCategory
import top.yukonga.miuix.kmp.symbol.VariableSymbol

val MiuixIcons.Symbol.NotificationMuted: VariableSymbol
    get() = _notificationMuted ?: VariableSymbol(
        name = "NotificationMuted",
        category = SymbolCategory.Content,
        encodedData = "KxyhX-DP!;;]Hp!5zJEQY,M+U^]')!T0=5Z1S!.%nj,!J!S+D09&)!e0c6HSU~.%c_.~X0;G.#(D/xp%0#&k!!3CNcTt;.3~L%Rw`g#!4(vMt?]I#!s/0uM+b+XTjg3'D4:e^Eg*4,%~0'iD<!WGOx(%S3uD+~8%iD)%{mLhP#!),[K8`!Q)#;Heml1PAQ,!4P-U[2K!E=S2VqStF<ZR32rM<(5Msgi*Ox#PC(ZBGkn/>#FZV``ti7{odg|%4cGPf#Y%h79'f%+#Tg=~T+lDd53%rQV~t3prhP|oSgCM?[5&bJzQv,8V,(6MRg&!l(*FU-j+^%-8jrR9?%.@m(&!0]l+V9)PA:/b!&~:-EZQ~HYE|421P*W-6cjWo9Y%h7-B?kSLuarR6bvQC(`!^%P2PP<(5M,ij*U{rQc;`!Huy,f&m@K^On^_q=nD{,Us&~o7Dj4;/6/nkT#>;Gh7{oSg+UcyiZKfi0q7{o%P^Gq._+yK=^,*0v)j!Gdbg-S#E0I0dmg%Z+t!9T*",
    ).also { _notificationMuted = it }

private var _notificationMuted: VariableSymbol? = null
