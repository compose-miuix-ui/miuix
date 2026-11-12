# 材质

`miuix-material` 是 Miuix 的渲染原语与材质定义层：运行时着色器抽象、共享 AGSL 片段、玻璃的样式 / 材质 / 形状 / 投影定义，以及边缘描边材质。`miuix-blur`、`miuix-squircle` 与 `miuix-glass` 都构建在它之上。

该模块的 `minSdk` 为 24；真正绘制运行时着色器的路径在运行时判定，见[运行时能力检测](#运行时能力检测)。

## 配置

```kotlin
kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation("top.yukonga.miuix.kmp:miuix-material:<version>")
        }
    }
}
```

仅 Android 项目：

```kotlin
dependencies {
    implementation("top.yukonga.miuix.kmp:miuix-material-android:<version>")
}
```

`miuix-material` 已由 `miuix-blur`、`miuix-squircle` 与 `miuix-glass` 传递包含；只有直接使用其类型时才需要显式声明。

## 渲染原语

### 运行时能力检测

| 函数 | 含义 |
| --- | --- |
| `isRuntimeShaderSupported()` | 可以编译并绘制 AGSL/SkSL 着色器（Android 13+；Skia 后端恒为 true） |
| `isRenderEffectSupported()` | 可以使用 `RenderEffect` 链（Android 12+） |

### RuntimeShader

`RuntimeShader` 是已编译着色器的跨平台句柄：先设置 uniform，再作为 Compose `Shader` 或 `ShaderBrush` 使用。

```kotlin
val shader = RuntimeShader(source)
shader.setFloatUniform("in_size", width, height)
drawRect(brush = shader.asBrush())
```

需要 `Shader` 而非画刷时用 `asComposeShader()`。Android 上底层着色器可变，因此返回缓存的画刷；Skiko 上每次 `asBrush()` 都会快照当前 uniform。

### RuntimeShaderCache

`RuntimeShaderCache` 按 key 复用已编译的着色器，避免逐帧重编译。`LocalRuntimeShaderCache` 为每个 composition 提供一个；`DefaultRuntimeShaderCache` 是脱离 composition 时使用的独立内存实现。

```kotlin
val shader = LocalRuntimeShaderCache.current.obtainRuntimeShader("MyShader", source)
```

### MaterialAgsl

`MaterialAgsl` 暴露所有材质着色器共享的两个 AGSL 片段，二者写在同一套 uniform 契约上——`in_size`、`in_radii`（左上、右上、右下、左下）与 `in_smoothing`：

| 片段 | 提供 |
| --- | --- |
| `SilhouetteSdf` | `roundedBoxSdf`、`supercircleSdf`、`pickRadius`、`sdfShape`、`silhouetteSdf`（形状内部为负） |
| `RimLighting` | `luma`、`smooth5Map`、`edgeCurve`、`edgeCurveSlope`、`sdfGradient`、`liftToward`、`dynamicAdd`、`lightFalloff`、`rimLighten` |

在自己的着色器里引入它们，即可与内置材质保持几何一致。

## 材质定义

### GlassStyle

使用 `GlassDefaults.style(isDark)` 获取随主题切换的折射、反射和光照样式，或从 `GlassStyles` 选择预设。`GlassDefaults.Style` 固定为 `CommonMediumRegularLowLight`。

### GlassMaterial

`GlassMaterial` 控制模糊体及其颜色层。非空材质会覆盖 `GlassStyle` 请求的模糊半径。

| 属性名 | 类型 | 说明 | 默认值 | 必需 |
| --- | --- | --- | --- | --- |
| blurRadius | Dp | 背景模糊半径 | - | 是 |
| first | GlassColorLayer | 第一层颜色，最贴近背景 | - | 是 |
| second | GlassColorLayer? | 第二层颜色 | null | 否 |
| third | GlassColorLayer? | 第三层颜色，需第二层非空 | null | 否 |

每个 `GlassColorLayer` 指定一个 `Color` 与 `GlassColorBlendMode`，各层按顺序叠加。

`GlassMaterials` 提供随主题切换的预设：

| 函数名 | 返回类型 | 说明 | 模糊半径 |
| --- | --- | --- | --- |
| puredThinGlass(isDark) | GlassMaterial | 悬浮栏材质 | 20.dp |
| popupViewGlass(isDark) | GlassMaterial | 展开弹层材质 | 60.dp |
| dialogGlass(isDark) | GlassMaterial | 对话框与挂载底部抽屉材质 | 60.dp |
| actionBarMask(isDark) | GlassMaterial | 顶栏遮罩带 | 浅色 40.dp / 深色 60.dp |

每个预设都有对应的 `*Light` 与 `*Dark` 值。调整预设时使用 `copy`：

```kotlin
val material = GlassMaterials.puredThinGlass(isDark).copy(
    blurRadius = 32.dp,
)
```

`withUnderlayBlur(underlay)` 把两层材质合并到同一次背景过滤中：模糊方差按平方和相加，一次采样即可得到两层的模糊范围。

### GlassShape

`GlassShape` 是裁剪、边缘描边与投影共用的轮廓。等半径用 `GlassShape(cornerRadius)`，非对称表面则给全四个角：

```kotlin
val shape = GlassShape(
    topStart = 24.dp,
    topEnd = 24.dp,
    bottomEnd = 0.dp,
    bottomStart = 0.dp,
)
```

起止角遵循布局方向。主构造函数同样接受 `CornerSize`，包括用于胶囊形状的百分比圆角。

`smoothing` 控制着色器圆角（`1f`：连续圆角；`0f`：圆形圆角）。Compose 轮廓与裁剪使用圆角矩形。

### GlassShadow

`GlassShadows` 提供 `Low`、`Regular`、`High`、`ExtraHigh` 与 `Float` 预设投影，按 `density / GlassDefaults.SourceDensity` 缩放。修饰符的 `shadow` 传 `null` 即移除投影。

## 边缘描边

边缘描边沿形状轮廓绘制受光的边缘：一条平直描边带，加上由两路定向光照亮的半球法线。

### EdgeStroke

| 属性名 | 类型 | 说明 | 默认值 | 必需 |
| --- | --- | --- | --- | --- |
| width | Dp | 平直描边带宽度 | - | 是 |
| bevel | Dp | 内侧光晕深度 | - | 是 |
| color | Color | 平直描边颜色，alpha 控制强度 | - | 是 |
| primary | EdgeLightSource | 第一路光 | - | 是 |
| secondary | EdgeLightSource | 第二路光 | - | 是 |
| blendMode | BlendMode | 描边合成到内容上的方式 | BlendMode.Plus | 否 |
| peak | EdgePeak | 每路光在轮廓上的门控方式 | EdgePeak.Vertical | 否 |

`EdgeLightSource` 用归一化 UV 定位光源：着色器归一化 `(x - 0.5, y - 0.7, z)`，因此 `(0.5, 0.7)` 是参考原点、不产生贡献；负的 `z` 把光放在表面平面之后，照亮朝内的边缘。`color` 的 alpha 与 `intensity` 都会缩放贡献。

| EdgePeak | 门控 |
| --- | --- |
| `Vertical` | 固定垂直轴：第一路光负责轮廓上半，第二路负责下半 |
| `Axial` | 门控跟随每路光自身的 XY 方向，受光弧随光移动 |
| `Dual` | `dot(n.xy, L.xy)²`：一路光产生两个相隔 180° 的峰值 |

### Modifier.edgeStroke

在绘制表面的修饰符之后追加描边。绘制在模糊背景之上时：

```kotlin
import androidx.compose.foundation.shape.RoundedCornerShape
import top.yukonga.miuix.kmp.blur.blur
import top.yukonga.miuix.kmp.blur.drawBackdrop
import top.yukonga.miuix.kmp.material.EdgeStrokes
import top.yukonga.miuix.kmp.material.edgeStroke

val shape = RoundedCornerShape(24.dp)

Box(
    modifier = Modifier
        .size(200.dp, 100.dp)
        .drawBackdrop(
            backdrop = backdrop,
            shape = { shape },
            effects = { blur(20.dp.toPx()) },
        )
        .edgeStroke(
            shape = shape,
            stroke = EdgeStrokes.MiddleLight,
            alpha = 1f, // 例如按压进度
        ),
)
```

该修饰符接收普通形状；传入 `GlassShape` 时其 `smoothing` 同样生效。`alpha = 0f`（或 `width = 0.dp` 的描边）会关闭描边。若已在持有正确边界的 `DrawScope` 内，可直接调用 `drawEdgeStroke(shape, stroke, alpha)`。

### 预设

| 预设 | 所属系列 | 说明 |
| --- | --- | --- |
| `EdgeStrokes` | OS3 风格 bloom | `BigLight` / `MiddleLight` / `SmallLight` / `BigDark` / `MiddleDark` / `SmallDark`，另有 `Default`（等同 `MiddleLight`）。全部使用 `EdgePeak.Axial` |
| `GlassEdgeStrokes` | 玻璃系列 | 同名六个，另有 `forTheme(isDark)`。全部使用 `EdgePeak.Vertical` |

命名读作 `{Big,Middle,Small}{Light,Dark}`：尺寸决定光晕深度，后缀决定主题。两个对象都直接给出完整的 `EdgeStroke`，调整时使用 `copy`。

### 倾斜光

`rememberTiltLight(base, sensitivity)` 返回一个 UV 位置跟随设备旋转传感器的 `EdgeLightSource`，形成随设备方向"流动"的边缘视差。无传感器的平台上，光固定在 `base`。

```kotlin
import top.yukonga.miuix.kmp.material.EdgeLightSource
import top.yukonga.miuix.kmp.material.rememberTiltLight

val primary = rememberTiltLight(
    base = EdgeLightSource(0.5f, 0.5f, -0.5f, intensity = 0.4f),
    sensitivity = 0.1f, // 每弧度倾斜对应的 UV 偏移
)
```

`sensitivity = 0.1f` 表示在 1 rad 倾斜下光位置移动描边边界的 10%。光在水平方向移动时应使用 `EdgePeak.Axial`，让受光弧跟随它。

### 行为说明

- 着色器输出预乘的光强：alpha 与光本身的大小一致，因此叠加通道不会被更低的 alpha 裁掉，再由 `BlendMode.Plus` 加到表面上。
- 平直描边带带半像素偏置，因此极小的 `width` 仍至少得到一个抗锯齿像素。

## GlassDefaults 对象

### 常量

| 常量名 | 类型 | 说明 | 默认值 |
| --- | --- | --- | --- |
| CornerRadius | Dp | 默认圆角半径 | 24.dp |
| Smoothing | Float | 默认圆角平滑度 | 1f |
| SourceDensity | Float | 像素样式与投影令牌的源密度 | 3f |
| NoiseCoefficient | Float | 模糊抖动系数 | 0.0045f |
| Overspill | Float | 遮罩裁切前的材质外扩 | 3f |
| Style | GlassStyle | 默认光学预设 | GlassStyles.CommonMediumRegularLowLight |

样式与投影中以像素定义的值按 `density / SourceDensity` 缩放。

### 方法

| 方法名 | 返回类型 | 说明 |
| --- | --- | --- |
| style(isDark: Boolean) | GlassStyle | 选择 CommonMediumRegularLowLight 或 CommonMediumRegularDark |

## 相关页面

- [模糊效果](/zh_CN/guide/blur) —— 背景采样与模糊效果栈，描边通常绘制在它们之上。
- [玻璃表面](/zh_CN/guide/glass) —— `glassPanel` / `glass` / `glassShadow` / `glassSurface` 与玻璃组件。
