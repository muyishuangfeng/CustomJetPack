package com.gnetop.sdk.customlivedata.activity

import androidx.lifecycle.Observer
import com.gnetop.sdk.customlivedata.R
import com.gnetop.sdk.customlivedata.bus.LiveDataBus
import com.gnetop.sdk.customlivedata.bus.impl.EventListener
import com.gnetop.sdk.customlivedata.util.Constants
import com.gnetop.sdk.customlivedata.viewmodel.Event
import kotlinx.android.synthetic.main.activity_third.*

class ThirdActivity : BaseActivity() {




    override fun getLayoutID(): Int {
        return R.layout.activity_third
    }

    override fun initView() {
        btn_send.setOnClickListener {
            LiveDataBus
                .getInstance()
                .get<String>(Constants.MSG_SEND_NAME)
                .postValue(edt_send_content.text.toString())

        }
        btn_send_object.setOnClickListener {
            val mEvent = Event()
            mEvent.code = 123
            mEvent.msg = "=====456======"
            LiveDataBus
                .getInstance()
                .get<Event>(Constants.MSG_SEND_NAME)
                .postValue(mEvent)

        }
        btn_send_second.setOnClickListener {
            val mEvent = Event()
            mEvent.code = 456
            mEvent.msg = "=====789======"
            LiveDataBus
                .getInstance()
                .of(EventListener::class.java)
                .event()
                .postValue(mEvent)

        }

        val observer = Observer<String> {
            txt_result.text = it
        }


        btn_subject.setOnClickListener {
            LiveDataBus
                .getInstance()
                .get<String>(Constants.MSG_SEND_NAME)
                .observe(this, observer)
        }

        btn_unSubject.setOnClickListener {
            LiveDataBus
                .getInstance()
                .get<String>(Constants.MSG_SEND_NAME)
                .removeObserver(observer)
            txt_result.text = ""
        }

        LiveDataBus
            .getInstance()
            .get<Event>(Constants.MSG_SEND_NAME)
            .observe(this, Observer {
                txt_result.text = it.toString()
            })

    }
}
