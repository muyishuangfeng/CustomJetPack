package com.gnetop.sdk.customdatabinding.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.gnetop.sdk.customdatabinding.R
import com.gnetop.sdk.customdatabinding.databinding.FragmentMineBinding
import kotlinx.android.synthetic.main.fragment_mine.*

/**
 * @description
 */
class MineFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentMineBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_login.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.login_Fragment)
        }
    }
}