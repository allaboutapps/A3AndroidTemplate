
# APT - Android Project Template


![all about apps](https://allaboutapps.at/wp-content/uploads/2019/11/aaa-logo-white@2x.png "all about apps")

---

Use this template project to improve the project setup experience.

Template contains:

* recommended package structure
* latest libraries
    * androidx
    * glide
    * retrofit
    * moshi
    * architecture components
    * navigation + safe args
    * a3 libraries
    * timber
    * LeakCanary


* flavor for `dev`, `staging`, `live`
* retrofit setup
* firebase analytics and messaging setup
* SplashScreen helper -> https://blog.davidmedenjak.com/android/2017/09/02/splash-screens.html
* Code to show [Open Source Licenses](#open-source-licenses) as standalone activity or dialog
* (optional) Texterify: texterify.json setup

---

## Setup
### Install cookiecutter
http://cookiecutter.readthedocs.io/en/latest/installation.html

### Setup a new project

cookiecutter gh:allaboutapps/A3AndroidTemplate

Fill in the following values:
1) repo_name        - name of cloned directory  (eg   mycards-android)
2) app_name         - app name
3) package_name     - "com.example.app",
4) string_tool      - Choose your selected string tool for this project (none, googlesheet or texterify)
5) texterify_project_id - project id of your texterify project - default: empty
6) texterify_export_config - export configuration id of your texterify project - default: empty
7) firebase_analytics - Setup firebase analytics
8) firebase_messaging - Basic setup for FCM
9) strings_sheet_id - Sheet ID for strings (eg 1234565432345) (AAA internal tool for translations - just leave it empty if you don't know what this is) default: empty 


### Texterify
To use [Texterify](https://github.com/chrztoph/texterify) just run the gradle task updateStrings. The file texterify.json is located in /app directory.

---
## Open Source Licenses

The oss_licenses module is setup to generate a html file containing licenses of all dependencies. Just run the gradle task checkLicense to generate everything (Bitrise Step preferred)

The file allowed_licenses.json contains license descriptions that are allowed to be used in our apps. If you find a valid license that is not supported feel free to start a pull request.

Shoing an open source dialog or activity is easy as that:

```kotlin
    val licenseScreenSettings = LicenseScreenSettings(title = "License Screen", showUpArrow = true)
        
    // open an activity    
    OssLicenseActivity.showLicenses(this, licenseScreenSettings)
    
    // or open a DialogFragment
    OssLicenseDialogFragment.showLicenseDialog(supportFragmentManager, licenseScreenSettings)
```

---

Du suchst einen spannenden Job im Mobile Bereich?
Looking for a job mobile developer?

https://www.allaboutapps.at/jobs/


