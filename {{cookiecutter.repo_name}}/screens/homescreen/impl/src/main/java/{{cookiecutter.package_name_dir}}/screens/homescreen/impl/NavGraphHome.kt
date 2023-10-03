package {{ cookiecutter.package_name }}.screens.homescreen.impl

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.app.common.navigation.pub.NavGraphDefinition

object NavGraphHome : NavGraphDefinition {
    override val route = "home"

    override val destinations: List<com.example.app.common.navigation.pub.NavDestinationDefinition> = listOf(
        Overview,
    )

    override val startDestinationRoute: String = Overview.route

    object Overview : com.example.app.common.navigation.pub.NavDestinationDefinition {
        override val route = "overview"

        override fun buildRoute(navGraphBuilder: NavGraphBuilder, navController: NavController) {
            navGraphBuilder.composable(route) { _ ->
                HomeRoute(navController)
            }
        }
    }
}
