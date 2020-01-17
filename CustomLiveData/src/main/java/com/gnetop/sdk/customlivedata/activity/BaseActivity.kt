package com.gnetop.sdk.customlivedata.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.gnetop.sdk.customlivedata.viewmodel.Event
import com.gnetop.sdk.customlivedata.viewmodel.EventModel

abstract class BaseActivity : AppCompatActivity() {

    lateinit var mEventModel: EventModel
    var mEvent: Event? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (getLayoutID() != 0) {
            setContentView(getLayoutID())
        }
        init()
        initView()

    }


    abstract fun getLayoutID(): Int

    abstract fun initView()


    override fun onStart() {
        super.onStart()
        initData()
    }

    private fun init() {
        mEvent = Event(0, "")
        mEventModel = ViewModelProviders.of(this, EventModel.CustomFactory(mEvent!!))
            .get<EventModel>(EventModel::class.java)
    }

    /**
     * 初始化数据
     */
    private fun initData() {
        val nameEvent: MutableLiveData<Event> = mEventModel.getEventMode()
        nameEvent.observe(this,
            Observer<Event?> { t ->
                when (t?.code) {
                    1 -> {
                        Log.e("Tag", t.msg)
                    }
                    2 -> {
                        Log.e("Tag", t.msg)
                    }
                }
            })
    }

    /**
     * 发送消息
     */
    fun sendMsg(code: Int, msg: String) {
        mEvent = Event(code, msg)
        mEventModel.getEventMode().value = mEvent
    }
}