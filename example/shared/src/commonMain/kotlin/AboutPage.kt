// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

@file:OptIn(ExperimentalScrollBarApi::class)

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
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kyant.shapes.RoundedRectangle
import component.BackNavigationIcon
import component.effect.BgEffectBackground
import misc.VersionInfo
import navigation3.Route
import top.yukonga.miuix.kmp.basic.BasicComponent
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
import top.yukonga.miuix.kmp.blur.foregroundBlur
import top.yukonga.miuix.kmp.blur.layerBackdrop
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
import top.yukonga.miuix.kmp.blur.textureBlur
import top.yukonga.miuix.kmp.extra.SuperArrow
import top.yukonga.miuix.kmp.extra.SuperBottomSheet
import top.yukonga.miuix.kmp.interfaces.ExperimentalScrollBarApi
import top.yukonga.miuix.kmp.theme.MiuixTheme
import ui.isInDarkTheme
import utils.pageContentPadding
import utils.pageScrollModifiers

@Composable
fun AboutPage(
    padding: PaddingValues,
) {
    val topAppBarScrollBehavior = MiuixScrollBehavior()
    val navigator = LocalNavigator.current
    val lazyListState = rememberLazyListState()
    var logoHeightPx by remember { mutableIntStateOf(0) }

    val scrollProgress by remember {
        derivedStateOf {
            if (logoHeightPx <= 0) {
                0f
            } else {
                val index = lazyListState.firstVisibleItemIndex
                val offset = lazyListState.firstVisibleItemScrollOffset
                if (index > 0) 1f else (offset.toFloat() / logoHeightPx).coerceIn(0f, 1f)
            }
        }
    }

    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = "About",
                scrollBehavior = topAppBarScrollBehavior,
                color = MiuixTheme.colorScheme.surface.copy(alpha = if (scrollProgress == 1f) 1f else 0f),
                titleColor = MiuixTheme.colorScheme.onSurface.copy(alpha = scrollProgress),
                defaultWindowInsetsPadding = false,
                navigationIcon = {
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
            lazyListState = lazyListState,
            scrollProgress = scrollProgress,
            onLogoHeightChanged = { logoHeightPx = it },
        )
    }
}

