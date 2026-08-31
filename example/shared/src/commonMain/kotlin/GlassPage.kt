// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import component.basicComponentSection
import component.buttonSection
import component.cardSection
import component.sliderSection
import component.switchSection
import component.textFieldSection
import org.jetbrains.compose.resources.painterResource
import top.yukonga.miuix.kmp.basic.BasicComponent
import top.yukonga.miuix.kmp.basic.Button
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.HorizontalDivider
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.basic.MiuixScrollBehavior
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.basic.Switch
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.basic.TextButton
import top.yukonga.miuix.kmp.blur.isRuntimeShaderSupported
import top.yukonga.miuix.kmp.blur.layerBackdrop
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
import top.yukonga.miuix.kmp.glass.GlassDialog
import top.yukonga.miuix.kmp.glass.GlassIconButton
import top.yukonga.miuix.kmp.glass.GlassNavigationBar
import top.yukonga.miuix.kmp.glass.GlassNavigationItem
import top.yukonga.miuix.kmp.glass.GlassOverlayDefaults
import top.yukonga.miuix.kmp.glass.GlassPopup
import top.yukonga.miuix.kmp.glass.GlassSegmentedTabRow
import top.yukonga.miuix.kmp.glass.GlassShape
import top.yukonga.miuix.kmp.glass.GlassStroke
import top.yukonga.miuix.kmp.glass.GlassStrokes
import top.yukonga.miuix.kmp.glass.GlassStyle
import top.yukonga.miuix.kmp.glass.GlassStyles
import top.yukonga.miuix.kmp.glass.GlassTabRow
import top.yukonga.miuix.kmp.glass.GlassTabRowDefaults
import top.yukonga.miuix.kmp.glass.GlassTopAppBar
import top.yukonga.miuix.kmp.glass.GlassTopAppBarDefaults
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.basic.ArrowRight
import top.yukonga.miuix.kmp.icon.extended.Back
import top.yukonga.miuix.kmp.icon.extended.Create
import top.yukonga.miuix.kmp.icon.extended.Edit
import top.yukonga.miuix.kmp.icon.extended.Home
import top.yukonga.miuix.kmp.icon.extended.Image
import top.yukonga.miuix.kmp.icon.extended.Settings
import top.yukonga.miuix.kmp.preference.OverlayDropdownPreference
import top.yukonga.miuix.kmp.preference.SliderPreference
import top.yukonga.miuix.kmp.preference.SwitchPreference
import top.yukonga.miuix.kmp.shared.generated.resources.Res
import top.yukonga.miuix.kmp.shared.generated.resources.blur_test
import top.yukonga.miuix.kmp.theme.MiuixTheme
import ui.isInDarkTheme

private val Materials: List<Pair<String, GlassStyle>> = listOf(
    "Common Small Thin" to GlassStyles.CommonSmallThin,
    "Common Small Normal" to GlassStyles.CommonSmallNormal,
    "Common Small Regular" to GlassStyles.CommonSmallRegular,
    "Common Small Semibold" to GlassStyles.CommonSmallSemibold,
    "Common Small Bold" to GlassStyles.CommonSmallBold,
    "Common Medium Thin Low" to GlassStyles.CommonMediumThinLow,
    "Common Medium Thin High" to GlassStyles.CommonMediumThinHigh,
    "Common Medium Regular Low Light" to GlassStyles.CommonMediumRegularLowLight,
    "Common Medium Regular High Light" to GlassStyles.CommonMediumRegularHighLight,
    "Common Medium Regular Dark" to GlassStyles.CommonMediumRegularDark,
    "Common Medium Bold Light" to GlassStyles.CommonMediumBoldLight,
    "Common Medium Bold Dark" to GlassStyles.CommonMediumBoldDark,
    "Common Medium Bold Low" to GlassStyles.CommonMediumBoldLow,
    "Common Large Regular" to GlassStyles.CommonLargeRegular,
    "Common Large Demibold" to GlassStyles.CommonLargeDemibold,
    "Bionic Medium Thin" to GlassStyles.BionicMediumThin,
    "Bionic Medium Normal" to GlassStyles.BionicMediumNormal,
    "Bionic Medium Regular" to GlassStyles.BionicMediumRegular,
    "Tint Purple" to GlassStyles.TintPurple,
    "Tint Orange" to GlassStyles.TintOrange,
    "Tint Blue" to GlassStyles.TintBlue,
    "Tint Yellow" to GlassStyles.TintYellow,
    "Tint Brown" to GlassStyles.TintBrown,
    "Tint Black Thin" to GlassStyles.TintBlackThin,
    "Tint Black Regular" to GlassStyles.TintBlackRegular,
)

