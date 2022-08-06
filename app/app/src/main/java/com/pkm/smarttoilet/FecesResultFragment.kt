package com.pkm.smarttoilet

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.highlight.Highlight
import com.pkm.smarttoilet.viewmodel.FecesResultViewModel


class FecesResultFragment : Fragment() {

    private lateinit var fecesResultViewModel: FecesResultViewModel
    private val model: FecesResultViewModel by activityViewModels()
    private val colorClass: MutableList<Int> = mutableListOf(Color.parseColor("#876445"), Color.parseColor("#D61C4E"), Color.parseColor("#F2DF3A"), Color.parseColor("#F7F7F7"), Color.parseColor("#76BA99"), Color.parseColor("#2C3333"))

    private fun dataValues1(): ArrayList<PieEntry>? {
        val dataVals: ArrayList<PieEntry> = ArrayList()
        dataVals.add(PieEntry(50f, "Coklat"))
        dataVals.add(PieEntry(15f, "Merah"))
        dataVals.add(PieEntry(15f, "Kuning"))
        dataVals.add(PieEntry(15f, "Putih"))
        dataVals.add(PieEntry(15f, "Hijau"))
        dataVals.add(PieEntry(15f, "Hitam"))
//        dataVals.add(PieEntry(50f, ""))
//        dataVals.add(PieEntry(15f, ""))
//        dataVals.add(PieEntry(15f, ""))
//        dataVals.add(PieEntry(15f, ""))
//        dataVals.add(PieEntry(15f, ""))
//        dataVals.add(PieEntry(15f, ""))
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

        val fecesColorChart = getView()?.findViewById<PieChart>(R.id.pc_result_feces)
        val pieColorDataSet = PieDataSet(dataValues1(),"ok")
        pieColorDataSet.colors = colorClass
        pieColorDataSet.sliceSpace = 2f
        pieColorDataSet.valueTextSize = 8f
        pieColorDataSet.setDrawValues(false)
//        pieColorDataSet.setDrawIcons(true)
//        val font = Typeface.createFromAsset(context?.assets, "font/quicksand_light.ttf")
//        val typeface = context?.let { ResourcesCompat.getFont(it, R.font.karla_light) };
//        pieColorDataSet.valueTypeface = font

        val pieDataColorFeces = PieData(pieColorDataSet)

        fecesColorChart?.data = pieDataColorFeces
        fecesColorChart?.setDrawEntryLabels(false)
        fecesColorChart?.description?.isEnabled = false
        fecesColorChart?.setUsePercentValues(true)
        fecesColorChart?.setDrawMarkers(false)
        fecesColorChart?.holeRadius = 65f
        fecesColorChart?.transparentCircleRadius =  75f
        fecesColorChart?.isRotationEnabled = false
        fecesColorChart?.setDrawEntryLabels(false)
        fecesColorChart?.legend?.isEnabled = false
        val h: Highlight = Highlight(0f,0f, 0) // dataset index for piechart is always 0
        fecesColorChart?.highlightValues(arrayOf(h))

        fecesColorChart?.invalidate()

        fecesColorChart?.animateY(1400, Easing.EaseInOutQuad)

        Log.d("TAG1", "onViewCreated: ${R.color.yellow_feces.toString()} ${Color.RED} ")


    }

    override fun onResume() {
        super.onResume()
        val myText = view?.findViewById<TextView>(R.id.text_test)
        model.myData.observe(viewLifecycleOwner){
            myText?.text = it
            Log.d("TAG", "onViewCreated: $it")
        }
    }

    override fun onPause() {
        super.onPause()

    }


    fun changeText(){
        val myText = view?.findViewById<TextView>(R.id.text_test)
        fecesResultViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[FecesResultViewModel::class.java]
        fecesResultViewModel.myData.observe(viewLifecycleOwner){
            myText?.text = it
            Log.d("TAG", "onViewCreated: $it")
        }
    }
    companion object {

    }
}