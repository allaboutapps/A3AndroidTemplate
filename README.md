# Android Project Template

_Use this template project to improve your project setup experience._

<img alt="all about apps" src="https://allaboutapps.at/wp-content/uploads/2023/04/aaa_logo_white.png" width="200" />

## Contents

* recommended package structure
* latest libraries ([AndroidX], [Glide], [Retrofit], [Moshi], A3 libraries, [Timber], [LeakCanary])
* flavor dimension for development environments (`dev`, `staging`, `live`)
* Retrofit + Moshi setup
* Firebase Analytics and Messaging setup
* Splash screen setup: <https://developer.android.com/guide/topics/ui/splash-screen>
* Google Play services' [Open Source Notices](#open-source-notices) activity
* (optional) [Texterify](#texterify) setup
* [ktlint] integration via [ktlint-gradle-plugin]

[AndroidX]: <https://developer.android.com/jetpack/androidx> "AndroidX Overview &nbsp;|&nbsp; Android Developers"
[Glide]: <https://github.com/bumptech/glide> "bumptech/glide: An image loading and caching library for Android focused on smooth scrolling"
[Retrofit]: <https://github.com/square/retrofit> "square/retrofit: A type-safe HTTP client for Android and the JVM"
[Moshi]: <https://github.com/square/moshi> "square/moshi: A modern JSON library for Kotlin and Java."
[Timber]: <https://github.com/JakeWharton/timber> "JakeWharton/timber: A logger with a small, extensible API which provides utility on top of Android's normal Log class."
[LeakCanary]: <https://github.com/square/leakcanary> "square/leakcanary: A memory leak detection library for Android."
[ktlint]: <https://github.com/pinterest/ktlint> "pinterest/ktlint: An anti-bikeshedding Kotlin linter with built-in formatter"
[ktlint-gradle-plugin]: <https://github.com/mfederczuk/ktlint-gradle-plugin> "mfederczuk/ktlint-gradle-plugin: ktlint Gradle Plugin"

## Project Setup (using this template)

Install [Cookiecutter](http://cookiecutter.readthedocs.io/en/latest/installation.html) and run the following command to
fetch and use this template:

```sh
cookiecutter gh:allaboutapps/A3AndroidTemplate
```

Supply the following values when prompted:

1) `repo_name`               - Directory name where to create the new project (e.g.: `myproject-android`)
2) `app_name`                - App name (pretty/display name)
3) `package_name`            - App package name (e.g.: `com.example.app`)
4) `package_name_dir`        - Directory of the app package, relative to the `app/src/main/java` directory.
   (used to generate the correct directory structure)  
   Default value is the same as `package_name`, with dots/periods replaced with slashes.
5) `string_tool`             - The string-export tool for this project (`none` or `texterify`)
6) `texterify_project_id`    - ID of your Texterify project (default is empty)
7) `texterify_export_config` - Export Configuration ID of your Texterify project (default is empty)
8) `firebase_analytics`      - Whether or not to setup Firebase Analytics (default: `no`)
9) `firebase_messaging`      - Whether or not to add basic setup for FCM (default: `no`)

### Texterify

To fetch the latest strings from the configured [Texterify] project, run the gradle task `updateStrings`.
The config file `texterify.json` is located in the `app` directory.

[Texterify]: <https://github.com/chrztoph/texterify> "texterify/texterify: The translation and localization management system."

### Open Source Notices

To show the Google Play services' Open Source Notices activity just add the following code:

```kotlin
startActivity(Intent(this, OssLicensesMenuActivity::class.java))
```

More details can be found in the [Google Play services documentation].

[Google Play services documentation]: <https://developers.google.com/android/guides/opensource> "Include open source notices &nbsp;|&nbsp; Google Play services &nbsp;|&nbsp; Google Developers"

---

Du suchst einen spannenden Job im Mobile Bereich?
Looking for a job?

[We're hiring @all about apps](https://www.allaboutapps.at/jobs/)
