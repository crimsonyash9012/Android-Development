package com.example.recyclerviewtest

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView

class CountriesAdapter(
    var countryNamesList: ArrayList<String>,
    var detailsList: ArrayList<String>,
    var imageList: ArrayList<Int>,
    var context: Context
) : RecyclerView.Adapter<CountriesAdapter.CountryViewHolder>()
{
    class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var countryName : TextView= itemView.findViewById(R.id.countryName)
        var details : TextView = itemView.findViewById(R.id.textView)
        var imageView : CircleImageView = itemView.findViewById(R.id.profile_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view : View = LayoutInflater.from(context).inflate(R.layout.card_design,parent, false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.countryName.text = countryNamesList.get(position)
        holder.details.text = detailsList.get(position)
        holder.imageView.setImageResource(imageList.get(position))
    }

    override fun getItemCount(): Int {
        return imageList.size
    }
}
