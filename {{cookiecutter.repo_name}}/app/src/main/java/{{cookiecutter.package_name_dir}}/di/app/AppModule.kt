package {{ cookiecutter.package_name }}.di.app

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import at.allaboutapps.moshi.converter.envelope.EnvelopeAdapterFactory
import {{ cookiecutter.package_name }}.BuildConfig
import {{ cookiecutter.package_name }}.config.data.ConfigRepo
import {{ cookiecutter.package_name }}.networking.CommonHeaderInterceptor
import {{ cookiecutter.package_name }}.networking.services.ApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.migration.DisableInstallInCheck
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.Date
import javax.inject.Named
import javax.inject.Provider
import javax.inject.Singleton

{%- if cookiecutter.firebase_messaging == "yes" %}
import com.google.firebase.messaging.FirebaseMessaging
{%- endif %}

@InstallIn(SingletonComponent::class)
@Module(
    includes = [
        AppModule.Bindings::class,
    ],
)
class AppModule {

    @Module
    @DisableInstallInCheck
    interface Bindings {
        @Binds
        fun context(app: Application): Context
    }

    @Singleton
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(Date::class.java, Rfc3339DateJsonAdapter())
            .add(EnvelopeAdapterFactory())
            .build()
    }

    @Singleton
    @Provides
    fun provideOkHttp(agentInterceptor: CommonHeaderInterceptor): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .addInterceptor(agentInterceptor)

        if (BuildConfig.DEBUG) {
            builder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        }

        return builder.build()
    }

    @Provides
    @Named(ConfigRepo.NAME_URL_CONFIG)
    fun provideConfigUrl() = BuildConfig.URL_CONFIG

    @Singleton
    @Provides
    fun provideApiService(
        okHttpProvider: Provider<OkHttpClient>,
        moshi: Moshi,
    ): ApiService {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_API_URL)
            .callFactory { request: Request ->
                // using .callFactory() instead of .client() to defer the initialization of the OkHttp client to
                // when the first API call is made
                okHttpProvider.get().newCall(request)
            }
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Reusable
    @Provides
    fun preferences(app: Application): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(app)
}
