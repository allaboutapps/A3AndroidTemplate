package {{ cookiecutter.package_name }}.networking

import android.content.Context
import android.os.Build
import {{ cookiecutter.package_name }}.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber
import javax.inject.Inject

/**
 * Adds `User-Agent` and `Accept-Language` headers to every request.
 */
class UserAgentInterceptor @Inject constructor(private val context: Context) : Interceptor {

    private val userAgent: String

    init {
        val packageName = BuildConfig.APPLICATION_ID
        val versionCode = BuildConfig.VERSION_CODE
        val platformVersion = Build.VERSION.RELEASE
        val sdkInt = Build.VERSION.SDK_INT
        val versionName = BuildConfig.VERSION_NAME

        userAgent = "$packageName/$versionCode ($PLATFORM; $platformVersion; $sdkInt) Version/$versionName"

        Timber.i("Initialized user agent as `$userAgent`")
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader(HEADER_USER_AGENT, userAgent)
            .addHeader(HEADER_ACCEPT_LANGUAGE, readUserLanguages(context))
            .build()

        return chain.proceed(request)
    }

    companion object {
        private const val HEADER_ACCEPT_LANGUAGE = "Accept-Language"
        private const val HEADER_USER_AGENT = "User-Agent"

        private const val PLATFORM = "Android"

        private fun readUserLanguages(context: Context): String {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                context.resources.configuration.locales.toLanguageTags()
            } else {
                @Suppress("DEPRECATION")
                context.resources.configuration.locale.toLanguageTag()
            }
        }
    }
}
