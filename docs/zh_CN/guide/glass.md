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

`GlassMaterials.dialogGlass(isDark)` 选择下文中的对话框专用材质。

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

### 对话框材质

`glassDialogSurface` 为 `OverlayDialog` 提供专用的模糊混色材质。标题、尺寸、系统插入内边距和关闭行为仍由 `OverlayDialog` 负责。操作按钮使用 `GlassDialogButton`，在这层共享材质上叠加填色。

背景采样应位于弹窗宿主下方。例如，让外层 `Scaffold` 承载对话框，内层记录页面及顶部、底部栏：

```kotlin
val dialogBackdrop = rememberLayerBackdrop()
var showDialog by remember { mutableStateOf(false) }

Scaffold(contentWindowInsets = WindowInsets(0)) {
    Scaffold(
        modifier = Modifier.fillMaxSize().layerBackdrop(dialogBackdrop),
        topBar = { /* 页面顶部栏 */ },
        bottomBar = { /* 页面底部栏 */ },
    ) { padding ->
        TextButton(
            text = "显示对话框",
            onClick = { showDialog = true },
            modifier = Modifier.padding(padding),
        )
        OverlayDialog(
            show = showDialog,
            onDismissRequest = { showDialog = false },
            dimColor = GlassDialogDefaults.ScrimColor,
            surface = glassDialogSurface(backdrop = dialogBackdrop),
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                GlassDialogButton(
                    onClick = { showDialog = false },
                    modifier = Modifier.weight(1f),
                ) {
                    Text("取消", style = MiuixTheme.textStyles.button)
                }
                GlassDialogButton(
                    onClick = { showDialog = false },
                    modifier = Modifier.weight(1f),
                    role = GlassDialogButtonRole.Primary,
                ) {
                    Text("确定", style = MiuixTheme.textStyles.button)
                }
            }
        }
    }
}
```

此处应保持 `renderInRootScaffold = true`，使对话框位于内层采样之外。如果已有外层宿主提供这个边界，直接复用即可。

| GlassDialogDefaults | 默认值 / 行为 |
| --- | --- |
| ScrimColor | `#33000000`，即 20% 黑色 |
| material() | 按主题选择 `DialogGlassLight` / `DialogGlassDark`，模糊输入为 60dp |
| stroke() | 使用浅色 / 深色小描边作为首版 bloom 近似 |
| containerColor() | 主题背景色，用于不透明回退 |

`glassDialogSurface(backdrop, material, stroke, containerColor)` 支持覆盖这些参数；背景为空或运行时 shader 不可用时使用不透明回退。圆角由宿主传入，此表面关闭折射及额外投影。

材质按以下顺序混色：

| 层 | 浅色 | 深色 |
| --- | --- | --- |
| 1 | `#05000000 / PlusDarker` | `#1A000000 / PlusDarker` |
| 2 | `#99FFFFFF / SoftLight` | `#996C6C6C / Luminosity` |
| 3 | `#99FFFFFF / HardLight` | `#99060606 / SoftLight` |

当前实现先采样干净的页面，在局部采样中混入宿主的实时遮罩，再模糊和混色。传入的背景应排除遮罩。原生遮罩采样顺序、模糊核换算及 bloom 仍需视觉校准，这是首版还原。

#### 对话框操作按钮

`GlassDialogButton` 直接绘制在父级对话框材质上，不接收背景采样。普通和警示按钮通过半透明填色透出模糊背景，主操作按钮使用主题主色填充。

| `GlassDialogButtonRole` | 填色 | 内容颜色 |
| --- | --- | --- |
| `Normal`（默认） | 主题 `onBackground`，浅色 15/255、深色 36/255 不透明度 | `onBackground`，浅色 90%、深色 80% |
| `Primary` | `primary` | `onPrimary` |
| `Destructive` | 与 `Normal` 相同 | `error` |

默认中性主题下，普通填色为 `#0F000000` / `#24FFFFFF`。按下时在填色和内容上方共同覆盖 `#1F000000` / `#24FFFFFF`，按下、松开和取消时即时更新，不额外添加涟漪、模糊或缩放。

