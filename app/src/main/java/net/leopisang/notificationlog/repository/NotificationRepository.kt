package net.leopisang.notificationlog.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import net.leopisang.notificationlog.data.access.NotificationIconDAO
import net.leopisang.notificationlog.data.access.NotificationInfoDAO
import net.leopisang.notificationlog.data.dbo.NotificationInfoIcon
import net.leopisang.notificationlog.data.entity.NotificationIcon
import net.leopisang.notificationlog.data.entity.NotificationInfo

/**
 *
 * @author LeoPisanGG
 *         Created on 03/04/2021 02:00.
 */
class NotificationRepository (private val notifInfoDao: NotificationInfoDAO, private val notifIconDao: NotificationIconDAO) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allNotifDataWithIconLive: LiveData<List<NotificationInfoIcon>> = notifInfoDao.getAllWithIcon()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertWithIcon(notifInfo: NotificationInfo, notifIcon: NotificationIcon) {
        notifIconDao.insert(notifIcon)
        notifInfoDao.insert(notifInfo)
    }


}