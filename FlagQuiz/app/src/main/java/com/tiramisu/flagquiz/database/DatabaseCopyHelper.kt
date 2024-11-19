package com.tiramisu.flagquiz.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.nio.Buffer
import java.sql.SQLException
import kotlin.jvm.Throws

class DatabaseCopyHelper(context: Context) : SQLiteOpenHelper(context,DB_NAME,null,1) {

    var DB_PATH : String? = null
    var myContext : Context ? = null
    lateinit var myDataBase : SQLiteDatabase

    companion object{
        var DB_NAME : String = "countries.db"
    }

    init{
        DB_PATH = "/data/data/" + context.packageName + "/" + "databases/"
        myContext = context
    }

    // checks database if it already exists or not to avoid recopying of file each time you run the application
    private fun checkDataBase() : Boolean{
        var checkDB : SQLiteDatabase? = null
        try{
            val myPath : String = DB_PATH + DB_NAME
            checkDB = SQLiteDatabase.openDatabase(myPath,null,SQLiteDatabase.OPEN_READONLY)
        }catch (e : SQLiteException){
            // db doesn't exists
        }
        checkDB?.close()
        return checkDB!=null
    }

    /**
     * copies your database from your local assets folder to just created empty database in
     * systems folder from where it can be accessed and handled
     * this is done by transferring bytestream
     */

    @Throws(IOException::class)
    private fun copyDataBase(){
        // open local db as input stream
        val myInput = myContext?.assets?.open(DB_NAME)
        // path to the just created empty db
        val outFileName : String = DB_PATH + DB_NAME
        // open the empty db as the output stream
        val myOutput : OutputStream = FileOutputStream(outFileName)

        // transfer bytes from the inputfile to the outputfile
        val buffer = ByteArray(1024)
        var length : Int
        if(myInput !=null){
            while(myInput!=null){
                while(myInput.read(buffer).also { length=it }>0){
                    myOutput.write(buffer,0,length)
                }
            }
        }

        // close the stream
        myOutput.flush()
        myOutput.close()
        myInput?.close()
    }

    /**
     * creates an empty database on the system and rewrites it with your own DB
     */

    @Throws(IOException::class)
    fun createDataBase(){
        val dbExist = checkDataBase()
        if(dbExist){
            // do nothing
        }else{
            this.readableDatabase
            try{
                copyDataBase()
            }
            catch (e : IOException){
                throw Error("Error copying database")
            }
        }
    }

    @Throws(SQLException::class)
    fun openDataBase(){
        val myPath : String = DB_PATH + DB_NAME
        myDataBase = SQLiteDatabase.openDatabase(myPath,null,SQLiteDatabase.OPEN_READONLY)
    }

    override fun close() {
        myDataBase.close()
        super.close()
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE IF NOT EXISTS flags (flag_id INTEGER, country_name TEXT, flag_name TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS flags")
        onCreate(db)
    }


}