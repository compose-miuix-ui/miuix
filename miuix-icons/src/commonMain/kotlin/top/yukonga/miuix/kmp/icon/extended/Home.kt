// Copyright 2026, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0

package top.yukonga.miuix.kmp.icon.extended

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.PathNode
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.icon.MiuixIcons

val MiuixIcons.Home: ImageVector
    get() = MiuixIcons.Regular.Home

val MiuixIcons.Light.Home: ImageVector
    get() {
        if (_homeLight != null) return _homeLight!!
        _homeLight = ImageVector.Builder(
            name = "Home.Light",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 24.0f,
            viewportHeight = 24.0f,
        ).apply {
            addPath(
                pathData = listOf(
                    PathNode.MoveTo(2.242f, 10.431f),
                    PathNode.CurveTo(2.13f, 10.853f, 2.13f, 11.311f, 2.13f, 12.295f),
                    PathNode.LineTo(2.13f, 17.344f),
                    PathNode.CurveTo(2.13f, 19.067f, 2.13f, 19.864f, 2.435f, 20.465f),
                    PathNode.CurveTo(2.711f, 21.007f, 3.122f, 21.418f, 3.663f, 21.693f),
                    PathNode.CurveTo(4.264f, 22.0f, 5.062f, 22.0f, 6.784f, 22.0f),
                    PathNode.LineTo(7.036f, 22.0f),
                    PathNode.CurveTo(7.725f, 22.0f, 8.006f, 22.0f, 8.212f, 21.894f),
                    PathNode.CurveTo(8.406f, 21.796f, 8.534f, 21.667f, 8.633f, 21.474f),
                    PathNode.CurveTo(8.739f, 21.267f, 8.739f, 20.987f, 8.739f, 20.298f),
                    PathNode.LineTo(8.739f, 17.719f),
                    PathNode.CurveTo(8.739f, 15.955f, 10.235f, 14.458f, 11.999f, 14.458f),
                    PathNode.CurveTo(13.762f, 14.458f, 15.259f, 15.955f, 15.259f, 17.719f),
                    PathNode.LineTo(15.259f, 20.298f),
                    PathNode.CurveTo(15.259f, 20.987f, 15.259f, 21.267f, 15.365f, 21.474f),
                    PathNode.CurveTo(15.463f, 21.667f, 15.592f, 21.796f, 15.785f, 21.894f),
                    PathNode.CurveTo(15.992f, 22.0f, 16.272f, 22.0f, 16.961f, 22.0f),
                    PathNode.LineTo(17.215f, 22.0f),
                    PathNode.CurveTo(18.937f, 22.0f, 19.735f, 22.0f, 20.335f, 21.693f),
                    PathNode.CurveTo(20.877f, 21.418f, 21.288f, 21.007f, 21.564f, 20.465f),
                    PathNode.CurveTo(21.87f, 19.864f, 21.87f, 19.067f, 21.87f, 17.344f),
                    PathNode.LineTo(21.87f, 12.295f),
                    PathNode.CurveTo(21.87f, 11.31f, 21.87f, 10.854f, 21.757f, 10.432f),
                    PathNode.CurveTo(21.655f, 10.05f, 21.496f, 9.71f, 21.269f, 9.386f),
                    PathNode.CurveTo(21.019f, 9.028f, 20.669f, 8.734f, 19.914f, 8.101f),
                    PathNode.LineTo(13.938f, 3.083f),
                    PathNode.CurveTo(13.188f, 2.454f, 12.857f, 2.176f, 12.492f, 2.072f),
                    PathNode.CurveTo(12.158f, 1.976f, 11.841f, 1.976f, 11.507f, 2.072f),
                    PathNode.CurveTo(11.142f, 2.176f, 10.811f, 2.454f, 10.061f, 3.083f),
                    PathNode.LineTo(4.084f, 8.101f),
                    PathNode.CurveTo(3.33f, 8.734f, 2.98f, 9.028f, 2.73f, 9.386f),
                    PathNode.CurveTo(2.503f, 9.71f, 2.344f, 10.05f, 2.242f, 10.431f),
                    PathNode.Close,
                ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero,
            )
        }.build()
        return _homeLight!!
    }

private var _homeLight: ImageVector? = null

val MiuixIcons.Normal.Home: ImageVector
    get() {
        if (_homeNormal != null) return _homeNormal!!
        _homeNormal = ImageVector.Builder(
            name = "Home.Normal",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 24.0f,
            viewportHeight = 24.0f,
        ).apply {
            addPath(
                pathData = listOf(
                    PathNode.MoveTo(2.243f, 10.412f),
                    PathNode.CurveTo(2.126f, 10.849f, 2.126f, 11.323f, 2.126f, 12.289f),
                    PathNode.LineTo(2.126f, 17.247f),
                    PathNode.CurveTo(2.126f, 18.938f, 2.126f, 19.765f, 2.446f, 20.394f),
                    PathNode.CurveTo(2.73f, 20.951f, 3.174f, 21.395f, 3.731f, 21.679f),
                    PathNode.CurveTo(4.36f, 22.0f, 5.187f, 22.0f, 6.878f, 22.0f),
                    PathNode.LineTo(7.126f, 22.0f),
                    PathNode.CurveTo(7.802f, 22.0f, 8.122f, 22.0f, 8.363f, 21.876f),
                    PathNode.CurveTo(8.579f, 21.767f, 8.746f, 21.6f, 8.855f, 21.384f),
                    PathNode.CurveTo(8.979f, 21.143f, 8.979f, 20.824f, 8.979f, 20.147f),
                    PathNode.LineTo(8.979f, 17.615f),
                    PathNode.CurveTo(8.979f, 15.957f, 10.342f, 14.594f, 11.999f, 14.594f),
                    PathNode.CurveTo(13.655f, 14.594f, 15.019f, 15.957f, 15.019f, 17.615f),
                    PathNode.LineTo(15.019f, 20.147f),
                    PathNode.CurveTo(15.019f, 20.824f, 15.019f, 21.143f, 15.142f, 21.384f),
                    PathNode.CurveTo(15.252f, 21.6f, 15.419f, 21.767f, 15.634f, 21.876f),
                    PathNode.CurveTo(15.876f, 22.0f, 16.195f, 22.0f, 16.872f, 22.0f),
                    PathNode.LineTo(17.12f, 22.0f),
                    PathNode.CurveTo(18.812f, 22.0f, 19.638f, 22.0f, 20.267f, 21.679f),
                    PathNode.CurveTo(20.825f, 21.395f, 21.269f, 20.951f, 21.553f, 20.394f),
                    PathNode.CurveTo(21.874f, 19.765f, 21.874f, 18.938f, 21.874f, 17.247f),
                    PathNode.LineTo(21.874f, 12.289f),
                    PathNode.CurveTo(21.874f, 11.322f, 21.874f, 10.849f, 21.756f, 10.412f),
                    PathNode.CurveTo(21.651f, 10.022f, 21.482f, 9.659f, 21.251f, 9.328f),
                    PathNode.CurveTo(20.991f, 8.957f, 20.629f, 8.653f, 19.888f, 8.032f),
                    PathNode.LineTo(14.019f, 3.105f),
                    PathNode.CurveTo(13.283f, 2.487f, 12.928f, 2.189f, 12.533f, 2.076f),
                    PathNode.CurveTo(12.181f, 1.975f, 11.818f, 1.975f, 11.466f, 2.076f),
                    PathNode.CurveTo(11.071f, 2.189f, 10.716f, 2.487f, 9.979f, 3.105f),
                    PathNode.LineTo(4.111f, 8.032f),
                    PathNode.CurveTo(3.37f, 8.653f, 3.008f, 8.957f, 2.748f, 9.328f),
                    PathNode.CurveTo(2.517f, 9.659f, 2.347f, 10.022f, 2.243f, 10.412f),
                    PathNode.Close,
                ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero,
            )
        }.build()
        return _homeNormal!!
    }

private var _homeNormal: ImageVector? = null

val MiuixIcons.Regular.Home: ImageVector
    get() {
        if (_homeRegular != null) return _homeRegular!!
        _homeRegular = ImageVector.Builder(
            name = "Home.Regular",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 24.0f,
            viewportHeight = 24.0f,
        ).apply {
            addPath(
                pathData = listOf(
                    PathNode.MoveTo(2.243f, 10.404f),
                    PathNode.CurveTo(2.125f, 10.848f, 2.125f, 11.328f, 2.125f, 12.286f),
                    PathNode.LineTo(2.125f, 17.205f),
                    PathNode.CurveTo(2.125f, 18.883f, 2.125f, 19.722f, 2.451f, 20.363f),
                    PathNode.CurveTo(2.738f, 20.927f, 3.196f, 21.386f, 3.761f, 21.673f),
                    PathNode.CurveTo(4.402f, 22.0f, 5.241f, 22.0f, 6.919f, 22.0f),
                    PathNode.LineTo(7.164f, 22.0f),
                    PathNode.CurveTo(7.836f, 22.0f, 8.171f, 22.0f, 8.428f, 21.869f),
                    PathNode.CurveTo(8.653f, 21.754f, 8.836f, 21.571f, 8.951f, 21.346f),
                    PathNode.CurveTo(9.082f, 21.089f, 9.082f, 20.754f, 9.082f, 20.082f),
                    PathNode.LineTo(9.082f, 17.57f),
                    PathNode.CurveTo(9.082f, 15.959f, 10.388f, 14.653f, 11.999f, 14.653f),
                    PathNode.CurveTo(13.609f, 14.653f, 14.915f, 15.959f, 14.915f, 17.57f),
                    PathNode.LineTo(14.915f, 20.082f),
                    PathNode.CurveTo(14.915f, 20.754f, 14.915f, 21.089f, 15.047f, 21.346f),
                    PathNode.CurveTo(15.161f, 21.571f, 15.345f, 21.754f, 15.57f, 21.869f),
                    PathNode.CurveTo(15.826f, 22.0f, 16.162f, 22.0f, 16.833f, 22.0f),
                    PathNode.LineTo(17.08f, 22.0f),
                    PathNode.CurveTo(18.758f, 22.0f, 19.597f, 22.0f, 20.238f, 21.673f),
                    PathNode.CurveTo(20.802f, 21.386f, 21.261f, 20.927f, 21.548f, 20.363f),
                    PathNode.CurveTo(21.875f, 19.722f, 21.875f, 18.883f, 21.875f, 17.205f),
                    PathNode.LineTo(21.875f, 12.286f),
                    PathNode.CurveTo(21.875f, 11.327f, 21.875f, 10.848f, 21.756f, 10.404f),
                    PathNode.CurveTo(21.65f, 10.01f, 21.476f, 9.638f, 21.243f, 9.303f),
                    PathNode.CurveTo(20.979f, 8.926f, 20.611f, 8.619f, 19.877f, 8.002f),
                    PathNode.LineTo(14.055f, 3.114f),
                    PathNode.CurveTo(13.324f, 2.501f, 12.959f, 2.194f, 12.55f, 2.078f),
                    PathNode.CurveTo(12.19f, 1.974f, 11.808f, 1.974f, 11.449f, 2.078f),
                    PathNode.CurveTo(11.04f, 2.194f, 10.675f, 2.501f, 9.944f, 3.114f),
                    PathNode.LineTo(4.122f, 8.002f),
                    PathNode.CurveTo(3.387f, 8.619f, 3.02f, 8.926f, 2.756f, 9.303f),
                    PathNode.CurveTo(2.523f, 9.638f, 2.349f, 10.01f, 2.243f, 10.404f),
                    PathNode.Close,
                ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero,
            )
        }.build()
        return _homeRegular!!
    }

private var _homeRegular: ImageVector? = null

val MiuixIcons.Medium.Home: ImageVector
    get() {
        if (_homeMedium != null) return _homeMedium!!
        _homeMedium = ImageVector.Builder(
            name = "Home.Medium",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 24.0f,
            viewportHeight = 24.0f,
        ).apply {
            addPath(
                pathData = listOf(
                    PathNode.MoveTo(2.244f, 10.389f),
                    PathNode.CurveTo(2.123f, 10.845f, 2.123f, 11.337f, 2.123f, 12.282f),
                    PathNode.LineTo(2.123f, 17.132f),
                    PathNode.CurveTo(2.123f, 18.787f, 2.123f, 19.648f, 2.459f, 20.31f),
                    PathNode.CurveTo(2.752f, 20.886f, 3.236f, 21.369f, 3.812f, 21.662f),
                    PathNode.CurveTo(4.473f, 22.0f, 5.334f, 22.0f, 6.989f, 22.0f),
                    PathNode.LineTo(7.232f, 22.0f),
                    PathNode.CurveTo(7.893f, 22.0f, 8.258f, 22.0f, 8.541f, 21.855f),
                    PathNode.CurveTo(8.782f, 21.733f, 8.995f, 21.52f, 9.117f, 21.279f),
                    PathNode.CurveTo(9.262f, 20.996f, 9.262f, 20.631f, 9.262f, 19.969f),
                    PathNode.LineTo(9.262f, 17.492f),
                    PathNode.CurveTo(9.262f, 15.961f, 10.468f, 14.755f, 11.999f, 14.755f),
                    PathNode.CurveTo(13.529f, 14.755f, 14.736f, 15.961f, 14.736f, 17.492f),
                    PathNode.LineTo(14.736f, 19.969f),
                    PathNode.CurveTo(14.736f, 20.631f, 14.736f, 20.996f, 14.88f, 21.279f),
                    PathNode.CurveTo(15.003f, 21.52f, 15.215f, 21.733f, 15.457f, 21.855f),
                    PathNode.CurveTo(15.74f, 22.0f, 16.104f, 22.0f, 16.766f, 22.0f),
                    PathNode.LineTo(17.01f, 22.0f),
                    PathNode.CurveTo(18.664f, 22.0f, 19.525f, 22.0f, 20.187f, 21.662f),
                    PathNode.CurveTo(20.763f, 21.369f, 21.247f, 20.886f, 21.54f, 20.31f),
                    PathNode.CurveTo(21.877f, 19.648f, 21.877f, 18.787f, 21.877f, 17.132f),
                    PathNode.LineTo(21.877f, 12.282f),
                    PathNode.CurveTo(21.877f, 11.335f, 21.877f, 10.845f, 21.755f, 10.389f),
                    PathNode.CurveTo(21.648f, 9.989f, 21.466f, 9.6f, 21.229f, 9.26f),
                    PathNode.CurveTo(20.958f, 8.873f, 20.582f, 8.558f, 19.857f, 7.95f),
                    PathNode.LineTo(14.116f, 3.129f),
                    PathNode.CurveTo(13.395f, 2.525f, 13.012f, 2.204f, 12.581f, 2.08f),
                    PathNode.CurveTo(12.207f, 1.973f, 11.792f, 1.973f, 11.418f, 2.08f),
                    PathNode.CurveTo(10.986f, 2.204f, 10.604f, 2.525f, 9.883f, 3.129f),
                    PathNode.LineTo(4.142f, 7.95f),
                    PathNode.CurveTo(3.417f, 8.558f, 3.041f, 8.873f, 2.77f, 9.26f),
                    PathNode.CurveTo(2.533f, 9.6f, 2.351f, 9.989f, 2.244f, 10.389f),
                    PathNode.Close,
                ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero,
            )
        }.build()
        return _homeMedium!!
    }

private var _homeMedium: ImageVector? = null

val MiuixIcons.Demibold.Home: ImageVector
    get() {
        if (_homeDemibold != null) return _homeDemibold!!
        _homeDemibold = ImageVector.Builder(
            name = "Home.Demibold",
            defaultWidth = 24.0f.dp,
            defaultHeight = 24.0f.dp,
            viewportWidth = 24.0f,
            viewportHeight = 24.0f,
        ).apply {
            addPath(
                pathData = listOf(
                    PathNode.MoveTo(2.244f, 10.38f),
                    PathNode.CurveTo(2.121f, 10.843f, 2.121f, 11.342f, 2.121f, 12.278f),
                    PathNode.LineTo(2.121f, 17.084f),
                    PathNode.CurveTo(2.121f, 18.723f, 2.121f, 19.599f, 2.465f, 20.274f),
                    PathNode.CurveTo(2.762f, 20.858f, 3.262f, 21.358f, 3.846f, 21.655f),
                    PathNode.CurveTo(4.521f, 22.0f, 5.397f, 22.0f, 7.036f, 22.0f),
                    PathNode.LineTo(7.276f, 22.0f),
                    PathNode.CurveTo(7.932f, 22.0f, 8.316f, 22.0f, 8.616f, 21.846f),
                    PathNode.CurveTo(8.868f, 21.718f, 9.1f, 21.487f, 9.228f, 21.234f),
                    PathNode.CurveTo(9.382f, 20.934f, 9.382f, 20.55f, 9.382f, 19.895f),
                    PathNode.LineTo(9.382f, 17.44f),
                    PathNode.CurveTo(9.382f, 15.962f, 10.521f, 14.822f, 11.999f, 14.822f),
                    PathNode.CurveTo(13.476f, 14.822f, 14.616f, 15.962f, 14.616f, 17.44f),
                    PathNode.LineTo(14.616f, 19.895f),
                    PathNode.CurveTo(14.616f, 20.55f, 14.616f, 20.934f, 14.77f, 21.234f),
                    PathNode.CurveTo(14.898f, 21.487f, 15.129f, 21.718f, 15.382f, 21.846f),
                    PathNode.CurveTo(15.682f, 22.0f, 16.066f, 22.0f, 16.722f, 22.0f),
                    PathNode.LineTo(16.963f, 22.0f),
                    PathNode.CurveTo(18.602f, 22.0f, 19.478f, 22.0f, 20.153f, 21.655f),
                    PathNode.CurveTo(20.737f, 21.358f, 21.237f, 20.858f, 21.534f, 20.274f),
                    PathNode.CurveTo(21.879f, 19.599f, 21.879f, 18.723f, 21.879f, 17.084f),
                    PathNode.LineTo(21.879f, 12.278f),
                    PathNode.CurveTo(21.879f, 11.341f, 21.879f, 10.843f, 21.755f, 10.379f),
                    PathNode.CurveTo(21.646f, 9.975f, 21.459f, 9.575f, 21.219f, 9.231f),
                    PathNode.CurveTo(20.944f, 8.838f, 20.562f, 8.518f, 19.844f, 7.916f),
                    PathNode.LineTo(14.156f, 3.14f),
                    PathNode.CurveTo(13.442f, 2.541f, 13.048f, 2.21f, 12.601f, 2.082f),
                    PathNode.CurveTo(12.219f, 1.973f, 11.78f, 1.973f, 11.397f, 2.082f),
                    PathNode.CurveTo(10.951f, 2.21f, 10.557f, 2.541f, 9.843f, 3.14f),
                    PathNode.LineTo(4.155f, 7.916f),
                    PathNode.CurveTo(3.437f, 8.518f, 3.055f, 8.838f, 2.78f, 9.231f),
                    PathNode.CurveTo(2.54f, 9.575f, 2.353f, 9.975f, 2.244f, 10.38f),
                    PathNode.Close,
                ),
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                pathFillType = PathFillType.NonZero,
            )
        }.build()
        return _homeDemibold!!
    }

private var _homeDemibold: ImageVector? = null
