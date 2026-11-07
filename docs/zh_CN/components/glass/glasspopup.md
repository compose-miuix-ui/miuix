---
title: GlassPopup
---

# GlassPopup

`GlassPopup` 是以测量得到的控件为锚点的玻璃菜单。

<div style="position: relative; height: 380px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../../../compose/index.html?id=glassPopup" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

::: warning 背景采样
使用背景采样时，`backdrop` 应记录组件后方的不透明内容，不能包含使用该采样的玻璃表面。配置方式见[玻璃材质](/zh_CN/guide/glass#background-sampling)。
:::

## 引入

```kotlin
import top.yukonga.miuix.kmp.glass.GlassPopup
import top.yukonga.miuix.kmp.glass.GlassPopupItem
```

## 基本用法

- 将弹窗放在整页 `Box` 的最后，避免父级裁剪。锚点须使用该 Box 的坐标；宿主有偏移时需转换 `boundsInRoot()`。
- 按需提供系统栏和输入法内边距；返回处理需要 `LocalNavigationEventDispatcherOwner`。
- 用 `show` 控制显隐，不要用 `if (show)` 包裹调用。

在与弹窗相同的坐标根中测量触发控件，并在菜单项中更新状态：

```kotlin
var show by remember { mutableStateOf(false) }
var anchorBounds by remember { mutableStateOf(Rect.Zero) }

Box(Modifier.fillMaxSize()) {
    TextButton(
        text = "操作",
        onClick = { show = true },
        modifier = Modifier.onGloballyPositioned {
            anchorBounds = it.boundsInRoot()
        },
    )
    GlassPopup(
        show = show,
        onDismissRequest = { show = false },
        anchorBounds = anchorBounds,
        backdrop = backdrop,
    ) {
        GlassPopupItem(text = "分享", icon = MiuixIcons.Glass.Share, onClick = { show = false })
        GlassPopupItem(text = "收藏", selected = true, onClick = { show = false })
        GlassPopupItem(text = "更多选项", summary = "详细配置", showArrow = true, onClick = { show = false })
        GlassPopupItem(text = "不可用操作", enabled = false, onClick = {})
    }
}
```

### 菜单项（GlassPopupItem）

`GlassPopupItem` 用于构建交互菜单行，支持图标、摘要、选中勾选和子菜单箭头。

```kotlin
GlassPopupItem(
    text = "排序方式",
    summary = "按名称",
    showArrow = true,
    onClick = { /* 打开二级菜单 */ },
)
```

## 属性

### GlassPopup 属性

| 属性名 | 类型 | 说明 | 默认值 | 是否必须 |
| --- | --- | --- | --- | --- |
| show | Boolean | 菜单是否打开；退场期间仍须保留调用 | - | 是 |
| onDismissRequest | () -> Unit | 外部点击或返回请求时调用 | - | 是 |
| anchorBounds | Rect | 控件在当前弹窗 Box 坐标系中的范围 | - | 是 |
| backdrop | Backdrop? | 面板背后的场景；null 使用不透明回退填充 | - | 是 |
| modifier | Modifier | 作用于面板的修饰符 | Modifier | 否 |
| sizing | GlassPopupSizing | 宽度、高度和安全边距限制 | GlassPopupSizing() | 否 |
| visuals | GlassPopupVisuals | 面板外观 | GlassPopupDefaults.visuals() | 否 |
| cornerRadius | Dp | 面板稳定状态的圆角 | GlassPopupDefaults.CornerRadius（24.dp） | 否 |
| secondary | Boolean | 使用二级菜单几何；需要继承一级菜单材质时应使用 GlassSecondaryPopup | false | 否 |
| contentPadding | PaddingValues | 菜单行周围的内边距 | PaddingValues(vertical = GlassPopupDefaults.ContentPaddingVertical) | 否 |
| content | @Composable ColumnScope.() -> Unit | 菜单行 | - | 是 |

### GlassPopupItem 属性 {#glasspopupitem}

| 属性名 | 类型 | 说明 | 默认值 | 是否必须 |
| --- | --- | --- | --- | --- |
| text | String | 单行菜单标签 | - | 是 |
| onClick | () -> Unit | 启用菜单行被点击时调用 | - | 是 |
| modifier | Modifier | 作用于菜单行的修饰符 | Modifier | 否 |
| icon | ImageVector? | 可选前置图标 | null | 否 |
| summary | String? | 可选的单行第二标签 | null | 否 |
| enabled | Boolean | 菜单行是否响应点击 | true | 否 |
| selected | Boolean | 显示末尾勾；启用时标签和前置图标使用选中颜色 | false | 否 |
| showArrow | Boolean | 显示末尾子菜单箭头 | false | 否 |
| arrowRotation | () -> Float | 箭头旋转角度 | { 0f } | 否 |
| contentColor | Color | 标签和图标颜色 | MiuixTheme.colorScheme.onSurfaceContainer | 否 |
| summaryColor | Color | 摘要和箭头颜色 | MiuixTheme.colorScheme.onSurfaceContainerVariant | 否 |
| selectedContentColor | Color | 已启用选中行的标签和前置图标颜色；选中勾在禁用时也使用此色 | MiuixTheme.colorScheme.primary | 否 |
| pressedColor | Color | 按压时菜单行后的填充色 | GlassPopupDefaults.itemPressedColor() | 否 |

### GlassPopupSizing 属性

| 属性名 | 类型 | 说明 | 默认值 | 是否必须 |
| --- | --- | --- | --- | --- |
| minWidth | Dp | 面板最小宽度 | GlassPopupDefaults.MinWidth | 否 |
| maxWidth | Dp | 面板最大宽度 | GlassPopupDefaults.MaxWidth | 否 |
| maxHeight | Dp | 面板最大高度，超过后菜单行滚动 | GlassPopupDefaults.MaxHeight | 否 |
| safeMargin | Dp | 与页面边缘的最小距离 | GlassPopupDefaults.SafeMargin | 否 |


### GlassPopupVisuals 属性

`GlassPopupDefaults.visuals()` 提供按主题选择的样式、透明度、描边、投影、材质和回退色默认值。

| 属性名 | 类型 | 说明 | 默认值 | 是否必须 |
| --- | --- | --- | --- | --- |
| style | GlassStyle | 材质未指定模糊半径时使用的模糊样式 | - | 是 |
| alpha | Float | 表面透明度乘数；GlassTransformPopup 还将其应用于菜单内容 | - | 是 |
| stroke | GlassStroke? | 辉光描边；null 移除描边 | - | 是 |
| shadow | GlassShadow? | 面板投影；null 移除投影 | - | 是 |
| material | GlassMaterial? | 面板颜色处理和可选模糊半径；null 移除颜色处理，而非背景采样或模糊 | - | 是 |
| containerColor | Color | backdrop 为 null 时使用的不透明填充 | - | 是 |

backdrop 非 null 时使用材质或样式模糊。`material = null` 会移除颜色处理，但不会移除采样、模糊、描边或投影。最终 backdrop 为 null 时使用 `containerColor`，同时保留描边和投影。

### GlassPopupDefaults 对象 {#shared-defaults}

`GlassPopupDefaults` 提供弹窗尺寸、菜单行、表面和叠层的共用默认值。

#### 常量

| 常量名 | 类型 | 说明 | 值 |
| --- | --- | --- | --- |
| `MinWidth` | `Dp` | 面板最小宽度 | `200.dp` |
| `MaxWidth` | `Dp` | 面板最大宽度 | `288.dp` |
| `MaxHeight` | `Dp` | 菜单行开始滚动前的高度 | `416.dp` |
| `SafeMargin` | `Dp` | 页面边缘安全边距 | `12.dp` |
| `ContentPaddingVertical` | `Dp` | 面板垂直内边距 | `8.dp` |
| `CornerRadius` | `Dp` | 普通弹窗圆角 | `24.dp` |
| `DropdownCornerRadius` | `Dp` | 下拉弹窗圆角 | `24.dp` |
| `ItemMinHeight` | `Dp` | 菜单行最小高度 | `44.dp` |
| `ItemPaddingHorizontal` | `Dp` | 菜单行水平内边距 | `20.dp` |
| `ItemPaddingVertical` | `Dp` | 菜单行垂直内边距 | `10.dp` |
| `ItemIconSize` | `Dp` | 前置图标大小 | `24.dp` |
| `ItemIconGap` | `Dp` | 菜单行图标间距 | `8.dp` |
| `ItemTickSize` | `Dp` | 选中勾大小 | `24.dp` |
| `ItemArrowSize` | `Dp` | 子菜单箭头大小 | `16.dp` |
| `ItemPressInset` | `Dp` | 按压填充内缩 | `7.dp` |
| `ItemPressRadius` | `Dp` | 按压填充圆角 | `15.dp` |
| `DividerPaddingHorizontal` | `Dp` | 分割线水平内缩 | `20.dp` |
| `DividerPaddingVertical` | `Dp` | 分割线垂直间距 | `7.75.dp` |
| `DividerThickness` | `Dp` | 分割线厚度 | `0.5.dp` |

#### 方法

| 方法名 | 参数 | 返回类型 | 说明 |
| --- | --- | --- | --- |
| `visuals` | `style: GlassStyle = GlassDefaults.Style`、`alpha: Float = 1f`、`stroke: GlassStroke? = stroke()`、`shadow: GlassShadow? = GlassShadows.Regular`、`material: GlassMaterial? = material()`、`containerColor: Color = containerColor()` | `GlassPopupVisuals` | 使用按主题选择的默认值创建弹窗外观 |
| `shape` | `cornerRadius: Dp = CornerRadius` | `GlassShape` | 创建弹窗轮廓 |
| `material` | — | `GlassMaterial` | 返回按主题选择的弹窗材质 |
| `stroke` | — | `GlassStroke` | 返回按主题选择的辉光描边 |
| `containerColor` | — | `Color` | 浅色主题为白色，深色主题为 `Color(0xFF2C2C2C)` |
| `itemPressedColor` | — | `Color` | 浅色主题为 `0.1f` 透明度的黑色，深色主题为同透明度白色 |
| `maskColor` | — | `Color` | 浅色主题为 `0.4f` 透明度的白色，深色主题为同透明度黑色 |

以上方法均为 `@Composable` 并使用当前主题。

### GlassDropdownDefaults 对象

#### 常量

| 常量名 | 类型 | 说明 | 值 |
| --- | --- | --- | --- |
| `CornerRadius` | `Dp` | 下拉菜单稳定圆角 | `24.dp` |
| `StartCornerRadius` | `Dp` | 下拉菜单开始展开时的圆角 | `4.dp` |

## 进阶用法

多个菜单需要统一尺寸或外观时，可以共享 `sizing` 和 `visuals`。没有 backdrop 时，仍会在不透明回退填充外保留描边和投影：

```kotlin
val sizing = GlassPopupSizing(maxWidth = 320.dp, maxHeight = 480.dp)
val visuals = GlassPopupDefaults.visuals(
    stroke = null,
    shadow = null,
    containerColor = MiuixTheme.colorScheme.background,
)

GlassPopup(
    show = show,
    onDismissRequest = { show = false },
    anchorBounds = anchorBounds,
    backdrop = backdrop,
    sizing = sizing,
    visuals = visuals,
) {
    GlassPopupItem(text = "设置", onClick = { show = false })
}
```

需要继承变换中一级菜单的展开材质和返回进度时，不要设置 `secondary = true`，应改用 [`GlassSecondaryPopup`](./glasssecondarypopup)。

