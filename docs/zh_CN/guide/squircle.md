# 平滑圆角

`miuix-squircle` 是一个独立的 Compose Multiplatform 平滑圆角（squircle，连续
曲率圆角）矩形库。它通过 `Modifier` 扩展提供填充 / 裁剪 / 描边，并暴露底层的
`Path` 构造器。支持 Android、Desktop (JVM)、iOS、macOS 和 Web (WasmJs/Js) 平台。

::: tip 与 RoundedCornerShape 的区别
`RoundedCornerShape` 的圆角是一段纯粹的四分之一圆弧，直线段与弧之间的曲率
存在突变。squircle 把曲率分布到更宽的角部区域，呈现现代移动端图标常见的
"连续曲率圆角"观感。视觉差异在中到大半径时最明显。
:::

::: warning 注意
基于 shader 的 modifier（`squircleBackground`、`squircleSurface`、
`squircleClip`）在 Android 上需要 API 33+。在更低 API 上**会自动回退**到
等价的 `RoundedCornerShape` 实现 —— 不会崩溃，也无需手动门控。基于 path
的 API（`squircleBorder`、`Path.addSquircleRect`）则没有平台下限。
:::

## 配置

在项目中添加 `miuix-squircle` 依赖：

```kotlin
kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation("top.yukonga.miuix.kmp:miuix-squircle:<version>")
        }
    }
}
```

Android 项目：

```kotlin
dependencies {
    implementation("top.yukonga.miuix.kmp:miuix-squircle-android:<version>")
}
```

## 平台支持

| 平台          | 基于 shader 的 modifier             | 基于 path 的 API |
| ------------- | ----------------------------------- | ---------------- |
| Android       | API 33+（更低版本自动回退圆弧圆角） | 所有 API 级别    |
| Desktop (JVM) | 支持                                | 支持             |
| iOS / macOS   | 支持                                | 支持             |
| WasmJs / Js   | 支持                                | 支持             |

`squircleBackground`、`squircleSurface`、`squircleClip` 由一个 SDF 驱动的
`RuntimeShader` 实现，Android 上需要 API 33。运行时着色器不可用时，每个
modifier 会静默降级为 `Modifier.background(color, RoundedCornerShape(...))`
或 `Modifier.clip(RoundedCornerShape(...))`，调用方无需自行判断。

`squircleBorder` 和 `Path.addSquircleRect` 由三次贝塞尔段拼接而成，在所有
平台上均可使用。

### 运行时能力检查

如果你需要知道是否走的是 shader 路径（例如根据它来决定其他视觉选择），
复用 `miuix-shader` 暴露的能力标志即可：

```kotlin
import top.yukonga.miuix.kmp.shader.isRuntimeShaderSupported

val realSquircle = isRuntimeShaderSupported()
```

## 基本用法

```kotlin
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.squircle.squircleBackground

Box(
    modifier = Modifier
        .size(120.dp, 80.dp)
        .squircleBackground(
            color = MiuixTheme.colorScheme.primaryVariant,
            cornerRadius = 20.dp,
        ),
)
```

默认的 `extension = 1.1f` 和 `control = 0.63f` 给出标准的连续曲率观感。
若想近似同半径的 `RoundedCornerShape`，可传 `extension = 1f` 和
`control = 0.55228f`。

## 变体

### squircleBackground — 仅填充

在 squircle 轮廓内填充纯色。子内容**不会**被裁剪到圆角 —— 适合只想画一个
背景、但仍允许子内容溢出或自行处理边角的场景。

```kotlin
Modifier.squircleBackground(
    color = MiuixTheme.colorScheme.primaryVariant,
    cornerRadius = 16.dp,
)
```

### squircleSurface — 填充 + 裁剪

可直接替代 `Modifier.clip(RoundedCornerShape(r)).background(color)`，但用的
是 squircle 轮廓。子内容在同一个离屏 layer 中被裁剪到 squircle 轮廓 ——
没有重复绘制成本。

```kotlin
Modifier.squircleSurface(
    color = MiuixTheme.colorScheme.primaryVariant,
    cornerRadius = 16.dp,
)
```

### squircleClip — 仅裁剪

把子内容裁剪到 squircle 轮廓，但不绘制填充。适合填充由其它途径提供（如
`Image`、其它 modifier 链等）的场景。

```kotlin
Modifier.squircleClip(cornerRadius = 16.dp)
```

::: warning 离屏成本
`squircleSurface` 和 `squircleClip` 各自会开一个离屏 GPU layer ——
与 `Modifier.clip(...)` 加内容绘制成本结构相同。size 和参数稳定时 shader
输出会被缓存；能传常量就传常量，避免让 `cornerRadius` 受持续动画状态驱动。
:::

### squircleBorder — 仅描边

围绕布局绘制 squircle 轮廓的描边，自动内缩半个描边宽度，使其与同半径
的 `squircleBackground` / `squircleSurface` 视觉对齐。基于 path 实现，
不需要 `RuntimeShader`。

```kotlin
import top.yukonga.miuix.kmp.squircle.squircleBorder

Modifier.squircleBorder(
    width = 1.dp,
    color = MiuixTheme.colorScheme.outline,
    cornerRadius = 16.dp,
)
```

