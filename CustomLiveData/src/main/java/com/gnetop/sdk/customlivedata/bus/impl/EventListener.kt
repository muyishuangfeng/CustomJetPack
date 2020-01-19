package com.gnetop.sdk.customlivedata.bus.impl

import androidx.lifecycle.BusLiveData
import com.gnetop.sdk.customlivedata.viewmodel.Event

interface EventListener {

    fun event(): BusLiveData<Event>
}