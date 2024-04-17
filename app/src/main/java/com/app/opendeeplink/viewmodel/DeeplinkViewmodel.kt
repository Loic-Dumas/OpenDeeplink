package com.app.opendeeplink.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.app.opendeeplink.data.DeeplinkEntity
import com.app.opendeeplink.domain.room.DeeplinkHistoryDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class DeeplinkViewmodel(val dao: DeeplinkHistoryDao) : ViewModel() {

    private val _deeplinks = MediatorLiveData<List<String>>()
    val deeplinks: LiveData<List<String>> = _deeplinks

    init {
        _deeplinks.addSource(dao.getAllLiveData()) {
            _deeplinks.value = it.map { item -> item.deeplink }
        }
    }

    fun addDeeplink(deeplink: String) {
        MainScope().launch(Dispatchers.IO) {
            dao.add(DeeplinkEntity(deeplink))
        }
    }

    fun deleteDeeplink(deeplink: String) {
        MainScope().launch(Dispatchers.IO) {
            dao.delete(deeplink)
        }
    }
}