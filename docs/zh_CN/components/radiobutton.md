# RadioButton

`RadioButton` 是 Miuix 中的基础选择组件，支持两种状态：选中和未选中。选中时显示对勾动画指示器，未选中时不显示任何指示器。适用于单选场景，即从一组选项中只能选择一个。

<div style="position: relative; height: 220px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../../compose/index.html?id=radioButton" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

## 引入

```kotlin
import top.yukonga.miuix.kmp.basic.RadioButton
```

## 基本用法

RadioButton 组件通常在互斥选择组中使用：

```kotlin
var selectedIndex by remember { mutableIntStateOf(0) }

Column {
    RadioButtonPreference(
        title = "选项 A",
        selected = selectedIndex == 0,
        onClick = { selectedIndex = 0 }
    )
    RadioButtonPreference(
        title = "选项 B",
        selected = selectedIndex == 1,
        onClick = { selectedIndex = 1 }
    )
}
```

## 组件状态

### 禁用状态

```kotlin
RadioButton(
    selected = true,
    onClick = null,
    enabled = false
)
```

## 属性

### RadioButton 属性

| 属性名    | 类型              | 说明                                              | 默认值                                    | 是否必须 |
| --------- | ----------------- | ------------------------------------------------- | ----------------------------------------- | -------- |
| selected  | Boolean           | 单选按钮是否被选中                                | -                                         | 是       |
| onClick   | (() -> Unit)?     | 点击时的回调；为 `null` 时组件不可交互            | -                                         | 是       |
| modifier  | Modifier          | 应用于单选按钮的修饰符                            | Modifier                                  | 否       |
| colors    | RadioButtonColors | 单选按钮的颜色配置                                | RadioButtonDefaults.radioButtonColors()   | 否       |
| enabled   | Boolean           | 单选按钮是否可交互                                | true                                      | 否       |

### RadioButtonDefaults 对象

RadioButtonDefaults 对象提供了 RadioButton 组件的默认颜色配置。

#### 方法

| 方法名              | 类型              | 说明                       |
| ------------------- | ----------------- | -------------------------- |
| radioButtonColors() | RadioButtonColors | 创建单选按钮的默认颜色配置 |

### RadioButtonColors 类

| 属性名                | 类型  | 说明                     |
| --------------------- | ----- | ------------------------ |
| selectedColor         | Color | 选中状态时对勾颜色       |
| disabledSelectedColor | Color | 禁用且选中状态时对勾颜色 |

## 进阶用法

### 自定义颜色

```kotlin
var selected by remember { mutableStateOf(false) }

RadioButton(
    selected = selected,
    onClick = { selected = !selected },
    colors = RadioButtonDefaults.radioButtonColors(
        selectedColor = Color.Red
    )
)
```

### 在列表中使用

```kotlin
val options = listOf("选项 A", "选项 B", "选项 C")
var selectedIndex by remember { mutableIntStateOf(0) }

Card {
    options.forEachIndexed { index, option ->
        RadioButtonPreference(
            title = option,
            selected = selectedIndex == index,
            onClick = { selectedIndex = index }
        )
    }
}
```
