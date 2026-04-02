---
name: create-component
description: Create a new Compose Multiplatform UI component for the miuix library. Use when the user wants to add a new component, create a new composable, scaffold a component file, or add a UI element to miuix. Triggers on phrases like "create component", "new component", "add a component", "scaffold component", "new composable", "新建组件", "添加组件", "新组件".
---

# 创建 Miuix 组件

为 Compose Multiplatform 的 UI 库生成新的组件文件，严格遵循项目已有的代码规范。

始终使用中文与用户沟通、输出 Plan 内容，但生成的代码（包括注释和 KDoc）需保持英文。

## 第一步：收集需求

询问用户以下尚未提供的信息：

1. **组件名称** — PascalCase 命名（如 `Chip`、`Badge`、`Tooltip`）
2. **分类** — `basic` 或 `extra`
   - `basic`：独立的 UI 基础组件，如 Button、Card、Switch、Slider
   - `extra`：基于 `BasicComponent` 封装的组合组件，带有标题/摘要/操作区布局，通常使用 "Super" 前缀（SuperArrow、SuperSwitch、SuperCheckbox）
3. **简要描述** — 组件的功能和使用场景
4. **关键参数** — 组件需要的特定状态、回调或配置项
5. **是否需要颜色自定义** — 大多数组件都需要；极简组件可以省略

## 第二步：阅读参考文件

生成代码前，根据组件类型阅读对应的参考文件以精确匹配代码风格：

**basic 组件参考：**
- `miuix/src/commonMain/kotlin/top/yukonga/miuix/kmp/basic/Button.kt` — 可点击组件模式（Surface + 交互）
- `miuix/src/commonMain/kotlin/top/yukonga/miuix/kmp/basic/Card.kt` — 容器组件模式（多重载 + press 反馈）
- `miuix/src/commonMain/kotlin/top/yukonga/miuix/kmp/basic/Divider.kt` — 极简组件模式（无 Colors 类，直接用 Color 参数）
- `miuix/src/commonMain/kotlin/top/yukonga/miuix/kmp/basic/ProgressIndicator.kt` — 动画/Canvas 绘制组件模式

**extra 组件参考：**
- `miuix/src/commonMain/kotlin/top/yukonga/miuix/kmp/extra/SuperArrow.kt` — 无状态 extra 模式
- `miuix/src/commonMain/kotlin/top/yukonga/miuix/kmp/extra/SuperSwitch.kt` — 带状态 extra 模式（checked + onCheckedChange）
- `miuix/src/commonMain/kotlin/top/yukonga/miuix/kmp/basic/Component.kt` — BasicComponent 完整 API

根据新建组件的复杂度，选择最接近的参考文件阅读。严格匹配编码风格、缩进和约定。

## 第三步：生成组件文件

### 文件位置

- basic 组件：`miuix/src/commonMain/kotlin/top/yukonga/miuix/kmp/basic/{ComponentName}.kt`
- extra 组件：`miuix/src/commonMain/kotlin/top/yukonga/miuix/kmp/extra/{ComponentName}.kt`

### 文件结构（严格按以下顺序）

#### 3.1 版权头

```kotlin
// Copyright 2025, compose-miuix-ui contributors
// SPDX-License-Identifier: Apache-2.0
```

#### 3.2 包声明

```kotlin
package top.yukonga.miuix.kmp.basic   // basic 组件
package top.yukonga.miuix.kmp.extra   // extra 组件
```

#### 3.3 导入

按来源分组组织导入。禁止使用通配符导入。只导入实际使用的内容。

导入规则：

- 按来源分组：Foundation/Layout、Runtime、UI、`top.yukonga.miuix`
- 禁止通配符导入（`*`），只导入实际使用的内容
- 圆角形状使用 `miuixShape(cornerRadius)` / `miuixCapsuleShape()` / `miuixUnevenShape(...)`，**不要**直接使用 `RoundedCornerShape` / `CircleShape`
- 具体导入内容参照所选参考文件的 import 部分

#### 3.4 KDoc 文档

每个公开的 composable 函数必须有 KDoc，并为每个参数添加 `@param` 标签：

```kotlin
/**
 * A [{ComponentName}] component with Miuix style.
 *
 * @param paramName Description of the parameter.
 */
```

#### 3.5 主 @Composable 函数

**参数排列顺序**（严格遵循）：

1. 必需参数 — 无默认值的状态、回调、标题等（如 `onClick: () -> Unit`、`checked: Boolean`、`title: String`）
2. `modifier: Modifier = Modifier`
3. 带默认值的可选参数 — 配置、颜色、尺寸等混合排列（如 `enabled`、`colors`、`cornerRadius`、`insideMargin`）
4. 内容插槽（如 `content: @Composable RowScope.() -> Unit`）

**注解规则：**
- 始终使用 `@Composable`
- 当函数完全委托给另一个 composable 且无本地状态时，使用 `@NonRestartableComposable`（如 Button -> Surface、SuperArrow -> BasicComponent）
- 当函数有自己的状态、动画或交互源时，不要使用 `@NonRestartableComposable`

**关键内部模式：**

```kotlin
// 稳定回调引用
val currentOnClick by rememberUpdatedState(onClick)

// 形状（通过 SmoothRounding.kt 的 wrapper 函数使用）
val shape = miuixShape(cornerRadius)

// 根据状态计算颜色
val color = remember(enabled, colors) { if (enabled) colors.color else colors.disabledColor }
```

