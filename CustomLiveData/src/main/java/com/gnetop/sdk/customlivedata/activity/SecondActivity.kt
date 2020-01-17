package com.gnetop.sdk.customlivedata.activity

import android.view.View
import com.gnetop.sdk.customlivedata.R
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : BaseActivity(), View.OnClickListener {

    override fun getLayoutID(): Int {
        return R.layout.activity_second
    }

    override fun initView() {
        btn_send.setOnClickListener(this)
        btn_send2.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_send -> {
                sendMsg(1, "SecondActivity")
            }
            R.id.btn_send2 -> {
                sendMsg(2, "========SecondActivity===========2222")
            }
        }

    }

}
