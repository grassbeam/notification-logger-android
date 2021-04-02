package net.leopisang.notificationlog.data.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *
 * @author LeoPisanGG
 *         Created on 02/04/2021 13:36.
 */

@Entity
data class NotificationInfo(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id")
    val id: Int,

    @ColumnInfo(name = "PackageName")
    val packageName: String?,

    @ColumnInfo(name = "Title")
    val notifTitle: String?,

    @ColumnInfo(name = "Message")
    val notifMessage: String?,

    @ColumnInfo(name = "PostingTime")
    val postingTime: Long,
)