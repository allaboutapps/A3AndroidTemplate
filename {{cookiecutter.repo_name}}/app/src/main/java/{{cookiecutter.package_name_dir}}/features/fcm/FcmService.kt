package {{ cookiecutter.package_name }}.features.fcm

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class FcmService : FirebaseMessagingService() {
    @Inject lateinit var fcmHandler: FcmHandler

    @Inject lateinit var tokenHandler: FirebaseTokenHandler

    /**
     * Called when message is received when the app is in the foreground.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Timber.d("onMessageReceived")
        fcmHandler.handleFcmPushNotification(remoteMessage)
    }

    override fun onNewToken(token: String) {
        Timber.d("onNewToken: $token")
        tokenHandler.sendFirebaseTokenToServer(token)
    }
}
