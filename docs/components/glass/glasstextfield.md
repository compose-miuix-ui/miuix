# GlassTextField

`GlassTextField` is a text input field styled for translucent glass surfaces such as [GlassDialog](./glassdialog) and [GlassBottomSheet](./glassbottomsheet). It delegates to [TextField](../textfield) with `GlassTextFieldDefaults.textFieldColors()` so the input container and label remain legible over frosted glass.

<div style="position: relative; height: 240px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../../compose/index.html?id=glassTextField" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

## Import

```kotlin
import top.yukonga.miuix.kmp.glass.GlassTextField
```

## Basic Usage

```kotlin
var text by remember { mutableStateOf("") }

GlassTextField(
    value = text,
    onValueChange = { text = it },
    label = "Input something...",
    useLabelAsPlaceholder = true,
)
```

`GlassTextField` also provides `TextFieldState` and `TextFieldValue` overloads matching `TextField`.

## Properties

`GlassTextField` accepts the same parameters as [TextField](../textfield#properties), with `colors` defaulting to `GlassTextFieldDefaults.textFieldColors()`, `cornerRadius` defaulting to `GlassTextFieldDefaults.CornerRadius`, and `insideMargin` defaulting to `GlassTextFieldDefaults.InsideMargin`.

## Defaults

### GlassTextFieldDefaults

| Name | Type | Description |
| --- | --- | --- |
| CornerRadius | Dp | Default corner radius (`TextFieldDefaults.CornerRadius`) |
| InsideMargin | DpSize | Default inner padding (`TextFieldDefaults.InsideMargin`) |
| textFieldColors(...) | TextFieldColors | Translucent tonal fill and higher-contrast label color for glass surfaces |
