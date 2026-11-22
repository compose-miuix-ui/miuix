// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import top.yukonga.miuix.kmp.basic.BasicComponent
import top.yukonga.miuix.kmp.basic.InputField
import top.yukonga.miuix.kmp.basic.SearchBar
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.theme.MiuixTheme

fun LazyListScope.searchBarSection() {
    item(key = "searchbar") {
        var searchValue by remember { mutableStateOf("") }
        var expanded by remember { mutableStateOf(false) }
        val suggestions = remember { List(4) { "Suggestion $it" } }
        SmallTitle(text = "SearchBar")
        SearchBar(
            modifier = Modifier.padding(bottom = 12.dp),
            inputField = {
                InputField(
                    query = searchValue,
                    onQueryChange = { searchValue = it },
                    onSearch = { expanded = false },
                    expanded = expanded,
                    onExpandedChange = { expanded = it },
                    label = "Search",
                )
            },
            outsideEndAction = {
                Text(
                    modifier = Modifier
                        .padding(end = 12.dp)
                        .clickable(
                            interactionSource = null,
                            indication = null,
                            onClick = {
                                expanded = false
                                searchValue = ""
                            },
                        ),
                    text = "Cancel",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = MiuixTheme.colorScheme.primary,
                )
            },
            expanded = expanded,
            onExpandedChange = { expanded = it },
        ) {
            Column {
                suggestions.forEach { resultText ->
                    BasicComponent(
                        title = resultText,
                        onClick = {
                            searchValue = resultText
                            expanded = false
                        },
                    )
                }
            }
        }
    }
}
