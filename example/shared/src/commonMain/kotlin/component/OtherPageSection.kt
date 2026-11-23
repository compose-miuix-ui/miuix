// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package component

import LocalNavigator
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import navigation.Route
import top.yukonga.miuix.kmp.basic.CardDefaults
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.nav.transition.NavTransitionCorners
import top.yukonga.miuix.kmp.nav.transition.navTransitionSource
import top.yukonga.miuix.kmp.preference.ArrowPreference
import top.yukonga.miuix.kmp.theme.MiuixTheme
import kotlin.random.Random

fun LazyListScope.otherPageSection() {
    item(key = "otherTitle") {
        SmallTitle(text = "Other")
    }
    groupedCardItems(
        keyPrefix = "other",
        outerTopPadding = 0.dp,
        outerBottomPadding = 0.dp,
        items = listOf(
            CardItem("navigationTest") {
                val navigator = LocalNavigator.current
                val destination = Route.Navigation(rememberSaveable { Random.nextLong().toString() })
                ArrowPreference(
                    title = "Navigation test",
                    summary = "Navigate to a Navigation Page",
                    modifier = Modifier.navTransitionSource(
                        destination,
                        containerColor = MiuixTheme.colorScheme.surfaceContainer,
                        corners = NavTransitionCorners(CardDefaults.CornerRadius, CardDefaults.CornerRadius, 0.dp, 0.dp),
                        sourceKey = "navigationTest",
                    ),
                    onClick = { navigator.push(destination) },
                )
            },
            CardItem("multiScaffoldTest") {
                val navigator = LocalNavigator.current
                ArrowPreference(
                    title = "MultiScaffold Test",
                    summary = "Navigate to a MultiScaffold Page",
                    modifier = Modifier.navTransitionSource(Route.MultiScaffold, containerColor = MiuixTheme.colorScheme.surfaceContainer, cornerRadius = 0.dp, sourceKey = "multiScaffoldTest"),
                    onClick = { navigator.push(Route.MultiScaffold) },
                )
            },
            CardItem("nestedNavTest") {
                val navigator = LocalNavigator.current
                ArrowPreference(
                    title = "Nested Navigation Test",
                    summary = "A NavDisplay nested inside an entry",
                    modifier = Modifier.navTransitionSource(Route.NestedNav, containerColor = MiuixTheme.colorScheme.surfaceContainer, cornerRadius = 0.dp, sourceKey = "nestedNavTest"),
                    onClick = { navigator.push(Route.NestedNav) },
                )
            },
            CardItem("overscrollLoadMoreTest") {
                val navigator = LocalNavigator.current
                ArrowPreference(
                    title = "Overscroll + Load More Test",
                    summary = "Fling to the bottom, then fling again",
                    modifier = Modifier.navTransitionSource(
                        Route.OverscrollLoadMore,
                        containerColor = MiuixTheme.colorScheme.surfaceContainer,
                        corners = NavTransitionCorners(0.dp, 0.dp, CardDefaults.CornerRadius, CardDefaults.CornerRadius),
                        sourceKey = "overscrollLoadMoreTest",
                    ),
                    onClick = { navigator.push(Route.OverscrollLoadMore) },
                )
            },
        ),
    )
}
