package {{ cookiecutter.package_name }}.application

import {{ cookiecutter.package_name }}.BuildConfig
import {{ cookiecutter.package_name }}.di.AppInjector
import {{ cookiecutter.package_name }}.di.app.DaggerAppComponent
import {{ cookiecutter.package_name }}.features.forceupdate.ForceUpdateCheckerCallback
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import timber.log.Timber
import javax.inject.Inject

class App : DaggerApplication() {

    @Inject
    lateinit var forceUpdateCheckerCallback: ForceUpdateCheckerCallback

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> =
        DaggerAppComponent.builder().create(this)

    override fun onCreate() {
        super.onCreate()

        initLogging()

        AppInjector.init(this)

        registerActivityLifecycleCallbacks(forceUpdateCheckerCallback)
    }

    private fun initLogging() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
