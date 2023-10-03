package {{ cookiecutter.package_name }}.features.fcm

import android.content.Context
import com.google.firebase.messaging.RemoteMessage
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FcmHandler @Inject constructor(private val context: Context) {
    fun handleFcmPushNotification(remoteMessage: RemoteMessage?) {
        val notification = remoteMessage?.notification
        if (notification == null) {
            Timber.e("Missing notification")
            return
        }

        val payload = remoteMessage.data
        if (payload.isEmpty()) {
            Timber.e("Missing payload")
            return
        }

        // TODO: handle notification
    }
}
