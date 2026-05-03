# Checkbox

`Checkbox` 是 Miuix 中的基础选择组件，支持三种状态：选中、未选中和半选中。它提供了具有动画效果的交互式选择控件，适用于多选场景和配置项的启用与禁用。

<div style="position: relative; height: 100px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../../compose/index.html?id=checkbox" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

## 引入

```kotlin
import top.yukonga.miuix.kmp.basic.Checkbox
import androidx.compose.ui.state.ToggleableState
```

## 基本用法

Checkbox 组件可以用于创建可选择的选项：

```kotlin
var state by remember { mutableStateOf(ToggleableState.Off) }

Checkbox(
    state = state,
    onClick = { state = if (state == ToggleableState.On) ToggleableState.Off else ToggleableState.On }
)
```

## 组件状态

### 禁用状态

```kotlin
Checkbox(
    state = ToggleableState.Off,
    onClick = null,
    enabled = false
)
```

### 半选中状态

半选中状态通常用于父复选框中，表示子复选框仅部分被选中。处于半选中时，显示一条横线而非对勾。

```kotlin
var parentState by remember { mutableStateOf(ToggleableState.Indeterminate) }

// 循环切换：Off → Indeterminate → On → Off
Checkbox(
    state = parentState,
    onClick = {
        parentState = when (parentState) {
            ToggleableState.Off -> ToggleableState.Indeterminate
            ToggleableState.Indeterminate -> ToggleableState.On
            ToggleableState.On -> ToggleableState.Off
        }
    }
)
```

## 属性

### Checkbox 属性

| 属性名    | 类型              | 说明                                              | 默认值                            | 是否必须 |
| --------- | ----------------- | ------------------------------------------------- | --------------------------------- | -------- |
| state     | ToggleableState   | 复选框当前状态（On、Off 或 Indeterminate）        | -                                 | 是       |
| onClick   | (() -> Unit)?     | 点击时的回调；为 `null` 时组件不可交互            | -                                 | 是       |
| modifier  | Modifier          | 应用于复选框的修饰符                              | Modifier                          | 否       |
| colors    | CheckboxColors    | 复选框的颜色配置                                  | CheckboxDefaults.checkboxColors() | 否       |
| enabled   | Boolean           | 复选框是否可交互                                  | true                              | 否       |

### CheckboxDefaults 对象

CheckboxDefaults 对象提供了 Checkbox 组件的默认颜色配置。

#### 方法

| 方法名           | 类型           | 说明                     |
| ---------------- | -------------- | ------------------------ |
| checkboxColors() | CheckboxColors | 创建复选框的默认颜色配置 |

### CheckboxColors 类

| 属性名                           | 类型  | 说明                         |
| -------------------------------- | ----- | ---------------------------- |
| checkedForegroundColor           | Color | 选中状态时前景色（对勾颜色） |
| uncheckedForegroundColor         | Color | 未选中状态时前景色           |
| disabledCheckedForegroundColor   | Color | 禁用且选中状态时前景色       |
| disabledUncheckedForegroundColor | Color | 禁用且未选中状态时前景色     |
| checkedBackgroundColor           | Color | 选中状态时背景色             |
| uncheckedBackgroundColor         | Color | 未选中状态时背景色           |
| disabledCheckedBackgroundColor   | Color | 禁用且选中状态时背景色       |
| disabledUncheckedBackgroundColor | Color | 禁用且未选中状态时背景色     |

> 半选中状态与选中状态共用同一套颜色。

## 进阶用法

### 自定义颜色

```kotlin
var state by remember { mutableStateOf(ToggleableState.Off) }

Checkbox(
    state = state,
    onClick = { state = if (state == ToggleableState.On) ToggleableState.Off else ToggleableState.On },
    colors = CheckboxDefaults.checkboxColors(
        checkedBackgroundColor = Color.Red,
        checkedForegroundColor = Color.White
    )
)
```

### 父子复选框（半选中模式）

```kotlin
val childStates = remember { mutableStateListOf(false, true, false) }
val parentState by remember {
    derivedStateOf {
        when {
            childStates.all { it } -> ToggleableState.On
            childStates.none { it } -> ToggleableState.Off
            else -> ToggleableState.Indeterminate
        }
    }
}

Column {
    Checkbox(
        state = parentState,
        onClick = {
            val newValue = parentState != ToggleableState.On
            childStates.indices.forEach { childStates[it] = newValue }
        }
    )
    childStates.forEachIndexed { index, checked ->
        Checkbox(
            state = ToggleableState(checked),
            onClick = { childStates[index] = !childStates[index] }
        )
    }
}
```

### 结合文本使用

```kotlin
var state by remember { mutableStateOf(ToggleableState.Off) }

Row(
    verticalAlignment = Alignment.CenterVertically,
    modifier = Modifier.padding(16.dp)
) {
    Checkbox(
        state = state,
        onClick = { state = if (state == ToggleableState.On) ToggleableState.Off else ToggleableState.On }
    )
    Spacer(modifier = Modifier.width(8.dp))
    Text(text = "接受用户协议与隐私政策")
}
```

### 在列表中使用

```kotlin
val options = listOf("选项 A", "选项 B", "选项 C")
val checkedStates = remember { mutableStateListOf(false, true, false) }

LazyColumn {
    items(options.size) { index ->
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                state = ToggleableState(checkedStates[index]),
                onClick = { checkedStates[index] = !checkedStates[index] }
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = options[index])
        }
    }
}
```
