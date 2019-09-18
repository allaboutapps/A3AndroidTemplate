package {{ cookiecutter.package_name }}.features.fcm


import io.reactivex.Single
import {{ cookiecutter.package_name }}.networking.services.ApiService


class FirebaseTokenService(private val apiService: ApiService) {

  fun registerFirebaseToken(token: String): Single<Any> {
    //TODO: send FCM Token to backend
    return Single.never<Any>()
  }
}