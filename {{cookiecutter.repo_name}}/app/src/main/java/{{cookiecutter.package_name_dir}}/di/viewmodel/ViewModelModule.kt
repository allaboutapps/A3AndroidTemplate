package {{ cookiecutter.package_name }}.di.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import {{ cookiecutter.package_name }}.features.start.MainViewModel
import dagger.Binds
import dagger.BindsInstance
import dagger.Module
import dagger.Subcomponent
import dagger.multibindings.IntoMap
import dagger.multibindings.Multibinds
import javax.inject.Provider

@Module(subcomponents = [ViewModelComponent::class])
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(viewModel: MainViewModel): ViewModel

    @Multibinds
    abstract fun viewModels(): Map<Class<out ViewModel>, @JvmSuppressWildcards ViewModel>
}

@Subcomponent
interface ViewModelComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance savedStateHandle: SavedStateHandle): ViewModelComponent
    }

    fun viewModelProviders(): Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
}
