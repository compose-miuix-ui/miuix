// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

@file:OptIn(ExperimentalScrollBarApi::class)

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kyant.shapes.RoundedRectangle
import component.BackNavigationIcon
import component.effect.BgEffectBackground
import misc.VersionInfo
import navigation3.Route
import org.jetbrains.compose.resources.painterResource
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.CardDefaults
import top.yukonga.miuix.kmp.basic.MiuixScrollBehavior
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.ScrollBehavior
import top.yukonga.miuix.kmp.basic.Slider
import top.yukonga.miuix.kmp.basic.SmallTopAppBar
import top.yukonga.miuix.kmp.basic.Switch
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.basic.TextButton
import top.yukonga.miuix.kmp.basic.VerticalScrollBar
import top.yukonga.miuix.kmp.basic.rememberScrollBarAdapter
import top.yukonga.miuix.kmp.blur.BlendColorEntry
import top.yukonga.miuix.kmp.blur.BlendMode
import top.yukonga.miuix.kmp.blur.BlurColors
import top.yukonga.miuix.kmp.blur.layerBackdrop
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
import top.yukonga.miuix.kmp.blur.textureBlur
import top.yukonga.miuix.kmp.extra.SuperArrow
import top.yukonga.miuix.kmp.extra.SuperBottomSheet
import top.yukonga.miuix.kmp.interfaces.ExperimentalScrollBarApi
import top.yukonga.miuix.kmp.shared.generated.resources.Res
import top.yukonga.miuix.kmp.shared.generated.resources.ic_launcher
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.theme.MiuixTheme.colorScheme
import utils.pageContentPadding
import utils.pageScrollModifiers

@Composable
fun AboutPage(
    padding: PaddingValues,
) {
    val appState = LocalAppState.current
    val isWideScreen = LocalIsWideScreen.current
    val topAppBarScrollBehavior = MiuixScrollBehavior()
    val navigator = LocalNavigator.current
    Scaffold(
        topBar = {

            SmallTopAppBar(
                title = "About",
                scrollBehavior = topAppBarScrollBehavior,
                color = Color.Transparent,// colorScheme.surface,
                defaultWindowInsetsPadding = false,
                navigationIcon =  {
                    BackNavigationIcon(
                        onClick = { navigator.pop() },
                    )
                },
            )
        },
    ) { innerPadding ->
        AboutContent(
            padding = PaddingValues(
                top = innerPadding.calculateTopPadding(),
                bottom = padding.calculateBottomPadding(),
            ),
            topAppBarScrollBehavior = topAppBarScrollBehavior,
        )
    }
}

