# GlassTabRow

`GlassTabRow` 使用等宽的玻璃胶囊标签展示内容分类，提供 primary 和 neutral 配色。`GlassSegmentedTabRow` 将标签连接为一条轨道，并使用滑动指示器标识选中项。两者都接收选中状态，并通过 `onSelect` 报告变更。

<div style="position: relative; height: 440px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../../../compose/index.html?id=glassTabRow" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

::: warning 背景采样
使用背景采样时，`backdrop` 应记录组件后方的不透明内容，不能包含使用该采样的玻璃表面。配置方式见[玻璃材质](/zh_CN/guide/glass#background-sampling)。
:::

## 引入

```kotlin
import top.yukonga.miuix.kmp.glass.GlassTabRow
import top.yukonga.miuix.kmp.glass.GlassTabRowDefaults
import top.yukonga.miuix.kmp.glass.GlassTabColors
import top.yukonga.miuix.kmp.glass.GlassSegmentedTabRow
import top.yukonga.miuix.kmp.glass.GlassSegmentedTabRowDefaults
```

## 基本用法

`GlassTabRow` 允许背景采样源为空，`GlassSegmentedTabRow` 则要求非空。

### Primary 独立胶囊

```kotlin
val tabs = listOf("首页", "小组件", "主题")
var selectedIndex by remember { mutableIntStateOf(0) }

GlassTabRow(
    tabs = tabs,
    selectedIndex = selectedIndex,
    onSelect = { selectedIndex = it },
    backdrop = backdrop,
    modifier = Modifier.fillMaxWidth(),
)
```

默认 primary 配色使用主题主色填充选中胶囊。各胶囊分别改变自身颜色，没有在胶囊之间移动的指示器。

### Neutral 独立胶囊

页面内容已经使用强调色时，可以选择 neutral 配色，并单独设置对应的较矮高度：

```kotlin
GlassTabRow(
    tabs = tabs,
    selectedIndex = selectedIndex,
    onSelect = { selectedIndex = it },
    backdrop = backdrop,
    modifier = Modifier.fillMaxWidth(),
    height = GlassTabRowDefaults.NeutralHeight,
    colors = GlassTabRowDefaults.neutralColors(),
)
```

### 相连分段轨道

```kotlin
GlassSegmentedTabRow(
    tabs = tabs,
    selectedIndex = selectedIndex,
    onSelect = { selectedIndex = it },
    backdrop = backdrop,
    modifier = Modifier.fillMaxWidth(),
)
```

两种布局均通过点击选择，不提供导航栏式的拖动选择。请使用互不相同的简短标签：标签文本作为组合键，等宽排列，单行显示，超出时省略。标签栏不可横向滚动，需要有界宽度。

## 组件状态

### 静止表面与玻璃表面

有可用背景采样源时，`surfaceAlpha` 表示材质进度，不是整个控件的透明度。零值时，选中填充、标签和分段指示器仍然可见。未选中胶囊使用 `restingContainerColor`，分段轨道使用 `trackColor`。值为一时，材质色层、描边和阴影达到完整强度，未选中胶囊使用 `containerColor`。`GlassTabRow` 在进度为零时仍会采样并模糊背景，`GlassSegmentedTabRow` 则会在进度为零时禁用背景采样。

没有可用背景采样源时，`GlassTabRow` 使用 `glassPanel` 的回退路径：`alpha * surfaceAlpha`（其中 `surfaceAlpha` 限制到 0–1）会乘到整个胶囊的绘制透明度上，包括填充、标签和按下覆盖色。因此，`surfaceAlpha = 0f` 会使胶囊不可见，而不是显示不透明的静止胶囊；它不会禁用选择。空背景采样源不会自动重置进度。

## 属性

### GlassTabRow 属性

| 属性名 | 类型 | 说明 | 默认值 | 是否必须 |
| --- | --- | --- | --- | --- |
| tabs | List\<String> | 按顺序排列且互不相同的标签文本 | - | 是 |
| selectedIndex | Int | 当前标签索引 | - | 是 |
| onSelect | (Int) -> Unit | 返回所点击标签索引的回调 | - | 是 |
| backdrop | Backdrop? | 背景采样源，允许为 null | - | 是 |
| modifier | Modifier | 应用于标签栏的修饰符 | Modifier | 否 |
| style | GlassStyle | material 为 null 时使用的模糊样式 | GlassDefaults.Style | 否 |
| alpha | Float | 材质色层、描边和阴影的透明度乘数；无背景采样源时作用于整个胶囊绘制 | 1f | 否 |
| surfaceAlpha | Float | 材质进度，限制到 0–1；无背景采样源时还会乘到整个胶囊的绘制透明度上 | 1f | 否 |
| stroke | GlassStroke? | 每个胶囊的描边，null 表示不绘制 | GlassTabRowDefaults.stroke() | 否 |
| shadow | GlassShadow? | 每个胶囊的阴影，null 表示不绘制 | GlassShadows.Float | 否 |
| material | GlassMaterial? | 胶囊材质，null 时使用样式提供的模糊 | GlassTabRowDefaults.material() | 否 |
| height | Dp | 胶囊固定高度 | GlassTabRowDefaults.Height | 否 |
| tabGap | Dp | 胶囊之间的间距 | GlassTabRowDefaults.TabGap | 否 |
| colors | GlassTabColors | 选中、静止和按下配色 | GlassTabRowDefaults.primaryColors() | 否 |

### GlassSegmentedTabRow 属性 {#glasssegmentedtabrow}

| 属性名 | 类型 | 说明 | 默认值 | 是否必须 |
| --- | --- | --- | --- | --- |
| tabs | List\<String> | 按顺序排列且互不相同的标签文本 | - | 是 |
| selectedIndex | Int | 当前标签索引 | - | 是 |
| onSelect | (Int) -> Unit | 返回所点击标签索引的回调 | - | 是 |
| backdrop | Backdrop | 背景采样源 | - | 是 |
| modifier | Modifier | 应用于轨道的修饰符 | Modifier | 否 |
| style | GlassStyle | material 为 null 时使用的模糊样式 | GlassDefaults.Style | 否 |
| alpha | Float | 材质色层、描边和阴影的透明度乘数 | 1f | 否 |
| surfaceAlpha | Float | 材质进度，限制到 0–1 | 1f | 否 |
| stroke | GlassStroke? | 轨道描边，null 表示不绘制 | GlassSegmentedTabRowDefaults.stroke() | 否 |
| shadow | GlassShadow? | 轨道阴影，null 表示不绘制 | GlassShadows.Float | 否 |
| material | GlassMaterial? | 轨道材质，null 时使用样式提供的模糊 | GlassSegmentedTabRowDefaults.material() | 否 |
| height | Dp | 轨道固定高度 | GlassSegmentedTabRowDefaults.Height | 否 |
| trackColor | Color | 材质进度为零时的轨道填充色 | GlassSegmentedTabRowDefaults.restingTrackColor() | 否 |
| indicatorColor | Color | 滑动指示器填充色 | GlassSegmentedTabRowDefaults.indicatorColor() | 否 |
| selectedContentColor | Color | 选中标签颜色 | MiuixTheme.colorScheme.onSurface | 否 |
| contentColor | Color | 未选中标签颜色 | MiuixTheme.colorScheme.onSurface | 否 |

空列表不渲染任何内容。越界索引会在显示时限制到有效范围，不会修改调用方状态。高度固定，分段轨道内部高度为 `height - 2 * TrackPadding`。

### GlassTabColors 属性 {#glasstabcolors}

构造函数的全部字段均必填。只自定义部分颜色时，请使用默认工厂配合 `copy`。

| 属性名 | 类型 | 说明 | 默认值 | 是否必须 |
| --- | --- | --- | --- | --- |
| selectedContainerColor | Color | 选中胶囊填充色 | - | 是 |
| selectedContentColor | Color | 选中标签颜色 | - | 是 |
| restingContainerColor | Color | 材质进度为零时的未选中填充色 | - | 是 |
| containerColor | Color | 材质进度为一时的未选中填充色 | - | 是 |
| contentColor | Color | 未选中标签颜色 | - | 是 |
| pressedOverlayColor | Color | 任意胶囊按下时的覆盖色，不区分选中状态 | - | 是 |
| restingStrokeAlpha | Float | 未选中胶囊的额外静止描边强度 | - | 是 |

### GlassTabRowDefaults 对象 {#glasstabrowdefaults}

#### 常量

| 常量名 | 类型 | 说明 | 默认值 |
| --- | --- | --- | --- |
| Height | Dp | Primary 胶囊高度 | 40.dp |
| NeutralHeight | Dp | 搭配 neutral 配色的高度 | 35.dp |
| TabGap | Dp | 胶囊之间的间距 | 12.dp |
| TabPaddingHorizontal | Dp | 内容水平内边距 | 16.dp |
| TabPaddingVertical | Dp | 内容垂直内边距 | 6.dp |

#### 方法

| 方法名 | 返回类型 | 说明 |
| --- | --- | --- |
| shape(height: Dp = Height) | GlassShape | 半径为 height / 2、smoothing 为 0f 的胶囊 |
| material() | GlassMaterial | 随主题选择的 `GlassMaterials.puredThinGlass` |
| stroke() | GlassStroke | 浅色模式为 `GlassStrokes.MiddleLight`，深色模式为 `MiddleDark` |
| primaryColors(selectedContainerColor: Color = MiuixTheme.colorScheme.primary, containerColor: Color = Color.Transparent) | GlassTabColors | 强调色配色，参数设置选中填充和材质激活后的未选中填充 |
| neutralColors() | GlassTabColors | 中性配色，选中填充在浅色模式为 `#FF383838`，深色模式为 `#FFE6E6E6`；材质激活后的未选中填充为透明色 |

### GlassSegmentedTabRowDefaults 对象 {#glasssegmentedtabrowdefaults}

#### 常量

| 常量名 | 类型 | 说明 | 默认值 |
| --- | --- | --- | --- |
| Height | Dp | 轨道高度 | 40.dp |
| TrackPadding | Dp | 标签和指示器周围的内缩 | 3.dp |
| TabGap | Dp | 标签区域之间的间距 | 8.dp |
| TabPaddingHorizontal | Dp | 标签内容水平内边距 | 16.dp |

#### 方法

| 方法名 | 返回类型 | 说明 |
| --- | --- | --- |
| shape(height: Dp = Height) | GlassShape | 半径为 height / 2、smoothing 为 0f 的胶囊 |
| material() | GlassMaterial | 随主题选择的 `GlassMaterials.puredThinGlass` |
| stroke() | GlassStroke | 浅色模式为 `GlassStrokes.MiddleLight`，深色模式为 `MiddleDark` |
| restingTrackColor() | Color | 浅色模式为白色；深色模式为透明度 0.133 的白色叠加到 surface |
| indicatorColor() | Color | 浅色模式为透明度 0.06 的黑色，深色模式为透明度 0.133 的白色 |

## 进阶用法

### 自定义颜色

```kotlin
GlassTabRow(
    tabs = tabs,
    selectedIndex = selectedIndex,
    onSelect = { selectedIndex = it },
    backdrop = backdrop,
    modifier = Modifier.fillMaxWidth(),
    colors = GlassTabRowDefaults.primaryColors().copy(
        selectedContainerColor = MiuixTheme.colorScheme.primaryContainer,
        selectedContentColor = MiuixTheme.colorScheme.onPrimaryContainer,
    ),
)
```

### 在 GlassTopAppBar 中使用 {#inside-glasstopappbar}

两种布局均继承 [GlassTopAppBar](./glasstopappbar) 的非空背景采样源和有效模糊半径，但保留自身材质颜色和 `surfaceAlpha`。空材质仍为空。下例使用顶栏示例中的列表状态和背景采样源，以及上文的标签状态：

```kotlin
val isContentScrolled = listState.canScrollBackward

GlassTopAppBar(
    title = "资料库",
    isContentScrolled = isContentScrolled,
    backdrop = backdrop,
    scrollBehavior = scrollBehavior,
    bottomContent = {
        GlassTabRow(
            tabs = tabs,
            selectedIndex = selectedIndex,
            onSelect = { selectedIndex = it },
            backdrop = backdrop,
            surfaceAlpha = if (isContentScrolled) 1f else 0f,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp),
        )
    },
)
```

请根据页面重叠状态驱动标签栏材质进度，不要根据标题展开状态驱动。标签栏不会自动共享顶栏的材质过渡。
