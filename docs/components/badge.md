#! /usr/bin/env markdown

# Badge

`Badge` is a small status element in Miuix that overlays dynamic information — such as a count of unread messages or pending requests — onto an anchor like an icon. Following Material 3, `BadgedBox` positions a `Badge` relative to its `content`; the badge can be a small dot (icon only) or contain short text.

<div style="position: relative; height: 360px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../compose/index.html?id=badge" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

## Import

```kotlin
import top.yukonga.miuix.kmp.basic.Badge
import top.yukonga.miuix.kmp.basic.BadgedBox
import top.yukonga.miuix.kmp.basic.BadgeDefaults
```

## Basic Usage

Wrap an anchor with `BadgedBox` and supply a `Badge` in its `badge` slot. A `Badge` with no content renders as a small dot:

```kotlin
BadgedBox(badge = { Badge() }) {
    Icon(imageVector = MiuixIcons.Settings, contentDescription = "Settings")
}
```

Pass content to show short text, such as an unread count:

```kotlin
BadgedBox(badge = { Badge { Text("99+") } }) {
    Icon(imageVector = MiuixIcons.Settings, contentDescription = "Settings")
}
```

## Badge

`Badge` draws the badge container. With no `content` it is a small dot; with `content` it grows to fit a short label.

```kotlin
@Composable
fun Badge(
    modifier: Modifier = Modifier,
    containerColor: Color = BadgeDefaults.containerColor,
    contentColor: Color = BadgeDefaults.contentColor,
    content: @Composable (RowScope.() -> Unit)? = null,
)
```

| Parameter Name | Type                              | Description                                       | Default Value               | Required |
| -------------- | --------------------------------- | ------------------------------------------------ | --------------------------- | -------- |
| modifier       | Modifier                          | Modifier applied to this badge                   | Modifier                    | No       |
| containerColor | Color                             | Background color of the badge                    | BadgeDefaults.containerColor | No      |
| contentColor   | Color                             | Preferred color for content inside the badge     | BadgeDefaults.contentColor  | No       |
| content        | @Composable (RowScope.() -> Unit)? | Optional content rendered inside the badge       | null                        | No       |

## BadgedBox

`BadgedBox` is a top-level layout that places a `badge` relative to its `content`. The badge is positioned at the top-end corner of the anchor; its offset adapts depending on whether the badge has content.

```kotlin
@Composable
fun BadgedBox(
    badge: @Composable BoxScope.() -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit,
)
```

| Parameter Name | Type                          | Description                                       | Default Value | Required |
| -------------- | ----------------------------- | ------------------------------------------------- | ------------- | -------- |
| badge          | @Composable BoxScope.() -> Unit | The badge to display - typically a `Badge`        | -             | Yes      |
| modifier       | Modifier                      | Modifier applied to this BadgedBox                | Modifier      | No       |
| content        | @Composable BoxScope.() -> Unit | The anchor to which the badge will be positioned  | -             | Yes      |

## BadgeDefaults

`BadgeDefaults` provides the default sizes and colors for a badge.

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

| Name           | Type  | Description                                  | Default Value                  |
| -------------- | ----- | -------------------------------------------- | ------------------------------ |
| Size           | Dp    | Size of an icon-only badge (no content)      | 6.dp                           |
| LargeSize      | Dp    | Size of a badge containing content           | 16.dp                          |
| containerColor | Color | Default background color of a badge          | MiuixTheme.colorScheme.error   |
| contentColor   | Color | Default content color of a badge             | MiuixTheme.colorScheme.onError |
