import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.nfctool.app.ui.NfcToolApp

fun main() {
    application {
        Window(onCloseRequest = ::exitApplication, title = "NFC Tool") {
            NfcToolApp()
        }
    }
}