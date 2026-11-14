# GlassNavigationRail

`GlassNavigationRail` 是面向宽屏布局的悬浮玻璃侧边栏。展开时是位于起始边的一整块面板，按“图标 + 文字”列出目标；折叠后它移动到顶部居中位置，变成只显示文字的 `GlassNavigationBar`，展开开关占据它的前导槽位，因此胶囊的导航方式与悬浮底栏完全一致。

<div style="position: relative; height: 320px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../../../compose/index.html?id=glassNavigationRail" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

::: warning 背景采样
记录一层不含玻璃表面本身的不透明背景，详见[玻璃表面](/zh_CN/guide/glass#背景采样)。
:::

## 导入

```kotlin
import top.yukonga.miuix.kmp.glass.GlassNavigationItem
import top.yukonga.miuix.kmp.glass.GlassNavigationRail
import top.yukonga.miuix.kmp.glass.GlassNavigationRailDefaults
import top.yukonga.miuix.kmp.glass.rememberGlassNavigationRailState
```

## 基本用法

侧边栏是一个浮层：它铺满被给定的空间，在其中绘制自身表面。请把它放在页面之上、背景录制之外。

```kotlin
var selectedIndex by remember { mutableIntStateOf(0) }
val railState = rememberGlassNavigationRailState(initialExpanded = true)
val items = listOf(
    GlassNavigationItem(homeIcon, "Home"),
    GlassNavigationItem(libraryIcon, "Library"),
    GlassNavigationItem(settingsIcon, "Settings"),
)

Box(Modifier.fillMaxSize()) {
    PageContent(
        // 在布局阶段按侧边栏自身的进度让出面板位置。
        Modifier.layout { measurable, constraints ->
            val inset = ((GlassNavigationRailDefaults.Margin + GlassNavigationRailDefaults.ExpandedWidth).toPx() *
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
    )
}
```

折叠是一次连续的运动：每一行从所在列的位置飞向它在导航栏那一行中的槽位，图标同时淡出；表面收缩、上移并横移到顶部居中，末尾带一次轻微回弹。`railState.progress` 是形变位置，`0` 为胶囊、`1` 为面板。请像上例一样在布局或绘制阶段读取它，让页面跟随面板而不触发重组。

折叠并停稳后，侧边栏**就是**只显示文字的 `GlassNavigationBar`。点击、横向拖拽、选中指示器与无障碍语义都由底栏自身提供：拖拽会预览指针下的目标，松手时提交。

## 组件状态

### 折叠

```kotlin
val railState = rememberGlassNavigationRailState(initialExpanded = false)
```

折叠时侧边栏只占据顶栏中线上居中的胶囊，页面可以使用全部宽度。胶囊占据了顶栏折叠标题的位置，此时应隐藏该标题，`GlassTopAppBar` 可传入 `titleAlpha = { railState.progress }`。胶囊会在两侧为顶栏的导航图标和操作按钮留出空间，文字所需宽度超过可用空间时按比例压缩各槽位。

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
| showToggle | Boolean | 是否显示内置展开/折叠开关 | true | 否 |
| defaultWindowInsetsPadding | Boolean | 是否让表面避开系统栏、标题栏与刘海 | true | 否 |
| collapsedTop | Dp | 折叠胶囊相对安全区域的顶部位置 | GlassNavigationRailDefaults.CollapsedTop | 否 |
| expandedWidth | Dp | 展开时的面板宽度 | GlassNavigationRailDefaults.ExpandedWidth | 否 |
| style | GlassStyle | 渲染风格 | GlassDefaults.Style | 否 |
| alpha | Float | 材质不透明度系数 | 1f | 否 |
| material | GlassMaterial? | 表面材质；null 省略显式材质层 | GlassNavigationRailDefaults.material() | 否 |
| stroke | EdgeStroke? | 表面描边；null 移除 | GlassNavigationRailDefaults.stroke() | 否 |
| shadow | GlassShadow? | 表面阴影；null 移除 | GlassShadows.Float | 否 |
| selectedColor | Color | 选中图标与文字颜色 | 动态取色主题为 MiuixTheme.colorScheme.primary，其余为 onSurface | 否 |
| unselectedColor | Color | 未选中图标与文字颜色 | MiuixTheme.colorScheme.onSurface | 否 |
| indicatorColor | Color | 指示器常态填充 | GlassNavigationBarDefaults.indicatorColor() | 否 |
| indicatorPressedColor | Color | 折叠胶囊中按下时的指示器填充 | GlassNavigationBarDefaults.indicatorPressedColor() | 否 |
| labelStyle | TextStyle | 目标文字样式，同时决定折叠槽位宽度 | MiuixTheme.textStyles.body1 | 否 |
| expandContentDescription | String | 折叠时开关的无障碍描述 | GlassNavigationRailDefaults.ExpandContentDescription | 否 |
| collapseContentDescription | String | 展开时开关的无障碍描述 | GlassNavigationRailDefaults.CollapseContentDescription | 否 |
| scrollState | ScrollState | 展开列表的滚动状态 | rememberScrollState() | 否 |

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
| Margin | Dp | 面板与窗口边缘的距离 | 12.dp |
| CollapsedHeight | Dp | 折叠胶囊高度 | 44.dp |
| CollapsedTop | Dp | 使胶囊落在顶栏中线上的顶部位置 | 6.dp |
| ExpandContentDescription | String | 折叠时开关的默认描述 | "Expand navigation rail" |
| CollapseContentDescription | String | 展开时开关的默认描述 | "Collapse navigation rail" |

#### 方法

| 方法名 | 返回类型 | 说明 |
| --- | --- | --- |
| material() | GlassMaterial | 按主题选择的 `GlassMaterials.puredThinGlass` |
| stroke() | EdgeStroke | 浅色为 `GlassEdgeStrokes.MiddleLight`，深色为 `MiddleDark` |