**basic 可点击组件**，使用 `Surface` 作为基础：

```kotlin
Surface(
    onClick = currentOnClick,
    enabled = enabled,
    modifier = modifier.semantics { role = Role.Button },
    shape = shape,
    color = color,
) {
    // content layout
}
```

**extra 无状态组件**（如 SuperArrow），使用 `BasicComponent` 作为基础：

```kotlin
BasicComponent(
    modifier = modifier,
    insideMargin = insideMargin,
    title = title,
    titleColor = titleColor,
    summary = summary,
    summaryColor = summaryColor,
    startAction = startAction,
    endActions = {
        // 自定义末尾操作
    },
    bottomAction = bottomAction,
    onClick = onClick,
    holdDownState = holdDownState,
    enabled = enabled,
)
```

标准 extra 组件参数参照 `SuperArrow.kt` 的函数签名。

**extra 带状态组件**（如 SuperSwitch、SuperCheckbox），额外添加状态参数并在 endActions/startAction 中嵌入控件：

```kotlin
// 参数中增加状态和回调
checked: Boolean,
onCheckedChange: ((Boolean) -> Unit)?,

// 在 endActions 中嵌入控件（如 Switch）
endActions = {
    SuperSwitchEndActions(checked, currentOnCheckedChange, enabled)
},
```

将复杂的 endActions/startAction 内容提取为私有辅助 composable：

```kotlin
@Composable
private fun RowScope.SuperSwitchEndActions(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    enabled: Boolean,
) {
    Switch(
        checked = checked,
        onCheckedChange = onCheckedChange,
        enabled = enabled,
    )
}
```

#### 3.6 Defaults 对象与颜色类

根据组件复杂度选择合适的模式：

**极简组件**（如 Divider）— 不需要自定义 Colors 类，直接用 `Color` 参数：

```kotlin
@Composable
fun HorizontalDivider(
    modifier: Modifier = Modifier,
    thickness: Dp = DividerDefaults.Thickness,
    color: Color = DividerDefaults.DividerColor,
)

object DividerDefaults {
    val Thickness = 0.75.dp
    val DividerColor @Composable get() = MiuixTheme.colorScheme.dividerLine
}
```

**标准组件** — 命名为 `{ComponentName}Defaults`：

```kotlin
object ChipDefaults {

    val CornerRadius = 16.dp

    val MinHeight = 32.dp

    @Composable
    fun chipColors(
        color: Color = MiuixTheme.colorScheme.surfaceContainer,
        disabledColor: Color = MiuixTheme.colorScheme.disabledSecondaryVariant,
    ): ChipColors = remember(color, disabledColor) {
        ChipColors(
            color = color,
            disabledColor = disabledColor,
        )
    }
}
```

#### 3.7 颜色数据类

命名为 `{ComponentName}Colors`，使用 `@Immutable` 注解和 `data class`：

```kotlin
@Immutable
data class ChipColors(
    val color: Color,
    val disabledColor: Color,
)
```

如果颜色类需要辅助函数来解析 enabled/disabled 状态，将属性设为 `private` 并添加 `@Stable internal` 函数：

```kotlin
@Immutable
data class ChipColors(
    private val color: Color,
    private val disabledColor: Color,
) {
    @Stable
    internal fun color(enabled: Boolean): Color = if (enabled) color else disabledColor
}
```

## 可用主题颜色

通过 `MiuixTheme.colorScheme.xxx` 访问。完整定义见 `miuix/src/commonMain/kotlin/top/yukonga/miuix/kmp/theme/Colors.kt`。

常用颜色：`primary`、`onPrimary`、`secondary`、`onSecondary`、`surface`、`onSurface`、`surfaceContainer`、`onSurfaceContainer`、`onSurfaceVariantActions`、`onSurfaceVariantSummary`、`dividerLine`。

## 可用文本样式

通过 `MiuixTheme.textStyles.xxx` 访问。完整定义见 `miuix/src/commonMain/kotlin/top/yukonga/miuix/kmp/theme/TextStyles.kt`。

可用样式：`main`、`paragraph`、`body1`、`body2`、`button`、`footnote1`、`footnote2`、`headline1`、`headline2`、`subtitle`、`title1`、`title2`、`title3`、`title4`

## 规则

1. **禁止使用 Material Design 相关组件** — 这是自定义 UI 库
2. **使用 `miuixShape(cornerRadius)` / `miuixCapsuleShape()`** 作为圆角形状，不要直接用 `RoundedCornerShape` / `CircleShape`
3. **所有公开 API 必须有 KDoc**，包含 `@param` 标签
4. **对所有回调参数使用 `rememberUpdatedState`**
5. **使用 `remember`** 并传入合适的 key 来缓存计算值
6. **保持文件自包含** — Defaults 对象和 Colors 类放在同一文件中
7. **严格匹配现有代码风格** — 缩进、尾逗号、顶层声明之间的空行
8. **组件优先在 commonMain 中实现** — 如确实需要平台特定功能，可创建平台特定的实现文件（使用 `expect`/`actual` 机制）
9. **返回和箭头等方向相关图标需处理 RTL** — 使用 `graphicsLayer { scaleX = if (layoutDirection == LayoutDirection.Rtl) -1f else 1f }`
10. **复杂的 endActions/startAction 内容提取为私有辅助 composable** — 命名为 `{ComponentName}EndAction()` 或类似
