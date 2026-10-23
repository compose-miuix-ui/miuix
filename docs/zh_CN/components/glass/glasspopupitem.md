---
title: GlassPopupItem
---

# GlassPopupItem

`GlassPopupItem` 是菜单行，可选图标、摘要、选中勾和子菜单箭头，并可独立组合。点击菜单行不会自动更新选中状态或关闭弹窗；应在 `onClick` 中处理。

<div style="position: relative; height: 440px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../../../compose/index.html?id=glassPopupItem" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

## 引入

```kotlin
import top.yukonga.miuix.kmp.glass.GlassPopupItem
```

## 基本用法

`GlassPopupItem` 不接收 `backdrop`，背景采样由承载它的弹窗配置。

将菜单行放入弹窗的 `content`，并由调用方管理菜单状态：

```kotlin
GlassPopupItem(
    text = "排序方式",
    summary = "名称",
    showArrow = true,
    arrowRotation = { if (submenuOpen) 90f else 0f },
    onClick = { submenuOpen = true },
)
```

### 可选中菜单行

在同一个点击回调中更新选中值并请求关闭：

```kotlin
GlassPopupItem(
    text = "名称",
    selected = selectedIndex == 0,
    onClick = {
        selectedIndex = 0
        show = false
    },
)
```

## 组件状态

### 禁用状态

设置 `enabled = false` 可阻止菜单行操作；标签和前置图标使用 `contentColor.copy(alpha = 0.4f)`，选中时也不例外。摘要和子菜单箭头保留 `summaryColor`；选中勾仍以 `selectedContentColor` 显示：

```kotlin
GlassPopupItem(
    text = "不可用",
    enabled = false,
    onClick = {},
)
```

## 属性

### GlassPopupItem 属性

| 属性名 | 类型 | 说明 | 默认值 | 是否必须 |
| --- | --- | --- | --- | --- |
| `text` | `String` | 单行菜单标签 | — | 是 |
| `onClick` | `() -> Unit` | 启用菜单行被点击时调用 | — | 是 |
| `modifier` | `Modifier` | 作用于菜单行的修饰符 | `Modifier` | 否 |
| `icon` | `ImageVector?` | 可选前置图标 | `null` | 否 |
| `summary` | `String?` | 可选的单行第二标签 | `null` | 否 |
| `enabled` | `Boolean` | 菜单行是否响应点击 | `true` | 否 |
| `selected` | `Boolean` | 显示末尾勾；启用时标签和前置图标使用选中颜色 | `false` | 否 |
| `showArrow` | `Boolean` | 显示末尾子菜单箭头 | `false` | 否 |
| `arrowRotation` | `() -> Float` | 箭头旋转角度，在图层阶段读取 | `{ 0f }` | 否 |
| `contentColor` | `Color` | 标签和图标颜色 | `MiuixTheme.colorScheme.onSurfaceContainer` | 否 |
| `summaryColor` | `Color` | 摘要和箭头颜色 | `MiuixTheme.colorScheme.onSurfaceContainerVariant` | 否 |
| `selectedContentColor` | `Color` | 已启用选中行的标签和前置图标颜色；选中勾在禁用时也使用此色 | `MiuixTheme.colorScheme.primary` | 否 |
| `pressedColor` | `Color` | 按压时菜单行后的填充色 | `GlassPopupDefaults.itemPressedColor()` | 否 |

## 进阶用法

### 让子菜单箭头跟随预测性返回

需要让箭头跟随二级菜单的预测性返回进度时，应在绘制或图层回调中读取锚点，而不是单独保存进度状态：

```kotlin
GlassPopupItem(
    text = "排序方式",
    showArrow = true,
    arrowRotation = {
        90f * (1f - anchor.secondaryBackProgress)
    },
    onClick = { openSecondary = true },
)
```

菜单行占满面板宽度，超出的标签以省略号截断；按压填充立即出现并动画退出，不使用涟漪。菜单行尺寸与 `itemPressedColor()` 见 [GlassPopup 共享默认值](./glasspopup#shared-defaults)。
