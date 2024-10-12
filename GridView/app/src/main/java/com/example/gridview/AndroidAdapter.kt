package com.example.gridview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import org.w3c.dom.Text

class AndroidAdapter(
    var context: Context,
    var nameList: ArrayList<String>,
    var imgList: ArrayList<Int>
) : BaseAdapter() {


    override fun getCount(): Int {
        return imgList.size
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        // !! -> guarantees that the object can never be null
        val view : View = LayoutInflater.from(parent!!.context).inflate(R.layout.custom_layout, parent,false)

        var animalName : TextView = view.findViewById(R.id.textView)
        var animalImage : ImageView = view.findViewById(R.id.imgView)

        animalName.text = nameList[position]
        animalImage.setImageResource(imgList[position])

        return view

    }

}