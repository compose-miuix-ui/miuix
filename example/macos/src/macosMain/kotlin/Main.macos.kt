// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import compose_miuix_ui.example.macos.generated.resources.Res
import kotlinx.cinterop.ExperimentalForeignApi
import platform.AppKit.NSApplication
import platform.AppKit.NSApplicationActivationPolicy
import platform.AppKit.NSApplicationDelegateProtocol
import platform.CoreGraphics.CGSizeMake
import platform.darwin.NSObject
import utils.LocalAboutLibrariesJsonLoader

@OptIn(ExperimentalForeignApi::class)
fun main() {
    val nsApplication = NSApplication.sharedApplication()
    nsApplication.setActivationPolicy(NSApplicationActivationPolicy.NSApplicationActivationPolicyRegular)
    nsApplication.delegate =
        object : NSObject(), NSApplicationDelegateProtocol {
            override fun applicationShouldTerminateAfterLastWindowClosed(sender: NSApplication): Boolean = true
        }
    Window(
        title = "Miuix",
        size = DpSize(420.dp, 840.dp),
    ) {
        window.minSize = CGSizeMake(300.0, 600.0)
        val loadAboutLibrariesJson: suspend () -> String = remember {
            { Res.readBytes("files/aboutlibraries.json").decodeToString() }
        }
        CompositionLocalProvider(LocalAboutLibrariesJsonLoader provides loadAboutLibrariesJson) {
            App()
        }
    }
    nsApplication.run()
}
