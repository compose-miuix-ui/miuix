# NumberPicker

`NumberPicker` 是 Miuix 中的基础交互组件，用于通过垂直滚动从一组数字中选择值。选中项居中并高亮显示，周围的项目逐渐淡出并缩小。通过 `wrapAround` 参数支持无限循环滚动。

<div style="position: relative; height: 300px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../../compose/index.html?id=numberPicker" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

## 引入

```kotlin
import top.yukonga.miuix.kmp.basic.NumberPicker
```

## 基本用法

```kotlin
var value by remember { mutableIntStateOf(5) }

NumberPicker(
    value = value,
    onValueChange = { value = it },
    range = 0..10
)
```

## 组件状态

### 禁用状态

```kotlin
var value by remember { mutableIntStateOf(5) }

NumberPicker(
    value = value,
    onValueChange = { value = it },
    range = 0..10,
    enabled = false
)
```

### 无限滚动

启用 `wrapAround` 后，选择器会从最后一项循环回到第一项，实现连续滚动。

```kotlin
var value by remember { mutableIntStateOf(0) }

NumberPicker(
    value = value,
    onValueChange = { value = it },
    range = 0..23,
    wrapAround = true
)
```

## 属性

### NumberPicker 属性

| 属性名           | 类型               | 说明                                                 | 默认值                           | 是否必须 |
| ---------------- | ------------------ | ---------------------------------------------------- | -------------------------------- | -------- |
| value            | Int                | 当前选中的值，超出 range 时会自动修正                | -                                | 是       |
| onValueChange    | (Int) -> Unit      | 选中值变化时的回调函数                               | -                                | 是       |
| modifier         | Modifier           | 应用于选择器的修饰符                                 | Modifier                         | 否       |
| enabled          | Boolean            | 是否启用用户交互                                     | true                             | 否       |
| range            | IntRange           | 可选择的值范围                                       | 0..10                            | 否       |
| label            | (Int) -> String    | 将值转换为显示字符串的函数                           | { it.toString() }                | 否       |
| visibleItemCount | Int                | 可见项数。必须为奇数且不小于 3                       | 5                                | 否       |
| wrapAround       | Boolean            | 是否启用循环滚动（无限滚动）                         | false                            | 否       |
| colors           | NumberPickerColors | 选择器的颜色配置                                     | NumberPickerDefaults.colors()    | 否       |
| textStyle        | TextStyle          | 选择器项目的文本样式                                 | MiuixTheme.textStyles.title1     | 否       |
| itemHeight       | Dp                 | 每个项目的高度                                       | NumberPickerDefaults.ItemHeight   | 否       |

### NumberPickerDefaults 对象

NumberPickerDefaults 对象提供了 NumberPicker 组件的默认配置。

#### 属性

| 属性名     | 类型 | 说明                 | 默认值  |
| ---------- | ---- | -------------------- | ------- |
| ItemHeight | Dp   | 每个项目的默认高度   | 45.dp   |

#### 方法

| 方法名   | 返回类型           | 说明                       |
| -------- | ------------------ | -------------------------- |
| colors() | NumberPickerColors | 创建默认的颜色配置         |

### NumberPickerColors 类

| 属性名                      | 类型  | 说明                       |
| --------------------------- | ----- | -------------------------- |
| selectedTextColor           | Color | 选中项（居中）的文字颜色   |
| unselectedTextColor         | Color | 未选中项的文字颜色         |
| disabledSelectedTextColor   | Color | 禁用时选中项的文字颜色     |
| disabledUnselectedTextColor | Color | 禁用时未选中项的文字颜色   |

## 进阶用法

### 自定义标签格式

```kotlin
var hour by remember { mutableIntStateOf(9) }

NumberPicker(
    value = hour,
    onValueChange = { hour = it },
    range = 0..23,
    label = { it.toString().padStart(2, '0') }  // "00", "01", ... "23"
)
```

### 时间选择器

组合两个 NumberPicker 创建时间选择器。

```kotlin
var hour by remember { mutableIntStateOf(16) }
var minute by remember { mutableIntStateOf(30) }

Row(
    horizontalArrangement = Arrangement.Center,
    verticalAlignment = Alignment.CenterVertically,
) {
    NumberPicker(
        value = hour,
        onValueChange = { hour = it },
        range = 0..23,
        label = { it.toString().padStart(2, '0') },
        wrapAround = true,
        modifier = Modifier.weight(1f),
    )
    Text(
        text = ":",
        fontWeight = FontWeight.Bold,
    )
    NumberPicker(
        value = minute,
        onValueChange = { minute = it },
        range = 0..59,
        label = { it.toString().padStart(2, '0') },
        wrapAround = true,
        modifier = Modifier.weight(1f),
    )
}
```

### 自定义可见项数

```kotlin
var value by remember { mutableIntStateOf(5) }

NumberPicker(
    value = value,
    onValueChange = { value = it },
    range = 1..100,
    visibleItemCount = 3
)
```

### 自定义颜色

```kotlin
var value by remember { mutableIntStateOf(5) }

NumberPicker(
    value = value,
    onValueChange = { value = it },
    range = 0..10,
    colors = NumberPickerDefaults.colors(
        selectedTextColor = Color.Red,
        unselectedTextColor = Color.Gray
    )
)
```
