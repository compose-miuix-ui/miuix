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

`GlassDropdownPreference` 是 Miuix 中带有玻璃弹出菜单、标题和可选说明的下拉设置组件。它管理展开和关闭，调用方负责控制选中项。

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

下例记录包含设置行的页面 `Column`，由 `Scaffold` 在采样范围之外承载弹窗。

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

浮层可能覆盖页面顶栏或底栏时，应将这些栏包含在背景采样中，但排除弹窗宿主及其遮罩。背景采样为空时，使用 `visuals.containerColor` 作为不透明回退，同时保留配置的描边与投影。

## 组件状态

- **收起**：设置行显示选中值和下拉箭头，`showValue = false` 仅隐藏选中值。
- **展开**：选中值和箭头淡出，设置行其余内容保持不动。选中菜单项显示勾选图标。选择选项后请求关闭并调用 `onSelectedIndexChange`，调用方须更新 `selectedIndex`。
- **禁用**：`enabled = false` 或 `items` 为空时无法展开，已经展开的菜单也会关闭。
- **未选中**：`selectedIndex` 越界时不显示选中值或勾选图标。

展开和选择分别提供上下文点击与确认触觉反馈。点击外部或返回会关闭菜单；取消预测性返回手势后菜单会恢复。组件在内部保留离场内容，并在请求关闭时立即停止菜单行输入和无障碍操作。无需使用外部 `if (expanded)` 包裹，也无需手动测量锚点。

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

设置行通过 `BasicComponentDefaults` 获取内边距及标题、说明颜色。菜单行使用 `GlassPopupItem`。`GlassPopupSizing()` 默认最小宽度为 `200.dp`、最大宽度为 `288.dp`、最大高度为 `416.dp`、安全边距为 `12.dp`。外观选项详见[共享弹窗默认值](./glasspopup#shared-defaults)。本组件不单独提供圆角参数。

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

菜单仍使用默认的主题材质和辉光描边。在嵌套 Scaffold 中，`renderInRootScaffold = false` 选择当前宿主，应确保该宿主同样位于传入背景采样的记录范围之外。

### 观察展开状态

通过 `onExpandedChange` 更新辅助说明或协调附近界面。沿用基本示例的选中状态与背景采样，将以下状态及设置行放在背景采样页面内：

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

回调只观察展开变化，不会将展开状态改为外部控制。`false` 表示开始关闭，而非离场动画完成。展开、锚点测量与离场组合仍由组件管理。
