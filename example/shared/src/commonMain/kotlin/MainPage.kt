// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

@file:OptIn(ExperimentalScrollBarApi::class)

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import component.arrowSection
import component.basicComponentSection
import component.blurSection
import component.bottomSheetSection
import component.buttonSection
import component.cardSection
import component.checkboxSection
import component.colorPickerSection
import component.dialogSection
import component.dropdownSection
import component.numberPickerSection
import component.progressIndicatorSection
import component.radioButtonSection
import component.shapeComparisonSection
import component.sliderSection
import component.snackbarSection
import component.spinnerSection
import component.switchSection
import component.tabRowSection
import component.textFieldSection
import kotlinx.coroutines.launch
import top.yukonga.miuix.kmp.basic.BasicComponent
import top.yukonga.miuix.kmp.basic.DropdownEntry
import top.yukonga.miuix.kmp.basic.DropdownItem
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.basic.InputField
import top.yukonga.miuix.kmp.basic.MiuixScrollBehavior
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.SearchBar
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.basic.SnackbarHostState
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.basic.VerticalScrollBar
import top.yukonga.miuix.kmp.basic.rememberScrollBarAdapter
import top.yukonga.miuix.kmp.blur.layerBackdrop
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.extended.More
import top.yukonga.miuix.kmp.icon.extended.Tune
import top.yukonga.miuix.kmp.interfaces.ExperimentalScrollBarApi
import top.yukonga.miuix.kmp.menu.OverlayIconDropdownMenu
import top.yukonga.miuix.kmp.theme.MiuixTheme
import utils.AdaptiveTopAppBar
import utils.BlurredBar
import utils.pageContentPadding
import utils.pageScrollModifiers
import utils.rememberBlurBackdrop

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
    val onCancelSearch = remember {
        {
            expanded = false
            searchValue = ""
        }
    }

    val backdrop = rememberBlurBackdrop()
    val blurActive = backdrop != null
    val barColor = if (blurActive) Color.Transparent else MiuixTheme.colorScheme.surface

    val topAppBarScrollBehavior = MiuixScrollBehavior()
    val lazyListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    fun snackbar(msg: String) {
        coroutineScope.launch {
            snackbarHostState.showSnackbar(msg)
        }
    }

    val menuItems = remember {
        listOf(
            DropdownEntry(
                items = listOf("Item A-1", "Item A-2")
                    .map {
                        DropdownItem(
                            text = it,
                            onClick = { snackbar("$it clicked") },
                        )
                    },
            ),
            DropdownEntry(
                items = listOf("Item B-1", "Item B-2", "Item B-3")
                    .map {
                        DropdownItem(
                            text = it,
                            onClick = { snackbar("$it clicked") },
                        )
                    },
            ),
            DropdownEntry(
                items = listOf("Item C-1", "Item C-2", "Item C-3", "Item C-4")
                    .mapIndexed { index, string ->
                        DropdownItem(
                            text = string,
                            onClick = { snackbar("$string clicked") },
                            enabled = index % 2 == 0,
                        )
                    },
            ),
        )
    }
    var selectedIndex1 by remember { mutableIntStateOf(0) }
    var selectedIndex2 by remember { mutableIntStateOf(1) }
    var selectedIndex3 by remember { mutableIntStateOf(2) }
    val optionItems = remember(
        selectedIndex1,
        selectedIndex2,
        selectedIndex3,
    ) {
        listOf(
            DropdownEntry(
                items = listOf("Selection A-1", "Selection A-2")
                    .map { DropdownItem(text = it) },
                selectedIndex = selectedIndex1,
                onSelectedIndexChange = { selectedIndex1 = it },
            ),
            DropdownEntry(
                items = listOf("Selection B-1", "Selection B-2", "Selection B-3")
                    .map { DropdownItem(text = it) },
                selectedIndex = selectedIndex2,
                onSelectedIndexChange = { selectedIndex2 = it },
            ),
            DropdownEntry(
                items = listOf("Selection C-1", "Selection C-2", "Selection C-3", "Selection C-4")
                    .map { DropdownItem(text = it) },
                selectedIndex = selectedIndex3,
                onSelectedIndexChange = { selectedIndex3 = it },
            ),
        )
    }

    Scaffold(
        topBar = {
            BlurredBar(backdrop, blurActive) {
                AdaptiveTopAppBar(
                    title = "Home",
                    showTopAppBar = appState.showTopAppBar,
                    isWideScreen = isWideScreen,
                    scrollBehavior = topAppBarScrollBehavior,
                    color = barColor,
                    actions = {
                        OverlayIconDropdownMenu(
                            entries = optionItems,
                            collapseOnSelection = false,
                        ) {
                            Icon(
                                imageVector = MiuixIcons.Tune,
                                contentDescription = "Tune",
                            )
                        }
                        OverlayIconDropdownMenu(
                            entries = menuItems,
                        ) {
                            Icon(
                                imageVector = MiuixIcons.More,
                                contentDescription = "More",
                            )
                        }
                    },
                )
            }
        },
    ) { innerPadding ->
        val contentPadding = pageContentPadding(innerPadding, padding, isWideScreen)
        Box(modifier = if (backdrop != null) Modifier.layerBackdrop(backdrop) else Modifier) {
            LazyColumn(
                state = lazyListState,
                modifier = Modifier.pageScrollModifiers(
                    appState.enableScrollEndHaptic,
                    appState.showTopAppBar,
                    topAppBarScrollBehavior,
                ),
                contentPadding = contentPadding,
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
                                        onClick = onCancelSearch,
                                    ),
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
                    radioButtonSection()
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
                    shapeComparisonSection()
                    blurSection()
                    item { Spacer(modifier = Modifier.height(12.dp)) }
                }
            }
            VerticalScrollBar(
                adapter = rememberScrollBarAdapter(lazyListState),
                modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight(),
                trackPadding = contentPadding,
            )
        }
    }
}
