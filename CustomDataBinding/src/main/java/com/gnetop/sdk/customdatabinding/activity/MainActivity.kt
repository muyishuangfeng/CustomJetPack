package com.gnetop.sdk.customdatabinding.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import com.gnetop.sdk.customdatabinding.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onSupportNavigateUp() =
        Navigation.findNavController(this, R.id.main_navigation).navigateUp()
}
