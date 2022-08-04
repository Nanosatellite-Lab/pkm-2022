package com.pkm.smarttoilet.adapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.pkm.smarttoilet.FecesResultFragment
import com.pkm.smarttoilet.FecesTipsFragment

class SectionPagerAdapter (activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position){
            0 -> {
                fragment = FecesResultFragment()
            }
            1 -> {
                fragment = FecesTipsFragment()
            }
        }
        return fragment as Fragment
    }
}