package {{ cookiecutter.package_name }}.di

import {{ cookiecutter.package_name }}.features.main.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface FragmentModule {

    @PerFragment
    @ContributesAndroidInjector
    fun provideMainFragment(): MainFragment
}
