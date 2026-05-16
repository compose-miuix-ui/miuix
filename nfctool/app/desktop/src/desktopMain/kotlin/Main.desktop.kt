import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.nfctool.app.ui.NfcToolApp

fun main() = application {
    val windowState = rememberWindowState(
        width = 480.dp,
        height = 900.dp,
    )

    Window(
        onCloseRequest = ::exitApplication,
        title = "NFC Tool",
        state = windowState,
    ) {
        NfcToolApp()
    }
}