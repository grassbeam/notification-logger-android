package net.leopisang.notificationlog.data.access

import androidx.lifecycle.LiveData
import androidx.room.*
import net.leopisang.notificationlog.data.dbo.NotificationInfoIcon
import net.leopisang.notificationlog.data.entity.NotificationInfo

/**
 *
 * @author LeoPisanGG
 *         Created on 02/04/2021 20:25.
 */

@Dao
interface NotificationInfoDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg users: NotificationInfo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: NotificationInfo)

    @Delete
    fun delete(user: NotificationInfo)

    @Query("DELETE FROM NotificationInfo WHERE Id = :id")
    fun deleteByID(id : Int)

    @Query("DELETE FROM NotificationInfo")
    fun deleteAll()

    @Query("SELECT * FROM NotificationInfo")
    fun getAll(): List<NotificationInfo>

    @Query("SELECT notif.*, icon.id AS icon_Id, icon.icon AS icon_Icon FROM NotificationInfo notif " +
            "JOIN NotificationIcon icon ON icon.Id = notif.PackageName ORDER BY notif.PostingTime DESC")
    fun getAllWithIcon() : LiveData<List<NotificationInfoIcon>>

}