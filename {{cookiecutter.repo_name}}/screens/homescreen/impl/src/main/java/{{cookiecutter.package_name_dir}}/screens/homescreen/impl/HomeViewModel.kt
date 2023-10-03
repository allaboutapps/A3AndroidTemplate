package {{ cookiecutter.package_name }}.screens.homescreen.impl

import androidx.lifecycle.ViewModel
import {{ cookiecutter.package_name }}.screens.about.pub.AboutNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val aboutNavigator: AboutNavigator,
) : ViewModel() {
    // TODO do something
}
