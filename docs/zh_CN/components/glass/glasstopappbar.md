# GlassTopAppBar

`GlassTopAppBar` 是支持折叠标题和操作插槽的玻璃顶栏。使用 [GlassIconButton](./glassiconbutton) 为导航和操作按钮提供一致的样式。

<div style="position: relative; height: 440px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../../../compose/index.html?id=glassTopAppBar" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

::: warning 背景采样
采样区域应包含不透明背景，并排除玻璃表面自身。见[玻璃材质](/zh_CN/guide/glass#background-sampling)。
:::

## 引入

```kotlin
import top.yukonga.miuix.kmp.basic.MiuixScrollBehavior
import top.yukonga.miuix.kmp.glass.GlassIconButton
import top.yukonga.miuix.kmp.glass.GlassTopAppBar
import top.yukonga.miuix.kmp.glass.GlassTopAppBarDefaults
```

## 基本用法

```kotlin
val backdrop = rememberLayerBackdrop()
val listState = rememberLazyListState()
val scrollBehavior = MiuixScrollBehavior()

Scaffold(
    topBar = {
        GlassTopAppBar(
            title = "资料库",
            isContentScrolled = listState.canScrollBackward,
            backdrop = backdrop,
            scrollBehavior = scrollBehavior,
            navigationIcon = {
                GlassIconButton(onClick = onBack) {
                    Icon(backIcon, contentDescription = "返回")
                }
            },
        )
    },
) { padding ->
    LazyColumn(
        state = listState,
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection)
            .layerBackdrop(backdrop)
            .background(MiuixTheme.colorScheme.surface),
        contentPadding = padding,
    ) {
        items(50) { index ->
            Text("项目 $index", modifier = Modifier.padding(16.dp))
        }
    }
}
```

只有列表被记录到 `backdrop` 中。玻璃控件自动使用顶栏的背景采样源和材质。

## 组件状态

### 内容位于顶部或已滚动

- 传入 `scrollBehavior`，根据滚动状态控制材质；未传入时材质保持显示。
- 传入 `isContentScrolled` 可显式控制材质状态。Lazy 列表建议使用 `listState.canScrollBackward`，使返回顶部时清除材质，不受标题折叠状态影响。

## 属性

### GlassTopAppBar 属性

以下参数由两个重载共用：

| 属性名 | 类型 | 说明 | 默认值 | 是否必须 |
| --- | --- | --- | --- | --- |
| title | String | 紧凑标题 | - | 是 |
| modifier | Modifier | 应用于整个顶栏和背景带的修饰符 | Modifier | 否 |
| contentModifier | Modifier | 仅应用于前景顶栏的修饰符 | Modifier | 否 |
| backdrop | Backdrop? | 背景采样源，null 时按钮使用回退填充色 | null | 否 |
| largeTitle | String | 展开时的大标题 | title | 否 |
| subtitle | String | 副标题 | "" | 否 |
| scrollBehavior | ScrollBehavior? | 标题折叠与阴影行为 | null | 否 |
| bandBrush | Brush | 背景带渐变 | GlassTopAppBarDefaults.bandBrush(MiuixTheme.colorScheme.surface) | 否 |
| bandOverhang | Dp | 背景带向顶栏下方延伸的距离 | GlassTopAppBarDefaults.BandOverhang | 否 |
| largeTitleBlurRadius | Dp | 大标题淡出时的模糊半径 | GlassTopAppBarDefaults.LargeTitleBlurRadius | 否 |
| style | GlassStyle | 渲染样式，不会替换默认按钮材质 | GlassDefaults.Style | 否 |
| alpha | Float | 额外的按钮表面透明度乘数 | 1f | 否 |
| buttonSize | Dp | 内嵌玻璃按钮的默认直径 | GlassTopAppBarDefaults.ButtonSize | 否 |
| buttonShape | GlassShape | 内嵌玻璃按钮的默认形状 | GlassShape(buttonSize / 2) | 否 |
| fill | Color | 按钮回退填充色 | GlassTopAppBarDefaults.buttonFill() | 否 |
| stroke | GlassStroke? | 按钮描边，null 表示不绘制 | GlassTopAppBarDefaults.buttonStroke() | 否 |
| buttonShadow | GlassShadow? | 按钮阴影，null 表示不绘制 | GlassShadows.Regular | 否 |
| defaultWindowInsetsPadding | Boolean | 是否添加横向刘海和导航栏内边距；顶部系统栏内边距始终添加 | true | 否 |
| titleAlpha | () -> Float | 大小标题及副标题在绘制阶段使用的透明度乘数 | { 1f } | 否 |
| navigationIcon | @Composable () -> Unit | 前导控件插槽 | {} | 否 |
| actions | @Composable RowScope.() -> Unit | 尾部操作插槽 | {} | 否 |
| bottomContent | @Composable () -> Unit | 标题下方的内容 | {} | 否 |

显式重叠状态重载额外包含一个必填参数：

| 属性名 | 类型 | 说明 | 默认值 | 是否必须 |
| --- | --- | --- | --- | --- |
| isContentScrolled | Boolean | 页面内容是否离开初始位置 | - | 是 |

### GlassTopAppBarDefaults 对象 {#glasstopappbardefaults}

#### 常量

| 常量名 | 类型 | 说明 | 默认值 |
| --- | --- | --- | --- |
| HorizontalPadding | Dp | 前导与尾部控件边距 | 12.dp |
| RampStart | Dp | 阴影渐变起始偏移 | 0.dp |
| RampDistance | Dp | 阴影渐变对应的滚动距离 | 32.dp |
| LargeTitleBlurRadius | Dp | 大标题模糊半径 | 12.dp |
| ButtonSize | Dp | 按钮直径 | 44.dp |
| PressedContentAlpha | Float | 按下时的内容透明度 | 0.6f |
| BandOverhang | Dp | 背景带向顶栏下方延伸的距离 | 28.dp |

#### 方法

| 方法名 | 返回类型 | 说明 |
| --- | --- | --- |
| collapseRamp(scrollBehavior: ScrollBehavior?) | State\<Float> | 从 RampStart 开始，在 RampDistance 内生成 0 到 1 的阴影进度；行为为 null 时返回 1f |
| buttonFill() | Color | 浅色模式为白色，深色模式为 `#FF2C2C2C` |
| buttonMaterial() | GlassMaterial | 随主题选择的 `GlassMaterials.puredThinGlass` |
| buttonStroke() | GlassStroke | 浅色模式为 `GlassStrokes.SmallLight`，深色模式为 `SmallDark` |
| buttonPressedOverlay() | Color | 浅色模式为透明度 0.10 的黑色，深色模式为透明度 0.14 的白色 |
| bandBrush(color: Color) | Brush | 使用页面颜色创建纵向渐变，顶部不透明，底部透明 |

## 进阶用法

### 添加标签栏

将 [GlassTabRow 或 GlassSegmentedTabRow](./glasstabrow#inside-glasstopappbar) 放入 `bottomContent`。它们继承背景采样源和模糊，请根据页面滚动状态设置 `surfaceAlpha`。

### 变换前景内容

使用 `contentModifier` 变换前景内容而保持背景带不动，通过 `titleAlpha` 独立控制标题透明度：

```kotlin
GlassTopAppBar(
    title = "资料库",
    isContentScrolled = listState.canScrollBackward,
    backdrop = backdrop,
    scrollBehavior = scrollBehavior,
    contentModifier = Modifier.graphicsLayer { translationY = foregroundOffsetPx },
    titleAlpha = { titleOpacity },
)
```

背景带延伸、按钮阴影和标题模糊不会增加布局高度，请避免父布局裁剪这些内容。
