package {{ cookiecutter.package_name }}.screens.homescreen.impl

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun HomeRoute(
    navController: NavController,
    vm: HomeViewModel = hiltViewModel(),
) {
    HomeScreen(
        onBackToHomeClicked = {
            vm.aboutNavigator.navigateToAbout(navController)
        },
    )
}
