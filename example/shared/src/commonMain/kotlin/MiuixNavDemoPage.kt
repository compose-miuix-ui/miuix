// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import component.BackNavigationIcon
import kotlinx.serialization.Serializable
import top.yukonga.miuix.kmp.basic.BasicComponent
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.MiuixScrollBehavior
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.nav.core.NavDisplay
import top.yukonga.miuix.kmp.nav.core.NavDisplayEffects
import top.yukonga.miuix.kmp.nav.core.NavKey
import top.yukonga.miuix.kmp.nav.core.rememberNavBackStack
import top.yukonga.miuix.kmp.nav.transition.NavTransition
import top.yukonga.miuix.kmp.nav.transition.NavTransitions
import top.yukonga.miuix.kmp.nav.transition.navGraphicsTransition
import top.yukonga.miuix.kmp.preference.ArrowPreference
import top.yukonga.miuix.kmp.theme.MiuixTheme
import utils.AdaptiveTopAppBar

/**
 * Self-contained miuix-nav demo routes.
 * Kept private to this demo screen so they never collide with the app's nav3 [navigation3.Route].
 */
@Serializable
private sealed interface DemoRoute : NavKey {
    @Serializable
    data object Home : DemoRoute

    @Serializable
    data class Detail(val index: Int) : DemoRoute

    @Serializable
    data object Modal : DemoRoute

    @Serializable
    data object Custom : DemoRoute
}

/**
 * miuix-nav showcase screen.
 *
 * Hosts an independent miuix-nav back stack and [NavDisplay], demonstrating continuous-depth
 * navigation (single/continuous push & pop), per-route transitions, custom float-depth
 * transitions and gesture back. It is reached from the example app through the legacy nav3
 * route `Route.MiuixNav`, so the two runtimes coexist without coupling.
 */
