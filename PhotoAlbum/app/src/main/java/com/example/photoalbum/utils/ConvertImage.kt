package com.example.photoalbum.utils

import android.util.Base64
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import kotlin.io.encoding.ExperimentalEncodingApi

// conversion of image from bitmap to string
class ConvertImage {
    companion object{

        fun convertToString(bitmap: Bitmap) : String?{
            // bitmap -> byte array -> string (by base 64)
            val stream = ByteArrayOutputStream()
            val resultCompress = bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
            if(resultCompress){
                val byteArray = stream.toByteArray()

                if(byteArray.size > 2000000){
                    return resizeImage(bitmap,0.1)
                }
                else if(byteArray.size in 1000000..2000000){
                    return resizeImage(bitmap,0.5)
                }

                return Base64.encodeToString(byteArray, Base64.DEFAULT)

            }
            return null
        }

        // if user uploads higher image size => can cause its failure
        fun resizeImage(bitmap : Bitmap , coefficient : Double) : String?{
            val resizedBitmap = Bitmap.createScaledBitmap(
                bitmap,
                (bitmap.width*coefficient).toInt(),
                (bitmap.height*coefficient).toInt(),
                true // true -> to avoid loss of quality
            )

            val stream = ByteArrayOutputStream()
            val resultCompress = bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
            if(resultCompress){
                val byteArray = stream.toByteArray()
                val imageAsString = Base64.encodeToString(byteArray, Base64.DEFAULT)
                return imageAsString
            }
            return null
        }

        fun convertToBitmap(imageAsString: String) : Bitmap{
            val byteArrayAsDecodedString = Base64.decode(imageAsString, Base64.DEFAULT)
            val bitmap = BitmapFactory.decodeByteArray(byteArrayAsDecodedString,0,byteArrayAsDecodedString.size)
            return bitmap
        }
    }
}