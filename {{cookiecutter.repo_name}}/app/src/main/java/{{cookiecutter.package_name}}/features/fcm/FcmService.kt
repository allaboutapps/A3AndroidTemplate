package {{ cookiecutter.package_name }}.features.fcm

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FcmService : FirebaseMessagingService() {

    private val fcmHandler = FcmHandler()

    /**
     * Called when message is received when the app is in the foreground.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        fcmHandler.handleFcmPushNotification(this, remoteMessage)
    }
}
