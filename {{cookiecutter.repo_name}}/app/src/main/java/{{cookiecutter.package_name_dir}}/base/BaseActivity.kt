package {{ cookiecutter.package_name }}.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import {{ cookiecutter.package_name }}.di.Injectable
import {{ cookiecutter.package_name }}.di.viewmodel.ViewModelFactory
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

/**
 * Base class to use for this application
 */
abstract class BaseActivity : AppCompatActivity(), Injectable, HasAndroidInjector {

    @Inject
    lateinit var viewModelFactoryFactory: ViewModelFactory.Factory

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override val defaultViewModelProviderFactory: ViewModelProvider.Factory
        get() = viewModelFactoryFactory.create(this, intent.extras)

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
    }

    /**
     * Request a ViewModel, scoped to this Activity, from the injected factory.
     * @see ViewModelFactory
     */
    inline fun <reified T : ViewModel> viewModel() = ViewModelProvider(
        this,
        viewModelFactoryFactory.create(this, intent.extras),
    ).get(T::class.java)
}
