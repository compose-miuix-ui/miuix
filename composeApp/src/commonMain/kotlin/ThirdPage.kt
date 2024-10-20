import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.LazyColumn
import top.yukonga.miuix.kmp.basic.ScrollBehavior
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.extra.SuperDropdown
import top.yukonga.miuix.kmp.extra.SuperSwitch
import top.yukonga.miuix.kmp.utils.getWindowSize
import utils.VersionInfo

@Composable
fun ThirdPage(
    topAppBarScrollBehavior: ScrollBehavior,
    padding: PaddingValues,
    showFPSMonitor: Boolean,
    onShowFPSMonitorChange: (Boolean) -> Unit,
    showTopAppBar: Boolean,
    onShowTopAppBarChange: (Boolean) -> Unit,
    showBottomBar: Boolean,
    onShowBottomBarChange: (Boolean) -> Unit,
    showFloatingActionButton: Boolean,
    onShowFloatingActionButtonChange: (Boolean) -> Unit,
    enablePageUserScroll: Boolean,
    onEnablePageUserScrollChange: (Boolean) -> Unit,
    colorMode: MutableState<Int>
) {
    LazyColumn(
        modifier = Modifier.height(getWindowSize().height.dp),
        contentPadding = PaddingValues(top = padding.calculateTopPadding()),
        topAppBarScrollBehavior = topAppBarScrollBehavior
    ) {
        item {
            Card(
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .padding(top = 12.dp, bottom = 6.dp)
            ) {
                SuperSwitch(
                    title = "Show FPS Monitor",
                    checked = showFPSMonitor,
                    onCheckedChange = onShowFPSMonitorChange
                )
                SuperSwitch(
                    title = "Show Top App Bar",
                    checked = showTopAppBar,
                    onCheckedChange = onShowTopAppBarChange
                )
                SuperSwitch(
                    title = "Show Bottom Bar",
                    checked = showBottomBar,
                    onCheckedChange = onShowBottomBarChange
                )
                SuperSwitch(
                    title = "Show Floating Action Button",
                    checked = showFloatingActionButton,
                    onCheckedChange = onShowFloatingActionButtonChange
                )
                SuperSwitch(
                    title = "Enable Page User Scroll",
                    checked = enablePageUserScroll,
                    onCheckedChange = onEnablePageUserScrollChange
                )
                SuperDropdown(
                    title = "Color Mode",
                    items = listOf("System", "Light", "Dark"),
                    selectedIndex = colorMode.value,
                    onSelectedIndexChange = { colorMode.value = it }
                )
            }
            SmallTitle(
                modifier = Modifier.padding(bottom = 6.dp + padding.calculateBottomPadding()),
                text = "Current Version: " + VersionInfo.VERSION_NAME + " (" + VersionInfo.VERSION_CODE + ")"
            )
        }
    }
}