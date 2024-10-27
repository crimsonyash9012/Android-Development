package com.tiramisu.listfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class SecondFragment : Fragment() {

    lateinit var textView : TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view : View = inflater.inflate(R.layout.fragment_second, container, false)
        textView = view.findViewById(R.id.textView)

        val position = arguments?.getInt("pos", 0)
        when(position){
            0 -> textView.text = "Berlin"
            1 -> textView.text = "Athenes"
            2 -> textView.text = "Tokyo"
            3 -> textView.text = "Rome"
            4 -> textView.text = "Beijing"
        }

        return view
    }
}