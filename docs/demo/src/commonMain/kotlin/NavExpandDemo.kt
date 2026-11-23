// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
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
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin

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
                    BasicComponent(title = "Component expansion", summary = "Tap any shape, then swipe right or press Back.")
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.widthIn(max = 360.dp).fillMaxWidth(),
                    ) {
                        val cardRoute = ExpandDemoDetail(1)
                        Card(
                            modifier = Modifier.weight(1f).aspectRatio(1f)
                                .navTransitionSource(cardRoute, CardDefaults.CornerRadius, sourceColor),
                            onClick = { if (cardRoute !in stack) stack.add(cardRoute) },
                        ) {
                            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                Text("Title")
                            }
                        }
                        val circleRoute = ExpandDemoDetail(2)
                        Card(
                            modifier = Modifier.weight(1f).aspectRatio(1f)
                                .navTransitionSource(circleRoute, cornerRadius = 0.dp, containerColor = sourceColor, shape = CircleShape)
                                .clip(CircleShape),
                            cornerRadius = 0.dp,
                            onClick = { if (circleRoute !in stack) stack.add(circleRoute) },
                        ) {
                            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                Text("Circle")
                            }
                        }
                        val starRoute = ExpandDemoDetail(3)
                        Card(
                            modifier = Modifier.weight(1f).aspectRatio(1f)
                                .navTransitionSource(starRoute, cornerRadius = 0.dp, containerColor = sourceColor, shape = DemoStarShape)
                                .clip(DemoStarShape),
                            cornerRadius = 0.dp,
                            onClick = { if (starRoute !in stack) stack.add(starRoute) },
                        ) {
                            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                Text("Star")
                            }
                        }
                    }
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

private object DemoStarShape : Shape {
    override fun createOutline(size: Size, layoutDirection: LayoutDirection, density: Density): Outline {
        val cx = size.width / 2f
        val cy = size.height / 2f
        val outer = min(cx, cy)
        val inner = outer * 0.48f
        val path = Path().apply {
            for (i in 0 until 10) {
                val radius = if (i % 2 == 0) outer else inner
                val angle = -PI / 2.0 + i * PI / 5.0
                val x = cx + (radius * cos(angle)).toFloat()
                val y = cy + (radius * sin(angle)).toFloat()
                if (i == 0) moveTo(x, y) else lineTo(x, y)
            }
            close()
        }
        return Outline.Generic(path)
    }
}
