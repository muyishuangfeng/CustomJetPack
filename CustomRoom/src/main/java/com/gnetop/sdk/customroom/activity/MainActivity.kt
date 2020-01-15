package com.gnetop.sdk.customroom.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.gnetop.sdk.customroom.R
import com.gnetop.sdk.customroom.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mBinding: ActivityMainBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )
        mBinding.btnAdd.setOnClickListener {

        }
        mBinding.btnDelete.setOnClickListener {

        }
        mBinding.btnQuery.setOnClickListener {

        }
        mBinding.btnUpdate.setOnClickListener {

        }
    }
}
