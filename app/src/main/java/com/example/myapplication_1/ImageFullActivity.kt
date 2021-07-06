package com.example.myapplication_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.bumptech.glide.Glide

class ImageFullActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_full)



        val imagePath=intent.getStringExtra("path")
        val imageName=intent.getStringExtra("name")

        var textView = findViewById<TextView>(R.id.textView)
        textView.text = imageName
        Glide.with(this)
            .load(imagePath)
            .into(findViewById(R.id.imageView))
    }
}