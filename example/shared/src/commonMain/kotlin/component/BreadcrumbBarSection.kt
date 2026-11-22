// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.BreadcrumbBar
import top.yukonga.miuix.kmp.basic.BreadcrumbItem
import top.yukonga.miuix.kmp.basic.SmallTitle

fun LazyListScope.breadcrumbBarSection() {
    item(key = "breadcrumbBar") {
        SmallTitle(text = "BreadcrumbBar")
        val items = remember {
            listOf(
                BreadcrumbItem(
                    path = "/storage/emulated/0",
                    text = "Internal storage",
                ),
                BreadcrumbItem(path = "DataBackup"),
                BreadcrumbItem(path = "apps"),
                BreadcrumbItem(path = "com.tencent.mobileqq"),
                BreadcrumbItem(path = "user_0"),
            )
        }
        var highlightIndex by remember { mutableIntStateOf(items.size - 1) }

        BreadcrumbBar(
            items = items,
            onItemClick = { index -> highlightIndex = index },
            highlightIndex = highlightIndex,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
                .padding(bottom = 12.dp),
        )
        BreadcrumbBar(
            items = items,
            onItemClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
                .padding(bottom = 12.dp),
            enabled = false,
        )
    }
}
