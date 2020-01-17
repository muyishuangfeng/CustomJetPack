package com.gnetop.sdk.customlivedata.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class EventModel constructor(event: Event) : ViewModel() {

    var mEvent: Event? = null
    var mEventMode = MutableLiveData<Event>()

    init {
        mEvent = event
    }


    /**
     * 获取EventModel
     */
    fun getEventMode(): MutableLiveData<Event> {
        return mEventMode
    }

    /**
     * 工厂
     */
    class CustomFactory constructor(event: Event) : ViewModelProvider.Factory {

        private var mEvent: Event = event

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return EventModel(mEvent) as T
        }

    }


}