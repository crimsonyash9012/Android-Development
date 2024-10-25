package com.tiramisu.fragmentdatasharing

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class SecondFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.fragment_second, container, false)
        val name : TextView = view.findViewById(R.id.textViewName)
        val userName = arguments?.getString("username").toString()
        name.text = userName

        return view
    }


}