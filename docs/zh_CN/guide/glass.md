# 柔光玻璃

`miuix-glass` 是一个独立模块，把柔光玻璃材质带到了 Compose Multiplatform，并附带建立在该材质之上的组件：顶栏、底栏、标签栏、弹窗与对话框。支持 Android、Desktop (JVM)、iOS、macOS 以及 Web (WasmJs/Js)。

::: warning
在 Android 上，`miuix-glass` 需要 `minSdk` 33（Android 13）或更高。材质由运行时着色器绘制，因此下限与 `miuix-blur` 相同。如果应用仍需支持更低的 `minSdk`，请用[能力检测](/zh_CN/guide/blur#运行时能力检查)包住相关代码路径。
:::

## 安装

`miuix-glass` 依赖 `miuix-blur` 与 `miuix-ui`，并会传递引入两者。组件所配套的 Glass 图标集位于独立的构件中：

```kotlin
kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation("top.yukonga.miuix.kmp:miuix-glass:<version>")
            // 可选：Glass 图标集
            implementation("top.yukonga.miuix.kmp:miuix-glass-icons:<version>")
        }
    }
}
```

仅 Android 项目：

```kotlin
dependencies {
    implementation("top.yukonga.miuix.kmp:miuix-glass-android:<version>")
    implementation("top.yukonga.miuix.kmp:miuix-glass-icons-android:<version>")
}
```

## 平台支持

| 平台          | 最低要求            |
| ------------- | ------------------- |
| Android       | API 33（Android 13）|
| Desktop (JVM) | 支持                |
| iOS / macOS   | 支持                |
| WasmJs / Js   | 支持                |

每个柔光玻璃表面都通过运行时着色器采样其身后的内容。在不支持运行时着色器的设备上，组件会退化为纯色填充，而不是采样背景。

## 背景采样

柔光玻璃需要采样身后的内容，因此页面必须先记录自己。这里使用的正是[模糊效果](/zh_CN/guide/blur)中那套 `Backdrop` 机制：

```kotlin
import top.yukonga.miuix.kmp.blur.layerBackdrop
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop

val backdrop = rememberLayerBackdrop()

Box(
    modifier = Modifier
        .fillMaxSize()
        .layerBackdrop(backdrop)
        .background(MiuixTheme.colorScheme.surface),
) {
    // 页面内容
}
```

`layerBackdrop` 只会捕获它所附着组件自身绘制的内容，因此请为该组件提供不透明背景，原因见[模糊效果](/zh_CN/guide/blur#基本用法)。接受可空 `Backdrop` 的组件在传入 `null` 时会退化为纯色填充。

## 材质

`GlassStyle` 描述一份材质，`GlassStyles` 保存完整的原始令牌表。`GlassDefaults.Style` 是默认值，`GlassDefaults.style(isDark)` 在浅色与深色默认值之间二选一；需要特定条目时，`Common*`、`Bionic*`、`Tint*` 各系列均可直接使用。

一个表面还需要三个配套类型：

| 类型          | 作用                                        |
| ------------- | ------------------------------------------- |
| `GlassShape`  | 表面轮廓。                                  |
| `GlassStroke` | 边缘描边。                                  |
| `GlassShadow` | 投影。`GlassShadows.Regular`、`Float`、`ExtraHigh`。 |

`GlassMaterial` 是悬浮栏使用的另一套处理：一个模糊半径，外加最多三层颜色，每层各自使用独立的混合模式。`GlassMaterials.puredThinGlass(isDark)` 是每个悬浮栏都使用的令牌，`GlassMaterials.popupViewGlass(isDark)` 对应弹窗，`GlassMaterials.actionBarMask(isDark)` 对应顶栏遮罩。

`GlassMotion` 保存组件使用的弹簧参数，需要与组件对齐动画时可读取。

## 表面

模块中所有柔光玻璃表面都由两个原语构建：

```kotlin
// 带投影的面板，绘制在 backdrop 之上。
Modifier.glassPanel(
    backdrop = backdrop,
    shape = GlassShape(24.dp),
    style = GlassDefaults.style(isDark),
    material = GlassMaterials.puredThinGlass(isDark),
    stroke = GlassStrokes.forTheme(isDark),
    shadow = GlassShadows.Regular,
    shading = false,
)
```

```kotlin
// 同样的表面，但采用库内组件索要自身表面时的形式。
val surface = glassSurface(backdrop, GlassPopupDefaults.visuals())
```

`shading = true` 会折射并给背景着色，适合控件；`false` 则是纯材质，适合栏或菜单。`glassSurface` 是让既有浮层组件变成柔光玻璃的入口——把它交给组件的 `surface` 槽位即可，不存在第二套实现：

```kotlin
OverlayIconCascadingDropdownMenu(
    entries = entries,
    surface = glassSurface(backdrop, GlassPopupDefaults.visuals()),
) {
    Icon(imageVector = MiuixIcons.Glass.More, contentDescription = null)
}
```

## 组件

### GlassTopAppBar

`GlassTopAppBar` 是柔光玻璃顶栏。把按钮放进 `GlassIconButton`，它们就会继承顶栏的材质：

```kotlin
GlassTopAppBar(
    title = "Glass",
    backdrop = backdrop,
    scrollBehavior = scrollBehavior,
    navigationIcon = {
        GlassIconButton(onClick = { navigator.pop() }) {
            Icon(
                imageVector = MiuixIcons.Glass.ChevronBackward,
                contentDescription = "Back",
                modifier = Modifier.size(24.dp),
            )
        }
    },
)
```

带 `isContentScrolled` 的重载不再从 `scrollBehavior` 推导，而是直接接收页面自身的“内容已离开顶部”信号：

```kotlin
GlassTopAppBar(
    title = "Glass",
    isContentScrolled = listState.canScrollBackward,
    backdrop = backdrop,
    scrollBehavior = scrollBehavior,
)
```

`contentModifier` 只变换前景而不移动背景带，这正是搜索过渡需要的；`titleAlpha` 会与两个标题容器原有的折叠透明度相乘。

### GlassNavigationBar

```kotlin
GlassNavigationBar(
    items = items,
    selectedIndex = selectedIndex,
    onSelect = { selectedIndex = it },
    backdrop = backdrop,
    modifier = Modifier.padding(horizontal = 24.dp),
)
```

`items` 是 `List<GlassNavigationItem>`，每项包含一个图标和一行标签。`height` 为最小高度而非固定高度；外部位置由调用方控制，组件不会自行添加系统栏 inset。

### GlassTabRow

```kotlin
GlassTabRow(
    tabs = listOf("Home", "Widget", "Theme"),
    selectedIndex = selectedIndex,
    onSelect = { selectedIndex = it },
    backdrop = backdrop,
)
```

组件自带两套配色：`GlassTabRowDefaults.primaryColors()` 用于默认观感，`GlassTabRowDefaults.neutralColors()` 适合内容本身已占据强调色的页面（配合 `GlassTabRowDefaults.NeutralHeight` 使用）。

`surfaceAlpha` 表示材质的显现程度。请由页面重叠状态驱动它，而不是标题的展开状态。`GlassSegmentedTabRow` 是相连的变体。

### GlassDialog

```kotlin
GlassDialog(
    visible = showDialog,
    onDismissRequest = { showDialog = false },
    backdrop = backdrop,
) {
    Text(text = "Glass Dialog", style = MiuixTheme.textStyles.title4)
    TextButton(text = "OK", onClick = { showDialog = false })
}
```

遮罩与预测返回手势都由组件内部处理。

### 弹窗

`GlassTransformPopup` 会从按钮处生长出面板。先持有 `GlassPopupAnchor`，用 `Modifier.glassPopupAnchor` 上报按钮、用 `Modifier.glassPopupAnchorContent` 上报按钮内容，再把锚点交给弹窗：

```kotlin
val anchor = rememberGlassPopupAnchor()

Box {
    GlassIconButton(
        onClick = { showMenu = true },
        modifier = Modifier.glassPopupAnchor(anchor, cornerRadius = 20.dp),
    ) {
        Icon(
            imageVector = MiuixIcons.Glass.More,
            contentDescription = "More",
            modifier = Modifier.glassPopupAnchorContent(anchor),
        )
    }

    GlassTransformPopup(
        show = showMenu,
        onDismissRequest = { showMenu = false },
        anchor = anchor,
        backdrop = backdrop,
        anchorContent = {
            Icon(imageVector = MiuixIcons.Glass.More, contentDescription = null)
        },
    ) {
        GlassPopupItem(text = "Rename", onClick = { showMenu = false })
        GlassPopupItem(text = "Delete", onClick = { showMenu = false })
    }
}
```

共享同一个锚点，才能让按钮和从它生长出的面板保持一致。

`GlassPopup`、`GlassSecondaryPopup` 与 `GlassDropdownPopup` 则接收 `anchorBounds: Rect`，由调用方上报触发元素自身：

```kotlin
var anchorBounds by remember { mutableStateOf(Rect.Zero) }

GlassPopupItem(
    text = "Sort by",
    onClick = { submenu = true },
    modifier = Modifier.onGloballyPositioned { anchorBounds = it.boundsInRoot() },
)

GlassSecondaryPopup(
    show = submenu,
    onDismissRequest = { submenu = false },
    anchorBounds = anchorBounds,
    backdrop = backdrop,
) {
    GlassPopupItem(text = "Name", onClick = { submenu = false })
}
```

`GlassSecondaryPopup` 在已展开的菜单旁打开二级菜单；收起过程中请保持其组合，并把一级弹窗的 `materialAnchor` 传给它，让两者共享材质。

## 完整示例

```kotlin
@Composable
fun GlassPage() {
    if (!isRuntimeShaderSupported()) return

    val backdrop = rememberLayerBackdrop()
    val scrollBehavior = MiuixScrollBehavior()
    var tab by remember { mutableIntStateOf(0) }
    var dialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            GlassTopAppBar(
                title = "Glass",
                backdrop = backdrop,
                scrollBehavior = scrollBehavior,
                bottomContent = {
                    GlassTabRow(
                        tabs = listOf("Home", "Widget", "Theme"),
                        selectedIndex = tab,
                        onSelect = { tab = it },
                        backdrop = backdrop,
                    )
                },
            )
        },
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize()) {
            // 页面记录自身，所有柔光玻璃表面都采样这份记录。
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .layerBackdrop(backdrop)
                    .background(MiuixTheme.colorScheme.surface),
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = innerPadding,
                ) {
                    items(40) { index ->
                        BasicComponent(
                            title = "Item $index",
                            onClick = { dialog = true },
                        )
                    }
                }
            }

            GlassDialog(
                visible = dialog,
                onDismissRequest = { dialog = false },
                backdrop = backdrop,
            ) {
                Text(text = "Glass Dialog", style = MiuixTheme.textStyles.title4)
                TextButton(text = "OK", onClick = { dialog = false })
            }
        }
    }
}
```

## 另见

- [模糊效果](/zh_CN/guide/blur)——柔光玻璃采样所依赖的背景机制。
- [图标系统](/zh_CN/guide/icons)——`miuix-glass-icons` 中的 Glass 图标集。
- [TopAppBar](/zh_CN/components/topappbar)、[NavigationBar](/zh_CN/components/navigationbar)、[TabRow](/zh_CN/components/tabrow)——对应的普通版本。
