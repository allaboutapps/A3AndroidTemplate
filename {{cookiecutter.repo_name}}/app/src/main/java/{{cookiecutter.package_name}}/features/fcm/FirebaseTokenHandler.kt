package {{ cookiecutter.package_name }}.features.fcm


import android.annotation.SuppressLint
import android.os.AsyncTask
import {{ cookiecutter.package_name }}.features.fcm.FirebaseTokenService
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.iid.FirebaseInstanceId
import timber.log.Timber
import javax.inject.Inject


class FirebaseTokenHandler @Inject constructor(private val firebaseTokenService: FirebaseTokenService) {

  fun sendFirebaseTokenToServer() {
    FirebaseInstanceId.getInstance().instanceId
        .addOnCompleteListener(AsyncTask.THREAD_POOL_EXECUTOR, OnCompleteListener { task ->
          if (task.isSuccessful) {
            task.result?.token?.let {
              Timber.d("Token: $it")
              registerTokenWithServer(it, { onError() }, { onSuccess() }, { })
            }
          } else {
            Timber.e(task.exception, "Cannot retrieve token from Firebase instance")
          }
        })
  }

  @SuppressLint("CheckResult")
  fun registerTokenWithServer(token: String, onError: () -> Unit, onSuccess: () -> Unit, onComplete: () -> Unit) {
    firebaseTokenService.registerFirebaseToken(token)
        .doFinally(onComplete)
        .subscribe({ onSuccess.invoke() }, { onError.invoke() })
  }

  private fun onError() {
    Timber.d("Error transmitting token, probably didn't change ...")
    //todo try again later?
  }

  private fun onSuccess() {
    Timber.d("Token transmitted")
  }

}