package net.leopisang.notificationlog.service

import android.app.Notification
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.IBinder
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.leopisang.notificationlog.NotificationLogApplication
import net.leopisang.notificationlog.data.entity.NotificationIcon
import net.leopisang.notificationlog.data.entity.NotificationInfo
import net.leopisang.notificationlog.repository.NotificationRepository
import java.io.ByteArrayOutputStream
import java.util.*


/**
 *
 * @author LeoPisanGG
 *         Created on 03/04/2021 00:08.
 */
class NotificationCaptorService : NotificationListenerService() {

    lateinit var Context : Context
    lateinit var repository: NotificationRepository

    override fun onCreate() {
        super.onCreate()
        Context = applicationContext
        repository = (application as NotificationLogApplication).repository

    }

    override fun onBind(intent: Intent?): IBinder? {
        return super.onBind(intent)
    }

    override fun onNotificationPosted(sbn: StatusBarNotification) {
        GlobalScope.launch {
            val notif = sbn.notification

            val pack = sbn.packageName
            var ticker = ""
            if (notif.tickerText != null) {
                ticker = notif.tickerText.toString()
            }
            val extras = notif.extras
            val title = extras.getString("android.title")
            val text = extras.getCharSequence("android.text").toString()
            val smallIcon = extras.getInt(Notification.EXTRA_SMALL_ICON)
            val bigIcon = extras[Notification.EXTRA_LARGE_ICON] as Bitmap?


            val stream = ByteArrayOutputStream()
            var byteArray: ByteArray? = null
            if (bigIcon != null) {
                bigIcon.compress(Bitmap.CompressFormat.PNG, 100, stream)
                byteArray = stream.toByteArray()
            }

            val notifInfo = NotificationInfo(0, pack, title, text, Date().time)
            val notifIcon = NotificationIcon(pack, byteArray)

            repository.insertWithIcon(notifInfo, notifIcon)
        }

    }
}