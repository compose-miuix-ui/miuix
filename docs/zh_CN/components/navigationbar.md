# NavigationBar

`NavigationBar` 是 Miuix 中的底部导航栏组件，用于在应用底部创建导航菜单，支持 2 到 5 个导航项，提供不同的显示模式（仅图标、仅文本、图标和文本、仅选中项显示文本）。

`FloatingNavigationBar` 是一个悬浮样式的底部导航栏组件，同样支持 2 到 5 个导航项，提供不同的显示模式。

这些组件通常与 `Scaffold` 组件结合使用，以便在应用程序的不同页面中保持一致的布局和行为。

<div style="position: relative; height: 300px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../../compose/index.html?id=navigationBar" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

## 引入

```kotlin
import top.yukonga.miuix.kmp.basic.NavigationBar
import top.yukonga.miuix.kmp.basic.NavigationBarItem
import top.yukonga.miuix.kmp.basic.FloatingNavigationBar
import top.yukonga.miuix.kmp.basic.FloatingNavigationBarItem
import top.yukonga.miuix.kmp.basic.NavigationBarDisplayMode
import top.yukonga.miuix.kmp.basic.FloatingNavigationBarDisplayMode
```

## 基本用法

### NavigationBar

NavigationBar 组件可用于创建固定在底部的导航菜单：

```kotlin
var selectedIndex by remember { mutableStateOf(0) }
val items = listOf("首页", "我的", "设置")
val icons = listOf(MiuixIcons.VerticalSplit, MiuixIcons.Contacts, MiuixIcons.Settings)

Scaffold(
    bottomBar = {
        NavigationBar {
            items.forEachIndexed { index, label ->
                NavigationBarItem(
                    selected = selectedIndex == index,
                    onClick = { selectedIndex = index },
                    icon = icons[index],
                    label = label
                )
            }
        }
    }
)
```

### FloatingNavigationBar

FloatingNavigationBar 组件可用于创建悬浮在底部的导航菜单：

```kotlin
var selectedIndex by remember { mutableStateOf(0) }
val items = listOf("首页", "我的", "设置")
val icons = listOf(MiuixIcons.VerticalSplit, MiuixIcons.Contacts, MiuixIcons.Settings)

Scaffold(
    bottomBar = {
        FloatingNavigationBar(
            mode = FloatingNavigationBarDisplayMode.IconOnly // 仅显示图标
        ) {
            items.forEachIndexed { index, label ->
                FloatingNavigationBarItem(
                    selected = selectedIndex == index,
                    onClick = { selectedIndex = index },
                    icon = icons[index],
                    label = label
                )
            }
        }
    }
)
```

## 组件状态

### 选中状态

`NavigationBarItem` 和 `FloatingNavigationBarItem` 都会自动处理选中项的视觉样式，选中项将显示粗体文本并高亮图标/文本。

## 属性

### NavigationBar 属性

| 属性名                     | 类型                    | 说明                       | 默认值                                | 是否必须 |
| -------------------------- | ----------------------- | -------------------------- | ------------------------------------- | -------- |
| modifier                   | Modifier                | 应用于导航栏的修饰符       | Modifier                              | 否       |
| color                      | Color                   | 导航栏背景颜色             | MiuixTheme.colorScheme.surface        | 否       |
| showDivider                | Boolean                 | 是否显示顶部分割线         | true                                  | 否       |
| defaultWindowInsetsPadding | Boolean                 | 是否应用默认窗口嵌入边距   | true                                  | 否       |
| mode                       | NavigationBarDisplayMode   | 导航项的显示模式           | NavigationBarDisplayMode.IconAndText     | 否       |
| content                    | @Composable RowScope.() | 导航栏的内容               | -                                     | 是       |

### NavigationBarItem 属性

| 属性名 | 类型        | 说明             | 默认值 | 是否必须 |
| ------ | ----------- | ---------------- | ------ | -------- |
| selected | Boolean     | 是否选中         | -      | 是       |
| onClick  | () -> Unit  | 点击时的回调     | -      | 是       |
| icon     | ImageVector | 图标             | -      | 是       |
| label    | String      | 文本标签         | -      | 是       |
| modifier | Modifier    | 应用于导航项的修饰符 | Modifier | 否       |
| enabled  | Boolean     | 是否启用         | true     | 否       |

### FloatingNavigationBar 属性

