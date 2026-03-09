// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import component.arrowSection
import component.basicComponentSection
import component.bottomSheetSection
import component.buttonSection
import component.cardSection
import component.checkboxSection
import component.colorPickerSection
import component.dialogSection
import component.dropdownSection
import component.numberPickerSection
import component.progressIndicatorSection
import component.sliderSection
import component.snackbarSection
import component.spinnerSection
import component.switchSection
import component.tabRowSection
import component.textFieldSection
import top.yukonga.miuix.kmp.basic.BasicComponent
import top.yukonga.miuix.kmp.basic.InputField
import top.yukonga.miuix.kmp.basic.MiuixScrollBehavior
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.SearchBar
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.basic.SnackbarHostState
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.theme.MiuixTheme
import utils.AdaptiveTopAppBar
import utils.pageContentPadding
import utils.pageScrollModifiers

@Composable
fun MainPage(
    snackbarHostState: SnackbarHostState,
    padding: PaddingValues,
) {
    val appState = LocalAppState.current
    val isWideScreen = LocalIsWideScreen.current
    var searchValue by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }

    val notExpanded by remember { derivedStateOf { !expanded } }

    val topAppBarScrollBehavior = MiuixScrollBehavior()

    Scaffold(
        topBar = {
            AdaptiveTopAppBar(
                title = "Home",
                showTopAppBar = appState.showTopAppBar,
                isWideScreen = isWideScreen,
                scrollBehavior = topAppBarScrollBehavior,
            )
        },
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.pageScrollModifiers(appState.enableScrollEndHaptic, appState.enableOverScroll, appState.showTopAppBar, topAppBarScrollBehavior),
            contentPadding = pageContentPadding(innerPadding, padding, isWideScreen),
            overscrollEffect = null,
        ) {
            item(key = "searchbar") {
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
                                ) {
                                    expanded = false
                                    searchValue = ""
                                },
                            text = "Cancel",
                            style = TextStyle(fontSize = 17.sp, fontWeight = FontWeight.Bold),
                            color = MiuixTheme.colorScheme.primary,
                        )
                    },
                    expanded = expanded,
                    onExpandedChange = { expanded = it },
                ) {
                    Column {
                        repeat(4) { idx ->
                            val resultText = "Suggestion $idx"
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
            if (notExpanded) {
                basicComponentSection()
                checkboxSection()
                switchSection()
                arrowSection()
                dialogSection()
                bottomSheetSection()
                dropdownSection()
                spinnerSection()
                buttonSection()
                snackbarSection(snackbarHostState)
                progressIndicatorSection()
                textFieldSection()
                sliderSection()
                tabRowSection()
                numberPickerSection()
                colorPickerSection()
                cardSection()
            }
        }
    }
}
