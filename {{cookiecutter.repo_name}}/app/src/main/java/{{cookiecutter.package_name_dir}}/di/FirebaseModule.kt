package {{ cookiecutter.package_name }}.di

import {{ cookiecutter.package_name }}.features.fcm.FcmService
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FirebaseModule {

    @ContributesAndroidInjector
    fun provideFcmService(): FcmService
}
