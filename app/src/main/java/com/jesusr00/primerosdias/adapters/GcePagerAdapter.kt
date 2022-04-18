package com.jesusr00.primerosdias.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jesusr00.primerosdias.ui.scientific_groups.GceActivitiesFragment
import com.jesusr00.primerosdias.ui.scientific_groups.GceEventsFragment
import com.jesusr00.primerosdias.ui.scientific_groups.GceFragment

class GcePagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
): FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> GceFragment()
            1 -> GceActivitiesFragment()
            2 -> GceEventsFragment()
            else -> GceFragment()
        }
    }
}