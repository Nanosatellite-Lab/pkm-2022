package com.pkm.smarttoilet

import android.os.Bundle
import android.text.format.DateFormat
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.pkm.smarttoilet.adapter.FecesSectionPagerAdapter
import com.pkm.smarttoilet.adapter.UrineSectionPagerAdapter
import com.pkm.smarttoilet.viewmodel.FecesResultViewModel
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var hasilDeteksi: TextView
    private lateinit var fecesResultViewModel: FecesResultViewModel
    var num: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        val fecesSectionPagerAdapter = FecesSectionPagerAdapter(this)
        val fecesViewPager: ViewPager2 = findViewById(R.id.view_pager_feces)
        fecesViewPager.adapter = fecesSectionPagerAdapter
        val tabsFeces: TabLayout = findViewById(R.id.tab_layout_feces)
        TabLayoutMediator(tabsFeces, fecesViewPager){ tab, position->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        val urineSectionPagerAdapter = UrineSectionPagerAdapter(this)
        val urineViewPager: ViewPager2 = findViewById(R.id.view_pager_urine)
        urineViewPager.adapter = urineSectionPagerAdapter
        val tabsUrine: TabLayout = findViewById(R.id.tab_layout_urine)
        TabLayoutMediator(tabsUrine, urineViewPager){ tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        fecesResultViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[FecesResultViewModel::class.java]
        hasilDeteksi = findViewById(R.id.tv_result)
        swipeRefresh = findViewById(R.id.refresh_layout)

        val updateTimeFeces = findViewById<TextView>(R.id.tv_date_updated)

        swipeRefresh.setOnRefreshListener {
            val df = SimpleDateFormat("yyyy-MM-dd hh:mm:ss a", Locale.US)
            val time: String = df.format(Date())
            updateTimeFeces.text = "Last Updated: $time"
            swipeRefresh.isRefreshing = false

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