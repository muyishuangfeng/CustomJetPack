package com.gnetop.sdk.jetpack.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation

import com.gnetop.sdk.jetpack.R
import kotlinx.android.synthetic.main.fragment_third.*


class ThirdFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_third, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //传参
        val mData = arguments?.get("Test")
        btn_third.setOnClickListener {
            Navigation.findNavController(it).navigateUp()
        }
        txt_result.text = mData.toString()

    }


}
