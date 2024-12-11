package com.example.photoalbum.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "my_images")
class MyImages(val imageTitle : String,
               val imageDesc : String,
                // BLOB - binary large object (images,sound, multimedia) or save images as String
                // String -> Base64 format
                val imageAsString : String
                 )
{
    @PrimaryKey(autoGenerate = true)
    var imageId = 0
}