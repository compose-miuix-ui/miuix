// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.glass

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.snap
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlurEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.blur.Backdrop
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.basic.ArrowRight
import top.yukonga.miuix.kmp.icon.basic.Check
import top.yukonga.miuix.kmp.layout.CascadingPopupDefaults
import top.yukonga.miuix.kmp.theme.MiuixTheme

/** Default values for [GlassPopup]. */
object GlassPopupDefaults {

    /** Minimum popup width. */
    val MinWidth: Dp = 200.dp

    /** Maximum popup width. */
    val MaxWidth: Dp = 288.dp

    /** Maximum popup height before content scrolls. */
    val MaxHeight: Dp = 416.dp

    /** Minimum margin from the window edges. */
    val SafeMargin: Dp = 12.dp

    /** Vertical content padding. */
    val ContentPaddingVertical: Dp = 8.dp

    /** Corner radius of an anchored menu. */
    val CornerRadius: Dp = 24.dp

    /** Corner radius of a list dropdown. */
    val DropdownCornerRadius: Dp = 24.dp

    /** Minimum menu item height. */
    val ItemMinHeight: Dp = 44.dp

    /** Horizontal menu item padding. */
    val ItemPaddingHorizontal: Dp = 20.dp

    /** Vertical menu item padding. */
    val ItemPaddingVertical: Dp = 10.dp

    /** Leading icon size. */
    val ItemIconSize: Dp = 24.dp

    /** Spacing between an item label and adjacent icons. */
    val ItemIconGap: Dp = 8.dp

    /** Horizontal divider inset. */
    val DividerPaddingHorizontal: Dp = 20.dp

    /** Vertical divider padding. */
    val DividerPaddingVertical: Dp = 7.75.dp

    /** Divider thickness. */
    val DividerThickness: Dp = 0.5.dp

    /** Selected item checkmark size. */
    val ItemTickSize: Dp = 24.dp

    /** Submenu chevron size. */
    val ItemArrowSize: Dp = 16.dp

    /** Horizontal inset of the pressed item background. */
    val ItemPressInset: Dp = 7.dp

    /** Corner radius of the pressed item background. */
    val ItemPressRadius: Dp = 15.dp

    /** Pressed item background color. */
    @Composable
    fun itemPressedColor(): Color = if (MiuixTheme.colorScheme.background.luminance() < 0.5f) {
        Color.White.copy(alpha = 0.1f)
    } else {
        Color.Black.copy(alpha = 0.1f)
    }

    /** Overlay color for a primary menu behind a secondary menu. */
    @Composable
    fun maskColor(): Color = if (MiuixTheme.colorScheme.background.luminance() < 0.5f) {
        Color.Black.copy(alpha = 0.4f)
    } else {
        Color.White.copy(alpha = 0.4f)
    }

    /** Creates the popup surface appearance. */
    @Composable
    fun visuals(
        style: GlassStyle = GlassDefaults.Style,
        alpha: Float = 1f,
        stroke: GlassStroke? = stroke(),
        shadow: GlassShadow? = GlassShadows.Regular,
        material: GlassMaterial? = material(),
        containerColor: Color = containerColor(),
    ): GlassPopupVisuals = GlassPopupVisuals(style, alpha, stroke, shadow, material, containerColor)

    /** Creates the popup shape. */
    @Composable
    fun shape(cornerRadius: Dp = CornerRadius): GlassShape = GlassShape(cornerRadius)

    /** Default popup material for the current theme. */
    @Composable
    fun material(): GlassMaterial = GlassMaterials.popupViewGlass(
        isDark = MiuixTheme.colorScheme.background.luminance() < 0.5f,
    )

    /** Default popup bloom stroke for the current theme. */
    @Composable
    fun stroke(): GlassStroke = GlassStrokes.forTheme(
        isDark = MiuixTheme.colorScheme.background.luminance() < 0.5f,
        light = GlassStrokes.SmallLight,
        dark = GlassStrokes.SmallDark,
    )

    /** Opaque fallback color for the current theme. */
    @Composable
    fun containerColor(): Color = if (MiuixTheme.colorScheme.background.luminance() < 0.5f) {
        Color(0xFF2C2C2C)
    } else {
        Color.White
    }
}

