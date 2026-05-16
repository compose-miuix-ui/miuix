NFC Tool - Compose Multiplatform NFC Utility Application

## Quick Start

- Always use Chinese to communicate with users, but the generated code (including comments and KDoc) must remain in English.
- For significant features or refactors, sketch a plan first; keep it updated as you work.

## Key Commands

| Action                | Command                                                 |
| :-------------------- | :------------------------------------------------------ |
| Build (full)          | `./gradlew assemble`                                    |
| Build (quick check)   | `./gradlew compileKotlinDesktop`                        |
| Test                  | `./gradlew check`                                       |
| Run Android demo      | `./gradlew :app:android:installDebug`                   |
| Run Desktop demo      | `./gradlew :app:desktop:run`                            |
| Run Web demo          | `./gradlew :app:web:wasmJsBrowserRun`                   |
| Run Web demo (JS)     | `./gradlew :app:web:jsBrowserDevelopmentRun`            |

## Repository Structure

| Directory                | Purpose                                                        |
| :----------------------- | :------------------------------------------------------------- |
| `nfctool-core/`          | NFC data models, platform abstractions (expect/actual), NfcManager |
| `app/shared/`            | Shared UI code — screens, components, theme, state management  |
| `app/android/`           | Android entry — MainActivity with NFC intent handling          |
| `app/desktop/`           | Desktop entry — JVM window with viewer mode                    |
| `app/web/`               | Web entry — Wasm/JS browser app                                |
| `build-plugins/`         | Custom Gradle plugins (BuildConfig, spotless)                  |
| `gradle/`                | Version catalog (`libs.versions.toml`) and wrapper             |

### Source Layout

```
nfctool-core/src/commonMain/kotlin/com/nfctool/core/
├── model/                # NfcTag, NdefRecord, SavedNfcRecord, NfcEnums
├── platform/             # NfcAdapter interface, expect declarations
└── NfcManager.kt         # Facade over platform NFC adapter

nfctool-core/src/androidMain/     # Android NFC implementation (android.nfc APIs)
nfctool-core/src/skikoMain/       # Stub for non-Android platforms

app/shared/src/commonMain/kotlin/com/nfctool/app/ui/
├── App.kt                # Root composable with navigation
├── theme/                # NfcToolTheme (Material3 color scheme)
├── state/                # AppState, LocalNfcManager
├── screen/               # ScanScreen, TagDetailScreen, WriteScreen, HistoryScreen, SettingsScreen
└── component/            # TagCard, NfcStatusIndicator
```

### Platform Source Sets

```
commonMain
├── androidMain
└── skikoMain (Skiko rendering — all non-Android targets)
    ├── darwinMain (iOS)
    │   └── iosMain
    ├── desktopMain (JVM)
    └── webMain
        ├── wasmJsMain
        └── jsMain
```

## Architecture

### NFC Abstraction Layer

The NFC functionality is abstracted using Kotlin's `expect`/`actual` mechanism:

- `commonMain` defines the `NfcAdapter` interface and `expect class PlatformNfcAdapter`
- `androidMain` implements `PlatformNfcAdapter` using `android.nfc` APIs
- `skikoMain` provides a stub implementation for non-NFC platforms (Desktop, Web, iOS)

### Navigation

Simple state-based navigation using an enum `Screen`:
```
SCAN → TAG_DETAIL → WRITE
  ↓
HISTORY, SETTINGS
```

### State Management

`AppState` is a data class holding all UI state. The `LocalNfcManager` CompositionLocal provides access to the NFC manager.

## NFC Features

- **Read**: Scan NDEF-formatted tags (text, URI, Smart Poster records)
- **Write**: Write text or URI NDEF records to writable tags
- **View**: Inspect tag details (UID, technology, supported techs, records)
- **History**: View previously scanned tags
- **Settings**: Configure app preferences

### Supported Tag Technologies (Android)
- NDEF
- Mifare Classic / Ultralight
- NFC-A, NFC-B, NFC-F, NFC-V
- ISO-DEP
- NDEF Formatable

### Platform Support
- **Android**: Full NFC support (read/write)
- **Desktop**: Viewer mode only (no NFC hardware)
- **Web**: Viewer mode only (Web NFC API not yet implemented)
- **iOS**: Viewer mode only (Core NFC requires additional setup)

## Code Style

- Follow Kotlin official code style
- Composable functions: PascalCase with `@Composable` annotation
- Data models: data classes with `@Serializable` for persistence
- Use `remember` with keys for derived values
- Theme colors from `MaterialTheme.colorScheme.*`