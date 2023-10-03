package {{ cookiecutter.package_name }}.screens.about.impl

import {{ cookiecutter.package_name }}.features.navigation.pub.NavGraphDefinition
import {{ cookiecutter.package_name }}.screens.about.pub.AboutNavigator
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.migration.DisableInstallInCheck
import dagger.multibindings.IntoSet

@Module(includes = [AboutWiring.Bindings::class])
@InstallIn(SingletonComponent::class)
object AboutWiring {

    @Provides
    @IntoSet
    fun navGraph(): NavGraphDefinition = NavGraphAbout

    @Module
    @DisableInstallInCheck
    interface Bindings {
        @Binds
        fun navigator(navigator: AboutNavigatorImpl): AboutNavigator
    }
}