| 参数 | 默认值 / 行为 |
| --- | --- |
| `onClick` | 必填操作回调 |
| `modifier` | `Modifier`；宽度和位置由调用方决定 |
| `enabled` | `true`；禁用时无按压覆盖层，也不触发回调 |
| `role` | `GlassDialogButtonRole.Normal` |
| `cornerRadius` | `GlassDialogButtonDefaults.CornerRadius` = 16dp |
| `minWidth` / `minHeight` | `GlassDialogButtonDefaults.MinWidth` / `MinHeight` = 0dp / 50dp |
| `colors` | `GlassDialogButtonDefaults.buttonColors(role)`，返回 `ButtonColors` |
| `pressedColor` | `GlassDialogButtonDefaults.pressedColor()`，按当前主题选择 |
| `insideMargin` | `GlassDialogButtonDefaults.InsideMargin` = 水平 24dp |
| `interactionSource` | 可选 `MutableInteractionSource` |
| `content` | 居中的 `RowScope` 插槽；文字使用 `MiuixTheme.textStyles.button` |

禁用的普通和警示按钮保留半透明填色，内容不透明度分别为 38% 和 30%。禁用主按钮使用 30% 主色填充，`onPrimary` 在浅色模式保持不透明，深色模式使用 77/255 不透明度。主色与警示色的 RGB 跟随 Compose 主题。当前水平内边距始终为 24dp；原生 Flutter 会在文字缩放从 1 增至 3 时，把水平内边距从 24 插值到 12、再到 6。

### 底部抽屉材质

桌面原生 bottomsheet 和 dialog 引用了完全相同的浅深色三层混色表，模糊输入同为 60dp。贴底形态只圆顶部。`glassBottomSheetSurface` 通过 `OverlayBottomSheet.surface` 接入这套材质：

```kotlin
val sheetBackdrop = rememberLayerBackdrop()
var showSheet by remember { mutableStateOf(false) }

Scaffold(contentWindowInsets = WindowInsets(0)) {
    Scaffold(
        modifier = Modifier.fillMaxSize().layerBackdrop(sheetBackdrop),
        topBar = { /* 页面顶部栏 */ },
        bottomBar = { /* 页面底部栏 */ },
    ) { padding ->
        TextButton(
            text = "显示底部抽屉",
            onClick = { showSheet = true },
            modifier = Modifier.padding(padding),
        )
        OverlayBottomSheet(
            show = showSheet,
            title = "Glass Bottom Sheet",
            onDismissRequest = { showSheet = false },
            dimColor = GlassBottomSheetDefaults.ScrimColor,
            surface = glassBottomSheetSurface(backdrop = sheetBackdrop),
        ) {
            Column(Modifier.verticalScroll(rememberScrollState())) {
                Text("可滚动的内容清晰地绘制在材质上方")
            }
        }
    }
}
```

保持 `renderInRootScaffold = true`，让外层 Scaffold 在采样区域之外承载 sheet。已有的干净页面背景可以和 dialog 共用；不要把任何浮层或其遮罩录入背景。

| GlassBottomSheetDefaults | 默认值 / 行为 |
| --- | --- |
| `ScrimColor` | `#33000000`，即 20% 黑色，由宿主驱动动画 |
| `material()` | 按主题选择 `DialogGlassLight` / `DialogGlassDark`；原生 sheet 共用这些模糊和颜色输入 |
| `stroke()` | 使用 `MiddleLight` / `MiddleDark` 作为首版 bloom 近似 |
| `containerColor()` | 主题背景色，用于不透明回退 |

`glassBottomSheetSurface(backdrop, material, stroke, containerColor)` 支持参数覆盖。背景为空或运行时 shader 不可用时使用回退。宿主通过 `BottomSheetSurfaceScope` 提供顶部圆角及绘制阶段读取的实时遮罩。上拉回弹时，露出的底部区域也会补上相同材质，使用直角并关闭描边。滑入和拖动时按 sheet 当前屏幕位置采样；内容、拖动条、系统插入内边距、嵌套滚动及关闭行为仍由宿主负责。

此辅助组件面向贴底的 `OverlayBottomSheet`，不实现原生浮动 sheet 形态或分段停靠。原生 sheet 的 bloom 配置与 dialog 不同；bloom 映射、模糊核换算及遮罩采样顺序仍需视觉校准。当前实现不添加折射或额外投影。

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
