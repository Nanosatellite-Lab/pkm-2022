package com.pkm.smarttoilet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.pkm.smarttoilet.adapter.HistorySectionPagerAdapter

class HistoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        supportActionBar?.title = "Riwayat"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val historySectionPagerAdapter = HistorySectionPagerAdapter(this)
        val historyViewPager: ViewPager2 = findViewById(R.id.view_pager_history)
        historyViewPager.adapter = historySectionPagerAdapter
        val tabsHistory: TabLayout = findViewById(R.id.tl_history)
        TabLayoutMediator(tabsHistory, historyViewPager){ tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
    }


    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_feces_history,
            R.string.tab_urine_history
        )
    }
}