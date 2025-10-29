# Slider

`Slider` 是 Miuix 中的基础交互组件，用于在连续的数值范围内进行选择。用户可以通过拖动滑块来调整值，适用于诸如音量调节、亮度控制、进度显示等场景。

Miuix 还提供了 `VerticalSlider` 用于垂直方向的滑块，以及 `RangeSlider` 用于选择数值范围。

<div style="position: relative; max-width: 700px; height: 250px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../../compose/index.html?id=slider" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

## 引入

```kotlin
import top.yukonga.miuix.kmp.basic.Slider
import top.yukonga.miuix.kmp.basic.VerticalSlider
import top.yukonga.miuix.kmp.basic.RangeSlider
```

## 基本用法

### Slider

```kotlin
var sliderValue by remember { mutableFloatStateOf(0.5f) }

Slider(
    value = sliderValue,
    onValueChange = { sliderValue = it }
)
```

### VerticalSlider

```kotlin
var sliderValue by remember { mutableFloatStateOf(0.5f) }

VerticalSlider(
    value = sliderValue,
    onValueChange = { sliderValue = it },
    modifier = Modifier.height(200.dp)
)
```

### RangeSlider

```kotlin
var rangeValue by remember { mutableStateOf(0.2f..0.8f) }

RangeSlider(
    value = rangeValue,
    onValueChange = { rangeValue = it }
)
```

## 组件状态

### 禁用状态

```kotlin
var value by remember { mutableFloatStateOf(0.5f) }

Slider(
    value = value,
    onValueChange = { value = it },
    enabled = false
)
```

## 触觉反馈

