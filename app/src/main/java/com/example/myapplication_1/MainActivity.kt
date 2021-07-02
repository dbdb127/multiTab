package com.example.myapplication_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    //private var vpAdapter: FragmentStateAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tabLayout=findViewById<TabLayout>(R.id.tab)
        val viewpager2=findViewById<ViewPager2>(R.id.viewPager2)

        val adapter = FragmentAdapter(supportFragmentManager, lifecycle)

        viewpager2.adapter=adapter

        TabLayoutMediator(tabLayout, viewpager2){tab, position->
            when(position){
                0->{
                    tab.text = "Contact"
                    tab.setIcon(R.drawable.ic_baseline_person_24)
                }
                1->{
                    tab.text = "Gallery"
                    tab.setIcon(R.drawable.ic_baseline_photo_24)
                }
                else->{
                    tab.text = "Map"
                    tab.setIcon(R.drawable.ic_baseline_map_24)
                }
            }
        }.attach()
    }

}