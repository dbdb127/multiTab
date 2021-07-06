package com.example.myapplication_1

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.icu.text.SimpleDateFormat
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.File
import java.io.IOException
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList
import kotlin.properties.Delegates

class SecondFragment : Fragment() {

    private var imageRecycler:RecyclerView?=null
    private var progressBar: ProgressBar?=null
    private var allPictures:ArrayList<Image>?=null

    private val REQUEST_TAKE_PHOTO = 1
    private lateinit var currentPhotoPath: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bringGallery()

        //open camera
        val fab: View = view.findViewById(R.id.fab)
        fab.setOnClickListener {
            takePictureIntent()
            bringGallery()
        }

        val update: View = view.findViewById(R.id.update)
        update.setOnClickListener {
            bringGallery()
        }
    }

    fun bringGallery(){
        //get all images
        imageRecycler= view?.findViewById(R.id.image_recycler)
        progressBar=view?.findViewById(R.id.recycler_progress)

        imageRecycler?.layoutManager=GridLayoutManager(activity,3)
        imageRecycler?.setHasFixedSize(true)

        allPictures= ArrayList()

        if(allPictures!!.isEmpty()){
            progressBar?.visibility=View.VISIBLE

            //get ALL Images From Storage
            allPictures=getAllImages()

            //Set Adapter to recycler
            imageRecycler?.adapter=ImageAdapter(requireContext(),allPictures!!)
            progressBar?.visibility= View.GONE
        }
    }

    private fun takePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(requireActivity().packageManager)?.also {
                // 사진 파일을 만듭니다.
                val photoFile: File? = try {
                    createImageFile()
                } catch (ex: IOException) {
                    // Error occurred while creating the File
                    Log.d("test", "error: $ex")
                    null
                }

                // photoUri를 보내는 코드
                photoFile?.also {
                    val photoURI: Uri = FileProvider.getUriForFile(
                        requireContext(),
                        "com.example.myapplication_1.provider",
                        it
                    )

                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO)

                }
            }
        }
    }

        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            super.onActivityResult(requestCode, resultCode, data)

            if (requestCode == REQUEST_TAKE_PHOTO && resultCode == AppCompatActivity.RESULT_OK) {
                galleryAddPic()
            }

            //bringGallery()
        }

        // 사진 파일을 만드는 메소드
        @Throws(IOException::class)
        private fun createImageFile(): File {
            // Create an image file name
            val timeStamp: String = java.text.SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())


        val storageDir: File? = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_", /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        ).apply {
            // Save a file: path for use with ACTION_VIEW intents
            currentPhotoPath = absolutePath
            Log.d("test", "currentPhotoPath : $currentPhotoPath")
        }

    }

    // 갤러리에 파일을 추가하는 함수.
    private fun galleryAddPic() {
        Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE).also { mediaScanIntent ->
            Log.d("test", "currentPhotoPath2 : $currentPhotoPath")
            val f = File(currentPhotoPath)
            mediaScanIntent.data = Uri.fromFile(f)
            requireActivity().sendBroadcast(mediaScanIntent)
        }
    }

    fun getAllImages(): ArrayList<Image>? {
        val images=ArrayList<Image>()

        //access to app file
        val allImageUri=MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(MediaStore.Images.ImageColumns.DATA,MediaStore.Images.Media.DISPLAY_NAME)

        if (activity != null) {
            var cursor =
                requireActivity().contentResolver.query(allImageUri, projection, null, null, null)

            //images에 불러온 image 추가
            try {
                cursor!!.moveToFirst()
                do {
                    val image = Image()
                    image.imagePath =
                        cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA))
                    image.imageName =
                        cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME))


                    //만약 deletedFile이 있다면 해당 파일이 삭제되었는지 확인
                    var valid = 1
                    if(deletedFile.isEmpty() != true) {
                        for (i in deletedFile!!) {
                            if (image.imagePath == i) {
                                valid=0
                                break
                            }
                        }
                    }

                    if(valid == 1) {
                        images.add(image)
                    }
                } while (cursor.moveToNext())
                cursor.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return images
    }
}