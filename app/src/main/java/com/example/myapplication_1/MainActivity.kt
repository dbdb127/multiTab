package com.example.myapplication_1

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //permission
        val requiredPermissions = arrayOf(
            Manifest.permission.READ_CONTACTS,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.CALL_PHONE
        )

        var rejectedPermissionList = ArrayList<String>()

        //필요한 퍼미션들을 하나씩 끄집어내서 현재 권한을 받았는지 체크
        for(permission in requiredPermissions){
            if(ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                //만약 권한이 없다면 rejectedPermissionList에 추가
                rejectedPermissionList.add(permission)
            }
        }
        //거절된 퍼미션이 있다면...
        if(rejectedPermissionList.isNotEmpty()){
            //권한 요청!
            val array = arrayOfNulls<String>(rejectedPermissionList.size)
            ActivityCompat.requestPermissions(this, rejectedPermissionList.toArray(array), 100)
        }

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