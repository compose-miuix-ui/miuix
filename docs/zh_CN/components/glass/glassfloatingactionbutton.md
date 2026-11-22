# GlassFloatingActionButton

`GlassFloatingActionButton` 是圆形悬浮操作按钮，玻璃表面默认使用主题主色。按钮按下时缩放至 90%，松手或取消按压时回弹。

<div style="position: relative; height: 240px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../../../compose/index.html?id=glassFloatingActionButton" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

::: warning 背景采样
采样区域应包含不透明背景，并排除玻璃表面自身。见[玻璃表面](/zh_CN/guide/glass#background-sampling)。
:::

## 引入

```kotlin
import top.yukonga.miuix.kmp.glass.GlassFloatingActionButton
```

## 基本用法

提供背景采样源时，按钮在模糊玻璃上叠加半透明主色。无法渲染采样背景时，使用主题主色作为回退填充。

```kotlin
GlassFloatingActionButton(
    onClick = onAction,
    backdrop = backdrop,
) {
    Icon(
        imageVector = actionIcon,
        tint = MiuixTheme.colorScheme.onPrimary,
        contentDescription = "添加",
    )
}
```

## 属性

### GlassFloatingActionButton 属性

| 属性名 | 类型 | 说明 | 默认值 | 是否必须 |
| --- | --- | --- | --- | --- |
| onClick | () -> Unit | 点击时触发的回调 | - | 是 |
| modifier | Modifier | 应用于按钮的修饰符 | Modifier | 否 |
| backdrop | Backdrop? | 背景采样源 | null | 否 |
| surfaceAlpha | Float | 表面透明度乘数 | 1f | 否 |
| style | GlassStyle | 渲染样式 | GlassDefaults.Style | 否 |
| material | GlassMaterial | 提供 `backdrop` 时应用的玻璃材质 | GlassFloatingActionButtonDefaults.material() | 否 |
| size | Dp | 按钮直径 | GlassFloatingActionButtonDefaults.Size | 否 |
| shape | GlassShape | 按钮形状 | GlassShape(size / 2) | 否 |
| fill | Color | 无法渲染采样背景时使用的填充色 | GlassFloatingActionButtonDefaults.fill() | 否 |
| stroke | EdgeStroke? | 表面描边，null 表示不绘制 | GlassFloatingActionButtonDefaults.stroke() | 否 |
| shadow | GlassShadow? | 按钮阴影，null 表示不绘制 | GlassShadows.Regular | 否 |
| content | @Composable () -> Unit | 居中显示的按钮内容 | - | 是 |

### GlassFloatingActionButtonDefaults 对象

#### 常量

| 名称 | 类型 | 说明 | 默认值 |
| --- | --- | --- | --- |
| Size | Dp | 按钮直径 | 56.dp |

#### 方法

| 名称 | 返回类型 | 说明 |
| --- | --- | --- |
| material() | GlassMaterial | 20.dp 模糊、两层 PuredThin 混合，以及不透明度为 92% 的主色层 |
| fill() | Color | 回退填充色（`MiuixTheme.colorScheme.primary`） |
| stroke() | EdgeStroke | 与 `GlassTopAppBarDefaults.buttonStroke()` 一致的边缘描边 |
