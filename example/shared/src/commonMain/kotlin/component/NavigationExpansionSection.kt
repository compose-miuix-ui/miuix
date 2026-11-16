// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package component

import LocalNavigator
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import navigation.Route
import top.yukonga.miuix.kmp.basic.BasicComponent
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.CardDefaults
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.nav.transition.NavTransitionCorners
import top.yukonga.miuix.kmp.nav.transition.navTransitionSource
import top.yukonga.miuix.kmp.preference.ArrowPreference
import top.yukonga.miuix.kmp.squircle.absoluteSquircleSurface
import top.yukonga.miuix.kmp.theme.MiuixTheme
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

private val StarShape = GenericShape { size, _ ->
    repeat(10) { index ->
        val angle = -PI / 2 + index * PI / 5
        val radius = if (index % 2 == 0) 0.5f else 0.25f
        val x = size.width * (0.5f + radius * cos(angle).toFloat())
        val y = size.height * (0.5f + radius * sin(angle).toFloat())
        if (index == 0) moveTo(x, y) else lineTo(x, y)
    }
    close()
}

fun LazyListScope.navigationExpansionSection(origin: String) {
    item(key = "nav_expansion") {
        SmallTitle(text = "Component expansion")
        Card(modifier = Modifier.padding(horizontal = 12.dp).padding(bottom = 12.dp)) {
            ExpansionPreference(
                destination = Route.ExpansionDetail(origin, 1),
                corners = NavTransitionCorners(CardDefaults.CornerRadius, CardDefaults.CornerRadius, 0.dp, 0.dp),
            )
            ExpansionPreference(
                destination = Route.ExpansionDetail(origin, 2),
                corners = NavTransitionCorners(0.dp, 0.dp, CardDefaults.CornerRadius, CardDefaults.CornerRadius),
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp).padding(bottom = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            ExpansionCard(Route.ExpansionDetail(origin, 3), Modifier.weight(1f))
            ExpansionShapeCard(Route.ExpansionDetail(origin, 4), CircleShape, Modifier.weight(1f))
            ExpansionShapeCard(Route.ExpansionDetail(origin, 5), StarShape, Modifier.weight(1f))
        }
    }
}

@Composable
private fun ExpansionPreference(
    destination: Route.ExpansionDetail,
    corners: NavTransitionCorners,
) {
    val navigator = LocalNavigator.current
    val sourceColor = MiuixTheme.colorScheme.surfaceContainer
    ArrowPreference(
        title = "Push another Navigation page",
        summary = "Component expansion",
        modifier = Modifier.navTransitionSource(destination, containerColor = sourceColor, corners = corners)
            .absoluteSquircleSurface(sourceColor, corners.topLeft, corners.topRight, corners.bottomRight, corners.bottomLeft),
        onClick = { navigator.push(destination) },
    )
}

@Composable
private fun ExpansionCard(destination: Route.ExpansionDetail, modifier: Modifier = Modifier) {
    val navigator = LocalNavigator.current
    val sourceColor = MiuixTheme.colorScheme.surfaceContainer
    Card(
        modifier = modifier.navTransitionSource(destination, CardDefaults.CornerRadius, sourceColor),
        onClick = { navigator.push(destination) },
    ) {
        BasicComponent(title = "Title", summary = "Summary")
    }
}

@Composable
private fun ExpansionShapeCard(destination: Route.ExpansionDetail, shape: Shape, modifier: Modifier = Modifier) {
    val navigator = LocalNavigator.current
    val sourceColor = MiuixTheme.colorScheme.surfaceContainer
    Card(
        modifier = modifier.aspectRatio(1f)
            .navTransitionSource(destination, cornerRadius = 0.dp, containerColor = sourceColor, shape = shape)
            .clip(shape),
        cornerRadius = 0.dp,
        onClick = { navigator.push(destination) },
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "Title")
        }
    }
}
