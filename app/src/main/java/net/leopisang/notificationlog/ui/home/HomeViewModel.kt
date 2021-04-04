package net.leopisang.notificationlog.ui.home

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.leopisang.notificationlog.data.dbo.NotificationInfoIcon
import net.leopisang.notificationlog.repository.NotificationRepository

class HomeViewModel(private val repository: NotificationRepository) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Notification List:"
    }
    val textPageHeader: LiveData<String> = _text

    val isShowLoader = MutableLiveData<Boolean>().apply {
        value = false
    }

    val allNotificationDataWithIcon : LiveData<List<NotificationInfoIcon>> = repository.allNotifDataWithIconLive

    fun clearAllNotificationWithoutIcon() {
        isShowLoader.value = true
        viewModelScope.launch(Dispatchers.IO) {
            repository.clearNotifInfo()
            isShowLoader.postValue(false)
        }
    }

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