Slider 支持触觉反馈，可以通过 `hapticEffect` 参数自定义反馈效果，详见 [SliderHapticEffect](../components/slider#sliderhapticeffect)。

```kotlin
var value by remember { mutableFloatStateOf(0.5f) }

Slider(
    value = value,
    onValueChange = { value = it },
    hapticEffect = SliderHapticEffect.Step
)
```

## 属性

### Slider 属性

| 属性名                | 类型                              | 说明                                                                                                             | 默认值                             | 是否必须 |
| --------------------- | --------------------------------- | ---------------------------------------------------------------------------------------------------------------- | ---------------------------------- | -------- |
| value                 | Float                             | 当前滑块的值。如果超出 valueRange，值将被强制限制在该范围内                                                      | -                                  | 是       |
| onValueChange         | (Float) -> Unit                   | 值变化时的回调函数                                                                                               | -                                  | 是       |
| modifier              | Modifier                          | 应用于滑块的修饰符                                                                                               | Modifier                           | 否       |
| enabled               | Boolean                           | 是否启用滑块                                                                                                     | true                               | 否       |
| valueRange            | ClosedFloatingPointRange\<Float\> | 滑块可以采用的值范围。传递的值将被强制限制在此范围内                                                             | 0f..1f                             | 否       |
| steps                 | Int                               | 离散值的数量。如果为 0，滑块将连续运行。必须非负                                                                 | 0                                  | 否       |
| onValueChangeFinished | (() -> Unit)?                     | 值变化结束时调用                                                                                                 | null                               | 否       |
| reverseDirection      | Boolean                           | 控制滑块的方向。false 时从左到右增加,true 时从右到左增加(适用于 RTL 布局)                                        | false                              | 否       |
| height                | Dp                                | 滑块的高度                                                                                                       | SliderDefaults.MinHeight           | 否       |
| colors                | SliderColors                      | 滑块的颜色配置                                                                                                   | SliderDefaults.sliderColors()      | 否       |
| effect                | Boolean                           | 是否显示特殊效果                                                                                                 | false                              | 否       |
| hapticEffect          | SliderDefaults.SliderHapticEffect | 滑块的触感反馈类型                                                                                               | SliderDefaults.DefaultHapticEffect | 否       |
| showKeyPoints         | Boolean                           | 是否显示关键点指示器。仅当 keyPoints 不为 null 时有效                                                            | false                              | 否       |
| keyPoints             | List\<Float\>?                    | 要在滑块上显示的自定义关键点值。如果为 null,则使用 steps 参数的步长位置。值应在 valueRange 范围内                | null                               | 否       |
| magnetThreshold       | Float                             | 磁吸对齐阈值,以分数表示 (0.0 到 1.0)。当滑块值与关键点的距离在此阈值内时,将对齐到该点。仅在设置 keyPoints 时生效 | 0.02f                              | 否       |

### VerticalSlider 属性

| 属性名                | 类型                              | 说明                                                                                                             | 默认值                             | 是否必须 |
| --------------------- | --------------------------------- | ---------------------------------------------------------------------------------------------------------------- | ---------------------------------- | -------- |
| value                 | Float                             | 当前滑块的值                                                                                                     | -                                  | 是       |
| onValueChange         | (Float) -> Unit                   | 值变化时的回调函数                                                                                               | -                                  | 是       |
| modifier              | Modifier                          | 应用于滑块的修饰符                                                                                               | Modifier                           | 否       |
| enabled               | Boolean                           | 是否启用滑块                                                                                                     | true                               | 否       |
| valueRange            | ClosedFloatingPointRange\<Float\> | 滑块可以采用的值范围                                                                                             | 0f..1f                             | 否       |
| steps                 | Int                               | 离散值的数量                                                                                                     | 0                                  | 否       |
| onValueChangeFinished | (() -> Unit)?                     | 值变化结束时调用                                                                                                 | null                               | 否       |
| reverseDirection      | Boolean                           | 控制滑块的方向。false 时从下到上增加,true 时从上到下增加                                                         | false                              | 否       |
| width                 | Dp                                | 垂直滑块的宽度                                                                                                   | SliderDefaults.MinHeight           | 否       |
| colors                | SliderColors                      | 滑块的颜色配置                                                                                                   | SliderDefaults.sliderColors()      | 否       |
| effect                | Boolean                           | 是否显示特殊效果                                                                                                 | false                              | 否       |
| hapticEffect          | SliderDefaults.SliderHapticEffect | 滑块的触感反馈类型                                                                                               | SliderDefaults.DefaultHapticEffect | 否       |
| showKeyPoints         | Boolean                           | 是否显示关键点指示器。仅当 keyPoints 不为 null 时有效                                                            | false                              | 否       |
| keyPoints             | List\<Float\>?                    | 要在滑块上显示的自定义关键点值。如果为 null,则使用 steps 参数的步长位置。值应在 valueRange 范围内                | null                               | 否       |
| magnetThreshold       | Float                             | 磁吸对齐阈值,以分数表示 (0.0 到 1.0)。当滑块值与关键点的距离在此阈值内时,将对齐到该点。仅在设置 keyPoints 时生效 | 0.02f                              | 否       |

### RangeSlider 属性

| 属性名                | 类型                                        | 说明                                                                                                             | 默认值                             | 是否必须 |
| --------------------- | ------------------------------------------- | ---------------------------------------------------------------------------------------------------------------- | ---------------------------------- | -------- |
| value                 | ClosedFloatingPointRange\<Float\>           | RangeSlider 的当前值。如果任一值超出 valueRange，将被强制限制在范围内                                            | -                                  | 是       |
| onValueChange         | (ClosedFloatingPointRange\<Float\>) -> Unit | 值变化时的回调函数                                                                                               | -                                  | 是       |
| modifier              | Modifier                                    | 应用于滑块的修饰符                                                                                               | Modifier                           | 否       |
| enabled               | Boolean                                     | 是否启用滑块                                                                                                     | true                               | 否       |
| valueRange            | ClosedFloatingPointRange\<Float\>           | Range Slider 值可以采用的范围                                                                                    | 0f..1f                             | 否       |
| steps                 | Int                                         | 离散值的数量                                                                                                     | 0                                  | 否       |
| onValueChangeFinished | (() -> Unit)?                               | 值变化结束时调用                                                                                                 | null                               | 否       |
| height                | Dp                                          | 滑块的高度                                                                                                       | SliderDefaults.MinHeight           | 否       |
| colors                | SliderColors                                | 滑块的颜色配置                                                                                                   | SliderDefaults.sliderColors()      | 否       |
| effect                | Boolean                                     | 是否显示特殊效果                                                                                                 | false                              | 否       |
| hapticEffect          | SliderDefaults.SliderHapticEffect           | 滑块的触感反馈类型                                                                                               | SliderDefaults.DefaultHapticEffect | 否       |
| showKeyPoints         | Boolean                                     | 是否显示关键点指示器。仅当 keyPoints 不为 null 时有效                                                            | false                              | 否       |
| keyPoints             | List\<Float\>?                              | 要在滑块上显示的自定义关键点值。如果为 null,则使用 steps 参数的步长位置。值应在 valueRange 范围内                | null                               | 否       |
| magnetThreshold       | Float                                       | 磁吸对齐阈值,以分数表示 (0.0 到 1.0)。当滑块值与关键点的距离在此阈值内时,将对齐到该点。仅在设置 keyPoints 时生效 | 0.02f                              | 否       |

### SliderDefaults 对象

SliderDefaults 对象提供了 Slider 组件的默认配置。

#### 常量

| 常量名              | 类型               | 默认值                  | 说明               |
| ------------------- | ------------------ | ----------------------- | ------------------ |
| MinHeight           | Dp                 | 30.dp                   | 滑块的默认高度     |
| DefaultHapticEffect | SliderHapticEffect | SliderHapticEffect.Edge | 默认的触感反馈类型 |

### SliderHapticEffect

| 值   | 说明                   |
| ---- | ---------------------- |
| None | 无触感反馈             |
| Edge | 在边缘处有触感反馈     |
| Step | 在每个步长处有触感反馈 |

#### 方法

| 方法名         | 返回类型     | 说明                   |
| -------------- | ------------ | ---------------------- |
| sliderColors() | SliderColors | 创建滑块的默认颜色配置 |

### SliderColors 类

| 属性名                  | 类型  | 说明                     |
| ----------------------- | ----- | ------------------------ |
| foregroundColor         | Color | 滑块的前景颜色           |
| disabledForegroundColor | Color | 禁用状态时滑块的前景颜色 |
| backgroundColor         | Color | 滑块的背景颜色           |
| keyPointColor           | Color | 背景中关键点指示器的颜色   |
| keyPointForegroundColor | Color | 前景中关键点指示器的颜色   |

## 进阶用法

### 自定义数值范围

```kotlin
var temperature by remember { mutableFloatStateOf(25f) }

Column {
    Text("温度: ${temperature.roundToInt()}°C")
    Slider(
        value = temperature,
        onValueChange = { temperature = it },
        valueRange = 16f..32f
    )
}
```

### 离散步长

```kotlin
var rating by remember { mutableFloatStateOf(3f) }

Column {
    Text("评分: ${rating.roundToInt()}/5")
    Slider(
        value = rating,
        onValueChange = { rating = it },
        valueRange = 1f..5f,
        steps = 3  // 创建 5 个离散值: 1, 2, 3, 4, 5
    )
}
```

### 自定义颜色

```kotlin
var volume by remember { mutableFloatStateOf(0.7f) }

Slider(
    value = volume,
    onValueChange = { volume = it },
    colors = SliderDefaults.sliderColors(
        foregroundColor = Color.Red,
        backgroundColor = Color.LightGray
    )
)
```

### 自定义高度和效果

```kotlin
var brightness by remember { mutableFloatStateOf(0.8f) }

Slider(
    value = brightness,
    onValueChange = { brightness = it },
    height = 40.dp,
    effect = true
)
```

### 带触感反馈的滑块

```kotlin
var value by remember { mutableFloatStateOf(0.5f) }

Slider(
    value = value,
    onValueChange = { value = it },
    hapticEffect = SliderDefaults.SliderHapticEffect.Step
)
```

### 反向方向的 VerticalSlider

```kotlin
var volume by remember { mutableFloatStateOf(0.5f) }

VerticalSlider(
    value = volume,
    onValueChange = { volume = it },
    modifier = Modifier.height(200.dp),
    reverseDirection = true  // 从上到下
)
```

### 用于价格筛选的 RangeSlider

```kotlin
var priceRange by remember { mutableStateOf(100f..500f) }

Column {
    Text("价格: ¥${priceRange.start.roundToInt()} - ¥${priceRange.endInclusive.roundToInt()}")
    RangeSlider(
        value = priceRange,
        onValueChange = { priceRange = it },
        valueRange = 0f..1000f,
        steps = 99  // 从 0 到 1000 的 101 个离散值
    )
}
```

### 完整示例（含值变化回调）

```kotlin
var value by remember { mutableFloatStateOf(0.5f) }
var finalValue by remember { mutableFloatStateOf(0.5f) }

Column {
    Text("当前值: $value")
    Text("最终值: $finalValue")
    Slider(
        value = value,
        onValueChange = { value = it },
        onValueChangeFinished = { finalValue = value },
        valueRange = 0f..100f,
        steps = 99
    )
}
```

### 自定义关键点的滑块

```kotlin
var value by remember { mutableFloatStateOf(50f) }

Column {
    Text("值: ${value.roundToInt()}")
    Slider(
        value = value,
        onValueChange = { value = it },
        valueRange = 0f..100f,
        showKeyPoints = true,
        keyPoints = listOf(0f, 25f, 50f, 75f, 100f),
        magnetThreshold = 0.05f,  // 5% 磁吸阈值
        hapticEffect = SliderDefaults.SliderHapticEffect.Step
    )
}
```

### 带关键点的 RangeSlider

```kotlin
var range by remember { mutableStateOf(20f..80f) }

Column {
    Text("范围: ${range.start.roundToInt()} - ${range.endInclusive.roundToInt()}")
    RangeSlider(
        value = range,
        onValueChange = { range = it },
        valueRange = 0f..100f,
        showKeyPoints = true,
        keyPoints = listOf(0f, 20f, 40f, 60f, 80f, 100f),
        magnetThreshold = 0.03f
    )
}
```
