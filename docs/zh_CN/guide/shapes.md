# 平滑圆角

`miuix-shapes` 为 Compose Multiplatform 提供具有连续曲率过渡的平滑圆角形状。与标准 `RoundedCornerShape` 在直线边缘和圆弧之间存在突变的曲率不同，平滑形状会逐渐过渡到曲线，呈现更自然的外观。

## 引入

在项目中添加 `miuix-shapes` 依赖：

```kotlin
kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation("top.yukonga.miuix.kmp:miuix-shapes:<version>")
        }
    }
}
```

仅 Android 项目：

```kotlin
dependencies {
    implementation("top.yukonga.miuix.kmp:miuix-shapes-android:<version>")
}
```

::: tip
如果已经使用了 `miuix-ui`，平滑形状会自动包含，无需额外添加依赖。
:::

## 形状类型

### SmoothRoundedCornerShape

四角等半径的平滑圆角矩形。

```kotlin
Box(
    modifier = Modifier
        .size(200.dp)
        .clip(SmoothRoundedCornerShape(24.dp))
        .background(Color.Blue)
)
```

### SmoothCapsuleShape

胶囊（体育场/药丸）形状。圆角半径自动设置为较短维度的一半。

```kotlin
Box(
    modifier = Modifier
        .width(200.dp)
        .height(48.dp)
        .clip(SmoothCapsuleShape())
        .background(Color.Blue)
)
```

### SmoothUnevenRoundedCornerShape

每个角可以独立设置半径的平滑圆角矩形。支持 RTL 布局方向。

```kotlin
Box(
    modifier = Modifier
        .size(200.dp)
        .clip(
            SmoothUnevenRoundedCornerShape(
                topStart = 32.dp,
                topEnd = 8.dp,
                bottomEnd = 32.dp,
                bottomStart = 8.dp,
            )
        )
        .background(Color.Blue)
)
```

## 配合 MiuixTheme 使用

使用 `miuix-ui` 时，推荐使用 `SmoothRounding.kt` 中的主题感知包装函数。这些函数会根据 `MiuixTheme.smoothRounding` 自动切换平滑和标准形状：

```kotlin
// 等半径 — 启用平滑圆角时使用 SmoothRoundedCornerShape
val shape = miuixShape(16.dp)

// 胶囊形 — 启用时使用 SmoothCapsuleShape
val shape = miuixCapsuleShape()

// 独立四角 — 启用时使用 SmoothUnevenRoundedCornerShape
val shape = miuixUnevenShape(topStart = 16.dp, topEnd = 16.dp)
```

`MiuixTheme` 的 `smoothRounding` 参数控制此行为：

```kotlin
MiuixTheme(smoothRounding = true) {
    // 所有 miuixShape() 调用将使用平滑形状
}
```

## 自适应圆角行为

当相邻圆角过大无法容纳时（如胶囊形或紧凑布局），平滑圆角会自动退化为标准圆弧。无需手动调整即可确保在所有尺寸下正确渲染。

| 场景 | 行为 |
| :--- | :--- |
| 空间充足（如 `r=16dp` 在 `400×200` 卡片上） | 完整平滑圆角 |
| 空间紧凑（如 `r=40dp` 在 `100×100` 盒子上） | 部分平滑 |
| 胶囊形（`r = height/2`） | 标准圆弧 |
