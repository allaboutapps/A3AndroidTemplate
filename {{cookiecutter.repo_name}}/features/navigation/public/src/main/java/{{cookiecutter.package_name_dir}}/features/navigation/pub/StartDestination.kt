package {{ cookiecutter.package_name }}.features.navigation.pub

import javax.inject.Qualifier

@Qualifier
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class StartDestinationRoute
