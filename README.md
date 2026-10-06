# Saathi AI

Saathi is a native Android app built with **Kotlin and Jetpack Compose**, using an original dark/red visual identity inspired by the supplied assistant reference without copying its logo or artwork.

## Current functionality

The app contains four native Compose destinations: **Home**, **Chat**, **Tools**, and **Settings**. Chat preserves the previous session-only local demo replies. The voice control explains that microphone capture is not connected; tools and settings clearly show planned/unavailable states. No AI provider, network request, microphone capture, contacts/location access, notifications listener, or device-control action is implemented.

The manifest requests no app permissions. The UI does not pretend planned actions are active. A future AI provider must use a protected backend; never place provider keys in this Android client.

## Build requirements

- JDK 17 or newer (CI uses JDK 21)
- Android SDK Platform 37.0
- Android SDK Build-Tools 36.0.0

From the repository root:

```bash
cd android
./gradlew testDebugUnitTest lintDebug assembleDebug
```

The APK is generated at `android/app/build/outputs/apk/debug/app-debug.apk`. A CI workflow on pushes to `main` and manual dispatch uploads the debug APK as `saathi-compose-debug-apk` for 14 days.

## Release status

`assembleRelease` is built unsigned for compilation checks only. No production signing key is present, and the release artifact is not suitable for installation or Play Store distribution until a protected production signing process is configured. The debug APK uses the standard temporary debug signing key for sideload testing.

## Project history

The initial prototype used Expo/React Native. This repository has been migrated in place to native Compose, preserving the existing screens and demo behavior. The public source repository remains [`levinhocall/Saathi-`](https://github.com/levinhocall/Saathi-).
