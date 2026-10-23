# GlassNavigationBar

`GlassNavigationBar` 是用于顶层目的地的悬浮玻璃导航栏。选中指示器跟随按下与拖动，可选的尾部操作位于导航胶囊之外。选中状态和页面导航由调用方管理。

<div style="position: relative; height: 320px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../../../compose/index.html?id=glassNavigationBar" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

::: warning 背景采样
使用背景采样时，`backdrop` 应记录组件后方的不透明内容，不能包含使用该采样的玻璃表面。配置方式见[玻璃材质](/zh_CN/guide/glass#background-sampling)。
:::

## 引入

```kotlin
import top.yukonga.miuix.kmp.glass.GlassNavigationBar
import top.yukonga.miuix.kmp.glass.GlassNavigationBarDefaults
import top.yukonga.miuix.kmp.glass.GlassNavigationItem
```

## 基本用法

必须提供非空的 `backdrop`，并将导航栏及尾部操作放在该背景采样范围之外。

`homeIcon` 和 `libraryIcon` 是页面提供的 `ImageVector` 图标。根据 `selectedIndex` 显示对应页面：

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
    modifier = Modifier
        .navigationBarsPadding()
        .padding(horizontal = 24.dp, vertical = 12.dp),
)
```

导航栏填满可用宽度，不自动添加系统栏内边距。请提供有界的水平空间，并按需添加外边距和系统栏内边距。各目的地等宽，标签最多两行，可能使导航栏高度增加。

`onSelect` 在指针按下和拖动进入其他目的地时调用，并非只在松手时调用；按下当前目的地也会触发回调。使用 Enter、NumPad Enter 或空格键激活时在按键松开时调用，无障碍激活则直接调用。纯图标项目应提供有意义的 `contentDescription`。

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

`showNavigation` 是调用方持有的 Boolean 状态。隐藏时，整个组合缩小、淡出并模糊；显示时有短暂延迟。隐藏不会移除布局占位。隐藏过渡结束后，目的地交互和语义被禁用，尾部操作不再被放置。

`alpha` 只控制胶囊材质，不影响标签、图标和指示器。请使用 `visible` 隐藏整个组合。

## 属性

### GlassNavigationBar 属性

| 属性名 | 类型 | 说明 | 默认值 | 是否必须 |
| --- | --- | --- | --- | --- |
| items | List\<GlassNavigationItem> | 按顺序排列的目的地 | - | 是 |
| selectedIndex | Int | 当前目的地索引 | - | 是 |
| onSelect | (Int) -> Unit | 指针、键盘和无障碍选择的回调 | - | 是 |
| backdrop | Backdrop | 背景采样源 | - | 是 |
| modifier | Modifier | 应用于胶囊和尾部操作组合的修饰符 | Modifier | 否 |
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

`items` 为空时不渲染任何内容，包括尾部操作。越界的 `selectedIndex` 会在显示时限制到有效范围，不会修改调用方状态。`material = null` 不会移除指示器、描边或阴影。

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

尾部操作自行决定尺寸和材质，只共享整个组合的可见性过渡。此处的 `GlassIconButton` 不继承导航栏材质，因此需要显式传入背景采样源和尺寸。`searchIcon` 是 `ImageVector`，`openSearch` 是操作回调：

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

布局先测量尾部操作，预留其宽度和 `ActionSpacing` 后再测量胶囊。放置位置遵循布局方向。设为 `trailingAction = null` 会同时移除操作和间距；非空但内容为空的插槽仍会预留间距。
