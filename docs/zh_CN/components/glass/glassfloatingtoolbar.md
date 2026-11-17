# GlassFloatingToolbar

`GlassFloatingToolbar` 是承载工具栏操作的玻璃悬浮容器，通常配合 [Scaffold](../basic/scaffold) 使用。

<div style="position: relative; height: 240px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../../../compose/index.html?id=glassFloatingToolbar" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

::: warning 背景采样
采样区域应包含不透明背景，并排除玻璃表面自身。见[玻璃表面](/zh_CN/guide/glass#background-sampling)。
:::

## 引入

```kotlin
import top.yukonga.miuix.kmp.glass.GlassFloatingToolbar
```

## 基本用法

```kotlin
GlassFloatingToolbar(
    backdrop = backdrop,
    cornerRadius = 20.dp,
) {
    Row(
        modifier = Modifier.padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        IconButton(onClick = onEdit) {
            Icon(MiuixIcons.Edit, contentDescription = "编辑")
        }
        IconButton(onClick = onDelete) {
            Icon(MiuixIcons.Delete, contentDescription = "删除")
        }
    }
}
```

## 属性

### GlassFloatingToolbar 属性

| 属性名 | 类型 | 说明 | 默认值 | 是否必须 |
| --- | --- | --- | --- | --- |
| modifier | Modifier | 应用于工具栏的修饰符 | Modifier | 否 |
| backdrop | Backdrop? | 背景采样源 | null | 否 |
| cornerRadius | Dp | 用于构建 `shape` 的圆角半径 | GlassFloatingToolbarDefaults.CornerRadius | 否 |
| shape | GlassShape | 工具栏形状 | GlassShape(cornerRadius) | 否 |
| outSidePadding | PaddingValues | 工具栏外部边距 | GlassFloatingToolbarDefaults.OutSidePadding | 否 |
| surfaceAlpha | Float | 表面与阴影透明度乘数 | 1f | 否 |
| style | GlassStyle | 渲染样式 | GlassDefaults.Style | 否 |
| material | GlassMaterial | 提供 `backdrop` 时应用的玻璃材质 | GlassFloatingToolbarDefaults.material() | 否 |
| fill | Color | 没有背景采样源时使用的填充色 | GlassFloatingToolbarDefaults.fill() | 否 |
| stroke | EdgeStroke? | 表面描边，null 表示不绘制 | GlassFloatingToolbarDefaults.stroke() | 否 |
| shadow | GlassShadow? | 工具栏阴影，null 表示不绘制 | GlassShadows.Regular | 否 |
| content | @Composable () -> Unit | 工具栏内容 | - | 是 |

### GlassFloatingToolbarDefaults 对象

#### 常量

| 名称 | 类型 | 说明 | 默认值 |
| --- | --- | --- | --- |
| CornerRadius | Dp | 默认圆角半径 | 50.dp |
| OutSidePadding | PaddingValues | 默认外部边距 | PaddingValues(12.dp, 8.dp) |

#### 方法

| 名称 | 返回类型 | 说明 |
| --- | --- | --- |
| material() | GlassMaterial | 轻薄玻璃材质（`GlassMaterials.puredThinGlass`） |
| stroke() | EdgeStroke | 主题自适应边缘描边（`GlassEdgeStrokes.forTheme`） |
| fill() | Color | 回退填充色（`MiuixTheme.colorScheme.surfaceContainer`） |
