package {{ cookiecutter.package_name }}.screens.about.impl

import android.os.Bundle
import androidx.navigation.NavController
import {{ cookiecutter.package_name }}.common.navigation.navigateWithArgs
import {{ cookiecutter.package_name }}.screens.about.pub.AboutNavigator
import javax.inject.Inject

class AboutNavigatorImpl @Inject constructor() : AboutNavigator {
    override fun navigateToAbout(navController: NavController) {
        navController.navigateWithArgs(NavGraphAbout.route, Bundle())
    }
}
