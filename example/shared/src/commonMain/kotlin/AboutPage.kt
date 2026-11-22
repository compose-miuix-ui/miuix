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
import androidx.compose.runtime.computedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.constrainHeight
import androidx.compose.ui.unit.constrainWidth
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import component.BackNavigationIcon
import component.effect.BgEffectBackground
import component.liquid.rememberCombinedBackdrop
import misc.VersionInfo
import navigation.Route
import org.jetbrains.compose.resources.painterResource
import top.yukonga.miuix.kmp.basic.MiuixScrollBehavior
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.ScrollBehavior
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.basic.VerticalScrollBar
import top.yukonga.miuix.kmp.basic.rememberScrollBarAdapter
import top.yukonga.miuix.kmp.blur.Backdrop
import top.yukonga.miuix.kmp.blur.BlendColorEntry
import top.yukonga.miuix.kmp.blur.BlurBlendMode
import top.yukonga.miuix.kmp.blur.BlurDefaults
import top.yukonga.miuix.kmp.blur.layerBackdrop
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
import top.yukonga.miuix.kmp.blur.textureBlur
import top.yukonga.miuix.kmp.glass.GlassBottomSheet
import top.yukonga.miuix.kmp.glass.GlassCard
import top.yukonga.miuix.kmp.glass.GlassDropdownPreference
import top.yukonga.miuix.kmp.interfaces.ExperimentalScrollBarApi
import top.yukonga.miuix.kmp.material.isRuntimeShaderSupported
import top.yukonga.miuix.kmp.preference.ArrowPreference
import top.yukonga.miuix.kmp.preference.SwitchPreference
import top.yukonga.miuix.kmp.shared.generated.resources.Res
import top.yukonga.miuix.kmp.shared.generated.resources.ic_launcher
import top.yukonga.miuix.kmp.theme.MiuixTheme
import ui.isInDarkTheme
import utils.AdaptiveTopAppBar
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
    val contentUnderTopBar by remember(lazyListState) {
        computedStateOf { lazyListState.canScrollBackward }
    }

    val scrollProgress by remember {
        computedStateOf {
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
    val barBackdrop = if (backdrop != null) rememberLayerBackdrop() else null
    // Capture content and the bar separately so overlays never sample their own host or scrim.
    val overlayBackdrop = if (backdrop != null && barBackdrop != null) rememberCombinedBackdrop(backdrop, barBackdrop) else null
    // Defer the frame-rate scroll read out of composition: these booleans only flip at the
    // single 1f threshold, so computedStateOf recomposes the bar on flip rather than every frame.
    val collapsed by remember { computedStateOf { scrollProgress == 1f } }
    val blurActive by remember(backdrop) { computedStateOf { backdrop != null && scrollProgress == 1f } }

    Scaffold(
        topBar = {
            val barColor = if (blurActive) {
                Color.Transparent
            } else {
                if (collapsed) MiuixTheme.colorScheme.surface else Color.Transparent
            }
            val titleAlpha = remember { { ((scrollProgress - 0.35f) / 0.65f).coerceIn(0f, 1f) } }
            val titleColor = MiuixTheme.colorScheme.onSurface
            Box(modifier = if (barBackdrop != null) Modifier.layerBackdrop(barBackdrop) else Modifier) {
                BlurredBar(backdrop, blurActive) {
                    AdaptiveTopAppBar(
                        title = "About",
                        showTopAppBar = true,
                        isWideScreen = LocalIsWideScreen.current,
                        expandable = false,
                        scrollBehavior = topAppBarScrollBehavior,
                        isContentScrolled = contentUnderTopBar,
                        color = barColor,
                        titleColor = titleColor,
                        titleAlpha = titleAlpha,
                        navigationIcon = {
                            BackNavigationIcon(
                                onClick = { navigator.pop() },
                            )
                        },
                    )
                }
            }
        },
    ) { innerPadding ->
        Box(modifier = if (backdrop != null) Modifier.layerBackdrop(backdrop) else Modifier) {
            AboutContent(
                scrollPadding = pageContentPadding(innerPadding, padding),
                logoPadding = pageContentPadding(innerPadding, padding, extraTop = 40.dp),
                topAppBarScrollBehavior = topAppBarScrollBehavior,
                lazyListState = lazyListState,
                scrollProgressProvider = { scrollProgress },
                overlayBackdrop = overlayBackdrop,
            )
        }
    }
}

@Composable
private fun AboutContent(
    scrollPadding: PaddingValues,
    logoPadding: PaddingValues,
    topAppBarScrollBehavior: ScrollBehavior,
    lazyListState: LazyListState,
    scrollProgressProvider: () -> Float,
    overlayBackdrop: Backdrop?,
) {
    val appState = LocalAppState.current
    val shaderSupported = isRuntimeShaderSupported()
    val uriHandler = LocalUriHandler.current
    val navigator = LocalNavigator.current
    val logoContentPadding = remember(logoPadding) {
        object : PaddingValues by logoPadding {
            override fun calculateTopPadding() = logoPadding.calculateTopPadding() + 52.dp
            override fun calculateBottomPadding() = 0.dp
        }
    }
    val listContentPadding = remember(scrollPadding) {
        object : PaddingValues by scrollPadding {
            override fun calculateBottomPadding() = 0.dp
        }
    }

    val backdrop = rememberBlurBackdrop()
    var isOs3Effect by remember { mutableStateOf(true) }
    var showTextureSet by remember { mutableStateOf(false) }
    var noiseCoefficient by remember { mutableFloatStateOf(BlurDefaults.NoiseCoefficient) }

    val isInDark = isInDarkTheme()
    val dynamicBackground = remember { mutableStateOf(shaderSupported) }
    val isFullScreenBackground = remember { mutableStateOf(true) }

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

    val logoHeightPx = remember { mutableIntStateOf(0) }

    BgEffectBackground(
        dynamicBackground = dynamicBackground.value,
        isOs3Effect = isOs3Effect,
        isFullSize = isFullScreenBackground.value,
        modifier = Modifier.fillMaxSize(),
        bgModifier = if (backdrop != null) Modifier.layerBackdrop(backdrop) else Modifier,
        alpha = { 1f - scrollProgressProvider() },
    ) {
        Layout(
            modifier = Modifier.fillMaxSize(),
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(logoContentPadding),
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
                                            blurRadius = 100f,
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

                Box(modifier = Modifier.fillMaxSize()) {
                    // Scrollable content
                    LazyColumn(
                        state = lazyListState,
                        modifier = Modifier.fillMaxSize().pageScrollModifiers(
                            appState.enableScrollEndHaptic,
                            appState.showTopAppBar,
                            topAppBarScrollBehavior,
                        ),
                        overscrollEffect = null,
                        contentPadding = listContentPadding,
                    ) {
                        // Transparent spacer matching logo height
                        item(key = "logoSpacer") {
                            Box(
                                Modifier
                                    .fillMaxWidth()
                                    .layout { measurable, constraints ->
                                        val totalHeight = (
                                            logoHeightPx.intValue -
                                                scrollPadding.calculateTopPadding().roundToPx() +
                                                126.dp.roundToPx()
                                            ).coerceAtLeast(0)
                                        val placeable = measurable.measure(
                                            constraints.copy(minHeight = totalHeight, maxHeight = totalHeight),
                                        )
                                        layout(placeable.width, placeable.height) {
                                            placeable.place(0, 0)
                                        }
                                    }
                                    .then(
                                        if (shaderSupported) {
                                            Modifier.pointerInput(Unit) {
                                                detectTapGestures {
                                                    showTextureSet = true
                                                }
                                            }
                                        } else {
                                            Modifier
                                        },
                                    ),
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
                                    GlassCard(
                                        modifier = Modifier.padding(horizontal = 12.dp),
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
                                    GlassCard(
                                        modifier = Modifier
                                            .padding(horizontal = 12.dp)
                                            .padding(top = 12.dp),
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
                        topAppBarScrollBehavior = topAppBarScrollBehavior,
                    )
                }
            },
        ) { measurables, constraints ->
            val relaxed = constraints.copy(minWidth = 0, minHeight = 0)
            val logoPlaceable = measurables[0].measure(relaxed)
            if (logoHeightPx.intValue != logoPlaceable.height) {
                logoHeightPx.intValue = logoPlaceable.height
            }
            val listPlaceable = measurables[1].measure(relaxed)
            val width = constraints.constrainWidth(maxOf(logoPlaceable.width, listPlaceable.width))
            val height = constraints.constrainHeight(maxOf(logoPlaceable.height, listPlaceable.height))
            layout(width, height) {
                logoPlaceable.placeRelative(0, 0)
                listPlaceable.placeRelative(0, 0)
            }
        }
    }

    if (!shaderSupported) return

    GlassBottomSheet(
        backdrop = overlayBackdrop,
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
                GlassDropdownPreference(
                    backdrop = overlayBackdrop,
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
            // This sheet is window-owned: reserve its navigation area, not the page sidebar.
            item { Spacer(modifier = Modifier.height(WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding())) }
        }
    }
}
