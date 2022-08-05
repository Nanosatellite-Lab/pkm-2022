package com.pkm.smarttoilet

import android.os.Bundle
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.pkm.smarttoilet.adapter.SectionPagerAdapter


class MainActivity : AppCompatActivity() {
    private lateinit var swipeRefreh: SwipeRefreshLayout
    private lateinit var hasilDeteski: TextView
    var num: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        val sectionPagerAdapter = SectionPagerAdapter(this)
        val viewPager: ViewPager2 = findViewById(R.id.view_pager_feces)
        viewPager.adapter = sectionPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tab_layout_feces)
        TabLayoutMediator(tabs, viewPager){tab, position->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        hasilDeteski = findViewById(R.id.tv_result)
        swipeRefreh = findViewById(R.id.refresh_layout)
        swipeRefreh.setOnRefreshListener {
            num++
            hasilDeteski.text = num.toString()
            swipeRefreh.isRefreshing = false
        }


    }

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text_feces_result,
            R.string.tab_text_feces_tips
        )
    }

}