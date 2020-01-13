package com.gnetop.sdk.jetpack.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavOptions
import androidx.navigation.Navigation

import com.gnetop.sdk.jetpack.R
import kotlinx.android.synthetic.main.fragment_first.*


class FirstFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_first.setOnClickListener {
            //传参
            val mBundle = Bundle()
            mBundle.putString("Test", "From FirstFragment")
            //转场动画
            val mBuilder = NavOptions.Builder()
                .setEnterAnim(R.anim.h_fragment_enter)
                .setExitAnim(R.anim.h_fragment_exit)
                .setPopEnterAnim(R.anim.h_fragment_pop_enter)
                .setPopExitAnim(R.anim.h_fragment_pop_exit)
                .build()
            Navigation.findNavController(it)
                .navigate(R.id.action_page_second, mBundle, mBuilder)
        }
    }


}
