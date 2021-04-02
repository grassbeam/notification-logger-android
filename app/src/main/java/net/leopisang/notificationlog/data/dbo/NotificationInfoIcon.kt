package net.leopisang.notificationlog.data.dbo

import androidx.room.Embedded
import net.leopisang.notificationlog.data.entity.NotificationIcon
import net.leopisang.notificationlog.data.entity.NotificationInfo

/**
 *
 * @author LeoPisanGG
 *         Created on 03/04/2021 00:22.
 */

data class NotificationInfoIcon (
    @Embedded
    val info : NotificationInfo,

    @Embedded(prefix = "icon_")
    val icon : NotificationIcon,

    )