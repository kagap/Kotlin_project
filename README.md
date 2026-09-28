# 🪙 Monety — Coin Catching Game (Android / Kotlin)

A native Android arcade game written in Kotlin. Coins fall down the screen and the player has to tap matching pairs before time runs out. The Android Studio project lives in the `Monety/` folder (Gradle root project name `SplineRider`, application id `com.example.splinerider`).

From the app's own description string: *catch the falling 1 zł, 2 zł and 5 zł coins — a coin only counts when two or more coins of the same type are pressed together at once. You have one minute to score as many correct catches as possible.*

## 📌 What's Inside

- `Monety/` — the Android Studio / Gradle project
- `Monety/app/src/main/java/com/example/splinerider/`
  - `MainActivity.kt` — entry/login screen
  - `HomeActivity.kt` — main menu (new game, best scores, game description, author info, logout)
  - `Game.kt` — core gameplay view/logic
  - `GameDescriptionActivity.kt` — in-app rules screen
  - `LeaderboardActivity.kt` — high-score list
  - `Autor.kt` — "about the author" screen
  - `DatabaseHelper.kt`, `DatabaseContract.kt`, `User.kt`, `UserContract.kt` — SQLite-backed storage (`game_results3.db`) for user accounts and game results
- `Monety/app/src/main/res/` — layouts, drawable coin icons, fall/animation resources, and Polish-language strings
- `Monety/app/src/test` and `Monety/app/src/androidTest` — JUnit/Espresso test stubs

## 🚀 Getting Started

Requirements: Android Studio, JDK 8+, Android SDK with API 31 (compile/target SDK 31, min SDK 21).

```bash
git clone https://github.com/kagap/Kotlin_project.git
cd Kotlin_project/Monety
```

Open the `Monety` folder in Android Studio and let Gradle sync, then run the app on an emulator or device. From the command line:

```bash
./gradlew assembleDebug
./gradlew installDebug
```

## 🛠️ Tech Stack

- Kotlin on the Android SDK
- Gradle (Android Gradle Plugin 7.4.2, Kotlin plugin 1.6.21)
- SQLite (`SQLiteOpenHelper`) for local persistence of users and scores
- AndroidX (AppCompat, Material Components, ConstraintLayout)
