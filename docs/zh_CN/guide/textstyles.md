# 文本样式

本页基于实际实现，完整列出 Miuix 提供的文本样式。

## 使用方式

- 在组合函数中通过 `MiuixTheme.textStyles.<名称>` 访问。
- 所有样式的颜色会在运行时由 `MiuixTheme.colorScheme.onBackground` 统一设置。

## 样式列表

| 样式名       | 字号  | 字重   | 行高   |
|--------------|-------|--------|--------|
| `main`       | 17sp  | Normal | -      |
| `paragraph`  | 17sp  | Normal | 1.2em  |
| `body1`      | 16sp  | Normal | -      |
| `body2`      | 14sp  | Normal | -      |
| `button`     | 17sp  | Normal | -      |
| `footnote1`  | 13sp  | Normal | -      |
| `footnote2`  | 11sp  | Normal | -      |
| `headline1`  | 17sp  | Normal | -      |
| `headline2`  | 16sp  | Normal | -      |
| `subtitle`   | 14sp  | Bold   | -      |
| `title1`     | 32sp  | Normal | -      |
| `title2`     | 24sp  | Normal | -      |
| `title3`     | 20sp  | Normal | -      |
| `title4`     | 18sp  | Normal | -      |

## 使用示例

```kotlin
Text(
    text = "标题",
    style = MiuixTheme.textStyles.title2
)

Text(
    text = "正文",
    style = MiuixTheme.textStyles.body1
)
```

## 自定义

- 通过 `defaultTextStyles(...)` 覆盖样式，并传入 `MiuixTheme(textStyles = ...)`。

```kotlin
val customTextStyles = defaultTextStyles(
    title1 = TextStyle(
        fontSize = 36.sp,
        fontWeight = FontWeight.Bold
    )
)

val controller = remember { ThemeController(ColorSchemeMode.System) }
MiuixTheme(
    controller = controller,
    textStyles = customTextStyles
) { /* 内容 */ }
```
