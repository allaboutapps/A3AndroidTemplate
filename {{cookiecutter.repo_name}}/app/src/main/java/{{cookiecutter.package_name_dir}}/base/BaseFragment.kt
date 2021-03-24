package {{ cookiecutter.package_name }}.base

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import {{ cookiecutter.package_name }}.di.Injectable
import {{ cookiecutter.package_name }}.di.viewmodel.ViewModelFactory
import javax.inject.Inject

/**
 * Base class to use for this application
 */
abstract class BaseFragment(@LayoutRes contentLayoutId: Int = 0) :
        Fragment(contentLayoutId), Injectable, HasAndroidInjector {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    /**
     * Request a ViewModel from the factory
     * @see ViewModelFactory
     */
    inline fun <reified T : ViewModel> fragmentViewModel() =
            ViewModelProvider(this, viewModelFactory).get(T::class.java)

    /**
     * Request a ViewModel scoped to the Activity from the factory
     * @see ViewModelFactory
     */
    inline fun <reified T : ViewModel> activityViewModel() =
            ViewModelProvider(requireActivity(), viewModelFactory).get(T::class.java)

    /**
     * Request a ViewModel scoped to the parentFragment from the factory
     * @see ViewModelFactory
     */
    inline fun <reified T : ViewModel> parentFragmentViewModel() =
            ViewModelProvider(requireParentFragment(), viewModelFactory).get(T::class.java)
}
