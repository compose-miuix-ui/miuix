# BreadcrumbBar

`BreadcrumbBar` 是 Miuix 中的水平导航组件，以箭头图标分隔显示路径段，每段呈现为胶囊形状。当内容超出可用宽度时，组件会水平滚动而非折叠（考虑到 miuix 通常用于移动设备）。

`highlightIndex` 与 `items` 列表解耦：调用方可以显示完整路径的同时高亮任意一段（例如当前目录，或用户回退到的某个父级目录）。组件会自动滚动以保持高亮项可见。

<div style="position: relative; height: 200px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../../compose/index.html?id=breadcrumbBar" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

## 引入

```kotlin
import top.yukonga.miuix.kmp.basic.BreadcrumbBar
import top.yukonga.miuix.kmp.basic.BreadcrumbItem
import top.yukonga.miuix.kmp.basic.joinToPath
```

## 基本用法

```kotlin
val items = listOf(
    BreadcrumbItem(path = "/storage/emulated/0", text = "Internal storage"),
    BreadcrumbItem(path = "DataBackup"),
    BreadcrumbItem(path = "apps"),
    BreadcrumbItem(path = "com.tencent.mobileqq"),
    BreadcrumbItem(path = "user_0"),
)

BreadcrumbBar(
    items = items,
    onItemClick = { index -> /* 跳转到对应段 */ },
)
```

## 高亮控制

`highlightIndex` 参数与 items 列表解耦。你可以显示完整路径的同时高亮任意一段：

```kotlin
var highlightIndex by remember { mutableIntStateOf(items.lastIndex) }

BreadcrumbBar(
    items = items,
    onItemClick = { index -> highlightIndex = index },
    highlightIndex = highlightIndex,
)
```

## 拼接路径段

使用 `joinToPath` 扩展函数从 `BreadcrumbItem` 列表重建完整路径：

```kotlin
val fullPath = items.joinToPath("/")
// 结果: "/storage/emulated/0/DataBackup/apps/com.tencent.mobileqq/user_0"

val windowsPath = items.joinToPath("\\")
// 结果: "/storage/emulated/0\\DataBackup\\apps\\com.tencent.mobileqq\\user_0"
```

## 组件状态

### 禁用状态

```kotlin
BreadcrumbBar(
    items = items,
    onItemClick = {},
    enabled = false,
)
```

## 属性

### BreadcrumbBar 属性

| 属性名            | 类型                       | 说明                                   | 默认值                                      | 是否必须 |
| ----------------- | -------------------------- | -------------------------------------- | ------------------------------------------- | -------- |
| items             | List&lt;BreadcrumbItem&gt; | 要显示的面包屑项列表                   | -                                           | 是       |
| onItemClick       | (Int) -> Unit              | 点击某段时触发，回传索引               | -                                           | 是       |
| modifier          | Modifier                   | 应用于面包屑栏的修饰符                 | Modifier                                    | 否       |
| highlightIndex    | Int                        | 高亮段的索引                           | items.lastIndex                             | 否       |
| enabled           | Boolean                    | 面包屑项是否可点击；禁用时仍可水平滚动 | true                                        | 否       |
| colors            | BreadcrumbBarColors        | 面包屑栏颜色配置                       | BreadcrumbBarDefaults.breadcrumbBarColors() | 否       |
| insideMargin      | PaddingValues              | 面包屑栏内部边距                       | BreadcrumbBarDefaults.InsideMargin          | 否       |
| scrollState       | ScrollState?               | 水平滚动的状态，为 null 时内部创建     | null                                        | 否       |
| interactionSource | MutableInteractionSource?  | 交互源                                 | null                                        | 否       |
| indication        | Indication?                | 点击交互的反馈效果                     | LocalIndication.current                     | 否       |

### BreadcrumbItem 属性

| 属性名 | 类型    | 说明                                            | 默认值 | 是否必须 |
| ------ | ------- | ----------------------------------------------- | ------ | -------- |
| path   | String  | 路径段，`joinToPath` 用它重建完整路径           | -      | 是       |
| text   | String? | 显示文本，为 null 时显示 path                   | null   | 否       |

### BreadcrumbBarDefaults 对象

BreadcrumbBarDefaults 对象提供了面包屑栏组件的默认值和颜色配置。

#### 常量

| 常量名                | 类型          | 说明             | 默认值 |
| --------------------- | ------------- | ---------------- | ------ |
| InsideMargin          | PaddingValues | 面包屑栏内部边距 | PaddingValues(horizontal = 12.dp, vertical = 8.dp) |
| ItemHeight            | Dp            | 每个胶囊项的高度 | 32.dp  |
| ItemHorizontalPadding | Dp            | 每个胶囊项的水平内边距（等于圆角半径，使左右端形成完整半圆） | 10.dp |

#### 方法

| 方法名                | 类型                | 说明                       |
| --------------------- | ------------------- | -------------------------- |
| breadcrumbBarColors() | BreadcrumbBarColors | 创建面包屑栏的颜色配置     |

### BreadcrumbBarColors

| 属性名                   | 类型  | 说明                   |
| ------------------------ | ----- | ---------------------- |
| color                    | Color | 普通段的文字颜色       |
| highlightColor           | Color | 高亮段的文字颜色       |
| disabledColor            | Color | 禁用时的文字颜色       |
| separatorColor           | Color | 箭头分隔符的颜色       |
| backgroundColor          | Color | 普通段的胶囊背景色     |
| highlightBackgroundColor | Color | 高亮段的胶囊背景色     |
| disabledBackgroundColor  | Color | 禁用时的胶囊背景色     |

## 进阶用法

### 自定义颜色

```kotlin
BreadcrumbBar(
    items = items,
    onItemClick = { index -> /* 处理点击 */ },
    colors = BreadcrumbBarDefaults.breadcrumbBarColors(
        color = MiuixTheme.colorScheme.onBackground.copy(alpha = 0.55f),
        highlightColor = MiuixTheme.colorScheme.primary,
        backgroundColor = MiuixTheme.colorScheme.onBackground.copy(alpha = 0.1f),
        highlightBackgroundColor = MiuixTheme.colorScheme.primary.copy(alpha = 0.2f),
    ),
)
```

### 高亮与导航解耦

`highlightIndex` 独立于 items 列表，支持"显示完整路径但高亮某个父级段"的场景：

```kotlin
// 完整路径: /storage/emulated/0/DataBackup/apps/com.tencent.mobileqq/user_0
// 高亮在 "apps"（索引 2），表示用户回退到了这里
BreadcrumbBar(
    items = items,
    onItemClick = { index ->
        // 按需更新 items 和 highlightIndex
        highlightIndex = index
    },
    highlightIndex = 2,
)
```
