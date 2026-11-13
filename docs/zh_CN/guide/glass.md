# 玻璃表面

`miuix-glass` 为 Compose Multiplatform 提供玻璃表面与组件：在[材质定义](/zh_CN/guide/material)之上实现 `glassPanel`、`glass`、`glassShadow`、`glassSurface`，支持背景模糊、颜色混合与光照效果。

::: note 说明
`miuix-glass` 的 Android 端 `minSdk` 为 24。玻璃效果依赖 `RuntimeShader`（Android 13 及以上）；低于该版本时表面退化为不透明的 `containerColor`，见[运行时能力检测](/zh_CN/guide/material#运行时能力检测)。

注意 `miuix-glass` 依赖 `miuix-blur`，后者自身仍声明 `minSdk` 33：低于 API 33 的应用需要在清单中加入 `tools:overrideLibrary="top.yukonga.miuix.kmp.blur"`，并且不要期望模糊层生效。
:::

## 配置

在项目中添加 `miuix-glass` 依赖：

```kotlin
kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation("top.yukonga.miuix.kmp:miuix-glass:<version>")
        }
    }
}
```

Android 项目：

```kotlin
dependencies {
    implementation("top.yukonga.miuix.kmp:miuix-glass-android:<version>")
}
```

`miuix-glass` 会传递引入 `miuix-material`、`miuix-blur` 与 `miuix-ui`。

Glass 图标由可选的 `miuix-glass-icons` 模块提供，通过 `MiuixIcons.Glass` 访问，参见[图标系统](/zh_CN/guide/icons)。

## 基本用法

创建 backdrop，通过 `layerBackdrop` 记录背景，再对独立表面应用 `glassPanel`：

```kotlin
import androidx.compose.ui.graphics.luminance
import top.yukonga.miuix.kmp.blur.layerBackdrop
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
import top.yukonga.miuix.kmp.glass.glassPanel
import top.yukonga.miuix.kmp.material.GlassDefaults
import top.yukonga.miuix.kmp.material.GlassEdgeStrokes
import top.yukonga.miuix.kmp.material.GlassMaterials
import top.yukonga.miuix.kmp.material.GlassShape

val backdrop = rememberLayerBackdrop()
val isDark = MiuixTheme.colorScheme.background.luminance() < 0.5f

Box(modifier = Modifier.fillMaxSize()) {
    // 记录不透明背景，不包含玻璃面板。
    Box(
        modifier = Modifier
            .matchParentSize()
            .layerBackdrop(backdrop)
            .background(MiuixTheme.colorScheme.background),
    ) {
        Text("页面内容", modifier = Modifier.padding(24.dp))
    }
    Box(
        modifier = Modifier
            .align(Alignment.Center)
            .size(240.dp, 120.dp)
            .glassPanel(
                backdrop = backdrop,
                shape = GlassShape(24.dp),
                style = GlassDefaults.style(isDark),
                material = GlassMaterials.puredThinGlass(isDark),
                stroke = GlassEdgeStrokes.forTheme(isDark),
                shading = false,
            )
            .padding(24.dp),
    ) {
        Text("玻璃面板")
    }
}
```

### 背景采样 {#background-sampling}

`layerBackdrop` 仅记录自身 composable 的内容，不包含父级背景。采样区域应包含不透明背景，并排除玻璃表面，避免自采样。

对话框和底部抽屉还须排除自身遮罩，由表面函数自动应用。需要采样页面栏时，可记录页面 Scaffold，由外层 Scaffold 承载浮层。见[背景采样 API](/zh_CN/guide/blur#基本用法)。

## 材质配置

`GlassStyle`、`GlassStyles`、`GlassMaterial`、`GlassMaterials`、`GlassShape`、`GlassShadow`、`GlassShadows` 与 `GlassDefaults` 位于 `miuix-material` 模块（`top.yukonga.miuix.kmp.material`）。属性与预设见[材质定义](/zh_CN/guide/material#材质定义)，描边见[边缘描边](/zh_CN/guide/material#边缘描边)。

## 修饰符 API

### glassPanel

`Modifier.glassPanel` 组合材质、裁剪、边缘描边与投影：

| 参数名 | 类型 | 说明 | 默认值 | 是否必须 |
| --- | --- | --- | --- | --- |
| backdrop | Backdrop? | 背景采样，传入 null 时使用 fallback | - | 是 |
| shape | GlassShape | 表面、描边与投影共享的形状 | - | 是 |
| style | GlassStyle | 光学处理样式 | GlassDefaults.Style | 否 |
| alpha | Float | 颜色层、光照、描边与投影的透明度乘数 | 1f | 否 |
| material | GlassMaterial? | 模糊半径与主体颜色层；null 保留 style 的模糊，不叠加 GlassMaterial 颜色层 | null | 否 |
| stroke | EdgeStroke? | 边缘描边（`width`/`bevel` 为 `Dp`） | null | 否 |
| shadow | GlassShadow? | 投影 | GlassShadows.Regular | 否 |
| shading | Boolean | 是否应用折射与光照 | true | 否 |
| fallback | Modifier | backdrop 为空时使用的表面 | Modifier.clip(shape) | 否 |

平面玻璃栏和菜单可使用 `shading = false`，独立的 `stroke` 仍有效。默认 `fallback` 仅裁剪，不添加填色。

有 backdrop 时，`alpha` 影响材质透明度，不影响模糊和前景内容；无 backdrop 时，它作用于整个回退层。

### glass

`Modifier.glass` 绘制玻璃材质，不附加投影：

| 参数名 | 类型 | 说明 | 默认值 | 是否必须 |
| --- | --- | --- | --- | --- |
| backdrop | Backdrop | 背景采样 | - | 是 |
| shape | GlassShape | 表面轮廓 | - | 是 |
| style | GlassStyle | 光学处理样式 | GlassDefaults.Style | 否 |
| alpha | Float | 颜色层、光照与描边的透明度乘数，不会关闭模糊或淡出内容 | 1f | 否 |
| tint | Color | 覆盖样式的 tint，alpha 控制着色强度 | Color.Unspecified | 否 |
| material | GlassMaterial? | 模糊半径与主体颜色层；null 保留 style 的模糊，不叠加 GlassMaterial 颜色层 | null | 否 |
| noiseCoefficient | Float | 模糊抖动系数，0f 关闭 | GlassDefaults.NoiseCoefficient | 否 |
| stroke | EdgeStroke? | 边缘描边（`width`/`bevel` 为 `Dp`） | null | 否 |
| contentBlendMode | BlendMode | 前景内容的混合模式 | BlendMode.SrcOver | 否 |
| enabled | Boolean | 是否绘制玻璃效果 | true | 否 |
| shading | Boolean | 是否应用折射与光照 | true | 否 |

`enabled = false` 时，只绘制组件内容。需要跳过背景模糊及其他玻璃效果时，应使用该开关，而不是 `alpha = 0f`。

### glassShadow

`Modifier.glassShadow` 只绘制投影，应与材质使用相同的形状：

| 参数名 | 类型 | 说明 | 默认值 | 是否必须 |
| --- | --- | --- | --- | --- |
| shape | GlassShape | 投影形状 | - | 是 |
| shadow | GlassShadow? | 投影预设，null 关闭绘制 | GlassShadows.Regular | 否 |
| alpha | Float | 投影透明度乘数 | 1f | 否 |

### glassSurface

`glassSurface` 返回 `@Composable (Shape) -> Modifier`，可传入兼容浮层的 `surface` 插槽：

```kotlin
import top.yukonga.miuix.kmp.glass.GlassPopupDefaults
import top.yukonga.miuix.kmp.glass.glassSurface

val surface = glassSurface(
    backdrop = backdrop,
    visuals = GlassPopupDefaults.visuals(),
)
```

| 参数名 | 类型 | 说明 | 默认值 | 是否必须 |
| --- | --- | --- | --- | --- |
| backdrop | Backdrop? | 背景采样，null 使用容器填色 | - | 是 |
| visuals | GlassPopupVisuals | 弹窗样式、材质、描边、投影与回退颜色 | - | 是 |
| cornerRadius | Dp | 宿主形状不能转换时使用的圆角半径 | GlassPopupDefaults.CornerRadius | 否 |

该函数保留 `RoundedCornerShape` 和 `GlassShape` 的四个圆角，不启用折射。外观由 [GlassPopupVisuals](/zh_CN/components/glass/glasspopup#shared-defaults) 配置。

宿主存在动画遮罩或缩放时，应使用 [glassDialogSurface](/zh_CN/components/glass/glassdialog#glassdialogsurface) 或 [glassBottomSheetSurface](/zh_CN/components/glass/glassbottomsheet#glassbottomsheetsurface)。

## GlassDefaults 对象

`GlassDefaults` 属于 `miuix-material`；其常量（`CornerRadius`、`Smoothing`、`SourceDensity`、`NoiseCoefficient`、`Overspill`、`Style`）与 `style(isDark)` 方法见 [GlassDefaults](/zh_CN/guide/material#glassdefaults-对象)。

## 进阶用法

### 自定义回退表面

没有背景采样时，可通过 `fallback` 为自定义面板设置不透明填色：

```kotlin
val shape = GlassShape(24.dp)

Box(
    modifier = Modifier
        .size(240.dp, 120.dp)
        .glassPanel(
            backdrop = null,
            shape = shape,
            fallback = Modifier
                .clip(shape)
                .background(MiuixTheme.colorScheme.surface),
        )
        .padding(24.dp),
) {
    Text("回退表面")
}
```
