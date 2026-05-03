# RadioButtonPreference

`RadioButtonPreference` 是 Miuix 中的单选按钮组件，提供标题、摘要和单选按钮控件。它支持点击交互，常用于单选设置和选择列表。

<div style="position: relative; height: 293px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../../compose/index.html?id=radioButtonPreference" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

## 引入

```kotlin
import top.yukonga.miuix.kmp.preference.RadioButtonPreference
```

## 基本用法

RadioButtonPreference 通常在互斥选择组中使用：

```kotlin
var selectedIndex by remember { mutableIntStateOf(0) }

Card {
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
    RadioButtonPreference(
        title = "选项 C",
        selected = selectedIndex == 2,
        onClick = { selectedIndex = 2 }
    )
}
```

## 带摘要的单选按钮

```kotlin
var selectedIndex by remember { mutableIntStateOf(0) }

RadioButtonPreference(
    title = "选项 A",
    summary = "选项 A 的描述",
    selected = selectedIndex == 0,
    onClick = { selectedIndex = 0 }
)
```

## 组件状态

### 禁用状态

```kotlin
RadioButtonPreference(
    title = "已禁用的单选按钮",
    summary = "此单选按钮当前不可用",
    selected = true,
    onClick = {},
    enabled = false
)
```

## 属性

### RadioButtonPreference 属性

| 属性名            | 类型                            | 说明                         | 默认值                                    | 是否必须 |
| ----------------- | ------------------------------- | ---------------------------- | ----------------------------------------- | -------- |
| title             | String                          | 单选按钮项的标题             | -                                         | 是       |
| selected          | Boolean                         | 单选按钮的选中状态           | -                                         | 是       |
| onClick           | (() -> Unit)?                   | 点击单选按钮时的回调         | -                                         | 是       |
| modifier          | Modifier                        | 应用于组件的修饰符           | Modifier                                  | 否       |
| titleColor        | BasicComponentColors            | 标题文本颜色配置             | BasicComponentDefaults.titleColor()       | 否       |
| summary           | String?                         | 摘要描述                     | null                                      | 否       |
| summaryColor      | BasicComponentColors            | 摘要文本颜色配置             | BasicComponentDefaults.summaryColor()     | 否       |
| radioButtonColors | RadioButtonColors               | 单选按钮控件颜色配置         | RadioButtonDefaults.radioButtonColors()   | 否       |
| endActions        | @Composable RowScope.() -> Unit | 自定义末尾内容               | {}                                        | 否       |
| bottomAction      | @Composable (() -> Unit)?       | 自定义底部内容               | null                                      | 否       |
| holdDownState     | Boolean                         | 组件是否处于按下状态         | false                                     | 否       |
| insideMargin      | PaddingValues                   | 内部内容边距                 | BasicComponentDefaults.InsideMargin       | 否       |
| enabled           | Boolean                         | 组件是否可交互               | true                                      | 否       |

## 进阶用法

### 自定义颜色

```kotlin
var selected by remember { mutableStateOf(false) }

RadioButtonPreference(
    title = "自定义颜色",
    titleColor = BasicComponentDefaults.titleColor(
        color = MiuixTheme.colorScheme.primary
    ),
    summary = "带自定义颜色的单选按钮",
    summaryColor = BasicComponentDefaults.summaryColor(
        color = MiuixTheme.colorScheme.secondary
    ),
    selected = selected,
    onClick = { selected = !selected },
    radioButtonColors = RadioButtonDefaults.radioButtonColors(
        selectedColor = Color.Red
    )
)
```

### 结合对话框使用

```kotlin
var showDialog by remember { mutableStateOf(false) }
var selectedTheme by remember { mutableIntStateOf(0) }
val themes = listOf("浅色", "深色", "跟随系统")

Scaffold {
    ArrowPreference(
        title = "主题设置",
        onClick = { showDialog = true },
        holdDownState = showDialog
    )

    OverlayDialog(
        title = "主题设置",
        show = showDialog,
        onDismissRequest = { showDialog = false }
    ) {
        Card {
            themes.forEachIndexed { index, theme ->
                RadioButtonPreference(
                    title = theme,
                    selected = selectedTheme == index,
                    onClick = { selectedTheme = index }
                )
            }
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(top = 12.dp)
        ) {
            TextButton(
                text = "取消",
                onClick = { showDialog = false },
                modifier = Modifier.weight(1f)
            )
            Spacer(Modifier.width(16.dp))
            TextButton(
                text = "确认",
                onClick = { showDialog = false },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.textButtonColorsPrimary()
            )
        }
    }
}
```
