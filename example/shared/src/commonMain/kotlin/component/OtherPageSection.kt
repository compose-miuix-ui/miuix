// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package component

import LocalNavigator
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import navigation.Route
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.CardDefaults
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.nav.transition.NavTransitionCorners
import top.yukonga.miuix.kmp.nav.transition.navTransitionSource
import top.yukonga.miuix.kmp.preference.ArrowPreference
import top.yukonga.miuix.kmp.theme.MiuixTheme
import kotlin.random.Random

fun LazyListScope.otherPageSection() {
    item(key = "other") {
        val navigator = LocalNavigator.current
        val navigationRoute = remember { Route.Navigation(Random.nextLong().toString()) }
        val sourceColor = MiuixTheme.colorScheme.surfaceContainer
        SmallTitle(text = "Other")
        Card(
            modifier = Modifier
                .padding(horizontal = 12.dp),
        ) {
            ArrowPreference(
                title = "Navigation test",
                summary = "Navigate to a Navigation Page",
                modifier = Modifier.navTransitionSource(navigationRoute, containerColor = sourceColor, corners = NavTransitionCorners(CardDefaults.CornerRadius, CardDefaults.CornerRadius, 0.dp, 0.dp)),
                onClick = { navigator.push(navigationRoute) },
            )
            ArrowPreference(
                title = "MultiScaffold Test",
                summary = "Navigate to a MultiScaffold Page",
                modifier = Modifier.navTransitionSource(Route.MultiScaffold, containerColor = sourceColor, cornerRadius = 0.dp),
                onClick = { navigator.push(Route.MultiScaffold) },
            )
            ArrowPreference(
                title = "Nested Navigation Test",
                summary = "A NavDisplay nested inside an entry",
                modifier = Modifier.navTransitionSource(Route.NestedNav, containerColor = sourceColor, cornerRadius = 0.dp),
                onClick = { navigator.push(Route.NestedNav) },
            )
            ArrowPreference(
                title = "Overscroll + Load More Test",
                summary = "Fling to the bottom, then fling again",
                modifier = Modifier.navTransitionSource(Route.OverscrollLoadMore, containerColor = sourceColor, corners = NavTransitionCorners(0.dp, 0.dp, CardDefaults.CornerRadius, CardDefaults.CornerRadius)),
                onClick = { navigator.push(Route.OverscrollLoadMore) },
            )
        }
    }
}
