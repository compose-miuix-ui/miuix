# 玻璃材质

`miuix-glass` 是一个独立的 Compose Multiplatform 玻璃材质库。它通过 Modifier 扩展提供背景采样、模糊、颜色混合和边缘光照。支持 Android、Desktop (JVM)、iOS、macOS 和 Web (WasmJs/Js) 平台。

::: warning 注意
`miuix-glass` 在 Android 端需要 `minSdk` 33（Android 13）或更高。运行时能力检测不能降低 Android 构件的最低 SDK 要求。
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

`miuix-glass` 会传递引入 `miuix-blur` 与 `miuix-ui`。

## 基本用法

应用玻璃材质分为三步：

1. 创建 `LayerBackdrop` 以捕获背景内容
2. 在内容容器上应用 `Modifier.layerBackdrop()`
3. 在玻璃表面上应用 `Modifier.glassPanel()`

```kotlin
import androidx.compose.ui.graphics.luminance
import top.yukonga.miuix.kmp.blur.layerBackdrop
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
import top.yukonga.miuix.kmp.glass.GlassDefaults
import top.yukonga.miuix.kmp.glass.GlassMaterials
import top.yukonga.miuix.kmp.glass.GlassShape
import top.yukonga.miuix.kmp.glass.GlassStrokes
import top.yukonga.miuix.kmp.glass.glassPanel

// 第 1 步：创建 LayerBackdrop
val backdrop = rememberLayerBackdrop()
val isDark = MiuixTheme.colorScheme.background.luminance() < 0.5f

Box(modifier = Modifier.fillMaxSize()) {
    // 第 2 步：捕获背景内容
    Box(
        modifier = Modifier
            .matchParentSize()
            .layerBackdrop(backdrop)
            .background(MiuixTheme.colorScheme.background),
    ) {
        Text("页面内容", modifier = Modifier.padding(24.dp))
    }
    // 第 3 步：在叠加层上应用玻璃材质
    Box(
        modifier = Modifier
            .align(Alignment.Center)
            .size(240.dp, 120.dp)
            .glassPanel(
                backdrop = backdrop,
                shape = GlassShape(24.dp),
                style = GlassDefaults.style(isDark),
                material = GlassMaterials.puredThinGlass(isDark),
                stroke = GlassStrokes.forTheme(isDark),
                shading = false,
            )
            .padding(24.dp),
    ) {
        Text("玻璃面板")
    }
}
```

### 背景采样 {#background-sampling}

`layerBackdrop` 仅捕获其所在 composable 绘制的内容，**不包含**父级 composable 的背景（例如 Scaffold 的 Surface）。请在采样范围内绘制不透明背景，并将玻璃面板放在该范围之外。

