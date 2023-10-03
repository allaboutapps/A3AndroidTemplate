package {{ cookiecutter.package_name }}.screens.homescreen.impl

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import {{ cookiecutter.package_name }}.features.navigation.pub.NavDestinationDefinition
import {{ cookiecutter.package_name }}.features.navigation.pub.NavGraphDefinition

object NavGraphHome : NavGraphDefinition {
    override val route = "home"

    override val destinations: List<NavDestinationDefinition> = listOf(
        Overview,
    )

    override val startDestinationRoute: String = Overview.route

    object Overview : NavDestinationDefinition {
        override val route = "overview"

        override fun buildRoute(navGraphBuilder: NavGraphBuilder, navController: NavController) {
            navGraphBuilder.composable(route) { _ ->
                HomeRoute(navController)
            }
        }
    }
}
