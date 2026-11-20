// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

@file:OptIn(ExperimentalScrollBarApi::class)

import androidx.compose.animation.CapturedAnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import component.CardItem
import component.groupedCardItems
import misc.VersionInfo
import navigation.Route
import top.yukonga.miuix.kmp.basic.MiuixScrollBehavior
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.ScrollBehavior
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
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.theme.ThemeColorSpec
import top.yukonga.miuix.kmp.theme.ThemePaletteStyle
import utils.AdaptiveTopAppBar
import utils.BlurredBar
import utils.pageContentPadding
import utils.pageScrollModifiers
import utils.rememberBlurBackdrop
import kotlin.math.roundToInt

private val NavigationBarDisplayModeOptions = listOf("IconAndText", "IconOnly", "IconWithSelectedLabel")
private val SidebarStyleOptions = listOf("Original", "Glass")
private val FloatingNavigationBarStyleOptions = listOf("Original", "Glass", "iOS-like")
private val FloatingNavigationBarPositionOptions = listOf("Center", "Start", "End")
private val FloatingToolbarPositionOptions =
    listOf("TopStart", "CenterStart", "BottomStart", "TopEnd", "CenterEnd", "BottomEnd", "TopCenter", "BottomCenter")
private val FloatingToolbarOrientationOptions = listOf("Horizontal", "Vertical")
private val FabPositionOptions = listOf("Start", "Center", "End", "EndOverlay")
private val ColorModeOptions = listOf("System", "Light", "Dark", "MonetSystem", "MonetLight", "MonetDark")
private val NavTransitionStyleOptions = listOf("Miuix", "AOSP")
private val TopAppBarStyleOptions = listOf("Original", "Glass")
private val PaletteStyleOptions = ThemePaletteStyle.entries.map { it.name }
private val ColorSpecOptions = ThemeColorSpec.entries.map { it.name }
private val KeyColorOptions = listOf("Default") + ui.KeyColors.map { it.first }
private val PagerGestureModeOptions = listOf("Default", "Cross-Axis")
private val DensityScaleKeyPoints = listOf(80f, 90f, 100f, 110f)
private val Spec2025SupportedPaletteStyles = setOf(
    ThemePaletteStyle.TonalSpot,
    ThemePaletteStyle.Neutral,
    ThemePaletteStyle.Vibrant,
    ThemePaletteStyle.Expressive,
)

@Composable
fun SettingsPage(
    padding: PaddingValues,
) {
    val appState = LocalAppState.current
    val isWideScreen = LocalIsWideScreen.current
    val backdrop = rememberBlurBackdrop()
    val blurActive = backdrop != null
    val barColor = if (blurActive) Color.Transparent else MiuixTheme.colorScheme.surface
    val topAppBarScrollBehavior = MiuixScrollBehavior()

    Scaffold(
        topBar = {
            BlurredBar(backdrop, blurActive) {
                AdaptiveTopAppBar(
                    title = "Settings",
                    showTopAppBar = appState.showTopAppBar,
                    isWideScreen = isWideScreen,
                    scrollBehavior = topAppBarScrollBehavior,
                    color = barColor,
                    subtitle = "v${VersionInfo.VERSION_NAME} (${VersionInfo.VERSION_CODE})",
                )
            }
        },
    ) { innerPadding ->
        SettingsContent(
            contentPadding = pageContentPadding(innerPadding, padding),
            topAppBarScrollBehavior = topAppBarScrollBehavior,
            backdrop = backdrop,
        )
    }
}

