package {{ cookiecutter.package_name }}.di.viewmodel

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class ViewModelFactory @AssistedInject constructor(
    @Assisted owner: SavedStateRegistryOwner,
    @Assisted defaultArgs: Bundle?,
    private val viewModelComponentFactory: ViewModelComponent.Factory,
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    @AssistedFactory
    interface Factory {
        fun create(
            owner: SavedStateRegistryOwner,
            defaultArgs: Bundle?,
        ): ViewModelFactory
    }

    override fun <T : ViewModel> create(key: String, modelClass: Class<T>, handle: SavedStateHandle): T {
        val viewModelComponent = viewModelComponentFactory.create(handle)
        val viewModelProviders = viewModelComponent.viewModelProviders()

        val viewModelProvider = viewModelProviders[modelClass]
            ?: viewModelProviders.entries.firstOrNull { (viewModelKey) ->
                modelClass.isAssignableFrom(viewModelKey)
            }?.value
        requireNotNull(viewModelProvider) { "No ViewModel provider found for requested ViewModel type ($modelClass)" }

        val viewModel = viewModelProvider.get()
        check(modelClass.isInstance(viewModel)) {
            "Provided ViewModel instance (${viewModel.javaClass})" +
                " cannot be cast to requested ViewModel type ($modelClass)"
        }

        @Suppress("UNCHECKED_CAST")
        return viewModel as T
    }
}
