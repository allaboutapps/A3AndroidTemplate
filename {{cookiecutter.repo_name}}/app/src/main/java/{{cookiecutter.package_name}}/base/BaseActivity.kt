package {{ cookiecutter.package_name }}.base

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import {{ cookiecutter.package_name }}.di.Injectable
import {{ cookiecutter.package_name }}.di.viewmodel.ViewModelFactory
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * Base class to use for this application
 */
abstract class BaseActivity : AppCompatActivity(), Injectable, HasSupportFragmentInjector {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var supportFragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = supportFragmentInjector

    /**
     * Request a ViewModel from the factory
     * @see ViewModelFactory
     */
    inline fun <reified T : ViewModel> viewModel() = ViewModelProviders.of(this, viewModelFactory).get(T::class.java)
}