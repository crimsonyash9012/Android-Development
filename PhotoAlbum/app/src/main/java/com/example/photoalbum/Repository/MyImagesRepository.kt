package com.example.photoalbum.Repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.photoalbum.Model.MyImages
import com.example.photoalbum.Room.MyImagesDao
import com.example.photoalbum.Room.MyImagesDatabase
import kotlinx.coroutines.flow.Flow

class MyImagesRepository (application: Application){
    var myImagesDao : MyImagesDao
    var imagesList: LiveData<List<MyImages>>

    init{
        val database = MyImagesDatabase.getDatabaseInstance(application)
        myImagesDao = database.myImagesDao()
        imagesList = myImagesDao.getAllImages()
    }

    suspend fun insert(myImages: MyImages){
        myImagesDao.insert(myImages)
    }

    suspend fun update(myImages: MyImages){
        myImagesDao.update(myImages)
    }

    suspend fun delete(myImages: MyImages){
        myImagesDao.delete(myImages)
    }

    fun getAllImages(): LiveData<List<MyImages>> {
        return imagesList
    }

    suspend fun getItemById(id:Int) : MyImages{
        return myImagesDao.getItemById(id)
    }
}