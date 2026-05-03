# NavigationRail

`NavigationRail` 是 Miuix 中的侧边导航组件，适用于宽屏设备。提供不同的显示模式（仅图标、仅文本、图标和文本、仅选中项显示文本）。

<div style="position: relative; height: 300px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../../compose/index.html?id=navigationRail" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

## 引入

```kotlin
import top.yukonga.miuix.kmp.basic.NavigationRail
import top.yukonga.miuix.kmp.basic.NavigationRailItem
import top.yukonga.miuix.kmp.basic.NavigationRailDisplayMode
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

## 组件状态

### 选中状态

`NavigationRailItem` 都会自动处理选中项的视觉样式，选中项将显示粗体文本并高亮图标/文本。

## 属性

### NavigationRail 属性

| 属性名                     | 类型                                   | 说明                                          | 默认值                            | 是否必须 |
| -------------------------- | -------------------------------------- | --------------------------------------------- | --------------------------------- | -------- |
| modifier                   | Modifier                               | 应用于 NavigationRail 的修饰符                | Modifier                          | 否       |
| header                     | @Composable (ColumnScope.() -> Unit)?  | 头部内容（通常是 FAB 或 Logo）                | null                              | 否       |
| color                      | Color                                  | NavigationRail 的背景颜色                     | MiuixTheme.colorScheme.surface    | 否       |
| showDivider                | Boolean                                | 是否在 NavigationRail 和内容之间显示分割线    | true                              | 否       |
| defaultWindowInsetsPadding | Boolean                                | 是否对 NavigationRail 应用默认的窗口边距      | true                              | 否       |
| minWidth                   | Dp                                     | NavigationRail 的最小宽度                     | NavigationRailDefaults.MinWidth   | 否       |
| mode                       | NavigationRailDisplayMode                  | 项目的显示模式                                | NavigationRailDisplayMode.IconAndText | 否       |
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

### NavigationRailDefaults 对象

NavigationRailDefaults 对象提供了 NavigationRail 和 NavigationRailItem 组件的默认值。

#### 常量

| 常量名                 | 类型     | 说明                         | 默认值 |
| ---------------------- | -------- | ---------------------------- | ------ |
| MinWidth               | Dp       | 导航栏最小宽度               | 80.dp  |
| VerticalPadding        | Dp       | 内容垂直内边距               | 24.dp  |
| HeaderSpacing          | Dp       | 头部后的间距                 | 24.dp  |
| IconSize               | Dp       | 图标尺寸                     | 28.dp  |
| IconTextSpacing        | Dp       | 图标与文字间距               | 4.dp   |
| ItemVerticalPadding    | Dp       | 每个项目的垂直内边距         | 12.dp  |
| LabelFontSize          | TextUnit | 标签字号                     | 12.sp  |
| TextOnlyFontSize       | TextUnit | TextOnly 模式字号            | 14.sp  |
| TextOnlyVerticalPadding| Dp       | TextOnly 模式垂直内边距      | 4.dp   |
| SelectedPressedAlpha   | Float    | 选中项按压时的透明度         | 0.5f   |
| UnselectedPressedAlpha | Float    | 未选中项按压时的透明度       | 0.6f   |
| UnselectedAlpha        | Float    | 未选中项的透明度             | 0.4f   |

### NavigationRailDisplayMode

| 值                    | 描述                                    |
| --------------------- | --------------------------------------- |
| IconAndText           | 同时显示图标和文本。                    |
| IconOnly              | 仅显示图标。                            |
| TextOnly              | 仅显示文本。                            |
| IconWithSelectedLabel | 始终显示图标，仅在选中时显示文本。      |