@Composable
private fun AboutContent(
    padding: PaddingValues,
    topAppBarScrollBehavior: ScrollBehavior,
    lazyListState: LazyListState,
    scrollProgress: Float,
    onLogoHeightChanged: (Int) -> Unit,
) {
    val appState = LocalAppState.current
    val isWideScreen = LocalIsWideScreen.current
    val uriHandler = LocalUriHandler.current
    val navigator = LocalNavigator.current

    val backdrop = rememberLayerBackdrop()
    var showTextureSet by remember { mutableStateOf(false) }
    var blurRadius by remember { mutableFloatStateOf(60f) }
    var noiseCoefficient by remember { mutableFloatStateOf(0.001f) }
    var brightness by remember { mutableFloatStateOf(0f) }
    var contrast by remember { mutableFloatStateOf(1f) }
    var saturation by remember { mutableFloatStateOf(1f) }
    val contentPadding = pageContentPadding(
        padding,
        padding,
        isWideScreen,
        extraTop = 40.dp,
        extraStart = WindowInsets.displayCutout.asPaddingValues().calculateLeftPadding(LayoutDirection.Ltr),
        extraEnd = WindowInsets.displayCutout.asPaddingValues().calculateRightPadding(LayoutDirection.Ltr),
    )

    var blurEnable by remember { mutableStateOf(true) }
    val dynamicBackground = remember { mutableStateOf(true) }
    val effectBackground = remember { mutableStateOf(true) }

    val surface = MiuixTheme.colorScheme.surface.copy(alpha = 0.6f)
    val blendConfigs = remember {
        listOf(
            // No blend
            "None" to emptyList(),
            // Standard SkBlendMode (GPU hardware)
            "SrcOver" to listOf(BlendColorEntry(surface, BlendMode.SRC_OVER)),
            "Screen" to listOf(BlendColorEntry(surface, BlendMode.SCREEN)),
            "Multiply" to listOf(BlendColorEntry(surface, BlendMode.MULTIPLY)),
            "Overlay" to listOf(BlendColorEntry(surface, BlendMode.OVERLAY)),
            "Soft Light" to listOf(BlendColorEntry(surface, BlendMode.SOFT_LIGHT)),
            // Xiaomi custom modes (runtime shader)
            "Linear Light" to listOf(BlendColorEntry(surface, BlendMode.LINEAR_LIGHT)),
            "Linear Light Grey" to listOf(BlendColorEntry(surface, BlendMode.LINEAR_LIGHT_WITH_GREYSCALE)),
            "Linear Light Lab" to listOf(BlendColorEntry(surface, BlendMode.LINEAR_LIGHT_LAB)),
            "Lab Lighten" to listOf(BlendColorEntry(surface, BlendMode.LAB_LIGHTEN_WITH_GREYSCALE)),
            "Lab Darken" to listOf(BlendColorEntry(surface, BlendMode.LAB_DARKEN_WITH_GREYSCALE)),
            "MI Difference" to listOf(BlendColorEntry(surface, BlendMode.MI_DIFFERENCE)),
            "MI Color Dodge" to listOf(BlendColorEntry(surface, BlendMode.MI_COLOR_DODGE)),
            "MI Color Burn" to listOf(BlendColorEntry(surface, BlendMode.MI_COLOR_BURN)),
            "Plus Lighter" to listOf(BlendColorEntry(surface, BlendMode.PLUS_LIGHTER)),
            "Plus Darker" to listOf(BlendColorEntry(surface, BlendMode.PLUS_DARKER)),
        )
    }
    val isInDark = isInDarkTheme()
    val defaultBlendIndex = if (isInDarkTheme()) 15 else 14
    var blendModeIndex by remember { mutableIntStateOf(defaultBlendIndex) }
    val currentBlend = blendConfigs[blendModeIndex]
    val logoBlend = remember(isInDark) {
        if (isInDark) {
            listOf(
                BlendColorEntry(Color(0xe6a1a1a1), BlendMode.COLOR_DODGE),
                BlendColorEntry(Color(0x4de6e6e6), BlendMode.LINEAR_LIGHT),
                BlendColorEntry(Color(0xff1af500), BlendMode.LAB),
            )
        } else {
            listOf(
                BlendColorEntry(Color(0xcc4a4a4a), BlendMode.COLOR_BURN),
                BlendColorEntry(Color(0xff4f4f4f), BlendMode.LINEAR_LIGHT),
                BlendColorEntry(Color(0xff1af200), BlendMode.LAB),
            )
        }
    }

    val cardColor = MiuixTheme.colorScheme.surfaceContainer.copy(alpha = scrollProgress)

    val density = LocalDensity.current
    var logoHeightDp by remember { mutableStateOf(300.dp) }

    BgEffectBackground(
        effectBackground = effectBackground,
        dynamicBackground = dynamicBackground,
        modifier = Modifier.fillMaxSize(),
        bgModifier = Modifier.then(
            if (blurEnable) {
                Modifier.layerBackdrop(backdrop)
            } else {
                Modifier
            }.alpha(1f - scrollProgress),
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .graphicsLayer { alpha = 1f - scrollProgress }
                .padding(
                    top = contentPadding.calculateTopPadding() + 52.dp,
                    start = contentPadding.calculateLeftPadding(LayoutDirection.Ltr),
                    end = contentPadding.calculateRightPadding(LayoutDirection.Ltr),
                )
                .onSizeChanged { size ->
                    onLogoHeightChanged(size.height)
                    with(density) { logoHeightDp = size.height.toDp() }
                },
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
//            Box(
//                contentAlignment = Alignment.Center,
//                modifier = Modifier
//                    .size(88.dp)
//                    .clip(RoundedCornerShape(24.dp))
//                    .background(Color.White),
//            ) {
//                Image(
//                    modifier = Modifier
//                        .size(74.dp),
//                    painter = painterResource(Res.drawable.ic_launcher),
//                    contentDescription = null,
//                )
//            }
            Text(
                modifier = Modifier.padding(top = 12.dp).then(
                    if (blurEnable) {
                        Modifier.foregroundBlur(
                            backdrop = backdrop,
                            shape = RoundedRectangle(16.dp),
                            blurRadius = 200f,
                            noiseCoefficient = noiseCoefficient,
                            colors = BlurColors(
                                blendColors = logoBlend,
                            ),
                        )
                    } else {
                        Modifier
                    },
                ),
                text = "Miuix",
                fontWeight = FontWeight.Bold,
                fontSize = 60.sp,
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "v" + VersionInfo.VERSION_NAME + " (" + VersionInfo.VERSION_CODE + ")",
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
            )
        }

        // Scrollable content
        LazyColumn(
            state = lazyListState,
            modifier = Modifier.fillMaxSize().pageScrollModifiers(
                appState.enableScrollEndHaptic,
                appState.showTopAppBar,
                topAppBarScrollBehavior,
            ),
            contentPadding = contentPadding,
        ) {
            // Transparent spacer matching logo height
            item(key = "logoSpacer") {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(logoHeightDp + 52.dp + 98.dp),
                    contentAlignment = Alignment.TopCenter,
                ) {
                    Spacer(
                        Modifier
                            .padding(top = 52.dp)
                            .size(88.dp)
                            .clip(RoundedCornerShape(24.dp))
                            .clickable { showTextureSet = true },
                    )
                }
            }

            item(key = "about") {
                Card(
                    modifier = Modifier.padding(horizontal = 12.dp)
                        .then(
                            if (blurEnable) {
                                Modifier.textureBlur(
                                    backdrop = backdrop,
                                    shape = RoundedRectangle(16.dp),
                                    blurRadius = blurRadius,
                                    noiseCoefficient = noiseCoefficient,
                                    colors = BlurColors(
                                        blendColors = currentBlend.second,
                                        brightness = brightness,
                                        contrast = contrast,
                                        saturation = saturation,
                                    ),
                                )
                            } else {
                                Modifier
                            },
                        ),
                    colors = CardDefaults.defaultColors(cardColor, Color.Transparent),
                ) {
                    SuperArrow(
                        title = "View Source",
                        endActions = {
                            Text(
                                text = "GitHub",
                                fontSize = MiuixTheme.textStyles.body2.fontSize,
                                color = MiuixTheme.colorScheme.onSurfaceVariantActions,
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
                                color = MiuixTheme.colorScheme.onSurfaceVariantActions,
                            )
                        },
                        onClick = { uriHandler.openUri("https://t.me/YuKongA13579") },
                    )
                }
                Card(
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .padding(top = 12.dp)
                        .then(
                            if (blurEnable) {
                                Modifier.textureBlur(
                                    backdrop = backdrop,
                                    shape = RoundedRectangle(16.dp),
                                    blurRadius = blurRadius,
                                    noiseCoefficient = noiseCoefficient,
                                    colors = BlurColors(
                                        blendColors = currentBlend.second,
                                        brightness = brightness,
                                        contrast = contrast,
                                        saturation = saturation,
                                    ),
                                )
                            } else {
                                Modifier
                            },
                        ),
                    colors = CardDefaults.defaultColors(cardColor, Color.Transparent),
                ) {
                    SuperArrow(
                        title = "License",
                        endActions = {
                            Text(
                                text = "Apache-2.0",
                                fontSize = MiuixTheme.textStyles.body2.fontSize,
                                color = MiuixTheme.colorScheme.onSurfaceVariantActions,
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
        show = showTextureSet,
        title = "Texture Set",
        onDismissRequest = {
            showTextureSet = false
        },
        insideMargin = DpSize(0.dp, 0.dp),
    ) {
        Card {
            BasicComponent(
                title = "Effect Background Enabled",
                endActions = {
                    Switch(
                        effectBackground.value,
                        {
                            effectBackground.value = it
                        },
                    )
                },
                insideMargin = PaddingValues(16.dp, 16.dp, 16.dp, 0.dp),
            )
            BasicComponent(
                title = "Dynamic Background Enabled",
                endActions = {
                    Switch(
                        dynamicBackground.value,
                        {
                            dynamicBackground.value = it
                        },
                    )
                },
                insideMargin = PaddingValues(16.dp, 16.dp, 16.dp, 0.dp),
            )
            BasicComponent(
                title = "Blur Enable",
                endActions = {
                    Switch(
                        blurEnable,
                        {
                            blurEnable = it
                        },
                    )
                },
                insideMargin = PaddingValues(16.dp, 16.dp, 16.dp, 0.dp),
            )
            // Blur radius
            BasicComponent(
                title = "Blur Radius",
                endActions = { ValueText("${blurRadius.toInt()}") },
                bottomAction = {
                    Slider(
                        value = blurRadius / 200f,
                        onValueChange = { blurRadius = it * 200f },
                    )
                },
                insideMargin = PaddingValues(16.dp, 16.dp, 16.dp, 0.dp),
            )

            // Noise
            BasicComponent(
                title = "Noise",
                endActions = { ValueText("${(noiseCoefficient * 1000).toInt() / 1000f}") },
                bottomAction = {
                    Slider(
                        value = noiseCoefficient / 0.1f,
                        onValueChange = { noiseCoefficient = it * 0.1f },
                    )
                },
                insideMargin = PaddingValues(16.dp, 16.dp, 16.dp, 0.dp),
            )

            // Brightness
            BasicComponent(
                title = "Brightness",
                endActions = { ValueText("${(brightness * 100).toInt() / 100f}") },
                bottomAction = {
                    Slider(
                        value = (brightness + 1f) / 2f,
                        onValueChange = { brightness = it * 2f - 1f },
                    )
                },
                insideMargin = PaddingValues(16.dp, 16.dp, 16.dp, 0.dp),
            )

            // Contrast
            BasicComponent(
                title = "Contrast",
                endActions = { ValueText("${(contrast * 100).toInt() / 100f}") },
                bottomAction = {
                    Slider(
                        value = contrast / 3f,
                        onValueChange = { contrast = it * 3f },
                    )
                },
                insideMargin = PaddingValues(16.dp, 16.dp, 16.dp, 0.dp),
            )

            // Saturation
            BasicComponent(
                title = "Saturation",
                endActions = { ValueText("${(saturation * 100).toInt() / 100f}") },
                bottomAction = {
                    Slider(
                        value = saturation / 3f,
                        onValueChange = { saturation = it * 3f },
                    )
                },
                insideMargin = PaddingValues(16.dp, 16.dp, 16.dp, 0.dp),
            )

            // Blend mode
            val sheetBlend = blendConfigs[blendModeIndex]
            val modeId = sheetBlend.second.firstOrNull()?.mode
            BasicComponent(
                title = "Blend Mode",
                endActions = {
                    ValueText(sheetBlend.first + if (modeId != null) " ($modeId)" else "")
                },
                bottomAction = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        TextButton(
                            text = "Prev",
                            onClick = {
                                blendModeIndex = (blendModeIndex - 1 + blendConfigs.size) % blendConfigs.size
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
                },
            )
            Spacer(modifier = Modifier.height(WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()))
        }
    }
}

@Suppress("FunctionName")
@Composable
private fun ValueText(text: String) {
    Text(
        text = text,
        fontSize = MiuixTheme.textStyles.body2.fontSize,
        color = MiuixTheme.colorScheme.onSurfaceVariantActions,
    )
}
