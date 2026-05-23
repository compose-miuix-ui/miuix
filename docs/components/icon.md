# Icon

`Icon` is a fundamental icon component in Miuix used to display various vector icons, bitmap icons, or custom drawn content in the interface. It provides multiple ways to render icons to accommodate different icon resource types.

By default, `tint` resolves to `LocalContentColor.current`, so the icon follows the content color provided by parent components such as `Button` or `Surface`. Pass an explicit `Color` (for example `MiuixTheme.colorScheme.onBackground`) when you need to override that, or `Color.Unspecified` to disable tinting and keep the source colors.

<div style="position: relative; height: 120px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../compose/index.html?id=icon" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

## Import

```kotlin
import top.yukonga.miuix.kmp.basic.Icon
```

## Basic Usage

The Icon component can be used to display icons:

```kotlin
Icon(
    imageVector = MiuixIcons.Favorites,
    contentDescription = "Favorites"
)
```

## Icon Types

Miuix Icon supports multiple types of icon resources:

### Vector Icon

```kotlin
Icon(
    imageVector = MiuixIcons.Settings,
    contentDescription = "Settings Icon"
)
```

### Bitmap Icon

```kotlin
val bitmap = ImageBitmap(...)
Icon(
    bitmap = bitmap,
    contentDescription = "Bitmap Icon"
)
```

### Custom Painter

```kotlin
val customPainter = remember { /* Custom Painter */ }

Icon(
    painter = customPainter,
    contentDescription = "Custom Icon"
)
```

## Component States

### Custom Color

```kotlin
Icon(
    imageVector = MiuixIcons.Contacts,
    contentDescription = "Personal Icon",
    tint = Color.Red
)
```

### Original Color (No Tinting)

```kotlin
Icon(
    imageVector = MiuixIcons.Favorites,
    contentDescription = "Favorites",
    tint = Color.Unspecified // Disable tinting and keep the source colors
)
```

## Properties

### Icon Properties (ImageVector Version)

| Property Name      | Type        | Description                                                                              | Default Value              | Required |
| ------------------ | ----------- | ---------------------------------------------------------------------------------------- | -------------------------- | -------- |
| imageVector        | ImageVector | Vector icon to draw                                                                      | -                          | Yes      |
| contentDescription | String?     | Accessibility description text                                                           | -                          | Yes      |
| modifier           | Modifier    | Modifier applied to the icon                                                             | Modifier                   | No       |
| tint               | Color       | Color tint applied to the icon. If `Color.Unspecified` is provided, no tint is applied   | LocalContentColor.current  | No       |

### Icon Properties (ImageBitmap Version)

| Property Name      | Type        | Description                                                                              | Default Value              | Required |
| ------------------ | ----------- | ---------------------------------------------------------------------------------------- | -------------------------- | -------- |
| bitmap             | ImageBitmap | Bitmap icon to draw                                                                      | -                          | Yes      |
| contentDescription | String?     | Accessibility description text                                                           | -                          | Yes      |
| modifier           | Modifier    | Modifier applied to the icon                                                             | Modifier                   | No       |
| tint               | Color       | Color tint applied to the icon. If `Color.Unspecified` is provided, no tint is applied   | LocalContentColor.current  | No       |

### Icon Properties (Painter Version)

| Property Name      | Type     | Description                                                                              | Default Value              | Required |
| ------------------ | -------- | ---------------------------------------------------------------------------------------- | -------------------------- | -------- |
| painter            | Painter  | Painter to use                                                                           | -                          | Yes      |
| contentDescription | String?  | Accessibility description text                                                           | -                          | Yes      |
| modifier           | Modifier | Modifier applied to the icon                                                             | Modifier                   | No       |
| tint               | Color    | Color tint applied to the icon. If `Color.Unspecified` is provided, no tint is applied   | LocalContentColor.current  | No       |

### Icon Properties (Painter + ColorProducer Version)

This overload defers the tint color read to the draw phase via a [`ColorProducer`](https://developer.android.com/reference/kotlin/androidx/compose/ui/graphics/ColorProducer), which is useful when the tint color changes frequently and you want to avoid recomposition.

| Property Name      | Type            | Description                                                                | Default Value | Required |
| ------------------ | --------------- | -------------------------------------------------------------------------- | ------------- | -------- |
| painter            | Painter         | Painter to use                                                             | -             | Yes      |
| tint               | ColorProducer?  | Tint producer; if `null`, no tint is applied                               | -             | Yes      |
| contentDescription | String?         | Accessibility description text                                             | -             | Yes      |
| modifier           | Modifier        | Modifier applied to the icon                                               | Modifier      | No       |

## Advanced Usage

### Custom Size

```kotlin
Icon(
    imageVector = MiuixIcons.Favorites,
    contentDescription = "Favorites",
    modifier = Modifier.size(32.dp)
)
```

### Using with Other Components

```kotlin
Button(
    onClick = { /* Handle click event */ }
) {
    Icon(
        imageVector = MiuixIcons.Save,
        contentDescription = "Download Icon"
    )
    Spacer(modifier = Modifier.width(8.dp))
    Text("Download")
}
```

### Custom Style

```kotlin
Icon(
    imageVector = MiuixIcons.Info,
    contentDescription = "Warning Icon",
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

### Dynamic Icon

```kotlin
var isSelected by remember { mutableStateOf(false) }

Icon(
    imageVector = if (isSelected) MiuixIcons.FavoritesFill else MiuixIcons.Favorites,
    contentDescription = "Favorites",
    modifier = Modifier.clickable { isSelected = !isSelected }
)
```