/**
 * A glass menu anchored to a control, with an edge-reveal transition.
 *
 * Place it in the page's root [BoxScope] to avoid clipping by the anchor's container.
 * Keep it composed until the exit animation finishes.
 *
 * @param show Whether the popup is open.
 * @param onDismissRequest Called when an outside tap or Back requests dismissal.
 * @param anchorBounds The control's bounds in this composable's coordinate space. Use resting
 *   bounds when the anchor belongs to a scaled primary menu.
 * @param backdrop Background sampled by the panel. Exclude the popup itself. `null` uses an
 *   opaque fill while retaining the configured bloom stroke and shadow.
 * @param modifier Modifier applied to the panel.
 * @param sizing Panel size limits and window margin.
 * @param visuals Panel surface appearance.
 * @param cornerRadius Expanded panel corner radius.
 * @param secondary Whether to use [GlassSecondaryPopup] geometry. Use that component directly
 *   to share the primary popup's appearance through `materialAnchor`.
 * @param contentPadding Padding around the items.
 * @param content Menu items.
 */
@Composable
fun BoxScope.GlassPopup(
    show: Boolean,
    onDismissRequest: () -> Unit,
    anchorBounds: Rect,
    backdrop: Backdrop?,
    modifier: Modifier = Modifier,
    sizing: GlassPopupSizing = GlassPopupSizing(),
    visuals: GlassPopupVisuals = GlassPopupDefaults.visuals(),
    cornerRadius: Dp = GlassPopupDefaults.CornerRadius,
    secondary: Boolean = false,
    contentPadding: PaddingValues = PaddingValues(vertical = GlassPopupDefaults.ContentPaddingVertical),
    content: @Composable ColumnScope.() -> Unit,
) {
    if (secondary) {
        GlassSecondaryPopup(
            show = show,
            onDismissRequest = onDismissRequest,
            anchorBounds = anchorBounds,
            backdrop = backdrop,
            modifier = modifier,
            sizing = sizing,
            visuals = visuals,
            cornerRadius = cornerRadius,
            contentPadding = contentPadding,
            content = content,
        )
        return
    }
    val progress = animateFloatAsState(
        targetValue = if (show) 1f else 0f,
        animationSpec = GlassMotion.popupMorph(),
        label = "glassPopupBounds",
    )
    val fade by animateFloatAsState(
        targetValue = if (show) 1f else 0f,
        animationSpec = GlassMotion.popupMorphFade(show),
        label = "glassPopupFade",
    )
    val sharpness by animateFloatAsState(
        targetValue = if (show) 1f else 0f,
        animationSpec = GlassMotion.popupMorphBlur(),
        label = "glassPopupBlur",
    )
    val active by remember(show, progress) {
        derivedStateOf { progress.value > 0.001f || show }
    }
    rememberGlassPopupBackProgress(
        show = show,
        active = active,
        enabled = show,
        retainWhenInactive = false,
        resetSpec = GlassMotion.popupMorph(),
        onDismissRequest = onDismissRequest,
    )
    if (!active) return

    val startRadius = GlassMotion.POPUP_START_CORNER_DP.dp
    val layoutDirection = LocalLayoutDirection.current
    GlassPopupSurface(
        onDismissRequest = onDismissRequest,
        backdrop = backdrop,
        sizing = sizing,
        visuals = visuals,
        contentPadding = contentPadding,
        panelLayer = {},
        overlay = {},
        frame = { end, page ->
            val settled = placeGlassPopup(anchorBounds, end, sizing.safeMargin.toPx(), page, layoutDirection)
            directionFrame(settled, end, progress.value, startRadius, cornerRadius, layoutDirection)
        },
        contentLayer = { _, _ ->
            val t = progress.value.coerceIn(0f, 1f)
            val scale = GlassMotion.POPUP_START_WIDTH + (1f - GlassMotion.POPUP_START_WIDTH) * t
            scaleX = scale
            scaleY = scale
            val blur = (1f - sharpness) * GlassMotion.POPUP_MORPH_BLUR_PX
            renderEffect = if (blur > 0.5f) BlurEffect(blur, blur, TileMode.Decal) else null
            alpha = fade
        },
        modifier = modifier,
        scrollable = true,
        content = content,
    )
}

