# SuperBottomSheet

`SuperBottomSheet` 是 Miuix 中的底部抽屉组件，从屏幕底部滑入显示。持拖拽手势关闭和自定义样式。

<div style="position: relative; max-width: 700px; height: 210px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../../compose/index.html?id=superBottomSheet" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

::: warning 注意
`SuperBottomSheet` 需要在 `Scaffold` 组件内使用!
:::

## 引入

```kotlin
import top.yukonga.miuix.kmp.extra.SuperBottomSheet
```

## 基本用法

SuperBottomSheet 组件提供了基础的底部抽屉功能:

```kotlin
var showBottomSheet = remember { mutableStateOf(false) }

Scaffold {
    TextButton(
        text = "显示底部抽屉",
        onClick = { showBottomSheet.value = true }
    )

    SuperBottomSheet(
        show = showBottomSheet,
        title = "底部抽屉标题",
        onDismissRequest = { showBottomSheet.value = false }
    ) {
        Text(text = "这是底部抽屉的内容")
    }
}
```

## 属性

### SuperBottomSheet 属性

| 属性名                     | 类型                            | 说明                         | 默认值                                      | 是否必须 |
| -------------------------- | ------------------------------- | ---------------------------- | ------------------------------------------- | -------- |
| show                       | MutableState\<Boolean>          | 控制底部抽屉显示状态的状态对象 | -                                           | 是       |
| modifier                   | Modifier                        | 应用于底部抽屉的修饰符       | Modifier                                    | 否       |
| title                      | String?                         | 底部抽屉的标题               | null                                        | 否       |
| leftAction                 | @Composable (() -> Unit?)?      | 可选的左侧操作按钮(例如关闭按钮) | null                                     | 否       |
| rightAction                | @Composable (() -> Unit?)?      | 可选的右侧操作按钮(例如提交按钮) | null                                     | 否       |
| backgroundColor            | Color                           | 底部抽屉背景色               | SuperBottomSheetDefaults.backgroundColor()  | 否       |
| enableWindowDim            | Boolean                         | 是否启用遮罩层               | true                                        | 否       |
| cornerRadius               | Dp                              | 顶部圆角半径                 | SuperBottomSheetDefaults.cornerRadius       | 否       |
| sheetMaxWidth              | Dp                              | 底部抽屉的最大宽度           | SuperBottomSheetDefaults.sheetMaxWidth      | 否       |
| onDismissRequest           | (() -> Unit)?                   | 底部抽屉关闭时的回调函数     | null                                        | 否       |
| outsideMargin              | DpSize                          | 底部抽屉外部边距             | SuperBottomSheetDefaults.outsideMargin      | 否       |
| insideMargin               | DpSize                          | 底部抽屉内部内容的边距       | SuperBottomSheetDefaults.insideMargin       | 否       |
| defaultWindowInsetsPadding | Boolean                         | 是否应用默认窗口插入内边距   | true                                        | 否       |
| dragHandleColor            | Color                           | 拖拽指示器的颜色             | SuperBottomSheetDefaults.dragHandleColor()  | 否       |
| allowDismiss               | Boolean                         | 是否允许通过拖拽或返回手势关闭抽屉 | true                                  | 否       |
| content                    | @Composable () -> Unit          | 底部抽屉的内容               | -                                           | 是       |

### SuperBottomSheetDefaults 对象

SuperBottomSheetDefaults 对象提供了 SuperBottomSheet 组件的默认设置。

#### 属性

| 属性名        | 类型   | 说明                        |
| ------------- | ------ | --------------------------- |
| cornerRadius  | Dp     | 默认圆角半径 (28.dp)        |
| sheetMaxWidth | Dp     | 默认最大宽度 (640.dp)       |
| outsideMargin | DpSize | 底部抽屉外部默认边距        |
| insideMargin  | DpSize | 底部抽屉内部默认边距        |

#### 函数

| 函数名            | 返回类型 | 说明                       |
| ----------------- | -------- | -------------------------- |
| backgroundColor() | Color    | 获取默认背景颜色           |
| dragHandleColor() | Color    | 获取默认拖拽指示器颜色     |

## 进阶用法

### 自定义样式底部抽屉

```kotlin
var showBottomSheet = remember { mutableStateOf(false) }

Scaffold {
    TextButton(
        text = "显示自定义样式底部抽屉",
        onClick = { showBottomSheet.value = true }
    )

    SuperBottomSheet(
        show = showBottomSheet,
        title = "自定义样式",
        backgroundColor = MiuixTheme.colorScheme.surfaceVariant,
        dragHandleColor = MiuixTheme.colorScheme.primary,
        outsideMargin = DpSize(16.dp, 0.dp),
        insideMargin = DpSize(32.dp, 16.dp),
        onDismissRequest = { showBottomSheet.value = false }
    ) {
        Column {
            Text("自定义样式的底部抽屉")
            Spacer(modifier = Modifier.height(16.dp))
            TextButton(
                text = "关闭",
                onClick = { showBottomSheet.value = false },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
```

### 带列表内容的底部抽屉

