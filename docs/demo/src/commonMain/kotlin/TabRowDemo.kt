// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.TabRow
import top.yukonga.miuix.kmp.basic.TabRowWithContour

@Composable
fun TabRowDemo() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(demoBackground()),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            Modifier
                .padding(16.dp)
                .widthIn(max = 600.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            val tabs1 = listOf("Recommended", "Following", "Popular", "Featured")
            var selectedTabIndex1 by remember { mutableIntStateOf(0) }
            val tabListState1 = rememberLazyListState()

            TabRow(
                tabs = tabs1,
                selectedTabIndex = selectedTabIndex1,
                onTabSelected = { selectedTabIndex1 = it },
                listState = tabListState1,
            )

            val tabs2 = listOf("All", "Photos", "Videos", "Documents")
            var selectedTabIndex2 by remember { mutableIntStateOf(0) }
            val contourTabListState2 = rememberLazyListState()

            TabRowWithContour(
                tabs = tabs2,
                selectedTabIndex = selectedTabIndex2,
                onTabSelected = { selectedTabIndex2 = it },
                listState = contourTabListState2,
            )
        }
    }
}
