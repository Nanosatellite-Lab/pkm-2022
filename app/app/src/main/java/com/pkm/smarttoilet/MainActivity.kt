package com.pkm.smarttoilet

import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.pkm.smarttoilet.adapter.SectionPagerAdapter
import com.pkm.smarttoilet.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var tips: TextView
    private lateinit var result: TextView
    private lateinit var select: TextView
    private lateinit var def: ColorStateList
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)

//        tips = findViewById(R.id.tv_tips_tab)
//        result = findViewById(R.id.tv_result_tab)
//
//
//
//        select = findViewById(R.id.select)
//        def = tips.textColors

        val sectionPagerAdapter = SectionPagerAdapter(this)
        val viewPager: ViewPager2 = findViewById(R.id.view_pager_feces)
        viewPager.adapter = sectionPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tab_layout_feces)
        TabLayoutMediator(tabs, viewPager){tab, position->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

//        tips.setOnClickListener {
//            tips.setTextColor(resources.getColor(R.color.red))
//            result.setTextColor(resources.getColor(R.color.grey))
//            val size: Int = tips.width
//            select.animate().x(size.toFloat()-3).duration = 200
//        }
//        result.setOnClickListener {
//            select.animate().x(0f).duration = 200
//            tips.setTextColor(resources.getColor(R.color.grey))
//            result.setTextColor(resources.getColor(R.color.red))
//        }

    }

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_feces_result,
            R.string.tab_text_feces_tips
        )
    }

}