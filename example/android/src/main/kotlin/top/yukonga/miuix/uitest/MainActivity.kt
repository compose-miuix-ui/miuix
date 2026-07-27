// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.uitest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.MiuixTheme
import top.yukonga.miuix.kmp.basic.MiuixScrollView
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.basic.TopAppBar
import top.yukonga.miuix.kmp.preference.DropdownPreference
import top.yukonga.miuix.kmp.preference.PreferenceCategory
import top.yukonga.miuix.kmp.preference.SwitchPreference
import top.yukonga.miuix.kmp.utils.isSystemInDarkTheme
import top.yukonga.miuix.kmp.lightColorScheme
import top.yukonga.miuix.kmp.darkColorScheme
import top.yukonga.miuix.kmp.ThemeController
import top.yukonga.miuix.kmp.ColorSchemeMode

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val isDark = isSystemInDarkTheme()
            val controller = remember {
                ThemeController(
                    colorSchemeMode = ColorSchemeMode.MonetSystem
                )
            }

            MiuixTheme(
                controller = controller,
                colors = if (isDark) darkColorScheme() else lightColorScheme()
            ) {
                SettingsScreen()
            }
        }
    }

    @Composable
    fun SettingsScreen() {
        var playerStyle by remember { mutableStateOf(0) }
        var enabled by remember { mutableStateOf(true) }
        var statusText by remember { mutableStateOf("正在检测…") }

        MiuixScrollView(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                TopAppBar(
                    title = { Text("锁屏播放器") },
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                PreferenceCategory(title = "播放器样式") {
                    DropdownPreference(
                        title = "播放器样式",
                        summary = "选择锁屏播放器样式（需重启生效）",
                        options = listOf("默认样式", "通知样式"),
                        selected = playerStyle,
                        onSelectedChange = { index ->
                            playerStyle = index
                            statusText = "已选择: ${if (index == 0) "默认样式" else "通知样式"}（重启生效）"
                        }
                    )
                }

                PreferenceCategory(title = "其他") {
                    SwitchPreference(
                        title = "4×1 播放器",
                        summary = "单行紧凑布局，隐藏进度条与投放按钮",
                        checked = enabled,
                        onCheckedChange = { checked ->
                            enabled = checked
                            statusText = if (checked) "已开启" else "已关闭，需重启才能完全还原"
                        }
                    )
                }

                Text(
                    text = statusText,
                    modifier = Modifier.padding(top = 16.dp, start = 16.dp),
                    color = MiuixTheme.colors.textSecondary
                )
            }
        }
    }
}