@Composable
private fun AboutContent(
    padding: PaddingValues,
    topAppBarScrollBehavior: ScrollBehavior,
) {
    val appState = LocalAppState.current
    val isWideScreen = LocalIsWideScreen.current
    val uriHandler = LocalUriHandler.current
    val navigator = LocalNavigator.current
    val lazyListState = rememberLazyListState()
    val backdrop = rememberLayerBackdrop()
    var showTextureSet by remember { mutableStateOf(false) }

    var blurRadius by remember { mutableFloatStateOf(60f) }
    var noiseCoefficient by remember { mutableFloatStateOf(0.001f) }
    var blendModeIndex by remember { mutableIntStateOf(0) }
    var dynamicBg by remember { mutableStateOf(false) }
    val contentPadding = pageContentPadding(
        padding,
        padding,
        isWideScreen,
        extraTop = 40.dp,
        extraStart = WindowInsets.displayCutout.asPaddingValues().calculateLeftPadding(LayoutDirection.Ltr),
        extraEnd = WindowInsets.displayCutout.asPaddingValues().calculateRightPadding(LayoutDirection.Ltr),
    )

    val blendConfigs = remember {
        listOf(
            // No blend
            "None" to emptyList(),
            // Standard SkBlendMode (GPU hardware)
            "SrcOver White" to listOf(BlendColorEntry(Color.White.copy(alpha = 0.3f), BlendMode.SRC_OVER)),
            "SrcOver Black" to listOf(BlendColorEntry(Color.Black.copy(alpha = 0.2f), BlendMode.SRC_OVER)),
            "Screen" to listOf(BlendColorEntry(Color(0xFF1565C0).copy(alpha = 0.3f), BlendMode.SCREEN)),
            "Multiply" to listOf(BlendColorEntry(Color(0xFFE91E63).copy(alpha = 0.3f), BlendMode.MULTIPLY)),
            "Overlay" to listOf(BlendColorEntry(Color(0xFF6750A4).copy(alpha = 0.4f), BlendMode.OVERLAY)),
            "Soft Light" to listOf(BlendColorEntry(Color(0xFFFFB74D).copy(alpha = 0.4f), BlendMode.SOFT_LIGHT)),
            // Xiaomi custom modes (runtime shader)
            "Linear Light" to listOf(BlendColorEntry(Color(0xFF6750A4).copy(alpha = 0.3f), BlendMode.LINEAR_LIGHT)),
            "Linear Light Grey" to listOf(BlendColorEntry(Color(0xFF90A4AE).copy(alpha = 0.4f), BlendMode.LINEAR_LIGHT_WITH_GREYSCALE)),
            "Linear Light Lab" to listOf(BlendColorEntry(Color(0xFF5C6BC0).copy(alpha = 0.3f), BlendMode.LINEAR_LIGHT_LAB)),
            "Lab Lighten" to listOf(BlendColorEntry(Color.White.copy(alpha = 0.5f), BlendMode.LAB_LIGHTEN_WITH_GREYSCALE)),
            "Lab Darken" to listOf(BlendColorEntry(Color.Black.copy(alpha = 0.5f), BlendMode.LAB_DARKEN_WITH_GREYSCALE)),
            "MI Difference" to listOf(BlendColorEntry(Color(0xFF00BCD4).copy(alpha = 0.4f), BlendMode.MI_DIFFERENCE)),
            "MI Color Dodge" to listOf(BlendColorEntry(Color(0xFFFF7043).copy(alpha = 0.3f), BlendMode.MI_COLOR_DODGE)),
            "MI Color Burn" to listOf(BlendColorEntry(Color(0xFF8D6E63).copy(alpha = 0.3f), BlendMode.MI_COLOR_BURN)),
            "Plus Darker" to listOf(BlendColorEntry(Color(0xFF006D40).copy(alpha = 0.4f), BlendMode.PLUS_DARKER)),
            "Plus Lighter" to listOf(BlendColorEntry(Color(0xFF1A237E).copy(alpha = 0.4f), BlendMode.PLUS_LIGHTER)),
        )
    }
    val currentBlend = blendConfigs[blendModeIndex]

    BgEffectBackground(
        modifier = Modifier.fillMaxSize(),
        bgModifier = Modifier.background(Color.White).layerBackdrop(backdrop)
    ) {
        LazyColumn(
            state = lazyListState,
            modifier = Modifier.fillMaxSize().pageScrollModifiers(
                appState.enableScrollEndHaptic,
                appState.showTopAppBar,
                topAppBarScrollBehavior,
            ),
            contentPadding = contentPadding,
        ) {
            item(key = "Logo") {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(){
                            showTextureSet = true
                        }
                        .padding(top = 52.dp, bottom = 98.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(100.dp)
                            .clip(RoundedCornerShape(28.dp))
                            .background(Color.White),
                    ) {
                        Image(
                            modifier = Modifier.size(80.dp),
                            painter = painterResource(Res.drawable.ic_launcher),
                            contentDescription = null,
                        )
                    }
                    Text(
                        modifier = Modifier.padding(top = 12.dp),
                        text = "Miuix",
                        fontWeight = FontWeight.Medium,
                        fontSize = 26.sp,
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = "v" + VersionInfo.VERSION_NAME + " (" + VersionInfo.VERSION_CODE + ")",
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center,
                    )
                }
            }

            item(key = "about") {
                Card(
                    modifier = Modifier.padding(horizontal = 12.dp)
                        .textureBlur(
                            backdrop = backdrop,
                            shape = RoundedRectangle(16.dp),
                            blurRadius = blurRadius,
                            noiseCoefficient = noiseCoefficient,
                            colors = BlurColors(blendColors = currentBlend.second),
                        ),

                    colors = CardDefaults.defaultColors(Color.Transparent, Color.Transparent),
                ) {
                    SuperArrow(
                        title = "View Source",
                        endActions = {
                            Text(
                                text = "GitHub",
                                fontSize = MiuixTheme.textStyles.body2.fontSize,
                                color = colorScheme.onSurfaceVariantActions,
                            )
                        },
                        onClick = { uriHandler.openUri("https://github.com/compose-miuix-ui/miuix") },
                    )
                    SuperArrow(
                        title = "Join Group",
                        endActions = {
                            Text(
                                text = "Telegram",
                                fontSize = MiuixTheme.textStyles.body2.fontSize,
                                color = colorScheme.onSurfaceVariantActions,
                            )
                        },
                        onClick = { uriHandler.openUri("https://t.me/YuKongA13579") },
                    )
                }
                Card(
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .padding(top = 12.dp)
                        .textureBlur(
                            backdrop = backdrop,
                            shape = RoundedRectangle(16.dp),
                            blurRadius = blurRadius,
                            noiseCoefficient = noiseCoefficient,
                            colors = BlurColors(blendColors = currentBlend.second),
                        ),
                    colors = CardDefaults.defaultColors(Color.Transparent, Color.Transparent),
                ) {
                    SuperArrow(
                        title = "License",
                        endActions = {
                            Text(
                                text = "Apache-2.0",
                                fontSize = MiuixTheme.textStyles.body2.fontSize,
                                color = colorScheme.onSurfaceVariantActions,
                            )
                        },
                        onClick = {
                            uriHandler.openUri("https://www.apache.org/licenses/LICENSE-2.0.txt")
                        },
                    )
                    SuperArrow(
                        title = "Third Party Licenses",
                        onClick = { navigator.push(Route.License) },
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
            }
            item {
                Spacer(Modifier.fillMaxWidth().height(500.dp))
            }
        }
        VerticalScrollBar(
            adapter = rememberScrollBarAdapter(lazyListState),
            modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight(),
            trackPadding = contentPadding,
        )
    }

    SuperBottomSheet(
        showTextureSet,
        onDismissFinished = {
            showTextureSet = false
        },
        onDismissRequest = {
            showTextureSet = false

        },
    ) {
        Card {
            // Blur radius
            Text(
                text = "Blur Radius: ${blurRadius.toInt()}",
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(top = 12.dp, bottom = 4.dp),
            )
            Slider(
                value = blurRadius / 200f,
                onValueChange = { blurRadius = it * 200f },
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(bottom = 12.dp),
            )

            // Noise
            Text(
                text = "Noise: ${(noiseCoefficient * 1000).toInt() / 1000f}",
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(bottom = 4.dp),
            )
            Slider(
                value = noiseCoefficient / 0.1f,
                onValueChange = { noiseCoefficient = it * 0.1f },
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(bottom = 12.dp),
            )

            // Blend mode
            val modeId = currentBlend.second.firstOrNull()?.mode
            Text(
                text = "Blend Mode: ${currentBlend.first}" + if (modeId != null) " ($modeId)" else "",
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(bottom = 4.dp),
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
                    .padding(bottom = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                TextButton(
                    text = "Prev",
                    onClick = {
                        blendModeIndex =
                            (blendModeIndex - 1 + blendConfigs.size) % blendConfigs.size
                    },
                    modifier = Modifier.weight(1f),
                )
                TextButton(
                    text = "Next",
                    onClick = {
                        blendModeIndex = (blendModeIndex + 1) % blendConfigs.size
                    },
                    modifier = Modifier.weight(1f),
                )
            }

            // Dynamic background toggle
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp)
                    .padding(bottom = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = "Dynamic Background", fontSize = 14.sp)
                Switch(
                    checked = dynamicBg,
                    onCheckedChange = { dynamicBg = it },
                )
            }
        }
    }
}

