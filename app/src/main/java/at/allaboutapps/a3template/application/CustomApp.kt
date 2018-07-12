package at.allaboutapps.a3template.application

import android.app.Application
import at.allaboutapps.a3template.BuildConfig
import timber.log.Timber

class CustomApp : Application() {

    override fun onCreate() {
        super.onCreate()

        initLogging()

        SplashScreenHelper.register(this)
    }

    private fun initLogging() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