private val Strokes: List<Pair<String, GlassStroke?>> = listOf(
    "None" to null,
    "Small" to GlassStrokes.SmallLight,
    "Middle" to GlassStrokes.MiddleLight,
    "Big" to GlassStrokes.BigLight,
)

private val StrokesDark: List<GlassStroke?> = listOf(
    null,
    GlassStrokes.SmallDark,
    GlassStrokes.MiddleDark,
    GlassStrokes.BigDark,
)

private val NavItems: List<GlassNavigationItem> = listOf(
    GlassNavigationItem(MiuixIcons.Home, "Home"),
    GlassNavigationItem(MiuixIcons.Create, "Create"),
    GlassNavigationItem(MiuixIcons.Image, "Gallery"),
    GlassNavigationItem(MiuixIcons.Settings, "Settings"),
)

private val OverlayNames = listOf("None", "Popup", "Dialog")
private const val OVERLAY_NONE = 0
private const val OVERLAY_POPUP = 1
private const val OVERLAY_DIALOG = 2

private const val MATERIAL_LIGHT = 7
private const val MATERIAL_DARK = 9

/**
 * The glass components on a flat page, the way the source system shows them.
 *
 * The page is flat by default. A busy photograph flatters a refractive material and hides a rim
 * that is a pixel too wide, and a flat page is what the system's own settings pages actually are.
 * The wallpaper switch puts a photograph back for the opposite reason: over a flat page the blur
 * and the refraction have nothing to act on, so neither can be judged at all.
 *
 * @param padding Window padding supplied by the host scaffold.
 */
