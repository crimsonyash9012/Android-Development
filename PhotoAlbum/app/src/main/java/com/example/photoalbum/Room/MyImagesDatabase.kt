package com.example.photoalbum.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.photoalbum.Model.MyImages

/** must have following
 * annotation
 * abstraction and extend room database
 * abstract method returns instance of room dao
 */

@Database(entities = [MyImages::class],version=1)
abstract class MyImagesDatabase :RoomDatabase(){
    abstract fun myImagesDao(): MyImagesDao

    // singleton design pattern - avoid having multiple instances of database
    companion object{

        @Volatile // to make instances to be created from this class visible to all other threads
        private var instance: MyImagesDatabase? = null

        fun getDatabaseInstance(context : Context) : MyImagesDatabase{
            synchronized(this){ // this -> if try to create another instance => it'll block it
                if(instance==null){
                    instance = Room.databaseBuilder(context.applicationContext,
                        MyImagesDatabase::class.java,
                        "my_album"
                    ).build()

                }
                return instance as MyImagesDatabase
            }
        }
    }
}