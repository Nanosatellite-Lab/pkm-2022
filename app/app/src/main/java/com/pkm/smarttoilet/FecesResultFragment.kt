package com.pkm.smarttoilet

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.pkm.smarttoilet.viewmodel.FecesResultViewModel


class FecesResultFragment : Fragment() {

    private val model: FecesResultViewModel by activityViewModels()
    private lateinit var pieFecesColorDataSet: PieDataSet
    private lateinit var fecesColorChart: PieChart
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
        val myText = getView()?.findViewById<TextView>(R.id.tv_feces_color)

        val fecesColorChart = getView()?.findViewById<PieChart>(R.id.pc_result_feces)
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
//        val h: Highlight = Highlight(0f, 0f, 0) // dataset index for piechart is always 0
//        fecesColorChart?.highlightValues(arrayOf(h))

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
        val myText = view?.findViewById<TextView>(R.id.tv_feces_color)
        model.myData.observe(viewLifecycleOwner) {
            myText?.text = it
            Log.d("TAG", "onViewCreated: $it")
        }
        model.fecesColorData.observe(viewLifecycleOwner) { it ->
            Log.d("TAG", "onResume: ${it.prediction.coklat}")

            /* chart code */
            val coklatFloat = it.prediction.coklat.toFloat()
            val hijauFloat = it.prediction.hijau.toFloat()
            val hitamFloat = it.prediction.hitam.toFloat()
            Log.d("TAG", "onResume: $coklatFloat, $hijauFloat, $hitamFloat")
            val fecesColorChart = view?.findViewById<PieChart>(R.id.pc_result_feces)
            pieFecesColorDataSet = PieDataSet(dataValues1(coklatFloat, hijauFloat, hitamFloat), "ok")
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
//            val h: Highlight = Highlight(0f, 0f, 0) // dataset index for piechart is always 0
//            fecesColorChart?.highlightValues(arrayOf(h))

            fecesColorChart?.invalidate()
            //animation
            fecesColorChart?.animateY(1500, Easing.EaseInOutQuad)

            /* end of chart code*/

            /* XML view integration*/

            val classes = floatArrayOf(coklatFloat,hijauFloat, hitamFloat)
            val classType = classes.indices.maxByOrNull { classes[it] } ?: -1

            //Feces Color
            val ivDominant = view?.findViewById<ImageView>(R.id.iv_dominant_color)
            val tvDominant = view?.findViewById<TextView>(R.id.tv_dominant_color_urine)
            val tvPercentagePC = view?.findViewById<TextView>(R.id.tv_percentage_feces_color)
            val tvDescFecesColor = view?.findViewById<TextView>(R.id.tv_urine_color_desc)

            when (classType){
                0 -> {
                    ivDominant?.setImageResource(R.color.brown_feces)
//                    ivDominant?.setBackgroundColor(Color.parseColor("#876445"))
                    tvDominant?.text = "Coklat"
                    val percent = coklatFloat*100
                    tvPercentagePC?.text = "${percent.toInt()}" + "%"
                    tvDescFecesColor?.text = resources.getString(R.string.desc_feces_brown)
                }
                1 -> {
                    ivDominant?.setImageResource(R.color.green_feces)
//                    ivDominant?.setBackgroundColor(Color.parseColor("#76BA99"))
                    tvDominant?.text = "Hijau"
                    val percent = hijauFloat*100
                    tvPercentagePC?.text = "${percent.toInt()}" + "%"
                    tvDescFecesColor?.text = resources.getString(R.string.desc_feces_green)
                }
                else -> {
                    ivDominant?.setImageResource(R.color.black_feces)
//                    ivDominant?.setBackgroundColor(Color.parseColor("#2C3333"))
                    tvDominant?.text = "Hitam"
                    val percent = hitamFloat*100
                    tvPercentagePC?.text = "${percent.toInt()}" + "%"
                    tvDescFecesColor?.text = resources.getString(R.string.desc_feces_black)
                }
            }
            model.feceFormData.observe(viewLifecycleOwner){
                val tipe1Float = it.prediction.tipe1.toFloat()
                val tipe2Float = it.prediction.tipe2.toFloat()
                val tipe3Float = it.prediction.tipe3.toFloat()
                val tipe4Float = it.prediction.tipe4.toFloat()
                val tipe5Float = it.prediction.tipe5.toFloat()
                val tipe6Float = it.prediction.tipe6.toFloat()

                val classesForm = floatArrayOf(tipe1Float,tipe2Float,tipe3Float, tipe4Float, tipe5Float, tipe6Float)
                val ivFecesForm = view?.findViewById<ImageView>(R.id.iv_feces_form)
                val tvFecesFormType = view?.findViewById<TextView>(R.id.tv_feces_form_type)
                val tvFecesFormDesc = view?.findViewById<TextView>(R.id.tv_feces_form_desc)
                when (classesForm.indices.maxByOrNull { classesForm[it] } ?: -1){
                    0 -> {
                        ivFecesForm?.setImageResource(R.drawable.tipe_1)
                        tvFecesFormDesc?.text = resources.getString(R.string.desc_feces_type_1)
                        tvFecesFormType?.text = "Tipe 1"
                    }
                    1 -> {
                        ivFecesForm?.setImageResource(R.drawable.tipe_2)
                        tvFecesFormDesc?.text = resources.getString(R.string.desc_feces_type_2)
                        tvFecesFormType?.text = "Tipe 2"
                    }
                    2 -> {
                        ivFecesForm?.setImageResource(R.drawable.tipe_3)
                        tvFecesFormDesc?.text = resources.getString(R.string.desc_feces_type_3)
                        tvFecesFormType?.text = "Tipe 3"
                    }
                    3 -> {
                        ivFecesForm?.setImageResource(R.drawable.tipe_4)
                        tvFecesFormDesc?.text = resources.getString(R.string.desc_feces_type_4)
                        tvFecesFormType?.text = "Tipe 4"
                    }
                    4 -> {
                        ivFecesForm?.setImageResource(R.drawable.tipe_5)
                        tvFecesFormDesc?.text = resources.getString(R.string.desc_feces_type_5)
                        tvFecesFormType?.text = "Tipe 5"
                    }
                    5 -> {
                        ivFecesForm?.setImageResource(R.drawable.tipe_6)
                        tvFecesFormDesc?.text = resources.getString(R.string.desc_feces_type_6)
                        tvFecesFormType?.text = "Tipe 6"
                    }
                }

            }



            /* End of view integration*/

        }
    }

    companion object {

    }
}