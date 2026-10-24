---
title: GlassTransformPopup
---

# GlassTransformPopup

`GlassTransformPopup` 从参与变换的控件生长出菜单。控件材质与展开菜单材质在变换过程中混合，锚点内容由菜单内容接替显示。

<div style="position: relative; height: 380px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../../../compose/index.html?id=glassTransformPopup" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

::: warning 背景采样
使用背景采样时，`backdrop` 应记录组件后方的不透明内容，不能包含使用该采样的玻璃表面。配置方式见[玻璃材质](/zh_CN/guide/glass#background-sampling)。
:::

## 引入

```kotlin
import top.yukonga.miuix.kmp.glass.GlassIconButton
import top.yukonga.miuix.kmp.glass.GlassTransformPopup
import top.yukonga.miuix.kmp.glass.GlassPopupItem
import top.yukonga.miuix.kmp.glass.rememberGlassPopupAnchor
import top.yukonga.miuix.kmp.glass.glassPopupAnchor
import top.yukonga.miuix.kmp.glass.glassPopupAnchorContent
```

## 基本用法

此组件是 `BoxScope` 扩展，不依赖 `MiuixPopupHost`。应将其放在整页 `Box` 的最后，并置于裁剪容器之外；组件自行拦截外部点击。锚点坐标必须与弹窗所在 `Box` 的坐标系一致。

锚点修饰符直接记录 `boundsInRoot()`，不会转换到宿主 Box。因此弹窗 Box 的原点必须与坐标根原点重合，不应放入带 inset 内边距或偏移的内容区。系统栏和 IME insets 不会自动应用；`safeMargin` 相对于该 Box 计算。返回处理需要 `LocalNavigationEventDispatcherOwner`；没有该 owner 时仍可通过外部点击关闭。

关闭时仍须保留组件调用，直到离场动画完成。不要使用 `if (show)` 包裹调用。

为控件和菜单使用同一个记忆锚点。控件带有自己的背景时，只标记需要复制的内容：

```kotlin
val anchor = rememberGlassPopupAnchor()
var show by remember { mutableStateOf(false) }

Box(Modifier.fillMaxSize()) {
    GlassIconButton(
        onClick = { show = true },
        backdrop = backdrop,
        modifier = Modifier.glassPopupAnchor(anchor, cornerRadius = 20.dp),
    ) {
        Text("⋯", modifier = Modifier.glassPopupAnchorContent(anchor))
    }
    GlassTransformPopup(
        show = show,
        onDismissRequest = { show = false },
        anchor = anchor,
        backdrop = backdrop,
        anchorContent = { Text("⋯") },
    ) {
        GlassPopupItem(text = "重命名", onClick = { show = false })
        GlassPopupItem(text = "删除", onClick = { show = false })
    }
}
```

未使用 `glassPopupAnchorContent` 时，`anchorContent` 代表整个控件。`anchorContent` 的视觉内容应与已记录的锚点内容一致。

## 属性

### GlassTransformPopup 属性

| 属性名 | 类型 | 说明 | 默认值 | 是否必须 |
| --- | --- | --- | --- | --- |
| show | Boolean | 菜单是否打开；退场期间仍须保留调用 | - | 是 |
| onDismissRequest | () -> Unit | 外部点击或返回请求时调用 | - | 是 |
| anchor | GlassPopupAnchor | 已测量的控件与共享材质锚点 | - | 是 |
| backdrop | Backdrop? | 展开菜单场景；null 先回退到关联按钮的 backdrop，两者均不存在时使用 visuals.containerColor | - | 是 |
| anchorContent | @Composable () -> Unit | 控件整体或标记内容的视觉副本 | - | 是 |
| modifier | Modifier | 作用于面板的修饰符 | Modifier | 否 |
| simplified | Boolean | 普通展开时不绘制向外的内容副本；收起和预测性返回时副本仍参与变换 | false | 否 |
| stacked | Boolean | 二级菜单位于前方时缩小并覆盖当前菜单，当前菜单行停止输入 | false | 否 |
| sizing | GlassPopupSizing | 宽度、高度和安全边距限制 | GlassPopupSizing() | 否 |
| visuals | GlassPopupVisuals | 展开面板外观 | GlassPopupDefaults.visuals() | 否 |
| anchorAlpha | Float | 没有共享按钮表面时的锚点背景透明度 | 1f | 否 |
| maskColor | Color | 叠层时覆盖一级菜单的颜色 | GlassPopupDefaults.maskColor() | 否 |
| cornerRadius | Dp | 稳定面板圆角 | GlassPopupDefaults.CornerRadius（24.dp） | 否 |
| gap | Dp | 计算稳定面板位置前，将锚点向下偏移的距离；并非保证的边到边间距 | 0.dp | 否 |
| contentPadding | PaddingValues | 菜单行周围的内边距 | PaddingValues(vertical = GlassPopupDefaults.ContentPaddingVertical) | 否 |
| onMeasured | ((Size) -> Unit)? | 以像素传回稳定面板尺寸 | null | 否 |
| content | @Composable ColumnScope.() -> Unit | 菜单行 | - | 是 |

### 默认值

尺寸、外观和 token 见 [GlassPopup 共享默认值](./glasspopup#shared-defaults)。`show && !stacked` 时菜单行接受输入；保留的关闭行仅供显示。预测性返回预览反向变换，取消后恢复；叠层期间由二级菜单处理返回。
## 进阶用法

### 锚点 API {#anchor-api}

`GlassPopupAnchor()` 有公开的无参构造函数，但 `rememberGlassPopupAnchor(): GlassPopupAnchor` 还负责下拉值的淡出动画。应使用该 `@Composable` 工厂，每组控件/菜单关系使用独立锚点。以下修饰符记录根坐标范围：

```kotlin
import top.yukonga.miuix.kmp.glass.glassPopupAnchorRow
import top.yukonga.miuix.kmp.glass.glassPopupAnchorValue
```

| API | 类型 | 说明 | 默认值 | 是否必须 |
| --- | --- | --- | --- | --- |
| rememberGlassPopupAnchor() | GlassPopupAnchor | 记住供变换或下拉菜单使用的锚点；无参数 | - | - |
| Modifier.glassPopupAnchor(anchor, cornerRadius, floating) | Modifier | 记录控件外层、变换时隐藏控件，并发布 GlassIconButton 的共享材质 | floating = false | anchor, cornerRadius |
| Modifier.glassPopupAnchorContent(anchor) | Modifier | 只记录要复制的内容范围；省略时复制整个控件 | - | anchor |
| Modifier.glassPopupAnchorRow(anchor) | Modifier | 记录下拉行范围，不隐藏整行；下拉菜单仍须显式传入 anchorBounds | - | anchor |
| Modifier.glassPopupAnchorValue(anchor) | Modifier | 记录并淡出下拉值与箭头 | - | anchor |
| anchor.secondaryBackProgress | Float | 只读的二级返回进度，包含取消恢复和保留退场 | 0f | 否 |

需要让子菜单箭头跟随预测性返回时，应在绘制或图层回调中读取 `secondaryBackProgress`：

```kotlin
val anchor = rememberGlassPopupAnchor()
GlassIconButton(
    onClick = { show = true },
    modifier = Modifier.glassPopupAnchor(anchor, cornerRadius = 20.dp, floating = true),
) {
    Text("⋯", modifier = Modifier.glassPopupAnchorContent(anchor))
}
```

对于没有共享按钮表面的锚点，`floating = true` 会让面板透明度在变换中从 `anchorAlpha` 混合到完全显示；页面内面板则跟随图标材质过渡。参与共享的 `GlassIconButton` 会发布其实际 backdrop、样式、材质、底层材质、描边、填充和表面透明度；这些值构成收起端点，`anchorAlpha` 不会覆盖它们。存在共享表面时，弹窗根据按钮发布的表面（`surfaceFloating || surfaceAlpha > 0f`）判断悬浮行为，而不是使用锚点修饰符的 `floating` 标志。展开端点始终使用 `visuals`；显式 backdrop 为 null 时，先使用按钮发布的 backdrop，仍不存在时才回退到 `visuals.containerColor`。

