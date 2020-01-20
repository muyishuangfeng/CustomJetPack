package com.gnetop.sdk.customlivedata.activity

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.gnetop.sdk.customlivedata.R
import com.gnetop.sdk.customlivedata.util.ConvertUtil
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
               // startActivity(Intent(this@MainActivity, SecondActivity::class.java))
                // LoginEventManager.getInstance().addOrder(MainActivity.this);
                Log.e(
                    "TAG",
                    ConvertUtil.convert(
                        "77:42:AA:91:B2:B0:E4:B8:69:70:1C:A2:2D:A6:A5:B9:F0:50:7F:CF",
                        true
                    )
                )
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
