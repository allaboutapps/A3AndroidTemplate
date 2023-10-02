package {{ cookiecutter.package_name }}.application

import android.app.Application
import {{ cookiecutter.package_name }}.BuildConfig
import {{ cookiecutter.package_name }}.features.forceupdate.ForceUpdateCheckerCallback
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {

    @Inject
    lateinit var forceUpdateCheckerCallback: ForceUpdateCheckerCallback
    override fun onCreate() {
        super.onCreate()

        initLogging()

        registerActivityLifecycleCallbacks(forceUpdateCheckerCallback)
    }

    private fun initLogging() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
