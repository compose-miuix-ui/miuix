# 平滑圆角

`miuix-squircle` 是一个独立的 Compose Multiplatform 平滑圆角（squircle，连续曲率圆角）矩形库，提供填充、裁剪、描边 `Modifier` 扩展与底层 `Path` 构造器，支持 Android、Desktop (JVM)、iOS、macOS 及 Web (WasmJs/Js)。

::: tip 与 RoundedCornerShape 的区别
`RoundedCornerShape` 采用四分之一圆弧，直线与弧线相切处曲率存在突变；squircle 将曲率分布到更宽的角部区域，呈现连续平滑的过渡观感，在中到大圆角半径下尤为明显。
:::

::: warning 平台下限
基于 shader 的 modifier（`squircleBackground`、`squircleSurface`、`squircleClip` 及 `absolute*` 变体）需要 Android API 33+。更低版本上，它们与 `squircleBorder` 会自动回退为等价的 `RoundedCornerShape` 渲染，无需手动分支。运行时开关见[全局开关](#全局开关)。
:::

## 配置

```kotlin
kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation("top.yukonga.miuix.kmp:miuix-squircle:<version>")
        }
    }
}
```

仅限 Android 项目：

```kotlin
dependencies {
    implementation("top.yukonga.miuix.kmp:miuix-squircle-android:<version>")
}
```

## 平台支持

| 平台 | Shader modifier（`Background` / `Surface` / `Clip`） | Path API（`squircleBorder`、`addSquircleRect`） |
| --- | --- | --- |
| Android | API 33+（低版本自动回退 `RoundedCornerShape`） | 全版本支持（`squircleBorder` 随 `isSquircleEnabled()` 自动对齐） |
| Desktop / iOS / macOS / WasmJs / Js | 支持 | 支持 |

## Modifier 用法

### `squircleBackground` — 仅填充

填充平滑圆角背景，不裁剪子内容，也不分配离屏缓冲。

```kotlin
Modifier.squircleBackground(
    color = MiuixTheme.colorScheme.primaryVariant,
    cornerRadius = 16.dp,
)
```

### `squircleSurface` — 填充 + 裁剪

可替代 `.clip(RoundedCornerShape(r)).background(color)`，在单个离屏 layer 内同时完成填充与子内容裁剪。

```kotlin
Modifier.squircleSurface(
    color = MiuixTheme.colorScheme.primaryVariant,
    cornerRadius = 16.dp,
)
```

### `squircleClip` — 仅裁剪

仅将子内容裁剪至平滑圆角轮廓，不绘制填充（例如裁剪 `Image`）。

```kotlin
Modifier.squircleClip(cornerRadius = 16.dp)
```

::: warning 离屏成本
`squircleSurface` 与 `squircleClip`（含 `absolute*` 变体）通过每节点单个缓存离屏 GPU layer 裁剪子内容：
- 纯胶囊形态（四角半径均 `>=` 短边一半）会自动走硬件 `CircleShape` 裁剪，跳过离屏缓冲。
- 超限节点（任一边 `> 4096 px` 或面积 `> 4096 × 2160`）会自动回退至缓存 `Path` 裁剪。

请优先使用常量半径，避免非必要地用逐帧动画驱动 `cornerRadius`。
:::

### `squircleBorder` — 仅描边

基于 `Path` 绘制平滑圆角描边，自动内缩半个线宽以与同半径 `squircleBackground` / `squircleSurface` 对齐。另提供延迟读取重载（`width: () -> Dp`、`color: () -> Color`），可在绘制阶段响应动画而零重组。

```kotlin
Modifier.squircleBorder(
    width = 1.dp,
    color = MiuixTheme.colorScheme.outline,
    cornerRadius = 16.dp,
)
```

## 每角独立半径与绝对圆角

`squircleBackground`、`squircleSurface` 与 `squircleClip` 均提供按角独立半径重载（`topStart`、`topEnd`、`bottomEnd`、`bottomStart`），参数顺序与 `RoundedCornerShape` 一致并随 RTL 自动镜像：

```kotlin
Modifier.squircleSurface(
    color = MiuixTheme.colorScheme.secondaryVariant,
    topStart = 24.dp,
    topEnd = 24.dp,
    bottomEnd = 0.dp,
    bottomStart = 0.dp,
)
```

若需不随 `LocalLayoutDirection` 翻转的物理方位圆角（`topLeft`、`topRight`、`bottomRight`、`bottomLeft`），请使用 `absoluteSquircleBackground`、`absoluteSquircleSurface` 与 `absoluteSquircleClip`。

## 调整曲线参数

`extension` 控制连续曲率区域相对 `cornerRadius` 的延伸倍数：`1.0f` 等同标准圆弧，默认 `1.1f`（`SquircleDefaults.Extension`）为标准平滑圆角。

```kotlin
Modifier.squircleBackground(
    color = MiuixTheme.colorScheme.primaryVariant,
    cornerRadius = 24.dp,
    extension = 1.2f,
)
```

## Path API

在自定义 `Canvas` 绘制、`Outline` 或逐帧变化的裁剪路径等场景下，可直接调用 `Path.addSquircleRect`。常规静态填充与裁剪请优先使用 shader modifier——它们在进程内直接复用编译期预烘焙的距离场纹理。

```kotlin
val path = Path().apply {
    addSquircleRect(
        width = sizePx.width,
        height = sizePx.height,
        cornerRadius = 20.dp.toPx(),
    )
}
```

物理圆角重载将 `cornerRadius` 替换为 `topLeft`、`topRight`、`bottomRight`、`bottomLeft`（`Float`，单位 px）。

## 全局开关

`LocalSquircleEnabled`（默认 `true`）门控全部 squircle modifier（含 `squircleBackground`、`squircleSurface`、`squircleClip`、`squircleBorder` 及 `absolute*` 变体）。设为 `false` 时运行时统一回退至 `RoundedCornerShape`：

```kotlin
CompositionLocalProvider(LocalSquircleEnabled provides userPrefersSquircle) {
    AppContent()
}
```

`isSquircleEnabled()` 合并了 `LocalSquircleEnabled.current` 与 `isRuntimeShaderSupported()`。当同一组件混用 modifier 与 `addSquircleRect` 时，可在 `@Composable` 内读取并透传给后者：

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

## API 参考

### `squircleBackground` / `squircleSurface` / `squircleClip`

| 参数 | 类型 | 说明 | 默认值 |
| --- | --- | --- | --- |
| `color` | `Color` | 填充颜色（`squircleClip` 无此参数） | - |
| `cornerRadius` | `Dp` | 统一圆角半径 | - |
| `topStart` / `topEnd` / `bottomEnd` / `bottomStart` | `Dp` | 每角独立半径（`absolute*` 对应 `topLeft`、`topRight`、`bottomRight`、`bottomLeft`） | - |
| `extension` | `Float` | 角部区域倍数，钳位至 `[1.0, 2.0]` | `SquircleDefaults.Extension`（`1.1`） |

### `squircleBorder`

| 参数 | 类型 | 说明 | 默认值 |
| --- | --- | --- | --- |
| `width` | `Dp` / `() -> Dp` | 描边宽度（直接值或延迟读取 lambda） | - |
| `color` | `Color` / `() -> Color` | 描边颜色（直接值或延迟读取 lambda） | - |
| `cornerRadius` | `Dp` | 统一圆角半径 | - |
| `extension` | `Float` | 角部区域倍数，钳位至 `[1.0, 2.0]` | `SquircleDefaults.Extension`（`1.1`） |

### `Path.addSquircleRect`

| 参数 | 类型 | 说明 | 默认值 |
| --- | --- | --- | --- |
| `width` | `Float` | 路径宽度（像素） | - |
| `height` | `Float` | 路径高度（像素） | - |
| `cornerRadius` | `Float` | 圆角半径（像素；按角重载为 `topLeft`、`topRight`、`bottomRight`、`bottomLeft`） | - |
| `extension` | `Float` | 角部区域倍数，钳位至 `[1.0, 2.0]` | `SquircleDefaults.Extension`（`1.1`） |
| `squircleEnabled` | `Boolean` | 为 `false` 时改为追加圆角矩形路径 | `true` |

### `SquircleDefaults`

| 常量 | 类型 | 说明 | 值 |
| --- | --- | --- | ---: |
| `Extension` | `Float` | 默认角部区域倍数 | `1.1` |
| `ExtensionMin` | `Float` | `Extension` 下限 | `1.0` |
| `ExtensionMax` | `Float` | `Extension` 上限 | `2.0` |
