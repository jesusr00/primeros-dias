package com.jesusr00.primerosdias.ui.events

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.jesusr00.primerosdias.adapters.EventsPagerAdapter
import com.jesusr00.primerosdias.databinding.FragmentEventsBinding

class EventsFragment : Fragment() {

    private var _binding: FragmentEventsBinding? = null
    private val binding get() = _binding!!
    private var eventsPagerAdapter: EventsPagerAdapter? = null
    private var tabLayoutMediator: TabLayoutMediator? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEventsBinding.inflate(inflater, container, false)

        eventsPagerAdapter = EventsPagerAdapter(requireActivity().supportFragmentManager, lifecycle)
        binding.eventsVewPager.adapter = eventsPagerAdapter
        tabLayoutMediator = TabLayoutMediator(binding.eventsTabLayout, binding.eventsVewPager) { tab, position ->
            tab.text = eventsPagerAdapter?.getPageTitle(position)
        }
        tabLayoutMediator!!.attach()
        binding.eventsTabLayout.tabMode = TabLayout.MODE_SCROLLABLE;
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.eventsVewPager.adapter = null
        _binding = null
        eventsPagerAdapter = null
        tabLayoutMediator?.detach()
        tabLayoutMediator = null
    }
}