// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.basic

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Matrix
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.theme.MiuixTheme

/**
 * A search input's clear button. The translucent circle has a cutout that reveals the input background.
 *
 * @param onClick The callback that clears the search query.
 * @param modifier The modifier applied to the button.
 * @param contentDescription The accessibility label for the clear action.
 */
@Composable
fun SearchClearButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentDescription: String = "Clear",
) {
    val interactionSource = remember { MutableInteractionSource() }
    val pressed = interactionSource.collectIsPressedAsState()
    val tint = MiuixTheme.colorScheme.onSurfaceVariantActions
    val gray = (tint.red + tint.green + tint.blue) / 3f
    val neutralTint = tint.copy(red = gray, green = gray, blue = gray)
    Box(
        modifier = modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                role = Role.Button,
                onClick = onClick,
            )
            .semantics { this.contentDescription = contentDescription },
        contentAlignment = Alignment.Center,
    ) {
        Box(
            Modifier
                .size(20.dp)
                .drawWithCache {
                    // The cross is a hole in the circle, so the search field shows through it.
                    val unit = size.minDimension / 20f
                    val path = Path().apply {
                        fillType = PathFillType.EvenOdd
                        moveTo(14.3f, 5.98f)
                        quadraticTo(14.58f, 6.31f, 14.3f, 6.64f)
                        lineTo(10.94f, 10f)
                        lineTo(14.3f, 13.36f)
                        quadraticTo(14.58f, 13.69f, 14.3f, 14.02f)
                        lineTo(14.02f, 14.3f)
                        quadraticTo(13.69f, 14.58f, 13.36f, 14.3f)
                        lineTo(10f, 10.94f)
                        lineTo(6.64f, 14.3f)
                        quadraticTo(6.31f, 14.58f, 5.98f, 14.3f)
                        lineTo(5.7f, 14.02f)
                        quadraticTo(5.42f, 13.69f, 5.7f, 13.36f)
                        lineTo(9.06f, 10f)
                        lineTo(5.7f, 6.64f)
                        quadraticTo(5.42f, 6.31f, 5.7f, 5.98f)
                        lineTo(5.98f, 5.7f)
                        quadraticTo(6.31f, 5.42f, 6.64f, 5.7f)
                        lineTo(10f, 9.06f)
                        lineTo(13.36f, 5.7f)
                        quadraticTo(13.69f, 5.42f, 14.02f, 5.7f)
                        close()
                        addOval(Rect(Offset(10f, 10f), 10f))
                        transform(Matrix().apply { scale(unit, unit) })
                    }
                    onDrawBehind {
                        drawPath(path, neutralTint.copy(alpha = if (pressed.value) 0.4f else 77f / 255f))
                    }
                },
        )
    }
}
