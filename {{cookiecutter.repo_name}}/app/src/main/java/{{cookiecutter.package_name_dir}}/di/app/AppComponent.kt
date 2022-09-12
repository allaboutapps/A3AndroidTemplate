package {{ cookiecutter.package_name }}.di.app

import android.app.Application
import {{ cookiecutter.package_name }}.application.App
import {{ cookiecutter.package_name }}.di.ActivityModule
{% if cookiecutter.firebase_messaging == "yes" %}
import {{ cookiecutter.package_name }}.di.FirebaseModule
{% endif %}
import {{ cookiecutter.package_name }}.di.FragmentModule
import {{ cookiecutter.package_name }}.networking.services.ApiService
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityModule::class,
        FragmentModule::class,
        {% if cookiecutter.firebase_messaging == "yes" %}
        FirebaseModule::class,
        {% endif %}
    ]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Factory<App> {

        /**
         * Create an AppComponent with a bound Application.
         */
        override fun create(app: App): AppComponent {
            seedInstance(app)
            application(app)
            return build()
        }

        @BindsInstance
        abstract fun application(application: Application): Builder

        @BindsInstance
        abstract fun seedInstance(application: App): Builder

        abstract fun build(): AppComponent
    }
}
