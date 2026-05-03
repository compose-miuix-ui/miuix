# TopAppBar

`TopAppBar` 是 Miuix 中的顶部应用栏组件，用于在界面顶部提供导航、标题和操作按钮。支持大标题模式和普通模式，以及滚动时的动态效果。

此组件通常与 `Scaffold` 组件结合使用，以便在应用程序的不同页面中保持一致的布局和行为。

<div style="position: relative; height: 300px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../../compose/index.html?id=topAppBar" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

## 引入

```kotlin
import top.yukonga.miuix.kmp.basic.TopAppBar
import top.yukonga.miuix.kmp.basic.SmallTopAppBar
import top.yukonga.miuix.kmp.basic.MiuixScrollBehavior
import top.yukonga.miuix.kmp.basic.rememberTopAppBarState
```

## 基本用法

### 小标题顶部栏

```kotlin
Scaffold(
    topBar = {
        SmallTopAppBar(
            title = "标题",
            navigationIcon = {
                IconButton(onClick = { /* 处理点击事件 */ }) {
                    Icon(MiuixIcons.Back, contentDescription = "返回")
                }
            },
            actions = {
                IconButton(onClick = { /* 处理点击事件 */ }) {
                    Icon(MiuixIcons.More, contentDescription = "更多")
                }
            }
        )
    }
)
```

### 大标题顶部栏

```kotlin
Scaffold(
    topBar = {
        TopAppBar(
            title = "标题",
            largeTitle = "大标题", // 如果不指定，将使用 title 的值
            navigationIcon = {
                IconButton(onClick = { /* 处理点击事件 */ }) {
                    Icon(MiuixIcons.Back, contentDescription = "返回")
                }
            },
            actions = {
                IconButton(onClick = { /* 处理点击事件 */ }) {
                    Icon(MiuixIcons.More, contentDescription = "更多")
                }
            }
        )
    }
)
```

## 大标题顶部栏滚动行为（使用脚手架）

TopAppBar 支持随内容滚动时改变其显示状态：

```kotlin
val scrollBehavior = MiuixScrollBehavior()

Scaffold(
    topBar = {
        TopAppBar(
            title = "标题",
            largeTitle = "大标题", // 如果不指定，将使用 title 的值
            scrollBehavior = scrollBehavior
        )
    }
) { paddingValues ->
    // 内容区域需要考虑 padding
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            // 如需添加越界回弹效果，则应在绑定滚动行为之前添加
            .overScrollVertical()
            // 绑定 TopAppBar 滚动事件
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        contentPadding = PaddingValues(top = paddingValues.calculateTopPadding())
    ) {
        // 列表内容
    }
}
```

## 自定义样式

### 自定义颜色

```kotlin
TopAppBar(
    title = "标题",
    color = MiuixTheme.colorScheme.primary,
    titleColor = MiuixTheme.colorScheme.onPrimary,
    largeTitleColor = MiuixTheme.colorScheme.onPrimary
)
```

### 自定义内容边距

```kotlin
TopAppBar(
    title = "标题",
    titlePadding = 32.dp
)
```

### 自定义图标边距

```kotlin
TopAppBar(
    title = "标题",
    navigationIconPadding = 12.dp,
    actionIconPadding = 12.dp
)
```

## 属性

### TopAppBar 属性

| 属性名                     | 类型                            | 说明                           | 默认值                            | 是否必须 |
| -------------------------- | ------------------------------- | ------------------------------ | --------------------------------- | -------- |
| title                      | String                          | 顶部栏标题                     | -                                 | 是       |
| modifier                   | Modifier                        | 应用于顶部栏的修饰符           | Modifier                          | 否       |
| color                      | Color                           | 顶部栏背景颜色                 | MiuixTheme.colorScheme.surface    | 否       |
| titleColor                 | Color                           | 折叠时小标题文字颜色           | MiuixTheme.colorScheme.onSurface  | 否       |
| largeTitle                 | String                          | 大标题文本                     | title                             | 否       |
| largeTitleColor            | Color                           | 展开时大标题文字颜色           | MiuixTheme.colorScheme.onSurface  | 否       |
| subtitle                   | String                          | 显示在标题栏下方的副标题文本   | ""                                | 否       |
| subtitleColor              | Color                           | 副标题文字颜色                 | MiuixTheme.colorScheme.onSurfaceVariantSummary  | 否       |
| navigationIcon             | @Composable () -> Unit          | 导航图标区域的可组合函数       | {}                                | 否       |
| actions                    | @Composable RowScope.() -> Unit | 操作按钮区域的可组合函数       | {}                                | 否       |
| scrollBehavior             | ScrollBehavior?                 | 控制顶部栏滚动行为             | null                              | 否       |
| defaultWindowInsetsPadding | Boolean                         | 是否应用默认窗口边距           | true                              | 否       |
| titlePadding          | Dp                              | 水平内容边距                   | TopAppBarDefaults.TitlePadding | 否       |
| navigationIconPadding      | Dp                              | 导航图标的起始边距             | TopAppBarDefaults.NavigationIconPadding | 否       |
| actionIconPadding          | Dp                              | 操作图标的末尾边距             | TopAppBarDefaults.ActionIconPadding | 否       |
| bottomContent              | @Composable () -> Unit          | 显示在标题栏下方的可组合内容 | {}                                | 否       |

### SmallTopAppBar 属性

