package {{ cookiecutter.package_name }}.features.fcm

import android.content.Context
import com.google.firebase.messaging.RemoteMessage
import timber.log.Timber


class FcmHandler {

  fun handleFcmPushNotification(context: Context, remoteMessage: RemoteMessage?) {

    val notification = remoteMessage?.notification
    if (notification == null) {
      Timber.e("Missing notification")
      return
    }

    val payload = remoteMessage.data
    if (payload.isNullOrEmpty()) {
      Timber.e("Missing payload")
      return
    }

    //TODO: handle notification
  }

}