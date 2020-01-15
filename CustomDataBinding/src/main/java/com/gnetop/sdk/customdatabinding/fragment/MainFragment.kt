package com.gnetop.sdk.customdatabinding.fragment

import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.gnetop.sdk.customdatabinding.R
import com.gnetop.sdk.customdatabinding.adapter.*
import com.gnetop.sdk.customdatabinding.databinding.FragmentMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

/**
 * @description
 */
class MainFragment : Fragment() {

    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var viewPager2: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMainBinding.inflate(inflater, container, false)
        viewPager2 = binding.mainViewPager
        bottomNavigationView = binding.bottomBtnView
        viewPager2.adapter = MainViewPagerAdapter(this)
        bottomNavigationView.setOnNavigationItemSelectedListener(listener)
        initEvent()
        return binding.root
    }

    private val listener = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->

        when (menuItem.itemId) {
            R.id.home -> setCurrentItem(HOME_PAGE_INDEX)
            R.id.contact -> setCurrentItem(CONTACT_PAGE_INDEX)
            R.id.find -> setCurrentItem(FIND_PAGE_INDEX)
            R.id.mine -> setCurrentItem(MINE_PAGE_INDEX)
        }
        true
    }

    /**
     * 设置临时选中
     */
    private fun setCurrentItem(position: Int) {
        viewPager2.setCurrentItem(position, true)

    }

    /**
     * 设置viewPager2事件
     */
    private fun initEvent() {
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when(position){
                    0->{
                        bottomNavigationView.menu.getItem(position).setIcon(R.mipmap.ic_home)
                        bottomNavigationView.menu.getItem(position).setTitle(R.string.wechat)
                        bottomNavigationView.menu.getItem(position).isChecked=true
                    }
                    1->{
                        bottomNavigationView.menu.getItem(position).setIcon(R.mipmap.ic_contact)
                        bottomNavigationView.menu.getItem(position).setTitle(R.string.contact)
                        bottomNavigationView.menu.getItem(position).isChecked=true
                    }
                    2->{
                        bottomNavigationView.menu.getItem(position).setIcon(R.mipmap.ic_find)
                        bottomNavigationView.menu.getItem(position).setTitle(R.string.find)
                        bottomNavigationView.menu.getItem(position).isChecked=true
                    }
                    3->{
                        bottomNavigationView.menu.getItem(position).setIcon(R.mipmap.ic_mine)
                        bottomNavigationView.menu.getItem(position).setTitle(R.string.mine)
                        bottomNavigationView.menu.getItem(position).isChecked=true
                    }
                }


            }


        })

    }
}