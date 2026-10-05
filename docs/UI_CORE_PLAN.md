# Saathi AI — UI & Core Features (Draft)

**Status:** First implementation draft. The app name is provisional and can be changed before release.

## Product direction

A privacy-conscious, Hinglish-friendly Android companion inspired by the supplied dark/red assistant reference, but with an original identity and layout. The first milestone is a functional interface prototype; device actions and live AI are added only after their permissions and provider path are deliberately configured.

## Visual system

- Near-black charcoal surfaces with restrained crimson accents and subtle red glow; no copied MYRA wordmark, character art, or exact layout.
- Compact, one-handed portrait screens with rounded panels, clear tap targets, and high-contrast text.
- Four destinations: **Home**, **Chat**, **Tools**, and **Settings**.
- Warm, direct Hinglish microcopy; explicit status labels distinguish **Demo** from **Planned** functionality.

## Screens

### Home

- Greeting and short “what can I help with?” prompt.
- Prominent assistant microphone/orb control; until speech is integrated, tapping explains that microphone access is not yet enabled.
- Quick prompts for timer, web search, navigation, and summarization.
- A small “Preview build” note and compact activity/feature status.

### Chat

- User/assistant message bubbles, prompt suggestions, text composer, and send action.
- Current prototype uses canned replies only; no remote model is called and no conversation is persisted.
- Later: live model responses via a server-side proxy, streaming, and optional spoken answers.

### Tools

- Action cards for timers, web search, Maps, contacts/messages, files/OCR, and notifications.
- Prototype clearly labels device actions as not connected; it does not request permissions or silently perform actions.

### Settings

- Language and voice preferences (display-only in this preview).
- Permission status/consent center planned for microphone, notifications, location, contacts, and accessibility.
- Clear-history/privacy controls; first prototype stores chat state only in memory.

## MVP progression

1. **UI prototype (this milestone):** navigation, dark/red visual system, prompt composer, session-only demo chat, quick-action cards, settings, and explicit demo/planned labels.
2. **Usable assistant:** choose an AI provider; add a protected backend/proxy (never embed a provider secret in the APK); connect speech recognition and text-to-speech; persist chats only after defining retention controls.
3. **Permission-backed Android tools:** local timer/alarms and notification actions; Maps navigation intents; contact/dialer/message drafts, with confirmation for calls/messages; optional OCR/file lookup.
4. **Advanced integrations:** notification listener and foreground accessibility automation only after explicit opt-in and an explanation of what is read or acted on. PC connectivity is a separate later scope.

## Safety and platform limits

- Request permissions just in time, explain why, provide an off switch, and avoid collecting data not needed for the requested action.
- Require confirmation before sending messages or placing calls; prefer opening the user's dialer or message composer over background sending.
- Do not promise automatic call answering/rejection, universal device locking, silent WhatsApp/SMS delivery, OTP scraping, or unrestricted screen control. Android versions, default-handler rules, Play policies, and OEM behavior may prevent these features.
- Do not read notifications, location, contacts, or files in the prototype. No Android permissions are requested yet.

## Source and release path

- The selected target is the user-provided GitHub repository `levinhocall/Saathi-`; the user explicitly approved the initial push being public.
- `.github/workflows/android-debug.yml` checks the project and produces a debug APK artifact on a push to `main` or a manual workflow dispatch. It must be pushed and Actions enabled before a run is available.
- `eas.json` retains an internal APK preview profile as an alternative; EAS requires an authenticated Expo account.
- The current app is not connected to an AI provider. The debug APK is for sideload testing only, not a production or Play Store release.
