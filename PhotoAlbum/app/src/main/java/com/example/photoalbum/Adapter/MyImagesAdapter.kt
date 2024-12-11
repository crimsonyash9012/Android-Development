package com.example.photoalbum.Adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.photoalbum.Model.MyImages
import com.example.photoalbum.View.UpdateImageActivity
import com.example.photoalbum.databinding.ImageItemBinding
import com.example.photoalbum.utils.ConvertImage

class MyImagesAdapter(val activity : Activity) : RecyclerView.Adapter<MyImagesAdapter.MyImagesViewHolder>(){

    var imagesList : List<MyImages> = ArrayList()

    fun setImage(images: List<MyImages>){
        this.imagesList = images
        notifyDataSetChanged()
    }

    class MyImagesViewHolder( val itemBinding: ImageItemBinding) : RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyImagesViewHolder {
        val view = ImageItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyImagesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return imagesList.size
    }

    override fun onBindViewHolder(holder: MyImagesViewHolder, position: Int) {
        val myImage = imagesList[position]
        with(holder){ // for reaching components in image card, we should use holder object separately =>
                      // using with for kotlin scope
            itemBinding.textViewTitle.text = myImage.imageTitle
            itemBinding.textViewDesc.text = myImage.imageDesc
            val imageAsBitmap = ConvertImage.convertToBitmap(myImage.imageAsString)
            itemBinding.imageView.setImageBitmap(imageAsBitmap)

            itemBinding.cardView.setOnClickListener {
                val intent = Intent(activity, UpdateImageActivity::class.java)
                intent.putExtra("id", myImage.imageId)
                activity.startActivity(intent)
            }
        }
    }

    fun returnItemAtGivenPosition(position: Int) : MyImages{
        return imagesList[position]
    }

}