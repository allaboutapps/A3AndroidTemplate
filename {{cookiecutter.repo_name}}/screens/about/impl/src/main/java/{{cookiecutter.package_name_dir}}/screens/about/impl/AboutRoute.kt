package {{ cookiecutter.package_name }}.screens.about.impl

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun AboutRoute(
    navController: NavController,
) {
    AboutScreen(
        onFinishClicked = {
            navController.popBackStack()
        },
    )
}
