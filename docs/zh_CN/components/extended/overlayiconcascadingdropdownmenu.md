---
title: OverlayIconCascadingDropdownMenu
requiresScaffoldHost: true
prerequisites:
  - 必须在 `Scaffold` 中使用以提供 `MiuixPopupHost`
  - 未在 `Scaffold` 中使用将导致弹出内容无法渲染
  - 支持多个嵌套或并列的 `Scaffold`，无需额外配置
hostComponent: Scaffold
popupHost: MiuixPopupHost
---

# OverlayIconCascadingDropdownMenu

`OverlayIconCascadingDropdownMenu` 是基于 `IconButton` 的封装，点击图标按钮后会展开 `OverlayCascadingListPopup`。适用于工具栏的 action 槽位（例如 `TopAppBar` 的右侧动作按钮）——单个图标按钮展开后呈现一组带子菜单的项。`DropdownItem.children` 非空的项会成为子菜单触发行；级联深度限制为 **2 级**。

<div style="position: relative; height: 410px; border-radius: 10px; overflow: hidden; border: 1px solid #777;">
    <iframe id="demoIframe" style="position: absolute; top: 0; left: 0; width: 100%; height: 100%; border: none;" src="../../../compose/index.html?id=overlayIconCascadingDropdownMenu" title="Demo" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin"></iframe>
</div>

::: danger 使用前提
此组件依赖 `Scaffold` 提供的 `MiuixPopupHost` 以显示弹出内容。必须在 `Scaffold` 中使用，否则弹出内容无法正常渲染。
:::

## 引入

```kotlin
import top.yukonga.miuix.kmp.menu.OverlayIconCascadingDropdownMenu
import top.yukonga.miuix.kmp.basic.DropdownEntry
import top.yukonga.miuix.kmp.basic.DropdownItem
```

## 基本用法

将 `OverlayIconCascadingDropdownMenu` 放在 `TopAppBar`（或 `SmallTopAppBar`）的 `actions` 槽中，点击图标即可展开弹出框。`children` 非空的顶层项会带 chevron，点击后就地展开二级菜单。

```kotlin
var sortIndex by remember { mutableStateOf(0) }
var viewIndex by remember { mutableStateOf(0) }
var filterIndex by remember { mutableStateOf(0) }

val sortLabels = listOf("按拍摄日期排序", "按添加日期排序")
val viewLabels = listOf("按日期分组", "紧凑视图")
val filterLabels = listOf("全部内容", "相机相册")

val entries = listOf(
    DropdownEntry(
        items = sortLabels.mapIndexed { idx, label ->
            DropdownItem(
                text = label,
                selected = sortIndex == idx,
                onClick = { sortIndex = idx },
            )
        },
    ),
    DropdownEntry(
        items = listOf(
            DropdownItem(
                text = "查看模式",
                children = viewLabels.mapIndexed { idx, label ->
                    DropdownItem(
                        text = label,
                        selected = viewIndex == idx,
                        onClick = { viewIndex = idx },
                    )
                },
            ),
            DropdownItem(
                text = "筛选",
                children = filterLabels.mapIndexed { idx, label ->
                    DropdownItem(
                        text = label,
                        selected = filterIndex == idx,
                        onClick = { filterIndex = idx },
                    )
                },
            ),
        ),
    ),
)

Scaffold(
    topBar = {
        SmallTopAppBar(
            title = "图库",
            actions = {
                OverlayIconCascadingDropdownMenu(entries = entries) {
                    Icon(imageVector = MiuixIcons.Tune, contentDescription = "调整")
                }
            }
        )
    }
) { padding ->
    // 页面内容
}
```

## 单 Entry 重载

只需要一个分组时，可以使用 entry 重载省略外层的 `listOf(...)`。

```kotlin
val entry = DropdownEntry(
    items = listOf(
        DropdownItem(
            text = "查看模式",
            children = listOf(
                DropdownItem(text = "按日期分组", onClick = { /* ... */ }),
                DropdownItem(text = "紧凑视图", onClick = { /* ... */ }),
            ),
        ),
        DropdownItem(text = "刷新", onClick = { /* ... */ }),
    ),
)

Scaffold {
    OverlayIconCascadingDropdownMenu(entry = entry) {
        Icon(imageVector = MiuixIcons.Tune, contentDescription = "调整")
    }
}
```

## 组件状态

### 禁用状态

```kotlin
OverlayIconCascadingDropdownMenu(
    entry = DropdownEntry(items = listOf(DropdownItem(text = "选项 1"))),
    enabled = false,
) {
    Icon(imageVector = MiuixIcons.MoreCircle, contentDescription = "更多")
}
```

当所有 `DropdownEntry` 都不包含任何条目时，菜单也会被隐式禁用。

