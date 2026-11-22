// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

@file:OptIn(ExperimentalScrollBarApi::class)

import androidx.compose.animation.CapturedAnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.maxLengthReject
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.text.input.setTextAndPlaceCursorAtEnd
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import component.BackNavigationIcon
import component.CardItem
import component.groupedCardItems
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.CardDefaults
import top.yukonga.miuix.kmp.basic.MiuixScrollBehavior
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.Slider
import top.yukonga.miuix.kmp.basic.SliderDefaults
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.basic.VerticalScrollBar
import top.yukonga.miuix.kmp.basic.rememberScrollBarAdapter
import top.yukonga.miuix.kmp.blur.LayerBackdrop
import top.yukonga.miuix.kmp.blur.layerBackdrop
import top.yukonga.miuix.kmp.glass.GlassDialog
import top.yukonga.miuix.kmp.glass.GlassDialogButton
import top.yukonga.miuix.kmp.glass.GlassDialogButtonRole
import top.yukonga.miuix.kmp.glass.GlassDropdownPreference
import top.yukonga.miuix.kmp.glass.GlassTextField
import top.yukonga.miuix.kmp.interfaces.ExperimentalScrollBarApi
import top.yukonga.miuix.kmp.material.isRuntimeShaderSupported
import top.yukonga.miuix.kmp.preference.ArrowPreference
import top.yukonga.miuix.kmp.preference.SwitchPreference
import top.yukonga.miuix.kmp.squircle.squircleBorder
import top.yukonga.miuix.kmp.squircle.squircleClip
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.theme.ThemeColorSpec
import top.yukonga.miuix.kmp.theme.ThemePaletteStyle
import ui.isInDarkTheme
import utils.AdaptiveTopAppBar
import utils.BlurredBar
import utils.pageContentPadding
import utils.pageScrollModifiers
import utils.rememberBlurBackdrop
import kotlin.math.roundToInt

private val PaletteStyleOptions = ThemePaletteStyle.entries.map { it.name }
private val ColorSpecOptions = ThemeColorSpec.entries.map { it.name }
private val KeyColorOptions = listOf("Default") + ui.KeyColors.map { it.first }
private val Spec2025SupportedPaletteStyles = setOf(
    ThemePaletteStyle.TonalSpot,
    ThemePaletteStyle.Neutral,
    ThemePaletteStyle.Vibrant,
    ThemePaletteStyle.Expressive,
)
private val NavTransitionStyleOptions = listOf("Miuix", "AOSP")
private val PagerGestureModeOptions = listOf("Default", "Cross-Axis")
private val DensityScaleKeyPoints = listOf(80f, 90f, 100f, 110f)

