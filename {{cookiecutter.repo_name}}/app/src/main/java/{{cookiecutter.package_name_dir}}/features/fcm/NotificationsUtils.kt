package {{ cookiecutter.package_name }}.features.fcm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import androidx.annotation.DrawableRes
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlin.random.Random

// TODO: set ID of notification channel
const val CHANNEL_ID_DEFAULT_NOTIFICATION = "default_notification"

/**
 * Contains helper functions for notifications, especially firebase push notifications
 */
object NotificationsUtils {

    /**
     * Creates all used notification channels, only used in >= Build.VERSION_CODES.O
     *
     * @param context
     */
    fun createAllNotificationChannels(context: Context) {
        // TODO: set values of new notification channel
        createNotificationChannel(
            context,
            CHANNEL_ID_DEFAULT_NOTIFICATION,
            "title",
            "description",
            NotificationManagerCompat.IMPORTANCE_DEFAULT,
        )
    }

    /**
     * Creates the notification channel as specified, only used in >= Build.VERSION_CODES.O
     *
     * @param context
     * @param channelId Id of the notification channel
     * @param name name of the notification channel
     * @param description description of the notification channel
     * @param importance priority of the notification channel
     */
    fun createNotificationChannel(
        context: Context,
        channelId: String,
        name: String,
        description: String,
        importance: Int,
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, name, importance).apply {
                this.description = description
            }

            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    /**
     * Wraps the given intent in a pending intent and sends a notification using the given notification channel and the app's icon
     *
     * @param context
     * @param channelId id of matching notification channel
     * @param title title of notification
     * @param text text of notification
     * @param intent intent to open on tap
     * @param smallIcon icon of notification @DrawableRes
     */
    fun sendNotificationWithPendingIntent(
        context: Context,
        channelId: String,
        title: String,
        text: String,
        intent: Intent,
        @DrawableRes smallIcon: Int,
    ) {
        val notificationId = Random.nextInt() // notifications should not override each other

        val pendingIntent =
            PendingIntent.getActivity(context, notificationId, intent, PendingIntent.FLAG_ONE_SHOT)

        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder =
            NotificationCompat.Builder(context, channelId) // channelId ignored by older versions
                .setSmallIcon(smallIcon)
                .setContentTitle(title)
                .setContentText(text)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent)

        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(notificationId, notificationBuilder.build())
    }
}