@Composable
private fun SettingsContent(
    contentPadding: PaddingValues,
    topAppBarScrollBehavior: ScrollBehavior,
    backdrop: LayerBackdrop?,
) {
    val appState = LocalAppState.current
    val isWideScreen = LocalIsWideScreen.current
    val updateAppState = LocalUpdateAppState.current
    val navigator = LocalNavigator.current
    val onPredictiveBackChange = LocalOnPredictiveBackChange.current
    val lazyListState = rememberLazyListState()
    Box(modifier = if (backdrop != null) Modifier.layerBackdrop(backdrop) else Modifier) {
        LazyColumn(
            state = lazyListState,
            modifier = Modifier.pageScrollModifiers(
                appState.enableScrollEndHaptic,
                appState.showTopAppBar,
                topAppBarScrollBehavior,
            ),
            contentPadding = contentPadding,
        ) {
            groupedCardItems(
                keyPrefix = "settingsUi",
                outerTopPadding = 12.dp,
                outerBottomPadding = 12.dp,
                items = buildList {
                    add(
                        CardItem("fpsMonitor") {
                            SwitchPreference(
                                title = "Show FPS Monitor",
                                checked = appState.showFPSMonitor,
                                onCheckedChange = { updateAppState { state -> state.copy(showFPSMonitor = it) } },
                            )
                        },
                    )
                    add(
                        CardItem("colorMode") {
                            GlassDropdownPreference(
                                backdrop = backdrop,
                                title = "Color Mode",
                                items = ColorModeOptions,
                                selectedIndex = appState.colorMode,
                                onSelectedIndexChange = { updateAppState { state -> state.copy(colorMode = it) } },
                            )
                            CapturedAnimatedVisibility(
                                visible = appState.colorMode in 3..5,
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
                                visible = appState.colorMode in 3..5 && appState.seedIndex > 0,
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
                    )
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
                        CardItem("topAppBar") {
                            SwitchPreference(
                                title = "Show TopAppBar",
                                checked = appState.showTopAppBar,
                                onCheckedChange = { updateAppState { state -> state.copy(showTopAppBar = it) } },
                            )
                            CapturedAnimatedVisibility(
                                visible = appState.showTopAppBar,
                                enter = fadeIn() + expandVertically(),
                                exit = fadeOut() + shrinkVertically(),
                            ) {
                                GlassDropdownPreference(
                                    backdrop = backdrop,
                                    title = "TopAppBar Style",
                                    items = TopAppBarStyleOptions,
                                    selectedIndex = appState.topAppBarStyle,
                                    onSelectedIndexChange = { updateAppState { state -> state.copy(topAppBarStyle = it) } },
                                )
                            }
                        },
                    )
                    add(
                        CardItem("navigationBar") {
                            SwitchPreference(
                                title = if (isWideScreen) "Show NavigationRail" else "Show NavigationBar",
                                checked = appState.showNavigationBar,
                                onCheckedChange = { updateAppState { state -> state.copy(showNavigationBar = it) } },
                            )
                            // Sidebar options only exist on the layout that actually shows a sidebar.
                            CapturedAnimatedVisibility(
                                visible = appState.showNavigationBar && isWideScreen,
                                enter = fadeIn() + expandVertically(),
                                exit = fadeOut() + shrinkVertically(),
                            ) {
                                GlassDropdownPreference(
                                    backdrop = backdrop,
                                    title = "Sidebar Style",
                                    items = SidebarStyleOptions,
                                    selectedIndex = appState.navigationRailStyle,
                                    onSelectedIndexChange = { updateAppState { state -> state.copy(navigationRailStyle = it) } },
                                )
                            }
                            CapturedAnimatedVisibility(
                                visible = appState.showNavigationBar,
                                enter = fadeIn() + expandVertically(),
                                exit = fadeOut() + shrinkVertically(),
                            ) {
                                SwitchPreference(
                                    title = "Show Navigation Badge",
                                    checked = appState.showNavigationBarBadge,
                                    onCheckedChange = { updateAppState { state -> state.copy(showNavigationBarBadge = it) } },
                                )
                            }
                            CapturedAnimatedVisibility(
                                visible = appState.showNavigationBar && !isWideScreen && !appState.useFloatingNavigationBar,
                                enter = fadeIn() + expandVertically(),
                                exit = fadeOut() + shrinkVertically(),
                            ) {
                                GlassDropdownPreference(
                                    backdrop = backdrop,
                                    title = "NavigationBar Mode",
                                    items = NavigationBarDisplayModeOptions,
                                    selectedIndex = appState.navigationBarMode,
                                    onSelectedIndexChange = { updateAppState { state -> state.copy(navigationBarMode = it) } },
                                )
                            }
                            CapturedAnimatedVisibility(
                                visible = appState.showNavigationBar && !isWideScreen,
                                enter = fadeIn() + expandVertically(),
                                exit = fadeOut() + shrinkVertically(),
                            ) {
                                Column {
                                    SwitchPreference(
                                        title = "Use FloatingNavigationBar",
                                        checked = appState.useFloatingNavigationBar,
                                        onCheckedChange = { updateAppState { state -> state.copy(useFloatingNavigationBar = it) } },
                                    )
                                    CapturedAnimatedVisibility(
                                        visible = appState.useFloatingNavigationBar,
                                        enter = fadeIn() + expandVertically(),
                                        exit = fadeOut() + shrinkVertically(),
                                    ) {
                                        Column {
                                            GlassDropdownPreference(
                                                backdrop = backdrop,
                                                title = "FloatingNavigationBar Style",
                                                items = FloatingNavigationBarStyleOptions,
                                                selectedIndex = appState.floatingNavigationBarStyle,
                                                onSelectedIndexChange = { updateAppState { state -> state.copy(floatingNavigationBarStyle = it) } },
                                            )
                                            CapturedAnimatedVisibility(
                                                visible = appState.floatingNavigationBarStyle == 0,
                                                enter = fadeIn() + expandVertically(),
                                                exit = fadeOut() + shrinkVertically(),
                                            ) {
                                                Column {
                                                    GlassDropdownPreference(
                                                        backdrop = backdrop,
                                                        title = "FloatingNavigationBar Position",
                                                        items = FloatingNavigationBarPositionOptions,
                                                        selectedIndex = appState.floatingNavigationBarPosition,
                                                        onSelectedIndexChange = {
                                                            updateAppState { state ->
                                                                state.copy(
                                                                    floatingNavigationBarPosition = it,
                                                                )
                                                            }
                                                        },
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        },
                    )
                    add(
                        CardItem("floatingToolbar") {
                            SwitchPreference(
                                title = "Show FloatingToolbar",
                                checked = appState.showFloatingToolbar,
                                onCheckedChange = { updateAppState { state -> state.copy(showFloatingToolbar = it) } },
                            )
                            CapturedAnimatedVisibility(
                                visible = appState.showFloatingToolbar,
                                enter = fadeIn() + expandVertically(),
                                exit = fadeOut() + shrinkVertically(),
                            ) {
                                Column {
                                    GlassDropdownPreference(
                                        backdrop = backdrop,
                                        title = "FloatingToolbar Position",
                                        items = FloatingToolbarPositionOptions,
                                        selectedIndex = appState.floatingToolbarPosition,
                                        onSelectedIndexChange = { updateAppState { state -> state.copy(floatingToolbarPosition = it) } },
                                    )
                                    GlassDropdownPreference(
                                        backdrop = backdrop,
                                        title = "FloatingToolbar Orientation",
                                        items = FloatingToolbarOrientationOptions,
                                        selectedIndex = appState.floatingToolbarOrientation,
                                        onSelectedIndexChange = { updateAppState { state -> state.copy(floatingToolbarOrientation = it) } },
                                    )
                                    GlassDropdownPreference(
                                        backdrop = backdrop,
                                        title = "FloatingToolbar Style",
                                        items = TopAppBarStyleOptions,
                                        selectedIndex = appState.floatingToolbarStyle,
                                        onSelectedIndexChange = { updateAppState { state -> state.copy(floatingToolbarStyle = it) } },
                                    )
                                }
                            }
                        },
                    )
                    add(
                        CardItem("floatingActionButton") {
                            SwitchPreference(
                                title = "Show FloatingActionButton",
                                checked = appState.showFloatingActionButton,
                                onCheckedChange = { updateAppState { state -> state.copy(showFloatingActionButton = it) } },
                            )
                            CapturedAnimatedVisibility(
                                visible = appState.showFloatingActionButton,
                                enter = fadeIn() + expandVertically(),
                                exit = fadeOut() + shrinkVertically(),
                            ) {
                                Column {
                                    GlassDropdownPreference(
                                        backdrop = backdrop,
                                        title = "FloatingActionButton Position",
                                        items = FabPositionOptions,
                                        selectedIndex = appState.floatingActionButtonPosition,
                                        onSelectedIndexChange = { updateAppState { state -> state.copy(floatingActionButtonPosition = it) } },
                                    )
                                    GlassDropdownPreference(
                                        backdrop = backdrop,
                                        title = "FloatingActionButton Style",
                                        items = TopAppBarStyleOptions,
                                        selectedIndex = appState.floatingActionButtonStyle,
                                        onSelectedIndexChange = { updateAppState { state -> state.copy(floatingActionButtonStyle = it) } },
                                    )
                                }
                            }
                        },
                    )
                },
            )
            item(key = "settingsTransitionTitle") { SmallTitle("Navigation") }
            groupedCardItems(
                keyPrefix = "settingsTransition",
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
            item(key = "settingsAboutTitle") { SmallTitle("Other") }
            groupedCardItems(
                keyPrefix = "settingsAbout",
                outerTopPadding = 0.dp,
                outerBottomPadding = 0.dp,
                items = listOf(
                    CardItem("about") {
                        ArrowPreference(
                            title = "About",
                            summary = "About this example App",
                            onClick = { navigator.push(Route.About) },
                        )
                    },
                ),
            )
            item(key = "settingsBottomSpacer") { Spacer(modifier = Modifier.height(12.dp)) }
        }
        VerticalScrollBar(
            adapter = rememberScrollBarAdapter(lazyListState),
            modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight(),
            trackPadding = contentPadding,
            topAppBarScrollBehavior = topAppBarScrollBehavior.takeIf { appState.showTopAppBar },
        )
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
        GlassTextField(
            state = textState,
            modifier = Modifier.fillMaxWidth(),
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
