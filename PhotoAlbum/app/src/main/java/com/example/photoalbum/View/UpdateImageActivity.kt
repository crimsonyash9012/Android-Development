package com.example.photoalbum.View

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings.Global
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.photoalbum.Model.MyImages
import com.example.photoalbum.R
import com.example.photoalbum.ViewModel.MyImagesViewModel
import com.example.photoalbum.databinding.ActivityUpdateImageBinding
import com.example.photoalbum.utils.ConvertImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UpdateImageActivity : AppCompatActivity() {

    lateinit var updateImageBinding: ActivityUpdateImageBinding
    var id = -1

    lateinit var activityResultLauncherForSelectImage : ActivityResultLauncher<Intent>
    lateinit var viewModel : MyImagesViewModel
    var imageAsString = "" // bcz user may only want to change the title or desc not necessarily the image
    lateinit var selectedImage:Bitmap
    var control = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        updateImageBinding = ActivityUpdateImageBinding.inflate(layoutInflater)
        setContentView(updateImageBinding.root)

        viewModel = ViewModelProvider(this)[MyImagesViewModel::class.java]
        getAndSetData()

        // register
        registerActivityForSelectImage()

        updateImageBinding.imageViewUpdate.setOnClickListener {
            // access the images
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            activityResultLauncherForSelectImage.launch(intent)

        }
        updateImageBinding.buttonUpdate.setOnClickListener {
            updateImageBinding.buttonUpdate.text = "Updating... Please Wait"
            updateImageBinding.buttonUpdate.isEnabled = false

            GlobalScope.launch(Dispatchers.IO) { // since we want the UI not to be used up and blocked while the update's in progress


                val updateTitle = updateImageBinding.editTextUpdateTitle.text.toString()
                val updateDesc = updateImageBinding.editTextUpdateDesc.text.toString()

                if(control){
                    val newImageAsString = ConvertImage.convertToString(selectedImage)
                    if(newImageAsString!=null){
                        imageAsString = newImageAsString
                    }
                    else{

                        Toast.makeText(
                            applicationContext,
                            "There was an error while reading the image",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                val myUpdatedImage = MyImages(updateTitle,updateDesc, imageAsString)
                myUpdatedImage.imageId = id
                viewModel.update(myUpdatedImage)
                finish()

            }
        }
        updateImageBinding.toolbarUpdateImage.setNavigationOnClickListener {
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
                    updateImageBinding.imageViewUpdate.setImageBitmap(selectedImage)
                    control = true
                }
            }
        }
    }

    fun getAndSetData(){
        id = intent.getIntExtra("id", -1)
        if(id!=-1){
//            viewModel.getItemById(id) -> will give error bcz function is suspend => can only be called in the coroutine scope
            CoroutineScope(Dispatchers.IO).launch {
                val myImage = viewModel.getItemById(id)
                // display of components in the main thread
                // bcz these components are related to UI hence should be done in main thread
                withContext(Dispatchers.Main){
                    updateImageBinding.editTextUpdateTitle.setText(myImage.imageTitle)
                    updateImageBinding.editTextUpdateDesc.setText(myImage.imageDesc)
                    imageAsString = myImage.imageAsString
                    val imageAsBitmap : Bitmap = ConvertImage.convertToBitmap(imageAsString)
                    updateImageBinding.imageViewUpdate.setImageBitmap(imageAsBitmap)
                }
            }
        }
    }
}