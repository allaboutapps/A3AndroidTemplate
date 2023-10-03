package {{ cookiecutter.package_name }}.screens.about.impl

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import {{ cookiecutter.package_name }}.features.navigation.pub.NavDestinationDefinition
import {{ cookiecutter.package_name }}.features.navigation.pub.NavGraphDefinition

object NavGraphAbout : NavGraphDefinition {

    override val route = "about"
    override val startDestinationRoute: String = About.route

    override val destinations: List<NavDestinationDefinition> = listOf(
        About,
    )

    object About : NavDestinationDefinition {
        override val route = "aboutApp"

        override fun buildRoute(navGraphBuilder: NavGraphBuilder, navController: NavController) {
            navGraphBuilder.composable(route) { _ ->
                AboutRoute(navController)
            }
        }
    }
}
