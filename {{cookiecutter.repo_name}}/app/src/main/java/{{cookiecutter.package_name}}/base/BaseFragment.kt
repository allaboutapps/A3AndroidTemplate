package {{ cookiecutter.package_name }}.base

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import {{ cookiecutter.package_name }}.di.Injectable
import {{ cookiecutter.package_name }}.di.viewmodel.ViewModelFactory
import javax.inject.Inject

/**
 * Base class to use for this application
 */
abstract class BaseFragment(@LayoutRes contentLayoutId: Int = 0) : Fragment(contentLayoutId), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    /**
     * Request a ViewModel from the factory
     * @see ViewModelFactory
     */
    inline fun <reified T : ViewModel> viewModel() = ViewModelProvider(this).get(T::class.java)

    /**
     * Request a ViewModel scoped to the Activity from the factory
     * @see ViewModelFactory
     */
    inline fun <reified T : ViewModel> activityViewModel() = ViewModelProvider(requireActivity()).get(T::class.java)
}