@Composable
fun ThemeSettingsPage(
    padding: PaddingValues,
) {
    val appState = LocalAppState.current
    val updateAppState = LocalUpdateAppState.current
    val isWideScreen = LocalIsWideScreen.current
    val navigator = LocalNavigator.current
    val onPredictiveBackChange = LocalOnPredictiveBackChange.current
    val backdrop = rememberBlurBackdrop()
    val blurActive = backdrop != null
    val barColor = if (blurActive) Color.Transparent else MiuixTheme.colorScheme.surface
    val topAppBarScrollBehavior = MiuixScrollBehavior()
    val lazyListState = rememberLazyListState()

    val isMonet = appState.colorMode in 3..5
    val isSystem = appState.colorMode == 0 || appState.colorMode == 3
    val isDark = when (appState.colorMode) {
        2, 5 -> true
        1, 4 -> false
        else -> isInDarkTheme()
    }

    Scaffold(
        topBar = {
            BlurredBar(backdrop, blurActive) {
                AdaptiveTopAppBar(
                    title = "Theme Settings",
                    showTopAppBar = appState.showTopAppBar,
                    isWideScreen = isWideScreen,
                    scrollBehavior = topAppBarScrollBehavior,
                    color = barColor,
                    navigationIcon = {
                        BackNavigationIcon(onClick = { navigator.pop() })
                    },
                )
            }
        },
    ) { innerPadding ->
        Box(modifier = if (backdrop != null) Modifier.layerBackdrop(backdrop) else Modifier) {
            LazyColumn(
                state = lazyListState,
                modifier = Modifier.pageScrollModifiers(
                    appState.enableScrollEndHaptic,
                    appState.showTopAppBar,
                    topAppBarScrollBehavior,
                ),
                contentPadding = pageContentPadding(innerPadding, padding),
            ) {
                item(key = "themePreview") {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 12.dp)
                            .selectableGroup(),
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                    ) {
                        ThemeModePreview(
                            title = "Light Mode",
                            dark = false,
                            wide = isWideScreen,
                            isSelected = !isDark,
                            onClick = {
                                updateAppState { state ->
                                    state.copy(colorMode = if (isMonet) 4 else 1)
                                }
                            },
                            modifier = Modifier.weight(1f),
                        )
                        ThemeModePreview(
                            title = "Dark Mode",
                            dark = true,
                            wide = isWideScreen,
                            isSelected = isDark,
                            onClick = {
                                updateAppState { state ->
                                    state.copy(colorMode = if (isMonet) 5 else 2)
                                }
                            },
                            modifier = Modifier.weight(1f),
                        )
                    }
                }

                item(key = "followSystem") {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp)
                            .padding(bottom = 12.dp),
                    ) {
                        SwitchPreference(
                            title = "Follow System",
                            checked = isSystem,
                            onCheckedChange = { followSystem ->
                                updateAppState { state ->
                                    val newMode = if (followSystem) {
                                        if (isMonet) 3 else 0
                                    } else {
                                        if (isMonet) {
                                            if (isDark) 5 else 4
                                        } else {
                                            if (isDark) 2 else 1
                                        }
                                    }
                                    state.copy(colorMode = newMode)
                                }
                            },
                        )
                    }
                }

                item(key = "monetTitle") {
                    SmallTitle(text = "Dynamic Color")
                }

                groupedCardItems(
                    keyPrefix = "themeMonet",
                    outerTopPadding = 0.dp,
                    outerBottomPadding = 12.dp,
                    items = listOf(
                        CardItem("monetSwitch") {
                            SwitchPreference(
                                title = "Monet Dynamic Color",
                                checked = isMonet,
                                onCheckedChange = { enableMonet ->
                                    updateAppState { state ->
                                        val newMode = when {
                                            enableMonet && isSystem -> 3
                                            enableMonet && isDark -> 5
                                            enableMonet -> 4
                                            isSystem -> 0
                                            isDark -> 2
                                            else -> 1
                                        }
                                        state.copy(colorMode = newMode)
                                    }
                                },
                            )
                            CapturedAnimatedVisibility(
                                visible = isMonet,
                                enter = fadeIn() + expandVertically(),
                                exit = fadeOut() + shrinkVertically(),
                            ) {
                                GlassDropdownPreference(
                                    backdrop = backdrop,
                                    title = "Key Color",
                                    items = KeyColorOptions,
                                    selectedIndex = appState.seedIndex,
                                    onSelectedIndexChange = { updateAppState { state -> state.copy(seedIndex = it) } },
                                )
                            }
                            CapturedAnimatedVisibility(
                                visible = isMonet && appState.seedIndex > 0,
                                enter = fadeIn() + expandVertically(),
                                exit = fadeOut() + shrinkVertically(),
                            ) {
                                Column {
                                    GlassDropdownPreference(
                                        backdrop = backdrop,
                                        title = "Palette Style",
                                        items = PaletteStyleOptions,
                                        selectedIndex = appState.paletteStyle,
                                        onSelectedIndexChange = { updateAppState { state -> state.copy(paletteStyle = it) } },
                                    )
                                    val currentStyle =
                                        ThemePaletteStyle.entries.getOrNull(appState.paletteStyle) ?: ThemePaletteStyle.TonalSpot
                                    val isCmf = currentStyle == ThemePaletteStyle.Cmf
                                    val isSpec2025Supported = currentStyle in Spec2025SupportedPaletteStyles
                                    val (colorSpecItems, colorSpecIndex, colorSpecEnabled) = when {
                                        isCmf -> Triple(listOf("Spec2026"), 0, false)
                                        !isSpec2025Supported -> Triple(listOf("Spec2021"), 0, false)
                                        else -> Triple(ColorSpecOptions, appState.colorSpec, true)
                                    }
                                    GlassDropdownPreference(
                                        backdrop = backdrop,
                                        title = "Color Spec",
                                        enabled = colorSpecEnabled,
                                        items = colorSpecItems,
                                        selectedIndex = colorSpecIndex,
                                        onSelectedIndexChange = { updateAppState { state -> state.copy(colorSpec = it) } },
                                    )
                                }
                            }
                        },
                    ),
                )

                item(key = "appearanceTitle") {
                    SmallTitle(text = "Appearance & Interaction")
                }

                groupedCardItems(
                    keyPrefix = "themeAppearance",
                    outerTopPadding = 0.dp,
                    outerBottomPadding = 12.dp,
                    items = buildList {
                        if (isRuntimeShaderSupported()) {
                            add(
                                CardItem("squircle") {
                                    SwitchPreference(
                                        title = "Enable Squircle Shapes",
                                        checked = appState.enableSquircle,
                                        onCheckedChange = { updateAppState { state -> state.copy(enableSquircle = it) } },
                                    )
                                },
                            )
                            add(
                                CardItem("blur") {
                                    SwitchPreference(
                                        title = "Enable Blur Effect",
                                        checked = appState.enableBlur,
                                        onCheckedChange = { updateAppState { state -> state.copy(enableBlur = it) } },
                                    )
                                },
                            )
                        }
                        add(
                            CardItem("scrollEndHaptic") {
                                SwitchPreference(
                                    title = "Enable Scroll End Haptic",
                                    checked = appState.enableScrollEndHaptic,
                                    onCheckedChange = { updateAppState { state -> state.copy(enableScrollEndHaptic = it) } },
                                )
                            },
                        )
                        add(
                            CardItem("pageUserScroll") {
                                SwitchPreference(
                                    title = "Enable Page User Scroll",
                                    checked = appState.enablePageUserScroll,
                                    onCheckedChange = { updateAppState { state -> state.copy(enablePageUserScroll = it) } },
                                )
                                CapturedAnimatedVisibility(
                                    visible = appState.enablePageUserScroll,
                                    enter = fadeIn() + expandVertically(),
                                    exit = fadeOut() + shrinkVertically(),
                                ) {
                                    GlassDropdownPreference(
                                        backdrop = backdrop,
                                        title = "Pager Gesture Mode",
                                        items = PagerGestureModeOptions,
                                        selectedIndex = appState.pagerInterceptionMode,
                                        onSelectedIndexChange = { updateAppState { state -> state.copy(pagerInterceptionMode = it) } },
                                    )
                                }
                            },
                        )
                        add(
                            CardItem("densityScale") {
                                GlobalScalePreference(
                                    densityScale = appState.densityScale,
                                    backdrop = backdrop,
                                    onDensityScaleChange = { nextScale ->
                                        updateAppState { state -> state.copy(densityScale = nextScale) }
                                    },
                                )
                            },
                        )
                    },
                )

                item(key = "themeNavigationTitle") {
                    SmallTitle(text = "Navigation")
                }

                groupedCardItems(
                    keyPrefix = "themeNavigation",
                    outerTopPadding = 0.dp,
                    outerBottomPadding = 12.dp,
                    items = buildList {
                        add(
                            CardItem("transitionStyle") {
                                GlassDropdownPreference(
                                    backdrop = backdrop,
                                    title = "Transition Style",
                                    items = NavTransitionStyleOptions,
                                    selectedIndex = appState.navTransitionStyle,
                                    onSelectedIndexChange = { updateAppState { state -> state.copy(navTransitionStyle = it) } },
                                )
                            },
                        )
                        add(
                            CardItem("cornerClip") {
                                SwitchPreference(
                                    title = "Enable Corner Clip",
                                    summary = "Clip the top scene with rounded corners during transitions",
                                    checked = appState.enableCornerClip,
                                    onCheckedChange = { updateAppState { state -> state.copy(enableCornerClip = it) } },
                                )
                            },
                        )
                        add(
                            CardItem("dim") {
                                SwitchPreference(
                                    title = "Enable Dim",
                                    summary = "Dim the scene behind during transitions",
                                    checked = appState.enableDim,
                                    onCheckedChange = { updateAppState { state -> state.copy(enableDim = it) } },
                                )
                            },
                        )
                        add(
                            CardItem("blockInput") {
                                SwitchPreference(
                                    title = "Block Input During Transition",
                                    summary = "Block touch input on the non-target scene",
                                    checked = appState.blockInputDuringTransition,
                                    onCheckedChange = { updateAppState { state -> state.copy(blockInputDuringTransition = it) } },
                                )
                            },
                        )
                        add(
                            CardItem("swipeBack") {
                                SwitchPreference(
                                    title = "Enable Swipe Back",
                                    summary = "Swipe a pushed page to pop it; direction follows layout",
                                    checked = appState.enableSwipeBack,
                                    onCheckedChange = { updateAppState { state -> state.copy(enableSwipeBack = it) } },
                                )
                            },
                        )
                        if (onPredictiveBackChange != null) {
                            add(
                                CardItem("predictiveBack") {
                                    SwitchPreference(
                                        title = "Predictive Back",
                                        summary = "Enable predictive back gesture animation",
                                        checked = appState.enablePredictiveBack,
                                        onCheckedChange = { checked ->
                                            updateAppState { state -> state.copy(enablePredictiveBack = checked) }
                                            onPredictiveBackChange(checked)
                                        },
                                    )
                                },
                            )
                        }
                    },
                )

                item(key = "themeSettingsBottomSpacer") { Spacer(modifier = Modifier.height(12.dp)) }
            }
            VerticalScrollBar(
                adapter = rememberScrollBarAdapter(lazyListState),
                modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight(),
                trackPadding = pageContentPadding(innerPadding, padding),
                topAppBarScrollBehavior = topAppBarScrollBehavior.takeIf { appState.showTopAppBar },
            )
        }
    }
}

