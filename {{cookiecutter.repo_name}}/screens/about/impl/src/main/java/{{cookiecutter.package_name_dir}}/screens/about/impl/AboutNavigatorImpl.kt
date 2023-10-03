package {{ cookiecutter.package_name }}.screens.about.impl

import android.os.Bundle
import androidx.navigation.NavController
import com.example.app.common.navigation.navigateWithArgs
import com.example.app.features.about.pub.AboutNavigator
import javax.inject.Inject

class AboutNavigatorImpl @Inject constructor() : AboutNavigator {
    override fun navigateToAbout(navController: NavController) {
        navController.navigateWithArgs(NavGraphAbout.route, Bundle())
    }
}
