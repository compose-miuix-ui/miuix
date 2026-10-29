---
title: GlassDropdownPreference
requiresScaffoldHost: true
prerequisites:
  - 必须在 `Scaffold` 中使用以提供 `MiuixPopupHost`
  - 未在 `Scaffold` 中使用将导致弹出内容无法渲染
  - 支持多个嵌套或并列的 `Scaffold`，无需额外配置
hostComponent: Scaffold
popupHost: MiuixPopupHost
---

# GlassDropdownPreference

`GlassDropdownPreference` 是带玻璃下拉菜单的设置行。它管理展开和关闭，调用方负责维护选中索引。

<div style="position: relative; height: 360px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../../../compose/index.html?id=glassDropdownPreference" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

::: danger 使用前提
此组件依赖 `Scaffold` 提供的 `MiuixPopupHost` 以显示弹出内容。必须在 `Scaffold` 中使用，否则弹出内容无法正常渲染。
:::

::: warning 背景采样
使用背景采样时，`backdrop` 应记录组件后方的不透明内容，不能包含使用该采样的玻璃表面。配置方式见[玻璃材质](/zh_CN/guide/glass#background-sampling)。
:::

## 引入

```kotlin
import top.yukonga.miuix.kmp.glass.GlassDropdownPreference
import top.yukonga.miuix.kmp.glass.GlassPopupDefaults
import top.yukonga.miuix.kmp.glass.GlassPopupSizing
import top.yukonga.miuix.kmp.blur.layerBackdrop
import top.yukonga.miuix.kmp.blur.rememberLayerBackdrop
```

## 基本用法

页面内容由 `backdrop` 记录，`Scaffold` 在该采样范围之外承载弹窗。

```kotlin
val backdrop = rememberLayerBackdrop()
val items = listOf("Verbose", "Debug", "Info", "Warn", "Error")
var selectedIndex by remember { mutableIntStateOf(2) }

Scaffold { padding ->
    Column(
        Modifier.fillMaxSize()
            .layerBackdrop(backdrop)
            .background(MiuixTheme.colorScheme.background)
            .padding(padding),
    ) {
        GlassDropdownPreference(
            title = "日志级别",
            summary = "选择需要记录的信息",
            items = items,
            selectedIndex = selectedIndex,
            onSelectedIndexChange = { selectedIndex = it },
            backdrop = backdrop,
        )
    }
}
```

浮层可能覆盖页面栏时，应将这些栏包含在 `backdrop` 中，但排除弹窗宿主及遮罩。backdrop 为 null 时使用 `visuals.containerColor` 作为不透明回退，同时保留描边和投影。

## 组件状态

- **收起**：显示选中值和下拉箭头；`showValue = false` 仅隐藏选中值。
- **展开**：选择选项后调用 `onSelectedIndexChange` 并关闭菜单；调用方应更新 `selectedIndex`。
- **禁用**：`enabled = false` 或 `items` 为空时无法展开，已经展开的菜单也会关闭。
- **未选中**：`selectedIndex` 越界时不显示选中值或勾选图标。

关闭时保留组件组合，无需外部 `if (expanded)` 包裹或手动测量锚点。

## 属性

### GlassDropdownPreference 属性

| 属性名 | 类型 | 说明 | 默认值 | 是否必须 |
| --- | --- | --- | --- | --- |
| onSelectedIndexChange | (Int) -> Unit | 回调选中项索引，由调用方更新选择 | - | 是 |
| items | List&lt;String&gt; | 可选项列表；空列表禁用设置行 | - | 是 |
| selectedIndex | Int | 选中项索引；越界时不显示选择 | - | 是 |
| title | String | 设置行标题 | - | 是 |
| backdrop | Backdrop? | 排除弹窗宿主的页面背景采样；null 使用回退 | - | 是 |
| modifier | Modifier | 应用于设置行的修饰符 | Modifier | 否 |
| enabled | Boolean | 是否允许展开；还要求 items 非空 | true | 否 |
| showValue | Boolean | 是否在箭头旁显示选中值 | true | 否 |
| renderInRootScaffold | Boolean | 使用最外层 Scaffold；false 使用当前 Scaffold | true | 否 |
| titleColor | BasicComponentColors | 启用和禁用时的标题颜色 | BasicComponentDefaults.titleColor() | 否 |
| summary | String? | 标题下方的辅助说明 | null | 否 |
| summaryColor | BasicComponentColors | 启用和禁用时的说明颜色 | BasicComponentDefaults.summaryColor() | 否 |
| startAction | (@Composable () -> Unit)? | 前置内容 | null | 否 |
| bottomAction | (@Composable () -> Unit)? | 设置行下方内容 | null | 否 |
| insideMargin | PaddingValues | 设置行内容内边距 | BasicComponentDefaults.InsideMargin | 否 |
| sizing | GlassPopupSizing | 菜单尺寸限制和窗口安全边距 | GlassPopupSizing() | 否 |
| visuals | GlassPopupVisuals | 菜单材质、描边、投影和回退色 | GlassPopupDefaults.visuals() | 否 |
| onExpandedChange | ((Boolean) -> Unit)? | 通知目标展开状态变化，不表示动画完成 | null | 否 |

尺寸和外观选项见[共享弹窗默认值](./glasspopup#shared-defaults)。

## 进阶用法

### 自定义菜单外观

替换基本示例中背景采样页面内的设置行调用，即可调整菜单尺寸并移除投影：

```kotlin
GlassDropdownPreference(
    title = "日志级别",
    items = items,
    selectedIndex = selectedIndex,
    onSelectedIndexChange = { selectedIndex = it },
    backdrop = backdrop,
    showValue = false,
    sizing = GlassPopupSizing(maxWidth = 320.dp, maxHeight = 480.dp),
    visuals = GlassPopupDefaults.visuals(shadow = null),
)
```


### 观察展开状态

通过 `onExpandedChange` 更新辅助说明或协调附近界面，将状态及设置行放在背景采样页面内：

```kotlin
var expanded by remember { mutableStateOf(false) }

GlassDropdownPreference(
    title = "日志级别",
    summary = if (expanded) "选择一个级别" else "选择需要记录的信息",
    items = items,
    selectedIndex = selectedIndex,
    onSelectedIndexChange = { selectedIndex = it },
    backdrop = backdrop,
    onExpandedChange = { expanded = it },
)
```