@Composable
fun MiuixNavDemoPage(
    padding: PaddingValues,
) {
    val appState = LocalAppState.current
    val isWideScreen = LocalIsWideScreen.current
    val outerNavigator = LocalNavigator.current
    val topAppBarScrollBehavior = MiuixScrollBehavior()

    // Independent miuix-nav back stack; owns its own save/restore via rememberNavBackStack.
    // Explicit type arg <DemoRoute> so the whole sealed hierarchy is serializable and pushes
    // of Detail/Modal/Custom work (sealed-interface footgun: single-object seed would infer T=Home).
    val backStack = rememberNavBackStack<DemoRoute>(DemoRoute.Home)

    Scaffold(
        topBar = {
            AdaptiveTopAppBar(
                title = "miuix-nav Demo",
                showTopAppBar = appState.showTopAppBar,
                isWideScreen = isWideScreen,
                scrollBehavior = topAppBarScrollBehavior,
                color = MiuixTheme.colorScheme.surface,
                navigationIcon = {
                    BackNavigationIcon(
                        onClick = {
                            // Pop the inner stack first; fall back to leaving the demo screen.
                            if (backStack.size > 1) {
                                backStack.removeAt(backStack.lastIndex)
                            } else {
                                outerNavigator.pop()
                            }
                        },
                    )
                },
            )
        },
    ) { innerPadding ->
        Column(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
            Row(modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)) {
                Text(
                    text = "Stack depth: ${backStack.size}  •  top: ${backStack.last()::class.simpleName}",
                    color = MiuixTheme.colorScheme.onSurfaceVariantSummary,
                )
            }
            Column(modifier = Modifier.weight(1f).fillMaxSize()) {
                NavDisplay(
                    backStack = backStack,
                    modifier = Modifier.fillMaxSize(),
                    transition = NavTransitions.Cupertino,
                    effects = NavDisplayEffects(
                        enableCornerClip = appState.enableCornerClip,
                        dimAmount = if (appState.enableDim) 0.5f else 0f,
                        blockInputDuringTransition = appState.blockInputDuringTransition,
                    ),
                ) {
                    entry<DemoRoute.Home> {
                        DemoHomeContent(
                            padding = padding,
                            onOpenDetail = { backStack.add(DemoRoute.Detail(1)) },
                            onPushThree = {
                                backStack.add(DemoRoute.Detail(1))
                                backStack.add(DemoRoute.Detail(2))
                                backStack.add(DemoRoute.Detail(3))
                            },
                            onOpenModal = { backStack.add(DemoRoute.Modal) },
                            onOpenCustom = { backStack.add(DemoRoute.Custom) },
                        )
                    }
                    entry<DemoRoute.Detail> { route ->
                        DemoDetailContent(
                            padding = padding,
                            index = route.index,
                            onPushNext = { backStack.add(DemoRoute.Detail(route.index + 1)) },
                            onPopToHome = {
                                while (backStack.size > 1) backStack.removeAt(backStack.lastIndex)
                            },
                        )
                    }
                    entry<DemoRoute.Modal>(transition = NavTransitions.Modal) {
                        DemoModalContent(
                            padding = padding,
                            onClose = { backStack.removeAt(backStack.lastIndex) },
                        )
                    }
                    entry<DemoRoute.Custom>(transition = CustomDepthTransition) {
                        DemoCustomContent(
                            padding = padding,
                            onClose = { backStack.removeAt(backStack.lastIndex) },
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun DemoHomeContent(
    padding: PaddingValues,
    onOpenDetail: () -> Unit,
    onPushThree: () -> Unit,
    onOpenModal: () -> Unit,
    onOpenCustom: () -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize().padding(padding)) {
        SmallTitle(text = "Continuous depth")
        Card(modifier = Modifier.padding(horizontal = 12.dp)) {
            ArrowPreference(
                title = "Push one Detail",
                summary = "Single push (animatedTop N -> N+1)",
                onClick = onOpenDetail,
            )
            ArrowPreference(
                title = "Push three Detail at once",
                summary = "Continuous MultiPush (N -> N+3, one shared spring)",
                onClick = onPushThree,
            )
        }
        SmallTitle(text = "Transitions")
        Card(modifier = Modifier.padding(horizontal = 12.dp)) {
            ArrowPreference(
                title = "Open Modal",
                summary = "Per-route NavTransitions.Modal (bottom-up, keeps lower layer)",
                onClick = onOpenModal,
            )
            ArrowPreference(
                title = "Open Custom transition",
                summary = "navGraphicsTransition reading relativeDepth directly",
                onClick = onOpenCustom,
            )
        }
        SmallTitle(text = "Gesture back")
        Card(modifier = Modifier.padding(horizontal = 12.dp)) {
            BasicComponent(
                title = "Try gesture back",
                summary = "Android: system predictive back. Desktop: press ESC. " +
                    "Both drive the same animatedTop and NavTransition as normal pop.",
            )
        }
    }
}

@Composable
private fun DemoDetailContent(
    padding: PaddingValues,
    index: Int,
    onPushNext: () -> Unit,
    onPopToHome: () -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize().padding(padding)) {
        SmallTitle(text = "Detail #$index")
        Card(modifier = Modifier.padding(horizontal = 12.dp)) {
            ArrowPreference(
                title = "Push next Detail",
                summary = "Go to Detail #${index + 1}",
                onClick = onPushNext,
            )
            ArrowPreference(
                title = "Pop all to Home",
                summary = "Continuous MultiPop back to root",
                onClick = onPopToHome,
            )
        }
    }
}

@Composable
private fun DemoModalContent(
    padding: PaddingValues,
    onClose: () -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize().padding(padding)) {
        SmallTitle(text = "Modal")
        Card(modifier = Modifier.padding(horizontal = 12.dp)) {
            ArrowPreference(
                title = "Close modal",
                summary = "Slide back down; lower layer stayed visible",
                onClick = onClose,
            )
        }
    }
}

@Composable
private fun DemoCustomContent(
    padding: PaddingValues,
    onClose: () -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize().padding(padding)) {
        SmallTitle(text = "Custom transition")
        Card(modifier = Modifier.padding(horizontal = 12.dp)) {
            ArrowPreference(
                title = "Close",
                summary = "Entered via navGraphicsTransition (depth-driven scale + slide)",
                onClick = onClose,
            )
        }
    }
}

/**
 * A fully custom float-depth transition built with [navGraphicsTransition].
 *
 * The block runs inside a deferred-read `graphicsLayer`, so it reads `scope.relativeDepth`
 * (animatedTop - index) every frame without triggering recomposition. As an entry slides from
 * incoming (d in -1..0) through top (d == 0) to covered (d in 0..1) it combines a horizontal
 * slide with a slight depth scale, illustrating arbitrary visual mapping over the single driver.
 */
private val CustomDepthTransition: NavTransition = navGraphicsTransition { scope ->
    val d = scope.relativeDepth
    // Slide: incoming from the right edge, covered drifts left as a parallax.
    translationX = -d * scope.layoutSize.width
    // Depth scale: shrink slightly while covered, never below 0.92.
    val depthScale = 1f - 0.08f * d.coerceIn(0f, 1f)
    scaleX = depthScale
    scaleY = depthScale
    // Fade covered layers a touch so stacking reads clearly.
    alpha = 1f - 0.15f * d.coerceIn(0f, 1f)
    cameraDistance = 16f * scope.density.density
}
