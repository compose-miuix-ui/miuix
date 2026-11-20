// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.nav.transition

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.LayoutCoordinates
import top.yukonga.miuix.kmp.nav.core.LiveNavTransitionScope
import top.yukonga.miuix.kmp.nav.core.NavKey
import top.yukonga.miuix.kmp.nav.runtime.NavChange
import top.yukonga.miuix.kmp.nav.transition.internal.NavEntryRenderer
import top.yukonga.miuix.kmp.nav.transition.internal.NavHostTransition
import top.yukonga.miuix.kmp.nav.transition.internal.NavTransitionHostBinding
import top.yukonga.miuix.kmp.nav.transition.internal.NavTransitionHostContext
import top.yukonga.miuix.kmp.nav.transition.internal.NavTransitionReentry

/**
 * Composes three [NavTransition]s into one that dispatches per drive direction, mirroring the
 * platform split between programmatic transitions and the predictive back gesture:
 *
 * - a gesture owns the float (predictive back or edge swipe; the context stays frozen through
 *   the whole release settle) -> [predictivePop];
 * - the last stack change is a pop ([NavChange.Pop] / [NavChange.MultiPop]) -> [pop];
 * - anything else (push, replace, initial) -> [push].
 *
 * Static contracts merge from their natural sources: [NavTransition.opaqueDepth] is the max of
 * the three (the host keeps a layer alive while ANY branch would), [NavTransition.dismissDirection]
 * comes from [predictivePop] (the edge swipe drives that branch), and [NavTransition.motion]
 * takes commit/cancel from [predictivePop] and programmatic from [pop]. `push.motion` is never
 * consumed — route-level asymmetry is available via per-route transition overrides instead.
 *
 * Known limit: under the grab-anytime model a gesture claiming the stack mid-programmatic-settle
 * switches the dispatch from [pop]/[push] to [predictivePop] at the grab instant; if the two
 * branches disagree geometrically at that depth the style jumps for one frame. The platform
 * avoids this by making its commit animation uninterruptible; this library keeps interruption
 * and documents the trade-off — author branches that stay close in the grabbable range when
 * that matters.
 *
 * @param push transition for forward changes (and replace/initial states).
 * @param pop transition for programmatic pops; defaults to [push].
 * @param predictivePop transition while a gesture drives; defaults to [pop].
 */
fun navDirectionalTransition(
    push: NavTransition,
    pop: NavTransition = push,
    predictivePop: NavTransition = pop,
): NavTransition = NavDirectionalTransition(push = push, pop = pop, predictivePop = predictivePop)

@Stable
private class NavDirectionalTransition(
    private val push: NavTransition,
    private val pop: NavTransition,
    private val predictivePop: NavTransition,
) : NavTransition,
    NavHostTransition,
    NavTransitionHostBinding,
    NavTransitionReentry {
    override val needsSource: Boolean = (push as? NavTransitionHostBinding)?.needsSource == true ||
        (pop as? NavTransitionHostBinding)?.needsSource == true ||
        (predictivePop as? NavTransitionHostBinding)?.needsSource == true

    override val opaqueDepth: Float =
        maxOf(push.opaqueDepth, pop.opaqueDepth, predictivePop.opaqueDepth)

    override val dismissDirection: NavSwipeDirection = predictivePop.dismissDirection

    override val motion: NavMotion = NavMotion(
        commit = predictivePop.motion.commit,
        cancel = predictivePop.motion.cancel,
        programmatic = pop.motion.programmatic,
    )

    override fun bind(context: NavTransitionHostContext, entryKey: NavKey?): NavTransition {
        val boundPush = context.resolve(push, entryKey)
        val boundPop = if (pop == push) boundPush else context.resolve(pop, entryKey)
        val boundPredictivePop = when (predictivePop) {
            pop -> boundPop
            push -> boundPush
            else -> context.resolve(predictivePop, entryKey)
        }
        return if (boundPush === push && boundPop === pop && boundPredictivePop === predictivePop) {
            this
        } else {
            NavDirectionalTransition(boundPush, boundPop, boundPredictivePop)
        }
    }

    private fun branchFor(scope: NavTransitionScope): NavTransition = when {
        scope.isGestureDriven() -> predictivePop
        scope.change is NavChange.Pop || scope.change is NavChange.MultiPop -> pop
        else -> push
    }

    @Composable
    override fun rememberRenderer(scope: NavTransitionScope): NavEntryRenderer? = (branchFor(scope) as? NavHostTransition)?.rememberRenderer(scope)

    override fun Modifier.transformEntry(scope: NavTransitionScope): Modifier {
        val branch = branchFor(scope)
        return with(branch) { transformEntry(scope) }
    }

    override fun scrimFraction(scope: NavTransitionScope): Float = branchFor(scope).scrimFraction(scope)

    override fun Modifier.transformContent(scope: NavTransitionScope): Modifier = with(branchFor(scope)) { transformContent(scope) }

    override fun handlesCornerClip(scope: NavTransitionScope): Boolean = branchFor(scope).handlesCornerClip(scope)

    override fun reentryAt(key: NavKey, host: LayoutCoordinates, positionInWindow: Offset): ((Offset) -> Unit)? = (push as? NavTransitionReentry)?.reentryAt(key, host, positionInWindow)
        ?: (pop as? NavTransitionReentry)?.reentryAt(key, host, positionInWindow)
        ?: (predictivePop as? NavTransitionReentry)?.reentryAt(key, host, positionInWindow)
}

/**
 * Composition-safe gesture check: the live scope exposes a derived threshold-level flag (the raw
 * [NavTransitionScope.gesture] is a fresh instance per move event and would recompose hosts per
 * event if read during composition); foreign scope implementations fall back to the raw field.
 */
private fun NavTransitionScope.isGestureDriven(): Boolean = (this as? LiveNavTransitionScope)?.gestureActive ?: (gesture != null)
