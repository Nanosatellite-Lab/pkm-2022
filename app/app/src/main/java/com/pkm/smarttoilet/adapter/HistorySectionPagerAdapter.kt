package com.pkm.smarttoilet.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.pkm.smarttoilet.FecesHistoryFragment
import com.pkm.smarttoilet.UrineHistoryFragment

class HistorySectionPagerAdapter (activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position){
            0 -> {
                fragment = FecesHistoryFragment()
            }
            1 -> {
                fragment = UrineHistoryFragment()
            }
        }
        return fragment as Fragment
    }
}