| 属性名                     | 类型                      | 说明                               | 默认值                                  | 是否必须 |
| -------------------------- | ------------------------- | ---------------------------------- | --------------------------------------- | -------- |
| modifier                   | Modifier                  | 应用于导航栏的修饰符               | Modifier                                | 否       |
| color                      | Color                     | 导航栏背景颜色                     | MiuixTheme.colorScheme.surfaceContainer | 否       |
| cornerRadius               | Dp                        | 导航栏的圆角半径                   | FloatingToolbarDefaults.CornerRadius    | 否       |
| horizontalAlignment        | Alignment.Horizontal      | 导航栏在其父容器中的水平对齐方式   | CenterHorizontally                      | 否       |
| horizontalOutSidePadding   | Dp                        | 导航栏外部的水平内边距             | FloatingNavigationBarDefaults.HorizontalOutSidePadding | 否       |
| shadowElevation            | Dp                        | 导航栏的阴影高度                   | FloatingNavigationBarDefaults.ShadowElevation          | 否       |
| showDivider                | Boolean                   | 是否显示导航栏周围的分割线         | false                                   | 否       |
| defaultWindowInsetsPadding | Boolean                   | 是否应用默认窗口嵌入边距           | true                                    | 否       |
| mode                       | FloatingNavigationBarDisplayMode | 导航项的显示模式（图标/文本/两者） | FloatingNavigationBarDisplayMode.IconOnly  | 否       |
| content                    | @Composable RowScope.()   | 导航栏的内容                       | -                                       | 是       |

### FloatingNavigationBarItem 属性

| 属性名 | 类型        | 说明             | 默认值 | 是否必须 |
| ------ | ----------- | ---------------- | ------ | -------- |
| selected | Boolean     | 是否选中         | -      | 是       |
| onClick  | () -> Unit  | 点击时的回调     | -      | 是       |
| icon     | ImageVector | 图标             | -      | 是       |
| label    | String      | 文本标签         | -      | 是       |
| modifier | Modifier    | 应用于导航项的修饰符 | Modifier | 否       |
| enabled  | Boolean     | 是否启用         | true     | 否       |

### NavigationBarDefaults 对象

NavigationBarDefaults 对象提供了 NavigationBar 和 NavigationBarItem 组件的默认值。

#### 常量

| 常量名               | 类型     | 说明                       | 默认值 |
| -------------------- | -------- | -------------------------- | ------ |
| ItemHeight           | Dp       | 非 iOS 平台的项目高度      | 64.dp  |
| ItemHeightIOS        | Dp       | iOS 平台的项目高度         | 48.dp  |
| IconSize             | Dp       | 图标尺寸                   | 26.dp  |
| LabelFontSize        | TextUnit | 标签字号                   | 12.sp  |
| IconTopPadding       | Dp       | 图标顶部内边距             | 8.dp   |
| BottomPadding        | Dp       | 标签底部内边距             | 8.dp   |
| SelectedPressedAlpha | Float    | 选中项按压时的透明度       | 0.5f   |
| UnselectedPressedAlpha | Float  | 未选中项按压时的透明度     | 0.6f   |
| UnselectedAlpha      | Float    | 未选中项的透明度           | 0.4f   |

### FloatingNavigationBarDefaults 对象

FloatingNavigationBarDefaults 对象提供了 FloatingNavigationBar 和 FloatingNavigationBarItem 组件的默认值。

#### 常量

| 常量名                   | 类型     | 说明                               | 默认值 |
| ------------------------ | -------- | ---------------------------------- | ------ |
| HorizontalOutSidePadding | Dp      | 外部水平内边距                     | 36.dp  |
| ShadowElevation          | Dp      | 阴影高度                           | 1.dp   |
| HorizontalPadding        | Dp      | 内部水平内边距                     | 12.dp  |
| ItemSpacing              | Dp      | 项目间距                           | 12.dp  |
| IconSize                 | Dp      | 图标尺寸（IconAndText 模式）       | 24.dp  |
| LabelFontSize            | TextUnit| 标签字号（IconAndText 模式）       | 12.sp  |
| VerticalPadding          | Dp      | 垂直内边距（IconAndText 模式）     | 6.dp   |
| TextVerticalPadding      | Dp      | 垂直内边距（TextOnly 模式）        | 16.dp  |
| TextHorizontalPadding    | Dp      | 水平内边距（TextOnly 模式）        | 2.dp   |
| TextFontSize             | TextUnit| 字号（TextOnly 模式）              | 14.sp  |
| IconOnlySize             | Dp      | 图标尺寸（IconOnly 模式）          | 28.dp  |
| IconOnlyPadding          | Dp      | 内边距（IconOnly 模式）            | 10.dp  |
| SelectedPressedAlpha     | Float   | 选中项按压时的透明度               | 0.5f   |
| UnselectedPressedAlpha   | Float   | 未选中项按压时的透明度             | 0.6f   |
| UnselectedAlpha          | Float   | 未选中项的透明度                   | 0.4f   |

