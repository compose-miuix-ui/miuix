// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package navigation

import kotlinx.serialization.Serializable

enum class ComponentDemoGroup(val title: String, val requiresRuntimeShader: Boolean = false) {
    Ui("miuix-ui"),
    Preference("miuix-preference"),
    Blur("miuix-blur", requiresRuntimeShader = true),
}

@Serializable
enum class ComponentDemo(val title: String, val group: ComponentDemoGroup = ComponentDemoGroup.Ui) {
    Dialog("Dialog"),
    BottomSheet("BottomSheet"),
    Snackbar("Snackbar"),
    Slider("Slider"),
    ColorPicker("ColorPicker"),
    Preferences("Preferences", ComponentDemoGroup.Preference),
    TextureBlur("Texture Blur", ComponentDemoGroup.Blur),
    ForegroundBlur("Foreground Blur", ComponentDemoGroup.Blur),
    ProgressiveBlur("Progressive Blur", ComponentDemoGroup.Blur),
}
