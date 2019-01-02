package {{ cookiecutter.package_name }}.application

import android.app.Application
import {{ cookiecutter.package_name }}.BuildConfig
import timber.log.Timber

class App : Application() {

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
