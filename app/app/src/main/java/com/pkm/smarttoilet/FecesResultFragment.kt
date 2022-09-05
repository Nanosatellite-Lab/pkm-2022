package com.pkm.smarttoilet

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.highlight.Highlight
import com.pkm.smarttoilet.viewmodel.FecesResultViewModel


class FecesResultFragment : Fragment() {

    private val model: FecesResultViewModel by activityViewModels()
    private lateinit var pieFecesColorDataSet: PieDataSet
    private val colorClass: MutableList<Int> = mutableListOf(
        Color.parseColor("#876445"),
//        Color.parseColor("#D61C4E"),
//        Color.parseColor("#F2DF3A"),
//        Color.parseColor("#F7F7F7"),
        Color.parseColor("#76BA99"),
        Color.parseColor("#2C3333")
    )

    private fun dataValues1(coklat: Float, hijau: Float, hitam: Float ): ArrayList<PieEntry>? {
        val dataVals: ArrayList<PieEntry> = ArrayList()
        dataVals.add(PieEntry(coklat, "Coklat"))
//        dataVals.add(PieEntry(15f, "Merah"))
//        dataVals.add(PieEntry(15f, "Kuning"))
//        dataVals.add(PieEntry(15f, "Putih"))
        dataVals.add(PieEntry(hijau, "Hijau"))
        dataVals.add(PieEntry(hitam, "Hitam"))
        return dataVals
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_feces_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val myText = getView()?.findViewById<TextView>(R.id.text_test)

        val fecesColorChart = getView()?.findViewById<PieChart>(R.id.pc_result_urine)
        pieFecesColorDataSet = PieDataSet(dataValues1(30f, 30f, 30f), "ok")
        pieFecesColorDataSet.colors = colorClass
        pieFecesColorDataSet.sliceSpace = 2f
        pieFecesColorDataSet.valueTextSize = 8f
        pieFecesColorDataSet.setDrawValues(false)
//        pieColorDataSet.setDrawIcons(true)
//        val font = Typeface.createFromAsset(context?.assets, "font/quicksand_light.ttf")
//        val typeface = context?.let { ResourcesCompat.getFont(it, R.font.karla_light) };
//        pieColorDataSet.valueTypeface = font

        val pieDataColorFeces = PieData(pieFecesColorDataSet)

        fecesColorChart?.data = pieDataColorFeces
        fecesColorChart?.setDrawEntryLabels(false)
        fecesColorChart?.description?.isEnabled = false
        fecesColorChart?.setUsePercentValues(true)
        fecesColorChart?.setDrawMarkers(false)
        fecesColorChart?.holeRadius = 65f
        fecesColorChart?.transparentCircleRadius = 75f
        fecesColorChart?.isRotationEnabled = false
        fecesColorChart?.setDrawEntryLabels(false)
        fecesColorChart?.legend?.isEnabled = false
        val h: Highlight = Highlight(0f, 0f, 0) // dataset index for piechart is always 0
        fecesColorChart?.highlightValues(arrayOf(h))

        fecesColorChart?.invalidate()
        //animation
        fecesColorChart?.animateY(1500, Easing.EaseInOutQuad)
        model.fecesColorData.observe(viewLifecycleOwner) {
            if (it != null) {
                val coklatFloat = it.prediction.coklat.toFloat()
                val hijauFloat = it.prediction.hijau.toFloat()
                val hitamFloat = it.prediction.hitam.toFloat()
                pieFecesColorDataSet = PieDataSet(dataValues1(coklatFloat, hijauFloat, hitamFloat), "ok")
            }
        }
    }

    override fun onResume() {
        super.onResume()
        val myText = view?.findViewById<TextView>(R.id.text_test)
        model.myData.observe(viewLifecycleOwner) {
            myText?.text = it
            Log.d("TAG", "onViewCreated: $it")
        }
        model.fecesColorData.observe(viewLifecycleOwner) {
            if (it != null) {
                val coklatFloat = it.prediction.coklat.toFloat()
                val hijauFloat = it.prediction.hijau.toFloat()
                val hitamFloat = it.prediction.hitam.toFloat()
                pieFecesColorDataSet = PieDataSet(dataValues1(coklatFloat, hijauFloat, hitamFloat), "ok")
            }
        }
    }

    companion object {

    }
}