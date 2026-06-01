# Icon

`Icon` 是 Miuix 中的基础图标组件，用于在界面中展示各种矢量图标、位图图标或自定义绘制内容。它提供了多种绘制图标的方式，适应不同的图标资源类型。

`tint` 的默认值是 `LocalContentColor.current`，因此图标会跟随父级组件（如 `Button`、`Surface`）提供的内容颜色。若需要覆盖该颜色，传入显式的 `Color`（例如 `MiuixTheme.colorScheme.onBackground`）；若想保留资源本身的颜色而不做着色，传入 `Color.Unspecified`。

`Icon` 是为**单色图标**设计的组件，会根据所处场景对图标着色。如果要原样渲染多色图片，或图片不应遵循推荐图标尺寸，请使用 `androidx.compose.foundation.Image`。需要点击交互的图标，请使用 `IconButton`。

<div style="position: relative; height: 120px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../../compose/index.html?id=icon" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

## 引入

```kotlin
import top.yukonga.miuix.kmp.basic.Icon
```

## 基本用法

Icon 组件可以用于显示图标：

```kotlin
Icon(
    imageVector = MiuixIcons.Favorites,
    contentDescription = "Favorites"
)
```

## 图标类型

Miuix Icon 支持多种类型的图标资源：

### 矢量图标（Vector Icon）

```kotlin
Icon(
    imageVector = MiuixIcons.Settings,
    contentDescription = "设置图标"
)
```

### 位图图标（Bitmap Icon）

```kotlin
val bitmap = ImageBitmap(...)
Icon(
    bitmap = bitmap,
    contentDescription = "位图图标"
)
```

### 自定义绘制图标（Custom Painter）

任意 `Painter` 实现都可以使用，最常见的场景是 Compose Resources 的 `painterResource`：

```kotlin
Icon(
    painter = painterResource(Res.drawable.ic_custom),
    contentDescription = "自定义图标"
)
```

## 着色

### 自定义颜色

```kotlin
Icon(
    imageVector = MiuixIcons.Contacts,
    contentDescription = "人像图标",
    tint = Color.Red
)
```

### 原始颜色（不进行着色）

```kotlin
Icon(
    imageVector = MiuixIcons.Favorites,
    contentDescription = "Favorites",
    tint = Color.Unspecified // 关闭着色，保留图标自身颜色
)
```

## 属性

### Icon 属性（ImageVector 版本）

| 属性名             | 类型        | 说明                                                            | 默认值                    | 是否必须 |
| ------------------ | ----------- | --------------------------------------------------------------- | ------------------------- | -------- |
| imageVector        | ImageVector | 要绘制的矢量图标                                                | -                         | 是       |
| contentDescription | String?     | 图标的无障碍描述文本                                            | -                         | 是       |
| modifier           | Modifier    | 应用于图标的修饰符                                              | Modifier                  | 否       |
| tint               | Color       | 应用于图标的着色颜色。传入 `Color.Unspecified` 时不进行着色     | LocalContentColor.current | 否       |

### Icon 属性（ImageBitmap 版本）

| 属性名             | 类型        | 说明                                                            | 默认值                    | 是否必须 |
| ------------------ | ----------- | --------------------------------------------------------------- | ------------------------- | -------- |
| bitmap             | ImageBitmap | 要绘制的位图图标                                                | -                         | 是       |
| contentDescription | String?     | 图标的无障碍描述文本                                            | -                         | 是       |
| modifier           | Modifier    | 应用于图标的修饰符                                              | Modifier                  | 否       |
| tint               | Color       | 应用于图标的着色颜色。传入 `Color.Unspecified` 时不进行着色     | LocalContentColor.current | 否       |

### Icon 属性（Painter 版本）

| 属性名             | 类型     | 说明                                                            | 默认值                    | 是否必须 |
| ------------------ | -------- | --------------------------------------------------------------- | ------------------------- | -------- |
| painter            | Painter  | 要使用的绘制器                                                  | -                         | 是       |
| contentDescription | String?  | 图标的无障碍描述文本                                            | -                         | 是       |
| modifier           | Modifier | 应用于图标的修饰符                                              | Modifier                  | 否       |
| tint               | Color    | 应用于图标的着色颜色。传入 `Color.Unspecified` 时不进行着色     | LocalContentColor.current | 否       |

### Icon 属性（Painter + ColorProducer 版本）

此重载通过 [`ColorProducer`](https://developer.android.com/reference/kotlin/androidx/compose/ui/graphics/ColorProducer) 将 tint 颜色的读取延迟到绘制阶段，适合 tint 颜色频繁变化、希望避免触发重组的场景。

| 属性名             | 类型            | 说明                                            | 默认值   | 是否必须 |
| ------------------ | --------------- | ----------------------------------------------- | -------- | -------- |
| painter            | Painter         | 要使用的绘制器                                  | -        | 是       |
| tint               | ColorProducer?  | 提供 tint 颜色的生产器；为 `null` 时不进行着色  | -        | 是       |
| contentDescription | String?         | 图标的无障碍描述文本                            | -        | 是       |
| modifier           | Modifier        | 应用于图标的修饰符                              | Modifier | 否       |

## 进阶用法

### 自定义大小

```kotlin
Icon(
    imageVector = MiuixIcons.Favorites,
    contentDescription = "Favorites",
    modifier = Modifier.size(32.dp)
)
```

### 结合其他组件使用

```kotlin
Button(
    onClick = { /* 处理点击事件 */ }
) {
    Icon(
        imageVector = MiuixIcons.Save,
        contentDescription = "下载图标"
    )
    Spacer(modifier = Modifier.width(8.dp))
    Text("下载")
}
```

### 自定义样式

```kotlin
Icon(
    imageVector = MiuixIcons.Info,
    contentDescription = "警告图标",
    tint = Color(0xFFFFA500),
    modifier = Modifier
        .size(48.dp)
        .background(
            color = Color.LightGray.copy(alpha = 0.3f),
            shape = CircleShape
        )
        .padding(8.dp)
)
```

### 动态变化图标

需要点击交互时，用 `IconButton` 包裹 `Icon`，以获得 Miuix 风格的触摸反馈：

```kotlin
var isSelected by remember { mutableStateOf(false) }

IconButton(onClick = { isSelected = !isSelected }) {
    Icon(
        imageVector = if (isSelected) MiuixIcons.FavoritesFill else MiuixIcons.Favorites,
        contentDescription = if (isSelected) "喜欢" else "不喜欢",
    )
}
```

### 高频变化的 tint

当 tint 每帧都在变化（动画、手势驱动、选中态过渡等），使用 `ColorProducer` 重载——回调会在绘制阶段直接读取，`Icon` 自身可以跳过重组：

```kotlin
var isSelected by remember { mutableStateOf(false) }
val tint by animateColorAsState(
    if (isSelected) MiuixTheme.colorScheme.primary else MiuixTheme.colorScheme.onBackground,
)

Icon(
    painter = rememberVectorPainter(MiuixIcons.Favorites),
    tint = { tint },
    contentDescription = "Favorites",
)
```
