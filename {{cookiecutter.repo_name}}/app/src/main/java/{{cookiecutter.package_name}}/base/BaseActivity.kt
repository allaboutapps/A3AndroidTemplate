package {{ cookiecutter.package_name }}.base

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import {{ cookiecutter.package_name }}.di.Injectable
import {{ cookiecutter.package_name }}.di.viewmodel.ViewModelFactory
import javax.inject.Inject

/**
 * Base class to use for this application
 */
abstract class BaseActivity : AppCompatActivity(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    /**
     * Request a ViewModel from the factory
     * @see ViewModelFactory
     */
    inline fun <reified T : ViewModel> viewModel() = ViewModelProviders.of(this, viewModelFactory).get(T::class.java)
}