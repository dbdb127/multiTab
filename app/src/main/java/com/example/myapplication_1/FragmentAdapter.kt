package com.example.myapplication_1

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class FragmentAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment{
        val fragment = when(position)
        {
            0->FirstFragment().newInstant()
            1->SecondFragment().newInstant()
            else->ThirdFragment().newInstant()
        }
        return fragment
    }

    //사용할 tab의 개수
    override fun getCount(): Int = 3

    //tab 이름 지정하기
    override fun getPageTitle(position: Int):CharSequence?{
        val title = when(position){
            0->"One"
            1->"Two"
            else->"Three"
        }
        return title
    }
}
