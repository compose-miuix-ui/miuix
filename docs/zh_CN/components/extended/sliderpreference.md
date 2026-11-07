# SliderPreference

`SliderPreference` 是 Miuix 中的滑块偏好设置组件，将标题/摘要与滑块控件结合在一起。滑块放置在 `BasicComponent` 的底部操作区域中，适用于设置界面中需要调整数值的场景，如音量、亮度或字体大小控制。

## 引入

```kotlin
import top.yukonga.miuix.kmp.preference.SliderPreference
```

## 基本用法

```kotlin
var sliderValue by remember { mutableFloatStateOf(0.5f) }

SliderPreference(
    value = sliderValue,
    onValueChange = { sliderValue = it },
    title = "音量"
)
```

## 带摘要

```kotlin
var brightness by remember { mutableFloatStateOf(0.8f) }

SliderPreference(
    value = brightness,
    onValueChange = { brightness = it },
    title = "亮度",
    summary = "调整屏幕亮度级别"
)
```

## 组件状态

### 禁用状态

```kotlin
var value by remember { mutableFloatStateOf(0.5f) }

SliderPreference(
    value = value,
    onValueChange = { value = it },
    title = "禁用的滑块",
    summary = "此滑块当前不可用",
    enabled = false
)
```

## 属性

### SliderPreference 属性

| 属性名                | 类型                              | 说明                                                                                                             | 默认值                             | 是否必须 |
| --------------------- | --------------------------------- | ---------------------------------------------------------------------------------------------------------------- | ---------------------------------- | -------- |
| value                 | Float                             | 当前滑块的值。如果超出 valueRange，值将被强制限制在该范围内                                                      | -                                  | 是       |
| onValueChange         | (Float) -> Unit                   | 值变化时的回调函数                                                                                               | -                                  | 是       |
| title                 | String?                           | 偏好设置的标题                                                                                                   | null                               | 否       |
| modifier              | Modifier                          | 应用于组件的修饰符                                                                                               | Modifier                           | 否       |
| titleColor            | BasicComponentColors              | 标题文本的颜色配置                                                                                               | BasicComponentDefaults.titleColor() | 否       |
| summary               | String?                           | 摘要说明                                                                                                         | null                               | 否       |
| summaryColor          | BasicComponentColors              | 摘要文本的颜色配置                                                                                               | BasicComponentDefaults.summaryColor() | 否       |
| startAction           | @Composable (() -> Unit)?         | 左侧自定义内容                                                                                                   | null                               | 否       |
| valueText             | String?                           | 当前滑块值文本，以摘要样式显示在末尾区域。在 Row 布局中居中垂直对齐并使用权重                                     | null                               | 否       |
| endActions            | @Composable (RowScope.() -> Unit)?| 右侧自定义内容，紧跟 valueText 在同一 Row 中渲染                                                                 | null                               | 否       |
| bottomAction          | @Composable (() -> Unit)?         | 底部区域顶部的自定义内容，位于滑块上方                                                                           | null                               | 否       |
| onClick               | (() -> Unit)?                     | 点击时触发的回调。当非 null 时，末尾区域显示箭头图标                                                              | null                               | 否       |
| holdDownState         | Boolean                           | 组件是否处于按下状态                                                                                             | false                              | 否       |
| enabled               | Boolean                           | 是否启用偏好设置                                                                                                 | true                               | 否       |
| valueRange            | ClosedFloatingPointRange\<Float\> | 滑块可以采用的值范围。传递的值将被强制限制在此范围内                                                             | 0f..1f                             | 否       |
| steps                 | Int                               | 离散值的数量。如果为 0，滑块将连续运行。必须非负                                                                 | 0                                  | 否       |
| onValueChangeFinished | (() -> Unit)?                     | 值变化结束时调用                                                                                                 | null                               | 否       |
| reverseDirection      | Boolean                           | 控制滑块的方向。默认 false，方向跟随 LayoutDirection (LTR 时从左到右，RTL 时从右到左)。为 true 时反转方向。       | false                              | 否       |
| sliderHeight          | Dp                                | 滑块的高度                                                                                                       | SliderDefaults.MinHeight           | 否       |
| sliderColors          | SliderColors                      | 滑块的颜色配置                                                                                                   | SliderDefaults.sliderColors()      | 否       |
| hapticEffect          | SliderDefaults.SliderHapticEffect | 滑块的触感反馈类型                                                                                               | SliderDefaults.DefaultHapticEffect | 否       |
| showKeyPoints         | Boolean                           | 是否显示关键点指示器。仅当 keyPoints 不为 null 时有效                                                            | false                              | 否       |
| keyPoints             | List\<Float\>?                    | 要在滑块上显示的自定义关键点值。如果为 null，则使用 steps 参数的步长位置。值应在 valueRange 范围内               | null                               | 否       |
| magnetThreshold       | Float                             | 磁吸对齐阈值，以分数表示 (0.0 到 1.0)。当滑块值与关键点的距离在此阈值内时，将对齐到该点。仅在设置 keyPoints 时生效 | 0.02f                              | 否       |
| insideMargin          | PaddingValues                     | 组件内部内容的边距                                                                                               | BasicComponentDefaults.InsideMargin | 否       |

## 进阶用法

### 自定义数值范围与步长

```kotlin
var temperature by remember { mutableFloatStateOf(25f) }

SliderPreference(
    value = temperature,
    onValueChange = { temperature = it },
    title = "温度",
    summary = "当前: ${temperature.roundToInt()}°C",
    valueRange = 16f..32f,
    steps = 15,
    hapticEffect = SliderDefaults.SliderHapticEffect.Step
)
```

### 带左侧图标

```kotlin
var volume by remember { mutableFloatStateOf(0.7f) }

SliderPreference(
    value = volume,
    onValueChange = { volume = it },
    title = "音量",
    summary = "${(volume * 100).roundToInt()}%",
    startAction = {
        Icon(
            imageVector = MiuixIcons.Basic.Audio,
            contentDescription = "音量图标",
            tint = MiuixTheme.colorScheme.onBackground,
            modifier = Modifier.padding(end = 16.dp)
        )
    },
    valueRange = 0f..1f
)
```

### 自定义关键点

```kotlin
var fontSize by remember { mutableFloatStateOf(14f) }

SliderPreference(
    value = fontSize,
    onValueChange = { fontSize = it },
    title = "字体大小",
    summary = "当前: ${fontSize.roundToInt()}sp",
    valueRange = 10f..24f,
    showKeyPoints = true,
    keyPoints = listOf(10f, 12f, 14f, 16f, 18f, 20f, 24f),
    magnetThreshold = 0.05f,
    hapticEffect = SliderDefaults.SliderHapticEffect.Step
)
```
