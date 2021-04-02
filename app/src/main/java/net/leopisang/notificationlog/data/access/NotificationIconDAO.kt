package net.leopisang.notificationlog.data.access

import androidx.room.*
import net.leopisang.notificationlog.data.entity.NotificationIcon

/**
 *
 * @author LeoPisanGG
 *         Created on 03/04/2021 00:30.
 */
@Dao
interface NotificationIconDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg users: NotificationIcon)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: NotificationIcon)

    @Delete
    suspend fun delete(user: NotificationIcon)

    @Query("SELECT * FROM NotificationIcon")
    suspend fun getAll(): List<NotificationIcon>
}