::: tip 级联深度
级联深度上限为 2。二级菜单中的项不会再处理自己的 `children`；更深层的子树会被静默忽略。
:::

## 属性

### OverlayIconCascadingDropdownMenu 属性（Entries 重载）

| 属性名               | 类型                      | 说明                                                                                                            | 默认值                              | 是否必须 |
| -------------------- | ------------------------- | -------------------------------------------------------------------------------------------------------------- | ----------------------------------- | -------- |
| entries              | List\<DropdownEntry>      | 由分割线分组的下拉条目；顶层中 `children` 非空的项会成为子菜单触发行                                            | -                                   | 是       |
| modifier             | Modifier                  | 应用于外层 `Box` 的修饰符                                                                                       | Modifier                            | 否       |
| enabled              | Boolean                   | 图标按钮是否可交互                                                                                              | true                                | 否       |
| maxHeight            | Dp?                       | 级联弹窗任一面的最大高度                                                                                        | null                                | 否       |
| dropdownColors       | DropdownColors            | 下拉选项的颜色配置                                                                                              | DropdownDefaults.dropdownColors()   | 否       |
| renderInRootScaffold | Boolean                   | 是否在根（最外层）Scaffold 中渲染弹窗。为 true 时，弹窗覆盖全屏。为 false 时，在当前 Scaffold 的范围内渲染并进行位置补偿 | true | 否 |
| collapseOnSelection  | Boolean                   | 选中任何叶子项后是否关闭弹出框                                                                                  | true                                | 否       |
| onExpandedChange     | ((Boolean) -> Unit)?      | 展开状态变化时的回调                                                                                            | null                                | 否       |
| backgroundColor      | Color                     | 底层 `IconButton` 的背景颜色                                                                                    | Color.Unspecified                   | 否       |
| cornerRadius         | Dp                        | 底层 `IconButton` 的圆角半径                                                                                    | IconButtonDefaults.CornerRadius     | 否       |
| minHeight            | Dp                        | 底层 `IconButton` 的最小高度                                                                                    | IconButtonDefaults.MinHeight        | 否       |
| minWidth             | Dp                        | 底层 `IconButton` 的最小宽度                                                                                    | IconButtonDefaults.MinWidth         | 否       |
| content              | @Composable () -> Unit    | 按钮内显示的图标（或其他可组合内容）                                                                            | -                                   | 是       |

### Entry 重载属性

| 属性名              | 类型          | 说明                            | 默认值 | 是否必须 |
| ------------------- | ------------- | ------------------------------- | ------ | -------- |
| entry               | DropdownEntry | 单个下拉选项分组                | -      | 是       |
| collapseOnSelection | Boolean       | 选中任何叶子项后是否关闭弹出框  | true   | 否       |

其余参数与上方 entries 重载完全一致。

### DropdownEntry 属性

| 属性名  | 类型                | 说明                                                                              | 默认值 | 是否必须 |
| ------- | ------------------- | --------------------------------------------------------------------------------- | ------ | -------- |
| items   | List\<DropdownItem> | 此分组中显示的条目                                                                | -      | 是       |
| enabled | Boolean             | 此分组是否启用。为 false 时禁用整组条目；为 true 时仍会遵循每个条目的 enabled 状态 | true   | 否       |

### DropdownItem 属性

| 属性名   | 类型                              | 说明                                                                          | 默认值 | 是否必须 |
| -------- | --------------------------------- | ----------------------------------------------------------------------------- | ------ | -------- |
| text     | String                            | 选项显示的文本                                                                | -      | 是       |
| enabled  | Boolean                           | 选项是否可点击，禁用时置灰                                                    | true   | 否       |
| selected | Boolean                           | 选项是否处于选中状态                                                          | false  | 否       |
| onClick  | (() -> Unit)?                     | 点击选项时触发的回调。当 `children` 非空时被忽略（点击会改为展开二级菜单）    | null   | 否       |
| icon     | @Composable ((Modifier) -> Unit)? | 显示在选项文本前的图标                                                        | null   | 否       |
| summary  | String?                           | 显示在选项文本下方的摘要文本                                                  | null   | 否       |
| children | List\<DropdownItem>?              | 可选的子菜单项；仅级联变体会将其渲染为二级菜单（最多两级）                    | null   | 否       |

### DropdownColors 属性

| 属性名                 | 类型  | 说明             |
| ---------------------- | ----- | ---------------- |
| contentColor           | Color | 选项标题颜色     |
| summaryColor           | Color | 选项摘要颜色     |
| containerColor         | Color | 选项背景颜色     |
| selectedContentColor   | Color | 选中项标题颜色   |
| selectedSummaryColor   | Color | 选中项摘要颜色   |
| selectedContainerColor | Color | 选中项背景颜色   |
| selectedIndicatorColor | Color | 选中指示图标颜色 |
