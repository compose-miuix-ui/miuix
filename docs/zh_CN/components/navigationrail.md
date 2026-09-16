# NavigationRail

`NavigationRail` 是适用于宽屏设备的侧边导航组件，支持固定收起、固定展开和状态控制三种布局。

<div style="position: relative; height: 300px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../../compose/index.html?id=navigationRail" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

## 引入

```kotlin
import top.yukonga.miuix.kmp.basic.NavigationRail
import top.yukonga.miuix.kmp.basic.NavigationRailItem
import top.yukonga.miuix.kmp.basic.rememberNavigationRailState
```

## 基本用法

默认使用固定收起布局：

```kotlin
var selectedIndex by remember { mutableStateOf(0) }
val items = listOf("主页", "个人", "设置")
val icons = listOf(MiuixIcons.VerticalSplit, MiuixIcons.Contacts, MiuixIcons.Settings)

Row {
    NavigationRail {
        items.forEachIndexed { index, label ->
            NavigationRailItem(
                selected = selectedIndex == index,
                onClick = { selectedIndex = index },
                icon = icons[index],
                label = label
            )
        }
    }
    // 内容区域
}
```

## 布局模式

使用固定布局重载，通过 `expanded` 选择布局：

```kotlin
NavigationRail(expanded = true) {
    // NavigationRailItem 内容
}
```

使用状态重载可显示切换按钮，并在两种布局间动画切换：

```kotlin
val railState = rememberNavigationRailState()

NavigationRail(state = railState) {
    // NavigationRailItem(...)
}
```

可以通过 `railState.expand()`、`railState.collapse()` 或 `railState.toggle()` 控制布局。省略
`state` 和 `expanded` 时使用固定收起布局。

## 属性

### NavigationRail 重载

`NavigationRail` 提供两个重载，二选一使用：

#### 固定布局

| 参数 | 类型 | 说明 | 默认值 | 是否必须 |
| ---- | ---- | ---- | ------ | -------- |
| expanded | Boolean | 是否使用展开布局 | false | 否 |

#### 可切换布局

| 参数 | 类型 | 说明 | 默认值 | 是否必须 |
| ---- | ---- | ---- | ------ | -------- |
| state | NavigationRailState | 控制布局并显示切换按钮 | - | 是 |
| expandContentDescription | String | 收起时切换按钮的描述 | NavigationRailDefaults.ExpandContentDescription | 否 |
| collapseContentDescription | String | 展开时切换按钮的描述 | NavigationRailDefaults.CollapseContentDescription | 否 |

### NavigationRail 公共参数

| 属性名                     | 类型                                   | 说明                                          | 默认值                            | 是否必须 |
| -------------------------- | -------------------------------------- | --------------------------------------------- | --------------------------------- | -------- |
| modifier                   | Modifier                               | 应用于 NavigationRail 的修饰符                | Modifier                          | 否       |
| header                     | @Composable (ColumnScope.() -> Unit)?  | 头部内容（通常是 FAB 或 Logo）                | null                              | 否       |
| color                      | Color                                  | NavigationRail 的背景颜色                     | MiuixTheme.colorScheme.surface    | 否       |
| showDivider                | Boolean                                | 是否在 NavigationRail 和内容之间显示分割线    | true                              | 否       |
| defaultWindowInsetsPadding | Boolean                                | 是否对 NavigationRail 应用默认的窗口边距      | true                              | 否       |
| minWidth                   | Dp                                     | NavigationRail 收起时的最小宽度               | NavigationRailDefaults.MinWidth   | 否       |
| expandedWidth              | Dp                                     | NavigationRail 展开时的宽度                   | NavigationRailDefaults.ExpandedWidth | 否    |
| scrollState                | ScrollState                            | 内容列的滚动状态                              | rememberScrollState()             | 否       |
| content                    | @Composable ColumnScope.()             | NavigationRail 的内容                         | -                                 | 是       |

### NavigationRailItem 属性

| 属性名 | 类型        | 说明             | 默认值 | 是否必须 |
| ------ | ----------- | ---------------- | ------ | -------- |
| selected | Boolean     | 是否选中         | -      | 是       |
| onClick  | () -> Unit  | 点击时的回调     | -      | 是       |
| icon     | ImageVector | 该项的图标       | -      | 是       |
| label    | String      | 该项的标签文本   | -      | 是       |
| modifier | Modifier    | 应用于 NavigationRailItem 的修饰符 | Modifier | 否       |
| enabled  | Boolean     | 是否启用该项     | true     | 否       |
| badge    | (@Composable () -> Unit)? | 显示在该项图标上的可选徽章，通常是一个 `Badge` | null | 否 |

### NavigationRailDefaults 对象

NavigationRailDefaults 对象提供了 NavigationRail 和 NavigationRailItem 组件的默认值。

#### 常量

| 常量名                             | 类型     | 说明                         | 默认值 |
| ---------------------------------- | -------- | ---------------------------- | ------ |
| MinWidth                           | Dp       | 导航栏收起时的最小宽度       | 80.dp  |
| ExpandedWidth                      | Dp       | 导航栏展开时的宽度           | 240.dp |
| VerticalPadding                    | Dp       | 内容垂直内边距               | 24.dp  |
| HeaderSpacing                      | Dp       | 头部后的间距                 | 24.dp  |
| IconSize                           | Dp       | 图标尺寸                     | 28.dp  |
| IconTextSpacing                    | Dp       | 图标与文字间距               | 4.dp   |
| ItemVerticalPadding                | Dp       | 每个项目的垂直内边距         | 12.dp  |
| LabelFontSize                      | TextUnit | 标签字号                     | 12.sp  |
| ExpandedLabelFontSize              | TextUnit | 展开时的标签字号             | 16.sp  |
| ExpandedItemHorizontalMargin       | Dp       | 展开项与边缘之间的外边距     | 12.dp  |
| ExpandedItemCornerRadius           | Dp       | 选中药丸的圆角半径           | 16.dp  |
| CollapsedIndicatorVerticalPadding  | Dp       | 收起项指示器围绕图标的内边距 | 4.dp   |
| ExpandedItemContentHorizontalPadding | Dp     | 展开项内部的水平内边距       | 14.dp  |
| ExpandedItemContentVerticalPadding | Dp       | 展开项内部的垂直内边距       | 14.dp  |
| ExpandedItemIconTextSpacing        | Dp       | 展开项图标与文字间距         | 16.dp  |
| ExpandContentDescription           | String   | 收起时切换按钮的无障碍描述   | "Expand navigation rail" |
| CollapseContentDescription         | String   | 展开时切换按钮的无障碍描述   | "Collapse navigation rail" |

### NavigationRailState

通过 `rememberNavigationRailState(initialValue)` 创建，并传入 `NavigationRail` 以让导航栏支持展开/收起。

| 成员         | 类型                | 说明                           |
| ------------ | ------------------- | ------------------------------ |
| currentValue | NavigationRailValue | 当前的展开状态值               |
| isExpanded   | Boolean             | 导航栏当前是否处于展开状态     |
| expand()     | fun                 | 展开导航栏                     |
| collapse()   | fun                 | 收起导航栏                     |
| toggle()     | fun                 | 在收起与展开之间切换           |

### NavigationRailValue

| 值        | 描述                              |
| --------- | --------------------------------- |
| Collapsed | 导航栏收起到 `MinWidth`。         |
| Expanded  | 导航栏展开到 `ExpandedWidth`。    |
