package com.example.myapplication_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class ImageFullActivity : AppCompatActivity() {
    //image zoom parameter
    private var mScaleGestureDetector: ScaleGestureDetector?=null
    private var scaleFactor=1.0f
    private lateinit var mImageView:ImageView

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

        //image zoom
        mImageView = findViewById(R.id.imageView)
        mScaleGestureDetector = ScaleGestureDetector(this, ScaleListener())

    }

    override fun onTouchEvent(motionEvent: MotionEvent?): Boolean {
        mScaleGestureDetector!!.onTouchEvent(motionEvent)
        return true
    }

    inner class ScaleListener : ScaleGestureDetector.SimpleOnScaleGestureListener(){
        override fun onScale(scaleGestureDetector: ScaleGestureDetector): Boolean {
            scaleFactor*=scaleGestureDetector.scaleFactor

            scaleFactor = Math.max(0.5f, Math.min(scaleFactor, 2.0f))

            mImageView.scaleX = scaleFactor
            mImageView.scaleY = scaleFactor
            return true
        }
    }

}