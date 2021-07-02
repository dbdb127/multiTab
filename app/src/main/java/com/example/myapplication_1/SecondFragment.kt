package com.example.myapplication_1

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Camera
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.lang.Exception

class SecondFragment : Fragment() {

    private var imageRecycler:RecyclerView?=null
    private var progressBar: ProgressBar?=null
    private var allPictures:ArrayList<Image>?=null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageRecycler= view.findViewById(R.id.image_recycler)
        progressBar=view.findViewById(R.id.recycler_progress)

        imageRecycler?.layoutManager=GridLayoutManager(activity,3)
        imageRecycler?.setHasFixedSize(true)

        //Storage Permissions
        if(ContextCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            )!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(
                requireContext() as Activity,
            arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),101)
        }

        allPictures= ArrayList()

        if(allPictures!!.isEmpty()){
            progressBar?.visibility=View.VISIBLE

            //get ALL Images From Storage
            allPictures=getAllImages()

            //Set Adapter to recycler
            imageRecycler?.adapter=ImageAdapter(requireContext(),allPictures!!)
            progressBar?.visibility= View.GONE
        }

        val fab: View = view.findViewById(R.id.fab)
        fab.setOnClickListener {
            requireActivity().let{
                val intent = Intent(activity, CameraActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun getAllImages(): ArrayList<Image>? {
        val images=ArrayList<Image>()

        //access to app file
        val allImageUri=MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(MediaStore.Images.ImageColumns.DATA,MediaStore.Images.Media.DISPLAY_NAME)
        var cursor=requireActivity().contentResolver.query(allImageUri,projection,null,null,null)

        //images에 불러온 image 추가
        try{
            cursor!!.moveToFirst()
            do{
                val image=Image()
                image.imagePath=cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA))
                image.imageName=cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME))
                images.add(image)
            }while(cursor.moveToNext())
            cursor.close()
        }catch(e:Exception){
            e.printStackTrace()
        }
        return images
    }
}