@Composable
private fun ThemeModePreview(
    title: String,
    dark: Boolean,
    wide: Boolean,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.semantics(mergeDescendants = true) {
            role = Role.RadioButton
            selected = isSelected
        },
        cornerRadius = 26.dp,
        insideMargin = PaddingValues(vertical = 6.dp),
        colors = CardDefaults.defaultColors(color = Color.Transparent),
        onClick = onClick,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .squircleBorder(
                    width = 3.dp,
                    color = if (isSelected) MiuixTheme.colorScheme.primary else Color.Transparent,
                    cornerRadius = 26.dp,
                )
                .padding(6.dp),
        ) {
            ThemeModeIllustration(dark = dark, wide = wide)
        }
        Text(
            text = title,
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
            color = if (isSelected) MiuixTheme.colorScheme.primary else MiuixTheme.colorScheme.onSurfaceSecondary,
            style = MiuixTheme.textStyles.body2,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
private fun ThemeModeIllustration(
    dark: Boolean,
    wide: Boolean,
    modifier: Modifier = Modifier,
) {
    Canvas(
        modifier = modifier.fillMaxWidth().aspectRatio(if (wide) 2f else 1f).squircleClip(20.dp),
    ) {
        drawRect(
            brush = Brush.verticalGradient(listOf(Color(0xFF406EFF), Color(0xFF55B9FA))),
        )
        val illustrationWidth = size.width * if (wide) 0.7f else 1f
        val start = (size.width - illustrationWidth) / 2f
        val unitX = illustrationWidth / 680f
        val unitY = size.height / 680f
        val cornerUnit = minOf(unitX, unitY)
        val panelColor = if (dark) Color.Black.copy(alpha = 0.2f) else Color.White.copy(alpha = 0.2f)
        val lineColor = if (dark) Color.White.copy(alpha = 0.35f) else Color(0xFF285ABC).copy(alpha = 0.6f)
        for (top in listOf(148f, 356f)) {
            drawRoundRect(
                color = panelColor,
                topLeft = Offset(start + 48f * unitX, top * unitY),
                size = Size(584f * unitX, 176f * unitY),
                cornerRadius = CornerRadius(44f * cornerUnit),
            )
            for ((offset, width) in listOf(52f to 460f, 100f to 312f)) {
                drawRoundRect(
                    color = lineColor,
                    topLeft = Offset(start + 112f * unitX, (top + offset) * unitY),
                    size = Size(width * unitX, 24f * unitY),
                    cornerRadius = CornerRadius(12f * cornerUnit),
                )
            }
        }
    }
}

@Composable
private fun GlobalScalePreference(
    densityScale: Float,
    backdrop: LayerBackdrop?,
    onDensityScaleChange: (Float) -> Unit,
) {
    var densityScaleDraft by remember { mutableFloatStateOf((densityScale * 100f).roundToInt().toFloat()) }
    var showDensityScaleDialog by remember { mutableStateOf(false) }
    val densityScaleTextState = rememberTextFieldState()

    LaunchedEffect(densityScale) {
        densityScaleDraft = (densityScale * 100f).roundToInt().toFloat()
    }

    fun updateDensityScale(percent: Float) {
        val nextPercent = percent.roundToInt().coerceIn(
            (MIN_DENSITY_SCALE * 100f).roundToInt(),
            (MAX_DENSITY_SCALE * 100f).roundToInt(),
        )
        val nextScale = normalizeDensityScale(nextPercent / 100f)
        densityScaleDraft = nextScale * 100f
        onDensityScaleChange(nextScale)
    }

    ArrowPreference(
        title = "Global Scale",
        endActions = {
            Text(
                text = formatDensityScalePercent(densityScaleDraft),
                fontSize = MiuixTheme.textStyles.body2.fontSize,
                color = MiuixTheme.colorScheme.onSurfaceVariantActions,
            )
        },
        bottomAction = {
            Slider(
                value = densityScaleDraft.coerceIn(
                    MIN_DENSITY_SCALE * 100f,
                    MAX_DENSITY_SCALE * 100f,
                ),
                onValueChange = { value ->
                    densityScaleDraft = value
                },
                modifier = Modifier.fillMaxWidth(),
                valueRange = (MIN_DENSITY_SCALE * 100f)..(MAX_DENSITY_SCALE * 100f),
                onValueChangeFinished = {
                    updateDensityScale(densityScaleDraft)
                },
                showKeyPoints = true,
                keyPoints = DensityScaleKeyPoints,
                magnetThreshold = 0.01f,
                hapticEffect = SliderDefaults.SliderHapticEffect.Step,
            )
        },
        onClick = {
            densityScaleTextState.setTextAndPlaceCursorAtEnd(densityScaleDraft.roundToInt().toString())
            showDensityScaleDialog = true
        },
        holdDownState = showDensityScaleDialog,
    )
    DensityScaleDialog(
        show = showDensityScaleDialog,
        backdrop = backdrop,
        textState = densityScaleTextState,
        currentPercent = { densityScaleDraft },
        onDismiss = { showDensityScaleDialog = false },
        onConfirm = { percent ->
            updateDensityScale(percent)
            showDensityScaleDialog = false
        },
    )
}

@Composable
private fun DensityScaleDialog(
    show: Boolean,
    backdrop: LayerBackdrop?,
    textState: TextFieldState,
    currentPercent: () -> Float,
    onDismiss: () -> Unit,
    onConfirm: (Float) -> Unit,
) {
    GlassDialog(
        show = show,
        backdrop = backdrop,
        title = "Global Scale",
        summary = "Adjust overall UI density scale",
        onDismissRequest = onDismiss,
    ) {
        val focusRequester = remember { FocusRequester() }
        val keyboardController = LocalSoftwareKeyboardController.current
        LaunchedEffect(show) {
            if (show) {
                focusRequester.requestFocus()
                keyboardController?.show()
            }
        }
        GlassTextField(
            state = textState,
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester),
            inputTransformation = DigitsOnlyTransformation.maxLengthReject(3),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            trailingIcon = {
                Text(
                    text = "%",
                    modifier = Modifier.padding(horizontal = 16.dp),
                    color = MiuixTheme.colorScheme.onSurfaceVariantActions,
                )
            },
        )
        Spacer(Modifier.height(12.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            GlassDialogButton(
                onClick = onDismiss,
                modifier = Modifier.weight(1f),
            ) {
                Text(text = "Cancel", style = MiuixTheme.textStyles.button)
            }
            GlassDialogButton(
                onClick = {
                    val percent = textState.text.toString().toIntOrNull()?.toFloat() ?: currentPercent()
                    onConfirm(percent)
                },
                modifier = Modifier.weight(1f),
                role = GlassDialogButtonRole.Primary,
            ) {
                Text(text = "Confirm", style = MiuixTheme.textStyles.button)
            }
        }
    }
}

private val DigitsOnlyTransformation = InputTransformation {
    if (!asCharSequence().all { it.isDigit() }) revertAllChanges()
}

private fun formatDensityScalePercent(value: Float): String = "${value.roundToInt()}%"
