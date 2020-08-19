package {{ cookiecutter.package_name }}.di

import {{ cookiecutter.package_name }}.features.start.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ActivityModule {

    @PerActivity
    @ContributesAndroidInjector
    fun provideMainActivity(): MainActivity
}
