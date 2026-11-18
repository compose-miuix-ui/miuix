# GlassTextField

`GlassTextField` 是适配 [GlassDialog](./glassdialog) 与 [GlassBottomSheet](./glassbottomsheet) 等半透明玻璃表面的文本输入框。它基于 [TextField](../textfield) 并默认使用 `GlassTextFieldDefaults.textFieldColors()`，以在磨砂玻璃背景上保持清晰的容器边界与占位标签对比度。

<div style="position: relative; height: 240px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../../../compose/index.html?id=glassTextField" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

## 引入

```kotlin
import top.yukonga.miuix.kmp.glass.GlassTextField
```

## 基本用法

```kotlin
var text by remember { mutableStateOf("") }

GlassTextField(
    value = text,
    onValueChange = { text = it },
    label = "Input something...",
    useLabelAsPlaceholder = true,
)
```

`GlassTextField` 同样提供与 `TextField` 一致的 `TextFieldState` 与 `TextFieldValue` 重载。

## 属性

`GlassTextField` 接收与 [TextField](../textfield#属性) 相同的参数，其中 `colors` 默认为 `GlassTextFieldDefaults.textFieldColors()`，`cornerRadius` 默认为 `GlassTextFieldDefaults.CornerRadius`，`insideMargin` 默认为 `GlassTextFieldDefaults.InsideMargin`。

## 默认值

### GlassTextFieldDefaults

| 名称 | 类型 | 说明 |
| --- | --- | --- |
| CornerRadius | Dp | 默认圆角（`TextFieldDefaults.CornerRadius`） |
| InsideMargin | DpSize | 默认内边距（`TextFieldDefaults.InsideMargin`） |
| textFieldColors(...) | TextFieldColors | 适配玻璃表面的半透明填充色与高对比度标签色 |
