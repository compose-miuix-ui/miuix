import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import component.OtherComponent
import component.TextComponent
import top.yukonga.miuix.kmp.basic.BasicComponent
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.basic.InputField
import top.yukonga.miuix.kmp.basic.LazyColumn
import top.yukonga.miuix.kmp.basic.ScrollBehavior
import top.yukonga.miuix.kmp.basic.SearchBar
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.icons.useful.Search
import top.yukonga.miuix.kmp.theme.MiuixTheme

@Composable
fun MainPage(
    topAppBarScrollBehavior: ScrollBehavior,
    padding: PaddingValues
) {
    var miuixSearchValue by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }

    val showDialog = remember { mutableStateOf(false) }
    val dialogTextFieldValue = remember { mutableStateOf("") }
    val showDialog2 = remember { mutableStateOf(false) }
    val dialog2dropdownSelectedOption = remember { mutableStateOf(0) }
    val dialog2SuperSwitchState = remember { mutableStateOf(false) }
    val checkbox = remember { mutableStateOf(false) }
    val checkboxTrue = remember { mutableStateOf(true) }
    val switch = remember { mutableStateOf(false) }
    val switchTrue = remember { mutableStateOf(true) }
    val dropdownOptionSelected = remember { mutableStateOf(0) }
    val dropdownOptionSelectedRight = remember { mutableStateOf(1) }
    val spinnerOptionSelected = remember { mutableStateOf(0) }
    val spinnerOptionSelectedRight = remember { mutableStateOf(1) }
    val spinnerOptionSelectedDialog = remember { mutableStateOf(2) }
    val miuixSuperCheckbox = remember { mutableStateOf("State: false") }
    val miuixSuperCheckboxState = remember { mutableStateOf(false) }
    val miuixSuperRightCheckbox = remember { mutableStateOf("false") }
    val miuixSuperRightCheckboxState = remember { mutableStateOf(false) }
    val miuixSuperSwitch = remember { mutableStateOf("false") }
    val miuixSuperSwitchState = remember { mutableStateOf(false) }
    val miuixSuperSwitchAnimState = remember { mutableStateOf(false) }

    val textComponent = @Composable {
        TextComponent(
            showDialog, dialogTextFieldValue, showDialog2, dialog2dropdownSelectedOption, dialog2SuperSwitchState,
            checkbox, checkboxTrue, switch, switchTrue, dropdownOptionSelected, dropdownOptionSelectedRight, spinnerOptionSelected,
            spinnerOptionSelectedRight, spinnerOptionSelectedDialog, miuixSuperCheckbox, miuixSuperCheckboxState,
            miuixSuperRightCheckbox, miuixSuperRightCheckboxState, miuixSuperSwitch, miuixSuperSwitchState, miuixSuperSwitchAnimState
        )
    }
    val otherComponent = @Composable { OtherComponent(padding) }

    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {
        if (maxWidth < 840.dp) {
            LazyColumn(
                modifier = Modifier.nestedScroll(topAppBarScrollBehavior.nestedScrollConnection),
                contentPadding = PaddingValues(top = padding.calculateTopPadding())
            ) {
                item {
                    SmallTitle(text = "SearchBar")
                    SearchBar(
                        modifier = Modifier
                            .padding(start = 12.dp, end = 12.dp, bottom = 6.dp),
                        inputField = {
                            InputField(
                                query = miuixSearchValue,
                                onQueryChange = { miuixSearchValue = it },
                                onSearch = { expanded = false },
                                expanded = expanded,
                                onExpandedChange = { expanded = it },
                                label = "Search",
                                leadingIcon = {
                                    Icon(
                                        modifier = Modifier
                                            .padding(start = 12.dp, end = 8.dp)
                                            .size(20.dp)
                                            .alpha(0.4f),
                                        imageVector = MiuixIcons.Useful.Search,
                                        tint = MiuixTheme.colorScheme.onSurfaceContainer,
                                        contentDescription = "Search"
                                    )
                                },
                            )
                        },
                        outsideRightAction = {
                            Text(
                                modifier = Modifier
                                    .padding(start = 12.dp)
                                    .clickable(
                                        interactionSource = null,
                                        indication = null
                                    ) {
                                        expanded = false
                                        miuixSearchValue = ""
                                    },
                                text = "Cancel",
                                color = MiuixTheme.colorScheme.primary
                            )
                        },
                        expanded = expanded,
                        onExpandedChange = { expanded = it }
                    ) {
                        Column(
                            Modifier.fillMaxSize()
                        ) {
                            repeat(4) { idx ->
                                val resultText = "Suggestion $idx"
                                BasicComponent(
                                    title = resultText,
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    onClick = {
                                        miuixSearchValue = resultText
                                        expanded = false
                                    }
                                )
                            }
                        }
                    }
                }
                if (!expanded) {
                    item(
                        key = "text"
                    ) {
                        textComponent()
                    }
                    item(
                        key = "other"
                    ) {
                        otherComponent()
                    }
                }
            }
        } else {
            Row(
                modifier = Modifier
                    .windowInsetsPadding(WindowInsets.displayCutout.only(WindowInsetsSides.Horizontal))
                    .windowInsetsPadding(WindowInsets.navigationBars.only(WindowInsetsSides.Horizontal))
            ) {
                LazyColumn(
                    modifier = Modifier
                        .nestedScroll(topAppBarScrollBehavior.nestedScrollConnection)
                        .padding(start = 12.dp)
                        .weight(1f),
                    contentPadding = PaddingValues(top = padding.calculateTopPadding())
                ) {
                    item {
                        SmallTitle(text = "SearchBar")
                        SearchBar(
                            modifier = Modifier
                                .padding(start = 12.dp, end = 12.dp, bottom = 6.dp),
                            inputField = {
                                InputField(
                                    query = miuixSearchValue,
                                    onQueryChange = { miuixSearchValue = it },
                                    onSearch = { expanded = false },
                                    expanded = expanded,
                                    onExpandedChange = { expanded = it },
                                    label = "Search",
                                    leadingIcon = {
                                        Icon(
                                            modifier = Modifier.padding(horizontal = 16.dp),
                                            imageVector = MiuixIcons.Useful.Search,
                                            tint = MiuixTheme.colorScheme.onSurfaceContainer,
                                            contentDescription = "Search"
                                        )
                                    },
                                )
                            },
                            outsideRightAction = {
                                Text(
                                    modifier = Modifier
                                        .padding(start = 12.dp)
                                        .clickable(
                                            interactionSource = null,
                                            indication = null
                                        ) {
                                            expanded = false
                                            miuixSearchValue = ""
                                        },
                                    text = "Cancel",
                                    color = MiuixTheme.colorScheme.primary
                                )
                            },
                            expanded = expanded,
                            onExpandedChange = { expanded = it }
                        ) {
                            Column(
                                Modifier.fillMaxSize()
                            ) {
                                repeat(4) { idx ->
                                    val resultText = "Suggestion $idx"
                                    BasicComponent(
                                        title = resultText,
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                        onClick = {
                                            miuixSearchValue = resultText
                                            expanded = false
                                        }
                                    )
                                }
                            }
                        }
                    }
                    if (!expanded) {
                        item(
                            key = "other"
                        ) {
                            otherComponent()
                            Spacer(modifier = Modifier.height(6.dp))
                        }
                    }
                }
                LazyColumn(
                    modifier = Modifier
                        .nestedScroll(topAppBarScrollBehavior.nestedScrollConnection)
                        .padding(end = 12.dp)
                        .weight(1f),
                    contentPadding = PaddingValues(top = padding.calculateTopPadding())
                ) {
                    item(
                        key = "text"
                    ) {
                        textComponent()
                        Spacer(modifier = Modifier.height(12.dp + padding.calculateBottomPadding()))
                    }
                }
            }
        }
    }
}