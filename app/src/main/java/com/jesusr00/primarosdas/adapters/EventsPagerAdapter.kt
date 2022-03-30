package com.jesusr00.primarosdas.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jesusr00.primarosdas.ui.events.DayDetailsFragment

class EventsPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = 5

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> DayDetailsFragment.newInstance(1)
            1 -> DayDetailsFragment.newInstance(2)
            2 -> DayDetailsFragment.newInstance(3)
            3 -> DayDetailsFragment.newInstance(4)
            4 -> DayDetailsFragment.newInstance(5)
            else -> DayDetailsFragment.newInstance(6)
        }
    }

    fun getPageTitle(position: Int): String {
        return when (position) {
            0 -> "Lunes"
            1 -> "Martes"
            2 -> "Miercoles"
            3 -> "Jueves"
            4 -> "Viernes"
            else -> "Unknown"
        }
    }
}