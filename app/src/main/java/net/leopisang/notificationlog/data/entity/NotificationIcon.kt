package net.leopisang.notificationlog.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *
 * @author LeoPisanGG
 *         Created on 03/04/2021 00:17.
 */

@Entity
data class NotificationIcon (
    @PrimaryKey()
    @ColumnInfo(name = "Id")
    val packageID: String,

    @ColumnInfo(name = "Icon", typeAffinity = ColumnInfo.BLOB)
    val byteIcon: ByteArray?,

)