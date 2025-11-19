# Migration Guide: 0.6.1 to 0.7.0

## Overview

Version 0.7.0 represents a complete rewrite of the Miuix library. This major version brings significant improvements in architecture, performance, and API design, but also introduces breaking changes that require migration effort.

::: warning Important
This is a major breaking release. Please read this guide carefully before upgrading.
:::

## What's New in 0.7.0

### Highlights

- **Complete Library Rewrite**: The entire codebase has been restructured for better maintainability and performance
- **Enhanced Component Architecture**: All components have been redesigned with improved composition and modularity
- **Better Type Safety**: Stricter type definitions and null safety improvements
- **Performance Optimizations**: Reduced recompositions and improved rendering performance
- **Expanded Platform Support**: Better support across all Compose Multiplatform targets
- **Improved Documentation**: Comprehensive documentation with examples for all components

### New Components

Version 0.7.0 introduces all the components from the previous version with improved implementations:

- **Basic Components**: Button, Card, Checkbox, Divider, Icon, IconButton, SmallTitle, Surface, Switch, Text, TextField
- **Layout Components**: Scaffold, TopAppBar, NavigationBar, TabRow
- **Interactive Components**: Slider, ProgressIndicator, FloatingActionButton, FloatingToolbar
- **Advanced Components**: PullToRefresh, SearchBar, ColorPalette, ColorPicker, ListPopup
- **Extended Components**: SuperArrow, SuperSwitch, SuperCheckbox, SuperDropdown, SuperSpinner, SuperDialog, SuperBottomSheet

### Architecture Improvements

1. **Modular Package Structure**: Better organized package hierarchy
2. **Consistent API Design**: Unified parameter naming and ordering across components
3. **Enhanced Theme System**: More flexible and powerful theming capabilities
4. **Improved Utils**: Better utility functions for common tasks

## Breaking Changes

### Package Structure Changes

The entire package structure has been reorganized. You will need to update all import statements.

**Before (0.6.1):**
```kotlin
// Imports varied and were less organized
```

**After (0.7.0):**
```kotlin
// Basic components
import top.yukonga.miuix.kmp.basic.*

// Extended components
import top.yukonga.miuix.kmp.extra.*

// Theme
import top.yukonga.miuix.kmp.theme.*

// Utils
import top.yukonga.miuix.kmp.utils.*

// Icons
import top.yukonga.miuix.kmp.icon.icons.useful.*
import top.yukonga.miuix.kmp.icon.icons.basic.*
```

### Component API Changes

Due to the complete rewrite, all component APIs have been redesigned. The general patterns remain similar, but specific parameters and behaviors may differ.

### Theme System Changes

The theme system has been completely redesigned:

**Before (0.6.1):**
```kotlin
// Old theme setup
```

**After (0.7.0):**
```kotlin
@Composable
fun App() {
    val controller = remember { ThemeController(ColorSchemeMode.System) }
    
    MiuixTheme(controller = controller) {
        // Your content
    }
}
```

Available color scheme modes:
- `ColorSchemeMode.System`
- `ColorSchemeMode.Light`
- `ColorSchemeMode.Dark`
- `ColorSchemeMode.MonetSystem`
- `ColorSchemeMode.MonetLight`
- `ColorSchemeMode.MonetDark`

### Icon System Changes

Icons have been reorganized into clearer categories:

- `MiuixIcons.Basic.*` - Basic icons (arrows, check, search)
- `MiuixIcons.Useful.*` - Useful action icons (back, confirm, delete, edit, etc.)
- `MiuixIcons.Other.*` - Other icons (GitHub, etc.)

## Migration Steps

### Step 1: Update Dependencies

Update your `build.gradle.kts` to use version 0.7.0:

```kotlin
kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation("top.yukonga.miuix.kmp:miuix:0.7.0")
        }
    }
}
```

### Step 2: Update Imports

Due to the complete rewrite, you'll need to update all import statements. Use your IDE's auto-import feature to help with this process.

### Step 3: Update Theme Setup

Replace your existing theme setup with the new `MiuixTheme` and `ThemeController`:

```kotlin
@Composable
fun App() {
    val controller = remember { ThemeController(ColorSchemeMode.System) }
    
    MiuixTheme(controller = controller) {
        Scaffold(
            topBar = { /* TopBar content */ },
            bottomBar = { /* BottomBar content */ },
        ) { paddingValues ->
            // Your app content
        }
    }
}
```

### Step 4: Update Component Usage

Review each component's documentation and update your code accordingly:

- [Scaffold](/components/scaffold)
- [TopAppBar](/components/topappbar)
- [NavigationBar](/components/navigationbar)
- [Button](/components/button)
- [Card](/components/card)
- And all other components...

### Step 5: Test Thoroughly

After migration:

1. **Build your project** to identify compilation errors
2. **Run your app** on all target platforms
3. **Test all features** to ensure correct behavior
4. **Verify visual appearance** matches expectations

## Common Migration Issues

### Issue: Import Errors

**Problem**: Previous imports no longer work.

**Solution**: Update to the new package structure. Use IDE auto-import or refer to component documentation.

### Issue: Component API Changes

**Problem**: Component parameters or behavior differs.

**Solution**: Check the specific component documentation for the new API.

### Issue: Theme Not Applied

**Problem**: Colors or styles not applying correctly.

**Solution**: Ensure you've wrapped your content with `MiuixTheme` and created a `ThemeController`.

## Getting Help

If you encounter issues during migration:

1. Check the [component documentation](/components/)
2. Review [best practices](/guide/best-practices)
3. Open an issue on [GitHub](https://github.com/compose-miuix-ui/miuix/issues)
4. Join community discussions

## Conclusion

While version 0.7.0 introduces breaking changes, it provides a solid foundation for future development with better architecture, performance, and maintainability. The migration effort is worthwhile for the long-term benefits.

Thank you for using Miuix! We appreciate your patience during this major transition.
