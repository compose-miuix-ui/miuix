// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.transition.internal

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.isSpecified
import androidx.compose.ui.unit.Dp
import top.yukonga.miuix.kmp.nav.core.NavKey
import top.yukonga.miuix.kmp.nav.transition.NavMotion
import top.yukonga.miuix.kmp.nav.transition.NavTransition
import top.yukonga.miuix.kmp.nav.transition.NavTransitionAnchor
import top.yukonga.miuix.kmp.nav.transition.NavTransitionScope
import top.yukonga.miuix.kmp.nav.transition.NavTransitions

internal data class NavExpansionPreset(
    private val containerColor: Color,
    private val cornerRadius: Dp,
    override val motion: NavMotion,
) : NavTransition,
    NavTransitionHostBinding {
    override val needsSource: Boolean = true

    override fun Modifier.transformEntry(scope: NavTransitionScope): Modifier = this

    override fun bind(context: NavTransitionHostContext, entryKey: NavKey?): NavTransition {
        val anchor = entryKey?.let(context.expansion::anchorState)
        val color = { navExpansionContainerColor(containerColor, context.effects.backdropColor) }
        return NavExpansionTransition(
            state = context.expansion,
            anchor = anchor,
            containerColor = color,
            delegate = NavExpandTransition(
                containerColor = color,
                cornerRadius = cornerRadius,
                targetCornerRadius = { context.effects.cornerClipRadius },
                initialScale = 0.85f,
                motion = motion,
                anchor = { anchor?.value },
            ),
        )
    }
}

private class NavExpansionTransition(
    private val state: NavExpansionState,
    private val anchor: State<NavTransitionAnchor?>?,
    private val containerColor: () -> Color,
    private val delegate: NavTransition,
) : NavTransition by delegate,
    NavHostTransition,
    NavTransitionReentry {
    // The upper page also governs the covered layer: resolve against its owner, not the
    // scope's entryKey, which belongs to whichever layer is currently being transformed.
    private fun transition(): NavTransition {
        val source = anchor?.value ?: return NavTransitions.MiuixDefault
        return if (navExpansionContainerColor(containerColor(), source.containerColor).isSpecified) delegate else NavTransitions.MiuixDefault
    }

    override fun Modifier.transformEntry(scope: NavTransitionScope): Modifier = with(transition()) { transformEntry(scope) }

    override fun Modifier.transformContent(scope: NavTransitionScope): Modifier = with(transition()) { transformContent(scope) }

    override fun handlesCornerClip(scope: NavTransitionScope): Boolean = transition().handlesCornerClip(scope)

    override fun scrimFraction(scope: NavTransitionScope): Float = transition().scrimFraction(scope)

    @Composable
    override fun rememberRenderer(scope: NavTransitionScope): NavEntryRenderer? = (transition() as? NavHostTransition)?.rememberRenderer(scope)

    override fun reentryAt(key: NavKey, positionInWindow: Offset): (() -> Unit)? = state.reentryAt(key, positionInWindow)
}

/** A fading page needs an opaque surface underneath it throughout the handoff. */
internal fun navExpansionContainerColor(preferred: Color, fallback: Color): Color = when {
    preferred.isSpecified && preferred.alpha == 1f -> preferred
    fallback.isSpecified && fallback.alpha == 1f -> fallback
    else -> Color.Unspecified
}
