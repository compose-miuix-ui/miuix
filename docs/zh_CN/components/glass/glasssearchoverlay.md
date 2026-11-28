# GlassSearchOverlay

`GlassSearchOverlay` 是一个全屏液态玻璃搜索浮层组件，包含顶部渐进模糊过渡带、玻璃态搜索输入框胶囊、动画取消按钮以及可滚动的搜索结果容器。

<div style="position: relative; height: 440px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../../../compose/index.html?id=glassSearchOverlay" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

## 引入

```kotlin
import top.yukonga.miuix.kmp.glass.GlassSearchOverlay
import top.yukonga.miuix.kmp.glass.GlassSearchOverlayDefaults
import top.yukonga.miuix.kmp.glass.glassSearchBarTransition
import top.yukonga.miuix.kmp.glass.rememberGlassSearchBarAlpha
```

## 基本用法

将 `GlassSearchOverlay` 与 `rememberGlassSearchBarAlpha`、`Modifier.glassSearchBarTransition` 配合使用，可在进入搜索模式时让顶栏平滑淡出、缩放并模糊。

```kotlin
var searchExpanded by remember { mutableStateOf(false) }
val searchBarAlpha = rememberGlassSearchBarAlpha(searchExpanded)
val listState = rememberLazyListState()

Box(Modifier.fillMaxSize()) {
    Scaffold(
        topBar = {
            GlassTopAppBar(
                title = "Search",
                contentModifier = Modifier.glassSearchBarTransition(searchExpanded) { 1f },
                actions = {
                    GlassIconButton(onClick = { searchExpanded = true }) {
                        Icon(painter = rememberSymbolPainter(MiuixIcons.Symbol.Search), contentDescription = "Search")
                    }
                },
            )
        },
    ) {
        // 页面内容
    }

    GlassSearchOverlay(
        expanded = searchExpanded,
        onDismissRequest = { searchExpanded = false },
        label = "Search items",
        scrollOffset = {
            if (listState.firstVisibleItemIndex > 0) {
                Float.MAX_VALUE
            } else {
                listState.firstVisibleItemScrollOffset.toFloat()
            }
        },
    ) { query ->
        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(top = 52.dp),
        ) {
            // 搜索结果列表
        }
    }
}
```

## API 参考

### GlassSearchOverlay

| 属性 | 类型 | 默认值 | 说明 |
| :--- | :--- | :--- | :--- |
| expanded | Boolean | - | 搜索浮层是否展开 |
| onDismissRequest | () -> Unit | - | 点击取消或按下返回/Escape 键时的回调 |
| scrollMaterialThreshold | Dp | 省略时为 `Dp.Unspecified` | 传入搜索框底部与首条结果之间的初始间距。结果跨过这段间距时，搜索框和关闭按钮在 450 ms 内切换纯色和玻璃材质 |
| modifier | Modifier | Modifier | 应用于根容器的修饰符 |
| horizontalInsetsModifier | Modifier | Modifier | 应用于搜索栏行的水平安全区修饰符 |
| label | String | "Search" | 输入为空时显示的占位文本 |
| scrollOffset | () -> Float | `{ 0f }` | 结果列表的垂直滚动偏移量，单位为像素，列表为空时返回零。用于驱动顶部渐进模糊和可选的材质过渡 |
| topInsets | WindowInsets | WindowInsets.statusBars | 应用于搜索栏的顶部安全区 |
| searchTopPadding | () -> Dp | `{ 0.dp }` | 搜索栏额外的顶部偏移（例如位于折叠导航胶囊下方时） |
| blurEnabled | Boolean | true | 是否启用模糊与玻璃效果（不支持 RuntimeShader 时自动回退） |
| style | GlassStyle | GlassDefaults.Style | 玻璃外观样式 |
| material | GlassMaterial | GlassSearchOverlayDefaults.material() | 搜索框与取消按钮的玻璃材质 |
| stroke | EdgeStroke? | GlassSearchOverlayDefaults.stroke() | 玻璃胶囊边缘描边 |
| shadow | GlassShadow? | GlassSearchOverlayDefaults.shadow() | 玻璃胶囊外阴影 |
| searchIcon | ImageVector | MiuixIcons.Symbol.Search.toImageVector(defaultSize = 20.dp) | 搜索框前置图标 |
| closeIcon | ImageVector | MiuixIcons.Symbol.Close1.toImageVector() | 输入框旁的取消图标 |
| content | @Composable ColumnScope.(String) -> Unit | `{}` | 搜索结果内容，接收当前输入文本 |

### GlassSearchOverlayDefaults

| 属性 / 方法 | 类型 | 默认值 |
| :--- | :--- | :--- |
| BarHeight | Dp | `44.dp` |
| HorizontalPadding | Dp | `GlassTopAppBarDefaults.HorizontalPadding` |
| VerticalPadding | Dp | `4.dp` |
| ProgressiveBlurOverhang | Dp | `24.dp` |
| material() | GlassMaterial | `GlassNavigationRailDefaults.material()` |
| stroke() | EdgeStroke? | `GlassNavigationRailDefaults.stroke()` |
| shadow() | GlassShadow | `GlassShadows.Float` |
