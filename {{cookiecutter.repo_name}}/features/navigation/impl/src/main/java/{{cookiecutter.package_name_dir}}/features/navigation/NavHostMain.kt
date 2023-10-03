package {{ cookiecutter.package_name }}.features.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import {{ cookiecutter.package_name }}.features.navigation.pub.NavGraphDefinition
import {{ cookiecutter.package_name }}.features.navigation.pub.StartDestinationRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class NavViewModel @Inject constructor(
    @StartDestinationRoute val startDestinationRoute: String,
    val destinationBuilders: Set<@JvmSuppressWildcards NavGraphDefinition>,
) : ViewModel()

@Composable
fun NavHostMain(navController: NavHostController) {
    val viewModel = hiltViewModel<NavViewModel>()
    val destinationBuilders = viewModel.destinationBuilders

    // fixxme startdest
    NavHost(
        navController = navController,
        startDestination = viewModel.startDestinationRoute,
    ) {
        destinationBuilders.forEach { navGraphDefinition ->
            navGraphDefinition.build(navGraphBuilder = this, navController)
        }
    }
}
