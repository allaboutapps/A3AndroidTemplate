package {{ cookiecutter.package_name }}.screens.homescreen.impl

import {{ cookiecutter.package_name }}.features.navigation.pub.NavGraphDefinition
import {{ cookiecutter.package_name }}.screens.homescreen.pub.HomeNavigator
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.migration.DisableInstallInCheck
import dagger.multibindings.IntoSet

@Module(includes = [HomeWiring.Bindings::class])
@InstallIn(SingletonComponent::class)
object HomeWiring {

    @Provides
    @IntoSet
    fun navGraph(): NavGraphDefinition = NavGraphHome

    @Module
    @DisableInstallInCheck
    interface Bindings {
        @Binds
        fun navigator(navigator: HomeNavigatorImpl): HomeNavigator
    }
}
