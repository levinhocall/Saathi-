# Saathi AI (working title)

An original Android assistant prototype inspired by the supplied dark/red visual reference. It is not a copy of the reference app's logo or artwork.

## Current status

This is the first UI prototype: Home, Chat, Tools, and Settings screens; a session-only canned-response chat; quick prompts; and clear demo/planned labels. **No live AI provider, microphone capture, location, contacts, notification access, or other device control is connected.** No API key or sensitive Android permission is included.

See [the UI and core-features plan](docs/UI_CORE_PLAN.md) for the intended roadmap and platform limitations.

## Run locally

```bash
npm install
npx expo start
```

Open the QR code in Expo Go, or run on an Android development environment with `npx expo run:android` after the native toolchain is configured.

## Checks

```bash
npx expo lint
npx tsc --noEmit
npx expo-doctor
```

## Build an installable Android APK

An APK has **not** been built yet. This repository has an EAS `preview` profile configured to produce an APK. After signing into an Expo account (do not send login credentials in chat), configure the project and start the cloud build:

```bash
npx eas-cli@latest build:configure
npx eas-cli@latest build --platform android --profile preview
```

The completed EAS build provides the APK download link. The `production` profile targets app-store distribution (AAB by default), which is a separate release step.

## AI integration and secrets

The visible chat replies are local demo text. Before connecting a real model, choose a provider and route secret API credentials through a backend or protected secret store—never put provider keys in React Native source or a client-side `.env` value that ships in the APK.

## GitHub

The project is intended for a **private GitHub repository** as the primary source. The provisional app name, package ID, and UI copy can be updated before release.
