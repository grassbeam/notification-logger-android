package net.leopisang.notificationlog

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import net.leopisang.notificationlog.data.NotificationLogDatabase
import net.leopisang.notificationlog.repository.NotificationRepository

/**
 *
 * @author LeoPisanGG
 *         Created on 03/04/2021 02:03.
 */
class NotificationLogApplication: Application() {
    // No need to cancel this scope as it'll be torn down with the process
    val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { NotificationLogDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { NotificationRepository(database.notificationInfoDao(), database.notificationIconDao()) }
}