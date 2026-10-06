# Saathi AI — UI & Core Features

**Status:** Native Jetpack Compose implementation of the first UI milestone. The app name remains provisional.

## Product direction

A privacy-conscious, Hinglish-friendly Android companion with an original dark/red identity. The current milestone is an installable Compose UI prototype; live AI and device actions remain unimplemented until their backend, permissions, and consent model are deliberately configured.

## Implemented screens

- **Home:** greeting, preview badge, voice/orb explainer, chat entry point, and quick prompts.
- **Chat:** Compose message bubbles, input field, IME send action, quick suggestions, saved session-only chat state, and deterministic local example replies.
- **Tools:** planned cards for timers, search, maps, calls, files/OCR, and notifications; tapping a card explains that it is inactive.
- **Settings:** display-only preferences, clear demo state, and privacy notice.
- **Navigation:** native Material 3 bottom navigation for Home, Chat, Tools, and Settings, plus back-to-home handling.

## Not connected in this milestone

- No AI provider, model-selection UI, network client, API key, or conversation backend.
- No microphone permission, speech recognition, or text-to-speech.
- No location, calls, contacts, messages, notification access, files/OCR, screen automation, or PC connection.
- The manifest declares no app permissions; add them only alongside a real feature and just-in-time consent.

## Android build

- Native single-activity Kotlin/Compose app; application ID `com.saathi.aiassistant`.
- Compose compiler plugin and Kotlin Gradle Plugin 2.4.20; Android Gradle Plugin 9.3.1; Gradle 9.7.0 wrapper.
- Compose BOM 2026.09.00; compile/target SDK 37, minimum SDK 24.
- Debug APK is self-contained and debug-signed for sideload testing.
- Release build is unsigned and kept unminified until a protected signing/release process is designed.
- GitHub Actions runs unit tests, Android lint, debug APK build, and unsigned release compilation, then uploads the debug APK artifact.

## Safety and platform limits

Do not claim that the demo replies come from an AI model. Never ship provider secrets in the APK. Any future actions that send messages, place calls, read private data, or control the screen need deliberate implementation, explicit permission, user confirmation where appropriate, and platform-policy review.
