package {{ cookiecutter.package_name }}.features.fcm

import android.annotation.SuppressLint
import com.google.firebase.messaging.FirebaseMessaging
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseTokenHandler @Inject constructor(
    private val firebaseMessaging: FirebaseMessaging,
    private val firebaseTokenService: FirebaseTokenService,
) {
    fun sendFirebaseTokenToServer() {
        firebaseMessaging.token
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val token = task.result
                    Timber.d("Token: $token")
                    registerTokenWithServer(token, { onError() }, { onSuccess() }, { })
                } else {
                    Timber.e(task.exception, "Cannot retrieve token from Firebase instance")
                }
            }
    }

    fun sendFirebaseTokenToServer(token: String) {
        Timber.d("Token: $token")
        registerTokenWithServer(token, { onError() }, { onSuccess() }, { })
    }

    @SuppressLint("CheckResult")
    fun registerTokenWithServer(token: String, onError: () -> Unit, onSuccess: () -> Unit, onComplete: () -> Unit) {
        firebaseTokenService.registerFirebaseToken(token)
            .doFinally(onComplete)
            .subscribe({ onSuccess.invoke() }, { onError.invoke() })
    }

    private fun onError() {
        Timber.d("Error transmitting token, probably didn't change ...")
        // todo try again later?
    }

    private fun onSuccess() {
        Timber.d("Token transmitted")
    }
}
