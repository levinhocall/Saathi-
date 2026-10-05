# Saathi AI (working title)

An original Android assistant prototype inspired by the supplied dark/red visual reference. It is not a copy of the reference app's logo or artwork.

## Current status

This is the first UI prototype: Home, Chat, Tools, and Settings screens; a session-only canned-response chat; quick prompts; and clear demo/planned labels. **No live AI provider, microphone capture, location, contacts, notification access, or other device control is connected.** No API key or sensitive Android permission is included.

See [the UI and core-features plan](docs/UI_CORE_PLAN.md) for the roadmap and platform limitations.

## Run locally

```bash
npm ci
npx expo start
```

Open the QR code in Expo Go, or run on an Android development environment with `npx expo run:android` after the native toolchain is configured.

## Checks

```bash
npm run lint
npm run typecheck
npx expo-doctor
```

## Testing APK through GitHub Actions

The workflow at [`.github/workflows/android-debug.yml`](.github/workflows/android-debug.yml) runs on pushes to `main` and can also be started manually with **Actions → Android debug APK → Run workflow**. It checks the project, generates the native Android project, compiles a debug APK, and uploads `saathi-debug-apk` as a 14-day workflow artifact. Download it from the completed run's **Artifacts** section.

The APK is debug-signed for sideload testing only; it is not a Play Store release or production-signed build. The CI workflow must first be pushed to the repository and Actions must be enabled.

## EAS alternative

An EAS `preview` profile is also configured in `eas.json`. A cloud build requires an Expo account and an authenticated EAS CLI session; do not send account credentials or tokens in chat.

## AI integration and secrets

The visible chat replies are local demo text. Before connecting a real model, choose a provider and route secret API credentials through a backend or protected secret store—never put provider keys in React Native source or a client-side `.env` value that ships in the APK.

## GitHub

The user selected `https://github.com/levinhocall/Saathi-` and explicitly confirmed that the initial source push may be public. Repository write authentication is required to publish the commit and start the CI workflow.
