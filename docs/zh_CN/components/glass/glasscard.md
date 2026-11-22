---
title: GlassCard
---

# GlassCard

`GlassCard` 是用于玻璃表面（如 [GlassBottomSheet](./glassbottomsheet) 或 [GlassDialog](./glassdialog)）内容的 [Card](../basic/card)。它在玻璃材质之上绘制半透明浅色填充，而不是不透明的表面色，使模糊后的页面在每个分组中仍然可见。

<div style="position: relative; height: 360px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../../../compose/index.html?id=glassCard" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

::: tip 材质
`GlassCard` 本身不采样也不模糊背景，其填充依赖下方的玻璃表面；放在不透明页面上时只会呈现为一张淡色卡片。
:::

## 引入

```kotlin
import top.yukonga.miuix.kmp.glass.GlassCard
import top.yukonga.miuix.kmp.glass.GlassCardDefaults
```

## 基本用法

将卡片放在玻璃表面的内容中：

```kotlin
GlassBottomSheet(
    show = showSheet,
    onDismissRequest = { showSheet = false },
    backdrop = backdrop,
    title = "壁纸",
) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        GlassCard(
            modifier = Modifier.fillMaxWidth(),
            insideMargin = PaddingValues(horizontal = 16.dp, vertical = 18.dp),
            onClick = { /* 处理点击 */ },
        ) {
            Text("从相册选择")
        }
        GlassCard(modifier = Modifier.fillMaxWidth()) {
            ArrowPreference(title = "历史壁纸", onClick = {})
        }
    }
}
```

## 属性

### GlassCard 属性

| 属性名 | 类型 | 说明 | 默认值 | 是否必须 |
| --- | --- | --- | --- | --- |
| modifier | Modifier | 应用于卡片的修饰符 | Modifier | 否 |
| cornerRadius | Dp | 卡片圆角半径 | GlassCardDefaults.CornerRadius | 否 |
| insideMargin | PaddingValues | 卡片内边距 | GlassCardDefaults.InsideMargin | 否 |
| colors | CardColors | 填充色与内容色 | GlassCardDefaults.colors() | 否 |
| pressFeedbackType | PressFeedbackType | 按压反馈类型（可交互重载） | PressFeedbackType.None | 否 |
| showIndication | Boolean | 交互时是否显示指示效果（可交互重载） | false | 否 |
| holdDownState | Boolean | 卡片是否处于按下状态（可交互重载） | false | 否 |
| onClick | (() -> Unit)? | 点击回调（可交互重载） | null | 否 |
| onLongPress | (() -> Unit)? | 长按回调（可交互重载） | null | 否 |
| content | @Composable ColumnScope.() -> Unit | 卡片内容 | - | 是 |

### GlassCardDefaults 对象

#### 常量

| 常量名 | 类型 | 说明 | 值 |
| --- | --- | --- | --- |
| CornerRadius | Dp | 默认圆角半径 | 20.dp |
| InsideMargin | PaddingValues | 默认内边距 | CardDefaults.InsideMargin |

#### 方法

| 方法名 | 返回类型 | 说明 |
| --- | --- | --- |
| color() | Color | 基于当前主题配色方案派生的半透明填充 |
| colors(color, contentColor) | CardColors | 卡片颜色；默认使用 `color()` 与 `onSurfaceContainer` |

## 进阶用法

### 自定义填充

```kotlin
GlassCard(
    colors = GlassCardDefaults.colors(color = Color(0x33FFFFFF)),
) {
    Text("更强的填充")
}
```
