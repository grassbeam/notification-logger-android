package net.leopisang.notificationlog.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import net.leopisang.notificationlog.data.dbo.NotificationInfoIcon
import net.leopisang.notificationlog.repository.NotificationRepository

class HomeViewModel(private val repository: NotificationRepository) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Notification List:"
    }
    val textPageHeader: LiveData<String> = _text

    val allNotificationDataWithIcon : LiveData<List<NotificationInfoIcon>> = repository.allNotifDataWithIconLive

}

class HomeViewModelFactory(private val repository: NotificationRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}