| 属性名                     | 类型                            | 说明                     | 默认值                            | 是否必须 |
| -------------------------- | ------------------------------- | ------------------------ | --------------------------------- | -------- |
| title                      | String                          | 顶部栏标题               | -                                 | 是       |
| modifier                   | Modifier                        | 应用于顶部栏的修饰符     | Modifier                          | 否       |
| color                      | Color                           | 顶部栏背景颜色           | MiuixTheme.colorScheme.surface    | 否       |
| titleColor                 | Color                           | 标题文字颜色             | MiuixTheme.colorScheme.onSurface  | 否       |
| subtitle                   | String                          | 显示在标题栏下方的副标题文本 | ""                                | 否       |
| subtitleColor              | Color                           | 副标题文字颜色           | MiuixTheme.colorScheme.onSurfaceVariantSummary  | 否       |
| navigationIcon             | @Composable () -> Unit          | 导航图标区域的可组合函数 | {}                                | 否       |
| actions                    | @Composable RowScope.() -> Unit | 操作按钮区域的可组合函数 | {}                                | 否       |
| scrollBehavior             | ScrollBehavior?                 | 控制顶部栏滚动行为       | null                              | 否       |
| defaultWindowInsetsPadding | Boolean                         | 是否应用默认窗口边距     | true                              | 否       |
| titlePadding          | Dp                              | 水平内容边距             | TopAppBarDefaults.TitlePadding | 否       |
| navigationIconPadding      | Dp                              | 导航图标的起始边距       | TopAppBarDefaults.NavigationIconPadding | 否       |
| actionIconPadding          | Dp                              | 操作图标的末尾边距       | TopAppBarDefaults.ActionIconPadding | 否       |
| bottomContent              | @Composable () -> Unit          | 显示在标题栏下方的可组合内容 | {}                                | 否       |

### TopAppBarDefaults 对象

TopAppBarDefaults 对象提供了 TopAppBar 和 SmallTopAppBar 组件的默认值。

#### 常量

| 常量名                    | 类型 | 说明                              | 默认值 |
| ------------------------- | ---- | --------------------------------- | ------ |
| TitlePadding    | Dp   | 标题和大标题的水平内边距          | 26.dp  |
| NavigationIconPadding      | Dp   | 导航图标的起始边距               | 16.dp  |
| ActionIconPadding          | Dp   | 操作图标的末尾边距               | 16.dp  |
| CollapsedHeight            | Dp   | TopAppBar 折叠时的高度           | 52.dp  |
| SmallTopAppBarCenterHeight | Dp   | SmallTopAppBar 布局的垂直中心高度 | 50.dp  |
| LargeTitleBottomPadding    | Dp   | 无副标题时大标题下方的底部边距   | 4.dp   |
| SubtitleBottomPadding      | Dp   | 副标题下方的底部边距（大小标题均适用） | 8.dp   |

### ScrollBehavior

MiuixScrollBehavior 是用于控制顶部栏滚动行为的配置对象。

#### rememberTopAppBarState

用于创建和记住 TopAppBarState：

```kotlin
val scrollBehavior = MiuixScrollBehavior(
    state = rememberTopAppBarState(),
    snapAnimationSpec = spring(stiffness = 2500f),
    canScroll = { true }
)
```

| 参数名             | 类型                        | 默认值                     | 说明                       |
| ------------------ | --------------------------- | -------------------------- | -------------------------- |
| state              | TopAppBarState              | rememberTopAppBarState()   | 控制滚动状态的状态对象     |
| canScroll          | () -> Boolean               | { true }                   | 控制是否允许滚动的回调     |
| snapAnimationSpec  | AnimationSpec\<Float>?      | spring(stiffness = 2500f)  | 定义顶部栏滚动后的吸附动画 |
| flingAnimationSpec | DecayAnimationSpec\<Float>? | rememberSplineBasedDecay() | 定义顶部栏滑动的衰减动画   |

## 进阶用法

### 处理窗口边距

```kotlin
TopAppBar(
    title = "标题",
    largeTitle = "大标题",
    defaultWindowInsetsPadding = false // 自行处理窗口嵌入边距
)
```

### 自定义滚动行为动画

```kotlin
var isScrollingEnabled by remember { mutableStateOf(true) }
val scrollBehavior = MiuixScrollBehavior(
    snapAnimationSpec = tween(durationMillis = 100),
    flingAnimationSpec = rememberSplineBasedDecay(),
    canScroll = { isScrollingEnabled } // 可以动态控制是否允许滚动
)

TopAppBar(
    title = "标题",
    largeTitle = "大标题",
    scrollBehavior = scrollBehavior
)
```

### 大标题和小标题结合使用

```kotlin
var useSmallTopBar by remember { mutableStateOf(false) }

Box(modifier = Modifier.fillMaxSize()) {
    if (useSmallTopBar) {
        SmallTopAppBar(
            title = "精简模式",
            navigationIcon = {
                IconButton(onClick = { useSmallTopBar = false }) {
                    Icon(
                        imageVector = MiuixIcons.Back,
                        contentDescription = "切换到大标题",
                        tint = MiuixTheme.colorScheme.onBackground
                    )
                }
            }
        )
    } else {
        TopAppBar(
            title = "标题",
            largeTitle = "展开模式",
            navigationIcon = {
                IconButton(onClick = { useSmallTopBar = true }) {
                    Icon(
                        imageVector = MiuixIcons.Back,
                        contentDescription = "切换到小标题",
                        tint = MiuixTheme.colorScheme.onBackground
                    )
                }
            }
        )
    }
}
```
