package com.example.photoalbum.View

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.example.photoalbum.Model.MyImages
import com.example.photoalbum.ViewModel.MyImagesViewModel
import com.example.photoalbum.databinding.ActivityAddImageBinding
import com.example.photoalbum.utils.ControlPermission
import com.example.photoalbum.utils.ConvertImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddImageActivity : AppCompatActivity() {

    lateinit var addImageBinding: ActivityAddImageBinding
    lateinit var activityResultLauncherForSelectImage : ActivityResultLauncher<Intent>
    lateinit var selectedImage : Bitmap
    lateinit var myImagesViewModel : MyImagesViewModel
    var control =  false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addImageBinding = ActivityAddImageBinding.inflate(layoutInflater)
        setContentView(addImageBinding.root)

        // initialisation
        myImagesViewModel = ViewModelProvider(this)[MyImagesViewModel::class.java]

        // register
        registerActivityForSelectImage()

        addImageBinding.imageView2.setOnClickListener {
            if(ControlPermission.checkPermission(this)){
                // access the images
                val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                activityResultLauncherForSelectImage.launch(intent)
            }
            else{
                if(Build.VERSION.SDK_INT >= 33){
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.READ_MEDIA_IMAGES),1
                    )
                }
                else{
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),1
                    )
                }
            }
        }
        addImageBinding.buttonAdd.setOnClickListener {

            if(control) {

                addImageBinding.buttonAdd.text = "Uploading... Please Wait"
                addImageBinding.buttonAdd.isEnabled = false

                GlobalScope.launch(Dispatchers.IO){
                    val title = addImageBinding.editTextAddTitle.text.toString()
                    val desc = addImageBinding.editTextAddDesc.text.toString()
                    val imageAsString = ConvertImage.convertToString(selectedImage)
                    if (imageAsString != null) {
                        myImagesViewModel.insert(MyImages(title, desc, imageAsString))
                        finish()
                    } else {
                        Toast.makeText(
                            applicationContext,
                            "There was an error while reading the image",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
            else{
                Toast.makeText(
                    applicationContext,
                    "There was an error while reading the image",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }
        addImageBinding.toolbarAddImage.setNavigationOnClickListener {
            finish()
        }
    }

    fun registerActivityForSelectImage(){
        activityResultLauncherForSelectImage = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
            // result of intent
            val resultCode = result.resultCode
            val data = result.data

            if(resultCode== RESULT_OK && data!=null){
                val imageUri = data.data

                imageUri?.let{ // if imageUri !=null // it -> it is the iterator for imageUri
                    if(Build.VERSION.SDK_INT>=28){
                        val imageSource = ImageDecoder.createSource(this.contentResolver, it)
                        selectedImage = ImageDecoder.decodeBitmap(imageSource)
                    }
                    else{
                        selectedImage = MediaStore.Images.Media.getBitmap(this.contentResolver, it)
                    }
                    addImageBinding.imageView2.setImageBitmap(selectedImage)
                    control = true
                }
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode==1 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            // access the images
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            activityResultLauncherForSelectImage.launch(intent)
        }
    }
}