// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

@file:OptIn(ExperimentalScrollBarApi::class)

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import component.BackNavigationIcon
import component.blend.ColorBlendToken
import component.effect.BgEffectBackground
import misc.VersionInfo
import navigation.Route
import org.jetbrains.compose.resources.painterResource
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.CardDefaults
import top.yukonga.miuix.kmp.basic.MiuixScrollBehavior
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.ScrollBehavior
import top.yukonga.miuix.kmp.basic.SmallTopAppBar
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.basic.VerticalScrollBar
import top.yukonga.miuix.kmp.basic.rememberScrollBarAdapter
import top.yukonga.miuix.kmp.blur.BlendColorEntry
import top.yukonga.miuix.kmp.blur.BlurBlendMode
import top.yukonga.miuix.kmp.blur.BlurDefaults
import top.yukonga.miuix.kmp.blur.isRuntimeShaderSupported
import top.yukonga.miuix.kmp.blur.layerBackdrop
import top.yukonga.miuix.kmp.blur.textureBlur
import top.yukonga.miuix.kmp.interfaces.ExperimentalScrollBarApi
import top.yukonga.miuix.kmp.overlay.OverlayBottomSheet
import top.yukonga.miuix.kmp.preference.ArrowPreference
import top.yukonga.miuix.kmp.preference.OverlayDropdownPreference
import top.yukonga.miuix.kmp.preference.SwitchPreference
import top.yukonga.miuix.kmp.shared.generated.resources.Res
import top.yukonga.miuix.kmp.shared.generated.resources.ic_launcher
import top.yukonga.miuix.kmp.theme.MiuixTheme
import ui.isInDarkTheme
import utils.BlurredBar
import utils.pageContentPadding
import utils.pageScrollModifiers
import utils.rememberBlurBackdrop
import androidx.compose.ui.graphics.BlendMode as ComposeBlendMode

@Composable
fun AboutPage(
    padding: PaddingValues,
) {
    val topAppBarScrollBehavior = MiuixScrollBehavior()
    val navigator = LocalNavigator.current
    val lazyListState = rememberLazyListState()

    val scrollProgress by remember {
        derivedStateOf {
            when {
                lazyListState.firstVisibleItemIndex > 0 -> 1f

                else -> {
                    val spacer = lazyListState.layoutInfo.visibleItemsInfo.firstOrNull { it.key == "logoSpacer" }
                    if (spacer != null && spacer.size > 0) {
                        (lazyListState.firstVisibleItemScrollOffset.toFloat() / spacer.size).coerceIn(0f, 1f)
                    } else {
                        0f
                    }
                }
            }
        }
    }

    val backdrop = rememberBlurBackdrop()
    // Defer the frame-rate scroll read out of composition: these booleans only flip at the
    // single 1f threshold, so derivedStateOf recomposes the bar on flip rather than every frame.
    val collapsed by remember { derivedStateOf { scrollProgress == 1f } }
    val blurActive by remember(backdrop) { derivedStateOf { backdrop != null && scrollProgress == 1f } }

    Scaffold(
        topBar = {
            val barColor = if (blurActive) {
                Color.Transparent
            } else {
                if (collapsed) MiuixTheme.colorScheme.surface else Color.Transparent
            }
            val titleColor = MiuixTheme.colorScheme.onSurface.copy(
                alpha = ((scrollProgress - 0.35f) / 0.65f).coerceIn(0f, 1f),
            )
            BlurredBar(backdrop, blurActive) {
                SmallTopAppBar(
                    title = "About",
                    scrollBehavior = topAppBarScrollBehavior,
                    color = barColor,
                    titleColor = titleColor,
                    navigationIcon = {
                        BackNavigationIcon(
                            onClick = { navigator.pop() },
                        )
                    },
                )
            }
        },
    ) { innerPadding ->
        Box(modifier = if (backdrop != null) Modifier.layerBackdrop(backdrop) else Modifier) {
            AboutContent(
                padding = PaddingValues(
                    top = innerPadding.calculateTopPadding(),
                    bottom = padding.calculateBottomPadding(),
                ),
                topAppBarScrollBehavior = topAppBarScrollBehavior,
                lazyListState = lazyListState,
                scrollProgressProvider = { scrollProgress },
            )
        }
    }
}

