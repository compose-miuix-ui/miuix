package component.blend

import androidx.compose.ui.graphics.Color
import top.yukonga.miuix.kmp.blur.BlendColorEntry
import top.yukonga.miuix.kmp.blur.BlendMode


object BlendTokenConfig {

    /**
     * 定义深色主题下的各种强度的混合颜色配置。
     */
    object DarkColors {
        val DEFAULT = listOf(
            BlendColorEntry(Color(0xE6A1A1A1), BlendMode.OVERLAY),
            BlendColorEntry(Color(0x4DE6E6E6), BlendMode.HARD_LIGHT),
        )

        val EXTRA_HEAVY = listOf(
            BlendColorEntry(Color(0xE6A1A1A1), BlendMode.COLOR_BURN),
            BlendColorEntry(Color(0x4DE6E6E6), BlendMode.SRC_OVER),
            BlendColorEntry(Color(0x11B0B0B0), BlendMode.SRC_OVER), // 假设 0xB0B0B0 是对应的颜色
        )

        val HEAVY = listOf(
            BlendColorEntry(Color(0x80121212), BlendMode.COLOR_BURN), // 假设 0x121212 是对应的颜色
            BlendColorEntry(Color(0x40555555), BlendMode.SRC_OVER), // 假设 0x555555 是对应的颜色
        )

        val LIGHT = listOf(
            BlendColorEntry(Color(0x61616161), BlendMode.COLOR_BURN), // 假设 0x61616161 是对应的颜色
            BlendColorEntry(Color(0x4D4D4D4D), BlendMode.SRC_OVER), // 假设 0x4D4D4D4D 是对应的颜色
        )

        val EXTRA_LIGHT = listOf(
            BlendColorEntry(Color(0x4D4D4D4D), BlendMode.COLOR_BURN), // 假设 0x4D4D4D4D 是对应的颜色
            BlendColorEntry(Color(0x33333333), BlendMode.SRC_OVER), // 假设 0x33333333 是对应的颜色
        )
    }

    /**
     * 定义浅色主题下的各种强度的混合颜色配置。
     */
    object LightColors {
        val DEFAULT = listOf(
            BlendColorEntry(Color(0x701A1A1A), BlendMode.COLOR_DODGE), // 假设 0x1A1A1A 是对应的颜色
            BlendColorEntry(Color(0x5D5D5D5D), BlendMode.SRC_OVER), // 假设 0x5D5D5D5D 是对应的颜色
        )

        val EXTRA_HEAVY = listOf(
            BlendColorEntry(Color(0x701A1A1A), BlendMode.COLOR_DODGE), // 假设 0x1A1A1A 是对应的颜色
            BlendColorEntry(Color(0x60606060), BlendMode.SRC_OVER), // 假设 0x60606060 是对应的颜色
        )

        val HEAVY = listOf(
            BlendColorEntry(Color(0x5A5A5A5A), BlendMode.COLOR_DODGE), // 假设 0x5A5A5A5A 是对应的颜色
            BlendColorEntry(Color(0x37373737), BlendMode.SRC_OVER), // 假设 0x37373737 是对应的颜色
        )

        val LIGHT = listOf(
            BlendColorEntry(Color(0x7A7A7A7A), BlendMode.COLOR_DODGE), // 假设 0x7A7A7A7A 是对应的颜色
            BlendColorEntry(Color(0x40404040), BlendMode.SRC_OVER), // 假设 0x40404040 是对应的颜色
        )

        val EXTRA_LIGHT = listOf(
            BlendColorEntry(Color(0x80808080), BlendMode.COLOR_DODGE), // 假设 0x80808080 是对应的颜色
            BlendColorEntry(Color(0x27272727), BlendMode.SRC_OVER), // 假设 0x27272727 是对应的颜色
        )
    }

    /**
     * 定义深色主题下的混合模式。
     * 注意：这里的 BlendMode 可能也需要根据 Miuix KMP 的 API 进行调整。
     * 例如，如果 Miuix 使用的是枚举而非 Int，这里应相应改变。
     */
    object DarkModes {
        val DEFAULT = listOf(BlendMode.COLOR_DODGE, BlendMode.LINEAR_LIGHT, BlendMode.LINEAR_LIGHT)
    }

    object LightModes {
        val DEFAULT = listOf(BlendMode.COLOR_BURN, BlendMode.LINEAR_LIGHT)
    }

    /**
     * 定义效果强度。
     */
    object Effects {
        const val DEFAULT = 66
        const val EXTRA_THIN = 30
        const val HEAVY = 74
        const val THIN = 52
    }
}