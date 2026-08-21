package com.example.miuixdemo.ui.screen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.miuixdemo.ui.theme.LocalOnThemeModeChange
import com.example.miuixdemo.ui.theme.LocalThemeMode
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.preference.ArrowPreference
import top.yukonga.miuix.kmp.preference.OverlayDropdownPreference
import top.yukonga.miuix.kmp.preference.SliderPreference
import top.yukonga.miuix.kmp.preference.SwitchPreference
import top.yukonga.miuix.kmp.theme.MiuixTheme

private val themeModeOptions = listOf("System", "Light", "Dark", "Monet (Dynamic)")

/**
 * 设置页：演示 Preference 组件（Switch / Dropdown / Slider / Arrow）与主题切换。
 */
@Composable
fun SettingsScreen() {
    val themeMode = LocalThemeMode.current
    val onThemeModeChange = LocalOnThemeModeChange.current
    var wifiEnabled by remember { mutableStateOf(true) }
    var notificationsEnabled by remember { mutableStateOf(false) }
    var autoSyncEnabled by remember { mutableStateOf(true) }
    var volume by remember { mutableFloatStateOf(0.7f) }

    LazyColumn {
        item {
            SmallTitle("Appearance")
            Card(Modifier.padding(horizontal = 12.dp)) {
                OverlayDropdownPreference(
                    title = "Theme Mode",
                    summary = "Monet follows system wallpaper color",
                    items = themeModeOptions,
                    selectedIndex = themeMode,
                    onSelectedIndexChange = { onThemeModeChange?.invoke(it) },
                )
            }
        }

        item {
            SmallTitle("General")
            Card(Modifier.padding(horizontal = 12.dp)) {
                SwitchPreference(
                    title = "Wi-Fi",
                    summary = "Connect to wireless networks",
                    checked = wifiEnabled,
                    onCheckedChange = { wifiEnabled = it },
                )
                SwitchPreference(
                    title = "Notifications",
                    summary = "Show notifications on the lock screen",
                    checked = notificationsEnabled,
                    onCheckedChange = { notificationsEnabled = it },
                )
                SwitchPreference(
                    title = "Auto Sync",
                    checked = autoSyncEnabled,
                    onCheckedChange = { autoSyncEnabled = it },
                )
            }
        }

        item {
            SmallTitle("Audio")
            Card(Modifier.padding(horizontal = 12.dp)) {
                SliderPreference(
                    title = "Volume",
                    valueText = "${(volume * 100).toInt()}%",
                    value = volume,
                    onValueChange = { volume = it },
                )
            }
        }

        item {
            SmallTitle("About")
            Card(Modifier.padding(horizontal = 12.dp)) {
                ArrowPreference(
                    title = "About Miuix",
                    summary = "A Compose Multiplatform UI library with HyperOS style",
                    endActions = {
                        Text(
                            text = "v1.0.0",
                            fontSize = MiuixTheme.textStyles.body2.fontSize,
                            color = MiuixTheme.colorScheme.onSurfaceVariantActions,
                        )
                    },
                    onClick = {},
                )
            }
        }
        item { Spacer(Modifier.height(24.dp)) }
    }
}