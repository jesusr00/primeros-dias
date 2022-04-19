package com.jesusr00.primerosdias.adapters

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jesusr00.primerosdias.ui.cloister.BrigadeCloisterFragment

class CloisterPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
): FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): BrigadeCloisterFragment {
        return BrigadeCloisterFragment.newInstance(position+1)
    }
}
