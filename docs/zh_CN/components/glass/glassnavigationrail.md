# GlassNavigationRail

`GlassNavigationRail` 是面向宽屏布局的悬浮玻璃侧边栏。展开时是位于起始边的一整块面板，按“图标 + 文字”列出目标；折叠后它移动到顶部居中位置，变成 `GlassNavigationBar`——只显示文字，展开开关占据它的前导槽位——因此胶囊的导航方式与悬浮底栏完全一致。

<div style="position: relative; height: 320px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../../compose/index.html?id=glassnavigationRail" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

::: warning 背景采样
记录一层不含玻璃表面本身的不透明背景，详见[玻璃表面](/zh_CN/guide/glass#背景采样)。
:::

## 导入

```kotlin
import top.yukonga.miuix.kmp.glass.GlassNavigationItem
import top.yukonga.miuix.kmp.glass.GlassNavigationRail
import top.yukonga.miuix.kmp.glass.GlassNavigationRailDefaults
```

## 基本用法

侧边栏是一个浮层：它铺满被给定的空间，在其中绘制自身表面。请把它放在页面之上、背景录制之外；若页面内容需要避开面板，在页面自身的布局里预留展开宽度。

```kotlin
var selectedIndex by remember { mutableIntStateOf(0) }
var expanded by rememberSaveable { mutableStateOf(true) }
val items = listOf(
    GlassNavigationItem(homeIcon, "Home"),
    GlassNavigationItem(libraryIcon, "Library"),
    GlassNavigationItem(settingsIcon, "Settings"),
)

Box(Modifier.fillMaxSize()) {
    PageContent(Modifier.padding(start = if (expanded) 274.dp else 0.dp))
    GlassNavigationRail(
        items = items,
        selectedIndex = selectedIndex,
        onSelect = { selectedIndex = it },
        backdrop = backdrop,
        expanded = expanded,
        onExpandedChange = { expanded = it },
    )
}
```

折叠是一次连续的运动：每一行从所在列的位置飞向它在导航栏那一行中的槽位，图标同时淡出。表面本身走直角而不是斜线——折叠时先在起始边原地收缩、再横移到顶部居中；展开时先横移回去、再生长。形变进度只在布局与绘制阶段读取，因此形变的每一帧都只移动行、不重组行内容。

折叠并停稳后，侧边栏**就是** `GlassNavigationBar`（`labelsOnly = true`）。点击、横向拖拽、选中指示器与无障碍语义都由底栏自身提供：拖拽会预览指针下的目标、松手时提交；当文字需要的宽度超过窗口时，胶囊会按比例压缩各槽位。

## 组件状态

### 折叠

```kotlin
GlassNavigationRail(
    items = items,
    selectedIndex = selectedIndex,
    onSelect = { selectedIndex = it },
    backdrop = backdrop,
    expanded = false,
    onExpandedChange = { expanded = it },
)
```

折叠时侧边栏只占顶部居中的胶囊，页面可使用整行宽度。

### 不显示开关

```kotlin
GlassNavigationRail(
    items = items,
    selectedIndex = selectedIndex,
    onSelect = { selectedIndex = it },
    backdrop = backdrop,
    expanded = expanded,
)
```

传入 `onExpandedChange = null` 可隐藏内置开关，由调用方自行控制 `expanded`。

## 进阶用法

### 可滚动列

```kotlin
val scrollState = rememberScrollState()
GlassNavigationRail(
    items = items,
    selectedIndex = selectedIndex,
    onSelect = { selectedIndex = it },
    backdrop = backdrop,
    expanded = expanded,
    onExpandedChange = { expanded = it },
    scrollState = scrollState,
)
```

展开后的列可滚动，表面本身保持不动。

## 属性

### GlassNavigationRail 属性

| 属性名 | 类型 | 描述 | 默认值 | 必填 |
| --- | --- | --- | --- | --- |
| items | List\<GlassNavigationItem> | 有序的目标列表 | - | 是 |
| selectedIndex | Int | 当前目标索引 | - | 是 |
| onSelect | (Int) -> Unit | 底栏指针、键盘与无障碍选择回调 | - | 是 |
| backdrop | Backdrop? | 背景采样源；null 时使用主题纯色填充 | - | 是 |
| modifier | Modifier | 应用于浮层的修饰符 | Modifier | 否 |
| expanded | Boolean | 侧边栏是面板还是顶部胶囊 | true | 否 |
| onExpandedChange | ((Boolean) -> Unit)? | 内置开关的回调；null 时隐藏开关 | null | 否 |
| defaultWindowInsetsPadding | Boolean | 让表面避让系统栏与显示缺口 | true | 否 |
| expandedWidth | Dp | 展开时的面板宽度 | GlassNavigationRailDefaults.ExpandedWidth | 否 |
| style | GlassStyle | 渲染样式 | GlassDefaults.Style | 否 |
| alpha | Float | 材质不透明度乘数 | 1f | 否 |
| material | GlassMaterial? | 表面本体；null 省略显式材质层 | GlassNavigationRailDefaults.material() | 否 |
| stroke | EdgeStroke? | 表面描边；null 移除 | GlassNavigationRailDefaults.stroke() | 否 |
| shadow | GlassShadow? | 表面阴影；null 移除 | GlassShadows.Float | 否 |
| selectedColor | Color | 选中图标与文字的颜色 | 动态取色时为主题 primary，否则 onSurface | 否 |
| unselectedColor | Color | 未选中图标与文字的颜色 | MiuixTheme.colorScheme.onSurface | 否 |
| indicatorColor | Color | 常态指示器填充 | GlassNavigationBarDefaults.indicatorColor() | 否 |
| indicatorPressedColor | Color | 折叠胶囊按压时的指示器填充 | GlassNavigationBarDefaults.indicatorPressedColor() | 否 |
| labelStyle | TextStyle | 目标文字样式；同时决定折叠槽位的宽度 | MiuixTheme.textStyles.body1 | 否 |
| expandContentDescription | String | 折叠状态下开关的描述 | GlassNavigationRailDefaults.ExpandContentDescription | 否 |
| collapseContentDescription | String | 展开状态下开关的描述 | GlassNavigationRailDefaults.CollapseContentDescription | 否 |
| scrollState | ScrollState | 展开列的滚动状态 | rememberScrollState() | 否 |

### GlassNavigationRailDefaults 对象 {#glassnavigationraildedaults}

#### 常量

| 常量名 | 类型 | 描述 | 默认值 |
| --- | --- | --- | --- |
| ExpandedWidth | Dp | 展开时的面板宽度 | 250.dp |
| Margin | Dp | 面板与窗口边缘的距离 | 12.dp |
| CornerRadius | Dp | 展开面板的圆角 | 22.dp |
| IconSize | Dp | 目标图标尺寸 | 24.dp |
| IconColumnWidth | Dp | 图标列宽度（图标加间距） | 36.dp |
| ItemContentPadding | Dp | 目标内部水平内边距 | 14.dp |
| CollapsedRowHeight | Dp | 折叠时的行高 | 38.dp |
| ExpandedRowGrowth | Dp | 展开过程中行高增量 | 4.dp |
| RowPitch | Dp | 展开时两行之间的距离 | 46.dp |
| RowTop | Dp | 折叠时的行顶部位置 | 3.dp |
| ExpandedRowTop | Dp | 展开时第一行的顶部位置 | 64.dp |
| ExpandedToggleTop | Dp | 展开时开关的顶部位置 | 12.dp |
| CollapsedToggleStart | Dp | 折叠时开关的起始位置，与底栏前导操作对齐 | 2.dp |
| ExpandedToggleStart | Dp | 展开时开关的起始位置 | 16.dp |
| ToggleSize | Dp | 展开时开关的直径 | 44.dp |
| CollapsedToggleSpacing | Dp | 折叠时开关与第一个目标的间距 | 4.dp |
| LeadingActionWidth | Dp | 折叠胶囊内为开关预留的宽度 | 48.dp |
| ItemHorizontalMargin | Dp | 展开行的水平外边距 | 12.dp |
| CollapsedEdgePadding | Dp | 胶囊内最后一个目标之后的内边距 | 3.dp |
| CollapsedContentStart | Dp | 折叠槽位的起点，即底栏内容内边距加其前导操作宽度 | 56.dp |
| CollapsedTopMargin | Dp | 胶囊顶部位置（相对安全区） | 6.dp |
| ExpandContentDescription | String | 折叠状态下的默认开关描述 | "Expand navigation rail" |
| CollapseContentDescription | String | 展开状态下的默认开关描述 | "Collapse navigation rail" |

#### 方法

| 方法名 | 返回类型 | 描述 |
| --- | --- | --- |
| material() | GlassMaterial | 按主题选择的 `GlassMaterials.puredThinGlass` |
| stroke() | EdgeStroke | 浅色模式为 `GlassEdgeStrokes.MiddleLight`，深色模式为 `MiddleDark` |
