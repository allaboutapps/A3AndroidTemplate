package {{ cookiecutter.package_name }}.di.app

import {{ cookiecutter.package_name }}.features.navigation.pub.StartDestinationRoute
import {{ cookiecutter.package_name }}.screens.homescreen.impl.NavGraphHome
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object Navigation {
    @Provides
    @StartDestinationRoute
    fun startDestination(): String = NavGraphHome.route
}
