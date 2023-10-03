package {{ cookiecutter.package_name }}.screens.homescreen.pub

import androidx.navigation.NavController

interface HomeNavigator {
    fun navigateToHome(navController: NavController)
}