@Composable
private fun AboutContent(
    padding: PaddingValues,
    topAppBarScrollBehavior: ScrollBehavior,
    lazyListState: LazyListState,
    scrollProgressProvider: () -> Float,
) {
    val appState = LocalAppState.current
    val isWideScreen = LocalIsWideScreen.current
    val uriHandler = LocalUriHandler.current
    val navigator = LocalNavigator.current

    val backdrop = rememberBlurBackdrop()
    var isOs3Effect by remember { mutableStateOf(true) }
    var showTextureSet by remember { mutableStateOf(false) }
    var blurRadius by remember { mutableFloatStateOf(60f) }
    var noiseCoefficient by remember { mutableFloatStateOf(BlurDefaults.NoiseCoefficient) }
    var brightness by remember { mutableFloatStateOf(0f) }
    var contrast by remember { mutableFloatStateOf(1f) }
    var saturation by remember { mutableFloatStateOf(1f) }
    val scrollPadding = pageContentPadding(
        padding,
        padding,
        isWideScreen,
        extraStart = WindowInsets.displayCutout.asPaddingValues().calculateLeftPadding(LayoutDirection.Ltr),
        extraEnd = WindowInsets.displayCutout.asPaddingValues().calculateRightPadding(LayoutDirection.Ltr),
    )
    val logoPadding = pageContentPadding(
        padding,
        padding,
        isWideScreen,
        extraTop = 40.dp,
        extraStart = WindowInsets.displayCutout.asPaddingValues().calculateLeftPadding(LayoutDirection.Ltr),
        extraEnd = WindowInsets.displayCutout.asPaddingValues().calculateRightPadding(LayoutDirection.Ltr),
    )

    val isInDark = isInDarkTheme()
    val dynamicBackground = remember { mutableStateOf(isRuntimeShaderSupported()) }
    val isFullScreenBackground = remember { mutableStateOf(true) }

    val cardBlend = if (isInDark) ColorBlendToken.Overlay_Thin_Light else ColorBlendToken.Pured_Regular_Light
    val logoBlend = remember(isInDark) {
        if (isInDark) {
            listOf(
                BlendColorEntry(Color(0xe6a1a1a1), BlurBlendMode.ColorDodge),
                BlendColorEntry(Color(0x4de6e6e6), BlurBlendMode.LinearLight),
                BlendColorEntry(Color(0xff1af500), BlurBlendMode.Lab),
            )
        } else {
            listOf(
                BlendColorEntry(Color(0xcc4a4a4a), BlurBlendMode.ColorBurn),
                BlendColorEntry(Color(0xff4f4f4f), BlurBlendMode.LinearLight),
                BlendColorEntry(Color(0xff1af200), BlurBlendMode.Lab),
            )
        }
    }

    val density = LocalDensity.current
    var logoHeightDp by remember { mutableStateOf(300.dp) }

    BgEffectBackground(
        dynamicBackground = dynamicBackground.value,
        isOs3Effect = isOs3Effect,
        isFullSize = isFullScreenBackground.value,
        modifier = Modifier.fillMaxSize(),
        bgModifier = if (backdrop != null) Modifier.layerBackdrop(backdrop) else Modifier,
        alpha = { 1f - scrollProgressProvider() },
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = logoPadding.calculateTopPadding() + 52.dp,
                    start = logoPadding.calculateLeftPadding(LayoutDirection.Ltr),
                    end = logoPadding.calculateRightPadding(LayoutDirection.Ltr),
                )
                .onSizeChanged { size ->
                    with(density) { logoHeightDp = size.height.toDp() }
                },
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(88.dp)
                    .graphicsLayer {
                        val iconProgress = ((scrollProgressProvider() - 0.35f) / 0.15f).coerceIn(0f, 1f)
                        clip = true
                        shape = RoundedCornerShape(24.dp)
                        alpha = 1 - iconProgress
                        scaleX = 1 - (iconProgress * 0.05f)
                        scaleY = 1 - (iconProgress * 0.05f)
                    }
                    .background(Color.White),
            ) {
                Image(
                    modifier = Modifier.size(74.dp),
                    painter = painterResource(Res.drawable.ic_launcher),
                    contentDescription = null,
                )
            }
            Text(
                modifier = Modifier.padding(top = 12.dp, bottom = 5.dp)
                    .graphicsLayer {
                        val projectNameProgress = ((scrollProgressProvider() - 0.20f) / 0.15f).coerceIn(0f, 1f)
                        alpha = 1 - projectNameProgress
                        scaleX = 1 - (projectNameProgress * 0.05f)
                        scaleY = 1 - (projectNameProgress * 0.05f)
                    }
                    .then(
                        if (backdrop != null) {
                            Modifier
                                .textureBlur(
                                    backdrop = backdrop,
                                    shape = RoundedCornerShape(16.dp),
                                    blurRadius = 150f,
                                    noiseCoefficient = noiseCoefficient,
                                    colors = BlurDefaults.blurColors(
                                        blendColors = logoBlend,
                                    ),
                                    contentBlendMode = ComposeBlendMode.DstIn,
                                )
                        } else {
                            Modifier
                        },
                    ),
                text = "Miuix for Compose",
                color = MiuixTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold,
                fontSize = 35.sp,
            )
            Text(
                modifier = Modifier.fillMaxWidth()
                    .graphicsLayer {
                        val versionCodeProgress = ((scrollProgressProvider() - 0.05f) / 0.15f).coerceIn(0f, 1f)
                        alpha = 1 - versionCodeProgress
                        scaleX = 1 - (versionCodeProgress * 0.05f)
                        scaleY = 1 - (versionCodeProgress * 0.05f)
                    },
                color = MiuixTheme.colorScheme.onSurfaceVariantSummary,
                text = "v" + VersionInfo.VERSION_NAME + " (" + VersionInfo.VERSION_CODE + ")",
                fontSize = 14.sp,
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
            contentPadding = PaddingValues(
                top = scrollPadding.calculateTopPadding(),
                start = scrollPadding.calculateLeftPadding(LayoutDirection.Ltr),
                end = scrollPadding.calculateRightPadding(LayoutDirection.Ltr),
            ),
        ) {
            // Transparent spacer matching logo height
            item(key = "logoSpacer") {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(
                            logoHeightDp + 52.dp + logoPadding.calculateTopPadding() - scrollPadding.calculateTopPadding() + 126.dp,
                        )
                        .pointerInput(Unit) {
                            detectTapGestures {
                                showTextureSet = true
                            }
                        },
                    contentAlignment = Alignment.TopCenter,
                    content = { },
                )
            }

            item(key = "about") {
                Box {
                    Spacer(Modifier.fillParentMaxHeight())
                    Column(
                        modifier = Modifier.padding(bottom = scrollPadding.calculateBottomPadding()),
                    ) {
                        Card(
                            modifier = Modifier.padding(horizontal = 12.dp)
                                .then(
                                    if (backdrop != null) {
                                        Modifier
                                            .textureBlur(
                                                backdrop = backdrop,
                                                shape = RoundedCornerShape(16.dp),
                                                blurRadius = blurRadius,
                                                noiseCoefficient = noiseCoefficient,
                                                colors = BlurDefaults.blurColors(
                                                    blendColors = cardBlend,
                                                    brightness = brightness,
                                                    contrast = contrast,
                                                    saturation = saturation,
                                                ),
                                            )
                                    } else {
                                        Modifier
                                    },
                                ),
                            colors = CardDefaults.defaultColors(
                                if (backdrop != null) Color.Transparent else MiuixTheme.colorScheme.surfaceContainer,
                                Color.Transparent,
                            ),
                        ) {
                            ArrowPreference(
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
                            ArrowPreference(
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
                                    if (backdrop != null) {
                                        Modifier
                                            .textureBlur(
                                                backdrop = backdrop,
                                                shape = RoundedCornerShape(16.dp),
                                                blurRadius = blurRadius,
                                                noiseCoefficient = noiseCoefficient,
                                                colors = BlurDefaults.blurColors(
                                                    blendColors = cardBlend,
                                                    brightness = brightness,
                                                    contrast = contrast,
                                                    saturation = saturation,
                                                ),
                                            )
                                    } else {
                                        Modifier
                                    },
                                ),
                            colors = CardDefaults.defaultColors(
                                if (backdrop != null) Color.Transparent else MiuixTheme.colorScheme.surfaceContainer,
                                Color.Transparent,
                            ),
                        ) {
                            ArrowPreference(
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
                            ArrowPreference(
                                title = "Third Party Licenses",
                                onClick = { navigator.push(Route.License) },
                            )
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }
            }
        }
        VerticalScrollBar(
            adapter = rememberScrollBarAdapter(lazyListState),
            modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight(),
            trackPadding = scrollPadding,
        )
    }

    OverlayBottomSheet(
        show = showTextureSet,
        title = "Background Effect",
        onDismissRequest = {
            showTextureSet = false
        },
        insideMargin = DpSize(0.dp, 0.dp),
    ) {
        LazyColumn {
            item {
                val effectVariantOptions = listOf("OS2", "OS3")
                OverlayDropdownPreference(
                    title = "Effect Variant",
                    items = effectVariantOptions,
                    selectedIndex = if (isOs3Effect) 1 else 0,
                    onSelectedIndexChange = { isOs3Effect = (it == 1) },
                )

                SwitchPreference(
                    title = "Dynamic Background",
                    checked = dynamicBackground.value,
                    onCheckedChange = {
                        dynamicBackground.value = it
                    },
                )

                SwitchPreference(
                    title = "Full Screen Background",
                    checked = isFullScreenBackground.value,
                    onCheckedChange = {
                        isFullScreenBackground.value = it
                    },
                )
            }
            item { Spacer(modifier = Modifier.height(WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding())) }
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
