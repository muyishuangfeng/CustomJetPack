package com.gnetop.sdk.customdatabinding.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.gnetop.sdk.customdatabinding.R

/**
 * @description
 */
class HomeFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = LayoutInflater.from(context).inflate(R.layout.fragment_home,container,false)
        val text = view.findViewById<TextView>(R.id.text)
        text.setOnClickListener(listener)
        return view
    }

    private val listener = View.OnClickListener {
        Navigation.findNavController(it).navigate(R.id.detail_fragment)
    }

}