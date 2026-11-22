# RangeSliderPreference

`RangeSliderPreference` 是 Miuix 中的范围滑块偏好设置组件，将标题/摘要与范围滑块控件结合在一起。范围滑块放置在 `BasicComponent` 的底部操作区域中，适用于设置界面中需要选择数值范围的场景，如价格筛选、频段选择或双阈值控制。

## 引入

```kotlin
import top.yukonga.miuix.kmp.preference.RangeSliderPreference
```

## 基本用法

```kotlin
var rangeValue by remember { mutableStateOf(0.2f..0.8f) }

RangeSliderPreference(
    value = rangeValue,
    onValueChange = { rangeValue = it },
    title = "范围选择"
)
```

## 带摘要

```kotlin
var priceRange by remember { mutableStateOf(100f..500f) }

RangeSliderPreference(
    value = priceRange,
    onValueChange = { priceRange = it },
    title = "价格范围",
    summary = "¥${priceRange.start.roundToInt()} - ¥${priceRange.endInclusive.roundToInt()}",
    valueRange = 0f..1000f
)
```

## 组件状态

### 禁用状态

```kotlin
var rangeValue by remember { mutableStateOf(0.3f..0.7f) }

RangeSliderPreference(
    value = rangeValue,
    onValueChange = { rangeValue = it },
    title = "禁用的范围",
    summary = "此范围滑块当前不可用",
    enabled = false
)
```

## 属性

### RangeSliderPreference 属性

| 属性名                | 类型                                        | 说明                                                                                                             | 默认值                             | 是否必须 |
| --------------------- | ------------------------------------------- | ---------------------------------------------------------------------------------------------------------------- | ---------------------------------- | -------- |
| value                 | ClosedFloatingPointRange\<Float\>           | 范围滑块的当前值。如果任一值超出 valueRange，将被强制限制在范围内                                                | -                                  | 是       |
| onValueChange         | (ClosedFloatingPointRange\<Float\>) -> Unit | 值变化时的回调函数                                                                                               | -                                  | 是       |
| title                 | String?                                     | 偏好设置的标题                                                                                                   | null                               | 否       |
| modifier              | Modifier                                    | 应用于组件的修饰符                                                                                               | Modifier                           | 否       |
| titleColor            | BasicComponentColors                        | 标题文本的颜色配置                                                                                               | BasicComponentDefaults.titleColor() | 否       |
| summary               | String?                                     | 摘要说明                                                                                                         | null                               | 否       |
| summaryColor          | BasicComponentColors                        | 摘要文本的颜色配置                                                                                               | BasicComponentDefaults.summaryColor() | 否       |
| startAction           | @Composable (() -> Unit)?                   | 左侧自定义内容                                                                                                   | null                               | 否       |
| valueText             | String?                                     | 当前滑块值文本，以摘要样式显示在末尾区域。在 Row 布局中居中垂直对齐并使用权重                                     | null                               | 否       |
| endActions            | @Composable (RowScope.() -> Unit)?          | 右侧自定义内容，紧跟 valueText 在同一 Row 中渲染                                                                 | null                               | 否       |
| bottomAction          | @Composable (() -> Unit)?                   | 底部区域顶部的自定义内容，位于范围滑块上方                                                                       | null                               | 否       |
| onClick               | (() -> Unit)?                               | 点击时触发的回调。当非 null 时，末尾区域显示箭头图标                                                              | null                               | 否       |
| holdDownState         | Boolean                                     | 组件是否处于按下状态                                                                                             | false                              | 否       |
| enabled               | Boolean                                     | 是否启用偏好设置                                                                                                 | true                               | 否       |
| valueRange            | ClosedFloatingPointRange\<Float\>           | 范围滑块值可以采用的范围。传递的值将被强制限制在此范围内                                                         | 0f..1f                             | 否       |
| steps                 | Int                                         | 离散值的数量。如果为 0，滑块将连续运行。必须非负                                                                 | 0                                  | 否       |
| onValueChangeFinished | (() -> Unit)?                               | 值变化结束时调用                                                                                                 | null                               | 否       |
| sliderHeight          | Dp                                          | 范围滑块的高度                                                                                                   | SliderDefaults.MinHeight           | 否       |
| sliderColors          | SliderColors                                | 范围滑块的颜色配置                                                                                               | SliderDefaults.sliderColors()      | 否       |
| hapticEffect          | SliderDefaults.SliderHapticEffect           | 滑块的触感反馈类型                                                                                               | SliderDefaults.DefaultHapticEffect | 否       |
| showKeyPoints         | Boolean                                     | 是否显示关键点指示器。仅当 keyPoints 不为 null 时有效                                                            | false                              | 否       |
| keyPoints             | List\<Float\>?                              | 要在滑块上显示的自定义关键点值。如果为 null，则使用 steps 参数的步长位置。值应在 valueRange 范围内               | null                               | 否       |
| magnetThreshold       | Float                                       | 磁吸对齐阈值，以分数表示 (0.0 到 1.0)。当滑块值与关键点的距离在此阈值内时，将对齐到该点。仅在设置 keyPoints 时生效 | 0.02f                              | 否       |
| insideMargin          | PaddingValues                               | 组件内部内容的边距                                                                                               | BasicComponentDefaults.InsideMargin | 否       |

## 进阶用法

### 价格范围筛选

```kotlin
var priceRange by remember { mutableStateOf(100f..500f) }

RangeSliderPreference(
    value = priceRange,
    onValueChange = { priceRange = it },
    title = "价格筛选",
    summary = "¥${priceRange.start.roundToInt()} - ¥${priceRange.endInclusive.roundToInt()}",
    valueRange = 0f..1000f,
    steps = 99
)
```

### 带左侧图标

```kotlin
var frequencyRange by remember { mutableStateOf(20f..20000f) }

RangeSliderPreference(
    value = frequencyRange,
    onValueChange = { frequencyRange = it },
    title = "频率范围",
    summary = "${frequencyRange.start.roundToInt()}Hz - ${frequencyRange.endInclusive.roundToInt()}Hz",
    startAction = {
        Icon(
            imageVector = MiuixIcons.Basic.Audio,
            contentDescription = "频率图标",
            tint = MiuixTheme.colorScheme.onBackground,
            modifier = Modifier.padding(end = 16.dp)
        )
    },
    valueRange = 20f..20000f,
    showKeyPoints = true,
    keyPoints = listOf(20f, 100f, 1000f, 5000f, 10000f, 20000f)
)
```

### 自定义关键点

```kotlin
var range by remember { mutableStateOf(20f..80f) }

RangeSliderPreference(
    value = range,
    onValueChange = { range = it },
    title = "自定义范围",
    summary = "${range.start.roundToInt()}% - ${range.endInclusive.roundToInt()}%",
    valueRange = 0f..100f,
    showKeyPoints = true,
    keyPoints = listOf(0f, 20f, 40f, 60f, 80f, 100f),
    magnetThreshold = 0.03f
)
```
