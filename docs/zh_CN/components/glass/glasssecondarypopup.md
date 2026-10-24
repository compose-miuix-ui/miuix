---
title: GlassSecondaryPopup
---

# GlassSecondaryPopup

`GlassSecondaryPopup` 从一级菜单中的一行生长出二级菜单。裁剪区域从触发行（包括垂直 `contentPadding`）开始，扩展到测量得到的面板；菜单行尺寸保持不变。起始边与触发行对齐，在 RTL 中镜像；空间不足时向上平移，而不是翻转展开方向。

<div style="position: relative; height: 440px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../../../compose/index.html?id=glassSecondaryPopup" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

::: warning 背景采样
使用背景采样时，`backdrop` 应记录组件后方的不透明内容，不能包含使用该采样的玻璃表面。配置方式见[玻璃材质](/zh_CN/guide/glass#background-sampling)。
:::

## 引入

```kotlin
import top.yukonga.miuix.kmp.glass.GlassIconButton
import top.yukonga.miuix.kmp.glass.GlassTransformPopup
import top.yukonga.miuix.kmp.glass.GlassSecondaryPopup
import top.yukonga.miuix.kmp.glass.GlassPopupItem
import top.yukonga.miuix.kmp.glass.GlassPopupSizing
import top.yukonga.miuix.kmp.glass.GlassPopupDefaults
import top.yukonga.miuix.kmp.glass.rememberGlassPopupAnchor
import top.yukonga.miuix.kmp.glass.glassPopupAnchor
import top.yukonga.miuix.kmp.glass.glassPopupAnchorContent
```

## 基本用法

此组件是 `BoxScope` 扩展，不依赖 `MiuixPopupHost`。应将其放在整页 `Box` 的最后，并置于裁剪容器之外；组件自行拦截外部点击。锚点坐标必须与弹窗所在 `Box` 的坐标系一致。

使用下方 `boundsInRoot()` 示例时，两级弹窗 Box 的原点必须与共享坐标根原点重合。宿主偏移时，显式 `anchorBounds` 应转换到宿主 Box 的局部坐标；变换中的一级菜单仍要求宿主原点与根重合。系统栏和 IME insets 不会自动应用；`safeMargin` 相对于宿主计算。返回处理需要 `LocalNavigationEventDispatcherOwner`；没有该 owner 时仍可通过外部点击关闭。

关闭时仍须保留组件调用，直到离场动画完成。不要使用 `if (show)` 包裹调用。

第一个 backdrop 记录页面。第二个 backdrop 记录页面与一级菜单，二级菜单放在该采样层之外：

```kotlin
val anchor = rememberGlassPopupAnchor()
val secondaryBackdrop = rememberLayerBackdrop()
val density = LocalDensity.current
var show by remember { mutableStateOf(false) }
var secondary by remember { mutableStateOf(false) }
var stacked by remember { mutableStateOf(false) }
var rowBounds by remember { mutableStateOf(Rect.Zero) }
var frozenBounds by remember { mutableStateOf(Rect.Zero) }
var primaryWidth by remember { mutableStateOf(GlassPopupDefaults.MinWidth) }

Box(Modifier.fillMaxSize()) {
    Box(Modifier.fillMaxSize().layerBackdrop(secondaryBackdrop)) {
        Box(Modifier.fillMaxSize().background(MiuixTheme.colorScheme.background)) {
            GlassIconButton(
                onClick = { show = true },
                backdrop = backdrop,
                modifier = Modifier.glassPopupAnchor(anchor, cornerRadius = 20.dp),
            ) {
                Text("⋯", modifier = Modifier.glassPopupAnchorContent(anchor))
            }
        }
        GlassTransformPopup(
            show = show,
            onDismissRequest = { show = false },
            anchor = anchor,
            backdrop = backdrop,
            anchorContent = { Text("⋯") },
            stacked = stacked,
            onMeasured = { primaryWidth = with(density) { it.width.toDp() } },
        ) {
            GlassPopupItem(
                text = "排序方式",
                showArrow = true,
                arrowRotation = { if (stacked) 90f * (1f - anchor.secondaryBackProgress) else 0f },
                modifier = Modifier.onGloballyPositioned {
                    if (!stacked) rowBounds = it.boundsInRoot()
                },
                onClick = {
                    frozenBounds = rowBounds
                    stacked = true
                    secondary = true
                },
            )
        }
    }
    GlassSecondaryPopup(
        show = secondary,
        onDismissRequest = { secondary = false },
        anchorBounds = frozenBounds,
        backdrop = secondaryBackdrop,
        materialAnchor = anchor,
        sizing = GlassPopupSizing(minWidth = primaryWidth),
        onDismissFinished = { stacked = false },
    ) {
        GlassPopupItem(text = "返回", onClick = { secondary = false })
        GlassPopupItem(text = "名称", onClick = { secondary = false })
        GlassPopupItem(text = "日期", onClick = { secondary = false })
    }
}
```

## 属性

### GlassSecondaryPopup 属性

| 属性名 | 类型 | 说明 | 默认值 | 是否必须 |
| --- | --- | --- | --- | --- |
| show | Boolean | 二级菜单是否打开；收起期间仍须保留调用 | - | 是 |
| onDismissRequest | () -> Unit | 外部点击或返回时调用，回到一级菜单 | - | 是 |
| anchorBounds | Rect | 当前弹窗坐标根中冻结的触发行静止范围 | - | 是 |
| backdrop | Backdrop? | 包含页面和一级菜单但排除本菜单的场景；null 先尝试 materialAnchor 的按钮 backdrop，仍不存在时使用解析后外观的 containerColor | - | 是 |
| modifier | Modifier | 作用于面板的修饰符 | Modifier | 否 |
| materialAnchor | GlassPopupAnchor? | 共享一级锚点的展开外观与返回进度 | null | 否 |
| sizing | GlassPopupSizing | 面板限制；minWidth 应设置为一级面板宽度 | GlassPopupSizing() | 否 |
| visuals | GlassPopupVisuals | 仅在 materialAnchor 未发布展开弹窗外观时使用；已发布外观即使 material = null 也优先 | GlassPopupDefaults.visuals() | 否 |
| cornerRadius | Dp | 变换期间的面板圆角 | GlassPopupDefaults.CornerRadius（24.dp） | 否 |
| contentPadding | PaddingValues | 菜单行周围的内边距；仅上下内边距扩展收起端触发行裁剪区域 | PaddingValues(vertical = GlassPopupDefaults.ContentPaddingVertical) | 否 |
| onDismissFinished | (() -> Unit)? | 打开后收起动画完成时调用；初始关闭状态不调用 | null | 否 |
| content | @Composable ColumnScope.() -> Unit | 请求收起的标题行及后续选项 | - | 是 |

### 默认值

尺寸、外观和菜单行 token 见 [GlassPopup 共享默认值](./glasspopup#shared-defaults)。

## 进阶用法

### 叠层与关闭

在设置 `stacked = true` 前冻结触发行；从缩小的一级菜单读取坐标会让二级面板偏离来源。使用 `onMeasured` 测量一级宽度，并将它传给二级 `minWidth`。在 `onDismissFinished` 前保持一级菜单 stacked：

```kotlin
GlassSecondaryPopup(
    show = secondary,
    onDismissRequest = { secondary = false },
    anchorBounds = frozenBounds,
    backdrop = secondaryBackdrop,
    materialAnchor = anchor,
    sizing = GlassPopupSizing(minWidth = primaryWidth),
    onDismissFinished = { stacked = false },
) {
    GlassPopupItem(text = "返回", onClick = { secondary = false })
}
```

二级菜单行在展开期间接受输入；请求关闭后立即停止指针输入与无障碍操作。预测性返回预览收起，取消后恢复，提交后关闭。`secondaryBackProgress` 的说明见 [`GlassTransformPopup` 锚点 API](./glasstransformpopup#anchor-api)。

外观先使用一级锚点发布的展开弹窗外观，再回退到 `visuals`；背景先使用显式 `backdrop`，再尝试锚点按钮的 backdrop，最后使用解析后外观的 `containerColor` 填充。`materialAnchor` 不会继承一级菜单显式传入的展开背景，因此应显式传入二级背景采样层。尺寸、`cornerRadius` 和 `contentPadding` 不会继承。material 为 null 只移除其颜色处理，存在 backdrop 时仍有基于样式的模糊；见[共享默认值](./glasspopup#shared-defaults)。

