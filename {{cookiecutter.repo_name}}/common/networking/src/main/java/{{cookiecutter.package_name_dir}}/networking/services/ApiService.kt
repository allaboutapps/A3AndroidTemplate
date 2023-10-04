package {{ cookiecutter.package_name }}.networking.services

import {{ cookiecutter.package_name }}.networking.model.AuthToken
import {{ cookiecutter.package_name }}.networking.model.ConfigDto
import at.allaboutapps.moshi.converter.envelope.EnvelopeJson
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Url

const val API_VERSION = "v1"

interface ApiService {

    @POST("/$API_VERSION/login")
    fun login(): Single<AuthToken>

    @EnvelopeJson("android")
    @GET
    fun fetchConfig(@Url url: String): Single<ConfigDto>
}