@Composable
fun GlassPage(padding: PaddingValues) {
    val navigator = LocalNavigator.current
    val isInDark = isInDarkTheme()
    val backdrop = rememberLayerBackdrop()
    val scrollBehavior = MiuixScrollBehavior()
    val collapseRamp = GlassTopAppBarDefaults.collapseRamp(scrollBehavior)

    var materialIndex by remember(isInDark) {
        mutableIntStateOf(if (isInDark) MATERIAL_DARK else MATERIAL_LIGHT)
    }
    var strokeIndex by remember { mutableIntStateOf(2) }
    var cornerRadius by remember { mutableFloatStateOf(26f) }
    var smoothing by remember { mutableFloatStateOf(1f) }
    var alpha by remember { mutableFloatStateOf(1f) }
    var overlayIndex by remember { mutableIntStateOf(OVERLAY_NONE) }
    var navIndex by remember { mutableIntStateOf(0) }
    var primaryTab by remember { mutableIntStateOf(0) }
    var neutralTab by remember { mutableIntStateOf(0) }
    var joinedTab by remember { mutableIntStateOf(0) }
    var visible by remember { mutableStateOf(false) }
    var wallpaper by remember { mutableStateOf(false) }

    val style = Materials[materialIndex].second
    val stroke = if (isInDark) StrokesDark[strokeIndex] else Strokes[strokeIndex].second
    val shape = GlassShape(cornerRadius.dp, smoothing)

    if (!isRuntimeShaderSupported()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "This device has no runtime shader support.")
        }
        return
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        // The bar goes in the scaffold's own slot, not on top of the page as an overlay.
        // The scaffold measures it and hands its *current* height back as the content
        // padding in the same layout pass, so the list stays glued to the bar's bottom edge
        // while it collapses. Reserving a fixed height instead leaves the title collapsing
        // at its own rate while the list slides underneath, which is what reads as lag.
        topBar = {
            // The tab rows go in the bar's own slot, pinned under the title, and the list scrolls
            // beneath them. That is where the source system puts them, and it is also the only
            // place a glass surface can sit: inside the recorded layer it would be drawing the
            // very layer that records it.
            GlassTopAppBar(
                title = "Glass",
                scrollBehavior = scrollBehavior,
                style = style,
                alpha = alpha,
                stroke = stroke,
                navigationIcon = {
                    Icon(
                        imageVector = MiuixIcons.Back,
                        contentDescription = "Back",
                        modifier = Modifier
                            .size(20.dp)
                            .clickable(interactionSource = null, indication = null) { navigator.pop() },
                        tint = MiuixTheme.colorScheme.onSurface,
                    )
                },
                actions = {
                    GlassIconButton(
                        onClick = { overlayIndex = OVERLAY_POPUP },
                        surfaceAlpha = collapseRamp * alpha,
                        style = style,
                        stroke = stroke,
                    ) {
                        Icon(
                            imageVector = MiuixIcons.Settings,
                            contentDescription = "Sort",
                            modifier = Modifier.size(20.dp),
                            tint = MiuixTheme.colorScheme.onSurface,
                        )
                    }
                },
                bottomContent = {
                    Column(
                        modifier = Modifier.padding(horizontal = 12.dp).padding(bottom = 6.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                    ) {
                        GlassTabRow(
                            tabs = listOf("Sound", "Haptics"),
                            selectedIndex = primaryTab,
                            onSelect = { primaryTab = it },
                            backdrop = backdrop,
                            style = style,
                            alpha = alpha,
                            surfaceAlpha = collapseRamp,
                            stroke = stroke,
                        )
                        GlassTabRow(
                            tabs = listOf("Home", "Widget", "Theme", "Paper"),
                            selectedIndex = neutralTab,
                            onSelect = { neutralTab = it },
                            backdrop = backdrop,
                            style = style,
                            alpha = alpha,
                            surfaceAlpha = collapseRamp,
                            stroke = stroke,
                            height = GlassTabRowDefaults.NeutralHeight,
                            colors = GlassTabRowDefaults.neutralColors(),
                        )
                        GlassSegmentedTabRow(
                            tabs = listOf("Privacy", "Security"),
                            selectedIndex = joinedTab,
                            onSelect = { joinedTab = it },
                            backdrop = backdrop,
                            style = style,
                            alpha = alpha,
                            surfaceAlpha = collapseRamp,
                            stroke = stroke,
                        )
                    }
                },
            )
        },
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize()) {
            // The page's own colour belongs inside the recorded layer, not behind it. A glass surface
            // over a part of the page the list does not reach would otherwise sample transparent
            // black, and unpremultiplying that turns rounding into visible grain.
            Box(modifier = Modifier.fillMaxSize().layerBackdrop(backdrop).background(MiuixTheme.colorScheme.surface)) {
                if (wallpaper) {
                    Image(
                        painter = painterResource(Res.drawable.blur_test),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop,
                    )
                }
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .nestedScroll(scrollBehavior.nestedScrollConnection),
                    contentPadding = PaddingValues(
                        top = innerPadding.calculateTopPadding(),
                        bottom = 160.dp + padding.calculateBottomPadding(),
                    ),
                ) {
                    // The demo's own components, the same ones the home page lists. They give the
                    // glass something real to sit over: cards, text, controls and empty page in
                    // turn, at the density a page actually has. A page written for this screen
                    // alone would only ever show the material what it was tuned against.
                    switchSection()
                    buttonSection()
                    cardSection()
                    sliderSection()
                    textFieldSection()
                    item(key = "controls-title") { SmallTitle(text = "Material") }
                    item(key = "controls") {
                        Card(modifier = Modifier.padding(horizontal = 12.dp)) {
                            OverlayDropdownPreference(
                                title = "Material",
                                items = Materials.map { it.first },
                                selectedIndex = materialIndex,
                                onSelectedIndexChange = { materialIndex = it },
                            )
                            OverlayDropdownPreference(
                                title = "Bloom Stroke",
                                items = Strokes.map { it.first },
                                selectedIndex = strokeIndex,
                                onSelectedIndexChange = { strokeIndex = it },
                            )
                            OverlayDropdownPreference(
                                title = "Overlay",
                                items = OverlayNames,
                                selectedIndex = overlayIndex,
                                onSelectedIndexChange = { overlayIndex = it },
                            )
                            SwitchPreference(
                                title = "Wallpaper",
                                summary = "A photograph behind the glass, to judge the refraction",
                                checked = wallpaper,
                                onCheckedChange = { wallpaper = it },
                            )
                            SwitchPreference(
                                title = "Show Dialog",
                                checked = visible,
                                onCheckedChange = {
                                    visible = it
                                    overlayIndex = if (it) OVERLAY_DIALOG else OVERLAY_NONE
                                },
                            )
                            HorizontalDivider(Modifier.fillMaxWidth().padding(horizontal = 16.dp))
                            SliderPreference(
                                title = "Corner Radius",
                                valueText = "${cornerRadius.toInt()}",
                                value = cornerRadius / 64f,
                                onValueChange = { cornerRadius = it * 64f },
                                insideMargin = PaddingValues(16.dp, 16.dp, 16.dp, 0.dp),
                            )
                            SliderPreference(
                                title = "Corner Smoothing",
                                valueText = "${(smoothing * 100).toInt() / 100f}",
                                value = smoothing,
                                onValueChange = { smoothing = it },
                                insideMargin = PaddingValues(16.dp, 16.dp, 16.dp, 0.dp),
                            )
                            SliderPreference(
                                title = "Alpha",
                                valueText = "${(alpha * 100).toInt() / 100f}",
                                value = alpha,
                                onValueChange = { alpha = it },
                            )
                        }
                    }
                }
            }
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .navigationBarsPadding()
                    .padding(horizontal = 27.dp, vertical = 9.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                GlassNavigationBar(
                    items = NavItems,
                    selectedIndex = navIndex,
                    onSelect = { navIndex = it },
                    backdrop = backdrop,
                    style = style,
                    alpha = alpha,
                    stroke = stroke,
                )
            }

            GlassPopup(
                expanded = overlayIndex == OVERLAY_POPUP,
                backdrop = backdrop,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 64.dp + padding.calculateTopPadding(), end = 16.dp),
                transformOrigin = TransformOrigin(1f, 0f),
                style = style,
                alpha = alpha,
                stroke = stroke,
            ) {
                listOf("Sort by name", "Sort by date", "Sort by size").forEach { label ->
                    Text(
                        text = label,
                        style = MiuixTheme.textStyles.body1,
                        modifier = Modifier
                            .clickable(interactionSource = null, indication = null) { overlayIndex = OVERLAY_NONE }
                            .padding(horizontal = 20.dp, vertical = 13.dp),
                    )
                }
            }

            GlassDialog(
                visible = overlayIndex == OVERLAY_DIALOG,
                onDismissRequest = {
                    overlayIndex = OVERLAY_NONE
                    visible = false
                },
                backdrop = backdrop,
                scrimAlpha = if (isInDark) {
                    GlassOverlayDefaults.ScrimAlphaDark
                } else {
                    GlassOverlayDefaults.ScrimAlphaLight
                },
                style = style,
                alpha = alpha,
                stroke = stroke,
            ) {
                Text(text = "Glass Dialog", style = MiuixTheme.textStyles.title4)
                Text(
                    text = "The dialog wears the material the page is set to.",
                    style = MiuixTheme.textStyles.body2,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 8.dp, bottom = 18.dp),
                )
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    TextButton(
                        text = "Cancel",
                        onClick = {
                            overlayIndex = OVERLAY_NONE
                            visible = false
                        },
                        modifier = Modifier.weight(1f),
                    )
                    Button(
                        onClick = {
                            overlayIndex = OVERLAY_NONE
                            visible = false
                        },
                        modifier = Modifier.weight(1f),
                    ) {
                        Text(text = "Confirm", style = MiuixTheme.textStyles.button)
                    }
                }
            }
        }
    }
}
