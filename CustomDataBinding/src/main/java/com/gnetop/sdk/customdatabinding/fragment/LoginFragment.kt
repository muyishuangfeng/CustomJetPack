package com.gnetop.sdk.customdatabinding.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.gnetop.sdk.customdatabinding.R
import com.gnetop.sdk.customdatabinding.common.Constants
import com.gnetop.sdk.customdatabinding.databinding.FragmentLoginBinding
import com.gnetop.sdk.customdatabinding.model.LoginModel
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val mBinding = FragmentLoginBinding.inflate(inflater, container, false)
        val loginModel = LoginModel()
        loginModel.name = "silence"
        loginModel.password = "123456"
        mBinding.model = loginModel
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_back.setOnClickListener {
            Navigation.findNavController(it).navigateUp()
        }

        btn_sure.setOnClickListener {
            if (edt_name.text.toString() == Constants.BASE_NAME &&
                edt_pass.text.toString() == Constants.BASE_PASS
            ) {
                Toast.makeText(activity, "登录成功", Toast.LENGTH_SHORT).show()
                Navigation.findNavController(it).popBackStack(R.id.main_fragment,false)
            }
        }


    }
}