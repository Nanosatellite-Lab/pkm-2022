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
import com.github.mikephil.charting.charts.ScatterChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet


class FecesHistoryFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var monday: ArrayList<Entry>
    private lateinit var tuesday: ArrayList<Entry>
    private lateinit var wednesday: ArrayList<Entry>
    private lateinit var thursday: ArrayList<Entry>
    private lateinit var friday: ArrayList<Entry>
    private lateinit var saturday: ArrayList<Entry>
    private lateinit var sunday: ArrayList<Entry>

    private fun dataValues(): ArrayList<Entry>{
        val dataVals: ArrayList<Entry> = ArrayList()
        dataVals.add(Entry(1f,3f))
        dataVals.add(Entry(2f,2f))
        dataVals.add(Entry(3f,3f))
        dataVals.add(Entry(4f,3f))
        dataVals.add(Entry(5f, 2f))
        dataVals.add(Entry(6f, 3f))
        dataVals.add(Entry(7f, 4f))
        dataVals.add(Entry(8f, 3f))
        dataVals.add(Entry(9f, 3f))
        dataVals.add(Entry(10f, 4f))
        dataVals.add(Entry(11f, 3f))
        dataVals.add(Entry(12f, 2f))
        dataVals.add(Entry(13f, 3f))
        dataVals.add(Entry(14f, 4f))
        return dataVals
    }

    class MyXAxisFormatterColor : ValueFormatter() {
        private val days = arrayOf(" ", " ", "Hijau", "Coklat", "Hitam", " ")
        override fun getAxisLabel(value: Float, axis: AxisBase?): String {
            return days.getOrNull(value.toInt()) ?: value.toString()
        }
    }

    private fun dataValuesForm(): ArrayList<Entry>{
        val dataVals: ArrayList<Entry> = ArrayList()
        dataVals.add(Entry(1f,3f))
        dataVals.add(Entry(2f,3f))
        dataVals.add(Entry(3f,5f))
        dataVals.add(Entry(4f,5f))
        dataVals.add(Entry(5f, 1f))
        dataVals.add(Entry(6f, 3f))
        dataVals.add(Entry(7f, 4f))
        dataVals.add(Entry(8f, 3f))
        dataVals.add(Entry(9f, 2f))
        dataVals.add(Entry(10f, 4f))
        dataVals.add(Entry(11f, 6f))
        dataVals.add(Entry(12f, 2f))
        dataVals.add(Entry(13f, 3f))
        dataVals.add(Entry(14f, 5f))
        return dataVals
    }

    class MyXAxisFormatterForm : ValueFormatter() {
        private val days = arrayOf(" ","Tipe 1", "Tipe 2", "Tipe 3", "Tipe 4", "Tipe 5", "Tipe 6")
        override fun getAxisLabel(value: Float, axis: AxisBase?): String {
            return days.getOrNull(value.toInt()) ?: value.toString()
        }
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feces_history, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /* Feces Color chart */
        val fecesColorHistoryChart = getView()?.findViewById<LineChart>(R.id.sc_history_feces_color)
        val lineFecesColorDataset = LineDataSet(dataValues(),"ok")
        lineFecesColorDataset.setDrawFilled(true)
        lineFecesColorDataset.fillDrawable = ContextCompat.getDrawable(requireActivity(), R.drawable.gradient)
        val iLineDatasetFecesColor = ArrayList<ILineDataSet>()
        iLineDatasetFecesColor.add(lineFecesColorDataset)
        val lineFecesColorData = LineData(iLineDatasetFecesColor)
        fecesColorHistoryChart?.data = lineFecesColorData
//        fecesColorHistoryChart?.setVisibleXRangeMaximum(20f)
        fecesColorHistoryChart?.setMaxVisibleValueCount(1)
        fecesColorHistoryChart?.setVisibleXRange(0f,13f)

        fecesColorHistoryChart?.description?.isEnabled = false
        fecesColorHistoryChart?.setDrawGridBackground(false)
//        fecesColorHistoryChart?.xAxis?.isEnabled = false
//        fecesColorHistoryChart?.axisLeft?.isEnabled = false
        fecesColorHistoryChart?.axisRight?.isEnabled = false

//        fecesColorHistoryChart?.setVisibleYRange(0f,6f, YAxis.AxisDependency.RIGHT)
        fecesColorHistoryChart?.isDragXEnabled = false
        fecesColorHistoryChart?.isScaleXEnabled = false
//        fecesColorHistoryChart?.setDrawEntryLabels(false)
        fecesColorHistoryChart?.legend?.isEnabled = false


        // Controlling X axis
        val xAxis = fecesColorHistoryChart?.xAxis
        // Set the xAxis position to bottom. Default is top
        xAxis?.position = XAxis.XAxisPosition.BOTTOM

        //Customizing left axis value
        val leftAxis = fecesColorHistoryChart?.axisLeft
        leftAxis?.granularity = 1f // minimum axis-step (interval) is 1
        leftAxis?.valueFormatter = MyXAxisFormatterColor()

        fecesColorHistoryChart?.invalidate()
//        fecesColorHistoryChart?.animateY(1500,Easing.EaseInOutQuart)
        fecesColorHistoryChart?.animateX(3500,Easing.EaseInOutQuad)

        /* End feces color chart */
//        monday = ArrayList()
//        tuesday = ArrayList()
//        wednesday = ArrayList()
//        thursday = ArrayList()
//        friday = ArrayList()
//        saturday = ArrayList()
//        sunday = ArrayList()
//
//        monday.add(Entry(1f,1f))
//        monday.add(Entry(2f,1f))
//
//        tuesday.add(Entry(3f,1f))
//        tuesday.add(Entry(4f,2f))
//
//        wednesday.add(Entry(5f, 3f))
//        wednesday.add(Entry(6f, 2f))
//
//        thursday.add(Entry(7f, 2f))
//        thursday.add(Entry(8f, 1f))
//
//        friday.add(Entry(9f, 3f))
//        friday.add(Entry(10f, 2f))
//
//        saturday.add(Entry(11f, 2f))
//        saturday.add(Entry(121f, 2f))
//
//        sunday.add(Entry(13f, 2f))
//        sunday.add(Entry(14f, 1f))

        /* Feces form history chart*/
        val fecesFormHistoryChart = getView()?.findViewById<LineChart>(R.id.sc_history_feces_form)
        val lineFecesFormDataset = LineDataSet(dataValuesForm(),"ok")
        lineFecesFormDataset.color = Color.parseColor("#76BA99")
        lineFecesFormDataset.setDrawFilled(true)
        lineFecesFormDataset.setCircleColor(Color.parseColor("#76BA99"))
        lineFecesFormDataset.fillDrawable = ContextCompat.getDrawable(requireActivity(), R.drawable.gradient_feces_form)
        val iLineDatasetFecesForm = ArrayList<ILineDataSet>()
        iLineDatasetFecesForm.add(lineFecesFormDataset)
        val lineFecesFormData = LineData(iLineDatasetFecesForm)
        fecesFormHistoryChart?.data = lineFecesFormData
//        fecesColorHistoryChart?.setVisibleXRangeMaximum(20f)
        fecesFormHistoryChart?.setMaxVisibleValueCount(1)
        fecesFormHistoryChart?.setVisibleXRange(0f,13f)

        fecesFormHistoryChart?.description?.isEnabled = false
        fecesFormHistoryChart?.setDrawGridBackground(false)
//        fecesColorHistoryChart?.xAxis?.isEnabled = false
//        fecesColorHistoryChart?.axisLeft?.isEnabled = false
        fecesFormHistoryChart?.axisRight?.isEnabled = false

//        fecesColorHistoryChart?.setVisibleYRange(0f,6f, YAxis.AxisDependency.RIGHT)
        fecesFormHistoryChart?.isDragXEnabled = false
        fecesFormHistoryChart?.isScaleXEnabled = false
//        fecesColorHistoryChart?.setDrawEntryLabels(false)
        fecesFormHistoryChart?.legend?.isEnabled = false


        // Controlling X axis
        val xAxisForm = fecesFormHistoryChart?.xAxis
        // Set the xAxis position to bottom. Default is top
        xAxisForm?.position = XAxis.XAxisPosition.BOTTOM

        //Customizing left axis value
        val leftAxisForm = fecesFormHistoryChart?.axisLeft
        leftAxisForm?.granularity = 1f // minimum axis-step (interval) is 1
        leftAxisForm?.valueFormatter = MyXAxisFormatterForm()

        fecesFormHistoryChart?.invalidate()
//        fecesColorHistoryChart?.animateY(1500,Easing.EaseInOutQuart)
        fecesFormHistoryChart?.animateX(3500, Easing.EaseInOutQuad)
        /* End feces form history chart*/

    }

    companion object {

    }
}