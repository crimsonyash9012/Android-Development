package com.tiramisu.flagquiz.view

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.tiramisu.flagquiz.R
import com.tiramisu.flagquiz.databinding.FragmentResultBinding

class FragmentResult : Fragment() {

    lateinit var fragmentResultBinding: FragmentResultBinding
    var correctNumber : Float = 0F
    var emptyNumber : Float = 0F
    var wrongNumber : Float = 0F

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        fragmentResultBinding = FragmentResultBinding.inflate(inflater, container, false)

        val args = arguments?.let {
            FragmentResultArgs.fromBundle(it)
        }

        args?.let{
            correctNumber = it.correct.toFloat()
            wrongNumber = it.wrong.toFloat()
            emptyNumber = it.empty.toFloat()
        }

        val barEntriesCorrect = ArrayList<BarEntry>()
        val barEntriesEmpty = ArrayList<BarEntry>()
        val barEntriesWrong = ArrayList<BarEntry>()

        barEntriesCorrect.add(BarEntry(0F,correctNumber))
        barEntriesEmpty.add(BarEntry(1F,emptyNumber))
        barEntriesWrong.add(BarEntry(2F,wrongNumber))

        val barDataSetCorrect = BarDataSet(barEntriesCorrect, "Correct Number").apply {
            color = Color.GREEN
            valueTextSize = 24F
            setValueTextColors(arrayListOf(Color.BLACK))
        }
        val barDataSetEmpty = BarDataSet(barEntriesEmpty, "Empty Number").apply {
            color = Color.BLUE
            valueTextSize = 24F
            setValueTextColors(arrayListOf(Color.BLACK))
        }
        val barDataSetWrong = BarDataSet(barEntriesWrong, "Wrong Number").apply {
            color = Color.RED
            valueTextSize = 24F
            setValueTextColors(arrayListOf(Color.BLACK))
        }

        val barData = BarData(barDataSetCorrect, barDataSetEmpty, barDataSetWrong)
        fragmentResultBinding.resultChart.data = barData

        fragmentResultBinding.resultChart.xAxis.apply {
            position = XAxis.XAxisPosition.BOTTOM
            granularity = 1F
            setDrawGridLines(false)
        }

        fragmentResultBinding.buttonNextQuiz.setOnClickListener {
            this.findNavController().popBackStack(R.id.fragmentHome, false)

        }

        fragmentResultBinding.buttonExit.setOnClickListener {
            requireActivity().finish()
        }


        return fragmentResultBinding.root
    }
}