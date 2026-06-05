# 平滑圆角

`miuix-squircle` 是一个独立的 Compose Multiplatform 平滑圆角（squircle，连续
曲率圆角）矩形库。它通过 `Modifier` 扩展提供填充 / 裁剪 / 描边，并暴露底层的
`Path` 构造器。支持 Android、Desktop (JVM)、iOS、macOS 和 Web (WasmJs/Js) 平台。

::: tip 与 RoundedCornerShape 的区别
`RoundedCornerShape` 的圆角是一段纯粹的四分之一圆弧，直线段与弧之间的曲率
存在突变。squircle 把曲率分布到更宽的角部区域，呈现现代移动端图标常见的
"连续曲率圆角"观感。视觉差异在中到大半径时最明显。
:::

::: warning 平台下限
基于 shader 的 modifier（`squircleBackground`、`squircleSurface`、
`squircleClip`、`squircleBorder`）需要 Android API 33+。更低 API 上会自动
回退到等价的 `RoundedCornerShape` 实现 —— 不会崩溃，无需手动门控。运行时
开关见下方[全局开关](#全局开关)。
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

回退是自动的，调用方无需分支。`Path.addSquircleRect` 在所有平台都能跑，但
提供了显式的 `squircleEnabled` 参数 —— 跟 modifier 混用时透传它即可保持
视觉一致。

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
`squircleSurface` 和 `squircleClip` 各自会开一个离屏 GPU layer。size 和参数
稳定时 shader 输出会被缓存；能传常量就传常量，避免让 `cornerRadius` 受持续
动画状态驱动。
:::

### squircleBorder — 仅描边

围绕布局绘制 squircle 轮廓的描边，自动内缩半个描边宽度，使其与同半径
的 `squircleBackground` / `squircleSurface` 视觉对齐。基于 path 实现（不需要
shader），比 shader 变体更便宜。

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

## 绝对圆角（与布局方向无关）

`absoluteSquircleBackground`、`absoluteSquircleClip`、`absoluteSquircleSurface`
与每角独立半径的重载相同，但接受物理方位的角参数（`topLeft`、`topRight`、
`bottomRight`、`bottomLeft`，从左上顺时针），且**不会**随 `LocalLayoutDirection`
翻转——与 `AbsoluteRoundedCornerShape` 之于 `RoundedCornerShape` 的关系一致。
当圆角需要固定在物理方位、不随 RTL/LTR 改变时使用（例如绑定到滑动边缘的过渡揭示）。

```kotlin
Modifier.absoluteSquircleSurface(
    color = MiuixTheme.colorScheme.surface,
    topLeft = 24.dp,
    topRight = 24.dp,
    bottomRight = 0.dp,
    bottomLeft = 0.dp,
)
```

## 调整曲线参数

`extension` 决定连续曲率区从顶点延伸出去的距离。`1.0` 等同标准圆弧；
默认 `1.1` 给出标准的 squircle 观感。

| 参数        | 默认值 | 范围         | 作用                                                                |
| ----------- | :----: | ------------ | ------------------------------------------------------------------- |
| `extension` | `1.1`  | `[1, 2]`     | 角部区域大小相对 `cornerRadius` 的倍数；`1.0` 即标准圆弧。           |

```kotlin
Modifier.squircleBackground(
    color = MiuixTheme.colorScheme.primaryVariant,
    cornerRadius = 24.dp,
    extension = 1.2f,
)
```

引用默认值用 `SquircleDefaults.Extension`。

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

静态填充 / 裁剪场景请优先用 shader-backed modifier —— 它们每进程只算一次
距离场并缓存。

## 全局开关

`LocalSquircleEnabled`（默认 `true`）门控本模块全部 shader-backed squircle
modifier。置为 `false` 时所有 modifier 在运行时切换到 `RoundedCornerShape`
回退，适合用户偏好或 A/B 对比：

```kotlin
import top.yukonga.miuix.kmp.squircle.LocalSquircleEnabled

CompositionLocalProvider(LocalSquircleEnabled provides userPrefersRoundedRects) {
    AppContent()
}
```

`isSquircleEnabled()` 把 `LocalSquircleEnabled.current` 与运行时 shader 能力
检查合并为单次读取。当你在同一组件里混用 shader-backed modifier 与
`addSquircleRect` 时，在 @Composable 处读它一次，捕获布尔后透传给 path 构造器：

```kotlin
@Composable
fun OutlinedSurface(cornerRadius: Dp) {
    val squircleEnabled = isSquircleEnabled()
    val path = remember { Path() }
    Box(
        Modifier
            .squircleBackground(MiuixTheme.colorScheme.surface, cornerRadius)
            .drawWithContent {
                drawContent()
                path.rewind()
                path.addSquircleRect(
                    width = size.width,
                    height = size.height,
                    cornerRadius = cornerRadius.toPx(),
                    squircleEnabled = squircleEnabled,
                )
                drawPath(path, MiuixTheme.colorScheme.outline, style = Stroke(1.dp.toPx()))
            },
    )
}
```

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

### squircleBorder

| 参数           | 类型    | 说明              | 默认值                          |
| -------------- | ------- | ----------------- | ------------------------------- |
| `width`        | `Dp`    | 描边宽度          | -                               |
| `color`        | `Color` | 描边颜色          | -                               |
| `cornerRadius` | `Dp`    | 统一圆角半径      | -                               |
| `extension`    | `Float` | 角部区域倍数      | `SquircleDefaults.Extension`    |

### Path.addSquircleRect

| 参数              | 类型      | 说明                                                 | 默认值                          |
| ----------------- | --------- | ---------------------------------------------------- | ------------------------------- |
| `width`           | `Float`   | path 宽度（像素）                                    | -                               |
| `height`          | `Float`   | path 高度（像素）                                    | -                               |
| `cornerRadius`    | `Float`   | 圆角半径（像素）                                     | -                               |
| `extension`       | `Float`   | 角部区域倍数                                         | `SquircleDefaults.Extension`    |
| `squircleEnabled` | `Boolean` | 为 `false` 时改为追加同尺寸的圆角矩形                 | `true`                          |

### SquircleDefaults

| 常量           | 类型    | 说明                       |  值    |
| -------------- | ------- | -------------------------- | -----: |
| `Extension`    | `Float` | 默认角部区域倍数           | `1.1`  |
| `ExtensionMin` | `Float` | `Extension` 下限           | `1.0`  |
| `ExtensionMax` | `Float` | `Extension` 上限           | `2.0`  |
