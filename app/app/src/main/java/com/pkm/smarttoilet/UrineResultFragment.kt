package com.pkm.smarttoilet

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.highlight.Highlight

class UrineResultFragment : Fragment() {

    private val colorClass: MutableList<Int> = mutableListOf(Color.parseColor("#876445"), Color.parseColor("#D61C4E"), Color.parseColor("#F2DF3A"), Color.parseColor("#F7F7F7"), Color.parseColor("#76BA99"), Color.parseColor("#2C3333"))
    private fun dataValues1(): ArrayList<PieEntry>? {
        val dataVals: ArrayList<PieEntry> = ArrayList()
        dataVals.add(PieEntry(100f, "Coklat"))
        dataVals.add(PieEntry(15f, "Merah"))
        dataVals.add(PieEntry(15f, "Kuning"))
        dataVals.add(PieEntry(15f, "Putih"))
        dataVals.add(PieEntry(15f, "Hijau"))
        dataVals.add(PieEntry(15f, "Hitam"))
        return dataVals
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_urine_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val urineColorChart = getView()?.findViewById<PieChart>(R.id.pc_result_urine)
        val pieUrineColorDataSet = PieDataSet(dataValues1(),"ok")
        pieUrineColorDataSet.colors = colorClass
        pieUrineColorDataSet.sliceSpace = 2f
        pieUrineColorDataSet.valueTextSize = 8f
        pieUrineColorDataSet.setDrawValues(false)
//        pieColorDataSet.setDrawIcons(true)
//        val font = Typeface.createFromAsset(context?.assets, "font/quicksand_light.ttf")
//        val typeface = context?.let { ResourcesCompat.getFont(it, R.font.karla_light) };
//        pieColorDataSet.valueTypeface = font

        val pieDataColorUrine = PieData(pieUrineColorDataSet)

        urineColorChart?.data = pieDataColorUrine
        urineColorChart?.setDrawEntryLabels(false)
        urineColorChart?.description?.isEnabled = false
        urineColorChart?.setUsePercentValues(true)
        urineColorChart?.setDrawMarkers(false)
        urineColorChart?.holeRadius = 65f
        urineColorChart?.transparentCircleRadius =  75f
        urineColorChart?.isRotationEnabled = false
        urineColorChart?.setDrawEntryLabels(false)
        urineColorChart?.legend?.isEnabled = false
        val h: Highlight = Highlight(0f,0f, 0) // dataset index for piechart is always 0
        urineColorChart?.highlightValues(arrayOf(h))

        urineColorChart?.invalidate()
        //animation
        urineColorChart?.animateY(1500, Easing.EaseInOutQuad)
    }

    override fun onResume() {
        super.onResume()

    }

    companion object {

    }

}