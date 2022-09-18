package com.pkm.smarttoilet

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.pkm.smarttoilet.viewmodel.UrineResultViewModel

class UrineResultFragment : Fragment() {

    private val model: UrineResultViewModel by activityViewModels()
    private val colorClass: MutableList<Int> = mutableListOf(
//        Color.parseColor("#876445"),
//        Color.parseColor("#D61C4E"),
        Color.parseColor("#F2DF3A"),
        Color.parseColor("#F7F7F7"),
        Color.parseColor("#76BA99"),
//        Color.parseColor("#2C3333")
    )

    private fun dataValues1(kuning: Float, putih: Float, hijau:Float): ArrayList<PieEntry>? {
        val dataVals: ArrayList<PieEntry> = ArrayList()
//        dataVals.add(PieEntry(100f, "Coklat"))
//        dataVals.add(PieEntry(15f, "Merah"))
        dataVals.add(PieEntry(kuning, "Kuning"))
        dataVals.add(PieEntry(putih, "Putih"))
        dataVals.add(PieEntry(hijau, "Hijau"))
//        dataVals.add(PieEntry(15f, "Hitam"))
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

        val urineColorChart = getView()?.findViewById<PieChart>(R.id.pc_result_feces)
        val pieUrineColorDataSet = PieDataSet(dataValues1(30f,30f,30f), "ok")
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
        urineColorChart?.transparentCircleRadius = 75f
        urineColorChart?.isRotationEnabled = false
        urineColorChart?.setDrawEntryLabels(false)
        urineColorChart?.legend?.isEnabled = false
//        val h: Highlight = Highlight(0f, 0f, 0) // dataset index for piechart is always 0
//        urineColorChart?.highlightValues(arrayOf(h))

        urineColorChart?.invalidate()
        //animation
        urineColorChart?.animateY(1500, Easing.EaseInOutQuad)
    }

    override fun onResume() {
        super.onResume()

        model.urineColorData.observe(viewLifecycleOwner){
            Log.d("TAG", "onResume: $it")
            val beningFloat = it.prediction.bening.toFloat()
            val hijauFloat = it.prediction.hijau.toFloat()
            val kuningFloat = it.prediction.kuning.toFloat()

            /* Chart code */
            val urineColorChart = view?.findViewById<PieChart>(R.id.pc_result_feces)
            val pieUrineColorDataSet = PieDataSet(dataValues1(kuningFloat,beningFloat,hijauFloat), "ok")
            pieUrineColorDataSet.colors = colorClass
            pieUrineColorDataSet.sliceSpace = 2f
            pieUrineColorDataSet.valueTextSize = 8f
            pieUrineColorDataSet.setDrawValues(false)

            val pieDataColorUrine = PieData(pieUrineColorDataSet)

            urineColorChart?.data = pieDataColorUrine
            urineColorChart?.setDrawEntryLabels(false)
            urineColorChart?.description?.isEnabled = false
            urineColorChart?.setUsePercentValues(true)
            urineColorChart?.setDrawMarkers(false)
            urineColorChart?.holeRadius = 65f
            urineColorChart?.transparentCircleRadius = 75f
            urineColorChart?.isRotationEnabled = false
            urineColorChart?.setDrawEntryLabels(false)
            urineColorChart?.legend?.isEnabled = false
//        val h: Highlight = Highlight(0f, 0f, 0) // dataset index for piechart is always 0
//        urineColorChart?.highlightValues(arrayOf(h))

            urineColorChart?.invalidate()
            //animation
            urineColorChart?.animateY(1500, Easing.EaseInOutQuad)

            /* End of chart code */

            /*XML view integration*/
            val classes = floatArrayOf(beningFloat, hijauFloat, kuningFloat)
            val classType = classes.indices.maxByOrNull { classes[it] } ?: -1

            val tvPercentageUrineColor = view?.findViewById<TextView>(R.id.tv_percentage_urine_color)
            val tvDominantColor = view?.findViewById<TextView>(R.id.tv_dominant_color_urine)
            val tvDescUrineColor = view?.findViewById<TextView>(R.id.tv_urine_color_desc)
            val ivDominant = view?.findViewById<ImageView>(R.id.iv_dominant_color)

            when (classType){
                0 -> {
                    val percent = beningFloat*100
                    tvPercentageUrineColor?.text = "${percent.toInt()}"
                    tvDominantColor?.text = "Bening"
                    tvDescUrineColor?.text = resources.getString(R.string.desc_urine_white)
                    ivDominant?.setImageResource(R.color.white_feces)
                }
                1 -> {
                    val percent = hijauFloat*100
                    tvPercentageUrineColor?.text = "${percent.toInt()}"
                    tvDominantColor?.text = "Hijau"
                    tvDescUrineColor?.text = resources.getString(R.string.desc_urine_green)
                    ivDominant?.setImageResource(R.color.green_feces)
                }
                2 -> {
                    val percent = kuningFloat*100
                    tvPercentageUrineColor?.text = "${percent.toInt()}"
                    tvDominantColor?.text = "Kuning"
                    tvDescUrineColor?.text = resources.getString(R.string.desc_urine_yellow)
                    ivDominant?.setImageResource(R.color.yellow_feces)
                }
            }

            /*End of XML view integration*/


        }

    }

    companion object {

    }

}