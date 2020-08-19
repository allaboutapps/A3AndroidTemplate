package {{ cookiecutter.package_name }}.di

import javax.inject.Scope

@Scope
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class PerActivity

@Scope
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class PerFragment

@Scope
@MustBeDocumented
@Retention(AnnotationRetention.RUNTIME)
annotation class PerService
