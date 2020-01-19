package com.gnetop.sdk.customlivedata.activity

import android.content.Intent
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import com.gnetop.sdk.customlivedata.R
import com.gnetop.sdk.customlivedata.bus.LiveDataBus
import com.gnetop.sdk.customlivedata.bus.Logger
import com.gnetop.sdk.customlivedata.bus.impl.EventListener
import com.gnetop.sdk.customlivedata.util.Constants
import com.gnetop.sdk.customlivedata.viewmodel.Event
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : BaseActivity(), View.OnClickListener {

    companion object {
        val mObserver = Observer<Event> {
            Log.e("TAG", it.toString())
        }
    }


    override fun getLayoutID(): Int {
        return R.layout.activity_second
    }

    override fun initView() {
        btn_send.setOnClickListener(this)
        btn_send2.setOnClickListener(this)
        btn_send3.setOnClickListener(this)
        LiveDataBus
            .get<Event>(Constants.MSG_SEND_NAME)
            .observeSticky(this, mObserver)


    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_send -> {
                sendMsg(1, "SecondActivity")
            }
            R.id.btn_send2 -> {
                sendMsg(2, "========SecondActivity===========2222")
            }
            R.id.btn_send3 -> {
                startActivity(Intent(this@SecondActivity, ThirdActivity::class.java))
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        LiveDataBus
            .get<Event>(Constants.MSG_SEND_NAME)
            .removeObserver(mObserver)
    }

}
