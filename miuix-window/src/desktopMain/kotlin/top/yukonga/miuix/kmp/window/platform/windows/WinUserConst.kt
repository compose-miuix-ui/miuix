// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.window.platform.windows

internal object WinUserConst {
    const val WM_SETTINGCHANGE = 0x001A
    const val WM_ACTIVATE = 0x0006
    const val WA_INACTIVE = 0x00000000

    const val WM_NCCALCSIZE = 0x0083
    const val WM_NCHITTEST = 0x0084
    const val WM_NCACTIVATE = 0x0086

    const val WM_MOUSEMOVE = 0x0200
    const val WM_LBUTTONDOWN = 0x0201
    const val WM_LBUTTONUP = 0x0202
    const val WM_MOUSELEAVE = 0x02A3

    const val WM_NCMOUSEMOVE = 0x00A0
    const val WM_NCLBUTTONDOWN = 0x00A1
    const val WM_NCLBUTTONUP = 0x00A2
    const val WM_NCRBUTTONUP = 0x00A5
    const val WM_NCMOUSELEAVE = 0x02A2

    const val SC_RESTORE = 0x0000F120
    const val SC_MOVE = 0xF010
    const val SC_SIZE = 0xF000
    const val SC_CLOSE = 0xF060

    const val WINT_MAX = 0xFFFF
    const val MIIM_STATE = 0x00000001
    const val MFT_STRING = 0x00000000
    const val TPM_RETURNCMD = 0x0100
    const val MFS_ENABLED = 0x00000000
    const val MFS_DISABLED = 0x00000003
    const val WS_EX_TOOLWINDOW = 0x00000080
    const val WM_EXITSIZEMOVE = 0x0232
}
