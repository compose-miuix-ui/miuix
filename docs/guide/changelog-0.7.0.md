# Changelog: Version 0.7.0

## Release Date: November 19, 2025

::: info Version 0.7.0
This is a major release with breaking changes. Please see the [Migration Guide](/guide/migration-0.7.0) for upgrade instructions.
:::

## Overview

Version 0.7.0 represents a complete rewrite of the Miuix library, bringing significant improvements in architecture, performance, and developer experience. This release establishes a solid foundation for future development.

## Major Changes

### Complete Library Rewrite

The entire codebase has been rebuilt from the ground up with:

- **Modern Architecture**: Improved code organization and modularity
- **Better Performance**: Optimized rendering and reduced recompositions
- **Enhanced Type Safety**: Stricter type definitions and null safety
- **Consistent APIs**: Unified parameter naming and behavior across components
- **Comprehensive Documentation**: Full documentation for all components with examples

### All Components Rebuilt

Every component has been reimplemented with improvements:

#### Basic Components
- **Button** - Rebuilt with better state management and animations
- **Card** - Enhanced with improved elevation and interaction handling
- **Checkbox** - Refined animations and accessibility
- **Divider** - Both horizontal and vertical dividers with better styling
- **Icon** - Improved icon rendering and sizing
- **IconButton** - Enhanced with better touch targets and feedback
- **SmallTitle** - Consistent typography and spacing
- **Surface** - Better background and elevation handling
- **Switch** - Smoother animations and interactions
- **Text** - Enhanced text rendering with better style inheritance
- **TextField** - Improved input handling and validation support

#### Layout Components
- **Scaffold** - Complete rewrite with better layout management
- **TopAppBar** - Enhanced scroll behavior and customization
- **NavigationBar** - Improved item management and animations
- **TabRow** - Better tab switching and indicator animations

#### Interactive Components
- **Slider** - Enhanced with smoother dragging and haptic feedback
- **ProgressIndicator** - Linear, circular, and infinite progress variants
- **FloatingActionButton** - Improved elevation and animations
- **FloatingToolbar** - New component for floating action toolbars

#### Advanced Components
- **PullToRefresh** - Smooth pull-to-refresh with customizable indicators
- **SearchBar** - Enhanced search functionality with animations
- **ColorPalette** - Interactive color selection palette
- **ColorPicker** - Advanced color picker with multiple color spaces
- **ListPopup** - Improved popup positioning and animations

#### Extended Components
- **SuperArrow** - Enhanced arrow component for settings
- **SuperSwitch** - Extended switch with additional features
- **SuperCheckbox** - Advanced checkbox with custom content
- **SuperDropdown** - Improved dropdown with better item management
- **SuperSpinner** - Enhanced spinner with customizable options
- **SuperDialog** - Flexible dialog system with animations
- **SuperBottomSheet** - Modal bottom sheet with drag support

### Theme System Overhaul

Complete redesign of the theme system:

- **ThemeController**: New centralized theme management
- **Multiple Color Schemes**: Support for Light, Dark, and Monet variants
- **Dynamic Colors**: Better dynamic color support across platforms
- **Material 3 Mapping**: Improved Material 3 color scheme mapping
- **Custom Styling**: Enhanced customization capabilities

### Icon System Reorganization

Icons have been reorganized into logical categories:

- **Basic Icons**: Essential icons (arrows, check, search)
- **Useful Icons**: Action icons (back, confirm, delete, edit, save, etc.)
- **Other Icons**: Third-party service icons (GitHub, etc.)

### Utility Improvements

Enhanced utility functions and modifiers:

- **MiuixIndication**: Custom indication effects for interactions
- **Pressable**: Improved pressable modifier with feedback
- **PressFeedback**: Enhanced press feedback system
- **Overscroll**: Custom overscroll effects
- **ScrollEndHaptic**: Haptic feedback for scroll end
- **MiuixPopupUtils**: Utilities for popup management

### Platform Support

Improved support across all platforms:

- **Android**: Full support with platform-specific optimizations
- **iOS**: Enhanced iOS support with native feel
- **Desktop (JVM)**: Better desktop integration
- **WasmJS**: WebAssembly JavaScript support
- **JavaScript**: Browser JavaScript support
- **macOS Native**: Native macOS support

## Breaking Changes

::: danger Breaking Changes
This release contains many breaking changes due to the complete rewrite. Migration is required.
:::

### Package Structure

- All packages have been reorganized
- Import statements need to be updated
- New package hierarchy: `top.yukonga.miuix.kmp.{basic,extra,theme,utils,icon}`

### Component APIs

- Component parameter names and ordering may have changed
- Some components have different default values
- Behavior changes in scroll and animation components

### Theme System

- Complete replacement of the old theme system
- New `ThemeController` and `MiuixTheme` APIs
- Different color scheme modes

### Icon Paths

- Icons moved to new packages
- `MiuixIcons.Basic.*` for basic icons
- `MiuixIcons.Useful.*` for action icons
- `MiuixIcons.Other.*` for third-party icons

## Migration Guide

Please refer to the comprehensive [Migration Guide](/guide/migration-0.7.0) for detailed instructions on upgrading from version 0.6.1 to 0.7.0.

## Documentation

Complete documentation is now available:

- **Component Documentation**: Full documentation for every component
- **Code Examples**: Working examples for all features
- **API Reference**: Detailed API documentation
- **Best Practices**: Guidelines for using Miuix effectively

## Acknowledgments

Thank you to all contributors and users who provided feedback during the development of this major release. Special thanks to:

- [@YuKongA](https://github.com/YuKongA) - Lead developer and architecture
- [@HowieHChen](https://github.com/HowieHChen) - Core contributor
- [@Voemp](https://github.com/Voemp) - Component development
- All community members who tested and provided feedback

## Getting Started

To start using version 0.7.0:

1. Update your dependency to `top.yukonga.miuix.kmp:miuix:0.7.0`
2. Follow the [Migration Guide](/guide/migration-0.7.0)
3. Explore the [Component Documentation](/components/)
4. Check out [Best Practices](/guide/best-practices)

## Support

If you encounter any issues:

- Check the [documentation](/guide/getting-started)
- Review the [migration guide](/guide/migration-0.7.0)
- Search [existing issues](https://github.com/compose-miuix-ui/miuix/issues)
- Create a [new issue](https://github.com/compose-miuix-ui/miuix/issues/new) if needed

## What's Next

Future versions will focus on:

- Additional components based on community feedback
- Performance optimizations
- Enhanced accessibility features
- More platform-specific optimizations
- Expanded documentation and examples

---

**Full Changelog**: [v0.6.1...v0.7.0](https://github.com/compose-miuix-ui/miuix/compare/v0.6.1...v0.7.0)