对话框和底部抽屉的背景采样应排除自身面板及遮罩，专用表面函数会应用宿主当前的动画遮罩。需要同时采样页面顶栏、底栏时，可记录页面 Scaffold，由外层 Scaffold 承载浮层。背景内容的记录方式见[背景采样 API](/zh_CN/guide/blur#基本用法)。

## 材质配置

### GlassStyle

`GlassStyle` 控制折射、反射、边缘光照和颜色处理。使用 `GlassDefaults.style(isDark)` 选择默认的浅色或深色样式，也可从 `GlassStyles` 的 `Common*`、`Bionic*`、`Tint*` 系列中选择预设。

`GlassDefaults.Style` 固定为 `CommonMediumRegularLowLight`，不会读取当前主题。

### GlassMaterial

`GlassMaterial` 控制模糊后的主体及其颜色层。非空材质会覆盖 `GlassStyle` 请求的模糊半径。

| 属性名 | 类型 | 说明 | 默认值 | 是否必须 |
| --- | --- | --- | --- | --- |
| blurRadius | Dp | 背景模糊半径 | - | 是 |
| first | GlassColorLayer | 最接近背景的第一层颜色 | - | 是 |
| second | GlassColorLayer? | 第二层颜色 | null | 否 |
| third | GlassColorLayer? | 第三层颜色，要求 second 非空 | null | 否 |

每个 `GlassColorLayer` 包含 `color: Color` 和 `mode: GlassColorBlendMode`，颜色层按顺序应用。混合模式包括 `SrcOver`、`PlusDarker`、`PlusLighter`、`SoftLight`、`HardLight`、`Overlay`、`Luminosity`、`ColorDodge` 与 `ColorBurn`。

`GlassMaterials` 提供随主题选择的预设：

| 方法名 | 返回类型 | 说明 | 模糊半径 |
| --- | --- | --- | --- |
| puredThinGlass(isDark) | GlassMaterial | 悬浮栏材质 | 20.dp |
| popupViewGlass(isDark) | GlassMaterial | 展开的弹窗材质 | 60.dp |
| dialogGlass(isDark) | GlassMaterial | 对话框与贴底抽屉材质 | 60.dp |
| actionBarMask(isDark) | GlassMaterial | 顶栏遮挡背景带 | 浅色 40.dp / 深色 60.dp |

每个预设都有对应的 `*Light` 与 `*Dark` 值。需要修改预设时，可使用 `copy`：

```kotlin
val material = GlassMaterials.puredThinGlass(isDark).copy(
    blurRadius = 32.dp,
)
```

### GlassShape

使用 `GlassShape(cornerRadius)` 设置统一圆角，也可为四个角分别指定半径：

```kotlin
val shape = GlassShape(
    topStart = 24.dp,
    topEnd = 24.dp,
    bottomEnd = 0.dp,
    bottomStart = 0.dp,
)
```

起始端和结束端随布局方向变化。主构造函数还接受 `CornerSize`，可通过百分比圆角创建胶囊形状。

`smoothing` 默认为 `1f`，在玻璃着色器中使用连续圆角；`0f` 使用圆形圆角。普通 Compose 轮廓与 `Modifier.clip` 仍使用圆角矩形。

### 描边与投影

使用 `GlassStrokes.forTheme(isDark)` 获取与主题匹配的边缘描边，设置 `stroke = null` 可移除描边。`GlassShadows` 提供 `Low`、`Regular`、`High`、`ExtraHigh` 与 `Float` 预设，设置 `shadow = null` 可移除投影。

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
| stroke | GlassStroke? | 边缘描边 | null | 否 |
| shadow | GlassShadow? | 投影 | GlassShadows.Regular | 否 |
| shading | Boolean | 是否应用折射与光照 | true | 否 |
| fallback | Modifier | backdrop 为空时使用的表面 | Modifier.clip(shape) | 否 |

`shading = true` 适合需要折射的控件，`false` 适合平面玻璃栏或菜单。关闭 shading 后，不应用样式中的折射、反射和边缘光照；独立的 `stroke` 仍控制辉光描边。默认的回退表面只裁剪，不绘制背景填色。

背景采样非空时，`alpha` 不会关闭模糊，也不会淡出前景内容。背景采样为空时，它会淡出整个回退层，包括内容。

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
| stroke | GlassStroke? | 边缘描边 | null | 否 |
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

### 常量

| 常量名 | 类型 | 说明 | 默认值 |
| --- | --- | --- | --- |
| CornerRadius | Dp | 默认圆角半径 | 24.dp |
| Smoothing | Float | 默认圆角平滑度 | 1f |
| SourceDensity | Float | 像素样式与投影令牌的源密度 | 3f |
| NoiseCoefficient | Float | 模糊抖动系数 | BlurDefaults.NoiseCoefficient |
| Overspill | Float | 遮罩裁切前的材质外扩 | 3f |
| Style | GlassStyle | 默认光学预设 | GlassStyles.CommonMediumRegularLowLight |

样式与投影中以像素定义的值按 `density / SourceDensity` 缩放。

### 方法

| 方法名 | 返回类型 | 说明 |
| --- | --- | --- |
| style(isDark: Boolean) | GlassStyle | 选择 CommonMediumRegularLowLight 或 CommonMediumRegularDark |

`GlassMotion` 提供 Glass 组件使用的动画预设。

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

该回退由空 backdrop 触发。具有运行时能力处理的组件还会提供各自的回退行为，具体见组件参数说明。
