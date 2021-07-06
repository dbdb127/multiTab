package com.example.myapplication_1

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

import java.io.File

class ImageAdapter(private var context:Context,private var imagesList:ArrayList<Image>):
    RecyclerView.Adapter<ImageAdapter.ImageViewHolder>(){

    class ImageViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        var image: ImageView?=null
        init{
            image=itemView.findViewById(R.id.row_image)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.row_custom_recycler_item,parent,false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val currentImage=imagesList[position]
        Glide.with(context)
            .load(currentImage.imagePath)
            .apply(RequestOptions().centerCrop())
            .into(holder?.image!!)

        //짧게 클릭하면 사진 크게 보여주기
        holder.image?.setOnClickListener {
            val intent = Intent(context, ImageFullActivity::class.java)
            intent.putExtra("path", currentImage.imagePath)
            intent.putExtra("name", currentImage.imageName)
            context.startActivity(intent)
        }

        //길게 클릭하면 사진 삭제하기
        holder.image?.setOnLongClickListener{
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Alert")
            builder.setMessage("Do you want to delete the file?")
            builder.setPositiveButton("Yes") { dialogInterface: DialogInterface, i: Int ->
                //사진 파일 삭제
                var file = File("${currentImage.imagePath}")
                com.example.myapplication_1.deletedFile.add("${currentImage.imagePath}")
                file.delete()

                SecondFragment().bringGallery()
            }
            builder.setNegativeButton("No") { dialogInterface: DialogInterface, i: Int ->
            }

            builder.show()
            true
        }

        //사진 정보 보기
    }


    override fun getItemCount(): Int {
        return imagesList.size
    }
}