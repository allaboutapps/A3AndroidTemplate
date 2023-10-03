package {{ cookiecutter.package_name }}.screens.about.pub

import androidx.navigation.NavController

interface AboutNavigator {
    fun navigateToAbout(navController: NavController)
}
