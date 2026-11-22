// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package component

import LocalNavigator
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.unit.dp
import navigation.Route
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.preference.ArrowPreference
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
                ArrowPreference(
                    title = "Navigation test",
                    summary = "Navigate to a Navigation Page",
                    onClick = { navigator.push(Route.Navigation(Random.nextLong().toString())) },
                )
            },
            CardItem("multiScaffoldTest") {
                val navigator = LocalNavigator.current
                ArrowPreference(
                    title = "MultiScaffold Test",
                    summary = "Navigate to a MultiScaffold Page",
                    onClick = { navigator.push(Route.MultiScaffold) },
                )
            },
            CardItem("nestedNavTest") {
                val navigator = LocalNavigator.current
                ArrowPreference(
                    title = "Nested Navigation Test",
                    summary = "A NavDisplay nested inside an entry",
                    onClick = { navigator.push(Route.NestedNav) },
                )
            },
            CardItem("overscrollLoadMoreTest") {
                val navigator = LocalNavigator.current
                ArrowPreference(
                    title = "Overscroll + Load More Test",
                    summary = "Fling to the bottom, then fling again",
                    onClick = { navigator.push(Route.OverscrollLoadMore) },
                )
            },
        ),
    )
}