## 每角独立半径

所有基于 shader 的 modifier 都提供按角独立半径的重载，参数顺序与
`RoundedCornerShape` 一致（`topStart`、`topEnd`、`bottomEnd`、`bottomStart`）：

```kotlin
Modifier.squircleSurface(
    color = MiuixTheme.colorScheme.secondaryVariant,
    topStart = 24.dp,
    topEnd = 24.dp,
    bottomEnd = 0.dp,
    bottomStart = 0.dp,
)
```

混搭即可得到非对称形状（"贴在顶部的卡片"、"一侧切直的内嵌徽章"等）。

## 调整曲线参数

两个标量决定圆角观感，均被自动钳位到安全范围：

| 参数        | 默认值 | 范围         | 作用                                                                |
| ----------- | :----: | ------------ | ------------------------------------------------------------------- |
| `extension` | `1.1`  | `[1, 2]`     | 角部区域大小相对 `cornerRadius` 的倍数；`1.0` 即标准圆弧。           |
| `control`   | `0.63` | `[0.3, 0.9]` | 三次贝塞尔控制柄比例；`0.55228` 等价于四分之一圆。                  |

```kotlin
// 更接近 iOS 观感：角部区域稍大、曲线更"鼓"
Modifier.squircleBackground(
    color = MiuixTheme.colorScheme.primaryVariant,
    cornerRadius = 24.dp,
    extension = 1.2f,
    control = 0.7f,
)
```

如果你在自己的 API 表面里需要引用默认值，可直接用 `SquircleDefaults.Extension`
/ `SquircleDefaults.Control` —— 两者都对外公开。

## Path API

当你需要在 modifier 流水线之外构造 squircle 轮廓（例如每帧重建的 `clipPath`
揭示动画、自定义 `Canvas` 绘制、非 shader Shape 的 `Outline` 等），可直接
构造 path：

```kotlin
import androidx.compose.ui.graphics.Path
import top.yukonga.miuix.kmp.squircle.addSquircleRect

val path = Path().apply {
    addSquircleRect(
        width = sizePx.width,
        height = sizePx.height,
        cornerRadius = 20.dp.toPx(),
    )
}
```

path 由 8 段三次贝塞尔曲线构成，可在所有平台运行。静态填充/裁剪请优先使用
modifier API —— 它们走 shader 加速并缓存 SDF。

## 属性

### squircleBackground / squircleSurface / squircleClip

| 参数           | 类型    | 说明                                              | 默认值                              |
| -------------- | ------- | ------------------------------------------------- | ----------------------------------- |
| `color`        | `Color` | 填充颜色（`squircleClip` 无此参数）               | -                                   |
| `cornerRadius` | `Dp`    | 统一圆角半径                                      | -                                   |
| `topStart`     | `Dp`    | 每角半径（按角重载）                              | -                                   |
| `topEnd`       | `Dp`    | 每角半径（按角重载）                              | -                                   |
| `bottomEnd`    | `Dp`    | 每角半径（按角重载）                              | -                                   |
| `bottomStart`  | `Dp`    | 每角半径（按角重载）                              | -                                   |
| `extension`    | `Float` | 角部区域倍数，钳位到 `[1, 2]`                     | `SquircleDefaults.Extension` = 1.1  |
| `control`      | `Float` | 贝塞尔控制柄比例，钳位到 `[0.3, 0.9]`             | `SquircleDefaults.Control` = 0.63   |

### squircleBorder

| 参数           | 类型    | 说明              | 默认值                          |
| -------------- | ------- | ----------------- | ------------------------------- |
| `width`        | `Dp`    | 描边宽度          | -                               |
| `color`        | `Color` | 描边颜色          | -                               |
| `cornerRadius` | `Dp`    | 统一圆角半径      | -                               |
| `extension`    | `Float` | 角部区域倍数      | `SquircleDefaults.Extension`    |
| `control`      | `Float` | 贝塞尔控制柄比例  | `SquircleDefaults.Control`      |

### Path.addSquircleRect

| 参数           | 类型    | 说明              | 默认值                          |
| -------------- | ------- | ----------------- | ------------------------------- |
| `width`        | `Float` | path 宽度（像素） | -                               |
| `height`       | `Float` | path 高度（像素） | -                               |
| `cornerRadius` | `Float` | 圆角半径（像素）  | -                               |
| `extension`    | `Float` | 角部区域倍数      | `SquircleDefaults.Extension`    |
| `control`      | `Float` | 贝塞尔控制柄比例  | `SquircleDefaults.Control`      |

### SquircleDefaults

| 常量           | 类型    | 说明                       |  值    |
| -------------- | ------- | -------------------------- | -----: |
| `Extension`    | `Float` | 默认角部区域倍数           | `1.1`  |
| `Control`      | `Float` | 默认贝塞尔控制柄比例       | `0.63` |
| `ExtensionMin` | `Float` | `Extension` 下限           | `1.0`  |
| `ExtensionMax` | `Float` | `Extension` 上限           | `2.0`  |
| `ControlMin`   | `Float` | `Control` 下限             | `0.3`  |
| `ControlMax`   | `Float` | `Control` 上限             | `0.9`  |
