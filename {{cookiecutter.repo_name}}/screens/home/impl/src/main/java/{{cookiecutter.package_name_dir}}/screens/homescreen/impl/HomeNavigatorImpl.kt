package {{ cookiecutter.package_name }}.screens.homescreen.impl

import android.os.Bundle
import androidx.navigation.NavController
import {{ cookiecutter.package_name }}.common.navigation.navigateWithArgs
import {{ cookiecutter.package_name }}.screens.homescreen.pub.HomeNavigator
import javax.inject.Inject

class HomeNavigatorImpl @Inject constructor() : HomeNavigator {
    override fun navigateToHome(navController: NavController) {
        navController.navigateWithArgs(NavGraphHome.route, Bundle())
    }
}
