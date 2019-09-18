
# APT - Android Project Template


![all about apps](https://www.allaboutapps.at/wp-content/uploads/2017/06/aaa-Logo-black-646x165.png "all about apps")

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
    * a3 libraries
    * timber


* flavor for `dev`, `staging`, `live`
* retrofit setup
* firebase analytics and messaging setup
* SplashScreen helper -> https://blog.davidmedenjak.com/android/2017/09/02/splash-screens.html

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
4) firebase_analytics - Setup firebase analytics
5) firebase_messaging - Basic setup for FCM
6) strings_sheet_id - Sheet ID for strings (eg 1234565432345) (AAA internal tool for translations - just leave it empty if you don't know what this is) 

---

Du suchst einen spannenden Job im Mobile Bereich?
Looking for a job mobile developer?

https://www.allaboutapps.at/jobs/