### NavigationBarDisplayMode 枚举

| 值                    | 说明                   |
| --------------------- | ---------------------- |
| IconAndText           | 显示图标和文本         |
| IconOnly              | 仅显示图标             |
| TextOnly              | 仅显示文本             |
| IconWithSelectedLabel | 始终显示图标，仅选中时显示文本 |

### FloatingNavigationBarDisplayMode 枚举

| 值          | 说明           |
| ----------- | -------------- |
| IconAndText | 显示图标和文本 |
| IconOnly    | 仅显示图标     |
| TextOnly    | 仅显示文本     |

## 进阶用法

### NavigationBar

#### 自定义颜色

```kotlin
NavigationBar(
    color = Color.Red.copy(alpha = 0.3f)
) {
    // ... items ...
}
```

#### 无分割线

```kotlin
NavigationBar(
    showDivider = false
) {
    // ... items ...
}
```

#### 处理窗口边距

```kotlin
NavigationBar(
    defaultWindowInsetsPadding = false // 自行处理窗口嵌入边距
) {
    // ... items ...
}
```

### FloatingNavigationBar

#### 自定义模式（图标和文本）

```kotlin
FloatingNavigationBar(
    mode = FloatingNavigationBarDisplayMode.IconAndText
) {
    // ... items ...
}
```

#### 自定义模式（仅文本）

```kotlin
FloatingNavigationBar(
    mode = FloatingNavigationBarDisplayMode.TextOnly
) {
    // ... items ...
}
```

#### 自定义颜色和圆角

```kotlin
FloatingNavigationBar(
    color = MiuixTheme.colorScheme.primaryContainer,
    cornerRadius = 28.dp,
    mode = FloatingNavigationBarDisplayMode.IconAndText
) {
    // ... items ...
}
```

#### 自定义对齐和边距

```kotlin
FloatingNavigationBar(
    horizontalAlignment = Alignment.Start, // 左对齐
    horizontalOutSidePadding = 16.dp, // 设置外部边距
    mode = FloatingNavigationBarDisplayMode.IconOnly
) {
    // ... items ...
}
```

### 结合页面切换使用（使用脚手架）

#### 使用 NavigationBar

```kotlin
val pages = listOf("首页", "我的", "设置")
val icons = listOf(MiuixIcons.VerticalSplit, MiuixIcons.Contacts, MiuixIcons.Settings)
var selectedIndex by remember { mutableStateOf(0) }

Scaffold(
    bottomBar = {
        NavigationBar {
            pages.forEachIndexed { index, label ->
                NavigationBarItem(
                    selected = selectedIndex == index,
                    onClick = { selectedIndex = index },
                    icon = icons[index],
                    label = label
                )
            }
        }
    }
) { paddingValues ->
    // 内容区域需要考虑 padding
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "当前页面：${pages[selectedIndex]}",
            style = MiuixTheme.textStyles.title1
        )
    }
}
```

#### 使用 FloatingNavigationBar

```kotlin
val pages = listOf("首页", "我的", "设置")
val icons = listOf(MiuixIcons.VerticalSplit, MiuixIcons.Contacts, MiuixIcons.Settings)
var selectedIndex by remember { mutableStateOf(0) }

Scaffold(
    bottomBar = {
        FloatingNavigationBar(
            mode = FloatingNavigationBarDisplayMode.IconOnly // 仅显示图标
        ) {
            pages.forEachIndexed { index, label ->
                FloatingNavigationBarItem(
                    selected = selectedIndex == index,
                    onClick = { selectedIndex = index },
                    icon = icons[index],
                    label = label
                )
            }
        }
    }
) { paddingValues ->
    // 内容区域需要考虑 padding
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "当前页面：${pages[selectedIndex]}",
            style = MiuixTheme.textStyles.title1
        )
    }
}
```
