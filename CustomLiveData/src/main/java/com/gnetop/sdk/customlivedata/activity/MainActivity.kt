package com.gnetop.sdk.customlivedata.activity

import android.content.Intent
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.gnetop.sdk.customlivedata.R
import com.gnetop.sdk.customlivedata.viewmodel.Event
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity(), View.OnClickListener {

    override fun getLayoutID(): Int {
        return R.layout.activity_main
    }


    override fun initView() {
        btn_send.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_send -> {
                startActivity(Intent(this@MainActivity, SecondActivity::class.java))
            }
        }

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

                    }
                }
            })
    }
}
