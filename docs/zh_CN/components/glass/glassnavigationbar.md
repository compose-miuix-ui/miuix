# GlassNavigationBar

`GlassNavigationBar` 是用于主要页面切换的悬浮玻璃导航栏，支持独立尾部操作。选中状态和页面导航由调用方管理。

<div style="position: relative; height: 320px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../../../compose/index.html?id=glassNavigationBar" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

::: warning 背景采样
采样区域应包含不透明背景，并排除玻璃表面自身。见[玻璃材质](/zh_CN/guide/glass#background-sampling)。
:::

## 引入

```kotlin
import top.yukonga.miuix.kmp.glass.GlassNavigationBar
import top.yukonga.miuix.kmp.glass.GlassNavigationBarDefaults
import top.yukonga.miuix.kmp.glass.GlassNavigationItem
```

## 基本用法

将导航栏及尾部操作放在背景采样范围之外。传入 `backdrop = null` 时使用主题的 `surfaceContainer` 填充色。

`homeIcon` 和 `libraryIcon` 是页面提供的 `ImageVector` 图标：

```kotlin
var selectedIndex by remember { mutableIntStateOf(0) }
val items = listOf(
    GlassNavigationItem(homeIcon, label = "首页"),
    GlassNavigationItem(libraryIcon, label = "资料库"),
)

GlassNavigationBar(
    items = items,
    selectedIndex = selectedIndex,
    onSelect = { selectedIndex = it },
    backdrop = backdrop,
    modifier = Modifier.padding(horizontal = 24.dp),
)
```

各导航项等宽排列，标签最多两行。按下和拖动时更新选中项；纯图标项目应提供 `contentDescription`。

## 组件状态

### 显示与隐藏

```kotlin
GlassNavigationBar(
    items = items,
    selectedIndex = selectedIndex,
    onSelect = { selectedIndex = it },
    backdrop = backdrop,
    visible = showNavigation,
)
```

通过 `visible` 显示或隐藏导航栏，`alpha` 用于控制材质透明度。

## 属性

### GlassNavigationBar 属性

| 属性名 | 类型 | 说明 | 默认值 | 是否必须 |
| --- | --- | --- | --- | --- |
| items | List\<GlassNavigationItem> | 按顺序排列的目的地 | - | 是 |
| selectedIndex | Int | 当前目的地索引 | - | 是 |
| onSelect | (Int) -> Unit | 指针、键盘和无障碍选择的回调 | - | 是 |
| backdrop | Backdrop? | 背景采样源；null 时使用主题纯色填充 | - | 是 |
| modifier | Modifier | 应用于胶囊和尾部操作组合的修饰符 | Modifier | 否 |
| defaultWindowInsetsPadding | Boolean | 是否应用默认窗口嵌入边距 | true | 否 |
| style | GlassStyle | 渲染样式 | GlassDefaults.Style | 否 |
| shape | GlassShape | 胶囊形状 | GlassShape(CornerSize(50), CornerSize(50), CornerSize(50), CornerSize(50)) | 否 |
| alpha | Float | 胶囊材质透明度乘数 | 1f | 否 |
| visible | Boolean | 整个组合的目标可见状态 | true | 否 |
| stroke | GlassStroke? | 胶囊描边，null 表示不绘制 | GlassNavigationBarDefaults.stroke() | 否 |
| shadow | GlassShadow? | 胶囊阴影，null 表示不绘制 | GlassShadows.Float | 否 |
| height | Dp | 胶囊最小高度 | GlassNavigationBarDefaults.Height | 否 |
| material | GlassMaterial? | 胶囊主体，null 表示不使用显式材质色层 | GlassNavigationBarDefaults.material() | 否 |
| indicatorColor | Color | 静止时的指示器填充色 | GlassNavigationBarDefaults.indicatorColor() | 否 |
| indicatorPressedColor | Color | 按下时的指示器填充色 | GlassNavigationBarDefaults.indicatorPressedColor() | 否 |
| selectedColor | Color | 选中图标和标签颜色 | MiuixTheme.colorScheme.onBackground | 否 |
| unselectedColor | Color | 未选中图标和标签颜色 | MiuixTheme.colorScheme.onBackground | 否 |
| trailingAction | (@Composable () -> Unit)? | 胶囊外部的独立操作 | null | 否 |

### GlassNavigationItem 属性 {#glassnavigationitem}

| 属性名 | 类型 | 说明 | 默认值 | 是否必须 |
| --- | --- | --- | --- | --- |
| icon | ImageVector | 目的地图标 | - | 是 |
| label | String? | 图标下方的标签 | null | 否 |
| contentDescription | String? | 无障碍描述，未指定时使用 label | null | 否 |

### GlassNavigationBarDefaults 对象 {#glassnavigationbardefaults}

#### 常量

| 常量名 | 类型 | 说明 | 默认值 |
| --- | --- | --- | --- |
| Height | Dp | 胶囊最小高度 | 54.dp |
| ActionSpacing | Dp | 胶囊与尾部操作之间的间距 | 12.dp |
| IconSize | Dp | 目的地图标尺寸 | 28.dp |
| LabelSize | Dp | 按当前密度转换为 sp 的标签字号 | 11.dp |
| LargeLabelSize | Dp | 字体缩放至少为 1.6 时的标签字号 | 16.dp |
| ContentPaddingVertical | Dp | 图标与标签组合的垂直内边距 | 6.dp |
| LabelSpacing | Dp | 图标与标签间距 | 0.dp |
| IndicatorPaddingVertical | Dp | 指示器上下内缩 | 3.dp |
| ContentPaddingHorizontal | Dp | 目的地的水平内缩 | 8.dp |
| IndicatorOverhang | Dp | 指示器在目的地两侧各延伸的距离 | 5.dp |
| PressedAlpha | Float | 按下时的目的地内容透明度 | 0.6f |

#### 方法

| 方法名 | 返回类型 | 说明 |
| --- | --- | --- |
| material() | GlassMaterial | 随主题选择的 `GlassMaterials.puredThinGlass` |
| stroke() | GlassStroke | 浅色模式为 `GlassStrokes.MiddleLight`，深色模式为 `MiddleDark` |
| indicatorColor() | Color | 浅色模式为透明度 0.06 的黑色，深色模式为透明度 0.12 的白色 |
| indicatorPressedColor() | Color | 浅色模式为透明度 0.16 的黑色，深色模式为透明度 0.26 的白色 |

## 进阶用法

### 独立尾部操作

在 `trailingAction` 中传入 `GlassIconButton`，并指定背景采样源和尺寸：

```kotlin
import top.yukonga.miuix.kmp.glass.GlassIconButton

GlassNavigationBar(
    items = items,
    selectedIndex = selectedIndex,
    onSelect = { selectedIndex = it },
    backdrop = backdrop,
    trailingAction = {
        GlassIconButton(
            onClick = openSearch,
            backdrop = backdrop,
            size = GlassNavigationBarDefaults.Height,
        ) {
            Icon(searchIcon, contentDescription = "搜索")
        }
    },
)
```
