# GlassNavigationRail

`GlassNavigationRail` 是面向宽屏布局的悬浮玻璃侧边栏。展开时是位于起始边的面板，按“图标 + 文字”列出目标；折叠后变为顶部居中的纯文字胶囊，带有展开开关，并采用与 `GlassNavigationBar` 相同的拖拽选择交互。

<div style="position: relative; height: 320px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../../../compose/index.html?id=glassNavigationRail" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

::: warning 背景采样
记录一层不含玻璃表面本身的不透明背景，详见[玻璃表面](/zh_CN/guide/glass#background-sampling)。
:::

## 导入

```kotlin
import top.yukonga.miuix.kmp.glass.GlassNavigationItem
import top.yukonga.miuix.kmp.glass.GlassNavigationRail
import top.yukonga.miuix.kmp.glass.GlassNavigationRailDefaults
import top.yukonga.miuix.kmp.glass.rememberGlassNavigationRailState
```

## 基本用法

侧栏是覆盖页面的浮层。下例由父容器统一处理页面前景和侧栏的安全区，并关闭侧栏内部 padding；不透明背景单独铺满录制，不包含侧栏。下文偏移量均相对安全区内的可用区域。

```kotlin
var selectedIndex by remember { mutableIntStateOf(0) }
val railState = rememberGlassNavigationRailState(initialExpanded = true)
val expandedWidth = GlassNavigationRailDefaults.ExpandedWidth
val items = listOf(
    GlassNavigationItem(homeIcon, "Home"),
    GlassNavigationItem(libraryIcon, "Library"),
    GlassNavigationItem(settingsIcon, "Settings"),
)

Box(
    Modifier.fillMaxSize().windowInsetsPadding(
        WindowInsets.systemBars.union(WindowInsets.captionBar).union(WindowInsets.displayCutout),
    ),
) {
    PageContent(
        // 在布局阶段从起始边让出面板位置；背景仍保持铺满。
        Modifier.layout { measurable, constraints ->
            val inset = ((GlassNavigationRailDefaults.Margin + expandedWidth).toPx() *
                railState.progress).roundToInt()
            val placeable = measurable.measure(constraints.offset(horizontal = -inset))
            layout(constraints.maxWidth, placeable.height) { placeable.placeRelative(inset, 0) }
        },
    )
    GlassNavigationRail(
        items = items,
        selectedIndex = selectedIndex,
        onSelect = { selectedIndex = it },
        backdrop = backdrop,
        state = railState,
        expandedWidth = expandedWidth,
        defaultWindowInsetsPadding = false,
    )
}
```

`railState.progress` 的范围为 `0`（折叠）到 `1`（展开）。在布局或绘制阶段读取，可避免动画逐帧触发重组。

展开态使用主题的 `MiuixIndication` 行反馈并支持纵向滚动；折叠态支持横向拖拽选择。形变不会替换目标节点或中断按压。

## 组件状态

### 折叠

```kotlin
val railState = rememberGlassNavigationRailState(initialExpanded = false)
```

折叠后页面恢复完整宽度，胶囊居于顶栏中央。为 `GlassTopAppBar` 设置 `titleAlpha = { railState.progress }` 可隐藏被覆盖的标题。两侧预留空间固定，窄容器或宽操作区可能重叠。

### 无开关

```kotlin
GlassNavigationRail(
    items = items,
    selectedIndex = selectedIndex,
    onSelect = { selectedIndex = it },
    backdrop = backdrop,
    state = railState,
    showToggle = false,
)
```

隐藏内置开关，由调用方自行设置 `railState.expanded`。

## 进阶用法

### 可滚动列表

```kotlin
val scrollState = rememberScrollState()
GlassNavigationRail(
    items = items,
    selectedIndex = selectedIndex,
    onSelect = { selectedIndex = it },
    backdrop = backdrop,
    state = railState,
    scrollState = scrollState,
)
```

面板停稳后，目标列表可以在开关所在的顶部区域下方滚动，表面本身保持不动。

## 属性

### GlassNavigationRail 属性

| 属性名 | 类型 | 说明 | 默认值 | 是否必须 |
| --- | --- | --- | --- | --- |
| items | List\<GlassNavigationItem> | 有序的目标列表 | - | 是 |
| selectedIndex | Int | 当前目标索引 | - | 是 |
| onSelect | (Int) -> Unit | 用户选中目标时回调 | - | 是 |
| backdrop | Backdrop? | 背景采样源；null 时使用主题纯色填充 | - | 是 |
| modifier | Modifier | 应用于浮层容器的修饰符 | Modifier | 否 |
| state | GlassNavigationRailState | 展开状态与形变进度 | rememberGlassNavigationRailState() | 否 |
| secondaryItems | List&lt;GlassNavigationItem&gt; | 展开态下显示在分割线下方的次级目标列表（折叠态隐藏） | emptyList() | 否 |
| showToggle | Boolean | 是否显示内置展开/折叠开关 | true | 否 |
| defaultWindowInsetsPadding | Boolean | 应用系统安全区间距；父容器已处理时关闭 | true | 否 |
| collapsedTop | Dp | 胶囊相对可用区域的顶部偏移 | GlassNavigationRailDefaults.CollapsedTop | 否 |
| expandedWidth | Dp | 展开时的面板宽度 | GlassNavigationRailDefaults.ExpandedWidth | 否 |
| style | GlassStyle | 渲染风格 | GlassDefaults.Style | 否 |
| alpha | Float | 材质不透明度系数 | 1f | 否 |
| material | GlassMaterial? | 表面材质；null 省略显式材质层 | GlassNavigationRailDefaults.material() | 否 |
| stroke | EdgeStroke? | 表面描边；null 移除 | GlassNavigationRailDefaults.stroke() | 否 |
| shadow | GlassShadow? | 表面阴影；null 移除 | GlassShadows.Float | 否 |
| selectedColor | Color | 选中图标与文字颜色 | MiuixTheme.colorScheme.onSurface | 否 |
| unselectedColor | Color | 未选中图标与文字颜色 | MiuixTheme.colorScheme.onSurface | 否 |
| indicatorColor | Color | 指示器常态填充 | GlassNavigationBarDefaults.indicatorColor() | 否 |
| indicatorPressedColor | Color | 折叠胶囊中按下时的指示器填充 | GlassNavigationBarDefaults.indicatorPressedColor() | 否 |
| labelStyle | TextStyle | 目标文字样式，同时决定折叠槽位宽度 | MiuixTheme.textStyles.body1 | 否 |
| expandContentDescription | String | 折叠时开关的无障碍描述 | GlassNavigationRailDefaults.ExpandContentDescription | 否 |
| collapseContentDescription | String | 展开时开关的无障碍描述 | GlassNavigationRailDefaults.CollapseContentDescription | 否 |
| scrollState | ScrollState | 展开列表的滚动状态 | rememberScrollState() | 否 |

### GlassNavigationItem 属性

| 属性名 | 类型 | 说明 | 默认值 | 是否必须 |
| --- | --- | --- | --- | --- |
| icon | ImageVector | 目的地图标 | - | 是 |
| label | String? | 导航项标签 | null | 否 |
| contentDescription | String? | 无障碍描述，未指定时使用 label | null | 否 |
| badge | (@Composable () -> Unit)? | 显示在该项图标上的可选徽章，通常是一个 `Badge` | null | 否 |

### GlassNavigationRailState {#glassnavigationrailstate}

通过 `rememberGlassNavigationRailState(initialExpanded = true)` 创建，可在配置变更后保留。

| 成员 | 类型 | 说明 |
| --- | --- | --- |
| expanded | Boolean | 是否为展开面板；设置后开始形变 |
| progress | Float | 形变位置，`0` 为胶囊、`1` 为面板；请在布局或绘制阶段读取 |

### GlassNavigationRailDefaults 对象 {#glassnavigationraildefaults}

#### 常量

| 常量名 | 类型 | 说明 | 默认值 |
| --- | --- | --- | --- |
| ExpandedWidth | Dp | 展开时的面板宽度 | 250.dp |
| Margin | Dp | 展开面板的起始边与底部间距 | 12.dp |
| CollapsedHeight | Dp | 折叠胶囊高度 | 44.dp |
| CollapsedTop | Dp | 胶囊默认顶部偏移，也是展开面板的顶部间距 | 4.dp |
| ExpandContentDescription | String | 折叠时开关的默认描述 | "Expand navigation rail" |
| CollapseContentDescription | String | 展开时开关的默认描述 | "Collapse navigation rail" |

#### 方法

| 方法名 | 返回类型 | 说明 |
| --- | --- | --- |
| material() | GlassMaterial | 按主题选择的 `GlassMaterials.puredThinGlass` |
| stroke() | EdgeStroke | 浅色为 `GlassEdgeStrokes.MiddleLight`，深色为 `MiddleDark` |
