package {{ cookiecutter.package_name }}.screens.homescreen.impl

import androidx.lifecycle.ViewModel
import com.example.app.features.about.pub.AboutNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val aboutNavigator: AboutNavigator,
) : ViewModel() {
    // TODO do something
}
