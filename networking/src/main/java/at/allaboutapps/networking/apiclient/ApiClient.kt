package at.allaboutapps.networking.apiclient

import android.util.Log
import at.allaboutapps.networking.BuildConfig
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit


object ApiClient {

    private lateinit var apiService: ApiService

    fun getClient(): ApiService {

        if (::apiService.isInitialized) {
            return apiService
        }

        val moshi = Moshi.Builder()
            .build()

        val loggingInterceptor = HttpLoggingInterceptor({ message -> log(message) })
        loggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS

        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()


        apiService = Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_API_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(ApiService::class.java)

        return apiService
    }

    private fun log(message: String) {
        Log.e("HTTP INTERCEPTOR", message)
    }
}