/** Interpolates the panel bounds while keeping two edges fixed. */
private fun directionFrame(
    placement: GlassPopupPlacement,
    end: Size,
    progress: Float,
    startRadius: Dp,
    endRadius: Dp,
    layoutDirection: androidx.compose.ui.unit.LayoutDirection,
): GlassPopupFrame {
    val t = progress.coerceIn(0f, 1f)
    val width = end.width * (GlassMotion.POPUP_START_WIDTH + (1f - GlassMotion.POPUP_START_WIDTH) * t)
    val endRatio = if (end.width > 0f) end.height / end.width else 1f
    val ratio = GlassMotion.POPUP_START_RATIO + (endRatio - GlassMotion.POPUP_START_RATIO) * t
    val height = (width * ratio).coerceAtMost(end.height)
    val left = if (layoutDirection == androidx.compose.ui.unit.LayoutDirection.Ltr) {
        placement.rect.right - width
    } else {
        placement.rect.left
    }
    val top = if (placement.alignTop) placement.rect.top else placement.rect.bottom - height
    return GlassPopupFrame(
        rect = Rect(left, top, left + width, top + height),
        cornerRadius = startRadius + (endRadius - startRadius) * t,
    )
}

/**
 * A menu item with an optional icon, summary, selection checkmark and submenu chevron.
 *
 * @param text The label.
 * @param onClick Called when the row is tapped.
 * @param modifier The modifier applied to the row.
 * @param icon Optional icon before the label.
 * @param summary Optional second line under the label. Usually the row's current setting.
 * @param enabled Whether the row responds to a tap.
 * @param selected Whether to show a selection checkmark.
 * @param showArrow Whether to show a submenu chevron.
 * @param arrowRotation Chevron rotation in degrees. An open submenu uses
 *   [CascadingPopupDefaults.arrowRotation].
 * @param contentColor Colour of the label and the icon.
 * @param summaryColor Colour of the second line.
 * @param selectedContentColor Colour of a selected row's label and its tick.
 * @param pressedColor Pressed background color.
 */
@Composable
fun GlassPopupItem(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    summary: String? = null,
    enabled: Boolean = true,
    selected: Boolean = false,
    showArrow: Boolean = false,
    arrowRotation: () -> Float = { 0f },
    contentColor: Color = MiuixTheme.colorScheme.onSurfaceContainer,
    summaryColor: Color = MiuixTheme.colorScheme.onSurfaceContainerVariant,
    selectedContentColor: Color = MiuixTheme.colorScheme.primary,
    pressedColor: Color = GlassPopupDefaults.itemPressedColor(),
) {
    val interactionSource = remember { MutableInteractionSource() }
    val pressed by interactionSource.collectIsPressedAsState()
    val pressFill = animateColorAsState(
        targetValue = if (pressed) pressedColor else Color.Transparent,
        animationSpec = if (pressed) snap() else GlassMotion.popupPressExit(),
        label = "glassPopupItemPress",
    )
    val color = when {
        !enabled -> contentColor.copy(alpha = 0.4f)
        selected -> selectedContentColor
        else -> contentColor
    }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = GlassPopupDefaults.ItemMinHeight)
            .padding(horizontal = GlassPopupDefaults.ItemPressInset)
            .drawBehind {
                drawRoundRect(
                    color = pressFill.value,
                    cornerRadius = CornerRadius(GlassPopupDefaults.ItemPressRadius.toPx()),
                )
            }
            .selectable(
                selected = selected,
                enabled = enabled,
                interactionSource = interactionSource,
                indication = null,
            ) { onClick() }
            .padding(
                horizontal = GlassPopupDefaults.ItemPaddingHorizontal - GlassPopupDefaults.ItemPressInset,
                vertical = GlassPopupDefaults.ItemPaddingVertical,
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (icon != null) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier
                    .padding(end = GlassPopupDefaults.ItemIconGap)
                    .size(GlassPopupDefaults.ItemIconSize),
                tint = color,
            )
        }
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = text,
                fontSize = MiuixTheme.textStyles.body1.fontSize,
                fontWeight = FontWeight.Medium,
                color = color,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            if (summary != null) {
                Text(
                    text = summary,
                    style = MiuixTheme.textStyles.body2,
                    color = summaryColor,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
        Icon(
            imageVector = MiuixIcons.Basic.Check,
            contentDescription = null,
            modifier = Modifier
                .padding(start = GlassPopupDefaults.ItemIconGap)
                .size(GlassPopupDefaults.ItemTickSize),
            tint = if (selected) selectedContentColor else Color.Transparent,
        )
        if (showArrow) {
            Icon(
                imageVector = MiuixIcons.Basic.ArrowRight,
                contentDescription = null,
                modifier = Modifier
                    .padding(start = GlassPopupDefaults.ItemIconGap)
                    .size(GlassPopupDefaults.ItemArrowSize)
                    .graphicsLayer { rotationZ = arrowRotation() },
                tint = summaryColor,
            )
        }
    }
}
