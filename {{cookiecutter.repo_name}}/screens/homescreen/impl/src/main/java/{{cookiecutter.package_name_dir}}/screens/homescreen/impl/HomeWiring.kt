package {{ cookiecutter.package_name }}.screens.homescreen.impl

import com.example.app.common.navigation.pub.NavGraphDefinition
import com.example.app.screens.homescreen.pub.HomeNavigator
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
