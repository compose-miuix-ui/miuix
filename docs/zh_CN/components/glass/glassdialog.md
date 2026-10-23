---
title: GlassDialog
requiresScaffoldHost: true
prerequisites:
  - 必须在 `Scaffold` 中使用以提供 `MiuixPopupHost`
  - 未在 `Scaffold` 中使用将导致弹出内容无法渲染
  - 支持多个嵌套或并列的 `Scaffold`，无需额外配置
hostComponent: Scaffold
popupHost: MiuixPopupHost
---

# GlassDialog

`GlassDialog` 是 Miuix 中用于确认操作和输入内容的玻璃对话框组件。它保留 `OverlayDialog` 的布局、动画和关闭行为，并提供配套的 `GlassDialogButton` 操作按钮。

<div style="position: relative; height: 440px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../../../compose/index.html?id=glassDialog" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

::: danger 前置条件
此组件依赖 Miuix `Scaffold` 提供的 `MiuixPopupHost`。必须在 `Scaffold` 中使用，否则弹出内容无法正常渲染。
:::

::: warning 背景采样
使用背景采样时，`backdrop` 应记录组件后方的不透明内容，不能包含使用该采样的玻璃表面。配置方式见[玻璃材质](/zh_CN/guide/glass#background-sampling)。
:::

## 引入

```kotlin
import top.yukonga.miuix.kmp.glass.GlassDialog
import top.yukonga.miuix.kmp.glass.GlassDialogDefaults
import top.yukonga.miuix.kmp.glass.GlassDialogButton
import top.yukonga.miuix.kmp.glass.GlassDialogButtonDefaults
import top.yukonga.miuix.kmp.glass.GlassDialogButtonRole
import top.yukonga.miuix.kmp.glass.glassDialogSurface
import top.yukonga.miuix.kmp.blur.layerBackdrop
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
```

## 基本用法

下例仅记录页面 `Box` 的内容，由 `Scaffold` 在采样范围之外承载浮层。

```kotlin
val backdrop = rememberLayerBackdrop()
var showDialog by remember { mutableStateOf(false) }

Scaffold { padding ->
    Box(
        Modifier.fillMaxSize()
            .layerBackdrop(backdrop)
            .background(MiuixTheme.colorScheme.background)
            .padding(padding),
    ) {
        TextButton(text = "显示对话框", onClick = { showDialog = true })
    }

    GlassDialog(
        show = showDialog,
        onDismissRequest = { showDialog = false },
        backdrop = backdrop,
        title = "放弃修改？",
        summary = "尚未保存的修改将丢失。",
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            GlassDialogButton(
                onClick = { showDialog = false },
                modifier = Modifier.weight(1f),
            ) { Text("取消", style = MiuixTheme.textStyles.button) }
            GlassDialogButton(
                onClick = { showDialog = false },
                modifier = Modifier.weight(1f),
                role = GlassDialogButtonRole.Destructive,
            ) { Text("放弃", style = MiuixTheme.textStyles.button) }
        }
    }
}
```

关闭时仍须保留组件调用，直到离场动画完成。不要使用 `if (show)` 包裹调用。

浮层可能覆盖页面顶栏或底栏时，应将这些栏包含在背景采样中，但排除弹窗宿主及其遮罩。背景采样为空或运行时着色器不可用时使用 `containerColor`，应提供不透明回退色。

## 属性

### GlassDialog 属性

| 属性名 | 类型 | 说明 | 默认值 | 是否必须 |
| --- | --- | --- | --- | --- |
| onDismissRequest | () -> Unit | 点击外部或完成返回手势时调用；更新 `show` 以关闭 | - | 是 |
| show | Boolean | 对话框的目标显示状态 | - | 是 |
| backdrop | Backdrop? | 不含弹窗的页面背景采样；null 使用不透明回退 | - | 是 |
| modifier | Modifier | 应用于对话框面板的修饰符 | Modifier | 否 |
| enableWindowDim | Boolean | 绘制并采样动画遮罩 | true | 否 |
| defaultWindowInsetsPadding | Boolean | 应用输入法、导航栏和标题栏内边距；顶部安全内边距始终保留 | true | 否 |
| renderInRootScaffold | Boolean | 使用最外层 Scaffold；false 使用当前 Scaffold | true | 否 |
| largeScreen | Boolean? | 使用居中缩放淡入淡出样式；null 根据窗口尺寸判断 | null | 否 |
| title | String? | 对话框标题 | null | 否 |
| titleColor | Color | 标题文本颜色 | DialogDefaults.titleColor() | 否 |
| summary | String? | 辅助说明 | null | 否 |
| summaryColor | Color | 说明文本颜色 | DialogDefaults.summaryColor() | 否 |
| cornerRadius | Dp? | 圆角覆盖值；null 使用宿主当前呈现方式的默认值 | null | 否 |
| maxWidth | Dp | 对话框最大宽度 | DialogDefaults.MaxWidth | 否 |
| outsideMargin | DpSize | 外部边距 | DialogDefaults.outsideMargin | 否 |
| insideMargin | DpSize | 内容内边距 | DialogDefaults.insideMargin | 否 |
| material | GlassMaterial | 模糊及有序颜色层 | GlassDialogDefaults.material() | 否 |
| stroke | GlassStroke? | 辉光描边；null 移除描边 | GlassDialogDefaults.stroke() | 否 |
| containerColor | Color | 不透明回退填充色 | GlassDialogDefaults.containerColor() | 否 |
| dimColor | Color | 应用动画进度之前的遮罩颜色 | GlassDialogDefaults.ScrimColor | 否 |
| onDismissFinished | (() -> Unit)? | 隐藏动画完成后调用；隐藏被取消时不调用 | null | 否 |
| content | @Composable () -> Unit | 在材质上方清晰绘制的对话框内容 | - | 是 |

### GlassDialogButton 属性 {#glassdialogbutton}

`GlassDialogButton` 在对话框材质上绘制操作按钮，不单独进行背景采样或模糊。它提供 `LocalContentColor`，文字可使用 `MiuixTheme.textStyles.button`。按钮行的宽度分配由调用方负责。单独使用此按钮不需要 `Scaffold`。

| 属性名 | 类型 | 说明 | 默认值 | 是否必须 |
| --- | --- | --- | --- | --- |
| onClick | () -> Unit | 启用时点击按钮的回调 | - | 是 |
| modifier | Modifier | 应用于按钮的修饰符 | Modifier | 否 |
| enabled | Boolean | 是否接受输入 | true | 否 |
| role | GlassDialogButtonRole | 操作按钮的配色样式 | GlassDialogButtonRole.Normal | 否 |
| cornerRadius | Dp | 填充、裁剪和按压反馈的圆角 | GlassDialogButtonDefaults.CornerRadius | 否 |
| minWidth | Dp | 按钮最小宽度 | GlassDialogButtonDefaults.MinWidth | 否 |
| minHeight | Dp | 按钮最小高度 | GlassDialogButtonDefaults.MinHeight | 否 |
| colors | ButtonColors | 启用和禁用时的填充及内容颜色 | GlassDialogButtonDefaults.buttonColors(role) | 否 |
| pressedColor | Color | 按压时的前景叠加色 | GlassDialogButtonDefaults.pressedColor() | 否 |
| insideMargin | PaddingValues | 内容内边距 | GlassDialogButtonDefaults.InsideMargin | 否 |
| interactionSource | MutableInteractionSource? | 用于观察或驱动交互的来源 | null | 否 |
| content | @Composable RowScope.() -> Unit | 居中的按钮内容 | - | 是 |

`GlassDialogButtonRole` 提供三个值：

| 值 | 说明 |
| --- | --- |
| Normal | 色调填充搭配普通内容颜色 |
| Primary | 主题主色填充及配套内容颜色 |
| Destructive | 色调填充搭配错误色内容 |

禁用按钮使用禁用颜色且不响应点击。按压反馈统一覆盖填充与内容，不使用涟漪或缩放动画。

### glassDialogSurface 属性 {#glassdialogsurface}

`glassDialogSurface` 返回 `@Composable DialogSurfaceScope.() -> Modifier`，用于 `OverlayDialog` 的 `surface` 插槽。它使用宿主解析后的圆角、动画遮罩与缩放，不创建宿主或处理关闭。

| 属性名 | 类型 | 说明 | 默认值 | 是否必须 |
| --- | --- | --- | --- | --- |
| backdrop | Backdrop? | 排除对话框宿主和遮罩的页面背景采样；null 使用回退 | - | 是 |
| material | GlassMaterial | 模糊及有序颜色层 | GlassDialogDefaults.material() | 否 |
| stroke | GlassStroke? | 辉光描边；null 移除描边 | GlassDialogDefaults.stroke() | 否 |
| containerColor | Color | 不透明回退填充色 | GlassDialogDefaults.containerColor() | 否 |

### GlassDialogDefaults 对象

#### 常量

| 常量名 | 类型 | 说明 | 值 |
| --- | --- | --- | --- |
| ScrimColor | Color | 宿主应用动画之前的遮罩颜色 | Color(0x33000000) |

#### 方法

| 方法名 | 返回类型 | 说明 |
| --- | --- | --- |
| material() | GlassMaterial | 随主题变化的 `GlassMaterials.dialogGlass` 材质 |
| stroke() | GlassStroke | 随主题选择 `GlassStrokes.SmallLight` 或 `SmallDark` 辉光描边 |
| containerColor() | Color | 用于不透明回退的主题背景色 |

### GlassDialogButtonDefaults 对象

#### 常量

| 常量名 | 类型 | 说明 | 值 |
| --- | --- | --- | --- |
| CornerRadius | Dp | 默认按钮圆角 | 16.dp |
| MinWidth | Dp | 默认最小宽度 | 0.dp |
| MinHeight | Dp | 默认最小高度 | 50.dp |
| InsideMargin | PaddingValues | 默认内容内边距 | PaddingValues(horizontal = 24.dp) |

#### 方法

| 方法名 | 返回类型 | 说明 |
| --- | --- | --- |
| buttonColors(role: GlassDialogButtonRole = GlassDialogButtonRole.Normal) | ButtonColors | 随主题和角色变化的启用及禁用颜色 |
| pressedColor() | Color | 主题 `onBackground`，浅色模式透明度为 `31/255`，深色为 `36/255` |

## 进阶用法

### 为现有对话框应用玻璃材质

已有 `OverlayDialog` 管理布局与显示状态时，可以使用表面工厂。沿用基本示例的 `showDialog` 和不含弹窗的 `backdrop`：

```kotlin
OverlayDialog(
    show = showDialog,
    onDismissRequest = { showDialog = false },
    dimColor = GlassDialogDefaults.ScrimColor,
    surface = glassDialogSurface(backdrop = backdrop),
) {
    GlassDialogButton(
        onClick = { showDialog = false },
        role = GlassDialogButtonRole.Primary,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text("确认", style = MiuixTheme.textStyles.button)
    }
}
```

表面会自行将宿主的动画遮罩应用于背景采样，并在缩放时保持采样对齐。不要将遮罩记录到背景采样中。`enableWindowDim = false` 会同时移除显示和采样中的变暗效果。内容保持清晰，此表面不添加折射或额外投影。

### 关闭与系统内边距

需要等待离场动画完成的操作可放入 `onDismissFinished`。若隐藏完成前 `show` 重新变为 `true`，则不会调用。应保留对话框组合直到动画完成，不要使用 `if (showDialog)` 包裹调用。

`defaultWindowInsetsPadding = true` 时，宿主应用输入法、导航栏和标题栏内边距。自行提供这些内边距时可设为 `false`，但顶部安全内边距（状态栏、标题栏和屏幕开孔顶部内边距的最大值）仍会保留。在嵌套 Scaffold 中，`renderInRootScaffold = false` 选择当前宿主，该宿主同样必须位于传入背景采样的记录范围之外。
