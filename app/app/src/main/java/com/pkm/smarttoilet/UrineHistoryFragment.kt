package com.pkm.smarttoilet

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet

class UrineHistoryFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    private fun dataValuesUrine(): ArrayList<Entry>{
        val dataVals: ArrayList<Entry> = ArrayList()
        dataVals.add(Entry(1f,4f))
        dataVals.add(Entry(2f,2f))
        dataVals.add(Entry(3f,3f))
        dataVals.add(Entry(4f,3f))
        dataVals.add(Entry(5f, 2f))
        dataVals.add(Entry(6f, 3f))
        dataVals.add(Entry(7f, 4f))
        dataVals.add(Entry(8f, 3f))
        dataVals.add(Entry(9f, 3f))
        dataVals.add(Entry(10f, 4f))
        dataVals.add(Entry(11f, 4f))
        dataVals.add(Entry(12f, 2f))
        dataVals.add(Entry(13f, 3f))
        dataVals.add(Entry(14f, 4f))
        dataVals.add(Entry(15f,3f))
        dataVals.add(Entry(16f,3f))
        dataVals.add(Entry(17f,3f))
        dataVals.add(Entry(18f,3f))
        dataVals.add(Entry(19f, 2f))
        dataVals.add(Entry(20f, 3f))
        dataVals.add(Entry(21f, 4f))
//        dataVals.add(Entry(22f, 3f))
//        dataVals.add(Entry(23f, 3f))
//        dataVals.add(Entry(24f, 4f))
//        dataVals.add(Entry(25f, 3f))
//        dataVals.add(Entry(26f, 3f))
//        dataVals.add(Entry(27f, 3f))
//        dataVals.add(Entry(28f, 4f))
//        dataVals.add(Entry(29f,2f))
//        dataVals.add(Entry(30f,2f))
//        dataVals.add(Entry(31f,3f))
//        dataVals.add(Entry(32f, 2f))
//        dataVals.add(Entry(33f, 4f))
//        dataVals.add(Entry(34f, 4f))
//        dataVals.add(Entry(35f, 3f))
//        dataVals.add(Entry(36f, 3f))
//        dataVals.add(Entry(37f, 4f))
//        dataVals.add(Entry(38f, 3f))
//        dataVals.add(Entry(39f, 3f))
//        dataVals.add(Entry(40f, 3f))
//        dataVals.add(Entry(41f, 4f))
//        dataVals.add(Entry(42f, 4f))
        return dataVals
    }

    class MyXAxisFormatterUrineColor : ValueFormatter() {
        private val days = arrayOf(" ", " ", "Hijau", "Bening", "Kuning", " ")
        override fun getAxisLabel(value: Float, axis: AxisBase?): String {
            return days.getOrNull(value.toInt()) ?: value.toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_urine_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /* Urine Color chart */
        val urineColorHistoryChart = getView()?.findViewById<LineChart>(R.id.sc_history_urine_color)
        val lineUrineColorDataset = LineDataSet(dataValuesUrine(),"ok")
        lineUrineColorDataset.setDrawFilled(true)
        lineUrineColorDataset.color = Color.parseColor("#F2DF3A")
        lineUrineColorDataset.setCircleColor(Color.parseColor("#F2DF3A"))
        lineUrineColorDataset.fillDrawable = ContextCompat.getDrawable(requireActivity(), R.drawable.gradient_urine)
        val iLineDatasetUrineColor = ArrayList<ILineDataSet>()
        iLineDatasetUrineColor.add(lineUrineColorDataset)
        val lineUrineColorData = LineData(iLineDatasetUrineColor)
        urineColorHistoryChart?.data = lineUrineColorData
//        fecesColorHistoryChart?.setVisibleXRangeMaximum(20f)
        urineColorHistoryChart?.setMaxVisibleValueCount(1)
        urineColorHistoryChart?.setVisibleXRange(0f,21f)

        urineColorHistoryChart?.description?.isEnabled = false
        urineColorHistoryChart?.setDrawGridBackground(false)
//        fecesColorHistoryChart?.xAxis?.isEnabled = false
//        fecesColorHistoryChart?.axisLeft?.isEnabled = false
        urineColorHistoryChart?.axisRight?.isEnabled = false

//        fecesColorHistoryChart?.setVisibleYRange(0f,6f, YAxis.AxisDependency.RIGHT)
        urineColorHistoryChart?.isDragXEnabled = false
        urineColorHistoryChart?.isScaleXEnabled = false
//        fecesColorHistoryChart?.setDrawEntryLabels(false)
        urineColorHistoryChart?.legend?.isEnabled = false


        // Controlling X axis
        val xAxis = urineColorHistoryChart?.xAxis
        // Set the xAxis position to bottom. Default is top
        xAxis?.position = XAxis.XAxisPosition.BOTTOM

        //Customizing left axis value
        val leftAxis = urineColorHistoryChart?.axisLeft
        leftAxis?.granularity = 1f // minimum axis-step (interval) is 1
        leftAxis?.valueFormatter = MyXAxisFormatterUrineColor()

        urineColorHistoryChart?.invalidate()
//        fecesColorHistoryChart?.animateY(1500,Easing.EaseInOutQuart)
        urineColorHistoryChart?.animateX(3500, Easing.EaseInOutQuad)

        /* End feces color chart */
    }

    companion object {

    }
}