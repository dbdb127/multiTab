package com.example.myapplication_1

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class CameraActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)

        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, 101)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("카메라","req=$requestCode, result=$resultCode, data=$data")
        val imageView=findViewById<ImageView>(R.id.imageView)

        if(resultCode == Activity.RESULT_OK){
            when (requestCode){
                101->{
                    val bitmap = data?.extras?.get("data") as Bitmap
                    Log.d("카메라", "bitmap=$bitmap")
                    imageView.setImageBitmap(bitmap)
                }
            }
        }
    }
}