package {{ cookiecutter.package_name }}.screens.homescreen.impl

import android.os.Bundle
import androidx.navigation.NavController
import com.example.app.common.navigation.navigateWithArgs
import com.example.app.screens.homescreen.pub.HomeNavigator
import javax.inject.Inject

class HomeNavigatorImpl @Inject constructor() : HomeNavigator {
    override fun navigateToHome(navController: NavController) {
        navController.navigateWithArgs(NavGraphHome.route, Bundle())
    }
}
