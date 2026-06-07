#! /usr/bin/env markdown

# Badge

`Badge` 是 Miuix 中的小型状态元素，用于将动态信息（例如未读消息数或待处理请求数）叠加到图标等锚点元素上。参照 Material 3，`BadgedBox` 将 `Badge` 相对其 `content` 定位；徽章可以是一个小圆点（仅图标），也可以包含简短文字。

<div style="position: relative; height: 360px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../../compose/index.html?id=badge" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

## 引入

```kotlin
import top.yukonga.miuix.kmp.basic.Badge
import top.yukonga.miuix.kmp.basic.BadgedBox
import top.yukonga.miuix.kmp.basic.BadgeDefaults
```

## 基本用法

用 `BadgedBox` 包裹锚点，并在其 `badge` 槽里提供一个 `Badge`。不带内容的 `Badge` 会渲染为一个小圆点：

```kotlin
BadgedBox(badge = { Badge() }) {
    Icon(imageVector = MiuixIcons.Settings, contentDescription = "设置")
}
```

传入内容即可显示简短文字，例如未读数：

```kotlin
BadgedBox(badge = { Badge { Text("99+") } }) {
    Icon(imageVector = MiuixIcons.Settings, contentDescription = "设置")
}
```

## Badge

`Badge` 绘制徽章容器。不带 `content` 时是一个小圆点；带 `content` 时会扩大以容纳简短标签。

```kotlin
@Composable
fun Badge(
    modifier: Modifier = Modifier,
    containerColor: Color = BadgeDefaults.containerColor,
    contentColor: Color = BadgeDefaults.contentColor,
    content: @Composable (RowScope.() -> Unit)? = null,
)
```

| 参数名         | 类型                               | 说明                             | 默认值                       | 是否必须 |
| -------------- | ---------------------------------- | -------------------------------- | ---------------------------- | -------- |
| modifier       | Modifier                           | 应用于该徽章的修饰符             | Modifier                     | 否       |
| containerColor | Color                              | 徽章的背景颜色                   | BadgeDefaults.containerColor | 否       |
| contentColor   | Color                              | 徽章内部内容的首选颜色           | BadgeDefaults.contentColor   | 否       |
| content        | @Composable (RowScope.() -> Unit)? | 渲染在徽章内部的可选内容         | null                         | 否       |

## BadgedBox

`BadgedBox` 是一个顶层布局，将 `badge` 相对其 `content` 定位。徽章被放置在锚点的顶端角落；其偏移会根据徽章是否带内容自动调整。

```kotlin
@Composable
fun BadgedBox(
    badge: @Composable BoxScope.() -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit,
)
```

| 参数名   | 类型                            | 说明                             | 默认值   | 是否必须 |
| -------- | ------------------------------- | -------------------------------- | -------- | -------- |
| badge    | @Composable BoxScope.() -> Unit | 要显示的徽章——通常是一个 `Badge` | -        | 是       |
| modifier | Modifier                        | 应用于该 BadgedBox 的修饰符      | Modifier | 否       |
| content  | @Composable BoxScope.() -> Unit | 徽章定位所依附的锚点内容         | -        | 是       |

## BadgeDefaults

`BadgeDefaults` 提供徽章的默认尺寸与颜色。

```kotlin
object BadgeDefaults {
    val Size = 6.dp
    val LargeSize = 16.dp

    val containerColor: Color
        @Composable get() = MiuixTheme.colorScheme.error

    val contentColor: Color
        @Composable get() = MiuixTheme.colorScheme.onError
}
```

| 名称           | 类型  | 说明                       | 默认值                         |
| -------------- | ----- | -------------------------- | ------------------------------ |
| Size           | Dp    | 仅图标徽章（无内容）的尺寸 | 6.dp                           |
| LargeSize      | Dp    | 带内容徽章的尺寸           | 16.dp                          |
| containerColor | Color | 徽章的默认背景颜色         | MiuixTheme.colorScheme.error   |
| contentColor   | Color | 徽章的默认内容颜色         | MiuixTheme.colorScheme.onError |
