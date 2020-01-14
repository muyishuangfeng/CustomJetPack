package com.gnetop.sdk.customdatabinding.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gnetop.sdk.customdatabinding.databinding.FragmentDetailBinding

/**
 * @description
 */
class DetailFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return FragmentDetailBinding.inflate(inflater,container,false).root
    }
}