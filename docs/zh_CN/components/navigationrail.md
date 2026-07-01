# NavigationRail

`NavigationRail` 是 Miuix 中的侧边导航组件，适用于宽屏设备。提供不同的显示模式（仅图标、图标和文本、仅选中项显示文本）。

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

NavigationRail 组件可用于创建侧边导航菜单：

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

## 可展开导航栏

传入一个 `NavigationRailState`（通过 `rememberNavigationRailState` 创建）即可让导航栏支持展开/收起。导航栏左上角会显示一个内建的切换按钮，点击后导航栏会在收起宽度与 `expandedWidth` 之间做动画切换。展开后，每个项目会变为「图标 + 文字」横排布局，并在选中项后方绘制高亮药丸。

```kotlin
var selectedIndex by remember { mutableStateOf(0) }
val items = listOf("主页", "个人", "设置")
val icons = listOf(MiuixIcons.VerticalSplit, MiuixIcons.Contacts, MiuixIcons.Settings)
val railState = rememberNavigationRailState()

Row {
    NavigationRail(state = railState) {
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

你也可以通过 `railState.expand()`、`railState.collapse()` 或 `railState.toggle()` 以编程方式控制状态。当 `state` 保持为 `null`（默认值）时，导航栏保持经典的不可展开布局，且不显示切换按钮。

## 组件状态

### 选中状态

`NavigationRailItem` 都会自动处理选中项的视觉样式，选中项将显示粗体文本并高亮图标/文本。

## 属性

### NavigationRail 属性

| 属性名                     | 类型                                   | 说明                                          | 默认值                            | 是否必须 |
| -------------------------- | -------------------------------------- | --------------------------------------------- | --------------------------------- | -------- |
| modifier                   | Modifier                               | 应用于 NavigationRail 的修饰符                | Modifier                          | 否       |
| state                      | NavigationRailState?                   | 展开/收起状态；非空时导航栏可展开             | null                              | 否       |
| header                     | @Composable (ColumnScope.() -> Unit)?  | 头部内容（通常是 FAB 或 Logo）                | null                              | 否       |
| color                      | Color                                  | NavigationRail 的背景颜色                     | MiuixTheme.colorScheme.surface    | 否       |
| showDivider                | Boolean                                | 是否在 NavigationRail 和内容之间显示分割线    | true                              | 否       |
| defaultWindowInsetsPadding | Boolean                                | 是否对 NavigationRail 应用默认的窗口边距      | true                              | 否       |
| minWidth                   | Dp                                     | NavigationRail 收起时的最小宽度               | NavigationRailDefaults.MinWidth   | 否       |
| expandedWidth              | Dp                                     | NavigationRail 展开时的宽度                   | NavigationRailDefaults.ExpandedWidth | 否    |
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
