# Android Compose build sources

The Compose migration uses the versions documented and verified for this build on 2026-10-06:

- [Android Gradle Plugin 9.3 release notes](https://developer.android.com/build/releases/agp-9-3-0-release-notes): AGP 9.3 supports API 37, requires Gradle 9.5.0, and defaults to Build Tools 36.0.0 / JDK 17.
- [Compose setup and compiler plugin](https://developer.android.com/develop/ui/compose/setup-compose-dependencies-and-compiler): use the Compose Compiler Gradle plugin matching Kotlin 2.4.x and Compose BOM `2026.09.00`; Compose 1.12 requires compile SDK 37 and AGP 9.
- [Compose BOM documentation](https://developer.android.com/develop/ui/compose/bom): BOM coordinates are used so Compose UI libraries resolve to a tested compatible set.
- [Kotlin Gradle compatibility table](https://kotlinlang.org/docs/gradle-configure-project.html): Kotlin Gradle Plugin 2.4.20 is fully supported with Gradle 7.6.3–9.7.0 and AGP 8.5.2–9.3.1.
- [AGP built-in Kotlin migration guide](https://developer.android.com/build/migrate-to-built-in-kotlin): AGP 9 supplies Kotlin support without applying `org.jetbrains.kotlin.android`; a higher KGP version may be pinned via the root buildscript classpath.
- [Kotlin Compose compiler migration guide](https://kotlinlang.org/docs/compose-compiler-migration-guide.html): the Compose compiler Gradle plugin is versioned with Kotlin and should match the Kotlin compiler.
- [Android 12 backup/restore changes](https://developer.android.com/about/versions/12/backup-restore): the manifest uses separate cloud and device-transfer rules and disables backup for this local-only prototype.

## Project build choices

The module compiles against and targets Android API 37, and supports API 24+. Java/Kotlin bytecode target is 17; the build environment uses JDK 21. AGP's built-in Kotlin path is used with KGP 2.4.20 pinned by the documented root classpath mechanism; the matching Compose compiler plugin is applied to the app module. Gradle 9.7.0 is the highest fully supported wrapper version for KGP 2.4.20. The only ignored lint check is its newer-Gradle advisory for 9.8.0, which is outside that KGP compatibility range. No NDK, CMake, Metro, Node.js, or Expo tooling is required.

The debug APK uses the standard debug signing key generated in the CI runner. The release variant is intentionally unsigned and unminified until a protected production signing process and R8 rules are reviewed. No signing key or provider secret belongs in the repository.
