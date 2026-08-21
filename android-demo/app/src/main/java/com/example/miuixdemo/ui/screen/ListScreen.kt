package com.example.miuixdemo.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.HorizontalDivider
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.basic.Surface
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.extended.Album
import top.yukonga.miuix.kmp.icon.extended.File
import top.yukonga.miuix.kmp.icon.extended.Images
import top.yukonga.miuix.kmp.icon.extended.Music
import top.yukonga.miuix.kmp.icon.extended.Video
import top.yukonga.miuix.kmp.preference.ArrowPreference
import top.yukonga.miuix.kmp.theme.MiuixTheme

/**
 * 列表与卡片演示页：Card、Surface、ArrowPreference 列表项、Divider。
 */
@Composable
fun ListScreen() {
    LazyColumn {
        item {
            SmallTitle("Card + ArrowPreference")
            Card(Modifier.padding(horizontal = 12.dp)) {
                ArrowPreference(
                    title = "Documents",
                    summary = "View your files",
                    startAction = {
                        Icon(
                            modifier = Modifier.padding(end = 8.dp),
                            imageVector = MiuixIcons.File,
                            contentDescription = "File",
                            tint = MiuixTheme.colorScheme.onSurface,
                        )
                    },
                    endActions = {
                        Text(
                            text = "12 items",
                            fontSize = MiuixTheme.textStyles.body2.fontSize,
                            color = MiuixTheme.colorScheme.onSurfaceVariantActions,
                        )
                    },
                    onClick = {},
                )
                ArrowPreference(
                    title = "Photos",
                    summary = "Gallery & camera",
                    startAction = {
                        Icon(
                            modifier = Modifier.padding(end = 8.dp),
                            imageVector = MiuixIcons.Images,
                            contentDescription = "Photos",
                            tint = MiuixTheme.colorScheme.onSurface,
                        )
                    },
                    endActions = {
                        Text(
                            text = "48 items",
                            fontSize = MiuixTheme.textStyles.body2.fontSize,
                            color = MiuixTheme.colorScheme.onSurfaceVariantActions,
                        )
                    },
                    onClick = {},
                )
                ArrowPreference(
                    title = "Music",
                    summary = "My playlists",
                    startAction = {
                        Icon(
                            modifier = Modifier.padding(end = 8.dp),
                            imageVector = MiuixIcons.Music,
                            contentDescription = "Music",
                            tint = MiuixTheme.colorScheme.onSurface,
                        )
                    },
                    endActions = {
                        Text(
                            text = "6 albums",
                            fontSize = MiuixTheme.textStyles.body2.fontSize,
                            color = MiuixTheme.colorScheme.onSurfaceVariantActions,
                        )
                    },
                    onClick = {},
                )
                ArrowPreference(
                    title = "Videos",
                    summary = "Recent recordings",
                    startAction = {
                        Icon(
                            modifier = Modifier.padding(end = 8.dp),
                            imageVector = MiuixIcons.Video,
                            contentDescription = "Videos",
                            tint = MiuixTheme.colorScheme.onSurface,
                        )
                    },
                    enabled = false,
                )
            }
        }

        item {
            SmallTitle("Surface")
            Surface(
                modifier = Modifier.padding(horizontal = 12.dp),
                shape = RoundedCornerShape(16.dp),
                color = MiuixTheme.colorScheme.secondaryVariant,
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                ) {
                    Text(
                        text = "Surface Card",
                        style = MiuixTheme.textStyles.title3,
                        color = MiuixTheme.colorScheme.onSecondaryVariant,
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = "Surface is a basic container with customizable shape, color, and shadow.",
                        fontSize = MiuixTheme.textStyles.body2.fontSize,
                        color = MiuixTheme.colorScheme.onSecondaryVariant.copy(alpha = 0.7f),
                    )
                }
            }
        }

        item {
            SmallTitle("HorizontalDivider")
            Card(Modifier.padding(horizontal = 12.dp)) {
                Column(modifier = Modifier.padding(vertical = 8.dp)) {
                    Text("Item 1", modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp))
                    HorizontalDivider(thickness = 0.5.dp)
                    Text("Item 2", modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp))
                    HorizontalDivider(thickness = 0.5.dp)
                    Text("Item 3", modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp))
                }
            }
        }

        item {
            SmallTitle("Row layout inside Card")
            Card(Modifier.padding(horizontal = 12.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    IconItem(MiuixIcons.Album, "Album")
                    IconItem(MiuixIcons.File, "File")
                    IconItem(MiuixIcons.Music, "Music")
                    IconItem(MiuixIcons.Video, "Video")
                }
            }
        }
        item { Spacer(Modifier.height(24.dp)) }
    }
}

@Composable
private fun IconItem(icon: androidx.compose.ui.graphics.vector.ImageVector, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            modifier = Modifier.size(32.dp).padding(bottom = 4.dp),
            imageVector = icon,
            contentDescription = label,
            tint = MiuixTheme.colorScheme.onSurface,
        )
        Text(
            text = label,
            fontSize = MiuixTheme.textStyles.footnote2.fontSize,
            color = MiuixTheme.colorScheme.onSurfaceVariantSummary,
        )
    }
}