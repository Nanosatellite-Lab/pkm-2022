package com.pkm.smarttoilet.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.pkm.smarttoilet.UrineResultFragment
import com.pkm.smarttoilet.UrineTipsFragment

class UrineSectionPagerAdapter (activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position){
            0 -> {
                fragment = UrineResultFragment()
            }
            1 -> {
                fragment = UrineTipsFragment()
            }
        }
        return fragment as Fragment
    }
}