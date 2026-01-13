// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import component.TextComponent
import component.otherComponent
import top.yukonga.miuix.kmp.basic.BasicComponent
import top.yukonga.miuix.kmp.basic.InputField
import top.yukonga.miuix.kmp.basic.MiuixScrollBehavior
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.SearchBar
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.basic.SmallTopAppBar
import top.yukonga.miuix.kmp.basic.SnackbarHostState
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.basic.TopAppBar
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.utils.overScrollVertical
import top.yukonga.miuix.kmp.utils.scrollEndHaptic

@Composable
fun MainPage(
    snackbarHostState: SnackbarHostState,
    padding: PaddingValues,
    enableScrollEndHaptic: Boolean,
    enableOverScroll: Boolean,
    isWideScreen: Boolean,
    showTopAppBar: Boolean,
) {
    var searchValue by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    val showSuperDialog = remember { mutableStateOf(false) }
    val showWindowDialog = remember { mutableStateOf(false) }
    val showSuperBottomSheet = remember { mutableStateOf(false) }
    val showWindowBottomSheet = remember { mutableStateOf(false) }
    val bottomSheetDropdownSelectedOption = remember { mutableIntStateOf(0) }
    val bottomSheetSuperSwitchState = remember { mutableStateOf(true) }
    val checkbox = remember { mutableStateOf(false) }
    val checkboxTrue = remember { mutableStateOf(true) }
    val switch = remember { mutableStateOf(false) }
    val switchTrue = remember { mutableStateOf(true) }
    val superDropdownOptionSelected = remember { mutableIntStateOf(0) }
    val windowDropdownOptionSelected = remember { mutableIntStateOf(1) }
    val superSpinnerOptionSelected = remember { mutableIntStateOf(0) }
    val windowSpinnerOptionSelected = remember { mutableIntStateOf(1) }
    val superSpinnerOptionSelectedDialog = remember { mutableIntStateOf(2) }
    val windowSpinnerOptionSelectedDialog = remember { mutableIntStateOf(3) }
    val superCheckbox = remember { mutableStateOf("State: false") }
    val superCheckboxState = remember { mutableStateOf(false) }
    val superRightCheckbox = remember { mutableStateOf("false") }
    val superRightCheckboxState = remember { mutableStateOf(false) }
    val superSwitch = remember { mutableStateOf("false") }
    val superSwitchState = remember { mutableStateOf(false) }
    val superSwitchAnimState = remember { mutableStateOf(false) }

    val notExpanded by remember { derivedStateOf { !expanded } }

    val textComponent = @Composable {
        TextComponent(
            showSuperDialog,
            showWindowDialog,
            showSuperBottomSheet,
            showWindowBottomSheet,
            bottomSheetDropdownSelectedOption,
            bottomSheetSuperSwitchState,
            checkbox,
            checkboxTrue,
            switch,
            switchTrue,
            superDropdownOptionSelected,
            windowDropdownOptionSelected,
            superSpinnerOptionSelected,
            windowSpinnerOptionSelected,
            superSpinnerOptionSelectedDialog,
            windowSpinnerOptionSelectedDialog,
            superCheckbox,
            superCheckboxState,
            superRightCheckbox,
            superRightCheckboxState,
            superSwitch,
            superSwitchState,
            superSwitchAnimState,
        )
    }

    val topAppBarScrollBehavior = MiuixScrollBehavior()

    Scaffold(
        topBar = {
            if (showTopAppBar) {
                if (isWideScreen) {
                    SmallTopAppBar(
                        title = "Home",
                        scrollBehavior = topAppBarScrollBehavior,
                        defaultWindowInsetsPadding = false,
                    )
                } else {
                    TopAppBar(
                        title = "Home",
                        scrollBehavior = topAppBarScrollBehavior,
                    )
                }
            }
        },
        popupHost = {},
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .then(
                    if (enableScrollEndHaptic) Modifier.scrollEndHaptic() else Modifier,
                )
                .overScrollVertical(
                    isEnabled = { enableOverScroll },
                )
                .then(
                    if (showTopAppBar) Modifier.nestedScroll(topAppBarScrollBehavior.nestedScrollConnection) else Modifier,
                )
                .fillMaxHeight(),
            contentPadding = PaddingValues(
                top = innerPadding.calculateTopPadding(),
                bottom = if (isWideScreen) {
                    WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding() +
                        padding.calculateBottomPadding() + 12.dp
                } else {
                    padding.calculateBottomPadding() + 12.dp
                },
            ),
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
                item(key = "textComponent") {
                    textComponent()
                }
                otherComponent(snackbarHostState)
            }
        }
    }
}
