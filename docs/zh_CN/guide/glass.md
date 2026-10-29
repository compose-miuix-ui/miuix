# 玻璃材质

`miuix-glass` 为 Compose Multiplatform 提供玻璃材质和组件，支持背景模糊、颜色混合与光照效果。

::: warning 注意
Android 端需要 `minSdk` 33（Android 13）或更高。
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

Glass 图标由可选的 `miuix-glass-icons` 模块提供，通过 `MiuixIcons.Glass` 访问，参见[图标系统](/zh_CN/guide/icons)。

## 基本用法

创建 backdrop，通过 `layerBackdrop` 记录背景，再对独立表面应用 `glassPanel`：

```kotlin
import androidx.compose.ui.graphics.luminance
import top.yukonga.miuix.kmp.blur.layerBackdrop
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
import top.yukonga.miuix.kmp.glass.GlassDefaults
import top.yukonga.miuix.kmp.glass.GlassMaterials
import top.yukonga.miuix.kmp.glass.GlassShape
import top.yukonga.miuix.kmp.glass.GlassStrokes
import top.yukonga.miuix.kmp.glass.glassPanel

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

`layerBackdrop` 仅记录自身 composable 的内容，不包含父级背景。采样区域应包含不透明背景，并排除玻璃表面，避免自采样。

对话框和底部抽屉还须排除自身遮罩，由表面函数自动应用。需要采样页面栏时，可记录页面 Scaffold，由外层 Scaffold 承载浮层。见[背景采样 API](/zh_CN/guide/blur#基本用法)。

## 材质配置

### GlassStyle

使用 `GlassDefaults.style(isDark)` 获取随主题切换的折射、反射和光照样式，或从 `GlassStyles` 选择预设。`GlassDefaults.Style` 固定为 `CommonMediumRegularLowLight`。

### GlassMaterial

`GlassMaterial` 控制模糊后的主体及其颜色层。非空材质会覆盖 `GlassStyle` 请求的模糊半径。

| 属性名 | 类型 | 说明 | 默认值 | 是否必须 |
| --- | --- | --- | --- | --- |
| blurRadius | Dp | 背景模糊半径 | - | 是 |
| first | GlassColorLayer | 最接近背景的第一层颜色 | - | 是 |
| second | GlassColorLayer? | 第二层颜色 | null | 否 |
| third | GlassColorLayer? | 第三层颜色，要求 second 非空 | null | 否 |

每个 `GlassColorLayer` 指定 `Color` 和 `GlassColorBlendMode`，颜色层按顺序应用。

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

`smoothing` 控制着色器圆角（`1f`：连续圆角；`0f`：圆形圆角）。Compose 轮廓与裁剪使用圆角矩形。

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
