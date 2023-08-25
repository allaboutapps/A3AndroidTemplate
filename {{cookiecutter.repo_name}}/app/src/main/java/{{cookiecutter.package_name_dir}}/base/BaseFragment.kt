package {{ cookiecutter.package_name }}.base

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
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
abstract class BaseFragment(@LayoutRes contentLayoutId: Int = 0) :
    Fragment(contentLayoutId), Injectable, HasAndroidInjector {

    @Inject
    lateinit var viewModelFactoryFactory: ViewModelFactory.Factory

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override val defaultViewModelProviderFactory: ViewModelProvider.Factory
        get() = viewModelFactoryFactory.create(this, arguments)

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    /**
     * Request a ViewModel, scoped to this Fragment, from the injected factory.
     * @see ViewModelFactory
     */
    inline fun <reified T : ViewModel> fragmentViewModel() = ViewModelProvider(
        this,
        viewModelFactoryFactory.create(this, arguments),
    ).get(T::class.java)

    /**
     * Request a ViewModel, scoped to the parent Activity, from the injected factory.
     * @see ViewModelFactory
     */
    inline fun <reified T : ViewModel> activityViewModel() = ViewModelProvider(
        requireActivity(),
        viewModelFactoryFactory.create(this, arguments),
    ).get(T::class.java)

    /**
     * Request a ViewModel, scoped to the parent fragment, from the injected factory.
     * @see ViewModelFactory
     */
    inline fun <reified T : ViewModel> parentFragmentViewModel() = ViewModelProvider(
        requireParentFragment(),
        viewModelFactoryFactory.create(this, arguments),
    ).get(T::class.java)
}
