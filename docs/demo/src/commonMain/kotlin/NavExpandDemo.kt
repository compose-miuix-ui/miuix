// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.BasicComponent
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.CardDefaults
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.basic.TextButton
import top.yukonga.miuix.kmp.nav.core.NavDisplay
import top.yukonga.miuix.kmp.nav.core.NavKey
import top.yukonga.miuix.kmp.nav.core.navBackStackOf
import top.yukonga.miuix.kmp.nav.transition.NavSwipeDirection
import top.yukonga.miuix.kmp.nav.transition.NavTransitions
import top.yukonga.miuix.kmp.nav.transition.navTransitionSource
import top.yukonga.miuix.kmp.theme.MiuixTheme

private data object ExpandDemoHome : NavKey

private data class ExpandDemoDetail(val id: Int) : NavKey

@Composable
fun NavExpandDemo() {
    val stack = remember { navBackStackOf(ExpandDemoHome) }
    val containerColor = MiuixTheme.colorScheme.surface
    val sourceColor = MiuixTheme.colorScheme.surfaceContainer
    NavDisplay(stack, modifier = Modifier.fillMaxSize(), transition = NavTransitions.expansion(containerColor)) {
        entry<ExpandDemoHome> {
            Scaffold { padding ->
                Column(Modifier.fillMaxSize().padding(padding).padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    BasicComponent(title = "Expand from a card", summary = "Tap the card, then swipe right or press Back.")
                    val cardRoute = ExpandDemoDetail(1)
                    Card(
                        modifier = Modifier.navTransitionSource(cardRoute, CardDefaults.CornerRadius, sourceColor),
                        onClick = {
                            if (cardRoute !in stack) stack.add(cardRoute)
                        },
                    ) {
                        BasicComponent(title = "Title", summary = "Summary")
                    }
                    val circleRoute = ExpandDemoDetail(2)
                    Box(
                        Modifier.size(64.dp).navTransitionSource(circleRoute, containerColor = sourceColor, shape = CircleShape).clip(CircleShape).background(sourceColor)
                            .clickable { if (circleRoute !in stack) stack.add(circleRoute) },
                        contentAlignment = Alignment.Center,
                    ) { Text("Circle") }
                }
            }
        }
        entry<ExpandDemoDetail>(swipeDismiss = NavSwipeDirection.LeftToRight) { route ->
            Scaffold { padding ->
                Column(Modifier.fillMaxSize().padding(padding).padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    TextButton(text = "Back", onClick = { if (stack.lastOrNull() == route) stack.removeLastOrNull() })
                    BasicComponent(title = "Navigation page", summary = "Expand from a card or a circle.")
                    repeat(3) { index ->
                        Card { BasicComponent(title = "Title ${index + 1}", summary = "Summary") }
                    }
                }
            }
        }
    }
}
