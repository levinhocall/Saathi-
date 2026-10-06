# Saathi Android (Jetpack Compose)

This repository is a native Android application built with Kotlin and Jetpack Compose. The Android application module is `android/app` and the application ID is `com.saathi.aiassistant`.

## Build and checks

From the repository root:

```bash
cd android
./gradlew testDebugUnitTest lintDebug assembleDebug
```

Java 17 or newer is required (CI uses Java 21). Install Android SDK Platform 37.0 and Android SDK Build-Tools 36.0.0. The Gradle wrapper and SDK versions are checked into or declared by this project; do not replace them with arbitrary versions.

## Architecture

- Keep Android UI in Jetpack Compose under `android/app/src/main/java/com/saathi/aiassistant/ui/`.
- Keep platform entry points minimal in `MainActivity.kt`.
- Current Chat is intentionally a local demo responder. Do not claim it calls an AI provider or performs phone actions.
- Never add API secrets to Kotlin source, resources, Gradle files, APK assets, or documentation. A future provider integration must use a protected backend and explicit error states.
- Request Android permissions only when a real implemented feature needs them, with clear user-facing consent.

## Verification

Run unit tests and Android lint before declaring a change complete. Verify the APK package ID and signature. If no emulator/device is available, report that a device launch smoke test could not be performed.
