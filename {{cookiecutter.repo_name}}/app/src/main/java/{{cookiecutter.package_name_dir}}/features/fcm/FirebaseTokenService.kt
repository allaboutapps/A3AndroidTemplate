package {{ cookiecutter.package_name }}.features.fcm

import {{ cookiecutter.package_name }}.networking.services.ApiService
import io.reactivex.rxjava3.core.Single
import com.google.firebase.messaging.FirebaseMessaging
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseTokenService @Inject constructor(private val apiService: ApiService) {

    fun registerFirebaseToken(token: String): Single<Any> {
        // TODO: send FCM Token to backend
        return Single.never<Any>()
    }
}

@Module
@InstallIn(SingletonComponent::class)
object FirebaseTokenServiceModule {
    @Provides
    fun firebaseMessaging(): FirebaseMessaging = FirebaseMessaging.getInstance()
}
