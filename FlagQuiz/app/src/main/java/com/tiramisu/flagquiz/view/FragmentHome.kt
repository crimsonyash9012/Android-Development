package com.tiramisu.flagquiz.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.tiramisu.flagquiz.R
import com.tiramisu.flagquiz.database.DatabaseCopyHelper
import com.tiramisu.flagquiz.databinding.FragmentHomeBinding


class FragmentHome : Fragment() {

    lateinit var fragmentHomeBinding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        fragmentHomeBinding = FragmentHomeBinding.inflate(inflater,container,false)
        createAndOpenDatabase()

        fragmentHomeBinding.buttonStart.setOnClickListener{
            val direction = FragmentHomeDirections.actionFragmentHomeToFragmentQuiz()
            this.findNavController().navigate(direction)
        }

        return fragmentHomeBinding.root
    }


    private fun createAndOpenDatabase(){
        try{
            val helper = DatabaseCopyHelper(requireActivity())
            helper.createDataBase()
            helper.openDataBase()
        }
        catch(e : Exception){
            e.printStackTrace()
        }
    }
}