```kotlin
var showBottomSheet = remember { mutableStateOf(false) }
var selectedItem by remember { mutableStateOf("") }

Scaffold {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        TextButton(
            text = "显示选择列表",
            onClick = { showBottomSheet.value = true }
        )
        
        Text("已选择: $selectedItem")
    }

    SuperBottomSheet(
        show = showBottomSheet,
        title = "选择项目",
        onDismissRequest = { showBottomSheet.value = false }
    ) {
        LazyColumn {
            items(20) { index ->
                Text(
                    text = "项目 ${index + 1}",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            selectedItem = "项目 ${index + 1}"
                            showBottomSheet.value = false
                        }
                        .padding(vertical = 12.dp)
                )
            }
        }
    }
}
```

### 不使用遮罩层

```kotlin
var showBottomSheet = remember { mutableStateOf(false) }

Scaffold {
    TextButton(
        text = "显示无遮罩底部抽屉",
        onClick = { showBottomSheet.value = true }
    )

    SuperBottomSheet(
        show = showBottomSheet,
        title = "无遮罩层",
        enableWindowDim = false,
        onDismissRequest = { showBottomSheet.value = false }
    ) {
        Text("这个底部抽屉没有背景遮罩层")
        Spacer(modifier = Modifier.height(16.dp))
        TextButton(
            text = "关闭",
            onClick = { showBottomSheet.value = false },
            modifier = Modifier.fillMaxWidth()
        )
    }
}
```

### 带操作按钮的底部抽屉

```kotlin
var showBottomSheet = remember { mutableStateOf(false) }

Scaffold {
    TextButton(
        text = "显示带操作按钮的底部抽屉",
        onClick = { showBottomSheet.value = true }
    )

    SuperBottomSheet(
        show = showBottomSheet,
        title = "操作面板",
        leftAction = {
            TextButton(
                text = "取消",
                onClick = { showBottomSheet.value = false }
            )
        },
        rightAction = {
            TextButton(
                text = "确认",
                onClick = { 
                    // 处理确认操作
                    showBottomSheet.value = false 
                },
                colors = ButtonDefaults.textButtonColorsPrimary()
            )
        },
        onDismissRequest = { showBottomSheet.value = false }
    ) {
        Text("带有自定义标题栏操作按钮的内容")
        Spacer(modifier = Modifier.height(16.dp))
        Text("左右两侧的操作按钮显示在标题栏中")
    }
}
```

### 带表单的底部抽屉

```kotlin
var showBottomSheet = remember { mutableStateOf(false) }
var textFieldValue by remember { mutableStateOf("") }
var switchState by remember { mutableStateOf(false) }

Scaffold {
    TextButton(
        text = "显示表单底部抽屉",
        onClick = { showBottomSheet.value = true }
    )

    SuperBottomSheet(
        show = showBottomSheet,
        title = "设置表单",
        onDismissRequest = { showBottomSheet.value = false }
    ) {
        Card(
            color = MiuixTheme.colorScheme.secondaryContainer,
        ) {
            TextField(
                modifier = Modifier.padding(vertical = 12.dp),
                value = textFieldValue,
                label = "输入内容",
                maxLines = 1,
                onValueChange = { textFieldValue = it }
            )
            
            SuperSwitch(
                title = "开关选项",
                checked = switchState,
                onCheckedChange = { switchState = it }
            )
        }
        
        Spacer(Modifier.height(12.dp))
        
        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextButton(
                text = "取消",
                onClick = { showBottomSheet.value = false },
                modifier = Modifier.weight(1f)
            )
            Spacer(Modifier.width(20.dp))
            TextButton(
                text = "确认",
                onClick = { showBottomSheet.value = false },
                modifier = Modifier.weight(1f),
                colors = ButtonDefaults.textButtonColorsPrimary()
            )
        }
    }
}
```

### 自适应内容高度

```kotlin
var showBottomSheet = remember { mutableStateOf(false) }

Scaffold {
    TextButton(
        text = "显示自适应高度底部抽屉",
        onClick = { showBottomSheet.value = true }
    )

    SuperBottomSheet(
        show = showBottomSheet,
        title = "自适应高度",
        onDismissRequest = { showBottomSheet.value = false }
    ) {
        Column(modifier = Modifier.padding(vertical = 16.dp)) {
            Text("高度会自动适应内容")
            Spacer(modifier = Modifier.height(16.dp))
            Text("可以添加任意多的内容")
            Spacer(modifier = Modifier.height(16.dp))
            Text("但不会覆盖到状态栏区域")
            Spacer(modifier = Modifier.height(16.dp))
            TextButton(
                text = "关闭",
                onClick = { showBottomSheet.value = false },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
```

### 禁止关闭的底部抽屉

```kotlin
var showBottomSheet = remember { mutableStateOf(false) }

Scaffold {
    TextButton(
        text = "显示禁止关闭的底部抽屉",
        onClick = { showBottomSheet.value = true }
    )

    SuperBottomSheet(
        show = showBottomSheet,
        title = "禁止关闭",
        allowDismiss = false,
        onDismissRequest = { showBottomSheet.value = false }
    ) {
        Column(modifier = Modifier.padding(vertical = 16.dp)) {
            Text("这个底部抽屉无法通过拖拽或返回手势关闭")
            Spacer(modifier = Modifier.height(16.dp))
            Text("您必须通过下面的按钮显式关闭它")
            Spacer(modifier = Modifier.height(16.dp))
            TextButton(
                text = "关闭",
                onClick = { showBottomSheet.value = false },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
```
