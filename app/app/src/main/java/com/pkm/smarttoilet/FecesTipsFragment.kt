package com.pkm.smarttoilet

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.pkm.smarttoilet.viewmodel.FecesResultViewModel

class FecesTipsFragment : Fragment() {

    private val model: FecesResultViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feces_tips, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onResume() {
        super.onResume()

        val tipsFeces = view?.findViewById<TextView>(R.id.tv_tips_feces)

        model.fecesColorData.observe(viewLifecycleOwner){
            val coklatFloat = it.prediction.coklat.toFloat()
            val hijauFloat = it.prediction.hijau.toFloat()
            val hitamFloat = it.prediction.hitam.toFloat()
            val classes = floatArrayOf(coklatFloat,hijauFloat, hitamFloat)
            val classType = classes.indices.maxByOrNull { classes[it] } ?: -1
            val tipsColor = when (classType){
                0 -> {
                    resources.getString(R.string.tips_feces_color_brown)
                }
                1 -> {
                    resources.getString(R.string.tips_feces_color_green)
                }
                else -> {
                    resources.getString(R.string.tips_feces_color_black)
                }
            }

            val tipsForm: String = resources.getString(R.string.tips_feces_type_1)

            tipsFeces?.text = tipsColor + "\n" + tipsForm
            //Feces color


        }
    }

    companion object {

    }
}