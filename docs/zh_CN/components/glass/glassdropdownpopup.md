---
title: GlassDropdownPopup
---

# GlassDropdownPopup

`GlassDropdownPopup` 是用于从设置行展示选项的玻璃下拉菜单。

<div style="position: relative; height: 380px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../../../compose/index.html?id=glassDropdownPopup" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

::: warning 背景采样
使用背景采样时，`backdrop` 应记录组件后方的不透明内容，不能包含使用该采样的玻璃表面。配置方式见[玻璃材质](/zh_CN/guide/glass#background-sampling)。
:::

## 引入

```kotlin
import top.yukonga.miuix.kmp.glass.GlassDropdownPopup
import top.yukonga.miuix.kmp.glass.GlassPopupItem
import top.yukonga.miuix.kmp.glass.rememberGlassPopupAnchor
import top.yukonga.miuix.kmp.glass.glassPopupAnchorRow
import top.yukonga.miuix.kmp.glass.glassPopupAnchorValue
```

## 基本用法

- 将弹窗放在坐标根处的整页 `Box` 最后，避免父级裁剪。
- 按需提供系统栏和输入法内边距；返回处理需要 `LocalNavigationEventDispatcherOwner`。
- 用 `show` 控制显隐，不要用 `if (show)` 包裹调用。

记录整行以及值/箭头内容，协调下拉变换：

```kotlin
val anchor = rememberGlassPopupAnchor()
val items = listOf("名称", "日期", "大小")
var selected by remember { mutableIntStateOf(0) }
var show by remember { mutableStateOf(false) }
var rowBounds by remember { mutableStateOf(Rect.Zero) }

Box(Modifier.fillMaxSize()) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .glassPopupAnchorRow(anchor)
            .onGloballyPositioned { rowBounds = it.boundsInRoot() }
            .clickable { show = true }
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text("排序方式")
        Text(items[selected], modifier = Modifier.glassPopupAnchorValue(anchor))
    }
    GlassDropdownPopup(
        show = show,
        onDismissRequest = { show = false },
        anchorBounds = rowBounds,
        backdrop = backdrop,
        anchor = anchor,
    ) {
        items.forEachIndexed { index, text ->
            GlassPopupItem(
                text = text,
                selected = selected == index,
                onClick = { selected = index; show = false },
            )
        }
    }
}
```

## 属性

### GlassDropdownPopup 属性

| 属性名 | 类型 | 说明 | 默认值 | 是否必须 |
| --- | --- | --- | --- | --- |
| show | Boolean | 面板是否打开；为 false 时立即停止菜单行输入与无障碍操作 | - | 是 |
| onDismissRequest | () -> Unit | 外部点击或返回请求时调用 | - | 是 |
| anchorBounds | Rect | 设置行在当前弹窗 Box 坐标系中的范围 | - | 是 |
| backdrop | Backdrop? | 面板背后的场景；null 使用保留描边与投影的不透明填充 | - | 是 |
| modifier | Modifier | 作用于面板的修饰符 | Modifier | 否 |
| anchor | GlassPopupAnchor? | 可选的值对齐与淡出协调锚点；不提供 anchorBounds、backdrop 或外观 | null | 否 |
| sizing | GlassPopupSizing | 宽度、高度和安全边距限制 | GlassPopupSizing() | 否 |
| visuals | GlassPopupVisuals | 面板外观 | GlassPopupDefaults.visuals() | 否 |
| cornerRadius | Dp | 稳定下拉圆角 | GlassDropdownDefaults.CornerRadius（24.dp） | 否 |
| contentPadding | PaddingValues | 菜单行周围的内边距 | PaddingValues(vertical = GlassPopupDefaults.ContentPaddingVertical) | 否 |
| content | @Composable ColumnScope.() -> Unit | 选项行，通常为带 selected 的 GlassPopupItem | - | 是 |

### 默认值

尺寸和外观选项见 [GlassPopup 共享默认值](./glasspopup#shared-defaults)。

## 进阶用法

### 值对齐与自定义外观

使用 `glassPopupAnchorValue` 可让弹窗水平对齐显示值；垂直定位仍使用 `anchorBounds`。省略此修饰符时，水平和垂直定位均使用 `anchorBounds`。`backdrop` 为 null 时使用 `visuals.containerColor`。

```kotlin
val sizing = GlassPopupSizing(maxWidth = 320.dp, maxHeight = 480.dp)
val visuals = GlassPopupDefaults.visuals(shadow = null)

GlassDropdownPopup(
    show = show,
    onDismissRequest = { show = false },
    anchorBounds = rowBounds,
    backdrop = backdrop,
    anchor = anchor,
    sizing = sizing,
    visuals = visuals,
) {
    GlassPopupItem(text = items[selected], selected = true, onClick = { show = false })
}
```

行和值修饰符见 [`GlassTransformPopup` 锚点 API](./glasstransformpopup#anchor-api)。

