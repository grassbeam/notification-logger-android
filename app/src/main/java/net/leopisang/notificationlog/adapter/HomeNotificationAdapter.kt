package net.leopisang.notificationlog.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import net.leopisang.notificationlog.R
import net.leopisang.notificationlog.data.dbo.NotificationInfoIcon
import net.leopisang.notificationlog.data.entity.NotificationIcon
import net.leopisang.notificationlog.data.entity.NotificationInfo

/**
 *
 * @author LeoPisanGG
 *         Created on 03/04/2021 02:52.
 */

class HomeNotificationAdapter : ListAdapter<NotificationInfoIcon, HomeNotificationAdapter.HomeNotificationViewHolder>(NotifComparator()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeNotificationViewHolder {
        return HomeNotificationViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: HomeNotificationViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.info, current.icon)
    }

    class HomeNotificationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textPackageName: TextView = itemView.findViewById(R.id.text_package_name)
        private val textNotifTitle: TextView = itemView.findViewById(R.id.text_notification_title)
        private val textNotifMessage: TextView = itemView.findViewById(R.id.text_notification_message)

        fun bind(notifInfo: NotificationInfo?, notifIcon: NotificationIcon?) {
            textPackageName.text = notifInfo?.packageName
            textNotifTitle.text = notifInfo?.notifTitle
            textNotifMessage.text = notifInfo?.notifMessage
        }

        companion object {
            fun create(parent: ViewGroup): HomeNotificationViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_item_notification, parent, false)
                return HomeNotificationViewHolder(view)
            }
        }
    }

    class NotifComparator : DiffUtil.ItemCallback<NotificationInfoIcon>() {
        override fun areItemsTheSame(oldItem: NotificationInfoIcon, newItem: NotificationInfoIcon): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: NotificationInfoIcon, newItem: NotificationInfoIcon): Boolean {
            return oldItem.info.id == newItem.info.id
        }
    }

}